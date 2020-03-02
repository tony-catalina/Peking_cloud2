package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.CrjjcModel;
import awd.bj.kss.model.LshjModel;
import awd.cloud.platform.api.KssServerApis;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/lshj")
@Api(tags = "kss-lshj", description = "lshj")
public class Kss_LshjController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private ProcessService processService;

    /**
     * @api {get} /v4/kss/lshj/jbxxForLshj 律师会见查询
     * @apiVersion 0.4.0
     * @apiName jbxxForLshj
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 律师会见查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}lsxb2String          				            律师1性别(已转换)
     * @apiSuccess {String}lsxbString          				                律师2性别(已转换)
     * @apiSuccess {String}lsxb          				                    律师1性别
     * @apiSuccess {String}lsxb2         				                    律师2性别
     * @apiSuccess {String}xb          				                        人员性别
     * @apiSuccess {String}hjsjString          				                会见开始时间
     * @apiSuccess {String}jssjString          				                会见结束时间
     * @apiSuccess {String}lsxm          				                    律师1姓名
     * @apiSuccess {String}xbString          				                人员性别(已转换)
     * @apiSuccess {String}xm          				                        人员姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}hjsy          				                    会见事由
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}lsxm2          				                    律师2姓名
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "lsxb2String": "男性",
     *         "lsxbString": "男性",
     *         "lsxb": "1",
     *         "lsxb2": "1",
     *         "xb": "1",
     *         "hjsjString": "2019-11-19 16:26:13",
     *         "jssjString": "2019-11-21 09:45:12",
     *         "lsxm": "律师姓名*\t",
     *         "xbString": "男性",
     *         "xm": "爱新觉罗冤人",
     *         "rybh": "110000114201910120002",
     *         "hjsy": "会见事由",
     *         "jsh": "0101",
     *         "lsxm2": "律师姓名2"
     *       }
     *     ],
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
     *   "jsh":"监室号",
     *   "xm":"人员姓名",
     *   "lsxm":"律师1姓名",
     *   "lsxm2":"律师2姓名",
     *   "zjh":"律师1证件号",
     *   "zjh2":"律师2证件号",
     *   "rsrq":"入所日期(格式:yyyy-MM-dd)",
     *   "hjsj_start":"会见时间开始(格式:yyyy-MM-dd)",
     *   "hjsj_end":"会见时间结束(格式:yyyy-MM-dd)",
     * }
     *
     */
    @ApiOperation("律师会见查询")
    @GetMapping("/jbxxForLshj")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jbxxForLshj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/lshj/jbxxForLshj";
        String state="R2";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam qParam = new QueryParam();

            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                qParam.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                qParam.and("jbxx_xm", TermType.like, "%" + maps.getResult().get("xm") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("lsxm"))) {
                qParam.and("lsxm", TermType.like, "%" + maps.getResult().get("lsxm") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("lsxm2"))) {
                qParam.and("lsxm2", TermType.like, "%" + maps.getResult().get("lsxm2") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
                qParam.and("zjh", TermType.not, maps.getResult().get("zjh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh2"))) {
                qParam.and("zjh2", TermType.not, maps.getResult().get("zjh2"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
                qParam.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
                qParam.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("hjsj_start"))) {
                qParam.and("hjsj", TermType.gte, maps.getResult().get("hjsj_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("hjsj_end"))) {
                qParam.and("hjsj", TermType.lte, maps.getResult().get("hjsj_end") + " 23:59:59");
            }


            qParam.and("jsbh", TermType.eq, jsbh);
            qParam.and("jbxx_jsbh", TermType.eq, jsbh);
            qParam.and("jbxx_state", TermType.eq, "R8");
            qParam.and("state", TermType.eq, "R2");
            qParam.and("pastable", TermType.eq, "1");

            DefaultQueryParam.addDefaultQueryParam(request,qParam, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.lshjQuery(qParam);
            System.err.println("--result--" + JSON.toJSONString(result));

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
     * @api {get} /v4/kss/lshj/lshjList 律师会见List查询
     * @apiVersion 0.4.0
     * @apiName lshjList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 律师会见List查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}lsxb2String          				            律师1性别(已转换)
     * @apiSuccess {String}lsxbString          				                律师2性别(已转换)
     * @apiSuccess {String}lsxb          				                    律师1性别
     * @apiSuccess {String}lsxb2         				                    律师2性别
     * @apiSuccess {String}xb          				                        人员性别
     * @apiSuccess {String}hjsjString          				                会见开始时间
     * @apiSuccess {String}jssjString          				                会见结束时间
     * @apiSuccess {String}lsxm          				                    律师1姓名
     * @apiSuccess {String}xbString          				                人员性别(已转换)
     * @apiSuccess {String}xm          				                        人员姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}hjsy          				                    会见事由
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}lsxm2          				                    律师2姓名
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "lsxb2String": "男性",
     *         "lsxbString": "男性",
     *         "lsxb": "1",
     *         "lsxb2": "1",
     *         "xb": "1",
     *         "hjsjString": "2019-11-19 16:26:13",
     *         "jssjString": "2019-11-21 09:45:12",
     *         "lsxm": "律师姓名*\t",
     *         "xbString": "男性",
     *         "xm": "爱新觉罗冤人",
     *         "rybh": "110000114201910120002",
     *         "hjsy": "会见事由",
     *         "jsh": "0101",
     *         "lsxm2": "律师姓名2"
     *       }
     *     ],
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
     *   "lsxm":"律师1姓名",
     *   "dw":"单位",
     *   "zjh":"律师1证件号",
     *   "startTime":"会见时间开始(格式:yyyy-MM-dd)",
     *   "endTime":"会见时间结束(格式:yyyy-MM-dd)",
     * }
     *
     */
    @OpenAPI
    @ApiOperation("律师会见List查询")
    @GetMapping("/lshjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> lshjList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/lshj/lshjList";
        String state = "R2";

        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam qParam = new QueryParam();


            if (!StringUtils.isNullOrEmpty(maps.getResult().get("lsxm"))) {
                qParam.and("lsxm", TermType.like, "%"+maps.getResult().get("lsxm") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
                qParam.and("zjh", TermType.like, "%" + maps.getResult().get("zjh") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dw"))) {
                qParam.and("dw", TermType.eq, maps.getResult().get("dw") );
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("startTime"))) {
                qParam.and("hjsj", TermType.gte, maps.getResult().get("startTime")+" 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))) {
                qParam.and("hjsj", TermType.lte, maps.getResult().get("endTime")+" 23:59:59");
            }

            System.err.println("律师违规查询"+ JSONUtil.toJson(qParam));
            DefaultQueryParam.addDefaultQueryParam(request,qParam, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.lshjQuery(qParam);
            System.err.println("--result--" + JSON.toJSONString(result));

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
     * @api {post} /v4/kss/lshj/LshjHdpz 核对凭证保存
     * @apiVersion 0.4.0
     * @apiName LshjHdpz
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 核对凭证保存
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
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json{
     *   "entity":[{
     *      "rybh":"人员编号(必填; 最大长度:21)",
     * 	    "jyr": "校验人(必填; 最大长度:255)",
     * 	    "jysj": "校验时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	    "creator": "创建人(必填; 最大长度:50)"
     *   }]
     * }
     *
     */
    @ApiOperation("核对凭证保存")
    @PostMapping("/LshjHdpz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addLshjHdpz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/lshj/LshjHdpz";
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

            LshjModel model = JSONArray.parseArray(map.get("entity").toString(), LshjModel.class).get(0);

            String lcid = CacheUtils.get().getFlowKey("_kss_lshj".toUpperCase());
            System.err.println("lcid==" + lcid);
            String lshjKey = "kss_lshj".toUpperCase();
            String rybh = model.getRybh();

            if (processService.FlowMutex(jsbh, rybh, lshjKey, "LSHJ").getStatus() != 200) {
                return processService.FlowMutex(jsbh, rybh, lshjKey, "LSHJ");
            }
            model.setJsbh(jsbh);
            model.setPastable("1");
            model.setCreatetime(new Date());
            System.err.println("model==" + model);

            ResponseMessage<String> result = kssServerApis.addLshjHdpz(lcid, model);
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
     * @api {get} /v4/kss/lshj/hjsIsNull 会见室是否为空
     * @apiVersion 0.4.0
     * @apiName hjsIsNull
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 会见室是否为空
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}LSXM2          				                    律师2姓名
     * @apiSuccess {String}HJS          				                    会见室
     * @apiSuccess {String}HJSY          				                    会见事由
     * @apiSuccess {String}RYBH         				                    人员编号
     * @apiSuccess {String}HJSJSTRING          				                会见时间
     * @apiSuccess {String}JSBH          				                    监所编号
     * @apiSuccess {String}LSXM          				                    律师1姓名
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
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "LSXM2": "律师姓名2",
     *         "HJS": "1",
     *         "HJSY": "会见事由",
     *         "RYBH": "110000114201912040018",
     *         "HJSJSTRING": "2020-01-22 15:21:05",
     *         "JSBH": "110000114",
     *         "LSXM": "律师姓名1"
     *       }
     *     ],
     *     "page": "1"
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
     *          "hjs":"会见室(必填)",
     *        }
     *
     */
    @ApiOperation("会见室是否为空")
    @GetMapping("/hjsIsNull")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> hjsIsNull(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/lshj/hjsIsNull";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            System.err.println("maps--" + JSON.toJSONString(maps));

            if(StringUtils.isNullOrEmpty(maps.getResult().get("hjs"))){
                return ResponseMessage.error("hjs不可为空");
            }
            String hjs = maps.getResult().get("hjs").toString();

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.hjsIsNull(hjs, jsbh);
            System.err.println("--result--" + JSON.toJSONString(result));

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
     * @api {post} /v4/kss/lshj/hjdjadd 会见登记保存
     * @apiVersion 0.4.0
     * @apiName hjdjadd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 会见登记保存
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
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "taskid":"流程id(必填)",
     *   "entity":[{
     *      "lsxm": "律师姓名(必填; 最大长度:30)",
     * 	    "lsxb": "律师性别(必填; 字典:XB; 长度:1)",
     * 	    "zjlx": "证件类型(必填; 字典:zjlx; 长度:2)",
     * 	    "zjh": "证件号(必填; 最大长度:45)",
     * 	    "hjs": "会见室(必填; 最大长度:30)",
     * 	    "lszgzh": "律师资格证号(最大长度:255)",
     * 	    "jsxbh": "介绍信编号(最大长度:255)",
     * 	    "dw": "单位(最大长度:60)",
     * 	    "hjpzjg": "会见批准机关(最大长度:30)",
     * 	    "dh": "律师电话(最大长度:255)",
     * 	    "sj": "律师手机(最大长度:255)",
     * 	    "rybh": "人员编号(必填; 最大长度:21)",
     * 	    "ywlcid": "业务流程id(必填; 最大长度:21)"
     *     }]
     *   }
     * }
     *
     */
    @ApiOperation("会见登记保存")
    @PostMapping("/hjdjadd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addLshjDjcb(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/lshj/hjdjadd";
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

            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = maps.getResult().get("taskid").toString();

            LshjModel model = JSONArray.parseArray(map.get("entity").toString(), LshjModel.class).get(0);

            model.setState("R2");
            model.setJsbh(jsbh);
            model.setUpdatetime(new Date());
            System.out.println("model==" + JSON.toJSONString(model));

            ResponseMessage<String> result = kssServerApis.addLshjDjcb(taskid, model);
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
     * @api {post} /v4/kss/lshj/jqtrdjAdd 监区提人登记保存
     * @apiVersion 0.4.0
     * @apiName jqtrdjAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监区提人登记保存
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
     * appcode:"应用代码(必填)",
     * jsbh":监所编号(必填; 最大长度:9)",
     * json{
     *   "taskid":"流程id(必填)",
     *   "entity":[{
     *      "creator":"创建人(必填; 最大长度:50)",
     * 	    "rybh":"人员编号(必填; 最大长度:21)",
     * 	    "dlmj":"带领民警(必填; 最大长度:100)",
     * 	    "blsj":"办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	    "ywlcid":"业务流程ID(必填; 最大长度:21)"
     *   }]
     * }
     *
     *
     */
    @ApiOperation("监区提人登记保存")
    @PostMapping("/jqtrdjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> addLshjCrjFlow(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        //{"creator":"\\S{1,50}","rybh":"\\d{1,21}","dlmj":".{1,100}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ywlcid":"\\d{1,21}"}
        String interfaceId = "/v4/kss/lshj/jqtrdjAdd";
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

            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = maps.getResult().get("taskid").toString();

            CrjjcModel model = JSONArray.parseArray(map.get("entity").toString(), CrjjcModel.class).get(0);
            System.err.println("model------" + JSON.toJSONString(model));

            model.setJsbh(jsbh);
            model.setCreatetime(new Date());
            model.setState("R2");
            model.setBllx("1");

            ResponseMessage<String> result = kssServerApis.addLshjCrjFlow("1", taskid, model);
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
     * @api {post} /v4/kss/lshj/hjaqjcAdd 会见安全检查保存
     * @apiVersion 0.4.0
     * @apiName hjaqjcAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 会见安全检查保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
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
     * {
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大长度:9)",
     *   json{
     *   "taskid":"流程id(必填)",
     *   "entity":[
     *        {
     *           "creator": "创建人(必填; 最大长度:50)",
     * 	         "rybh":"人员编号(必填; 最大长度:21)",
     * 	         "dlmj":"带领民警(必填; 最大长度:100)",
     * 	         "blsj":"办理时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 	         "ywqxyc": "有无异常(必填;字典：YWYC；最大长度:1)",
     * 	         "qxycqk": "情绪异常情况",
     * 	         "ywlcid":"业务流程ID(必填; 最大长度:21)"
     *        }
     *     ]
     *   }
     *
     *
     */
    @ApiOperation("会见安全检查保存")
    @PostMapping("/hjaqjcAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> aqjc(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        //{"creator":"\\S{1,50}","rybh":"\\d{1,21}","dlmj":".{1,100}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ywlcid":"\\d{1,21}","ywqxyc":"\\d{1,1}"}
        String interfaceId = "/v4/kss/lshj/hjaqjcAdd";
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

            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = maps.getResult().get("taskid").toString();

            CrjjcModel model = JSONArray.parseArray(map.get("entity").toString(), CrjjcModel.class).get(0);

            model.setJsbh(jsbh);
            model.setCreatetime(new Date());
            model.setState("R2");
            model.setBllx("2");
            model.setUpdatetime(new Date());
            model.setBlsj(new Date());
            ResponseMessage<String> result = kssServerApis.addLshjCrjFlow("2", taskid, model);
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
