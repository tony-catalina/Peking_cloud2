/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JkfjlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JkfjlModel;
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
@RequestMapping("/v4/kss/jkfjl")
@Api(tags = "kss-jkfjl",description = "Jkfjl") 
public class Kss_JkfjlController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;
	
	@Autowired
    private KssService kssService;


	/**
	 * @api {post} /v4/kss/jkfjl/jkfjlAdd 加扣分保存
	 * @apiVersion 0.4.0
	 * @apiName jkfjlAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 加扣分保存
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
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
	 *       "jbr":"经办人(必填；最大字段长度:50)",
	 *       "creator":"创建人(必填；最大字段长度:50)",
	 *       "jkfz":"加扣分分值(必填；最大字段长度:5)",
	 *       "szrq":"设置日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *       "xm":"姓名(必填；最大字段长度:50)",
	 *       "snbh":"所内编号(必填；最大字段长度:8)",
	 *       "jsh":"监室号(必填；最大字段长度:4)",
	 *       "jkfsy":"加扣分事由(必填；最大字段长度:100)",
	 *       "gjyj":"管教意见(必填；最大字段长度:200)",
	 *       "bz":"备注",
	 *       "rybh":"人员编号(必填；最大字段长度:21)"
	 *   }
	 * @return
	 */
	//{"jbr":".{1,50}","creator":".{1,50}","jkfz":"\\d{1,5}","szrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xm":".{1,50}","snbh":"\\d{1,8}","jsh":"\\d{1,4}","jkfsy":".{1,100}","gjyj":".{1,200}","rybh":"\\d{1,21}"}
	@ApiOperation("加扣分保存")
	@PostMapping("/jkfjlAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkfjlAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/jkfjl/jkfjlAdd";
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


			JkfjlModel jkfjlModel = JSONArray.parseArray(map.get("entity").toString(), JkfjlModel.class).get(0);
	//		JbxxModel jbxxModel = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class).get(0);
			jkfjlModel.setState("R2");
			jkfjlModel.setJsbh(jsbh);
	//		jkfjlModel.setJbr(users.getUsername());
	//		jkfjlModel.setCreator(users.getUsername());
			jkfjlModel.setCreatetime(new Date());
			jkfjlModel.setPsbz("1");
	//		jkfjlModel.setJkfz(request.getParameter("jkfz"));
	//		String szrq=request.getParameter("szrq");
	//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//		jkfjlModel.setSzrq(sdf.parse(szrq));

	//		jbxxModel.setXm(request.getParameter("xm"));
	//		jbxxModel.setSnbh(request.getParameter("snbh"));
			Double jkfz = Double.parseDouble(jkfjlModel.getJkfz());
			//根据前台传入的分值进行判断是加分还是减分
			String type = jkfz >= 0 ? "1" : "0";
			jkfjlModel.setType(type);
			System.err.println("***********邹嘉**********");
			System.err.println("保存成功");
			ResponseMessage<String> result = kssServerApis.jkfjlSave(jkfjlModel);

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
	 * @return
	 * @api {get} /v4/kss/jkfjl/jkfjlList  加扣分记录
	 * @apiVersion 0.4.0
	 * @apiName jkfjlList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 加扣分记录
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}type												类别
	 * @apiSuccess {String}typeString										类别（已转换）
	 * @apiSuccess {String}jkffs											理由（已转换）
	 * @apiSuccess {String}jkffsString										加扣分方式: (JKFFS)
	 * @apiSuccess {String}jkfz										        加扣分方式: (JKFFS)（已转换）
	 * @apiSuccess {String}jkfsy											加扣分事由
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {Datetime}createtime										创建时间
	 * @apiSuccess {String}scbz										        上传标志
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}ldyj											    领导意见
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}psbz											    批示标志(PSBZ)
	 * @apiSuccess {String}psbzString										批示标志(PSBZ)（已转换）
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}updator										    跟新人
	 * @apiSuccess {Datetime}updatetime										跟新时间
	 * @apiSuccess {String}jsh										        监室号
	 * @apiSuccess {String}ly											    理由
	 * @apiSuccess {String}lyString									     	理由（已转换）
	 * @apiSuccess {String}jbr                                              经办人
	 * @apiSuccess {Datetime}szrq										    设置日期
	 * @apiSuccess {String}gjxm										        管教姓名
	 * @apiSuccess {Datetime}gjpssj										    管教批示时间
	 * @apiSuccess {String}qtqk
	 * @apiSuccess {Datetime}zdzpssj										中队长批示时间
	 * @apiSuccess {String}jkfsyString										加扣分事由（已转换）
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
	 *      "jkffsString": null,
	 *         "gjyj": "十大高手",
	 *         "zdzyj": null,
	 *         "jkfsyString": "与他人聊天",
	 *         "qtqk": null,
	 *         "ywlcid": null,
	 *         "psbzString": "批示通过",
	 *         "stateString": "有效",
	 *         "type": "1",
	 *         "ly": null,
	 *         "szrq": "2019-08-14 19:28:43",
	 *         "ldpssj": null,
	 *         "jkffs": null,
	 *         "typeString": "加分",
	 *         "bz": null,
	 *         "id": "11000011420190814000030",
	 *         "state": "R2",
	 *         "jsbhString": "北京市第一看守所",
	 *         "scbz": null,
	 *         "ldxm": null,
	 *         "createtime": "2019-08-14 19:29:08",
	 *         "creator": "李四",
	 *         "zdzpssj": null,
	 *         "zdzxm": null,
	 *         "jkfz": "2",
	 *         "gjxm": null,
	 *         "jkfsy": "0011100004",
	 *         "psbz": "1",
	 *         "gjpssj": null,
	 *         "lyString": null,
	 *         "rybh": "110000111201907150009",
	 *         "updator": null,
	 *         "ldyj": null,
	 *         "jbr": "管理员",
	 *         "rybhString": null,
	 *         "updatetime": null,
	 *         "jsbh": "110000114",
	 *         "jsh": "0104"
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
	@ApiOperation("加扣分记录")
	@GetMapping("/jkfjlList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jkfjlList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/jkfjl/jkfjlList";
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
				qParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jkfjlQueryForPage(qParam);

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
	public ResponseMessage<PagerResult<Kss_JkfjlModel>> jkfjl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JkfjlModel>> result= kssService.jkfjl_query(queryParam);
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
	public ResponseMessage<String> jkfjl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkfjlModel data) {
		return kssService.jkfjl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkfjl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkfjlModel data) {
		return kssService.jkfjl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JkfjlModel> jkfjl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkfjl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jkfjl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkfjl_deleteByKey(id);
	}
}
