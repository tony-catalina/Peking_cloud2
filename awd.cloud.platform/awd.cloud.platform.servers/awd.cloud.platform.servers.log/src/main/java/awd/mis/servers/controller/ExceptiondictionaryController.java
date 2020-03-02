/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.HashMap;
import java.util.Map;

import awd.framework.common.utils.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.expands.logging.AccessLogger;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.ExceptiondictionaryEntity;
import awd.mis.servers.service.ExceptiondictionaryService;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;


@RestController
@RefreshScope
@RequestMapping("/exceptiondictionary")
@AccessLogger("Exceptiondictionary")
@Api(consumes = "字典转换错误日志记录")
public class ExceptiondictionaryController implements GenericEntityController<ExceptiondictionaryEntity, String, QueryParamEntity, ExceptiondictionaryEntity> {

	private ExceptiondictionaryService exceptiondictionaryService;


	@Override
    public ExceptiondictionaryEntity modelToEntity(ExceptiondictionaryEntity model, ExceptiondictionaryEntity entity) {
        return model;
    }

    @Autowired
    public void setExceptiondictionaryService(ExceptiondictionaryService exceptiondictionaryService) {
        this.exceptiondictionaryService = exceptiondictionaryService;
    }
	 
	@Override
	public CrudService<ExceptiondictionaryEntity, String> getService() {
		return exceptiondictionaryService;
	}

    @Override
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody ExceptiondictionaryEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<PagerResult<ExceptiondictionaryEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
        return GenericEntityController.super.list(reqest, param);
    }

    @Override
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@RequestParam("id") String id, @RequestBody ExceptiondictionaryEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<ExceptiondictionaryEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody ExceptiondictionaryEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
    
    
}
