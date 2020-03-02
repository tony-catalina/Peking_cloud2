/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.entity.RabbitQueuesEntity;
import awd.mis.servers.service.RabbitQueuesService;
import awd.mis.servers.utils.AwdMqUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/msg_rabbitQueues")
@AccessLogger("RabbitQueues")
@Api(tags = "RabbitQueues-Controller", description = "保存到数据库的应用的消息队列")
public class RabbitQueuesController implements GenericEntityController<RabbitQueuesEntity, String, QueryParamEntity, RabbitQueuesEntity> {

	private RabbitQueuesService rabbitQueuesService;

	@Override
    public RabbitQueuesEntity modelToEntity(RabbitQueuesEntity model, RabbitQueuesEntity entity) {
        return model;
    }

    @Autowired
    public void setRabbitQueuesService(RabbitQueuesService rabbitQueuesService) {
        this.rabbitQueuesService = rabbitQueuesService;
    }
	 
	@Override
	public CrudService<RabbitQueuesEntity, String> getService() {
		return rabbitQueuesService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<RabbitQueuesEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(rabbitQueuesService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody RabbitQueuesEntity data) {
		if (StringUtils.isNullOrEmpty(data.getVhost())) {
			data.setVhost(AwdMqInit.get().getVirtualHost());
		}
		try {
			AwdMqUtils.get().addRabbitQueue(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody RabbitQueuesEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	@Override
	@PatchMapping
	@AccessLogger("{save_or_update}")
	@ApiOperation("保存数据,如果数据不存在则新增一条数据")
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody RabbitQueuesEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据主键查询数据")
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<RabbitQueuesEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@DeleteMapping(path = {"/{id:.+}"})
	@AccessLogger("{delete_by_primary_key}")
	@ApiOperation("根据ID删除数据")
	@ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
	@PostMapping("/deleteQueueEntity")
	@ApiOperation("根据ID删除数据")
	@ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
	@OpenAPI
	public ResponseMessage<?> deleteQueueEntity(@RequestParam("id") String id,
			@RequestParam("vhost") String vhost,
			@RequestParam("queuename") String queuename) {
		Map<String, Object> re = rabbitQueuesService.deleteQueueEntity(id, vhost, queuename);
		if ((int) re.get("num") == 1) {
			return ResponseMessage.ok(re.get("msg"));
		}else {
			return ResponseMessage.error(500, (String) re.get("msg"));
		}
	}
	
}
