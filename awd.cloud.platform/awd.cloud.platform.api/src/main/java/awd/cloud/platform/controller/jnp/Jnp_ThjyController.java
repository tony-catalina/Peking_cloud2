/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_ThjyModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/thjy")
@Api(tags = "jnp-thjy",description = "Thjy")
public class Jnp_ThjyController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/jnp/thjy/thjyQuery 谈话记录查询
	 * @apiVersion 0.4.0
	 * @apiName thjyQuery
	 * @apiGroup g_jnp
	 * @apiPermission any
	 *
	 * @apiDescription 谈话记录查询
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
	 * @apiSuccess {String} fzmj        				负责民警
	 * @apiSuccess {String} xbString        			性别(已转换)
	 * @apiSuccess {String} xm         					姓名
	 * @apiSuccess {String} kssjString         			开始时间
	 * @apiSuccess {String} rybh         				人员编号
	 * @apiSuccess {String} xb         					性别
	 * @apiSuccess {String} jssjString        			结束时间
	 * @apiSuccess {String} thnr         				谈话内容
	 * @apiSuccess {String} jsh         				监室号
	 * @apiSuccess {String} nl         					年龄
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
	 *         "fzmj": "管理员",
	 *         "xbString": "男性",
	 *         "xm": "王靖03",
	 *         "kssjString": "2019-12-20 10:00:08",
	 *         "rybh": "110000114201911050002",
	 *         "xb": "1",
	 *         "jssjString": "2019-12-20 12:00:08",
	 *         "thnr": "44444444444444",
	 *         "jsh": "0203",
	 *         "nl": 34
	 *       }
	 *       ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576463697953
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * "appcode": "应用代码(必填)",
	 * "jsbh": "监所编号(必填; 最大长度:9)",
	 * "json": {
	 *   "rynh": "人员编号（最大长度：21"
	 *   "page": "页数",
	 *   "pageSize": "每页的条数",
	 * }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("谈话记录查询")
	@GetMapping("/thjyQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> thjy_query(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/thjy/thjyQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsbh"))) {
				param.and("jsbh", TermType.eq, maps.getResult().get("jsbh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));
			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.thjyQueryForPage(param);
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
	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> thjy_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ThjyModel data) {
		return kssService.thjy_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> thjy_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ThjyModel data) {
		return kssService.thjy_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_ThjyModel> thjy_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.thjy_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> thjy_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.thjy_deleteByKey(id);
	}
}
