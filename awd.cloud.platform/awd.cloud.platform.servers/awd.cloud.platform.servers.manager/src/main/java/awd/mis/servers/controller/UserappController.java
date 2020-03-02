/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.Userapp;
import awd.mis.servers.entity.UserappEntity;
import awd.mis.servers.service.UserappService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/userapp")
@AccessLogger("APP禁用管理")
@Api(tags = "awd-userapp",description="提供用户应用管理功能") 
public class UserappController implements GenericEntityController<UserappEntity, String, QueryParamEntity, UserappEntity> {

	private UserappService userappService;


	@Override
    public UserappEntity modelToEntity(UserappEntity model, UserappEntity entity) {
        return model;
    }

    @Autowired
    public void setUserappService(UserappService userappService) {
        this.userappService = userappService;
    }
	 
	@Override
	public CrudService<UserappEntity, String> getService() {
		return userappService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<UserappEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	
	@PostMapping("forbid")
	@OpenAPI
	public ResponseMessage<String> forbid(@RequestParam("user") String user,@RequestBody Userapp userapp){
		return ResponseMessage.ok(userappService.forbid(user,userapp));
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UserappEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody UserappEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<UserappEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UserappEntity data) {
		return GenericEntityController.super.save(data);
	}
	
	
}
