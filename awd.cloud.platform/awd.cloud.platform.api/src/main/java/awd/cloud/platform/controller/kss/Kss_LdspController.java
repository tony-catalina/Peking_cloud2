/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JkqkModel;
import awd.bj.kss.model.LdspModel;
import awd.bj.kss.model.XjModel;
import awd.bj.kss.model.XzpaModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.JstzLdspModel;
import awd.cloud.platform.model.kss.Kss_LdspModel;
import awd.cloud.platform.model.kss.LdspModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.Result;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/ldsp")
@Api(tags = "kss-ldsp", description = "Ldsp")
public class Kss_LdspController extends PublicService {

    @Autowired
    private KssService kssService;

    @Autowired
    private KssServerApis kssServerApis;


    @OpenAPI
    @ApiOperation("分页查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<Kss_LdspModel>> ldsp_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_LdspModel>> result = kssService.ldsp_query(queryParam);
        if (result.getStatus() == 200) {
            result.setMessage("查询成功");
            if (result.getResult() == null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
    }

    /**
     * @api {post} /v4/kss/ldsp/ldspAdd 领导审批保存
     * @apiVersion 0.4.0
     * @apiName ldspAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 领导审批保存.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *      "taskid":"任务ID(必填)",
     *      "processDefinitionKey":"流程ID",
     *       "entity":[{
     *                     "jbxxxm":"姓名(必填)",
     *                     "creator":"创建人(必填;最大字段长度：50)",
     *                     "blsj":"办理时间(必填; 格式：yyyy-MM-dd)",
     *                     "ywlcid":"业务流程ID(必填)",
     *                     "sqyy":"申请原因(必填;最大字段长度：500)",
     *                     "psbz":"审批意见(批示结果)(必填;字典（PS）；最大字段长度：1)",
     *                     "ldyj":"领导批示意见(必填;最大字段长度：500)"
     *                  }]
     *             }
     *
     *
     */
    // {"creator":".{1,50}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sqyy":".{1,500}","psbz":"\\d{1,1}","ldyj":".{1,500}"}
    @ApiOperation("领导审批保存")
    @PostMapping("/ldspAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<?> ldspAdd(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh,String json) {

        String interfaceId = "/v4/kss/ldsp/ldspAdd";

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


            List<LdspModelDO> ldspList = JSONArray.parseArray(map.get("entity").toString(), LdspModelDO.class);
            String taskid = map.get("taskid").toString();
            if (null==taskid  || "".equals(taskid)) {
                return ResponseMessage.error("任务ID:taskid必传");
            }
            String processDefinitionKey = map.get("processDefinitionKey").toString();

            LdspModelDO ldspModel= ldspList.get(0);
            ldspModel.setJsbh(jsbh);
            ldspModel.setProcessName(processDefinitionKey);
            System.out.println("=========="+processDefinitionKey);
            ldspModel.setCreatetime(new Date());
            System.out.println("-------------"+ldspModel.getBlsj().toString());
            ldspModel.setBlsj(ldspModel.getBlsj());
            ldspModel.setBlr(ldspModel.getCreator());
            ldspModel.setState("R2");
            System.out.println("-------------------------"+ldspModel.getCreator());
            System.out.println("----------------------------------"+ldspModel.getYwlcid());
            if(null==ldspModel.getJbxxXm() || "".equals(ldspModel.getJbxxXm())){
                return ResponseMessage.error("请输入姓名");
            }

            System.out.println(ldspModel.getJbxxXm()+"_+_+_+_+_+_+_+_+");
            ldspModel.setSqr(ldspModel.getJbxxXm());

            ResponseMessage<?> ldsp = kssServerApis.ldspSave(ldspModel, taskid);

            if(ldsp.getStatus() == 200){
                ldsp.setMessage("保存成功!");
            }else{
                ldsp.setMessage("服务异常,保存失败!");
            }
            return ldsp;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }

    }




    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldsp_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_LdspModel data) {
        return kssService.ldsp_updateByKey(id, data);
    }


    @OpenAPI
    public ResponseMessage<Kss_LdspModel> ldsp_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.ldsp_getByKey(id);
    }

    /**
     * @api {post} /v4/kss/ldsp/xzpa_ldspflow 协助破案领导审批保存
     * @apiVersion 0.4.0
     * @apiName xzpa_ldspflow
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 协助破案领导审批保存.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *      "taskid":"任务ID(必填)",
     *      "processDefinitionKey":"流程ID",
     *       "entity":[{
     *                     "id":"id(必填;最大字段长度：23)",
     *                     "ywlcid":"业务流程ID(必填;最大字段长度：30)",
     *                     "rybh":"人员编号(最大字段长度：21)",
     *                     "taskid":"任务id(必填;最大字段长度：30)",
     *                     "creator":"创建人(最大字段长度：30)",
     *                     "createtime":"创建时间(格式：yyyy-MM-dd hh:mm:ss)",
     *                     "tbjurq":"坦白检举日期(格式：yyyy-MM-dd hh:mm:ss)",
     *                     "sldyj":"所领导意见(必填;最大字段长度：1)",
     *                     "sldyjnr":"所领导意见内容(必填;最大字段长度：500)",
     *                     "sldqmrq":"签字时间(格式：yyyy-MM-dd hh:mm:ss)",
     *                     "sldqm":"所领导签字(必填;最大字段长度：50)",
     *                     "jjqxz":"检举案性质(最大字段长度：100)"
     *                  }]
     *             }
     *
     *
     */
    // {"creator":".{1,50}","sldyj":"\\d{1}","sldyjnr":"\\S{1,500}","sldqm":"\\S{1,50}"}
    //{"entity":[{"sldqm":"jnj","id":"11000011420191104000059","ywlcid":"3973156","rybh":"110000114201910160001","taskid":"3973370","creator":"aa","createtime":"2019-12-30 10:10:10","tbjurq":"2019-11-04 10:10:10","sldyj":"1","sldyjnr":"ff","sldqmrq":"2019-12-30 10:10:10","jjqxz":"1"}]}
    @ApiOperation("协助破案领导审批保存")
    @PostMapping("/xzpa_ldspflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xzpa_ldspflow(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh,String json) {

        String interfaceId = "/v4/kss/ldsp/xzpa_ldspflow";

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
            List<Map<String, Object>> rslist = toListMap(map.get("entity").toString());
            XzpaModel xzpaModel=JSONObject.parseObject(rslist.get(0).toString(),XzpaModel.class);
            String taskid = (String) rslist.get(0).get("taskid");
            if (null==taskid  || "".equals(taskid)) {
                return ResponseMessage.error("任务ID:taskid必传");
            }
            xzpaModel.setJsbh(jsbh);
            ResponseMessage<String> result = kssServerApis.xzpaSpFlow(xzpaModel, taskid);

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

    @OpenAPI
    public ResponseMessage<Integer> ldsp_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.ldsp_deleteByKey(id);
    }

    /**
     * @api {post} /v4/kss/ldsp/ldspForRsdj 入所登记领导审批
     * @apiVersion 0.4.0
     * @apiName ldspForRsdj
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 入所登记领导审批
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
     *   "message": null,
     *   "result": "审批成功！",
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
     *        "taskid": "任务id(必填)",
     * 	      "xm": "姓名(必填)",
     * 	      "entity": [{
     * 		     "gcbh": "人员编号(必填; 最大长度:50)",
     * 		     "ysyj": "医生意见(必填; 最大长度:100)",
     * 		     "updator": "更新人(必填; 最大长度:50)",
     * 		     "ldyj": "领导意见(必填; 最大长度:200)",
     * 		     "ldpssj": "领导审批时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 		     "ldxm": "领导意见(必填; 最大长度:50)",
     * 		     "ldpsbz": "领导批示标志(必填; 最大长度:1; 字典:0,未批示 1,批示通过 2,批示未通过)",
     * 		     "ywlcid": "业务流程id(必填; 最大长度:15)"
     *        }]
     *      }
     * }
     *
     */
    @ApiOperation("入所登记领导审批")
    @PostMapping("/ldspForRsdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldspForRsdj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldsp/ldspForRsdj";
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

            if(StringUtils.isNullOrEmpty(map.get("taskid"))) {
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = map.get("taskid").toString();

            if(StringUtils.isNullOrEmpty(map.get("xm"))) {
                return ResponseMessage.error("xm不可为空");
            }
            String xm = map.get("xm").toString();

            List<JkqkModel> modelList = JSONArray.parseArray(map.get("entity").toString(), JkqkModel.class);
            JkqkModel jkqkModel = modelList.get(0);
            System.err.println("model--"+ JSON.toJSONString(jkqkModel));

            jkqkModel.setState("R2");
            jkqkModel.setUpdatetime(new Date());
            jkqkModel.setJsbh(jsbh);

            System.err.println("model--"+JSON.toJSONString(jkqkModel));
            ResponseMessage<String> fyqrModel = kssServerApis.ldspForRsdj(jkqkModel,taskid,xm);
            return fyqrModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


    /**
     * @api {post} /v4/kss/ldsp/ldspByjstz 监室调整领导审批
     * @apiVersion 0.4.0
     * @apiName ldspByjstz
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监室调整领导审批
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
     *   "message": null,
     *   "result": "审批成功！",
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
     *        "taskid": "任务id(必填)",
     * 	      "sz": "1:同监区调监; 2:不同监区调监; 3:中断流程(不填默认为3)",
     * 	      "entity": [{
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "creator": "创建人(必填; 最大长度:50)",
     * 		     "sqr": "申请人(必填; 最大长度:50)",
     * 		     "sqsj": "申请时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 		     "psbz": "批示标志(必填; 1:同意 2:不同意; 最大长度:1)",
     * 		     "ldyj": "领导意见(最大长度:500)",
     * 		     "blr": "办理人(必填; 最大长度:50)",
     * 		     "sqyy": "申请原因(必填; 最大长度:500)",
     * 		     "blsj": "办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 		     "ywlcid": "业务流程id(必填; 最大长度:50)",
     * 		     "yjsh": "原监室号(最大长度:4)",
     * 		     "xjsh": "现监室号(最大长度:4)"
     *        }]
     *      }
     * }
     *
     */
    @ApiOperation("监室调整领导审批")
    @PostMapping("/ldspByjstz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldspByjstz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldsp/ldspByjstz";
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

            //获取taskid
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = maps.getResult().get("taskid").toString();

            //创建实体类
            LdspModelDO ldspModel = JSONArray.parseArray(map.get("entity").toString(), LdspModelDO.class).get(0);
            ldspModel.setJsbh(jsbh);
            ldspModel.setState("R2");
            ldspModel.setCreatetime(new Date());
            ldspModel.setProcessNode("kss_jstz_zdzyj");
            System.err.println("ldspModel==" + JSON.toJSONString(ldspModel));

            JstzLdspModel jstzLdspModel = new JstzLdspModel();
            jstzLdspModel.setTaskid(taskid);
            jstzLdspModel.setLdspEntity(ldspModel);
            System.err.println("jstzLdspModel==" + JSON.toJSONString(jstzLdspModel));

            //判断sz
            if (StringUtils.isNullOrEmpty(maps.getResult().get("sz"))) {
                jstzLdspModel.setSz("3");
            } else {
                jstzLdspModel.setSz(maps.getResult().get("sz").toString());
            }
            System.err.println("sz==" + jstzLdspModel.getSz());

            List<JstzLdspModel> modelList = new ArrayList<>();
            modelList.add(jstzLdspModel);

            ResponseMessage<String> result = kssServerApis.ldspSavejstz(modelList);
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
     * @api {post} /v4/kss/ldsp/xj_addSpflow 械具增添审批保存
     * @apiVersion 0.4.0
     * @apiName xj_addSpflow
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 械具增添审批保存.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *      "taskid":"任务ID(必填)",
     *      "processDefinitionKey":"流程ID",
     *       "entity":[{
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                  "syqx":"使用情景(最大字段长度：10)",
     *                  "id":"id(最大字段长度：23)",
     *                  "syts":"使用天数(必填;最大字段长度：4)",
     *                  "syqxString":"使用情景(字典转换)(最大字段长度：200)",
     *                  "ly":"理由()",
     *                  "zdzpsbz":"中队长批示标志(必填;最大字段长度：1)",
     *                  "zdzyj":"中队长意见(必填;最大字段长度：200)",
     *                  "zdzxm":"中队长姓名(必填;最大字段长度：30)",
     *                  "zdzpssj":"中队长批示时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
     *                  "taskid":"任务id(必填;最大字段长度：30)",
     *                  "ywlcid":"流程id(必填;最大字段长度：30)",
     *                  "type":"类型(必填;)"
     *                  }]
     *             }
     *
     *
     */
    // {"rybh":"\\d{1,21}","zdzpsbz":"\\d{1}","zdzyj":"\\S{1,200}","zdzxm":"\\S{1,30}","zdzpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"rybh":"110000114201912190006","syqx":"1","id":"11000011420191219000445","sqrq":"2019-12-30","syts":"1","syqxString":"一审判处死刑","ly":"3","zdzpsbz":"1","zdzyj":"同意","zdzxm":"fff","zdzpssj":"2019-12-30 10:10:10","taskid":"5390622","ywlcid":"5390404","type":"1"}]}
    @ApiOperation("械具增添审批保存")
    @PostMapping("/xj_addSpflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xj_addSpflow(HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh,String json) {

        String interfaceId = "/v4/kss/ldsp/xj_addSpflow";

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
            List<Map<String, Object>> rslist = toListMap(map.get("entity").toString());
            XjModel xjModel=JSONObject.parseObject(rslist.get(0).toString(),XjModel.class);
            String type = (String) rslist.get(0).get("type");
            String ywlcid = (String) rslist.get(0).get("ywlcid");
            String taskid = (String) rslist.get(0).get("taskid");
            if (null==taskid  || "".equals(taskid)) {
                return ResponseMessage.error("任务ID:taskid必传");
            }
            if (StringUtils.isNullOrEmpty(ywlcid)) {
                return ResponseMessage.error("ywlcid必传");
            }
            if (StringUtils.isNullOrEmpty(type)) {
                return ResponseMessage.error("type必传");
            }
            xjModel.setJsbh(jsbh);
            ResponseMessage<String> result = kssServerApis.xjsySpFlow(xjModel,taskid,type);

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



}
