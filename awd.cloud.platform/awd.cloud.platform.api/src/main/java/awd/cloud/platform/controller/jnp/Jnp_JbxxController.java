/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import awd.cloud.platform.api.KssServerApis;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/jbxx")
@Api(tags = "jnp-jbxx",description = "Jbxx")
public class Jnp_JbxxController extends PublicService {
	
	@Autowired
    private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/jnp/jbxx/jszyryxxQuery 监室在押人员信息查询
	 * @apiVersion 0.4.0
	 * @apiName jszyryxxQuery
	 * @apiGroup g_jnp
	 * @apiPermission any
	 *
	 * @apiDescription 监室在押人员信息查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 											    查询参数集
	 *
	 * @apiSuccess {String} xbString       				                    性别(已转换)
	 * @apiSuccess {String} photoUrl        				                照片路径
	 * @apiSuccess {String} xm           				                    姓名
	 * @apiSuccess {String} xb        				                 		性别（字典）
	 * @apiSuccess {String} nl       				                        年龄
	 * @apiSuccess {String} rybh       				                        人员编号
	 * @apiSuccess {String} jsh       				                        监室号
	 *
	 * @apiSuccess {String}message         				                     返回信息
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}total         				                     返回数量
	 * @apiSuccess {String}date         				                     返回数据
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *  appcode:"应用代码(必填)",
	 *  jsbh:"监所编号(必填; 最大字段长度：9)",
	 *  json:{
	 *     "rybh":"人员编号(最大字段长度:21)",
	 *     "jsh":"监室号(最大字段长度:4)",
	 *     "page":"当前页",
	 *     "pagesize":"当前页数"
	 *       }
	 *
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 *
	 *{
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 5,
	 *     "data": [
	 *      {
	 *         "xbString": "女性",
	 *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-M2AMiu3AAABsc1UHh4780.jpg",
	 *         "xm": "司马茜",
	 *         "rybh": "110000111201907120002",
	 *         "xb": "2",
	 *         "nl": 34
	 *       },
	 *       {
	 *         "xbString": "女性",
	 *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-R-AXRKdAAABsc1UHh4337.jpg",
	 *         "xm": "李东",
	 *         "rybh": "110000111201907120009",
	 *         "xb": "2",
	 *         "nl": 32
	 *       },
	 *       {
	 *         "xbString": "女性",
	 *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-M2AMiu3AAABsc1UHh4780.jpg",
	 *         "xm": "嘻哈侠",
	 *         "rybh": "110000111201907120011",
	 *         "xb": "2",
	 *         "nl": 37
	 *       },
	 *       {
	 *         "xbString": "男性",
	 *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/1E/wKgEMl2QeAeADFuQAAAIiecvuh8199.jpg",
	 *         "xm": "打算",
	 *         "rybh": "110000111201907120015",
	 *         "xb": "1",
	 *         "nl": 29
	 *       },
	 *       {
	 *         "xbString": "男性",
	 *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/1E/wKgEMl2QeAmAD1U4AAABsc1UHh4984.jpg",
	 *         "xm": "测试3",
	 *         "rybh": "110000111201907150005",
	 *         "xb": "1",
	 *         "nl": 28
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576559972957
	 * }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("监室在押人员信息查询")
	@GetMapping("jszyryxxQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jszyryxx_query(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/jbxx/jszyryxxQuery";
		String state = "R8";
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
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.in, maps.getResult().get("rybh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<List<Map<String,Object>>> result= kssServerApis.getRyxxAndPhoto(param);
			System.err.println("result"+JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",  result.getResult().size());
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
