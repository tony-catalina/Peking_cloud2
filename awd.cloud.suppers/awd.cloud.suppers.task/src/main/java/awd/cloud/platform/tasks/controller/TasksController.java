/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.tasks.entity.TasksEntity;
import awd.cloud.platform.tasks.model.Message;
import awd.cloud.platform.tasks.quartz.job.ScheduleJob;
import awd.cloud.platform.tasks.quartz.service.ScheduleJobService;
import awd.cloud.platform.tasks.service.TasksService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@RefreshScope
@RequestMapping("/kss_tasks")
@AccessLogger("Tasks")
@Api(value = "Tasks") 
public class TasksController implements GenericEntityController<TasksEntity, String, QueryParamEntity, TasksEntity> {

	private TasksService tasksService;
	
	@Autowired 
 	private ScheduleJobService scheduleJobService;


	@Override
    public TasksEntity modelToEntity(TasksEntity model, TasksEntity entity) {
        return model;
    }

    @Autowired
    public void setTasksService(TasksService tasksService) {
        this.tasksService = tasksService;
    }
	 
	@Override
	public CrudService<TasksEntity, String> getService() {
		return tasksService;
	}

	@Override
	@OpenAPI
	@ApiOperation("自定义查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<TasksEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
		return ResponseMessage.ok(tasksService.selectPager(arg1));
	}

	@Override
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody TasksEntity data) {
		return GenericEntityController.super.save(data);
	}

	@Override
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody TasksEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}
	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody TasksEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<TasksEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}
	
	@GetMapping("/metaData")
	@OpenAPI
	public Object metaData() throws SchedulerException{
		SchedulerMetaData metaData = scheduleJobService.getMetaData();
		return metaData;
	}
	
	@GetMapping("/getRunningJobs")
	public Object getRunningJobs() throws SchedulerException{
		List<ScheduleJob> jobList = scheduleJobService.getRunningJobList();
		return jobList;
	}
	
	@RequestMapping(value = "/pauseJob", method = { RequestMethod.POST})
	public Object pauseJob(String id){

		Message message = Message.failure();
		return message;
	}
	
	@RequestMapping(value = "/resumeJob", method = { RequestMethod.POST})
	public Object resumeJob(String  id){
		Message message = Message.failure();
		return message;
	}
}
