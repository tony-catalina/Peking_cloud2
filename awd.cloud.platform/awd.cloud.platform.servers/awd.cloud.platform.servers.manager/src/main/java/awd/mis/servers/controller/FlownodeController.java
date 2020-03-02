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
import awd.mis.servers.entity.FlownodeEntity;
import awd.mis.servers.service.FlownodeService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/flownode")
@AccessLogger("流程节点")
@Api(tags = "awd-flownode", description = "提供流程事务管理功能")
public class FlownodeController implements GenericEntityController<FlownodeEntity, String, QueryParamEntity, FlownodeEntity> {

    private FlownodeService flownodeService;


    @Override
    public FlownodeEntity modelToEntity(FlownodeEntity model, FlownodeEntity entity) {
        return model;
    }

    @Autowired
    public void setFlownodeService(FlownodeService flownodeService) {
        this.flownodeService = flownodeService;
    }

    @Override
    public CrudService<FlownodeEntity, String> getService() {
        return flownodeService;
    }

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<FlownodeEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable("id")String id, @RequestBody FlownodeEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<FlownodeEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody FlownodeEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody FlownodeEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	
	

    


}
