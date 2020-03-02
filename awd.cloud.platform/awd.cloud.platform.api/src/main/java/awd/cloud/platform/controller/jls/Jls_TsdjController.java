/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.jls.model.TsdjModel;
import awd.bj.jls.model.WpjsModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jls.TsdjModelDO;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/tsdj")
@Api(tags = "jls-tsdj",description = "Tsdj")
public class Jls_TsdjController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;
    @Autowired
    private ProcessService processService;

    /**
     * @api {post} /v4/jls/tsdj/hdpzSave 核对凭证保存
     * @apiVersion 0.4.0
     * @apiName hdpzSave
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 核对凭证保存
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 											监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 											保存参数集(必填)
     *
     * @apiSuccess {String} message         			                    成功信息
     * @apiSuccess {String} result         				                生成的主键信息
     * @apiSuccess {String} status         				                代码
     * @apiSuccess {String} timestamp         			                    时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "entity":[{
     *      "tbr":" 填表人(必填; 最大字段长度：30)",
     *      "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "rybh":"人员编号(必填; 最大字段长度：21)",
     *      "hdr":"核对人(必填; 最大字段长度：10)",
     *      "hdsj":"核对时间时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","hdr":".{1,10}"，"hdsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
    @ApiOperation("核对凭证保存")
    @PostMapping("hdpzSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> hdpzSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/tsdj/hdpzSave";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            String flowkey = CacheUtils.get().getFlowKey("JLS_TX");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            //流程互斥
            String key = "jls_tx";
            if (processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(), "tsdj").getStatus() != 200) {
                return processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(),"tsdj");
            }
            List<TsdjModel> modelList = JSONArray.parseArray(map.get("entity").toString(),TsdjModel.class);
            TsdjModel tsdjModel = modelList.get(0);
            tsdjModel.setState("R2");
            tsdjModel.setBllx("2");//非预约
            tsdjModel.setJsbh(jsbh);
            tsdjModel.setCreatetime(new Date());
            ResponseMessage<String> result = jlsServerApis.hdpzSave(tsdjModel);
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/tsdj/djcpSave 登记呈批保存
     * @apiVersion 0.4.0
     * @apiName djcpSave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 登记呈批保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": null,
     *   "result": "保存成功！",
     *   "status": 200,
     *   "timestamp": 1578018166421
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "json":{
     * 	      "entity": [{
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "bajglxfs": "办案机关联系方式",
     *           "bajg": "办案机关(必填; 最大字段长度：50)",
     *           "barxm1": "办案人姓名1(必填; 最大字段长度：10)",
     *           "gzzjhm1": "工作证件号码1(必填; 最大字段长度：20)",
     *           "lxfs1": "联系方式1",
     *           "barxm2": "办案人姓名2",
     *           "gzzjhm2": "工作证件号码2",
     *           "lxfs2": "联系方式2"，
     *           "xway": "讯问案由",
     *           "jsxbh": ""介绍信编号,
     * 		     "djr": "登记人(必填; 最大长度:30)",
     * 		     "djsj": "登记日期(必填; 格式：yyyy-MM-dd hh:mm:ss)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","bajg":".{1,50}","barxm1":".{1,10}","gzzjhm1":".{1,20}","djr":".{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("登记呈批保存")
    @PostMapping("/djcpSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> djcpSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/tsdj/djcpSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            List<TsdjModel> modelList = JSONArray.parseArray(map.get("entity").toString(), TsdjModel.class);
            TsdjModel tsdjModel = modelList.get(0);
            String taskid = tsdjModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(tsdjModel));

            tsdjModel.setJsbh(jsbh);
            System.err.println("model--"+JSON.toJSONString(tsdjModel));
            ResponseMessage<String> result = jlsServerApis.djcpSave(tsdjModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/tsdj/szyjSave 所长意见保存
     * @apiVersion 0.4.0
     * @apiName szyjSave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 所长意见保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": null,
     *   "result": "保存成功！",
     *   "status": 200,
     *   "timestamp": 1578018166421
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "json":{
     * 	      "entity": [{
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "bajglxfs": "办案机关联系方式",
     *           "bajg": "办案机关(必填; 最大字段长度：50)",
     *           "barxm1": "办案人姓名1(必填; 最大字段长度：10)",
     *           "gzzjhm1": "工作证件号码1(必填; 最大字段长度：20)",
     *           "lxfs1": "联系方式1",
     *           "barxm2": "办案人姓名2",
     *           "gzzjhm2": "工作证件号码2",
     *           "lxfs2": "联系方式2"，
     *           "ayString": "讯问案由",
     *           "xway": "讯问案由",
     *           "jsxbh": ""介绍信编号,
     * 		     "djr": "登记人(必填; 最大长度:30)",
     * 		     "djsj": "登记日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "ldyj" :"领导意见(必填; 最大长度:200)",
     * 		     "ldxm" :"领导姓名"(必填; 最大长度:30),
     * 		     "ldpssj" :"领导批示时间(必填; 格式：yyyy-MM-dd hh:mm:ss)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","bajg":".{1,50}","barxm1":".{1,10}","gzzjhm1":".{1,20}","djr":".{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ldyj":".{1,200}","ldxm":".{1,30}","ldpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("所长意见保存")
    @PostMapping("/szyjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> szyjSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/tsdj/szyjSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            List<TsdjModel> modelList = JSONArray.parseArray(map.get("entity").toString(), TsdjModel.class);
            TsdjModel tsdjModel = modelList.get(0);
            String taskid = tsdjModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(tsdjModel));

            tsdjModel.setJsbh(jsbh);
            tsdjModel.setLdpsbz("1");
            tsdjModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(tsdjModel));
            ResponseMessage<String> result = jlsServerApis.szyjSave(tsdjModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/tsdj/aptxSave 安排提询保存
     * @apiVersion 0.4.0
     * @apiName aptxSave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 安排提询保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": null,
     *   "result": "保存成功！",
     *   "status": 200,
     *   "timestamp": 1578018166421
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "json":{
     * 	      "entity": [{
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "bajglxfs": "办案机关联系方式",
     *           "bajg": "办案机关(必填; 最大字段长度：50)",
     *           "barxm1": "办案人姓名1(必填; 最大字段长度：10)",
     *           "gzzjhm1": "工作证件号码1(必填; 最大字段长度：20)",
     *           "lxfs1": "联系方式1",
     *           "barxm2": "办案人姓名2",
     *           "gzzjhm2": "工作证件号码2",
     *           "lxfs2": "联系方式2"，
     *           "ayString": "讯问案由",
     *           "xway": "讯问案由",
     *           "jsxbh": ""介绍信编号,
     * 		     "djr": "登记人(必填; 最大长度:30)",
     * 		     "djsj": "登记日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "ldyj" :"领导意见(必填; 最大长度:200)",
     * 		     "ldxm" :"领导姓名"(必填; 最大长度:30)",
     * 		     "ldpssj" :"领导批示时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "tss": "提审室(必填; 最大长度:20)",
     *           "dcmj": "带出民警（必填; 最大长度:100)",
     *           "kssj": "开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","bajg":".{1,50}","barxm1":".{1,10}","gzzjhm1":".{1,20}","djr":".{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ldyj":".{1,200}","ldxm":".{1,30}","ldpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","tss":".{1,20}","dcmj":".{1,100}","kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("安排提询保存")
    @PostMapping("/aptxSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> aptxSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/tsdj/aptxSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            List<TsdjModel> modelList = JSONArray.parseArray(map.get("entity").toString(), TsdjModel.class);
            TsdjModel tsdjModel = modelList.get(0);
            String taskid = tsdjModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(tsdjModel));

            tsdjModel.setJsbh(jsbh);
            tsdjModel.setUpdatetime(new Date());
            tsdjModel.setDjsj(new Date());
            System.err.println("model--"+JSON.toJSONString(tsdjModel));
            ResponseMessage<String> result = jlsServerApis.aptxSave(tsdjModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/tsdj/xwdjSave 询完登记
     * @apiVersion 0.4.0
     * @apiName xwdjSave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 询完登记
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": null,
     *   "result": "保存成功！",
     *   "status": 200,
     *   "timestamp": 1578018166421
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "json":{
     * 	      "entity": [{
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "bajglxfs": "办案机关联系方式",
     *           "bajg": "办案机关(必填; 最大字段长度：50)",
     *           "barxm1": "办案人姓名1(必填; 最大字段长度：10)",
     *           "gzzjhm1": "工作证件号码1(必填; 最大字段长度：20)",
     *           "lxfs1": "联系方式1",
     *           "barxm2": "办案人姓名2",
     *           "gzzjhm2": "工作证件号码2",
     *           "lxfs2": "联系方式2"，
     *           "ayString": "讯问案由",
     *           "xway": "讯问案由",
     *           "jsxbh": ""介绍信编号,
     * 		     "djr": "登记人(必填; 最大长度:30)",
     * 		     "djsj": "登记日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "ldyj" :"领导意见(必填; 最大长度:200)",
     * 		     "ldxm" :"领导姓名"(必填; 最大长度:30)",
     * 		     "ldpssj" :"领导批示时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "tss": "提审室(必填; 最大长度:20)",
     *           "dcmj": "带出民警（必填; 最大长度:100)",
     *           "kssj": "开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "jsr": "收监民警（必填; 最大长度:100)",
     *           "jssj": "开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "stjcqk": "身体检查情况（必填; 必填; 最大长度:100))",
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","bajg":".{1,50}","barxm1":".{1,10}","gzzjhm1":".{1,20}","djr":".{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ldyj":".{1,200}","ldxm":".{1,30}","ldpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","tss":".{1,20}","dcmj":".{1,100}","kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jsr":".{1,100}","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","stjcqk":".{1,100}"}
    @ApiOperation("询完登记")
    @PostMapping("/xwdjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xwdjSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/tsdj/xwdjSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            List<TsdjModelDO> modelList = JSONArray.parseArray(map.get("entity").toString(), TsdjModelDO.class);
            TsdjModelDO tsdjModel = modelList.get(0);
            String taskid = tsdjModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(tsdjModel));

            tsdjModel.setJsbh(jsbh);
            tsdjModel.setUpdatetime(new Date());
            tsdjModel.setDjsj(new Date());
            System.err.println("model--"+JSON.toJSONString(tsdjModel));
            String stjcqk = tsdjModel.getStjcqk();
            ResponseMessage<String> result = jlsServerApis.xwdjSave(stjcqk,tsdjModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/tsdj/ywdtQuery  提讯业务动态查询
     * @apiVersion 0.4.0
     * @apiName ywdtQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   提讯业务动态查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}jsr                                                  收监民警
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}kssj                                                 开始时间
     * @apiSuccess {String}kssjString                                           开始时间(已转换)
     * @apiSuccess {String}jssj                                                 结束时间
     * @apiSuccess {String}jssjString                                           结束时间(已转换)
     * @apiSuccess {String}xway                                                 询问案由
     * @apiSuccess {String}bajg                                                 办案机关
     * @apiSuccess {String}lxfs1                                                联系方式1
     * @apiSuccess {String}ldpsbz                                               领导批示标志
     * @apiSuccess {String}lxfs2                                                联系方式2
     * @apiSuccess {String}ldpssj                                               领导批示时间
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}jsxbh                                                介绍信编号
     * @apiSuccess {String}taskid                                               业务进程id
     * @apiSuccess {String}ldxm                                                 领导姓名
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}barxm2                                               办案人姓名2
     * @apiSuccess {String}djr                                                  登记人
     * @apiSuccess {String}barxm1                                               办案人姓名1
     * @apiSuccess {String}gzzjhm2                                              工作证件号码2
     * @apiSuccess {String}xbString                                             性别（已转换）
     * @apiSuccess {String}djsj                                                 登记时间
     * @apiSuccess {String}djsjString                                           登记时间(已转换)
     * @apiSuccess {String}gzzjhm1                                              工作证件号码1
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}ayString                                             案由（已转换）
     * @apiSuccess {String}ay                                                   案由
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}bllx                                                 办理类型
     * @apiSuccess {String}zjh                                                  证件号
     * @apiSuccess {String}updator                                              修改人
     * @apiSuccess {String}bajglxfs                                             办案机关联系方式
     * @apiSuccess {String}ldyj                                                 领导意见
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jsh                                                  监室号
     * @apiSuccess {String}jsbh                                                 监所编号
     *
     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *       jsr:"带入民警"
     *       ywlcid: "4559438"
     *       hjdxz: "北京市郊县怀柔县"
     *       kssj: 1582342705000
     *       kssjString: "2020-02-22"
     *       jssj: 1582343851000
     *       jssjString: "2020-02-22"
     *       xway: ""
     *       bajg: "11"
     *       lxfs1: "13588887451"
     *       ldpsbz: "1"
     *       lxfs2: ""
     *       ldpssj: 1582343714000
     *       snbh: "20190011"
     *       id: "11000012120200222000073"
     *       state: "R2"
     *       jsxbh: ""
     *       taskid: "4559788"
     *       ldxm: "管理员"
     *       creator: "管理员"
     *       createtime: 1582339821000
     *       xb: "2"
     *       barxm2: ""
     *       djr: "管理员"
     *       barxm1: "办案人1"
     *       gzzjhm2: ""
     *       xbString: "女性"
     *       djsj: 1582341838000
     *       djsjString: "2020-02-22"
     *       gzzjhm1: "44"
     *       xm: "5555"
     *       ayString: "扰乱大型群众性活动秩序案件"
     *       ay: "010105"
     *       rybh: "110000121201911080003"
     *       bllx: "2"
     *       zjh: "513436200011056382"
     *       updator: "管理员"
     *       bajglxfs: "110"
     *       ldyj: "同意该拘留人提讯"
     *       updatetime: 1582343649000
     *       jsh: "0202"
     *       jsbh: "110000121"
     *       }，
     *       "page": "1"
     *     }，
     *   "status": 200,
     *   "timestamp": 1576826568061
     *  }
     *
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * json:{
     *      "timeStart":"开始时间",
     *      "timeEnd":"结束时间",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //jsr,ywlcid,kssj,kssjString,jssj,jssjString,xway,bajg,lxfs1,ldpsbz,lxfs2,ldpssj,snbh,id,jsxbh,taskid,ldxm,xb,barxm2,djr,barxm1,gzzjhm2,xbString,djsj,djsjString,gzzjhm1,xm,ayString,ay,rybh,bllx,zjh,bajglxfs,ldyj,jsh,jsbh
    @ApiOperation("提讯业务动态查询")
    @GetMapping("/ywdtQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywdtQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/tsdj/ywdtQuery";
        String state = request.getParameter("state");
        try{
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam queryParam = new QueryParam();
            queryParam.and("jsbh", TermType.eq, jsbh);
            queryParam.and("ldpsbz", TermType.eq, "1");
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("timeStart"))) {
                queryParam.and("kssj", TermType.gte, maps.getResult().get("timeStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("timeEnd"))) {
                queryParam.and("kssj", TermType.lte, maps.getResult().get("timeEnd"));
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.txYwdtQuery(queryParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String,Object> mapList = new HashMap<String,Object>();
            mapList.put("entity",result.getResult().getData());
            mapList.put("interfaceId",interfaceId);
            mapList.put("total",result.getResult().getTotal());
            mapList.put("page",request.getParameter("page"));
            System.err.println("result" + JSON.toJSONString(mapList));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(mapList);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {get} /v4/jls/tsdj/ywtzQuery  提讯业务台账查询
     * @apiVersion 0.4.0
     * @apiName ywtzQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   提讯业务台账查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}jsr                                                  收监民警
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}kssj                                                 开始时间
     * @apiSuccess {String}kssjString                                           开始时间(已转换)
     * @apiSuccess {String}jssj                                                 结束时间
     * @apiSuccess {String}jssjString                                           结束时间(已转换)
     * @apiSuccess {String}xway                                                 询问案由
     * @apiSuccess {String}bajg                                                 办案机关
     * @apiSuccess {String}lxfs1                                                联系方式1
     * @apiSuccess {String}ldpsbz                                               领导批示标志
     * @apiSuccess {String}lxfs2                                                联系方式2
     * @apiSuccess {String}ldpssj                                               领导批示时间
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}jsxbh                                                介绍信编号
     * @apiSuccess {String}taskid                                               业务进程id
     * @apiSuccess {String}ldxm                                                 领导姓名
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}barxm2                                               办案人姓名2
     * @apiSuccess {String}djr                                                  登记人
     * @apiSuccess {String}barxm1                                               办案人姓名1
     * @apiSuccess {String}gzzjhm2                                              工作证件号码2
     * @apiSuccess {String}xbString                                             性别（已转换）
     * @apiSuccess {String}djsj                                                 登记时间
     * @apiSuccess {String}djsjString                                           登记时间(已转换)
     * @apiSuccess {String}gzzjhm1                                              工作证件号码1
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}ayString                                             案由（已转换）
     * @apiSuccess {String}ay                                                   案由
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}bllx                                                 办理类型
     * @apiSuccess {String}zjh                                                  证件号
     * @apiSuccess {String}updator                                              修改人
     * @apiSuccess {String}bajglxfs                                             办案机关联系方式
     * @apiSuccess {String}ldyj                                                 领导意见
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jsh                                                  监室号
     * @apiSuccess {String}jsbh                                                 监所编号
     *
     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *       jsr:"带入民警"
     *       ywlcid: "4559438"
     *       hjdxz: "北京市郊县怀柔县"
     *       kssj: 1582342705000
     *       kssjString: "2020-02-22"
     *       jssj: 1582343851000
     *       jssjString: "2020-02-22"
     *       xway: ""
     *       bajg: "11"
     *       lxfs1: "13588887451"
     *       ldpsbz: "1"
     *       lxfs2: ""
     *       ldpssj: 1582343714000
     *       snbh: "20190011"
     *       id: "11000012120200222000073"
     *       state: "R2"
     *       jsxbh: ""
     *       taskid: "4559788"
     *       ldxm: "管理员"
     *       creator: "管理员"
     *       createtime: 1582339821000
     *       xb: "2"
     *       barxm2: ""
     *       djr: "管理员"
     *       barxm1: "办案人1"
     *       gzzjhm2: ""
     *       xbString: "女性"
     *       djsj: 1582341838000
     *       djsjString: "2020-02-22"
     *       gzzjhm1: "44"
     *       xm: "5555"
     *       ayString: "扰乱大型群众性活动秩序案件"
     *       ay: "010105"
     *       rybh: "110000121201911080003"
     *       bllx: "2"
     *       zjh: "513436200011056382"
     *       updator: "管理员"
     *       bajglxfs: "110"
     *       ldyj: "同意该拘留人提讯"
     *       updatetime: 1582343649000
     *       jsh: "0202"
     *       jsbh: "110000121"
     *       }，
     *       "page": "1"
     *     }，
     *   "status": 200,
     *   "timestamp": 1576826568061
     *  }
     *
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * json:{
     *      "bajg":"办案机关",
     *      "xway":"询问案由",
     *      "djr":"登记人",
     *      "djsjStart":"登记开始时间",
     *      "djsjEnd":"登记结束时间",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //jsr,ywlcid,kssj,kssjString,jssj,jssjString,xway,bajg,lxfs1,ldpsbz,lxfs2,ldpssj,snbh,id,jsxbh,taskid,ldxm,xb,barxm2,djr,barxm1,gzzjhm2,xbString,djsj,djsjString,gzzjhm1,xm,ayString,ay,rybh,bllx,zjh,bajglxfs,ldyj,jsh,jsbh
    @ApiOperation("提讯业务台账查询")
    @GetMapping("/ywtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/tsdj/ywtzQuery";
        String state = request.getParameter("state");
        try{
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam queryParam = new QueryParam();
            queryParam.and("jsbh", TermType.eq, jsbh);
            queryParam.and("ldpsbz", TermType.eq, "1");
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bajg"))){
                queryParam.and("bajg", TermType.eq, maps.getResult().get("bajg"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xway"))){
                queryParam.and("xway", TermType.eq, maps.getResult().get("xway"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("djr"))){
                queryParam.and("djr", TermType.eq, maps.getResult().get("djr"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("djsjStart"))) {
                queryParam.and("djsj", TermType.gte, maps.getResult().get("djsjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("djsjEnd"))) {
                queryParam.and("djsj", TermType.lte, maps.getResult().get("djsjEnd"));
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.txYwtzQuery(queryParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String,Object> mapList = new HashMap<String,Object>();
            mapList.put("entity",result.getResult().getData());
            mapList.put("interfaceId",interfaceId);
            mapList.put("total",result.getResult().getTotal());
            mapList.put("page",request.getParameter("page"));
            System.err.println("result" + JSON.toJSONString(mapList));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(mapList);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }
}
