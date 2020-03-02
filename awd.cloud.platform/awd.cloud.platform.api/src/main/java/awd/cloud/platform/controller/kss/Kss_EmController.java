package awd.cloud.platform.controller.kss;


import awd.bj.kss.model.EmdjModel;
import awd.bj.kss.model.JbxxModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.kss.EmdjModelDO;
import awd.cloud.platform.model.kss.JshjModelDO;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/em")
@Api(tags = "kss-em",description = "Em")
public class Kss_EmController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
   private ProcessService processService;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;



    /**
     * @api {post} /v4/kss/em/emdjList 耳目登记查询
     * @apiVersion 0.4.0
     * @apiName emdjList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 耳目登记查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsbhString                                       监所编号（已转换）
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}bllx                                             办理类型
     * @apiSuccess {String}bllxString                                       办理类型（已转换）
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}ly                                               理由
     * @apiSuccess {String}lyString                                         理由（已转换）
     * @apiSuccess {String}jbr                                              经办人
     * @apiSuccess {String}szrq                                             设置日期
     * @apiSuccess {String}emlx                                             耳目类型
     * @apiSuccess {String}emlxString                                       耳目类型（已转换）
     * @apiSuccess {String}jnbx                                             自觉遵守监规监纪情况
     * @apiSuccess {String}xlzy                                             反映监室动态情况
     * @apiSuccess {String}khpj                                             考核等级
     * @apiSuccess {String}khpjString                                       考核等级（已转换）
     * @apiSuccess {String}gjxm                                             管教姓名
     * @apiSuccess {String}gjyj                                             管教意见
     * @apiSuccess {String}gjpssj                                           管教批示时间
     * @apiSuccess {String}ldxm                                             领导姓名
     * @apiSuccess {String}ldyj                                             领导意见
     * @apiSuccess {String}ldpssj                                           领导批示时间
     * @apiSuccess {String}qtqk                                             其他耳目工作完成情况
     * @apiSuccess {String}zdzxm                                            中队长姓名
     * @apiSuccess {String}zdzyj                                            中队长意见
     * @apiSuccess {String}zdzpssj                                          中队长批示时间
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}ywlcid                                           业务流程id
     * @apiSuccess {String}psbz                                             批示标志
     * @apiSuccess {String}psbzString                                       批示标志（已转换）
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}stateString                                      状态（已转换）
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}updatetime                                       更新时间
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
     * 		    "id": "11000011420200117000454",
     * 			"jsbh": "110000114",
     * 			"jsbhString": "北京市第一看守所",
     * 			"jsh": "0101",
     * 			"bllx": "",
     * 			"bllxString": "",
     * 			"rybh": "110000112201911050002",
     * 			"ly": "",
     * 			"lyString": "",
     * 			"jbr": "",
     * 			"szrq": "2020-01-17 15:37:19",
     * 			"emlx": "02",
     * 			"emlxString": "狱侦耳目",
     * 			"jnbx": "111",
     * 			"xlzy": "111",
     * 			"khpj": "1",
     * 			"khpjString": "一般",
     * 			"gjxm": "",
     * 			"gjyj": "",
     * 			"gjpssj": null,
     * 			"ldxm": "",
     * 			"ldyj": "",
     * 			"ldpssj": null,
     * 			"qtqk": "111",
     * 			"zdzxm": "",
     * 			"zdzyj": "",
     * 			"zdzpssj": null,
     * 			"bz": "",
     * 			"ywlcid": "",
     * 			"psbz": "",
     * 			"psbzString": "",
     * 			"state": "R2",
     * 			"stateString": "有效",
     * 			"creator": "管理员",
     * 			"createtime": "2020-01-17 15:37:27",
     * 			"updator": "",
     * 			"updatetime": null
     * 		}],
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
     *          "rybh":"110000112201911050002",
     *          "page":"1",
     *          "rows":"20",
     *          "sort":"id",
     *          "order":"desc"
     *        }
     *
     */


    // id,jsbh,jsbhString,jsh,bllx,bllxString,rybh,ly,lyString,jbr,szrq,emlx,emlxString,jnbx,xlzy,khpj,khpjString,gjxm,gjyj,gjpssj,ldxm,ldyj,ldpssj,qtqk,zdzxm,zdzyj,zdzpssj,bz,ywlcid,psbz,psbzString,state,stateString,creator,createtime,updator,updatetime
    //{"rybh":"110000112201911050002","page":"1","rows":"20","sort":"id","order":"desc"}
    @OpenAPI
    @ApiOperation("耳目登记查询")
    @PostMapping("/emdjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> emdjList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/em/emdjList";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数
            String rybh = (String) maps.getResult().get("rybh");
            QueryParam qParam = new QueryParam();
            if(!StringUtils.isNullOrEmpty(rybh)) {
                qParam.and("rybh", TermType.gte, rybh);
            }
            DefaultQueryParam.addDefaultQueryParam(request, qParam, "R2");
            ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.emdjQueryForPage(qParam);
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

    /**
     * @api {post} /v4/kss/em/emkhAdd  耳目考核保存
     * @apiVersion 0.4.0
     * @apiName emkhAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  耳目考核保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     *              "creator":"创建人(必填;最大字段长度:21)",
     *              "createtime":"创建时间(格式:yyyy-MM-dd hh:mm:ss)",
     *              "rybh":"人员编号(必填;最大字段长度:21)",
     *              "xm":"姓名(必填;最大字段长度:30)",
     *              "jsh":"监室号(必填;最大字段长度:4)",
     *              "emlx":"耳目类型(必填;最大字段长度:2)",
     *              "szrq":"考核日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *              "khpj":"考核等级(必填;最大字段长度:1)",
     *              "jnbx":"监规监纪情况(必填;)",
     *              "xlzy":"反映监室动态情况(必填;)",
     *              "qtqk":"其他耳目完成情况(必填;)"
     * 	        }]
     * }
     *
     *
     */
    @ApiOperation("耳目考核保存")
    @PostMapping("/emkhAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> emkhAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"rybh":"\\d{1,21}","xm":"\\S{1,30}","jsh":"\\d{1,4}","emlx":"\\d{1,2}","szrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","khpj":"\\d{1}"}
        //{"entity":[{"creator":"sw","createtime":"","rybh":"110000112201911050002","xm":"刘峰","jsh":"0101","emlx":"02","szrq":"2020-01-19 13:51:41","khpj":"2","jnbx":"ccccc","xlzy":"sssss","qtqk":"wddwd"}]}
        //接口id
        String interfaceId = "/v4/kss/em/emkhAdd";
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

            //封装数据
            List<EmdjModel> emdjModels = JSONArray.parseArray(map.get("entity").toString(), EmdjModel.class);
            EmdjModel emdjModel = emdjModels.get(0);
            emdjModel.setState("R2");
            emdjModel.setJsbh(jsbh);
            long myDate = Calendar.getInstance().getTimeInMillis();
            Date date = new Date(myDate);
            emdjModel.setCreatetime(date);
            if (StringUtils.isNullOrEmpty(emdjModel.getCreator())){
                return ResponseMessage.error("创建人creator不能为空！");
            }
            if (StringUtils.isNullOrEmpty(emdjModel.getJnbx())){
                return ResponseMessage.error("监规监纪情况jnbx不能为空！");
            }
            if (StringUtils.isNullOrEmpty(emdjModel.getXlzy())){
                return ResponseMessage.error("反映监室动态情况xlzy不能为空！");
            }
            if (StringUtils.isNullOrEmpty(emdjModel.getQtqk())){
                return ResponseMessage.error("其他耳目完成情况qtqk不能为空！");
            }
            ResponseMessage<String> result = kssServerApis.emdjSave(emdjModel);
            System.err.println("--"+ JSON.toJSONString(result));
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
     * @api {post} /v4/kss/em/emdjAdd  耳目设置的保存操作
     * @apiVersion 0.4.0
     * @apiName emdjAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  耳目设置的保存操作.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     * 	     "emlx":"耳目类型(必填;字典:EMLX;最大字段长度:2)"
     * 	    "lcid":"业务流程流程id(必填;最大字段长度:21)"
     * 	    "jsh":"监室号(必填;最大字段长度:4)"
     * 		"creator": "创建者(必填;最大字段长度:50)",
     * 		"rybh": "人员编号(必填;最大字段长度:21)",
     * 		"ly": "设置理由(必填;(字典:EMSZLY)(字典:EMCXLY))",
     * 		"jbr": "经办人(必填;最大字段长度:50)",
     * 		"szrq": "设置日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * 		"xm": "姓名(最大字段长度：50)",
     * 		"snbh": "人员所内编号(必填;最大字段长度:8)"
     * 	}]
     * }
     *
     *
     */
    @ApiOperation("耳目设置的保存操作")
    @PostMapping("/emdjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, String>> emdjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/em/emdjAdd";
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

            //封装数据
            List<JbxxModelDO> jbxxModelDOs = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class);
            List<EmdjModel> emdjModels = JSONArray.parseArray(map.get("entity").toString(), EmdjModel.class);
            JbxxModelDO jbxxModelDO = jbxxModelDOs.get(0);
            EmdjModel emdjModel = emdjModels.get(0);

            ResponseMessage<String> s = processService.FlowMutex(jsbh, emdjModel.getRybh(), "KSS_EMSZ", "EMDJ");
            System.err.println("消息=" + s.getMessage());
            if (s.getStatus() != 200) {
                return ResponseMessage.error(s.getMessage());
            }

            if (StringUtils.isNullOrEmpty(emdjModel.getLy())) {
                return ResponseMessage.error( "请设置理由" );
            }
            emdjModel.setState("R2");
            emdjModel.setJsbh(jsbh);
            emdjModel.setCreatetime(new Date());
            emdjModel.setPsbz("0");
            emdjModel.setBllx("1");
            EmdjModelDO emd = new EmdjModelDO();
            emd.setLcid(CacheUtils.get().getFlowKey("KSS_EMSZ"));
            emd.setEmdjEntity(emdjModel);
            emd.setJbxxModel(jbxxModelDO);
            emd.setJsbh(jsbh);

            ResponseMessage<Map<String, String>> jshj = kssServerApis.emdjAddFlow(emd);
            System.err.println("--"+ JSON.toJSONString(jshj));
            if(jshj.getStatus() == 200){
                jshj.setMessage("保存成功!");
            }else{
                jshj.setMessage("服务异常,保存失败!");
            }
            return jshj;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }





    /**
     * @api {post} /v4/kss/em/zdjsyjAdd  中队警署意见的保存操作ztx
     * @apiVersion 0.4.0
     * @apiName zdjsyjAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  中队警署意见的保存操作ztx.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *  "taskid": "任务id",
     *  "type":类型,
     * 	"entity": [{     *
     * 		"updator": "更新人(必填;最大字段长度:50)",
     * 		"jsh": "监室号(必填;最大字段长度:4)",
     * 		"psbz": "批示标志(必填;字典:PSBZ,最大长度为1)",
     * 		"zdzyj": "中队长意见(必填;最大字段长度:200)",
     * 		"zdzxm": "中队长姓名(必填;最大字段长度:30)",
     * 		"rybh": "人员所内编号(必填;最大字段长度:21)",     *
     * 	    "ywlcid": "业务流程使用ID(必填;最大字段长度:21)"
     * 	}]
     * }
     *
     *
     */
    @ApiOperation("中队警署意见的保存操作ztx")
    @PostMapping("/zdjsyjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zdjsyjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/em/zdjsyjAdd";
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
            //封装数据
            List<EmdjModel> emdjModels = JSONArray.parseArray(map.get("entity").toString(), EmdjModel.class);
            EmdjModel emdjModel = emdjModels.get(0);
            emdjModel.setJsbh(jsbh);
            emdjModel.setZdzpssj(new Date());
            emdjModel.setUpdatetime(new Date());
            String type=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("type"))) {
                return ResponseMessage.error("type必传");
            }
            type=maps.getResult().get("type").toString();

            String taskid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))) {
                return ResponseMessage.error("taskid必传");
            }

            ResponseMessage<String> result = kssServerApis.emdjSpFlow(emdjModel,taskid,type);
            System.err.println("--"+ JSON.toJSONString(result));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }





    /**
     * @api {post} /v4/kss/em/emdjCXAdd  耳目撤销的保存操作
     * @apiVersion 0.4.0
     * @apiName emdjCXAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  耳目撤销的保存操作.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
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
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     * 	"xm": "姓名(必填;最大字段长度:50)",
     * 	"snbh": "所内编号(必填;最大字段长度:8)",
     * 	"creator": "更新人(必填;最大字段长度:50)",
     * 	"jsh": "监室号(必填;最大字段长度:4)",
     * 	"ywlcid": "业务流程使用ID(必填;最大字段长度:21)",
     * 	"rybh": "人员所内编号(必填;最大字段长度:21)",
     * 	"ly": "设置理由(必填;(字典:EMSZLY)(字典:EMCXLY))",
     * 	"gjyj": "管教意见(必填;最大字段长度:200)",
     * 	"szrq": "设置日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * 	"gjxm": "管教姓名(必填;最大字段长度:30)",
     * 	"gjpssj": "管教批示时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * 	"emlx": "耳目类型(必填;字典:EMLX;最大字段长度:2)"
     * }]
     * }
     *
     *
     */
    @ApiOperation("耳目撤销的保存操作")
    @PostMapping("/emdjCXAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String,Object>> emdjCXAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/em/emdjCXAdd";
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

            //封装数据
            List<EmdjModel> emdjModels = JSONArray.parseArray(map.get("entity").toString(), EmdjModel.class);
            List<JbxxModelDO> jbxxModelDOS = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class);
            EmdjModel emdjModel = emdjModels.get(0);
            JbxxModelDO jbxxModelDO = jbxxModelDOS.get(0);
            //校验理由
            if (StringUtils.isNullOrEmpty(emdjModel.getLy())) {
                return ResponseMessage.error( "请设置理由" );
            }


            ResponseMessage<String> s = processService.FlowMutex(jsbh, emdjModel.getRybh(), "KSS_EMSZ", "EMDJ");
            System.err.println("消息=" + s.getMessage());
            if (s.getStatus() != 200) {
                return ResponseMessage.error(s.getMessage());
            }
            emdjModel.setState("R2");
            emdjModel.setJsbh(jsbh);
            emdjModel.setCreatetime(new Date());
            emdjModel.setPsbz("0");
            emdjModel.setBllx("2");
            emdjModel.setJbr(emdjModel.getCreator());

            EmdjModelDO emd = new EmdjModelDO();
            emd.setLcid(CacheUtils.get().getFlowKey("KSS_EMCX"));
            emd.setEmdjEntity(emdjModel);
            emd.setJbxxModel(jbxxModelDO);
            emd.setJsbh(jsbh);

            ResponseMessage<Map<String,Object>> result = kssServerApis.emdjAddCXflow(emd);
            System.err.println("--"+ JSON.toJSONString(result));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }





    /**
     * @api {post} /v4/kss/em/emYwdt   耳目业务动态查询
     * @apiVersion 0.4.0
     * @apiName emYwdt   耳目业务动态查询
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription    耳目业务动态查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}emTj          				                    耳目提解
     * @apiSuccess {String}queryJq                                          查询监区号总数
     * @apiSuccess {String}emzs                                             耳目总数
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}JQH                                              监区号
     * @apiSuccess {String}jqmc                                             监区名称
     * @apiSuccess {String}emWbjjs                                          耳目未布建监室
     * @apiSuccess {String}emYbjjs                                          耳目已布建监室
     * @apiSuccess {String}Wbjjs                                            未布建监室
     * @apiSuccess {String}Ybjjs                                            已布建监室
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
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "emTj": {
     *       "1001": [
     *         {
     *           "jshrs": 0,
     *           "jsh": "1001"
     *         }
     *       ],
     *       "1002": [
     *         {
     *           "jshrs": 0,
     *           "jsh": "1002"
     *         }
     *         ],}
     *      "queryJq": {
     * 			"西二监区": [{
     * 				"JQH": "07",
     * 				"JSH": "0701",
     * 				"JQMC": "西二监区"
     * 			}]}
     * 	    "emYbjjs": [{
     * 			"ybjemjs": 9
     * 		}],
     * 			"emWbjjs": [{
     * 			"wbjjs": 238
     * 		}],
     * 		"emzs": [{
     * 			"emzs": 22
     * 		}]
     * 	},
     * 	 "total":"4"
     * 	 "page": "2"
     * 	  },
     *   "status": 200,
     *   "timestamp": 1579138160876
     * }


     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填)",
     * 	  json:{
     *             "jqh":"监区号(必填)"
     *         }
     *
     *
     */

    @ApiOperation("耳目业务动态查询")
    @GetMapping("/emYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> emYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/em/emYwdt";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            String jqh=null;
            if (StringUtils.isNullOrEmpty(maps.getResult().get("jqh"))) {
                return  ResponseMessage.error("监区号没传");
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.emYwdt(jsbh,jqh);

            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResult().get("queryJq");
            Map<String, List<Map<String, Object>>> glist = list.stream()
                    .collect(Collectors.groupingBy(e -> e.get("JQMC").toString()));
            result.getResult().put("queryJq", glist);

            List<Map<String, Object>> emlist = (List<Map<String, Object>>) result.getResult().get("emTj");
            Map<String, List<Map<String, Object>>> gemlist = emlist.stream()
                    .collect(Collectors.groupingBy(e -> e.get("jsh").toString()));
            result.getResult().put("emTj", gemlist);

            System.err.println("result" + JSON.toJSONString(result));

            Map<String, Object> maplist = new HashMap<String, Object>();
            List<Map<String,Object>> entityList = new ArrayList<>();
            entityList.add(result.getResult());
            maplist.put("entity", entityList);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total","4");
            maplist.put("page", "1");

            ResponseMessage<Map<String, Object>> lists = this.kfzdShow(maplist);
            if (lists.getStatus() == 200) {
                result.setMessage("查询成功");
                if (lists.getResult() == null) {
                    result.setMessage("未查询数据");
                }
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }







    /**
     * @api {post} /v4/kss/em/emYwtz   耳目管理业务台账查询
     * @apiVersion 0.4.0
     * @apiName emYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription    耳目管理业务台账查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}zdzyj          				                    中队长意见
     * @apiSuccess {String}ywlcid                                           业务流程id
     * @apiSuccess {String}ly                                               理由
     * @apiSuccess {String}szrq                                             设置日期
     * @apiSuccess {String}snbh                                             人员所内编号
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}emlx                                             耳目类型(字典:EMLX)
     * @apiSuccess {String}state                                            状态(字典:STATE)
     * @apiSuccess {String}zdzpssj                                          中队长批示时间
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}zdzxm                                            中队长姓名
     * @apiSuccess {String}rsrqString                                       入所日期(已转换)
     * @apiSuccess {String}emlxString                                       耳目类型(已转换)
     * @apiSuccess {String}bahjString                                       办案环节(已转换)
     * @apiSuccess {String}xb                                               性别
     * @apiSuccess {String}szrqString                                       设置日期(已转换)
     * @apiSuccess {String}bahj                                             办案环节(字典:BAJD)
     * @apiSuccess {String}psbz                                             批示标志(字典:PSBZ)
     * @apiSuccess {String}xm                                               姓名
     * @apiSuccess {String}rsrq                                             入所日期
     * @apiSuccess {String}ay                                               案由
     * @apiSuccess {String}bllx                                             耳目办理类型(字典:EMBLLX)
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}jsbh                                             监所编号
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
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 76,
     *     "data": [
     *       {
     *         "zdzyj": "12312312",
     *         "ywlcid": "4631316",
     *         "ly": "6",
     *         "szrq": 1574412782000,
     *         "lris_url2": null,
     *         "lris_url1": null,
     *         "snbh": "20190204",
     *         "lris_url4": null,
     *         "lris_url5": null,
     *         "bz": "123123123",
     *         "id": "11000011420191122000384",
     *         "emlx": "01",
     *         "state": "R2",
     *         "zdzpssj": 1574412834000,
     *         "creator": "管理员",
     *         "createtime": 1574412820000,
     *         "zdzxm": "管理员",
     *         "rsrqString": "2019-10-17 09:43:42",
     *         "emlxString": "安全耳目",
     *         "bahjString": "逮捕",
     *         "xb": null,
     *         "szrqString": "2019-11-22 16:53:02",
     *         "bahj": "12",
     *         "psbz": "2",
     *         "xm": "妊娠检测",
     *         "rsrq": 1571276622000,
     *         "ay": null,
     *         "bllx": "1",
     *         "rybh": "110000114201910170001",
     *         "updator": "管理员",
     *         "updatetime": 1574412834000,
     *         "jsh": "9010",
     *         "jsbh": "110000114"
     *       },
     *     ],
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579151476811
     * }


     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * 	  json:{
     *             "xm":"姓名"
     *             "xm_type":"姓名类型";
     *             "jsh":"监室号";
     *             "xb":"性别"
     *             "xb_type":"性别类型"
     *             "blrqstart":"办理日期开始"
     *             "blrqend"办理日期结束"
     *             "rybh":"人员编号"
     *             "ay":"案由"
     *             "ay_type":"案由类型"
     *             "rsrq_start":"入所日期开始"
     *             "rsrq_end":"入所日期结束"
     *             "csrq_start":"出生日期开始"
     *             "csrq_end":"出生日期结束"
     *         }
     *
     *
     */

    @ApiOperation("耳目管理业务台账查询")
    @GetMapping("/emYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> emYwtz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/em/emYwtz";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                param.and("szrq", TermType.gte, maps.getResult().get("blrqstart").toString()+ " 00:00:00");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                param.and("szrq", TermType.lte, maps.getResult().get("blrqend").toString()+ " 23:59:59");
            }
            String xm=null;
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))){
                xm=(String) maps.getResult().get("xm");
            }
            if (!StringUtils.isNullOrEmpty(xm)) {
                if ("0".equals((String) maps.getResult().get("xm_type"))) {
                    xm = word2py(xm);
                    param.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
                } else {
                    param.and("jbxx_xm", TermType.like, "%" + xm + "%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
                if ("0".equals(maps.getResult().get("xb_type").toString())) {
                    param.and("jbxx_xb", TermType.eq, maps.getResult().get("xb").toString());
                } else {
                    param.and("jbxx_xb", TermType.not, maps.getResult().get("xb").toString());
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
                if ("0".equals(maps.getResult().get("ay_type").toString())) {
                    param.and("jbxx_ay", TermType.like, "%" + maps.getResult().get("ay").toString() + "%");
                } else {
                    param.and("jbxx_ay", TermType.not, maps.getResult().get("ay").toString());
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh").toString());
            }
            if (!StringUtils.isNullOrEmpty( maps.getResult().get("rsrq_start"))) {
                param.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq_start").toString());
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
                param.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq_end").toString());
            }
            if (!StringUtils.isNullOrEmpty( maps.getResult().get("csrq_start"))) {
                param.and("jbxx_csrq", TermType.gte, maps.getResult().get("csrq_start").toString() );
            }
            if (!StringUtils.isNullOrEmpty( maps.getResult().get("csrq_end"))) {
                param.and("jbxx_csrq", TermType.lte,maps.getResult().get("csrq_end").toString());
            }


            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            param.and("state", TermType.eq, "R2");
            param.and("jbxx_state", TermType.eq, "R8");
            param.and("jsbh", TermType.eq, jsbh);
            param.and("jbxx_jsbh", TermType.eq, jsbh);

            ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.emYwtz(param);
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

    public String word2py(String word) {
        if (StringUtils.isNullOrEmpty(word)) {
            return "";
        }
        return Pinyin4j.cn2Pinyin(word);

    }








}
