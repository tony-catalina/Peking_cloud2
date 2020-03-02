/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.SqfxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.UploadFileModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/sqfx")
@Api(tags = "kss-sqfx",description = "Sqfx") 
public class Kss_SqfxController extends PublicService{
	
	@Autowired
    private KssService kssService;
	
	@Autowired
	private KssServerApis  kssServerApis;


	
	
	/**
     * @api {get} /v4/kss/sqfx/sqfxListQuery 所情分析查询
     * @apiVersion 0.4.0
     * @apiName sqfxListQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 所情分析查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsbhString                                       监所编号（已转换）
     * @apiSuccess {String}bz                                               办理类型
     * @apiSuccess {String}cjhyry                                           办理类型（已转换）
     * @apiSuccess {String}cjrs                                             参加人数
     * @apiSuccess {String}createtime   									创建时间
     * @apiSuccess {String}creator    										创建人
     * @apiSuccess {String}hydd       										会议地点
     * @apiSuccess {String}hyjlr     										会议记录人
     * @apiSuccess {String}hyjlsj   										会议记录时间
     * @apiSuccess {String}hyjssj    										会议结束时间
     * @apiSuccess {String}hykssj     										会议开始时间
     * @apiSuccess {String}hylx       										会议类型
     * @apiSuccess {String}hylxString   									会议类型(已转换)
     * @apiSuccess {String}hylyurl     										会议录音URL
     * @apiSuccess {String}hynr    											会议内容
     * @apiSuccess {String}hyzcr  											会议主持人
     * @apiSuccess {String}state    										状态
     * @apiSuccess {String}stateString   									状态(已转换)
     * @apiSuccess {String}updatetime    									更新时间
     * @apiSuccess {String}updator       									更新人
     * 
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [{
     * 		    "id": "11000011420200117000454",
     * 			"jsbh": "110000114",
     * 			"jsbhString": "北京市第一看守所",
     * 			"jsh": "0101",
     * 			"bz": "fd",
     * 			"cjhyry": "fd",
     * 			"cjrs": "1",
     * 			"createtime": "2020-01-17 15:37:19",
     * 			"creator": "管理员",
     * 			"hydd": "北京市",
     * 			"hyjlr": "管理员",
     * 			"hyjlsj": "2020-01-17 15:37:19",
     * 			"hyjssj": "2020-01-17 15:37:19",
     * 			"hykssj": "2020-01-17 15:37:19",
     * 			"hylx": "1",
     * 			"hylxString": "",
     * 			"hylyurl": "",
     * 			"hynr": "",
     * 			"hyzcr": "ggg",
     * 			"state": "R2",
     * 			"stateString": "有效",
     * 			"updatetime": "2020-01-17 15:37:19",
     * 			"updator": 更新人
     * 		}],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579158673428
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "rybh":"110000112201911050002",
     *          "page":"1",
     *          "rows":"20",
     *          "sort":"id",
     *          "order":"desc"
     *        }
     *
     */
	@OpenAPI
    @ApiOperation("所情分析查询")
    @GetMapping("/sqfxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> sqfx_Query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		 String interfaceId = "/v4/kss/sqfx/sqfxListQuery";
		
		 try {
			 ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
	            if (maps.getStatus() != 200) {
	                return ResponseMessage.error(maps.getMessage());
	            }
	         //查询参数
	         String state="R2";
	         QueryParam qParam = new QueryParam();
	         if(!StringUtils.isNullOrEmpty(maps.getResult().get("hylx"))) {
	         	qParam.and("hylx", TermType.eq, maps.getResult().get("hylx"));
	         }
	         if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_start"))) {
	         	qParam.and("hykssj", TermType.gte, maps.getResult().get("sj_start")+" 00:00:00");
	         }
	         if(!StringUtils.isNullOrEmpty(maps.getResult().get("sj_end"))) {
	         	qParam.and("hykssj", TermType.lte, maps.getResult().get("sj_end")+" 23:59:59");
	         }
	         qParam.and("jsbh", jsbh);
	         qParam.and("state", state);
	         ResponseMessage<PagerResult<SqfxModel>> result=kssServerApis.sqfxList(qParam);
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
     * @api {post} /v4/kss/sqfx/sqfxSave  所情分析保存操作
     * @apiVersion 0.4.0
     * @apiName sqfxSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription   所情分析保存操作.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     * 		"id":"ID(必填;最大字段长度:23)"
     * 		"hylx":"会议类型(必填;最大字段长度:1)"
     * 		"hykssj":"会议开始时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
     * 		"hyjssj":"会议结束时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
     * 	    "cjrs":"参加人数(必填;最大字段长度:10)"
     * 	    "hyzcr":"会议主持人(必填;最大字段长度:21)"
     * 	    "cjhyry":"参加会议人员(最大字段长度:200)"
     * 		"hynr": "会议内容(必填;最大字段长度:200)",
     * 		"hydd": "会议地点(必填;最大字段长度:100)",
     * 		"hyjlsj": "会议记录时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * 		"hyjlr": "会议记录人(必填;最大字段长度:50)",
     * 		"bz": "备注",
     * 		"hyly": "会议录音"
     * 	}]
     * }
     *
     *
     */
	@ApiOperation("所情分析保存")
    @PostMapping("/sqfxSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> sqfx_Save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		
		String interfaceId = "/v4/kss/sqfx/sqfxSave";
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
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
        	MultipartFile hyly = multiRequest.getFile("hyly");
          //封装数据
            List<SqfxModel> sqfxModels = JSONArray.parseArray(map.get("entity").toString(), SqfxModel.class);
            sqfxModels.get(0).setState("R2");
            sqfxModels.get(0).setJsbh(jsbh);
            sqfxModels.get(0).setCreator(maps.getResult().get("creator").toString());
            sqfxModels.get(0).setCreatetime(new Date());
            if(hyly != null) {
        		UploadFileModel upfile = new UploadFileModel();
        		String fileName = hyly.getOriginalFilename();
        		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        		upfile.setFileName(fileName);
        		upfile.setFile(hyly.getBytes());
        		upfile.setExt(ext);
        		String url = kssServerApis.uploadZpDfs(upfile);
        		
        		if (url == null) {
        			url = "";
        		}
        		sqfxModels.get(0).setHylyurl(url);
        	}
           ResponseMessage<String> result=kssServerApis.sqfxSave(sqfxModels.get(0));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败");
		}
	}
	
	/**
     * @api {post} /v4/kss/sqfx/sqfxUpdate  所情分析保存修改
     * @apiVersion 0.4.0
     * @apiName sqfxUpdate
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription   所情分析保存修改.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     * 		"id":"ID(必填;最大字段长度:23)"
     * 		"hylx":"会议类型(必填;最大字段长度:1)"
     * 		"hykssj":"会议开始时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
     * 		"hyjssj":"会议结束时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
     * 	    "cjrs":"参加人数(必填;最大字段长度:10)"
     * 	    "hyzcr":"会议主持人(必填;最大字段长度:21)"
     * 	    "cjhyry":"参加会议人员(必填;最大字段长度:200)"
     * 		"hynr": "会议内容(必填;最大字段长度:200)",
     * 		"hydd": "会议地点(必填;最大字段长度:100)",
     * 		"hyjlsj": "会议记录时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     * 		"hyjlr": "会议记录人(必填;最大字段长度:50)",
     * 		"bz": "备注(必填)",
     * 	}]
     * }
     *
     *
     */
	@ApiOperation("所情分析修改")
    @PostMapping("/sqfxUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> sqfx_Update(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		
		String interfaceId = "/v4/kss/sqfx/sqfxUpdate";
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
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
        	MultipartFile hyly = multiRequest.getFile("hyly");
          //封装数据
            List<SqfxModel> sqfxModels = JSONArray.parseArray(map.get("entity").toString(), SqfxModel.class);
            if(hyly != null) {
        		UploadFileModel upfile = new UploadFileModel();
        		String fileName = hyly.getOriginalFilename();
        		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        		upfile.setFileName(fileName);
        		upfile.setFile(hyly.getBytes());
        		upfile.setExt(ext);
        		String url = kssServerApis.uploadZpDfs(upfile);
        		
        		if (url == null) {
        			url = "";
        		}
        		sqfxModels.get(0).setUpdatetime(new Date());
        		sqfxModels.get(0).setUpdator(maps.getResult().get("creator").toString());
        		sqfxModels.get(0).setJsbh(jsbh);
        	}
            ResponseMessage<String> result=kssServerApis.sqfxUpdate(sqfxModels.get(0).getId(), sqfxModels.get(0));
            if(result.getStatus() == 200){
                result.setMessage("修改!");
            }else{
                result.setMessage("服务异常,修改失败!");
            }
            return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("修改失败");
		}
	}
}
