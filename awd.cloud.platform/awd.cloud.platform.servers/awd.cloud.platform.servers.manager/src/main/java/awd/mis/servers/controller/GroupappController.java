/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.ApplunboEntity;
import awd.mis.servers.entity.Groupapp;
import awd.mis.servers.entity.GroupappEntity;
import awd.mis.servers.entity.JsappEntity;
import awd.mis.servers.service.GroupappService;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/groupapp")
@AccessLogger("用户组应用")
@Api(tags = "awd-groupapp",description="提供用户组应用管理功能") 
public class GroupappController implements GenericEntityController<GroupappEntity, String, QueryParamEntity, GroupappEntity> {

	private GroupappService groupappService;


	@Override
    public GroupappEntity modelToEntity(GroupappEntity model, GroupappEntity entity) {
        return model;
    }

    @Autowired
    public void setGroupappService(GroupappService groupappService) {
        this.groupappService = groupappService;
    }
	 
	@Override
	public CrudService<GroupappEntity, String> getService() {
		return groupappService;
	}
	
	@Override
	@GetMapping
	@ApiOperation("自定义查询")
	@AccessLogger("{action_list}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<GroupappEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@ApiOperation("用户组添加禁用APP")
	@AccessLogger("{action_add}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody GroupappEntity data) {
		return GenericEntityController.super.save(data);
	}
	
	@PostMapping("addGroupApp")
	@ApiOperation("用户组添加禁用APP")
	@AccessLogger("{action_add}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> addGroupApp(@RequestBody Groupapp groupapp) {
		return ResponseMessage.ok(groupappService.addGroupApp(groupapp));
	}

}
