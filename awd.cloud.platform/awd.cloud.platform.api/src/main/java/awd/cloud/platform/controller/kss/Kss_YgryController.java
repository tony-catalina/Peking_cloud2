/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.CypjModel;
import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.YgryModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import awd.cloud.platform.model.kss.Kss_YgryModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/ygry")
@Api(tags = "kss-ygry",description = "Ygry") 
public class Kss_YgryController extends PublicService {
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
    private KssService kssService;
	@Autowired
	private ProcessService processService;

	/**
	 * @api {post} /v4/kss/ygry/ygrySyList 严管人员查询
	 * @apiVersion 0.4.0
	 * @apiName ygrySyList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 严管人员查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}jbxxSnbh											所内编号
	 * @apiSuccess {String}jbxxXm											姓名
	 * @apiSuccess {String}jbxxJsh											监室号
	 * @apiSuccess {String}jbxxRsrq											入所日期
	 * @apiSuccess {String}jbxxCsrq											出所日期
	 * @apiSuccess {String}jbxxHjd											户籍地
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}ly												理由
	 * @apiSuccess {String}lyString											理由（已转换）
	 * @apiSuccess {String}kzcs												控制措施
	 * @apiSuccess {String}kssj												开始时间
	 * @apiSuccess {String}jssj												结束时间
	 * @apiSuccess {String}blr												办理人
	 * @apiSuccess {String}blrq												办理日期
	 * @apiSuccess {String}bllx												办理类型
	 * @apiSuccess {String}bllxString										办理类型（已转换）
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}zdzpssj											中队长批示时间
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}ldpssj											领导批示时间
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}psbz												批示备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}pastable											是否有效
	 * @apiSuccess {String}pastableString									是否有效（已转换）
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
	 *     			"jbxxSnbh": "20190035",
	 * 				"jbxxXm": "汪枫桦",
	 * 				"jbxxJsh": "0114",
	 * 				"jbxxRsrq": "2019-10-10 10:10:10",
	 * 				"jbxxCsrq": "1997-06-18 00:00:00",
	 * 				"jbxxHjd": "321300",
	 * 				"id": "31000011120190620000123",
	 * 				"rybh": "310000111201906200004",
	 * 				"jsbh": "110000114",
	 * 				"jsbhString": "北京市第一看守所",
	 * 				"ly": "7",
	 * 				"lyString": "有其它严重违规行为，经教育不改的",
	 * 				"kzcs": "",
	 * 				"kssj": "2019-06-20 15:49:10",
	 * 				"jssj": "2019-06-21 15:49:17",
	 * 				"blr": "管理员",
	 * 				"blrq": "2019-10-22 03:02:02",
	 * 				"bllx": "1",
	 * 				"bllxString": "设置",
	 * 				"zdzxm": "",
	 * 				"zdzyj": "",
	 * 				"zdzpssj": null,
	 * 				"ldxm": "管理员",
	 * 				"ldyj": "2222",
	 * 				"ldpssj": "2019-06-20 00:00:00",
	 * 				"bz": "凧",
	 * 				"psbz": "2",
	 * 				"state": "R2",
	 * 				"stateString": "有效",
	 * 				"ywlcid": "3929485",
	 * 				"creator": "管理员",
	 * 				"createtime": "2019-06-20 15:49:27",
	 * 				"updator": "管理员",
	 * 				"updatetime": "2019-11-01 18:02:51",
	 * 				"pastable": "1",
	 * 				"pastableString": ""
	 * 		}],
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
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }
	 *
	 */

	//jbxxSnbh,jbxxXm,jbxxJsh,jbxxRsrq,jbxxCsrq,jbxxHjd,id,rybh,jsbh,jsbhString,ly,lyString,kzcs,kssj,jssj,blr,blrq,bllx,bllxString,zdzxm,zdzyj,zdzpssj,ldxm,ldyj,ldpssj,bz,psbz,state,stateString,ywlcid,creator,createtime,updator,updatetime,pastable,pastableString
	//{"page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("严管人员查询")
	@PostMapping("/ygrySyList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> ygrySyList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/ygry/ygrySyList";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数

			QueryParam qParam = new QueryParam();
			String state = request.getParameter("state$eq");
			String kssj = request.getParameter("kssj$gte");
			String jssj = request.getParameter("jssj$gte");
			String bllx = request.getParameter("bllx$in");
			if(!StringUtils.isNullOrEmpty(kssj)) {
				qParam.and("blrq", TermType.gte, kssj);
			}
			if(!StringUtils.isNullOrEmpty(kssj)) {
				qParam.and("blrq", TermType.lte, jssj);
			}
			if(!StringUtils.isNullOrEmpty(kssj)) {
				qParam.and("bllx", TermType.in, bllx);
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam,"R2");
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.ygryQueryForPage(qParam);
			System.err.println("result" + JSON.toJSONString(result));
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().getTotal());
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
	 * @api {post} /v4/kss/ygry/ygryszList 严管人员设置查询
	 * @apiVersion 0.4.0
	 * @apiName ygryszList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 严管人员设置查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}jbxxSnbh											所内编号
	 * @apiSuccess {String}jbxxXm											姓名
	 * @apiSuccess {String}jbxxJsh											监室号
	 * @apiSuccess {String}jbxxRsrq											入所日期
	 * @apiSuccess {String}jbxxCsrq											出所日期
	 * @apiSuccess {String}jbxxHjd											户籍地
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}ly												理由
	 * @apiSuccess {String}lyString											理由（已转换）
	 * @apiSuccess {String}kzcs												控制措施
	 * @apiSuccess {String}kssj												开始时间
	 * @apiSuccess {String}jssj												结束时间
	 * @apiSuccess {String}blr												办理人
	 * @apiSuccess {String}blrq												办理日期
	 * @apiSuccess {String}bllx												办理类型
	 * @apiSuccess {String}bllxString										办理类型（已转换）
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}zdzpssj											中队长批示时间
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}ldpssj											领导批示时间
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}psbz												批示备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}pastable											是否有效
	 * @apiSuccess {String}pastableString									是否有效（已转换）
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
	 *     			"jbxxSnbh": "20200004",
	 * 				"jbxxXm": "ff",
	 * 				"jbxxJsh": "0201",
	 * 				"jbxxRsrq": "2019-12-03 17:22:34",
	 * 				"jbxxCsrq": "2019-12-03 00:00:00",
	 * 				"jbxxHjd": "110106",
	 * 				"id": "11000011420200120000349",
	 * 				"rybh": "110000114202001020004",
	 * 				"jsbh": "110000114",
	 * 				"jsbhString": "北京市第一看守所",
	 * 				"ly": "7",
	 * 				"lyString": "有其它严重违规行为，经教育不改的",
	 * 				"kzcs": "",
	 * 				"kssj": "2020-01-20 11:33:28",
	 * 				"jssj": "2020-01-20 11:33:30",
	 * 				"blr": "管理员",
	 * 				"blrq": "2020-01-20 11:33:28",
	 * 				"bllx": "1",
	 * 				"bllxString": "设置",
	 * 				"zdzxm": "",
	 * 				"zdzyj": "",
	 * 				"zdzpssj": null,
	 * 				"ldxm": "",
	 * 				"ldyj": "",
	 * 				"ldpssj": null,
	 * 				"bz": "",
	 * 				"psbz": "0",
	 * 				"state": "R2",
	 * 				"stateString": "有效",
	 * 				"ywlcid": "5397844",
	 * 				"creator": "管理员",
	 * 				"createtime": "2020-01-20 11:33:39",
	 * 				"updator": "",
	 * 				"updatetime": null,
	 * 				"pastable": "1",
	 * 				"pastableString": ""
	 * 		}],
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
	 *          "rybh$eq":"人员编号(最大长度:21)",
	 *          "state$eq":"状态(最大长度:2)",
	 *          "bllx$in":办理类型(最大长度:1)",
	 * 			"pastable$eq":是否有效(最大长度:1)",
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }
	 *
	 */

	//jbxxSnbh,jbxxXm,jbxxJsh,jbxxRsrq,jbxxCsrq,jbxxHjd,id,rybh,jsbh,jsbhString,ly,lyString,kzcs,kssj,jssj,blr,blrq,bllx,bllxString,zdzxm,zdzyj,zdzpssj,ldxm,ldyj,ldpssj,bz,psbz,state,stateString,ywlcid,creator,createtime,updator,updatetime,pastable,pastableString
	//{"rybh$eq":"110000114202001020004","state$eq":"R2","bllx$in":"1","pastable$eq":"1","page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("严管人员设置查询")
	@PostMapping("/ygryszList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> ygryszList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/ygry/ygryszList";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String rybh=(String) maps.getResult().get("rybh$eq");
			String state = (String) maps.getResult().get("state$eq");
			String bllx = (String) maps.getResult().get("bllx$in");
			String pastable=(String) maps.getResult().get("pastable$eq");
			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(rybh)) {
				qParam.and("rybh", TermType.gte, rybh);
			}
			if(StringUtils.isNullOrEmpty(state)) {
				return ResponseMessage.error("state不能为空！");
			}
			if(!StringUtils.isNullOrEmpty(bllx)) {
				qParam.and("bllx", TermType.in, bllx);
			}
			if(!StringUtils.isNullOrEmpty(pastable)) {
				qParam.and("pastable", TermType.eq, pastable);
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam,state);
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.ygryQueryForPage(qParam);
			System.err.println("result" + JSON.toJSONString(result));
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().getTotal());
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
	 * @api {post} /v4/kss/ygry/ygryszAdd  严管人员设置保存
	 * @apiVersion 0.4.0
	 * @apiName ygryszAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  严管人员设置保存.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json  											保存参数集(必填)
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 				"snbh":"所内编号(最大字段长度:8)",
	 * 				"xm":"姓名(最大字段长度:30)",
	 * 				"jsh":"监室号(最大字段长度:4)",
	 * 				"kssj":"开始时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"jssj":"结束时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"blrq":"办理日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"blr":"办理人(最大字段长度:30)",
	 * 				"ly":"理由(必填;最大字段长度:12)",
	 * 				"bz":"备注()",
	 * 				"bllx":"办理类型(最大字段长度:2)",
	 * 				"rybh":"人员编号(最大字段长度:21)"
	 * 	        }]
	 * }
	 *
	 *
	 */
	@ApiOperation("严管人员设置保存")
	@PostMapping("/ygryszAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> ygryszAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ly":"\\d{1,12}"}
		//{"entity":[{"creator":"aa","snbh":"20200004","xm":"ff","jsh":"0201","kssj":"2020-01-20 11:33:28","jssj":"2020-01-20 11:33:30","blrq":"2020-01-20 11:33:28","blr":"管理员","ly":"7","bz":"ss","bllx":"1","rybh":"110000114202001020004"}]}
		//接口id
		String interfaceId = "/v4/kss/ygry/ygryszAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String,Object>> rslist=toListMap(map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}

			//封装数据
			ResponseMessage<String> s = processService.FlowMutex(jsbh,rslist.get(0).get("rybh").toString(),"kss_ygsz".toUpperCase(), "YGRY");
			YgryModel ygryModel=JSONObject.parseObject(rslist.get(0).toString(),YgryModel.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			ygryModel.setState("R2");
			ygryModel.setJsbh(jsbh);
			ygryModel.setCreatetime(new Date());
			ygryModel.setPsbz("0");
			ygryModel.setPastable("1");
			if (StringUtils.isNullOrEmpty(ygryModel.getCreator())){
				return ResponseMessage.error("creator不能为空！");
			}
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_YGSZ").get("mapname"));
			mapss.put("ygryModel", ygryModel);
			mapss.put("jbxxModel", jbxxModel);
			ResponseMessage<Map<String, String>> result = kssServerApis.ygryAddflow(mapss);
			System.err.println("--"+ JSON.toJSONString(result));
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
	 * @api {post} /v4/kss/ygry/ygryjcAdd  严管人员解除保存
	 * @apiVersion 0.4.0
	 * @apiName ygryjcAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  严管人员解除保存.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json  											保存参数集(必填)
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 				"snbh":"所内编号(最大字段长度:8)",
	 * 				"xm":"姓名(最大字段长度:30)",
	 * 				"jsh":"监室号(最大字段长度:4)",
	 * 				"blrq":"办理日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"blr":"办理人(最大字段长度:30)",
	 * 				"ly":"理由(必填;最大字段长度:12)",
	 * 				"bz":"备注()",
	 * 				"bllx":"办理类型(最大字段长度:2)",
	 * 				"rybh":"人员编号(最大字段长度:21)"
	 * 	        }]
	 * }
	 *
	 *
	 */
	@ApiOperation("严管人员解除保存")
	@PostMapping("/ygryjcAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> ygryjcAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"ly":"\\d{1,12}"}
		//{"entity":[{"creator":"aa","snbh":"20200004","xm":"ff","jsh":"0201","blrq":"2020-01-20 11:33:28","blr":"管理员","ly":"2","bz":"ss","bllx":"1","rybh":"110000114202001020004"}]}
		//接口id
		String interfaceId = "/v4/kss/ygry/ygryjcAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String,Object>> rslist=toListMap(map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}

			//封装数据
			ResponseMessage<String> s = processService.FlowMutex(jsbh,rslist.get(0).get("rybh").toString(),"kss_ygjc".toUpperCase(), "YGRY");
			YgryModel ygryModel=JSONObject.parseObject(rslist.get(0).toString(),YgryModel.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			QueryParam qParam = new QueryParam();
			qParam.and("rybh", TermType.eq, request.getParameter("rybh"));
			ResponseMessage<PagerResult<Map<String,Object>>> ygryList = kssServerApis.ygryQueryForPage(qParam);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ygryModel.setKssj(sdf.parse(ygryList.getResult().getData().get(0).get("kssj").toString()));
			ygryModel.setState("R2");
			ygryModel.setJsbh(jsbh);
			ygryModel.setCreatetime(new Date());
			ygryModel.setPsbz("0");
			ygryModel.setPastable("1");
			String blrq = (String) rslist.get(0).get("blrq");
			ygryModel.setBlrq(sdf.parse(blrq));
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_YGJC").get("mapname"));
			mapss.put("ygryModel", ygryModel);
			mapss.put("jbxxModel", jbxxModel);
			ResponseMessage<Map<String, String>> result = kssServerApis.ygryAddflow(mapss);
			System.err.println("--"+ JSON.toJSONString(result));
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
	 * @api {post} /v4/kss/ygry/ygryycAdd  严管人员延长保存
	 * @apiVersion 0.4.0
	 * @apiName ygryycAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  严管人员延长保存.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json  											保存参数集(必填)
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 				"snbh":"所内编号(最大字段长度:8)",
	 * 				"xm":"姓名(最大字段长度:30)",
	 * 				"jsh":"监室号(最大字段长度:4)",
	 * 				"blrq":"办理日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"blr":"办理人(最大字段长度:30)",
	 * 				"ly":"理由(必填;最大字段长度:12)",
	 * 				"bz":"备注()",
	 * 				"bllx":"办理类型(最大字段长度:2)",
	 * 				"rybh":"人员编号(最大字段长度:21)"
	 * 	        }]
	 * }
	 *
	 *
	 */
	@ApiOperation("严管人员延长保存")
	@PostMapping("/ygryycAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> ygryycAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"ly":"\\d{1,12}"}
		//{"entity":[{"creator":"aa","snbh":"20190295","xm":"请问123","jsh":"9010","blrq":"2020-01-20 11:33:28","blr":"管理员","ly":"7","bz":"ss","bllx":"4","rybh":"110000114201912040011"}]}
		//接口id
		String interfaceId = "/v4/kss/ygry/ygryycAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String,Object> ss=JSONObject.parseObject(json);
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String,Object>> rslist=toListMap(map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}

			//封装数据
			ResponseMessage<String> s = processService.FlowMutex(jsbh,rslist.get(0).get("rybh").toString(),"kss_ygyc".toUpperCase(), "YGRY");
			YgryModel ygryModel=JSONObject.parseObject(rslist.get(0).toString(),YgryModel.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			ygryModel.setState("R2");
			ygryModel.setJsbh(jsbh);
			ygryModel.setCreatetime(new Date());
			ygryModel.setPsbz("0");
			ygryModel.setPastable("1");
			String blrq = (String) rslist.get(0).get("blrq");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ygryModel.setBlrq(sdf.parse(blrq));
			ygryModel.setKssj(sdf.parse(blrq));
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_YGYC").get("mapname"));
			mapss.put("ygryModel", ygryModel);
			mapss.put("jbxxModel", jbxxModel);
			ResponseMessage<Map<String, String>> result = kssServerApis.ygryAddflow(mapss);
			System.err.println("--"+ JSON.toJSONString(result));
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

	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_YgryModel>> ygry_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_YgryModel>> result= kssService.ygry_query(queryParam);
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
	public ResponseMessage<String> ygry_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_YgryModel data) {
		return kssService.ygry_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ygry_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_YgryModel data) {
		return kssService.ygry_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_YgryModel> ygry_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.ygry_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> ygry_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.ygry_deleteByKey(id);
	}
}
