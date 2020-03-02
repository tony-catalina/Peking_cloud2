/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.entity.RabbitQueuesEntity;
import awd.mis.servers.entity.RabbitUsersEntity;
import awd.mis.servers.model.RabbitUserAndQueueModel;
import awd.mis.servers.service.RabbitUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/msg_rabbitUsers")
@AccessLogger("RabbitUsers")
@Api(value = "RabbitUsers") 
public class RabbitUsersController implements GenericEntityController<RabbitUsersEntity, String, QueryParamEntity, RabbitUsersEntity> {

	private RabbitUsersService rabbitUsersService;

	@Override
	public RabbitUsersEntity modelToEntity(RabbitUsersEntity model, RabbitUsersEntity entity) {
        return entity;
    }

    @Autowired
    public void setRabbitUserService(RabbitUsersService rabbitUserService) {
        this.rabbitUsersService = rabbitUserService;
    }
	 
	@Override
	public CrudService<RabbitUsersEntity, String> getService() {
		return rabbitUsersService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<RabbitUsersEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(rabbitUsersService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody RabbitUsersEntity data) {
		if (StringUtils.isNullOrEmpty(data.getVhost())) {
			data.setVhost(AwdMqInit.get().getVirtualHost());
		}
		try {
			rabbitUsersService.addRabbitUser(data);
			return ResponseMessage.ok("添加rabbit用户成功！");
		} catch (IOException e) {
			return ResponseMessage.error("添加rabbit用户失败！");
		}
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody RabbitUsersEntity data) {
		/*
		 * try { AwdMqUtils.get().closeChannelByUser(data); } catch (IOException e) {
		 * //e.printStackTrace(); }
		 */
		try {
			rabbitUsersService.updateRabbitUser(id,data);
			return ResponseMessage.ok("修改rabbit用户成功！");
		} catch (IOException e) {
			return ResponseMessage.error("修改rabbit用户失败！");
		}
		//return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	
	@Override
	@PatchMapping
	@AccessLogger("{save_or_update}")
	@ApiOperation("保存数据,如果数据不存在则新增一条数据")
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody RabbitUsersEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@GetMapping(path = {"/{id:.+}"})
	@ApiOperation("根据主键查询数据")
	@AccessLogger("{get_by_id}")
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<RabbitUsersEntity> getByPrimaryKey(@PathVariable String id) {
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
	
	@DeleteMapping("/deleteUser")
	@ApiOperation("删除数据")
	@ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
	@OpenAPI
	public ResponseMessage<?> deleteUser(@RequestBody RabbitUsersEntity users) {
		
		try {
			rabbitUsersService.deleteUser(users);
			return ResponseMessage.ok("删除成功");
		} catch (IOException e) {
			return ResponseMessage.error("删除失败");
		}
	}
	
	@DeleteMapping("/deleteByList")
	@ApiOperation("根据ID 批量删除数据")
	@ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
		@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
	@OpenAPI
	public ResponseMessage<Integer> deleteByList(@RequestBody List<RabbitUsersEntity> users) {
		users = JSON.parseArray(JSON.toJSONString(users), RabbitUsersEntity.class);
		return ResponseMessage.ok(rabbitUsersService.deleteByList(users));
	}
	
	@ApiOperation("直接新增用户与消息队列")
	@PostMapping("/addUserAndQueue")
	@HystrixCommand
	@OpenAPI
	public ResponseMessage<String> addUserAndQueue(@RequestBody RabbitUserAndQueueModel data) {
		if (StringUtils.isNullOrEmpty(data.getVhost())) {
			data.setVhost(AwdMqInit.get().getVirtualHost());
		}
		RabbitUsersEntity user = new RabbitUsersEntity();
		BeanUtils.copyProperties(data, user);
		RabbitQueuesEntity queue = new RabbitQueuesEntity();
		BeanUtils.copyProperties(data, queue);
		/**
		 * 默认新增用户的读写权限为
		 * 无配置权限
		 * 无写入权限
		 * 读取权限为传入的单个队列
		 */
		user.setWritrpermission("");
		user.setReadpermission(queue.getQueuename());
		try {
			rabbitUsersService.addUserAndQueue(user, queue);
			return ResponseMessage.ok("添加rabbit用户与消息队列成功！");
		} catch (IOException e) {
			return ResponseMessage.error("添加rabbit用户与消息队列失败！");
		}
	}
}
