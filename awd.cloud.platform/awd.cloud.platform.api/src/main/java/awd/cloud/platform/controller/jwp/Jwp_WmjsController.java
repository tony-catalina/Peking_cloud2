/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_WmjsModel;
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
@RequestMapping("/v4/jwp/jwp_js")
@Api(tags = "jwp-wmjs",description = "wmjs")
public class Jwp_WmjsController extends PublicService {


	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {get} /v4/jwp/jwp_js/jsShow 文明监室查询
	 * @apiVersion 0.4.0
	 * @apiName jsShow
	 * @apiGroup g_jwp
	 * @apiPermission any
	 * @apiDescription 文明监室查询
	 *
	 * @apiParam {String} appcode 											 应用代码（必填）
	 * @apiParam {String} jsbh 												 监所编号(必填；最大字段长度：9)
	 * @apiParam {String} json 											     查询参数集(必填)


	 * @apiSuccess {String} bz								 				备注
	 * @apiSuccess {String} bznum											额定押量
	 * @apiSuccess {String} createtime										创建时间
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}id												ID
	 * @apiSuccess {String}innum											关押量
	 * @apiSuccess {String}jqh												监区号
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所名称（字典已转换）
	 * @apiSuccess {String}jsh												监室号
	 * @apiSuccess {String}jslb												监室类别(JSLX)
	 * @apiSuccess {String} wmjsString										文明监室名称（字典已转换）
	 * @apiSuccess {String} jslbString										监室类别（字典已转换）
	 * @apiSuccess {String}jsmc												监室名称
	 * @apiSuccess {String}state											状态(STATE)
	 * @apiSuccess {String}stateString										状态（字典已转换）
	 * @apiSuccess {String}type												男女类别(XB)
	 * @apiSuccess {String}typeString										女类别（字典已转换）
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}wmjs												文明监室(SHFO)
	 * @apiSuccess {String}xgmj												协管民警
	 * @apiSuccess {String}zgmj												主管民警
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "jqh": "90",
	 *         "createtime": "2019-09-24 00:00:00",
	 *         "creator": "管理员",
	 *         "wmjs": "0",
	 *         "stateString": "有效",
	 *         "type": "1",
	 *         "jslb": "1",
	 *         "jsmc": "单独关押监室一",
	 *         "bznum": 0,
	 *         "jslbString": "普通",
	 *         "zgmj": "十大大大",
	 *         "xgmj": "大苏打",
	 *         "bz": "单独关押监室一",
	 *         "typeString": "男监",
	 *         "updator": "管理员",
	 *         "innum": 1,
	 *         "wmjsString": "否",
	 *         "id": "101263",
	 *         "state": "R2",
	 *         "jsbhString": "北京市第一看守所",
	 *         "updatetime": "2019-12-13 00:00:00",
	 *         "jsbh": "110000114",
	 *         "jsh": "9001"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1577415840432
	 * }
	 *
	 *
	 *
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *  appcode:"应用代码(必填)",
	 *  jsbh:"监所编号(必填; 最大字段长度：9)",
	 *  json:{
	 *     "jsh":"监室号(最大字段长度:4)",
	 *       }
	 *
	 */
	@OpenAPI
	@ApiOperation("文明监室查询")
	@GetMapping("/jsShow")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jsShow(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jwp/jwp_js/jsShow";
		String state = request.getParameter("state");

		try {
			//通过校验获取查询参数
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}

			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jsQueryForPage(param);
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
		}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
}
