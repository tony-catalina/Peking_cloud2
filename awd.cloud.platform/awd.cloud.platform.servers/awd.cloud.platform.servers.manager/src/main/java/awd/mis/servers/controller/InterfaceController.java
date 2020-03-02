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


import awd.mis.servers.model.InterfaceModel;
import awd.mis.servers.tools.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.InterfaceEntity;
import awd.mis.servers.service.InterfaceService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/interface")
@AccessLogger("接口")
@Api(tags = "awd-interface", description = "提供已存在接口查询功能") 
public class InterfaceController implements GenericEntityController<InterfaceEntity, String, QueryParamEntity, InterfaceEntity> {

	private InterfaceService interfaceService;


	@Override
    public InterfaceEntity modelToEntity(InterfaceEntity model, InterfaceEntity entity) {
        return model;
    }

    @Autowired
    public void setInterfaceService(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
    }
	 
	@Override
	public CrudService<InterfaceEntity, String> getService() {
		return interfaceService;
	}

	@Override
	@GetMapping
	@OpenAPI
	@ApiOperation("自定义查询可提供的接口")
	public ResponseMessage<PagerResult<InterfaceEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		System.err.println("============"+JSONObject.toJSONString(arg1));
		System.err.println("============"+JSONObject.toJSONString(arg1));
		System.err.println("============"+JSONObject.toJSONString(arg1));
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody InterfaceEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override	
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable("id") String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody InterfaceEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable("id") String id, @RequestBody InterfaceEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	
	@PostMapping("/getinterfaceByAppcode")
	@OpenAPI
	@ApiOperation("根据appcode查询绑定的api的详细参数")
	@ResponseBody
	public ResponseMessage<PagerResult<InterfaceEntity>> getinterfaceByAppcode(@RequestParam(value="appcode",required=true)String appcode,@RequestParam(value="bdzt",required=true)String bdzt,@RequestParam(value="start",required=true)String start,@RequestParam(value="end",required=true)String end){
		System.err.println(appcode);
		System.err.println(bdzt);
		return ResponseMessage.ok(interfaceService.selectByappcode(appcode, bdzt,start,end));
		
	}
	
	@GetMapping("/getApiTreeNode")
	@OpenAPI
	@ApiOperation("获取api树型结构父节点种类")
	@ResponseBody
	public List getApiTreeNode(){
		List<InterfaceEntity>	l=interfaceService.getApiParentNode();
		List list = new ArrayList<>();
		for(InterfaceEntity entry : l ) {
			list.add(entry.getInterfaceType());
		}
		return list;
	}

	/**
	 * 获取接口信息与appcode对应的接口绑定状态
	 * @return
	 */
	@PostMapping("/selectInterfaceAndBdzt")
	@OpenAPI
	@ApiOperation("获取接口信息与appcode对应的接口绑定状态")
	public ResponseMessage<PagerResult<InterfaceModel>> interfaceBdztQuery(@RequestBody Map<String,Object> param){
		return interfaceService.interfaceBdztQuery(param);
	}

	@GetMapping("/apiDshslQuery")
	@OpenAPI
	@ApiOperation("通过appcode查询当前应用api待审核数")
	public List<Integer> apiDshslQuery(String[] appcodes){
		return interfaceService.apiDshslQuery(appcodes);
	}

	/**
	 * app商城api树查询
	 * @return
	 */
	@GetMapping("/getTree")
	@OpenAPI
	public List<Map<String,Object>> getApiTree(){
		return interfaceService.getTree();
	}
}
