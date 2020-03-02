/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_KssModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/kss")
@Api(tags = "kss-kss",description = "Kss") 
public class Kss_KssController  extends PublicService {

	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 *
	 * @api {get} /v4/kss/jshj/kssList kss查询
	 * @apiVersion 0.4.0
	 * @apiName kssList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription kss查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}creator          				             创建人
	 * @apiSuccess {String}id                                            ID
	 * @apiSuccess {String}jsbh                                          监所编号
	 * @apiSuccess {String}email                                         邮件
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
	 * "data": [
	 *       {
	 *        "creator": "管理员",
	 *         "id": "31000011320171219000015",
	 *         "jsbh": "110000114",
	 *         "email": "12313"
	 *           }
	 *        ]
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
	 *   }
	 */
	@OpenAPI
	@ApiOperation("kss查询")
	@GetMapping("/kssList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> kssList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/kss/kssList";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			QueryParam queryParam = new QueryParam();

			String sortName = (String) maps.getResult().get("sort");

			if (sortName != null && sortName.indexOf("String") > 0) {
				sortName = sortName.substring(0, sortName.length() - 6);
			}

			String orderBy = (String) maps.getResult().get("order");

			List<Sort> sorts = new ArrayList<>();
			Sort sort = new Sort();
			if(sortName != null && orderBy != null) {
				sort.setName(sortName);
				sort.setOrder(orderBy);
			}else {
				sort.setName("id");
				sort.setOrder("desc");
			}
			sorts.add(sort);
			queryParam.setSorts(sorts);


			if(!StringUtils.isNullOrEmpty(jsbh)) {
				queryParam.and("jsbh", TermType.eq,jsbh);
			}
			DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.kssQueryForPage(queryParam);

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

	
	

	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> kss_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_KssModel data) {
		return kssService.kss_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> kss_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_KssModel data) {
		return kssService.kss_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_KssModel> kss_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.kss_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> kss_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.kss_deleteByKey(id);
	}
}
