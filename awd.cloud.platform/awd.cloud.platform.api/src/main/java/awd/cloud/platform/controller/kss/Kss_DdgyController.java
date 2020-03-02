package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.DdgyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：张延
 * Date：2020-01-17 14:56
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/ddgy")
@Api(tags = "kss-ddgy",description = "Ddgy")
public class Kss_DdgyController  extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     *
     * @api {get} /v4/kss/ddgy/ddgyList 单独关押查询
     * @apiVersion 0.4.0
     * @apiName ddgyList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 单独关押查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id												id
     * @apiSuccess {String}bllx												办理类型
     * @apiSuccess {String}bllxString										办理类型（已转换）
     * @apiSuccess {String}jsbh												监所编号
     * @apiSuccess {String}jsbhString										监所编号（已转换）
     * @apiSuccess {String}rybh												人员编号
     * @apiSuccess {String}ly												理由
     * @apiSuccess {String}lyString											理由（已转换）
     * @apiSuccess {String}jbqx												禁闭期限
     * @apiSuccess {String}kssj												开始时间
     * @apiSuccess {String}syts												禁闭天数
     * @apiSuccess {String}jssj												结束时间
     * @apiSuccess {String}ysxm												医生姓名
     * @apiSuccess {String}ysyj												医生意见
     * @apiSuccess {String}yspssj											医生批示时间
     * @apiSuccess {String}zdzxm											中队长姓名
     * @apiSuccess {String}zdzyj											中队长意见
     * @apiSuccess {String}zdzpssj											中队长批示时间
     * @apiSuccess {String}ldxm												领导姓名
     * @apiSuccess {String}ldyj												领导意见
     * @apiSuccess {String}ldpssj											领导批示意见
     * @apiSuccess {String}pastable											是否有效
     * @apiSuccess {String}pastableString									是否有效（已转换）
     * @apiSuccess {String}jbr												经办人
     * @apiSuccess {String}blrq												办理日期
     * @apiSuccess {String}lsh												流水号
     * @apiSuccess {String}bz												备注
     * @apiSuccess {String}ywlcid											业务流程id
     * @apiSuccess {String}psbz												批示标识
     * @apiSuccess {String}psbzString										批示标识（已转换）
     * @apiSuccess {String}state											状态
     * @apiSuccess {String}stateString										状态（已转换）
     * @apiSuccess {String}creator											创建人
     * @apiSuccess {String}createtime										创建时间
     * @apiSuccess {String}updator											更新人
     * @apiSuccess {String}updatetime										更新时间
     * @apiSuccess {String}xm												姓名
     * @apiSuccess {String}snbh												所内编号
     * @apiSuccess {String}jsh												监室号
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data":  [{
     *       "lsh": null,
     *       "zdzyj": "同意",
     *       "ywlcid": "2902867",
     *       "psbzString": "批示通过",
     *       "stateString": "有效",
     *       "ly": "14",
     *       "kssj": "2019-08-14 20:10:24",
     *       "jssj": "2019-08-17 20:10:24",
     *       "syts": 3,
     *       "ysyj": "同意",
     *       "ldpssj": "2019-08-14 00:00:00",
     *       "snbh": null,
     *       "jsh\r\n": null,
     *       "bz": null,
     *       "id": "11000011420190814000077",
     *       "state": "R2",
     *       "jsbhString": "北京市第一看守所",
     *       "ldxm": "管理员",
     *       "pastable": "1",
     *       "zdzpssj": "2019-08-14 00:00:00",
     *       "creator": "管理员",
     *       "createtime": "2019-08-14 20:10:45",
     *       "zdzxm": "管理员",
     *       "bllxString": "禁闭设置",
     *       "blrq": "2019-08-14 00:00:00",
     *       "pastableString": "是",
     *       "jbqx": "2019-08-21 20:10:24",
     *       "psbz": "1",
     *       "lyString": "企图自杀、自残、行凶、越狱的",
     *       "xm": null,
     *       "ysxm": "管理员",
     *       "yspssj": "2019-08-14 00:00:00",
     *       "bllx": "01",
     *       "rybh": "110000114201910110010",
     *       "updator": "管理员",
     *       "ldyj": "同意",
     *       "jbr": "管理员",
     *       "updatetime": "2019-08-14 20:06:11",
     *       "jsbh": "110000114"
     *     }],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *   "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     * }
     */
    @OpenAPI
    @ApiOperation("单独关押查询")
    @GetMapping("/ddgyList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> cfglYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/ddgy/ddgyList";
        String state = "R2";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数
            QueryParam qParam = new QueryParam();
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("kssj"))) {
                qParam.and("kssj",TermType.gte, maps.getResult().get("kssj"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jb"))) {
                qParam.and("jb",TermType.eq, maps.getResult().get("jb"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jssj"))) {
                qParam.and("jssj",TermType.lte, maps.getResult().get("jssj"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jbQueryForPage(qParam);

            System.err.println("result" + JSON.toJSONString(result));


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
     * @api {post} /v4/kss/ddgy/ddgyaddflow 单独关押保存
     * @apiVersion 0.4.0
     * @apiName ddgyaddflow
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 单独关押保存
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
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大字段长度:9)",
     *   json{
     *      "entity":[{
     *       "processName":"流程名",
     *       "ywlcid":"业务流程id(必填; 最大字段长度:30)",
     *       "taskid":"任务id",
     *       "snbh":"所内编号(必填; 最大字段长度:8)",
     *       "xm":"姓名(必填; 最大字段长度:50)",
     *       "jsh":"监室号(必填; 最大字段长度:4)",
     *       "gjxm":"管教姓名(必填; 最大字段长度:255)",
     *       "gjblsj": "管教办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *       "ly":"理由(必填; 最大字段长度:255)",
     *       "qkms":"情况描述",
     *       "xjsh":"现监室号(必填; 最大字段长度:4)",
     *       "bllx":"办理类型",
     *       "rybh":"人员编号(必填; 最大字段长度:21)"
     *        }]
     *   }
     *
     */
    @ApiOperation("单独关押保存")
    @PostMapping("/ddgyaddflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ddgyaddflow(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/ddgy/ddgyaddflow";
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


            DdgyModel ddgyModel = JSONArray.parseArray(map.get("entity").toString(), DdgyModel.class).get(0);

                String flowkey = CacheUtils.get().getFlowKey("KSS_DDGY");
                System.err.println(flowkey+"----------");
                if ("".equals(flowkey)) {
                    return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
                }

                ddgyModel.setState("R2");
                ddgyModel.setJsbh(jsbh);
                ddgyModel.setCreatetime(new Date());


            ResponseMessage<String> result = kssServerApis.ddgyaddflow(ddgyModel);

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
     * @api {post} /v4/kss/ddgy/ddgyExecuteflow 单独关押流程保存
     * @apiVersion 0.4.0
     * @apiName ddgyExecuteflow
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 单独关押流程保存
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
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大字段长度:9)",
     *   json{
     *      "entity":[{
     *         "processName":"流程名",
     *         "ywlcid":"业务流程id(必填; 最大字段长度:30)",
     *         "taskid":"任务id",
     *         "snbh":"所内编号(必填; 最大字段长度:8)",
     *         "xm":"姓名(必填; 最大字段长度:50)",
     *         "jsh":"监室号(必填; 最大字段长度:4)",
     *         "gjxm":"管教姓名(必填; 最大字段长度:255)",
     *         "gjblsj": "管教办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *         "ly":"理由(必填; 最大字段长度:255)",
     *         "qkms":"情况描述",
     *         "xjsh":"现监室号(必填; 最大字段长度:4)",
     *         "zdzyj":"中队长意见(必填;字典：PSBZ； 最大字段长度:255)",
     *         "zdzyjnr":"中队长意见内容",
     *         "zdzxm":"中队长姓名(必填; 最大字段长度:255)",
     *         "zdzqmsj":"中队长签名时间(必填; 格式:yyyy-MM-dd hh:mm:ss)"
     *         "rybh":"人员编号(必填; 最大字段长度:21)",
     *         "processNode":"流程号(必填)",
     *         "id":"id(必填; 最大字段长度:23)",
     *         "updator":"更新人(必填; 最大字段长度:255)"
     *        }]
     *   }
     *
     */
    @ApiOperation("单独关押流程保存")
    @PostMapping("/ddgyExecuteflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ddgyExecuteflow(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/ddgy/ddgyExecuteflow";
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

            DdgyModel ddgyModel = JSONArray.parseArray(map.get("entity").toString(), DdgyModel.class).get(0);
            ddgyModel.setState("R2");
            ddgyModel.setJsbh(jsbh);
            ddgyModel.setUpdatetime(new Date());
            System.err.println("model-------"+JSON.toJSONString(ddgyModel));

            ResponseMessage<String> result = kssServerApis.ddgyExecuteflow(ddgyModel);

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
     * @api {post} /v4/kss/ddgy/ddgy_ldspAdd 单独关押领导审批
     * @apiVersion 0.4.0
     * @apiName ddgy_ldspAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 单独关押领导审批
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
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大字段长度:9)",
     *   json{
     *      "entity":[{
     *            "processName":"流程名",
     *            "ywlcid":"业务流程id(必填; 最大字段长度:30)",
     *            "taskid":"任务id",
     *            "snbh":"所内编号(必填; 最大字段长度:8)",
     *            "xm":"姓名(必填; 最大字段长度:50)",
     *            "jsh":"监室号(必填; 最大字段长度:4)",
     *            "gjxm":"管教姓名(必填; 最大字段长度:255)",
     *            "gjblsj": "管教办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "ly":"理由(必填; 最大字段长度:255)",
     *            "qkms":"情况描述",
     *            "xjsh":"现监室号(必填; 最大字段长度:4)",
     *            "ldyj":"领导意见(必填;字典：PSBZ； 最大字段长度:255)",
     *            "ldyjnr":"领导意见内容",
     *            "ldxm":"领导姓名(必填; 最大字段长度:255)",
     *            "ldqmsj":"领导签名时间(必填; 格式:yyyy-MM-dd hh:mm:ss)"
     *            "rybh":"人员编号(必填; 最大字段长度:21)",
     *            "processNode":"流程号(必填)",
     *            "id":"id(必填; 最大字段长度:23)",
     *            "updator":"更新人(必填; 最大字段长度:255)"
     *        }]
     *   }
     *
     */
    @ApiOperation("单独关押领导审批")
    @PostMapping("/ddgy_ldspAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ddgy_ldspAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/ddgy/ddgy_ldspAdd";
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

            DdgyModel ddgyModel = JSONArray.parseArray(map.get("entity").toString(), DdgyModel.class).get(0);

            ddgyModel.setState("R2");
            ddgyModel.setJsbh(jsbh);
            ddgyModel.setUpdatetime(new Date());
       //     ddgyModel.setUpdator(users.getUsername());
       //     String ldqmsj = request.getParameter("ldqmsj");
        //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //    ddgyModel.setLdqmsj(sdf.parse(ldqmsj));

            System.err.println("model-------"+JSON.toJSONString(ddgyModel));
            ResponseMessage<String> result = kssServerApis.ddgy_ldspAdd(ddgyModel);

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
     * @api {post} /v4/kss/ddgy/drgyjsAdd 带入关押监室保存
     * @apiVersion 0.4.0
     * @apiName drgyjsAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 带入关押监室保存
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
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大字段长度:9)",
     *   json{
     *      "entity":[{
     *            "tbrq":"填表日期(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "jstzsj":"监室调整时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "bz":"备注",
     *            "blr":"带入监室民警(必填; 最大字段长度:255)",
     *            "blsj":"带入监室时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "ywlcid":"业务流程id(必填; 最大字段长度:30)",
     *            "taskid":"任务id",
     *            "yjsh":"原监室号(必填; 最大字段长度:4)",
     *            "rybh":"人员编号(必填; 最大字段长度:21)",
     *            "xjsh":"现监室号(必填; 最大字段长度:4)",
     *            "updator":"创建人(必填; 最大字段长度:255)"
     *        }]
     *   }
     *
     */
    @ApiOperation("带入关押监室保存")
    @PostMapping("/drgyjsAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> drgyjsAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/ddgy/drgyjsAdd";
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

            DdgyModel ddgyModel = JSONArray.parseArray(map.get("entity").toString(), DdgyModel.class).get(0);

            ddgyModel.setState("R2");
            ddgyModel.setJsbh(jsbh);
            ddgyModel.setUpdatetime(new Date());
         //   ddgyModel.setUpdator(users.getUsername());
            System.err.println("model-------"+JSON.toJSONString(ddgyModel));
            ResponseMessage<String> result = kssServerApis.drgyjsAdd(ddgyModel);

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

}
