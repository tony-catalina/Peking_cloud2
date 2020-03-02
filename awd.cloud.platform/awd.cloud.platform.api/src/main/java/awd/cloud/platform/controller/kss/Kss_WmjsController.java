package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.WmjsModel;
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
 * Date：2020-01-19 14:59
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/wmjs")
@Api(tags = "kss-wmjs",description = "Wmjs")
public class Kss_WmjsController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     *
     * @api {get} /v4/kss/wmjs/findFlowBylcid 根据流程id查找流程
     * @apiVersion 0.4.0
     * @apiName findFlowBylcid
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 根据流程id查找流程
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}rs												人数
     * @apiSuccess {String}psbzString										批示标识(已转换)
     * @apiSuccess {String}ly										        理由
     * @apiSuccess {String}stateString										状态(已转换)
     * @apiSuccess {String}ywlcid										    业务流程id
     * @apiSuccess {String}jzyj												警组意见
     * @apiSuccess {Date}ldpssj											    领导批示时间
     * @apiSuccess {String}bz												备注
     * @apiSuccess {String}id												id
     * @apiSuccess {String}state											状态
     * @apiSuccess {String}jsbhString										监所编号(已转换)
     * @apiSuccess {String}ldxm												领导姓名
     * @apiSuccess {String}pastable											是否有效(SHFO)
     * @apiSuccess {Date}createtime										    创建时间
     * @apiSuccess {String}creator											创建人
     * @apiSuccess {String}bllxString										办理类型(WMJSBLLX)(已转换)
     * @apiSuccess {String}blrq												办理日期
     * @apiSuccess {String}blr												办理人
     * @apiSuccess {String}djrq												登记日期
     * @apiSuccess {String}pastableString									是否有效(SHFO)(已转换)
     * @apiSuccess {String}psbz												批示标识
     * @apiSuccess {String}bllx												办理类型
     * @apiSuccess {String}updator											跟新人
     * @apiSuccess {String}ldpsxx											领导审批姓名
     * @apiSuccess {String}ldyj												领导意见
     * @apiSuccess {Date}updatetime										    更新时间
     * @apiSuccess {String}jsbh												监所编号
     * @apiSuccess {String}jsh												监室号
     * @apiSuccess {String}taskId											任务id
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data":  [
     * {
     *       "rs": null,
     *         "psbzString": "批示通过",
     *         "ywlcid": "5004613",
     *         "stateString": "有效",
     *         "ly": "2",
     *         "jzyj": "2",
     *         "ldpssj": "2019-12-04 14:31:04",
     *         "bz": "2",
     *         "id": "11000011420191204000256",
     *         "state": "R2",
     *         "jsbhString": "北京市第一看守所",
     *         "ldxm": null,
     *         "pastable": null,
     *         "createtime": "2019-12-04 14:30:03",
     *         "creator": "管理员",
     *         "bllxString": "撤销",
     *         "blrq": "2019-12-04 00:00:00",
     *         "bzZH": null,
     *         "blr": "管理员",
     *         "djrq": null,
     *         "pastableString": null,
     *         "psbz": "1",
     *         "bllx": "02",
     *         "updator": null,
     *         "ldpsxx": null,
     *         "ldyj": "同意",
     *         "updatetime": "2019-12-04 14:20:44",
     *         "jsbh": "110000114",
     *         "jsh": "0625",
     *         "taskId": null
     * ],
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *       "jsh":"监室号(最大字段长度：4)",
     *       "psbz":"批示标识(最大字段长度：4)"
     * }
     */
    @OpenAPI
    @ApiOperation("根据流程id查找流程")
    @GetMapping("/findFlowBylcid")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> findFlowBylcid(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/wmjs/findFlowBylcid";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                qParam.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("psbz"))) {
                qParam.and("psbz", TermType.eq, maps.getResult().get("psbz"));
            }
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                qParam.and("jsbh", TermType.eq, jsbh);
            }

            DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wmjsQueryForPage(qParam);
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
     *
     * @api {get} /v4/kss/wmjs/wmjsList 文明监室历史记录查询
     * @apiVersion 0.4.0
     * @apiName wmjsList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 文明监室历史记录查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}rs												人数
     * @apiSuccess {String}psbzString										批示标识(已转换)
     * @apiSuccess {String}ly										        理由
     * @apiSuccess {String}stateString										状态(已转换)
     * @apiSuccess {String}ywlcid										    业务流程id
     * @apiSuccess {String}jzyj												警组意见
     * @apiSuccess {Date}ldpssj											    领导批示时间
     * @apiSuccess {String}bz												备注
     * @apiSuccess {String}id												id
     * @apiSuccess {String}state											状态
     * @apiSuccess {String}jsbhString										监所编号(已转换)
     * @apiSuccess {String}ldxm												领导姓名
     * @apiSuccess {String}pastable											是否有效(SHFO)
     * @apiSuccess {Date}createtime										    创建时间
     * @apiSuccess {String}creator											创建人
     * @apiSuccess {String}bllxString										办理类型(WMJSBLLX)(已转换)
     * @apiSuccess {String}blrq												办理日期
     * @apiSuccess {String}blr												办理人
     * @apiSuccess {String}djrq												登记日期
     * @apiSuccess {String}pastableString									是否有效(SHFO)(已转换)
     * @apiSuccess {String}psbz												批示标识
     * @apiSuccess {String}bllx												办理类型
     * @apiSuccess {String}updator											跟新人
     * @apiSuccess {String}ldpsxx											领导审批姓名
     * @apiSuccess {String}ldyj												领导意见
     * @apiSuccess {Date}updatetime										    更新时间
     * @apiSuccess {String}jsbh												监所编号
     * @apiSuccess {String}jsh												监室号
     * @apiSuccess {String}Referer											referer
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data":
     * {
     *        "rs": null,
     *         "psbzString": "批示通过",
     *         "ywlcid": "5004523",
     *         "stateString": "有效",
     *         "ly": "1",
     *         "jzyj": "1",
     *         "ldpssj": "2019-12-04 14:24:55",
     *         "bz": "1",
     *         "id": "11000011420191204000254",
     *         "state": "R2",
     *         "jsbhString": "北京市第一看守所",
     *         "ldxm": null,
     *         "pastable": null,
     *         "createtime": "2019-12-04 14:24:39",
     *         "creator": "管理员",
     *         "bllxString": "撤销",
     *         "blrq": "2019-12-04 00:00:00",
     *         "bzZH": null,
     *         "Referer": null,
     *         "blr": "管理员",
     *         "djrq": null,
     *         "pastableString": null,
     *         "psbz": "1",
     *         "bllx": "02",
     *         "updator": null,
     *         "ldpsxx": null,
     *         "ldyj": "同意",
     *         "updatetime": "2019-12-04 14:14:35",
     *         "jsbh": "110000114",
     *         "jsh": "0219"
     * }
     *
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *        "Referer":"refer",
     *        "xsfw":"监室号(最大字段长度：4)",
     *        "bllx":"办理类型(字典：WMJSBLLX；最大字段长度：2)",
     *        "xssj1":"办理日期(格式:yyyy-MM-dd hh:mm:ss)",
     *        "xssj1":"办理日期(格式:yyyy-MM-dd hh:mm:ss)",
     *      }
     */

    @OpenAPI
    @ApiOperation("文明监室历史记录查询")
    @GetMapping("/wmjsList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> wmjsList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/wmjs/wmjsList";
        String state = "R2";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数

            QueryParam qParam = new QueryParam();
            //2019-12-5 开始 通过页面请求来源判断是已办理文明监室申请的监室列表查询还是已办理取消文明监室的列表查询；可以避免在对应的两个页面添加参数,等价于对bllx的处理
            //设置文明监室
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("Referer"))){
                 String refer= (String) maps.getResult().get("Referer");

             if (!StringUtils.isNullOrEmpty(refer ) &&  refer.contains("wmjsszindex.html"))             {
                qParam.and("bllx", TermType.eq, "01");
            }
            //撤销文明监室
              if (StringUtils.isNullOrEmpty(refer) && refer.contains("wmjscxindex.html")) {
                qParam.and("bllx", TermType.eq, "02");
             }
            }
            qParam.and("ldyj", TermType.eq, "同意");
            //结束
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xsfw"))) {
                qParam.and("jsh", TermType.like, maps.getResult().get("xsfw") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bllx"))) {
                qParam.and("bllx", TermType.eq, maps.getResult().get("bllx"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xssj1"))) {
                qParam.and("blrq", TermType.gte, maps.getResult().get("xssj1"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xssj2"))) {
                qParam.and("blrq", TermType.lte, maps.getResult().get("xssj2") + " 23:59:59");
            }

            DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wmjsQueryForPage(qParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

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
     * @api {post} /v4/kss/wmjs/wmjsszAdd 新增文明监室
     * @apiVersion 0.4.0
     * @apiName wmjsszAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 新增文明监室
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
     *       "creator":"创建人(必填; 最大字段长度:50)",
     *       "lcid":"流程id(必填)",
     *       "jsh":"监室号(必填; 最大字段长度:4)",
     *       "jzyj":"警组意见(必填; 最大字段长度:200)",
     *       "ly":"设置/撤销理由(必填)",
     *       "blr":"办理人(必填; 最大字段长度:30)",
     *       "blrq":"办理日期(必填;  格式:yyyy-MM-dd hh:mm:ss)",
     *       "bz": "备注"
     *        }]
     *   }
     *
     */
    //{"creator":".{1,50}","jsh":"\\d{1,4}","jzyj":".{1,200}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blr":".{1,30}"}
    @ApiOperation("新增文明监室")
    @PostMapping("/wmjsszAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<?> wmjsszAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/wmjs/wmjsszAdd";
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


            WmjsModel wmjsModel = JSONArray.parseArray(map.get("entity").toString(), WmjsModel.class).get(0);
            // List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            // Map<String,Object> entityMap = mapsList.get(0);

            String lcid = CacheUtils.get().getFlowKey("KSS_WMJSSZ");

            QueryParam ap = new QueryParam();
            ap.and("jsh", wmjsModel.getJsh());
            ap.and("psbz", TermType.isnull, "psbz");
            ap.and("jsbh", jsbh);
            ResponseMessage<PagerResult<Map<String, Object>>> list = kssServerApis.wmjsQueryForPage(ap);
            System.err.println(JSON.toJSONString(list));
            if (list.getResult().getTotal() != 0) {
                return ResponseMessage.error("错误！当前监室正在办理文明监室领导审批的流程");
            }
            wmjsModel.setState("R2");
            wmjsModel.setBllx("01");
            wmjsModel.setJsbh(jsbh);
       //     wmjsModel.setCreator(users.getUsername());
            wmjsModel.setCreatetime(new Date());
        //    wmjsModel.setJsh(request.getParameter("jsh"));
       //     wmjsModel.setLy(request.getParameter("ly"));
       //     wmjsModel.setJzyj(request.getParameter("jzyj"));
       //     wmjsModel.setBlr(request.getParameter("blr"));
       //     String blrq = request.getParameter("blrq");
        //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       //     wmjsModel.setBlrq(sdf.parse(blrq));
        //    wmjsModel.setBz(request.getParameter("bz"));

            ResponseMessage<?> result = kssServerApis.wmjsSave(lcid, wmjsModel);
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
     * @api {post} /v4/kss/wmjs/wmjsszdelete 文明监室撤销
     * @apiVersion 0.4.0
     * @apiName wmjsszdelete
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 文明监室撤销
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
     *       "creator":"创建人(必填; 最大字段长度:50)",
     *       "lcid":"流程id(必填)",
     *       "jsh":"监室号(必填; 最大字段长度:4)",
     *       "jzyj":"警组意见(必填; 最大字段长度:200)",
     *       "ly":"设置/撤销理由(必填)",
     *       "blr":"办理人(必填; 最大字段长度:30)",
     *       "blrq":"办理日期(必填;  格式:yyyy-MM-dd hh:mm:ss)",
     *       "bz": "备注"
     *        }]
     *   }
     *
     */
    //{"creator":".{1,50}","jsh":"\\d{1,4}","jzyj":".{1,200}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blr":".{1,30}"}
    @ApiOperation("文明监室撤销")
    @PostMapping("/wmjsszdelete")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<?> wmjsszdelete(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/wmjs/wmjsszdelete";
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


            WmjsModel wmjsModel = JSONArray.parseArray(map.get("entity").toString(), WmjsModel.class).get(0);
            // List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            // Map<String,Object> entityMap = mapsList.get(0);


            QueryParam ap = new QueryParam();
            ap.and("jsh", wmjsModel.getJsh());
            ap.and("psbz", TermType.isnull, "psbz");
            ResponseMessage<PagerResult<Map<String, Object>>> list = kssServerApis.wmjsQueryForPage(ap);
            System.err.println(JSON.toJSONString(list));
            if (list.getResult().getTotal() != 0) {
                return ResponseMessage.error("错误！当前监室正在办理文明监室领导审批的流程");
            }
            wmjsModel.setState("R2");
            wmjsModel.setBllx("02");
            wmjsModel.setJsbh(jsbh);
        //   wmjsModel.setCreator(users.getUsername());
            wmjsModel.setCreatetime(new Date());
        //    wmjsModel.setLy(request.getParameter("ly"));
        //    wmjsModel.setBlr(request.getParameter("blr"));
        //    String blrq = request.getParameter("blrq");
        //    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //    wmjsModel.setBlrq(sdf.parse(blrq));
            String lcid = CacheUtils.get().getFlowKey("KSS_WMJSCX");

            ResponseMessage<?> result = kssServerApis.wmjsSave(lcid, wmjsModel);
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
