/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JszxjcModel;
import awd.bj.kss.model.QkfyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JszxjcModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jszxjc")
@Api(tags = "kss-jszxjc", description = "Jszxjc")
public class Kss_JszxjcController extends PublicService {

    @Autowired
    private KssService kssService;

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {get} /v4/kss/jszxjc/jszxjcList 监室秩序检查查询
     * @apiVersion 0.4.0
     * @apiName jszxjcList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监室秩序检查查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}jcsj          				                    检查时间
     * @apiSuccess {String}jcqk          				                    检查情况
     * @apiSuccess {String}jbxxXm          				                    姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}jcr          				                    检查人
     * @apiSuccess {String}jsh          				                    监室号
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
     *         "jcsj": "2019-09-04 20:13:39",
     *         "jcqk": "zZ",
     *         "jbxxXm": "发射点",
     *         "rybh": "110000111201907120003",
     *         "jcr": "管理员",
     *         "jsh": "0101"
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
     *   "jsh":"监室号",
     *   "jscjStart":"检查开始时间(格式:yyyy-MM-dd)",
     *   "jscjEnd":"检查结束时间(格式:yyyy-MM-dd)",
     * }
     *
     */
    @OpenAPI
    @ApiOperation("监室秩序检查查询")
    @GetMapping("/jszxjcList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jszxjcList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jszxjc/jszxjcList";
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
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jscjStart"))) {
				param.and("jcsj", TermType.gte, maps.getResult().get("jscjStart") + "00:00:00");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jscjEnd"))) {
				param.and("jcsj", TermType.lte, maps.getResult().get("jscjEnd") + " 23:59:59");
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jszxjcQueryForPage(param);
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
     * @api {post} /v4/kss/jszxjc/jszxjcAdd 监室秩序检查保存
     * @apiVersion 0.4.0
     * @apiName jszxjcAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监室秩序检查保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
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
     *   "result": "0",
     *   "status": 200,
     *   "timestamp": 1579420871733
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
     *      "jsh":"监室号(必填; 最大长度:4)",
     *      "rybh":"人员编号(必填; 最大长度:21)",
     *      "jcsj":"检查时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *      "jcr":"检查人(必填; 最大长度:100)",
     *      "jcqk":"检查情况(必填; 最大长度:100)"
     *   }]
     * }
     *
     */
    @ApiOperation("监室秩序检查保存")
    @PostMapping("/jszxjcAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jszxjcAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"creator":".{1,30}","rybh":"\\d{1,21}","jcsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jsh":"\\d{1,4}","jcr":".{1,100}","jcqk":".{1,100}"}
        //接口id
        String interfaceId = "/v4/kss/jszxjc/jszxjcAdd";
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

            JszxjcModel model = JSONArray.parseArray(map.get("entity").toString(), JszxjcModel.class).get(0);
            model.setState("R2");
            model.setJsbh(jsbh);
            model.setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.jszxjcSave(model);
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

    /**
     * @api {post} /v4/kss/jszxjc/jszxjcUpdate 监室秩序检查修改
     * @apiVersion 0.4.0
     * @apiName jszxjcUpdate
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监室秩序检查修改
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							修改参数集（必填）
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
     *      "updator":"更新人(必填; 最大长度:50)",
     *      "id":"id(必填; 最大长度:23)"
     *   }]
     * }
     *
     */
    @ApiOperation("监室秩序检查修改")
    @PostMapping("/jszxjcUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jszxjcUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"id":"\\d{1,23}","updator":".{1,30}"}
        //接口id
        String interfaceId = "/v4/kss/jszxjc/jszxjcUpdate";
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

            JszxjcModel model = JSONArray.parseArray(map.get("entity").toString(), JszxjcModel.class).get(0);
            model.setUpdatetime(new Date());
            model.setJsbh(jsbh);
            ResponseMessage<String> result = kssServerApis.jszxjcUpdate(model.getId(), model);
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
    public ResponseMessage<Kss_JszxjcModel> jszxjc_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.jszxjc_getByKey(id);
    }


    @OpenAPI
    public ResponseMessage<Integer> jszxjc_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.jszxjc_deleteByKey(id);
    }
}
