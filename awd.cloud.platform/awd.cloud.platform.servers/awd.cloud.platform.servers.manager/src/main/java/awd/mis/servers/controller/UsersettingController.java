/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

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
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.UsersettingEntity;
import awd.mis.servers.service.UsersettingService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/usersetting")
@AccessLogger("用户应用设置")
@Api(tags = "awd-usersetting",description="提供用户拥有的应用配置管理功能")
public class UsersettingController implements GenericEntityController<UsersettingEntity, String, QueryParamEntity, UsersettingEntity> {

	private UsersettingService usersettingService;


	@Override
    public UsersettingEntity modelToEntity(UsersettingEntity model, UsersettingEntity entity) {
        return model;
    }

    @Autowired
    public void setUsersettingService(UsersettingService usersettingService) {
        this.usersettingService = usersettingService;
    }
	 
	@Override
	public CrudService<UsersettingEntity, String> getService() {
		return usersettingService;
	}
	
	@ApiOperation("根据用户id和应用id,查询用户对该应用的设置")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})
	@HystrixCommand
	@PostMapping("/getUserSetting")
	@OpenAPI
	public ResponseMessage<List<UsersettingEntity>> getUserSetting(@RequestParam(value="userid")String userid,@RequestParam(value="app")String app){
		return ResponseMessage.ok(usersettingService.getUserSetting(userid,app));
	}
	@ApiOperation("获取用户一个设置")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})
	@GetMapping("/getbykey")
	@OpenAPI
	public ResponseMessage<UsersettingEntity> getBykey(@RequestParam(value="userid") String userid, @RequestParam(value="appcode")String appcode,@RequestParam(value="key") String key){
		QueryParamEntity param=new QueryParamEntity();
		param.and("userid", userid)
		.and("app", appcode)
		.and("key", key);
		UsersettingEntity usersetting=usersettingService.selectSingle(param);
		return ResponseMessage.ok(usersetting);
	}
	
	
	@ApiOperation("批量设置用户设置")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})
	@PostMapping("/setWallForList")
	@OpenAPI
	public ResponseMessage<String> getbykeyForList(@RequestBody  List<Map<String,Object>> list ){
		try {
			for(Map m:list) {
				UsersettingEntity entity = new UsersettingEntity();
				if(m.containsKey("id")) {
					entity.setId(m.get("id").toString());
				}
				entity.setUserid(m.get("userid").toString());
				entity.setKey(m.get("key").toString());
				entity.setValue(m.get("value").toString());
				entity.setApp(m.get("app").toString());
				entity.setJsbh(m.get("jsbh").toString());
				entity.setState("R2");
				System.err.println("***"+JSONObject.toJSONString(entity));
				usersettingService.saveOrUpdate(entity);
			}
			return ResponseMessage.ok("更新成功");
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("更新失败");
		}
		
		
	
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UsersettingEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody UsersettingEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UsersettingEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<UsersettingEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<UsersettingEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	

}
