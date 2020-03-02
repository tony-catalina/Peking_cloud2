/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;
import awd.bj.kss.model.ZdryModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
import awd.cloud.platform.model.kss.Kss_ZdryModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/zdry")
@Api(tags = "kss-zdry",description = "Zdry") 
public class Kss_ZdryController extends PublicService {
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
	private KssAnalyseApis kssAnalyseApis;
	@Autowired
    private KssService kssService;
	@Autowired
	private ProcessService processService;


	/**
	 * @api {post} /v4/kss/zdry/zdryAdd 在押人员保存
	 * @apiVersion 0.4.0
	 * @apiName zdryAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 在押人员保存.
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
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *                  "rybh": "人员编号(最大长度:21)",
	 *                  "jbxxId": "基本信息id(最大长度:23)",
	 *                  "rygllb": "人员管理类别(必填；最大长度:2)",
	 *                  "ly": "理由(最大长度:200)",
	 *                  "kzcs": "控制措施(最大长度:200)",
	 *                  "qzcs": "强制措施(最大长度:200)",
	 *                  "bz": "备注()",
	 *                  "jbr": "呈批人(必填；最大长度:30)",
	 *                  "blrq": "呈批时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "creator":"创建人(必填；最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"creator":".{1,30}","rygllb":"\\d{1,2}","jbr":"\\S{1,30}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"creator":"aa","rybh":"110000114201912190006","jbxxId":"11000011420191219000445","rygllb":"1","ly":"","kzcs":"","qzcs":"","bz":"","jbr":"管理员","blrq":"2020-01-16 10:14:19"}]}

	@ApiOperation("在押人员保存")
	@PostMapping("/zdryAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdryAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/zdry/zdryAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			ZdryModel zdryModel=JSONObject.parseObject(rslist.get(0).toString(),ZdryModel.class);
			zdryModel.setState("R2");
			zdryModel.setBllx("1");
			zdryModel.setCreatetime(new Date());
			zdryModel.setJsbh(jsbh);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> s = processService.FlowMutex(jsbh, zdryModel.getRybh(), "KSS_ZDRYSZ", "ZDRY");
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			String flowKey = CacheUtils.get().getFlowKey("KSS_ZDRYSZ");
			if (StringUtils.isNullOrEmpty(flowKey)) {
				return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
			}
			ResponseMessage<String> result = kssServerApis.zdryaddFlow(zdryModel, flowKey);
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
	 * @api {post} /v4/kss/zdry/zdrySpAdd 在押人员审批保存
	 * @apiVersion 0.4.0
	 * @apiName zdrySpAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 在押人员审批保存.
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
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *                  "rybh": "人员编号(最大长度:21)",
	 *                  "id": "id(最大长度:23)",
	 *                  "taskid":"任务id()",
	 *                  "ywlcid":"流程id()",
	 *                  "jsh": "监室号(最大长度:4)",
	 * 					"xm": "姓名(最大长度:30)",
	 * 					"bm": "别名(最大长度:30)",
	 * 					"xb": "性别(最大长度:1)",
	 * 					"csrq": "出生日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 					"rsrq": "入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 					"bahj": "办案环节(最大长度:50)",
	 * 					"ay": "案由(最大长度:50)",
	 * 					"hjd": "户籍地(最大长度:120)",
	 *                  "rygllb": "人员管理类别(最大长度:2)",
	 *                  "ly": "理由(最大长度:200)",
	 *                  "kzcs": "控制措施(最大长度:200)",
	 *                  "qzcs": "强制措施(最大长度:200)",
	 *                  "bz": "备注()",
	 *                  "jbr": "呈批人(必填；最大长度:30)",
	 *                  "blrq": "呈批时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "zdzpsbz":"中队长批示标志(必填；最大长度:1)"
	 *                  "zdzyj":"中队长意见内容(必填；最大长度:200)"
	 *                  "zdzxm":"中队长姓名(必填；最大长度:50)"
	 *                  "zdzpssj":"中队长批示时间(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"creator":".{1,30}","zdzxm":"\\S{1,50}","zdzyj":"\\S{1,200}","zdzpsbz":"\\d{1}","jbr":"\\S{1,30}","zdzpssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"type":"1","creator":"aa","rybh":"110000111201907120001","id":"11000011420191218000202","taskid":"5085557","ywlcid":"5085327","jsh":"0101","xm":"","bm":"","xb":"2","csrq":"1986-01-01 10:10:10","rsrq":"2019-05-25 10:10:10","bahj":"12","ay":"060139","hjd":"110101","rygllb":"","ly":"","kzcs":"","qzcs":"","bz":"","jbr":"eee","blrq":"2019-07-25 10:10:10","zdzpsbz":"1","zdzyj":"dsd","zdzxm":"sss","zdzpssj":"2019-12-28 10:10:10"}]}

	@ApiOperation("在押人员审批保存")
	@PostMapping("/zdrySpAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdrySpAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/zdry/zdrySpAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			ZdryModel zdryModel=JSONObject.parseObject(rslist.get(0).toString(),ZdryModel.class);
			String taskid=(String) rslist.get(0).get("taskid");
			String type=(String) rslist.get(0).get("type");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(type)){
				return ResponseMessage.error("type不能为空！");
			}
			zdryModel.setState("R2");
			zdryModel.setBllx("1");
			zdryModel.setCreatetime(new Date());
			zdryModel.setJsbh(jsbh);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			ResponseMessage<String> result = kssServerApis.zdrySpflow(zdryModel, taskid, type);
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
	 * @api {post} /v4/kss/zdry/selectNewResult 查询新的结果
	 * @apiVersion 0.4.0
	 * @apiName selectNewResult
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 查询新的结果.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}result         				                    result

	 *
	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}total                                             返回总数
	 * @apiSuccess {String}data                                              返回数据
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "result": {
	 *           "rygllb": "1",
	 *           "fzss": "",
	 *           "jbxxJsh": "0303",
	 *           "zdzyj": "请问",
	 *           "ywlcid": "3187233",
	 *           "stateString": "有效",
	 *           "ly": "我的",
	 *           "ldpsbz": "1",
	 *           "ldpssj": "2019-09-05 20:26:48",
	 *           "bz": "水电费第三方",
	 *           "id": "11000011420190904000068",
	 *           "state": "R2",
	 *           "jsbhString": "北京市第一看守所",
	 *           "jbxxSnbh": "20190123",
	 *           "ldxm": "管理员",
	 *           "createtime": "2019-09-04 18:30:14",
	 *           "creator": "管理员",
	 *           "zdzpssj": "2019-09-05 20:26:37",
	 *           "zdzpsbz": "2",
	 *           "zdzxm": "管理员",
	 *           "blrqString": "2019-09-04",
	 *           "jzrqString": "",
	 *           "blrq": "2019-09-04 18:29:57",
	 *           "jbxxCsrq": "1925-12-24 00:00:00",
	 *           "jbxxHjd": "110101",
	 *           "rygllbString": "重点",
	 *           "qzcs": "地方是非得失",
	 *           "kzcs": "是非得失",
	 *           "jbxxXm": "11",
	 *           "updatetimeString": "",
	 *           "bllx": "1",
	 *           "rybh": "110000114201909040001",
	 *           "updator": "",
	 *           "jbr": "搭顺风车的身份",
	 *           "ldyj": "请问",
	 *           "createtimeString": "2019-09-04",
	 *           "updatetime": "",
	 *           "jzrq": "",
	 *           "jbxxRsrq": "2019-09-04 09:32:13",
	 *           "jsbh": "110000114"
	 *         }
	 *       }],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1579158673428
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "rybh":"110000112201911040001"
	 *        }

	 */
	//result
	@OpenAPI
	@ApiOperation("查询新的结果")
	@PostMapping("/selectNewResult")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> selectNewResult(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/zdry/selectNewResult";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String rybh=(String) maps.getResult().get("rybh");;
			if (StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				return ResponseMessage.error("人员编号不能空！");
			}
			ResponseMessage<Map<String,Object>> result= kssServerApis.selectNewResult(jsbh,rybh);
			List lists=new ArrayList();
			lists.add(result);
			System.err.println("result" + JSON.toJSONString(result));
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", lists);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", lists.size());
			maplist.put("page", request.getParameter("page"));

			System.err.println("result" + JSON.toJSONString(maplist));

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

	/**
	 * @api {post} /v4/kss/zdry/zdryjcAdd 重点人员解除保存
	 * @apiVersion 0.4.0
	 * @apiName zdryjcAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 重点人员解除保存.
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
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *                  "rybh": "人员编号(最大长度:21)",
	 *                  "jbxxId": "基本信息id(最大长度:23)",
	 *                  "jsh": "监室号(最大长度:4)",
	 * 					"xm": "姓名(最大长度:30)",
	 * 					"bm": "别名(最大长度:30)",
	 * 					"xb": "性别(最大长度:1)",
	 * 					"csrq": "出生日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 					"rsrq": "入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 					"bahj": "办案环节(最大长度:50)",
	 * 					"ay": "案由(最大长度:50)",
	 * 					"hjd": "户籍地(最大长度:120)",
	 *                  "rygllb": "人员管理类别(最大长度:2)",
	 *                  "ly": "理由(必填；最大长度:200)",
	 *                  "kzcs": "控制措施(最大长度:200)",
	 *                  "qzcs": "强制措施(最大长度:200)",
	 *                  "bz": "备注()",
	 *                  "jbr": "呈批人(必填；最大长度:30)",
	 *                  "blrq": "呈批时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"creator":".{1,30}","ly":"\\S{1,200}","jbr":"\\S{1,30}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"creator":"sss","rybh":"110000112201911040001","jbxxId":"11000011220191104000329","jsh":"0101","xm":"aa","bm":"","xb":"1","csrq":"1999-02-01 10:10:10","rsrq":"2019-10-26 10:10:10","bahj":"11","ay":"010410","hjd":"110101","yrygllb":"1","kzcs":"","qzcs":"","ly":"sds","jbr":"vv","blrq":"2019-12-28 10:10:10"}]}

	@ApiOperation("重点人员解除保存")
	@PostMapping("/zdryjcAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdryjcAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/zdry/zdryjcAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			ZdryModel zdryModel=JSONObject.parseObject(rslist.get(0).toString(),ZdryModel.class);
			ResponseMessage<String> s = processService.FlowMutex(jsbh, (String) rslist.get(0).get("rybh"), "KSS_ZDRYCX", "ZDRY");
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			String flowKey = CacheUtils.get().getFlowKey("KSS_ZDRYCX");
			if ("".equals(flowKey)) {
				return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
			}
			zdryModel.setState("R2");
			zdryModel.setBllx("2");
			zdryModel.setJsbh(jsbh);
			zdryModel.setCreatetime(new Date());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_ZDRYCX").get("mapname"));
			mapss.put("zdryModel", zdryModel);
			ResponseMessage<String> result = kssServerApis.zdryaddFlow(zdryModel, flowKey);
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
	 * @api {post} /v4/kss/zdry/zdryYwdt 重点人员业务动态查询
	 * @apiVersion 0.4.0
	 * @apiName zdryYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 重点人员业务动态查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}zdry         				                    重点人员
	 * @apiSuccess {String}jccpry											解除呈批人员

	 *
	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}total                                             返回总数
	 * @apiSuccess {String}data                                              返回数据
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [{
	 *         "result": {
	 *           "zdryYwdt": [{
	 *               "zdry": 150,
	 *               "jccpry": 2
	 *             }]
	 *         },
	 *       }],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1579158673428
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
	 * 			"endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
	 * 			"dw":"单位(最大字段长度：50)"
	 *        }

	 */
	//result
	//{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
	@OpenAPI
	@ApiOperation("重点人员业务动态查询")
	@PostMapping("/zdryYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> zdryYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/zdry/zdryYwdt";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String starttime = (String) maps.getResult().get("starttime");
			String endtime =  (String) maps.getResult().get("endtime");
			ResponseMessage<Map<String,Object>> result= kssAnalyseApis.zdryYwdt(jsbh,starttime,endtime);
			List lists=new ArrayList();
			lists.add(result);
			System.err.println("result" + JSON.toJSONString(result));
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", lists);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", lists.size());
			maplist.put("page", request.getParameter("page"));

			System.err.println("result" + JSON.toJSONString(maplist));

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


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_ZdryModel>> zdry_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_ZdryModel>> result= kssService.zdry_query(queryParam);
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
	public ResponseMessage<String> zdry_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZdryModel data) {
		return kssService.zdry_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdry_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZdryModel data) {
		return kssService.zdry_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_ZdryModel> zdry_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zdry_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> zdry_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zdry_deleteByKey(id);
	}
}
