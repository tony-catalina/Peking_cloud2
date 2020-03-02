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
import awd.mis.servers.entity.GroupmenusEntity;
import awd.mis.servers.service.GroupmenusService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/groupmenus")
@AccessLogger("分组菜单")
@Api(tags = "awd-groupmenus",description="提供用户组应用菜单管理功能") 
public class GroupmenusController implements GenericEntityController<GroupmenusEntity, String, QueryParamEntity, GroupmenusEntity> {

	private GroupmenusService groupmenusService;


	@Override
    public GroupmenusEntity modelToEntity(GroupmenusEntity model, GroupmenusEntity entity) {
        return model;
    }

    @Autowired
    public void setGroupmenusService(GroupmenusService groupmenusService) {
        this.groupmenusService = groupmenusService;
    }
	 
	@Override
	public CrudService<GroupmenusEntity, String> getService() {
		return groupmenusService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<GroupmenusEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody GroupmenusEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<GroupmenusEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody GroupmenusEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody GroupmenusEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	

	
	
}
