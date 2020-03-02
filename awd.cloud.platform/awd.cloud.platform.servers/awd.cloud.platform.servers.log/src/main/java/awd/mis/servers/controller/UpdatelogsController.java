/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.UpdatelogsEntity;
import awd.mis.servers.model.UpdatelogsModel;
import awd.mis.servers.service.UpdatelogsService;
import awd.mis.servers.utils.CachePageKeyGenerator;
import awd.mis.servers.utils.CacheKeyGenerator.ResultCacheResultParser;
import io.swagger.annotations.Api;


@RestController
@RefreshScope
@RequestMapping("/updatelogs")
@AccessLogger("服务操作日志")
@Api(tags = "awd-server-log", description = "提供服务访问日志管理功能")
public class UpdatelogsController implements GenericEntityController<UpdatelogsEntity, String, QueryParamEntity, UpdatelogsModel> {

	private UpdatelogsService updatelogsService;


	@Override
    public UpdatelogsEntity modelToEntity(UpdatelogsModel model, UpdatelogsEntity entity) {
		BeanUtils.copyProperties(model, entity);
        return entity;
    }

    @Autowired
    public void setUpdatelogsService(UpdatelogsService updatelogsService) {
        this.updatelogsService = updatelogsService;
    }
	 
	@Override
	public CrudService<UpdatelogsEntity, String> getService() {
		return updatelogsService;
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody UpdatelogsModel data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<PagerResult<UpdatelogsEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return GenericEntityController.super.list(arg0, arg1);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody UpdatelogsModel data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody UpdatelogsModel data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<UpdatelogsEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}
	
	
	
	

}
