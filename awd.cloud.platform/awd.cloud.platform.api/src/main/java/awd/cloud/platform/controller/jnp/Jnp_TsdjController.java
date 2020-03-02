/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_TsdjModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/tsdj")
@Api(tags = "jnp-tsdj", description = "Tsdj")
public class Jnp_TsdjController extends PublicService {

    @Autowired
    private KssService kssService;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jnp/tsdj/tsdjQuery 提审登记查询
     * @apiVersion 0.4.0
     * @apiName tsdjQuery
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 提审登记查询.
     *
     * @apiParam {String} appcode 						应用代码(必传)
     * @apiParam {String} jsbh 							监所编号(必传)
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} bnryy         				办案人员姓名1
     * @apiSuccess {String} xbString         			性别(已转换)
     * @apiSuccess {String} kssjString         			提审开始时间
     * @apiSuccess {String} rybh         				被监管人员编号
     * @apiSuccess {String} xb         				    性别
     * @apiSuccess {String} jssjString         			提审结束时间
     * @apiSuccess {String} barye         				办案人员姓名2
     * @apiSuccess {String} jsh         				监室号
     * @apiSuccess {String} xm         					姓名
     * @apiSuccess {Int} nl         				    年龄
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "baryy": "123",
     *         "xbString": "女性",
     *         "xm": "图片1",
     *         "kssjString": "2019-12-20 18:56:35",
     *         "rybh": "110000114201908200004",
     *         "xb": "2",
     *         "jssjString": "2019-12-21 17:13:16",
     *         "barye": "123",
     *         "jsh": "0101",
     *         "nl": 65
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576472621798
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "jsh":"监室号(最大长度:4)",
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     */
    @OpenAPI
    @ApiOperation("提审登记查询")
    @GetMapping("/tsdjQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> tsdj_query(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/tsdj/tsdjQuery";
        String state = request.getParameter("state");

        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }


            //查询参数
            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
//            if (!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//                param.and("user", TermType.eq, maps.getResult().get("user"));
//            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.txQuery(param);
            System.err.println("result" + JSON.toJSONString(result));


            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));
            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }



    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> tsdj_save(@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_TsdjModel data) {
        return kssService.tsdj_save(data);
    }



    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> tsdj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_TsdjModel data) {
        return kssService.tsdj_updateByKey(id, data);
    }


    @OpenAPI
    public ResponseMessage<Kss_TsdjModel> tsdj_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.tsdj_getByKey(id);
    }



    @OpenAPI
    public ResponseMessage<Integer> tsdj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.tsdj_deleteByKey(id);
    }
}
