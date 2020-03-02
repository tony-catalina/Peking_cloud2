/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import awd.mis.servers.entity.CcicEntity;
import awd.mis.servers.service.CcicService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/ccic")
@AccessLogger("CCIC")
@Api(tags = "awd-ccic", description = "提供基本在逃比对功能")
public class CcicController implements GenericEntityController<CcicEntity, String, QueryParamEntity, CcicEntity> {

	private CcicService ccicService;


	@Override
    public CcicEntity modelToEntity(CcicEntity model, CcicEntity entity) {
        return model;
    }

    @Autowired
    public void setCcicService(CcicService ccicService) {
        this.ccicService = ccicService;
    }
	 
	@Override
	public CrudService<CcicEntity, String> getService() {
		return ccicService;
	}

	@Override
	@RequestMapping(method = {RequestMethod.GET})
	@OpenAPI
	public ResponseMessage<PagerResult<CcicEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
		return GenericEntityController.super.list(reqest, param);
	}

	@Override
	@DeleteMapping(path = {"/{id:.+}"})
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@PostMapping
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody CcicEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody CcicEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody CcicEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<CcicEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	
	
	
	
	

}
