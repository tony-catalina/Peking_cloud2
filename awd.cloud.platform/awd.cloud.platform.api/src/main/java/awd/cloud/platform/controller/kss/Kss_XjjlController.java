/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.XjjlModel;
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
import awd.cloud.platform.model.kss.Kss_XjjlModel;
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
@RequestMapping("/v4/kss/xjjl")
@Api(tags = "kss-xjjl",description = "Xjjl") 
public class Kss_XjjlController extends PublicService {
	
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
	public ResponseMessage<PagerResult<Kss_XjjlModel>> xjjl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_XjjlModel>> result= kssService.xjjl_query(queryParam);
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
	public ResponseMessage<String> xjjl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XjjlModel data) {
		return kssService.xjjl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xjjl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XjjlModel data) {
		return kssService.xjjl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_XjjlModel> xjjl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xjjl_getByKey(id);
	}
	


	@OpenAPI
	public ResponseMessage<Integer> xjjl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xjjl_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/kss/xjjl/xjjlList 巡检记录查询
	 * @apiVersion 0.4.0
	 * @apiName xjjlList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 巡检记录查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}type          				                        项目类型
	 * @apiSuccess {String}typeString       				                    项目类型（已转换）
	 * @apiSuccess {String}name          				                		项目名称
	 * @apiSuccess {String}xjrq          				                		巡检日期
	 * @apiSuccess {String}xjr          				                        巡检人
	 * @apiSuccess {String}ywyc          				                		有无异常
	 * @apiSuccess {String}ywycString          				                有无异常（已转换）
	 * @apiSuccess {String}ycqk				                        		异常情况
	 * @apiSuccess {String}bz   				                    			备注
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间
	 * @apiSuccess {String}dbrzsfzc          				                	打包日志是否异常
	 * @apiSuccess {String}fsrzsfzc          				                	发送日志是否异常
	 * @apiSuccess {String}zsjcsbsfzc          				                驻所检查设备是否正常

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
	 *        	id: "11000011420191230000099"
	 * 			jsbh: "110000114"
	 * 			jsbhString: "北京市第一看守所"
	 * 			type: ""
	 * 			typeString: ""
	 * 			name: ""
	 * 			xjrq: "2019-12-30 20:08:46"
	 * 			xjr: "管理员"
	 * 			ywyc: ""
	 * 			ycqk: ""
	 * 			bz: ""
	 * 			state: "R2"
	 * 			stateString: "有效"
	 * 			creator: "管理员"
	 * 			createtime: "2019-12-30 20:08:55"
	 * 			updator: ""
	 * 			updatetime: null
	 * 			dbrzsfzc: "正常"
	 * 			fsrzsfzc: "正常"
	 * 			zsjcsbsfzc: "正常"
	 * 			ywycString: ""
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
	 *          "xjrq_start": "开始时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "xjrq_end": "结束时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "page": "当前页数",
	 *          "rows": "一页数据量",
	 *          "sort": "id",
	 *          "order": "desc"
	 *         }
	 *
	 */
	//id,jsbh,jsbhString,type,typeString,name,xjrq,xjr,ywyc,ycqk,bz,dbrzsfzc,fsrzsfzc,zsjcsbsfzc,ywycString
	@OpenAPI
	@ApiOperation("巡检记录查询")
	@GetMapping("/xjjlList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> xjjlList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/xjjl/xjjlList";
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
				queryParam.and("xjrq", TermType.gte, maps.getResult().get("xjrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xjrq_end"))) {
				queryParam.and("xjrq", TermType.lte, maps.getResult().get("xjrq_end"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
			System.err.println("param--" + JSON.toJSONString(queryParam));

			ResponseMessage<PagerResult<XjjlModel>> result = kssServerApis.xjjlList(queryParam);
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
	 * @api {post} /v4/kss/xjjl/xjjlAdd 巡检记录保存
	 * @apiVersion 0.4.0
	 * @apiName xjjlAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 巡检记录保存
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
	 *     	"xjrq": "巡检日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "xjr": "巡检人（必填; 最大长度:50）",
	 *      "dbrzsfzc": "打包日志是否正常（必填; 最大长度:20）",
	 *      "fsrzsfzc": "发送日志是否正常（必填; 最大长度:20）",
	 *      "zsjcsbsfzc": "驻所检查设备是否正常（必填; 最大长度:20）",
	 *      "ycqk": "异常情况",
	 *      "creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"xjrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xjr":".{1,50}","dbrzsfzc":".{1,20}","fsrzsfzc":".{1,20}","zsjcsbsfzc":".{1,20}","creator":".{1,30}"}
	@ApiOperation("巡检记录保存")
	@PostMapping("/xjjllAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xjjllAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		String interfaceId = "/v4/kss/xjjl/xjjllAdd";
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
			List<XjjlModel> list = JSONArray.parseArray(map.get("entity").toString(),XjjlModel.class);
			XjjlModel xjjlModel = list.get(0);
			xjjlModel.setState("R2");
			xjjlModel.setJsbh(jsbh);
			xjjlModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.xjjlSave(xjjlModel);
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
	 * @api {post} /v4/kss/xjjl/xjjlUpdate 巡检记录修改
	 * @apiVersion 0.4.0
	 * @apiName xjjlUpdate
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 巡检记录修改
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
	 *		"xjrq": "巡检日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "xjr": "巡检人（必填; 最大长度:50）",
	 *      "dbrzsfzc": "打包日志是否正常（必填; 最大长度:20）",
	 *      "fsrzsfzc": "发送日志是否正常（必填; 最大长度:20）",
	 *      "zsjcsbsfzc": "驻所检查设备是否正常（必填; 最大长度:20）",
	 *      "ycqk": "异常情况",
	 *      "creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"id":".{1,23}","xjrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xjr":".{1,50}","dbrzsfzc":".{1,20}","fsrzsfzc":".{1,20}","zsjcsbsfzc":".{1,20}","creator":".{1,30}"}
	@ApiOperation("巡检记录修改")
	@PostMapping("xjjlUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xjjlUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/xjjl/xjjlUpdate";
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
			List<XjjlModel> list = JSONArray.parseArray(map.get("entity").toString(),XjjlModel.class);
			XjjlModel xjjlModel = list.get(0);
			xjjlModel.setState("R2");
			xjjlModel.setJsbh(jsbh);
			xjjlModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.xjjlUpdate(xjjlModel.getId(),xjjlModel);
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
