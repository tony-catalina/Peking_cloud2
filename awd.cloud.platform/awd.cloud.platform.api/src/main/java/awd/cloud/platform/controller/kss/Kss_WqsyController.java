/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.LdspModel;
import awd.bj.kss.model.WqsyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wqsy")
@Api(tags = "kss-wqsy", description = "Wqsy")
public class Kss_WqsyController extends PublicService {

	@Autowired
	private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

//	@OpenAPI
//	@ApiOperation("分页查询")
//	@GetMapping
//	@HystrixCommand
//	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
//			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
//	public ResponseMessage<PagerResult<Kss_WqsyModel>> wqsy_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
//		QueryParam queryParam = new QueryParam();
//        ResponseMessage<PagerResult<Kss_WqsyModel>> result= kssService.wqsy_query(queryParam);
//        if(result.getStatus()==200) {
//            result.setMessage("查询成功");
//            if(result.getResult()==null) {
//                result.setMessage("未查询数据");
//            }
//        }
//        return result;
//	}
//	
//	
//
//	@ApiOperation("新增")
//	@PostMapping
//	@HystrixCommand
//	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
//			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
//	@OpenAPI
//	public ResponseMessage<String> wqsy_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_WqsyModel data) {
//		return kssService.wqsy_save(data);
//	}
//	
//	
//
//
//	@ApiOperation("根据id更新")
//	@PutMapping(path = {"/{id:.+}"})
//	@HystrixCommand
//	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
//			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
//	@OpenAPI
//	public ResponseMessage<String> wqsy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_WqsyModel data) {
//		return kssService.wqsy_updateByKey(id, data);
//	}	
//
//
//	@OpenAPI
//	public ResponseMessage<Kss_WqsyModel> wqsy_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
//		return kssService.wqsy_getByKey(id);
//	}
//	
//	
//
//	@OpenAPI
//	public ResponseMessage<Integer> wqsy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
//		return kssService.wqsy_deleteByKey(id);
//	}

	
	/**
	 * @api {post} /v4/kss/wqsy/wqsySaveFlow 武器使用保存
	 * @apiVersion 0.4.0
	 * @apiName wqsySaveFlow
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 武器使用保存.
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
	 *                      "syly":"使用理由(必填;最大字段长度：200)",
	 *                      "syts":"使用天数(必填;最大字段长度：4)",
	 *                      "wqzl":"武器种类(必填;最大字段长度：30)",
	 *                      "jh":"警号(必填; 最大字段长度：20)",
	 *                      "creator":"创建人 (必填; 最大字段长度：30)",
	 *                      "createtime":"创建时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "sykssj":"使用开始时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "syjssj":"使用结束时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "bz":"备注(必填;最大字段长度：500)",
	 *                      "cpr":"呈批人(必填;最大字段长度：30)",
	 *                      "cpsj":"呈批时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "taskid":"任务id(必填;最大字段长度：21))",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("武器使用保存")
	@GetMapping("wqsySaveFlow")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> wqsy_SaveFlow(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/wqsy/wqsySaveFlow";
		try {
			// 校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<WqsyModel> wqsyModel = JSONArray.parseArray(map.get("entity").toString(), WqsyModel.class);
			// 查询该人员是否有正在使用且未归还的武器
			QueryParam param = new QueryParam();
			param.and("state", TermType.eq, "R2");
			param.and("jsbh", jsbh);
			param.and("jh", wqsyModel.get(0).getJh());
			param.and("pastable", TermType.eq, "1");
			param.and("sfgh", "0");
			ResponseMessage<PagerResult<WqsyModel>> re = kssServerApis.getWqsy(param);
			if (re.getStatus() == 200 && re.getResult().getTotal() != 0) {
				return ResponseMessage.error("错误！当前人员还有未归还的武器");
			}
			wqsyModel.get(0).setCreator(maps.getResult().get("creator").toString());
			wqsyModel.get(0).setCreatetime(new Date());
			wqsyModel.get(0).setJsbh(jsbh);
			wqsyModel.get(0).setPastable("1");
			wqsyModel.get(0).setState("R2");
			wqsyModel.get(0).setPsbz("0");
			wqsyModel.get(0).setSfgh("0");
			String lcid = CacheUtils.get().getFlowKey("KSS_WQSY");
			ResponseMessage<String> result = kssServerApis.wqsyAddFlow(lcid, wqsyModel.get(0));
			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败");
		}
	}

	
	/**
	 * @api {post} /v4/kss/wqsy/wqsyldspSave 武器使用领导审批
	 * @apiVersion 0.4.0
	 * @apiName wqsyldspSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 武器使用领导审批.
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
	 *                      "ywlcid":"流程Id(最大字段长度：50)",
	 *                      "rybh":"人员编号(必填; 最大字段长度：21)",
	 *                      "xm":"姓名 (必填; 最大字段长度：30)",
	 *                      "sqyy":"申请原因(必填)",
	 *                      "sqsj":"申请时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "psbz":"批示标志(必填;最大字段长度：1)",
	 *                      "ldyj":"领导意见(必填;最大字段长度：200)",
	 *                      "ldxm":"领导姓名(必填;最大字段长度：30)",
	 *                      "ldpssj":"领导批示时间(必填;格式：yyyy-MM-dd hh:mm:ss))",
	 *                      "taskid":"任务id(必填;最大字段长度：21))",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("武器使用领导审批")
	@PostMapping("wqsyldspSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> wqsyldsp_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/wqsy/wqsyldspSave";
		try {
			// 校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<LdspModel> ldspModel = JSONArray.parseArray(map.get("entity").toString(), LdspModel.class);
			if (StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))) {
				return ResponseMessage.error("taskid必传");
			}
			ldspModel.get(0).setJsbh(jsbh);
			ldspModel.get(0).setState("R2");
			ldspModel.get(0).setBlr(maps.getResult().get("creator").toString());
			ldspModel.get(0).setSqr(maps.getResult().get("xm").toString());
			ldspModel.get(0).setCreator(maps.getResult().get("creator").toString());
			ldspModel.get(0).setBlsj(new Date());

			ResponseMessage<String> result = kssServerApis.addLdsp(maps.getResult().get("taskid").toString(),
					ldspModel.get(0));
			if (result.getStatus() == 200) {
				ResponseMessage.ok("保存成功!");
			} else {
				ResponseMessage.error("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			return ResponseMessage.error("保存失败");
		}
	}

	
	/**
	 * @api {post} /v4/kss/wqsy/wqsythSave 武器使用退还
	 * @apiVersion 0.4.0
	 * @apiName wqsythSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 武器使用退还.
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
	 *                      "id":"Id(最大字段长度：23)",
	 *                      "updatetime":"更新时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "updator":"更新人 (必填; 最大字段长度：30)",
	 *                      "bz:":"备注",
	 *                      "ghsj":"归还时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "createtime":"创建时间(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "creator":"创建人(必填;最大字段长度：30)",
	 *                      "taskid":"任务id(必填;最大字段长度：21))",
	 *                      }
	 *                     }]
	 *             }
	 *
	 */
	@ApiOperation("武器使用退还")
	@PostMapping("wqsythSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> wqsyth_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/wqsy/wqsythSave";
		try {
			// 校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<WqsyModel> wqsyModel = JSONArray.parseArray(map.get("entity").toString(), WqsyModel.class);
			// String taskid = request.getParameter("taskid");
			if (StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))) {
				return ResponseMessage.error("taskid必传");
			}
			wqsyModel.get(0).setSfgh("1");
			wqsyModel.get(0).setJsbh(jsbh);

			ResponseMessage<String> result = kssServerApis.addWqgh(maps.getResult().get("taskid").toString(),
					wqsyModel.get(0));
			if (result.getStatus() == 200) {
				ResponseMessage.ok("保存成功!");
			} else {
				ResponseMessage.error("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败!");
		}
	}

	/**
	 * @return
	 * @api {get} /v4/kss/wqsy/wqsyAndMjxxQuery 武器使用业务台账
	 * @apiVersion 0.4.0
	 * @apiName wqsyAndMjxxQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 武器使用业务台账
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}jh												警号
	 * @apiSuccess {String}psbz												批示标志
	 * @apiSuccess {String}syjssj											使用结束时间
	 * @apiSuccess {String}syjssjString										使用结束时间(已转换)
	 * @apiSuccess {String}sykssj										             使用开始时间
	 * @apiSuccess {String}sykssjString										使用开始时间(已转换)
	 * @apiSuccess {String}syly											            使用理由
	 * @apiSuccess {String}syts										                        使用天数
	 * @apiSuccess {String}wqzl										                        武器种类
	 * @apiSuccess {String}xm									                                    姓名
	 * 
	 *
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
	 * "data":  [
	 *          "jh": "123232111111",
	 *			"psbz": "1",
	 *			"syjssj": 1578561137000,
	 *			"syjssjString": "2020-01-09 17:12:17",
	 *			"sykssj": 1577697137000,
	 *			"sykssjString": "2019-12-30 17:12:17",
	 *			"syly": "aa",
	 *			"syts": 10,
	 *			"wqzl": "bbb",
	 *			"xm": "的撒"
	 * ],
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
	 *    "rybh":"人员编号(最大字段长度：21)",
	 * }
	 */
	@ApiOperation("武器使用业务台账")
	@GetMapping("wqsyAndMjxxQuery")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<Map<String, Object>> wqsyAndMjxx_Query(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/wqsy/wqsyAndMjxxQuery";
		String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 查询参数
			String syly = "";
			String syts = "";
			String wqzl = "";
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("syly"))) {
				syly = maps.getResult().get("syly").toString();
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("syts"))) {
				syts = maps.getResult().get("syts").toString();
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("wqzl"))) {
				wqzl = maps.getResult().get("wqzl").toString();
			}
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wqsyAndMjxx(syly, syts, wqzl);
			// 封装需要的数据
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
}
