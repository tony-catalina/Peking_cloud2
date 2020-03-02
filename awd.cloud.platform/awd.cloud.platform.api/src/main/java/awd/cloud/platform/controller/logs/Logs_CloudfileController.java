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
import awd.cloud.platform.model.logs.Logs_CloudfileModel;
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
@RequestMapping("/v4/logs/cloudfile")
@Api(tags = "logs-cloudfile",description = "Cloudfile") 
public class Logs_CloudfileController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/cloudfile cloudfile_query
	 * @apiVersion 0.4.0
	 * @apiName cloudfile_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Cloudfile分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				jsbh
	 * @apiSuccess {String} rybh         				rybh
	 * @apiSuccess {String} fdir         				fdir
	 * @apiSuccess {String} dir         				dir
	 * @apiSuccess {String} filename         				filename
	 * @apiSuccess {String} filetype         				filetype
	 * @apiSuccess {String} fileicon         				fileicon
	 * @apiSuccess {String} scbz         				scbz
	 * @apiSuccess {String} share         				share
	 * @apiSuccess {String} isdir         				isdir
	 * @apiSuccess {String} uploader         				uploader
	 * @apiSuccess {String} uptime         				uptime
	 * @apiSuccess {String} downnum         				downnum
	 * @apiSuccess {String} bz         				bz
	 * @apiSuccess {String} creator         				creator
	 * @apiSuccess {String} createtime         				createtime
	 * @apiSuccess {String} updator         				updator
	 * @apiSuccess {String} updatetime         				updatetime
	 * @apiSuccess {String} groupname         				存储组
	 * @apiSuccess {String} remotefilename         				存储文件名
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Logs_CloudfileModel>> cloudfile_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_CloudfileModel>> result= logsService.cloudfile_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/cloudfile cloudfile_save
	 * @apiVersion 0.4.0
	 * @apiName cloudfile_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Cloudfile自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				jsbh
	 * @apiParam {String} rybh         				rybh
	 * @apiParam {String} fdir         				fdir
	 * @apiParam {String} dir         				dir
	 * @apiParam {String} filename         				filename
	 * @apiParam {String} filetype         				filetype
	 * @apiParam {String} fileicon         				fileicon
	 * @apiParam {String} scbz         				scbz
	 * @apiParam {String} share         				share
	 * @apiParam {String} isdir         				isdir
	 * @apiParam {String} uploader         				uploader
	 * @apiParam {String} uptime         				uptime
	 * @apiParam {String} downnum         				downnum
	 * @apiParam {String} bz         				bz
	 * @apiParam {String} creator         				creator
	 * @apiParam {String} createtime         				createtime
	 * @apiParam {String} updator         				updator
	 * @apiParam {String} updatetime         				updatetime
	 * @apiParam {String} groupname         				存储组
	 * @apiParam {String} remotefilename         				存储文件名
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
	public ResponseMessage<String> cloudfile_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_CloudfileModel data) {
		return logsService.cloudfile_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/cloudfile cloudfile_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName cloudfile_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Cloudfile数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				jsbh
	 * @apiParam {String} rybh         				rybh
	 * @apiParam {String} fdir         				fdir
	 * @apiParam {String} dir         				dir
	 * @apiParam {String} filename         				filename
	 * @apiParam {String} filetype         				filetype
	 * @apiParam {String} fileicon         				fileicon
	 * @apiParam {String} scbz         				scbz
	 * @apiParam {String} share         				share
	 * @apiParam {String} isdir         				isdir
	 * @apiParam {String} uploader         				uploader
	 * @apiParam {String} uptime         				uptime
	 * @apiParam {String} downnum         				downnum
	 * @apiParam {String} bz         				bz
	 * @apiParam {String} creator         				creator
	 * @apiParam {String} createtime         				createtime
	 * @apiParam {String} updator         				updator
	 * @apiParam {String} updatetime         				updatetime
	 * @apiParam {String} groupname         				存储组
	 * @apiParam {String} remotefilename         				存储文件名
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
	public ResponseMessage cloudfile_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_CloudfileModel data) {
		return logsService.cloudfile_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/cloudfile/{id} cloudfile_getByKey
	 * @apiVersion 0.4.0
	 * @apiName cloudfile_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Cloudfile根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				jsbh
	 * @apiSuccess {String} rybh         				rybh
	 * @apiSuccess {String} fdir         				fdir
	 * @apiSuccess {String} dir         				dir
	 * @apiSuccess {String} filename         				filename
	 * @apiSuccess {String} filetype         				filetype
	 * @apiSuccess {String} fileicon         				fileicon
	 * @apiSuccess {String} scbz         				scbz
	 * @apiSuccess {String} share         				share
	 * @apiSuccess {String} isdir         				isdir
	 * @apiSuccess {String} uploader         				uploader
	 * @apiSuccess {String} uptime         				uptime
	 * @apiSuccess {String} downnum         				downnum
	 * @apiSuccess {String} bz         				bz
	 * @apiSuccess {String} creator         				creator
	 * @apiSuccess {String} createtime         				createtime
	 * @apiSuccess {String} updator         				updator
	 * @apiSuccess {String} updatetime         				updatetime
	 * @apiSuccess {String} groupname         				存储组
	 * @apiSuccess {String} remotefilename         				存储文件名
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_CloudfileModel> cloudfile_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.cloudfile_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/cloudfile/{id} cloudfile_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName cloudfile_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Cloudfile数据删除.
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
	public ResponseMessage<Integer> cloudfile_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.cloudfile_deleteByKey(id);
	}
}
