/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.StringUtils;
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
import awd.cloud.platform.model.kss.Kss_LsfxModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/lsfx")
@Api(tags = "kss-lsfx",description = "Lsfx") 
public class Kss_LsfxController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	KssServerApis kssServerApis;


	/**
	 * @api {get} /v4/kss/lsfx/lsfxQuery 留所服刑查询
	 * @apiVersion 0.4.0
	 * @apiName lsfxQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 留所服刑查询.
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 * @apiSuccess {String} snbh                        所内编号
	 * @apiSuccess {String} JSBH	                    监所编号
	 * @apiSuccess {String} RYBH	                    人员编号
	 * @apiSuccess {String} LSYY	                    留所原因(LSYY)
	 * @apiSuccess {String} BLR	                        办理人
	 * @apiSuccess {String} BLRQ	                    办理日期
	 * @apiSuccess {String} KCQK	                    考察情况
	 * @apiSuccess {String} KSRQ	                    开始日期
	 * @apiSuccess {String} JSRQ	                    结束日期
	 * @apiSuccess {String} PSBZ	                    批示标志
	 * @apiSuccess {String} LDXM	                    领导姓名
	 * @apiSuccess {String} LDYJ	                    领导意见
	 * @apiSuccess {String} LDPSSJ	                    领导批示时间
	 * @apiSuccess {String} BZ	                        备注
	 * @apiSuccess {String} ZSJCSYJ	                    驻所监察室意见
	 *
	 */
	@OpenAPI
	@ApiOperation("留所服刑查询")
	@GetMapping("/lsfxQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> lsfxQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/lsfx/lsfxQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}

		QueryParam param = new QueryParam();
		param.and("jsbh", jsbh);

		if(!StringUtils.isNullOrEmpty(maps.getResult().get("blr"))){
			param.and("blr", TermType.like,"%"+maps.getResult().get("blr")+"%");
		}
		if(!StringUtils.isNullOrEmpty(maps.getResult().get("spzt"))){
			param.and("psbz",maps.getResult().get("spzt"));
		}
		if(!StringUtils.isNullOrEmpty(maps.getResult().get("blsj_start"))){
			param.and("blrq", TermType.gte, maps.getResult().get("blsj_start")+"00:00:00");
		}
		if(!StringUtils.isNullOrEmpty(maps.getResult().get("blsj_end"))){
			param.and("blrq",TermType.lte, maps.getResult().get("blsj_end")+"23:59:59");
		}
		ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.lsfxQuery(param);
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
	}
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_LsfxModel>> lsfx_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_LsfxModel>> result= kssService.lsfx_query(queryParam);
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
	public ResponseMessage<String> lsfx_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LsfxModel data) {
		return kssService.lsfx_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lsfx_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LsfxModel data) {
		return kssService.lsfx_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_LsfxModel> lsfx_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lsfx_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> lsfx_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lsfx_deleteByKey(id);
	}
}
