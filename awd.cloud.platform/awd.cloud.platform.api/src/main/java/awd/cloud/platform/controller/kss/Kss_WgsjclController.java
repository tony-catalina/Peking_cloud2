package awd.cloud.platform.controller.kss;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.WgsjclModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wgsjcl")
@Api(tags = "kss-xjzcwgsjcl",description = "wgsjcl")
public class Kss_WgsjclController extends PublicService{
	
	@Autowired
    private KssServerApis kssServerApis;
	
	
	/**
     * @api {post} /v4/kss/wgsjcl/wgsjclSave 监区违规保存
	 * @apiVersion 0.4.0
     * @apiName wgsjclSave
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监区违规保存
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
     *      "entity":[
     *           {
     *              "wgqk":"违规情况(必填; 最大长度:100)",
     *              "wgqkcon":"违规情况详细
     *              "clqk":"处理方式(必填; 最大长度:50，字典 (WGCLQK))",
     *              "jqh":"监区号(必填; 最大长度:2)"
     *              "wgsj": 违规时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *              "bz": 备注(必填; 最大长度:255)",
     *              "creator": 创建人(必填; 最大长度:30)"
     *           }
     *        ]
     *      }
     * }
     *
     */
	@ApiOperation("监区违规新增")
    @PostMapping("/wgsjclSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
	public ResponseMessage<String> wgsjcl_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
        String interfaceId = "/v4/kss/wgsjcl/wgsjclSave";
        try {
        	//校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<WgsjclModelDO> Wgsjclmodel = JSONArray.parseArray(map.get("entity").toString(), WgsjclModelDO.class);
            Wgsjclmodel.get(0).setState("R2");
            Wgsjclmodel.get(0).setJsbh(jsbh);
            Wgsjclmodel.get(0).setCreator(maps.getResult().get("creator").toString());
            Wgsjclmodel.get(0).setCreatetime(new Date());
            if (!StringUtils.isNullOrEmpty(Wgsjclmodel.get(0).getClqk())) {
				Wgsjclmodel.get(0).setClr(maps.getResult().get("creator").toString());
            	Wgsjclmodel.get(0).setClsj(new Date());
            }
            ResponseMessage<Integer> result = kssServerApis.wgglSaves(Wgsjclmodel.get(0));
            if (1 == result.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.error("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseMessage.ok("保存成功");
	}
	
	
	
	/**
     * @api {post} /v4/kss/jsWgsjcl/jsWgsjclSave 监室违规保存
	 * @apiVersion 0.4.0
     * @apiName jsWgsjclSave
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 监室违规保存
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
     *      "entity":[
     *           {
     *              "wgqk":"违规情况(必填; 最大长度:100)",
     *              "wgqkcon":"违规情况详细
     *              "clqk":"处理方式(必填; 最大长度:50，字典 (WGCLQK))",
     *              "jsh":"监室号(必填; 最大长度:4)"
     *              "wgsj": 违规时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *              "bz": 备注(必填; 最大长度:255)"
     *           }
     *        ]
     *      }
     * }
     *
     */
	@ApiOperation("监室违规新增")
    @PostMapping("/jsWgsjclSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
	public ResponseMessage<String> jsWgsjcl_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
        String interfaceId = "/v4/kss/jsWgsjcl/jsWgsjclSave";
        try {
        	//校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<WgsjclModelDO> Wgsjclmodel = JSONArray.parseArray(map.get("entity").toString(), WgsjclModelDO.class);
            Wgsjclmodel.get(0).setState("R2");
            Wgsjclmodel.get(0).setJsbh(jsbh);
            Wgsjclmodel.get(0).setCreator(maps.getResult().get("creator").toString());
            Wgsjclmodel.get(0).setCreatetime(new Date());
            if (!StringUtils.isNullOrEmpty(Wgsjclmodel.get(0).getClqk())) {
				Wgsjclmodel.get(0).setClr(maps.getResult().get("creator").toString());
            	Wgsjclmodel.get(0).setClsj(new Date());
            }
            ResponseMessage<Integer> result = kssServerApis.wgglSaves(Wgsjclmodel.get(0));
            if (1 == result.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.error("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseMessage.ok("保存成功");
	}
	
	
	/**
     * @api {post} /v4/kss/ryWgsjcl/ryWgsjclSave 人员违规新增
	 * @apiVersion 0.4.0
     * @apiName ryWgsjcllSave
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 人员违规新增
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
     *      "entity":[
     *           {
     *              "wgqk":"违规情况(必填; 最大长度:100)",
     *              "wgqkcon":"违规情况详细
     *              "clqk":"处理方式(必填; 最大长度:50，字典 (WGCLQK))",
     *              "rybh":"人员编号(必填; 最大长度:21)"
     *              "wgsj": 违规时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *              "bz": 备注(必填; 最大长度:255)"
     *              "ywyc":"有无异常(最大长度:1)"
     *              "ycqk":"异常情况(最大长度:100)",
     *              "ycqkcon":"异常情况详细"
     *           }
     *        ]
     *      }
     * }
     *
     */
	@ApiOperation("人员违规新增")
    @PostMapping("/ryWgsjclSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
	public ResponseMessage<String> ryWgsjcl_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
        String interfaceId = "/v4/kss/ryWgsjcl/ryWgsjclSave";
        try {
        	//校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<WgsjclModelDO> Wgsjclmodel = JSONArray.parseArray(map.get("entity").toString(), WgsjclModelDO.class);
            Wgsjclmodel.get(0).setState("R2");
            Wgsjclmodel.get(0).setJsbh(jsbh);
            Wgsjclmodel.get(0).setCreator(maps.getResult().get("creator").toString());
            Wgsjclmodel.get(0).setCreatetime(new Date());
            if (!StringUtils.isNullOrEmpty(Wgsjclmodel.get(0).getClqk())) {
				Wgsjclmodel.get(0).setClr(maps.getResult().get("creator").toString());
            	Wgsjclmodel.get(0).setClsj(new Date());
            }
            ResponseMessage<Integer> result = kssServerApis.wgglSaves(Wgsjclmodel.get(0));
            if (1 == result.getResult()) {
                return ResponseMessage.ok("保存成功");
            } else {
            	return ResponseMessage.error("保存失败");
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return ResponseMessage.ok("保存成功");
	}
}
