/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.PwglModel;
import awd.bj.kss.model.ZdzyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.framework.common.utils.StringUtils;
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
import awd.cloud.platform.model.kss.Kss_ZyrybxjdModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
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
@RequestMapping("/v4/kss/zyrybxjd")
@Api(tags = "kss-zyrybxjd",description = "Zyrybxjd") 
public class Kss_ZyrybxjdController extends PublicService {
	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
    private KssService kssService;



	/**
	 * @api {post} /v4/kss/zyrybxjd/addzyrybxjd 在押人员表现鉴定保存
	 * @apiVersion 0.4.0
	 * @apiName addzyrybxjd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 在押人员表现鉴定保存.
	 *

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
	 *  "message": "保存成功!",
	 *  "result": "11000011420200113000070",
	 *  "status": 200,
	 *  "timestamp": 1578892709281
	 *}
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       	"entity":[{
	 *                  "jsh": "监室号(最大长度:4)",
	 *                  "xm": "姓名(最大长度:30)",
	 *                  "bm": "别名(最大长度:30)",
	 *                  "xb": "性别(最大长度:1)",
	 *                  "csrq": "出生日期(格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "rsrq": "入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "bahj": "办案环节(最大长度:50)",
	 *                  "ay": "案由(最大长度:50)",
	 *                  "hjd": "户籍地(最大长度:120)",
	 *                  "bz":"备注(必填；)",
	 *                  "rybh": "人员编号(最大长度:21)"
	 *					"zybx":"主要表现(必填；最大长度:255)",
	 *       			"tbr":"填表人(最大长度:50)",
	 *       			"tbrq":"填表日期(格式:yyyy-MM-dd hh:mm:ss)"，
	 *  				"creator":"创建人(必填;最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"zybx":"\\S{1,255}","creator":".{1,30}"}
	//{"entity":[{"creator":"aaa","tbr":"管理员","tbrq":"","rybh":"110000114201912040010","jsh":"9009","xm":"123123","bm":"","xb":"2","csrq":"1985-12-24","rsrq":"2019-10-28 13:58:33","bahj":"12","ay":"010120","hjd":"110000","zybx":"第三方","bz":"fdsf","creator":"ddd"}]}

	@ApiOperation("在押人员表现鉴定保存")
	@PostMapping("/addzyrybxjd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> addzyrybxjd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/zyrybxjd/addzyrybxjd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String bz=(String)rslist.get(0).get("bz");
			if (StringUtils.isNullOrEmpty(bz)){
				return ResponseMessage.error("bz不能为空！");
			}
			ZdzyModel zdzyModel=JSONObject.parseObject(rslist.get(0).toString(),ZdzyModel.class);
			zdzyModel.setState("R2");
			zdzyModel.setJsbh(jsbh);
			zdzyModel.setCreatetime(new Date());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> result = kssServerApis.zdzySave(zdzyModel);
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
	public ResponseMessage<PagerResult<Kss_ZyrybxjdModel>> zyrybxjd_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_ZyrybxjdModel>> result= kssService.zyrybxjd_query(queryParam);
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
	public ResponseMessage<String> zyrybxjd_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZyrybxjdModel data) {
		return kssService.zyrybxjd_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> zyrybxjd_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ZyrybxjdModel data) {
		return kssService.zyrybxjd_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_ZyrybxjdModel> zyrybxjd_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zyrybxjd_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> zyrybxjd_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.zyrybxjd_deleteByKey(id);
	}
}
