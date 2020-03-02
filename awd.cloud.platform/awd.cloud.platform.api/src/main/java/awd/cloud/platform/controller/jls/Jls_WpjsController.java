/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.base.model.Variables;
import awd.bj.jls.model.*;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jls.SqswInfoModel;
import awd.cloud.platform.model.kss.WpjsModelDO;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/wpjs")
@Api(tags = "jls-wpjs",description = "Wpjs")
public class Jls_WpjsController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;
    @Autowired
    private ProcessService processService;

    /**
     * @api {post} /v4/jls/wpjs/swdjSave 送物登记保存
     * @apiVersion 0.4.0
     * @apiName swdjSave
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 送物登记保存.
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
     *      "jsbh":"监所编号(必填; 最大字段长度：9)",
     *      "swrxm":"送物人姓名(必填; 最大字段长度：30)",
     *      "swrxb":"送物人姓名(必填; 最大字段长度：1)",
     *      "gx":"与在拘人员关系(必填; 最大字段长度：3)",
     *      "zjlx":"证件类型(必填; 最大字段长度：2)",
     *      "zjhm":"证件号码(必填; 最大字段长度：30)",
     *      "zqmj":"执勤民警(必填; 最大字段长度：30)",
     *      "swsj":"送物时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "swrlxdh":"送物人联系电话(必填; 最大字段长度：11)",
     *      "swrdw":"送物人单位（必填; 最大字段长度：60)",
     *      "dz":"地址（必填; 最大字段长度：200)",
     *      "sqje":"送钱金额（必填; 最大字段长度：10)",\\d{1,10}
     *      "wpxx":"物品名称（必填; 最大字段长度：200)",
     *      "bz":"备注",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","jsbh":".{1,9}","swrxm":".{1,30}","swrxb":".{1,1}","gx":".{1,3}","zjlx":".{1,2}","zjhm":".{1,30}","zqmj":".{1,30}","swsj":"送物时间(必填; 格式：yyyy-MM-dd hh:mm:ss)","swrlxdh":".{1,11}","swrdw":".{1,60}","dz":".{1,200}","wpxx":"200","bz":"备注","creator":".{1,30}"}
    @ApiOperation("送物登记保存")
    @PostMapping("swdjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> swdjSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/wpjs/qmcs/swdjSave";

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

            String flowkey = CacheUtils.get().getFlowKey("JLS_JSSW");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            //流程互斥
            String key = "jls_jssw";
            if (processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(), "wpjs").getStatus() != 200) {
                return processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(),"wpjs");
            }
            SqswInfoModel sqswInfoModel = new SqswInfoModel();
            sqswInfoModel.setJsbh(jsbh);
            //社会关系
            ShgxModel shgxModel = new ShgxModel();
            shgxModel.setJsbh(jsbh);
            shgxModel.setRybh(map.get("rybh").toString());
            shgxModel.setXm(map.get("swrxm").toString());
            shgxModel.setXb(map.get("swrxb").toString());
            shgxModel.setGx(map.get("gx").toString());
            shgxModel.setGzdw(map.get("swrdw").toString());
            shgxModel.setDz(map.get("dz").toString());
            shgxModel.setDh(map.get("swrlxdh").toString());
            shgxModel.setZjlx(map.get("zjlx").toString());
            shgxModel.setZjh(map.get("zjhm").toString());
            shgxModel.setBz(map.get("bz").toString());
            shgxModel.setState("R2");
            shgxModel.setCreator(map.get("creator").toString());
            sqswInfoModel.setShgxEntity(shgxModel);
            //现金接收
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(!StringUtils.isNullOrEmpty(map.get("sqje"))){
                XjjsModel xjjsModel = new XjjsModel();
                xjjsModel.setRybh(map.get("rybh").toString());
                xjjsModel.setJsbh(jsbh);
                xjjsModel.setCurrency("156");
                xjjsModel.setSrlx("2");
                xjjsModel.setJszjh(map.get("zjhm").toString());
                xjjsModel.setJsje(new BigDecimal(map.get("sqje").toString()));
                xjjsModel.setBlrq(simpleDateFormat.parse(map.get("tbrq").toString()));
                xjjsModel.setBlr(map.get("zqmj").toString());
                xjjsModel.setState("R2");
                xjjsModel.setCreator(map.get("creator").toString());
                sqswInfoModel.setXjjsEmtity(xjjsModel);
            }
            //物品接收
            List<WpjsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), WpjsModel.class);
            WpjsModel wpjsModel = modelList.get(0);
            wpjsModel.setJsbh(jsbh);
            wpjsModel.setState("R2");
            wpjsModel.setDjr(map.get("zqmj").toString());
            wpjsModel.setCreator(map.get("creator").toString());
            sqswInfoModel.setWpjsEntity(wpjsModel);
            ResponseMessage<String> result = jlsServerApis.swdjSave(flowkey, sqswInfoModel);
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
     * @api {post} /v4/jls/wpjs/wpjsAdd 物品接收保存
     * @apiVersion 0.4.0
     * @apiName wpjsAdd
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 物品接收保存
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
     * 	         "id": "id(必填; 最大长度:23)",
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "jzr": "见证人(必填; 最大长度:30)",
     * 		     "jzrq": "见证日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *        }]
     *      }
     * }
     */
    //{"id":".{1,23}","tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","jzr":".{1,30}","jzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("物品接收保存")
    @PostMapping("/wpjsAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> wpjsAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/wpjs/wpjsAdd";
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

            List<WpjsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), WpjsModel.class);
            WpjsModel wpjsModel = modelList.get(0);
            String taskid = wpjsModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(wpjsModel));

            wpjsModel.setJsbh(jsbh);
            System.err.println("model--"+JSON.toJSONString(wpjsModel));
            ResponseMessage<String> result = jlsServerApis.wpjsZxflow(taskid, wpjsModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/wpjs/pfwpAdd 派发物品保存
     * @apiVersion 0.4.0
     * @apiName pfwpAdd
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 派发物品保存
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
     *        "wpgls": [{"id":"11000012120200221000196","jsbh":"110000121","rybh":"110000121201911280001","uuid":"d5ef2f63-f121-4951-aca","wply":"1","mc":"1","sl":1,"wpdw":"02","wplx":"0","xjcd":"","wpxx":"","ys":"","djr":"管理员","djrq":"2020-02-21 13:58:14","lqzt":"0","lqr":"","lqrq":null,"twr":"","twrq":null,"jzr":"","jzrq":null,"bz":"","state":"R2","creator":"管理员","createtime":"2020-02-21 13:58:14","updator":"","updatetime":null,"jsbhString":"","wplyString":"物品保管","mcString":"毯子","wpdwString":"件","wplxString":"日常物品","xjcdString":"","ysString":"","lqztString":"未领取","stateString":"有效"}]，
     * 	      "entity": [{
     * 	         "id": "id(必填; 最大长度:23)",
     * 	         "tbr":" 填表人(必填; 最大字段长度：30)",
     *           "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "tybh":"人员编号(必填; 最大字段长度：21)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "pfr": "派发人人(必填; 最大长度:30)",
     * 		     "pfsj": "见证日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *        }]
     *      }
     * }
     */
    //{"id":".{1,23}","tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","pfr":".{1,30}","jzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("派发物品保存")
    @PostMapping("/pfwpAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> pfwpAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/wpjs/pfwpAdd";
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

            List<WpjsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), WpjsModel.class);
            WpjsModel wpjsModel = modelList.get(0);
            String taskid = wpjsModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            wpjsModel.setJsbh(jsbh);
            System.err.println("model--"+ JSON.toJSONString(wpjsModel));

            String wpgls = map.get("wpgls").toString();
            List<WpglModel> wpglList = JSONUtil.toList(wpgls, WpglModel.class);
            for (WpglModel wpgl : wpglList){
                wpgl.setLqzt("1");
                wpgl.setLqrq(wpjsModel.getPfsj());
                wpgl.setTwr(wpjsModel.getPfr());
                wpgl.setTwrq(wpjsModel.getPfsj());
            }
            System.err.println("model--"+JSON.toJSONString(wpjsModel));
            SqswInfoModel sqswInfoModel = new SqswInfoModel();
            sqswInfoModel.setJsbh(jsbh);
            sqswInfoModel.setTaskid(taskid);
            sqswInfoModel.setWpglList(wpglList);
            sqswInfoModel.setWpjsEntity(wpjsModel);
            ResponseMessage<String> result = jlsServerApis.swPfwpSave(sqswInfoModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/wpjs/ywtzQuery  送物业务台账查询
     * @apiVersion 0.4.0
     * @apiName ywtzQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   送物业务台账查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}zjlx                                                 证件类型
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}zjhm                                                 证件号码
     * @apiSuccess {String}swsj                                                 送物时间
     * @apiSuccess {String}uuid                                                 UUID
     * @apiSuccess {String}swrxm                                                送物人姓名
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}wpmcString                                           物品名称(已转换)
     * @apiSuccess {String}zjlxString                                           证件类型(已转换)
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}wpmc                                                 物品名称
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}taskid                                               业务进程id
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}jzr                                                  见证人
     * @apiSuccess {String}jzrqString                                           见证日期(已转换)
     * @apiSuccess {String}swrlxdh                                              送物人电话
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}djrq                                                 登记日期
     * @apiSuccess {String}swrxbString                                          送物人性别(已转换)
     * @apiSuccess {String}djr                                                  登记人
     * @apiSuccess {String}swrdw                                                送物人工作单位
     * @apiSuccess {String}zqmj                                                 执勤民警
     * @apiSuccess {String}swsjString                                           送物时间（已转换）
     * @apiSuccess {String}xbString                                             性别(已转换)
     * @apiSuccess {String}swrxb                                                送物人性别
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jzrq                                                 见证日期
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
     *     "total": 8,
     *     "data": [
     *       {
     *       tbr: "管理员"
     *       zjlx: "11"
     *       ywlcid: "4558608"
     *       zjhm: "142402199712250314"
     *       swsj: 1582270307000
     *       uuid: "f23c6711-0f13-4a8c-84a"
     *       swrxm: "1"
     *       snbh: "20190080"
     *       wpmcString: "长裤"
     *       zjlxString: "身份证"
     *       bz: ""
     *       wpmc: "17"
     *       id: "11000012120200221000208"
     *       tbrq: 1582214400000
     *       state: "R2"
     *       taskid: "4558876"
     *       creator: "管理员"
     *       createtime: 1582270279000
     *       jzr: "管理员"
     *       jzrqString: "2020-02-21 15:32:45"
     *       swrlxdh: "15535465348"
     *       xb: "1"
     *       djrq: 1582270279000
     *       swrxbString: "男性"
     *       djr: "管理员"
     *       swrdw: "1"
     *       zqmj: "管理员"
     *       swsjString: "2020-02-21 15:31:47"
     *       xbString: "男性"
     *       swrxb: "1"
     *       xm: "张三"
     *       rybh: "110000121201911280001"
     *       updatetime: 1582270303000
     *       jzrq: 1582270365000
     *       jsh: "0101"
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
     *      "swrxm":"送物人姓名",
     *      "zjlx":"证件类型",
     *      "swsjStart":"送物时间",
     *      "swsjEnd":"送物时间",
     *      "swrlxdh":"送物人电话",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //tbr,zjlx,ywlcid,zjhm,swsj,uuid,swrxm,snbh,wpmcString,zjlxString,bz,wpmc,id,tbrq,taskid,jzr,jzrqString,swrlxdh,xb,djrq,swrxbString,djr,swrdw,zqmj,swsjString,xbString,swrxb,xm,rybh,jzrq,jsh,jsbh
    @ApiOperation("送物业务台账查询")
    @GetMapping("/ywtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/wpjs/ywtzQuery";
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("swrxm"))){
                queryParam.and("swrxm", TermType.eq, "%"+maps.getResult().get("swrxm")+"%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjlx"))){
                queryParam.and("zjlx", TermType.eq, maps.getResult().get("zjlx"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("swsjStart"))) {
                queryParam.and("swsj", TermType.gte, maps.getResult().get("swsjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("swsjEnd"))) {
                queryParam.and("swsj", TermType.lte, maps.getResult().get("swsjEnd"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("swrlxdh"))){
                queryParam.and("swrlxdh", TermType.eq, "%"+maps.getResult().get("swrlxdh")+"%");
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.swYwtzQuery(queryParam);
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
