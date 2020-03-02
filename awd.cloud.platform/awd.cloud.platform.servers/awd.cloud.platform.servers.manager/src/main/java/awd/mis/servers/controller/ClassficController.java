/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.SimpleGenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.ClassficEntity;
import awd.mis.servers.service.ClassficService;
import awd.mis.servers.tools.CacheKeyGenerator;
import awd.mis.servers.tools.CachePageKeyGenerator;
import awd.mis.servers.tools.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/classfic")
@AccessLogger("分类表")
@Api(tags = "awd-classfic", description = "提供数据分类管理功能") 
public class ClassficController implements SimpleGenericEntityController<ClassficEntity, String, QueryParamEntity>,GenericEntityController<ClassficEntity, String, QueryParamEntity,ClassficEntity> {

	private ClassficService classficService;


	@Override
    public ClassficEntity modelToEntity(ClassficEntity model, ClassficEntity entity) {
        return model;
    }

    @Autowired
    public void setClassficService(ClassficService classficService) {
        this.classficService = classficService;
    }
    
    @Override
	public CrudService getService() {
		return classficService;
	}
    
    
    @Override
	@PostMapping
    @AccessLogger("{action_add}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "创建数据")
    @ApiResponses({
            @ApiResponse(code = 201, message = "创建成功,返回创建数据的ID"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")
    })    
    @OpenAPI
	public ResponseMessage<String> save(@RequestBody ClassficEntity data) {
		List<ClassficEntity> list =classficService.selectByParentIdandName(data.getParentid(),data.getName());
		if(list!=null&&list.size()>0) {
			return ResponseMessage.error("已经存在该分类");
		}else {				
			return ResponseMessage.ok(classficService.insert(data));
		}
	}
	
	

	@Override
	@DeleteMapping(path = "/{id}")
	@AccessLogger("{delete_by_primary_key}")
    @ApiOperation("根据ID删除数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "要删除的数据不存在")
    })
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		ClassficEntity classficEntity=classficService.selectByPk(id);
		if(classficEntity!=null && classficEntity.getParentid().equals("0")) {
			return ResponseMessage.error("根分类不能删除");
		}else {
			return GenericEntityController.super.deleteByPrimaryKey(id);
		}	
		
	}
	
	
	@GetMapping(path = "/findNode")
	@AccessLogger("{find_by_parentid}")
    @ApiOperation("根据父ID查询节点数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "要删除的数据不存在")
    })
	@OpenAPI
	public ResponseMessage<List<ClassficEntity>> findNode(@RequestParam(value="lx") String lx,@RequestParam(value="parentid",defaultValue = "0") String parentid) {
		List<ClassficEntity> list=classficService.selectChildNode(lx.toUpperCase(),parentid);		
		return ResponseMessage.ok(list);
		
	}
	
	
	@GetMapping(path = "/findNodes")
	@AccessLogger("{find_all_by_parentid}")
    @ApiOperation("根据父ID查询所有子节点数据")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "要删除的数据不存在")
    })
	@OpenAPI
	public ResponseMessage<List<ClassficEntity>> findNodes(@RequestParam(value="lx") String lx,@RequestParam(value="parentid",defaultValue = "0")String parentid) {
		List<ClassficEntity> list=classficService.selectAllChildNode(lx.toUpperCase(),parentid);		
		return ResponseMessage.ok(list);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody ClassficEntity data) {
		return SimpleGenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ClassficEntity data) {
		return SimpleGenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<ClassficEntity> getByPrimaryKey(@PathVariable String id) {
		return SimpleGenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<ClassficEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return SimpleGenericEntityController.super.list(arg0, arg1);
	}
	
	
}
