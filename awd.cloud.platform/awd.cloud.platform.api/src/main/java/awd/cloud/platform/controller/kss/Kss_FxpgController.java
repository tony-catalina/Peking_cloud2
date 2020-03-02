/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_FxpgModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/fxpg")
@Api(tags = "kss-fxpg",description = "Fxpg") 
public class Kss_FxpgController extends PublicService {
	
	@Autowired
    private KssService kssService;

    @Autowired
	private KssServerApis kssServerApis;


	/**
	 * @return
	 * @api {get} /v4/kss/fxpg/lastFxpgRecord 最后一条风险评估记录
	 * @apiVersion 0.4.0
	 * @apiName lastFxpgRecord
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 最后一条风险评估记录
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}dddhyzk										    当前婚姻状况
	 * @apiSuccess {String}dqjtqk										    当前家庭情况
	 * @apiSuccess {String}ddcjdjl											曾经经历
	 * @apiSuccess {String}pglx									            评估类型
	 * @apiSuccess {String}yfxdj									        原风险等级(FXDJ)
	 * @apiSuccess {String}yfxdjString									    原风险等级(已转换)
	 * @apiSuccess {String}xfxdj									        现风险等级
	 * @apiSuccess {String}xfxdjString									    现风险等级(已转换)
	 *
	 *
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
	 * "data":  [
	 *         "dddhyzk": null,
	 *         "yfxdjString": null,
	 *         "ddcjdjl": "0060010001,0060010002,0060010003",
	 *         "pglx": null,
	 *         "rybh": "310000111201906200006",
	 *         "yfxdj": null,
	 *         "id": "11000011420190909000043",
	 *         "xfxdj": "1",
	 *         "jsbh": "110000111",
	 *         "xfxdjString": "重大一级",
	 *         "dqjtqk": null
	 * ],
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
	 *    "rybh":"人员编号(最大字段长度：21)",
	 * }
	 */
	@OpenAPI
	@ApiOperation("最后一条风险评估记录")
	@GetMapping("/lastFxpgRecord")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> zdzyjlList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/fxpg/lastFxpgRecord";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			QueryParam param = new QueryParam();
			Sort sort = new Sort("createtime");
			sort.setOrder("desc");
			List<Sort> li = Lists.newArrayList();
			li.add(sort);
			param.setSorts(li);
			param.setPageSize(1);
			param.setPageIndex(0);

			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh",TermType.eq, maps.getResult().get("rybh"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.fxpgQueryForPage(param);

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



	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_FxpgModel>> fxpg_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_FxpgModel>> result= kssService.fxpg_query(queryParam);
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
	public ResponseMessage<String> fxpg_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_FxpgModel data) {
		return kssService.fxpg_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> fxpg_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_FxpgModel data) {
		return kssService.fxpg_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_FxpgModel> fxpg_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.fxpg_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> fxpg_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.fxpg_deleteByKey(id);
	}
}
