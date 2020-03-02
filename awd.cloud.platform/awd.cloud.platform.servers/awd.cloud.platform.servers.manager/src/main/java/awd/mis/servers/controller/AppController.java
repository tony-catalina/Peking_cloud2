/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.service.AppService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/app")
@AccessLogger("应用")
@Api(tags = "awd-app", description = "提供App管理功能")
public class AppController implements GenericEntityController<AppEntity, String, QueryParamEntity, AppEntity> {

	private AppService appService;


	@Override
    public AppEntity modelToEntity(AppEntity model, AppEntity entity) {
        return model;
    }

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }
	 
	@Override
	public CrudService<AppEntity, String> getService() {
		return appService;
	}

	@Override
	@RequestMapping(method = {RequestMethod.GET})
	@ApiOperation("应用自定义查询")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<AppEntity>> list(HttpServletRequest request, QueryParamEntity param) {
		Set<String> exclude=new HashSet<String>();
		int flag=0;
		for(int i=0;i<param.getTerms().size();i++) {
			JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(param.getTerms().get(i)));
			if("code".equals(object.get("column"))) {
				flag=1;
				break;
			}
		}
		if(flag ==1 ) {
			param.and("state", TermType.eq,"R2");
		}else {
			exclude.add("p1");
			exclude.add("p2");
			exclude.add("p3");
			param.setExcludes(exclude);
			param.and("state", TermType.eq,"R2");	//数据状态位为正常的数据
		}
		try {
			PagerResult<AppEntity> appList = GenericEntityController.super.list(request, param).getResult();
			appList.getData().forEach(app->{
				CacheUtils.get().setAppCacheWithoutByte(app,app.getAppcode());
			});
			return ResponseMessage.ok(appList);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception--->");
			return ResponseMessage.error("获取app信息出错");
		}
	}
	

	@Override
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID查询应用信息")
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<AppEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	
	

	@Override
	@DeleteMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID删除应用")
	@AccessLogger("{delete_by_primary_key}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	

	@Override
	@PutMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID更新应用")
	@AccessLogger("{update_by_primary_key}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody AppEntity data) {
		return ResponseMessage.ok(appService.updateByPk(id, data));
	}

	@Override
	@PostMapping
	@ApiOperation("新增一条应用信息")
	@AccessLogger("{action_add}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody AppEntity data) {
		return ResponseMessage.ok(appService.insert(data));
	}

	@Override
	@PatchMapping
	@ApiOperation("保存数据,如果应用不存在则新增一条数据")
	@AccessLogger("{save_or_update}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody AppEntity data) {
		System.err.println(JSON.toJSONString(data));
		System.err.println(JSON.toJSONString(data));
		System.err.println(JSON.toJSONString(data));
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	@GetMapping("/selectSingle")
	@ApiOperation("应用自定义查询一个app信息")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<AppEntity> selectSingle(HttpServletRequest request, QueryParamEntity param) {
		Set<String> exclude=new HashSet<String>();
		exclude.add("p1");
		exclude.add("p2");
		exclude.add("p3");
		param.setExcludes(exclude);
		param.and("state", TermType.eq,"R2");	//数据状态位为正常的数据
		if (StringUtils.isNullOrEmpty(appService.selectSingle(param))) {
			return ResponseMessage.error(400, "没有获取到app信息");
		}
		return ResponseMessage.ok(appService.selectSingle(param));
	}
	
	/**
	 * 根据jsbh查询jsapp，然后在查询对应的app信息，返回applist
	 * @return
	 */
	@GetMapping("/getApplistWithJsappByJsbh")
	@ApiOperation("根据jsbh查询jsapp，然后在查询对应的app信息，返回applist")
	//@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<List<AppEntity>> getApplistWithJsappByJsbh(HttpServletRequest request, QueryParamEntity  params) {
		return ResponseMessage.ok(appService.getApplistWithJsappByJsbh(params));
	}
	
	
	@GetMapping("/test")
	@OpenAPI
	public ResponseMessage<String> test(){
		return appService.test();
	}
	
	
	
}
