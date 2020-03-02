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
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.DictionaryEntity;
import awd.mis.servers.service.DictionaryService;
import awd.mis.servers.tools.CacheUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping(value = "/dictionary")
@AccessLogger("字典信息")
@Api(tags = "awd-dictionary", description = "提供基本字典管理功能")
public class DictionaryController implements GenericEntityController<DictionaryEntity, String, QueryParamEntity, DictionaryEntity> {

	@Autowired
	private DictionaryService dictionaryService;
	
	@GetMapping("/getbyfield/{field}")
	@ApiOperation("根据类型获取所有字典")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "field", value = "字典类型", required = true, dataType = "String",paramType="path")
	})
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("getbyfield/{field}")
	@HystrixCommand
	@OpenAPI
	public  ResponseMessage<List<DictionaryEntity>> getByField(@PathVariable(value = "field") String field){
		List<DictionaryEntity> list=null;
		try {
			list=dictionaryService.getByField(field);
		} catch (Exception e) {
			ResponseMessage.error("查询错误！");
		}
		
		return ResponseMessage.ok(list);		
	}
	
	@GetMapping("getallfields")
	@ApiOperation("获取所有字典类型")
	@ApiResponses({
        @ApiResponse(code = 200, message = "查询成功"),
        @ApiResponse(code = 401, message = "未授权"),
        @ApiResponse(code = 403, message = "无权限"),
        @ApiResponse(code = 404, message = "数据不存在")
	})	
	@AccessLogger("{getAllFields}")
	@HystrixCommand
	@OpenAPI
	public ResponseMessage<List<Map<String, Object>>> getAllFields(){
		List<Map<String, Object>>result=new ArrayList<>();
		try {
			List<Map<String, Object>> list=dictionaryService.getAllFields();		
			for (Map<String, Object> map : list) {	
				System.out.println(map);
				map.put("jslx", map.get("jslx").toString());				
				map.put("jslxString", CacheUtils.get().getDictionary("DM", map.get("jslx").toString()));
				map.put("fieldname", map.get("fieldname").toString());
				map.put("fieldnameString", CacheUtils.get().getDictionary("ZDMC", map.get("fieldname").toString()));
				result.add(map);
			}			
		} catch (Exception e) {
			ResponseMessage.error("查询错误！");
		}		
		return ResponseMessage.ok(result);
	}
	
	
	
	
	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<DictionaryEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}
	
    

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody DictionaryEntity data) {
		return GenericEntityController.super.save(data);
	}
	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable("id")String id, @RequestBody DictionaryEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

    @Override
    public DictionaryEntity modelToEntity(DictionaryEntity model, DictionaryEntity entity) {
        return model;
    }

	@Override
	@OpenAPI
	public ResponseMessage<DictionaryEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody DictionaryEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
    @Autowired
    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public CrudService<DictionaryEntity, String> getService() {
        return dictionaryService;
    }


    @GetMapping("/getDictionaryByFieldCode/{field}/{code}")
    @ApiOperation("根据类型（field）和代码（code）获取字典")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")
    })
    @AccessLogger("{getDictionaryByFieldCode}")
    @HystrixCommand
    @OpenAPI
    public ResponseMessage<DictionaryEntity> getDictionaryByFieldCode(@PathVariable("field") String field, @PathVariable("code") String code) {
        DictionaryEntity dictionaryEntity = dictionaryService.getDictionaryByFieldCode(field, code);
        return ResponseMessage.ok(dictionaryEntity);
    }
    
    


}
