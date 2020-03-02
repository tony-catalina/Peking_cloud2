/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.ExceptionmessageEntity;
import awd.mis.servers.service.ExceptionmessageService;
import io.swagger.annotations.Api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
@RequestMapping("/exceptionmessage")
@AccessLogger("Exceptionmessage")
@Api(value = "Exceptionmessage")
public class ExceptionmessageController implements GenericEntityController<ExceptionmessageEntity, String, QueryParamEntity, ExceptionmessageEntity> {

    private ExceptionmessageService exceptionmessageService;


    @Override
    public ExceptionmessageEntity modelToEntity(ExceptionmessageEntity model, ExceptionmessageEntity entity) {
        return model;
    }

    @Autowired
    public void setExceptionmessageService(ExceptionmessageService exceptionmessageService) {
        this.exceptionmessageService = exceptionmessageService;
    }

    @Override
    public CrudService<ExceptionmessageEntity, String> getService() {
        return exceptionmessageService;
    }

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody ExceptionmessageEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<ExceptionmessageEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<ExceptionmessageEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody ExceptionmessageEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ExceptionmessageEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
    
    
}
