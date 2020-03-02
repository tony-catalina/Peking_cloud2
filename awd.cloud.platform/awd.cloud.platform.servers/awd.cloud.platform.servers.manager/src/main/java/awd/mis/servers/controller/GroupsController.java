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
import org.springframework.web.bind.annotation.GetMapping;
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

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.GroupsEntity;
import awd.mis.servers.service.GroupsService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/groups")
@AccessLogger("用户组")
@Api(tags = "awd-groups",description="提供用户组管理功能") 
public class GroupsController implements GenericEntityController<GroupsEntity, String, QueryParamEntity, GroupsEntity> {

	private GroupsService groupsService;


	@Override
    public GroupsEntity modelToEntity(GroupsEntity model, GroupsEntity entity) {
        return model;
    }

    @Autowired
    public void setGroupsService(GroupsService groupsService) {
        this.groupsService = groupsService;
    }
	 
	@Override
	public CrudService<GroupsEntity, String> getService() {
		return groupsService;
	}
	
	@ApiOperation("根据监所编号,查询groups数据")
	@ApiResponses({
		@ApiResponse(code = 200, message = "查询成功"),
		@ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"),
		@ApiResponse(code = 404, message = "数据不存在")
	})
	@GetMapping("/getJsTypeByGroups")
	@OpenAPI
	public ResponseMessage<List<Map<String, Object>>> getJsTypeByGroups(@RequestParam(required=true)String type){
		return ResponseMessage.ok(groupsService.getJsTypeByGroups(type));
	}
	
	@ApiOperation("根据监所编号,查询groups数据")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})
	@GetMapping("/getGroups")
	@OpenAPI
	public ResponseMessage<List<GroupsEntity>> getGroups(@RequestParam(required=true)String jsbh){
		return ResponseMessage.ok(groupsService.getGroups(jsbh));
	}
	

	@Override
	@OpenAPI
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据ID查询用户组信息")
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	public ResponseMessage<GroupsEntity> getByPrimaryKey(@PathVariable("id") String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody GroupsEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<GroupsEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody GroupsEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody GroupsEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
	
	@ApiOperation("根据监所编号,查询groups数据")
	@ApiResponses({
		@ApiResponse(code = 200, message = "查询成功"),
		@ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"),
		@ApiResponse(code = 404, message = "数据不存在")
	})
	@GetMapping("/getJsTreeTypeByGroups")
	@OpenAPI
	public String getJsTreeTypeByGroups(@RequestParam(required=true)String type,@RequestParam String callback){
		int t = 0;
		if(type !=null && type !="") {
			t = Integer.parseInt(type)-1;
		}
		List<Map<String, Object>> list = groupsService.getJsTreeTypeByGroups(t+"");
		String result = JSONUtil.toJson(list);
		callback = callback + "(" + result + ")";
		System.err.println("getJsTreeTypeByGroups=="+callback);
		return callback;
	}
	
	

}
