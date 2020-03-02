/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import awd.bj.kss.model.ShgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.entity.JsswEntity;
import awd.cloud.platform.model.kss.GxswModel;
import awd.cloud.platform.model.kss.Kss_JsswModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/jssw")
@Api(tags = "jnp-jssw",description = "Jssw")
public class Jnp_JsswController extends PublicService {
	
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
	public ResponseMessage<PagerResult<Kss_JsswModel>> jssw_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JsswModel>> result= kssService.jssw_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /v4/jnp/jssw/jsswSave 家属送物申请(钱申请)保存
	 * @apiVersion 0.4.0
	 * @apiName jsswSave
	 * @apiGroup g_jnp
	 * @apiPermission user
	 *
	 * @apiDescription 家属送物申请(钱申请).
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
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample Example usage:
	 * appcode:"应用代码(必填)"
	 * jsbh:"监所编号(必填; 最大长度:9)"
	 * json:{
	 *        "entity":[
	 *          {
	 *            "gx":"与在押人员关系(必填; 字典:GX)",
	 *            "rybh":"人员编号(必填; 最大长度:21)",
	 *            "jsxm":"家属姓名(必填; 最大长度:30)",
	 *            "sqsj":"申请时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *            "sqyy":"申请原因(必填; 最大长度:255)",
	 *            "sqwp":"申请物品(必填; 最大长度:255; 格式:物品名称1*数量,物品名称2*数量)",
	 *            "jszjh":"家属证件号(必填; 最大长度:18)",
	 *            "creator":"创建人(必填; 最大长度:50)"
	 *          }
	 *        ]
	 *      }
	 *
	 */
	@ApiOperation("家属送物申请（钱申请）")
	@PostMapping("/jsswSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jssw_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		//接口id
		String interfaceId = "/v4/jnp/jssw/jsswSave";
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
			System.err.println(map.get("entity").toString());
			List<Kss_JsswModel> jsswModels = JSONArray.parseArray(map.get("entity").toString(), Kss_JsswModel.class);
			List<ShgxModel> shgxModels = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class);
			System.err.println("jsswModels--"+JSON.toJSONString(jsswModels.get(0)));
			System.err.println("shgxModels--"+JSON.toJSONString(shgxModels.get(0)));
			jsswModels.get(0).setJsbh(jsbh);
			jsswModels.get(0).setYwzt("1");
			jsswModels.get(0).setQrbz("0");
			shgxModels.get(0).setJsbh(jsbh);
			shgxModels.get(0).setState("R2");
			Map<String,Object> swMap = new HashMap();
			swMap.put("jsswModel",jsswModels.get(0));
			swMap.put("shgxModel",shgxModels.get(0));
			GxswModel gxswModel = new GxswModel();
			gxswModel.setMap(swMap);
			System.err.println("yyysmodel--"+JSON.toJSONString(gxswModel));
			ResponseMessage<String> jsswsq = kssServerApis.jsswSave(gxswModel);
			System.err.println("--"+JSON.toJSONString(jsswsq));
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
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jssw_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JsswModel data) {
		return kssService.jssw_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JsswModel> jssw_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jssw_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jssw_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jssw_deleteByKey(id);
	}

	/**
	 * @api {post} /v4/jnp/jssw/swqrSave     收物确认保存
	 * @apiVersion 0.4.0
	 * @apiName swqrSave
	 * @apiGroup g_jnp
	 * @apiPermission user
	 *
	 * @apiDescription 收物确认保存
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
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 *
	 * @apiExample Example usage:
	 *
	 * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:{
	 *        "entity":[
	 *          {
	 *            "id":"id(必填; 最大长度:23)"
	 *            "rybh":"人员编号(必填; 最大长度:21)",
	 *            "lqsj":"领取时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *            "qrbz":"确认标志(必填; 字典:SHFO 1.是 0.否; 最大长度:1)"
	 *          }
	 *        ]
	 *      }
	 */
	@ApiOperation("收物确认新增")
	@PostMapping("/swqrSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> swqr_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/jssw/swqrSave";
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
			System.err.println(map.get("entity").toString());
			List<JsswEntity> jsswEntity = JSONArray.parseArray(map.get("entity").toString(), JsswEntity.class);
			System.err.println("jsswmodel--"+ JSON.toJSONString(jsswEntity.get(0)));
			jsswEntity.get(0).setJsbh(jsbh);
			JsswEntity entity = jsswEntity.get(0);

			//实体类添加到GxswModel里
			Map<String, Object> gxswMap = Maps.newHashMap();
			gxswMap.put("jsswModel",entity);
			GxswModel gxswModel = new GxswModel();
			gxswModel.setMap(gxswMap);

			System.err.println("jsswmodel--"+JSON.toJSONString(entity));
			ResponseMessage<String> result = kssServerApis.jsswSave(gxswModel);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

}
