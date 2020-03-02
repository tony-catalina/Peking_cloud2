/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSONObject;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.BarEntity;
import awd.mis.servers.service.BarService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RefreshScope
@RequestMapping("/bar")
@AccessLogger("办案人信息")
@Api(tags = "awd-bar", description = "提供办案人员信息管理功能")
public class BarController implements GenericEntityController<BarEntity, String, QueryParamEntity, BarEntity> {

	private BarService barService;


	@Override
    public BarEntity modelToEntity(BarEntity model, BarEntity entity) {
        return model;
    }

    @Autowired
    public void setBarService(BarService barService) {
        this.barService = barService;
    }
	 
	@Override
	public CrudService<BarEntity, String> getService() {
		return barService;
	}

	@Override
	@ApiOperation("保存数据,如果应用不存在则新增一条数据")
	@AccessLogger("{save_or_update}")
	@PatchMapping
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody BarEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@PostMapping
	@ApiOperation("新增一条办案人信息")
	@AccessLogger("{action_add}")
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody BarEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@DeleteMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID删除办案人")
	@AccessLogger("{delete_by_primary_key}")
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@GetMapping@RequestMapping(method = {RequestMethod.GET})
	@ApiOperation("办案人自定义查询")
	@AccessLogger("{dynamic_query}")
	@OpenAPI
	public ResponseMessage<PagerResult<BarEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
		System.err.println("数据"+JSONObject.toJSONString(param));
		ResponseMessage<PagerResult<BarEntity>> list  = GenericEntityController.super.list(reqest, param);
		System.err.println(JSONObject.toJSONString(list));
		return list;
	}

	@Override
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID查询办案人信息")
	@AccessLogger("{get_by_id}")
	@OpenAPI
	public ResponseMessage<BarEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody BarEntity data) {
		System.err.println(id+"--------------");
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	
	

}
