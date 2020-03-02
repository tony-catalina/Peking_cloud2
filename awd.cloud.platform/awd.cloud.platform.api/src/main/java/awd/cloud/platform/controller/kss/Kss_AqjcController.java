/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.AqjcModel;
import awd.bj.kss.model.ZyrybgclModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_AqjcModel;
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
@RequestMapping("/v4/kss/aqjc")
@Api(tags = "kss-aqjc",description = "Aqjc")
public class Kss_AqjcController extends PublicService{
	@Autowired
	private KssServerApis kssServerApis;
	
	/**
	 * @return
	 * @api {get} /v4/kss/aqjc/aqjcQuery 安全检查查询
	 * @apiVersion 0.4.0
	 * @apiName aqjcQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 安全检查查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}jcxs										                    检查形式
	 * @apiSuccess {String}ksscjrs										          看守所参加人数
	 * @apiSuccess {String}wjcjrs											 武警参加人数
	 * @apiSuccess {String}jskcjrs											 监所科参加人数
	 * @apiSuccess {String}sj										                     检查时间
	 * @apiSuccess {String}fzr										                     负责人
	 * @apiSuccess {String}zsjcry										           住所检查人员
	 * @apiSuccess {String}nr										                     检查内容
	 * @apiSuccess {String}jcjg										                     检查结果
	 * @apiSuccess {String}cljg										                     处理结果
	 * @apiSuccess {String}bz										                     备注
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
	 *         "id": "111",
	 *         "jsbh": "110000114",
	 *         "jsbhString": "北京市一看",
	 *         "jcxs": "111111",
	 *         "ksscjrs": "1",
	 *         "wjcjrs": "1",
	 *         "jskcjrs": "1",
	 *         "sj":"2020-02-14 14:19:02",
	 *         "fzr":"负责人",
	 *         "zsjcry": "1",
	 *         "nr": "内容",
	 *         "jcjg": "结果",
	 *         "cljg":"结果",
	 *         "bz":"备注",
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
	@ApiOperation("安全检查查询")
	@GetMapping("/aqjcQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> aqjcQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/aqjc/aqjcQuery";
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

	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jcxs"))) {
	        	qParam.and("jcxs", TermType.like, "%"+maps.getResult().get("jcxs").toString().trim()+"%");
	        }
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_start"))) {
	        	qParam.and("sj", TermType.gte, maps.getResult().get("sj_start")+" 00:00:00");
	        }
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_end"))) {
	        	qParam.and("sj", TermType.lte, maps.getResult().get("sj_end")+" 23:59:59");
	        }
	        qParam.and("jsbh", jsbh);
	        ResponseMessage<PagerResult<AqjcModel>> result=kssServerApis.aqjcQueryForPage(qParam);
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
	 * @api {post} /v4/kss/aqjc/aqjcSave 
	 * @apiVersion 0.4.0
	 * @apiName aqjcSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 安全检查保存
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
	 *      "sj":"检查时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "ksscjrs":"看守所参加人数(非必填; 最大字段长度：2)",
	 *      "wjcjrs":"武警参加人数(非必填; 最大字段长度：2)",
	 *      "jskcjrs":"监所科参加人数(非必填;最大长度2)",
	 *      "fzr":"报告负责人(必填;最大字段长度：30)",
	 *      "zsjcry":"住所检查人员(非必填;最大字段长度：30)",
	 *      "jcxs":"检查形式(必填;最大字段长度：200)",
	 *       "nr":"内容(必填;)",
	 *      "bz":"备注(非必填;)",
	 *      "jcjg":"检查结果(必填;)",
	 *      "cljg":"处理结果(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("安全检查保存")
	@PostMapping("aqjcSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> aqjcSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/aqjc/aqjcSave";

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
			List<AqjcModel> aqjcModel = JSONArray.parseArray(map.get("entity").toString(), AqjcModel.class);
			aqjcModel.get(0).setState("R2");
			aqjcModel.get(0).setFw("监区外部");
			aqjcModel.get(0).setJsbh(jsbh);
			aqjcModel.get(0).setCreatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.aqjcSave(aqjcModel.get(0));
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
	 * @api {post} /v4/kss/aqjc/aqjcUpdate 
	 * @apiVersion 0.4.0
	 * @apiName aqjcUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 安全检查修改
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
	 *      "sj":"检查时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "ksscjrs":"看守所参加人数(非必填; 最大字段长度：2)",
	 *      "wjcjrs":"武警参加人数(非必填; 最大字段长度：2)",
	 *      "jskcjrs":"监所科参加人数(非必填;最大长度2)",
	 *      "fzr":"报告负责人(必填;最大字段长度：30)",
	 *      "zsjcry":"住所检查人员(非必填;最大字段长度：30)",
	 *      "jcxs":"检查形式(必填;最大字段长度：200)",
	 *      "nr":"内容(必填;)",
	 *      "bz":"备注(非必填;)",
	 *      "jcjg":"检查结果(必填;)",
	 *      "cljg":"处理结果(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("安全检查修改")
	@PostMapping("aqjcUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> aqjcUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/aqjc/aqjcUpdate";

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
			List<AqjcModel> aqjcModel = JSONArray.parseArray(map.get("entity").toString(), AqjcModel.class);
			aqjcModel.get(0).setState("R2");
			aqjcModel.get(0).setJsbh(jsbh);
			aqjcModel.get(0).setUpdatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.aqjcUpdate(aqjcModel.get(0).getId(),aqjcModel.get(0));
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
