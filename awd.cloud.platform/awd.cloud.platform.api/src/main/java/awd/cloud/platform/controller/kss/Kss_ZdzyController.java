/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.ZdzyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_ZdzyModel;
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
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/zdzy")
@Api(tags = "kss-zdzy",description = "Zdzy") 
public class Kss_ZdzyController extends PublicService {
	
	@Autowired
    private KssService kssService;

    @Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {post} /v4/kss/zdzy/zdzyAdd 重点人员登记保存操作
	 * @apiVersion 0.4.0
	 * @apiName zdzyAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 重点人员登记保存操作
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
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
	 *
	 *   "appcode":"应用代码(必填)",
	 *   "jsbh":"监所编号(必填; 最大字段长度:9)",
	 *   json{
	 *      "entity":[{
	 *       "id":"id(最大字段长度:23)",
	 *       "rybh":"人员编号(必填；最大字段长度:21)",
	 *       "snbh":"所内编号(必填；最大字段长度:8)",
	 *       "xm":"姓名(必填；最大字段长度:50)",
	 *       "jsh":"监室号(必填；最大字段长度:4)",
	 *       "jbr":"经办人(必填；最大字段长度:50)",
	 *       "blrq":"办理日期(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *       "bllx":"办理类型(必填；字典：JJBLLX；最大字段长度:1)",
	 *       "ly":"列/撤控理由(必填；最大字段长度:200)",
	 *       "kzcs":"控制措施(必填；最大字段长度:200)",
	 *       "bz":"备注",
	 *       "fzss":"犯罪事实(最大字段长度:200)"，
	 *       "creator":"创建人(必填；最大字段长度:50)""
	 *   }
	 *
	 */
	//{"creator":".{1,50}","fzss":".{0,200}","kzcs":".{1,200}","ly":".{1,200}","bllx":"\\d{1,1}","blrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbr":".{1,30}","xm":".{1,50}","snbh":"\\d{1,8}","rybh":"\\d{1,21}","id":"\\d{0,23}"}
	@ApiOperation("重点人员登记保存操作")
	@PostMapping("/zdzyAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdzyAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/zdzy/zdzyAdd";
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
			System.err.println(map.get("entity").toString());


			ZdzyModel zdzyModel = JSONArray.parseArray(map.get("entity").toString(), ZdzyModel.class).get(0);


			if(StringUtils.isNullOrEmpty(zdzyModel.getBz())) {
				return ResponseMessage.error("请输入备注");
			}

			zdzyModel.setState("R2");
			zdzyModel.setJsbh(jsbh);
			//	zdzyModel.setCreator(users.getUsername());
			zdzyModel.setCreatetime(new Date());
			System.err.println("重点在押新增："+ JSONObject.toJSONString(zdzyModel));
			ResponseMessage<String> result = kssServerApis.zdzySave(zdzyModel);
			System.err.println("result--" + JSON.toJSONString(result));

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
	 *
	 * @api {get} /v4/kss/zdzy/zdzyjlList 重点在押记录
	 * @apiVersion 0.4.0
	 * @apiName zdzyjlList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 重点在押记录
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}fzss												犯罪事实
	 * @apiSuccess {String}ly												理由
	 * @apiSuccess {String}lyString											理由（已转换）
	 * @apiSuccess {String}kzcs												控制措施
	 * @apiSuccess {String}kzcsString										控制措施（已转换）
	 * @apiSuccess {Datetime}blrq											办理日期
	 * @apiSuccess {String}bllx												办理类型
	 * @apiSuccess {Datetime}jzrq											截止日期
	 * @apiSuccess {String}jbr												经办人
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {Datetime}zdzpssj										中队长批示时间
	 * @apiSuccess {String}zdzpsbz											中队长批示备注
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}ldpsbz											领导批示备注
	 * @apiSuccess {Datetime}ldpssj											领导批示时间
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {Datetime}createtime										创建时间
	 * @apiSuccess {String}updator											跟新人
	 * @apiSuccess {Datetime}updatetime										跟新时间
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
	 * {
	 *      "id": "11000011420191230000119",
	 * 		"jsbh": "110000114",
	 * 		"jsbhString": "北京市第一看守所",
	 * 		"rybh": "110000114201912190001",
	 * 		"fzss": "",
	 * 		"ly": "15",
	 * 		"lyString": "情绪不稳可能危及安全的。",
	 * 		"kzcs": "3",
	 * 		"kzcsString": "监室夜间安排值班",
	 * 		"blrq": "2019-12-30 13:11:49",
	 * 		"bllx": "2",
	 * 		"jzrq": null,
	 * 		"jbr": "管理员",
	 * 		"zdzxm": null,
	 * 		"zdzpssj": null,
	 * 		"zdzpsbz": null,
	 * 		"zdzyj": "",
	 * 		"ldxm": null,
	 * 		"ldpsbz": null,
	 * 		"ldpssj": null,
	 * 		"ldyj": "",
	 * 		"bz": "啊实打实",
	 * 		"state": "R2",
	 * 		"stateString": "有效",
	 * 		"creator": "管理员",
	 * 		"createtime": "2019-12-30 13:12:00",
	 * 		"updator": "",
	 * 		"updatetime": null
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
	@ApiOperation("重点在押记录")
	@GetMapping("/zdzyjlList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> zdzyjlList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/zdzy/zdzyjlList";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				qParam.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				qParam.and("rybh",TermType.eq, maps.getResult().get("rybh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.zdzyQueryForPage(qParam);

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


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_ZdzyModel>> zdzy_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_ZdzyModel>> result= kssService.zdzy_query(queryParam);
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
	public ResponseMessage<String> zdzy_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZdzyModel data) {
		return kssService.zdzy_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zdzy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZdzyModel data) {
		return kssService.zdzy_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_ZdzyModel> zdzy_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zdzy_getByKey(id);
	}
	
	
	
	@OpenAPI
	public ResponseMessage<Integer> zdzy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zdzy_deleteByKey(id);
	}
}
