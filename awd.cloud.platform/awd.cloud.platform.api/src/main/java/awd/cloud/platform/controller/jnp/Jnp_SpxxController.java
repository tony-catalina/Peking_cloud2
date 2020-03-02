/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_SpxxModel;
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
@RequestMapping("/v4/jnp/spxx")
@Api(tags = "jnp-spxx",description = "spxx")
public class Jnp_SpxxController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/jnp/spxx/spxxQuery 可选商品及数量查询
	 * @apiVersion 0.4.0
	 * @apiName spxxQuery
	 * @apiGroup g_jnp
	 * @apiPermission any
	 *
	 * @apiDescription 可选商品及数量查询.
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} id         					商品ID
	 * @apiSuccess {String} spmc         				商品名称
	 * @apiSuccess {String} photos         				商品图片
	 * @apiSuccess {String} lsj         				商品价格
	 * @apiSuccess {String}message         				                     返回信息
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}total         				                     返回数量
	 * @apiSuccess {String}date         				                     返回数据
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 19,
	 *     "data": [
	 *       {
	 *         "spmc": "抹布",
	 *         "id": "11000011420191019000023",
	 *         "lsj": "1"
	 *         "photos": "iVBORw0KGgoAAAANSUhEUgAAAj8AAAECCAYAAABTPwsAAAABc1JHQgCuzhw",
	 *       }
	 *       ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576478383719
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * {
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 *   "json": {
	 *     "page": "页数",
	 *     "pageSize": "每页的条数",
	 *     "rynh": "人员编号（最大长度：21"
	 *   }
	 * }
	 *
	 */
	@OpenAPI
	@ApiOperation("可选商品及数量查询")
	@GetMapping("/spxxQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> spxx_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/spxx/spxxQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
		if(maps.getStatus()!=200) {
			return ResponseMessage.error(maps.getMessage());
		}

		//查询参数
		QueryParam param = new QueryParam();
		if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
			param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
		}
		if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsbh"))) {
			param.and("jsbh", TermType.eq, jsbh);
		}
//		if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//			param.and("user", TermType.eq, maps.getResult().get("user"));
//		}
		DefaultQueryParam.addDefaultQueryParam(request, param, state);
		ResponseMessage<PagerResult<Map<String,Object>>>  result= kssServerApis.spxxQuery(param);
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
	}
	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> spxx_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_SpxxModel data) {
		return kssService.spxx_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> spxx_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_SpxxModel data) {
		return kssService.spxx_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_SpxxModel> spxx_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.spxx_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> spxx_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.spxx_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/jnp/spxx/yygwQuery 预约购物查询
	 * @apiVersion 0.4.0
	 * @apiName yygwQuery
	 * @apiGroup g_jnp
	 * @apiPermission any
	 *
	 * @apiDescription 预约购物查询
	 *
	 * @apiParam {String} appcode 						应用代码(必传)
	 * @apiParam {String} jsbh 							监所编号(必传)
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 *
	 * @apiSuccess {String} photourl         			商品照片地址
	 * @apiSuccess {String} lsj         				零售价
	 * @apiSuccess {String} spmc         				商品名称
	 * @apiSuccess {String} tm         					条码
	 * @apiSuccess {String} photos         				商品照片
	 *
	 * @apiSuccess {String} page         				当前页数
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "photourl": "http://14.66.87.165:8888/storagegroup/M00/00/00/wKgEHl33SkOAIzdrAACbK6Nrhzo863.gif",
	 *         "lsj": 10,
	 *         "spmc": "加多宝",
	 *         "tm": "120",
	 *         "photos": "R0lGODlhZABkAPUAAAAAAAgICBAQEBkZGSEhISkpKTMzMzo6OkJ………………"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576473169454
	 * }
	 *
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:
	 *   {
	 *     "page":"当前页数",
	 *     "pageSize":"一页数据数量"
	 *   }
	 */
	@OpenAPI
	@ApiOperation("预约购物查询")
	@GetMapping("/yygwQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> yygw_query(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/spxx/yygwQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
//			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
//				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
//			}
//			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
//				param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
//			}
//			if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//				param.and("user", TermType.eq, maps.getResult().get("user"));
//			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.spxxQuery(param);
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
}
