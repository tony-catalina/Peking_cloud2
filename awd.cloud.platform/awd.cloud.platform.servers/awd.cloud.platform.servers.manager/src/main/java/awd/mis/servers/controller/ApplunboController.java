/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.ApplunboEntity;
import awd.mis.servers.service.ApplunboService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.Result;
import awd.mis.servers.tools.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.ace.cache.parser.ICacheResultParser;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/applunbo")
@AccessLogger("Applunbo")
@Api(tags = "app-lunbo", description = "提供查询APP轮播图的接口")
public class ApplunboController implements GenericEntityController<ApplunboEntity, String, QueryParamEntity, ApplunboEntity> {

    private ApplunboService applunboService;


    @Override
    public ApplunboEntity modelToEntity(ApplunboEntity model, ApplunboEntity entity) {
        return model;
    }

    @Autowired
    public void setApplunboService(ApplunboService applunboService) {
        this.applunboService = applunboService;
    }

    @Override
    public CrudService<ApplunboEntity, String> getService() {
        return applunboService;
    }


    @GetMapping("/getURL")
    @ApiOperation("获取轮播图的四个URL地址")
    @OpenAPI
    public ResponseMessage<List<ApplunboEntity>> getFourURL(HttpServletRequest reqest, QueryParamEntity param) {
        List<ApplunboEntity> li = applunboService.select();
        return ResponseMessage.ok(li);
    }
    
    @PostMapping("/insertLunbo")
    @ApiOperation("审批通过的应用插入轮播")
    @OpenAPI
    public ResponseMessage<String> insertLunbo(@RequestBody ApplunboEntity data) {
    	return ResponseMessage.ok(applunboService.insert(data));
    }

    
    
    @Override
    @OpenAPI
    @PostMapping
    @ApiOperation("新增一条轮播信息")
    public ResponseMessage<String> save(@RequestBody ApplunboEntity data) {
        data.setState("R2");
        return GenericEntityController.super.save(data);
    }
    
    @OpenAPI
    @PostMapping("/setLunBoApp")
    @ApiOperation("设置四个app轮播图")
    @ResponseBody
    public ResponseMessage<?> setLunBoApp(String appcode,String username){
    	String [] str = appcode.split(",");
    	List<ApplunboEntity> list  = new ArrayList<>();
    	//清除数据
    	System.err.println("-------更新applunbo开始----------------");
    	applunboService.setAppLunboR3();
    	System.err.println("-------更新applunbo结束----------------");
    	for(int i=0;i<str.length;i++) {
    		ApplunboEntity app = new ApplunboEntity();
    		app.setAppcode(str[i]);
    		app.setSort("1");
    		app.setState("R2");
    		app.setCreator(username);
    		app.setCreatetime(Calendar.getInstance().getTime());
    		list.add(app);
    	}
    	String code=applunboService.setAppLunBo(list);
    	Result result = new Result();
    	if("200".equals(code)) {
	    	 return ResponseMessage.ok("保存成功");
    	}else {
       		return ResponseMessage.error("保存失败");
    	}
    	
    }

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<ApplunboEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody ApplunboEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ApplunboEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<ApplunboEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
    
    
}
