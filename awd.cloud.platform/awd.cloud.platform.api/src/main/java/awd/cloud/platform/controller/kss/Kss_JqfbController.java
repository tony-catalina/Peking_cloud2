/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.JqfbModelDO;
import awd.cloud.platform.model.kss.Kss_JqfbModel;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jqfb")
@Api(tags = "kss-jqfb",description = "Jqfb") 
public class Kss_JqfbController extends PublicService {
	
	@Autowired
    private KssServerApis kssServerApis;


	/**
	 * @api {get} /v4/kss/jqgl/jqglQuery 警情管理查询
	 * @apiVersion 0.4.0
	 * @apiName jqglQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 *
	 * @apiDescription 警情管理查询.
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 *
	 * @apiSuccess {String} xm         			      姓名
	 * @apiSuccess {String} jh         			      警号
	 * @apiSuccess {String} fbr         			      发布人
	 * @apiSuccess {String} fbsj         			     发布时间
	 * @apiSuccess {String} fbdd         			     发布地点
	 * @apiSuccess {String} jqnr         			      警情内容
	 * @apiSuccess {String} cljg         			       处理结果
	 * @apiSuccess {String} bz         			       备注
	 * @apiSuccess {String} ly         			       来源
	 * @apiSuccess {String} clsj         			       处理时间
	 * @apiSuccess {String} clr         			       处理人
	 *
	 * @apiSuccess {String} page         				当前页数
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "jh": "1212",
	 *         "xm": "司马茜",
	 *         "fbr": "阿萨德",
	 *         "fbsj": "2019-08-01 14:14:02",
	 *         "fbdd": "监管总队",
	 *         "jqnr": "有警情",
	 *         "cljg": "关禁闭",
	 *         "bz": "备注",
	 *         "ly": "监控"
	 *         "clsj": "2019-08-01 14:14:02"
	 *         "clr": "啊实打实"
	 *       }
	 *      ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576496854065
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample Example usage:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:
	 *   {
	 *     "fbr":"发布人(最大长度:50)",
	 *     "startTime":"开始发布时间格式:yyyy-MM-dd hh:mm:ss",
	 *     "endTime":"结束发布时间 格式:yyyy-MM-dd hh:mm:ss",
	 *   }
	 *
	 */
	@OpenAPI
	@ApiOperation("警情管理查询")
	@GetMapping("/jqglQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jqgl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		//接口id
		String interfaceId = "/v4/kss/jqgl/jqglQuery";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			JSONObject jsonObject = JSONObject.parseObject(json);
			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbr"))) {
				param.and("fbr", TermType.like, maps.getResult().get("fbr"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("startTime"))) {
				param.and("fbsj", TermType.gte, maps.getResult().get("startTime"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))) {
				param.and("fbsj", TermType.lte, maps.getResult().get("endTime"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<JqfbModelDO>> result= kssServerApis.jqfbList(param);
			System.err.println("result"+JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",  result.getResult().getTotal());
			maplist.put("page",  request.getParameter("page"));

			ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
			if(list.getStatus()==200) {
				list.setMessage("查询成功");
				if(list.getResult()==null) {
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
	 * @api {post} /v4/kss/jqfb/jqfbSaveOrUpdate 警情发布新增(修改)
	 * @apiVersion 0.4.0
	 * @apiName jqfbSaveOrUpdate
	 * @apiGroup g_kss
	 * @apiPermission any
	 *
	 * @apiDescription 警情发布新增(修改)
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
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * {
	 *      "appcode":"应用代码(必填)",
	 *      "jsbh":"监所编号(必填; 最大长度:9)",
	 *      "entity":[
	 *           {
	 *              "id":"id(非必填; 最大长度:23)",
	 *              "fbr":"发布人(非必填; 最大长度:50)",
	 *              "fbsj":"发布时间(非必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *              "fbdd":"发布地点(非必填; 最大长度:60)",
	 *              "ly":"来源(非必填; 监控、录像)",
	 *              "jqnr":"警情内容(非必填;)",
	 *              "clr":"处理人(非必填; 最大长度:50)"
	 *              "clsj":"处理时间(非必填; 格式:yyyy-MM-dd hh:mm:ss)"
	 *              "cljg":"处理结果(非必填; 谈话教育、加带刑具、关禁闭)"
	 *              "bz":"备注(非必填; )"
	 *
	 *           }
	 *        ]
	 *      }
	 * }
	 *
	 */

	@ApiOperation("警情发布新增(修改)")
	@PostMapping("/jqfbSaveOrUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jqfb_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jqfb/jqfbSaveOrUpdate";
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
			List<JqfbModelDO> modelList = JSONArray.parseArray(map.get("entity").toString(), JqfbModelDO.class);
			System.err.println("model--"+ JSON.toJSONString(modelList.get(0)));
			JqfbModelDO jqfbModelDO = modelList.get(0);
			ResponseMessage<String> result;
			if (!StringUtils.isNullOrEmpty(jqfbModelDO.getId())){
				if (!StringUtils.isNullOrEmpty(jqfbModelDO.getFbr())){
					jqfbModelDO.setUpdator(jqfbModelDO.getFbr());
				}else{
					jqfbModelDO.setClsj(new Date());
					jqfbModelDO.setUpdator(jqfbModelDO.getClr());
				}
				jqfbModelDO.setUpdatetime(new Date());
				jqfbModelDO.setJsbh(jsbh);
				result = kssServerApis.jqfbUpdate(jqfbModelDO.getId(),jqfbModelDO);
			}else {
				jqfbModelDO.setCreatetime(new Date());
				jqfbModelDO.setState("R2");
				jqfbModelDO.setCreator(jqfbModelDO.getFbr());
				jqfbModelDO.setJsbh(jsbh);
				result = kssServerApis.jqfbSave(jqfbModelDO);
			}
			System.err.println("model--"+JSON.toJSONString(jqfbModelDO));

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

}
