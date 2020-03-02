/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.LoginlogsEntity;
import awd.mis.servers.model.LoginlogsModel;
import awd.mis.servers.service.LoginlogsService;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/loginlogs")
@AccessLogger("登录日志")
@Api(tags = "awd-login-log", description = "提供基本登录日志管理功能")
public class LoginlogsController implements GenericEntityController<LoginlogsEntity, String, QueryParamEntity, LoginlogsModel> {

	@Autowired
	private LoginlogsService loginlogsService;


	@Override
    public LoginlogsEntity modelToEntity(LoginlogsModel model, LoginlogsEntity entity) {
		BeanUtils.copyProperties(model, entity);
		entity.setCreator(entity.getLoginname());
        return entity;
    }

    @Autowired
    public void setLoginlogsService(LoginlogsService loginlogsService) {
        this.loginlogsService = loginlogsService;
    }
	 
	@Override
	public CrudService<LoginlogsEntity, String> getService() {
		return loginlogsService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<LoginlogsEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@ApiOperation("用户登录")
	@PostMapping(path = {"/login"})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody LoginlogsModel data) {
		data.setLogintime(Calendar.getInstance().getTime());
		return GenericEntityController.super.save(data);
	}


	@ApiOperation("用户登出")
	@AccessLogger("{update_by_primary_key}")
	@PutMapping(path = {"/logout/{id}"})
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable("id")String id) {
		LoginlogsEntity entity=loginlogsService.selectByPk(id);
		entity.setLogouttime(Calendar.getInstance().getTime());
		return ResponseMessage.ok(loginlogsService.updateByPk(id, entity));
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<LoginlogsEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody LoginlogsModel data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody LoginlogsModel data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	
	
	
	
	

}
