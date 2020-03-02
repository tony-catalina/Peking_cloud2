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
import org.springframework.web.bind.annotation.PutMapping;
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
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.PublishEntity;
import awd.mis.servers.service.PublishService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/publish")
@AccessLogger("发布")
@Api(tags = "awd-publish", description = "提供App发布功能")
public class PublishController implements GenericEntityController<PublishEntity, String, QueryParamEntity, PublishEntity> {

	private PublishService publishService;


	@Override
    public PublishEntity modelToEntity(PublishEntity model, PublishEntity entity) {
        return model;
    }

    @Autowired
    public void setPublishService(PublishService publishService) {
        this.publishService = publishService;
    }
	 
	@Override
	public CrudService<PublishEntity, String> getService() {
		return publishService;
	}

	@Override
	@RequestMapping(method = {RequestMethod.GET})
	@ApiOperation("应用自定义查询")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<PublishEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@PostMapping
	@ApiOperation("发布应用")
	@AccessLogger("{action_add}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody PublishEntity data) {
		return GenericEntityController.super.save(data);
	}
	
	@Override
	@PutMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID更新已发布应用")
	@AccessLogger("{update_by_primary_key}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody PublishEntity data) {
		return ResponseMessage.ok(publishService.updateByPk(id, data));
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PublishEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody PublishEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	

}
