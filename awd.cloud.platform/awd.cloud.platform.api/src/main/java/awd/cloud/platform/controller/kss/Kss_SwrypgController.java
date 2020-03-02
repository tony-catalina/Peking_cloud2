/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.JstzModel;
import awd.bj.kss.model.SwrypgModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_JstzLdspModel;
import awd.cloud.platform.service.PublicService;
import com.alibaba.fastjson.JSON;
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
import awd.cloud.platform.model.kss.Kss_SwrypgModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/swrypg")
@Api(tags = "kss-swrypg",description = "Swrypg") 
public class Kss_SwrypgController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @api {post} /v4/kss/swrypg/swry_add 涉维人员保存
	 * @apiVersion 0.4.0
	 * @apiName swry_add
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 涉维人员保存.
	 *
	 @Autowired
	 private KssServerApis kssServerApis;

	  * @apiParam {String} appcode 					    应用代码(必填)
	 * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 							保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 * 	  	            "sfswpg":"是否涉维评估(必填;最大字段长度：1)",
	 * 					"pgrq":"评估日期(必填;格式：yyyy-MM-dd hh:mm:ss)",
	 *  				"swpgzb":"涉维评估指标(必填;最大字段长度：500)",
	 *  				"pgr":"评估人(必填;最大字段长度：30)",
	 *  				"rybh":"人员编号(必填;最大字段长度：21)"
	 *          		}]
	 *     	   }
	 */

	//{"sfswpg":"\\d{1}","rybh":"\\d{1,21}","swpgzb":"\\S{1,500}","pgr":"\\S{1,30}","pgrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"rybh":"110000114201912040010","sfswpg":"1","pgr":"aaa","pgrq":"2020-01-15 13:12:43","swpgzb":"每日礼拜"}]}
	@ApiOperation("涉维人员保存")
	@PostMapping("/swry_add")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})

	@OpenAPI
	public ResponseMessage<String> swry_add(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/swrypg/swry_add";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			SwrypgModel swrypg=JSONObject.parseObject(rslist.get(0).toString(),SwrypgModel.class);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			swrypg.setPgrq(sdf.parse((String) rslist.get(0).get("pgrq")));
			swrypg.setJsbh(jsbh);
			swrypg.setState("R2");
			swrypg.setCreator((String) rslist.get(0).get("pgr"));
			swrypg.setCreatetime(new Date());
			System.err.println("swrypg--------"+swrypg);

			ResponseMessage<String> result = kssServerApis.saveSwrypg(swrypg);

			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_SwrypgModel>> swrypg_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_SwrypgModel>> result= kssService.swrypg_query(queryParam);
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
	public ResponseMessage<String> swrypg_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_SwrypgModel data) {
		return kssService.swrypg_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> swrypg_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_SwrypgModel data) {
		return kssService.swrypg_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_SwrypgModel> swrypg_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.swrypg_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> swrypg_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.swrypg_deleteByKey(id);
	}
}
