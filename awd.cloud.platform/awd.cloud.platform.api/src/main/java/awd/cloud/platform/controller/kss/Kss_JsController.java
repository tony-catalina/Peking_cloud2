/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JqModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JsModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/js")
@Api(tags = "kss-js",description = "Js") 
public class Kss_JsController extends PublicService {
	
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
	public ResponseMessage<PagerResult<Kss_JsModel>> js_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JsModel>> result= kssService.js_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}




	/**
	 * @api {get} /v4/kss/js/jqAndList 获取监区对于监室信息
	 * @apiVersion 0.4.0
	 * @apiName jqAndList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 获取监区对于监室信息.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}innum
	 * @apiSuccess {String}type                                             类别(已转换)
	 * @apiSuccess {String}jsh                                       		监室号
	 * @apiSuccess {String}jsmc                                        		监室名称
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
	 *        {
	 *         "js0": [
	 *           {
	 *             "innum": 1,
	 *             "type": "1",
	 *             "jsh": "9001",
	 *             "jsmc": "单独关押监室一"
	 *           },
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
	 *
	 *        }
	 *
	 */
	@OpenAPI
	@ApiOperation("获取监区对于监室信息")
	@GetMapping("/jqAndList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jqAndList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		//接口id
		String interfaceId = "/v4/kss/js/jqAndList";
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}

			//查询参数
			String xb = (String) maps.getResult().get("xb");
			QueryParam param = new QueryParam();
			int ss = 0;
			param.and("jsbh", TermType.eq,jsbh);
			param.and("state", TermType.eq, "R2");
			ResponseMessage<PagerResult<JqModel>> jqlist = kssServerApis.jqQuery(param);

			QueryParam params = new QueryParam();
			params.and("jsbh", TermType.eq, jsbh);
			params.and("state", TermType.eq, "R2");
			params.setPaging(false);
			if(!StringUtils.isNullOrEmpty(xb)) {
				params.and("type", TermType.eq, xb);
			}
			ResponseMessage<PagerResult<Map<String, Object>>> jslist = kssServerApis.jsQueryForPage(params);

			List<Map<String,Object>> jsandjq = new ArrayList();
			if(jqlist.getResult().getTotal()>0) {
				for(int i = 0;i<jqlist.getResult().getTotal();i++) {
					Map<String, Object> mapsj = new HashMap<String, Object>();
					List<Map<String,Object>> mapss = new ArrayList();
					for(int j = 0;j<jslist.getResult().getTotal();j++) {
						if(jqlist.getResult().getData().get(i).getJqh().equals(jslist.getResult().getData().get(j).get("jqh"))) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("innum",jslist.getResult().getData().get(j).get("innum"));
							map.put("jsh", jslist.getResult().getData().get(j).get("jsh"));
							map.put("jsmc", jslist.getResult().getData().get(j).get("jsmc"));
							map.put("type", jslist.getResult().getData().get(j).get("type"));
							mapss.add(map);
						}
					}

					if(mapss!=null&&mapss.size()!=0) {
						mapsj.put("jq"+ss,jqlist.getResult().getData().get(i).getJqh());
						mapsj.put("js"+ss,mapss);
						ss++;
					}
					if(mapsj!=null&&mapsj.size()!=0) {
						jsandjq.add(mapsj);
					}
				}
			}
			System.err.println("result"+JSON.toJSONString(jsandjq));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", jsandjq);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",  jsandjq.size());
			maplist.put("page",  request.getParameter("page"));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			if(list.getStatus()==200) {
				list.setMessage("查询成功");
				if(list.getResult()==null) {
					list.setMessage("未查询数据");
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}




	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> js_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JsModel data) {
		return kssService.js_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> js_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JsModel data) {
		return kssService.js_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JsModel> js_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.js_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> js_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.js_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/kss/js/jsCombotree 监室树查询
	 * @apiVersion 0.4.0
	 * @apiName jsCombotree
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监室树查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}jslbString         				                监室类别(已转换)
	 * @apiSuccess {String}typeString                                       男女类别(已转换)
	 * @apiSuccess {String}type                                       		男女类别
	 * @apiSuccess {String}jsmc                                        		监室名称
	 * @apiSuccess {String}jsh                                  			监室号
	 * @apiSuccess {String}jslb                                             监室类别
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
	 *         "jslbString": "普通",
	 *         "typeString": "男监",
	 *         "type": "1",
	 *         "jsmc": "0101",
	 *         "jsh": "0101",
	 *         "jslb": "1"
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
	 *          “jqh”:"监区号(必填; 最大长度:2)"
	 *        }
	 */
	@OpenAPI
	@ApiOperation("监室树查询")
	@GetMapping("/jsCombotree")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> js_list(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		//接口id
		String interfaceId = "/v4/kss/js/jsCombotree";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}

			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jqh"))) {
				param.and("jqh", TermType.eq, maps.getResult().get("jqh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.jsQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",  result.getResult().getTotal());
			maplist.put("page",  request.getParameter("page"));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			if(list.getStatus()==200) {
				list.setMessage("查询成功");
				if(list.getResult()==null) {
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
