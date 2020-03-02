/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.DwkfRyxxModel;
import awd.cloud.platform.model.jls.DwkfAndRyxxModel;
import awd.cloud.platform.utils.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.jls.model.JkqkModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/jkqk")
@Api(tags = "jls-jkqk",description = "Jkqk")
public class Jls_JkqkController extends PublicService {

	@Autowired
	private JlsServerApis jlsServerApis;

	/**
	 * @api {post} /v4/jls/jkqk/jkqkAdd 健康情况添加
	 * @apiVersion 0.4.0
	 * @apiName jkqkAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 健康情况添加.
	 * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
	 * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 *  appcode:"应用代码(必填)",
	 *  jsbh:"监所编号(必填; 最大字段长度：9)",
	 *  json:{"entity":[{
	 * "tbr": "管理员",
	 * "tbrq": "2020-02-20",
	 * "gcbh": "435971608758933",
	 * "rybh": "110000121201911280008",
	 * "taskid": "5341201",
	 * "ywlcid": "5340937",
	 * "sg": "160",
	 * "tz": "60",
	 * "zc": "30",
	 * "xl": "120",
	 * "xy": "120",
	 * "jcr": "管理员",
	 * "tbtsbj": "无",
	 * "brbs": "无",
	 * "crb": "无",
	 * "jtbs": "无",
	 * "sss": "无",
	 * "wss": "无",
	 * "zszz": "无",
	 * "sbq": "无",
	 * "jkzk": "1",
	 * "ysyj": "无",
	 * "jcrq": "2020-02-20",
	 * "ysqm": "管理员",
	 * "qmrq": "2020-02-20",
	 * "bz": "啊啊啊"
	 * }]
	 * }
	 */
	@ApiOperation("健康情况添加")
	@PostMapping("jkqkAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkqkAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/jls/jkqk/jkqkAdd";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);

			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<JkqkModel> jkqkMOdel = JSONArray.parseArray(map.get("entity").toString(), JkqkModel.class);

			jkqkMOdel.get(0).setJsbh(jsbh);
			jkqkMOdel.get(0).setState("R2");
			jkqkMOdel.get(0).setCreatetime(new Date());
			String taskid=null;
			String ywlxid=null;
			if(StringUtils.isNullOrEmpty(maps.getResult().get("ywlxid"))){
				ywlxid=maps.getResult().get("ywlxid").toString();
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
				taskid=maps.getResult().get("taskid").toString();
			}
			else {
				return ResponseMessage.error("taskid不能为空！");
			}
			ResponseMessage<String> result = jlsServerApis.jkqkSave(taskid, ywlxid,jkqkMOdel.get(0));
			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {post} /v4/jls/jkqk/saveByryxxbl 人员信息补录健康补录
	 * @apiVersion 0.4.0
	 * @apiName saveByryxxbl
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 人员信息补录健康补录
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String} message         			                    成功信息
	 * @apiSuccess {String} result         				                	生成的主键信息
	 * @apiSuccess {String} status         				                	代码
	 * @apiSuccess {String} timestamp         			                    时间戳
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
	 *   	"rybh":"人员编号(必填; 最大字段长度：30)",
	 *   	"sg":"身高(必填; 最大字段长度：3)",
	 *   	"tz":"体重(必填; 最大字段长度：3)",
	 *   	"zc":"足长(必填; 最大字段长度：6)",
	 *   	"xl":"心率(必填; 最大字段长度：10)",
	 *   	"xy":"血压(必填; 最大字段长度：10)",
	 *   	"jcr":"检查人(必填; 最大字段长度：30)",
	 *   	"tbtsbj":"体表特殊标记(必填)",
	 *   	"crb":"传染病(必填; 最大字段长度：100)",
	 *   	"brbs":"本人病史(必填)",
	 *   	"jtbs":"家庭病史(必填)",
	 *   	"sss":"手术史(必填)",
	 *   	"wss":"外伤史(必填)",
	 *   	"zszz":"自述症状",
	 *   	"sbq":"伤病情",
	 *   	"jkzk":"健康状况(必填; 最大字段长度：1)",
	 *   	"jcrq":"检查日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *   	"ysyj":"医生意见(必填; 最大字段长度：100)",
	 *   	"ysqm":"医生签名(必填; 最大字段长度：30)",
	 *   	"qmrq":"签名日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *      "bz'":"备注",
	 *      "creator":"创建人(必填; 最大字段长度：30)"
	 *   }
	 *   ]
	 * }
	 */
	//{"rybh":".{1,30}","sg":".{1,3}","tz":".{1,3}","zc":".{1,3}","xl":".{1,10}","xy":".{1,10}","jcr":".{1,30}","crb":".{1,100}","jkzk":".{1,1}","jcrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ysyj":".{1,100}","ysqm":".{1,100}","qmrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
	@ApiOperation("人员信息补录健康补录")
	@PostMapping("saveByryxxbl")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveByryxxbl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/jkqk/saveByryxxbl";

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

			List<JkqkModel> modelList = JSONArray.parseArray(map.get("entity").toString(),JkqkModel.class);
			JkqkModel jkqkModel = modelList.get(0);
			jkqkModel.setState("R2");
			jkqkModel.setJsbh(jsbh);
			jkqkModel.setCreatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.jkqksave(jkqkModel);
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
	 * @api {post} /v4/jls/jkqk/updateByryxxbl 人员信息补录检查维护
	 * @apiVersion 0.4.0
	 * @apiName updateByryxxbl
	 * @apiGroup g_jls
	 * @apiPermission any
	 * @apiDescription 人员信息补录检查维护
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String} message         			                    成功信息
	 * @apiSuccess {String} result         				                	生成的主键信息
	 * @apiSuccess {String} status         				                	代码
	 * @apiSuccess {String} timestamp         			                    时间戳
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
	 *   	"id":"id(必填; 最大字段长度：23)",
	 *   	"rybh":"人员编号(必填; 最大字段长度：30)",
	 *   	"sg":"身高(必填; 最大字段长度：3)",
	 *   	"tz":"体重(必填; 最大字段长度：3)",
	 *   	"zc":"足长(必填; 最大字段长度：6)",
	 *   	"xl":"心率(必填; 最大字段长度：10)",
	 *   	"xy":"血压(必填; 最大字段长度：10)",
	 *   	"jcr":"检查人(必填; 最大字段长度：30)",
	 *   	"tbtsbj":"体表特殊标记(必填)",
	 *   	"crb":"传染病(必填; 最大字段长度：100)",
	 *   	"brbs":"本人病史(必填)",
	 *   	"jtbs":"家庭病史(必填)",
	 *   	"sss":"手术史(必填)",
	 *   	"wss":"外伤史(必填)",
	 *   	"zszz":"自述症状",
	 *   	"sbq":"伤病情",
	 *   	"jkzk":"健康状况(必填; 最大字段长度：1)",
	 *   	"jcrq":"检查日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *   	"ysyj":"医生意见(必填; 最大字段长度：100)",
	 *   	"ysqm":"医生签名(必填; 最大字段长度：30)",
	 *   	"qmrq":"签名日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *      "bz'":"备注",
	 *      "updator":"创建人(必填; 最大字段长度：30)"
	 *   }
	 *   ]
	 * }
	 */
	//{"id":".{1,23}","rybh":".{1,30}","sg":".{1,3}","tz":".{1,3}","zc":".{1,3}","xl":".{1,10}","xy":".{1,10}","jcr":".{1,30}","crb":".{1,100}","jkzk":".{1,1}","jcrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ysyj":".{1,100}","ysqm":".{1,100}","qmrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","updator":".{1,30}"}
	@ApiOperation("人员信息补录检查维护")
	@PostMapping("updateByryxxbl")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> updateByryxxbl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/jls/jkqk/updateByryxxbl";

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

			List<JkqkModel> modelList = JSONArray.parseArray(map.get("entity").toString(),JkqkModel.class);
			JkqkModel jkqkModel = modelList.get(0);
			jkqkModel.setUpdatetime(new Date());
			ResponseMessage<String> result = jlsServerApis.jkqkUpdate(jkqkModel.getId(),jkqkModel);
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
