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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.MjshgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.service.PublicService;
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
@RequestMapping("/v4/kss/mjshgx")
@Api(tags = "kss-mjshgx", description = "Mjshgx")
public class Kss_MjshgxController extends PublicService {

	@Autowired
	private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	
	/**
	 * @return
	 * @api {get} /v4/kss/mjshgx/mjshgxQuery 民警社会关系查询
	 * @apiVersion 0.4.0
	 * @apiName mjshgxQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警社会关系查询
	 * @apiParam {String} appcode 					应用代码(必填)
	 * @apiParam {String} jsbh 						监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 						查询参数集
	 *
	 * @apiSuccess {String}jh 						警号
	 * @apiSuccess {String}zjh 						证件号
	 * @apiSuccess {String}createtime 				创建时间
	 * @apiSuccess {String}creator 					创建人
	 * @apiSuccess {String}id 						id
	 * @apiSuccess {String}jsbh 					监所编号
	 * @apiSuccess {String}updatetime 				更新时间
	 * @apiSuccess {String}updator                  更新人
	 * @apiSuccess {String}jsbhString				监所名称(已转换)
	 * @apiSuccess {String}xxmc 					学校名称
	 * @apiSuccess {String}zmr 						证明人
	 * @apiSuccess {String}zzny  					证明日期				
	 * 
	 *
	 *
	 *
	 * @apiSuccess {String}message 					返回信息
	 * @apiSuccess {String}result 					返回结果
	 * @apiSuccess {String}total 					返回总数
	 * @apiSuccess {String}data 					返回数据
	 * @apiSuccess {String}status 					返回状态
	 * @apiSuccess {String}timestamp 				时间戳
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK 
	 * 			{ 
	 * 				"message": "查询成功",
	 *              "result": { 
	 *              	"total": 1, 
	 *              	"data": [ 
	 *              		"createtime": "2020-01-15 19:02:48",
	 *						"creator": "管理员",
	 *						"gx": "120",
	 *						"gxString": "儿子",
	 *						"gxrgzdw": "12",
	 *						"gxrlxdh": "18188006644",
	 *						"gxrxm": "12",
	 *						"gxrzzmm": "13",
	 *						"gxrzzmmString": "群众",
	 *						"id": "11000011420200115000100",
	 *						"jh": "10086",
	 *						"jsbh": "110000114",
	 *						"jsbhString": "北京市第一看守所",
	 *						"updatetime": "",
	 *						"updator": ""
	 *                    		], 
	 *                    	"page": "1" 
	 *                    },
	 *                    	"status": 200,
	 *                    	"timestamp": 1576826568061 }
	 * @apiUse QueryError
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)", 
	 * 				json:{
	 *             			"rybh":"人员编号(最大字段长度：21)", 
	 *             		}
	 */
	@OpenAPI
	@ApiOperation("民警社会关系查询")
	@GetMapping("/mjshgxQuery")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	public ResponseMessage<Map<String, Object>> mjshgx_Query(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/mjshgx/mjshgxQuery";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 查询参数
			// String jh = request.getParameter("jh");
			String state = "R2";
			QueryParam qParam = new QueryParam();
			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jh"))) {
				qParam.and("jh", TermType.eq, maps.getResult().get("jh"));
			}
			ResponseMessage<PagerResult<MjshgxModel>> result = kssServerApis.mjshgxQueryForPage(qParam);
			// 封装需要的数据
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
			return ResponseMessage.error("服务异常,查询失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/mjshgx/mjshgxSave 民警社会关系保存
	 * @apiVersion 0.4.0
	 * @apiName mjshgxSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警社会关系保存
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss)",
	 *             				"creator":"创建人(必填;最大字段长度：30)",
	 *             				"jh":"警号(必填;最大字段长度：20)", 
	 *             				"xm":"姓名(必填;最大字段长度：30)",
	 *             				"gx":"关系(必填;字典(GX),最大字段长度：3)", 
	 *             				"gxrxm":"关系人员姓名(必填;最大字段长度：30)",
	 *             				"gxrgzdw":"关系人工作单位(必填;最大字段长度：50)",
	 *             				"gxrzzmm":"关系人政治面貌(必填;最大字段长度：2)",
	 *             				"gxrlxdh":"关系人政治面貌(最大字段长度：11)"
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警社会关系保存")
	@PostMapping("mjshgxSave")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mjshgx_Save(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/mjshgx/mjshgxSave";
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
			List<MjshgxModel> mjshgxModel = JSONArray.parseArray(map.get("entity").toString(), MjshgxModel.class);
			mjshgxModel.get(0).setState("R2");
			mjshgxModel.get(0).setJsbh(jsbh);
			mjshgxModel.get(0).setCreator(maps.getResult().get("creator").toString());
			mjshgxModel.get(0).setCreatetime(new Date());
			ResponseMessage<String> result = kssServerApis.mjshgxSave(mjshgxModel.get(0));
			if (result.getStatus() == 200) {
				result.setMessage("保存成功");
			} else {
				result.setMessage("服务异常，保存失败！");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败");
		}
	}

	
	/**
	 * @api {post} /v4/kss/mjshgx/mjshgxUpdate 民警社会关系修改
	 * @apiVersion 0.4.0
	 * @apiName mjshgxUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 民警社会关系修改.
	 *
	 * @apiParam {String} appcode 应用代码(必填)
	 * @apiParam {String} jsbh 监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result 返回结果
	 * @apiSuccess {String}page 返回页数
	 * @apiSuccess {String}status 返回状态
	 * @apiSuccess {String}timestamp 时间戳
	 *
	 *
	 * 
	 * @apiSuccessExample {json} 返回 (成功): HTTP/1.1 200 OK { "message": "保存成功!",
	 *                    "result": "11000011420191214000011", "status": 200,
	 *                    "timestamp": 1576308305534 }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数: 
	 * 				appcode:"应用代码（必填）", 
	 * 				jsbh:"监所编号(必填; 最大字段长度：9)",
	 *             		json:{
	 *             			"entity":[{ 
	 *             				"id":"id(必填;最大字段长度：23)",
	 *             				"createtime":"创建时间(必填;格式：yyyy-MM-ddhh:mm:ss)",
	 *             				"creator":"创建人(必填;最大字段长度：30)",
	 *             				"jh":"警号(必填;最大字段长度：20)", 
	 *             				"xm":"姓名(必填;最大字段长度：30)",
	 *             				"gx":"关系(必填;字典(GX),最大字段长度：3)", 
	 *             				"gxrxm":"关系人员姓名(必填;最大字段长度：30)",
	 *             				"gxrgzdw":"关系人工作单位(必填;最大字段长度：50)",
	 *             				"gxrzzmm":"关系人政治面貌(必填;最大字段长度：2)",
	 *             				"gxrlxdh":"关系人政治面貌(最大字段长度：11)"
	 *             			} 
	 *             }] 
	 *       }
	 *
	 */
	@ApiOperation("民警社会关系修改")
	@PostMapping("mjshgxUpdate")
	@HystrixCommand
	@ApiResponses({ @ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在") })
	@OpenAPI
	public ResponseMessage<String> mjshgx_Update(HttpServletRequest request,
			@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/mjshgx/mjshgxUpdate";
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
			List<MjshgxModel> mjshgxModel = JSONArray.parseArray(map.get("entity").toString(), MjshgxModel.class);
			mjshgxModel.get(0).setUpdatetime(new Date());
			mjshgxModel.get(0).setUpdator(maps.getResult().get("updator").toString());
			mjshgxModel.get(0).setJsbh(jsbh);

			ResponseMessage<String> result = kssServerApis.mjshgxUpdate(mjshgxModel.get(0).getId(), mjshgxModel.get(0));
			if (result.getStatus() == 200) {
				result.setMessage("修改成功");
			} else {
				result.setMessage("数据异常，修改失败！");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("修改失败");
		}
	}
}
