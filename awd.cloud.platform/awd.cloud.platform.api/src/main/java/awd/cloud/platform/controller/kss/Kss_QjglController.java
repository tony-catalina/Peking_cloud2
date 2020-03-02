/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.EmdjModel;
import awd.bj.kss.model.QjglModel;
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
import awd.cloud.platform.model.kss.Kss_QjglModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/qjgl")
@Api(tags = "kss-qjgl",description = "Qjgl") 
public class Kss_QjglController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;



	/**
	 * @api {post} /v4/kss/qjgl/qjglList 清监管理查询
	 * @apiVersion 0.4.0
	 * @apiName qjglList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 清监管理查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}jsh												监室号
	 * @apiSuccess {String}qjmj												清监民警
	 * @apiSuccess {String}jjwj												警戒武警
	 * @apiSuccess {String}qjsj												清监时间
	 * @apiSuccess {String}qjqk												清监情况
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
	 * 		    	"id": "11000011420191230000144",
	 * 				"jsbh": "110000114",
	 * 				"jsbhString": "北京市第一看守所",
	 * 				"jsh": "02",
	 * 				"qjmj": "小猪",
	 * 				"jjwj": "阿萨德",
	 * 				"qjsj": "2019-12-30 11:35:43",
	 * 				"qjqk": "啊实打实",
	 * 				"bz": "阿萨德",
	 * 				"state": "R2",
	 * 				"stateString": "有效",
	 * 				"creator": "管理员",
	 * 				"createtime": "2019-12-30 11:36:46",
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
	 *          "jsh":"监室号(最大长度:4)",
	 *          "qjsjBengin":"检查时间开始(格式:yyyy-MM-dd hh:mm:ss)",
	 *          "qjsjEnd":"检查时间结束(格式:yyyy-MM-dd hh:mm:ss)",
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }
	 *
	 */


	//id,jsbh,jsbhString,jsh,qjmj,jjwj,qjsj,qjqk,bz,state,stateString,creator,createtime,updator,updatetime
	//{"jsh":"110000112201911050002","qjsjBengin":"","qjsjEnd":"","page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("清监管理查询")
	@PostMapping("/qjglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> qjglList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/qjgl/qjglList";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String qjsjBengin=(String) maps.getResult().get("qjsjBengin");
			String qjsjEnd=(String) maps.getResult().get("qjsjEnd");
			String jsh=(String) maps.getResult().get("jsh");
			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(qjsjBengin)) {
				qParam.and("qjsj", TermType.gte, qjsjBengin);
			}
			if(!StringUtils.isNullOrEmpty(qjsjEnd)) {
				qParam.and("qjsj", TermType.lte, qjsjEnd+ " 23:59:59");
			}
			if(!StringUtils.isNullOrEmpty(jsh)) {
				qParam.and("jsh", TermType.like, jsh+"%");
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam,"R2");
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.qjglQueryForPage(qParam);
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
	 * @api {post} /v4/kss/qjgl/qjglAdd  耳目考核保存
	 * @apiVersion 0.4.0
	 * @apiName qjglAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  耳目考核保存.
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
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 *              "creator":"创建人(必填;最大字段长度:21)",
	 *  			"jsh": "监室号(必填;最大字段长度:4)",
	 * 				"qjsj": "清监时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 				"qjmj": "清监民警(必填;最大字段长度:50)",
	 * 				"jjwj": "警戒武警(必填;最大字段长度:50)",
	 * 				"qjqk": "清监情况(必填;)",
	 * 				"bz": "备注()"
	 * 	        }]
	 * }
	 *
	 * @apiUse CreateError
	 */
	@ApiOperation("清监管理保存")
	@PostMapping("/qjglAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> qjglAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//{"jsh":"\\d{1,4}","qjmj":"\\S{1,50}","jjwj":"\\S{1,50}","qjsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"creator":"aaa","jsh":"0201","qjsj":"2019-12-30 10:10:10","qjmj":"ffff","jjwj":"aa","qjqk":"hghgh","bz":"hrgh"}]}
		//接口id
		String interfaceId = "/v4/kss/qjgl/qjglAdd";
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
			List<QjglModel> qjglModels = JSONArray.parseArray(map.get("entity").toString(), QjglModel.class);
			QjglModel qjglModel = qjglModels.get(0);
			qjglModel.setState("R2");
			qjglModel.setJsbh(jsbh);
			qjglModel.setCreatetime(new Date());
			if (StringUtils.isNullOrEmpty(qjglModel.getCreator())){
				return ResponseMessage.error("创建人creator不能为空！");
			}
			if (StringUtils.isNullOrEmpty(qjglModel.getQjqk())){
				return ResponseMessage.error("清监情况qjqk不能为空！");
			}
			ResponseMessage<String> result = kssServerApis.qjglSave(qjglModel);
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
	public ResponseMessage<PagerResult<Kss_QjglModel>> qjgl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_QjglModel>> result= kssService.qjgl_query(queryParam);
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
	public ResponseMessage<String> qjgl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_QjglModel data) {
		return kssService.qjgl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> qjgl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_QjglModel data) {
		return kssService.qjgl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_QjglModel> qjgl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.qjgl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> qjgl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.qjgl_deleteByKey(id);
	}
}
