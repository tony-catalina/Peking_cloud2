/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.WpglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import awd.cloud.platform.model.kss.Kss_WpglModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wpgl")
@Api(tags = "kss-wpgl",description = "Wpgl") 
public class Kss_WpglController extends PublicService {
	
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
	public ResponseMessage<PagerResult<Kss_WpglModel>> wpgl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_WpglModel>> result= kssService.wpgl_query(queryParam);
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
	public ResponseMessage<String> wpgl_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_WpglModel data) {
		return kssService.wpgl_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> wpgl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_WpglModel data) {
		return kssService.wpgl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_WpglModel> wpgl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.wpgl_getByKey(id);
	}
	

	@OpenAPI
	public ResponseMessage<Integer> wpgl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.wpgl_deleteByKey(id);
	}

	/**
	 * @api {post} /v4/kss/wpgl/wpglList 根据人员标号查询现金汇总
	 * @apiVersion 0.4.0
	 * @apiName findWpgl
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据人员标号查询现金汇总.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}rybh          				                    人员编号
	 * @apiSuccess {String}jsbh          				                    监所编号
	 * @apiSuccess {String}wpjsid          				                    物品接收id
	 * @apiSuccess {String}wpmc          				                    物品名称
	 * @apiSuccess {String}sl          				                        数量
	 * @apiSuccess {String}xh          				                        型号
	 * @apiSuccess {String}tz          				                        特征
	 * @apiSuccess {String}qlzt          				                    领取状态
	 * @apiSuccess {String}lqrq          				                    领取日期
	 * @apiSuccess {String}lqztString          				                领取状态(已转换)

	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 268,
	 *     "data": [
	 *       {
	 *         "xh": "12",
	 *         "wpjsid": "110000114201909240008",
	 *         "tz": "zxc",
	 *         "lqrq": "2019-10-08 11:08:18",
	 *         "rybh": "110000114201909230001",
	 *         "wpmc": "12",
	 *         "sl": 1,
	 *         "lqzt": "1",
	 *         "lqztString": "已领取",
	 *         "jsbh": "110000114"
	 *       },
	 *       ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578447454182
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 *    json:{
	 *           "rybh":"人员编号"
	 *           "lqzt":"领取状态"
	 *         }
	 *
	 *
	 */

	@ApiOperation("根据条件查新物品管理")
	@PostMapping("/wpglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> findWpgl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/wpgl/wpglList";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("lqzt"))) {
				if (maps.getResult().get("lqzt").equals("1")) {
					param.and("lqzt", TermType.not, "0");
				}else if (maps.getResult().get("lqzt").equals("0")){
					param.and("lqzt", TermType.eq, "0");
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wpglQuery(param);
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



}
