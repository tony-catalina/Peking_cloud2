/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.DwkfRyxxModel;
import awd.bj.jls.model.TsdjModel;
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
@RequestMapping("/v4/jls/dwkf")
@Api(tags = "jls-dwkf",description = "Dwkf")
public class Jls_DwkfController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;
    @Autowired
    private ProcessService processService;

    /**
     * @api {get} /v4/jls/dwkf/dwkfQuery   对外开放查询
     * @apiVersion 0.4.0
     * @apiName dwkfQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   对外开放查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}kfsj                                                 开放时间
     * @apiSuccess {String}kflx                                                 开放类型
     * @apiSuccess {String}cgdx                                                 参观对象
     * @apiSuccess {String}cgrs                                                 参观人数
     * @apiSuccess {String}uuid
     * @apiSuccess {String}dwmc                                                 单位名称
     * @apiSuccess {String}sqly                                                 申请理由
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}szyj                                                 所长意见
     * @apiSuccess {String}szyjnr                                               所长意见内容
     * @apiSuccess {String}szqm                                                 所长签名
     * @apiSuccess {String}szqmsj                                               所长签名时间
     * @apiSuccess {String}sjldyj                                               上级领导意见
     * @apiSuccess {String}sjldyjnr                                             上级领导意见内容
     * @apiSuccess {String}sjldqm                                               上级领导签名
     * @apiSuccess {String}sjldqmsj                                             上级领导签名时间
     * @apiSuccess {String}kskfsj                                               开始开放时间
     * @apiSuccess {String}kfdd                                                 开放地点
     * @apiSuccess {String}sqdh                                                 申请单号
     * @apiSuccess {String}sqr                                                  申请人
     * @apiSuccess {String}fzr                                                  负责人
     * @apiSuccess {String}hdqk                                                 活动情况
     * @apiSuccess {String}jssj                                                 结束时间
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}taskid                                               任务id
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}scbz                                                 上传标志
     * @apiSuccess {String}operator                                             操作人
     * @apiSuccess {String}stateString                                          状态（已转换）
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}updator                                              更新人
     * @apiSuccess {String}updatetime                                           更新时间
     * @apiSuccess {String}jsbhString                                           监所编号（已转换）
     * @apiSuccess {String}kflxString                                           开放类型（已转换）
     * @apiSuccess {String}szyjString                                           所长意见（已转换）
     * @apiSuccess {String}sjldyjString                                         上级领导意见（已转换）
     * @apiSuccess {String}scbzString                                           上传标志（已转换）
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
     *     "total": 3,
     *     "data": [
     *       {
     *       id: "11000012120191128000144"
     *       jsbh: "110000121"
     *       tbr: "管理员"
     *       tbrq: "2019-11-28"
     *       kfsj: "2019-11-28 16:08:12"
     *       kflx: "01"
     *       cgdx: "asdf"
     *       cgrs: "23"
     *       uuid: "11000012115749284836333"
     *       dwmc: "sdf"
     *       sqly: "sdfasdf"
     *       bz: "sadfasdf"
     *       szyj: "1"
     *       szyjnr: "同意对外开放。"
     *       szqm: "管理员"
     *       szqmsj: "2019-11-28 16:08:54"
     *       sjldyj: "1"
     *       sjldyjnr: "同意对外开放。"
     *       sjldqm: "管理员"
     *       sjldqmsj: "2019-11-28 16:09:07"
     *       kskfsj: "2019-11-28 16:09:21"
     *       kfdd: "sdfsadf"
     *       sqdh: "3242343"
     *       sqr: "管理员"
     *       fzr: "管理员"
     *       hdqk: "asdfasdf"
     *       jssj: "2019-11-28 16:09:39"
     *       ywlcid: "4424088"
     *       taskid: "4424249"
     *       state: "R2"
     *       scbz: ""
     *       operator: ""
     *       creator: "管理员"
     *       createtime: "2019-11-28 16:08:04"
     *       updator: "管理员"
     *       updatetime: "2019-11-28 16:09:10"
     *       jsbhString: ""
     *       kflxString: "个人"
     *       szyjString: "批示通过"
     *       sjldyjString: "批示通过"
     *       stateString: "有效"
     *       scbzString: ""
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
     *      "kfsjStart": "开始时间",
     *      "kfsjEnd": "结束时间",
     *      "kflx": "开放类型",
     *      "cgrs": "参观人数",
     *      "dwmc": "单位名称",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "lysj",
     *      "order": "desc"
     * }
     */
    //id,jsbh,jsbhString,pch,tm,gg,jldw,lsj,splb,spldString,sfzjff,sfzjffString,sfxg,sfxgString,jhpl,bz,creator,createtime,updator,updatetime,photos,photourl
    @ApiOperation("对外开放查询")
    @GetMapping("/dwkfQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> dwkfQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/dwkf/dwkfQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kfsjStart"))) {
                queryParam.and("kfsj", TermType.gte, maps.getResult().get("kfsjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kfsjEnd"))) {
                queryParam.and("kfsj", TermType.lte, maps.getResult().get("kfsjEnd"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kflx"))) {
                queryParam.and("kflx", TermType.eq, maps.getResult().get("kflx"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("cgrs"))) {
                queryParam.and("cgrs", TermType.eq, "%"+ maps.getResult().get("cgrs")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("dwmc"))) {
                queryParam.and("dwmc", TermType.eq, "%"+ maps.getResult().get("dwmc")+"%");
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<DwkfModel>> result = jlsServerApis.dwkfQueryForPage(queryParam);
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
     * @api {post} /v4/jls/dwkf/saveBySqdj 对外开放申请登记保存
     * @apiVersion 0.4.0
     * @apiName saveBySqdj
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 对外开放申请登记保存
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
     *      "kfsj":"开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "kflx":"开放类型(必填; 最大字段长度：10)",
     *      "cgdx":"参观对象(必填; 最大字段长度：20)",
     *      "cgrs":"参观人数(必填; 最大字段长度：10)",
     *      "dwmc":"单位名称(必填; 最大字段长度：20)",
     *      "xm":"姓名(必填; 最大字段长度：30)",
     *      "xb":"性别(必填; 最大字段长度：1)",
     *      "csrq":"出生日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "zjlx":"证件类型",
     *      "zjh'":"证件号",
     *      "lxdh'":"联系电话",
     *      "sqly'":"申请理由(必填; 最大字段长度：50)",
     *      "bz'":"备注",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }
     *   ]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","kfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kflx":".{1,10}","cgdx":".{1,20}","cgrs":".{1,10}","dwmc":".{1,20}","xm":".{1,30}","xb":".{1,1}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sqly":".{1,50}","creator":".{1,30}"}
    @ApiOperation("对外开放申请登记保存")
    @PostMapping("saveBySqdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveBySqdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/dwkf/saveBySqdj";

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

            String flowkey = CacheUtils.get().getFlowKey("JLS_DWKF");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            //流程互斥
            String key = "jls_dwkf";
            if (processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(), "dwkf").getStatus() != 200) {
                return processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(),"dwkf");
            }
            DwkfAndRyxxModel dwkfAndRyxxModel = new DwkfAndRyxxModel();
            List<DwkfModel> modelList = JSONArray.parseArray(map.get("entity").toString(),DwkfModel.class);
            List<DwkfRyxxModel> list = JSONArray.parseArray(map.get("entity").toString(),DwkfRyxxModel.class);
            DwkfModel dwkfModel = modelList.get(0);
            dwkfModel.setState("R2");
            dwkfModel.setJsbh(jsbh);
            dwkfModel.setCreatetime(new Date());
            dwkfAndRyxxModel.setDwkfModel(dwkfModel);
            dwkfAndRyxxModel.setDwList(list);
            ResponseMessage<String> result = jlsServerApis.dwkfBySqdj(dwkfAndRyxxModel);
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
     * @api {post} /v4/jls/dwkf/saveBySzyj 对外开放所长意见保存
     * @apiVersion 0.4.0
     * @apiName saveBySzyj
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 对外开放所长意见保存
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
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "kfsj":"开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kflx":"开放类型(必填; 最大字段长度：10)",
     *           "cgdx":"参观对象(必填; 最大字段长度：20)",
     *           "cgrs":"参观人数(必填; 最大字段长度：10)",
     *           "dwmc":"单位名称(必填; 最大字段长度：20)",
     *           "sqly'":"申请理由(必填; 最大字段长度：50)",
     *           "bz'":"备注",
     *           "szyj":"所长意见(必填; 最大字段长度：1)",
     *           "szyjnr":"所长意见内容(必填)",
     *           "szqm":"所长签名(必填; 最大字段长度：30)",
     *           "szqmsj":"所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kflx":".{1,10}","cgdx":".{1,20}","cgrs":".{1,10}","dwmc":".{1,20}","sqly":".{1,50}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
    @ApiOperation("对外开放所长意见保存")
    @PostMapping("/saveBySzyj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveBySzyj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/dwkf/saveBySzyj";
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

            List<DwkfModel> modelList = JSONArray.parseArray(map.get("entity").toString(), DwkfModel.class);
            DwkfModel dwkfModel = modelList.get(0);
            String taskid = dwkfModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(dwkfModel));

            dwkfModel.setJsbh(jsbh);
            dwkfModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(dwkfModel));
            ResponseMessage<String> result = jlsServerApis.dwkfBySzyj(dwkfModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/dwkf/saveBySjsc 对外开放上级审查保存
     * @apiVersion 0.4.0
     * @apiName saveBySjsc
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 对外开放上级审查保存
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
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "kfsj":"开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kflx":"开放类型(必填; 最大字段长度：10)",
     *           "cgdx":"参观对象(必填; 最大字段长度：20)",
     *           "cgrs":"参观人数(必填; 最大字段长度：10)",
     *           "dwmc":"单位名称(必填; 最大字段长度：20)",
     *           "sqly'":"申请理由(必填; 最大字段长度：50)",
     *           "bz'":"备注",
     *           "szyj":"所长意见(必填; 最大字段长度：1)",
     *           "szyjnr":"所长意见内容(必填)",
     *           "szqm":"所长签名(必填; 最大字段长度：30)",
     *           "szqmsj":"所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "sjldyj":"上级领导意见(必填; 最大字段长度：1)",
     *           "sjldyjnr":"上级领导意见内容(必填)",
     *           "sjldqm":"上级领导签名(必填; 最大字段长度：30)",
     *           "sjldqmsj":"上级领导签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kflx":".{1,10}","cgdx":".{1,20}","cgrs":".{1,10}","dwmc":".{1,20}","sqly":".{1,50}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sjldyj":".{1,1}","sjldqm":".{1,30}","sjldqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
    @ApiOperation("对外开放上级审查保存")
    @PostMapping("/saveBySjsc")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveBySjsc(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/dwkf/saveBySjsc";
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

            List<DwkfModel> modelList = JSONArray.parseArray(map.get("entity").toString(), DwkfModel.class);
            DwkfModel dwkfModel = modelList.get(0);
            String taskid = dwkfModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(dwkfModel));

            dwkfModel.setJsbh(jsbh);
            dwkfModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(dwkfModel));
            ResponseMessage<String> result = jlsServerApis.dwkfBySzyj(dwkfModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/dwkf/saveByHdap 对外开放活动安排保存
     * @apiVersion 0.4.0
     * @apiName saveByHdap
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 对外开放活动安排保存
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
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "kfsj":"开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kflx":"开放类型(必填; 最大字段长度：10)",
     *           "cgdx":"参观对象(必填; 最大字段长度：20)",
     *           "cgrs":"参观人数(必填; 最大字段长度：10)",
     *           "dwmc":"单位名称(必填; 最大字段长度：20)",
     *           "sqly'":"申请理由(必填; 最大字段长度：50)",
     *           "bz'":"备注",
     *           "szyj":"所长意见(必填; 最大字段长度：1)",
     *           "szyjnr":"所长意见内容(必填)",
     *           "szqm":"所长签名(必填; 最大字段长度：30)",
     *           "szqmsj":"所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "sjldyj":"上级领导意见(必填; 最大字段长度：1)",
     *           "sjldyjnr":"上级领导意见内容(必填)",
     *           "sjldqm":"上级领导签名(必填; 最大字段长度：30)",
     *           "sjldqmsj":"上级领导签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kskfsj":"开始开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kfdd":"开放地点(必填; 最大字段长度：50)",
     *           "sqdh":"申请单号(必填; 最大字段长度：20)",
     *           "sqr":"申请人(必填; 最大字段长度：30)",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kflx":".{1,10}","cgdx":".{1,20}","cgrs":".{1,10}","dwmc":".{1,20}","sqly":".{1,50}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sjldyj":".{1,1}","sjldqm":".{1,30}","sjldqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kskfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfdd":".{1,50}","sqdh":".{1,20}","sqr":".{1,30}","updator":".{1,30}"}
    @ApiOperation("对外开放活动安排保存")
    @PostMapping("/saveByHdap")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveByHdap(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/dwkf/saveByHdap";
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

            List<DwkfModel> modelList = JSONArray.parseArray(map.get("entity").toString(), DwkfModel.class);
            DwkfModel dwkfModel = modelList.get(0);
            String taskid = dwkfModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(dwkfModel));

            dwkfModel.setJsbh(jsbh);
            dwkfModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(dwkfModel));
            ResponseMessage<String> result = jlsServerApis.dwkfBySzyj(dwkfModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/dwkf/saveByWbdj  对外开放完毕登记保存
     * @apiVersion 0.4.0
     * @apiName saveByWbdj
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 对外开放完毕登记保存
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
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填)",
     * 	         "kfsj":"开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kflx":"开放类型(必填; 最大字段长度：10)",
     *           "cgdx":"参观对象(必填; 最大字段长度：20)",
     *           "cgrs":"参观人数(必填; 最大字段长度：10)",
     *           "dwmc":"单位名称(必填; 最大字段长度：20)",
     *           "sqly'":"申请理由(必填; 最大字段长度：50)",
     *           "bz'":"备注",
     *           "szyj":"所长意见(必填; 最大字段长度：1)",
     *           "szyjnr":"所长意见内容(必填)",
     *           "szqm":"所长签名(必填; 最大字段长度：30)",
     *           "szqmsj":"所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "sjldyj":"上级领导意见(必填; 最大字段长度：1)",
     *           "sjldyjnr":"上级领导意见内容(必填)",
     *           "sjldqm":"上级领导签名(必填; 最大字段长度：30)",
     *           "sjldqmsj":"上级领导签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kskfsj":"开始开放时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "kfdd":"开放地点(必填; 最大字段长度：50)",
     *           "sqdh":"申请单号(必填; 最大字段长度：20)",
     *           "sqr":"申请人(必填; 最大字段长度：30)",
     *           "fzr":"负责人(必填; 最大字段长度：30)",
     *           "jssj":"结束时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "hdqk":"活动情况(必填)",
     *           "updator":"修改人(必填; 最大字段长度：30)"
     *        }]
     *      }
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kflx":".{1,10}","cgdx":".{1,20}","cgrs":".{1,10}","dwmc":".{1,20}","sqly":".{1,50}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sjldyj":".{1,1}","sjldqm":".{1,30}","sjldqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kskfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","kfdd":".{1,50}","sqdh":".{1,20}","sqr":".{1,30}","fzr":".{1,30}","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
    @ApiOperation("对外开放完毕登记保存")
    @PostMapping("/saveByWbdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> saveByWbdj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/dwkf/saveByWbdj";
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

            List<DwkfModel> modelList = JSONArray.parseArray(map.get("entity").toString(), DwkfModel.class);
            DwkfModel dwkfModel = modelList.get(0);
            String taskid = dwkfModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(dwkfModel));

            dwkfModel.setJsbh(jsbh);
            dwkfModel.setUpdatetime(new Date());
            System.err.println("model--"+JSON.toJSONString(dwkfModel));
            ResponseMessage<String> result = jlsServerApis.dwkfBySzyj(dwkfModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/dwkf/ywtzQuery  对外开放业务台账查询
     * @apiVersion 0.4.0
     * @apiName ywtzQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   对外开放业务台账查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}kfsj                                                 开放时间
     * @apiSuccess {String}kflx                                                 开放类型
     * @apiSuccess {String}cgdx                                                 参观对象
     * @apiSuccess {String}cgrs                                                 参观人数
     * @apiSuccess {String}uuid                                                 UUID
     * @apiSuccess {String}dwmc                                                 单位名称
     * @apiSuccess {String}sqly                                                 申请理由
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}szyj                                                 所长意见
     * @apiSuccess {String}szyjnr                                               所长意见内容
     * @apiSuccess {String}szqm                                                 所长签名
     * @apiSuccess {String}szqmsj                                               所长签名时间
     * @apiSuccess {String}sjldyj                                               上级领导意见
     * @apiSuccess {String}sjldyjnr                                             上级领导意见内容
     * @apiSuccess {String}sjldqm                                               上级领导签名
     * @apiSuccess {String}sjldqmsj                                             上级领导签名
     * @apiSuccess {String}kskfsj                                               开始开放时间
     * @apiSuccess {String}kfdd                                                 开放地点
     * @apiSuccess {String}sqdh                                                 申请单号
     * @apiSuccess {String}sqr                                                  申请人
     * @apiSuccess {String}fzr                                                  负责人
     * @apiSuccess {String}hdqk                                                 活动情况
     * @apiSuccess {String}jssj                                                 结束时间
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}taskid                                               任务id
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}scbz                                                 上传标志
     * @apiSuccess {String}operator                                             操作人
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}updator                                              修改人
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jsbhString                                           监所编号(已转换)
     * @apiSuccess {String}kflxString                                           开放类型(已转换)
     * @apiSuccess {String}szyjString                                           所长意见(已转换)
     * @apiSuccess {String}sjldyjString                                         上级领导意见(已转换)
     * @apiSuccess {String}stateString                                          状态(已转换)
     * @apiSuccess {String}scbzString                                           上传标志(已转换)
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
     *       id: "11000012120200222000146"
     *       jsbh: "110000121"
     *       tbr: "管理员"
     *       tbrq: "2020-02-24"
     *       kfsj: "2020-02-22 15:17:13"
     *       kflx: "02"
     *       cgdx: "北京看守所"
     *       cgrs: "10"
     *       uuid: "11000012115823559149335"
     *       dwmc: "北京看守所"
     *       sqly: "申请理由"
     *       bz: "备注"
     *       szyj: "1"
     *       szyjnr: "同意对外开放。"
     *       szqm: "管理员"
     *       szqmsj: "2020-02-24 10:47:05"
     *       sjldyj: "1"
     *       sjldyjnr: "同意对外开放。"
     *       sjldqm: "管理员"
     *       sjldqmsj: "2020-02-24 11:15:20"
     *       kskfsj: "2020-02-24 11:15:29"
     *       kfdd: "1"
     *       sqdh: "1"
     *       sqr: "管理员"
     *       fzr: "管理员"
     *       hdqk: "活动情况"
     *       jssj: "2020-02-24 11:17:35"
     *       ywlcid: "4560327"
     *       taskid: "4560761"
     *       state: "R2"
     *       scbz: ""
     *       operator: ""
     *       creator: "管理员"
     *       createtime: "2020-02-22 15:18:35"
     *       updator: "管理员"
     *       updatetime: "2020-02-24 11:16:37"
     *       jsbhString: ""
     *       kflxString: "团体"
     *       szyjString: "批示通过"
     *       sjldyjString: "批示通过"
     *       stateString: "有效"
     *       scbzString: ""
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
     *      "kfsjStart":"开放时间",
     *      "kfsjEnd":"开放时间",
     *      "kflx":"开放类型",
     *      "cgrs":"参观人数",
     *      "dwmc":"单位名称",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //id,jsbh,tbr,tbrq,kfsj,kflx,cgdx,cgrs,uuid,dwmc,sqly,bz,szyj,szyjnr,szqm,szqmsj,sjldyj,sjldyjnr,sjldqm,sjldqmsj,kskfsj,kfdd,sqdh,sqr,fzr,hdqk,jssj,ywlcid,taskid,scbz,jsbhString,kflxString,szyjString,sjldyjString,scbzString
    @ApiOperation("对外开放业务台账查询")
    @GetMapping("/ywtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/dwkf/ywtzQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kfsjStart"))) {
                queryParam.and("kfsj", TermType.gte, maps.getResult().get("kfsjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kfsjEnd"))) {
                queryParam.and("kfsj", TermType.lte, maps.getResult().get("kfsjEnd"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("kflx"))){
                queryParam.and("kflx", TermType.eq, maps.getResult().get("kflx"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("cgrs"))){
                queryParam.and("cgrs", TermType.eq, "%"+maps.getResult().get("cgrs")+"%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dwmc"))){
                queryParam.and("dwmc", TermType.eq, "%"+maps.getResult().get("dwmc")+"%");
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<DwkfModel>> result = jlsServerApis.dwkfYwtzQuery(queryParam);
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
