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
import awd.cloud.platform.model.logs.Logs_UpdatelogsModel;
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
@RequestMapping("/v4/logs/updatelogs")
@Api(tags = "logs-updatelogs",description = "Updatelogs") 
public class Logs_UpdatelogsController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/updatelogs updatelogs_query
	 * @apiVersion 0.4.0
	 * @apiName updatelogs_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Updatelogs分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} username         				用户名
	 * @apiSuccess {String} servername         				服务名
	 * @apiSuccess {String} httpheads         				请求头
	 * @apiSuccess {String} httpmethod         				请求方法
	 * @apiSuccess {String} url         				请求路径
	 * @apiSuccess {String} response         				返回结果
	 * @apiSuccess {String} operator         				请求人
	 * @apiSuccess {String} optime         				请求时间
	 * @apiSuccess {String} opip         				请求IP
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Logs_UpdatelogsModel>> updatelogs_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_UpdatelogsModel>> result= logsService.updatelogs_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/updatelogs updatelogs_save
	 * @apiVersion 0.4.0
	 * @apiName updatelogs_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Updatelogs自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} username         				用户名
	 * @apiParam {String} servername         				服务名
	 * @apiParam {String} httpheads         				请求头
	 * @apiParam {String} httpmethod         				请求方法
	 * @apiParam {String} url         				请求路径
	 * @apiParam {String} response         				返回结果
	 * @apiParam {String} operator         				请求人
	 * @apiParam {String} optime         				请求时间
	 * @apiParam {String} opip         				请求IP
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} updatetime         				更新时间
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
	public ResponseMessage<String> updatelogs_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_UpdatelogsModel data) {
		return logsService.updatelogs_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/updatelogs updatelogs_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName updatelogs_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Updatelogs数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} username         				用户名
	 * @apiParam {String} servername         				服务名
	 * @apiParam {String} httpheads         				请求头
	 * @apiParam {String} httpmethod         				请求方法
	 * @apiParam {String} url         				请求路径
	 * @apiParam {String} response         				返回结果
	 * @apiParam {String} operator         				请求人
	 * @apiParam {String} optime         				请求时间
	 * @apiParam {String} opip         				请求IP
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} updatetime         				更新时间
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
	public ResponseMessage updatelogs_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_UpdatelogsModel data) {
		return logsService.updatelogs_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/updatelogs/{id} updatelogs_getByKey
	 * @apiVersion 0.4.0
	 * @apiName updatelogs_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Updatelogs根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} username         				用户名
	 * @apiSuccess {String} servername         				服务名
	 * @apiSuccess {String} httpheads         				请求头
	 * @apiSuccess {String} httpmethod         				请求方法
	 * @apiSuccess {String} url         				请求路径
	 * @apiSuccess {String} response         				返回结果
	 * @apiSuccess {String} operator         				请求人
	 * @apiSuccess {String} optime         				请求时间
	 * @apiSuccess {String} opip         				请求IP
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_UpdatelogsModel> updatelogs_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.updatelogs_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/updatelogs/{id} updatelogs_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName updatelogs_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Updatelogs数据删除.
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
	public ResponseMessage<Integer> updatelogs_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.updatelogs_deleteByKey(id);
	}
}
