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
import awd.cloud.platform.model.logs.Logs_DocumentModel;
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
@RequestMapping("/v4/logs/document")
@Api(tags = "logs-document",description = "Document") 
public class Logs_DocumentController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/document document_query
	 * @apiVersion 0.4.0
	 * @apiName document_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Document分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} title         				标题
	 * @apiSuccess {String} theme         				主题
	 * @apiSuccess {String} zsdw         				主送单位
	 * @apiSuccess {String} csdw         				抄送单位
	 * @apiSuccess {String} cbdw         				呈报单位
	 * @apiSuccess {String} qfr         				签发人
	 * @apiSuccess {String} qfrq         				签发日期
	 * @apiSuccess {String} wjzh         				文件字号
	 * @apiSuccess {String} bmjb         				保密级别(BMJB)
	 * @apiSuccess {String} hj         				缓急（HJCD）
	 * @apiSuccess {String} ngr         				拟稿人
	 * @apiSuccess {String} ngdw         				拟稿单位
	 * @apiSuccess {String} uuid         				uuid
	 * @apiSuccess {String} state         				删除状态
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 * @apiSuccess {String} hfbj         				回复标记
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Logs_DocumentModel>> document_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_DocumentModel>> result= logsService.document_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/document document_save
	 * @apiVersion 0.4.0
	 * @apiName document_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Document自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} title         				标题
	 * @apiParam {String} theme         				主题
	 * @apiParam {String} zsdw         				主送单位
	 * @apiParam {String} csdw         				抄送单位
	 * @apiParam {String} cbdw         				呈报单位
	 * @apiParam {String} qfr         				签发人
	 * @apiParam {String} qfrq         				签发日期
	 * @apiParam {String} wjzh         				文件字号
	 * @apiParam {String} bmjb         				保密级别(BMJB)
	 * @apiParam {String} hj         				缓急（HJCD）
	 * @apiParam {String} ngr         				拟稿人
	 * @apiParam {String} ngdw         				拟稿单位
	 * @apiParam {String} uuid         				uuid
	 * @apiParam {String} state         				删除状态
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} updatetime         				更新时间
	 * @apiParam {String} hfbj         				回复标记
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
	public ResponseMessage<String> document_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_DocumentModel data) {
		return logsService.document_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/document document_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName document_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Document数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} title         				标题
	 * @apiParam {String} theme         				主题
	 * @apiParam {String} zsdw         				主送单位
	 * @apiParam {String} csdw         				抄送单位
	 * @apiParam {String} cbdw         				呈报单位
	 * @apiParam {String} qfr         				签发人
	 * @apiParam {String} qfrq         				签发日期
	 * @apiParam {String} wjzh         				文件字号
	 * @apiParam {String} bmjb         				保密级别(BMJB)
	 * @apiParam {String} hj         				缓急（HJCD）
	 * @apiParam {String} ngr         				拟稿人
	 * @apiParam {String} ngdw         				拟稿单位
	 * @apiParam {String} uuid         				uuid
	 * @apiParam {String} state         				删除状态
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} updatetime         				更新时间
	 * @apiParam {String} hfbj         				回复标记
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
	public ResponseMessage document_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_DocumentModel data) {
		return logsService.document_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/document/{id} document_getByKey
	 * @apiVersion 0.4.0
	 * @apiName document_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Document根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} title         				标题
	 * @apiSuccess {String} theme         				主题
	 * @apiSuccess {String} zsdw         				主送单位
	 * @apiSuccess {String} csdw         				抄送单位
	 * @apiSuccess {String} cbdw         				呈报单位
	 * @apiSuccess {String} qfr         				签发人
	 * @apiSuccess {String} qfrq         				签发日期
	 * @apiSuccess {String} wjzh         				文件字号
	 * @apiSuccess {String} bmjb         				保密级别(BMJB)
	 * @apiSuccess {String} hj         				缓急（HJCD）
	 * @apiSuccess {String} ngr         				拟稿人
	 * @apiSuccess {String} ngdw         				拟稿单位
	 * @apiSuccess {String} uuid         				uuid
	 * @apiSuccess {String} state         				删除状态
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 * @apiSuccess {String} hfbj         				回复标记
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_DocumentModel> document_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.document_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/document/{id} document_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName document_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Document数据删除.
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
	public ResponseMessage<Integer> document_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.document_deleteByKey(id);
	}
}
