package awd.cloud.platform.controller.kss;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * Author：张延
 * Date：2020-01-03 11:42
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/kss_process")
@Api(tags="kss_process",description="kss_process")
public class Kss_ProcessController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {get} /v4/kss/kss_process/getProcessTaskList 获取流程信息
     * @apiVersion 0.4.0
     * @apiName getProcessTaskList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 获取流程信息.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}id         				                         ID
     * @apiSuccess {String}rybh         				                     人员编号
     * @apiSuccess {String}jsbh         				                     监所编号
     * @apiSuccess {String}xm         				                         姓名
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
     * "data": [
     *       {
     *          "xm": "云承",
     *         "rybh": "310000111201906200007",
     *         "id": "31000011120190620000126",
     *         "jsbh": "110000114"
     *         }
     * ],
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1577356008049
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *      " taskDefinitionKey":"必传",
     *      " processDefinitionKey":"",
     *      " start":"",
     *      " limit":"",
     *    }
     */
    @OpenAPI
    @ApiOperation("获取流程信息")
    @GetMapping("/getProcessTaskList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> getProcessTaskList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/kss_process/getProcessTaskList";
        String state = request.getParameter("state");
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        Variables variables = new Variables();
        variables.setJsbh(jsbh);

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("taskDefinitionKey"))) {
            variables.setTaskDefinitionKey((String) maps.getResult().get("taskDefinitionKey"));
        }
        int _pageIndex = 0;
        int _pageSize = 0;
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("start")) && !StringUtils.isNullOrEmpty(maps.getResult().get("limit"))) {
            _pageIndex = Integer.parseInt((String) maps.getResult().get("start"));
            _pageSize = Integer.parseInt((String) maps.getResult().get("limit"));
            int num = _pageIndex;

            if (1 != _pageIndex && 0 != _pageIndex) {
                _pageIndex = _pageSize * num - _pageSize;
                _pageSize = _pageSize * num;
            } else {
                _pageIndex = 0;
            }
        }
        Map<String, Object> params = Maps.newHashMap();
        Map<String, Object> notEqualsMap = Maps.newHashMap();
        Map<String, Object> greaterThanOrEqualMap = Maps.newHashMap();
        Map<String, Object> lessThanOrEqualMap = Maps.newHashMap();
        variables.setStart(String.valueOf(_pageIndex));
        variables.setLimit((String) maps.getResult().get("limit"));

        // String xm = request.getParameter("xm");
        // String ay = request.getParameter("ay");
        // String bm = request.getParameter("bm");
        // String jsh = request.getParameter("jsh");
        // String xb = request.getParameter("xb");
        // String bahj = request.getParameter("bahj");
        // String badw = request.getParameter("badw");
        // String dah = request.getParameter("dah");
        // String lsdjr = request.getParameter("lsdjr");
        // String snbh = request.getParameter("snbh");
        // String yjmj = request.getParameter("yjmj");
        // String rsrq_start = request.getParameter("rsrq_start");
        // String rsrq_end = request.getParameter("rsrq_end");
        // String cssj_start = request.getParameter("cssj_start");
        // String cssj_end = request.getParameter("cssj_end");
        // String csrq_start = request.getParameter("csrq_start");
        // String csrq_end = request.getParameter("csrq_end");
        // String gyqx_start = request.getParameter("gyqx_start");
        // String gyqx_end = request.getParameter("gyqx_end");
        // String ncsrq_start = request.getParameter("ncsrq_start");
        // String ncsrq_end = request.getParameter("ncsrq_end");
        // String xqjsrq_start = request.getParameter("xqjsrq_start");
        // String xqjsrq_end = request.getParameter("xqjsrq_end");
        // String rsxz=request.getParameter("rsxz");
        // String gwjjb_jbgw=request.getParameter("gwjjb_jbgw");
        // String caaj=request.getParameter("caaj");
        // String sydw=request.getParameter("sydw");
        // String zrdw=request.getParameter("sydw");
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
            params.put("xm", maps.getResult().get("xm"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
            params.put("ay", maps.getResult().get("ay"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
            params.put("bm", maps.getResult().get("bm"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            params.put("jsh", maps.getResult().get("jsh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
            params.put("xb", maps.getResult().get("xb"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("badw"))) {
            params.put("badw", maps.getResult().get("badw"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("dah"))) {
            params.put("dah", maps.getResult().get("dah"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("lsdjr"))) {
            params.put("lsdjr", maps.getResult().get("lsdjr"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("yjmj"))) {
            params.put("yjmj", maps.getResult().get("yjmj"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("bahj"))) {
            params.put("bahj", maps.getResult().get("bahj"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("snbh"))) {
            params.put("snbh", maps.getResult().get("snbh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("caaj"))) {
            params.put("caaj", maps.getResult().get("caaj"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sydw"))) {
            params.put("sydw", maps.getResult().get("sydw"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("zrdw"))) {
            params.put("zrdw", maps.getResult().get("zrdw"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsxz"))) {
            params.put("rsxz", maps.getResult().get("rsxz"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("gwjjb_jbgw"))) {
            params.put("gwjjb_jbgw", maps.getResult().get("gwjjb_jbgw"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
            greaterThanOrEqualMap.put("rsrq", maps.getResult().get("rsrq"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq"))) {
            lessThanOrEqualMap.put("rsrq", maps.getResult().get("rsrq"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj"))) {
            greaterThanOrEqualMap.put("cssj", maps.getResult().get("cssj") + " 00:00::00");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj"))) {
            lessThanOrEqualMap.put("cssj", maps.getResult().get("cssj") + " 23:59:59");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx"))) {
            greaterThanOrEqualMap.put("gyqx", maps.getResult().get("gyqx"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx"))) {
            lessThanOrEqualMap.put("gyqx", maps.getResult().get("gyqx"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq"))) {
            greaterThanOrEqualMap.put("csrq", maps.getResult().get("csrq"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq"))) {
            lessThanOrEqualMap.put("csrq", maps.getResult().get("csrq") + " 23:59:59");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj"))) {
            lessThanOrEqualMap.put("cssj", maps.getResult().get("cssj"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("cssj"))) {
            lessThanOrEqualMap.put("cssj", maps.getResult().get("cssj") + " 23:59:59");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xqjsrq"))) {
            lessThanOrEqualMap.put("xqjsrq", maps.getResult().get("xqjsrq"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xqjsrq"))) {
            lessThanOrEqualMap.put("xqjsrq", maps.getResult().get("xqjsrq") + " 23:59:59");
        }
        if (!maps.getResult().get("processDefinitionKey").toString().contains(",")) {
            variables.setProcessDefinitionKey((String) maps.getResult().get("processDefinitionKey"));
        } else {
            variables.setProcessDefinitionKey("");
            params.put("in", maps.getResult().get("processDefinitionKey"));
        }
        variables.setParams(params);    //流程匹配参数
        variables.setNotEqualsMap(notEqualsMap);    //流程不等于匹配参数
        variables.setGreaterThanOrEqualMap(greaterThanOrEqualMap);    //流程大于匹配参数
        variables.setLessThanOrEqualMap(lessThanOrEqualMap);    //流程小于匹配参数

        long procedure_startTime = System.currentTimeMillis();   //获取开始时间
        System.err.println("getProcessTaskList-----------" + JSONUtil.toJson(variables));
        ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.getListCustom(variables);
        System.err.println("getProcessTaskresult-----------" + JSONUtil.toJson(result));
        Map<String, Object> returnMap = Maps.newHashMap();
        if (result != null && result.getStatus() == 200) {
            returnMap.put("rows", result.getResult().getData());
            returnMap.put("total", result.getResult().getTotal());
            returnMap.put("interfaceId", interfaceId);
            returnMap.put("entity", result.getResult().getData());
        } else {
            returnMap.put("rows", new ArrayList<JbxxModelDO>());
            returnMap.put("total", 0);
        }
        ResponseMessage<Map<String, Object>> list = this.kfzdShow(returnMap);

        // long procedure_endTime = System.currentTimeMillis(); //获取结束时间
        // System.out.println("程序运行时间： " + (procedure_endTime - procedure_startTime) + "ms");
        // System.err.println("========邹嘉、吴宝============");
        // System.err.println("参数" + JSONUtil.toJson(variables));
        // System.err.println("============================");
        return  list;

    }
}