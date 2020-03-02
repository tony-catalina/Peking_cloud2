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
import awd.cloud.platform.model.logs.Logs_LoginlogsModel;
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
@RequestMapping("/v4/logs/loginlogs")
@Api(tags = "logs-loginlogs",description = "Loginlogs") 
public class Logs_LoginlogsController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/loginlogs loginlogs_query
	 * @apiVersion 0.4.0
	 * @apiName loginlogs_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Loginlogs分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				ID
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} loginname         				登录名
	 * @apiSuccess {String} logintime         				登录时间
	 * @apiSuccess {String} loginip         				登录IP
	 * @apiSuccess {String} logintype         				登录类型(LGOINTYPE)
	 * @apiSuccess {String} logouttime         				注销时间
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
	public ResponseMessage<PagerResult<Logs_LoginlogsModel>> loginlogs_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_LoginlogsModel>> result= logsService.loginlogs_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/loginlogs loginlogs_save
	 * @apiVersion 0.4.0
	 * @apiName loginlogs_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Loginlogs自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				ID
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} loginname         				登录名
	 * @apiParam {String} logintime         				登录时间
	 * @apiParam {String} loginip         				登录IP
	 * @apiParam {String} logintype         				登录类型(LGOINTYPE)
	 * @apiParam {String} logouttime         				注销时间
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
	public ResponseMessage<String> loginlogs_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_LoginlogsModel data) {
		return logsService.loginlogs_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/loginlogs loginlogs_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName loginlogs_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Loginlogs数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				ID
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} loginname         				登录名
	 * @apiParam {String} logintime         				登录时间
	 * @apiParam {String} loginip         				登录IP
	 * @apiParam {String} logintype         				登录类型(LGOINTYPE)
	 * @apiParam {String} logouttime         				注销时间
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
	public ResponseMessage loginlogs_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_LoginlogsModel data) {
		return logsService.loginlogs_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/loginlogs/{id} loginlogs_getByKey
	 * @apiVersion 0.4.0
	 * @apiName loginlogs_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Loginlogs根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				ID
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} loginname         				登录名
	 * @apiSuccess {String} logintime         				登录时间
	 * @apiSuccess {String} loginip         				登录IP
	 * @apiSuccess {String} logintype         				登录类型(LGOINTYPE)
	 * @apiSuccess {String} logouttime         				注销时间
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
	public ResponseMessage<Logs_LoginlogsModel> loginlogs_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.loginlogs_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/loginlogs/{id} loginlogs_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName loginlogs_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Loginlogs数据删除.
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
	public ResponseMessage<Integer> loginlogs_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.loginlogs_deleteByKey(id);
	}
}
