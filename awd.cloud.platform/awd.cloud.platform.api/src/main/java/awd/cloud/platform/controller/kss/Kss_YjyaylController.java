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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.YjyaylModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
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
@RequestMapping("/v4/kss/yjyayl")
@Api(tags = "kss-yjyayl",description = "Yjyayl") 
public class Kss_YjyaylController extends PublicService{
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;
	
	
	/**
	 * @return
	 * @api {get} /v4/kss/yjyayl/yjyaylQuery 应急演练查询
	 * @apiVersion 0.4.0
	 * @apiName yjyaylQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 应急演练查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}ksscjry										            看守所参加人数
	 * @apiSuccess {String}qtry										                        其它人员
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态(已转换)
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}updator										            更新人
	 * @apiSuccess {String}ylqk												演练情况
	 * @apiSuccess {String}ylsj	 											演练时间
	 * @apiSuccess {String}zzzhz											组织指挥者
	 * @apiSuccess {String}hyjlr											会议记录人
	 * @apiSuccess {String}gjcs	 											改进措施
	 * @apiSuccess {String}czwt												存在的问题
	 * @apiSuccess {String}creator  								 		创建人
	 * @apiSuccess {String}createtime  										创建时间
	 * @apiSuccess {String}bz												备注						                  
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
	 *         "id": "11000011420191230000077",
	 *         "jsbh": "110000114",
	 *         "jsbhString": "北京市第一看守所",
	 *         "ksscjry": "打,地方",
	 *         "qtry": "dfsd,sdfas",
	 *         "state": "R2",
	 *         "stateString": "有效",
	 *         "updatetime": "2019-12-10 10:10:10",
	 *         "updator":"管理员",
	 *         "ylqk":"ddd",
	 *         "ylsj":"2019-12-10 10:10:10",
	 *         "zzzhz":"asdf",
	 *         "hyjlr":"管理员",
	 *         "gjcs":"sdf",
	 *         "czwt":"sdfasdf",
	 *         "creator":"管理员",
	 *         "createtime":"2019-12-10 10:10:10",
	 *         "bz":"sdfasdfasdfadsfds"
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
	 *    "rybh":"人员编号(最大字段长度：21)",
	 * }
	 */
	@OpenAPI
	@ApiOperation("应急演练查询")
	@GetMapping("/YjyaylQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> Yjyayl_Query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/yjyayl/yjyaylQuery";
		String state = "R2";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam queryParam = new QueryParam();
			String ylsj_start = maps.getResult().get("ylsj_start").toString();
	    	String ylsj_end = maps.getResult().get("ylsj_end").toString();
	    	if(!StringUtils.isNullOrEmpty(maps.getResult().get("ylsj_start"))) {
	    		ylsj_start=maps.getResult().get("ylsj_start")+" 00:00:00";
	    	}
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("ylsj_end"))) {
	        	ylsj_end=maps.getResult().get("ylsj_end")+" 23:59:59";
	        }
	        //String state = request.getParameter("state");
	        DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
	        queryParam.and("ylsj",TermType.gte,ylsj_start);
	        queryParam.and("ylsj",TermType.lte,ylsj_end);
	        queryParam.and("jsbh",TermType.eq,jsbh);
	        ResponseMessage<PagerResult<YjyaylModel>> result = kssServerApis.yjyaylQueryForPage(queryParam);
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
			// TODO: handle exception
			return ResponseMessage.error("查询失败");
		}
	}
	
	
	/**
	 * @api {post} /v4/kss/yjyayl/yjyaylSave 应急演练保存
	 * @apiVersion 0.4.0
	 * @apiName yjyaylSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 应急演练保存
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
	 *      "ylsj":"演练时间((必填; 格式(2019-10-10 10:10:10)))",
	 *      "zzzhz":"组织指挥者(必填; 最大字段长度：30)",
	 *      "hyjlr":"会议记录人(必填; 最大字段长度：30)",
	 *      "ksscjry":"看守所参加人员(最大字段长度：200)",
	 *      "wjzdcjry":"武警中队参加人员(最大长度200)",
	 *      "ylqk":"演练情况(必填)",
	 *      "qtry":"其它人员(最大字段长度：200)",
	 *      "czwt":"存在问题",
	 *      "gjcs":"改进措施",
	 *      "bz":"备注"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("应急演练保存")
	@PostMapping("yjyaylSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yjyayl_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/yjyayl/yjyaylSave";
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
			List<YjyaylModel> yjyaylModel = JSONArray.parseArray(map.get("entity").toString(), YjyaylModel.class);
			yjyaylModel.get(0).setState("R2");
            yjyaylModel.get(0).setJsbh(jsbh);
            yjyaylModel.get(0).setCreator(maps.getResult().get("creator").toString());
            yjyaylModel.get(0).setCreatetime(new Date());
            
            ResponseMessage<String> result= kssServerApis.yjyaylSave(yjyaylModel.get(0));
            if(result.getStatus() == 200){
            	result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessage.error("保存失败！");
		}
	}
	
	/**
	 * @api {post} /v4/kss/yjyayl/yjyaylUpdate 应急演练修改
	 * @apiVersion 0.4.0
	 * @apiName yjyaylUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 应急演练修改
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
	 *      "ylsj":"演练时间((必填; 格式(2019-10-10 10:10:10)))",
	 *      "zzzhz":"组织指挥者(必填; 最大字段长度：30)",
	 *      "hyjlr":"会议记录人(必填; 最大字段长度：30)",
	 *      "ksscjry":"看守所参加人员(最大字段长度：200)",
	 *      "wjzdcjry":"武警中队参加人员(最大长度200)",
	 *      "ylqk":"演练情况(必填)",
	 *      "qtry":"其它人员(最大字段长度：200)",
	 *      "czwt":"存在问题",
	 *      "gjcs":"改进措施",
	 *      "bz":"备注"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("应急演练修改")
	@PostMapping("yjyaylUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yjyayl_Update(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/yjyayl/yjyaylUpdate";
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
			List<YjyaylModel> yjyaylModel = JSONArray.parseArray(map.get("entity").toString(), YjyaylModel.class);
			yjyaylModel.get(0).setUpdatetime(new Date());
            yjyaylModel.get(0).setUpdator(maps.getResult().get("updator").toString());
            yjyaylModel.get(0).setJsbh(jsbh);
            String result= kssServerApis.yjyaylUpdate(yjyaylModel.get(0).getId(), yjyaylModel.get(0));
			return ResponseMessage.ok(result);
		} catch (Exception e) {
			return ResponseMessage.error("修改失败！");
		}
	}
}
