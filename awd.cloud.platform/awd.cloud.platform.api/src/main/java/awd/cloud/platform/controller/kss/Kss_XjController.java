/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.ShgxModel;
import awd.bj.kss.model.XjModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.ShgxsModel;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
import awd.cloud.platform.model.kss.Kss_XjModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xj")
@Api(tags = "kss-xj",description = "Xj") 
public class Kss_XjController  extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private KssAnalyseApis kssAnalyseApis;
	@Autowired
	private KssServerApis kssServerApis;


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_XjModel>> xj_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_XjModel>> result= kssService.xj_query(queryParam);
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
	public ResponseMessage<String> xj_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XjModel data) {
		return kssService.xj_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XjModel data) {
		return kssService.xj_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_XjModel> xj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xj_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> xj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xj_deleteByKey(id);
	}





	/**
	 * @api {post} /v4/kss/xj/xjszAdd  新增械具流程开始节点
	 * @apiVersion 0.4.0
	 * @apiName xjszAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  新增械具流程开始节点.
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
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }

	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	"entity": [{
	 * 	"creator":"创建人(必填:最大长度50)"
	 * 	"xjmc": "戒具名称(必填;字典:JJMC;最大长度20)",
	 * 	"syts": "使用天数(必填;最大长度4)",
	 * 	"syqx": "使用情形(必填;最大长度200)",
	 * 	"ly": "设置原因(必填;字典:JJSYYY;最大长度2)",
	 * 	"blr": "办理人(必填;最大长度20)",
	 * 	"blrq": "办理日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 * 	"rybh": "人员编号(必填;最大长度21)",
	 * 	"ywlcid": "业务流程ID(必填;最大长度21)",
	 * 	"xm": "姓名(必填;最大长度30)",
	 * 	"snbh": "人员所内编号(必填;最大长度8)"
	 * }]
	 * }
	 * @apiUse CreateError
	 */
	@ApiOperation("新增械具流程开始节点")
	@PostMapping("/xjszAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, String>> xjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/xj/xjszAdd";
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


			//封装数据
			List<XjModel> xjModels = JSONArray.parseArray(map.get("entity").toString(), XjModel.class);
			List<JbxxModel> jbxxModels = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class);
			XjModel xjModel = xjModels.get(0);
			JbxxModel jbxxModel = jbxxModels.get(0);

			if (processService.FlowMutex(jsbh, xjModel.getRybh(), "kss_xjsy".toUpperCase(), "XJ").getStatus() == 200) {
				ResponseMessage<String> xj = processService.FlowMutex(jsbh, xjModel.getRybh(), "kss_xjsy".toUpperCase(), "XJ");
				System.err.println("消息=" + xj.getMessage());
				return ResponseMessage.error(xj.getMessage());
			}
			xjModel.setState("R2");
			xjModel.setJsbh(jsbh);
			xjModel.setCreatetime(new Date());
			xjModel.setPsbz("0");
			xjModel.setPastable("1");
			xjModel.setCreatetime(new Date());
			Map<String, Object> maplist = Maps.newHashMap();
			maplist.put("lcid", CacheUtils.get().getFlowKey("KSS_XJSY"));
			System.err.println("lcid----------------"+CacheUtils.get().getFlowKey("KSS_XJSY"));
			maplist.put("jbxxEntity", jbxxModel);
			maplist.put("xjEntity", xjModel);

			ResponseMessage<Map<String, String>>  result = kssServerApis.xjAddFlow(maplist);
			System.err.println("--"+ JSON.toJSONString(result));
			if(result.getStatus() == 200){
				result.setMessage("保存成功!");
			}else{
				result.setMessage("服务异常,保存失败!");
			}
			return  result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}


	/**
	 * @api {post} /v4/kss/xj/xjYwdt 械具业务动态查询
	 * @apiVersion 0.4.0
	 * @apiName xjYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 械具业务动态查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}zrs         				                    	总人数
	 * @apiSuccess {String}sycp												械具使用呈批
	 * @apiSuccess {String}ycdj												械具使用登记
	 * @apiSuccess {String}sydj												延长使用登记

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
	 *         "result": {
	 *           "xjZrs": [
	 *             {
	 *               "zrs": 162
	 *             }
	 *           ],
	 *           "xjSycp": [
	 *             {
	 *               "sycp": 90
	 *             }
	 *           ],
	 *           "xjYcdj": [
	 *             {
	 *               "ycdj": 26
	 *             }
	 *           ],
	 *           "xjSydj": [
	 *             {
	 *               "sydj": 46
	 *             }
	 *           ]
	 *         }
	 *       }],
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
	 *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
	 * 			"endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
	 * 			"dw":"单位(最大字段长度：50)"
	 *        }

	 */
	//result
	//{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
	@OpenAPI
	@ApiOperation("械具业务动态查询")
	@PostMapping("/xjYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> xjYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xj/xjYwdt";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			String starttime = (String) maps.getResult().get("starttime");
			String endtime =  (String) maps.getResult().get("endtime");
			ResponseMessage<Map<String,Object>> result= kssAnalyseApis.xjYwdt(jsbh,starttime,endtime);
			List lists=new ArrayList();
			lists.add(result);
			System.err.println("result" + JSON.toJSONString(result));
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", lists);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", lists.size());
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
	 * @api {post} /v4/kss/xj/xjYwtz 械具业务台账查询
	 * @apiVersion 0.4.0
	 * @apiName xjYwtz
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 械具业务台账查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}csrq												出生日期
	 * @apiSuccess {String}dah												档案号
	 * @apiSuccess {String}kssj												开始时间
	 * @apiSuccess {String}ly												理由
	 * @apiSuccess {String}xjjcsj											械具解除时间
	 * @apiSuccess {String}xjsyblsj											械具使用时间
	 * @apiSuccess {String}ycldspsj											延长领导审批时间
	 * @apiSuccess {String}ycldspr											延长领导审批人
	 * @apiSuccess {String}ldpsbz											领导批示标志
	 * @apiSuccess {String}ldpssj											领导批示时间
	 * @apiSuccess {String}yczdpsbz											延长中队长批示标志
	 * @apiSuccess {String}kssjString										开始时间（已转换）
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}yxjsyqx											原械具使用期限
	 * @apiSuccess {String}ycjbrq											延长经办日期
	 * @apiSuccess {String}jczxr											解除执行人
	 * @apiSuccess {String}jczxqk											解除执行日期
	 * @apiSuccess {String}hjd												户籍地
	 * @apiSuccess {String}blrq												办理日期
	 * @apiSuccess {String}gyqx												关押期限
	 * @apiSuccess {String}yczdspr											延长中队长审批人
	 * @apiSuccess {String}bahj												办案环节
	 * @apiSuccess {String}psbz												批示标志
	 * @apiSuccess {String}xjmc												械具名称
	 * @apiSuccess {String}xjsyzxqk											械具使用执行情况
	 * @apiSuccess {String}xjid												械具id
	 * @apiSuccess {String}ayString											案由
	 * @apiSuccess {String}ay												案由（已转换）
	 * @apiSuccess {String}ldyj												领导意见
	 * @apiSuccess {String}syqx												使用情形
	 * @apiSuccess {String}badw												办案单位
	 * @apiSuccess {String}jsh												监室号
	 * @apiSuccess {String}yczdspyj											延长中队长审批意见
	 * @apiSuccess {String}ycjbmj											延长经办民警
	 * @apiSuccess {String}zdzyj											中队长意见
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}ycldspyj											延长领导审批意见内容
	 * @apiSuccess {String}xjsyblr											械具使用办理人
	 * @apiSuccess {String}jssjString										结束时间（已转换）
	 * @apiSuccess {String}jssj												结束时间
	 * @apiSuccess {String}syts												使用天数
	 * @apiSuccess {String}snbh												所内编号
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}xjycly											械具使用变动理由
	 * @apiSuccess {String}ldxm												领导姓名
	 * @apiSuccess {String}xjmcString										械具名称（已转换）
	 * @apiSuccess {String}bdlx												械具变动类型
	 * @apiSuccess {String}jcbz												解除备注
	 * @apiSuccess {String}hjdString										户籍地（已转换）
	 * @apiSuccess {String}pastable											是否有效
	 * @apiSuccess {String}xjsybz											械具使用备注
	 * @apiSuccess {String}zdzpssj											中队长批示时间
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}zdzxm											中队长姓名
	 * @apiSuccess {String}zdzpsbz											中队长批示标志
	 * @apiSuccess {String}rsrqString										入所日期（已转换）
	 * @apiSuccess {String}ycldpsbz											延长领导批示标志
	 * @apiSuccess {String}csrqString										出生日期（已转换）
	 * @apiSuccess {String}bahjString										办案环节（已转换）
	 * @apiSuccess {String}blr												办理人
	 * @apiSuccess {String}xb												性别
	 * @apiSuccess {String}xbString											性别（已转换）
	 * @apiSuccess {String}xm												姓名
	 * @apiSuccess {String}rsrq												入所日期
	 * @apiSuccess {String}yczdspsj											延长中队长批示时间
	 * @apiSuccess {String}lyString											理由（已转换）
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}jsbh												监所编号
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
	 *         "result": {
	 * 	           "xsjls": 3,
	 *             "afdd": "072900",
	 *             "ywlcid": "2775198",
	 *             "phxsaj": null,
	 *             "lx": "1",
	 *             "djrqString": "2019-08-01",
	 *             "afsj": 1565599799000,
	 *             "snbh": "20190038",
	 *             "cdrdh": null,
	 *             "czjg": "1",
	 *             "cddw": "上海市公安局国内安全保卫局",
	 *             "id": "31000011120190612000057",
	 *             "state": "R2",
	 *             "wszts": null,
	 *             "pastable": "1",
	 *             "creator": "管理员",
	 *             "createtime": 1561008171000,
	 *             "afsjString": "2019-08-12 16:49:59",
	 *             "sar": "下次",
	 *             "zwdw": "北京市公安局国内安全保卫局",
	 *             "djrq": 1564650033000,
	 *             "fkrq": 1560787200000,
	 *             "tbjurq": 1562662223000,
	 *             "tbjurqString": "2019-07-09",
	 *             "jjqxz": "球球球球",
	 *             "xm": "云承",
	 *             "rybh": "310000111201906200007",
	 *             "zjh": "230506200006168870",
	 *             "czjgString": "是",
	 *             "dbrs3": null,
	 *             "jsh": "0101",
	 *             "jsbh": "110000114"
	 *         },
	 *       }],
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
	 *          "xm":"姓名",
	 *          "jsh":"监室号",
	 *          "blrqstart":"办理日期开始",
	 *          "blrqend":"办理日期结束",
	 *          "processDefinitionKey":"processDefinitionKey",
	 *          "taskDefinitionKey":"taskDefinitionKey",
	 *          "pageSize":"20",
	 *          "pageIndex":"0",
	 *          "page":"1",
	 *          "rows":"20",
	 *          "sort":"id",
	 *          "order":"desc"
	 *        }

	 */

	//csrq,dah,kssj,ly,xjjcsj,xjsyblsj,ycldspsj,ycldspr,ldpsbz,ldpssj,yczdpsbz,kssjString,id,state,yxjsyqx,ycjbrq,jczxr,jczxqk,hjd,blrq,gyqx,yczdspr,bahj,psbz,xjmc,xjsyzxqk,xjid,ayString,ay,ldyj,syqx,badw,jsh,yczdspyj,ycjbmj,zdzyj,ywlcid,ycldspyj,xjsyblr,jssjString,jssj,syts,snbh,bz,xjycly,ldxm,xjmcString,bdlx,jcbz,hjdString,pastable,xjsybz,zdzpssj,creator,createtime,zdzxm,zdzpsbz,rsrqString,ycldpsbz,csrqString,bahjString,blr,xb,xbString,xm,rsrq,yczdspsj,lyString,rybh,updator,updatetime,jsbh
	//{"xm":"","jsh":"","blrqstart":"","blrqend":"","processDefinitionKey":"","taskDefinitionKey":"","pageSize":"20","pageIndex":"0","page":"1","rows":"20","sort":"id","order":"desc"}
	@OpenAPI
	@ApiOperation("械具业务台账查询")
	@PostMapping("/xjYwtz")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String,Object>> xjYwtz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = " ";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数
			QueryParam queryParam = new QueryParam();
			String xm = (String) maps.getResult().get("xm");
			String jj = (String) maps.getResult().get("jj");
			String xb = (String) maps.getResult().get("xb");
			String bm = (String) maps.getResult().get("bm");
			String jsh = (String) maps.getResult().get("jsh");
			String rsrq_start = (String) maps.getResult().get("rsrq_start");
			String rsrq_end = (String) maps.getResult().get("rsrq_end");
			String gyqx_start = (String) maps.getResult().get("gyqx_start");
			String gyqx_end = (String) maps.getResult().get("gyqx_end");
			String bahj = (String) maps.getResult().get("bahj");
			String xjjcsjBz = (String) maps.getResult().get("xjjcsjBz");
			String blrqstart = (String) maps.getResult().get("blrqstart");
			String blrqend = (String) maps.getResult().get("blrqend");
			String jssj_star = (String) maps.getResult().get("jssj_star");
			String jssj_end = (String) maps.getResult().get("jssj_end");
			if (!StringUtils.isNullOrEmpty(jssj_star)) {
				queryParam.and("jssj", TermType.gte, jssj_star+ " 00:00:00");
			}
			if (!StringUtils.isNullOrEmpty(jssj_end)) {
				queryParam.and("jssj", TermType.lte, jssj_end+ " 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(blrqstart)) {
				queryParam.and("kssj", TermType.gte, blrqstart+ " 00:00:00");
			}
			if (!StringUtils.isNullOrEmpty(blrqend)) {
				queryParam.and("kssj", TermType.lte, blrqend+ " 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(xm)) {
				queryParam.and("jbxx_xm", TermType.like, "%" + xm + "%");
			}
			if (!StringUtils.isNullOrEmpty(jj)) {
				queryParam.and("jbxx_jj", TermType.eq, jj);
			}
			if (!StringUtils.isNullOrEmpty(jsh)) {
				queryParam.and("jbxx_jsh", TermType.eq, jsh);
			}
			if (!StringUtils.isNullOrEmpty(xb)) {
				queryParam.and("jbxx_xb", TermType.eq, xb);
			}
			if (!StringUtils.isNullOrEmpty(bm)) {
				queryParam.and("jbxx_bm", TermType.eq, bm);
			}
			if (!StringUtils.isNullOrEmpty(bahj)) {
				queryParam.and("jbxx_bahj", TermType.eq, bahj);
			}
			if (!StringUtils.isNullOrEmpty(rsrq_start)) {
				queryParam.and("jbxx_rsrq", TermType.gte, rsrq_start);
			}
			if (!StringUtils.isNullOrEmpty(rsrq_end)) {
				queryParam.and("jbxx_rsrq", TermType.lte, rsrq_end);
			}
			if (!StringUtils.isNullOrEmpty(gyqx_start)) {
				queryParam.and("gyqx", TermType.gte, gyqx_start);
			}
			if (!StringUtils.isNullOrEmpty(gyqx_end)) {
				queryParam.and("gyqx", TermType.lte, gyqx_end);
			}
			if (!StringUtils.isNullOrEmpty(xjjcsjBz) && "true".equals(xjjcsjBz)) {
				queryParam.and("xjjcsj", TermType.isnull, "xjjcsj");
			}
			DefaultQueryParam.addDefaultQueryParam(request,queryParam,"R2");
			queryParam.and("jbxx_state", TermType.eq, "R8");
			queryParam.and("jsbh", TermType.eq, jsbh);
			queryParam.and("jbxx_jsbh", TermType.eq, jsbh);
			queryParam.and("pastable", TermType.eq, "1");
			System.err.println("械具业务台账queryParam"+ JSONUtil.toJson(queryParam));
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.xjYwtz(queryParam);
			System.err.println("result" + JSON.toJSONString(result));
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





}
