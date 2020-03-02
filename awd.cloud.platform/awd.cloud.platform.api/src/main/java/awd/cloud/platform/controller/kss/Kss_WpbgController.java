/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
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
@RequestMapping("/v4/kss/wpjs")
@Api(tags = "kss-wpbg",description = "wpbg")
public class Kss_WpbgController extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/kss/wpbg/wpjsList 根据人员编号查询物品接收
	 * @apiVersion 0.4.0
	 * @apiName wpjsList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据人员编号查询物品接收
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}rybh          				                        人员编号
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}srlx          				                        接收类型
	 * @apiSuccess {String}srlxString       				                    接收类型（已转换）
	 * @apiSuccess {String}jswpmc          				                    接受物品名称
	 * @apiSuccess {String}jszjh          				                        家属证件号
	 * @apiSuccess {String}djr          				                        登记人
	 * @apiSuccess {String}djsj          				                        登记时间
	 * @apiSuccess {String}fzdbh          				                        封装袋编号
	 * @apiSuccess {String}fzdsl          				                        封装袋数量
	 * @apiSuccess {String}djwpsl          				                    大件物品数量
	 * @apiSuccess {String}djwpbh          				                    大件物品编号
	 * @apiSuccess {String}bz          				                        备注
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间

	 * @apiSuccess {String}message                                              返回信息
	 * @apiSuccess {String}result                                               返回结果
	 * @apiSuccess {String}total                                                返回总数
	 * @apiSuccess {String}data                                                 返回数据
	 * @apiSuccess {String}status                                               返回状态
	 * @apiSuccess {String}timestamp                                            时间戳
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
	 *        	id: "11000011420200120000263"
	 * 			rybh: "110000114202001200001"
	 * 			jsbh: "110000114"
	 * 			jsbhString: null
	 * 			srlx: "1"
	 * 			srlxString: null
	 * 			jswpmc: "24"
	 * 			jszjh: null
	 * 			djr: "管理员"
	 * 			djsj: null
	 * 			fzdbh: "0002"
	 * 			fzdsl: 1
	 * 			djwpsl: 1
	 * 			djwpbh: "0001"
	 * 			bz: ",sda,,,,,,,"
	 * 			state: "R2"
	 * 			stateString: null
	 * 			creator: "管理员"
	 * 			createtime: "2020-01-20 11:44:51"
	 * 			updator: null
	 * 			updatetime: null
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
	 *          "rybh":"人员编号"
	 * 			"page": "当前页数",
	 * 	 		"rows": "一页数据量",
	 * 	 		"sort": "id",
	 * 	 		"order": "desc"
	 *         }
	 *
	 *
	 */

	@ApiOperation("根据人员编号查询物品接收")
	@GetMapping("/wpjsList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> wpjsList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/wpbg/wpjsList";
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
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wpjsQueryForPage(param);
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

	/**
	 * @api {get} /v4/kss/wpbg/wpglList 根据物品接收id查询物品
	 * @apiVersion 0.4.0
	 * @apiName wpjsList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据物品接收id查询物品
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}rybh          				                        人员编号
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}wpjsid          				                    物品接收id
	 * @apiSuccess {String}wpmc       				                    		物品名称
	 * @apiSuccess {String}sl          				                    	数量
	 * @apiSuccess {String}xh          				                        型号
	 * @apiSuccess {String}tz          				                        特征
	 * @apiSuccess {String}lqzt          				                        领取状态
	 * @apiSuccess {String}lqrq          				                        领取日期
	 * @apiSuccess {String}bz          				                        备注
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间
	 * @apiSuccess {String}lqr          				                		领取人
	 * @apiSuccess {String}wpmcString          				                物品名称（已转换）
	 * @apiSuccess {String}lqztString          				                领取状态（已转换）

	 * @apiSuccess {String}message                                              返回信息
	 * @apiSuccess {String}result                                               返回结果
	 * @apiSuccess {String}total                                                返回总数
	 * @apiSuccess {String}data                                                 返回数据
	 * @apiSuccess {String}status                                               返回状态
	 * @apiSuccess {String}timestamp                                            时间戳
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
	 *        	id: "11000011420200115000379"00263"
	 * 			rybh: "110000114201912190006"00001"
	 * 			jsbh: "110000114"
	 * 			jsbhString: "北京市第一看守所"
	 * 			wpjsid: "11000011420200115000259"
	 * 			wpmc: "3"
	 * 			sl: 1
	 * 			xh: "1"
	 * 			tz: "1"
	 * 			lqzt: "0"
	 * 			lqrq: null
	 * 			bz: ""
	 * 			creator: "管理员"
	 * 			createtime: "2020-01-15 13:28:23"
	 * 			updator: ""
	 * 			updatetime: null
	 * 			lqr: ""
	 * 			wpmcString: "卫生裤"
	 * 			lqztString: "未领取" 11:44:51"
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
	 *          "id":"物品接收id",
	 * 			"page": "当前页数",
	 * 			"rows": "一页数据量",
	 * 			"sort": "id",
	 * 			"order": "desc"
	 *         }
	 */

	@ApiOperation("根据物品接收id查询物品")
	@GetMapping("/wpglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> wpglList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/wpbg/wpglList";
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
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("wpjsid"))) {
				param.and("wpjsid", TermType.eq, maps.getResult().get("wpjsid"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wpjsQueryForPage(param);
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
