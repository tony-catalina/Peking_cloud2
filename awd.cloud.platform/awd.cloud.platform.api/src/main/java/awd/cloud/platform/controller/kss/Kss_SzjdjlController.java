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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.SzjdjlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_SzjdjlModel;
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
@RequestMapping("/v4/kss/szjdjl")
@Api(tags = "kss-szjdjl",description = "Szjdjl") 
public class Kss_SzjdjlController extends PublicService{
	
	@Autowired
	private KssServerApis kssServerApis;
	
	/**
	 * @return
	 * @api {get} /v4/kss/szjdjl/szjdjlQuery 所长接待记录查询
	 * @apiVersion 0.4.0
	 * @apiName szjdjlQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 所长接待记录查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称
	 * @apiSuccess {String}sldxm										          所领导姓名
	 * @apiSuccess {String}jdsj										                    接待时间
	 * @apiSuccess {String}zlfxm											 主要来访人姓名
	 * @apiSuccess {String}lfxb											 主要来访人性别
	 * @apiSuccess {String}lfxbString											 主要来访人性别(已转换)
	 * @apiSuccess {String}lfnl											 主要来访人年龄
	 * @apiSuccess {String}lfgzdw											 主要来访人工作单位
	 * @apiSuccess {String}lxfs											 联系方式
	 * @apiSuccess {String}fywt											 反映问题
	 * @apiSuccess {String}dfyj											 答复意见
	 * @apiSuccess {String}clqk											 处理情况
	 * @apiSuccess {String}bz											 备注
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
	 *         id: "11000011420200215000063"
	 *		   jsbh: "110000114"
	 *		   jsbhString: "北京市第一看守所"
	 *		   sldxm: "123"
	 *		   jdsj: "2020-02-15 12:13:01"
	 *		   zlfxm: "324"
	 *		   lfxb: "1"
	 *		   lfxbString: "男性"
	 *		   lfnl: "23"
	 *		   lfgzdw: "23"
	 *		   lxfs: "13222753885"
	 *		   fywt: "23"
	 * 		   dfyj: "2323"
	 *		   clqk: "232"
	 *		   bz: "323"
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
	 *    "sj_start":"接待开始时间(非必填,格式(2019-12-12))",
	 *    "sj_end":"接待结束时间(非必填,格式(2019-12-12))",
	 * }
	 */
    
    @OpenAPI
	@ApiOperation("所长接待记录查询")
	@GetMapping("/szjdjlQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> szjdjlQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/szjdjl/szjdjlQuery";
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
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_start"))) {
	        	qParam.and("jdsj", TermType.gte, maps.getResult().get("sj_start")+" 00:00:00");
	        }
	        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_end"))) {
	        	qParam.and("jdsj", TermType.lte, maps.getResult().get("sj_end")+" 23:59:59");
	        }
	        qParam.and("jsbh", jsbh);
	        ResponseMessage<PagerResult<SzjdjlModel>> result=kssServerApis.szjdjlQueryForPage(qParam);
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
	 * @api {post} /v4/kss/szjdjl/szjdjlSave 
	 * @apiVersion 0.4.0
	 * @apiName szjdjlSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 所长接待记录保存
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
	 *      "jdsj":"来访时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "sldxm":"所领导姓名(必填; 最大字段长度：30)",
	 *      "zlfxm":"主要来访人姓名(必填;最大字段长度：30)",
	 *      "lfxb":"主要来访人性别(必填;最大字段长度：1)",
	 *      "lfnl":"来访人年龄(必填; 最大字段长度：3)",
	 *      "lxfs":"联系方式(必填; 最大字段长度：15)",
	 *      "lfgzdw":"来访人员工作单位(非必填; 最大字段长度：30)",
	 *      "fywt":"反映问题(必填;)",
	 *      "dfyj":"当时答复意见(必填;)",
	 *      "clqk":"事后处理情况(必填;)",
	 *      "bz":"备注(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("所长接待记录保存")
	@PostMapping("szjdjlSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> szjdjlSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/szjdjl/szjdjlSave";

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
			List<SzjdjlModel> szjdjlModel = JSONArray.parseArray(map.get("entity").toString(), SzjdjlModel.class);
			szjdjlModel.get(0).setState("R2");
			szjdjlModel.get(0).setJsbh(jsbh);
			szjdjlModel.get(0).setCreatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.szjdjlSave(szjdjlModel.get(0));
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
	 * @api {post} /v4/kss/szjdjl/szjdjlUpdate 
	 * @apiVersion 0.4.0
	 * @apiName szjdjlUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 所长接待记录修改
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
	 *      "jdsj":"来访时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "sldxm":"所领导姓名(必填; 最大字段长度：30)",
	 *      "zlfxm":"主要来访人姓名(必填;最大字段长度：30)",
	 *      "lfxb":"主要来访人性别(必填;最大字段长度：1)",
	 *      "lfnl":"来访人年龄(必填; 最大字段长度：3)",
	 *      "lxfs":"联系方式(必填; 最大字段长度：15)",
	 *      "lfgzdw":"来访人员工作单位(非必填; 最大字段长度：30)",
	 *      "fywt":"反映问题(必填;)",
	 *      "dfyj":"当时答复意见(必填;)",
	 *      "clqk":"事后处理情况(必填;)",
	 *      "bz":"备注(非必填;)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("所长接待记录修改")
	@PostMapping("szjdjlUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> szjdjlUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/szjdjl/szjdjlUpdate";

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
			List<SzjdjlModel> szjdjlModel = JSONArray.parseArray(map.get("entity").toString(), SzjdjlModel.class);
			szjdjlModel.get(0).setState("R2");
			szjdjlModel.get(0).setJsbh(jsbh);
			szjdjlModel.get(0).setUpdatetime(new Date());
			ResponseMessage<String> jsswsq = kssServerApis.szjdjlUpdate(szjdjlModel.get(0).getId(),szjdjlModel.get(0));
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
