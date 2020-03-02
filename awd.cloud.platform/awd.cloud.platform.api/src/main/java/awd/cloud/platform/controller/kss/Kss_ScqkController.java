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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.ScqkModel;
import awd.cloud.platform.api.KssServerApis;
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
@RequestMapping("/v4/kss/scqk")
@Api(tags = "kss-scqk",description = "Scqk") 
public class Kss_ScqkController extends PublicService{
	@Autowired
	private KssServerApis kssServerApis;
	
	/**
	 * @return
	 * @api {get} /v4/kss/scqk/scqkQuery 视察情况查询
	 * @apiVersion 0.4.0
	 * @apiName scqkQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 视察情况查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}rq										                    视察时间
	 * @apiSuccess {String}tt										                    视察团体
	 * @apiSuccess {String}ldxm											 领导姓名
	 * @apiSuccess {String}ldzw											 领导职位
	 * @apiSuccess {String}cy										                     主要成员
	 * @apiSuccess {String}jdr										                     接待情况
	 * @apiSuccess {String}scnr										           视察内容
	 * @apiSuccess {String}yjjy										                     意见建议
	 * @apiSuccess {String}zgqk										                     整改情况
	 * @apiSuccess {String}jlr									                      记录人
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
	 *         id: "11000011420200214000044"
	 *		   jsbh: "110000114"
	 *		   jsbhString: "北京市第一看守所"
	 *		   rq: "2020-02-14 14:55:33"
	 *		   tt: "视察团体"
	 *		   ldxm: "领导姓名"
	 *		   ldzw: "领导职位"
	 *		   cy: "主要成员"
	 *		   jdr: "接待情况"
	 *		   scnr: "视察内容"
	 *		   yjjy: "意见建议"
	 *		   zgqk: "整改情况"
	 *		   jlr: "记录人"
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
	 *    "sj_start":"检查开始时间(非必填,格式(2019-12-12))",
	 *    "sj_end":"检查结束时间(非必填,格式(2019-12-12))",
	 * }
	 */
    
    @OpenAPI
	@ApiOperation("视察情况查询")
	@GetMapping("/scqkQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> scqkQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/scqk/scqkQuery";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();
	        DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_start"))) {
	        	qParam.and("rq", TermType.gte, maps.getResult().get("sj_start")+" 00:00:00");
	        }
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_end"))) {
	        	qParam.and("rq", TermType.lte, maps.getResult().get("sj_end")+" 23:59:59");
	        }
	        qParam.and("jsbh", jsbh);
	        ResponseMessage<PagerResult<ScqkModel>> result=kssServerApis.scqkQueryForPage(qParam);
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
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
    
    /**
	 * @api {post} /v4/kss/scqk/scqkSave 
	 * @apiVersion 0.4.0
	 * @apiName scqkSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 视察情况保存
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
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "creator":"操作人(必填; 最大字段长度：30)",
	 *      "rq":"检查时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jlr":"记录人(必填; 最大字段长度：30)",
	 *      "ldxm":"领导姓名(必填; 最大字段长度：30)",
	 *      "ldzw":"领导职位(必填;最大长度20)",
	 *      "tt":"视察团体(必填;最大字段长度：30)",
	 *      "jdr":"接待情况(必填;最大字段长度：200)",
	 *      "cy":"主要成员(必填;最大字段长度：40)",
	 *      "scnr":"视察内容(必填;)",
	 *      "yjjy":"意见建议(非必填;)",
	 *      "zgqk":"整改情况(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("视察情况保存")
	@PostMapping("scqkSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> scqkSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/scqk/scqkSave";

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
				return ResponseMessage.error(msg.getMessage());
			}
			List<ScqkModel> scqkModel = JSONArray.parseArray(map.get("entity").toString(), ScqkModel.class);
			scqkModel.get(0).setState("R2");
			scqkModel.get(0).setJsbh(jsbh);
			scqkModel.get(0).setJlrq(new Date());
			scqkModel.get(0).setCreatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.scqkSave(scqkModel.get(0));
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
	
    /**
	 * @api {post} /v4/kss/scqk/scqkUpdate 
	 * @apiVersion 0.4.0
	 * @apiName scqkUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 视察情况修改
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
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *   	"id":"id(必填; 最大字段长度：23)",
	 *      "updator":"操作人(必填; 最大字段长度：30)",
	 *      "rq":"检查时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jlr":"记录人(必填; 最大字段长度：30)",
	 *      "ldxm":"领导姓名(必填; 最大字段长度：30)",
	 *      "ldzw":"领导职位(必填;最大长度20)",
	 *      "tt":"视察团体(必填;最大字段长度：30)",
	 *      "jdr":"接待情况(必填;最大字段长度：200)",
	 *      "cy":"主要成员(必填;最大字段长度：40)",
	 *      "scnr":"视察内容(必填;)",
	 *      "yjjy":"意见建议(非必填;)",
	 *      "zgqk":"整改情况(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("视察情况修改")
	@PostMapping("scqkUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> scqkUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/scqk/scqkUpdate";

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
				return ResponseMessage.error(msg.getMessage());
			}
			List<ScqkModel> scqkModel = JSONArray.parseArray(map.get("entity").toString(), ScqkModel.class);
			scqkModel.get(0).setState("R2");
			scqkModel.get(0).setJsbh(jsbh);
			scqkModel.get(0).setUpdatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.scqkUpdate(scqkModel.get(0).getId(),scqkModel.get(0));
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
}
