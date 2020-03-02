/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.SettingEntity;
import awd.mis.servers.service.SettingService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/setting")
@AccessLogger("应用设置")
@Api(tags = "awd-setting", description = "提供应用设置管理功能")
public class SettingController implements GenericEntityController<SettingEntity, String, QueryParamEntity, SettingEntity> {

	private SettingService settingService;


	@Override
    public SettingEntity modelToEntity(SettingEntity model, SettingEntity entity) {
        return model;
    }

    @Autowired
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }
	 
	@Override
	public CrudService<SettingEntity, String> getService() {
		return settingService;
	}
	
	
	@PostMapping("/add")
	@ApiOperation("设置属性值")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("{setSetting}")
	@HystrixCommand
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "appcode", value = "应用代码", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "progroup", value = "属性组", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "type", value = "属性类型", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "proname", value = "KEY", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "pronamezh", value = "属性名", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "provalue", value = "属性值", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "user", value = "操作人", required = true)
	})
	@OpenAPI
	public ResponseMessage<Boolean> setSetting(@RequestParam(value="appcode") String appcode,
										@RequestParam(value="progroup") String progroup,
										@RequestParam(value="type") String type,
										@RequestParam(value="proname") String proname,
										@RequestParam(value="pronamezh") String pronamezh,
										@RequestParam(value="provalue")String provalue,
										@RequestParam(value="user")String user){
		QueryParamEntity query=new QueryParamEntity();
		query.and("appcode", TermType.eq, appcode)
		.and("progroup", TermType.eq, progroup)
		.and("proname", TermType.eq, proname);
		SettingEntity setting=settingService.selectSingle(query);
		if(setting==null) {
			setting=new SettingEntity();
			setting.setAppcode(appcode);
			setting.setProgroup(progroup);	
			setting.setProname(proname);
			setting.setProtype(type);
			setting.setProlist(pronamezh);	
			setting.setProvalue(provalue);				
			setting.setCreator(user);
		}else {
			setting.setProlist(pronamezh);	
			setting.setProvalue(provalue);	
			setting.setUpdator(user);
		}
		try {
			settingService.saveOrUpdate(setting);
		}catch (Exception e) {
			return ResponseMessage.ok(false);
		}
		return ResponseMessage.ok(true);
	}
	
	@PostMapping("/settingListInit")
	@ApiOperation("设置属性值")
	@ApiResponses({
		@ApiResponse(code = 200, message = "查询成功"),
		@ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"),
		@ApiResponse(code = 404, message = "数据不存在")
	})	
	@HystrixCommand
	@OpenAPI
	public ResponseMessage<String> settingListInit(@RequestParam(value="appcode") String appcode,
			@RequestBody List<Map<String, Object>> settingList, 
			@RequestParam(value="user")String user){
		if (settingList.size() == 0) {
			return ResponseMessage.error("未获取到系统设置json数据");
		}
		Result<String> result = settingService.initSettingList(appcode, settingList, user);
		if (result.getCode() == 200) {
			return ResponseMessage.ok(result.getMsg());
		}else {
			return ResponseMessage.error(result.getMsg());
		}
		
		/*try {
			settingService.initSettingList(appcode, settingList, user);
		}catch (Exception e) {
			return ResponseMessage.ok(false);
		}
		return ResponseMessage.ok(true);*/
	}
	
	
	
	
	
	@PostMapping("getSetting")
	@ApiOperation("获取应用设置")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("{getSetting}")
	@HystrixCommand
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "appcode", value = "应用代码", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "progroup", value = "属性组", required = true)
	})
	@OpenAPI
	public ResponseMessage<List<SettingEntity>> getSetting(
													@RequestParam(value="appcode") String appcode,
													@RequestParam(value="progroup") String progroup){
		List<SettingEntity> list=settingService.getSetting(appcode,progroup);
		return ResponseMessage.ok(list);
	}
	
	
	@PostMapping("getAppSetting")
	@ApiOperation("获取应用设置")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("{getSetting}")
	@HystrixCommand
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "appcode", value = "应用代码", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "jsbh", value = "监所编号", required = true),
						 @ApiImplicitParam(paramType = "form", dataType = "String", name = "proname", value = "属性名", required = true)
	})
	@OpenAPI
	public ResponseMessage<String> getAppSetting(
													@RequestParam(value="appcode") String appcode,
													@RequestParam(value="jsbh") String jsbh,
													@RequestParam(value="proname") String proname){
		String result=settingService.getAppSetting(appcode,jsbh,proname);
		return ResponseMessage.ok(result);
	}
	
	@GetMapping("getByKey")
	@ApiOperation("判断设置是否存在")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})
	@OpenAPI
	public ResponseMessage<SettingEntity> getSet(@RequestParam(value="appid")String appid,
												 @RequestParam(value="key")String key){
		QueryParamEntity paramEntity=new QueryParamEntity();
		paramEntity.and("appcode", appid)
		.and("proname", key);
		SettingEntity setting=settingService.selectSingle(paramEntity);
		return ResponseMessage.ok(setting);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody SettingEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody SettingEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<SettingEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<SettingEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody SettingEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	
	
	

}
