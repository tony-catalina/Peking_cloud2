package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XyrModel;
import awd.cloud.platform.api.KssServerApis;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/ts")
@Api(tags = "kss-ts",description = "ts")
public class Kss_TsController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;
    @Autowired
    private WorkFlowService workFlowService;
    @Autowired
    private ProcessService processService;

    /**
     * @api {post} /v4/kss/ts/txHdpz 提讯核对凭证
     * @apiVersion 0.4.0
     * @apiName txHdpz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提讯核对凭证.
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[
     *                {
     *                	"xm":"姓名(必填;最大长度:30)",
     *                  "xb":"性别(必填;最大长度:1)",
     *                  "mz":"民族(必填;最大长度:2)",
     *                  "csrq":"出生日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                  "hyzk":"婚姻状况(必填；最大长度:1)",
     *                  "whcd":"文化程度(必填；最大长度:2)",
     *                  "zy":"职业(必填；最大长度:4)",
     *                  "sf":"身份(必填；最大长度:2)",
     *                  "rsrq":"入所时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "lfrxm":"来访人姓名(必填；最大长度:50)",
     *                  "lfrzjh":"来访人证件号(必填；最大长度:30)",
     *                  "lfrpzh":"来访人凭证文书号(必填；最大长度:30)",
     *                  "lfsj":"来访时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "lxdh":"联系电话(最大长度:20)",
     *                  "rybh":"人员编号(必填;最大长度:30)"
     *
     *                }
     *              ]
     *     }
     */

    //{"xm":"\\S{1,30}","xb":"\\d{1}","mz":"\\d{1,2}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","hyzk":"\\d{1}","whcd":"\\d{1,2}","zy":"\\S{1,4}","sf":"\\d{1,2}","rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","lfrxm":"\\S{1,50}","lfrzjh":"\\S{1,30}","lfrpzh":"\\S{1,30}","lfsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":"\\d{1,30}"}
    //{"entity":[{"xm":"aa","xb":"1","mz":"01","csrq":"1985-10-10 10:10:10","hyzk":"1","whcd":"90","zy":"0-25","sf":"09","rsrq":"2019-10-10 10:10:10","lfrxm":"cc","lfrzjh":"342522199412300912","lfrpzh":"310000010200201912050001","lfsj":"2019-10-10 10:10:10","lxdh":"18862252659","rybh":"110000114201912050001"}]}
    @ApiOperation("提讯核对凭证")
    @PostMapping("/txHdpz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> txHdpz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ts/txHdpz";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            System.err.println("map--"+map.get("entity").toString());
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<Kss_TsdjModelDO> tsdjModels = JSONArray.parseArray(map.get("entity").toString(), Kss_TsdjModelDO.class);
            System.err.println("tsdjModel--"+ JSON.toJSONString(tsdjModels.get(0)));
            tsdjModels.get(0).setCreatetime(new Date());
            tsdjModels.get(0).setState("R2");
            tsdjModels.get(0).setPastable("1");
            tsdjModels.get(0).setJsbh(jsbh);
            tsdjModels.get(0).setCreator("管理者");
            Kss_TsdjModelDO tsdjModel=tsdjModels.get(0);
            System.err.println("tsdjModel--"+JSON.toJSONString(tsdjModel));
            String tsflowKey = CacheUtils.get().getFlowKey("_kss_tsdj".toUpperCase());
            String tsKey = "kss_tsdj".toUpperCase();
            if (processService.FlowMutex(jsbh, tsdjModel.getRybh(), tsKey, "TSDJ").getStatus() != 200) {
                return processService.FlowMutex(jsbh, tsdjModel.getRybh(), tsKey, "TSDJ");
            }
            if(!StringUtils.isNullOrEmpty(CacheUtils.get().getDictionary("BADW",tsdjModel.getDw()))) {
                tsdjModel.setDw(CacheUtils.get().getDictionary("BADW", tsdjModel.getDw()));
            }

            ResponseMessage<String> result = kssServerApis.tsdjAddflow(tsdjModel, tsflowKey);
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
     * @api {get} /v4/kss/ts/tssUseState 提审室状态查询
     * @apiVersion 0.4.0
     * @apiName tssUseState
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提审室状态查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": null,
     *     "result": "2",
     *     "status": 200,
     *     "timestamp": 1578393799247
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "tss":"提讯地点(最大字段长度：20)",
     *        }
     *
     *
     */
    @OpenAPI
    @ApiOperation("提审室状态查询")
    @GetMapping("/tssUseState")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> tssUseState(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
        String interfaceId = "/v4/kss/ts/tssUseState";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            String tss = (String) map.get("tss");

            ResponseMessage<List<Map<String, Object>>> result = kssServerApis.TssUseState(tss);
            System.out.println("0000000000-----"+JSONUtil.toJson(result));
            //封装需要的数据
            String isEmpty="2";
            if(result.getResult().size()>0){
                if(!StringUtils.isNullOrEmpty((String)result.getResult().get(0).get("TXRY"))){
                    System.out.println("success");
                    isEmpty="1";
                }else {
                    System.out.println("error");
                    System.out.println("00"+(String)result.getResult().get(0).get("TXRY"));
                    isEmpty="2";
                }
            }else {
                isEmpty="1";
            }
            return ResponseMessage.ok(isEmpty);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }


    /**
     * @api {post} /v4/kss/ts/addTsdj 提审登记保存
     * @apiVersion 0.4.0
     * @apiName addTsdj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提审登记保存.
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[{
     *          	"data_Jbxx":"基本信息(必填;)"{
     *                  "ywlcid":"业务流程ID(最大字段长度：50)",
     *                  "bm":"别名(最大字段长度：50)",
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                   "snbh":"人员所内编号(最大字段长度：8)",
     *                   "jsh":"监室号 (最大字段长度：4)",
     *                   "xm":"姓名(最大字段长度：50)",
     *                   "xb":"性别(最大字段长度：1)",
     *                   "taskid":"任务id(必填;最大字段长度：21)",
     *                   "jsbh":"监所编号(必填;最大字段长度：9)",
     *                   },
     *              "data_ywxx":"业务信息(必填;)"{
     *                   "taskid":"任务id(必填;最大字段长度：21)",
     *                   "rybh":"人员编号(必填;最大字段长度：21)",
     *                   "txrq":"提讯日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
     *                   "txsy":"退讯人员(必填;最大字段长度：50)",
     *                   "tss":"提讯地点(必填;最大字段长度：20)",
     *                   "txlx":"提讯类型(必填;最大字段长度：50)",
     *                   "baryy":"办案人员姓名1(必填;最大字段长度：50)",
     *                   "zjh1":"办案人证件号1(最大字段长度：18)",
     *                   "barye":"办案人员姓名2(必填;最大字段长度：50)",
     *                   "zjh2":"办案人证件号2(最大字段长度：18)",
     *                   "qtbar":"其他办案人(最大字段长度：50)",
     *                   "qtzjh":"其他办案人证件号(最大字段长度：18)",
     *                   "wcnjhr":"未成年人监护人(最大字段长度：50)",
     *                   "wcndlr":"未成年人代理人(最大字段长度：50)",
     *                   "dw":"办案机关(必填;最大字段长度：60)"
     *                                        }
     *          }]
     *     }
     */

    //{"rybh":"\\d{1,21}","txrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","txsy":"\\S{1,50}","tss":"\\S{1,20}","txlx":"\\S{1,50}","baryy":"\\S{1,50}","barye":"\\S{1,50}","dw":"\\S{1,60}"}
    //{"entity":[{"data_Jbxx":{"ywlcid":"5255737","bm":"","rybh":"110000114201912040013","snbh":"20190297","jsh":"1204","xm":"是是是","xb":"1","taskid":"5255993","jsbh":"110000114"},"data_ywxx":{"taskid":"5255993","rybh":"110000114201912040013","txrq":"2019-12-10 10:10:10","txsy":"aa","tss":"fds","txlx":"fs","baryy":"vv","zjh1":"123123","barye":"cc","zjh2":"123123","qtbar":"ff","qtzjh":"321321","wcnjhr":"grfg","wcndlr":"grg","dw":"ththt"}}]}
    @ApiOperation("提审登记保存")
    @PostMapping("/addTsdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addTsdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ts/addTsdj";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            Kss_JbxxModelDO jbxxModelDO=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(),Kss_JbxxModelDO.class);
            List<Kss_JbxxModelDO> jbxx =new ArrayList<Kss_JbxxModelDO>();
            jbxx.add(jbxxModelDO);
            Kss_TsdjModelDO tsdjModel = JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(),Kss_TsdjModelDO.class);
            Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_ywxx");
            Map<String, Object> mapls=new HashMap<String, Object>();
            mapls.put("entity",entity);
            String jsons="{'entity':["+JSON.toJSONString(entity)+"]}";
            Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
            maplss.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(maplss);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            if(jbxx.size() != 0) {
                if(!StringUtils.isNullOrEmpty(jbxx.get(0).getTaskid())) {
                    tsdjModel.setJsbh(jsbh);
                    tsdjModel.setUpdator("管理者");
                    tsdjModel.setUpdatetime(new Date());
                    ResponseMessage<String> result= kssServerApis.addTsdj(jbxx.get(0).getTaskid().toString(),jbxx.get(0).getYwlcid(),tsdjModel);
                    if(result.getStatus() == 200){
                        result.setMessage("保存成功!");
                    }else{
                        result.setMessage("服务异常,保存失败!");
                    }
                    return result;
                }else {
                    return ResponseMessage.error("taskid未传");
                }
            }else {
                return ResponseMessage.error("参数未传递完整");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


    /**
     * @api {post} /v4/kss/ts/txJqtrdj 监区提人登记保存
     * @apiVersion 0.4.0
     * @apiName txJqtrdj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监区提人登记保存.
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[{
     *          	"data_Jbxx":"基本信息(必填;)"{
     *                  "ywlcid":"业务流程ID(最大字段长度：50)",
     *                  "bm":"别名(最大字段长度：50)",
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                   "snbh":"人员所内编号(最大字段长度：8)",
     *                   "jsh":"监室号 (最大字段长度：4)",
     *                   "xm":"姓名(最大字段长度：50)",
     *                   "xb":"性别(最大字段长度：1)",
     *                   "taskid":"任务id(必填;最大字段长度：21)",
     *                   "jsbh":"监所编号(必填;最大字段长度：9)",
     *                   "ldspid":"领导审批id(必填;最大字段长度：23)"
     *                   },
     *              "data_ywxx":"业务信息(必填;)"{
     *                   "taskid":"任务id(必填;最大字段长度：21)",
     *                   "rybhs":"人员编号(必填;最大字段长度：21)",
     *                   "csyy":"出所原因(必填;最大字段长度：50)",
     *                   "blsj":"办理时间(必填;格式：yyyy-MM-dd hh:mm:ss)"
     *                   "dlmj":"带领民警(必填;最大字段长度：100)"
     *                                        }
     *          }]
     *     }
     */

    //{"dlmj":"\\S{1,100}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"data_Jbxx":{"ywlcid":"5255737","bm":"","rybh":"110000114201912040013","snbh":"20190297","jsh":"1204","xm":"是是是","xb":"1","taskid":"5255993","jsbh":"110000114","ldspid":"11000011420200107003804"},"data_ywxx":{"taskid":"5255993","tbr":"","blsj":"2019-12-10 10:10:10","csyy":"","dlmj":"擦擦擦","rybhs":"110000114201912040013","tbrq":"vv"}}]}
    @ApiOperation("监区提人登记保存")
    @PostMapping("/txJqtrdj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> txJqtrdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ts/txJqtrdj";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            Kss_TsdjPlModel tsdjPlModel=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(),Kss_TsdjPlModel.class);
            List<Kss_TsdjPlModel> list =new ArrayList<Kss_TsdjPlModel>();
            list.add(tsdjPlModel);
            Kss_CrjjcModelDO crjjc = JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(),Kss_CrjjcModelDO.class);
            Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_ywxx");
            Map<String, Object> mapls=new HashMap<String, Object>();
            mapls.put("entity",entity);
            String jsons="{'entity':["+JSON.toJSONString(entity)+"]}";
            Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
            maplss.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(maplss);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            String taskid=tsdjPlModel.getTaskid();
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }

            crjjc.setJsbh(jsbh);
            crjjc.setCreatetime(new Date());
            crjjc.setCreator("管理员");
            crjjc.setState("R2");
            crjjc.setBllx("1");
            crjjc.setYwlcid(list.get(0).getYwlcid());
            Map<String, Object> mapss=new HashMap<String, Object>();
            mapss.put("jbxx", list);
            mapss.put("crjjc", crjjc);

            ResponseMessage<String> result =kssServerApis.addJqtrFlow(mapss);

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
     * @api {post} /v4/kss/ts/addTsdjHsaqjc 安全检查登记保存
     * @apiVersion 0.4.0
     * @apiName addTsdjHsaqjc
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 安全检查登记保存.
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[{
     *                  "ywlcid":"业务流程ID(最大字段长度：50)",
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                   "taskid":"任务id(必填;最大字段长度：21)",
     *                   "wjwpdj":"违禁物品登记(最大字段长度：255)",
     *                   "kysqdj":"可疑伤情登记(最大字段长度：255)",
     *                   "jcjg":"检查结果"
     *                   "jcr":"检查人(必填;最大字段长度：50)",
     *                   "jcsj":"检查时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
     *                   "jssj":"结束时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
     *                   "djsj":"登记时间(必填;格式：yyyy-MM-dd hh:mm:ss)"
     *                   "zbmj":"值班民警(必填;最大字段长度：50)"
     *          }]
     *     }
     */

    //{"zbmj":"\\S{1,50}","jcsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jcr":"\\S{1,50}","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"tbr":"管理员","tbrq":"2020-01-08 11:07:38","rybh":"110000114201912040013","taskid":"5255993","ywlcid":"5255737","jcr":"管理员","jcsj":"2020-01-08 11:07:38","jssj":"2020-01-08 11:07:38","jcjg":"1","wjwpdj":"","kysqdj":"","bz":"","zbmj":"管理员","djsj":"2020-01-08 11:07:38"}]}
    @ApiOperation("安全检查登记保存")
    @PostMapping("/addTsdjHsaqjc")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addTsdjHsaqjc(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ts/addTsdjHsaqjc";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            Kss_TsdjModelDO tsdjDoModel=JSONObject.parseObject(rslist.get(0).toString(),Kss_TsdjModelDO.class);
            Kss_CrjjcModelDO model = JSONObject.parseObject(rslist.get(0).toString(),Kss_CrjjcModelDO.class);
            System.err.println("123456"+tsdjDoModel);
            System.err.println("654321"+model);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            String taskid=tsdjDoModel.getTaskid();
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }

            model.setBllx("2");
            model.setJsbh(jsbh);
            model.setUpdator("管理员");
            model.setUpdatetime(new Date());
            model.setDlmj(tsdjDoModel.getJcr());
            model.setBlsj(tsdjDoModel.getJcsj());
            model.setRybh(tsdjDoModel.getRybh());
            model.setCreator("管理员");
            model.setCreatetime(new Date());
            model.setState("R2");
            model.setYwlcid(tsdjDoModel.getYwlcid());

            Map<String, Object> mapss=new HashMap<String, Object>();
            mapss.put("model", model);
            mapss.put("tsdjModel", tsdjDoModel);
            ResponseMessage<String> result =kssServerApis.addTsdjHsaqjc(taskid,mapss);

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
     * @api {post} /v4/kss/ts/txAdd 退讯保存
     * @apiVersion 0.4.0
     * @apiName txAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 退讯保存.
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[{
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                  "taskid":"任务id(必填;最大字段长度：21)",
     *                  "ywlcid":"业务流程ID(最大字段长度：50)",
     *                  "id":"id(最大字段长度：23)",
     *                  "txry":"退讯人员(必填;最大字段长度：50)",
     *                  "txsj":"退讯时间(必填;格式：yyyy-MM-dd hh:mm:ss)"
     *          }]
     *     }
     */

    //{"txry":"\\S{1,50}","txsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    //{"entity":[{"tbr":"管理员","tbrq":"2020-01-08 11:07:38","rybh":"110000114201912040013","taskid":"5255993","ywlcid":"5255737","txsj":"2020-01-08 11:07:38","txry":"aa"}]}
    @ApiOperation("退讯保存")
    @PostMapping("/txAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> txAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ts/txAdd";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            Kss_TsdjModelDO model=JSONObject.parseObject(rslist.get(0).toString(),Kss_TsdjModelDO.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            String taskid=model.getTaskid();
            if (StringUtils.isNullOrEmpty(taskid)){
                return ResponseMessage.error("taskid不能为空！");
            }
            model.setJsbh(jsbh);
            model.setUpdatetime(new Date());
            model.setUpdator("管理员");
            ResponseMessage<String> result =kssServerApis.addTsdjTx(taskid,model);

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
     * @api {get} /v4/kss/ts/tssUseList 提审室查询
     * @apiVersion 0.4.0
     * @apiName tssUseList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提审室查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}wcndlr          				                    人员编号
     * @apiSuccess {String}jssj          				                    结束时间
     * @apiSuccess {String}wjwpdj                                           违禁物品登记
     * @apiSuccess {String}qtzjh                                            其他办案人证件号
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}tszbr                                            监区值班民警
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}txsy                                             提讯事由
     * @apiSuccess {String}dw                                               办案单位
     * @apiSuccess {String}baryy                                            办案人员姓名
     * @apiSuccess {String}jcjg                                             检查结果
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}lfrpzh                                           来访人凭证文书号
     * @apiSuccess {String}wcnjhr                                           未成年人监护人
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}kysqdj                                           可疑伤情登记
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}kssj                                             开始时间
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}lfrxm                                            来访人姓名
     * @apiSuccess {String}pastable                                         是否有效
     * @apiSuccess {String}txrq                                             提讯日期
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}qtbar                                            其他办案人
     * @apiSuccess {String}zbmj                                             值班民警
     * @apiSuccess {String}jsbh                                             监室编号
     * @apiSuccess {String}zjh2                                             证件号
     * @apiSuccess {String}lfrzjh                                           来访人证件号
     * @apiSuccess {String}jcsj                                             检查时间
     * @apiSuccess {String}lfsj                                             来访时间
     * @apiSuccess {String}zjh1                                             证件号
     * @apiSuccess {String}lxdh                                             联系电话
     * @apiSuccess {String}tss                                              提讯地点
     * @apiSuccess {String}txlx                                             提讯类型
     * @apiSuccess {String}jcr                                              检查人
     * @apiSuccess {String}ywlcid                                           业务流程id
     * @apiSuccess {String}djsj                                             登记时间
     * @apiSuccess {String}barye                                            办案人员姓名
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": null,
     *    "result": "{WCNDLR=, UPDATETIME=1577157606000, QTZJH=, STATE=R2, UPDATOR=管理员, TXSY=12, DW=北京市公安局国保局指挥处, BARYY=12, CREATOR=管理员, LFRPZH=310000010200201912240001, WCNJHR=, ID=11000011420191224000263, CREATETIME=1577157571000, LFRXM=123, PASTABLE=1, TXRQ=1577116800000, RYBH=110000112201911040001, QTBAR=, JSBH=110000114, ZJH2=, LFRZJH=320123199712043419, LFSJ=1577157522000, ZJH1=, LXDH=, TSS=1, TXLX=12, YWLCID=5150632, BARYE=12}",
     *     "status": 200,
     *     "timestamp": 1578393799247
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "tss":"提讯地点(最大字段长度：20)",
     *        }
     *
     *
     */
    @OpenAPI
    @ApiOperation("提审室查询")
    @GetMapping("/tssUseList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> tssUseList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
        String interfaceId = "/v4/kss/ts/tssUseList";
        //wcndlr,jssj,wjwpdj,qtzjh,state,tszbr,updator,txsy,dw,baryy,jcjg,creator,lfrpzh,wcnjhr,bz,kysqdj,id,kssj,createtime,lfrxm,pastable,txrq,rybh,qtbar,zbmj,jsbh,zjh2,lfrzjh,jcsj,lfsj,zjh1,lxdh,tss,txlx,jcr,ywlcid,djsj,barye
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            String tss = (String) map.get("tss");

            ResponseMessage<List<Map<String, Object>>> result = kssServerApis.TssUseState(tss);
            System.out.println("0000000000-----"+JSONUtil.toJson(result));
            //封装需要的数据
            if(result.getResult().size()>0){
                if(StringUtils.isNullOrEmpty((String)result.getResult().get(0).get("TXRY"))){
                    return ResponseMessage.ok(result.getResult().get(0).toString());
                }else {
                    return ResponseMessage.ok(null);
                }
            }else {
                return ResponseMessage.ok(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {get} /v4/kss/ts/jbxxForTs 提讯业务台账查询
     * @apiVersion 0.4.0
     * @apiName jbxxForTs
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提讯业务台账查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}zjh1                                             证件号
     * @apiSuccess {String}zjh2                                             证件号
     * @apiSuccess {String}ywlcid                                           业务流程id
     * @apiSuccess {String}kssj                                             开始时间
     * @apiSuccess {String}barye                                            办案人员姓名
     * @apiSuccess {String}jssjString                                       结束时间（已转换）
     * @apiSuccess {String}lfrxm                                            来访人
     * @apiSuccess {String}jssj          				                    结束时间
     * @apiSuccess {String}dw                                               办案单位
     * @apiSuccess {String}snbh                                             所内编号
     * @apiSuccess {String}tss                                              提讯地点
     * @apiSuccess {String}tssString                                        提讯地点（已转换）
     * @apiSuccess {String}kssjString                                       开始时间（已转换）
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}qtzjh                                            其他办案人证件号
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}txsy                                             提讯事由
     * @apiSuccess {String}lfrzjh                                           来访人证件号
     * @apiSuccess {String}baryy                                            办案人员姓名
     * @apiSuccess {String}pastable                                         是否有效
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}lxdh                                             联系电话
     * @apiSuccess {String}lfsj                                             来访时间
     * @apiSuccess {String}wcnjhr                                           未成年人监护人
     * @apiSuccess {String}txlx                                             提讯类型
     * @apiSuccess {String}gyqx                                             关押期限
     * @apiSuccess {String}bahj                                             办案环节
     * @apiSuccess {String}tszbr                                            监区值班民警
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}rsrq                                             入所日期
     * @apiSuccess {String}txrq                                             提讯日期
     * @apiSuccess {String}ayString                                         主要案由
     * @apiSuccess {String}ay                                               主要案由（已转换）
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}lfrpzh                                           来访人凭证文书号
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}badw                                             办案单位
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}qtbar                                            其他办案人
     * @apiSuccess {String}wcndlr          				                    人员编号
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": null,
     *     "result": {
     *         "total": 17,
     *         "data": [
     *         {
     *         "zjh1": "null",
     *         "zjh2": "null",
     *         "jcjg": "1",
     *         "ywlcid": "5122509",
     *         "kssj": 1576919583000,
     *         "barye": "123",
     *         "lfrxm": "123",
     *         "jssj": 1576919591000,
     *         "dw": "北京市公安局国内安全保卫局",
     *         "tss": "1",
     *         "bz": "null",
     *         "jcr": "管理员",
     *         "qtzjh": "null",
     *         "state": "R2",
     *         "id": "11000011420191219000261",
     *         "txsy": "123",
     *         "lfrzjh": "320123199712043419",
     *         "baryy": "123",
     *         "pastable": "1",
     *         "jcsj": 1576919591000,
     *         "creator": "管理员",
     *         "createtime": 1576746590000,
     *         "lxdh": "null",
     *         "lfsj": 1576746547000,
     *         "wcnjhr": "null",
     *         "txlx": "123",
     *         "kysqdj": "null",
     *         "wjwpdj": "null",
     *         "djsj": 1576919591000,
     *         "tszbr": "管理员",
     *         "txrq": 1576746604000,
     *         "lfrpzh": "110000114191125000002",
     *         "rybh": "110000114201909120002",
     *         "updator": "管理员",
     *         "zbmj": "管理员",
     *         "qtbar": "null",
     *         "jsbh": "110000114",
     *         "wcndlr": "null"
     *       }],
     *     "page": "1"
     *     },
     *     "status": 200,
     *     "timestamp": 1578393799247
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "pageSize":"10",
     *          "pageIndex":"0",
     *          "page":"1",
     *          "rows":"10",
     *          "sort":"id",
     *          "order":"desc"
     *        }
     *
     *
     */
    @OpenAPI
    @ApiOperation("提讯业务台账查询")
    @GetMapping("/jbxxForTs")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> jbxxForTs(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
        //wcndlr,jssj,wjwpdj,qtzjh,state,tszbr,updator,txsy,dw,baryy,jcjg,creator,lfrpzh,wcnjhr,bz,kysqdj,id,kssj,createtime,lfrxm,pastable,txrq,rybh,qtbar,zbmj,jsbh,zjh2,lfrzjh,jcsj,lfsj,zjh1,lxdh,tss,txlx,jcr,ywlcid,djsj,barye
        String interfaceId = "/v4/kss/ts/jbxxForTs";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            QueryParam qParam = new QueryParam();
            int pageIndex = Integer.parseInt((String) map.get("pageIndex"));
            int pageSize =  Integer.parseInt((String) map.get("pageSize"));
            qParam.setPageIndex(pageIndex);
            qParam.setPageSize(pageSize);
            qParam.and("jsbh",TermType.eq,jsbh);
            qParam.and("jbxx_jsbh",TermType.eq,jsbh);
            qParam.and("jbxx_state",TermType.eq,"R8");
            qParam.and("state",TermType.eq,"R2");
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.txQuery(qParam);
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





}
