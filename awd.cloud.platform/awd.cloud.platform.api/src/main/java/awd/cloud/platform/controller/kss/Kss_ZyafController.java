/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.XljkModel;
import awd.bj.kss.model.ZyafModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_ZyafModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import sun.swing.StringUIClientPropertyKey;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/zyaf")
@Api(tags = "kss-zyaf", description = "Zyaf")
public class Kss_ZyafController extends PublicService {

    @Autowired
    private KssService kssService;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/zyaf/zyafList 重要案犯查询
     * @apiVersion 0.4.0
     * @apiName zyafList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 重要案犯查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}xbString          				                性别(已转换)
     * @apiSuccess {String}zwString          				                职位(已转换)
     * @apiSuccess {String}snbh          				                    所内编号
     * @apiSuccess {String}xm          				                    	姓名
     * @apiSuccess {String}blrqString          				                办理日期
     * @apiSuccess {String}ygzdw          				                    原工作单位
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}bz          				                        备注
     * @apiSuccess {String}xb          				                        性别
     * @apiSuccess {String}zw          				                        职位
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}nl          				                        年龄
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "xbString": "女性",
     *         "zwString": "副司、副局、副地、副厅级",
     *         "snbh": "20190046",
     *         "xm": "司马茜",
     *         "blrqString": "2019-10-28 00:00:00",
     *         "ygzdw": "如来佛祖",
     *         "rybh": "110000111201907120002",
     *         "bz": "如来佛祖",
     *         "xb": "2",
     *         "zw": "6",
     *         "jsh": "1201",
     *         "nl": 34
     *       }
     *     ],
     *     "page": 1
     *   },
     *   "status": 200,
     *   "timestamp": 1579163979499
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "rybh":"人员编号"
     * }
     *
     */
    @ApiOperation("重要案犯查询")
    @GetMapping("/zyafList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> zyafList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/zyaf/zyafList";
        String state = "R2";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))){
                param.and("rybh",TermType.eq,maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.zyafQueryForPage(param);
            System.err.println("--result--" + JSON.toJSONString(result));

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
     * @api {post} /v4/kss/zyaf/zyafAdd 重要案犯保存
     * @apiVersion 0.4.0
     * @apiName zyafAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 重要案犯保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功!",
     *   "status": 200,
     *   "timestamp": null
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[{
     *      "creator":"创建人(必填; 最大长度:50)",
     *      "rybh":"人员编号(必填; 最大长度:21)"
     *   }]
     * }
     *
     */
    @ApiOperation("重要案犯保存")
    @PostMapping("/zyafAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zyafAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"rybh":"\\d{1,21}","creator":".{1,50}"}
        String interfaceId = "/v4/kss/zyaf/zyafAdd";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            ZyafModel model = JSONArray.parseArray(map.get("entity").toString(), ZyafModel.class).get(0);
            model.setState("R2");
            model.setJsbh(jsbh);
            model.setCreatetime(new Date());

            ResponseMessage<Map<String,Object>> result = kssServerApis.addZyaf("1", model);
            System.err.println("result--" + JSON.toJSONString(result));

            ResponseMessage newResult = new ResponseMessage();
            newResult.setStatus(result.getStatus());
            if (result.getStatus() == 200) {
                newResult.setMessage("保存成功!");
                newResult.setResult("保存成功!");
            } else {
                newResult.setMessage("服务异常,保存失败!");
            }
            return newResult;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/kss/zyaf/zyafUpdate 重要案犯修改
     * @apiVersion 0.4.0
     * @apiName zyafUpdate
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 重要案犯修改
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							修改参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "1",
     *   "status": 200,
     *   "timestamp": 1579426077880
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[{
     *      "updator":"更新人(必填; 最大长度:30)",
     *      "id":"id(必填; 最大长度:23)"
     *   }]
     * }
     *
     */
    @ApiOperation("重要案犯修改")
    @PostMapping("/zyafUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zyafUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"id":"\\d{1,23}","updator":".{1,50}"}
        String interfaceId = "/v4/kss/zyaf/zyafUpdate";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            ZyafModel model = JSONArray.parseArray(map.get("entity").toString(), ZyafModel.class).get(0);
            model.setJsbh(jsbh);
            model.setUpdatetime(new Date());

            ResponseMessage<String> result = kssServerApis.zyafUpdate(model.getId(), model);
            System.err.println("result--" + JSON.toJSONString(result));

            if (result.getStatus() == 200) {
                result.setMessage("保存成功!");
            } else {
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    @OpenAPI
    public ResponseMessage<Kss_ZyafModel> zyaf_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.zyaf_getByKey(id);
    }


    @OpenAPI
    public ResponseMessage<Integer> zyaf_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.zyaf_deleteByKey(id);
    }
}
