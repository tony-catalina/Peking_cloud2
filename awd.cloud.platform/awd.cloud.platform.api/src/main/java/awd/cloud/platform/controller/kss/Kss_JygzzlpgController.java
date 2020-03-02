/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.JygzzlpgModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JygzzlpgModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
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
@RequestMapping("/v4/kss/jygzzlpg")
@Api(tags = "kss-jygzzlpg",description = "Jygzzlpg") 
public class Kss_JygzzlpgController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;

	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_JygzzlpgModel>> jygzzlpg_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JygzzlpgModel>> result= kssService.jygzzlpg_query(queryParam);
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
	public ResponseMessage<String> jygzzlpg_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JygzzlpgModel data) {
		return kssService.jygzzlpg_save(data);
	}

	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jygzzlpg_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JygzzlpgModel data) {
		return kssService.jygzzlpg_updateByKey(id, data);
	}	

	@OpenAPI
	public ResponseMessage<Kss_JygzzlpgModel> jygzzlpg_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jygzzlpg_getByKey(id);
	}
	
	@OpenAPI
	public ResponseMessage<Integer> jygzzlpg_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jygzzlpg_deleteByKey(id);
	}

	/**
	 * @api {get} /v4/kss/jypg/jypgList 教育评估查询
	 * @apiVersion 0.4.0
	 * @apiName jypgList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 教育评估查询
	 *
	 * @apiParam {String} appcode 												应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh          				                        监所编号
	 * @apiSuccess {String}jsbhString          				                监所编号（已转换）
	 * @apiSuccess {String}rybh          				                        人员编号
	 * @apiSuccess {String}cjhywzs          				                	出所后有无住所
	 * @apiSuccess {String}cjhywzsString       				                出所后有无住所（已转换）
	 * @apiSuccess {String}cjhknjzd          				                	岀监后可能居住地
	 * @apiSuccess {String}rzrcqk          				                    认罪认错情况
	 * @apiSuccess {String}rzrcqkString          				                认罪认错情况（已转换）
	 * @apiSuccess {String}jsnbx          				                		监所内表现
	 * @apiSuccess {String}jsnbxString				                        	监所内表现（已转换）
	 * @apiSuccess {String}wffzs   				                    		违法犯罪史
	 * @apiSuccess {String}jstc   				                    			技术特长
	 * @apiSuccess {String}zyyx   				                    			择业意向
	 * @apiSuccess {String}czwt   				                    			存在问题
	 * @apiSuccess {String}tskn   				                    			特殊困难
	 * @apiSuccess {String}mxfzqx   				                    		有无犯罪倾向
	 * @apiSuccess {String}mxfzqxString   				                    	有无犯罪倾向（已转换）
	 * @apiSuccess {String}sjry   				                    			"三假"人员
	 * @apiSuccess {String}sjryString   				                    	"三假"人员（已转换）
	 * @apiSuccess {String}swry   				                    			"三无"人员
	 * @apiSuccess {String}swryString   				                    	"三无"人员（已转换）
	 * @apiSuccess {String}ybbjdx   				                    		一般帮教对象
	 * @apiSuccess {String}ybbjdxString   				                    	一般帮教对象（已转换）
	 * @apiSuccess {String}sfylrhlw   				                    		是否已录入互联网
	 * @apiSuccess {String}sfylrhlwString   				                    是否已录入互联网（已转换）
	 * @apiSuccess {String}azbjgzjy   				                    		安置帮教工作建议
	 * @apiSuccess {String}djrq   				                    			登记日期
	 * @apiSuccess {String}djr   				                    			登记人
	 * @apiSuccess {String}bz   				                    			备注
	 * @apiSuccess {String}state          				                        状态
	 * @apiSuccess {String}stateString          				                状态(已转换)
	 * @apiSuccess {String}creator          				                    创建人
	 * @apiSuccess {String}createtime          				                创建时间
	 * @apiSuccess {String}updator          				                    修改人
	 * @apiSuccess {String}updatetime          				                修改时间

	 * @apiSuccess {String}message                                              返回信息
	 * @apiSuccess {String}result                                               返回结果
	 * @apiSuccess {String}total                                                返回总数
	 * @apiSuccess {String}data                                                 返回数据
	 * @apiSuccess {String}status                                               返回状态
	 * @apiSuccess {String}timestamp                                            时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *        	id: "11000011420200217000055"
	 * 			jsbh: "110000114"
	 * 			jsbhString: "北京市第一看守所"
	 * 			rybh: "310000111201906200007"
	 * 			cjhywzs: "1"
	 * 			cjhywzsString: "正常"
	 * 			cjhknjzd: "居住地"
	 * 			rzrcqk: "2"
	 * 			rzrcqkString: "良好"
	 * 			jsnbx: "2"
	 * 			jsnbxString: "良好"
	 * 			wffzs: "违法犯罪史"
	 * 			jstc: "技术特长"
	 * 			zyyx: "择业意向"
	 * 			czwt: "存在问题"
	 * 			tskn: "特殊困难"
	 * 			mxfzqx: "1"
	 * 			mxfzqxString: "正常"
	 * 			sjry: "1"
	 * 			sjryString: "是"
	 * 			swry: "1"
	 * 			swryString: "是"
	 * 			ybbjdx: ""
	 * 			ybbjdxString: ""
	 * 			sfylrhlw: ""
	 * 			sfylrhlwString: null
	 * 			azbjgzjy: "安置帮教工作建议"
	 * 			djrq: "2019-12-29 12:34:49"
	 * 			djr: "登记人"
	 * 			bz: "备注"
	 * 			state: "R2"
	 * 			stateString: "有效"
	 * 			creator: "管理员"
	 * 			createtime: "2020-02-17 12:34:40"
	 * 			updator: ""
	 * 			updatetime: null
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578447454182
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 *    json:{
	 *     		"rybh": "人员编号（必填:最大长度：21）",
	 *          "page": "当前页数",
	 *          "rows": "一页数据量",
	 *          "sort": "id",
	 *          "order": "desc"
	 *         }
	 *
	 */
	//id,jsbh,jsbhString,type,typeString,name,xjrq,xjr,ywyc,ycqk,bz,dbrzsfzc,fsrzsfzc,zsjcsbsfzc,ywycString
	@OpenAPI
	@ApiOperation("教育评估查询")
	@GetMapping("/jypgList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> jypgList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/jypg/jypgList";
		String state = request.getParameter("state");
		try{
			ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
			if (maps.getStatus() != 200){
				return ResponseMessage.error(maps.getMessage());
			}
			QueryParam queryParam = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)){
				queryParam.and("jsbh", TermType.eq, jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))){
				queryParam.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, queryParam, state);
			System.err.println("param--" + JSON.toJSONString(queryParam));

			ResponseMessage<PagerResult<JygzzlpgModel>> result = kssServerApis.jypgList(queryParam);
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
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/jypg/jypgAdd 教育评估保存
	 * @apiVersion 0.4.0
	 * @apiName jypgAdd
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 教育评估保存
	 *
	 * @apiParam {String} appcode 						    应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			    成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			    时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "保存成功!",
	 *   "result": "保存成功",
	 *   "status": 200,
	 *   "timestamp": 1578886392184
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:{
	 *   "entity":[
	 *     {
	 *     	"rybh": "人员编号（必填; 最大长度:21）",
	 *     	"cjhywzs": "出所后有无住所（必填; 最大长度:1）",
	 *     	"cjhknjzd": "出监后可能居住地（必填; 最大长度:120）",
	 *     	"rzrcqk": "认罪认错情况（必填; 最大长度:1）",
	 *      "jsnbx": "监所内表现（必填; 最大长度:1）",
	 *      "wffzs": "违法犯罪史（必填）",
	 *      "jstc": "技术特长（必填）",
	 *      "zyyx": "择业意向（必填）",
	 *      "czwt": "存在问题（必填）",
	 *      "tskn": "特殊困难（必填; 最大长度:1）",
	 *      "mxfzqx": "明显犯罪倾向（必填; 最大长度:1）",
	 *      "sjry": ""三假"人员（必填; 最大长度:1）",
	 *      "swry": ""三无"人员（必填; 最大长度:1）",
	 *      "djrq": "登记日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "djr": "登记人（必填; 最大长度:30）,
	 *      "sfylrhlw": "是否已录入互联网（必填; 最大长度:1）,
	 *      "azbjgzjy": "安置帮教工作建议（必填）,
	 *      "bz": "备注",
	 *      "creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"rybh":".{1,21}","cjhywzs":".{1,1}","cjhknjzd":".{1,120}","rzrcqk":".{1,1}","jsnbx":".{1,1}","tskn":".{1,1}","mxfzqx":".{1,1}","sjry":".{1,1}","swry":".{1,1}","djrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djr":".{1,30}","sfylrhlw":".{1,1}","creator":".{1,30}"}
	@ApiOperation("教育评估保存")
	@PostMapping("/jypglAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jypglAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
		String interfaceId = "/v4/kss/jypg/jypglAdd";
		try{
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
				return ResponseMessage.error(msg.getMessage());
			}
			List<JygzzlpgModel> list = JSONArray.parseArray(map.get("entity").toString(),JygzzlpgModel.class);
			JygzzlpgModel jygzzlpgModel = list.get(0);
			jygzzlpgModel.setState("R2");
			jygzzlpgModel.setJsbh(jsbh);
			jygzzlpgModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.jypgSave(jygzzlpgModel);
			if (result.getStatus() == 200){
				result.setMessage("保存成功！");
			} else {
				result.setMessage("服务异常，保存失败！");
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/jypg/jypgUpdate 教育评估修改
	 * @apiVersion 0.4.0
	 * @apiName jypgUpdate
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription 教育评估修改
	 *
	 * @apiParam {String} appcode 						    应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集（必填）
	 *
	 * @apiSuccess {String} message         			    成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			    时间戳
	 *
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "修改成功!",
	 *   "result": "修改成功",
	 *   "status": 200,
	 *   "timestamp": 1578886392184
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:{
	 *   "entity":[
	 *     {
	 *      "id":"id(必填; 最大长度：23)",
	 *     	"rybh": "人员编号（必填; 最大长度:21）",
	 *     	"cjhywzs": "出所后有无住所（必填; 最大长度:1）",
	 *     	"cjhknjzd": "出监后可能居住地（必填; 最大长度:120）",
	 *     	"rzrcqk": "认罪认错情况（必填; 最大长度:1）",
	 *      "jsnbx": "监所内表现（必填; 最大长度:1）",
	 *      "wffzs": "违法犯罪史（必填）",
	 *      "jstc": "技术特长（必填）",
	 *      "zyyx": "择业意向（必填）",
	 *      "czwt": "存在问题（必填）",
	 *      "tskn": "特殊困难（必填; 最大长度:1）",
	 *      "mxfzqx": "明显犯罪倾向（必填; 最大长度:1）",
	 *      "sjry": ""三假"人员（必填; 最大长度:1）",
	 *      "swry": ""三无"人员（必填; 最大长度:1）",
	 *      "djrq": "登记日期（必填;格式:yyyy-MM-dd hh:mm:ss）",
	 *      "djr": "登记人（必填; 最大长度:30）,
	 *      "sfylrhlw": "是否已录入互联网（必填; 最大长度:1）,
	 *      "azbjgzjy": "安置帮教工作建议（必填）,
	 *      "bz": "备注",
	 *      "creator": "创建人(必填; 最大长度:50)"
	 *     }
	 *   ]
	 * }
	 *
	 */
	//{"id":".{1,23}","rybh":".{1,21}","cjhywzs":".{1,1}","cjhknjzd":".{1,120}","rzrcqk":".{1,1}","jsnbx":".{1,1}","tskn":".{1,1}","mxfzqx":".{1,1}","sjry":".{1,1}","swry":".{1,1}","djrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","djr":".{1,30}","sfylrhlw":".{1,1}","creator":".{1,30}"}
	@ApiOperation("教育评估修改")
	@PostMapping("jypgUpdate")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jypgUpdate(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		String interfaceId = "/v4/kss/jypg/jypgUpdate";
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
			if (msg.getStatus() != 200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<JygzzlpgModel> list = JSONArray.parseArray(map.get("entity").toString(),JygzzlpgModel.class);
			JygzzlpgModel jygzzlpgModel = list.get(0);
			jygzzlpgModel.setState("R2");
			jygzzlpgModel.setJsbh(jsbh);
			jygzzlpgModel.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.jypgUpdate(jygzzlpgModel.getId(),jygzzlpgModel);
			if (result.getStatus() == 200){
				result.setMessage("修改成功！");
			} else {
				result.setMessage("服务异常，修改失败！");
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("修改失败！");
		}
	}
}
