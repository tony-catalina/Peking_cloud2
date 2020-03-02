package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.*;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.druid.support.json.JSONUtils;
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
 * Date：2020-01-16 16:06
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/cfgl")
@Api(tags = "kss-cfgl",description = "Cfgl")
public class Kss_CfglController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    @Autowired
    private ProcessService processService;


    /**
     *
     * @api {get} /v4/kss/cfgl/cfglYwdt 处罚管理业务动态
     * @apiVersion 0.4.0
     * @apiName cfglYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 处罚管理业务动态
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     * @apiSuccess {String}cfglYwdt                                          处罚管理业务动态
     * @apiSuccess {String}xj                                                训诫
     * @apiSuccess {String}zljjhg                                            责令具结悔过
     * @apiSuccess {String}qtcf                                              其他惩罚
     * @apiSuccess {String}jb                                                紧闭
     * @apiSuccess {String}zrs                                               总人数
     * @apiSuccess {String}jg                                                警告
     * @apiSuccess {String}jx                                                加刑
     * @apiSuccess {String}yg                                                严管
     *
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
     *       "result": {
     *         "cfglYwdt": [{
     *            "xj": 10,
     *            "zljjhg": 11,
     *            "qtcf": 6,
     *            "jb": 9,
     *            "zrs": 58,
     *            "jg": 3,
     *            "jx": 6,
     *            "yg": 13
     *         }]
     *       }
     *     }],
     *   "page": "1"
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
    @ApiOperation("处罚管理业务动态")
    @GetMapping("/cfglYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> cfglYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/cfgl/cfglYwdt";
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

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.cfglYwdt(jsbh, starttime, endtime);

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
     *
     * @api {get} /v4/kss/cfgl/zyrycfjlYwtz 在押人员处罚记录业务台账查询
     * @apiVersion 0.4.0
     * @apiName zyryjljlYwtz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 在押人员处罚记录业务台账查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     * @apiSuccess {String}id                                                ID
     * @apiSuccess {String}state                                             状态
     * @apiSuccess {String}jsh                                               监室号
     * @apiSuccess {String}jsbh                                              监所编号
     *
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
     *       "id": "11000011420190909000031",
     *       "state": "R2",
     *       "jsh": "0107",
     *       "jsbh": "110000114"
     *     }]
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
     *   "blrqstart":"办理开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *   "blrqend":"办理结束时间(格式:yyyy-MM-dd hh:mm:ss)",
     *   "xm":"姓名(最大字段长度：50)",
     *   "xm_type":"姓名搜索类型",
     *   "bm":"别名(最大字段长度：50)",
     *   "bm_type":"别名搜索类型",
     *   "xb":"性别(字典：XB;最大字段长度：50)",
     *   "xb_type":"性别搜索类型",
     *   "jsh":"监室号(最大字段长度：4)",
     *   "rsrq_start":"入所开始日期(格式:yyyy-MM-dd hh:mm:ss)",
     *   "rsrq_end":"入所结束日期(格式:yyyy-MM-dd hh:mm:ss)",
     *   "csrq_start":"出所开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *   "csrq_end":"出所结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     * }
     */
    @OpenAPI
    @ApiOperation("在押人员处罚记录业务台账查询")
    @GetMapping("/zyrycfjlYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zyrycfjlYwtz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/cfgl/zyrycfjlYwtz";
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
            // String jsh_type = request.getParameter("jsh_type");
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
                queryParam.and("cfksrq", TermType.gte, maps.getResult().get("blrqstart") + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
                queryParam.and("cfksrq", TermType.lte, maps.getResult().get("blrqend") + " 23:59:59");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                String xm = maps.getResult().get("xm").toString();
                if ("0".equals(maps.getResult().get("xm_type"))) {
                    xm = word2py(xm);
                    queryParam.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
                } else {
                    queryParam.and("jbxx_xm", TermType.like, "%" + xm + "%");
                }
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
                String bm = maps.getResult().get("bm").toString();
                if ("0".equals(maps.getResult().get("bm_type"))) {
                    bm = word2py(bm);
                    queryParam.and("jbxx_bmty", TermType.like, "%" + bm + "%");
                } else {
                    queryParam.and("jbxx_bm", TermType.like, "%" + bm + "%");
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
                queryParam.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
                queryParam.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq_end"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
                queryParam.and("jbxx_csrq", TermType.gte, maps.getResult().get("csrq_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
                queryParam.and("jbxx_csrq", TermType.lte, maps.getResult().get("csrq_end"));
            }

            queryParam.and("state", TermType.eq, "R2");
            queryParam.and("jbxx_state", TermType.eq, "R8");
            queryParam.and("jsbh", TermType.eq, jsbh);
            queryParam.and("jbxx_jsbh", TermType.eq, jsbh);

            DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.zyrycfjlYwtz(queryParam);
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
     *
     * @api {post} /v4/kss/cfgl/cfcpAdd 惩罚管理的惩罚呈报保存
     * @apiVersion 0.4.0
     * @apiName cfcpAdd
     * @apiGroup g_kss
     * @apiPermission user
     * @apiDescription 惩罚管理的惩罚呈报保存
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
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度:9)",
     * json{
     * "entity":[{
     *    "entity":[{
     * 		"fjcs":[{
     * 			"cfyy":"惩罚原因(最大字段长度:255)"
     *                }, {
     * 			 "cfyy":"惩罚原因(最大字段长度:255)"
     *        }, {
     * 			 "cfyy":"惩罚原因(最大字段长度:255)"
     *        }],
     * 		"jsh_jjx":"监室号(必填；最大字段长度:4)",
     * 		"rybh":"人员编号(必填；最大字段长度:21)",
     * 		"snbh_jjx":"所内编号(必填；最大字段长度:8)",
     * 		"xm_jjx":"姓名(必填；最大字段长度:50)",
     * 		"creator": "创建人(必填；最大字段长度:50)",
     * 		"createtime": "创建时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"cfzl": "处罚种类(必填；字典：CFXS；最大字段长度:50)",
     * 		"sjms": "事件描述",
     * 		"cfly": "处罚理由(必填；最大字段长度:300)",
     * 		"cfksrq": "处罚开始日期(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"cfjsrq": "处罚结束日期(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"jbsj": "经办时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"jbr": "经办人(必填；最大字段长度:255)",
     * 		"cfts": "处罚天数(必填；最大字段长度:2)"
     * }]
     * }
     *
     */
    @ApiOperation("惩罚管理的惩罚呈报保存")
    @PostMapping("/cfcpAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> cfcpAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/cfgl/cfcpAdd";
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

            ZyrycfjlModel zyrycfjlModel = JSONArray.parseArray(map.get("entity").toString(), ZyrycfjlModel.class).get(0);
            JbxxModel jbxxModel = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class).get(0);
            JjxModel jjxEntity = JSONArray.parseArray(map.get("entity").toString(), JjxModel.class).get(0);
                List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
                Map<String, Object> entityMap = mapsList.get(0);

                String fjcs = JSONUtils.toJSONString( entityMap.get("fjcs"));
                System.out.println("12312312323123123"+fjcs);
                String jsh = (String) entityMap.get("jsh_jjx");
                String rybh = (String) entityMap.get("rybh");
                String snbh =(String) entityMap.get("snbh_jjx");
            String xm = (String) entityMap.get("xm_jjx");


            List<ZyrycfjlFjcsModel> fjcslist = new ArrayList<ZyrycfjlFjcsModel>();
            if (!StringUtils.isNullOrEmpty(fjcs) && !"[]".equals(fjcs)) {
                try {
                    fjcslist = JSONArray.parseArray(fjcs, ZyrycfjlFjcsModel.class);
                }catch (Exception e){
                    e.printStackTrace();
                    return ResponseMessage.error("格式不正确；正确格式:[]");
                }
            } else {
                return ResponseMessage.error("请输入fics");
            }
            zyrycfjlModel.setJsbh(jsbh);
            zyrycfjlModel.setState("R2");
            ResponseMessage<String> s = processService.FlowMutex(jsbh, rybh,
                    jsbh + "KSS_CFSZ", "zyrycfjl");
            System.err.println("消息=" + s.getStatus() == "200");
            if (s.getStatus() != 200) {
                return s;
            }
            String flowKey = CacheUtils.get().getFlowKey("KSS_CFSZ");
            if ("".equals(flowKey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            if ("55".equals(zyrycfjlModel.getCfzl())) {
                Map<String, Object> jbxxmap = new HashMap();
                jbxxModel.setXm(xm);
                jbxxModel.setJsh(jsh);
                jbxxModel.setRybh(rybh);
                jbxxModel.setSnbh(snbh);
                jjxEntity.setJsbh(zyrycfjlModel.getJsbh());
                jjxEntity.setRybh(zyrycfjlModel.getRybh());
                jjxEntity.setFlag("01");
                jjxEntity.setYxmrq(new Date());
                jjxEntity.setXmsfrq(zyrycfjlModel.getCfjsrq());
                jjxEntity.setJjxq(zyrycfjlModel.getCfts().toString());
                jjxEntity.setPsbz("0");
                jjxEntity.setState("R2");
                jjxEntity.setCreatetime(new Date());
                jjxEntity.setCreator(zyrycfjlModel.getCreator());
                jjxEntity.setPastable("1");
                jjxEntity.setBlrq(new Date());
                jbxxmap.put("lcid", CacheUtils.get().getFlowKey("KSS_JJX"));
                jbxxmap.put("jbxxEntity", jbxxModel);
                jbxxmap.put("jjxEntity", jjxEntity);
                kssServerApis.JJXaddFlow(jbxxmap);
            }
            Map<String, Object> tomap = new HashMap<String, Object>();
            tomap.put("processDefinitionId", flowKey);
            tomap.put("zyrycfjlModel", zyrycfjlModel);
            tomap.put("fjcslist", fjcslist);
            tomap.put("jsh", jsh);
            System.out.println("++++++++++++" + JSONUtil.toJson(tomap));
            ResponseMessage<String> result = kssServerApis.cfglAddflow(tomap);

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
     * @api {post} /v4/kss/cfgl/cfycAdd 延长呈报
     * @apiVersion 0.4.0
     * @apiName cfycAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 延长呈报
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
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度:9)",
     * json{
     *   "entity":[{
     * 		"ywlcid": "业务流程ID(必填；最大字段长度:15)",
     * 		"rybh": "人员编号(必填；最大字段长度:21)",
     * 		"taskid": "任务ID",
     * 		"ycjbr": "延长经办人(必填；最大字段长度:50)",
     * 		"jbxxXm": "姓名(必填；最大字段长度:50)"
     *        }]
     *   }
     *
     */
    @ApiOperation("延长呈报")
    @PostMapping("/cfycAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> cfycAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/cfgl/cfycAdd";
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


            ZyrycfjlModel zyrycfjlModel = JSONArray.parseArray(map.get("entity").toString(), ZyrycfjlModel.class).get(0);

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);

            zyrycfjlModel.setJsbh(jsbh);
             String taskid= (String) entityMap.get("taskid");

            if (StringUtils.isNullOrEmpty(taskid)) {
                return ResponseMessage.error("请输入taskid");
            }

            ResponseMessage<String> result = kssServerApis.cfglYcflow(zyrycfjlModel, taskid);
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
     * @api {post} /v4/kss/cfgl/cfglSpAdd 处罚管理审批
     * @apiVersion 0.4.0
     * @apiName cfglSpAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 处罚管理审批
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
     * appcode":"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度:9)",
     * json{
     *   "entity":[{
     * 	    "ywlcid": "业务流程ID(必填；最大字段长度:15)",
     * 		"rybh": "人员编号(必填；最大字段长度:21)",
     * 		"taskid": "任务ID",
     * 		"jbxxxm": "姓名(必填；最大字段长度:50)"
     * 		"type": "种类(必填：1,2,3)",
     * 		"zdzpsbz": "中队长批示标志(字典：PSBZ ；最大字段长度:1 )",
     * 		"ldpsbz": "领导批示标志(字典：PSBZ ；最大字段长度:1 )",
     * 		"ycpsbz": "延长批示标志(字典：PSBZ ；最大字段长度:1 )",
     * 		"id": "ID(必填；最大字段长度:23)",
     * 		"cfly": "处罚理由(最大字段长度:300)",
     * 		"ycly": "延长理由",
     * 		"jbsj": "经办时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"zdzyj": "中队长意见内容(必填；最大字段长度:200)",
     * 		"zdzxm": "中队长姓名(最大字段长度:50)",
     * 		"zdzpssj": "中队长批示时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"ldyj": "领导意见意见内容(必填；最大字段长度:200)",
     * 		"ldxm": "领导姓名(最大字段长度:50)",
     * 		"ldpssj": "领导批示时间(格式:yyyy-MM-dd hh:mm:ss)",
     * 		"ycspyj": "延长审批意见",
     * 		"ycspr": "延长审批人(最大字段长度:50)",
     * 		"ycspsj": "延长审批时间(格式:yyyy-MM-dd hh:mm:ss)"
     *        }]
     *   }
     *
     */
    @ApiOperation("处罚管理审批")
    @PostMapping("/cfglSpAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> cfglSpAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/cfgl/cfglSpAdd";
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


            ZyrycfjlModel zyrycfjlModel = JSONArray.parseArray(map.get("entity").toString(), ZyrycfjlModel.class).get(0);
            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            Map<String,Object> entityMap = mapsList.get(0);


                String type= entityMap.get("type").toString();
                String taskid=entityMap.get("taskid").toString();


            if (StringUtils.isNullOrEmpty(type)) {
                return ResponseMessage.error("请输入type");
            }
            if (type.equals("1")) {
                Integer  zdzpsbz= Integer.parseInt(entityMap.get("zdzpsbz").toString());
                if (StringUtils.isNullOrEmpty(zdzpsbz)) {
                    return ResponseMessage.error("请输入zdzpsbz");
                }
                if (zdzpsbz < 0 || zdzpsbz > 2) {
                    return ResponseMessage.error("zdzpsbz的值必须是0，1，2");
                }
            } else if (type.equals("2")) {
                Integer  ldpsbz= Integer.parseInt(entityMap.get("ldpsbz").toString());
                if (StringUtils.isNullOrEmpty(ldpsbz)) {
                    return ResponseMessage.error("请输入ldpsbz");
                }
                if (ldpsbz < 0 || ldpsbz > 2) {
                    return ResponseMessage.error("ldpsbz的值必须是0，1，2");
                }
            } else if (type.equals("3")) {
                Integer  ycpsbz= Integer.parseInt(entityMap.get("ycpsbz").toString());
                if (StringUtils.isNullOrEmpty(ycpsbz)) {
                    return ResponseMessage.error("请输入ycpsbz");
                }
                if (ycpsbz < 0 || ycpsbz > 2) {
                    return ResponseMessage.error("ycpsbz的值必须是0，1，2");
                }
            } else {
                return ResponseMessage.error("type的值必须是1，2，3");
            }

                if (StringUtils.isNullOrEmpty(type)) {
                    return ResponseMessage.error("请输入type");
                 }

                zyrycfjlModel.setJsbh(jsbh);

            ResponseMessage<String> result = kssServerApis.cfglSpflow(zyrycfjlModel, taskid, type);

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
     * @api {post} /v4/kss/cfgl/cfjcAdd 解除处罚登记
     * @apiVersion 0.4.0
     * @apiName cfjcAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 解除处罚登记
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
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度:9)",
     * json{
     *   "entity":[{
     * 		"ywlcid": "业务流程Id(必填；最大字段长度:15)",
     * 		"rybh": "人员编号(必填；最大字段长度:21)",
     * 		"jcr": "解除人(最大字段长度:50)",
     * 		"jbxxxm": "姓名(最大字段长度:50)"
     *
     *        }]
     *   }
     *
     */
    @ApiOperation("解除处罚登记")
    @PostMapping("/cfjcAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> cfjcAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/cfgl/cfjcAdd";
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


            ZyrycfjlModel zyrycfjlModel = JSONArray.parseArray(map.get("entity").toString(), ZyrycfjlModel.class).get(0);

            zyrycfjlModel.setJsbh(jsbh);

            String ywlcid=zyrycfjlModel.getYwlcid();

           ResponseMessage<String> result = kssServerApis.cfglJcflow(zyrycfjlModel, ywlcid);

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



