/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.service.MenusService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.Result;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/menus")
@AccessLogger("应用菜单")
@Api(tags = "awd-menus",description="提供应用菜单管理功能")
public class MenusController implements GenericEntityController<MenusEntity, String, QueryParamEntity, MenusEntity> {

	private MenusService menusService;


	@Override
    public MenusEntity modelToEntity(MenusEntity model, MenusEntity entity) {
        return model;
    }

    @Autowired
    public void setMenusService(MenusService menusService) {
        this.menusService = menusService;
    }
	 
	@Override
	public CrudService<MenusEntity, String> getService() {
		return menusService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<MenusEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
	@OpenAPI
	@RequestMapping(value="/add",method=RequestMethod.POST)
    public ResponseMessage<String> menusSave(@RequestParam("appcode")String appcode, @RequestParam("menu")String menu, @RequestParam("fname")String fname, @RequestParam("name")String name, @RequestParam("url")String url,
    		@RequestParam("creator")String creator){
		return ResponseMessage.ok(menusService.initMenu(appcode,menu,fname,name,url,creator));
	}
	
	/**
	 * 初始化菜单的方法，直接传递list后台处理，避免前台菜单过多大量调用后台接口
	 * @param appcode
	 * @param menu
	 * @param fname
	 * @param name
	 * @param url
	 * @param creator
	 * @return
	 */
	@OpenAPI
	@RequestMapping(value="/addMenusPl",method=RequestMethod.POST)
    public ResponseMessage<String> addMenusPl(@RequestParam("appcode")String appcode, @RequestParam("menu")String menu, @RequestParam("fname")String fname, @RequestParam("name")String name, @RequestParam("url")String url,
    		@RequestParam("creator")String creator){
		return ResponseMessage.ok(menusService.initMenu(appcode,menu,fname,name,url,creator));
	}
	
	
	
	@OpenAPI
	@RequestMapping(value="/addMenuList",method=RequestMethod.POST)
	public ResponseMessage<String> addMenuList(@RequestParam("appcode") String appcode,
			@RequestBody List<Map<String, Object>> menuList, 
			@RequestParam("creator") String creator){
		if (menuList.size() == 0) {
			return ResponseMessage.error("未获取到菜单json数据");
		}
		Result<String> result = menusService.initMenuList(appcode, menuList, creator);
		if (result.getCode() == 200) {
			return ResponseMessage.ok(result.getMsg());
		}else {
			return ResponseMessage.error(result.getMsg());
		}
	}

	@Override
	@OpenAPI
	public ResponseMessage<MenusEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody MenusEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody MenusEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody MenusEntity data) {
		return GenericEntityController.super.save(data);
	}
	
	
	
	
	
	
	

}
