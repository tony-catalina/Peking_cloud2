/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.JkqkModel;
import awd.bj.kss.model.JstzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_JstzLdspModel;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.kss.JstzModelDO;
import awd.cloud.platform.model.kss.Kss_JstzModel;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.cloud.platform.model.kss.Kss_YqModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jstz")
@Api(tags = "kss-jstz",description = "Jstz") 
public class Kss_JstzController extends PublicService {

	@Autowired
	private  ProcessService processService;
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {post} /v4/kss/jstz/jstzQr 监室确认
	 * @apiVersion 0.4.0
	 * @apiName jstzQr
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监室确认.
	 *
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
	 *       	"jstz":[{
	 *       		 	"bz":"备注()",
	 *  				"tzsj":"调整时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 * 	  	            "gjxm":"管教(必填;最大字段长度：30)",
	 * 					"djrq":"登记日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  				"yjsh":"原监室号(最大字段长度：4)",
	 *  				"xjsh":"现监室号(最大字段长度：4)",
	 *  				"ywlcid":"业务流程id(最大字段长度：18)",
	 *  				"rybh":"人员编号(最大字段长度：21)",
	 *  				"taskid":"任务id(最大字段长度：30)",
	 *  				"id":"id(最大字段长度：23)",
	 *  				"lyString":"理由(最大字段长度：60)",
	 *  				"ly":"理由(字典转换)(最大字段长度：1)"
	 *          		}]
	 *     	   }
	 */

	//{"gjxm":"\\S{1,30}","tzsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"jstz":[{"bz":"","tzsj":"2020-01-15 09:31:26","gjxm":"管理员","djrq":"2020-01-15 09:31:26","yjsh":"9101","xjsh":"0101","ywlcid":"5310042","rybh":"110000114201912040005","taskid":"5330153","id":"11000011420191204000415","processName":"kss_jstz","processNode":"kss_jstz_tzqr","lyString":"聚众闹事","ly":"3"}]}
	@ApiOperation("监室确认")
	@PostMapping("/jstzQr")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})

	@OpenAPI
	public ResponseMessage<String> jstzQr(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jstz/jstzQr";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("jstz").toString());
			Map<String, Object> entity=rslist.get(0);
			String jsons="{'entity':["+ JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Kss_JstzLdspModel> jstzlist = new ArrayList<>();
			for (int i = 0; i < rslist.size(); i++) {
				Kss_JstzLdspModel jstzLdspModels = new Kss_JstzLdspModel();
				JstzModel jstzModel = new JstzModel();
				jstzModel.setRybh((String) rslist.get(i).get("rybh"));
				jstzModel.setYwlcid((String) rslist.get(i).get("ywlcid"));
				jstzModel.setBz((String) rslist.get(i).get("bz"));
				Date tzsj = format.parse((String) rslist.get(i).get("tzsj"));
				Date djsj = format.parse((String) rslist.get(i).get("djrq"));
				jstzModel.setTzsj(tzsj);
				jstzModel.setYjsh((String) rslist.get(i).get("yjsh"));
				jstzModel.setXjsh((String) rslist.get(i).get("xjsh"));
				jstzModel.setDjsj(djsj);
				jstzModel.setGjxm((String) rslist.get(i).get("gjxm"));
				jstzModel.setJsbh(jsbh);
				jstzModel.setYyString((String) rslist.get(i).get("lyString"));
				jstzModel.setYy((String) rslist.get(i).get("ly"));
				jstzLdspModels.setTaskid((String) rslist.get(i).get("taskid"));
				jstzLdspModels.setJstzEntity(jstzModel);
				jstzlist.add(jstzLdspModels);
			}
			System.err.println("jstzlist--------"+jstzlist);

			ResponseMessage<String> result = kssServerApis.addJstzAndJbxx(jstzlist);

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
	 * @api {post} /v4/kss/jstz/jstzList 监室调整查询
	 * @apiVersion 0.4.0
	 * @apiName jstzList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监室调整查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}csrq												出生日期
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}dah												档案编号
	 * @apiSuccess {String}ldpssj											领导批示时间
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}yjsh												原监室号
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}yy											    原因
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}zdzpssj											中队长批示时间
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}rsrqString										入所日期（已转换）
	 * @apiSuccess {String}csrqString										出所日期（已转换）
	 * @apiSuccess {String}xb												性别
	 * @apiSuccess {String}yyString											原因（已转换）
	 * @apiSuccess {String}psbz												批示标识
	 * @apiSuccess {String}xbString											性别（已转换）
	 * @apiSuccess {String}zdzpssjString									中队长批示时间（已转换）
	 * @apiSuccess {String}updatetimeString									更新时间（已转换）
	 * @apiSuccess {String}xm												姓名
	 * @apiSuccess {String}rsrq												入所日期
	 * @apiSuccess {String}ayString											案由（已转换）
	 * @apiSuccess {String}ay												案由
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}createtimeString									更新时间（已转换）
	 * @apiSuccess {String}tjr												调监人
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}xjsh												现监室号
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
	 *   "result":
	 *   {
	 *     "total": 75,
	 *     "data": [{
	 * 	"csrq": 866563200000,
	 * 	"ywlcid": "4707373",
	 * 	"dah": "123123",
	 * 	"ldpssj": 1574733139000,
	 * 	"id": "11000011420191126000520",
	 * 	"state": "R2",
	 * 	"yjsh": "0102",
	 * 	"ldxm": "管理员",
	 * 	"yy": "1",
	 * 	"creator": "管理员",
	 * 	"createtime": 1574733094000,
	 * 	"zdzpssj": 1574733125000,
	 * 	"zdzxm": "管理员",
	 * 	"rsrqString": "2019-10-10",
	 * 	"csrqString": "1997-06-18",
	 * 	"xb": "1",
	 * 	"yyString": "打架",
	 * 	"psbz": "1",
	 * 	"xbString": "男性",
	 * 	"zdzpssjString": "2019-11-26 09:52:05",
	 * 	"updatetimeString": "2019-11-26 09:42:23",
	 * 	"xm": "汪枫桦",
	 * 	"rsrq": 1570673410000,
	 * 	"ayString": "煽动分裂国家案",
	 * 	"ay": "010130",
	 * 	"rybh": "310000111201906200004",
	 * 	"updator": "管理员",
	 * 	"ldyj": "1231564",
	 * 	"updatetime": 1574732543000,
	 * 	"createtimeString": "2019-11-26 09:51:34",
	 * 	"tjr": "管理员",
	 * 	"jsbh": "110000114",
	 * 	"xjsh": "0201"
	 * }],
	 * "page": "1"
	 * },
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
	 *          "sort":"id",
	 *          "order":"desc",
	 *          "page":"1",
	 *          "rows":"10"
	 *        }
	 *
	 *
	 */

	@OpenAPI
	@ApiOperation("监室调整查询")
	@PostMapping("/jstzList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jstzList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"page":"1","rows":"10","sort":"id","order":"desc"}
		//csrq,ywlcid,dah,ldpssj,id,state,yjsh,ldxm,yy,creator,createtime,zdzpssj,zdzxm,rsrqString,csrqString,xb,yyString,psbz,xbString,zdzpssjString,updatetimeString,xm,rsrq,ayString,ay,rybh,updator,ldyj,updatetime,createtimeString,tjr,jsbh,xjsh
		String interfaceId = "/v4/kss/jstz/jstzList";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam queryParam = new QueryParam();
			String xm = (String) maps.getResult().get("xm");
			String xm_type = (String) maps.getResult().get("xm_type");
			String xb = (String) maps.getResult().get("xb");
			String xb_type = (String) maps.getResult().get("xb_type");
			String rsrq_start = (String) maps.getResult().get("rsrq_start");
			String rsrq_end = (String) maps.getResult().get("rsrq_end");
			String jsh = (String) maps.getResult().get("jsh");
			String tzsj_start = (String) maps.getResult().get("blrqstart");
			String tzsj_end = (String) maps.getResult().get("blrqend");
			String bm = (String) maps.getResult().get("bm");
			String bm_type = (String) maps.getResult().get("bm_type");
			String bahj=(String) maps.getResult().get("bahj");
			String gyqx_start=(String) maps.getResult().get("gyqx_start");
			String gyqx_end=(String) maps.getResult().get("gyqx_end");
			if(!StringUtils.isNullOrEmpty(bahj)) {
				queryParam.and("jbxx_bahj", TermType.like, bahj);
			}
			if(!StringUtils.isNullOrEmpty(gyqx_start)) {
				queryParam.and("jbxx_gyqx", TermType.gte, gyqx_start);
			}
			if(!StringUtils.isNullOrEmpty(gyqx_end)) {
				queryParam.and("jbxx_gyqx", TermType.lte, gyqx_end);
			}
			if (!StringUtils.isNullOrEmpty(xm)) {
				if ("0".equals(xm_type)) {
					String xmpy = word2py(xm);
					queryParam.and("jbxx_xmpy", TermType.like, "%"+xmpy+"%");
				} else {
					queryParam.and("jbxx_xm", TermType.like, "%"+xm+"%");
				}
			}
			if (!StringUtils.isNullOrEmpty(bm)) {
				String bmpx = word2py(bm);
				if ("0".equals(bm_type)) {
					queryParam.and("jbxx_bmpy", TermType.eq, bmpx);
				} else {
					queryParam.and("jbxx_bm", TermType.like, "%"+bm+"%");
				}
			}
			if (!StringUtils.isNullOrEmpty(xb)) {
				if ("0".equals(xb_type)) {
					queryParam.and("jbxx_xb", TermType.eq, xb);
				} else {
					queryParam.and("jbxx_xb", TermType.not, xb);
				}
			}
			if (!StringUtils.isNullOrEmpty(jsh)) {
				queryParam.and("xjsh", TermType.eq, jsh);
			}
			if(!StringUtils.isNullOrEmpty(rsrq_start)) {
				queryParam.and("jbxx_rsrq", TermType.gte, rsrq_start);
			}
			if(!StringUtils.isNullOrEmpty(rsrq_end)) {
				queryParam.and("jbxx_rsrq", TermType.lte, rsrq_end);
			}
			if(!StringUtils.isNullOrEmpty(tzsj_start)) {
				queryParam.and("tzsj", TermType.gte, tzsj_start);
			}
			if(!StringUtils.isNullOrEmpty(tzsj_end)) {
				queryParam.and("tzsj", TermType.lte, tzsj_end);
			}
			queryParam.and("jbxx_state", TermType.eq, "R8");
			queryParam.and("jsbh", TermType.eq, jsbh);
			queryParam.and("jbxx_jsbh", TermType.eq,jsbh);
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, "R2");
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jstzQueryJbxxForPage(queryParam);

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
	public String word2py(String word) {
		if (StringUtils.isNullOrEmpty(word)) {
			return "";
		}
		return Pinyin4j.cn2Pinyin(word);

	}


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_JstzModel>> jstz_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JstzModel>> result= kssService.jstz_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}



	/**
	 * @api {post} /v4/kss/jstz/jstzMutex 监室调整过程保存
	 * @apiVersion 0.4.0
	 * @apiName jstzMutex
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 监室调整过程保存
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "保存成功",
	 *   "status": 200,
	 *   "timestamp": 1578886392184
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *
	 *   "appcode":"应用代码(必填)",
	 *   "jsbh":"监所编号(必填; 最大长度:9)",
	 *   json:{
	 *   "entity":[{
	 *           "rybh":"人员编号(必填; 最大长度:21)"
	 *          }]
	 *        }
	 *
	 *
	 * @return
	 */
	@ApiOperation("监室调整过程保存")
	@PostMapping("/jstzMutex")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jstzMutex(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jstz/jstzMutex";
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
			System.err.println(map.get("entity").toString());

			List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Map<String, Object> entityMap = mapsList.get(0);

			if (StringUtils.isNullOrEmpty(entityMap.get("rybh"))) {
				return ResponseMessage.error("rybh不可为空");
			}

			String rybh = (String) entityMap.get("rybh");
			String jstzKey = "kss_jstz".toUpperCase();
			if (processService.FlowMutex(jsbh, rybh, jstzKey, "JSTZ").getStatus()!=200) {
				return processService.FlowMutex(jsbh, rybh, jstzKey, "JSTZ");
			}

			ResponseMessage result = new ResponseMessage();
		 	  result.setResult("保存成功");
			  result.setMessage("保存成功");
              result.setStatus(200);
                return result;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");

		}

	}




	/**
	 * @api {post} /v4/kss/jstz/jstzAdd 监事调整的保存操作
	 * @apiVersion 0.4.0
	 * @apiName jstzAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 监事调整的保存操作
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "保存成功",
	 *   "status": 200,
	 *   "timestamp": 1578886392184
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *
	 *   "appcode":"应用代码(必填)",
	 *   "jsbh":"监所编号(必填; 最大长度:9)",
	 *   json{
	 *        "entity":[{
	 *              "creator":"人员编号(必填; 最大长度:30)",
	 *              "xjsh":"现监室(必填; 最大长度:4)",
	 *              "yy":"原因(必填; 字典：TJYY；最大长度:1)",
	 *              "tjr":"调监人(必填; 最大长度:30)",
	 *              "yjsh":"原监室(必填; 最大长度:4)",
	 * 	            "rybh":"校验人(必填; 最大长度:21)",
	 * 	            "createtime":"创建时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *              "xm":"姓名(必填; 最大长度:50)",
	 *              "xb":"性别(必填;字典：XB 最大长度:50)",
	 *              "snbh":"所内编号(必填; 最大长度:8)"
	 *        }]
	 *   }
	 */
	//{"creator":".{1,30}","xjsh":"\\d{1,4}","yy":"\\d{1,1}","tjr":".{1,30}","yjsh":"\\d{1,4}","rybh":"\\d{1,21}","createtime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xm":".{1,50}","xb":"\\d{1,1}","snbh":"\\d{1,8}"}
	@ApiOperation("监事调整的保存操作")
	@PostMapping("/jstzAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> jstzAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/jstz/jstzAdd";
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
			System.err.println(map.get("entity").toString());

			String key = "KSS_JSTZ";

			JstzModel  jstzModel = JSONArray.parseArray(map.get("entity").toString(), JstzModel.class).get(0);

			jstzModel.setState("R2");
			jstzModel.setJsbh(jsbh);
			jstzModel.setPsbz("0");

			JstzModelDO jmd = new JstzModelDO();
			jmd.setLcid(CacheUtils.get().getFlowKey(key));

			JbxxModelDO  jbxxModel = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class).get(0);
			jmd.setJbxxEntity(jbxxModel);
			jmd.setJstzEntity(jstzModel);
			System.err.println("jstzModel==="+ JSONUtil.toJson(jstzModel));
			System.err.println("jbxxModel==="+JSON.toJSONString(jbxxModel));
			String processDefinitionId = CacheUtils.get().getFlowKey(key);
			System.err.println("流程id------------------" + processDefinitionId);

			System.out.println("\33[32;4m监事调整登记校验成功\33[30;0m");

			System.err.println("保存成功");

			ResponseMessage<Map<String, String>> result = kssServerApis.addJstz(jmd);

			System.err.println("result--" + JSON.toJSONString(result));

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












	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jstz_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JstzModel data) {
		return kssService.jstz_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JstzModel> jstz_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jstz_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jstz_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jstz_deleteByKey(id);
	}
}
