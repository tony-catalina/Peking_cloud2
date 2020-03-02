package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.ShgxModel;
import awd.bj.kss.model.SzjdjlModel;
import awd.bj.kss.model.WlryModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JslxModelDO;
import awd.cloud.platform.model.kss.Kss_WlryModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
@RestController
@RefreshScope
@RequestMapping("/v4/kss/ldjf")
@Api(tags = "kss-ldjf",description = "ldjf")
public class Kss_LdjfController extends PublicService{
    @Autowired
    private KssServerApis kssServerApis;
    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    /**
     * @api {get} /v4/kss/ldjf/ldjfList 领导接访查询
     * @apiVersion 0.4.0
     * @apiName ldjfList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 领导接访查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}clqk                                             事后处理情况
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}dfyj                                             当时答复意见
     * @apiSuccess {String}djsj                                             登记时间
     * @apiSuccess {String}fywt                                             反映问题
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}jbr                                              经办人
     * @apiSuccess {String}jdsj                                             接待时间
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsbhString                                       监所编号（已转换）
     * @apiSuccess {String}lfgzdw                                           主要来访人工作单位
     * @apiSuccess {String}lfnl                                             主要来访人年龄
     * @apiSuccess {String}lfrs                                             来访人数
     * @apiSuccess {String}lfrzw                                            来访人职务
     * @apiSuccess {String}lfxb                                             主要来访人性别
     * @apiSuccess {String}lfxbString                                       主要来访人性别（已转换）
     * @apiSuccess {String}lssj                                             离所时间
     * @apiSuccess {String}lxdh                                             联系电话
     * @apiSuccess {String}lxfs                                             联系方式
     * @apiSuccess {String}sldxm                                            领导姓名
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}stateString                                      状态（已转换）
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}zjh                                              证件号
     * @apiSuccess {String}zjlx                                             证件类型
     * @apiSuccess {String}zjlxString                                       证件类型（已转换）
     * @apiSuccess {String}zlfxm                                            主要来访人姓名
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
     *   "message": "查询成功",
     *   "result":
     *    {
     *     "total": 67,
     *     "data": [{
     *         "bz": "",
     *         "clqk": "",
     *         "createtime": "2020-01-13 15:06:54",
     *         "creator": "管理员",
     *         "dfyj": "",
     *         "djsj": "2020-01-13 15:06:52",
     *         "fywt": "",
     *         "id": "11000011420200113000063",
     *         "jbr": "gggg",
     *         "jdsj": "2020-01-13 15:06:52",
     *         "jsbh": "110000114",
     *         "jsbhString": "北京市第一看守所",
     *         "lfgzdw": "",
     *         "lfnl": "",
     *         "lfrs": 0,
     *         "lfrzw": "",
     *         "lfxb": "",
     *         "lfxbString": "",
     *         "lssj": "2020-01-13 15:05:03",
     *         "lxdh": "",
     *         "lxfs": "",
     *         "sldxm": "ss",
     *         "state": "R2",
     *         "stateString": "有效",
     *         "updatetime": "",
     *         "updator": "",
     *         "zjh": "320481199706187634",
     *         "zjlx": "11",
     *         "zjlxString": "身份证",
     *         "zlfxm": "sss"
     *   }],
     *   "page":"1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
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
     *          "sort":"id",
     *          "order":"desc",
     *          "page":"1",
     *          "rows":"10"
     *        }
     *
     *
     */

    @OpenAPI
    @ApiOperation("领导接访查询")
    @GetMapping("/ldjfList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> ldjfList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //{"pageSize":"10","pageIndex":"0","sort":"id","order":"desc","page":"1","rows":"10"}
        //id,jsbh,jsbhString,sldxm,jdsj,zlfxm,lfxb,lfxbString,lfnl,lfgzdw,lxfs,fywt,dfyj,clqk,bz,state,stateString,creator,createtime,updator,updatetime,zjlx,zjh,lfrzw,jbr,lssj,djsj,lfrs,lxdh
        String interfaceId = "/v4/kss/ldjf/ldjfList";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam qParam = new QueryParam();
            String state=(String) maps.getResult().get("state");
            if(StringUtils.isNullOrEmpty(state)){
                state="R2";
            }
            String jdmj = (String) maps.getResult().get("jdmj");
            String jfldxm = (String) maps.getResult().get("jfldxm");
            String ldjfrq_start = (String) maps.getResult().get("ldjfrq_start");
            String ldjfrq_end = (String) maps.getResult().get("ldjfrq_end");
            String lfrxm = (String) maps.getResult().get("lfrxm");
            String dfyjnull = (String) maps.getResult().get("dfyjnull");
            if(!StringUtils.isNullOrEmpty(jdmj)) {
                qParam.and("jbr", TermType.like,"%"+jdmj+"%");
            }
            if(!StringUtils.isNullOrEmpty(dfyjnull)) {
                qParam.and("dfyj", TermType.isnull,dfyjnull);
            }
            if(!StringUtils.isNullOrEmpty(lfrxm)) {
                qParam.and("zlfxm", TermType.like,"%"+lfrxm+"%");
            }
            if(!StringUtils.isNullOrEmpty(jfldxm)) {
                qParam.and("sldxm", TermType.like,"%"+jfldxm+"%");
            }
            if(!StringUtils.isNullOrEmpty(ldjfrq_start)) {
                qParam.and("jdsj",TermType.gte,ldjfrq_start+"00:00:00");
            }
            if(!StringUtils.isNullOrEmpty(ldjfrq_end)) {
                qParam.and("jdsj",TermType.lte,ldjfrq_end+"23:59:59");
            }
            DefaultQueryParam.addDefaultQueryParam(request,qParam,state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.ldjfQueryForPage(qParam);
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
     * @api {post} /v4/kss/ldjf/jjdlAdd 领导接访情况登记保存
     * @apiVersion 0.4.0
     * @apiName jjdlAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 领导接访情况登记保存.
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
     *					"sldxm":"接访领导人姓名(必填；最大长度:30)",
     *       			"zlfxm":"来访人姓名(必填；最大长度:30)",
     *       			"jdsj":"接访领导日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *       		    "zjh":"来访人身份证号码(必填；最大长度:18)",
     *       		    "lssj":"来访时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *       	        "jbr":"接待民警(必填；最大长度:30)",
     *       	        "fywt":"反映问题(必填;)",
     *                  "creator":"创建人(必填；最大长度:30)"
     *          		}]
     *     	  }
     */

    //{"sldxm":"\\S{1,30}","zlfxm":"\\S{1,30}","jdsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","zjh":"\\d{1,18}","lssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbr":"\\S{1,30}","creator":"\\S{1,30}"}
    //{"entity":[{"fywt":"dsad","sldxm":"aa","zlfxm":"gg","jdsj":"2019-10-10 10:10:10","zjh":"320481199706187634","lssj":"2019-10-10 10:10:10","jbr":"dsad","creator":"管理员"}]}

    @ApiOperation("领导接访情况登记保存")
    @PostMapping("/jjdlAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jjdlAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjf/jjdlAdd";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            String sldxm = (String) rslist.get(0).get("sldxm");
            String zlfxm = (String) rslist.get(0).get("zlfxm");
            String zjh = (String)rslist.get(0).get("zjh");
            String jbr =  (String)rslist.get(0).get("jbr");
            String fywt =  (String)rslist.get(0).get("fywt");
            String creator = (String) rslist.get(0).get("creator");
            if (StringUtils.isNullOrEmpty(fywt)){
                return ResponseMessage.error("fywt不能为空！");
            }
            SzjdjlModel model=JSONObject.parseObject(rslist.get(0).toString(),SzjdjlModel.class);
            model.setState("R2");
            model.setJbr(jbr);
            model.setZjh(zjh);
            model.setZlfxm(zlfxm);
            model.setSldxm(sldxm);
            model.setZjlx("11");
            model.setJsbh(jsbh);
            model.setCreator(creator);
            model.setCreatetime(new Date());
            model.setJdsj(new Date());
            model.setDjsj(new Date());
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            ResponseMessage<String> result = kssServerApis.jjdlAdd(model);
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
     * @api {post} /v4/kss/ldjf/lfjfupdate 来访接访保存
     * @apiVersion 0.4.0
     * @apiName lfjfupdate
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 来访接访保存.
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
     *					"id":"id(必填;最大长度:23)",
     * 	 *					"lfrs":"来访人数()",
     * 	 *       			"dfyj":"答复意见(必填;)",
     * 	 *       			"clqk":"处理情况()",
     * 	 *       			"updator":"更新人()"
     *          		}]
     *     	  }
     */

    //{"id":"\\d{1,23}"}
    //{"entity":[{"id":"11000011420200113000064","lfrs":"0","dfyj":"dsad","clqk":"","updator":"管理员"}]}
    @ApiOperation("来访接访保存")
    @PostMapping("/lfjfupdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> lfjfupdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjf/lfjfupdate";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            String id = (String) rslist.get(0).get("id");
            String updator = (String) rslist.get(0).get("updator");
            String dfyj = (String) rslist.get(0).get("dfyj");
            SzjdjlModel model=JSONObject.parseObject(rslist.get(0).toString(),SzjdjlModel.class);
            model.setUpdator(updator);
            model.setUpdatetime(new Date());
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(StringUtils.isNullOrEmpty(updator)){
                return ResponseMessage.error("updator不能为空！");
            }
            if(StringUtils.isNullOrEmpty(id)){
                return ResponseMessage.error("id不能为空！");
            }
            if(StringUtils.isNullOrEmpty(dfyj)){
                return ResponseMessage.error("dfyj不能为空！");
            }
            if(msg.getStatus()!=200) {
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            ResponseMessage<String> result = kssServerApis.jjdlUpdate(id,model);
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
     * @api {post} /v4/kss/ldjf/ldjfYwdt 领导来访业务动态查询
     * @apiVersion 0.4.0
     * @apiName ldjfYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 领导来访业务动态查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}clqk											    答复或已处理
     * @apiSuccess {String}zrs												总人数
     * @apiSuccess {String}lfrs												来所人数
     * @apiSuccess {String}lfcs												来访次数
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
     *  "message": "查询成功",,
     *  "result":
     *  {
     *     "total": 7,
     *     "data": [{
     *    "ldjfClqk": [
     *      {
     *        "clqk": 44
     *      }
     *    ],
     *    "ldjfZrs": [
     *      {
     *        "zrs": 10
     *      }
     *    ],
     *    "ldjfLfcs": [
     *      {
     *        "lfcs": 6,
     *        "lfrs": 10
     *      }
     *    ]
     *  }],
     *  "page":"1"
     *  },
     *  "status": 200,
     *  "timestamp": 1578898263859
     *}
     *
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
     *          "endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
     *          "dw":"单位(最大字段长度：50)"
     *        }
     *
     *
     */
    @OpenAPI
    @ApiOperation("领导来访业务动态查询")
    @PostMapping("/ldjfYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> ldjfYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
        //result
        //{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
        String interfaceId = "/v4/kss/ldjf/ldjfYwdt";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            String starttime=(String)maps.getResult().get("starttime");
            String endtime=(String)maps.getResult().get("endtime");
            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.ldjfYwdt(jsbh, starttime, endtime);
            System.err.println("--result--" + JSON.toJSONString(result));
            List lists=new ArrayList<>();
            lists.add(result);
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

}
