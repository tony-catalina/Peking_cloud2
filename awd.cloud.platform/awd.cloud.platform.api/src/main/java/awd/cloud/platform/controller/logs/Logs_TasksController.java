/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.logs;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.LogsService;
import awd.cloud.platform.model.logs.Logs_TasksModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/logs/tasks")
@Api(tags = "logs-tasks",description = "Tasks") 
public class Logs_TasksController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/tasks tasks_query
	 * @apiVersion 0.4.0
	 * @apiName tasks_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Tasks分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} tasktype         				任务类型
	 * @apiSuccess {String} taskname         				任务名称
	 * @apiSuccess {String} method         				服务名
	 * @apiSuccess {String} schedule         				任务调度
	 * @apiSuccess {String} extime         				最后一次执行时间
	 * @apiSuccess {String} stoptime         				禁止时间段内运行
	 * @apiSuccess {String} exstate         				最后一次执行状态
	 * @apiSuccess {String} exresult         				最后一次返回结果
	 * @apiSuccess {String} extimes         				已执行次数
	 * @apiSuccess {String} enable         				是否启用
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Logs_TasksModel>> tasks_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_TasksModel>> result= logsService.tasks_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/tasks tasks_save
	 * @apiVersion 0.4.0
	 * @apiName tasks_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Tasks自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} tasktype         				任务类型
	 * @apiParam {String} taskname         				任务名称
	 * @apiParam {String} method         				服务名
	 * @apiParam {String} schedule         				任务调度
	 * @apiParam {String} extime         				最后一次执行时间
	 * @apiParam {String} stoptime         				禁止时间段内运行
	 * @apiParam {String} exstate         				最后一次执行状态
	 * @apiParam {String} exresult         				最后一次返回结果
	 * @apiParam {String} extimes         				已执行次数
	 * @apiParam {String} enable         				是否启用
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					生成的主键信息
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse CreateError
	 */

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tasks_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_TasksModel data) {
		return logsService.tasks_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/tasks tasks_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName tasks_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Tasks数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} tasktype         				任务类型
	 * @apiParam {String} taskname         				任务名称
	 * @apiParam {String} method         				服务名
	 * @apiParam {String} schedule         				任务调度
	 * @apiParam {String} extime         				最后一次执行时间
	 * @apiParam {String} stoptime         				禁止时间段内运行
	 * @apiParam {String} exstate         				最后一次执行状态
	 * @apiParam {String} exresult         				最后一次返回结果
	 * @apiParam {String} extimes         				已执行次数
	 * @apiParam {String} enable         				是否启用
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					生成的主键信息
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse UpdateError
	 */

	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage tasks_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_TasksModel data) {
		return logsService.tasks_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/tasks/{id} tasks_getByKey
	 * @apiVersion 0.4.0
	 * @apiName tasks_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Tasks根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} tasktype         				任务类型
	 * @apiSuccess {String} taskname         				任务名称
	 * @apiSuccess {String} method         				服务名
	 * @apiSuccess {String} schedule         				任务调度
	 * @apiSuccess {String} extime         				最后一次执行时间
	 * @apiSuccess {String} stoptime         				禁止时间段内运行
	 * @apiSuccess {String} exstate         				最后一次执行状态
	 * @apiSuccess {String} exresult         				最后一次返回结果
	 * @apiSuccess {String} extimes         				已执行次数
	 * @apiSuccess {String} enable         				是否启用
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_TasksModel> tasks_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.tasks_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/tasks/{id} tasks_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName tasks_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Tasks数据删除.
	 *
	 * @apiParam {String} id 												数据编号
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * 
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					删除记录数
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse UpdateError
	 */
	@OpenAPI
	public ResponseMessage<Integer> tasks_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.tasks_deleteByKey(id);
	}
}
