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
import awd.cloud.platform.model.logs.Logs_DocumentdetailModel;
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
@RequestMapping("/v4/logs/documentdetail")
@Api(tags = "logs-documentdetail",description = "Documentdetail") 
public class Logs_DocumentdetailController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/documentdetail documentdetail_query
	 * @apiVersion 0.4.0
	 * @apiName documentdetail_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Documentdetail分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} hfsj         				回复时间
	 * @apiSuccess {String} hfnr         				回复内容
	 * @apiSuccess {String} hfr         				回复人
	 * @apiSuccess {String} hfrjh         				回复人警号
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} flag         				是否已回复(SHFO)
	 * @apiSuccess {String} uuid         				uuid
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
	public ResponseMessage<PagerResult<Logs_DocumentdetailModel>> documentdetail_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_DocumentdetailModel>> result= logsService.documentdetail_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/documentdetail documentdetail_save
	 * @apiVersion 0.4.0
	 * @apiName documentdetail_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Documentdetail自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} hfsj         				回复时间
	 * @apiParam {String} hfnr         				回复内容
	 * @apiParam {String} hfr         				回复人
	 * @apiParam {String} hfrjh         				回复人警号
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} flag         				是否已回复(SHFO)
	 * @apiParam {String} uuid         				uuid
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
	public ResponseMessage<String> documentdetail_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_DocumentdetailModel data) {
		return logsService.documentdetail_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/documentdetail documentdetail_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName documentdetail_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Documentdetail数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} hfsj         				回复时间
	 * @apiParam {String} hfnr         				回复内容
	 * @apiParam {String} hfr         				回复人
	 * @apiParam {String} hfrjh         				回复人警号
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} flag         				是否已回复(SHFO)
	 * @apiParam {String} uuid         				uuid
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
	public ResponseMessage documentdetail_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_DocumentdetailModel data) {
		return logsService.documentdetail_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/documentdetail/{id} documentdetail_getByKey
	 * @apiVersion 0.4.0
	 * @apiName documentdetail_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Documentdetail根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} hfsj         				回复时间
	 * @apiSuccess {String} hfnr         				回复内容
	 * @apiSuccess {String} hfr         				回复人
	 * @apiSuccess {String} hfrjh         				回复人警号
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} flag         				是否已回复(SHFO)
	 * @apiSuccess {String} uuid         				uuid
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_DocumentdetailModel> documentdetail_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.documentdetail_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/documentdetail/{id} documentdetail_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName documentdetail_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Documentdetail数据删除.
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
	public ResponseMessage<Integer> documentdetail_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.documentdetail_deleteByKey(id);
	}
}
