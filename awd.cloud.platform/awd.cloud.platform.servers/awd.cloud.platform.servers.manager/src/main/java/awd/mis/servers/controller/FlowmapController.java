/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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
import com.ace.cache.parser.ICacheResultParser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.entity.FlowmapEntity;
import awd.mis.servers.entity.FlownodeEntity;
import awd.mis.servers.entity.RequestParameters;
import awd.mis.servers.entity.SettingEntity;
import awd.mis.servers.service.FlowmapService;
import awd.mis.servers.service.FlownodeService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheUtils;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/flowmap")
@AccessLogger("流程图信息")
@Api(tags = "awd-flowmap", description = "提供基本流程管理功能")
public class FlowmapController
		implements GenericEntityController<FlowmapEntity, String, QueryParamEntity, FlowmapEntity> {

	private FlowmapService flowmapService;

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private FlownodeService flownodeService;
	
	@Override
	public FlowmapEntity modelToEntity(FlowmapEntity model, FlowmapEntity entity) {
		return model;
	}

	@Autowired
	public void setFlowmapService(FlowmapService flowmapService) {
		this.flowmapService = flowmapService;
	}

	@Override
	public CrudService<FlowmapEntity, String> getService() {
		return flowmapService;
	}

	/**
	 * 对流程信息进行保存和缓存管理
	 * @param maps
	 * @return
	 * @throws JsonProcessingException
	 */
 	@ApiOperation("对流程信息进行保存和缓存管理")
	@ApiResponses({ 
        @ApiResponse(code = 200, message = "保存成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("/loopAdd")
	@HystrixCommand
	@PostMapping("/loopAdd")
 	@OpenAPI
	public boolean loopAdd(@RequestBody Map<String,Object> map) throws JsonProcessingException {

		boolean flag = flowmapService.loopAdd(map);
		if(!flag) {
			return false;
		}
		return true;
	}
 	
 	/**
 	 * 删除流程信息和对应的缓存
 	 * @param mapname
 	 * @return
 	 */
 	@ApiOperation("删除流程信息和对应的缓存")
	@ApiResponses({
        @ApiResponse(code = 200, message = "保存成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("/deleteByMapName")
	@HystrixCommand
	@PostMapping("/deleteByMapName")
 	@OpenAPI
	public boolean delete (@RequestParam("mapname") String mapname) {
		String id = flowmapService.deleteByMapname(mapname);
		
		if("".equals(id)) {
			return false;
		}
		Map<String,String[]> map  =Maps.newHashMap();
		
		map.put("flowmapid$eq", new String[] {id});
		
		List<FlownodeEntity > lists = flownodeService.select(QueryParamEntity.processRequestParams(map));
 
		String mapKey = CacheUtils.CACHE_FLOWMAP + "MAP" + "_" + mapname;
		redisUtils.remove(mapKey);

		String nodeKey = CacheUtils.CACHE_FLOWNODE + "NODE" + "_" ;
		lists.forEach(a -> {
			redisUtils.remove(nodeKey+a.getNodecode()+"_"+mapname.split(":")[1]);		
		});
		
	return true;
	}


	@PostMapping("/updateFlowMapFlowNodeCache")
	@OpenAPI
	public void updateFlowMapFlowNodeCache(@RequestBody RequestParameters requestParameters){

		flowmapService.updateFlowMapFlowNodeCache(requestParameters);

	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable("id")String id, @RequestBody FlowmapEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody FlowmapEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody FlowmapEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<FlowmapEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<FlowmapEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	


}
