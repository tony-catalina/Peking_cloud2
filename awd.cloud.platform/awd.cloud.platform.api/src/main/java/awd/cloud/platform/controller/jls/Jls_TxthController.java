/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.DwkfRyxxModel;
import awd.bj.jls.model.TxthModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jls.DwkfAndRyxxModel;
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
@RequestMapping("/v4/jls/txth")
@Api(tags = "jls-txth",description = "Txth")
public class Jls_TxthController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;
    @Autowired
    private ProcessService processService;

    /**
     * @api {post} /v4/jls/txth/saveByGjcp 亲情电话管教呈批保存
     * @apiVersion 0.4.0
     * @apiName saveByGjcp
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 亲情电话管教呈批保存
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
     *      "txrxm1":"通信人姓名1(必填; 最大字段长度：30)",
     *      "gx1":"关系1(必填; 最大字段长度：3)",
     *      "dhhm1":"电话号码1(必填; 最大字段长度：11)",
     *      "txrxm2":"通信人姓名2",
     *      "gx2":"关系2",
     *      "dhhm2":"电话号码2",
     *      "txrxm3":"通信人姓名3",
     *      "gx3":"关系3",
     *      "dhhm3":"电话号码3",
     *      "gjmjyj":"管教民警意见(必填; 最大字段长度：100)",
     *      "gjmj":"管教民警(必填; 最大字段长度：30)",
     *      "gjmjqmsj":"管教民警签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }
     *   ]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","txrxm1":".{1,30}","gx1":".{1,3}","dhhm1":".{1,11}","gjmjyj":".{1,30}","gjmj":".{1,30}","gjmjqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
    @ApiOperation("亲情电话管教呈批保存")
    @PostMapping("saveByGjcp")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveByGjcp(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/txth/saveByGjcp";

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

            String flowkey = CacheUtils.get().getFlowKey("JLS_QQDH");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            //流程互斥
            String key = "jls_qqdh";
            if (processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(), "txth").getStatus() != 200) {
                return processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(),"txth");
            }
            List<TxthModel> modelList = JSONArray.parseArray(map.get("entity").toString(),TxthModel.class);
            TxthModel txthModel = modelList.get(0);
            txthModel.setState("R2");
            txthModel.setJsbh(jsbh);
            txthModel.setCreatetime(new Date());
            txthModel.setDjrq(new Date());
            ResponseMessage<String> result = jlsServerApis.saveByGjcp(txthModel);
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
     * @api {post} /v4/jls/txth/sldyjsave 亲情电话所领导意见保存
     * @apiVersion 0.4.0
     * @apiName sldyjsave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 亲情电话所领导意见保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							    监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							    保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				    生成的主键信息
     * @apiSuccess {String} status         				    代码
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
     *           "rybh":"人员编号(必填; 最大字段长度：21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "szyj":"所长意见(必填; 最大字段长度：1)",
     * 	         "szyjnr":"所长意见内容(必填)",
     * 	         "szqm":"所长签名(必填; 最大字段长度：30)",
     * 	         "szqmsj":"所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
    @ApiOperation("亲情电话所领导意见保存")
    @PostMapping("/sldyjsave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> sldyjsave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/txth/sldyjsave";
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

            List<TxthModel> modelList = JSONArray.parseArray(map.get("entity").toString(), TxthModel.class);
            TxthModel txthModel = modelList.get(0);
            String taskid = txthModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(txthModel));

            txthModel.setJsbh(jsbh);
            txthModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(txthModel));
            ResponseMessage<String> result = jlsServerApis.saveByLdsp(txthModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/txth/thdjsave 亲情电话通话登记保存
     * @apiVersion 0.4.0
     * @apiName thdjsave
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 亲情电话通话登记保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							    监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							    保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				    生成的主键信息
     * @apiSuccess {String} status         				    代码
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
     *           "rybh":"人员编号(必填; 最大字段长度：21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "thkssj":"通话开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 	         "thjssj":"通话结束时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 	         "jdmj":"监督民警(必填; 最大字段长度：30)",
     * 	         "tsqk":"特殊情况(必填; 最大字段长度：255)",
     * 	         "bz":"备注",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","thkssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","thjssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jdmj":".{1,30}","tsqk":".{1,255}","updator":".{1,30}"}
    @ApiOperation("亲情电话通话登记保存")
    @PostMapping("/thdjsave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> thdjsave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/txth/thdjsave";
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

            List<TxthModel> modelList = JSONArray.parseArray(map.get("entity").toString(), TxthModel.class);
            TxthModel txthModel = modelList.get(0);
            String taskid = txthModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(txthModel));

            txthModel.setJsbh(jsbh);
            txthModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(txthModel));
            ResponseMessage<String> result = jlsServerApis.thdjsave(txthModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/txth/ywtzQuery  亲情电话业务台账查询
     * @apiVersion 0.4.0
     * @apiName ywtzQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   亲情电话业务台账查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}thjssjString                                         通话结束时间(已转换)
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}szqmsjString                                         所长签名时间(已转换)
     * @apiSuccess {String}thjssj                                               通话结束时间
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}gjmjqmsj                                             管教民警签名时间
     * @apiSuccess {String}szyj                                                 所长意见
     * @apiSuccess {String}szyjnr                                               所长意见内容
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}gjmjyj                                               管教民警意见
     * @apiSuccess {String}taskid                                               任务id
     * @apiSuccess {String}gx2                                                  关系2
     * @apiSuccess {String}gx1                                                  关系1
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}gx3                                                  关系3
     * @apiSuccess {String}jdmj                                                 监督民警
     * @apiSuccess {String}thkssjString                                         通话开始时间(已转换)
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}djrq                                                 登记日期
     * @apiSuccess {String}txrxm1                                               通信人姓名1
     * @apiSuccess {String}txrxm2                                               通信人姓名2
     * @apiSuccess {String}thkssj                                               通话开始时间
     * @apiSuccess {String}tsqk                                                 特殊情况
     * @apiSuccess {String}gjmj                                                 管教民警
     * @apiSuccess {String}gx1String                                            关系1(已转换)
     * @apiSuccess {String}xbString                                             性别(已转换)
     * @apiSuccess {String}txrxm3                                               通信人姓名3
     * @apiSuccess {String}gjmjqmsjString                                       管教民警签名时间(已转换)
     * @apiSuccess {String}szqmsj                                               所长签名时间
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}szqm                                                 所长签名
     * @apiSuccess {String}ayString                                             案由(已转换)
     * @apiSuccess {String}ay                                                   案由
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}dhhm3                                                电话号码3
     * @apiSuccess {String}updator                                              修改人
     * @apiSuccess {String}dhhm1                                                电话号码1
     * @apiSuccess {String}dhhm2                                                电话号码2
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
     *     "total": 9,
     *     "data": [
     *       {
     *       thjssjString: "2020-02-24"
     *       tbr: "管理员"
     *       szqmsjString: "2020-02-24"
     *       thjssj: 1582522537000
     *       ywlcid: "4561048"
     *       gjmjqmsj: 1582516057000
     *       szyj: "1"
     *       szyjnr: "同意该拘留人亲情电话。"
     *       snbh: "20190055"
     *       bz: "备注"
     *       id: "11000012120200224000083"
     *       tbrq: 1582473600000
     *       state: "R2"
     *       gjmjyj: "该拘留人需要亲情电话，请领导审批。"
     *       taskid: "4561705"
     *       gx2: ""
     *       gx1: "272"
     *       creator: "管理员"
     *       createtime: 1582516019000
     *       gx3: ""
     *       jdmj: "管理员"
     *       thkssjString: "2020-02-24"
     *       xb: "1"
     *       djrq: 1582516018000
     *       txrxm1: "李宗伟"
     *       txrxm2: ""
     *       thkssj: 1582522527000
     *       tsqk: "特殊情况"
     *       gjmj: "管理员"
     *       gx1String: "同性恋"
     *       xbString: "男性"
     *       txrxm3: ""
     *       gjmjqmsjString: "2020-02-24 11:47:37"
     *       szqmsj: 1582522504000
     *       xm: "李俊伟"
     *       szqm: "管理员"
     *       ayString: "违反治安管理案件"
     *       ay: "010000"
     *       rybh: "110000121201911220001"
     *       dhhm3: ""
     *       updator: "管理员"
     *       dhhm1: "15645641326"
     *       dhhm2: ""
     *       updatetime: 1582522481000
     *       jsh: "0207"
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
     *      "jbxx_xm":"姓名",
     *      "jbxx_xb":"性别",
     *      "jbxx_ay":"案由",
     *      "jbxx_rsrqStart":"入所日期",
     *      "jbxx_rsrqEnd":"入所日期",
     *      "jbxx_csrqStart":"出生日期",
     *      "jbxx_csrqEnd":"出生日期",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //thjssjString,tbr,szqmsjString,thjssj,ywlcid,gjmjqmsj,szyj,szyjnr,snbh,bz,id,tbrq,gjmjyj,taskid,gx2,gx1,gx3,jdmj,thkssjString,xb,djrq,txrxm1,txrxm2,thkssj,tsqk,gjmj,gx1String,xbString,txrxm3,gjmjqmsjString,szqmsj,xm,szqm,ayString,ay,rybh,dhhm3,dhhm1,dhhm2,jsh,jsbh
    @ApiOperation("亲情电话业务台账查询")
    @GetMapping("/ywtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/txth/ywtzQuery";
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
            queryParam.and("szyj", TermType.eq, "1");
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm"))){
                queryParam.and("xm", TermType.eq, "%"+maps.getResult().get("jbxx_xm")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
                queryParam.and("xb", TermType.eq, maps.getResult().get("jbxx_xb"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_ay"))) {
                queryParam.and("ay", TermType.eq, maps.getResult().get("jbxx_ay"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqStart"))){
                queryParam.and("rsrq", TermType.eq, maps.getResult().get("jbxx_rsrqEnd"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqEnd"))){
                queryParam.and("rsrq", TermType.eq, "%"+maps.getResult().get("jbxx_rsrqEnd")+"%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_csrqStart"))){
                queryParam.and("csrq", TermType.eq, maps.getResult().get("jbxx_csrqStart"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_csrqEnd"))){
                queryParam.and("csrq", TermType.eq, "%"+maps.getResult().get("jbxx_csrqEnd")+"%");
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.qqdhYwtzQuery(queryParam);
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
