/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.XzpaModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JbModel;
import awd.cloud.platform.model.kss.Kss_JbModelDO;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jb")
@Api(tags = "kss-jb",description = "Jb") 
public class Kss_JbController extends PublicService {
	@Autowired
	private ProcessService processService;
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {post} /v4/kss/jb/jbList 禁闭查询
	 * @apiVersion 0.4.0
	 * @apiName jbList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 禁闭查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}bllx												办理类型
	 * @apiSuccess {String}bllxString										办理类型（已转换）
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}ly												理由
	 * @apiSuccess {String}lyString											理由（已转换）
	 * @apiSuccess {String}jbqx												禁闭期限
	 * @apiSuccess {String}kssj												开始时间
	 * @apiSuccess {String}syts												禁闭天数
	 * @apiSuccess {String}jssj												结束时间
	 * @apiSuccess {String}ysxm												医生姓名
	 * @apiSuccess {String}ysyj												医生意见
	 * @apiSuccess {String}yspssj											医生批示时间
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}zdzpssj											中队长批示时间
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}ldpssj											领导批示意见
	 * @apiSuccess {String}pastable											是否有效
	 * @apiSuccess {String}pastableString									是否有效（已转换）
	 * @apiSuccess {String}jbr												经办人
	 * @apiSuccess {String}blrq												办理日期
	 * @apiSuccess {String}lsh												流水号
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}psbz												批示标识
	 * @apiSuccess {String}psbzString										批示标识（已转换）
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}xm												姓名
	 * @apiSuccess {String}snbh												所内编号
	 * @apiSuccess {String}jsh												监室号
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
	 * 		"id": "11000011420191230000210",
	 * 		"bllx": "01",
	 * 		"bllxString": "禁闭设置",
	 * 		"jsbh": "110000114",
	 * 		"jsbhString": "北京市第一看守所",
	 * 		"rybh": "110000114201912190001",
	 * 		"ly": "4",
	 * 		"lyString": "占他人财物的",
	 * 		"jbqx": "2020-01-06 10:29:07",
	 * 		"kssj": "2019-12-30 10:29:07",
	 * 		"syts": 4,
	 * 		"jssj": "2020-01-03 10:29:07",
	 * 		"ysxm": "",
	 * 		"ysyj": "",
	 * 		"yspssj": null,
	 * 		"zdzxm": "",
	 * 		"zdzyj": "",
	 * 		"zdzpssj": null,
	 * 		"ldxm": "",
	 * 		"ldyj": "",
	 * 		"ldpssj": null,
	 * 		"pastable": "1",
	 * 		"pastableString": "是",
	 * 		"jbr": "管理员",
	 * 		"blrq": "2019-12-30 10:29:07",
	 * 		"lsh": "",
	 * 		"bz": "",
	 * 		"ywlcid": "5183412",
	 * 		"psbz": "0",
	 * 		"psbzString": "未批示",
	 * 		"state": "R2",
	 * 		"stateString": "有效",
	 * 		"creator": "管理员",
	 * 		"createtime": "2019-12-30 10:29:21",
	 * 		"updator": "",
	 * 		"updatetime": null,
	 * 		"xm": null,
	 * 		"snbh": null,
	 * 		"jsh": null
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
	 *          "rybh":"110000112201911040001",
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }
	 *
	 */
	//id,bllx,bllxString,jsbh,jsbhString,rybh,ly,lyString,jbqx,kssj,syts,jssj,ysxm,ysyj,yspssj,zdzxm,zdzyj,zdzpssj,ldxm,ldyj,ldpssj,pastable,pastableString,jbr,blrq,lsh,bz,ywlcid,psbz,psbzString,state,stateString,creator,createtime,updator,updatetime,xm,snbh,jsh
	//{"rybh$eq":"110000114201912190001","page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("禁闭查询")
	@PostMapping("/jbList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> jbList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/jb/jbList";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String rybh = (String) maps.getResult().get("rybh$eq");
			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(rybh)) {
				qParam.and("rybh", TermType.gte, rybh);
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam, "R2");
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.jbQueryForPage(qParam);
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
	 * @api {post} /v4/kss/jb/jbszAdd 禁闭设置保存
	 * @apiVersion 0.4.0
	 * @apiName jbszAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 禁闭设置保存.
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
	 *                  	"snbh":"所内编号(必填；最大长度:8)",
	 * 						"xm":"姓名(必填；最大长度:30)",
	 * 						"jsh":"监室号(必填；最大长度:4)",
	 * 						"kssj":"开始时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"jssj":"结束时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"jbr":"经办人(必填；最大长度:50)",
	 * 						"blrq":"办理日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"syts":"禁闭天数(必填；最大长度:4)",
	 * 						"ly":"理由(必填；)",
	 * 						"bz":"备注()",
	 * 						"bllx":"办理类型(必填；最大长度:4)",
	 * 						"rybh":"人员编号(必填；最大长度:21)",
	 * 						"jbqx":"禁闭期限(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"snbh":"\\d{1,8}","xm":"\\S{1,30}","jsh":"\\d{1,4}","kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbr":"\\S{1,50}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","syts":"\\d{1,4}","bllx":"\\d{1,4}","rybh":"\\d{1,21}","jbqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"creator":"ss","jsbh":"110000114","snbh":"20190317","xm":"123","jsh":"201","kssj":"2020-01-17 14:22:34","jssj":"2020-01-19 14:22:34","jbr":"管理员","blrq":"2020-01-17 14:22:34","syts":"2","ly":"6","bz":"","bllx":"01","rybh":"110000114201912190006","jbqx":"2020-1-24 14:22:34"}]}

	@ApiOperation("禁闭设置保存")
	@PostMapping("/jbszAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> jbszAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jb/jbszAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_JbModelDO jbModel=JSONObject.parseObject(rslist.get(0).toString(),Kss_JbModelDO.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			String rybh=(String) rslist.get(0).get("rybh");
			String creator=(String) rslist.get(0).get("creator");
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> s = processService.FlowMutex(jsbh, rybh, "KSS_JBSZ", "JB");
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			jbModel.setState("R2");
			jbModel.setJsbh(jsbh);
			jbModel.setCreator(creator);
			jbModel.setCreatetime(new Date());
			jbModel.setPsbz("0");
			jbModel.setPastable("1");
			jbModel.setRybh(rybh);
			String blrq =(String) rslist.get(0).get("blrq");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jbModel.setBlrq(sdf.parse(blrq));
			jbxxModel.setXm(request.getParameter("xm"));
			jbxxModel.setSnbh(request.getParameter("snbh"));
			Map<String, Object> mapss = Maps.newHashMap();
			System.err.println("单独关押设置业务信息" + JSONObject.toJSONString(jbModel));
			System.err.println("单独关押设置基本信息" + JSONObject.toJSONString(jbxxModel));
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_JBSZ").get("mapname"));
			JSONObject kss_jbsz = CacheUtils.get().getFlowMap("KSS_JBSZ");
			System.err.println("jbsz===" + kss_jbsz.toJSONString());
			mapss.put("jbModel", jbModel);
			mapss.put("jbxxModel", jbxxModel);
			ResponseMessage<Map<String, String>> result = kssServerApis.jbAddflow(mapss);
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
	 * @api {post} /v4/kss/jb/jbycAdd 禁闭延长保存
	 * @apiVersion 0.4.0
	 * @apiName jbycAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 禁闭延长保存.
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
	 *                  	"snbh":"所内编号(必填；最大长度:8)",
	 * 						"xm":"姓名(必填；最大长度:30)",
	 * 						"jsh":"监室号(必填；最大长度:4)",
	 * 						"kssj":"开始时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"jssj":"结束时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"jbr":"经办人(必填；最大长度:50)",
	 * 						"blrq":"办理日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"syts":"禁闭天数(必填；最大长度:4)",
	 * 						"ly":"理由(必填；)",
	 * 						"bz":"备注()",
	 * 						"bllx":"办理类型(必填；最大长度:4)",
	 * 						"rybh":"人员编号(必填；最大长度:21)",
	 * 						"jbqx":"禁闭期限(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"snbh":"\\d{1,8}","xm":"\\S{1,30}","jsh":"\\d{1,4}","kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbr":"\\S{1,50}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","syts":"\\d{1,4}","bllx":"\\d{1,4}","rybh":"\\d{1,21}","jbqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"creator":"ss","jsbh":"110000114","snbh":"20190317","xm":"123","jsh":"201","kssj":"2020-01-17 14:22:34","jssj":"2020-01-19 14:22:34","jbr":"管理员","blrq":"2020-01-17 14:22:34","syts":"2","ly":"6","bz":"aa","bllx":"01","rybh":"110000114201912190006","jbqx":"2020-1-24 14:22:34"}]}

	@ApiOperation("禁闭延长保存")
	@PostMapping("/jbycAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> jbycAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jb/jbycAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_JbModelDO jbModel=JSONObject.parseObject(rslist.get(0).toString(),Kss_JbModelDO.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			String rybh=(String) rslist.get(0).get("rybh");
			String creator=(String) rslist.get(0).get("creator");
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> s = processService.FlowMutex(jsbh, rybh, "KSS_JBYC", "JB");
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			jbModel.setState("R2");
			jbModel.setJsbh(jsbh);
			jbModel.setCreatetime(new Date());
			jbModel.setPsbz("0");
			jbModel.setPastable("1");
			String blrq = (String) rslist.get(0).get("blrq");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jbModel.setBlrq(sdf.parse(blrq));
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_JBYC").get("mapname"));
			System.err.println("单独关押延长业务信息" + JSONObject.toJSONString(jbModel));
			System.err.println("单独关押延长基本信息" + JSONObject.toJSONString(jbxxModel));
			mapss.put("jbModel", jbModel);
			mapss.put("jbxxModel", jbxxModel);
			System.err.println("************邹嘉w*********");
			System.err.println("保存成功");
			ResponseMessage<Map<String, String>> result = kssServerApis.jbAddflow(mapss);
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
	public ResponseMessage<PagerResult<Kss_JbModel>> jb_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JbModel>> result= kssService.jb_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}

	/**
	 * @api {post} /v4/kss/jb/jbjcAdd 禁闭解除保存
	 * @apiVersion 0.4.0
	 * @apiName jbjcAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 禁闭解除保存.
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
	 *                  	"snbh":"所内编号(必填；最大长度:8)",
	 * 						"xm":"姓名(必填；最大长度:30)",
	 * 						"jsh":"监室号(必填；最大长度:4)",
	 * 						"jbr":"经办人(必填；最大长度:50)",
	 * 						"blrq":"办理日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 						"ly":"理由(必填；)",
	 * 						"bz":"备注()",
	 * 						"bllx":"办理类型(必填；最大长度:4)",
	 * 						"rybh":"人员编号(必填；最大长度:21)",
	 *          		}]
	 *     	  }
	 */

	//{"snbh":"\\d{1,8}","xm":"\\S{1,30}","jsh":"\\d{1,4}","jbr":"\\S{1,50}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","bllx":"\\d{1,4}","rybh":"\\d{1,21}"}
	//{"entity":[{"creator":"ss","jsbh":"110000114","snbh":"20190317","xm":"123","jsh":"201","jbr":"管理员","blrq":"2020-01-17 14:22:34","ly":"6","bz":"","bllx":"01","rybh":"110000114201912190006"}]}

	@ApiOperation("禁闭解除保存")
	@PostMapping("/jbjcAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> jbjcAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jb/jbjcAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_JbModelDO jbModel=JSONObject.parseObject(rslist.get(0).toString(),Kss_JbModelDO.class);
			JbxxModel jbxxModel=JSONObject.parseObject(rslist.get(0).toString(),JbxxModel.class);
			String rybh=(String) rslist.get(0).get("rybh");
			String creator=(String) rslist.get(0).get("creator");
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> s = processService.FlowMutex(jsbh, rybh, "KSS_JBJC", "JB");
			if (s.getStatus()!=200) {
				return ResponseMessage.error(s.getMessage());
			}
			jbModel.setState("R2");
			jbModel.setJsbh(jsbh);
			jbModel.setCreatetime(new Date());
			jbModel.setPsbz("0");
			jbModel.setPastable("1");
			String blrq = (String) rslist.get(0).get("blrq");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			jbModel.setBlrq(sdf.parse(blrq));
			Map<String, Object> mapss = Maps.newHashMap();
			mapss.put("lcid", CacheUtils.get().getFlowMap("KSS_JBJC").get("mapname"));
			mapss.put("jbModel", jbModel);
			mapss.put("jbxxModel", jbxxModel);
			ResponseMessage<Map<String, String>> result = kssServerApis.jbAddflow(mapss);
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

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jb_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JbModel data) {
		return kssService.jb_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jb_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JbModel data) {
		return kssService.jb_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JbModel> jb_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jb_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jb_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jb_deleteByKey(id);
	}
}
