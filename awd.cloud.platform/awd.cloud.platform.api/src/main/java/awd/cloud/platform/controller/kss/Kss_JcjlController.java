package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.ZyryjljlModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
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
import java.util.*;

/**
 * Author：张延
 * Date：2020-01-16 9:32
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/jcjl")
@Api(tags = "kss-jcjl",description = "Jcjl")
public class Kss_JcjlController  extends PublicService {

    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private ProcessService processService;

    /**
     * @return
     * @api {get} /v4/kss/jcjl/jcjlYwdt 奖惩记录业务动态
     * @apiVersion 0.4.0
     * @apiName jcjlYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 奖惩记录业务动态
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}jlglYwdt                                          奖励管理业务动态
     * @apiSuccess {String}qtjl                                              其他奖励
     * @apiSuccess {String}wzjl                                              物质奖励
     * @apiSuccess {String}ldjjgzfz                                          劳动改造积极分子
     * @apiSuccess {String}zrs                                               总人数
     * @apiSuccess {String}lg                                                立功
     * @apiSuccess {String}jx                                                减刑
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
     *      {
     *         "result": {
     *           "jlglYwdt": [
     *             {
     *               "qtjl": 12,
     *               "wzjl": 15,
     *               "ldjjgzfz": 7,
     *               "zrs": 62,
     *               "lg": 13,
     *               "jx": 15
     *             }
     *           ]
     *         }
     *       }
     *     ],
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
     *      "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *      "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     * }
     */
    @OpenAPI
    @ApiOperation("奖惩记录业务动态")
    @GetMapping("/jcjlYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jcjlYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jcjl/jcjlYwdt";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }

            String starttime = (String) maps.getResult().get("starttime");
            String endtime = (String) maps.getResult().get("endtime");

            if (!StringUtils.isNullOrEmpty(starttime)) {
                param.and("starttime", TermType.eq, starttime);
            }
            if (!StringUtils.isNullOrEmpty(endtime)) {
                param.and("endtime", TermType.eq, endtime);
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.jcjlYwdt(jsbh, starttime, endtime);

            System.err.println("result" + JSON.toJSONString(result));

            List lists = new ArrayList<>();
            lists.add(result);

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().size());
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
     * @return
     * @api {get} /v4/kss/jcjl/zyryjljlYwtz 在押人员奖励记录业务台账查询
     * @apiVersion 0.4.0
     * @apiName zyryjljlYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 在押人员奖励记录业务台账查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}creator                                           创建人
     * @apiSuccess {String}zdzxm                                             中队长姓名
     * @apiSuccess {String}zdzyj                                              中队/警组意见内容
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
     *      {
     *         "creator": "管理员",
     *         "zdzxm": "管理员",
     *         "zdzyj": "中队/警组意见内容"
     *           ]
     *         }
     *       }
     *     ],
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
     *        "blrqstart":"办理开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *        "blrqend":"办理结束时间(格式:yyyy-MM-dd hh:mm:ss)",
     *        "xm":"姓名(最大字段长度：50)",
     *        "xm_type":"姓名种类",
     *        "bm":"别名(最大字段长度：50)",
     *        "bm_type":"别名种类",
     *        "xb":"性别(字典：XB;最大字段长度：50)",
     *        "xb_type":"性别种类",
     *        "jsh":"监室号(最大字段长度：4)",
     *        "rsrq_start":"入所开始日期(格式:yyyy-MM-dd hh:mm:ss)",
     *        "rsrq_end":"入所结束日期(格式:yyyy-MM-dd hh:mm:ss)",
     *        "csrq_start":"出所开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *        "csrq_end":"出所结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     * }
     */
    @OpenAPI
    @ApiOperation("在押人员奖励记录业务台账查询")
    @GetMapping("/zyryjljlYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zyryjljlYwtz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jcjl/zyryjljlYwtz";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

            QueryParam queryParam = new QueryParam();


       //     String jsh_type = request.getParameter("jsh_type");

            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                queryParam.and("zxsj", TermType.gte, maps.getResult().get("blrqstart")+ " 00:00:00");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                queryParam.and("zxsj", TermType.lte, maps.getResult().get("blrqend")+ " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                String xm = maps.getResult().get("xm").toString();
                if ("0".equals(maps.getResult().get("xm_type"))) {
                    xm = word2py(xm);
                    queryParam.and("jbxx_xmpy", TermType.like, "%"+xm+"%");
                } else {
                    queryParam.and("jbxx_xm", TermType.like, "%"+xm+"%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
                String bm = maps.getResult().get("bm").toString();
                if ("0".equals(maps.getResult().get("bm_type"))) {
                    bm = word2py(bm);
                    queryParam.and("jbxx_bmty", TermType.like, "%"+bm+"%");
                } else {
                    queryParam.and("jbxx_bm", TermType.like, "%"+bm+"%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
                String xb = maps.getResult().get("xb").toString();
                if ("0".equals(maps.getResult().get("xb_type"))) {
                    queryParam.and("jbxx_xb", TermType.eq, xb);
                } else {
                    queryParam.and("jbxx_xb", TermType.not, xb);
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
//			if ("0".equals(jsh_type)) {
//				queryParam.and("jbxx_jsh", TermType.eq, jsh);
//			} else {
//				queryParam.and("jbxx_jsh", TermType.not, jsh);
//			}
                String jsh = maps.getResult().get("jsh").toString();
                queryParam.and("jbxx_jsh", TermType.eq, jsh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
                queryParam.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq_start"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
                queryParam.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq_end"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
                queryParam.and("jbxx_csrq", TermType.gte, maps.getResult().get("csrq_start"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
                queryParam.and("jbxx_csrq", TermType.lte, maps.getResult().get("csrq_end"));
            }

            queryParam.and("state", TermType.eq, "R2");
            queryParam.and("jbxx_state", TermType.eq, "R8");
            queryParam.and("jsbh", TermType.eq, jsbh);
            queryParam.and("jbxx_jsbh", TermType.eq, jsbh);

            DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.zyryjljlYwtz(queryParam);
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



    /**
     * @api {post} /v4/kss/jcjl/jlcpAdd 奖惩呈批登记
     * @apiVersion 0.4.0
     * @apiName jlcpAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 奖惩呈批登记
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
     *   "jsbh":"监所编号(必填; 最大长度:9)",
     *   json{
     *        "entity":[{
     *              "creator":"创建人(必填; 最大长度:30)",
     * 	            "rybh":"人员编号(必填; 最大长度:21)"
     *        }]
     *   }
     *
     */
    //{"creator":".{1,30}","rybh":"\\d{1,21}"}
    @ApiOperation("奖惩呈批登记")
    @PostMapping("/jlcpAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jlcpAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/jcjl/jlcpAdd";
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



            ZyryjljlModel zyryjljlModel = JSONArray.parseArray(map.get("entity").toString(), ZyryjljlModel.class).get(0);

            ResponseMessage<String> s = processService.FlowMutex(jsbh, zyryjljlModel.getRybh(),
                    "KSS_JLGL", "ZYRYJLJL");
            System.err.println("消息=" + (s.getStatus()==200));
            if (!(s.getStatus()==200)) {
                return s;
            }
            String flowKey = CacheUtils.get().getFlowKey("KSS_JLGL");
            if ("".equals(flowKey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            zyryjljlModel.setJsbh(jsbh);
            zyryjljlModel.setState("R2");
            zyryjljlModel.setZxsj(new Date());
            System.err.println("张延+zyryjljlModel=="+JSON.toJSONString(zyryjljlModel));
            ResponseMessage<String> result = kssServerApis.addflow(flowKey, zyryjljlModel);

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
     * @api {post} /v4/kss/jcjl/ldspyjAdd 中队警署/所领导意见的保存操作
     * @apiVersion 0.4.0
     * @apiName ldspyjAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 中队警署/所领导意见的保存操作
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
     *       "type": "类别(必填)",
     * 		"taskid": "任务id(必填)",
     * 		"id": "ID（必填；最大字段长度:23)",
     * 		"ywlcid": "业务流程id(必填；最大字段长度:15)",
     * 		"zdzxm": "中队长姓名(最大字段长度:50)",
     * 		"zdzpssj": "中队长批示时间（格式:yyyy-MM-dd hh:mm:ss）",
     * 		"zdzpsbz": "中队长批示标志（必填；字典：PSBZ ；最大字段长度:1)",
     * 		"zdzyj": "中队长意见内容 (最大字段长度:200)",
     * 		"ldxm": "领导姓名(必填；最大字段长度:50)",
     * 		"ldpssj": "领导批示时间（格式:yyyy-MM-dd hh:mm:ss）",
     * 		"ldpsbz": "领导批示标志（必填；字典：PSBZ ；最大字段长度:1)",
     * 		"ldyj": "领导意见意见内容 (最大字段长度:200)",
     * 		"rybh": "人员编号（必填；最大字段长度:21)"
     *        }]
     *   }
     *
     */
    //{"id":"\\d{1,23}","rybh":"\\d{1,21}","ywlcid":".{1,15}","zdzxm":".{0,50}","zdzpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","zdzpsbz":"\\d{1,1}","zdzyj":".{0,200}","ldxm":".{1,50}","ldpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ldpsbz":"\\d{1,1}","ldyj":".{0,200}"}
    @ApiOperation("中队警署/所领导意见的保存操作")
    @PostMapping("/ldspyjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldspyjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/jcjl/ldspyjAdd";
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



            ZyryjljlModel zyryjljlModel = JSONArray.parseArray(map.get("entity").toString(), ZyryjljlModel.class).get(0);

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);

            zyryjljlModel.setJsbh(jsbh);
            System.err.println("张延+zyryjljlModel=="+JSON.toJSONString(zyryjljlModel));

            String taskid= (String) entityMap.get("taskid");

            if (StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid必填");
            }

            String type= (String) entityMap.get("type");
            if (StringUtils.isNullOrEmpty(type)) {
                return ResponseMessage.error("type必填");
            }

            ResponseMessage<String> result = kssServerApis.jlglSpFlow(zyryjljlModel, taskid, type);

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
     * @api {post} /v4/kss/jcjl/jlglDjFlow 奖励管理登记保存
     * @apiVersion 0.4.0
     * @apiName jlglDjFlow
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 奖励管理登记保存
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
     * 		"taskid":"任务id(必填)",
     * 		"id":"ID（必填；最大字段长度:23)",
     * 		"rybh": "人员编号（必填；最大字段长度:21)",
     * 		"updator":"跟新人(最大字段长度:50)",
     * 		"jbxxxm": "姓名(最大字段长度:50)"
     *
     *        }]
     *   }
     *
     */
    //{"id":"\\d{1,23}","rybh":"\\d{1,21}","updator":".{0,50}","jbxxxm":".{0,50}"}
    @ApiOperation("奖励管理登记保存")
    @PostMapping("/jlglDjFlow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jlglDjFlow(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/jcjl/jlglDjFlow";
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


            ZyryjljlModel zyryjljlModel = JSONArray.parseArray(map.get("entity").toString(), ZyryjljlModel.class).get(0);

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);

            zyryjljlModel.setJsbh(jsbh);

            String taskid= (String) entityMap.get("taskid");
            if (StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("taskid必填");
            }
            ResponseMessage<String> result = kssServerApis.jlglDjFlow(zyryjljlModel, taskid);
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
