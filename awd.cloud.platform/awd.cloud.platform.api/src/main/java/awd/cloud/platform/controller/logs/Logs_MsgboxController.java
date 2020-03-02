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
import awd.cloud.platform.model.logs.Logs_MsgboxModel;
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
@RequestMapping("/v4/logs/msgbox")
@Api(tags = "logs-msgbox",description = "Msgbox") 
public class Logs_MsgboxController {
	
	@Autowired
    private LogsService logsService;

	/**
	 * @api {get} /logs/msgbox msgbox_query
	 * @apiVersion 0.4.0
	 * @apiName msgbox_query
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Msgbox分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} fsrxm         				发送人姓名 
	 * @apiSuccess {String} fsrjh         				发送人编号
	 * @apiSuccess {String} xxjb         				信息级别(XXJB)
	 * @apiSuccess {String} fssj         				发送时间
	 * @apiSuccess {String} fsnr         				发送内容
	 * @apiSuccess {String} jsrjh         				接收人编号
	 * @apiSuccess {String} jssj         				接收时间
	 * @apiSuccess {String} state         				消息状态
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} updatetime         				updatetime
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} jsrxm         				接收人姓名
	 * @apiSuccess {String} msgid         				消息队列ID
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Logs_MsgboxModel>> msgbox_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Logs_MsgboxModel>> result= logsService.msgbox_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /logs/msgbox msgbox_save
	 * @apiVersion 0.4.0
	 * @apiName msgbox_save
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Msgbox自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} fsrxm         				发送人姓名 
	 * @apiParam {String} fsrjh         				发送人编号
	 * @apiParam {String} xxjb         				信息级别(XXJB)
	 * @apiParam {String} fssj         				发送时间
	 * @apiParam {String} fsnr         				发送内容
	 * @apiParam {String} jsrjh         				接收人编号
	 * @apiParam {String} jssj         				接收时间
	 * @apiParam {String} state         				消息状态
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} updatetime         				updatetime
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} jsrxm         				接收人姓名
	 * @apiParam {String} msgid         				消息队列ID
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
	public ResponseMessage<String> msgbox_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_MsgboxModel data) {
		return logsService.msgbox_save(data);
	}
	
	

	/**
	 * @api {patch} /logs/msgbox msgbox_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName msgbox_updateByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Msgbox数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				id
	 * @apiParam {String} jsbh         				监所编号
	 * @apiParam {String} fsrxm         				发送人姓名 
	 * @apiParam {String} fsrjh         				发送人编号
	 * @apiParam {String} xxjb         				信息级别(XXJB)
	 * @apiParam {String} fssj         				发送时间
	 * @apiParam {String} fsnr         				发送内容
	 * @apiParam {String} jsrjh         				接收人编号
	 * @apiParam {String} jssj         				接收时间
	 * @apiParam {String} state         				消息状态
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} updatetime         				updatetime
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} jsrxm         				接收人姓名
	 * @apiParam {String} msgid         				消息队列ID
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
	public ResponseMessage msgbox_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Logs_MsgboxModel data) {
		return logsService.msgbox_updateByKey(id, data);
	}	

	/**
	 * @api {get} /logs/msgbox/{id} msgbox_getByKey
	 * @apiVersion 0.4.0
	 * @apiName msgbox_getByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Msgbox根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					获取业务数据
	 * @apiSuccess {String} id         				id
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} fsrxm         				发送人姓名 
	 * @apiSuccess {String} fsrjh         				发送人编号
	 * @apiSuccess {String} xxjb         				信息级别(XXJB)
	 * @apiSuccess {String} fssj         				发送时间
	 * @apiSuccess {String} fsnr         				发送内容
	 * @apiSuccess {String} jsrjh         				接收人编号
	 * @apiSuccess {String} jssj         				接收时间
	 * @apiSuccess {String} state         				消息状态
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} updatetime         				updatetime
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} jsrxm         				接收人姓名
	 * @apiSuccess {String} msgid         				消息队列ID
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse QueryError
	 */

	@OpenAPI
	public ResponseMessage<Logs_MsgboxModel> msgbox_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return logsService.msgbox_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /logs/msgbox/{id} msgbox_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName msgbox_deleteByKey
	 * @apiGroup g_logs
	 * @apiPermission user
	 *
	 * @apiDescription Msgbox数据删除.
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
	public ResponseMessage<Integer> msgbox_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name = "user")String user) {
		
		return logsService.msgbox_deleteByKey(id);
	}
}
