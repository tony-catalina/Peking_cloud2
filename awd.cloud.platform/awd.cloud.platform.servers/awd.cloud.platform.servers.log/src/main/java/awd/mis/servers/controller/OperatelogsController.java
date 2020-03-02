/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.OperatelogsEntity;
import awd.mis.servers.model.OperatelogsModel;
import awd.mis.servers.service.OperatelogsService;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/operatelogs")
@AccessLogger("应用操作日志")
@Api(tags = "awd-operate-log", description = "提供应用操作日志管理功能")
public class OperatelogsController implements GenericEntityController<OperatelogsEntity, String, QueryParamEntity, OperatelogsModel> {

	private OperatelogsService operatelogsService;


	@Override
    public OperatelogsEntity modelToEntity(OperatelogsModel model, OperatelogsEntity entity) {
		BeanUtils.copyProperties(model, entity);
        return entity;
    }

    @Autowired
    public void setOperatelogsService(OperatelogsService operatelogsService) {
        this.operatelogsService = operatelogsService;
    }
	 
	@Override
	public CrudService<OperatelogsEntity, String> getService() {
		return operatelogsService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<OperatelogsEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody OperatelogsModel data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody OperatelogsModel data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody  OperatelogsModel data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<OperatelogsEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	

}
