package awd.cloud.platform.controller.jls;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.base.model.Variables;
import awd.bj.jls.model.JbxxModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/jls_process")
@Api(tags="jls_process",description="jls_process")
public class Jls_ProcessController extends PublicService{
	
	@Autowired
    private JlsServerApis jlsServerApis;

	@OpenAPI
    @ApiOperation("获取流程信息")
    @GetMapping("/getProcessTaskList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> getProcessTaskList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		
		String interfaceId = "/v4/jlsjls_process/getProcessTaskList";
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
	        ResponseMessage<PagerResult<JbxxModel>> result = jlsServerApis.getListCustom(variables);
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
	        return  list;
	}
	
}
