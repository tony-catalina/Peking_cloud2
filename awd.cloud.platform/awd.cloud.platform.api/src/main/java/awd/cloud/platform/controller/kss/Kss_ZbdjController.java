/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.ZbdjModel;
import awd.bj.kss.model.ZbdjhistoryModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_ZbdjModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/kss/zbdj")
@Api(tags = "kss-zbdj",description = "Zbdj") 
public class Kss_ZbdjController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zbdj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZbdjModel data) {
		return kssService.zbdj_updateByKey(id, data);
	}	

	@OpenAPI
	public ResponseMessage<Kss_ZbdjModel> zbdj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zbdj_getByKey(id);
	}
	
	@OpenAPI
	public ResponseMessage<Integer> zbdj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zbdj_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/kss/zbdj/zbdjSelectOne 当前值班查询
	 * @apiVersion 0.4.0
	 * @apiName zbdjSelectOne
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 当前值班查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}zbrq          				                        值班日期
	 * @apiSuccess {String}sld       				                    		所领导
	 * @apiSuccess {String}sy          				                		收押
	 * @apiSuccess {String}ts          				                		提审
	 * @apiSuccess {String}xkzb          				                        巡控早班
	 * @apiSuccess {String}xkzhb          				                		巡控中班
	 * @apiSuccess {String}xkwb          				                		巡控晚班
	 * @apiSuccess {String}yszb				                        		医生早班
	 * @apiSuccess {String}yszhb   				                    		医生中班
	 * @apiSuccess {String}yswb   				                    			医生晚班
	 * @apiSuccess {String}gj   				                    			管教
	 * @apiSuccess {String}zkzb   				                    			总控早班
	 * @apiSuccess {String}zkzhb   				                    		总控中班
	 * @apiSuccess {String}zkwb   				                    			总控晚班
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间
	 * @apiSuccess {String}week          				                		星期

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
	 *        	id: ""
	 * 			jsbh: ""
	 * 			jsbhString: ""
	 * 			zbrq: "2020-01-03 00:00:00"
	 * 			sld: "发个"
	 * 			sy: "实弹"
	 * 			ts: "实弹"
	 * 			xkzb: "实弹"
	 * 			xkzhb: "实弹"
	 * 			xkwb: "实弹"
	 * 			yszb: "实弹"
	 * 			yszhb: "实弹"
	 * 			yswb: "实弹"
	 * 			gj: "实弹"
	 * 			zkzb: "实弹"
	 * 			zkzhb: "实弹"
	 * 			zkwb: "实弹"
	 * 			state: "R2"
	 * 			stateString: "有效"
	 * 			creator: ""
	 * 			createtime: "2019-12-31 09:59:58"
	 * 			updator: ""
	 * 			updatetime: null
	 * 			week: ""
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
	 *    json:{}
	 *
	 */
	//id,jsbh,jsbhString,zbrq,sld,sy,ts,xkzb,xkzhb,xkwb,yszb,yszhb,yswb,gj,zkzb,zkzhb,zkwb,week
	@OpenAPI
	@ApiOperation("当前值班查询")
	@GetMapping("/zbdjSelectOne")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> zbdjSelectOne(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/zbdj/zbdjSelectOne";
		String state = request.getParameter("state");
		try{
			ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
			if (maps.getStatus() != 200){
				return ResponseMessage.error(maps.getMessage());
			}
			ResponseMessage<List<ZbdjModel>> result = null;
			if (!StringUtils.isNullOrEmpty(jsbh)){
				result = kssServerApis.zbdjSelectOne(jsbh);
			}
			System.err.println("result" + JSON.toJSONString(result));
			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().size());

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
	 * @api {post} /v4/kss/zbdj/zbdjAdd 值班登记保存
	 * @apiVersion 0.4.0
	 * @apiName zbdjAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 值班登记保存
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
	 * 	 "creator:"创建人（必填：最大长度:50）",
	 *   "entity":[
	 * 		{"name": "sld", "value": "实弹", "creator": "创建人(必填; 最大长度:50)"},
	 * 		{"name": "sy", "value": "1111111111111111111"},
	 * 		{"name": "ts", "value": "123123123"},
	 * 		{"name": "xkzb", "value": "元始天尊"},
	 * 		{"name": "xkzhb", "value": "123123123"},
	 * 		{"name": "xkwb", "value": "123123123"},
	 * 		{"name": "yszb", "value": "123123123"},
	 * 		{"name": "yszhb", "value": "元始天尊"},
	 * 		{"name": "yswb", "value": "rr "},
	 * 		{"name": "gj", "value": "元始天尊"},
	 * 		{"name": "zkzb", "value": "元始天尊"},
	 * 		{"name": "zkzhb", "value": "元始天尊"},
	 * 		{"name": "zkwb", "value": "3"},
	 * 		...
	 *   ]
	 * }
	 *
	 */
	//{"creator":".{1,50}"}
	@ApiOperation("值班登记保存")
	@PostMapping("/zbdjlAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zbdjlAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		String interfaceId = "/v4/kss/zbdj/zbdjlAdd";
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

			List<ZbdjModel> zbdjModelList = new ArrayList<>();
			List list = JSONArray.parseArray(map.get("entity").toString(),ZbdjModel.class);
			JSONArray array = new JSONArray(list);
			int k = 1;
			for (int i = 0; i < 7; i++){
				ZbdjModel zbdjModel = new ZbdjModel();
				for (int j = 1 * 13 ;;){
					JSONObject js1 = JSONObject.parseObject(array.get(j).toString());
					JSONObject js2 = JSONObject.parseObject(array.get(j+1).toString());
					JSONObject js3 = JSONObject.parseObject(array.get(j+2).toString());
					JSONObject js4 = JSONObject.parseObject(array.get(j+3).toString());
					JSONObject js5 = JSONObject.parseObject(array.get(j+4).toString());
					JSONObject js6 = JSONObject.parseObject(array.get(j+5).toString());
					JSONObject js7 = JSONObject.parseObject(array.get(j+6).toString());
					JSONObject js8 = JSONObject.parseObject(array.get(j+7).toString());
					JSONObject js9 = JSONObject.parseObject(array.get(j+8).toString());
					JSONObject js10 = JSONObject.parseObject(array.get(j+9).toString());
					JSONObject js11 = JSONObject.parseObject(array.get(j+10).toString());
					JSONObject js12 = JSONObject.parseObject(array.get(j+11).toString());
					JSONObject js13 = JSONObject.parseObject(array.get(j+12).toString());
					zbdjModel.setSld(js1.get("value").toString());
					zbdjModel.setSy(js2.get("value").toString());
					zbdjModel.setTs(js3.get("value").toString());
					zbdjModel.setXkzb(js4.get("value").toString());
					zbdjModel.setXkzhb(js5.get("value").toString());
					zbdjModel.setXkwb(js6.get("value").toString());
					zbdjModel.setGj(js7.get("value").toString());
					zbdjModel.setZkzb(js8.get("value").toString());
					zbdjModel.setZkzhb(js9.get("value").toString());
					zbdjModel.setZkwb(js10.get("value").toString());
					zbdjModel.setYszb(js11.get("value").toString());
					zbdjModel.setYszhb(js12.get("value").toString());
					zbdjModel.setYswb(js13.get("value").toString());
					zbdjModel.setState("R2");
					zbdjModel.setJsbh(jsbh);
					zbdjModel.setCreator(JSONObject.parseObject(array.get(0).toString()).get("creator").toString());
					break;
				}
				zbdjModel.setWeek(String.valueOf(i+1));
				zbdjModelList.add(zbdjModel);
				k++;
			}

			ResponseMessage<String> result = kssServerApis.zbdjSave(zbdjModelList);
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
	 * @api {get} /v4/kss/zbdj/zbdjList 值班登记查询
	 * @apiVersion 0.4.0
	 * @apiName zbdjList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 值班登记查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}zbrq          				                        值班日期
	 * @apiSuccess {String}sld       				                    		所领导
	 * @apiSuccess {String}sy          				                		收押
	 * @apiSuccess {String}ts          				                		提审
	 * @apiSuccess {String}xkzb          				                        巡控早班
	 * @apiSuccess {String}xkzhb          				                		巡控中班
	 * @apiSuccess {String}xkwb          				                		巡控晚班
	 * @apiSuccess {String}yszb				                        		医生早班
	 * @apiSuccess {String}yszhb   				                    		医生中班
	 * @apiSuccess {String}yswb   				                    			医生晚班
	 * @apiSuccess {String}gj   				                    			管教
	 * @apiSuccess {String}zkzb   				                    			总控早班
	 * @apiSuccess {String}zkzhb   				                    		总控中班
	 * @apiSuccess {String}zkwb   				                    			总控晚班
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间
	 * @apiSuccess {String}week          				                		星期

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
	 *        	id: null
	 * 			jsbh: null
	 * 			jsbhString: null
	 * 			zbrq: null
	 * 			sld: ""
	 * 			sy: "123"
	 * 			ts: "元始天尊"
	 * 			xkzb: "撒地方"
	 * 			xkzhb: "1111111111111111111"
	 * 			xkwb: "3"
	 * 			yszb: "rr "
	 * 			yszhb: "123"
	 * 			yswb: "5"
	 * 			gj: "5"
	 * 			zkzb: "123123123"
	 * 			zkzhb: "撒地方"
	 * 			zkwb: "撒地方"
	 * 			state: null
	 * 			stateString: null
	 * 			creator: null
	 * 			createtime: null
	 * 			updator: null
	 * 			updatetime: null
	 * 			week: null
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
	 *          "apsj": "年月（格式:yyyy-MM）",
	 *          "week": "周（必填; 最大长度：6）",
	 *         }
	 *
	 */
	//id,jsbh,jsbhString,zbrq,sld,sy,ts,xkzb,xkzhb,xkwb,yszb,yszhb,yswb,gj,zkzb,zkzhb,zkwb,week
	@OpenAPI
	@ApiOperation("值班登记查询")
	@GetMapping("/zbdjList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> zbdjList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/zbdj/zbdjList";
		String state = request.getParameter("state");
		try{
			ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
			if (maps.getStatus() != 200){
				return ResponseMessage.error(maps.getMessage());
			}
			QueryParam queryParam = new QueryParam();
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("week"))){
				queryParam.and("week", maps.getResult().get("week"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("apsj"))) {
				queryParam.and("year", maps.getResult().get("apsj").toString().substring(0, 4));
				queryParam.and("month", "0"+maps.getResult().get("apsj").toString().substring(5));
			}
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
			System.err.println("param--" + JSON.toJSONString(queryParam));

			ResponseMessage<PagerResult<ZbdjhistoryModel>> result = kssServerApis.zbdjHistoryList(queryParam);
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
}
