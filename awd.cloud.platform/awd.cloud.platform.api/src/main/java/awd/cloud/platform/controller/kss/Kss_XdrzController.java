/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XdrzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xdrz")
@Api(tags = "kss-xdrz",description = "Xdrz")
public class Kss_XdrzController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {get} /v4/kss/xjjl/xdrzList 消毒日志查询
	 * @apiVersion 0.4.0
	 * @apiName xdrzList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 消毒日志查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}xdrq          				                        消毒日期
	 * @apiSuccess {String}xdry       				                    		消毒人员
	 * @apiSuccess {String}xddd          				                		消毒地点
	 * @apiSuccess {String}blr          				                		办理人
	 * @apiSuccess {String}blsj          				                        办理时间
	 * @apiSuccess {String}bz          				                		备注
	 * @apiSuccess {String}xdff          				                		消毒方法
	 * @apiSuccess {String}xdffString				                        	消毒方法（已转换）
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间

	 * @apiSuccess {String}message                                              返回信息
	 * @apiSuccess {String}result                                               返回结果
	 * @apiSuccess {String}total                                                返回总数
	 * @apiSuccess {String}data                                                 返回数据
	 * @apiSuccess {String}status                                               返回状态
	 * @apiSuccess {String}timestamp                                            时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 39,
	 *     "data": [
	 *       {
	 *        	id: "11000011420191231001458"
	 * 			jsbh: "110000114"
	 * 			xdrq: "2019-12-31"
	 * 			xdry: "ff"
	 * 			xddd: "ffff"
	 * 			blr: "管理员"
	 * 			blsj: "2019-12-31 10:27:58"
	 * 			bz: ""
	 * 			createtor: "管理员"
	 * 			createtime: "2019-12-31 10:28:05"
	 * 			updatetor: "管理员"
	 * 			updatetime: "2019-12-31 10:29:53"
	 * 			state: "R2"
	 * 			xdff: ""
	 * 			xdffString: ""
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578447454182
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 *    json:{
	 *          "sj_start": "开始时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "sj_end": "结束时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "page": "当前页数",
	 *          "rows": "一页数据量",
	 *          "sort": "id",
	 *          "order": "desc"
	 *         }
	 *
	 */
	//id,jsbh,jsbhString,xdrq,xdry,xddd,blr,blsj,bz,xdff,xdffString
	@OpenAPI
	@ApiOperation("消毒日志查询")
	@GetMapping("/xdrzList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> xdrzList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/xdrz/xdrzList";
		String state = request.getParameter("state");
		try{
			ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
			if (maps.getStatus() != 200){
				return ResponseMessage.error(maps.getMessage());
			}
			QueryParam queryParam = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)){
				queryParam.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xjrq_start"))) {
				queryParam.and("xdrq", TermType.gte, maps.getResult().get("xjrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xjrq_end"))) {
				queryParam.and("xdrq", TermType.lte, maps.getResult().get("xjrq_end"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
			System.err.println("param--" + JSON.toJSONString(queryParam));

			ResponseMessage<PagerResult<XdrzModel>> result = kssServerApis.xdrzList(queryParam);
			System.err.println("result" + JSON.toJSONString(result));
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
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/xdrz/xdrzAdd 消毒日志保存
	 * @apiVersion 0.4.0
	 * @apiName xdrzAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 消毒日志保存
	 *
	 * @apiParam {String} appcode 						    应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			    成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			    时间戳
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
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:{
	 *   "entity":[
	 *     {
	 *     	"xdrq": "消毒日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "xdry": "消毒人员（必填; 最大长度:100）",
	 *      "xddd": "消毒地点（必填）",
	 *      "blr": "办理人（必填; 最大长度:20）",
	 *      "blsj": "办理时间（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "bz": "备注",
	 *      "creator": "创建人(必填; 最大长度:20)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"xdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xdry":".{1,100}","blr":".{1,20}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
	@ApiOperation("消毒日志保存")
	@PostMapping("/xdrzlAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xdrzlAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		String interfaceId = "/v4/kss/xdrz/xdrzlAdd";
		try{
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
			List<XdrzModel> list = JSONArray.parseArray(map.get("entity").toString(),XdrzModel.class);
			XdrzModel xdrzModel = list.get(0);
			xdrzModel.setState("R2");
			xdrzModel.setJsbh(jsbh);
			xdrzModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.xdrzSave(xdrzModel);
			if (result.getStatus() == 200){
				result.setMessage("保存成功！");
			} else {
				result.setMessage("服务异常，保存失败！");
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/xdrz/xdrzUpdate 消毒日志修改
	 * @apiVersion 0.4.0
	 * @apiName xdrzUpdate
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 消毒日志修改
	 *
	 * @apiParam {String} appcode 						    应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			    成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			    时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "修改成功!",
	 *   "result": "修改成功",
	 *   "status": 200,
	 *   "timestamp": 1578886392184
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:{
	 *   "entity":[
	 *     {
	 *      "id":"id(必填; 最大长度：23)",
	 *		"xdrq": "消毒日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "xdry": "消毒人员（必填; 最大长度:100）",
	 *      "xddd": "消毒地点（必填）",
	 *      "blr": "办理人（必填; 最大长度:20）",
	 *      "blsj": "办理时间（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "bz": "备注",
	 *      "updatetor": "修改人(必填; 最大长度:20)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"id":".{1,23}","xdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xdry":".{1,100}","blr":".{1,20}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
	@ApiOperation("消毒日志修改")
	@PostMapping("xdrzUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xdrzUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/xdrz/xdrzUpdate";
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
				return ResponseMessage.error(msg.getMessage());
			}
			List<XdrzModel> list = JSONArray.parseArray(map.get("entity").toString(),XdrzModel.class);
			XdrzModel xdrzModel = list.get(0);
			xdrzModel.setState("R2");
			xdrzModel.setJsbh(jsbh);
			xdrzModel.setUpdatetime(new Date());

			ResponseMessage<String> result = kssServerApis.xdrzUpdate(xdrzModel.getId(),xdrzModel);
			if (result.getStatus() == 200){
				result.setMessage("修改成功！");
			} else {
				result.setMessage("服务异常，修改失败！");
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("修改失败！");
		}
	}
}
