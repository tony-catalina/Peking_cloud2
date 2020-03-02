/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.JkrzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JkrzModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jkrz")
@Api(tags = "kss-jkrz",description = "Jkrz") 
public class Kss_JkrzController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {post} /v4/kss/jkrz/jkrzSave 监控日志保存
	 * @apiVersion 0.4.0
	 * @apiName jkrzSave
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 监控日志保存
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * {
	 *      "appcode":"应用代码(必填)",
	 *      "jsbh":"监所编号(必填; 最大长度:9)",
	 *      json:{"entity":[{
	 *              "kssj":"开始时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *              "jssj":"结束时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *              "jkr":"监控人(必填; 最大长度:50)",
	 *              "jksc":"监控时常(必填; 最大长度:5)",
	 *              "jqh":"监区号(必填; 最大长度:2)",
	 *              "jkqk":"监控情况"(必填; 最大长度:255)",
	 *              "bz":"备注(最大长度:255)"
	 *           }]
	 *      }
	 * }
	 *
	 */
	@ApiOperation("新增监控日志")
	@PostMapping("/jkrzSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	  public ResponseMessage<String> jkrzSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jkrz/jkrzSave";
		//校验权限
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}
		try {
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			JkrzModel jkrzModel =new JkrzModel();
			jkrzModel.setState("R2");
			jkrzModel.setJsbh(jsbh);
			if(StringUtils.isNullOrEmpty(maps.getResult().get("jkr"))){
				jkrzModel.setJkr(maps.getResult().get("jkr").toString());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("jkqk"))){
				jkrzModel.setJkqk(maps.getResult().get("jkqk").toString());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("bz"))){
				jkrzModel.setBz(maps.getResult().get("bz").toString());
			}
			if (StringUtils.isNullOrEmpty(maps.getResult().get("jksc"))) {
				int jksc=Integer.parseInt(maps.getResult().get("jksc").toString());
				jkrzModel.setJksc(jksc);
			}

			SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(StringUtils.isNullOrEmpty(maps.getResult().get("jssj"))){
				Date jssj=spl.parse(maps.getResult().get("jssj").toString());
				jkrzModel.setJssj(jssj);
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("kssj"))){
				Date kssj=spl.parse(maps.getResult().get("kssj").toString());
				jkrzModel.setKssj(kssj);
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("creator"))){
				jkrzModel.setCreator(maps.getResult().get("creator").toString());
			}

			jkrzModel.setCreatetime(new Date());
			ResponseMessage<String> result =kssServerApis.jkrzSave(jkrzModel);

            return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	  }

	/**
	 * @api {get} /v4/kss/jkrz/jkrzQuery 监控日志查询
	 * @apiVersion 0.4.0
	 * @apiName jkrzQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监控日志查询.
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
	 * @apiParam {String} json 							查询参数集
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 *
	 * @apiSuccess {String} kssj:                       开始时间
	 * @apiSuccess {String} jssj:                       结束时间
	 * @apiSuccess {String} jksc:                       监控时常
	 * @apiSuccess {String} jkr:                       监控人
	 * @apiSuccess {String} bz:                        备注
	 * @apiSuccess {String} stateString:               状态
	 * @apiSuccess {String}creator:                    创建人
	 * @apiSuccess {String}createtime:                 创建时间
	 * @apiSuccess {String}updator:                    更新人
	 * @apiSuccess {String}updatetime:                 更新时间
	 * @apiSuccess {String}jqh:                        监区号
	 */
	@OpenAPI
	@ApiOperation("监控日志查询")
	@GetMapping("/jkrzQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jkrzQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jkrz/jkrzQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
		if (maps.getStatus() != 200) {
			return ResponseMessage.error(maps.getMessage());
		}

		QueryParam param = new QueryParam();

		if (!StringUtils.isNullOrEmpty(maps.getResult().get("jkr"))){
			String jkr = maps.getResult().get("jkr").toString();
			System.err.println("=============================================================");
			System.err.println("jkr:"+jkr);
			param.and("jkr", TermType.like,"%"+jkr+"%");
		}
		//开始时间

		if (!StringUtils.isNullOrEmpty(maps.getResult().get("kssj"))){
			String kssj = maps.getResult().get("kssj").toString();
			System.err.println("kssj:"+kssj);
			param.and("kssj",TermType.gte,kssj+" 00:00:00");
		}
		//结束时间

		if (!StringUtils.isNullOrEmpty(maps.getResult().get("jssj"))){
			String jssj = maps.getResult().get("jssj").toString();
			System.err.println("jssj:"+jssj);
			System.err.println("=============================================================");
			param.and("kssj",TermType.lte,jssj+" 23:59:59");
		}
		ResponseMessage<PagerResult<JkrzModel>> result = kssServerApis.jkrzQueryForPage(param);
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
	public ResponseMessage<PagerResult<Kss_JkrzModel>> jkrz_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JkrzModel>> result= kssService.jkrz_query(queryParam);
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
	public ResponseMessage<String> jkrz_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkrzModel data) {
		return kssService.jkrz_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkrz_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkrzModel data) {
		return kssService.jkrz_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JkrzModel> jkrz_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkrz_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jkrz_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkrz_deleteByKey(id);
	}
}
