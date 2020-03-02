/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_PjdjModel;
import awd.cloud.platform.model.kss.PjdjModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/pjdj")
@Api(tags = "kss-pjdj",description = "Pjdj") 

public class Kss_PjdjController  extends PublicService {

	@Autowired
	private KssAnalyseApis kssAnalyseApis;
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;


	/**
	 * @return
	 * @api {get} /v4/kss/pjdj/pjdjYwdt 处理结果业务动态
	 * @apiVersion 0.4.0
	 * @apiName pjdjYwdt
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 处理结果业务动态
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 * @apiSuccess {String}pjdj                                             判决登记
	 * @apiSuccess {String}cs                                               工作措施
	 * @apiSuccess {String}cf                                                刑事处罚
	 * @apiSuccess {String}cl                                                行政治安处理
	 * @apiSuccess {String}zrs                                               总人数
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
	 *       {
	 *         "result": {
	 *           "pjdj": [
	 *             {
	 *               "cs": 0,
	 *               "cf": 13,
	 *               "cl": 0,
	 *               "zrs": 13
	 *             }
	 *           ]
	 *         }
	 *       }
	 *     ],
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
	 * "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
	 * "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
	 * }
	 */
	@OpenAPI
	@ApiOperation("处理结果业务动态")
	@GetMapping("/pjdjYwdt")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> pjdjYwdt(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/pjdj/pjdjYwdt";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数


			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}

			String starttime = (String) maps.getResult().get("starttime");
			String endtime = (String) maps.getResult().get("endtime");

			if (!StringUtils.isNullOrEmpty(starttime)) {
				param.and("starttime", TermType.eq, starttime);
			}
			if (!StringUtils.isNullOrEmpty(endtime)) {
				param.and("endtime", TermType.eq, endtime);
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);

			ResponseMessage<Map<String, Object>> result = kssAnalyseApis.pjdjYwdt(jsbh, starttime, endtime);

			System.err.println("result" + JSON.toJSONString(result));

			List lists = new ArrayList<>();
			lists.add(result);

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity", lists);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total", result.getResult().size());
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
	 * @api {post} /v4/kss/pjdj/pjdjAdd  判决登记保存
	 * @apiVersion 0.4.0
	 * @apiName pjdjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 判决登记保存
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
	 *
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳

	 *
	 *
	 *  @apiSuccessExample {json} 返回 (成功):
	 *      HTTP/1.1 200 OK
	 *{
	 *   "message": "保存成功!",
	 *   "result": "11000011420191214000011",
	 *   "status": 200,
	 *   "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 * 	   "entity": [{
	 *  	    "xm": "姓名(必填; 最大字段长度：50)",
	 *      	"bdzzqlsj": "剥夺政治权利时间(必填; 最大字段长度：9)",
	 *      	"bz": "备注(最大字段长度：200)",
	 *  	    "cljg": "处理结果(必填; 字典：CLJG;最大字段长度：3)",
	 *  	    "createtime": "创建时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *  	    "creator": "创建人(必填; 最大字段长度：30)",
	 *  	    "fjje": "罚金金额(最大字段长度：10)",
	 *  	    "fjx": "附加刑(字典：FJX;最大字段长度：1)",
	 *      	"hxqx": "缓刑刑期(必填; 最大字段长度：20)",
	 *  	    "jbxxCsrq": "出所时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *  	    "jbxxHjd": "户籍地(必填; 字典：XZQH;最大字段长度：6)",
	 *       	"jbxxJsh": "监室号(必填; 最大字段长度：4)",
	 *      	"jbxxRsrq": "入所日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *  	    "jbxxSnbh": "所内编号(必填; 最大字段长度：8)",
	 *      	"jbxxXm": "姓名(必填; 最大字段长度：50)",
	 *  	    "pjdw": "判决单位(必填; 最大字段长度：60)",
	 *  	    "pjrq": "判决日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *  	    "qsrq": "起始日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *       	"rybh": "人员编号(必填; 最大字段长度：21)",
	 *  	    "sdrq": "送达日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *  	    "wsh": "文书号(最大字段长度：60)",
	 * 	        "xq": "刑期(必填; 最大字段长度：6)",
	 *  	    "zdbllsfx": "自动办理留所服刑(必填;0:是；1：否)"
	 *        }]
	 * }
	 *
	 */
	//{"xm":".{1,50}","bdzzqlsj":".{1,9}","bz":".{0,200}","cljg":"\\d{1,3}","createtime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}","fjje":"\\d{0,10}","fjx":"\\d{1,1}","hxqx":".{1,20}","jbxxCsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbxxHjd":"\\d{1,6}","jbxxJsh":"\\d{1,4}","jbxxRsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbxxSnbh":"\\d{1,8}","jbxxXm":".{1,50}","pjdw":".{1,60}","pjrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","qsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":"\\d{1,21}","sdrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","wsh":".{0,60}","xq":".{1,6}","zdbllsfx":"\\d{0,1}"}

	@ApiOperation("判决登记保存")
	@PostMapping("/pjdjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> pjdjAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/pjdj/pjdjAdd";

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
				System.out.println("----------------"+interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			List<PjdjModelDO> list = JSONArray.parseArray(map.get("entity").toString(), PjdjModelDO.class);
			PjdjModelDO pjdjEntity=list.get(0);
			List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Map<String,Object> entityMap = mapsList.get(0);

			if (!StringUtils.isNullOrEmpty(CacheUtils.get().getDictionary("BADW", pjdjEntity.getPjdw()))) {
				pjdjEntity.setPjdw(CacheUtils.get().getDictionary("BADW", pjdjEntity.getPjdw()));
			}
			pjdjEntity.setState("R2");
			pjdjEntity.setJsbh(jsbh);
			pjdjEntity.setCreator(entityMap.get("xm").toString());
			pjdjEntity.setCreatetime(new Date());

			ResponseMessage<String> result = kssServerApis.pjdjSave(pjdjEntity);

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
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> pjdj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_PjdjModel data) {
		return kssService.pjdj_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_PjdjModel> pjdj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.pjdj_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> pjdj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.pjdj_deleteByKey(id);
	}

	/**
	 * @api {post} /v4/kss/pjdj/jbxxForPjdj  判决登记查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForPjdj
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription    判决登记查询
	 *
	 * @apiParam {String}appcode 											应用代码(必填)
	 * @apiParam {String}jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String}json 												查询参数集
	 *
	 * @apiSuccess {String}creator          				                人员编号
	 * @apiSuccess {String}rybh                                             人员编号
	 * @apiSuccess {String}state                                            状态(数字字典:ywstate)
	 * @apiSuccess {String}jsbh                                             监所编号
	 * @apiSuccess {String}createtime                                       创建时间
	 * @apiSuccess {String}xm                                               姓名
	 * @apiSuccess {String}ayString                                         主要案由(已转换)
	 * @apiSuccess {String}ay                                               主要案由(数字字典:ajlb)
	 * @apiSuccess {String}jsh                                              监室号
	 * @apiSuccess {String}id                                               id
	 * @apiSuccess {String}hxqx                                             缓刑刑期
	 * @apiSuccess {String}cljg                                             处理结果(数字字典:cljg)
	 * @apiSuccess {String}bdzzqlsj                                         剥夺政治权利时间
	 * @apiSuccess {String}cligString                                       处理结果(已转换)
	 * @apiSuccess {String}xmrq                                             刑满日期
	 * @apiSuccess {String}pjrq                                             判决日期
	 * @apiSuccess {String}wsh                                              文书号
	 * @apiSuccess {String}fjje                                             罚金金额
	 * @apiSuccess {String}snbh                                             人员所内编号
	 * @apiSuccess {String}qsrq                                             起始日期
	 * @apiSuccess {String}bz                                               备注
	 * @apiSuccess {String}hxjsrq                                           缓刑结束日期
	 * @apiSuccess {String}sdrq                                             送达日期
	 * @apiSuccess {String}hxksrq                                           缓刑开始日期
	 * @apiSuccess {String}pjsxrq                                           判决生效日期
	 * @apiSuccess {String}fjx                                              附加刑(数字字典:fjx)
	 * @apiSuccess {String}pjdw                                             判决单位
	 * @apiSuccess {String}xq                                               刑期
	 * @apiSuccess {String}pjrqString                                       判决日期(已转换)
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
	 *
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 13,
	 *     "data": [
	 *       {
	 *         "hxqx": "0,10,0",
	 *         "cljg": "104",
	 *         "bdzzqlsj": "null",
	 *         "cljgString": "无期徒刑",
	 *         "xmrq": 1606233600000,
	 *         "pjrq": 1574611200000,
	 *         "wsh": "null",
	 *         "fjje": "1200",
	 *         "snbh": "20190035",
	 *         "qsrq": 1574611200000,
	 *         "bz": "null",
	 *         "id": "11000011420191125000140",
	 *         "state": "R2",
	 *         "hxjsrq": 1600963200000,
	 *         "creator": "管理员",
	 *         "createtime": 1574660533000,
	 *         "sdrq": 1574611200000,
	 *         "hxksrq": 1574611200000,
	 *         "pjsxrq": 1574611200000,
	 *         "xm": "汪枫桦",
	 *         "fjx": "0",
	 *         "ayString": "煽动分裂国家案",
	 *         "ay": "010130",
	 *         "rybh": "310000111201906200004",
	 *         "pjdw": "北京市公安局国保局指挥处",
	 *         "xq": "1,0,0",
	 *         "pjrqString": "2019-11-25 00:00:00",
	 *         "jsh": "0814",
	 *         "jsbh": "110000114"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578640952036
	 * }


	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 * 	  json:{
	 *             "rybh":"人员编号"
	 *         }
	 *
	 *
	 */

	@ApiOperation("判决登记查询")
	@PostMapping("/jbxxForPjdj")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jbxxForPjdl(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/pjdj/jbxxForPjdj";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			String xm=null;
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))){
				xm=(String) maps.getResult().get("xm");
			}

			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh").toString());
			}

			if (!StringUtils.isNullOrEmpty((String)maps.getResult().get("xm"))) {
				if ("0".equals( (String) maps.getResult().get("xm"))) {
					xm = word2py(xm);
					param.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
				} else {
					param.and("jbxx_xm", TermType.like, "%" + xm + "%");
				}
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))){
				param.and("jbxx_jsh", TermType.like, maps.getResult().get("jsh").toString()+"%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
				if ("0".equals((String) maps.getResult().get("jbxx_xb_type"))) {
					param.and("jbxx_xb", TermType.eq, maps.getResult().get("jbxx_xb").toString());
				} else {
					param.and("jbxx_xb", TermType.not, maps.getResult().get("jbxx_xb").toString());
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqstart"))) {
				param.and("pjrq", TermType.gte, maps.getResult().get("blrqstart").toString());
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
				param.and("pjrq", TermType.lte, maps.getResult().get("blrqend").toString());
			}

			param.and("jbxx_state", TermType.eq, "R8");
			param.and("state", TermType.eq, "R2");
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.jbxxForPjdj(param);
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
	public String word2py(String word) {
		if (StringUtils.isNullOrEmpty(word)) {
			return "";
		}
		return Pinyin4j.cn2Pinyin(word);

	}



}
