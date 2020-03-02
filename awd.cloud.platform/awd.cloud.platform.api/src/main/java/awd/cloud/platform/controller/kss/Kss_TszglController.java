/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.SzjdjlModel;
import awd.bj.kss.model.TszglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
import awd.cloud.platform.model.kss.Kss_TszglModel;
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
@RequestMapping("/v4/kss/tszgl")
@Api(tags = "kss-tszgl",description = "Tszgl") 
public class Kss_TszglController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {post} /v4/kss/tszgl/tszglAdd 提审证管理新增
	 * @apiVersion 0.4.0
	 * @apiName tszglAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 提审证管理新增.
	 *

	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *					"id":"id(最大长度:23)",
	 *       			"jsbh":"监所编号(必填；最大长度:9)",
	 *       			"snbh":"所内编号(必填;最大长度:8)",
	 *       		    "xm":"姓名(必填；最大长度:30)",
	 *       		    "jsh":"监室号(必填；最大长度:4)",
	 *       	        "badw":"办案单位(必填；最大长度:60)",
	 *       	        "fzrq":"发证时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *       	        "blr":"办理人(必填;最大长度:30)",
	 *                  "creator":"创建人(必填；最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"bar":"\\S{1,30}","badw":"\\S{1,60}","fzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"rybh":"110000114201912040013","jsbh":"110000114","snbh":"20190297","fzrq":"2019-10-10 10:10:10","xm":"是是是","jsh":"1204","badw":"310000000000","bar":"dsad","creator":"管理员"}]}

	@ApiOperation("提审证管理新增")
	@PostMapping("/tszglAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tszglAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tszgl/tszglAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			TszglModel tszglEntity=JSONObject.parseObject(rslist.get(0).toString(),TszglModel.class);
			tszglEntity.setJsbh(jsbh);
			tszglEntity.setState("R2");
			tszglEntity.setCreatetime(new Date());
			tszglEntity.setYxbs("1");
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> result = kssServerApis.tszglSave(tszglEntity);
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
	 * @api {post} /v4/kss/tszgl/tszglUpdate 提审证修改
	 * @apiVersion 0.4.0
	 * @apiName tszglUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 提审证修改.
	 *

	 * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *					"id":"id(必填;最大长度:23)",
	 *       			"jsbh":"监所编号(必填；最大长度:9)",
	 *       			"snbh":"所内编号(必填;最大长度:8)",
	 *       		    "xm":"姓名(必填；最大长度:30)",
	 *       		    "jsh":"监室号(必填；最大长度:4)",
	 *       	        "badw":"办案单位(必填；最大长度:60)",
	 *       	        "fzrq":"发证时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *       	        "blr":"办理人(必填;最大长度:30)",
	 *                  "creator":"创建人(必填；最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"id":"\\d{1,23}","bar":"\\S{1,30}","badw":"\\S{1,60}","fzrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"id":"11000011420200114000192","rybh":"110000114201912040013","jsbh":"110000114","snbh":"20190297","fzrq":"2019-10-10 10:10:10","xm":"是是是","jsh":"1204","badw":"310000000000","bar":"dsad","creator":"管理员"}]}

	@ApiOperation("提审证修改")
	@PostMapping("/tszglUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tszglUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tszgl/tszglUpdate";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			TszglModel tszglEntity=JSONObject.parseObject(rslist.get(0).toString(),TszglModel.class);
			if (!StringUtils.isNullOrEmpty(CacheUtils.get().getDictionary("BADW", tszglEntity.getBadw()))) {
				tszglEntity.setBadw(CacheUtils.get().getDictionary("BADW", tszglEntity.getBadw()));
			}
			tszglEntity.setUpdatetime(new Date());
			tszglEntity.setJsbh(jsbh);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> result = kssServerApis.tszglUpdate(tszglEntity.getId(), tszglEntity);
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
	public ResponseMessage<PagerResult<Kss_TszglModel>> tszgl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_TszglModel>> result= kssService.tszgl_query(queryParam);
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
	public ResponseMessage<String> tszgl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_TszglModel data) {
		return kssService.tszgl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tszgl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_TszglModel data) {
		return kssService.tszgl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_TszglModel> tszgl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.tszgl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> tszgl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.tszgl_deleteByKey(id);
	}




	/**
	 * @api {post} /v4/kss/tszgl/tszglList  提审证管理查询
	 * @apiVersion 0.4.0
	 * @apiName findtsz
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription    提审证管理查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}creator          				                 人员编号
	 * @apiSuccess {String}fzrqString                                        发证日期
	 * @apiSuccess {String}yxbsString                                        发卡状态(已转换)
	 * @apiSuccess {String}yxbs                                              发卡状态(数字字典:fkzt)
	 * @apiSuccess {String}blr                                               办理人
	 * @apiSuccess {String}stateString                                       状态(已转换)
	 * @apiSuccess {String}bar                                               办案人
	 * @apiSuccess {String}updatetimeString                                  更新时间
	 * @apiSuccess {String}rybh                                              人员编号
	 * @apiSuccess {String}bz                                                备注
	 * @apiSuccess {String}updator                                           创建人
	 * @apiSuccess {String}id                                                id
	 * @apiSuccess {String}tszh                                              提审证号
	 * @apiSuccess {String}state                                             状态(数字字典:ywstate)
	 * @apiSuccess {String}jsbhString                                        监所名称
	 * @apiSuccess {String}badw                                              办案单位
	 * @apiSuccess {String}createtimeString                                  创建时间
	 * @apiSuccess {String}jsbh                                              监所编号

	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}total                                             返回总数
	 * @apiSuccess {String}data                                              返回数据
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 114,
	 *     "data": [
	 *     {
	 *         "creator": "管理员",
	 *         "fzrqString": "2019-10-18 19:30:53",
	 *         "yxbsString": "卡已收回",
	 *         "yxbs": "3",
	 *         "blr": "办理人",
	 *         "stateString": "有效",
	 *         "bar": "办案人",
	 *         "updatetimeString": "2019-11-29 19:04:21",
	 *         "rybh": "310000111201906200004",
	 *         "bz": "备注",
	 *         "updator": "管理员",
	 *         "id": "11000011420191018000048",
	 *         "tszh": "110000114191018000001",
	 *         "state": "R2",
	 *         "jsbhString": "北京市第一看守所",
	 *         "badw": "北京市公安局",
	 *         "createtimeString": "2019-10-18 19:31:12",
	 *         "jsbh": "110000114"
	 *       },
	 *        ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578447852711
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 * 	  json:{
	 *            "rybh":"人员编号"
	 *            "tszh":"提审证号'
	 *            "fzrq":"发证日期"
	 *            "yxbs":"发卡状态"
	 * }
	 *
	 *
	 */

	@ApiOperation("提审证管理查询")
	@PostMapping("/tszglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> findtsz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/tszgl/tszglList";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("tszh"))) {
				param.and("tszh", TermType.eq, maps.getResult().get("tszh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("fkrq"))){
				param.and("fzrq", TermType.gt, maps.getResult().get("fkrq") + " 00:00:00");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("fkrq"))){
				param.and("fzrq", TermType.lt, maps.getResult().get("fkrq") + " 23:59:59");
			}

			if (!StringUtils.isNullOrEmpty(maps.getResult().get("yxbs"))) {
				param.and("yxbs", TermType.eq, maps.getResult().get("yxbs"));
			}


			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.tszglQuery(param);
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





}
