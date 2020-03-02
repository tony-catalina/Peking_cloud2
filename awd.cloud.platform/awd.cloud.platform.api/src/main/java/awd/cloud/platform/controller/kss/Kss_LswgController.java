/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.LswgModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_LswgModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
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
@RequestMapping("/v4/kss/lswg")
@Api(tags = "kss-lswg",description = "Lswg") 
public class Kss_LswgController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/kss/lswg/lswgList  律师违规查询
	 * @apiVersion 0.4.0
	 * @apiName lswgList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 律师违规查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}creator                                            姓名
	 * @apiSuccess {String}jsbhString                                         入所日期
	 * @apiSuccess {String}lsxm                                               监室号
	 * @apiSuccess {String}jsbh                                               监所编号
	 * @apiSuccess {String}id                                                 ID
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
	 * "data":
	 * {
	 *         "creator": "管理员",
	 *         "id": "11000011420190905000048",
	 *         "jsbhString": "北京市第一看守所",
	 *         "lsxm": "是",
	 *         "jsbh": "110000114"
	 * }
	 *
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
	 *       "lsxm":"律师姓名(最大字段长度：50)",
	 *       "zjh":"证件号",
	 *       "dw":"律师单位(最大字段长度：40)",
	 *       "startTime":"开始时间(格式：yyyy-MM-dd HH:mm:ss)",
	 *       "endTime":"结束时间(格式：yyyy-MM-dd HH:mm:ss)"
	 *      }
	 * @return
	 */
	@ApiOperation("律师违规查询")
	@GetMapping("/lswgList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> lswgList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId ="/v4/kss/lswg/lswgList";
		String state = "R2";

		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("lsxm"))) {
				qParam.and("lsxm",TermType.like,"%"+maps.getResult().get("lsxm")+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
				qParam.and("zjh",TermType.like,"%"+maps.getResult().get("zjh")+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("dw"))) {
				qParam.and("dw",TermType.like,"%"+maps.getResult().get("dw")+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("startTime"))) {
				qParam.and("wgsj",TermType.gte,maps.getResult().get("startTime")+" 00:00:00");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))) {
				qParam.and("wgsj",TermType.lte,maps.getResult().get("endTime")+" 23:59:00");
			}
			System.err.println("律师违规查询"+ JSONUtil.toJson(qParam));
			DefaultQueryParam.addDefaultQueryParam(request,qParam, state);

			ResponseMessage<PagerResult<Map<String, Object>>> result =kssServerApis.lswgQueryForPage(qParam);


			System.err.println("result" + JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().getTotal());
			maplist.put("page", request.getParameter("page"));

			System.err.println("result" + JSON.toJSONString(maplist));

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
	 * @api {post} /v4/kss/lswg/lswgAdd  律师违规的保存操作
	 * @apiVersion 0.4.0
	 * @apiName lswgAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 律师违规的保存操作
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 * 	"entity": [{
	 *        "creator":"创建人(必填;最大字段长度：50)",
	 *          "dw":"律师单位(最大字段长度：40)",
	 *          "id":"id(最大字段长度：21)",
	 *          "lsxm":"律师姓名(最大字段长度：50)",
	 *          "zjh":"证件号",
	 *          "wgqk":"违规情况",
	 *          "lrmj":"录入民警(最大字段长度：50)",
	 *          "updator":"更新人(最大字段长度：50)"
	 *        }]
	 * }
	 */

	//{"creator":".{1,50}","dw":".{0,40}","id":"\\d{0,21}","lsxm":".{0,50}","lrmj":".{0,50}","updator":".{0,50}"}
	@ApiOperation("律师违规的保存操作")
	@PostMapping("/lswgAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lswgAdd( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/lswg/lswgAdd";

		//通过校验获取查询参数
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				System.out.println("----------------" + interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			List<LswgModel> list = JSONArray.parseArray(map.get("entity").toString(), LswgModel.class);
			LswgModel lswgModel =list.get(0);
			// List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			// Map<String,Object> entityMap = mapsList.get(0);

			if(!StringUtils.isNullOrEmpty(CacheUtils.get().getDictionary("LSDW",lswgModel.getDw()))) {
				lswgModel.setDw(CacheUtils.get().getDictionary("LSDW", lswgModel.getDw()));
			}
			lswgModel.setState("R2");
			lswgModel.setJsbh(jsbh);
			lswgModel.setCreatetime(new Date());
			System.err.println("律师违规登记"+JSONUtil.toJson(lswgModel));

			ResponseMessage<String> result = kssServerApis.lswgSave(lswgModel);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
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
	public ResponseMessage<String> lswg_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LswgModel data) {
		return kssService.lswg_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_LswgModel> lswg_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lswg_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> lswg_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lswg_deleteByKey(id);
	}
}
