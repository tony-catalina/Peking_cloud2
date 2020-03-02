/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.jls.model.SzjdjlModel;
import awd.cloud.platform.api.JlsServerApis;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/szjdjl")
@Api(tags = "jls-szjdjl",description = "Szjdjl")
public class Jls_SzjdjlController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;


    /**
     * @api {get} /v4/jls/szjdjl/ldjfCount 业务动态查询
     * @apiVersion 0.4.0
     * @apiName ldjfCount
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 业务动态查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} ycl                         已处理
     * @apiSuccess {String} lfcs                        来访次数
     *
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * ycl: 1
     * lfcs: 1
     * }
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
     * "timeStart": "开始时间(格式：2020-01-22)",
     * "timeEnd": "结束时间(格式：2020-02-22)",
     * "state": "状态",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }
     */
    @OpenAPI
    @ApiOperation("业务动态查询")
    @GetMapping("/ldjfCount")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> ldjfCount(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/szjdjl/ldjfCount";
        String state = "R2";
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        String starttime=null;
        String endtime=null;
        if(StringUtils.isNullOrEmpty(maps.getResult().get("timeStart"))){
            starttime=maps.getResult().get("timeStart").toString();
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("timeEnd"))){
            endtime=maps.getResult().get("timeEnd").toString();
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.szjdjlCount(jsbh,starttime,endtime);
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
     * @api {post} /v4/jls/szjdjl/ldjfUpdate 领导接访修改
     * @apiVersion 0.4.0
     * @apiName ldjfUpdate
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 领导接访修改.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "tbr": "填表人（必填，最大长度30）",
     * "tbrq": "填表日期(必填，格式：2020-02-22)",
     * "id": "ID（必填，最大长度23）"
     * "sfcl":"是否处理（最大长度1）"
     * "dfyj": "提出的意见和建议"
     * "clqk": "落实情况"
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }]
     * }
     */
    @ApiOperation("领导接访修改")
    @PostMapping("ldjfUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldjfUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/szjdjl/ldjfUpdate";

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

            String jsonList=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("jsonList"))){
                jsonList=maps.getResult().get("jsonList").toString();
            }
            List<SzjdjlModel> model = JSONArray.parseArray(jsonList, SzjdjlModel.class);

            model.get(0).setJsbh(jsbh);
            model.get(0).setUpdatetime(new Date());

            String ywlcid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("ywlcid"))){
                ywlcid=maps.getResult().get("ywlcid").toString();
            }
            else {
                return ResponseMessage.error("ywlcid不能为空！");
            }
            String taskid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                taskid=maps.getResult().get("taskid").toString();
            }
            else {
                return ResponseMessage.error("taskid不能为空！");
            }
            ResponseMessage<String> result = jlsServerApis.szjdjlUpdate(model.get(0).getId(), model.get(0));
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
     * @api {post} /v4/jls/szjdjl/ldjfAdd 领导接访添加
     * @apiVersion 0.4.0
     * @apiName ldjfAdd
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 领导接访添加.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "tbr": "填表人（必填，最大长度30）",
     * "tbrq": "填表日期(必填，格式：2020-02-22)",
     * "sfcl": "是否处理（最大长度1）",
     * "lfsj": "来访时间(必填，格式：2020-02-22 10:12:31)",
     * "sldxm": "接访领导姓名（必填，最大长度30）",
     * "jdsj": "接待时间(必填，格式：2020-02-22 10:12:31)",
     * "zlfxm": "来访人姓名（必填，最大长度30）",
     * "lxfs": "联系电话（最大长度30）",
     * "lfrs": "来访人数（最大长度255）",
     * "lsdz": "联系地址（最大长度255）",
     * "jdmj": "接待民警（必填，最大长度30）",
     * "fywt": "来访内容（最大长度255）",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }]
     * }
     */
    @ApiOperation("领导接访添加")
    @PostMapping("ldjfAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ldjfAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/szjdjl/ldjfAdd";

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

            String jsonList=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("jsonList"))){
                jsonList=maps.getResult().get("jsonList").toString();
            }
            List<SzjdjlModel> model = JSONArray.parseArray(jsonList, SzjdjlModel.class);

            model.get(0).setState("R2");
            model.get(0).setJsbh(jsbh);
            model.get(0).setCreatetime(new Date());

            String ywlcid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("ywlcid"))){
                ywlcid=maps.getResult().get("ywlcid").toString();
            }
            else {
                return ResponseMessage.error("ywlcid不能为空！");
            }
            String taskid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                taskid=maps.getResult().get("taskid").toString();
            }
            else {
                return ResponseMessage.error("taskid不能为空！");
            }
            ResponseMessage<String> result = jlsServerApis.szjdjlSave(model.get(0));
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
     * @api {get} /v4/jls/szjdjl/ldjfQuery 领导接访查询
     * @apiVersion 0.4.0
     * @apiName ldjfQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 领导接访查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     *
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * id: "11000012120191218000080"
     * ywlcid: null
     * taskid: null
     * jsbh: "110000121"
     * sfcl: "1"
     * tbr: "管理员"
     * tbrq: "2019-12-18"
     * sldxm: "23"
     * lfsj: "2019-12-18 15:14:53"
     * jdsj: "2019-12-18 15:14:53"
     * jdmj: "管理员"
     * lfrs: ""
     * zlfxm: "3"
     * lfxb: ""
     * lfnl: ""
     * lfgzdw: ""
     * lsdz: ""
     * lxfs: ""
     * fywt: ""
     * dfyj: ""
     * clqk: "23"
     * bz: ""
     * state: "R2"
     * scbz: ""
     * operator: ""
     * creator: "管理员"
     * createtime: "2019-12-18 15:13:55"
     * updator: "管理员"
     * updatetime: "2019-12-18 15:14:04"
     * jsbhString: "北京市拘留所"
     * lfxbString: ""
     * stateString: "有效"
     * scbzString: ""
     * }
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
     * "sldxm": "所领导姓名（最大长度30）",
     * "jdsjStart": "接待开始时间（格式：2020-02-05 16:26:23）",
     * "jdsjEnd": "接待结束时间（格式：2020-02-11 16:26:25）",
     * "zlfxm": "来访人姓名（最大长度30）",
     * "lfsjStart": "来访开始时间（格式：2020-02-19 16:26:28）",
     * "lfsjEnd": "来访结束时间（格式：2020-02-20 16:26:29）",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }
     */
    @OpenAPI
    @ApiOperation("领导接访查询")
    @GetMapping("/ldjfQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> ldjfQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/szjdjl/ldjfQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        QueryParam param = new QueryParam();
        if (StringUtils.isNullOrEmpty(maps.getResult().get("lfsjEnd"))) {
            param.and("lfsj", TermType.lte, maps.getResult().get("lfsjEnd"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("lfsjStart"))) {
            param.and("lfsj", TermType.gte, maps.getResult().get("lfsjStart"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("jdsjEnd"))) {
            param.and("jdsj", TermType.lte, maps.getResult().get("jdsjEnd"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("jdsjStart"))) {
            param.and("jdsj", TermType.gte, maps.getResult().get("jdsjStart"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("sldxm"))) {
            param.and("sldxm", maps.getResult().get("sldxm"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("zlfxm"))) {
            param.and("zlfxm", maps.getResult().get("zlfxm"));
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.szjdjlQueryForPage(param);
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
