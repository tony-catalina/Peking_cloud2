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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.QybzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_QybzModel;
import awd.cloud.platform.service.PublicService;
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
@RequestMapping("/v4/kss/qybz")
@Api(tags = "kss-qybz",description = "Qybz") 
public class Kss_QybzController extends PublicService{
	
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
	public ResponseMessage<PagerResult<Kss_QybzModel>> qybz_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_QybzModel>> result= kssService.qybz_query(queryParam);
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
	public ResponseMessage<String> qybz_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_QybzModel data) {
		return kssService.qybz_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> qybz_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_QybzModel data) {
		return kssService.qybz_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_QybzModel> qybz_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.qybz_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> qybz_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.qybz_deleteByKey(id);
	}
	
	/**
	 * @return
	 * @api {get} /v4/kss/kss_qybz/qybzQuery 权益保障查询
	 * @apiVersion 0.4.0
	 * @apiName qybzQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsh										                    监室号
	 * @apiSuccess {String}jq										                     监区
	 * @apiSuccess {String}bzlx										                    保障类型
	 * @apiSuccess {String}starttime										开始时间
	 * @apiSuccess {String}endtime											 结束内容
	 * @apiSuccess {String}dcmj										                    带出民警
	 * @apiSuccess {String}dcrs										                    带出人数
	 * @apiSuccess {String}dlr									                               带领人
	 * @apiSuccess {String}jtqk									                               具体情况
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
	 *         "jsh": "03",
	 *         "bzlx": "发饭",
	 *         "kssj": "2019-12-10 10:10:10",
	 *         "jssj": "2019-12-10 10:10:10",
	 *         "dcmj": "带出民警",
	 *         "dcrs":"0",
	 *         "dlr":"管理员",
	 *         "jtqk":"具体情况"
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
	 *    "dcmj":"带出民警(非必填)",
	 *    "dcsj_start":"带出开始时间(非必填,格式(2019-12-12 10:10:10))",
	 *    "dcsj_end":"带出结束时间(非必填,格式(2019-12-12 10:10:10))",
	 *    "jsh":"监室号(非必填)",
	 * }
	 */
	@OpenAPI
    @ApiOperation("权益保障查询")
    @GetMapping("/qybzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> qybz_Query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/kss_qybz/qybzQuery";
        String state = "R2";
        //通过校验获取查询参数
        try {
        	ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
          //查询参数
            QueryParam queryParam = new QueryParam();
            String dcmj = maps.getResult().get("dcmj").toString();
            if (!StringUtils.isNullOrEmpty(dcmj)){
                queryParam.and("dcmj",TermType.like,"%"+dcmj+"%");
            }
            
            String dcsj_start = maps.getResult().get("StartTime").toString();
            if (!StringUtils.isNullOrEmpty(dcsj_start)){
                queryParam.and("kssj",TermType.gte,dcsj_start);
            }

            String dcsj_end = maps.getResult().get("EndTime").toString();
            if (!StringUtils.isNullOrEmpty(dcsj_end)){
                queryParam.and("jssj",TermType.lte,dcsj_end+" 23:59:59");
            }
            
            String bzlx = maps.getResult().get("bzlx").toString();
            if (!StringUtils.isNullOrEmpty(bzlx)){
            	queryParam.and("bzlx",TermType.eq,bzlx);
            }
            String jsh = maps.getResult().get("jsh").toString();
            if (!StringUtils.isNullOrEmpty(jsh)){
            	queryParam.and("jsh",TermType.like,"%"+jsh+"%");
            }
            String jq = maps.getResult().get("jq").toString();
            if (!StringUtils.isNullOrEmpty(jq)){
            	queryParam.and("jq",TermType.like,jq+"%");
            }
            queryParam.and("state", state);
            ResponseMessage<PagerResult<QybzModel>> result = kssServerApis.qybzQueryForPage(queryParam);
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
		 * @api {post} /v4/kss/qybz/ffQybzSave 权益保障放风保存
		 * @apiVersion 0.4.0
		 * @apiName ffQybzSave
		 * @apiGroup g_kss
		 * @apiPermission any
		 * @apiDescription 权益保障放风保存
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
		 *      "bzlx":"保障类型(必填;最大字段长度：20)",
		 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
		 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
		 *      "jsh":"监室号(必填; 最大字段长度：4)",
		 *      "dcmj":"带出民警(必填;最大长度30)",
		 *      "dd":"带出地点(必填;最大长度20)",
		 *      "bz":"备注(必填;最大长度200)"
		 *   }]
		 * }
		 *
		 */
	@ApiOperation("权益保障放风保存")
	@PostMapping("ffQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ffQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/ffQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
	
	
	/**
	 * @api {post} /v4/kss/qybz/xzQybzSave 权益保障洗澡保存
	 * @apiVersion 0.4.0
	 * @apiName xzQybzSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障洗澡保存
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
	 *      "bzlx":"保障类型(必填;最大字段长度：20)",
	 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jsh":"监室号(必填; 最大字段长度：4)",
	 *      "dcmj":"带出民警(必填;最大长度30)",
	 *      "dd":"带出地点(必填;最大长度20)",
	 *      "bz":"备注(必填;最大长度200)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("权益保障洗澡保存")
	@PostMapping("xzQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xzQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/xzQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
	
	
	/**
	 * @api {post} /v4/kss/qybz/lfQybzSave 权益保障理发保存
	 * @apiVersion 0.4.0
	 * @apiName lfQybzSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障理发保存
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
	 *      "bzlx":"保障类型(必填;最大字段长度：20)",
	 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jsh":"监室号(必填; 最大字段长度：4)",
	 *      "dcmj":"带出民警(必填;最大长度30)",
	 *      "dd":"带出地点(必填;最大长度20)",
	 *      "bz":"备注(必填;最大长度200)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("权益保障理发保存")
	@PostMapping("lfQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lfQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/lfQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
	
	/**
	 * @api {post} /v4/kss/qybz/fsQybzSave 权益保障发水保存
	 * @apiVersion 0.4.0
	 * @apiName fsQybzSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障发水保存
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
	 *      "bzlx":"保障类型(必填;最大字段长度：20)",
	 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jsh":"监室号(必填; 最大字段长度：4)",
	 *      "dcmj":"带出民警(必填;最大长度30)",
	 *      "dd":"带出地点(必填;最大长度20)",
	 *      "bz":"备注(必填;最大长度200)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("权益保障发水保存")
	@PostMapping("fsQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fsQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/fsQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
	
	
	/**
	 * @api {post} /v4/kss/qybz/jjwpffQybzSave 权益保障接济物品发放保存
	 * @apiVersion 0.4.0
	 * @apiName jjwpffQybzSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障接济物品发放保存
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
	 *      "bzlx":"保障类型(必填;最大字段长度：20)",
	 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jsh":"监室号(必填; 最大字段长度：4)",
	 *      "dcmj":"带出民警(必填;最大长度30)",
	 *      "dd":"带出地点(必填;最大长度20)",
	 *      "bz":"备注(必填;最大长度200)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("权益保障接济物品发放保存")
	@PostMapping("jjwpffQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jjwpffQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/jjwpffQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
	
	
	/**
	 * @api {post} /v4/kss/qybz/dllsyfbrQybzSave 权益保障带领晾晒衣服被褥保存
	 * @apiVersion 0.4.0
	 * @apiName dllsyfbrQybzSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 权益保障带领晾晒衣服被褥保存
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
	 *      "bzlx":"保障类型(必填;最大字段长度：20)",
	 *      "kssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jssj":"开始时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "jsh":"监室号(必填; 最大字段长度：4)",
	 *      "dcmj":"带出民警(必填;最大长度30)",
	 *      "dd":"带出地点(必填;最大长度20)",
	 *      "bz":"备注(必填;最大长度200)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("权益保障带领晾晒衣服被褥保存")
	@PostMapping("dllsyfbrQybzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> dllsyfbrQybz_Save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		String interfaceId = "/v4/kss/qybz/dllsyfbrQybzSave";
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
			List<QybzModel> qybzModel = JSONArray.parseArray(map.get("entity").toString(), QybzModel.class);
			qybzModel.get(0).setState("R2");
            qybzModel.get(0).setJsbh(jsbh);
            qybzModel.get(0).setCreator(maps.getResult().get("creator").toString());
            qybzModel.get(0).setCreatetime(new Date());
            ResponseMessage<Integer> num = kssServerApis.qybzSaves(qybzModel.get(0));
            if (1 == num.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.ok("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseMessage.ok("保存成功");
	}
}
