/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.FssgModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_FssgModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/fssg")
@Api(tags = "kss-fssg",description = "Fssg") 
public class Kss_FssgController extends PublicService{
	@Autowired
	private KssServerApis kssServerApis;
	
	/**
	 * @return
	 * @api {get} /v4/kss/fssg/fssgQuery 发生事故查询
	 * @apiVersion 0.4.0
	 * @apiName fssgQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 发生事故查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}qklx										        情况类型(QKLX)
	 * @apiSuccess {String}qklxString										                    情况类型(已转换)
	 * @apiSuccess {String}sglx											 事故类型(FSSGLX)
	 * @apiSuccess {String}sglxString											 事故类型(已转换)
	 * @apiSuccess {String}sgxz											 事故性质(FSSGXZ)
	 * @apiSuccess {String}sgxzString											           事故性质(已转换)
	 * @apiSuccess {String}sjlx											 事件类型(ZDSJLX)
	 * @apiSuccess {String}sjlxString											 事件类型(已转换)
	 * @apiSuccess {String}sgsj											 事故时间
	 * @apiSuccess {String}sgdd											 事故地点
	 * @apiSuccess {String}sjry											 涉及人员
	 * @apiSuccess {String}sjryString											 涉及人员(已转换)
	 * @apiSuccess {String}jyqk											 简要情况
	 * @apiSuccess {String}cljg											 处理结果
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
	 *         id: "11000011420191129000167"
	 *		   jsbh: "110000114"
	 *		   jsbhString: "北京市第一看守所"
	 *		   qklx: "03"
	 *		   qklxString: "重大事件"
	 *		   sglx: ""
	 *		   sglxString: ""
	 *		   sgxz: ""
	 *		   sgxzString: ""
	 *		   sjlx: "4"
	 *		   sjlxString: "在押人犯骚乱、劫持看守所干警或其他政法机关工作人员作人质"
	 *		   sgsj: "2019-11-19 09:32:41"
	 *		   sgdd: "12"
	 *		   sjry: "110000114201908190001,110000114201908190002,110000114201908200004"
	 *		   sjryString: "测试11号,一区啊啊啊啊,图片1,"
	 *		   jyqk: "12"
	 *		   cljg: "12"
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
	 *    "sj_start":"事故开始时间(非必填,格式(2019-12-12))",
	 *    "sj_end":"事故结束时间(非必填,格式(2019-12-12))",
	 *    "qklx":"情况类型(非必填;字典 QKLX)",
	 *    "sjlx":"事件类型(非必填;字典 SJLX)"
	 * }
	 */
    
    @OpenAPI
	@ApiOperation("发生事故查询")
	@GetMapping("/fssgQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> fssgQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/fssg/fssgQuery";
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
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("qklx"))) {
	            qParam.and("qklx", TermType.eq, maps.getResult().get("qklx"));
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sglx"))) {
	            qParam.and("sglx", TermType.eq, maps.getResult().get("sglx"));
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sjlx"))) {
	            qParam.and("sjlx", TermType.eq, maps.getResult().get("sjlx"));
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sgxz"))) {
	            qParam.and("sgxz", TermType.eq, maps.getResult().get("sgxz"));
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("cljg"))) {
	            qParam.and("cljg", TermType.like, "%"+maps.getResult().get("cljg")+"%");
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sgdd"))) {
	            qParam.and("sgdd", TermType.like, "%"+maps.getResult().get("sgdd")+"%");
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sjry"))) {
	            qParam.and("sjry", TermType.like, "%"+maps.getResult().get("sjry")+"%");
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sgsj_start"))) {
	            qParam.and("sgsj", TermType.gte, maps.getResult().get("sgsj_start"));
	        }
	        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sgsj_end"))) {
	            qParam.and("sgsj", TermType.lte, maps.getResult().get("sgsj_end"));
	        }
	        qParam.and("jsbh", jsbh);
	        ResponseMessage<PagerResult<FssgModel>> result=kssServerApis.fssgQueryForPage(qParam);
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
	 * @api {post} /v4/kss/fssg/fssgSave 
	 * @apiVersion 0.4.0
	 * @apiName fssgSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 发生事故保存
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
	 *      "sgsj":"所谓会议时间时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "qklx":"情况类型(必填; 字典QKLX)",
	 *      "sglx":"事故类型(必填;字典SGLX)",
	 *      "sgxz":"事故性质(必填;字典SGXZ)",
	 *      "sgdd":"事故地点(必填; 最大字段长度：30)",
	 *      "sjry":"涉及人员(非必填;保存人员变化,最大长度200)",
	 *      "jyqk":"简要情况(必填;最大长度200)",
	 *      "cljg":"处理结果(必填;最大长度200)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("发生事故保存")
	@PostMapping("fssgSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fssgSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/fssg/fssgSave";

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
			List<FssgModel> fssgModel = JSONArray.parseArray(map.get("entity").toString(), FssgModel.class);
			fssgModel.get(0).setState("R2");
			fssgModel.get(0).setJsbh(jsbh);
			fssgModel.get(0).setCreatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.fssgSave(fssgModel.get(0));
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
	 * @api {post} /v4/kss/fssg/fssgUpdate 
	 * @apiVersion 0.4.0
	 * @apiName fssgUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 发生事故修改
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
	 *      "sgsj":"所谓会议时间时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "qklx":"情况类型(必填; 字典QKLX)",
	 *      "sglx":"事故类型(必填;字典SGLX)",
	 *      "sgxz":"事故性质(必填;字典SGXZ)",
	 *      "sgdd":"事故地点(必填; 最大字段长度：30)",
	 *      "sjry":"涉及人员(非必填;保存人员变化,最大长度200)",
	 *      "jyqk":"简要情况(必填;最大长度200)",
	 *      "cljg":"处理结果(必填;最大长度200)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("发生事故修改")
	@PostMapping("fssgUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fssgUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/fssg/fssgUpdate";

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
			List<FssgModel> fssgModel = JSONArray.parseArray(map.get("entity").toString(), FssgModel.class);
			fssgModel.get(0).setState("R2");
			fssgModel.get(0).setJsbh(jsbh);
			fssgModel.get(0).setUpdatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.fssgUpdate(fssgModel.get(0).getId(),fssgModel.get(0));
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
