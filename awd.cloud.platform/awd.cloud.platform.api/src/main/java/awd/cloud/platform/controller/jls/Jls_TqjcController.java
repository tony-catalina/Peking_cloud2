/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.base.model.Variables;
import awd.bj.jls.model.ClcsModel;
import awd.bj.jls.model.WpglModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.model.jls.WpglInfoModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/tqjc")
@Api(tags = "kss-tqjc",description = "Tqjc") 
public class Jls_TqjcController extends PublicService{
	
	@Autowired
    private JlsServerApis jlsServerApis;
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcGjyj
	 * @apiVersion 0.4.0
	 * @apiName tqjcGjyj
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除管教意见.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "jsh":"监室号(必填;最大字段长度：4)",
	 *      "creator":"操作人(必填;最大字段长度：30)",
	 *      "tqjcjlqx":"提前解除拘留期限至(必填;格式(2019-02-20))",
	 *      "jcly":"解除理由(必填;最大字段长度：100)",
	 *      "gjyj":"管教意见(必填;最大字段长度：100)",
	 *      "gjqm":"管教姓名(必填;最大字段长度：10)",
	 *      "gjqmrq":"管教签名时间(必填;格式(2019-02-20 10:10:10))",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除管教意见")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcGjyj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcGjyj";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String flowkey = CacheUtils.get().getFlowKey("JLS_TQJC");
			List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
			clcsList.get(0).setState("R2");
			clcsList.get(0).setCsbllx("2");
			clcsList.get(0).setSjzljsbz("1");
			clcsList.get(0).setJsbh(jsbh);
			clcsList.get(0).setCreatetime(new Date());
			String taskid = clcsList.get(0).getTaskid();
			ResponseMessage<String> result = jlsServerApis.clcsStartflow(flowkey, clcsList.get(0));
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcJzsh
	 * @apiVersion 0.4.0
	 * @apiName tqjcJzsh
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除警组审核.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "updator":"操作人(必填;最大字段长度：30)",
	 *      "jzyjnr":"警组意见(必填;)",
	 *      "jzqm":"警组领导姓名(必填;最大字段长度：30)",
	 *      "jzyj":"警组领导审批标志(必填;字典SHFO)",
	 *      "jzqmsj":"警组签名时间(必填;格式(2019-02-20 10:10:10))",
	 *      "taskid":"流程id",
	 *      "ywlcid":"业务流程id",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除警组审核")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcJzsh(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcJzsh";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
			clcsList.get(0).setState("R2");
			clcsList.get(0).setJsbh(jsbh);
			clcsList.get(0).setUpdatetime(new Date());
			clcsList.get(0).setCreatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.clcsSpflow(clcsList.get(0),clcsList.get(0).getTaskid(),"1");
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcSzsp
	 * @apiVersion 0.4.0
	 * @apiName tqjcSzsp
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除所长审批
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "updator":"操作人(必填;最大字段长度：30)",
	 *      "szyjnr":"警组意见(必填;)",
	 *      "szqm":"警组领导姓名(必填;最大字段长度：30)",
	 *      "szyj":"警组领导审批标志(必填;字典SHFO)",
	 *      "szqmsj":"警组签名时间(必填;格式(2019-02-20 10:10:10))",
	 *      "taskid":"流程id",
	 *      "ywlcid":"业务流程id",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除所长审批")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcSzsp(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcSzsp";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
			clcsList.get(0).setState("R2");
			clcsList.get(0).setJsbh(jsbh);
			clcsList.get(0).setUpdatetime(new Date());
			clcsList.get(0).setCreatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.clcsSpflow(clcsList.get(0),clcsList.get(0).getTaskid(),"2");
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcFjldyj
	 * @apiVersion 0.4.0
	 * @apiName tqjcSzsp
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除分局领导意见
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "updator":"操作人(必填;最大字段长度：30)",
	 *      "fgjldyjnr":"警组意见(必填;)",
	 *      "fgjldqm":"警组领导姓名(必填;最大字段长度：30)",
	 *      "fgjldyj":"警组领导审批标志(必填;字典SHFO)",
	 *      "fgjldqmsj":"警组签名时间(必填;格式(2019-02-20 10:10:10))",
	 *      "taskid":"流程id",
	 *      "ywlcid":"业务流程id",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除分局领导意见")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcFjldyj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcFjldyj";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
			clcsList.get(0).setState("R2");
			clcsList.get(0).setJsbh(jsbh);
			clcsList.get(0).setUpdatetime(new Date());
			clcsList.get(0).setCreatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.clcsSpflow(clcsList.get(0),clcsList.get(0).getTaskid(),"3");
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcLsdj
	 * @apiVersion 0.4.0
	 * @apiName tqjcLsdj
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除离所登记
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "updator":"操作人(必填;最大字段长度：30)",
	 *      "csqx":"出所去向(必填;最大字段长度：40)",
	 *      "flwsh":"法律文书号(必填;最大字段长度：30)",
	 *      "bz":"备注(非必填)",
	 *      "jbsj":"经办时间(必填;格式(2019-02-20 10:10:10))",
	 *      "jbr":"经办人(必填;最大字段长度：30)",
	 *      "taskid":"流程id",
	 *      "ywlcid":"业务流程id",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除离所登记")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcLsdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcLsdj";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			List<ClcsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), ClcsModel.class);
			clcsList.get(0).setJsbh(jsbh);
			clcsList.get(0).setUpdatetime(new Date());
			clcsList.get(0).setCreatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.tqjcZxflow(clcsList.get(0),clcsList.get(0).getTaskid(),"2");
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcYwpth
	 * @apiVersion 0.4.0
	 * @apiName tqjcYwpth
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除有物品退还
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	     * "mc":"物品名称(必填)",
	     * "bz":"备注(非必填)",
	     * "lqrq":"领取时间(必填;格式(2019-20-20 10:10:10))",
	     * "id":"物品id",
	     * "jsh":"监室号(必填;最大字段长度：4)",
	     *  "snbh":"所内编号(必填;最大字段长度：15)",
	     *  "xm":"姓名(必填;最大字段长度：30)",
	     * "taskid": "流程id",
	     * "ywlcid": "业务流程id",
	     * "rybh": "人员编号(必填;最大字段长度：21)",
	     * "blr": "办理人(必填;最大字段长度：30)",
	     * "blsj": "办理时间(必填;格式(2019-20-20 10:10:10))",
	     * },{
	     * "mc":"物品名称(必填)",
	     * "bz":"备注(非必填)",
	     * "lqrq":"领取时间(必填;格式(2019-20-20 10:10:10))",
	     * "id":"物品id",
	     * "jsh":"监室号(必填;最大字段长度：4)",
	     *  "snbh":"所内编号(必填;最大字段长度：15)",
	     *  "xm":"姓名(必填;最大字段长度：30)",
	     * "taskid": "流程id",
	     * "ywlcid": "业务流程id",
	     * "rybh": "人员编号(必填;最大字段长度：21)",
	     * "blr": "办理人(必填;最大字段长度：30)",
	     * "blsj": "办理时间(必填;格式(2019-20-20 10:10:10))",
	     * },{
	     * "mc":"物品名称(必填)",
	     * "bz":"备注(非必填)",
	     * "lqrq":"领取时间(必填;格式(2019-20-20 10:10:10))",
	     * "id":"物品id",
	     * "jsh":"监室号(必填;最大字段长度：4)",
	     *  "snbh":"所内编号(必填;最大字段长度：15)",
	     *  "xm":"姓名(必填;最大字段长度：30)",
	     * "taskid": "流程id",
	     * "ywlcid": "业务流程id",
	     * "rybh": "人员编号(必填;最大字段长度：21)",
	     * "blr": "办理人(必填;最大字段长度：30)",
	     * "blsj": "办理时间(必填;格式(2019-20-20 10:10:10))",
	     * }]
	 * }
	 *
	 */
	@ApiOperation("提前解除有物品退还")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcYwpth(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcYwpth";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<Map> wpglmap = JSONUtil.toList(map.get("entity").toString(), Map.class);
			List<WpglModel> wpgls = JSONUtil.toList(map.get("entity").toString(), WpglModel.class);
	        List<WpglModel> wpglModels = new ArrayList<WpglModel>();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (WpglModel wpglModel : wpgls) {
                wpglModel.setLqzt("1");
                wpglModel.setTwr(wpglmap.get(0).get("blr").toString());
                wpglModel.setUpdator(wpglmap.get(0).get("blr").toString());
                wpglModel.setTwrq(new Date());
                wpglModels.add(wpglModel);
            }
	        Variables variables = new Variables();
	        variables.setRybh(wpglmap.get(0).get("rybh").toString());
	        variables.setJsbh(jsbh);
	        Map<String, Object> m = new HashMap<String, Object>();
	        m.put("rybh", wpglmap.get(0).get("rybh").toString());
	        m.put("snbh", wpglmap.get(0).get("snbh").toString());
	        m.put("jsh", wpglmap.get(0).get("jsh").toString());
	        m.put("xm", wpglmap.get(0).get("xm").toString());
	        variables.setParams(m);
	        WpglInfoModel wpglInfoModel = new WpglInfoModel();
	        wpglInfoModel.setTaskid(wpglmap.get(0).get("taskid").toString());
	        wpglInfoModel.setJsbh(jsbh);
	        wpglInfoModel.setWpglEntities(wpglModels);
	        wpglInfoModel.setVariables(variables);
	        System.err.println("==========================");
	        ResponseMessage<String> result = jlsServerApis.savePLAsCustom(wpglInfoModel);
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	
	/**
	 * @api {post} /v4/jls/tqjc/tqjcWwpth
	 * @apiVersion 0.4.0
	 * @apiName tqjcWwpth
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 提前解除无物品退还
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "jsh":"监室号(必填;最大字段长度：4)",
	 *      "snbh":"所内编号(必填;最大字段长度：15)",
	 *      "xm":"姓名(必填;最大字段长度：30)",
	 *      "taskid":"流程id",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("提前解除无物品退还")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tqjcWwpth(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/tqjc/tqjcWwpth";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<Map> clcsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Variables variables = new Variables();
		    Map<String, Object> m = new HashMap<>();
            variables.setRybh(clcsList.get(0).get("rybh").toString());
            variables.setJsbh(jsbh);
            m.put("rybh", clcsList.get(0).get("rybh").toString());
            m.put("snbh", clcsList.get(0).get("snbh").toString());
            m.put("jsh", clcsList.get(0).get("jsh").toString());
            m.put("xm", clcsList.get(0).get("xm").toString());
            variables.setParams(m);
			ResponseMessage<String> result = jlsServerApis.addFlowWfwth(clcsList.get(0).get("taskid").toString(), variables);
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
}
