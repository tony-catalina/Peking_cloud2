package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.*;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/ldjc")
@Api(tags = "kss-ldjc", description = "ldjc")
public class Kss_LdjcController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/kss/ldjc/xggzyqSave
     * @apiVersion 0.4.0
     * @apiName xggzyqSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 相关工作要求保存
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[{
     * "jqh":"监区号（必填; 最大字段长度：4）",
     * "yqqk":"要求情况(必填; )",
     * "fkr":"反馈人(必填; 最大字段长度：20)",
     * "fksj":"反馈时间(必填; 格式(2019-10-10 10:10:10))",
     * "yqnr":"要求内容(必填; )"
     * "yqfk":"要求反馈(必填; )"
     * }]
     * }
     */
    @ApiOperation("相关工作要求保存")
    @PostMapping("xggzyqSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xggzyqSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/ldjc/xggzyqSave";

        //通过校验获取查询参数
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
            List<XggzyqModel> xggzyqModel = JSONArray.parseArray(map.get("entity").toString(), XggzyqModel.class);
            xggzyqModel.get(0).setState("R2");
            xggzyqModel.get(0).setJsbh(jsbh);
            xggzyqModel.get(0).setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.saveXggzyq(xggzyqModel.get(0));
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
     * @api {get} /v4/kss/ldjc/xggzyqQuery 相关工作要求查询
     * @apiVersion 0.4.0
     * @apiName xggzyqQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 相关工作要求查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} yqqk:                       工作要求情况
     * @apiSuccess {String} yqnr:                       要求内容
     * @apiSuccess {String} yqfk:                       是否要求反馈
     * @apiSuccess {String} fkr:                        反馈人
     * @apiSuccess {String} fksj         				反馈时间
     * @apiSuccess {String} fkqk:                       反馈情况
     * @apiSuccess {String} fkzt:                       反馈状态
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "sfzjString": "null",
     * "jqh": "0403",
     * "yqqk": "aaa",
     * "yqnr": "aaa",
     * "yqfk": "1",
     * "fkr": "奥奥",
     * }],
     * "page": 1
     * },
     * "status": 200,
     * "timestamp": 1576308123984
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * * appcode:"应用代码（必填）",
     * * jsbh:"监所编号(必填; 最大字段长度：9)",
     * * json:{
     * * "jcsj_start":"2020-2-19 15:20:30",
     * * "jcsj_end":"2020-2-19 15:20:30",
     * * "czr":"奥奥",
     * *
     * * }
     */
    @OpenAPI
    @ApiOperation("相关工作要求查询")
    @GetMapping("/xggzyqQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xggzyqQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjc/xggzyqQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_start"))) {
            param.and("jcsj", TermType.gte, maps.getResult().get("jcsj_start"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_end"))) {
            param.and("jcsj", TermType.lte, maps.getResult().get("jcsj_end").toString() + 1);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("czr"))) {
            param.and("creator", TermType.like, '%' + maps.getResult().get("czr").toString() + "%");
        }
        param.and("jsbh", TermType.like, '%' + jsbh + "%");
        ResponseMessage<PagerResult<XggzyqModel>> result = kssServerApis.queryForPageXggzyq(param);
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
    }


    /**
     * @api {post} /v4/kss/ldjc/mjzfSave
     * @apiVersion 0.4.0
     * @apiName mjzfSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 民警执法保存
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[{
     * "jcnr":"检查内容（必填; 最大字段长度：255）",
     * "sjmj":"涉及民警(必填; 最大字段长度：100)",
     * "jcqk":"检查情况(必填; 最大字段长度：255)",
     * "dd":"地点(必填; 最大字段长度：100)",
     * "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))"
     * "jssj":"结束时间(必填; 格式(2019-10-10 10:10:10))"
     * "nrkh":"是否纳入考核(必填)"
     * "kl":"是否刻录(必填)"
     * }]
     * }
     */
    @ApiOperation("民警执法保存")
    @PostMapping("mjzfSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> mjzfSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/ldjc/mjzfSave";

        //通过校验获取查询参数
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
            List<MjzfModel> mjzfModel = JSONArray.parseArray(map.get("entity").toString(), MjzfModel.class);
            mjzfModel.get(0).setState("R2");
            mjzfModel.get(0).setJsbh(jsbh);
            mjzfModel.get(0).setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.saveMjzf(mjzfModel.get(0));
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
     * @api {get} /v4/kss/ldjc/mjzfQuery 民警执法查询
     * @apiVersion 0.4.0
     * @apiName mjzfQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 民警执法查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} sfzjString         			是否在监(已转换)
     * @apiSuccess {String} xm         				    姓名
     * @apiSuccess {String} sfzj         				是否在监字典
     * @apiSuccess {String} rybh         				人员编号
     * @apiSuccess {String} wdmyy         				未点名原因
     * @apiSuccess {String} mjqr         				民警确认
     * @apiSuccess {String} jsh         				监室号
     * @apiSuccess {String} dmsj         				点名时间
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "sjmj": "奥奥",
     * "jcnr": "aaaaa",
     * "jcqk": "aaaaa",
     * "nrkhString": "02",
     * "klString": "01",
     * }],
     * "page": 1
     * },
     * "status": 200,
     * "timestamp": 1576308123984
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     * {
     * "kssj_start": "2020-01-29",
     * "kssj_end": "2020-02-10",
     * "czr$like": "a'aaa"
     * }
     */
    @OpenAPI
    @ApiOperation("民警执法查询")
    @GetMapping("/mjzfQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> mjzfQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjc/mjzfQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_start"))) {
            param.and("jcsj", TermType.gte, maps.getResult().get("jcsj_start"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_end"))) {
            param.and("jcsj", TermType.lte, maps.getResult().get("jcsj_end").toString() + 1);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("czr"))) {
            param.and("creator", TermType.like, '%' + maps.getResult().get("czr").toString() + "%");
        }
        param.and("jsbh", TermType.like, '%' + jsbh + "%");
        ResponseMessage<PagerResult<MjzfModel>> result = kssServerApis.queryForPageMjzf(param);
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
    }


    /**
     * @api {post} /v4/kss/ldjc/jsnwSave
     * @apiVersion 0.4.0
     * @apiName jsnwSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监内务保存
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[{
     * "jsh":"监室号(必填; 最大字段长度：4)",
     * "jcsj":"检查时间(必填; 格式(2019-10-10 10:10:10))",
     * "jcnr":"检查内容(必填; 最大字段长度：255)",
     * "ztpj":"总体评价(必填;)",
     * "bz":"备注"
     * }]
     * }
     */
    @ApiOperation("监室内务保存")
    @PostMapping("jsnwSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jsnwSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/ldjc/jsnwSave";

        //通过校验获取查询参数
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
            List<JsnwModel> jsnwModel = JSONArray.parseArray(map.get("entity").toString(), JsnwModel.class);
            jsnwModel.get(0).setState("R2");
            jsnwModel.get(0).setJsbh(jsbh);
            jsnwModel.get(0).setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.saveJsnw(jsnwModel.get(0));
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
     * @api {get} /v4/kss/ldjc/jsnwQuery 监室内务查询
     * @apiVersion 0.4.0
     * @apiName jsnwQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监室内务查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} jcsj:                       检查时间
     * @apiSuccess {String} jcnr:                       检查内容
     * @apiSuccess {String} ztpj:                       总体评价
     * @apiSuccess {String} bz:                         备注
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "jsh": "0403",
     * "jcsj": "2020-01-29 18:36:20",
     * "jcnr": "01",
     * "ztpj":"aaaa"
     * }],
     * "page": 1
     * },
     * "status": 200,
     * "timestamp": 1576308123984
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     * {
     * "kssj_start": "2020-01-29",
     * "kssj_end": "2020-02-10",
     * "czr$like": "a'aaa"
     * }
     */
    @OpenAPI
    @ApiOperation("监室内务查询")
    @GetMapping("/jsnwQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jsnwQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjc/jsnwQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_start"))) {
            param.and("jcsj", TermType.gte, maps.getResult().get("jcsj_start"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_end"))) {
            param.and("jcsj", TermType.lte, maps.getResult().get("jcsj_end").toString() + 1);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("czr"))) {
            param.and("creator", TermType.like, '%' + maps.getResult().get("czr").toString() + "%");
        }
        param.and("jsbh", TermType.like, '%' + jsbh + "%");
        ResponseMessage<PagerResult<JsnwModel>> result = kssServerApis.queryForPageJsnw(param);
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
    }


    /**
     * @api {post} /v4/kss/ldjc/jqzxSave
     * @apiVersion 0.4.0
     * @apiName jqzxSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监区秩序保存
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "entity":[{
     * "jsh":"监室号(必填; 最大字段长度：4)",
     * "jcsj":"检查时间(必填; 格式(2019-10-10 10:10:10))",
     * "jcfs":"检查方式(必填; 最大字段长度：1)",
     * "jcqk":"检查情况(必填;)",
     * }]
     * }
     */
    @ApiOperation("监区秩序保存")
    @PostMapping("jqzxSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jqzxSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/ldjc/jqzxSave";

        //通过校验获取查询参数
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
            List<JqzxModel> jqzxModel = JSONArray.parseArray(map.get("entity").toString(), JqzxModel.class);
            jqzxModel.get(0).setState("R2");
            jqzxModel.get(0).setJsbh(jsbh);
            jqzxModel.get(0).setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.saveJqzx(jqzxModel.get(0));
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
     * @api {get} /v4/kss/ldjc/jqzxQuery 监区秩序查询
     * @apiVersion 0.4.0
     * @apiName jqzxQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监区秩序查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} jcsj:                       检查时间
     * @apiSuccess {String} jcfs:                       检查方式
     * @apiSuccess {String} jcqk:                       检查情况
     * @apiSuccess {String} scbz:                       上传标志
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "jsh": "0403",
     * "jcsj": "2020-01-29 18:36:20",
     * "jcfs": "01",
     * "jcqk": "aaaa",
     * }],
     * "page": 1
     * },
     * "status": 200,
     * "timestamp": 1576308123984
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     * {
     * "kssj_start": "2020-01-29",
     * "kssj_end": "2020-02-10",
     * "czr$like": "a'aaa"
     * }
     */
    @OpenAPI
    @ApiOperation("监区秩序查询")
    @GetMapping("/jqzxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jqzxQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/ldjc/jqzxQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_start"))) {
            param.and("jcsj", TermType.gte, maps.getResult().get("jcsj_start"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jcsj_end"))) {
            param.and("jcsj", TermType.lte, maps.getResult().get("jcsj_end").toString() + 1);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("czr"))) {
            param.and("creator", TermType.like, '%' + maps.getResult().get("czr").toString() + "%");
        }
        param.and("jsbh", TermType.like, '%' + jsbh + "%");
        ResponseMessage<PagerResult<JqzxModel>> result = kssServerApis.queryForPageJqzx(param);
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
    }
}
