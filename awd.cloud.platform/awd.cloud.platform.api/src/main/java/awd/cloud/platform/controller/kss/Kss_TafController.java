/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.TafModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_TafModel;
import awd.cloud.platform.model.kss.TafModelIDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/taf")
@Api(tags = "kss-taf",description = "Taf") 
public class Kss_TafController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_TafModel>> taf_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_TafModel>> result= kssService.taf_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}


	/**
	 * @api {post} /v4/kss/taf/addTary 同案犯保存
	 * @apiVersion 0.4.0
	 * @apiName addTary
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 同案犯保存.
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
	 *   "result": "null",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *     "creator":"创建人(必填; 最大长度:50))",
	 *     "tafh":"同案犯号(必填; 最大长度:21)",
	 *     "rybh":"人员编号(必填; 最大长度:21; 多个用','隔开)",
	 *     "czr":"操作人(必填; 最大长度:30)"
	 *   }]
	 * }
	 */
	@ApiOperation("同案犯保存")
	@PostMapping("/addTary")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> taf_save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/taf/addTary";
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

			TafModel tafModel = JSONArray.parseArray(map.get("entity").toString(), TafModel.class).get(0);

			String[] rybhs = tafModel.getRybh().split(",");
			System.err.println("rybhs=="+JSONUtil.toJson(rybhs));
			if(StringUtils.isNullOrEmpty(rybhs)){
				return ResponseMessage.error("rybh格式有误");
			}
			for (String rybh : rybhs) {
				if(rybh.length() > 21){
					ResponseMessage<String> errorResult = ResponseMessage.error("rybh格式有误");
					errorResult.setResult(rybh);
					return errorResult;
				}
			}

			tafModel.setState("R2");
			tafModel.setJsbh(jsbh);
			tafModel.setCreatetime(new Date());
			tafModel.setCzsj(new Date());
			System.err.println("tafModel=="+JSONUtil.toJson(tafModel));
			ResponseMessage<String> result = kssServerApis.saveTafs(tafModel);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败!");
		}
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> taf_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_TafModel data) {
		return kssService.taf_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_TafModel> taf_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.taf_getByKey(id);
	}


	/**
	 * @api {post} /v4/kss/taf/removeTaf 同案犯撤销
	 * @apiVersion 0.4.0
	 * @apiName removeTaf
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 同案犯撤销.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												更新参数集(必填)
	 *
	 * @apiSuccess {String}message         				                     返回信息
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "撤销成功!",
	 *   "result": "撤销成功",
	 *   "status": 200,
	 *   "timestamp": 1578448941666
	 * }
	 * @apiUse UpdateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *     "rybh":"人员编号(必填; 最大字段长度：21)",
	 *   }]
	 * }
	 */
	@ApiOperation("同案犯删除")
	@PostMapping("/removeTaf")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> removeTaf(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/taf/removeTaf";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			Map<String, Object> entityMap = (Map) JSONObject.parseObject(map.get("entity").toString(), List.class).get(0);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}

			String rybh = entityMap.get("rybh").toString();
			System.err.println("removeTaf=="+rybh);

			ResponseMessage<String> result = kssServerApis.removeTaf(rybh);
			if (result.getStatus() == 200) {
				result.setMessage("撤销成功!");
			} else {
				result.setMessage("服务异常,撤销失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("撤销失败!");
		}
	}

	/**
	 * @api {get} /v4/kss/taf/tafByRybh 根据人员编号查询同案犯
	 * @apiVersion 0.4.0
	 * @apiName tafByRybh
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据人员编号查询同案犯.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}xbString          				                性别(已转换)
	 * @apiSuccess {String}snbh         				                    所内编号
	 * @apiSuccess {String}xm          				                        人员姓名
	 * @apiSuccess {String}ayString          				                案由(已转换)
	 * @apiSuccess {String}ay         				                        案由
	 * @apiSuccess {String}xb          				                        性别
	 * @apiSuccess {String}jsbh          				                    监所编号
	 * @apiSuccess {String}jsh          				                    监室号
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "xbString": "男性",
	 *         "snbh": "20190319",
	 *         "xm": "梅西",
	 *         "ayString": "背叛国家案",
	 *         "ay": "010110",
	 *         "xb": "1",
	 *         "jsh": "0101",
	 *         "jsbh": "110000114"
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
	 *          "rybh":"人员编号(必填; 最大长度:21)",
	 *        }
	 *
	 */
	@ApiOperation("根据人员编号查询同案犯")
	@PostMapping("/tafByRybh")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> tafByRybh(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/taf/tafByRybh";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			if(StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))){
				return ResponseMessage.error("rybh不可为空");
			}

			TafModel tafModel = new TafModel();
			tafModel.setRybh(maps.getResult().get("rybh").toString());
			tafModel.setState("R2");
			System.err.println("tafModel" + JSON.toJSONString(tafModel));

			ResponseMessage<PagerResult<?>> result = kssServerApis.findTaf(tafModel);
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
	 * @api {get} /v4/kss/taf/tafList 同案犯信息查询
	 * @apiVersion 0.4.0
	 * @apiName tafList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 同案犯信息查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}snbh         				                    所内编号
	 * @apiSuccess {String}xm          				                        人员姓名
	 * @apiSuccess {String}jsbh          				                    监所编号
	 * @apiSuccess {String}jsh          				                    监室号
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "snbh": "20190319",
	 *         "xm": "梅西",
	 *         "jsh": "0101",
	 *         "jsbh": "110000114"
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
	 *          "rybh":"人员编号(必填; 最大长度:21)",
	 *        }
	 *
	 */
	@ApiOperation("同案犯信息查询")
	@GetMapping("/tafList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> tafList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/taf/tafList";
		String state="R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			//查询参数
			QueryParam param = new QueryParam();

			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.like, maps.getResult().get("rybh"));
			}
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.like, jsbh);
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);

		  ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.findTaf(param);
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
	 * @api {post} /v4/kss/taf/tafAdd 同案犯保存
	 * @apiVersion 0.4.0
	 * @apiName tafAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 同案犯保存.
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
	 *   "result": "null",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *          "creator":"创建人(必填; 最大长度:50))",
	 *          "zrybh":"主犯人员编号(必填; 最大长度:21)",
	 *          "frybh":"从犯人员编号(必填; 最大长度:21; 多个用','隔开)",
	 *          "ay":"案由"
	 *   }]
	 * }
	 */
	//{"creator":".{1,50}","zrybh":"\\d{1,21}"}
	@ApiOperation("同案犯保存")
	@PostMapping("/tafAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tafAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/taf/tafAdd";
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

			TafModelIDO tafModel = JSONArray.parseArray(map.get("entity").toString(), TafModelIDO.class).get(0);

			String[] rybhs = tafModel.getFrybh().split(",");
			if(StringUtils.isNullOrEmpty(rybhs)){
				return ResponseMessage.error("frybh格式有误");
			}
			for (String rybh : rybhs) {
				if(rybh.length() > 21){
					ResponseMessage<String> errorResult = ResponseMessage.error("frybh格式有误");
					errorResult.setResult(rybh);
					return errorResult;
				}
			}
			tafModel.setCreatetime(new Date());
		//	tafModel.setCreator(users.getUsername());
			tafModel.setJsbh(jsbh);
			ResponseMessage<String> result = kssServerApis.saveTaf(tafModel);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败!");
		}
	}
}
