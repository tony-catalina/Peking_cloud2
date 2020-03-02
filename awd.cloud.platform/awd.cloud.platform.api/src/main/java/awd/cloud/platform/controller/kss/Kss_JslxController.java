/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.base.model.Variables;
import awd.bj.kss.model.JslxModel;
import awd.bj.kss.model.ShgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jslx")
@Api(tags = "kss-jslx",description = "Jslx") 
public class Kss_JslxController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;
	@Autowired
	private WorkFlowService workFlowService;

	/**
	 * @api {post} /v4/kss/jslx/jslxAdd 家属联系保存
	 * @apiVersion 0.4.0
	 * @apiName jslxAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 家属联系保存.
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
	 *    "message": "保存成功!",
	 *    "result": {
	 *     "id": "11000011420200110000109"
	 *   	},
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
	 *					"rybh":"人员编号(必填；最大长度:21)",
	 *       			"jbxxSnbh":"所内编号(最大长度:9)",
	 *       			"jbxxXm":"姓名(最大长度:30)",
	 *       			"jbxxJsh":"监室号(最大长度:4)",
	 *       			"sqr":"申请人(必填；最大长度:30)",
	 *       			"sqsj":"申请时间(必填；格式：yyyy-MM-dd hh:mm:ss)",
	 *       			"lyjsx":"理由及事项(必填；)"
	 *          		}]
	 *     	  }
	 */

	//{"sqr":"\\S{1,20}","rybh":"\\d{1,21}","sqsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"rybh":"110000114201912040014","jbxxJsh":"9009","jbxxSnbh":"20190299","jbxxXm":"123","lyjsx":"sdd","sqsj":"2020-01-06 19:22:22","sqr":"aa"}]}

	@ApiOperation("家属联系保存")
	@PostMapping("/jslxAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String,String>> jslxAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jslx/jslxAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_JslxModelDO jslxModel=JSONObject.parseObject(rslist.get(0).toString(), Kss_JslxModelDO.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String csid = CacheUtils.get().getFlowKey( "kss_jslx".toUpperCase());
			System.err.println("csid--"+csid);
			if (StringUtils.isNullOrEmpty(csid)) {
				return ResponseMessage.error("获取流程实例失败，请联系管理员！");
			}
			Variables variables = new Variables();
			variables.setRybh(jslxModel.getRybh());
			variables.setJsbh(jsbh);
			System.err.println("jslxAdd=="+ JSONUtil.toJson(variables));
			Result r = workFlowService.findPersonalTaskList("admin", "1", variables);
			System.err.println("r--"+JSON.toJSONString(r));
			List<Map<String, Object>> obj = (List<Map<String, Object>>) r.getResult();
			for (Map<String, Object> mapss : obj) {
				String processDefinitionId = mapss.get("processDefinitionId").toString();
				if (StringUtils.isNullOrEmpty(processDefinitionId)) {
					continue;
				}
				if (csid.equals(processDefinitionId)) {
					return ResponseMessage.error("所选人员有存在于该流程中，无法重办理！！");
				}
			}
			String creator=(String) rslist.get(0).get("creator");
			if (StringUtils.isNullOrEmpty(creator)){
				return ResponseMessage.error("creator不能为空！");
			}
			jslxModel.setState("R2");
			jslxModel.setJsbh(jsbh);
			jslxModel.setCreator(creator);
			jslxModel.setCreatetime(new Date());
			jslxModel.setPsbz("0");
			jslxModel.setPastable("1");
			ResponseMessage<Map<String,String>> result =kssServerApis.jslxAddFlow(csid,jslxModel);

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


	/**
	 * @api {post} /v4/kss/jslx/ldspAdd 领导审批保存
	 * @apiVersion 0.4.0
	 * @apiName ldspAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 领导审批保存.
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
	 *					"rybh":"人员编号(必填；最大长度:21)",
	 *       			"jbxxSnbh":"所内编号(最大长度:8)",
	 *       			"jbxxXm":"姓名(最大长度:50)",
	 *       			"jbxxJsh":"监室号(最大长度:4)",
	 *       			"sqr":"申请人(必填；最大长度:30)",
	 *       			"sqsj":"申请时间(必填；格式：yyyy-MM-dd hh:mm:ss)",
	 *       			"psbz":"批示标志(必填；最大长度:2)",
	 * 	  				"ldyj":"领导意见(必填；最大长度:200)",
	 * 	  				"blr":"办理人(必填；最大长度:30)",
	 * 	  				"blsj":"办理时间(必填；格式：yyyy-MM-dd hh:mm:ss)"
	 *
	 *          		}]
	 *     	  }
	 */

	@ApiOperation("领导审批保存")
	@PostMapping("/ldspAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String,String>> ldspAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"rybh":"\\d{1,21}","sqr":"\\S{1,30}","sqsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","psbz":"\\d{1,2}","ldyj":".{1,200}","blr":"\\S{1,30}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
		//{"entity":[{"processDefinitionKey":"kss_jslx","taskid":"5205071","ywlcid":"5255737","type":"1","rybh":"110000114201912040014","jbxxSnbh":"20190298","jbxxXm":"123","snbh":"20190298","xm":"123","jsh":"9009","jbxxJsh":"9009","sqr":"dsad","sqsj":"2020-01-01 19:22:22","psbz":"1","ldyj":"fsafsd","blr":"aaa","blsj":"2020-01-01 19:22:22"}]}
		//接口id
		String interfaceId = "/v4/kss/jslx/ldspAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			Kss_LdspModelDO ldspEntity=JSONObject.parseObject(rslist.get(0).toString(), Kss_LdspModelDO.class);
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			String taskid = (String) rslist.get(0).get("taskid");
			String ywlcid = (String) rslist.get(0).get("ywlcid");
			String type = (String) rslist.get(0).get("type");
			if (StringUtils.isNullOrEmpty(taskid)) {
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(ywlcid)) {
				return ResponseMessage.error("ywlcid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(type)) {
				return ResponseMessage.error("type不能为空！");
			}
			ldspEntity.setYwlcid(ywlcid);
			ldspEntity.setJsbh(jsbh);
			ldspEntity.setCreatetime(new Date());
			ldspEntity.setState("R2");
			ResponseMessage<Map<String,String>> result =kssServerApis.saveJslxLdsp(taskid,ldspEntity,type);

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

	/**
	 * @api {post} /v4/kss/jslx/saveJslx 接待民警家属联系保存
	 * @apiVersion 0.4.0
	 * @apiName saveJslx
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 接待民警家属联系保存.
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
	 *    "message": "保存成功!",
	 *    "result": {
	 *     "id": "11000011420200110000109"
	 *   	},
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
	 *					"rybh":"人员编号(必填；最大长度:21)",
	 *       			"lxqk":"联系情况(必填；)",
	 *       			"rsqsfyjb":"入所前是否有疾病(必填；最大长度:1)",
	 *       			"sfyjzbs":"是否有家族病史(必填；最大长度:1)",
	 *       			"sfcqfyhfyzmyw":"是否长期服药或服用专门药物(必填；最大长度:1)",
	 *       			"lxr":"办理人(必填；最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"lxr":"\\S{1,30}","rybh":"\\d{1,21}","rsqsfyjb":"\\d{1}","sfyjzbs":"\\d{1}","sfcqfyhfyzmyw":"\\d{1}"}
	//{"entity":[{"data":{"taskid":"5307583","ywlcid":"5303124","rybh":"110000114201912040014","jbxxJsh":"9009","jbxxSnbh":"20190299","jbxxXm":"123","lxr":"sd","lxqk":"dsadasd"},"creator":"管理员","rsqsfyjb":"0","sfyjzbs":"0","sfcqfyhfyzmyw":"0"}]}

	@ApiOperation("接待民警家属联系保存")
	@PostMapping("/saveJslx")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveJslx(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jslx/saveJslx";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String jslx=rslist.get(0).get("data").toString();
			String shgxs=(String)(rslist.get(0).get("shgxs"));
			String creator=(String)(rslist.get(0).get("creator"));
			String taskid=(String)((Map<String, Object>)rslist.get(0).get("data")).get("taskid");
			String lxqk=(String)((Map<String, Object>)rslist.get(0).get("data")).get("lxqk");
			Kss_JslxModelDO jslxModel=new Kss_JslxModelDO();
			List<ShgxModel> shgxList = new ArrayList<ShgxModel>();
			if(!StringUtils.isNullOrEmpty(jslx)) {
				jslxModel = JSONObject.parseObject(rslist.get(0).get("data").toString(), Kss_JslxModelDO.class);
			}
			if(!StringUtils.isNullOrEmpty(shgxs)) {
				shgxList = JSONUtil.toList(shgxs, ShgxModel.class);
			}
			String rsqsfyjb=(String) ((Map<String, Object>)rslist.get(0)).get("rsqsfyjb");
			String sfyjzbs=(String) ((Map<String, Object>)rslist.get(0)).get("sfyjzbs");
			String sfcqfyhfyzmyw=(String) ((Map<String, Object>)rslist.get(0)).get("sfcqfyhfyzmyw");
			Map<String, Object> entity=(Map<String, Object>)rslist.get(0).get("data");
			entity.put("rsqsfyjb",rsqsfyjb);
			entity.put("sfyjzbs",sfyjzbs);
			entity.put("sfcqfyhfyzmyw",sfcqfyhfyzmyw);
			String jsons="{'entity':["+ JSON.toJSONString(entity)+"]}";
			Map<String, Object> maplss=JSONObject.parseObject(jsons, HashMap.class);
			maplss.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(maplss);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			if(StringUtils.isNullOrEmpty(lxqk)) {
				return ResponseMessage.error("lxqk不能为空！");
			}
			if(StringUtils.isNullOrEmpty(creator)) {
				return ResponseMessage.error("creator不能为空！");
			}
			if(StringUtils.isNullOrEmpty(taskid)) {
				return ResponseMessage.error("taskid不能为空！");
			}
			//处理家属联系表
			jslxModel.setState("R2");
			jslxModel.setJsbh(jsbh);
			jslxModel.setUpdator(creator);
			jslxModel.setUpdatetime(new Date());
			jslxModel.setRsqsfyjb(request.getParameter("rsqsfyjb"));
			jslxModel.setSfyjzbs(request.getParameter("sfyjzbs"));
			jslxModel.setSfcqfyhfyzmyw(request.getParameter("sfcqfyhfyzmyw"));
			System.err.println(JSONObject.toJSONString(jslxModel));
			//封装成一个map传递到后台
			Map<String, Object> mapls = Maps.newHashMap();
			mapls.put("jslxModel", jslxModel);
			mapls.put("shgxs", shgxList);
			mapls.put("rybh", jslxModel.getRybh());
			mapls.put("jsbh", jslxModel.getJsbh());
			ResponseMessage<String> result =kssServerApis.saveJslx(taskid,mapls);

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
	public ResponseMessage<PagerResult<Kss_JslxModel>> jslx_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JslxModel>> result= kssService.jslx_query(queryParam);
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
	public ResponseMessage<String> jslx_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JslxModel data) {
		return kssService.jslx_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jslx_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JslxModel data) {
		return kssService.jslx_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JslxModel> jslx_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jslx_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jslx_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jslx_deleteByKey(id);
	}
}
