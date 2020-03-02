/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.expands.logging.AccessLogger;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.param.Sort;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.mis.servers.entity.HfEntity;
import awd.mis.servers.entity.PlEntity;
import awd.mis.servers.service.PlService;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/pl")
@AccessLogger("Pl")
@Api(value = "Pl", description = "应用评论功能") 
public class PlController implements GenericEntityController<PlEntity, String, QueryParamEntity, PlEntity> {

	private PlService plService;


	@Override
    public PlEntity modelToEntity(PlEntity model, PlEntity entity) {
        return model;
    }

    @Autowired
    public void setPlService(PlService plService) {
        this.plService = plService;
    }
	 
	@Override
	public CrudService<PlEntity, String> getService() {
		return plService;
	}
	
	@GetMapping(path = {"/getPlEntyListByAppcode"})
	@ApiOperation("应用自定义查询")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
					@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI	
	public ResponseMessage<PagerResult<PlEntity>> getPlEntyListByAppcode(HttpServletRequest request, QueryParamEntity param){
		param.and("state", TermType.eq,"R2");	//数据状态位为正常的数据
		
        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        sort.setName("plsj");
        sort.setOrder("desc");
        sorts.add(sort);
        param.setSorts(sorts);
        param.setPaging(true);
		System.err.println(JSON.toJSON(param));
		System.err.println(JSON.toJSON(request.getParameterMap()));
		return ResponseMessage.ok(plService.selectPager(param));
		//return GenericEntityController.super.list(request, query);
	} 

	@Override
	@PostMapping("/saveOrUpdate")
	@ApiOperation("保存数据,如果评论不存在则新增一条数据")
	@AccessLogger("{save_or_update}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody PlEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	public ResponseMessage<PagerResult<PlEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		arg1.and("state", TermType.eq,"R2");
		return GenericEntityController.super.list(arg0, arg1);
	}

	
	@PostMapping(path = {"/removeById"})
	@ApiOperation("根据ID移除评论——吧state变为R3")
	@AccessLogger("{delete_by_primary_key}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> removeById(String id) {
		PlEntity data = plService.selectByPk(id);
		data.setState("R3");
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	
	@Override
	@PostMapping(path = {"/deleteById"})
	@ApiOperation("根据ID删除评论")
	@AccessLogger("{delete_by_primary_key}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@PostMapping("/save")
	@ApiOperation("保存数据,如果评论不存在则新增一条数据")
	@AccessLogger("{save}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody PlEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	@OpenAPI
	public ResponseMessage<PlEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody PlEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	
	
}
