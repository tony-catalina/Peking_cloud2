/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.HjbdModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_HjbdModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/hjbd")
@Api(tags = "kss-hjbd",description = "Hjbd") 
public class Kss_HjbdController  extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	@Autowired
	private KssAnalyseApis kssAnalyseApis;


	/**
	 * @api {get} /v4/kss/hjbd/jbxxForHjbd 环节变动基本信息查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForJwzx
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 环节变动基本信息查询
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}xm                                                姓名
	 * @apiSuccess {String}jsbh                                              监所编号
	 * @apiSuccess {String}jsh                                               监室号
	 * @apiSuccess {String}rybh                                              人员编号
	 *
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
	 *    "data":
	 *    {
	 *        "xm": "图片1",
	 *         "rybh": "110000114201908200004",
	 *         "jsh": "0825",
	 *         "jsbh": "110000114"
	 *         }]
	 *     "page": "1"
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
	 *           "xm":"姓名(最大字段长度：50)",
	 *           "jbxx_xm_type":"姓名类别(0:根据姓名查；!=0:不根据姓名查)",
	 *           "jsh":"监室号(最大字段长度：4)",
	 * 	         "jbxx_jsh_type":"监室号类别(0：根据监室号查；!=0:不根据监室号查)",
	 * 	         "jbxx_xb":"性别(字典：XB ；最大字段长度：1)",
	 * 	         "jbxx_xb_type":"性别类型(0:根据性别查；!=0:不根据性别查)",
	 * 	         "yssjd":"原诉讼阶段(字典：BAJD ；最大字段长度：2)"
	 * 	         "yssjd_type":"原诉讼阶段类型(0:根据原诉讼阶段查；!=0:不根据原诉讼阶段查)"
	 * 	         "bahj":"办案环节( 字典：BAJD；最大字段长度：2)",
	 * 	         "bahj_type":"办案环节类型(0:根据办案环节查；!=0:不根据办案环节查)",
	 * 	         "blrqstart":"开始办理日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 	         "blrqend":"结束办理日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * 	         "jbxx_jsbh":"监所编号(最大字段长度：9)",
	 *        }
	 *
	 */
	@OpenAPI
	@ApiOperation("环节变动基本信息查询")
	@GetMapping("/jbxxForHjbd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jbxxForHjbd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/hjbd/jbxxForHjbd";
		String state ="R8";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数

			QueryParam qParam = new QueryParam();

			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				String xm = maps.getResult().get("xm").toString();
				if ("0".equals( maps.getResult().get("jbxx_xm_type"))) {
					xm = word2py(xm);
					qParam.and("jbxx_xmpy", TermType.like, "%"+xm+"%");
				}else{
					qParam.and("jbxx_xm",TermType.like,"%"+xm+"%");
				}
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))){
				qParam.and("jbxx_jsh", TermType.like, maps.getResult().get("jsh")+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
				qParam.and("bdrq", TermType.gte, maps.getResult().get("blrqstart"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
				qParam.and("bdrq", TermType.lte, maps.getResult().get("blrqstart"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
				String xb = maps.getResult().get("jbxx_xb").toString();
				if("0".equals(maps.getResult().get("jbxx_xb_type"))){
					qParam.and("jbxx_xb",TermType.eq,xb);
				}else{
					qParam.and("jbxx_xb",TermType.not, xb);
				}
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("yssjd"))) {
				String yssjd = maps.getResult().get("yssjd").toString();
				if("0".equals(maps.getResult().get("yssjd_type"))){
					qParam.and("yssjd",TermType.eq,yssjd);
				}else{
					qParam.and("yssjd",TermType.not, yssjd);
				}
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("bahj"))) {
				String bahj =maps.getResult().get("bahj").toString();
				if("0".equals( maps.getResult().get("bahj_type"))){
					qParam.and("bahj",TermType.eq,bahj);
				}else{
					qParam.and("bahj",TermType.not, bahj);
				}
			}

			qParam.and("jsbh",TermType.eq,jsbh);
			qParam.and("jbxx_jsbh",TermType.eq,jsbh);
			qParam.and("jbxx_state",TermType.eq,"R8");
			qParam.and("state",TermType.eq,"R2");

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jbxxForHjbd(qParam);

			System.err.println("result" + JSON.toJSONString(result));

			//封装需要的数据
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


	/**
	 * @api {post} /v4/kss/hjbd/hjbdPlAdd  批量保存（环节变动）
	 * @apiVersion 0.4.0
	 * @apiName hjbdPlAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 批量保存（环节变动）
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
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
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *     "entity":[{
	 *         "czr":"操作人"
	 *         "czsj":"操作时间(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *         "bdrq":"变动日期(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *              "rybh":"人员编号(必填;  最大字段长度：21)",
	 *              "badw":"办案单位(必填;  最大字段长度：60)",
	 *              "bahj":"办案环节(必填; 字典：BAJD ;最大字段长度：2)",
	 *              "bar":"办案人(必填;  最大字段长度：30)"
	 *              "bardh":"办案民警电话(必填;  最大字段长度：40)"
	 *              "dwlx":"办案单位类型(必填; 字典：DWLX ;最大字段长度：1)"
	 *              "gyqx":"关押期限(必填；格式:yyyy-MM-dd hh:mm:ss)"
	 *              "ybar": "原办案人(必填；最大字段长度：60)",
	 * 	            "ybadwlx": "原办案单位类型(必填; 字典：DWLX ;最大字段长度：1)",
	 * 	            "yssjd": "原诉讼阶段(必填; 字典：BAJD ;最大字段长度：2)",
	 *          	"wsh": "文书号(必填;  最大字段长度：40)",
	 * 	            "ybadw": "原办案单位(必填;  最大字段长度：60)",
	 * 	            "creator": "创建人(必填;  最大字段长度：50)",
	 * 	            "createtime": "创建时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *           	"wspzlx": "文书凭证类型(必填; 字典：wspzlx ;最大字段长度：2)",
	 * 	            "ybardh": "原办案人电话(最大字段长度：40)",
	 * 	            "yjyqx": "原羁押期限(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 * 	            "wssdrq": "文书送达日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *              }]
	 *        }
	 */
	//  {"czsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","bdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","badw":".{1,60}","creator":".{1,50}","rybh":"\\d{1,21}","bahj":"\\d{1,2}","bar":".{1,30}","bardh":"\\d{1,40}","dwlx":"\\d{1,1}","gyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ybar":".{1,60}","ybadwlx":"\\d{1,1}","yssjd":"\\d{1,2}","wsh":".{1,40}","createtime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","wspzlx":"\\d{1,2}","ybardh":"\\d{1,40}","yjyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","wssdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}



	@ApiOperation("批量保存（环节变动）")
	@PostMapping("/hjbdPlAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> hjbdPlAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/hjbd/hjbdPlAdd";
		String state = request.getParameter("state");
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

			List<JbxxModelDO> list = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class);


			List<HjbdModel> l = Lists.newArrayList();
			for(JbxxModelDO jbxx:list) {

				List<HjbdModel> hjbdlist = JSONArray.parseArray(map.get("entity").toString(), HjbdModel.class);
				HjbdModel hjbdModel = hjbdlist.get(0);

				List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
				Map<String,Object> entityMap = mapsList.get(0);


				if(StringUtils.isNullOrEmpty(entityMap.get("czr").toString())){
					return ResponseMessage.error("请输入操作人");
				}

				System.err.println("jbxxList: ============"+jbxx.getRybh());
				hjbdModel.setRybh(jbxx.getRybh());
				hjbdModel.setJsbh(jsbh);
				hjbdModel.setState("R2");
				hjbdModel.setYbadw(jbxx.getBadw());
				hjbdModel.setYssjd(jbxx.getBahj());
				hjbdModel.setYbar(jbxx.getBar());
				hjbdModel.setYbardh(jbxx.getBardh());
				hjbdModel.setYbadwlx(jbxx.getDwlx());
				hjbdModel.setYjyqx(jbxx.getGyqx());
				hjbdModel.setCreator(entityMap.get("czr").toString());
				hjbdModel.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entityMap.get("czsj").toString()));
				hjbdModel.setBdrq(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(entityMap.get("bdrq").toString()));
				l.add(hjbdModel);
				System.err.println("jbxx----------"+JSON.toJSONString(jbxx));
			}
			System.err.println(JSONUtil.toJson(l));
			System.err.println("===============");
			ResponseMessage<String> result = kssServerApis.saveHjbdPl(l);


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


	/**
	 * @return
	 * @api {get} /v4/kss/hjbd/hjbdYwdt 环节变动业务动态
	 * @apiVersion 0.4.0
	 * @apiName hjbdYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 环节变动业务动态
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}hjbdYwdt                                          环节变动业务动态
	 * @apiSuccess {String}xsjl                                              刑事拘留
	 * @apiSuccess {String}qt                                                其他
	 * @apiSuccess {String}jcbczc                                            检查补充侦查
	 * @apiSuccess {String}scqs3                                             审查起诉（3）
	 * @apiSuccess {String}scqs2                                             审查起诉（2）
	 * @apiSuccess {String}dzx                                               待执行
	 * @apiSuccess {String}xsjl                                               巡视记录
	 * @apiSuccess {String}ysssq                                              一审上诉期
	 * @apiSuccess {String}es                                                 二审
	 * @apiSuccess {String}sxfh                                               死刑复核
	 * @apiSuccess {String}yj                                                 已决
	 * @apiSuccess {String}gabczc2                                            公安补充侦察（2）
	 * @apiSuccess {String}gabczc1                                            公安补充侦察（1）
	 * @apiSuccess {String}zs                                                 再审
	 * @apiSuccess {String}ys                                                 一审
	 * @apiSuccess {String}zrs                                                总人数
	 * @apiSuccess {String}scqs1                                              审查起诉（1）
	 * @apiSuccess {String}fhcs                                               发回重审
	 * @apiSuccess {String}db                                                 逮捕
	 *
	 *
	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}total                                             返回总数
	 * @apiSuccess {String}data                                              返回数据
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "查询成功",
	 * "result": {
	 * "total": 1,
	 * "data": [
	 * "result": {
	 *           "hjbdYwdt": [
	 *             {
	 *               "jcbczc": 3,
	 *               "scqs3": 4,
	 *               "scqs2": 5,
	 *               "dzx": 2,
	 *               "qt": 2,
	 *               "xsjl": 86,
	 *               "ysssq": 4,
	 *               "es": 2,
	 *               "sxfh": 2,
	 *               "yj": 2,
	 *               "gabczc2": 7,
	 *               "gabczc1": 26,
	 *               "zs": 4,
	 *               "ys": 1,
	 *               "zrs": 392,
	 *               "scqs1": 9,
	 *               "fhcs": 3,
	 *               "db": 128
	 *             }
	 *           ]
	 *         }
	 *       }
	 *     ],
	 * "page": "1"
	 * },
	 * "status": 200,
	 * "timestamp": 1576826568061
	 * }
	 * @apiUse QueryError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 * "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
	 * "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
	 * }
	 */
	@OpenAPI
	@ApiOperation("环节变动业务动态")
	@GetMapping("/hjbdYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> hjbdYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/hjbd/hjbdYwdt";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数

			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}

			String starttime = (String) maps.getResult().get("starttime");
			String endtime = (String) maps.getResult().get("endtime");

			if (!StringUtils.isNullOrEmpty(starttime)) {
				param.and("starttime", TermType.eq, starttime);
			}
			if (!StringUtils.isNullOrEmpty(endtime)) {
				param.and("endtime", TermType.eq, endtime);
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);

			ResponseMessage<Map<String, Object>> result = kssAnalyseApis.hjbdYwdt(jsbh, starttime, endtime);

			System.err.println("result" + JSON.toJSONString(result));

			List lists = new ArrayList<>();
			lists.add(result);

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", lists);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().size());
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





	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> hjbd_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_HjbdModel data) {
		return kssService.hjbd_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_HjbdModel> hjbd_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.hjbd_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> hjbd_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.hjbd_deleteByKey(id);
	}
}
