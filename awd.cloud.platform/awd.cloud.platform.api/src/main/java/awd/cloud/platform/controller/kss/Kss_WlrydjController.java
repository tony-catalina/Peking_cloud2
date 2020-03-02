/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.ShgxModel;
import awd.bj.kss.model.WlryModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JslxModelDO;
import awd.cloud.platform.model.kss.Kss_WlryModel;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wlrydj")
@Api(tags = "kss-wlrydj",description = "Wlrydj")
public class Kss_WlrydjController extends PublicService {
	
	@Autowired
    private KssServerApis kssServerApis;
	@Autowired
	private KssAnalyseApis kssAnalyseApis;

	/**
	 * @api {get} /v4/kss/wlrydj/wlryList 外来人员查询
	 * @apiVersion 0.4.0
	 * @apiName wlryList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 外来人员查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}clxx												车辆信息
	 * @apiSuccess {String}crdjbz											出入登记标志
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}creator											创建人
	 * @apiSuccess {String}dcsj												带出时间
	 * @apiSuccess {String}drmj												带入民警
	 * @apiSuccess {String}drsj												带入时间
	 * @apiSuccess {String}id												id
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号（已转换）
	 * @apiSuccess {String}jtsy												具体事由
	 * @apiSuccess {String}lfrs												来访人数
	 * @apiSuccess {String}lssy												来所事由
	 * @apiSuccess {String}lssyString										来所事由（已转换）
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态（已转换）
	 * @apiSuccess {String}sxcls											随行车辆数
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}updator											更新时间
	 * @apiSuccess {String}wljbxx											外来基本信息
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
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *        {
	 *         "clxx": "[{\"clssdw\":\"\",\"cphm\":\"\",\"aqjcqk\":\"\",\"snztzhm\":\"\",\"clsnzjh\":\"\"}]",
	 *         "crdjbz": "0",
	 *         "createtime": "2019-09-05 19:19:46",
	 *         "creator": "管理员",
	 *         "dcsj": "2019-12-23 15:34:24",
	 *         "drmj": "管理员",
	 *         "drsj": "2019-09-05 19:19:46",
	 *         "id": "11000011420190905000026",
	 *         "jsbh": "110000114",
	 *         "jsbhString": "北京市第一看守所",
	 *         "jtsy": "123",
	 *         "lfrs": 1,
	 *         "lssy": "0",
	 *         "lssyString": "提询",
	 *         "state": "R2",
	 *         "stateString": " ",
	 *         "sxcls": 123,
	 *         "updatetime": "2019-09-05 19:19:47",
	 *         "updator": "",
	 *         "wljbxx": "[{\"xm\":\"张三\",\"zjh\":\"110101199003073335\",\"dw\":\"北京市第一看守所\",\"lxdh\":\"18545451111\",\"snzjh\":\"210212\",\"edz\":\"扫描\"},{\"xm\":\"李四\",\"zjh\":\"110101199003070572\",\"dw\":\"北京市第二看守所\",\"lxdh\":\"15865661111\",\"snzjh\":\"213254121\",\"edz\":\"扫描\"},{\"xm\":\"王五\",\"zjh\":\"110101199003076173\",\"dw\":\"北京市第三看守所\",\"lxdh\":\"15965651214\",\"snzjh\":\"5646545\",\"edz\":\"扫描\"}]"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
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
	 *          "pageSize":"10",
	 *          "pageIndex":"0",
	 *          "sort":"id",
	 *          "order":"desc",
	 *          "page":"1",
	 *          "rows":"10"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("外来人员查询")
	@GetMapping("/wlryList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> wlry_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"pageSize":"10","pageIndex":"0","sort":"id","order":"desc","page":"1","rows":"10"}
		//id,jsbh,jsbhString,lssy,lssyString,jtsy,lfrs,sxcls,drmj,drsj,wljbxx,clxx,state,stateString,creator,createtime,updator,updatetime,crdjbz
		String interfaceId = "/v4/kss/wlrydj/wlryList";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam qParam = new QueryParam();
			String crdjbz = (String) maps.getResult().get("crdjbz");
			String lwsy = (String) maps.getResult().get("lwsy");
			String lssy = (String) maps.getResult().get("lssy");
			String jtsy = (String) maps.getResult().get("jtsy");
			String drmj = (String) maps.getResult().get("drmj");
			String lfrs = (String) maps.getResult().get("lfrs");
			String drsj_start =  (String) maps.getResult().get("blrqstart");
			String drsj_end =  (String) maps.getResult().get("blrqend");
			String drsj_start2 =  (String) maps.getResult().get("drsj_start");
			String drsj_end2 =  (String) maps.getResult().get("drsj_end");
			String createtime_start =  (String) maps.getResult().get("createtime_start");
			String createtime_end =  (String) maps.getResult().get("createtime_end");
			if (!StringUtils.isNullOrEmpty(crdjbz)) {
				qParam.and("crdjbz", TermType.eq, crdjbz);
			}
			if (!StringUtils.isNullOrEmpty(lwsy)) {
				qParam.and("lssy", TermType.eq, lwsy);
			}
			if (!StringUtils.isNullOrEmpty(lssy)) {
				qParam.and("lssy", TermType.like, "%"+lssy+"%");
			}
			if (!StringUtils.isNullOrEmpty(jtsy)) {
				qParam.and("jtsy", TermType.eq, jtsy);
			}
			if (!StringUtils.isNullOrEmpty(lfrs)) {
				qParam.and("lfrs", TermType.eq, lfrs);
			}
			if (!StringUtils.isNullOrEmpty(drmj)) {
				qParam.and("drmj", TermType.like,"%"+drmj+"%" );
			}

			if (!StringUtils.isNullOrEmpty(drsj_start)) {
				qParam.and("drsj", TermType.gte, drsj_start+" 00:00:00" );
			}
			if (!StringUtils.isNullOrEmpty(drsj_end)) {
				qParam.and("drsj", TermType.lte, drsj_end+" 23:59:59"  );
			}
			if (!StringUtils.isNullOrEmpty(createtime_start)) {
				qParam.and("createtime", TermType.gte, createtime_start );
			}
			if (!StringUtils.isNullOrEmpty(drsj_end)) {
				qParam.and("createtime", TermType.lte, createtime_end );
			}

			if (!StringUtils.isNullOrEmpty(drsj_start2)) {
				qParam.and("drsj", TermType.gte, drsj_start2+" 00:00:00" );
			}
			if (!StringUtils.isNullOrEmpty(drsj_end2)) {
				qParam.and("drsj", TermType.lte, drsj_end2+" 23:59:59"  );
			}

			String page = (String) maps.getResult().get("page");
			String rows = (String) maps.getResult().get("rows");
			int pageIndex = 0;
			int pageSize = 10;
			try {
				pageIndex = Integer.valueOf(page).intValue() - 1;
				pageSize = Integer.valueOf(rows).intValue();
			} catch (NumberFormatException e) {
				//e.printStackTrace();
				System.err.println("分页参数出错！");
			} finally {
				qParam.setPageIndex(pageIndex);
				qParam.setPageSize(pageSize);
			}
			qParam.and("state", TermType.eq, "R2");
			qParam.and("jsbh", TermType.eq, jsbh);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wlryQueryForPage(qParam);
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
	 * @api {post} /v4/kss/wlrydj/rydjAdd 人员登记保存
	 * @apiVersion 0.4.0
	 * @apiName rydjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 人员登记保存.
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
	 *					"lssy":"来所事由(必填；最大长度:50)",
	 *       			"drmj":"带入民警(必填；最大长度:50)",
	 *       			"drsj":"带入时间(必填;格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"lssy":"\\S{1,50}","drmj":"\\S{1,50}","drsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"jbxx":[{"xm":"","zjh":"","dw":"","lxdh":"","snzjh":"","edz":"扫描"}],"clxx":[{"clssdw":"","cphm":"","aqjcqk":"","snztzhm":"","clsnzjh":""}],"creator":"管理员","lssy":"dasd","drmj":"fdff","drsj":"2019-10-10 10:10:10"}]}

	@ApiOperation("人员登记保存")
	@PostMapping("/rydjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> rydjAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/wlrydj/rydjAdd";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String lssy = (String) rslist.get(0).get("lssy");
			String drmj = (String) rslist.get(0).get("drmj");
			String jbxx = rslist.get(0).get("jbxx").toString();
			String clxx =  rslist.get(0).get("clxx").toString();
			String creator = (String) rslist.get(0).get("creator");
			WlryModel model=JSONObject.parseObject(rslist.get(0).toString(),WlryModel.class);
			model.setDrsj(new Date());
			model.setLssy(lssy);
			model.setDrmj(drmj);
			model.setWljbxx(jbxx);
			model.setClxx(clxx);
			model.setCreator(creator);
			model.setCreatetime(new Date());
			model.setJsbh(jsbh);
			model.setState("R2");
			model.setCrdjbz("1");
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> result = kssServerApis.wlrydj(model);
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
	 * @api {post} /v4/kss/wlrydj/lkdjUpdata 离开登记保存
	 * @apiVersion 0.4.0
	 * @apiName lkdjUpdata
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 离开登记保存.
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
	 *  "result": "1",
	 *  "status": 200,
	 *  "timestamp": 1578894286922
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
	 *					"lssy":"来所事由(最大长度:50)",
	 *       			"drmj":"带入民警(最大长度:50)",
	 *       			"drsj":"带入时间(格式:yyyy-MM-dd hh:mm:ss)"
	 *          		}]
	 *     	  }
	 */

	//{"id":"\\d{1,23}"}
	//{"entity":[{"id":"11000011420200113000070","lssy":"dasd","drmj":"fdff","drsj":"2019-10-10 10:10:10"}]}
	@ApiOperation("离开登记保存")
	@PostMapping("/lkdjUpdata")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lkdjUpdata(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/wlrydj/lkdjUpdata";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String id = (String) rslist.get(0).get("id");
			WlryModel model=JSONObject.parseObject(rslist.get(0).toString(),WlryModel.class);
			model.setUpdatetime(new Date());
			model.setCrdjbz("0");
			model.setId(id);
			model.setDcsj(new Date());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(StringUtils.isNullOrEmpty(id)){
				return ResponseMessage.error("id不能为空！");
			}
			if(msg.getStatus()!=200) {
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}
			ResponseMessage<String> result = kssServerApis.wlrylk(id,model);
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
	 * @api {post} /v4/kss/wlrydj/wlryYwdt 外来人员业务动态查询
	 * @apiVersion 0.4.0
	 * @apiName wlryYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 外来人员业务动态查询.
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}sxcls											来所车辆
	 * @apiSuccess {String}qt												其他
	 * @apiSuccess {String}sc												视察
	 * @apiSuccess {String}sf												上访
	 * @apiSuccess {String}lfrs												来所人数
	 * @apiSuccess {String}tx												提讯
	 * @apiSuccess {String}cg												参观
	 * @apiSuccess {String}tj												提解
	 * @apiSuccess {String}lsgz												临时工作
	 * @apiSuccess {String}jc												检查
	 * @apiSuccess {String}jshj												家属会见
	 * @apiSuccess {String}lshj												律师会见
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
	 *  "message": "查询成功",,
	 *  "result":
	 *   {
	 *     "total": 1,
	 *     "data": [{
	 *    "wlrySxcls": [
	 *      {
	 *        "sxcls": 8744
	 *      }
	 *    ],
	 *    "wlryLfrs": [
	 *      {
	 *        "lfrs": 378
	 *      }
	 *    ],
	 *    "wlryLssy": [
	 *      {
	 *        "sc": 0,
	 *        "sf": 2,
	 *        "qt": 0,
	 *        "tx": 11,
	 *        "cg": 4,
	 *        "tj": 12,
	 *        "lsgz": 0,
	 *        "jc": 0,
	 *        "jshj": 11,
	 *        "lshj": 4
	 *      }
	 *    ]
	 *  }],
	 *  "page":"1"
	 *  },
	 *  "status": 200,
	 *  "timestamp": 1578898376314
	 *}
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *          "starttime":"开始时间(格式：yyyy-MM-dd hh:mm:ss)",
	 *          "endtime":"结束时间(格式：yyyy-MM-dd hh:mm:ss)",
	 *          "dw":"单位(最大字段长度：50)"
	 *        }
	 *
	 *
	 */
	@OpenAPI
	@ApiOperation("外来人员业务动态查询")
	@PostMapping("/wlryYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> wlryYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		//result
		//{"starttime":"2020-01-08 11:07:38","endtime":"2020-01-10 11:07:38","dw":"fdsf"}
		String interfaceId = "/v4/kss/wlrydj/wlryYwdt";
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			String starttime=(String)maps.getResult().get("starttime");
			String endtime=(String)maps.getResult().get("endtime");
			ResponseMessage<Map<String, Object>> result = kssAnalyseApis.wlryYwdt(jsbh, starttime, endtime);
			System.err.println("--result--" + JSON.toJSONString(result));
			List lists=new ArrayList<>();
			lists.add(result);
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





}
