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
import awd.mis.servers.entity.RolemenusEntity;
import awd.mis.servers.service.RolemenusService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/rolemenus")
@AccessLogger("角色菜单")
@Api(tags = "awd-rolemenu",description="提供角色菜单管理功能")
public class RolemenusController implements GenericEntityController<RolemenusEntity, String, QueryParamEntity, RolemenusEntity> {

	private RolemenusService rolemenusService;


	@Override
    public RolemenusEntity modelToEntity(RolemenusEntity model, RolemenusEntity entity) {
        return model;
    }

    @Autowired
    public void setRolemenusService(RolemenusService rolemenusService) {
        this.rolemenusService = rolemenusService;
    }
	 
	@Override
	public CrudService<RolemenusEntity, String> getService() {
		return rolemenusService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<RolemenusEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable("id")String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<RolemenusEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody RolemenusEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody RolemenusEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@OpenAPI
	public ResponseMessage<String> save(@RequestBody RolemenusEntity data) {
	
		return GenericEntityController.super.save(data);
	}
	
	
	

}
