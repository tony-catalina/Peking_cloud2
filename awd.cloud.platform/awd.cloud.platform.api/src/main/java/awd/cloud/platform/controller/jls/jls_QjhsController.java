package awd.cloud.platform.controller.jls;

import awd.bj.jls.model.CrjdjModel;
import awd.bj.jls.model.QjcsModel;
import awd.bj.jls.model.WpglModel;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/qjhs")
@Api(tags = "jls-qjhs",description = "qjhs")
public class jls_QjhsController extends PublicService {

    @Autowired
    private JlsServerApis jlsServerApis;

    /**
     * @api {get} /v4/jls/qjhs/qjhswtzQuery 请假回所业务台账查询
     * @apiVersion 0.4.0
     * @apiName qjhswtzQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 请假回所业务台账查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String}xm          				                        姓名
     * @apiSuccess {String}jsh         				                        拘室号
     * @apiSuccess {String}bm          				                        别名
     * @apiSuccess {String}xbString          				                性别
     * @apiSuccess {String}rsrq          				                    入所日期
     * @apiSuccess {String}ayString          				                案别
     * @apiSuccess {String}rsxzString          				                入所性质
     * @apiSuccess {String}badw												办案单位
     * @apiSuccess {String}bar												办案人
     * @apiSuccess {String}bardh											办案人电话
     * @apiSuccess {String}gyqx                                             关押期限
     * @apiSuccess {String}gyts                                             关押天数
     * @apiSuccess {String}hjdString                                        户籍地
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
     * "sqsj": 1576653014000,
     * "ybdbrgx": "",
     * "dbrdwdz": "",
     * "qjkssj": "2019-12-18 15:10:23",
     * "dh": "17566666666",
     * "dah": "155874",
     * "zgldyjnr": "同意该拘留人请假出所",
     * "szyj": "1",
     * "gjqm": "管理员",
     * "id": "11000012120191218000174",
     * "tbrq": 1576598400000,
     * "state": "R2",
     * "taskid": "3655226",
     * "qjjssj": "2020-01-07 15:10:23",
     * "hjd": "320113",
     * "gyqx": "2019-12-08",
     * "gjyjnr": "同意",
     * "zgldqmsj": "2019-12-18 15:11:11",
     * "dbr": "赵云",
     * "ayString": "",
     * "ay": "010100",
     * "dbrgh": "",
     * "dbrzjh": "",
     * "zgldyj": "1",
     * "badw": "江苏南京",
     * "jsh": "0301",
     * "tbr": "管理员",
     * "ywlcid": "3654740",
     * "bm": "李玉",
     * "zgldqm": "管理员",
     * "dbrxzd": "南京安威德",
     * "szyjnr": "同意该拘留人请假出所",
     * "qjly": "士大夫",
     * "snbh": "20190007",
     * "sqr": "管理员",
     * "qjrdh": "18977777777",
     * "dbrzw": "",
     * "hjdString": "江苏南京市栖霞区",
     * "creator": "管理员",
     * "createtime": 1576652964000,
     * "dbrdw": "",
     * "cssj": "2019-11-29 10:24:59",
     * "xb": "2",
     * "zjqx": "20",
     * "xbString": "女性",
     * "gjqmrq": "2019-12-18 15:10:23",
     * "szqmsj": "2019-12-18 15:11:01",
     * "xm": "李玉",
     * "rsrq": "2019-11-28 17:28:31",
     * "szqm": "管理员",
     * "rybh": "110000121201911280008",
     * "updatetime": "1576652964000",
     * "jsbh": "110000121"
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
     * "jbxx_xm": "姓名(最大长度30)",
     * "jbxx_jsh":"监室号(最大长度4)",
     * "jbxx_bm": "别名(最大长度30)",
     * "jbxx_ay": "案由",
     * "ksrqStart": "起始开始日期(2020-02-18)",
     * "ksrqEnd": "结束开始日期(2020-02-21)",
     * "jsrqStart": "起始结束日期(2020-02-18)",
     * "jsrqEnd": "结束结束日期(2020-02-21)",
     * "szqm": "所长签名(最大长度30)"
     * }
     */
    @OpenAPI
    @ApiOperation("请假回所业务台账查询")
    @GetMapping("/qjhswtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> qjhswtzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qjhs/qjhswtzQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        QueryParam param = new QueryParam();
        if(StringUtils.isNullOrEmpty(maps.getResult().get("szqm"))){
            param.and("szqm",maps.getResult().get("szqm"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jsrqEnd"))){
            param.and("jsrq",TermType.lte, maps.getResult().get("jsrqEnd"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jsrqStart"))){
            param.and("jsrq",TermType.gte, maps.getResult().get("jsrqStart"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("ksrqEnd"))){
            param.and("ksrq",TermType.lte, maps.getResult().get("ksrqEnd"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("ksrqStart"))){
            param.and("ksrq",TermType.gte, maps.getResult().get("ksrqStart"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))){
            param.and("xb", maps.getResult().get("jbxx_xb"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_ay"))){
            param.and("ay", maps.getResult().get("jbxx_ay"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_bm"))){
            param.and("bm", TermType.like, "%"+maps.getResult().get("jbxx_bm")+"%");
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_jsh"))){
            param.and("jsh", maps.getResult().get("jbxx_jsh"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm"))){
            param.and("xm", TermType.like, "%"+maps.getResult().get("jbxx_xm")+"%");
        }
        param.and("hssj", TermType.isnull, "1");
        param.and("cssj", TermType.notnull, "1");
        param.and("state", "R2");
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.qjcsJbxxForPage(param);
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
     * @api {post} /v4/jls/qjhs/drjsAdd 带入监室添加
     * @apiVersion 0.4.0
     * @apiName drjsAdd
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 带入监室添加.
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
     * "tbr": "填表人(必填，最大长度30)",
     * "tbrq": "填表日期(格式:2020-02-20)",
     * "rybh": "人员编号(必填，最大长度21)",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id",
     * "drmj": "带入民警(必填，最大长度30)",
     * "drsj": "带入时间(格式:2020-02-20 16:34:59)"
     * }]
     * }
     */
    @ApiOperation("带入监室添加")
    @PostMapping("drjsAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> drjsAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/qjhs/drjsAdd";

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
            List<CrjdjModel> crjdjModel = JSONArray.parseArray(jsonList, CrjdjModel.class);


            String ywlcid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("ywlcid"))){
                ywlcid=maps.getResult().get("ywlcid").toString();
            }
            else {
                return ResponseMessage.error("ywlcid！");
            }
            String taskid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                taskid=maps.getResult().get("taskid").toString();
            }
            else {
                return ResponseMessage.error("taskid不能为空！");
            }
            ResponseMessage<String> result = jlsServerApis.qjhsDrjs( crjdjModel.get(0),taskid);
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
     * @api {post} /v4/jls/qjhs/qjhsFwdjAdd 附物登记添加
     * @apiVersion 0.4.0
     * @apiName qjhsFwdjAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 附物登记添加.
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
     *@apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{"entity":[{
     * "mc":"物品名称",
     * "sl":"物品数量（最大长度1）",
     * "wpdw":"物品单位",
     * "tz":"物品特征（最大长度300）",
     * "bz":"备注",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id",
     * "rybh": "人员编号（必填，最大长度21）",
     * "jbr": "经办人（必填，最大长度30）",
     * "jbsj": "经办时间(格式：2020-02-20 11:12:12)"
     * }]
     * }
     */
    @ApiOperation("附物登记添加")
    @PostMapping("qjhsFwdjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qjhsFwdjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/qjhs/qjhsFwdjAdd";

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

            List<WpglModel> wpglModel = JSONArray.parseArray(map.get("entity").toString(), WpglModel.class);



            for (int i = 0; i < wpglModel.size(); i++) {
                System.err.println(wpglModel.size());
                wpglModel.get(i).setState("R2");
                wpglModel.get(i).setJsbh(jsbh);
                wpglModel.get(i).setLqzt("0");
                wpglModel.get(i).setCreatetime(new Date());

            }


            wpglModel.get(0).setJsbh(jsbh);
            wpglModel.get(0).setCreatetime(new Date());
            String ywlcid = null;

            if(StringUtils.isNullOrEmpty(maps.getResult().get("ywlcid"))){
                ywlcid=maps.getResult().get("ywlcid").toString();
            }

            String taskid=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                taskid=maps.getResult().get("taskid").toString();
            }
            else {
                return ResponseMessage.error("taskid不能为空！");
            }
            ResponseMessage<String> result = jlsServerApis.wpglSaveByZcrs(taskid, ywlcid, wpglModel);
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
     * @api {post} /v4/jls/qjhs/qjhsAdd 请假回所添加
     * @apiVersion 0.4.0
     * @apiName qjhsAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 请假回所添加.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
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
     * json:{"entity":[{
     * "tbr": "填表人（必填，最大长度30）",
     * "tbrq": "填表日期(格式：2020-02-19)",
     * "rybh": "人员编号（必填，最大长度21）",
     * "taskid":"业务id",
     * "ywlcid": "业务流程id",
     * "id": "ID",
     * "hssj": "回所时间(格式：2020-02-19 15:42:28)",
     * "zbmj": "民警（必填，最大长度30）"
     * }]
     * }
     */
    @ApiOperation("请假回所添加")
    @PostMapping("qjhsAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qjhsAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/qjhs/qjhsAdd";

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
            List<QjcsModel> qjcsModel = JSONArray.parseArray(map.get("entity").toString(), QjcsModel.class);
            qjcsModel.get(0).setJsbh(jsbh);
            ResponseMessage<String> result = jlsServerApis.qjcsStartflow(qjcsModel.get(0));
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
     * @api {get} /v4/jls/qjhs/qjhsQuery 请假回所查询
     * @apiVersion 0.4.0
     * @apiName qjhsQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 请假回所查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String}xm          				                        姓名
     * @apiSuccess {String}jsh         				                        拘室号
     * @apiSuccess {String}bm          				                        别名
     * @apiSuccess {String}xbString          				                性别
     * @apiSuccess {String}rsrq          				                    入所日期
     * @apiSuccess {String}ayString          				                案别
     * @apiSuccess {String}rsxzString          				                入所性质
     * @apiSuccess {String}badw												办案单位
     * @apiSuccess {String}bar												办案人
     * @apiSuccess {String}bardh											办案人电话
     * @apiSuccess {String}gyqx                                             关押期限
     * @apiSuccess {String}gyts                                             关押天数
     * @apiSuccess {String}hjdString                                        户籍地
     *
     *@apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *  @apiSuccess {String} page         				当前页数
     *  @apiSuccess {String} status         				代码
     *  @apiSuccess {String} timestamp         			时间戳
	 * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "sqsj": 1576653014000,
     * "ybdbrgx": "",
     * "dbrdwdz": "",
     * "qjkssj": "2019-12-18 15:10:23",
     * "dh": "17566666666",
     * "dah": "155874",
     * "zgldyjnr": "同意该拘留人请假出所",
     * "szyj": "1",
     * "gjqm": "管理员",
     * "id": "11000012120191218000174",
     * "tbrq": 1576598400000,
     * "state": "R2",
     * "taskid": "3655226",
     * "qjjssj": "2020-01-07 15:10:23",
     * "hjd": "320113",
     * "gyqx": "2019-12-08",
     * "gjyjnr": "同意",
     * "zgldqmsj": "2019-12-18 15:11:11",
     * "dbr": "赵云",
     * "ayString": "",
     * "ay": "010100",
     * "dbrgh": "",
     * "dbrzjh": "",
     * "zgldyj": "1",
     * "badw": "江苏南京",
     * "jsh": "0301",
     * "tbr": "管理员",
     * "ywlcid": "3654740",
     * "bm": "李玉",
     * "zgldqm": "管理员",
     * "dbrxzd": "南京安威德",
     * "szyjnr": "同意该拘留人请假出所",
     * "qjly": "士大夫",
     * "snbh": "20190007",
     * "sqr": "管理员",
     * "qjrdh": "18977777777",
     * "dbrzw": "",
     * "hjdString": "江苏南京市栖霞区",
     * "creator": "管理员",
     * "createtime": 1576652964000,
     * "dbrdw": "",
     * "cssj": "2019-11-29 10:24:59",
     * "xb": "2",
     * "zjqx": "20",
     * "xbString": "女性",
     * "gjqmrq": "2019-12-18 15:10:23",
     * "szqmsj": "2019-12-18 15:11:01",
     * "xm": "李玉",
     * "rsrq": "2019-11-28 17:28:31",
     * "szqm": "管理员",
     * "rybh": "110000121201911280008",
     * "updatetime": "1576652964000",
     * "jsbh": "110000121"
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
     * "jbxx_xm":"姓名（最大长度30）",
     * "jsh":"监室号（最大长度4）",
     * "jbxx_bm":"别名（最大长度30）",
     * "jbxx_ay":"案由",
     * "jbxx_xb":"性别",
     * "jbxx_rsrqStart":"开始入所日期（格式：2020-02-19）",
     * "jbxx_rsrqEnd": "结束入所日期（格式：2020-02-19）",
     * "state": "状态"
     * }
     */
    @OpenAPI
    @ApiOperation("请假回所查询")
    @GetMapping("/qjhsQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> qjhsQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/qjhs/qjhsQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        QueryParam param = new QueryParam();
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqEnd"))){
            param.and("rsrq",TermType.lte, maps.getResult().get("jbxx_rsrqEnd"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqStart"))){
            param.and("rsrq",TermType.gte, maps.getResult().get("jbxx_rsrqStart"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))){
            param.and("xb", maps.getResult().get("jbxx_xb"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_ay"))){
            param.and("ay", maps.getResult().get("jbxx_ay"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_bm"))){
            param.and("bm", TermType.like, "%"+maps.getResult().get("jbxx_bm")+"%");
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))){
            param.and("jsh", maps.getResult().get("jsh"));
        }
        if(StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm"))){
            param.and("xm", TermType.like, "%"+maps.getResult().get("jbxx_xm")+"%");
        }
        param.and("hssj", TermType.isnull, "1");
        param.and("cssj", TermType.notnull, "1");
        param.and("state", "state");
        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.tsclQueryForPage(param);
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
