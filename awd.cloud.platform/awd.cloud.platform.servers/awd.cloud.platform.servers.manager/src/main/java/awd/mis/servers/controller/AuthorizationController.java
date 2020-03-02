package awd.mis.servers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.model.Userinfo;
import awd.mis.servers.service.AuthenticationService;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;



@RestController
@AccessLogger("授权")
@RequestMapping("/authorization")
@Api(tags = "awd-authorization", description = "提供基本的授权功能")
public class AuthorizationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthenticationService authenticationService;
    
    @ApiOperation("登录账号查询")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
    @GetMapping("getUserByName")
    @OpenAPI
    public ResponseMessage<Userinfo>  getUserByPassword(@RequestParam("jsbh")String jsbh,@RequestParam("username")String username){
    	return ResponseMessage.ok(authenticationService.getUserinfo(jsbh,username));
    }
    
    
    @ApiOperation("根据账号获取用户信息")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
    @OpenAPI
    @GetMapping("getUserID")
    public ResponseMessage<Userinfo>  getUserByID(@RequestParam("id")String id){
    	return ResponseMessage.ok(authenticationService.getUserinfo(id));
    }
    
    

}
