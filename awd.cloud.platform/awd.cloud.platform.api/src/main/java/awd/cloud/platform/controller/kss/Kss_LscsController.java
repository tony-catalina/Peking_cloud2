/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.LscsModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;

import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/lscs")
@Api(tags = "kss-lscs",description = "Lscs")
public class Kss_LscsController extends PublicService {

	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	@Autowired
	private KssAnalyseApis kssAnalyseApis;

	@Autowired
	private ProcessService processService;

	/**
	 * @api {post} /v4/kss/lscs/addLscsLdsp 临时出所领导审批保存
	 * @apiVersion 0.4.0
	 * @apiName addLscsLdsp
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所领导审批保存.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳

	 *
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *   				   "data_Jbxx":"基本信息(必填;)"{
	 *                     "ywlcid":"业务流程ID(必填;最大字段长度：50)",
	 *                     "bm":"别名(必填;最大字段长度：50)",
	 *                     "rybh":"人员编号(必填;最大字段长度：21)",
	 *                      "snbh":"人员所内编号(必填; 最大字段长度：8)",
	 *                      "jsh":"监室号 (必填; 最大字段长度：4)",
	 *                      "xm":"姓名(必填;最大字段长度：50)",
	 *                      "xb":"性别(必填;最大字段长度：1)",
	 *                      "taskid":"任务id(必填;最大字段长度：21)",
	 *                      "sqyy":"申请原因(必填;最大字段长度：500)",
	 *                      "ldspid":"领导审批id(必填;最大字段长度：23)",
	 *                      "sqsj":"申请日期(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      },
	 *                      "data_ywxx":"业务信息(必填;)"{
	 *                      "psbz":"审批意见(必填;最大字段长度：1)",
	 *                      "ldyj":"领导批示意见(必填;最大字段长度：500)",
	 *                      "blr":"办理人(必填;最大字段长度：50)",
	 *                      "blsj":"办理时间(必填;格式：yyyy-MM-dd hh:mm:ss)"}
	 *                     }]
	 *             }
	 *
	 */
	@OpenAPI
	@ApiOperation("临时出所领导审批保存")
	@PostMapping("addLscsLdsp")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})

	public ResponseMessage<String> addLscsLdsp(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"ywlcid":"\\d{1,50}","bm":"\\S{1,50}","rybh":"\\d{1,21}","snbh":"\\d{1,8}","jsh":"\\d{1,4}","xm":"\\S{1,50}","xb":"\\d{1}","sqyy":"\\S{1,50}","sqsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","psbz":"\\d{1}","ldyj":"\\S{1,500}","blr":"\\S{1,50}"}
		//{"entity":[{"data_Jbxx":{"taskid":"5205071","ywlcid":"3858171","bm":"aa","rybh":"310000111201906200004","snbh":"20190035","jsh":"1201","xm":"汪枫桦","xb":"1","sqyy":"11111","sqsj":"2019-10-29 17:09:07"},"data_ywxx":{"blsj":"2019-10-29 17:09:07","psbz":"1","ldyj":"11111111","blr":"汪枫桦"}}]}
		String interfaceId = "/v4/kss/lscs/addLscsLdsp";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String blsj=(String)((Map<String, Object>)rslist.get(0).get("data_ywxx")).get("blsj");
			Kss_LscsPlModel lscsPlModel=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(),Kss_LscsPlModel.class);
			List<Kss_LscsPlModel> list =new ArrayList<Kss_LscsPlModel>();
			Kss_LdspModelDO ldsp = JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(),Kss_LdspModelDO.class);
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_Jbxx");
			entity.put("psbz",ldsp.getPsbz());
			entity.put("ldyj",ldsp.getLdyj());
			entity.put("blr",ldsp.getBlr());
			entity.put("blsj",blsj);
			Map<String, Object> mapls=new HashMap<String, Object>();
			mapls.put("entity",entity);
			String jsons="{'entity':["+JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			String taskid=(String) entity.get("taskid");
			String creator=(String) entity.get("creator");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			ldsp.setCreatetime(new Date());
			ldsp.setCreator(creator);
			ldsp.setState("R2");
			ldsp.setJsbh(jsbh);
			list.add(lscsPlModel);
			Map<String, Object> lsmap = new HashMap<String, Object>();
			mapls.put("jbxx", list);
			mapls.put("ldsp", ldsp);
			ResponseMessage<String> result = kssServerApis.addLscsLdsp(lsmap);

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
	 * @api {post} /v4/kss/lscs/lscsList 临时出所查询
	 * @apiVersion 0.4.0
	 * @apiName lscsList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}rybh          				                    人员编号
	 * @apiSuccess {String}cssj          				                    出所时间
	 * @apiSuccess {String}csyy          				                    出所原因
	 * @apiSuccess {String}csyyString          				                出所原因(已转换)
	 * @apiSuccess {String}blsj          				                    办理时间
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
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "rybh": "110000114201910160001",
	 *         "cssj": "2019-11-16 15:31:31",
	 *         "csyy": "1",
	 *         "csyyString": "开庭审理",
	 *         "blsj": "2019-11-16 15:32:00"
	 *       }
	 *     ],
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
	 *          "rybh":"人员编号(最大字段长度：21)",
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("临时出所查询")
	@PostMapping("/lscsList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> lscs_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		String interfaceId = "/v4/kss/lscs/lscsList";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();

			if (!StringUtils.isNullOrEmpty(jsbh)) {
				qParam.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			System.err.println("jbxx查询" + JSONUtil.toJson(qParam));
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.lscsQueryForPage(qParam);
			System.err.println("--result--" + JSON.toJSONString(result));

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


	/**
	 * @api {get} /v4/kss/lscs/lscsYwdt 临时出所业务动态查询
	 * @apiVersion 0.4.0
	 * @apiName lscsYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所业务动态查询
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}zrcs          				                     昨日出所
	 * @apiSuccess {String}bycswg          				                     本月出所未归
	 * @apiSuccess {String}bzcs        				                         本周出所
	 * @apiSuccess {String}bzcswg         				                     本周出所未归
	 * @apiSuccess {String}jrcs          				                     今日出所
	 * @apiSuccess {String}bycs          				                     本月出所
	 * @apiSuccess {String}zrcswg          				                     昨日出所未归
	 * @apiSuccess {String}jrcswg          				                     今日出所未归
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
	 *    "data": [
	 *       {
	 *         "result": {
	 *           "zrcs": [
	 *             {
	 *               "zrcs": 26
	 *             }
	 *           ],
	 *           "bycswg": [
	 *             {
	 *               "bycswg": 28
	 *             }
	 *           ],
	 *           "bzcs": [
	 *             {
	 *               "bzcs": 26
	 *             }
	 *           ],
	 *           "bzcswg": [
	 *             {
	 *               "bzcswg": 25
	 *             }
	 *           ],
	 *           "jrcs": [
	 *             {
	 *               "jrcs": 0
	 *             }
	 *           ],
	 *           "bycs": [
	 *             {
	 *               "bycs": 29
	 *             }
	 *           ],
	 *           "zrcswg": [
	 *             {
	 *               "zrcswg": 25
	 *             }
	 *           ],
	 *           "jrcswg": [
	 *             {
	 *               "jrcswg": 0
	 *             }
	 *           ]
	 *         }
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
	 *        }
	 *
	 */
	@OpenAPI
	@ApiOperation("临时出所业务动态查询")
	@GetMapping("/lscsYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> lscsYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/lscs/lscsYwdt";
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
				param.and("jsbh", TermType.eq,jsbh);
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);

			ResponseMessage<Map<String, Object>> result = kssAnalyseApis.lscsYwdt(jsbh);

			System.err.println("result" + JSON.toJSONString(result));

			List lists=new ArrayList<>();
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

	/**
	 * @api {post} /v4/kss/lscs/jbxxForLscs 临时出所基本信息查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForLscs
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所基本信息查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}blsjString          				                办理时间
	 * @apiSuccess {String}xbString          				                性别(已转换)
	 * @apiSuccess {String}cssjString          				                出所时间
	 * @apiSuccess {String}snbh          				                	所内编号
	 * @apiSuccess {String}xm          				                    	姓名
	 * @apiSuccess {String}xb          				                    	性别
	 * @apiSuccess {String}hssjString          				                回所时间
	 * @apiSuccess {String}jsh          				                    监室号
	 * @apiSuccess {String}csyy          				                    出所原因(字典)
	 * @apiSuccess {String}csyyString          				                出所原因(已转换)
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
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "blsjString": "2019-11-16 15:32:01",
	 *         "xbString": "男性",
	 *         "cssjString": "2019-11-16 15:31:31",
	 *         "snbh": "20190205",
	 *         "xm": "state",
	 *         "xb": "1",
	 *         "hssjString": "2019-11-16 15:38:59",
	 *         "jsh": "0102",
	 *         "csyy": "1",
	 *         "csyyString": "开庭审理"
	 *       }
	 *     ],
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
	 *          "rybh":"人员编号(最大字段长度：21)",
	 *          "blsjstart":"办理开始时间(格式:yyyy-MM-dd hh:mm:ss)",
	 *          "blsjend":"办理结束时间(格式:yyyy-MM-dd hh:mm:ss)",
	 *          "jsh":"监室号(最大字段长度：4)"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("临时出所基本信息查询")
	@PostMapping("/jbxxForLscs")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jbxxForLscs(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/lscs/jbxxForLscs";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsbh"))) {
				qParam.and("jsbh", TermType.eq, maps.getResult().get("jsbh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blsjstart"))) {
				qParam.and("blsj", TermType.gte, maps.getResult().get("blsjstart"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blsjend"))) {
				qParam.and("blsj", TermType.lte, maps.getResult().get("blsjend"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				qParam.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			System.err.println("qParam" + JSONUtil.toJson(qParam));
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jbxxForLscs(qParam);
			System.err.println("--result--" + JSON.toJSONString(result));

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
			return ResponseMessage.error("查询失败");
		}
	}


	/**
	 * @api {post} /v4/kss/lscs/addLscsCs 临时出所出所保存
	 * @apiVersion 0.4.0
	 * @apiName addLscsCs
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所出所保存.
	 *
	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
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
	 *                	"data_Jbxx":"基本信息(必填;)"{
	 * 	                     "ywlcid":"业务流程ID(必填;最大字段长度：50)",
	 * 	                     "bm":"别名(必填;最大字段长度：50)",
	 * 	                     "rybh":"人员编号(必填;最大字段长度：21)",
	 * 	                      "snbh":"人员所内编号(必填; 最大字段长度：8)",
	 * 	                      "jsh":"监室号 (必填; 最大字段长度：4)",
	 * 	                      "xm":"姓名(必填;最大字段长度：50)",
	 * 	                      "xb":"性别(必填;最大字段长度：1)",
	 * 	                      "taskid":"任务id(必填;最大字段长度：21)",
	 * 	                      "jsbh":"监所编号(必填;最大字段长度：9)",
	 * 	                      "ldspid":"领导审批id(必填;最大字段长度：23)",
	 * 	                      },
	 * 	              "data_ywxx":"业务信息(必填;)"{
	 * 	                      "blsj":"办理时间(必填;格式：yyyy-MM-dd hh:mm:ss))"}
	 *                }]
	 *     }
	 */

	@ApiOperation("临时出所出所保存")
	@PostMapping("/addLscsCs")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> addLscsCs(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"ywlcid":"\\d{1,50}","bm":"\\S{1,50}","rybh":"\\d{1,21}","snbh":"\\d{1,8}","jsh":"\\d{1,4}","xm":"\\S{1,50}","xb":"\\d{1}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"data_Jbxx":{"taskid":"5205071","ywlcid":"3858171","bm":"aa","rybh":"310000111201906200004","snbh":"20190035","jsh":"1201","xm":"汪枫桦","xb":"1"},"data_ywxx":{"blsj":"2019-10-29 17:09:07"}}]}

		//接口id
		String interfaceId = "/v4/kss/lscs/addLscsCs";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String blsj=(String)((Map<String, Object>)rslist.get(0).get("data_ywxx")).get("blsj");
			Kss_LscsPlModel lscsPlModel=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(),Kss_LscsPlModel.class);
			List<Kss_LscsPlModel> list =new ArrayList<Kss_LscsPlModel>();
			Kss_CrjjcModelDO crjjc = JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(),Kss_CrjjcModelDO.class);
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_Jbxx");
			entity.put("blsj",blsj);
			Map<String, Object> mapls=new HashMap<String, Object>();
			mapls.put("entity",entity);
			String jsons="{'entity':["+JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			String taskid=(String) entity.get("taskid");
			String creator=(String) entity.get("creator");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			crjjc.setCreatetime(new Date());
			crjjc.setCreator(creator);
			crjjc.setState("R2");
			crjjc.setJsbh(jsbh);
			crjjc.setBllx("1");
			list.add(lscsPlModel);
			Map<String, Object> lsmap = new HashMap<String, Object>();
			mapls.put("jbxx", list);
			mapls.put("crjjc", crjjc);
			ResponseMessage<String> result = kssServerApis.addLscsCs(lsmap);

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
	 * @api {post} /v4/kss/lscs/addLscsHsqr 临时出所回所保存
	 * @apiVersion 0.4.0
	 * @apiName addLscsHsqr
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所回所保存.
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
	 *                	"data_Jbxx":"基本信息(必填;)"{
	 * 	                      "ywlcid":"业务流程ID(必填;最大字段长度：50)",
	 * 	                      "bm":"别名(必填;最大字段长度：50)",
	 * 	                      "rybh":"人员编号(必填;最大字段长度：21)",
	 * 	                       "snbh":"人员所内编号(必填; 最大字段长度：8)",
	 * 	                       "jsh":"监室号 (必填; 最大字段长度：4)",
	 * 	                       "xm":"姓名(必填;最大字段长度：50)",
	 * 	                       "xb":"性别(必填;最大字段长度：1)",
	 * 	                       "taskid":"任务id(必填;最大字段长度：21)",
	 * 	                       "jsbh":"监所编号(必填;最大字段长度：9)",
	 * 	                       "ldspid":"领导审批id(必填;最大字段长度：23)",
	 * 	                       },
	 * 	               "data_ywxx":"业务信息(必填;)"{
	 * 	                       "blsj":"办理时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 * 	 						"dlmj":"带领民警(必填;最大字段长度：100)",
	 * 	 						"wjpqk":"违禁品情况(必填;)"
	 * 	 						}
	 *                }]
	 *     }
	 */

	@ApiOperation("临时出所回所保存")
	@PostMapping("/addLscsHsqr")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> addLscsHsqr(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"ywlcid":"\\d{1,50}","bm":"\\S{1,50}","rybh":"\\d{1,21}","snbh":"\\d{1,8}","jsh":"\\d{1,4}","xm":"\\S{1,50}","xb":"\\d{1}","dlmj":"\\S{1,100}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"data_Jbxx":{"taskid":"5205071","ywlcid":"3858171","bm":"aa","rybh":"310000111201906200004","snbh":"20190035","jsh":"1201","xm":"汪枫桦","xb":"1"},"data_ywxx":{"dlmj":"aa","wjpqk":"dasdasd","blsj":"2019-10-29 17:09:07"}}]}

		//接口id
		String interfaceId = "/v4/kss/lscs/addLscsHsqr";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String blsj=(String)((Map<String, Object>)rslist.get(0).get("data_ywxx")).get("blsj");
			String dlmj=(String)((Map<String, Object>)rslist.get(0).get("data_ywxx")).get("dlmj");
			Kss_LscsPlModel lscsPlModel=JSONObject.parseObject(rslist.get(0).get("data_Jbxx").toString(),Kss_LscsPlModel.class);
			List<Kss_LscsPlModel> list =new ArrayList<Kss_LscsPlModel>();
			Kss_CrjjcModelDO crjjc = JSONObject.parseObject(rslist.get(0).get("data_ywxx").toString(),Kss_CrjjcModelDO.class);
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data_Jbxx");
			entity.put("blsj",blsj);
			entity.put("dlmj",dlmj);
			Map<String, Object> mapls=new HashMap<String, Object>();
			mapls.put("entity",entity);
			String jsons="{'entity':["+JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String taskid=(String) entity.get("taskid");
			String creator=(String) entity.get("creator");
			String wjpqk=(String) ((Map<String, Object>)rslist.get(0).get("data_ywxx")).get("wjpqk");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(wjpqk)){
				return ResponseMessage.error("wjpqk不能为空！");
			}
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			crjjc.setCreatetime(new Date());
			crjjc.setCreator(creator);
			crjjc.setState("R2");
			crjjc.setJsbh(jsbh);
			crjjc.setBllx("2");
			list.add(lscsPlModel);
			Map<String, Object> lsmap = new HashMap<String, Object>();
			mapls.put("jbxx", list);
			mapls.put("crjjc", crjjc);
			ResponseMessage<String> result = kssServerApis.addLscsHsqr(lsmap);

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
	 * @api {post} /v4/kss/lscs/lscsAddFlow 临时出所流程保存
	 * @apiVersion 0.4.0
	 * @apiName lscsAddFlow
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 临时出所流程保存.
	 *
	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *   appcode:"应用代码(必填)",
	 *   jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *     "entity":[{
	 *       "rybh":"人员编号(必填; 最大长度:21)",
	 *       "csyy":"出所原因(必填; 字典:LSCS 最大长度:2)",
	 *       "cssj":"出所时间(必填; 格式:yyyy-MM-dd hh:mm:ss))",
	 *       "creator":"创建人(必填; 最大长度:50)",
	 *       "lrmj":"录入民警(必填; 最大长度:30)",
	 *       "csqx":"出所去向(必填; 最大长度:100)",
	 *       "csdriver":"出所司机(最大长度:50)"
	 *     }]
	 *   }
	 */
	@ApiOperation("临时出所流程保存")
	@PostMapping("/lscsAddFlow")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lscsAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/lscs/lscsAddFlow";
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
			if (msg.getStatus() != 200) {
				System.out.println("----------------" + interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String key = "kss_lscs";

			List<LscsModel> list = JSONUtil.toList(map.get("entity").toString(), LscsModel.class);
			for (LscsModel lscsModel : list) {
				if ("500".equals(processService.FlowMutex(jsbh, lscsModel.getRybh(), key.toUpperCase(), "LSCS").getStatus())) {
					return processService.FlowMutex(jsbh, lscsModel.getRybh(), key.toUpperCase(), "LSCS");
				}
				String ywlcid = lscsModel.getYwlcid();
				if(StringUtils.isNullOrEmpty(ywlcid)){
					return ResponseMessage.error("ywlcid不可为空");
				}
				lscsModel.setState("R2");
				lscsModel.setJsbh(jsbh);
				lscsModel.setBlsj(Calendar.getInstance().getTime());
				lscsModel.setCreatetime(Calendar.getInstance().getTime());
				lscsModel.setPsbz("0");
				lscsModel.setPastable("01");
				lscsModel.setWczt("1");
			}
			System.err.println("临时出所登记list" + JSONUtil.toJson(list));
			ModelList<LscsModel> modellist = new ModelList<>();
			modellist.setList(list);
			ResponseMessage<String> resposeMessage = kssServerApis.lscsAddflow(CacheUtils.get().getFlowKey("_kss_lscs".toUpperCase()), modellist);
			if (resposeMessage != null && 200 == resposeMessage.getStatus()) {
				return ResponseMessage.ok(resposeMessage.getResult() != null ? "保存成功" : resposeMessage.getResult());
			} else {
				return ResponseMessage.error(resposeMessage.getMessage() == null ? "保存失败" : resposeMessage.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error(Result.ERR_SAVE, "保存失败");
		}
	}


	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lscs_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LscsModel data) {
		return kssService.lscs_save(data);
	}


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lscs_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LscsModel data) {
		return kssService.lscs_updateByKey(id, data);
	}


	@OpenAPI
	public ResponseMessage<Kss_LscsModel> lscs_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lscs_getByKey(id);
	}



	@OpenAPI
	public ResponseMessage<Integer> lscs_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lscs_deleteByKey(id);
	}
}
