/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.TfsjdjModel;
import awd.bj.kss.model.XsjlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.StringUtils;
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
import awd.cloud.platform.model.kss.Kss_TfsjdjModel;
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
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/tfsjdj")
@Api(tags = "kss-tfsjdj",description = "Tfsjdj") 
public class Kss_TfsjdjController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {post} /v4/kss/tfsjdj/tfsjdjUpdate 突发登记处理
	 * @apiVersion 0.4.0
	 * @apiName tfsjdjUpdate
	 * @apiGroup g_kss
	 * @apiPermission user
	 * @apiDescription 突发登记处理
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * {
	 * "appcode":"应用代码(必填)",
	 * "jsbh":"监所编号(必填; 最大长度:9)",
	 * json:{"entity":[{
	 * "id":"Id(必填; 最大长度:23)",
	 * "clzt":"处理状态(必填; 最大长度:1  1、未处理2、已处理)",
	 * "jqh":"监区号(必填),
	 * "tfsj":"突发时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 * "clr":"处理人(必填; 最大长度:30)",
	 * "tfnr":"突发内容(必填; 最大长度:255)",
	 * "clqk":处理情况（必填; 最大长度:255）,
	 * "clsj":处理时间（必填; 格式:yyyy-MM-dd hh:mm:ss）,
	 * "bz":"备注(最大长度:255)
	 * }]
	 * }
	 * }
	 */
	@ApiOperation("突发登记处理")
	@PostMapping("/tfsjdjUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tfsjdjUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tfsjdj/tfsjdjUpdate";
		//校验权限
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}
		try {
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}

             TfsjdjModel tfsjdjModel=new TfsjdjModel();
			if(StringUtils.isNullOrEmpty(maps.getResult().get("jqh"))){
				tfsjdjModel.setJqh(maps.getResult().get("jqh").toString());
			}
			tfsjdjModel.setState("R2");
			tfsjdjModel.setJsbh(jsbh);
			if(StringUtils.isNullOrEmpty(maps.getResult().get("creator"))){
				tfsjdjModel.setCreator(maps.getResult().get("creator").toString());
			}
			SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNullOrEmpty(maps.getResult().get("tfsj"))){
				tfsjdjModel.setTfsj(spl.parse(maps.getResult().get("tfsj").toString()));
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("clr"))){
				tfsjdjModel.setClr(maps.getResult().get("clr").toString());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("tfnr"))){
				tfsjdjModel.setTfnr(maps.getResult().get("tfnr").toString());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("bz"))){
				tfsjdjModel.setBz(maps.getResult().get("bz").toString());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("clqk"))){
				tfsjdjModel.setClqk(maps.getResult().get("clqk").toString());
			}
			if (StringUtils.isNullOrEmpty(maps.getResult().get("clsj"))){
				tfsjdjModel.setClsj(spl.parse(maps.getResult().get("clsj").toString()));
			}
			String id=null;
			if(StringUtils.isNullOrEmpty(maps.getResult().get("id"))){
				id=maps.getResult().get("id").toString();
			}
			ResponseMessage<String> result=kssServerApis.tfsjdjUpdate(id, tfsjdjModel);

			return result ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("修改失败！");
		}
	}



	/**
	 * @api {post} /v4/kss/tfsjdj/tfsjdjSave 突发事件登记保存
	 * @apiVersion 0.4.0
	 * @apiName tfsjdjSave
	 * @apiGroup g_kss
	 * @apiPermission user
	 * @apiDescription 突发事件登记保存
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * {
	 * "appcode":"应用代码(必填)",
	 * "jsbh":"监所编号(必填; 最大长度:9)",
	 * json:{"entity":{
	 * "clzt":"处理状态(必填; 最大长度:1  1、未处理2、已处理)",
	 * "jqh":"监区号(必填; 用","分"),
	 * "tfsj":"突发时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 * "clr":"处理人(必填; 最大长度:30)",
	 * "tfnr":"突发内容(必填; 最大长度:255)",
	 * "bz":"备注(最大长度:255)
	 * }]
	 * }
	 * }
	 */
	@ApiOperation("新增突发登记")
	@PostMapping("/tfsjdjSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tfsjdjSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tfsjdj/tfsjdjSave";
		//校验权限
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}
		try {
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("jqh"))){
				String[] jqhs=maps.getResult().get("jqh").toString().split(",");
				TfsjdjModel tfsjdjModel=new TfsjdjModel();
				for (int i = 0; i < jqhs.length; i++) {
					tfsjdjModel.setJqh(jqhs[i]);
					tfsjdjModel.setState("R2");
					tfsjdjModel.setJsbh(jsbh);
                    if(StringUtils.isNullOrEmpty(maps.getResult().get("creator"))){
						tfsjdjModel.setCreator(maps.getResult().get("creator").toString());
					}
					SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(StringUtils.isNullOrEmpty(maps.getResult().get("tfsj"))){
                    	tfsjdjModel.setTfsj(spl.parse(maps.getResult().get("tfsj").toString()));
					}
                    if(StringUtils.isNullOrEmpty(maps.getResult().get("clr"))){
                    	tfsjdjModel.setClr(maps.getResult().get("clr").toString());
					}
                    if(StringUtils.isNullOrEmpty(maps.getResult().get("tfnr"))){
                    	tfsjdjModel.setTfnr(maps.getResult().get("tfnr").toString());
					}
                    if(StringUtils.isNullOrEmpty(maps.getResult().get("bz"))){
                    	tfsjdjModel.setBz(maps.getResult().get("bz").toString());
					}
					tfsjdjModel.setCreatetime(new Date());
					tfsjdjModel.setClzt("1");
					kssServerApis.tfsjdjSave(tfsjdjModel);
				}
				return ResponseMessage.ok();
			}else {
				return  ResponseMessage.error("保存失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}


	/**
	 * @api {get} /v4/kss/tfsjdj/tfsjdjQuery 突发登记查询
	 * @apiVersion 0.4.0
	 * @apiName tfsjdjQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 突发登记查询.
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 * @apiSuccess {String} jqh:                        监区号
	 * @apiSuccess {String} clzt:                       处理状态  1、未处理2、已处理
	 * @apiSuccess {String} clr:                        处理人
	 * @apiSuccess {String} clqk:                       处理情况
	 * @apiSuccess {String} state:                      删除状态
	 * @apiSuccess {String} bz:                         备注
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("突发登记查询")
	@GetMapping("/tfsjdjQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> tfsjdjQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/tfsjdj/tfsjdjQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}
		QueryParam param = new QueryParam();
		if (!StringUtils.isNullOrEmpty(maps.getResult().get("clzt"))){
			String clzt = maps.getResult().get("clzt").toString();
			param.and("clzt", TermType.eq,clzt);
		}

		ResponseMessage<PagerResult<TfsjdjModel>> result =kssServerApis.tfsjdjQueryForPage(param);
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

	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_TfsjdjModel>> tfsjdj_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_TfsjdjModel>> result= kssService.tfsjdj_query(queryParam);
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
	public ResponseMessage<String> tfsjdj_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_TfsjdjModel data) {
		return kssService.tfsjdj_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> tfsjdj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_TfsjdjModel data) {
		return kssService.tfsjdj_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_TfsjdjModel> tfsjdj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.tfsjdj_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> tfsjdj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.tfsjdj_deleteByKey(id);
	}
}
