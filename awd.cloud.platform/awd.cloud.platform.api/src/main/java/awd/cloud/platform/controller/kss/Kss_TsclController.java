/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.TsclModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.LdspModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.StringUtils;
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
import awd.cloud.platform.model.kss.Kss_TsclModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/tscl")
@Api(tags = "kss-tscl",description = "Tscl") 
public class Kss_TsclController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {get} /v4/kss/tscl/tsclTableQuery 投诉处理人员查询
	 * @apiVersion 0.4.0
	 * @apiName tsclTableQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 投诉处理人员查询.
	 *
	   @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
	 * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "查询成功",
	 * "result": {
	 * "total": 1,
	 * "data": [
	 * {
	 * id: "11000011420191126000113"
	 * jsbh: "110000114"
	 * jsbhString: "北京市第一看守所"
	 * rybh: "310000111201906200004"
	 * tsrxm: "汪枫桦"
	 * gx: ""
	 * gxString: ""
	 * btsdx: "我"
	 * tsdx: "04"
	 * tsdxString: "看守所民警"
	 * tslx: "3"
	 * tslxString: "信函"
	 * tsnr: ""
	 * tssj: "2019-11-26 19:02:02"
	 * slr: "我"
	 * slsj: "2019-11-26 19:02:12"
	 * spr: ""
	 * spyj: ""
	 * spsj: null
	 * state: "R2"
	 * stateString: "有效"
	 * scbz: ""
	 * scbzString: null
	 * operator: "管理员"
	 * savetime: "2019-11-26 19:02:13"
	 * saveuser: "管理员"
	 * bz: ""
	 * clsj: null
	 * cljg: ""
	 * clr: ""
	 * pastable: "1"
	 * pastableString: "是"
	 * creator: "管理员"
	 * createtime: "2019-11-26 19:02:12"
	 * updator: ""
	 * updatetime: null
	 * ywlcid: "4743750"
	 * psbz: "0"
	 * psbzString: "未批示"
	 * xm: null
	 * snbh: null
	 * }
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
	 * "rybh":"人员编号(最大字段长度：21)",
	 * }
	 */
	@OpenAPI
	@ApiOperation("投诉处理人员查询")
	@GetMapping("/tsclTableQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> tsclTableQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tscl/tsclTableQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}

		QueryParam param = new QueryParam();
		if(StringUtils.isNullOrEmpty(maps.getResult().get("tssj_start"))){
			param.and("tssj", TermType.gte, maps.getResult().get("tssj_start"));
		}
		if(StringUtils.isNullOrEmpty(maps.getResult().get("tssj_end"))){
			param.and("tssj", TermType.gte, maps.getResult().get("tssj_end"));
		}
		ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.tsclQueryForPage(param);
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
	}


	/**
	 * @api {post} /v4/kss/tscl/tscljgAdd 投诉处理结果添加
	 * @apiVersion 0.4.0
	 * @apiName tscljgAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 投诉处理结果添加.
	 *
	   @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
	 * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 *

	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{"entity":[{
	 * "taskid": "5340929",
	 * "rybh": "110000114201911130001",
	 * "ywlcid": "4416772",
	 * "clr": "管理员",
	 * "clsj": "2020-02-18 19:58:24",
	 * "cljg": "啊啊啊",
	 * }]
	 * }
	 */
	@ApiOperation("投诉处理结果添加")
	@PostMapping("tscljgAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tscljgAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/tscl/tscljgAdd";

		//通过校验获取查询参数
		try {
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
			List<TsclModel> tsclModel = JSONArray.parseArray(map.get("entity").toString(), TsclModel.class);
			tsclModel.get(0).setJsbh(jsbh);
			tsclModel.get(0).setClsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("clsj")));

			String taskid = map.get("taskid").toString();
			if (null==taskid  || "".equals(taskid)) {
				return ResponseMessage.error("任务ID:taskid必传");
			}
			ResponseMessage<String> result = kssServerApis.addTscldj(taskid,tsclModel.get(0).getRybh(),tsclModel.get(0));
			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/tscl/ldspAdd 投诉处理领导审批
	 * @apiVersion 0.4.0
	 * @apiName ldspAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 投诉处理领导审批.
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{"entity":[{
	 * "taskid": "4416804",
	 * "jsbh": "110000114",
	 * "rybh": "110000114201911130001",
	 * "ywlcid": "4416772",
	 * "snbh": "20190127",
	 * "xm": "张三",
	 * "jsh": "0301",
	 * "tslx": "口头",
	 * "gx": "",
	 * "tsrxm": "妊娠检测",
	 * "tsdx": "看守所领导",
	 * "btsdx": "wwdd",
	 * "tsnr":""  ,
	 * "tssj":" 2019-11-19 13:43:36",
	 * "slr": "dwdwdwd",
	 * "slsj": "2019-11-19 13:43:42",
	 * "psbz": "1",
	 * "ldyj": "同意",
	 * "blr": "啊啊啊",
	 * "blsj": "2020-02-18 19:24:40"
	 * }]
	 * }
	 */
	@ApiOperation("投诉处理领导审批")
	@PostMapping("ldspAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<?> ldspAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/tscl/ldspAdd";

		//通过校验获取查询参数
		try {
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
			List<LdspModelDO> ldspModelDO = JSONArray.parseArray(map.get("entity").toString(), LdspModelDO.class);
			ldspModelDO.get(0).setJsbh(jsbh);
			ldspModelDO.get(0).setPsbz(request.getParameter("psbz"));
			ldspModelDO.get(0).setState("R2");
			ldspModelDO.get(0).setSqsj(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("slsj")));
			ldspModelDO.get(0).setCreatetime(new Date());
			ldspModelDO.get(0).setProcessName("kss_tscl");
			ldspModelDO.get(0).setBlsj(new Date());
			String taskid = map.get("taskid").toString();
			if (null==taskid  || "".equals(taskid)) {
				return ResponseMessage.error("任务ID:taskid必传");
			}
			ResponseMessage<?> result = kssServerApis.ldspSave(ldspModelDO.get(0),taskid);
			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}



	/**
	 * @api {post} /v4/kss/tscl/tsclSave 投诉处理保存
	 * @apiVersion 0.4.0
	 * @apiName tsclSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 投诉处理保存.
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{"entity":[{
	 * "rybh": "310000111201906200004",
	 * "snbh": "20190035",
	 * "xm": "汪枫桦",
	 * "jsh": "0114",
	 * "gx": "110",
	 * "tsrxm": "汪枫桦",
	 * "tsdx": "04",
	 * "btsdx": "奥奥",
	 * "tslx": "2",
	 * "tssj":" 2020-02-18 18:56:55",
	 * "tsnr": "奥奥",
	 * "slr": "奥奥",
	 * "slsj":" 2020-02-18 18:56:55",
	 * "bz": "奥奥"
	 * }]
	 * }
	 */
	@ApiOperation("投诉处理保存")
	@PostMapping("tsclSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tsclSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/tscl/tsclSave";

		//通过校验获取查询参数
		try {
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
			List<TsclModel> tsclModel = JSONArray.parseArray(map.get("entity").toString(), TsclModel.class);
			tsclModel.get(0).setState("R2");
			tsclModel.get(0).setJsbh(jsbh);
			tsclModel.get(0).setCreatetime(new Date());
			JbxxModel jbxxModel=new JbxxModel();
			jbxxModel.setXm(maps.getResult().get("xm").toString());
			jbxxModel.setSnbh(maps.getResult().get("snbh").toString());
			jbxxModel.setJsh(maps.getResult().get("jsh").toString());
			Map<String,Object> map1=new HashMap<>();
			map1.put("lcid", CacheUtils.get().getFlowMap("kss_tscl").get("mapname"));
			map1.put("jbxxEntity",jbxxModel);
			map1.put("jjxEntity",tsclModel.get(0));
			ResponseMessage<String> result = kssServerApis.addTscl(map1);
			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}





	/**
	 * @api {get} /v4/kss/tscl/tsclQuery 投诉处理查询
	 * @apiVersion 0.4.0
	 * @apiName tsclQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 投诉处理查询.
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}snbh          				                    所内编号
	 * @apiSuccess {String}xm         				                        姓名
	 * @apiSuccess {String}tsrxm          				                    投诉人姓名
	 * @apiSuccess {String}btsdx          				                    被投诉对象
	 * @apiSuccess {String}tsdxString          				                投诉对象
	 * @apiSuccess {String}tslxString          				                投诉类型
	 * @apiSuccess {String}slr          				                    受理人
	 * @apiSuccess {String}tssj												投诉时间
	 * @apiSuccess {String}slsjString									    受理时间
	 * @apiSuccess {String}psbzString										领导审批意见
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "查询成功",
	 * "result": {
	 * "total": 1,
	 * "data": [
	 * {
	 * id: "11000011420191126000113"
	 * jsbh: "110000114"
	 * jsbhString: "北京市第一看守所"
	 * rybh: "310000111201906200004"
	 * tsrxm: "汪枫桦"
	 * gx: ""
	 * gxString: ""
	 * btsdx: "我"
	 * tsdx: "04"
	 * tsdxString: "看守所民警"
	 * tslx: "3"
	 * tslxString: "信函"
	 * tsnr: ""
	 * tssj: "2019-11-26 19:02:02"
	 * slr: "我"
	 * slsj: "2019-11-26 19:02:12"
	 * spr: ""
	 * spyj: ""
	 * spsj: null
	 * state: "R2"
	 * stateString: "有效"
	 * scbz: ""
	 * scbzString: null
	 * operator: "管理员"
	 * savetime: "2019-11-26 19:02:13"
	 * saveuser: "管理员"
	 * bz: ""
	 * clsj: null
	 * cljg: ""
	 * clr: ""
	 * pastable: "1"
	 * pastableString: "是"
	 * creator: "管理员"
	 * createtime: "2019-11-26 19:02:12"
	 * updator: ""
	 * updatetime: null
	 * ywlcid: "4743750"
	 * psbz: "0"
	 * psbzString: "未批示"
	 * xm: null
	 * snbh: null
	 * }
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
	 * "tssj_start": "2020-02-18"
	 * "tssj_end": "2020-02-18"
	 * }
	 */
	@OpenAPI
	@ApiOperation("投诉处理查询")
	@GetMapping("/tsclQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> tsclQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tscl/tsclQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}

		QueryParam param = new QueryParam();
		param.and("jsbh", jsbh);
		if(StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))){
			param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
		}

		ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.tsclQueryForPage(param);
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
	}



}
