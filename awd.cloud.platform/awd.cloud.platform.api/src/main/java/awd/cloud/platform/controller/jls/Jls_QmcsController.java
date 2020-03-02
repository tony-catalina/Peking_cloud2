package awd.cloud.platform.controller.jls;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.JlsServerApis;
import awd.bj.jls.model.*;
import awd.cloud.platform.model.jls.WpglInfoModel;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/qmcs")
@Api(tags = "jls-qmcs",description = "qmcs")
public class Jls_QmcsController extends PublicService {

    @Autowired
    private JlsServerApis jlsServerApis;
    @Autowired
    private ProcessService processService;

    /**
     * @api {post} /v4/jls/qmcs/qmcsGjhd 期满出所管教核定
     * @apiVersion 0.4.0
     * @apiName qmcsGjhd
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 期满出所管教核定.
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
     *      "csyy":"出所原因(必填; 最大字段长度：2)",
     *      "ncsrq":"拟出所日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "csqx":"出所去向(必填; 最大字段长度：40)",
     *      "pzjg":"批准机关",
     *      "bxqk":"表现情况",
     *      "bz":"备注",
     *      "gjyj":"管教意见(必填; 最大字段长度：2)",
     *      "gjqm":"管教签名(必填; 最大字段长度：10)",
     *      "gjqmrq":"管教签名日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":".{1,21}","csyy":".{1,2}","ncsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","csqx":".{1,40}","gjyj":".{1,2}","gjqm":".{1,10}","gjqmrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
    @ApiOperation("期满出所管教核定")
    @PostMapping("qmcsGjhd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qmcsGjhd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/qmcs/qmcsGjhd";

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

            String flowkey = CacheUtils.get().getFlowKey("JLS_QMCS");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            //流程互斥
            String key = "jls_qmcs";
            if (processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(), "clcs").getStatus() != 200) {
                return processService.FlowMutex(jsbh, maps.getResult().get("rybh").toString(), key.toUpperCase(),"clcs");
            }
            List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
            ClcsModel jls_clcsModel = clcsList.get(0);
            jls_clcsModel.setState("R2");
            jls_clcsModel.setCsbllx("1");
            jls_clcsModel.setSjzljsbz("1");
            jls_clcsModel.setJsbh(jsbh);
            jls_clcsModel.setCreatetime(new Date());
            ResponseMessage<String> result = jlsServerApis.clcsStartflow(flowkey, jls_clcsModel);
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
     * @api {post} /v4/jls/qmcs/szspAdd 期满出所所长审批
     * @apiVersion 0.4.0
     * @apiName szspAdd
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 期满出所所长审批
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
     * 	      "entity": [{
     * 	         "id":"id(必填; 最大长度:23)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "ywlcid": "业务流程id(必填; 最大长度:15)",
     * 		     "flwsh": "法律文书号(必填; 最大长度:30)",
     *           "szyj": "所长意见(必填; 最大长度:1)",
     *           "szyjnr": "所长意见内容(必填)",
     *           "szqm": "所长签名(必填; 最大长度:30)",
     *           "szqmsj": "所长签名时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		     "updator": "更新人(必填; 最大长度:30)",
     *        }]
     *      }
     * }
     */
    //{"id":".{1,23}","rybh":".{1,21}","ywlcid":".{1,15}","flwsh":".{1,30}","szyj":".{1,1}","szqm":".{1,30}","szqmsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
    @ApiOperation("期满出所所长审批")
    @PostMapping("/szspAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> szspAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qmcs/szspAdd";
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
            List<ClcsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
            ClcsModel jls_clcsModel = modelList.get(0);
            System.err.println("model--"+ JSON.toJSONString(jls_clcsModel));

            jls_clcsModel.setState("R2");
            jls_clcsModel.setUpdatetime(new Date());
            jls_clcsModel.setJsbh(jsbh);

            System.err.println("model--"+JSON.toJSONString(jls_clcsModel));
            ResponseMessage<String> result = jlsServerApis.clcsSpflow(jls_clcsModel,taskid,"2");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/qmcs/addFlowWfwth 期满出所无附物退还
     * @apiVersion 0.4.0
     * @apiName addFlowWfwth
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 期满出所无附物退还
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
     * 	      "entity": [{
     * 	         "taskid": "任务id(必填)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)"
     *        }]
     *      }
     * }
     */
    //{"rybh":".{1,21}"}
    @ApiOperation("期满出所无附物退还")
    @PostMapping("/addFlowWfwth")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addFlowWfwth(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qmcs/addFlowWfwth";
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

            List<ClcsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
            ClcsModel jls_clcsModel = modelList.get(0);
            String taskid = jls_clcsModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(jls_clcsModel));

            Variables variables = new Variables();
            Map<String, Object> m = new HashMap<>();
            variables.setRybh(jls_clcsModel.getRybh());
            variables.setJsbh(jsbh);
            m.put("rybh",jls_clcsModel.getRybh());
            variables.setParams(m);
            System.err.println("model--"+JSON.toJSONString(jls_clcsModel));
            ResponseMessage<String> result = jlsServerApis.addFlowWfwth(taskid, variables);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/qmcs/plFlowwpgl 期满出所附物退还
     * @apiVersion 0.4.0
     * @apiName plFlowwpgl
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 期满出所附物退还
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
     *         "taskid": "任务id(必填)",
     *         "wpx":[{"id":"11000012120191127000185","jsbh":"110000121","rybh":"110000121201911270001","uuid":"19112700011574841915862","wply":"","mc":"dfasd","sl":3,"wpdw":"16","wplx":"0","xjcd":"","wpxx":"","ys":"","djr":"管理员","djrq":"2019-11-27 16:05:16","lqzt":"0","lqr":"","lqrq":null,"twr":"","twrq":null,"jzr":"","jzrq":null,"bz":"asdf","state":"R2","creator":"管理员","createtime":"2019-11-27 16:05:16","updator":"","updatetime":null,"jsbhString":"","wplyString":"随身携带","mcString":"dfasd","wpdwString":"枚","wplxString":"日常物品","xjcdString":"","ysString":"","lqztString":"未领取","stateString":"有效"}],
     *         "num":"物品数量",
     * 	       "entity": [{
     *              "lqr": "领取人(必填; 最大长度:30)"",
     *              "lqrq": "领取时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *              "twr": "退物办理人(必填; 最大长度:30)"",
     *              "updatetime": "修改时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		        "rybh": "人员编号(必填; 最大长度:21)"
     *        }]
     *      }
     * }
     */
    //{"rybh":".{1,21}","lqr":".{1,30}","lqrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","twr":".{1,30}","updatetime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("期满出所附物退还")
    @PostMapping("/plFlowwpgl")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> plFlowwpgl(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qmcs/plFlowwpgl";
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

            List<WpglModel> wpgl = JSONArray.parseArray(map.get("entity").toString(), WpglModel.class);
            WpglModel jls_wpglModel = wpgl.get(0);
            String wpxx = map.get("wps").toString();
            List<WpglModel> wpgls = JSONUtil.toList(wpxx,WpglModel.class);
            List<WpglModel> wpglModels = new ArrayList<WpglModel>();
            try {
                for (WpglModel wpglModel : wpgls) {
                    wpglModel.setLqzt("1");
                    wpglModel.setLqr(jls_wpglModel.getLqr());
                    wpglModel.setTwr(jls_wpglModel.getTwr());
                    wpglModel.setUpdator(jls_wpglModel.getTwr());
                    if (!StringUtils.isNullOrEmpty(jls_wpglModel.getLqrq())) {
                        wpglModel.setLqrq(jls_wpglModel.getLqrq());
                    } else {
                        wpglModel.setLqrq(new Date());
                    }
                    if (!StringUtils.isNullOrEmpty(jls_wpglModel.getUpdatetime())) {
                        wpglModel.setTwrq(jls_wpglModel.getUpdatetime());
                    } else {
                        wpglModel.setTwrq(new Date());
                    }
                    wpglModels.add(wpglModel);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseMessage.error("保存失败！");
            }

            String taskid = map.get("taskid").toString();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }

            Variables variables = new Variables();
            Map<String, Object> m = new HashMap<>();
            variables.setRybh(jls_wpglModel.getRybh());
            variables.setJsbh(jsbh);
            m.put("rybh",jls_wpglModel.getRybh());
            variables.setParams(m);
            System.err.println("model--"+JSON.toJSONString(jls_wpglModel));

            WpglInfoModel wpglInfoModel = new WpglInfoModel();
            wpglInfoModel.setTaskid(taskid);
            wpglInfoModel.setJsbh(jsbh);
            wpglInfoModel.setWpglEntities(wpglModels);
            wpglInfoModel.setVariables(variables);
            ResponseMessage<String> result = jlsServerApis.savePLAsCustom(wpglInfoModel);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/jls/qmcs/qmcskzdy 期满出所开证打印
     * @apiVersion 0.4.0
     * @apiName qmcskzdy
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 期满出所开证打印
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
     * 	         "id":"id(必填; 最大长度:23)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填; 最大长度:15)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "gjqm":"管教签名(必填; 最大字段长度：10)",
     *           "gjqmrq":"管教签名日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *           "flwsh": "法律文书号(必填; 最大长度:30)",
     * 		     "jbr": "经办人(必填; 最大长度:30)",
     * 		     "jbsj": "经办时间((必填; 格式：yyyy-MM-dd hh:mm:ss))",
     *        }]
     *      }
     * }
     */
    //{"id":".{1,23}","ywlcid":".{1,15}","rybh":".{1,21}","gjqm":".{1,10},"gjqmrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","flwsh":".{1,30}","jbr":".{1,30}","jbsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("期满出所开证打印")
    @PostMapping("/qmcskzdy")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qmcskzdy(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qmcs/qmcskzdy";
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

            List<ClcsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
            ClcsModel jls_clcsModel = modelList.get(0);
            String taskid = jls_clcsModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(jls_clcsModel));

            ResponseMessage<String> result = jlsServerApis.qmcsZxflow(jls_clcsModel,taskid, "1");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


    /**
     * @api {post} /v4/jls/qmcs/qmcsCs 期满出所出所
     * @apiVersion 0.4.0
     * @apiName qmcsCs
     * @apiGroup jls
     * @apiPermission user
     *
     * @apiDescription 期满出所出所
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
     * 	         "id":"id(必填; 最大长度:23)",
     * 	         "taskid": "任务id(必填)",
     * 	         "ywlcid": "业务流程id(必填; 最大长度:15)",
     * 		     "rybh": "人员编号(必填; 最大长度:21)",
     * 		     "csbllx": "出所办理类型(必填; 最大长度:1)",
     * 		     "ccsj": "出所时间((必填; 格式：yyyy-MM-dd hh:mm:ss))",
     * 		     "jbr": "经办人(必填; 最大长度:30)",
     * 		     "jbsj": "经办时间((必填; 格式：yyyy-MM-dd hh:mm:ss))",
     *        }]
     *      }
     * }
     */
    //{"id":".{1,23}","ywlcid":".{1,15}","rybh":".{1,21}","csbllx":".{1,1},"ccsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbr":".{1,30}","jbsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("期满出所出所")
    @PostMapping("/qmcsCs")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qmcsCs(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qmcs/qmcsCs";
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

            List<ClcsModel> modelList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
            ClcsModel jls_clcsModel = modelList.get(0);
            String taskid = jls_clcsModel.getTaskid();
            if(StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid不可为空");
            }
            System.err.println("model--"+ JSON.toJSONString(jls_clcsModel));

            ResponseMessage<String> result = jlsServerApis.qmcsZxflow(jls_clcsModel,taskid, "2");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/qmcs/ywtzQuery   期满出所业务台账查询
     * @apiVersion 0.4.0
     * @apiName ywtzQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   期满出所业务台账查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}csrq                                                 出所日期
     * @apiSuccess {String}gjyj                                                 管教意见
     * @apiSuccess {String}tbrqString                                           填表日期（已转换）
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}szqmsjString                                         所长签名时间（已转换）
     * @apiSuccess {String}ncsrqString                                          拟出所日期（已转换）
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}szyj                                                 所长意见
     * @apiSuccess {String}gjqm                                                 管教签名
     * @apiSuccess {String}szyjnr                                               所长意见内容
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}flwsh                                                法律文书号
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}taskid                                               业务进程id
     * @apiSuccess {String}bxqk                                                 表现情况
     * @apiSuccess {String}csyyString                                           出所原因（已转换）
     * @apiSuccess {String}ncsrq                                                拟出所日期
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}sjzljsbz                                             及时标记(SHFO)
     * @apiSuccess {String}csrqString                                           出所日期（已转换）
     * @apiSuccess {String}csbllxString                                         出所办理类型（已转换）
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}xbString                                             性别（已转换）
     * @apiSuccess {String}pzjg                                                 批准机关
     * @apiSuccess {String}gjqmrq                                               管教签名日期
     * @apiSuccess {String}szqmsj                                               所长签名时间
     * @apiSuccess {String}csbllx                                               出所办理类型
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}szqm                                                 所长签名
     * @apiSuccess {String}gjqmrqString                                         管教签名日期（已转换）
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}csqx                                                 出所去向
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jsh                                                  拘室号
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}csyy                                                 出所原因
     * @apiSuccess {String}szyjString                                           所长意见（已转换）
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
     *     "total": 5,
     *     "data": [
     *       {
     *       csrq: 1008172800000
     *       gjyj: ""
     *       tbrqString: "2020-02-20"
     *       tbr: "管理员"
     *       szqmsjString: "2020-02-20"
     *       ncsrqString: "2020-02-20"
     *       ywlcid: "4556191"
     *       szyj: "1"
     *       gjqm: "管理员"
     *       szyjnr: "同意拘留人解除拘留"
     *       snbh: "20190078"
     *       flwsh: "22"
     *       bz: ""
     *       id: "11000012120200220000330"
     *       tbrq: 1582128000000
     *       state: "R2"
     *       taskid: "4556387"
     *       bxqk: ""
     *       csyyString: "拘留期满"
     *       ncsrq: 1582128000000
     *       creator: "管理员"
     *       createtime: 1582181158000
     *       sjzljsbz: "1"
     *       csrqString: "2001-12-13"
     *       csbllxString: "刑满出所"
     *       xb: "1"
     *       xbString: "男性"
     *       pzjg: ""
     *       gjqmrq: 1582181225000
     *       szqmsj: 1582181233000
     *       csbllx: "1"
     *       xm: "2"
     *       szqm: "管理员"
     *       gjqmrqString: "2020-02-20"
     *       rybh: "110000121201911260003"
     *       csqx: "回家"
     *       updatetime: 1582181159000
     *       jsh: "0202"
     *       jsbh: "110000121"
     *       csyy: "20"
     *       szyjString: "批示通过"
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
     *      "csyy":"出所原因",
     *      "cssjStart":"出所时间",
     *      "cssjEnd":"出所时间",
     *      "csqx":"出所去向",
     *      "pzjg":"批准机关",
     *      "state": "R2",
     *      "csbllx": "1",
     *      "jbxx_state": "R7",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //csrq,gjyj,tbrqString,tbr,szqmsjString,ncsrqString,ywlcid,szyj,gjqm,szyjnr,snbh,flwsh,bz,id,tbrq,taskid,bxqk,csyyString,ncsrq,sjzljsbz,csrqString,csbllxString,xb,xbString,pzjg,gjqmrq,szqmsj,csbllx,xm,szqm,gjqmrqString,rybh,csqx,jsh,jsbh,csyy,szyjString
    @ApiOperation("期满出所业务台账查询")
    @GetMapping("/ywtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/qmcs/ywtzQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csyy"))) {
                queryParam.and("csyy", TermType.eq, maps.getResult().get("csyy"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("cssjStart"))) {
                queryParam.and("cssj", TermType.gte, maps.getResult().get("cssjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("cssjEnd"))) {
                queryParam.and("cssj", TermType.lte, maps.getResult().get("cssjEnd"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csqx"))) {
                queryParam.and("csqx", TermType.eq, maps.getResult().get("csqx"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("pzjg"))) {
                queryParam.and("pzjg", TermType.eq, maps.getResult().get("pzjg"));
            }
            queryParam.and("state", TermType.eq, maps.getResult().get("state"));
            queryParam.and("csbllx", TermType.eq, maps.getResult().get("csbllx"));
            queryParam.and("jbxx_state", TermType.eq, maps.getResult().get("jbxx_state"));
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.ywtzQuery(queryParam);
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
     * @api {get} /v4/jls/qmcs/ywdtQuery   期满出所业务动态查询
     * @apiVersion 0.4.0
     * @apiName ywdtQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   期满出所业务动态查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}csrq                                                 出所日期
     * @apiSuccess {String}gjyj                                                 管教意见
     * @apiSuccess {String}tbrqString                                           填表日期（已转换）
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}szqmsjString                                         所长签名时间（已转换）
     * @apiSuccess {String}ncsrqString                                          拟出所日期（已转换）
     * @apiSuccess {String}ywlcid                                               业务流程id
     * @apiSuccess {String}szyj                                                 所长意见
     * @apiSuccess {String}gjqm                                                 管教签名
     * @apiSuccess {String}szyjnr                                               所长意见内容
     * @apiSuccess {String}snbh                                                 所内编号
     * @apiSuccess {String}flwsh                                                法律文书号
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}taskid                                               业务进程id
     * @apiSuccess {String}bxqk                                                 表现情况
     * @apiSuccess {String}csyyString                                           出所原因（已转换）
     * @apiSuccess {String}ncsrq                                                拟出所日期
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}sjzljsbz                                             及时标记(SHFO)
     * @apiSuccess {String}csrqString                                           出所日期（已转换）
     * @apiSuccess {String}csbllxString                                         出所办理类型（已转换）
     * @apiSuccess {String}xb                                                   性别
     * @apiSuccess {String}xbString                                             性别（已转换）
     * @apiSuccess {String}pzjg                                                 批准机关
     * @apiSuccess {String}gjqmrq                                               管教签名日期
     * @apiSuccess {String}szqmsj                                               所长签名时间
     * @apiSuccess {String}csbllx                                               出所办理类型
     * @apiSuccess {String}xm                                                   姓名
     * @apiSuccess {String}szqm                                                 所长签名
     * @apiSuccess {String}gjqmrqString                                         管教签名日期（已转换）
     * @apiSuccess {String}rybh                                                 人员编号
     * @apiSuccess {String}csqx                                                 出所去向
     * @apiSuccess {String}updatetime                                           修改时间
     * @apiSuccess {String}jsh                                                  拘室号
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}csyy                                                 出所原因
     * @apiSuccess {String}szyjString                                           所长意见（已转换）
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
     *     "total": 5,
     *     "data": [
     *       {
     *       csrq: 1008172800000
     *       gjyj: ""
     *       tbrqString: "2020-02-20"
     *       tbr: "管理员"
     *       szqmsjString: "2020-02-20"
     *       ncsrqString: "2020-02-20"
     *       ywlcid: "4556191"
     *       szyj: "1"
     *       gjqm: "管理员"
     *       szyjnr: "同意拘留人解除拘留"
     *       snbh: "20190078"
     *       flwsh: "22"
     *       bz: ""
     *       id: "11000012120200220000330"
     *       tbrq: 1582128000000
     *       state: "R2"
     *       taskid: "4556387"
     *       bxqk: ""
     *       csyyString: "拘留期满"
     *       ncsrq: 1582128000000
     *       creator: "管理员"
     *       createtime: 1582181158000
     *       sjzljsbz: "1"
     *       csrqString: "2001-12-13"
     *       csbllxString: "刑满出所"
     *       xb: "1"
     *       xbString: "男性"
     *       pzjg: ""
     *       gjqmrq: 1582181225000
     *       szqmsj: 1582181233000
     *       csbllx: "1"
     *       xm: "2"
     *       szqm: "管理员"
     *       gjqmrqString: "2020-02-20"
     *       rybh: "110000121201911260003"
     *       csqx: "回家"
     *       updatetime: 1582181159000
     *       jsh: "0202"
     *       jsbh: "110000121"
     *       csyy: "20"
     *       szyjString: "批示通过"
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
     *      "csyy":"出所原因",
     *      "cssjStart":"出所时间",
     *      "cssjEnd":"出所时间",
     *      "csqx":"出所去向",
     *      "pzjg":"批准机关",
     *      "state": "R2",
     *      "csbllx": "1",
     *      "jbxx_state": "R7",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     *//*
    //csrq,gjyj,tbrqString,tbr,szqmsjString,ncsrqString,ywlcid,szyj,gjqm,szyjnr,snbh,flwsh,bz,id,tbrq,taskid,bxqk,csyyString,ncsrq,sjzljsbz,csrqString,csbllxString,xb,xbString,pzjg,gjqmrq,szqmsj,csbllx,xm,szqm,gjqmrqString,rybh,csqx,jsh,jsbh,csyy,szyjString
    @ApiOperation("期满出所业务动态查询")
    @GetMapping("/ywdtQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ywdtQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/qmcs/ywdtQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csyy"))) {
                queryParam.and("csyy", TermType.eq, maps.getResult().get("csyy"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("cssjStart"))) {
                queryParam.and("cssj", TermType.gte, maps.getResult().get("cssjStart"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("cssjEnd"))) {
                queryParam.and("cssj", TermType.lte, maps.getResult().get("cssjEnd"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csqx"))) {
                queryParam.and("csqx", TermType.eq, maps.getResult().get("csqx"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("pzjg"))) {
                queryParam.and("pzjg", TermType.eq, maps.getResult().get("pzjg"));
            }
            queryParam.and("state", TermType.eq, maps.getResult().get("state"));
            queryParam.and("csbllx", TermType.eq, maps.getResult().get("csbllx"));
            queryParam.and("jbxx_state", TermType.eq, maps.getResult().get("jbxx_state"));
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.ywtzQuery(queryParam);
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
    }*/

}
