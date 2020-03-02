package awd.cloud.suppers.interfaces.controller.interfaces;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.cloud.suppers.interfaces.service.interfaces.UserinfoService;
import awd.cloud.suppers.interfaces.service.jls.JbxxService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RefreshScope
@RequestMapping("/userinfo")
@AccessLogger("interface")
@Api(value = "interface") 
public class UserinfoController {

	@Autowired
	private UserinfoService userinfoservice;
	
	@OpenAPI
	@ApiOperation("查询manager库的userinfo信息")
	@RequestMapping(value = "/getUserByType",method = {RequestMethod.POST})
	public ResponseMessage<PagerResult<Map<String, Object>>> getUserByType() {
		
		userinfoservice.getUserByType();
		
		return null; 
	}
	
	@OpenAPI
	@ApiOperation("把manager库的userinfo信息保存到interface库里面")
	@RequestMapping(value = "/setUserinfoFromManager",method = {RequestMethod.POST})
	public ResponseMessage<PagerResult<Map<String, Object>>> setUserinfoFromManager() {
		
		userinfoservice.setUserinfoFromManager();
		
		return null; 
	}
	
	
}
