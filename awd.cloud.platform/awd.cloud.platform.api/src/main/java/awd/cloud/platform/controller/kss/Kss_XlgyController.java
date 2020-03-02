/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.QjglModel;
import awd.bj.kss.model.XlgyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_XlgyModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xlgy")
@Api(tags = "kss-xlgy",description = "Xlgy") 
public class Kss_XlgyController extends PublicService {
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
    private KssService kssService;


	/**
	 * @api {post} /v4/kss/xlgy/xlgyList 心理干预查询
	 * @apiVersion 0.4.0
	 * @apiName xlgyList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 心理干预查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}djsj												登记时间
	 * @apiSuccess {String}djr												登记人
	 * @apiSuccess {String}gynr												干预内容
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
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
	 *     "data": [{
	 *     			"id": "11000011420200120000077",
	 * 				"jsbh": "110000114",
	 * 				"jsbhString": "北京市第一看守所",
	 * 				"rybh": "110000114202001030001",
	 * 				"djsj": "2020-01-20 09:26:46",
	 * 				"djr": "管理员",
	 * 				"gynr": "东方闪电",
	 * 				"bz": "",
	 * 				"state": "R2",
	 * 				"stateString": "有效",
	 * 				"creator": "管理员",
	 * 				"createtime": "2020-01-20 09:29:17",
	 * 				"updator": "",
	 * 				"updatetime": null
	 * 		}],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1579158673428
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "rybh$eq":"人员编号(最大长度:21)",
	 *          "state$eq":"状态(最大长度:2)",
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }
	 *
	 */

	//id,jsbh,jsbhString,rybh,djsj,djr,gynr,bz,state,stateString,creator,createtime,updator,updatetime
	//{"rybh$eq":"110000114202001030001","state$eq":"R2","page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("心理干预查询")
	@PostMapping("/xlgyList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> xlgyList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xlgy/xlgyList";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String rybh=(String) maps.getResult().get("rybh$eq");
			String state = (String) maps.getResult().get("state$eq");
			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(rybh)) {
				qParam.and("rybh", TermType.gte, rybh);
			}
			if(StringUtils.isNullOrEmpty(state)) {
				return ResponseMessage.error("state不能为空！");
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam,state);
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.xlgyQueryForPage(qParam);
			System.err.println("result" + JSON.toJSONString(result));
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
	 * @api {post} /v4/kss/xlgy/xlgyAdd  心理干预保存
	 * @apiVersion 0.4.0
	 * @apiName xlgyAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  心理干预保存.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json  											保存参数集(必填)
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 			"id":"id(最大字段长度:23)",
	 * 			"rybh":"人员编号(最大字段长度:21)",
	 * 			"xm":"姓名(最大字段长度:30)",
	 * 			"snbh":"所内编号(最大字段长度:8)",
	 * 			"jsh":"监室号(最大字段长度:4)",
	 * 			"djr":"登记人(必填;最大字段长度:30)",
	 * 			"djsj":"登记时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 			"gynr":"干预内容(必填;)",
	 * 			"bz":"备注()"
	 * 	        }]
	 * }
	 *
	 *
	 */
	@ApiOperation("心理干预保存")
	@PostMapping("/xlgyAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xlgyAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"djr":"\\S{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"creator":"aa","id":"","rybh":"110000114202001030001","xm":"黄屋恩","snbh":"20200006","jsh":"0906","djr":"管理员","djsj":"2020-01-20 09:49:20","gynr":"sddd","bz":""}]}
		//接口id
		String interfaceId = "/v4/kss/xlgy/xlgyAdd";
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

			//封装数据
			List<XlgyModel> xlgyModels = JSONArray.parseArray(map.get("entity").toString(), XlgyModel.class);
			XlgyModel xlgyModel=xlgyModels.get(0);
			xlgyModel.setState("R2");
			xlgyModel.setJsbh(jsbh);
			xlgyModel.setCreatetime(new Date());
			if (StringUtils.isNullOrEmpty(xlgyModel.getCreator())){
				return ResponseMessage.error("creator不能为空！");
			}
			if (StringUtils.isNullOrEmpty(xlgyModel.getGynr())){
				return ResponseMessage.error("干预内容gynr不能为空！");
			}
			ResponseMessage<String> result = kssServerApis.xlgySave(xlgyModel);
			System.err.println("--"+ JSON.toJSONString(result));
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
	 * @api {post} /v4/kss/xlgy/xlgyUpdate  心理干预修改
	 * @apiVersion 0.4.0
	 * @apiName xlgyUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  心理干预修改.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json  											保存参数集(必填)
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 			"id":"id(必填;最大字段长度:23)",
	 * 			"rybh":"人员编号(最大字段长度:21)",
	 * 			"xm":"姓名(最大字段长度:30)",
	 * 			"snbh":"所内编号(最大字段长度:8)",
	 * 			"jsh":"监室号(最大字段长度:4)",
	 * 			"djr":"登记人(必填;最大字段长度:30)",
	 * 			"djsj":"登记时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 			"gynr":"干预内容(必填;)",
	 * 			"bz":"备注()"
	 * 	        }]
	 * }
	 *
	 *
	 */
	@ApiOperation("心理干预修改")
	@PostMapping("/xlgyUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xlgyUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"id":"\\d{1,23}","djr":"\\S{1,30}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"creator":"fw","id":"11000011420200120000078","rybh":"110000114202001030001","xm":"黄屋恩","snbh":"20200006","jsh":"0906","djr":"管理员","djsj":"2020-01-20 09:49:20","gynr":"sddd","bz":""}]}
		//接口id
		String interfaceId = "/v4/kss/xlgy/xlgyUpdate";
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

			//封装数据
			List<XlgyModel> xlgyModels = JSONArray.parseArray(map.get("entity").toString(), XlgyModel.class);
			XlgyModel xlgyModel=xlgyModels.get(0);
			xlgyModel.setState("R2");
			xlgyModel.setJsbh(jsbh);
			xlgyModel.setCreatetime(new Date());
			if (StringUtils.isNullOrEmpty(xlgyModel.getCreator())){
				return ResponseMessage.error("creator不能为空！");
			}
			if (StringUtils.isNullOrEmpty(xlgyModel.getGynr())){
				return ResponseMessage.error("干预内容gynr不能为空！");
			}
			ResponseMessage<String> result = kssServerApis.xlgyUpdate(xlgyModel.getId(), xlgyModel);
			System.err.println("--"+ JSON.toJSONString(result));
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


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_XlgyModel>> xlgy_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_XlgyModel>> result= kssService.xlgy_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xlgy_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XlgyModel data) {
		return kssService.xlgy_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xlgy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XlgyModel data) {
		return kssService.xlgy_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_XlgyModel> xlgy_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xlgy_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> xlgy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xlgy_deleteByKey(id);
	}
}
