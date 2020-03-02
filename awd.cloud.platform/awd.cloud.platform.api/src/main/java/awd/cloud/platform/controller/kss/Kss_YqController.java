/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_YqModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/yq")
@Api(tags = "kss-yq",description = "Yq") 
public class Kss_YqController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssAnalyseApis kssAnalyseApis;
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
	private ProcessService processService;

	/**
	 * @api {post} /v4/kss/yq/yqZjhd 延期证件核对
	 * @apiVersion 0.4.0
	 * @apiName yqZjhd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 延期证件核对.
	 *
	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *       			"jbxx":"基本信息(必填;)"{
	 *       		 				"gyqx":"关押期限(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  							"sypzwsh":"收押凭证文书号(必填;最大字段长度：60)",
	 * 	  	                       },
	 * 	  	               "data":"业务信息(必填;)"{
	 * 	  	                       "blr":"办理人(必填;最大字段长度：20)",
	 * 								"rybh":"人员编号(必填;最大字段长度：21)",
	 *  							"blrzjh":"办理人证件号(必填;最大字段长度：18)",
	 *  							"flwsh":"法律文书号(必填;最大字段长度：60)",
	 *  							"blrq":"办理日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  							"blrdh":"办理人电话(最大字段长度：11)"
	 * 	  	 						}
	 *          }]
	 *     			}
	 */

	//{"sypzwsh":"\\d{1,60}","blr":"\\S{1,20}","rybh":"\\d{1,21}","flwsh":"\\S{1,60}","blrzjh":"\\d{1,18}","gyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"jbxx":{"creator":"aa","gyqx":"2020-01-01 19:22:22","sypzwsh":"23"},"data":{"blr":"aa","rybh":"110000114201912040014","blrzjh":"342522199412300912","flwsh":"ewrewrew","blrq":"2020-01-01 19:22:22"}}]}
	@ApiOperation("延期证件核对")
	@PostMapping("/yqZjhd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})

	@OpenAPI
	public ResponseMessage<String> yqZjhd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/yq/yqZjhd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String gyqx=(String) ((Map<String, Object>)rslist.get(0).get("jbxx")).get("gyqx");
			Kss_JbxxModelDO jbxx=JSONObject.parseObject(rslist.get(0).get("jbxx").toString(), Kss_JbxxModelDO.class);
			Kss_YqModelDO yq=JSONObject.parseObject(rslist.get(0).get("data").toString(), Kss_YqModelDO.class);
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data");
			entity.put("gyqx",gyqx);
			entity.put("sypzwsh",jbxx.getSypzwsh());
			Map<String, Object> mapls=new HashMap<String, Object>();
			mapls.put("entity",entity);
			String jsons="{'entity':["+ JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String lcid = CacheUtils.get().getFlowKey("_kss_yq".toUpperCase());
			String yqKey = "kss_yq".toUpperCase();
			if (processService.FlowMutex(jsbh, yq.getRybh(), yqKey, "YQ").getStatus() != 200) {
				return processService.FlowMutex(jsbh, yq.getRybh(), yqKey, "YQ");
			}
			if (StringUtils.isNullOrEmpty(jbxx.getCreator())){
				return ResponseMessage.error("creator不能为空！");
			}
			yq.setJsbh(jsbh);
			yq.setXgyqx(jbxx.getGyqx());
			yq.setState("R2");
			yq.setYgyqx(jbxx.getGyqx());
			yq.setFlwsh(jbxx.getSypzwsh());
			yq.setCreator(jbxx.getCreator());
			yq.setPzr(jbxx.getCreator());
			yq.setBlrq(new Date());

			ResponseMessage<String> result = kssServerApis.yqAddflow(lcid,yq);

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
	 * @api {post} /v4/kss/yq/yqdjAdd 延期登记
	 * @apiVersion 0.4.0
	 * @apiName yqdjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 延期登记.
	 *

	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       "entity":[{
	 *                  "rybh":"人员编号(最大字段长度：21)",
	 *                  "taskid":"任务id(必填;最大字段长度：21)",
	 *                  "yqyy":"延期原因(必填;最大字段长度：200)",
	 *                  "ygyqx":"原关押期限(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                  "xgyqx":"现关押期限(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                  "wspzlx":"文书凭证类型(必填;最大字段长度：2)",
	 *                  "flwsh":"法律文书号(必填;最大字段长度：60)",
	 *                  "blrq":"办理日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                  "pzrq":"批准日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                  "id":"id(最大字段长度：23)",
	 *                  "pzr":"批准人(必填;最大字段长度：50)"
	 *          }]
	 *     			}
	 */

	//{"rybh":"\\d{1,21}","yqyy":".{1,200}","wspzlx":"\\d{1,2}","flwsh":"\\S{1,60}","pzr":"\\S{1,50}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","pzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ygyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xgyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"creator":"aa","rybh":"110000114201912040013","taskid":"5255993","yqyy":"dsdsa","ygyqx":"2020-01-08 11:07:38","xgyqx":"2020-01-08 11:07:38","wspzlx":"2","flwsh":"dsad","blrq":"2020-01-08 11:07:38","pzrq":"2020-01-08 11:07:38","pzr":"dsd"}]}
	@ApiOperation("延期登记")
	@PostMapping("/yqdjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yqdjAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/yq/yqdjAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			System.out.println("map------"+map);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_YqModelDO model=JSONObject.parseObject(rslist.get(0).toString(),Kss_YqModelDO.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String taskid=(String) rslist.get(0).get("taskid");
			String creator=(String) rslist.get(0).get("creator");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			model.setJsbh(jsbh);
			model.setCreator(creator);
			model.setCreatetime(new Date());
			model.setUpdator(creator);
			ResponseMessage<String> result =kssServerApis.addYqdjFlow(taskid,model);

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
	 * @api {post} /v4/kss/yq/plyqAdd 批量延期保存
	 * @apiVersion 0.4.0
	 * @apiName plyqAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 批量延期保存.
	 *

	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *       			"data_Jbxx":"基本信息(必填;)"{
	 *       		 				"ywlcid":"业务流程ID(必填;最大字段长度：50)",
	 * 								"bm":"别名(必填;最大字段长度：50)",
	 * 								"rybh":"人员编号(必填;最大字段长度：21)",
	 * 								"snbh":"人员所内编号(必填; 最大字段长度：8)",
	 * 								"jsh":"监室号 (必填; 最大字段长度：4)",
	 * 								"xm":"姓名(必填;最大字段长度：50)",
	 * 								"xb":"性别(必填;最大字段长度：1)",
	 * 								"taskid":"任务id(必填;最大字段长度：21)",
	 * 								"jsbh":"监所编号(必填;最大字段长度：9)",
	 * 								"yqid":"延期id(最大字段长度：23)",
	 * 								"ygyqx":"原关押期限(必填;格式：yyyy-MM-dd hh:mm:ss)"
	 * 	  	                       },
	 * 	  	               "data_ywxx":"业务信息(必填;)"{
	 * 	  	                       	"yqyy":"延期原因(必填;最大字段长度：200)",
	 * 								"xgyqx":"现关押期限(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  							"wspzlx":"文书凭证类型(必填;最大字段长度：2)",
	 *  							"flwsh":"法律文书号(必填;最大字段长度：60)",
	 *  							"blrq":"办理日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  							"blrdh":"办理人电话(最大字段长度：11)",
	 *  							"pzrq":"批准日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  							"pzr":"批准人(必填;最大字段长度：50)"
	 * 	  	 						}
	 *          }]
	 *     			}
	 */

	//{"yqyy":"\\S{1,200}","xgyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","wspzlx":"\\d{1,2}","flwsh":"\\S{1,60}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","pzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","pzr":"\\S{1,50}"}
	//{"entity":[{"data_Jbxx":{"ywlcid":"5255737","bm":"","rybh":"110000114201912040014","snbh":"20190298","jsh":"9009","xm":"123","xb":"1","taskid":"5255993","jsbh":"110000114","ygyqx":"2020-01-08 11:07:38"},"data_ywxx":{"yqyy":"dsadsa","xgyqx":"2020-01-08 11:07:38","wspzlx":"2","flwsh":"ewrewrew","blrq":"2020-01-08 11:07:38","pzrq":"2020-01-08 11:07:38","pzr":"aa"}}]}
	@ApiOperation("批量延期保存")
	@PostMapping("/plyqAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> plyqAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/yq/plyqAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_YqModel yqModel=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(), Kss_YqModel.class);
			Kss_YqModelDO yqpl=JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(), Kss_YqModelDO.class);
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_ywxx");
			Map<String, Object> mapls=new HashMap<String, Object>();
			mapls.put("entity",entity);
			String jsons="{'entity':["+ JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			List<Kss_YqModel> list=new ArrayList();
			list.add(yqModel);
			yqpl.setState("R2");
			yqpl.setCreator(yqModel.getCreator());
			yqpl.setCreatetime(new Date());
			Map<String , Object> mapss = new HashMap<>();
			mapss.put("yq",yqpl);
			mapss.put("jbxx", list);
			ResponseMessage<String> result = kssServerApis.addYqdjPlFlow(mapss);

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
	 * @api {post} /v4/kss/yq/yqYwdt 延期业务动态
	 * @apiVersion 0.4.0
	 * @apiName lscsList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 延期业务动态.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}bpzsxm											不评真实姓名
	 * @apiSuccess {String}qt												其他
	 * @apiSuccess {String}jsbjd											精神病鉴定
	 * @apiSuccess {String}aqfz												案情复杂
	 * @apiSuccess {String}esyq												二审延期
	 * @apiSuccess {String}ysyq												一审延期
	 * @apiSuccess {String}zrs												审查起诉延期
	 * @apiSuccess {String}lcza												流窜作案
	 * @apiSuccess {String}thza												团伙作案
	 * @apiSuccess {String}scqsyq											审查起诉延期
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result": {
	 *     "yqYwdt": [
	 *       {
	 *         "bpzsxm": 5,
	 *         "qt": 0,
	 *         "jsbjd": 1,
	 *         "aqfz": 1,
	 *         "esyq": 0,
	 *         "ysyq": 0,
	 *         "zrs": 11,
	 *         "lcza": 4,
	 *         "thza": 0,
	 *         "scqsyq": 0
	 *       }
	 *     ]
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576826568061
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
	 *          "endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
	 *          "dw":"单位(最大字段长度：50)"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("延期业务动态")
	@PostMapping("/yqYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> yqYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		//bpzsxm,qt,jsbjd,aqfz,esyq,ysyq,zrs,lcza,thza,scqsyq
		//{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
		String interfaceId = "/v4/kss/yq/yqYwdt";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			String starttime=(String)maps.getResult().get("starttime");
			String endtime=(String)maps.getResult().get("endtime");
			ResponseMessage<Map<String, Object>> result = kssAnalyseApis.yqYwdt(jsbh, starttime, endtime);
			System.err.println("--result--" + JSON.toJSONString(result));

			if (result.getStatus() == 200) {
				result.setMessage("查询成功");
				if (result.getResult() == null) {
					result.setMessage("未查询数据");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}



	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_YqModel>> yq_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_YqModel>> result= kssService.yq_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yq_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_YqModel data) {
		return kssService.yq_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yq_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_YqModel data) {
		return kssService.yq_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_YqModel> yq_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.yq_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> yq_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.yq_deleteByKey(id);
	}




	/**
	 * @api {get} /v4/kss/yq/jbxxForYq   延期基本信息查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForYq
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription    延期基本信息查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}creator          				                人员编号
	 * @apiSuccess {String}rybh                                             人员编号
	 * @apiSuccess {String}updator                                          创建人
	 * @apiSuccess {String}state                                            状态(数字字典:ywstate)
	 * @apiSuccess {String}jsbh                                             监所编号
	 * @apiSuccess {String}createtime                                       创建时间
	 * @apiSuccess {String}pzr                                              批准人
	 * @apiSuccess {String}xm                                               姓名
	 * @apiSuccess {String}ayString                                         主要案由(已转换)
	 * @apiSuccess {String}ay                                               主要案由(数字字典:ajlb)
	 * @apiSuccess {String}updatetime                                       更新时间
	 * @apiSuccess {String}jsh                                              监室号
	 * @apiSuccess {String}yqyy                                             延期原因(数字字典:yqyy)
	 * @apiSuccess {String}ygyqx                                            原关押期限
	 * @apiSuccess {String}ygyqxString                                      原关押期限(已转换)
	 * @apiSuccess {String}yqyyString                                       延期原因(已转换)
	 * @apiSuccess {String}flwsh                                            法律文书号
	 * @apiSuccess {String}xgyqx                                            原关押期限
	 * @apiSuccess {String}id                                               id
	 * @apiSuccess {String}xgyqxString                                      原关押期限(已转换)
	 * @apiSuccess {String}rsrqString                                       入所日期(已转换)
	 * @apiSuccess {String}pzrqString                                       批准日期(已转换)
	 * @apiSuccess {String}blrqString                                       办理日期(已转换)
	 * @apiSuccess {String}blrq                                             办理日期
	 * @apiSuccess {String}wspzlx                                           文书凭证类型
	 * @apiSuccess {String}blr                                              办理人
	 * @apiSuccess {String}blrzjh                                           办理人证件号
	 * @apiSuccess {String}rsrq											    入所日期
	 * @apiSuccess {String}blrdh                                            办理人电话
	 * @apiSuccess {String}pzrq                                             批准日期
	 * @apiSuccess {String}jsh                                              监室号

	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 14,
	 *     "data": [
	 *       {
	 *         "yqyy": "3",
	 *         "ygyqx": 1574438400000,
	 *         "ygyqxString": "2019-11-23",
	 *         "yqyyString": "流窜作案",
	 *         "flwsh": "1231",
	 *         "xgyqx": 1575043200000,
	 *         "id": "11000011420191125000247",
	 *         "state": "R2",
	 *         "xgyqxString": "2019-11-30",
	 *         "creator": "管理员",
	 *         "createtime": 1574658604000,
	 *         "rsrqString": "2019-10-10 10:10:10",
	 *         "pzrqString": "2019-11-25 13:09:57",
	 *         "blrqString": "2019-11-25",
	 *         "blrq": 1574658597000,
	 *         "wspzlx": "2",
	 *         "pzr": "管理员",
	 *         "blr": "管理员",
	 *         "blrzjh": "513436200011218206",
	 *         "xm": "汪枫桦",
	 *         "rsrq": 1570673410000,
	 *         "ayString": "煽动分裂国家案",
	 *         "ay": "010130",
	 *         "rybh": "310000111201906200004",
	 *         "updator": "管理员",
	 *         "blrdh": "null",
	 *         "updatetime": 1574658606000,
	 *         "pzrq": 1574658597000,
	 *         "jsh": "0814",
	 *         "jsbh": "110000114"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578639758632
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 * 	  json:{
	 *				"rybh":"人员编号",
	 *				"xm":"姓名已转换",
	 *				"jbxx_xm_type":"姓名类型",
	 *				"jbxx_xmpy":"姓名拼音",
	 *				"jsh":"监室号",
	 *				"jbxx_xb":"性别(已转换)",
	 *				"jbxx_xb_type":"性别(数字字典:xb)",
	 *				"yqyy":"延期原因(已转换)",
	 *				"yqyy_type":"延期原因(数字字典:yqyy)",
	 *				"blrq_start":"开始办理日期",
	 *				"blrq_end":"结束办理日期",
	 *
	 *         }
	 *
	 *
	 */

	@ApiOperation("延期基本信息查询")
	@GetMapping("/jbxxForYq")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jbxxForYq(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/yq/jbxxForYq";
		String state=request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			String xm=null;
			 if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))){
			 	xm=maps.getResult().get("xm").toString();
			 }
			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(xm)) {
				if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm_type"))){
					if ("0".equals(maps.getResult().get("jbxx_xm_type"))) {
						xm = word2py(xm);
						param.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
					} else {
						param.and("jbxx_xm", TermType.like, "%" + xm + "%");
					}
				}else {
					param.and("jbxx_xm", TermType.like, "%" + xm + "%");
				}

			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_jsh_type"))){
					if ("0".equals(maps.getResult().get("jbxx_jsh_type"))) {
						param.and("jbxx_jsh", TermType.like, "%" + maps.getResult().get("jsh").toString() + "%");
					} else {
						param.and("jbxx_jsh", TermType.not, maps.getResult().get("jsh").toString());
					}
				}else {
					param.and("jbxx_jsh", TermType.like, "%" + maps.getResult().get("jsh").toString() + "%");
				}

			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
				if ("0".equals(maps.getResult().get("jbxx_xb_type"))) {
					param.and("jbxx_xb", TermType.eq, maps.getResult().get("jbxx_xb").toString());
				} else {
					param.and("jbxx_xb", TermType.not, maps.getResult().get("jbxx_xb").toString());
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("yqyy"))) {
				if ("0".equals(maps.getResult().get("yqyy_type"))) {
					param.and("yqyy", TermType.like, "%" + maps.getResult().get("yqyy").toString() + "%");
				} else {
					param.and("yqyy", TermType.not, maps.getResult().get("yqyy").toString());
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrq_start"))) {
				param.and("blrq", TermType.gte, maps.getResult().get("blrq_start").toString() + " 00:00:00");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrq_end"))) {
				param.and("blrq", TermType.lte, maps.getResult().get("blrq_end").toString() + " 23:59:59");
			}

			param.and("jbxx_state", TermType.eq, "R8");
			param.and("state", TermType.eq, "R2");
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.jbxxForYq(param);
			System.err.println("result" + JSON.toJSONString(result));
			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().getTotal());
			maplist.put("page", request.getParameter("page"));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			if (list.getStatus() == 200) {
				list.setMessage("查询成功");
				if (list.getResult() == null) {
					list.setMessage("未查询数据");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
	public String word2py(String word) {
		if (StringUtils.isNullOrEmpty(word)) {
			return "";
		}
		return Pinyin4j.cn2Pinyin(word);

	}

}
