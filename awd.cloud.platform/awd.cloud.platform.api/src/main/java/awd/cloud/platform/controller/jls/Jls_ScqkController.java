/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import awd.bj.jls.model.ScqkModel;
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
@RequestMapping("/v4/jls/scqk")
@Api(tags = "jls-scqk",description = "Scqk")
public class Jls_ScqkController extends PublicService {

    @Autowired
    private JlsServerApis jlsServerApis;




    /**
     * @api {post} /v4/jls/scqk/scqkAdd 视察情况添加
     * @apiVersion 0.4.0
     * @apiName scqkAdd
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 视察情况添加.
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
     * "rq":"视察日期（必填，格式：2020-02-22 14:43:26）",
     * "ldxm":"陪同领导（最大长度30）",
     * "ldzw":"领导职务",
     * "xm":"姓名（最大长度30）",
     * "zw":"职务",
     * "dw":"所在单位",
     * "scnr":"视察情况",
     * "yjjy":"提出的意见和建议",
     * "sfls":"是否处理（最大长度1）",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }]
     * }
     */
    @ApiOperation("视察情况添加")
    @PostMapping("scqkAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> scqkAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jls/scqk/scqkAdd";

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
            List<ScqkModel> model = JSONArray.parseArray(jsonList, ScqkModel.class);

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
            ResponseMessage<String> result = jlsServerApis.scqkAndsctt(model.get(0));
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
     * @api {get} /v4/jls/scqk/scqkQuery 视察情况查询
     * @apiVersion 0.4.0
     * @apiName scqkQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 视察情况查询.
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
     * id: "11000012120191218000183"
     * ywlcid: null
     * taskid: null
     * jsbh: "110000121"
     * rybh: ""
     * sfls: "0"
     * tbr: "管理员"
     * tbrq: "2019-12-18"
     * rq: "2019-12-18 15:15:25"
     * tt: "234"
     * cy: "234"
     * jdr: "管理员"
     * cdsj: null
     * ldxm: "23"
     * ldzw: "1"
     * scnr: "234"
     * yjjy: ""
     * zgqk: ""
     * jlr: "管理员"
     * bz: ""
     * state: "R2"
     * scbz: ""
     * operator: ""
     * creator: "管理员"
     * createtime: "2019-12-18 15:14:33"
     * updator: ""
     * updatetime: null
     * jsbhString: "北京市拘留所"
     * ldzwString: "国家主席副主席总理级"
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
     * "rqStart": "开始时间(格式：2020-01-28 14:27:25)",
     * "rqEnd": "结束时间(格式：2020-02-13 14:27:27)",
     * "jdr":" 接待人（最大长度30）",
     * "ldxm": "领导姓名（最大长度30）",
     * "jlr": "记录人（最大长度30）",
     * "taskid": "流程id",
     * "ywlcid": "业务流程id"
     * }
     */
    @OpenAPI
    @ApiOperation("领导接访查询")
    @GetMapping("/scqkQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> scqkQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jls/scqk/scqkQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        QueryParam param = new QueryParam();
        if (StringUtils.isNullOrEmpty(maps.getResult().get("rqEnd"))) {
            param.and("rq", TermType.lte, maps.getResult().get("rqEnd"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("rqStart"))) {
            param.and("rq", TermType.gte, maps.getResult().get("rqStart"));
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("jdr"))) {
            param.and("jdr",TermType.like, "%"+maps.getResult().get("jdr")+"%");
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("jlr"))) {
            param.and("jlr",TermType.like, "%"+maps.getResult().get("jlr")+"%");
        }
        if (StringUtils.isNullOrEmpty(maps.getResult().get("ldxm"))) {
            param.and("ldxm",TermType.like, "%"+maps.getResult().get("ldxm")+"%");
        }

        ResponseMessage<PagerResult<Map<String, Object>>> result = jlsServerApis.scqkQueryForPage(param);
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
