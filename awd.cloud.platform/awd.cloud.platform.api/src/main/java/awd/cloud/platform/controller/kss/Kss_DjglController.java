/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.DjglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_DjglModel;
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
@RequestMapping("/v4/kss/djgl")
@Api(tags = "kss-djgl",description = "Djgl") 
public class Kss_DjglController extends PublicService {
	
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
	public ResponseMessage<PagerResult<Kss_DjglModel>> djgl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_DjglModel>> result= kssService.djgl_query(queryParam);
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
	public ResponseMessage<String> djgl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_DjglModel data) {
		return kssService.djgl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> djgl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_DjglModel data) {
		return kssService.djgl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_DjglModel> djgl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.djgl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> djgl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.djgl_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/kss/djgl/djglList 党建管理查询
	 * @apiVersion 0.4.0
	 * @apiName djglList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 党建管理查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}djkt          				                        党建课题
	 * @apiSuccess {String}djlx       				                            党建类型
	 * @apiSuccess {String}djlxString          				                党建类型（已转换）
	 * @apiSuccess {String}djnr          				                        党建内容
	 * @apiSuccess {String}djbm          				                        党建部门
	 * @apiSuccess {String}djbmString          				                党建部门（已转换）
	 * @apiSuccess {String}djsj          				                        党建时间
	 * @apiSuccess {String}zqjcqk          				                    中期检查情况
	 * @apiSuccess {String}jxjg          				                        结项结果
	 * @apiSuccess {String}jxjgString          				                结项结果（已转换）
	 * @apiSuccess {String}hjqk          				                        获奖情况
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
	 *     "total": 12,
	 *     "data": [
	 *       {
	 *        	id: "11000011420191231000055"
	 * 			jsbh: "110000114"
	 * 			jsbhString: "北京市第一看守所"
	 * 			djkt: ""
	 * 			djlx: "01"
	 * 			djlxString: "党建类型1"
	 * 			djnr: ""
	 * 			djbm: "02"
	 * 			djbmString: "党建部门2"
	 * 			djsj: "2019-12-31"
	 * 			zqjcqk: ""
	 * 			jxjg: ""
	 * 			jxjgString: ""
	 * 			hjqk: ""
	 * 			state: "R2"
	 * 			stateString: "有效"
	 * 			creator: "管理员"
	 * 			createtime: "2019-12-31 10:44:37"
	 * 			updator: "管理员"
	 * 			updatetime: "2019-12-31 10:45:54"
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
	 *          "kssj": "开始时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "jssj": "结束时间（格式:yyyy-MM-dd hh:mm:ss）",
	 *          "djkt": ""党建课题,
	 *          "page": "当前页数",
	 *          "rows": "一页数据量",
	 *          "sort": "id",
	 *          "order": "desc"
	 *         }
	 *
	 */
	//jsbh,jsbhString,djkt,djlx,djlxString,djnr,djbm,djbmString,djsj,zqjcqk,jxjg,jxjgString,hjqk
	@OpenAPI
	@ApiOperation("党建管理查询")
	@GetMapping("/djglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> djglList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/djgl/djglList";
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
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("djkt"))){
				queryParam.and("djkt",TermType.like,"%"+ maps.getResult().get("djkt")+"%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("kssj"))) {
				queryParam.and("djsj", TermType.gte, maps.getResult().get("kssj"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jssj"))) {
				queryParam.and("djsj", TermType.lte, maps.getResult().get("jssj"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
			System.err.println("param--" + JSON.toJSONString(queryParam));

			ResponseMessage<PagerResult<DjglModel>> result = kssServerApis.djglList(queryParam);
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
	 * @api {post} /v4/kss/djgl/djglAdd 党建管理保存
	 * @apiVersion 0.4.0
	 * @apiName djglAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 党建管理保存
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
	 *      "djkt": "党建课题",
	 *      "djsj": "党建时间（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "djbm": "党建部门（必填; 最大长度:9）",
	 *      "djlx": "党建类型（必填; 最大长度:9）",
	 *      "djnr": "党建内容",
	 *      "zqjcqk": "中期检查情况",
	 *      "jxjg": "结项结果",
	 *      "hjqk": "获奖情况",
	 *      "creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djbm":".{1,9}","djlx":".{1,9}","creator":".{1,30}"}
	@ApiOperation("党建管理保存")
	@PostMapping("/djgllAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> djgllAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		String interfaceId = "/v4/kss/djgl/djgllAdd";
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
			List<DjglModel> list = JSONArray.parseArray(map.get("entity").toString(),DjglModel.class);
			DjglModel djglModel = list.get(0);
			djglModel.setState("R2");
			djglModel.setJsbh(jsbh);
			djglModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.djglSave(djglModel);
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
	 * @api {post} /v4/kss/djgl/djglUpdate 党建管理修改
	 * @apiVersion 0.4.0
	 * @apiName djglUpdate
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 党建管理修改
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
	 *      "djkt": "党建课题",
	 * 		"djsj": "党建时间（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 * 		"djbm": "党建部门（必填; 最大长度:9）",
	 * 		"djlx": "党建类型（必填; 最大长度:9）",
	 * 		"djnr": "党建内容",
	 * 		"zqjcqk": "中期检查情况",
	 * 		"jxjg": "结项结果",
	 * 		"hjqk": "获奖情况",
	 * 		"creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"id":".{1,23}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djbm":".{1,9}","djlx":".{1,9}","creator":".{1,30}"}
	@ApiOperation("党建管理修改")
	@PostMapping("djglUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> djglUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/djgl/djglUpdate";
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
			List<DjglModel> list = JSONArray.parseArray(map.get("entity").toString(),DjglModel.class);
			DjglModel djglModel = list.get(0);
			djglModel.setState("R2");
			djglModel.setJsbh(jsbh);
			djglModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.djglUpdate(djglModel.getId(),djglModel);
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
