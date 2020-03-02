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
import awd.mis.servers.entity.Userrole;
import awd.mis.servers.entity.UserroleEntity;
import awd.mis.servers.service.UserroleService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/userrole")
@AccessLogger("用户角色")
@Api(tags = "awd-userrole",description="提供用户角色管理功能")
public class UserroleController implements GenericEntityController<UserroleEntity, String, QueryParamEntity, UserroleEntity> {

	private UserroleService userroleService;


	@Override
    public UserroleEntity modelToEntity(UserroleEntity model, UserroleEntity entity) {
        return model;
    }

    @Autowired
    public void setUserroleService(UserroleService userroleService) {
        this.userroleService = userroleService;
    }
	 
	@Override
	public CrudService<UserroleEntity, String> getService() {
		return userroleService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<UserroleEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	@PostMapping("batchAdd")
	@OpenAPI
	public ResponseMessage<String> batchAdd(@RequestParam(value="user") String user,@RequestBody Userrole userrole){
		String result=userroleService.batchAdd(user,userrole);
		return ResponseMessage.ok(result);
	}

	@Override
	@OpenAPI
	public ResponseMessage<UserroleEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UserroleEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody UserroleEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UserroleEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
	
	

}
