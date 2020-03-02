/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_WgsjclModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/wgsjcl")
@Api(tags = "jnp-wgsjcl", description = "Wgsjcl")
public class Jnp_WgsjclController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private KssService kssService;

    /**
     * @api {get} /v4/jnp/wgsjcl/wgtxQuery 违规提醒查询
     * @apiVersion 0.4.0
     * @apiName wgtxQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 违规提醒查询
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
     * @apiSuccess {String} creator         			创建人
     * @apiSuccess {String} wgsj      			        违规时间
     * @apiSuccess {String} wglx         				违规类型(字典)
     * @apiSuccess {String} wglxString         			违规类型(已转换)
     * @apiSuccess {String} wgqk         				违规情况
     * @apiSuccess {String} jsbh         				监所编号
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "creator": "管理员",
     *         "wgsj": "2019-11-05 10:54:09",
     *         "wglx": "2",
     *         "wglxString": "监室违规",
     *         "wgqk": "0022010001,0022010002",
     *         "jsbh": "110000114"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576559574840
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "dxbh":"对象编号(最大长度:21; 可填:人员编号、监室号、监区号)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("违规提醒查询")
    @GetMapping("/wgtxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> wgsjcl_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/wgsjcl/wgtxQuery";
        String state = request.getParameter("state");
        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            System.err.println("maps: "+maps);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dxbh"))) {
                param.and("dxbh", TermType.eq, maps.getResult().get("dxbh"));
            }
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
//            if (!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//                param.and("user", TermType.eq, maps.getResult().get("user"));
//            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wgglQueryForPage(param);

            System.err.println("result: "+request);
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
    /**
     * @api {get} /v4/jnp/wgsjcl/jrwgQuery 今日违规查询
     * @apiVersion 0.4.0
     * @apiName jrwgQuery
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 今日违规查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 											    监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集(必填)
     *
     * @apiSuccess {String} photoUrl										 人员照片地址
     * @apiSuccess {String} xbString										 性别(已转换)
     * @apiSuccess {String} wgnr											 违规内容
     * @apiSuccess {String} xm 									 	         人员姓名
     * @apiSuccess {String} rybh											 人员编号
     * @apiSuccess {String} xb											     性别
     * @apiSuccess {String} nl 									 	         年龄
     * @apiSuccess {String} wgjb 									 	     违规级别
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiUse QueryError
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-N2AKzYEAAD2kdd41Eg566.jpg",
     *         "xbString": "女性",
     *         "wgnr": "null",
     *         "xm": "嘻哈侠",
     *         "rybh": "110000111201907120011",
     *         "xb": "2",
     *         "nl": 37,
     *         "wgjb": "特别严重违规"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576912897180
     * }
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{
     *     "jsh":"监室号(必填;最大字段长度:4)"
     *       }
     *
     */

    // @apiSuccess {String} ryxm 											 人员姓名
    // @apiSuccess {String} wgjb         				                     违规级别
    // @apiSuccess {String} ryzp          				                     人员照片
    @OpenAPI
    @ApiOperation("今日违规查询")
    @GetMapping("/jrwgQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jrwgQuery_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/wgsjcl/jrwgQuery";
        String state = "R8";
        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq,jsbh);
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            param.and("state",TermType.eq,"R8");
            System.err.println("param--"+ JSON.toJSONString(param));
            List<Map<String, Object>> results = kssServerApis.jrwgQueryForPage(param);
            System.err.println("result---------------"+ JSONObject.toJSONString(results));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", results);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", results.size());
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
    public ResponseMessage<String> wgsjcl_save(@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_WgsjclModel data) {
        return kssService.wgsjcl_save(data);
    }



    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> wgsjcl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_WgsjclModel data) {
        return kssService.wgsjcl_updateByKey(id, data);
    }


    @OpenAPI
    public ResponseMessage<Kss_WgsjclModel> wgsjcl_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.wgsjcl_getByKey(id);
    }


    @OpenAPI
    public ResponseMessage<Integer> wgsjcl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.wgsjcl_deleteByKey(id);
    }
}
