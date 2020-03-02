package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XzpaModel;
import awd.bj.kss.model.ZdryModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/v4/kss/xzpa")
@Api(tags = "kss-xzpa",description = "Xzpa")
public class Kss_XzpaController extends PublicService {
    @Autowired
    private KssServerApis kssServerApis;
    @Autowired
    private KssAnalyseApis kssAnalyseApis;
    @Autowired
    private ProcessService processService;



    /**
     * @api {post} /v4/kss/xzpa/xzpaAddflow 协助破案保存
     * @apiVersion 0.4.0
     * @apiName xzpaAddflow
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 协助破案保存.
     *

     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *  "message": "保存成功!",
     *  "result": "11000011420200113000070",
     *  "status": 200,
     *  "timestamp": 1578892709281
     *}
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       	"entity":[{
     *                  "rybh": "人员编号(必填;最大长度:21)",
     *                  "creator":"创建人(必填；最大长度:30)",
     *                  "createtime":"创建时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "lx":"类型(必填；最大长度:1)",
     *                  "sfzdaj":"是否重大案件(必填；最大长度:1)",
     *                  "sar":"涉案人(必填；最大长度:100)",
     *                  "sars":"涉案人数(必填；最大长度:3)",
     *                  "afsj":"案发时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "afdd":"案发地点(必填；最大长度:60)",
     *                  "saje":"涉案金额(必填；最大长度:10)",
     *                  "tbjurq":"坦白检举日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "jjqxz":"检举案性质(必填；最大长度:100)",
     *                  "saqy":"涉案区域(必填；最大长度:1)",
     *                  "djr":"登记人(必填；最大长度:30)",
     *                  "djrq":"登记时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "jyjg":"简要经过(必填；最大长度:300)"
     *          		}]
     *     	  }
     */

    //{"creator":".{1,30}","rygllb":"\\d{1,2}","jbr":"\\S{1,30}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"rybh":"110000114201912070001","creator":"aa","createtime":"2019-12-30 10:10:10","lx":"1","sfzdaj":"1","sar":"ss","sars":"12","afsj":"2019-12-03 10:10:10","afdd":"ddd","saje":"123123123","tbjurq":"2019-12-24 10:10:10","jjqxz":"fdfds","saqy":"1","djr":"ggg","djrq":"2019-12-30 10:10:10","jyjg":"fgfdgdfg"}]}

    @ApiOperation("协助破案保存")
    @PostMapping("/xzpaAddflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xzpaAddflow(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/xzpa/xzpaAddflow";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            XzpaModel xzpaModel=JSONObject.parseObject(rslist.get(0).toString(),XzpaModel.class);
            xzpaModel.setState("R2");
            xzpaModel.setJsbh(jsbh);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            ResponseMessage<String> s = processService.FlowMutex(jsbh, xzpaModel.getRybh(), "KSS_XZPA", "XZPA");
            if (s.getStatus()!=200) {
                return ResponseMessage.error(s.getMessage());
            }
            String flowKey = CacheUtils.get().getFlowKey("KSS_XZPA");
            if (StringUtils.isNullOrEmpty(flowKey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            ResponseMessage<String> result = kssServerApis.saveForXzpa(flowKey, xzpaModel);
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
     * @api {post} /v4/kss/xzpa/clzdflow 材料转递保存
     * @apiVersion 0.4.0
     * @apiName clzdflow
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 材料转递保存.
     *

     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *  "message": "保存成功!",
     *  "result": "11000011420200113000070",
     *  "status": 200,
     *  "timestamp": 1578892709281
     *}
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       	"entity":[{
     *                  "rybh": "人员编号(最大长度:21)",
     *                  "id":"id(最大长度:23)",
     *                  "taskid":"任务id(必填；最大长度:30)",
     *                  "ywlcid":"业务流程id(最大长度:30)",
     *                  "sar":"涉案人(最大长度:100)",
     *                  "sars":"涉案人数(最大长度:3)",
     *                  "afdd":"案发地点(最大长度:60)",
     *                  "saje":"涉案金额(最大长度:10)",
     *                  "cdr":"传递人(必填；最大长度:50)",
     *                  "cdrdh":"传递人电话(必填；最大长度:20)",
     *                  "cdrq":"传递日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                  "cddw":"传递单位(必填；最大长度:60)",
     *                  "jsr":" 接收人(必填；最大长度:50)",
     *                  "jsrdh":"接收人电话(必填；最大长度:20)",
     *                  "cdbz":"传递备注(最大长度:100)"
     *          		}]
     *     	  }
     */

    //{"updator":".{1,30}","cdr":"\\S{1,50}","cdrdh":"\\d{1,20}","cdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","cddw":"\\S{1,60}","jsr":" \\S{1,50}","jsrdh":"\\d{1,20}"}
    //{"entity":[{"updator":"sss","rybh":"110000114201910230001","id":"11000011420191112000069","taskid":"5337811","ywlcid":"4215573","sar":"2萨达","sars":"2","afdd":"萨达","saje":"22","cdr":"ss","cdrdh":"15236952111","cdrq":"2020-01-17 10:15:49","cddw":"310106310200","jsr":" fff","jsrdh":"15639851236","cdbz":""}]}

    @ApiOperation("材料转递保存")
    @PostMapping("/clzdflow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> clzdflow(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/xzpa/clzdflow";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            XzpaModel xzpaModel=JSONObject.parseObject(rslist.get(0).toString(),XzpaModel.class);
            String taskid=(String) rslist.get(0).get("taskid");
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }
            xzpaModel.setJsbh(jsbh);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            ResponseMessage<String> result = kssServerApis.saveForclzd(xzpaModel,taskid);
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
     * @api {post} /v4/kss/xzpa/xzpaYwdt 协助破案业务动态查询
     * @apiVersion 0.4.0
     * @apiName xzpaYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 协助破案业务动态查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}jj   				                            检举
     * @apiSuccess {String}chry                                             查获人员
     * @apiSuccess {String}ycs                                              已查实
     * @apiSuccess {String}ztry                                             抓逃人员
     * @apiSuccess {String}zs                                               获取线索(件)
     * @apiSuccess {String}je                                               追赃金额
     * @apiSuccess {String}phxsaj                                           破获刑事案件
     * @apiSuccess {String}tb                                               坦白

     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [{
     *         "result": {
     * 	            "xzpaYwdt": [{
     * 	            	"jj": 64,
     * 	            	"chry": 348,
     * 	            	"ycs": 14,
     * 	            	"ztry": 153,
     * 	            	"zs": 86,
     * 	            	"je": 211370,
     * 	            	"phxsaj": 44610,
     * 	            	"tb": 6
     *              }]
     *         },
     *       }],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579158673428
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
     * 			"endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
     * 			"dw":"单位(最大字段长度：50)"
     *        }

     */
    //result
    //{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
    @OpenAPI
    @ApiOperation("协助破案业务动态查询")
    @PostMapping("/xzpaYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> zdryYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/xzpa/xzpaYwdt";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数
            String starttime = (String) maps.getResult().get("starttime");
            String endtime =  (String) maps.getResult().get("endtime");
            ResponseMessage<Map<String,Object>> result= kssAnalyseApis.xzpaYwdt(jsbh,starttime,endtime);
            List lists=new ArrayList();
            lists.add(result);
            System.err.println("result" + JSON.toJSONString(result));
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", lists.size());
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
     * @api {post} /v4/kss/xzpa/xzpaYwtz 协助破案业务台账查询
     * @apiVersion 0.4.0
     * @apiName xzpaYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 协助破案业务台账查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}xsjls                                            刑事拘留人数
     * @apiSuccess {String}afdd                                             案发地点
     * @apiSuccess {String}ywlcid                                           业务流程id
     * @apiSuccess {String}phxsaj                                           破获刑事案件
     * @apiSuccess {String}lx                                               类型
     * @apiSuccess {String}djrqString                                       登记日期（已转换）
     * @apiSuccess {String}afsjaj                                           案发时间
     * @apiSuccess {String}snbh                                             所内编号
     * @apiSuccess {String}cdrdh                                            传递人电话
     * @apiSuccess {String}czjg                                             查证结果
     * @apiSuccess {String}cddw                                             传递单位
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}wszts                                            网上追逃人数
     * @apiSuccess {String}pastable                                         是否有效
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}afsjString                                       案发时间（已转换）
     * @apiSuccess {String}sar                                              涉案人
     * @apiSuccess {String}zwdw                                             转往单位
     * @apiSuccess {String}djrq                                             登记日期
     * @apiSuccess {String}fkrq                                             反馈日期
     * @apiSuccess {String}tbjurq                                           坦白检举日期
     * @apiSuccess {String}tbjurqString                                     坦白检举日期（已转换）
     * @apiSuccess {String}jjqxz                                            检举案性质
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}zjh                                              证件号
     * @apiSuccess {String}czjgString                                       查证结果（已转换）
     * @apiSuccess {String}dbrs3                                            逮捕人数
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}jsbh                                             监所编号
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [{
     *         "result": {
     * 	           "xsjls": 3,
     *             "afdd": "072900",
     *             "ywlcid": "2775198",
     *             "phxsaj": null,
     *             "lx": "1",
     *             "djrqString": "2019-08-01",
     *             "afsj": 1565599799000,
     *             "snbh": "20190038",
     *             "cdrdh": null,
     *             "czjg": "1",
     *             "cddw": "上海市公安局国内安全保卫局",
     *             "id": "31000011120190612000057",
     *             "state": "R2",
     *             "wszts": null,
     *             "pastable": "1",
     *             "creator": "管理员",
     *             "createtime": 1561008171000,
     *             "afsjString": "2019-08-12 16:49:59",
     *             "sar": "下次",
     *             "zwdw": "北京市公安局国内安全保卫局",
     *             "djrq": 1564650033000,
     *             "fkrq": 1560787200000,
     *             "tbjurq": 1562662223000,
     *             "tbjurqString": "2019-07-09",
     *             "jjqxz": "球球球球",
     *             "xm": "云承",
     *             "rybh": "310000111201906200007",
     *             "zjh": "230506200006168870",
     *             "czjgString": "是",
     *             "dbrs3": null,
     *             "jsh": "0101",
     *             "jsbh": "110000114"
     *         },
     *       }],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579158673428
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "xm":"姓名",
     *          "jsh":"监室号",
     *          "blrqstart":"办理日期开始",
     *          "blrqend":"办理日期结束",
     *          "processDefinitionKey":"processDefinitionKey",
     *          "taskDefinitionKey":"taskDefinitionKey",
     *          "pageSize":"20",
     *          "pageIndex":"0",
     *          "page":"1",
     *          "rows":"20",
     *          "sort":"id",
     *          "order":"desc"
     *        }

     */
    //xsjls,afdd,ywlcid,phxsaj,lx,djrqString,afsj,dbrs3,snbh,cdrdh,czjg,cddw,id,state,wszts,pastable,creator,createtime,afsjString,sar,zwdw,djrq,fkrq,tbjurq,tbjurqString,jjqxz,xm,rybh,zjh,czjgString,jsh,jsbh
    //{"xm":"","jsh":"","blrqstart":"","blrqend":"","processDefinitionKey":"","taskDefinitionKey":"","pageSize":"20","pageIndex":"0","page":"1","rows":"20","sort":"id","order":"desc"}
    @OpenAPI
    @ApiOperation("协助破案业务台账查询")
    @PostMapping("/xzpaYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> xzpaYwtz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/xzpa/xzpaYwtz";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数
            QueryParam queryParam = new QueryParam();
            String xm = (String) maps.getResult().get("xm");
            String xm_type = (String) maps.getResult().get("xm_type");
            String jsh = (String) maps.getResult().get("jsh");
            String fkrq_start = (String) maps.getResult().get("fkrq_start");
            String fkrq_end = (String) maps.getResult().get("fkrq_end");
            String xsdjrq_start = (String) maps.getResult().get("xsdjrq_start");
            String xsdjrq_end = (String) maps.getResult().get("xsdjrq_end");
            String tbjjrq_start = (String) maps.getResult().get("blrqstart");
            String tbjjrq_end = (String) maps.getResult().get("blrqend");
            if (!StringUtils.isNullOrEmpty(tbjjrq_start)) {
                queryParam.and("tbjurq", TermType.gte, tbjjrq_start+ " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(tbjjrq_end)) {
                queryParam.and("tbjurq", TermType.lte, tbjjrq_end+ " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(xm)) {
                if ("0".equals(xm_type)) {
                    xm = word2py(xm);
                    queryParam.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
                } else {
                    queryParam.and("jbxx_xm", TermType.like, "%" + xm + "%");
                }
            }
            if (!StringUtils.isNullOrEmpty(jsh)) {
                queryParam.and("jbxx_jsh", TermType.eq, jsh);
            }
            if (!StringUtils.isNullOrEmpty(fkrq_start)) {
                queryParam.and("fkrq", TermType.gte, fkrq_start);
            }
            if (!StringUtils.isNullOrEmpty(fkrq_end)) {
                queryParam.and("fkrq", TermType.lte, fkrq_end);
            }
            if (!StringUtils.isNullOrEmpty(xsdjrq_start)) {
                queryParam.and("djrq", TermType.gte, xsdjrq_start);
            }
            if (!StringUtils.isNullOrEmpty(xsdjrq_end)) {
                queryParam.and("djrq", TermType.lte, xsdjrq_end);
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam,"R2");
            queryParam.and("jbxx_state", TermType.eq, "R8");
            queryParam.and("jsbh", TermType.eq, jsbh);
            queryParam.and("jbxx_jsbh", TermType.eq, jsbh);
            queryParam.and("pastable", TermType.eq, "1");
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.xzpaYwtz(queryParam);
            System.err.println("result" + JSON.toJSONString(result));
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

    public String word2py(String word) {
        if (StringUtils.isNullOrEmpty(word)) {
            return "";
        }
        return Pinyin4j.cn2Pinyin(word);

    }




}
