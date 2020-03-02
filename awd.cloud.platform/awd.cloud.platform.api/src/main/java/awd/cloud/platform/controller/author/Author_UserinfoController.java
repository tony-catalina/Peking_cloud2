/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.author;
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

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_UserinfoModel;
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
@RequestMapping("/v4/author/userinfo")
@Api(tags = "manager-userinfo",description = "Userinfo") 
public class Author_UserinfoController {
	
	@Autowired
    private ManagerService managerService;

	/**
	 * @api {get} /userinfo userinfo_query
	 * @apiVersion 0.4.0
	 * @apiName userinfo_query
	 * @apiGroup g_author
	 * @apiPermission user
	 *
	 * @apiDescription Userinfo分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 * @apiSuccess {String} id         				ID
	 * @apiSuccess {String} userid         				用户编号(字段作废)
	 * @apiSuccess {String} usertype         				用户类型(USERTYPE)
	 * @apiSuccess {String} jsbh         				单位号
	 * @apiSuccess {String} loginname         				登录名
	 * @apiSuccess {String} loginpass         				登录密码
	 * @apiSuccess {String} sfzh         				身份证号
	 * @apiSuccess {String} jh         				警号
	 * @apiSuccess {String} email         				EMAIL
	 * @apiSuccess {String} realname         				真实姓名
	 * @apiSuccess {String} glybz         				管理员标识(SHFO)
	 * @apiSuccess {String} spr         				审批人
	 * @apiSuccess {String} spsj         				审批时间
	 * @apiSuccess {String} spbz         				审批标识
	 * @apiSuccess {String} state         				启用状态
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
	public ResponseMessage<PagerResult<Manager_UserinfoModel>> userinfo_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Manager_UserinfoModel>> result= managerService.userinfo_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /author/userinfo userinfo_save
	 * @apiVersion 0.4.0
	 * @apiName userinfo_save
	 * @apiGroup g_author
	 * @apiPermission user
	 *
	 * @apiDescription Userinfo自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 * @apiParam {String} id         				ID
	 * @apiParam {String} userid         				用户编号(字段作废)
	 * @apiParam {String} usertype         				用户类型(USERTYPE)
	 * @apiParam {String} jsbh         				单位号
	 * @apiParam {String} loginname         				登录名
	 * @apiParam {String} loginpass         				登录密码
	 * @apiParam {String} sfzh         				身份证号
	 * @apiParam {String} jh         				警号
	 * @apiParam {String} email         				EMAIL
	 * @apiParam {String} realname         				真实姓名
	 * @apiParam {String} glybz         				管理员标识(SHFO)
	 * @apiParam {String} spr         				审批人
	 * @apiParam {String} spsj         				审批时间
	 * @apiParam {String} spbz         				审批标识
	 * @apiParam {String} state         				启用状态
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
	public ResponseMessage<String> userinfo_save(@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Manager_UserinfoModel data) {
		return managerService.userinfo_save(data);
	}
	
	

	/**
	 * @api {patch} /author/userinfo userinfo_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName userinfo_updateByKey
	 * @apiGroup g_author
	 * @apiPermission user
	 *
	 * @apiDescription Userinfo数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 * @apiParam {String} id         				ID
	 * @apiParam {String} userid         				用户编号(字段作废)
	 * @apiParam {String} usertype         				用户类型(USERTYPE)
	 * @apiParam {String} jsbh         				单位号
	 * @apiParam {String} loginname         				登录名
	 * @apiParam {String} loginpass         				登录密码
	 * @apiParam {String} sfzh         				身份证号
	 * @apiParam {String} jh         				警号
	 * @apiParam {String} email         				EMAIL
	 * @apiParam {String} realname         				真实姓名
	 * @apiParam {String} glybz         				管理员标识(SHFO)
	 * @apiParam {String} spr         				审批人
	 * @apiParam {String} spsj         				审批时间
	 * @apiParam {String} spbz         				审批标识
	 * @apiParam {String} state         				启用状态
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
	public ResponseMessage userinfo_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Manager_UserinfoModel data) {
		return managerService.userinfo_updateByKey(id, data);
	}	

	/**
	 * @api {get} /author/userinfo/{id} userinfo_getByKey
	 * @apiVersion 0.4.0
	 * @apiName userinfo_getByKey
	 * @apiGroup g_author
	 * @apiPermission user
	 *
	 * @apiDescription Userinfo根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 * @apiSuccess {String} id         				ID
	 * @apiSuccess {String} userid         				用户编号(字段作废)
	 * @apiSuccess {String} usertype         				用户类型(USERTYPE)
	 * @apiSuccess {String} jsbh         				单位号
	 * @apiSuccess {String} loginname         				登录名
	 * @apiSuccess {String} loginpass         				登录密码
	 * @apiSuccess {String} sfzh         				身份证号
	 * @apiSuccess {String} jh         				警号
	 * @apiSuccess {String} email         				EMAIL
	 * @apiSuccess {String} realname         				真实姓名
	 * @apiSuccess {String} glybz         				管理员标识(SHFO)
	 * @apiSuccess {String} spr         				审批人
	 * @apiSuccess {String} spsj         				审批时间
	 * @apiSuccess {String} spbz         				审批标识
	 * @apiSuccess {String} state         				启用状态
	 * @apiSuccess {String} creator         				创建人
	 * @apiSuccess {String} createtime         				创建时间
	 * @apiSuccess {String} updator         				更新人
	 * @apiSuccess {String} updatetime         				更新时间
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	public ResponseMessage<Manager_UserinfoModel> userinfo_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return managerService.userinfo_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /author/userinfo/{id} userinfo_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName userinfo_deleteByKey
	 * @apiGroup g_author
	 * @apiPermission user
	 *
	 * @apiDescription Userinfo数据删除.
	 *
	 * @apiParam {String} id 												数据编号
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * 
	 * @apiParam {String} id         				ID
	 * @apiParam {String} userid         				用户编号(字段作废)
	 * @apiParam {String} usertype         				用户类型(USERTYPE)
	 * @apiParam {String} jsbh         				单位号
	 * @apiParam {String} loginname         				登录名
	 * @apiParam {String} loginpass         				登录密码
	 * @apiParam {String} sfzh         				身份证号
	 * @apiParam {String} jh         				警号
	 * @apiParam {String} email         				EMAIL
	 * @apiParam {String} realname         				真实姓名
	 * @apiParam {String} glybz         				管理员标识(SHFO)
	 * @apiParam {String} spr         				审批人
	 * @apiParam {String} spsj         				审批时间
	 * @apiParam {String} spbz         				审批标识
	 * @apiParam {String} state         				启用状态
	 * @apiParam {String} creator         				创建人
	 * @apiParam {String} createtime         				创建时间
	 * @apiParam {String} updator         				更新人
	 * @apiParam {String} updatetime         				更新时间
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					删除记录数
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse UpdateError
	 */
	@OpenAPI
	public ResponseMessage<Integer> userinfo_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		
		return managerService.userinfo_deleteByKey(id);
	}
}
