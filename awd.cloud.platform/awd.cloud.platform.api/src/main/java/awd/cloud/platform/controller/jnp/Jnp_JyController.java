/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_JyModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
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
@RequestMapping("/v4/jnp/jy")
@Api(tags = "jnp-jy", description = "Jy")
public class Jnp_JyController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jnp/jy/jyQuery 就医记录查询
     * @apiVersion 0.4.0
     * @apiName jyjlQuery
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 就医记录查询.
     *
     * @apiParam {String} appcode 						应用代码(必传)
     * @apiParam {String} jsbh 							监所编号(必传)
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} zdrqString         			诊断日期(格式化)
     * @apiSuccess {String} jzyy         				就诊医院
     * @apiSuccess {String} cljg         				处理结果
     * @apiSuccess {String} xb         				    性别
     * @apiSuccess {String} bhlxString         			病号类型(已转换)
     * @apiSuccess {String} ly         				    来源(字典)
     * @apiSuccess {String} brbq         				病人病情
     * @apiSuccess {String} xbString         			性别(已转换)
     * @apiSuccess {String} xm         				    姓名
     * @apiSuccess {String} lyString         			来源(已转换)
     * @apiSuccess {String} ptmj         				陪同民警
     * @apiSuccess {String} ysxm         				医生姓名
     * @apiSuccess {String} rybh         				人员编号
     * @apiSuccess {String} bz         				    备注
     * @apiSuccess {String} je         				    用户余额
     * @apiSuccess {String} zdqk         				医生诊断情况
     * @apiSuccess {String} bhlx         				病号类型(字典)
     * @apiSuccess {String} jsh         				监室号
     * @apiSuccess {String} nl         				    年龄
     * @apiSuccess {String} message         				                 返回信息
     * @apiSuccess {String} result         				                     返回结果
     * @apiSuccess {String} total         				                     返回数量
     * @apiSuccess {String} date         				                     返回数据
     * @apiSuccess {String} page         				                     返回页数
     * @apiSuccess {String} status         				                     返回状态
     * @apiSuccess {String} timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "zdrqString": "2019-11-05",
     *         "jzyy": "3就诊医院",
     *         "cljg": "3处理结果",
     *         "xb": "1",
     *         "bhlxString": "普通病号",
     *         "ly": "4",
     *         "brbq": "3病情",
     *         "xbString": "男性",
     *         "xm": "张海龙",
     *         "lyString": "自述伤病",
     *         "ptmj": "3陪同民警",
     *         "ysxm": "3医生",
     *         "rybh": "110000114201911050003",
     *         "bz": "3备注",
     *         "je": 500,
     *         "zdqk": "3诊断",
     *         "bhlx": "3",
     *         "jsh": "0324",
     *         "nl": "34"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576486015789
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
    @ApiOperation("分页查询")
    @GetMapping("jyQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jy_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/jy/jyQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam queryParam = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                queryParam.and("jsbh", TermType.eq, jsbh);
            }
            /*
             * if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
             * queryParam.and("user", TermType.eq, maps.getResult().get("user")); }
             */
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                queryParam.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                queryParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jyByjbxxQueryForPage(queryParam);
            System.err.println("result--------------------"+ JSON.toJSONString(result));

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
    public ResponseMessage<String> jy_save(@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_JyModel data) {
        //return kssService.jy_save(data);\
        return null;
    }



    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_JyModel data) {
        //return kssService.jy_updateByKey(id, data);
        return null;
    }


    @OpenAPI
    public ResponseMessage<Kss_JyModel> jy_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        //return kssService.jy_getByKey(id);
        return null;
    }



    @OpenAPI
    public ResponseMessage<Integer> jy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        //return kssService.jy_deleteByKey(id);
        return null;
    }
}
