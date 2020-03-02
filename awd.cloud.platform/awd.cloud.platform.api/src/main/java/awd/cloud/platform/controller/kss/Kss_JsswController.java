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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.JsswModel;
import awd.bj.kss.model.ShgxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.GxswModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
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
@RequestMapping("/v4/kss/jssw")
@Api(tags = "kss-jssw",description = "Jssw") 
public class Kss_JsswController extends PublicService{
    @Autowired
	private KssServerApis kssServerApis;
    
	/**
	 * @return
	 * @api {get} /v4/kss/jssw/jsswQuery 根据人员编号查询家属送物记录
	 * @apiVersion 0.4.0
	 * @apiName jsswQuery
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据人员编号查询家属送物记录
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}xm												送物人姓名
	 * @apiSuccess {String}sqwp												申请物品
	 * @apiSuccess {String}sqsj												申请时间
	 * @apiSuccess {String}sqsjDate										          申请时间(已转换)
	 * @apiSuccess {String}gx										                    关系
	 * @apiSuccess {String}gxString											关系(已转换)
	 * @apiSuccess {String}spbz											          审批标志
	 * @apiSuccess {String}lqbzString										领取标志(已转换)
	 * @apiSuccess {String}lqbz											          领取标志
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
	 *         "xm": "111",
	 *         "sqwp": "申请物品",
	 *         "sqsj": "155552559455",
	 *         "sqsjDate": "2019-12-04 10:10:10",
	 *         "gx": "11",
	 *         "gxString": "父子",
	 *         ......
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
	@ApiOperation("根据人员编号查询家属送物记录")
	@GetMapping("/jsswQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jsswQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/jssw/jsswQuery";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh",TermType.eq, maps.getResult().get("rybh"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.JsswListByRybh(param);
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
    
    /**
	 * @return
	 * @api {get} /v4/kss/jssw/QueryJsswByYwzt 管教审批查询
	 * @apiVersion 0.4.0
	 * @apiName QueryJsswByYwzt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 管教审批查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}jszjh										          送物人证件号
	 * @apiSuccess {String}sqyy										                    申请送物原因
	 * @apiSuccess {String}sqsj											          申请时间
	 * @apiSuccess {String}sqsjString								                    申请时间(已转换)
	 * @apiSuccess {String}sqwp											          申请物品
	 * @apiSuccess {String}ywzt											          所处阶段(2管教审批，3管教确认，4邮寄家属，5前台办理，6安检机安检，7管教送物，8在押人员确认，9审批未通过)
	 * @apiSuccess {String}xm											          人员姓名
	 * @apiSuccess {String}snbh											          所内编号
	 * @apiSuccess {String}spr											          管教审批人
	 * @apiSuccess {String}spnr											          管教审批内容
	 * @apiSuccess {String}spbz											          管教审批标志
	 * @apiSuccess {String}spsj											          管教审批时间
	 * @apiSuccess {String}spsjString										管教审批时间(转换)
	 * @apiSuccess {String}gjqrr											管教确认人
	 * @apiSuccess {String}gjqrsj											管教确认时间
	 * @apiSuccess {String}gjqrsjString										管教确认时间(转换)
	 * @apiSuccess {String}yjjsr											邮寄人
	 * @apiSuccess {String}yjjssj											邮寄时间
	 * @apiSuccess {String}yjjssjString										邮寄时间(转换)
	 * @apiSuccess {String}qtblsj											前台办理时间
	 * @apiSuccess {String}qtblr											前台办理人
	 * @apiSuccess {String}qtwpqr											前台确认寄回物品是否与申请物品一致（SHFO）
	 * @apiSuccess {String}qtwpqrString										前台确认寄回物品是否与申请物品一致（转换）
	 * @apiSuccess {String}ajsj											          安检时间
	 * @apiSuccess {String}ajsjString										安检人
	 * @apiSuccess {String}ajqk											          安检是否异常
	 * @apiSuccess {String}ajqkString										安检是否异常(转换)
	 * @apiSuccess {String}ajycqk											安检异常情况
	 * @apiSuccess {String}gjswsj											管教送物时间
	 * @apiSuccess {String}gjswr											管教送物人
	 * @apiSuccess {String}qrbz											          在押人员确认
	 * @apiSuccess {String}lqsj											          领取时间
	 * @apiSuccess {String}lqsjString										领取时间(转换)
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
	 *         "id": "1111111111111111",
	 *         "jsbh": "110000114",
	 *         "rybh": "155552559455",
	 *         "jszjh": "121313120191231",
	 *         "sqsj": "2019-01-01 12:12:12",
	 *         "sqyy": "申请送物原因",
	 *         "ywzt": "2",
	 *         "xm": "申请物品",
	 *         "snbh": "申请物品",
	 *         "sqwp":"12313",
	 *         .......
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
	 *    "ywzt":"所处阶段(必传;最大字段长度：21)(2管教审批，3管教确认，4邮寄家属，5前台办理，6安检机安检，7管教送物，8在押人员确认，9审批未通过)",
	 *    "jsh":"最大长度6",
	 *    "xm":"最多长度10",
	 * }
	 */
    
    @OpenAPI
	@ApiOperation("根据人员编号查询家属送物记录")
	@GetMapping("/QueryJsswByYwzt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> QueryJsswByYwzt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/jssw/QueryJsswByYwzt";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("ywzt"))) {
				param.and("ywzt",TermType.eq, maps.getResult().get("ywzt"));
			}else {
				return ResponseMessage.error("所处阶段必传！");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jbxx_jsh", TermType.like, "%" + maps.getResult().get("jsh") + "%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				param.and("jbxx_xm", TermType.like, "%" + maps.getResult().get("xm") + "%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("qrbz"))) {
				param.and("qrbz", maps.getResult().get("qrbz"));
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jsswQuery(param);
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
    
    
    /**
	 * @api {post} /v4/kss/jssw/saveWpByrysq 
	 * @apiVersion 0.4.0
	 * @apiName saveWpByrysq
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 在押人员申请.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
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
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "rybh":" 人员编号(必填; 最大字段长度：21)",
	 *      "jszjhq":"家属修改前证件号(非必填;最大字段长度：18;没有修改以前数据或者第一次添加不需要传)",
	 *      "jsxm":"家属姓名(必填; 最大长度：30)",
	 *      "gx":"关系(必填; 字典 GX; 最大长度3)",
	 *      "jszjh":"家属证件号(必填; 最大字段长度：18)",
	 *      "dh":"家属电话(必填; 最大字段长度：40)",
	 *      "dz":"家属联系地址(必填; 最多长度100)",
	 *      "sqsj":"申请时间; 格式(yyyy-MM-dd HH:mm:ss)",
	 *      "sqwp":"申请物品(必填; 最大字段长度：225)",
	 *      "sqyy":"申请原因(必填;最大长度225)",
	 *      "dwdh":"家属单位电话(非必填;最大长度11)",
	 *      "gzdw":"家属工作单位(非必填;最大长度60)",
	 *      "mz":"家属名族(非必填;字典 MZ ; 最大长度2)",
	 *      "yb":"家属邮编(非必填;最大长度6)",
	 *      "sfswgx":"家属关系是否涉维(非必填;字典 SHFO;最大长度1)",
	 *      "creator":"创建人",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("在押人员申请")
	@PostMapping("saveWpByrysq")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveWpByrysq(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/jssw/saveWpByrysq";

		//通过校验获取查询参数
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

			List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
			jsswModel.get(0).setQrbz("0");
			jsswModel.get(0).setYwzt("2");
			jsswModel.get(0).setJsbh(jsbh);
			List<ShgxModel> shgxModel = JSONArray.parseArray(map.get("entity").toString(), ShgxModel.class);
			String xb;
			if (Integer.parseInt(shgxModel.get(0).getJszjh().substring(16).substring(0, 1)) % 2 == 0) {
	            xb = "2";
	        } else {
	            xb = "1";
	        }
			shgxModel.get(0).setJsbh(jsbh);
			shgxModel.get(0).setCreatetime(new Date());
			shgxModel.get(0).setXb(xb);
			Map<String, Object> mapbc = new HashMap();
			mapbc.put("jsswModel", jsswModel);
			mapbc.put("shgxModel", shgxModel);
			GxswModel gxswModel = new GxswModel();
			gxswModel.setMap(map);
			ResponseMessage<String> jsswsq = kssServerApis.jsswSave(gxswModel);
			if(jsswsq.getStatus() == 200){
				jsswsq.setMessage("保存成功!");
			}else{
				jsswsq.setMessage("服务异常,保存失败!");
			}
			return jsswsq;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	 
    /**
	 * @api {post} /v4/kss/jssw/updateJsswById 
	 * @apiVersion 0.4.0
	 * @apiName updateJsswById
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 领导审批(根据id修改).
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
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
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
	 *      "spbz":"领导审批标志(必填;字典 1 同意 2 不同意)",
	 *      "spr":"审批人(必填; 最大长度：30)",
	 *      "spsj":"审批时间(必填; 格式(2019-10-10 10:10:10))",
	 *      "spnr":"审批内容(必填;)",
	 *      "ywzt":" '3'  (必填; 2管教审批，3管教确认，4邮寄家属，5前台办理，6安检机安检，7管教送物，8在押人员确认，9审批未通过)",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("管教审批(根据id修改)")
	@PostMapping("updateJsswById")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> updateJsswById(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/jssw/updateJsswById";

		//通过校验获取查询参数
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

			List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
			jsswModel.get(0).setYwzt("3");
			if(jsswModel.get(0).getSpbz() == "2") {
				jsswModel.get(0).setYwzt("9");
			}
			ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
			if(jsswsq.getStatus() == 200){
				jsswsq.setMessage("保存成功!");
			}else{
				jsswsq.setMessage("服务异常,保存失败!");
			}
			return jsswsq;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	
    /**
	 * @api {post} /v4/kss/jssw/updateJsswByGjqr
	 * @apiVersion 0.4.0
	 * @apiName updateJsswByGjqr
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 管教确认(根据id修改).
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
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
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
	 *      "gjqrr":"确认人(必填; 最大长度：30)",
	 *      "gjqrsj":"确认时间(必填; 格式(2019-10-10 10:10:10))",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("管教确认(根据id修改)")
	@PostMapping("updateJsswByGjqr")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> updateJsswByGjqr(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/jssw/updateJsswByGjqr";

		//通过校验获取查询参数
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

			List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
			jsswModel.get(0).setYwzt("4");
			ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
			if(jsswsq.getStatus() == 200){
				jsswsq.setMessage("保存成功!");
			}else{
				jsswsq.setMessage("服务异常,保存失败!");
			}
			return jsswsq;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
    /**
	 * @api {post} /v4/kss/jssw/updateJsswByYjjs
	 * @apiVersion 0.4.0
	 * @apiName updateJsswByYjjs
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 邮寄家属(根据id修改).
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
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
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "entity":[{
	 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
	 *      "yjjsr":"邮寄人(必填; 最大长度：30)",
	 *      "yjjssj":"邮寄时间(必填; 格式(2019-10-10 10:10:10))",
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("邮寄家属(根据id修改)")
	@PostMapping("updateJsswByYjjs")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> updateJsswByYjjs(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/jssw/updateJsswByYjjs";

		//通过校验获取查询参数
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

			List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
			jsswModel.get(0).setYwzt("5");
			ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
			if(jsswsq.getStatus() == 200){
				jsswsq.setMessage("保存成功!");
			}else{
				jsswsq.setMessage("服务异常,保存失败!");
			}
			return jsswsq;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}
	
	   /**
		 * @api {post} /v4/kss/jssw/updateJsswByQtbl
		 * @apiVersion 0.4.0
		 * @apiName updateJsswByQtbl
		 * @apiGroup g_kss
		 * @apiPermission any
		 * @apiDescription 前台办理(根据id修改).
		 *
		 * @apiParam {String} appcode 											应用代码(必填)
		 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
		 * @apiParam {String} json 												保存参数集(必填)
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
		 *
		 * @apiExample 请求参数:
		 * appcode:"应用代码（必填）",
		 * jsbh:"监所编号(必填; 最大字段长度：9)",
		 * json:{
		 *   "entity":[{
		 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
		 *      "qtwpqr":"确认物品是否一致(必填; 1一致  2  不一致)",
		 *      "qtblr":"确认人(必填; 最大长度：30)",
		 *      "qtblsj":"确认时间(必填; 格式(2019-10-10 10:10:10))",
		 *   }]
		 * }
		 *
		 */
		@ApiOperation("前台确认(根据id修改)")
		@PostMapping("updateJsswByQtbl")
		@HystrixCommand
		@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
				@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
		@OpenAPI
		public ResponseMessage<String> updateJsswByQtbl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

			String interfaceId = "/v4/kss/jssw/updateJsswByQtbl";

			//通过校验获取查询参数
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

				List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
				jsswModel.get(0).setYwzt("6");
				ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
				if(jsswsq.getStatus() == 200){
					jsswsq.setMessage("保存成功!");
				}else{
					jsswsq.setMessage("服务异常,保存失败!");
				}
				return jsswsq;
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败！");
			}
		}
		
   /**
		 * @api {post} /v4/kss/jssw/updateJsswByAjjaj
		 * @apiVersion 0.4.0
		 * @apiName updateJsswByAjjaj
		 * @apiGroup g_kss
		 * @apiPermission any
		 * @apiDescription 安检机安检(根据id修改).
		 *
		 * @apiParam {String} appcode 											应用代码(必填)
		 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
		 * @apiParam {String} json 												保存参数集(必填)
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
		 *
		 * @apiExample 请求参数:
		 * appcode:"应用代码（必填）",
		 * jsbh:"监所编号(必填; 最大字段长度：9)",
		 * json:{
		 *   "entity":[{
		 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
		 *      "ajqk":"安检情况(必填; 1一致  0 不一致)",
		 *      "ajr":"安检人(必填; 最大长度：30)",
		 *      "ajsj":"安检时间(必填; 格式(2019-10-10 10:10:10))",
		 *   }]
		 * }
		 *
		 */
		@ApiOperation("安检机安检(根据id修改)")
		@PostMapping("updateJsswByAjjaj")
		@HystrixCommand
		@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
				@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
		@OpenAPI
		public ResponseMessage<String> updateJsswByAjjaj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

			String interfaceId = "/v4/kss/jssw/updateJsswByAjjaj";

			//通过校验获取查询参数
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

				List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
				jsswModel.get(0).setYwzt("7");
				ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
				if(jsswsq.getStatus() == 200){
					jsswsq.setMessage("保存成功!");
				}else{
					jsswsq.setMessage("服务异常,保存失败!");
				}
				return jsswsq;
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败！");
			}
		}
		 /**
		 * @api {post} /v4/kss/jssw/updateJsswByGjsw
		 * @apiVersion 0.4.0
		 * @apiName updateJsswByGjsw
		 * @apiGroup g_kss
		 * @apiPermission any
		 * @apiDescription 管教送物(根据id修改).
		 *
		 * @apiParam {String} appcode 											应用代码(必填)
		 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
		 * @apiParam {String} json 												保存参数集(必填)
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
		 *
		 * @apiExample 请求参数:
		 * appcode:"应用代码（必填）",
		 * jsbh:"监所编号(必填; 最大字段长度：9)",
		 * json:{
		 *   "entity":[{
		 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
		 *      "gjswr":"安检人(必填; 最大长度：30)",
		 *      "gjswsj":"安检时间(必填; 格式(2019-10-10 10:10:10))",
		 *   }]
		 * }
		 *
		 */
		@ApiOperation("管教送物(根据id修改)")
		@PostMapping("updateJsswByGjsw")
		@HystrixCommand
		@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
				@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
		@OpenAPI
		public ResponseMessage<String> updateJsswByGjsw(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

			String interfaceId = "/v4/kss/jssw/updateJsswByGjsw";

			//通过校验获取查询参数
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

				List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
				jsswModel.get(0).setYwzt("8");
				ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
				if(jsswsq.getStatus() == 200){
					jsswsq.setMessage("保存成功!");
				}else{
					jsswsq.setMessage("服务异常,保存失败!");
				}
				return jsswsq;
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败！");
			}
		}
		
		 /**
		 * @api {post} /v4/kss/jssw/updateJsswByZyryqr
		 * @apiVersion 0.4.0
		 * @apiName updateJsswByZyryqr
		 * @apiGroup g_kss
		 * @apiPermission any
		 * @apiDescription 在押人员确认(根据id修改).
		 *
		 * @apiParam {String} appcode 											应用代码(必填)
		 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
		 * @apiParam {String} json 												保存参数集(必填)
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
		 *
		 * @apiExample 请求参数:
		 * appcode:"应用代码（必填）",
		 * jsbh:"监所编号(必填; 最大字段长度：9)",
		 * json:{
		 *   "entity":[{
		 *      "id":" id(必填,获取查询数据的; 最大字段长度：23)",
		 *      "qrbz":"确认标志(必填; 1 确认领取  0 暂不领取)",
		 *      "lqsj":"领取时间(必填; 格式(2019-10-10 10:10:10))",
		 *   }]
		 * }
		 *
		 */
		@ApiOperation("在押人员确认(根据id修改)")
		@PostMapping("updateJsswByZyryqr")
		@HystrixCommand
		@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
				@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
		@OpenAPI
		public ResponseMessage<String> updateJsswByZyryqr(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

			String interfaceId = "/v4/kss/jssw/updateJsswByZyryqr";

			//通过校验获取查询参数
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
				List<JsswModel> jsswModel = JSONArray.parseArray(map.get("entity").toString(), JsswModel.class);
				ResponseMessage<String> jsswsq = kssServerApis.updateJsswGjqrById(jsswModel.get(0).getId(),jsswModel.get(0));
				if(jsswsq.getStatus() == 200){
					jsswsq.setMessage("保存成功!");
				}else{
					jsswsq.setMessage("服务异常,保存失败!");
				}
				return jsswsq;
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败！");
			}
		}


}
