/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XjglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_XjglModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
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
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xjgl")
@Api(tags = "kss-xjgl",description = "Xjgl") 
public class Kss_XjglController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;
	
	@Autowired
    private KssService kssService;


	/**
	 *
	 * @api {get} /v4/kss/xjgl/jsxmList 信件管理查询社会关系信息
	 * @apiVersion 0.4.0
	 * @apiName jsxmList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 信件管理查询社会关系信息
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id                                                ID
	 * @apiSuccess {String}jsbh                                              监所编号
	 * @apiSuccess {String}rybh                                              人员编号
	 * @apiSuccess {String}state                                             状态
	 * @apiSuccess {String}jsxm                                              接收姓名
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
	 * "data":
	 * {
	 *         "STATE": "R2",
	 *         "ID": "11000011420190829000132",
	 *         "RYBH": "310000111201906200007",
	 *         "JSXM": "1",
	 *         "JSBH": "110000114"
	 * }
	 *
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
	 *        "rybh":"人员编号(必填；最大字段长度：21)",
	 *      }
	 */

	@OpenAPI
	@ApiOperation("信件管理查询社会关系信息")
	@GetMapping("/jsxmList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jsxmList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/xjgl/jsxmList";
		String state = "R8";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数

			  String rybh= (String) maps.getResult().get("rybh");

			if(StringUtils.isNullOrEmpty(rybh)) {
				return ResponseMessage.error("请输入人员编号");
			}

			if(rybh.length()>21) {
				return ResponseMessage.error("人员编号的最大长度：21");
			}


			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.shgxJsxmList(rybh);
			System.err.println("result" + JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			System.out.println("123123123123123123123"+ result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().getData().size());
			maplist.put("page", request.getParameter("page"));

			System.err.println("result" + JSON.toJSONString(maplist));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			System.out.println("12312312312"+list.getResult().get("entity"));
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
	 *
	 * @api {get} /v4/kss/xjgl/xjglList 信件管理业务查询
	 * @apiVersion 0.4.0
	 * @apiName xjglList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 信件管理业务查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id                                                ID
	 * @apiSuccess {String}jsbh                                              监所编号
	 * @apiSuccess {String}rybh                                              人员编号
	 * @apiSuccess {String}state                                             状态
	 * @apiSuccess {String}jsxm                                              接收姓名
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
	 * "data":
	 * {
	 *         "snbh": "20190225",
	 *         "xm": "12321321",
	 *         "rybh": "110000114201911220001",
	 *         "id": "11000011420191122000117",
	 *         "jsbh": "110000114"
	 * }
	 *
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
	 *        "rybh":"人员编号(必填；最大字段长度：21)",
	 *      }
	 */

	@OpenAPI
	@ApiOperation("信件管理业务查询")
	@GetMapping("/xjglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> xjglList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/xjgl/xjglList";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数

			QueryParam queryParam = new QueryParam();

			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				queryParam.and("jbxx_xm", TermType.like,"%"+maps.getResult().get("xm")+"%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				queryParam.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
				queryParam.and("jbxx_bm", TermType.like, "%" + maps.getResult().get("bm") + "%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
				queryParam.and("jbxx_xb", TermType.eq, maps.getResult().get("xb"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
				queryParam.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
				queryParam.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq_end")+" 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("bahj"))) {
				queryParam.and("jbxx_bahj", TermType.eq, maps.getResult().get("bahj"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("badw"))) {
				queryParam.and("jbxx_badw", TermType.eq, maps.getResult().get("badw"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_start"))) {
				queryParam.and("jbxx_gyqx", TermType.gte, maps.getResult().get("gyqx_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_end"))) {
				queryParam.and("jbxx_gyqx", TermType.lte, maps.getResult().get("gyqx_end")+" 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xjlx"))) {
				queryParam.and("xjlx", TermType.eq, maps.getResult().get("xjlx"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("ywjd"))) {
				queryParam.and("ywjd", TermType.eq, maps.getResult().get("ywjd"));
			}

			queryParam.and("state", TermType.eq, "R2");
			queryParam.and("jbxx_state", TermType.eq, "R8");
			queryParam.and("jsbh", TermType.eq, jsbh);
			queryParam.and("jbxx_jsbh", TermType.eq, jsbh);

			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.xjglJbxxlist(queryParam);

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





	/**
	 * @api {post} /v4/kss/xjgl/sxdjAdd 收信登记保存
	 * @apiVersion 0.4.0
	 * @apiName sxdjAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 收信登记保存
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
	 *              "creator":"创建人(必填; 最大长度:30)",
	 * 	            "rybh":"人员编号(必填; 最大长度:21)"
	 *        }]
	 *   }
	 *
	 */
	//{"creator":".{1,30}","rybh":"\\d{1,21}"}
	@ApiOperation("收信登记保存")
	@PostMapping("/sxdjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> sxdjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/xjgl/sxdjAdd";
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



			XjglModel xjglModel = JSONArray.parseArray(map.get("entity").toString(), XjglModel.class).get(0);

		//	String tbr = request.getParameter("tbr");
		//	String tbrq = request.getParameter("tbrq");
			xjglModel.setJsbh(jsbh);
			xjglModel.setState("R2");
			xjglModel.setXjlx("1");
			xjglModel.setYwjd("1");
			xjglModel.setPastable("1");
			xjglModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.xjglSave(xjglModel);

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





	/**
	 * @api {post} /v4/kss/xjgl/ldspAdd 领导审批
	 * @apiVersion 0.4.0
	 * @apiName ldspAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 领导审批
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
	 *              "id":"ID(必填; 最大长度:23)",
	 *              "updator":"创建人(必填; 最大长度:30)"
	 *        }]
	 *   }
	 *
	 */
	//{"updator":".{1,30}","id":"\\d{1,23}"}
	@ApiOperation("领导审批")
	@PostMapping("/ldspAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ldspAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/xjgl/ldspAdd";
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


			XjglModel xjglModel = JSONArray.parseArray(map.get("entity").toString(), XjglModel.class).get(0);

			// String tbr = request.getParameter("tbr");
			// String tbrq = request.getParameter("tbrq");
			xjglModel.setYwjd("2");
			xjglModel.setJsbh(jsbh);
			xjglModel.setUpdatetime(new Date());


			ResponseMessage<String> result = kssServerApis.xjglUpdate(xjglModel.getId(), xjglModel);

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


	/**
	 * @api {post} /v4/kss/xjgl/xjclAdd 信件处理
	 * @apiVersion 0.4.0
	 * @apiName xjclAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 信件处理
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
	 *              "id":"ID(必填; 最大长度:23)",
	 *              "updator":"创建人(必填; 最大长度:30)"
	 *        }]
	 *   }
	 *
	 */
	//{"updator":".{1,30}","id":"\\d{1,23}"}
	@ApiOperation("信件处理")
	@PostMapping("/xjclAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xjclAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/xjgl/xjclAdd";
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


			XjglModel xjglModel = JSONArray.parseArray(map.get("entity").toString(), XjglModel.class).get(0);

			//String tbr = request.getParameter("tbr");
			//String tbrq = request.getParameter("tbrq");
			xjglModel.setYwjd("3");
			xjglModel.setJsbh(jsbh);
			xjglModel.setUpdatetime(new Date());


			ResponseMessage<String> result = kssServerApis.xjglUpdate(xjglModel.getId(), xjglModel);

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


	/**
	 * @api {post} /v4/kss/xjgl/fxdjAdd 发信登记
	 * @apiVersion 0.4.0
	 * @apiName fxdjAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 发信登记
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
	 *              "creator":"创建人(必填; 最大长度:30)",
	 * 	            "rybh":"人员编号(必填; 最大长度:21)"
	 *        }]
	 *   }
	 *
	 */
	//{"creator":".{1,30}","rybh":"\\d{1,21}"}
	@ApiOperation("发信登记")
	@PostMapping("/fxdjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fxdjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/xjgl/fxdjAdd";
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


			XjglModel xjglModel = JSONArray.parseArray(map.get("entity").toString(), XjglModel.class).get(0);

			// String tbr = request.getParameter("tbr");
			// String tbrq = request.getParameter("tbrq");
			xjglModel.setJsbh(jsbh);
			xjglModel.setState("R2");
			xjglModel.setXjlx("2");
			xjglModel.setYwjd("1");
			xjglModel.setPastable("1");
			xjglModel.setCreatetime(new Date());


			ResponseMessage<String> result = kssServerApis.xjglSave(xjglModel);

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
	public ResponseMessage<String> xjgl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XjglModel data) {
		return kssService.xjgl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_XjglModel> xjgl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xjgl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> xjgl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xjgl_deleteByKey(id);
	}
}
