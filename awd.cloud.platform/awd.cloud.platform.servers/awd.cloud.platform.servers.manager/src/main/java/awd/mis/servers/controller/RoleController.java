/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
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
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.RoleEntity;
import awd.mis.servers.service.RoleService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/role")
@AccessLogger("角色")
@Api(tags = "awd-role",description="提供系统角色管理功能")
public class RoleController implements GenericEntityController<RoleEntity, String, QueryParamEntity, RoleEntity> {

	private RoleService roleService;


	@Override
    public RoleEntity modelToEntity(RoleEntity model, RoleEntity entity) {
        return model;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
	 
	@Override
	public CrudService<RoleEntity, String> getService() {
		return roleService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<RoleEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody RoleEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<RoleEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody RoleEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody RoleEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	

}
