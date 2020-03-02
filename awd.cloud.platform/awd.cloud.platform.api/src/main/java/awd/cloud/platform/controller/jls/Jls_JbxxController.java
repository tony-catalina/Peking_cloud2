/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.jls.model.JbxxModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.Term;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/jbxx")
@Api(tags = "kss-jbxx",description = "Jbxx") 
public class Jls_JbxxController extends PublicService{
	
	
	@Autowired
	private JlsServerApis jlsServersApi;
	
	    @OpenAPI
		@ApiOperation("基本信息查询")
		@GetMapping("/jbxxlistQuery")
		@HystrixCommand
		@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
				@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
		public ResponseMessage<Map<String, Object>> jbxxlist_Query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
	    	
	    	String interfaceId = "/v4/jls/jbxx/jbxxlistQuery";
			String state = "R8";
	    	try {
	    		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
				if (maps.getStatus() != 200) {
					return ResponseMessage.error(maps.getMessage());
				}
				//查询参数
				QueryParam queryParam = new QueryParam();
				// 区分是否为出入监登记
		        //String crj = request.getParameter("crj");
		        if(!StringUtils.isNullOrEmpty(maps.getResult().get("crj"))){
		            Term term = new Term();
		            if ("1".equals(maps.getResult().get("crj"))) {
		                term.or("crjbj" ,TermType.eq, "01").or("crjbj", TermType.isnull, "1");
		            }else if("2".equals(maps.getResult().get("crj"))){
		                term.or("crjbj" ,TermType.not, "01").and("crjbj", TermType.notnull, "1");
		            }
		            queryParam.addTerm(term);
		            queryParam.and("state", state);
		        }
		        ResponseMessage<PagerResult<JbxxModel>> result = jlsServersApi.jbxxQueryForPage(queryParam);
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
	
}
