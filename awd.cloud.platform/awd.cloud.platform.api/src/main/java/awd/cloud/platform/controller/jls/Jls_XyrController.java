/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jls;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.jls.model.JkqkModel;
import awd.bj.jls.model.PhotosModel;
import awd.bj.jls.model.XyrAndPhotosModel;
import awd.bj.jls.model.XyrModel;
import awd.cloud.platform.api.JlsServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.ResultUtils;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/jls/xyr")
@Api(tags = "kss-xyr",description = "Xyr") 
public class Jls_XyrController extends PublicService {

	@Autowired
	private JlsServerApis jlsServerApis;

	

	//快速入所查询接口
	@OpenAPI
	@ApiOperation("快速入所查询记录")
	@GetMapping("xyrQueryForPage")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> xyrQueryForPage(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jls/xyr/xyrQueryForPage";
		//在押人员
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				param.and("xm", TermType.in, maps.getResult().get("xm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
				param.and("bm", TermType.eq, maps.getResult().get("bm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
				param.and("ay", TermType.eq, maps.getResult().get("ay"));
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<XyrModel>> list = jlsServerApis.xyrQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(list));

			//封装需要的数据
			Map<String, Object> result = Maps.newHashMap();
			result.put("total", list.getResult().getTotal());
			result.put("rows", list.getResult().getData());

			return ResponseMessage.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}
	
	//检验凭证查询接口
	@OpenAPI
	@ApiOperation("检验凭证查询记录")
	@GetMapping("jypzList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jypzList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jls/xyr/jypzList";
		//在押人员
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}


			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				param.and("xm", TermType.in, maps.getResult().get("xm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("bm"))) {
				param.and("bm", TermType.eq, maps.getResult().get("bm"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("ay"))) {
				param.and("ay", TermType.eq, maps.getResult().get("ay"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
				param.and("xb", TermType.eq, maps.getResult().get("xb"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rsrqStart"))) {
				param.and("rsrqStart", TermType.eq, maps.getResult().get("rsrqStart"));
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rsrqEnd"))) {
				param.and("rsrqEnd", TermType.eq, maps.getResult().get("rsrqEnd"));
			}
			param.and("czzt",TermType.in,"03,07");
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));

			ResponseMessage<PagerResult<XyrModel>> list = jlsServerApis.xyrQueryForPage(param);
			System.err.println("result"+JSON.toJSONString(list));

			//封装需要的数据
			Map<String, Object> result = Maps.newHashMap();
			result.put("total", list.getResult().getTotal());
			result.put("rows", list.getResult().getData());

			return ResponseMessage.ok(result);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	@ApiOperation("嫌疑人快速入所的保存操作")
    @PostMapping("/ksrsSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> ksrs_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		 
		//接口id
        String interfaceId = "/v4/jls/xyr/ksrsSave";
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
            List<XyrModel> modelList = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);
            //List<PhotosModel> photosModel = JSONArray.parseArray(map.get("entity").toString(), PhotosModel.class);
            XyrAndPhotosModel xyrAndPhotosModel = new XyrAndPhotosModel(); //通过bean传输
            PhotosModel photosModel;
            List<PhotosModel> photosModelList = Lists.newArrayList();
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            //设置基础属性
            modelList.get(0).setJsbh(jsbh);
            modelList.get(0).setCreatetime(new Date());
            modelList.get(0).setCreator(maps.getResult().get("creator").toString());
            modelList.get(0).setState("R2");
            modelList.get(0).setCzzt("03");
            xyrAndPhotosModel.setXyrModel(modelList.get(0));
            Iterator<String> iterable = multiRequest.getFileNames();
            boolean photoL = true;
            boolean photoR = true;
            boolean photoF = true;
            while (iterable.hasNext()) {
                photosModel = initPhoto(modelList.get(0).getRybh(), jsbh, maps.getResult().get("creator").toString(), "1");
                photosModel.setYwzp("0");
                MultipartFile file = multiRequest.getFile(iterable.next());
                String fileName = file.getName();

                if ("leftPhoto".equals(fileName)) {    //实际前台传来的左侧照片
                    photosModel.setType("2");
                    photosModel.setPhoto(file.getBytes());
                    photosModelList.add(photosModel);
                    photoL = false;
                } else if ("centerPhoto".equals(fileName)) {    //实际前台传来的正面照
                    photosModel.setType("1");
                    photosModel.setPhoto(file.getBytes());
                    photosModelList.add(photosModel);
                    photoF = false;

                } else if ("rightPhoto".equals(fileName)) {  //实际前台传来的右侧照片
                    photosModel.setType("3");
                    photosModel.setPhoto(file.getBytes());
                    photosModelList.add(photosModel);
                    photoR = false;
                }
            }
            if (photoF) {
                photosModel = initPhoto(modelList.get(0).getRybh(), jsbh, maps.getResult().get("creator").toString(), "1");
                photosModelList.add(photosModel);
            }
            if (photoL) {
                photosModel = initPhoto(modelList.get(0).getRybh(), jsbh, maps.getResult().get("creator").toString(), "2");
                photosModelList.add(photosModel);

            }
            if (photoR) {
                photosModel = initPhoto(modelList.get(0).getRybh(), jsbh, maps.getResult().get("creator").toString(), "3");
                photosModelList.add(photosModel);

            }
            xyrAndPhotosModel.setPhotosModelList(photosModelList);
            ResponseMessage<String> res = jlsServerApis.saveAsKsrsOrJypz(xyrAndPhotosModel);
            if(res.getStatus() ==200) {
            	res.setMessage("保存成功");
            }else {
            	res.setMessage("保存失败");
            }
           return res;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务异常，保存失败!");
		}
	}
	
	
	private PhotosModel initPhoto(String rybh, String jsbh, String userName, String type) {
        PhotosModel photosModel = new PhotosModel();
        photosModel.setRybh(rybh);
        photosModel.setJsbh(jsbh);
        photosModel.setCreator(userName);
        photosModel.setCreatetime(Calendar.getInstance().getTime());
        photosModel.setState("R2");
        photosModel.setType(type);
        photosModel.setYwzp("1");
        return photosModel;
    }
	
	
	
	@ApiOperation("校验凭证保存")
    @PostMapping("/jypzSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jypz_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		//接口id
        String interfaceId = "/v4/jls/xyr/ksrsSave";
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
            List<XyrModel> modelList = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);
            modelList.get(0).setState("R2");
            modelList.get(0).setJsbh(jsbh);
            modelList.get(0).setCreatetime(new Date());
            modelList.get(0).setCreator(maps.getResult().get("creator").toString());
            modelList.get(0).setCzzt("07");
            ResponseMessage<String> result=jlsServerApis.saveAsXyrByJypz(modelList.get(0));
            if(result.getStatus() ==200) {
            	result.setMessage("保存成功");
            }else {
            	result.setMessage("保存失败");
            }
           return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务异常，保存失败!");
		}
	}
	
	
	@ApiOperation("嫌疑人简要登记的保存操作")
    @PostMapping("/xyrSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xyr_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
        String interfaceId = "/v4/jls/xyr/xyrSave";
        
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
            List<XyrModel> modelList = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);
            String flowkey = CacheUtils.get().getFlowKey("JLS_ZCRS");
            if ("".equals(flowkey)) {
                return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
            }
            modelList.get(0).setState("R2");
            modelList.get(0).setJsbh(jsbh);
            modelList.get(0).setCreatetime(new Date());
            modelList.get(0).setCreator(maps.getResult().get("creator").toString());
            modelList.get(0).setCzzt("01");
            ResponseMessage<String> result = jlsServerApis.saveAsXyr(modelList.get(0));
            if(result.getStatus() ==200) {
            	result.setMessage("保存成功");
            }else {
            	result.setMessage("保存失败");
            }
           return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("服务异常，保存失败!");
		}
	}
	
	
	@ApiOperation("健康检查数据保存操作正常入所")
    @PostMapping("/jkjcSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jkjc_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
        String interfaceId = "/v4/jls/xyr/jkjcSave";
        
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
            List<JkqkModel> model = JSONArray.parseArray(map.get("entity").toString(), JkqkModel.class);
            if(StringUtils.isNullOrEmpty(model.get(0).getGcbh())){
                return ResponseMessage.error("人员编号未传！");
            }
            String taskid = maps.getResult().get("taskid").toString();
            String ywlcid = maps.getResult().get("ywlcid").toString();
            String jkjcjg = maps.getResult().get("jkjcjg").toString();
            model.get(0).setState("R2");
            model.get(0).setJsbh(jsbh);
            model.get(0).setCreatetime(new Date());
            model.get(0).setCreator(maps.getResult().get("creator").toString());
            ResponseMessage<String> result=jlsServerApis.jkqkSaveByZcrs(jkjcjg, taskid, ywlcid, model.get(0));
            if(result.getStatus() ==200) {
            	result.setMessage("保存成功");
            }else {
            	result.setMessage("保存失败");
            }
           return result;
        } catch (Exception e) {
        	e.printStackTrace();
			return ResponseMessage.error("服务异常，保存失败!");
		}
	}
	
}
