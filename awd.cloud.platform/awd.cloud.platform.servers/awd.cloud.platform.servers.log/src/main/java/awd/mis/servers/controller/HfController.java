/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.HfEntity;
import awd.mis.servers.service.HfService;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import awd.mis.servers.utils.CachePageKeyGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RefreshScope
@RequestMapping("/hf")
@AccessLogger("Hf")
@Api(value = "Hf") 
public class HfController implements GenericEntityController<HfEntity, String, QueryParamEntity, HfEntity> {

	private HfService hfService;


	@Override
    public HfEntity modelToEntity(HfEntity model, HfEntity entity) {
        return model;
    }

    @Autowired
    public void setHfService(HfService hfService) {
        this.hfService = hfService;
    }
	 
	@Override
	public CrudService<HfEntity, String> getService() {
		return hfService;
	}
	
	@GetMapping(path = {"/getHfEntyListByHflbid"})
	@ApiOperation("应用自定义查询")
	@AccessLogger("{dynamic_query}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<PagerResult<HfEntity>> getHfEntyListByHflbid(HttpServletRequest request, QueryParamEntity param){
		param.and("state", TermType.eq,"R2");	//数据状态位为正常的数据
		param.setPaging(false);
		return GenericEntityController.super.list(request, param);		
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody HfEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody HfEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	@Override
	@OpenAPI
	public ResponseMessage<HfEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<HfEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
	@GetMapping("/test")
	@OpenAPI
	public ResponseMessage<Integer> test(HttpServletRequest reqest) {
		QueryParamEntity param=new QueryParamEntity();
		HfEntity data=new HfEntity();
		data.setHfnr("dsf");
		List<String> ids=new ArrayList<String>();
		List<HfEntity> datas=new ArrayList<HfEntity>();
		GenericEntityController.super.updateByPrimaryKey("324", data);
		return ResponseMessage.ok();
	}
}
