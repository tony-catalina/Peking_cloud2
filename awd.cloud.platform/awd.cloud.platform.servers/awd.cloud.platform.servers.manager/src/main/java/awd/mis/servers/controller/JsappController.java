/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;

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
import awd.mis.servers.entity.JsappEntity;
import awd.mis.servers.service.AppService;
import awd.mis.servers.service.JsappService;
import awd.mis.servers.service.MountService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/jsapp")
@AccessLogger("监所APP")
@Api(tags = "awd-jsapp",description="提供监所应用管理功能")
public class JsappController implements GenericEntityController<JsappEntity, String, QueryParamEntity, JsappEntity> {

	private JsappService jsappService;

	@Autowired
	private AppService appService;
	
	@Autowired
	private  MountService  mountService; 

	@Override
    public JsappEntity modelToEntity(JsappEntity model, JsappEntity entity) {
        return model;
    }

    @Autowired
    public void setJsappService(JsappService jsappService) {
        this.jsappService = jsappService;
    }
	 
	@Override
	public CrudService<JsappEntity, String> getService() {
		return jsappService;
	}

	@Override
	@GetMapping
	@ApiOperation("自定义查询")
	@AccessLogger("{action_list}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<JsappEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	

	@PostMapping("/delJsApp")
	@ApiOperation("卸载应用")
	@AccessLogger("{action_del}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<Integer> delJsApp(String appcode,String jsbh) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appcode", appcode);
		map.put("jsbh", jsbh);
		return ResponseMessage.ok(jsappService.delJsApp(map));
	}

	@Override
	@PostMapping
	@ApiOperation("所级管理员用户安装应用")
	@AccessLogger("{action_add}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody JsappEntity data) {
		return ResponseMessage.ok(jsappService.insertJsappAndMount(data));
	}
	
	
	@PostMapping("/getAppEntyListByJsbh")
	@ApiOperation("监所用户安装appEntity list")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<AppEntity>> getAppEntyListByJsbh(String jsbh,String pageIndex,String pageSize) {
		//先查询监所的安装的应用信息
		QueryParamEntity qEntity = new QueryParamEntity();
		qEntity.and("jsbh",TermType.eq, jsbh);
		List<JsappEntity> jsAppList = jsappService.select(qEntity);
		//在根据信息的appcode 获取appentity
		List<AppEntity> appEntyList = new ArrayList<AppEntity>();
		int mount ;
		if (jsAppList != null && jsAppList.size() > 0) {
			for (int i = 0; i < jsAppList.size(); i++) {
				//不从缓存查了，烦
				String appcode = jsAppList.get(i).getAppcode();
				QueryParamEntity query = new QueryParamEntity();
				Set set = new HashSet<>();
				set.add("p1");
				set.add("p2");
				set.add("p3");
				query.setExcludes(set);
				query.and("appcode", TermType.eq,appcode);
				AppEntity app = appService.selectSingle(query);
				if (app != null) {
					try {
						mount=mountService.getAppMountNum(app.getAppcode());
					} catch (Exception e) {
						System.err.println("获取应用 "+jsAppList.get(i).getAppcodeString()+" 的安装数错误");
						mount=0;
					}
					app.setMount(mount);
					appEntyList.add(app);
				}
			}
		}

		List<AppEntity> appPagerList = new ArrayList<AppEntity>();
		int index;
		int size;
		try {
			index = Integer.valueOf(pageIndex).intValue();
			size = Integer.valueOf(pageSize).intValue();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			index = 0;
			size = 12;
		}
		for (int i = index * size; i < appEntyList.size() && i < size + index; i++) {
			appPagerList.add(appEntyList.get(i));
		}

		PagerResult<AppEntity> list = new PagerResult<AppEntity>();
		
		/*list.setData(appEntyList);
		list.setTotal(appEntyList.size());*/
		list.setData(appPagerList);
		list.setTotal(appEntyList.size());
		return ResponseMessage.ok(list);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody JsappEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody JsappEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<JsappEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	

}
