/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.GwjjbModel;
import awd.bj.kss.model.JbjlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JsbjdModel;
import awd.cloud.platform.model.kss.Kss_JsbjdModelDO;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/v4/kss/jsbjd")
@Api(tags = "kss-jsbjd",description = "Jsbjd") 
public class Kss_JsbjdController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
	private ProcessService processService;


	/**
	 * @api {post} /v4/kss/jsbjd/jsbjdList 精神病鉴定查询
	 * @apiVersion 0.4.0
	 * @apiName jsbjdList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 精神病鉴定查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id                                  				id
	 * @apiSuccess {String}jsbh                                				监所编号
	 * @apiSuccess {String}jsbhString                          				监所编号（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}sfsjsb											是否是精神病
	 * @apiSuccess {String}sfsjsbString										是否是精神病（已转换）
	 * @apiSuccess {String}ygyqx											原关押期限
	 * @apiSuccess {String}xgyqx											现关押期限
	 * @apiSuccess {String}ksrq												开始日期
	 * @apiSuccess {String}jsrq												结束日期
	 * @apiSuccess {String}jdjg												鉴定结果
	 * @apiSuccess {String}blr												办理人
	 * @apiSuccess {String}blsj											    办理时间
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}ywlcid											任务流程id
	 * @apiSuccess {String}jbxxSnbh											所内编号
	 * @apiSuccess {String}jbxxXm											姓名
	 * @apiSuccess {String}jbxxJsh											监室号
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result":
	 *    {
	 *     "total": 16,
	 *     "data": [{
	 *          "blr": "管理员",
	 *          "blsj": "2020-01-07 13:07:37",
	 *          "bz": "626",
	 *          "createtime": "2020-01-07 13:07:37",
	 *          "creator": "管理员",
	 *          "id": "11000011420200107000084",
	 *          "jbxxCsrq": "1976-06-10 00:00:00",
	 *          "jbxxHjd": "110000",
	 *          "jbxxJsh": "9101",
	 *          "jbxxRsrq": "2019-12-04 09:39:26",
	 *          "jbxxSnbh": "20190289",
	 *          "jbxxXm": "王成",
	 *          "jdjg": "有精神病",
	 *          "jsbh": "110000114",
	 *          "jsbhString": "北京市第一看守所",
	 *          "jsrq": "2020-01-07 00:00:00",
	 *          "ksrq": "2020-01-07 00:00:00",
	 *          "rybh": "110000114201912040005",
	 *          "sfsjsb": "1",
	 *          "state": "R2",
	 *          "stateString": "有效",
	 *          "updatetime": "2020-01-07 13:08:02",
	 *          "updator": "管理员",
	 *          "xgyqx": "2020-01-07 00:00:00",
	 *          "ygyqx": "2019-12-04 00:00:00",
	 *          "ywlcid": "5239460"
	 *        }],
	 *        "page": "1"
	 *        },
	 *   "status": 200,
	 *   "timestamp": 1576826568061
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "rybh":"310000111201906200004",
	 *          "sort":"id",
	 *          "order":"desc",
	 *          "page":"1",
	 *          "rows":"10"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("精神病鉴定查询")
	@PostMapping("/jsbjdList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> gwjjbList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"rybh":"310000111201906200004","page":"1","rows":"10","sort":"id","order":"desc"}
		//id,jsbh,jsbhString,rybh,sfsjsb,sfsjsbString,ygyqx,xgyqx,ksrq,jsrq,jdjg,blr,blsj,bz,state,stateString,creator,createtime,updator,updatetime,ywlcid,jbxxSnbh,jbxxXm,jbxxJsh
		String interfaceId = "/v4/kss/jsbjd/jsbjdList";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();
			String rybh = (String) maps.getResult().get("rybh");
			String state = (String) maps.getResult().get("state");
			if(!StringUtils.isNullOrEmpty(rybh)){
				qParam.and("flag", TermType.eq, rybh);
			}
			qParam.and("jsbh", TermType.eq, jsbh);
			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jsbjdQueryForPage(qParam);
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
	 * @api {post} /v4/kss/jsbjd/jsbjdAdd 精神病鉴定保存
	 * @apiVersion 0.4.0
	 * @apiName jsbjdAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 精神病鉴定保存.
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
	 *					"rybh":"人员编号(最大长度:18)",
	 *       			"sfsjsb":"是否是精神病(最大长度:1)",
	 *       		    "snbh":"所内编号(最大长度:8)",
	 *       		    "xm":"姓名(最大长度:30)",
	 *       		    "jsh":"监室号(最大长度:4)",
	 *       		    "ygyqx":"原关押期限(格式:yyyy-MM-dd hh:mm:ss)",
	 *       		    "blr":"办理人(必填;最大长度:30)",
	 *       		    "ksrq":"开始日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *					"blsj":"办理日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *					"bz":"备注()",
	 *					"createtime":"创建时间(格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "creator":"创建人(最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"blr":"\\S{1,30}","ksrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"rybh":"110000114201912040014","sfsjsb":"0","snbh":"20190298","xm":"123","jsh":"9009","ygyqx":"2020-01-22","ksrq":"2020-01-14 10:10:10","blr":"sss","blsj":"2020-01-14 10:10:10","bz":"","creator":"管理员","createtime":""}]}

	@ApiOperation("精神病鉴定保存")
	@PostMapping("/jsbjdAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jsbjdAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jsbjd/jsbjdAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_JsbjdModelDO jsbjdModel=JSONObject.parseObject(rslist.get(0).toString(),Kss_JsbjdModelDO.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String flowKey = CacheUtils.get().getFlowKey("kss_jsbjd".toUpperCase());
			String key = "kss_jsbjd".toUpperCase();
			if (processService.FlowMutex(jsbh, jsbjdModel.getRybh(), key, "JSBJD").getStatus() != 200) {
				return processService.FlowMutex(jsbh, jsbjdModel.getRybh(), key, "JSBJD");
			}
			jsbjdModel.setState("R2");
			jsbjdModel.setJsbh(jsbh);
			jsbjdModel.setCreatetime(new Date());
			jsbjdModel.setBlsj(new Date());

			ResponseMessage<String> result = kssServerApis.jsbjdAddflow(flowKey,jsbjdModel);
			if (result.getStatus()==200){
				return ResponseMessage.ok("保存成功");
			}else {
				return ResponseMessage.error("服务异常,保存失败!");
			}
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
	public ResponseMessage<PagerResult<Kss_JsbjdModel>> jsbjd_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JsbjdModel>> result= kssService.jsbjd_query(queryParam);
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
	public ResponseMessage<String> jsbjd_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JsbjdModel data) {
		return kssService.jsbjd_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jsbjd_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JsbjdModel data) {
		return kssService.jsbjd_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JsbjdModel> jsbjd_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jsbjd_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jsbjd_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jsbjd_deleteByKey(id);
	}
}
