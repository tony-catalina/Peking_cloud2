/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import awd.bj.kss.model.GwjjbModel;
import awd.bj.kss.model.JbjlModel;
import awd.bj.kss.model.TszglModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_GwjjbModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
@RequestMapping("/v4/kss/gwjjb")
@Api(tags = "kss-gwjjb",description = "Gwjjb") 
public class Kss_GwjjbController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {post} /v4/kss/gwjjb/gwjjbList 岗位交接班查询
	 * @apiVersion 0.4.0
	 * @apiName gwjjbList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 岗位交接班查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}createtime                                       创建时间
	 * @apiSuccess {String}creator                                          创建人
	 * @apiSuccess {String}flag                                             交接班标志位：1交班、2接班
	 * @apiSuccess {String}gzjl												工作记录
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jbgw												交班岗位
	 * @apiSuccess {String}jbldps											接班领导批示
	 * @apiSuccess {String}jbldqm											接班领导签字
	 * @apiSuccess {String}jbldyj											接班领导意见
	 * @apiSuccess {String}jbqrsj											接班领导确认时间
	 * @apiSuccess {String}jbry												交班人员
	 * @apiSuccess {String}jbsj												交班时间
	 * @apiSuccess {String}jgzjl											接班工作记录
	 * @apiSuccess {String}jh												警号
	 * @apiSuccess {String}jjbgw											接班岗位
	 * @apiSuccess {String}jjbry                     						接班人员
	 * @apiSuccess {String}jjbsj											接班时间
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jzysx											接班注意事项
	 * @apiSuccess {String}ldps											    交班领导批示
	 * @apiSuccess {String}ldqm												交班领导签字
	 * @apiSuccess {String}ldyj												交办领导意见
	 * @apiSuccess {String}qrsj												交办领导确认时间
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}updatetime									    更新时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}ywlcid											任务流程id
	 * @apiSuccess {String}zysx												注意事项
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result":
	 *    {
	 *     "total": 16,
	 *     "data": [{
	 *          "createtime": "2020-01-07 16:16:18",
	 *          "creator": "管理员",
	 *          "flag": "2",,
	 *          "gzjl": "+6562",
	 *          "id": "11000011420200107000297",
	 *          "jbgw": "收押岗位",
	 *          "jbldps": "",
	 *          "jbldqm": "",
	 *          "jbldyj": "",
	 *          "jbqrsj": "",
	 *          "jbry": "哈哈哈",
	 *          "jbsj": "2020-01-07 16:15:57",
	 *          "jgzjl": "",
	 *          "jh": "12314",
	 *          "jjbgw": "",
	 *          "jjbry": "管理员",
	 *          "jjbsj": "2020-01-07 16:17:22",
	 *          "jsbh": "110000114",
	 *          "jzysx": "",
	 *          "ldps": "",
	 *          "ldqm": "",
	 *          "ldyj": "",
	 *          "qrsj": "",
	 *          "state": "R2",
	 *          "updatetime": "2020-01-07 16:17:25",
	 *          "updator": "管理员",
	 *          "ywlcid": "",
	 *          "zysx": ""
	 *        },
	 *        "page": "1"
	 *        },
	 *   "status": 200,
	 *   "timestamp": 1576826568061
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "jbgw":"sygw",
	 *          "sort":"id",
	 *          "order":"desc",
	 *          "page":"1",
	 *          "rows":"10"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("岗位交接班查询")
	@PostMapping("/gwjjbList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> gwjjbList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"jbgw":"sygw","page":"1","rows":"10","sort":"id","order":"desc"}
		//id,jsbh,jsbhString,jbgw,jbry,jbsj,gzjl,zysx,ldyj,ldps,ldqm,qrsj,jjbgw,jjbry,jjbsj,jgzjl,jzysx,jbldyj,jbldps,jbldqm,jbqrsj,ywlcid,state,stateString,creator,createtime,updator,updatetime,jh,flag
		String interfaceId = "/v4/kss/gwjjb/gwjjbList";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();
			String jbry = (String) maps.getResult().get("jbry");
			String jbsj_start = (String) maps.getResult().get("jbsj_start");
			String jbsj_end = (String) maps.getResult().get("jbsj_end");
			String jjbry = (String) maps.getResult().get("jjbry");
			String jjbsj_start = (String) maps.getResult().get("jjbsj_start");
			String jjbsj_end = (String) maps.getResult().get("jjbsj_end");
			String flag=(String) maps.getResult().get("flag");
			String jbgw=(String) maps.getResult().get("jbgw");
			if(!StringUtils.isNullOrEmpty(flag)){
				qParam.and("flag",TermType.eq, flag);
			}
			if(!StringUtils.isNullOrEmpty(jbry)) {
				qParam.and("jbry",TermType.like, "%"+jbry+"%");
			}
			if(!StringUtils.isNullOrEmpty(jbsj_start)) {
				qParam.and("jbsj", TermType.gte, jbsj_start+" 00:00:00");
				qParam.and("jbsj", TermType.lte, jbsj_end + " 23:59:59");
			}
			if(!StringUtils.isNullOrEmpty(jjbry)) {
				qParam.and("jjbry", TermType.like, "%"+jjbry+"%");
			}
			if(!StringUtils.isNullOrEmpty(jjbsj_start)) {
				qParam.and("jjbsj", TermType.gte, jjbsj_start);
				qParam.and("jjbsj", TermType.lte, jjbsj_end + " 23:59:59");
			}
			if ("sygw".equals(jbgw)){
				qParam.and("jbgw",TermType.eq,"收押岗位");
			}
			DefaultQueryParam.addDefaultQueryParam(request, qParam, null);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.gwjjbList(qParam);

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
	 * @api {post} /v4/kss/gwjjb/findMjJbxx 查找民警接班信息
	 * @apiVersion 0.4.0
	 * @apiName findMjJbxx
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 查找民警接班信息.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}xm                                               姓名
	 * @apiSuccess {String}jh                                          		警号
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "查询成功",
	 *   "result":[{"xm":"收押","jh":"112546"},{"xm":"张三","jh":"121212"},{"xm":"哈哈哈","jh":"12314"}]
	 *   "timestamp": 1576826568061
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("查找民警接班信息")
	@PostMapping("/findMjJbxx")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<String> findMjJbxx(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//
		//xm,jh
		String interfaceId = "/v4/kss/gwjjb/findMjJbxx";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			String code="11";
			List<Map<String, Object>> list=kssServerApis.findMjJbxx(jsbh,code);
			ResponseMessage<String> result = new ResponseMessage<>();
			result.setResult(list.toString());
			if (list.size()>0) {
				result.setMessage("查询成功");
				if (result.getResult() == null) {
					result.setMessage("未查询数据");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}


	/**
	 * @api {post} /v4/kss/gwjjb/gwjjbflow 岗位交接班保存
	 * @apiVersion 0.4.0
	 * @apiName gwjjbflow
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 岗位交接班保存.
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
	 *					"jbry":"交班人员(必填;最大长度:30)",
	 *       			"jbgw":"交班岗位(必填；最大长度:50)",
	 *       		    "gzjl":"工作记录()",
	 *       		    "jh":"警号(最大长度:255)",
	 *       	        "jbsj":"交班时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "creator":"创建人(必填；最大长度:30)"
	 *          		}]
	 *     	  }
	 */

	//{"jbry":"\\S{1,30}","jbsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"jbry":"aa","jbgw":"收押岗位","gzjl":"sdas","jbsj":"2019-10-10 10:10:10","jh":"121212","creator":"管理员"}]}

	@ApiOperation("岗位交接班保存")
	@PostMapping("/gwjjbflow")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> gwjjbflow(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/gwjjb/gwjjbflow";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			GwjjbModel gwjjbModel=JSONObject.parseObject(rslist.get(0).toString(),GwjjbModel.class);
			gwjjbModel.setState("R2");
			gwjjbModel.setFlag("1");
			gwjjbModel.setJsbh(jsbh);
			gwjjbModel.setCreatetime(new Date());

			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			Map<String, Object> mapss=new HashMap<String, Object>();
			JbjlModel jbjlModel=new JbjlModel();
			jbjlModel.setJsbh(jsbh);
			jbjlModel.setCreatetime(gwjjbModel.getJbsj());
			jbjlModel.setCreator(gwjjbModel.getJbry());
			jbjlModel.setJbr(gwjjbModel.getJbry());
			jbjlModel.setJbqk(gwjjbModel.getGzjl());
			jbjlModel.setJbly(gwjjbModel.getZysx());
			jbjlModel.setFlag("1");
			jbjlModel.setJh(gwjjbModel.getJh());
			mapss.put("gwjjbModel", gwjjbModel);
			mapss.put("jbjlModel", jbjlModel);
			String list = kssServerApis.SaveGwjjb(mapss);
			if (list.equals("保存成功")){
				return ResponseMessage.ok("保存成功");
			}else {
				return ResponseMessage.error("服务异常,保存失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}


	/**
	 * @api {post} /v4/kss/gwjjb/gwjjbjbqr 岗位交接班交班确认
	 * @apiVersion 0.4.0
	 * @apiName gwjjbjbqr
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 岗位交接班交班确认.
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
	 *       			"id":"id(必填;最大长度:23)",
	 *       			"zysx":"注意事项(最大长度:255)",
	 *       			"jjbry":"接班人员(必填;最大长度:30)",
	 *					"jbry":"交班人员(必填;最大长度:30)",
	 *       			"jbgw":"交班岗位(必填；最大长度:50)",
	 *       		    "gzjl":"工作记录()",
	 *       		    "jbsj":"交班时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *       	        "jjbsj":"接班时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"id":"\\d{1,23}","jjbry":"\\S{1,30}","jjbsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"id":"11000011420200114000302","jbgw":"收押岗位","jbry":"哈哈哈","jbsj":"2020-01-07 15:12:46","gzjl":"dsdsd","zysx":"545","jjbry":"管理员","jjbsj":"2020-01-14 13:24:50"}]}

	@ApiOperation("岗位交接班交班确认")
	@PostMapping("/gwjjbjbqr")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> gwjjbjbqr(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/gwjjb/gwjjbjbqr";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			GwjjbModel gwjjbModel=JSONObject.parseObject(rslist.get(0).toString(),GwjjbModel.class);
			gwjjbModel.setState("R2");
			gwjjbModel.setFlag("2");
			gwjjbModel.setJsbh(jsbh);
			gwjjbModel.setUpdatetime(gwjjbModel.getJjbsj());
			gwjjbModel.setUpdator(gwjjbModel.getJjbry());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			Map<String, Object> mapss=new HashMap<String, Object>();
			JbjlModel jbjlModel=new JbjlModel();
			jbjlModel.setJsbh(jsbh);
			jbjlModel.setJbly(gwjjbModel.getZysx());
			jbjlModel.setUpdatetime(gwjjbModel.getUpdatetime());
			jbjlModel.setJbr(gwjjbModel.getJbry());
			jbjlModel.setUpdator(gwjjbModel.getUpdator());
			jbjlModel.setJbqk(gwjjbModel.getGzjl());
			jbjlModel.setFlag("2");
			jbjlModel.setJh(gwjjbModel.getJh());
			mapss.put("gwjjbModel", gwjjbModel);
			mapss.put("jbjlModel", jbjlModel);
			String list = kssServerApis.SaveGwjjb(mapss);
			if (list.equals("保存成功")){
				return ResponseMessage.ok("保存成功");
			}else {
				return ResponseMessage.error("服务异常,保存失败!");
			}
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
	public ResponseMessage<PagerResult<Kss_GwjjbModel>> gwjjb_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_GwjjbModel>> result= kssService.gwjjb_query(queryParam);
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
	public ResponseMessage<String> gwjjb_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_GwjjbModel data) {
		return kssService.gwjjb_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> gwjjb_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_GwjjbModel data) {
		return kssService.gwjjb_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_GwjjbModel> gwjjb_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.gwjjb_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> gwjjb_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.gwjjb_deleteByKey(id);
	}
}
