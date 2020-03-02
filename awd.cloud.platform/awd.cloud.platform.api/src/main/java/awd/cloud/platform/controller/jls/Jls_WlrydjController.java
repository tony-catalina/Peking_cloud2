/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;

import awd.bj.jls.model.WlrydjClxxModel;
import awd.bj.jls.model.WlrydjModel;
import awd.bj.jls.model.WlrydjRyxxModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jls.WlrydjInfoModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/v4/jls/wlrydj")
@Api(tags = "jls-wlrydj", description = "Wlrydj")
public class Jls_WlrydjController extends PublicService {

    @Autowired
    private JlsServerApis jlsServerApis;

    /**
     * @api {get} /v4/jls/wlrydj/WlrydjCount 业务动态查询
     * @apiVersion 0.4.0
     * @apiName WlrydjCount
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
     * @apiSuccess {String}sxrs          				                    人数
     * @apiSuccess {String}sc         				                    视察
     * @apiSuccess {String}qshj          				                    亲属会见
     * @apiSuccess {String}sf          				                    上访
     * @apiSuccess {String}qt          				                    其它
     * @apiSuccess {String}tx          				                    提讯
     * @apiSuccess {String}cg          				                    参观
     * @apiSuccess {String}tj												提解
     * @apiSuccess {String}lsgz												临时工作
     * @apiSuccess {String}sxcls											    车辆数
     * @apiSuccess {String}jc                                                检查
     * @apiSuccess {String}lshj                                               律师会见
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
     * "sxrs": "1",
     * "sc": "0",
     * "qshj": "0",
     * "sf": "0",
     * "qt": "0",
     * "tx": "1",
     * "cg": "0",
     * "tj": "0",
     * "lsgz": "0",
     * "sxcls": "1",
     * "jc": "0",
     * "lshj": "0"
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
     * "timeStart": "开始时间(格式：2020-01-21)",
     * "timeEnd": "结束时间（格式：2020-02-21)",
     * "state": "状态"
     * }
     */
    @OpenAPI
    @ApiOperation("业务动态查询")
    @GetMapping("/WlrydjCount")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> WlrydjCount(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/wlrydj/WlrydjCount";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        String timeEnd = null;
        String timeStart = null;
        if (StringUtils.isNullOrEmpty(maps.getResult().get("timeEnd"))) {
            timeEnd = maps.getResult().get("timeEnd").toString();
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("timeStart"))) {
            timeEnd = maps.getResult().get("timeStart").toString();
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.wlrydjCount(jsbh, timeStart, timeEnd);
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
     * @api {post} /v4/jls/wlrydj/wlrydjUpdate 离开登记保存
     * @apiVersion 0.4.0
     * @apiName wlrydjUpdate
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 离开登记保存.
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
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "tbr": "管理员",
     * "tbrq": "2020-02-21",
     * "rybh": "人员编号(最大长度21)",
     * "id": "ID",
     * "sfls": "是否离所",
     * "dcmj": 带出民警（必填，最大长度30）,
     * "dcsj": 带出时间(必填，格式：2020-02-21 15:13:40),
     * "lksj": 离开时间(必填，格式：2020-02-21 15:13:40),
     * "lkqjywyc": 离开期间有无异常,
     * "taskid":"流程id",
     * "ywlcid":"业务流程id"
     * }]
     * }
     */
    @ApiOperation("离开登记保存")
    @PostMapping("wlrydjUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> wlrydjUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/wlrydj/wlrydjUpdate";

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
            List<WlrydjModel> model = JSONArray.parseArray(map.get("entity").toString(), WlrydjModel.class);
            model.get(0).setJsbh(jsbh);
            model.get(0).setUpdatetime(new Date());


            ResponseMessage<String> result = jlsServerApis.wlrydjUpdate(model.get(0).getId(), model.get(0));
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
     * @api {post} /v4/jls/wlrydj/wlrydjSave 来访人员登记添加
     * @apiVersion 0.4.0
     * @apiName wlrydjSave
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 来访人员登记添加.
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
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "jsonList": {
     * "tbr":"填表人（必填：最大长度30）",
     * "tbrq":"填表日期(格式：2020-02-21)",
     * "sfls":"是否离所",
     * "lssy":"来所事由(必填，最大长度10)",
     * "jtsy":"具体事由",
     * "sxrs":"随行人数（必填，最大长度4）",
     * "sxcls":"随行车辆数（必填，最大长度4）"
     * ,"bfr":"被访人（必填，最大长度30）",
     * "bfrbm":"被访人部门（必填，最大长度100）",
     * "drmj":"带入民警（必填，最大长度30）",
     * "drsj":"带入时间(格式：2020-02-21 10:38:48)"
     * },
     * "dataRyxx": [{
     * "xm":"姓名（最大长度30）",
     * "zjh":"证件号（最大长度50）",
     * "dw":"单位（最大长度50）",
     * "dh":"电话（最大长度50）",
     * "snzjh":"所内证件号（最大长度50）"
     * }],
     * "dataClxx": [{
     * "clssdw":"车辆所属单位（最大长度50）",
     * "cphm":"车牌号码（最大长度10）",
     * "aqjcqk":"安全检查情况（最大长度200）",
     * "snztzhm":"所内准停证号码（最大长度50）"
     * }],
     * "taskid":"流程id"，
     * "ywlcid":"业务流程id"
     * }]
     * }
     */
    @ApiOperation("来访人员登记添加")
    @PostMapping("wlrydjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> wlrydjSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/wlrydj/wlrydjSave";

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

            String jsonList = null;
            String dataRyxx = null;
            String dataClxx = null;
            if (StringUtils.isNullOrEmpty(maps.getResult().get("jsonList"))) {
                jsonList = maps.getResult().get("jsonList").toString();
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("dataRyxx"))) {
                dataRyxx = maps.getResult().get("dataRyxx").toString();
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("dataClxx"))) {
                dataClxx = maps.getResult().get("dataClxx").toString();
            }
            List<WlrydjModel> wlrydjModel = JSONArray.parseArray(jsonList, WlrydjModel.class);
            wlrydjModel.get(0).setCreatetime(new Date());
            wlrydjModel.get(0).setState("R2");
            wlrydjModel.get(0).setJsbh(jsbh);
            wlrydjModel.get(0).setLssj(wlrydjModel.get(0).getDrsj());
            List<WlrydjClxxModel> clxxList = JSON.parseArray(dataClxx, WlrydjClxxModel.class);
            List<WlrydjRyxxModel> ryxxList = JSON.parseArray(dataRyxx, WlrydjRyxxModel.class);

            if (!StringUtils.isNullOrEmpty(dataRyxx)) {
                ryxxList.forEach(ryxxmodel -> {
                    System.err.println("model==========" + JSON.toJSONString(ryxxmodel));
                    ryxxmodel.setJsbh(jsbh);
                    ryxxmodel.setCreatetime(new Date());
                    ryxxmodel.setCreator(wlrydjModel.get(0).getCreator());
                    ryxxmodel.setUuid(wlrydjModel.get(0).getUuid());
                });
            }

            System.err.println("车辆信息================================================");
            if (!StringUtils.isNullOrEmpty(dataClxx)) {
                clxxList.forEach(clxxmodel -> {
                    System.err.println("model==========" + JSON.toJSONString(clxxmodel));
                    clxxmodel.setJsbh(jsbh);
                    clxxmodel.setCreatetime(new Date());
                    clxxmodel.setCreator(wlrydjModel.get(0).getCreator());
                    clxxmodel.setUuid(wlrydjModel.get(0).getUuid());
                });
            }

            WlrydjInfoModel wlrydjInfoModel = new WlrydjInfoModel();
            wlrydjInfoModel.setJsbh(jsbh);
            wlrydjInfoModel.setRyxxList(ryxxList);
            wlrydjInfoModel.setClxxList(clxxList);
            wlrydjInfoModel.setWlrydjEntity(wlrydjModel.get(0));

            ResponseMessage<String> result = jlsServerApis.wlryxgSave(wlrydjInfoModel);
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
     * @api {get} /v4/jls/wlrydj/wlrydjQuery 外来人员登记查询
     * @apiVersion 0.4.0
     * @apiName wlrydjQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 外来人员登记查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String}lssy          				                    来所事由
     * @apiSuccess {String}lssj         				                    来所时间
     * @apiSuccess {String}sxrs          				                    随行人数
     * @apiSuccess {String}sxcls          				                    随性车辆数
     * @apiSuccess {String}bfr          				                    被访人
     * @apiSuccess {String}bfrbm          				                    被访人部门
     * @apiSuccess {String}jtsy          				                    具体事由
     * @apiSuccess {String}drmj												带入民警
     * @apiSuccess {String}drsj												带入时间
     * @apiSuccess {String}dcsj											    带出时间
     * @apiSuccess {String}lksj                                             离开时间
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
     * "id": "11000012120191218000094",
     * "jsbh": "110000121",
     * "tbr": "管理员",
     * "tbrq": "2019-12-18",
     * "uuid": "14ea3b35-4651-493e-a74",
     * "lssy": "2",
     * "sfls": "1",
     * "jtsy": "",
     * "lssj": "2019-12-18 15:14:25",
     * "drmj": "管理员",
     * "drsj": "2019-12-18 15:14:25",
     * "dcmj": "管理员",
     * "dcsj": "2019-12-18 15:14:41",
     * "sxrs": "",
     * "sxcls": "3",
     * "bfr": "4234",
     * "bfrbm": "33",
     * "pclsh": "",
     * "bz": "",
     * "lksj": "2019-12-18 15:14:41",
     * "lkqjywyc": "23",
     * "state": "R2",
     * "scbz": "",
     * "operator": "",
     * "creator": "管理员",
     * "createtime": "2019-12-18 15:13:32",
     * "updator": "管理员",
     * "updatetime": "2019-12-18 15:13:42",
     * "jsbhString": "北京市拘留所",
     * "lssyString": "家属会见",
     * "stateString": "有效",
     * "scbzString": ""
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
     * "lssy": "来所事由",
     * "lssjStart": "来所开始时间(格式：2020-02-18 10:16:21)",
     * "lssjEnd": "来所结束时间(格式：2020-02-21 10:16:23)",
     * "bfr": "被访人（最大长度30）",
     * "bfrbm": "被访人部门（最大长度50）"
     * }
     */
    @OpenAPI
    @ApiOperation("请假回所业务台账查询")
    @GetMapping("/wlrydjQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> wlrydjQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/wlrydj/wlrydjQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        QueryParam param = new QueryParam();
        if (StringUtils.isNullOrEmpty(maps.getResult().get("lssjEnd"))) {
            param.and("lssj", TermType.lte, maps.getResult().get("lssjEnd"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("lssjStart"))) {
            param.and("lssj", TermType.gte, maps.getResult().get("lssjStart"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("lssy"))) {
            param.and("lssy", maps.getResult().get("lssy"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("bfr"))) {
            param.and("bfr", maps.getResult().get("bfr"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("bfrbm"))) {
            param.and("bfrbm", maps.getResult().get("bfrbm"));
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.wlrydjQueryForPage(param);
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
