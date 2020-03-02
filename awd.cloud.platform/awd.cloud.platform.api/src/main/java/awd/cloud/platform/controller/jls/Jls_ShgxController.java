/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
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
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.jls.model.JbxxModel;
import awd.bj.jls.model.ShgxModel;
import awd.cloud.platform.api.JlsServerApis;
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
@RequestMapping("/v4/jls/shgx")
@Api(tags = "kss-shgx",description = "Shgx") 
public class Jls_ShgxController extends PublicService {
	
	@Autowired
	private JlsServerApis jlsServerApis;



	/**
     * @api {post} /v4/jls/shgx/shgxSave 关系登记新增
	 * @apiVersion 0.4.0
     * @apiName shgxSave
     * @apiGroup g_jls
     * @apiPermission user
     *
     * @apiDescription 关系登记新增
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "entity":[
     *           {
     *              "xm":"姓名(必填; 最大长度:30)",
     *              "gx":"关系(必填; 最大长度:3)",
     *              "gzdw":"工作单位(必填; 最大长度:255)",
     *              "dz":"地址(必填; 最大长度:255)",
     *              "dh":"电话(必填; 最大长度:11)",
     *              "zjh":"证件号(必填; 最大长度:18)",
     *              "rybh":"人员编号(必填; 最大长度:21)",
     *              "zjlx":"证件类型(最大长度:2)",
     *              "tbrq":"填表日期(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *              "tbr":"填表人( 最大长度:30)"
     *           }
     *        ]
     *      }
     * }
     *
     */
	@ApiOperation("关系登记新增")
	@PostMapping("/shgxSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> shgx_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		//接口id
		String interfaceId = "/v4/jls/shgx/shgxSave";
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
				return ResponseMessage.error(msg.getMessage());
			}
			List<ShgxModel> shgxModels = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class);
			shgxModels.get(0).setJsbh(jsbh);
			shgxModels.get(0).setState("R2");
			ResponseMessage<String> jsswsq = jlsServerApis.shgxSave(shgxModels.get(0));
			System.err.println("--"+JSON.toJSONString(jsswsq));
			if(jsswsq.getStatus() == 200){
				jsswsq.setMessage("保存成功!");
			}else{
				jsswsq.setMessage("服务异常,保存失败!");
			}
			return jsswsq;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	@OpenAPI
	@ApiOperation("关系登记查询（jbxx）")
	@GetMapping("gxdjList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> gxdjList(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jls/shgx/gxdjList";
		//在押人员
		String state = "R8";
		//通过校验获取查询参数
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
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.in, maps.getResult().get("rybh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<JbxxModel>> list = jlsServerApis.jbxxQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(list));

			//封装需要的数据
			Map<String, Object> result = Maps.newHashMap();
			result.put("total", list.getResult().getTotal());
			result.put("rows", list.getResult().getData());

			return ResponseMessage.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
	
	/**
     * @api {get} /v4/jls/shgx/shgxQueryForPage 业务动态查询记录
     * @apiVersion 0.4.0
     * @apiName shgxQueryForPage
     * @apiGroup g_jls
     * @apiPermission any
     *
     * @apiDescription 业务动态查询记录.
     * 
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} id         			        id
     * @apiSuccess {String} jsbh         			            监所编号
     * @apiSuccess {String} jsbhString         			监所编号(转换值)
     * @apiSuccess {String} nl         					年龄
     * @apiSuccess {String} operator         			操作人
     * @apiSuccess {String} rybh         			            人员编号
     * @apiSuccess {String} scbz         			  	上传标志
     * @apiSuccess {String} scbzString         			上传标志(转换值)
     * @apiSuccess {String} tbr         			             填表人
     * @apiSuccess {String} tbrq 						填表日期
     * @apiSuccess {String} updatetime  				更新日期
     * @apiSuccess {String} updator  					更新人
     * @apiSuccess {String} xb  						 性别
     * @apiSuccess {String} xbString   					性别(转换值)
     * @apiSuccess {String} xm   						姓名
     * @apiSuccess {String} yb  						邮编
     * @apiSuccess {String} zjh  						证件号
     * @apiSuccess {String} zjlx  						证件类型
     * @apiSuccess {String} zjlxString   				证件类型(转换值)
     * @apiSuccess {String} gzdw   						工作单位
     * @apiSuccess {String} gxString  					关系(转换值)
     * @apiSuccess {String} gx  						关系
     * @apiSuccess {String} dz							地址
     * @apiSuccess {String} dwdz 						单位地址
     * @apiSuccess {String} dh  						电话
     * @apiSuccess {String} creator  					创建人
     * @apiSuccess {String} createtime  				创建时间
     * @apiSuccess {String} bz  						备注
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *          "bz": ""
     *		    "createtime": "2020-02-21 15:31:19",
     *			"creator": "管理员",
     *			"dh": "15535465348",
     *			"dwdz": "",
     *			"dz": "1",
     *			"gx": "110",
     *			"gxString": "配偶",
     *			"gzdw": "1",
     *			"id": "11000012120200221000176",
     *			"jsbh": "110000121",
     *			"jsbhString": "",
     *			"nl": "",
     *			"operator": "",
     *			"rybh": "110000121201911280001",
     *			"scbz": "",
     *			"scbzString": "",
     *			"state": "R2",
     *			"stateString": "有效",
     *			"tbr": "",
     *			"tbrq": null,
     *			"updatetime": null,
     *			"updator": "",
     *			"xb": "1",
     *			"xbString": "男性",
     *			"xm": "1",
     *			"yb": "",
     *			"zjh": "142402199712250314",
     *			"zjlx": "11",
     *			"zjlxString": "身份证",
     *       }
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576496854065
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "jsh":"监室号(最大长度:4)",
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
	@OpenAPI
	@ApiOperation("业务动态查询记录")
	@GetMapping("shgxQueryForPage")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> shgxQueryForPage(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jls/shgx/shgxQueryForPage";
		//在押人员
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("createtimeStart"))) {
				param.and("createtimeStart", TermType.in, maps.getResult().get("createtimeStart"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("createtimeEnd"))) {
				param.and("createtimeEnd", TermType.eq, maps.getResult().get("createtimeEnd"));
			}
			param.and("zjlx","11");
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<ShgxModel>> list = jlsServerApis.shgxQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(list));

			//封装需要的数据
			Map<String, Object> result = Maps.newHashMap();
			result.put("total", list.getResult().getTotal());
			result.put("rows", list.getResult().getData());

			return ResponseMessage.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	
	/**
     * @api {get} /v4/jls/shgx/shgxJbxxQueryForPage 业务台账查询记录
     * @apiVersion 0.4.0
     * @apiName shgxJbxxQueryForPage
     * @apiGroup g_jls
     * @apiPermission any
     *
     * @apiDescription 业务台账查询记录.
     * 
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} id         			       		id(转换值)
     * @apiSuccess {String} jsbh         			       	监所编号
     * @apiSuccess {String} rybh         			       	人员编号
     * @apiSuccess {String} ryjsh         			       	人员监室号
     * @apiSuccess {String} ryxb         			       	人员性别
     * @apiSuccess {String} ryxm         			       	人员姓名
     * @apiSuccess {String} snbh         			     	所内编号
     * @apiSuccess {String} xb         						性别
     * @apiSuccess {String} xbString         			            性别(转换值)
     * @apiSuccess {String} xm								姓名
     * @apiSuccess {String} zjh								证件号
     * @apiSuccess {String} zjlx 							证件类型
     * @apiSuccess {String} zjlxString  					证件类型(转换值)
     * @apiSuccess {String} gzdw  							工作单位
     * @apiSuccess {String} gxString 						关系(转换值)
     * @apiSuccess {String} gx								关系
     * @apiSuccess {String} dz   							地址
     * @apiSuccess {String} dh  							电话
     * @apiSuccess {String} creator  						创建人
     * @apiSuccess {String} createtime   					创建日期
     * @apiSuccess {String} bz  							备注
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *          "bz": "",
     *			"createtime": 1582270279000,
     *			"creator": "管理员",
     *			"dh": "15535465348",
     *			"dz": "1",
     *			"gx": "110",
     *			"gxString": "配偶",
     *			"gzdw": "1",
     *			"id": "11000012120200221000176",
     *			"jsbh": "110000121",
     *			"rybh": "110000121201911280001",
     *			"ryjsh": "0101",
     *			"ryxb": "1",
     *			"ryxm": "张三",
     *			"snbh": "20190080",
     *			"state": "R2",
     *			"xb": "1",
     *			"xbString": "男性",
     *			"xm": "1",
     *			"zjh": "142402199712250314",
     *			"zjlx": "11",
     *			"zjlxString": "身份证"
     *       }
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576496854065
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "jsh":"监室号(最大长度:4)",
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
	@OpenAPI
	@ApiOperation("业务台账查询记录")
	@GetMapping("shgxJbxxQueryForPage")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> shgxJbxxQueryForPage(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jls/shgx/shgxJbxxQueryForPage";
		//在押人员
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xm"))) {
				param.and("jbxx_xm", TermType.in, maps.getResult().get("jbxx_xm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_jsh"))) {
				param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jbxx_jsh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_bm"))) {
				param.and("jbxx_bm", TermType.eq, maps.getResult().get("jbxx_bm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_ay"))) {
				param.and("jbxx_ay", TermType.eq, maps.getResult().get("jbxx_ay"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
				param.and("xb", TermType.eq, maps.getResult().get("xb"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqStart"))) {
				param.and("jbxx_rsrqStart", TermType.eq, maps.getResult().get("jbxx_rsrqStart"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_rsrqEnd"))) {
				param.and("jbxx_rsrqEnd", TermType.eq, maps.getResult().get("jbxx_rsrqEnd"));
			}
			param.and("zjlx","11");
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>> list = jlsServerApis.shgxJbxxQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(list));

			//封装需要的数据
			Map<String, Object> result = Maps.newHashMap();
			result.put("total", list.getResult().getTotal());
			result.put("rows", list.getResult().getData());

			return ResponseMessage.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
	
}
