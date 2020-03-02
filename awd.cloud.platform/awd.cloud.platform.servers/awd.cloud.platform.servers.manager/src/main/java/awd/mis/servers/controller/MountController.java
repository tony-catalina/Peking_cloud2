/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.MountEntity;
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
@RequestMapping("/mount")
@AccessLogger("安装卸载记录")
@Api(tags = "awd-mount", description = "提供监所App安装卸载管理功能")
public class MountController implements GenericEntityController<MountEntity, String, QueryParamEntity, MountEntity> {

	private MountService mountService;


	@Override
    public MountEntity modelToEntity(MountEntity model, MountEntity entity) {
        return model;
    }

    @Autowired
    public void setMountService(MountService mountService) {
        this.mountService = mountService;
    }
	 
	@Override
	public CrudService<MountEntity, String> getService() {
		return mountService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<MountEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<MountEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody MountEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody MountEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody MountEntity data) {
		return GenericEntityController.super.save(data);
	}
	
	@GetMapping("/appMountNum")
	@ApiOperation("获取APP的安装数")
	@AccessLogger("{save_or_update}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限")})
	@OpenAPI
	public ResponseMessage<String> appMountNum(HttpServletRequest request,String appcode) {
		//String appcode = request.getParameter("appcode");
		int num = mountService.getAppMountNum(appcode);
		if (StringUtils.isNullOrEmpty(num)) {
			return ResponseMessage.ok("0");
		}
		return ResponseMessage.ok(String.valueOf(num));
	}
	
	
}
