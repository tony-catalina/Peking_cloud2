/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JshjModel;
import awd.bj.kss.model.LdspModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.CrjjcModelDO;
import awd.cloud.platform.model.kss.JshjModelDO;
import awd.cloud.platform.model.kss.Kss_JshjModel;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
@RequestMapping("/v4/kss/jshj")
@Api(tags = "kss-jshj",description = "Jshj") 
public class Kss_JshjController extends PublicService {
	
	@Autowired
	private KssServerApis kssServerApis;

	@Autowired
	private KssAnalyseApis kssAnalyseApis;


	@Autowired
	private KssService kssService;

	@Autowired
	private ProcessService processService;

	/**
	 * @return
	 * @api {get} /v4/kss/jshj/jbxxForJshj 家属会见基本信息查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForJshj
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 家属会见基本信息查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}xm                                                姓名
	 * @apiSuccess {String}jsbh                                              监所编号
	 * @apiSuccess {String}id                                                ID
	 * @apiSuccess {String}snbh                                              所内编号
	 * @apiSuccess {String}ywlcid                                            业务流程ID
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
	 * "data":
	 * {
	 *         "snbh": "20190068",
	 *         "xm": "测试3",
	 *         "ywlcid": "4861866",
	 *         "id": "11000011420191129000185",
	 *         "jsbh": "110000114"
	 * }
	 *
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
	 * "xm":"姓名(最大字段长度：50)",
	 * "xm_type":"姓名类别(0:根据姓名查；!=0:不根据姓名查)",
	 * "jsh":"监室号(最大字段长度：4)",
	 * "jsh_type":"监室号类别(0：根据监室号查；!=0:不根据监室号查)",
	 * "jbxx_xb":"性别(字典：XB ；最大字段长度：1)",
	 * "xb_type":"性别类型(0:根据性别查；!=0:不根据性别查)",
	 * "rsrq_start":"开始入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "rsrq_end":"结束入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "hjsjstart":"开始会见日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "hjsjend":"结束会见日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "csrq_start":"开始出所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "csrq_end":"结束入所日期(格式:yyyy-MM-dd hh:mm:ss)",
	 * "jbxx_jsbh":"监所编号(最大字段长度：9)",
	 * }
	 */

	@OpenAPI
	@ApiOperation("家属会见基本信息查询")
	@GetMapping("/jbxxForJshj")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jbxxForJshj(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/jshj/jbxxForJshj";
		String state = "R8";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数

			QueryParam qParam = new QueryParam();
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				String xm = maps.getResult().get("xm").toString();
				if ("0".equals(maps.getResult().get("xm_type").toString())) {
					xm = word2py(xm);
					qParam.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
				} else {
					qParam.and("jbxx_xm", TermType.like, "%" + xm + "%");
				}
			}
//        if (!StringUtils.isNullOrEmpty(jsh)) {
//            if ("0".equals(jsh_type)) {
//                qParam.and("jbxx_jsh", TermType.like, "%" + jsh + "%");
//            } else {
//                qParam.and("jbxx_jsh", TermType.not, jsh);
//            }
//        }
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				qParam.and("jbxx_jsh", TermType.like, maps.getResult().get("jsh") + "%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
				String xb = maps.getResult().get("jbxx_xb").toString();
				if ("0".equals(maps.getResult().get("xb_type"))) {
					qParam.and("jbxx_xb", TermType.eq, xb);
				} else {
					qParam.and("jbxx_xb", TermType.not, xb);
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
				qParam.and("jbxx_rsrq", TermType.gte, maps.getResult().get("rsrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
				qParam.and("jbxx_rsrq", TermType.lte, maps.getResult().get("rsrq_end") + " 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("hjsjstart"))) {
				qParam.and("hjsj", TermType.gte, maps.getResult().get("hjsjstart"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("blrqend"))) {
				qParam.and("hjsj", TermType.lte, maps.getResult().get("blrqend") + " 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
				qParam.and("jbxx_csrq", TermType.gte, maps.getResult().get("csrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
				qParam.and("jbxx_csrq", TermType.lte, maps.getResult().get("csrq_end") + " 23:59:59");
			}

			qParam.and("jsbh", TermType.eq, jsbh);
			qParam.and("jbxx_jsbh", TermType.eq, jsbh);
			qParam.and("jbxx_state", TermType.eq, "R8");
			qParam.and("state", TermType.eq, "R2");
			qParam.and("psbz", TermType.not, "2");


			DefaultQueryParam.addDefaultQueryParam(request, qParam, state);

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jbxxForJshj(qParam);

			System.err.println("result" + JSON.toJSONString(result));

			//封装需要的数据
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

	public String word2py(String word) {
		if (StringUtils.isNullOrEmpty(word)) {
			return "";
		}
		return Pinyin4j.cn2Pinyin(word);

	}


	/**
	 * @return
	 * @api {get} /v4/kss/jshj/jshjHjs 家属会见和监室查询
	 * @apiVersion 0.4.0
	 * @apiName jshjHjs
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 家属会见和监室查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}BHJRGX          				                 被会见人关系(GX)
	 * @apiSuccess {String}STATE                                         状态
	 * @apiSuccess {String}LY                                            理由
	 * @apiSuccess {String}HJS                                           会见室
	 * @apiSuccess {String}SJMJ                                          收监民警
	 * @apiSuccess {String}DETFRXM                                       第二探访人姓名
	 * @apiSuccess {String}TFRS                                          探访人数
	 * @apiSuccess {String}JSZJH                                         家属证件号
	 * @apiSuccess {String}CREATOR                                       创建人
	 * @apiSuccess {String}ZJLX                                          证件类型(ZJLX)
	 * @apiSuccess {String}DETFRGX                                       第二探访人关系（GX）
	 * @apiSuccess {String}BZ                                            备注
	 * @apiSuccess {String}ID                                            ID
	 * @apiSuccess {String}CREATETIME                                    跟新时间
	 * @apiSuccess {String}jxm                                           姓名
	 * @apiSuccess {String}PASTABLE                                      是否有效(SHFO)
	 * @apiSuccess {String}DSTFRGX                                       第三探访人关系（GX）
	 * @apiSuccess {String}RYBH                                          人员编号
	 * @apiSuccess {String}DSTFRXM                                       第三探访人姓名
	 * @apiSuccess {String}JSBH                                          监所编号
	 * @apiSuccess {String}DETFRSFSW                                     第二探访人是否涉维（SHFO）
	 * @apiSuccess {String}XM                                            姓名
	 * @apiSuccess {String}BADWYJ                                        办案单位意见
	 * @apiSuccess {String}HJSJ                                          会见时间
	 * @apiSuccess {String}YWLCID                                        业务流程ID
	 * @apiSuccess {String}PSBZ                                          批示标志(PSBZ)
	 * @apiSuccess {String}jsh                                           监室号
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
	 * "data": [
	 *       {
	 *               "BHJRGX": "235",
	 *               "STATE": "R2",
	 *               "LY": "3",
	 *               "HJS": "0",
	 *               "SJMJ": "爱仕达",
	 *               "DETFRXM": "",
	 *               "TFRS": "",
	 *               "JSZJH": "123123123",
	 *               "CREATOR": "管理员",
	 *               "ZJLX": "99",
	 *               "DETFRGX": "",
	 *               "BZ": "",
	 *               "ID": "11000011420191203000193",
	 *               "CREATETIME": 1575336749000,
	 *               "jxm": "11",
	 *               "PASTABLE": "1",
	 *               "DSTFRGX": "",
	 *               "RYBH": "110000114201909040001",
	 *               "DSTFRXM": "",
	 *               "JSBH": "110000114",
	 *               "DSTFRSFSW": "2",
	 *               "DETFRSFSW": "2",
	 *               "XM": "阿斯顿",
	 *               "BADWYJ": "",
	 *               "HJSJ": "2019-12-03 09:28:17",
	 *               "YWLCID": "4960774",
	 *               "PSBZ": "0",
	 *               "jsh": "0303"
	 *             }
	 *        ]
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
	 * "hjs":"会见室(必填; 最大字段长度：10)"
	 * }
	 */
	@OpenAPI
	@ApiOperation("家属会见和监室查询")
	@GetMapping("/jshjHjs")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jshjHjs(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/jshj/jshjHjs";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数

			String hjs = "业务动态";
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("hjs"))) {
				hjs = maps.getResult().get("hjs").toString();
			}
			System.err.println("hjs--" + hjs);

			ResponseMessage<Map<String, Object>> result = kssServerApis.jshjHjs(hjs, jsbh);

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
	 * @api {post} /v4/kss/jshj/jshjAdd  家属会见保存
	 * @apiVersion 0.4.0
	 * @apiName jshjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 家属会见保存
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 * "entity":[{
	 *          "processid": "过程ID",
	 * 		    "ywlcid": "业务流程ID(必填;最大字段长度：21)",
	 * 		     "ly":"会见事由(必填;最大字段长度：100)",
	 * 		    "creator": "创建人(必填；最大字段长度：50)",
	 * 		    "rybh": "人员编号(必填；最大字段长度：21)",
	 *  	    "zjlx": "证件类型(必填；字典：ZJLX；最大字段长度：2)",
	 * 	        "xb": "性别(必填；字典：XB；最大字段长度：1)",
	 * 	        "jsxm": "家属姓名(必填；最大字段长度：30)",
	 * 	        "gx": "与在押人员关系(GX)(必填；字典：XB；最大字段长度：3)",
	 *      	"dh": "联系电话(必填；最大字段长度：40)",
	 *  	    "sj": "手机号码(必填；最大字段长度：11)",
	 *  	    "dwdh": "单位电话(必填；最大字段长度：11)",
	 *  	    "gzdw":"工作单位(必填；最大字段长度：60)",
	 *  	    "zy": "职业(必填；最大字段长度：50)",
	 *      	"dz": "联系地址(必填；最大字段长度：100)",
	 *  	    "zzdz": "暂住地址(必填；最大字段长度：255)",
	 *  	    "sfswgx": "是否涉维关系(必填；字典：SHFO；最大字段长度：1)"，
	 *  	    "jszjh":"家属证件号(必填；最大字段长度：100)"
	 *     }]
	 * }
	 *
	 *
	 */
	//{"processid":".{1,30}","ywlcid":"\\d{1,21}","ly":".{1,100}","creator":".{1,50}","rybh":"\\d{1,21}","zjlx":"\\d{1,1}","xb":"\\d{1,1}","jsxm":".{1,30}","gx":"\\d{1,3}","dh":".{1,40}","sj":"\\d{1,11}","dwdh":"\\d{1,11}","gzdw":".{1,60}","zy":".{1,50}","dz":".{1,100}","zzdz":".{1,255}","sfswgx":"\\d{1,1}","jszjh":".{1,100}"}
	@ApiOperation("家属会见保存")
	@PostMapping("/jshjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jshj_save( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/jshj/jshjAdd";

		//通过校验获取查询参数
		try {
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
				System.out.println("----------------" + interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			String flowKey = CacheUtils.get().getFlowKey("kss_jshj".toUpperCase());
			String key = "kss_jshj".toUpperCase();

			List<JshjModel> list = JSONArray.parseArray(map.get("entity").toString(), JshjModel.class);
			List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Map<String,Object> entityMap = mapsList.get(0);

			JshjModel jshjModel=list.get(0);
			if (processService.FlowMutex(jsbh, jshjModel.getRybh(), key.toUpperCase(), "JSHJ").getStatus() !=200) {
				return processService.FlowMutex(jsbh, jshjModel.getRybh(), key.toUpperCase(), "JSHJ");
			}
			jshjModel.setState("R2");
			jshjModel.setPastable("1");
			jshjModel.setJsbh(jsbh);
			jshjModel.setCreatetime(new Date());
			jshjModel.setPsbz("0");

			//保存家属会见中涉及社会关系的字段
			Map<String, Object> dataMap = Maps.newHashMap();
			Map<String, String> params = Maps.newHashMap();


			params.put("zjlx",entityMap.get("zjlx").toString());
			params.put("xb",entityMap.get("xb").toString());
			params.put("jsxm",entityMap.get("jsxm").toString());
			params.put("gx",entityMap.get("gx").toString());
			params.put("dh", entityMap.get("dh").toString()  );
			params.put("sj",entityMap.get("sj").toString()      );
			params.put("gzdw",entityMap.get("gzdw").toString());
			params.put("dwdh",entityMap.get("dwdh").toString());
			params.put("zy",entityMap.get("zy").toString());
			params.put("dz",entityMap.get("dz").toString());
			params.put("zzdz",entityMap.get("zzdz").toString());
			params.put("sfswgx",entityMap.get("sfswgx").toString());

			dataMap.put("params",params);
			dataMap.put("jshjModel",jshjModel);

			ResponseMessage<String> result = kssServerApis.jshjAddflow(flowKey, dataMap);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}



	/**
	 * @api {post} /v4/kss/jshj/trdjAdd  提人登记
	 * @apiVersion 0.4.0
	 * @apiName trdjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 提人登记
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 * 	"entity": [{
	 * 		"ywlcid": "业务流程ID(必填; 最大字段长度：21)",
	 * 		"rybh": "人员编号(必填; 最大字段长度：21)",
	 * 		"creator": "创建人(必填; 最大字段长度：50)",
	 * 		"createtime": "创建时间(格式：yyyy-MM-dd HH:mm:ss)",
	 * 		"dlmj": "代理民警(必填; 最大字段长度：100)",
	 * 		"blsj": "办理时间(格式：yyyy-MM-dd HH:mm:ss)",
	 * 		"zssbqk": "自述受伤情况",
	 * 		"wjpqk": "违禁品情况",
	 * 		"ywqxyc": "有无异常(必填;字典：YWYC； 最大字段长度：1)",
	 * 		"qxycqk": "情绪异常情况",
	 * 		"taskid": "任务id(必填)"
	 *        }]
	 * }
	 */
	//{"ywlcid":".{1,21}","rybh":".{1,21}","creator":".{1,50}","createtime":"\\d{4}-\\d{2}-\\d{2}","dlmj":".{1,100}","blsj":"\\d{4}-\\d{2}-\\d{2}","ywqxyc":"\\d{1,1}"}
	@ApiOperation("提人登记")
	@PostMapping("/trdjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> trdjAdd( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/jshj/trdjAdd";

		//通过校验获取查询参数
		try {
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
				System.out.println("----------------" + interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}


			List<CrjjcModelDO> list = JSONArray.parseArray(map.get("entity").toString(), CrjjcModelDO.class);
			CrjjcModelDO model =list.get(0);

			List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Map<String,Object> entityMap = mapsList.get(0);

			String taskid = entityMap.get("taskid").toString();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			model.setBlsj(sdf.parse(entityMap.get("blsj").toString()+ " 00:00:00"));
			model.setCreatetime(model.getCreatetime());

			String flag = "1";
			model.setJsbh(jsbh);
			model.setState("R2");
			model.setBllx("1");



			System.err.println(JSONUtil.toJson(map));
			System.out.println(JSONUtil.toJson(model)+"11111111111111111111111");

			ResponseMessage<String> result = kssServerApis.addJshjCrjjc(flag,taskid,model);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
				result.setMessage("服务异常,保存失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}



	/**
	 * @api {post} /v4/kss/jshj/ldspAdd  家属会见领导审批
	 * @apiVersion 0.4.0
	 * @apiName ldspAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 家属会见领导审批
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 * "message": "保存成功!",
	 * "result": "11000011420191214000011",
	 * "status": 200,
	 * "timestamp": 1576308305534
	 * }
	 * @apiUse CreateError
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 * 	"entity": [{
	 * 	     "zxtzssdsj":"",
	 * 	     "taskid":"任务ID",
	 * 		 "ywlcid":"业务流程ID(必填;最大字段长度:50)",
	 * 	     "psbz":"审批意见(批示结果)(必填；字典：PSJG;最大字段长度:1)",
	 * 	     "blr":"办理人(必填;最大字段长度:50)",
	 *     	 "blsj":"办理时间(必填;格式：yyyy-MM-dd HH:mm:ss)",
	 * 	     "ldyj":"领导审批(必填;最大字段长度:500)",
	 *    	 "creator":"创建人(必填;最大字段长度:50)",
	 *  	 "creatime":"创建时间(必填;格式：yyyy-MM-dd HH:mm:ss)",
	 *  	 "rybh":"人员编号(必填；最大字段长度:21)",
	 * 	     "jbxxXm": "姓名(必填；最大字段长度:50)",
	 * 	     "sqyy":"申请原因(必填；最大字段长度:500)"
	 * 	     "sqr":"申请人(必填；最大字段长度:50)"
	 *        }]
	 * }
	 */
	//{"zxtzssdsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ywlcid":".{1,21}","rybh":".{1,21}","creator":".{1,50}","createtime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","psbz":"\\d{1,1}","blr":".{1,50}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ldyj":".{1,500}","jbxxXm":".{1,50}","sqyy":".{1,500}","sqr":".{1,50}"}
	@ApiOperation("家属会见领导审批")
	@PostMapping("/ldspAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ldspAdd( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/jshj/ldspAdd";

		//通过校验获取查询参数
		try {
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
				System.out.println("----------------" + interfaceId);
				return ResponseMessage.error(msg.getMessage());
			}

			List<LdspModel> list = JSONArray.parseArray(map.get("entity").toString(), LdspModel.class);
			LdspModel model =list.get(0);

			List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
			Map<String,Object> entityMap = mapsList.get(0);

			Map<String,Object> ldspmap = new HashMap<>();
			Map<String,String> stringMap = new HashMap<>();
			stringMap.put("zxtzssdsj",entityMap.get("zxtzssdsj").toString());

				String taskid = entityMap.get("taskid").toString();
				if(StringUtils.isNullOrEmpty(taskid)) {
					return ResponseMessage.error("taskid必传");
				}
				model.setJsbh(jsbh);
				model.setState("R2");
				System.err.println(JSONUtil.toJson(model));
				System.err.println("===============================");
			    ldspmap.put("ldspModel",model);
			    ldspmap.put("stringMap",stringMap);

				ResponseMessage<String> result = kssServerApis.addJshjLdsp(taskid, ldspmap);

			if (result.getStatus() == 200) {
				result.setMessage("保存成功!");
			} else {
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
	public ResponseMessage<String> jshj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JshjModel data) {
		return kssService.jshj_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JshjModel> jshj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jshj_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jshj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jshj_deleteByKey(id);
	}


	/**
	 * @api {post} /v4/kss/jshj/aqjcAdd  会见完毕安全检查保存
	 * @apiVersion 0.4.0
	 * @apiName aqjcAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 会见完毕安全检查保存.
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
	 *           "taskid":"任务id",
	 *   		 "entity":[{
	 *   		 		"creator":"创建者(必填;最大字段长度:50)",
	 *					"ywlcid":"业务流程ID(必填;最大字段长度:21)",
	 *					"jcr":"检查人(必填;最大字段长度:255)",
	 *					"jcsj":"检查时间(必填;格式yyyy-MM-dd HH:mm:ss)",
	 *					"cbjcjg":"初步检查结果(必填)",
	 *					"ycqksm":"异常情况说明(必填)",
	 *					"jssj":"会见时间(必填;格式yyyy-MM-dd HH:mm:ss)",
	 *					"createtime":"创建时间(必填;格式yyyy-MM-dd HH:mm:ss)",
	 *					"rybh":"人员编号(必填;最大字段长度:21)",
	 *					"updator":"更新人(必填;最大字段长度:50)"
	 *               }]
	 *          }
	 *
	 * @apiUse CreateError
	 */
	@ApiOperation("会见完毕安全检查保存")
	@PostMapping("/aqjcAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> aqjcAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jshj/aqjcAdd";
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
			if (StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))) {
				return ResponseMessage.error( "请输入taskID" );
			}
			//封装数据
			List<JshjModelDO> list = JSONArray.parseArray(map.get("entity").toString(), JshjModelDO.class);

			if (StringUtils.isNullOrEmpty(list.get(0).getCbjcjg())) {
				return ResponseMessage.error( "请输入初步检查结果" );
			}
			if (StringUtils.isNullOrEmpty(list.get(0).getYcqksm())) {
				return ResponseMessage.error( "请输入异常情况说明" );
			}

			JshjModelDO jshjModelDO = list.get(0);
			jshjModelDO.setJsbh(jsbh);
			String taskid = maps.getResult().get("taskid").toString();
			ResponseMessage<String> jshj = kssServerApis.updateJshj(taskid,jshjModelDO);
			System.err.println("--"+JSON.toJSONString(jshj));
			if(jshj.getStatus() == 200){
				jshj.setMessage("保存成功!");
			}else{
				jshj.setMessage("服务异常,保存失败!");
			}
			return jshj;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}


	/**
	 * @api {post} /v4/kss/jshj/drjsAdd  带入监室
	 * @apiVersion 0.4.0
	 * @apiName drjsAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription  带入监室.
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
	 *           "taskid":"任务id",
	 *   		 "entity":[{
	 *   		 		"creator":"创建者(必填;最大字段长度:50)",
	 *					"ywlcid":"业务流程ID(必填;最大字段长度:21)",
	 *					"blsj":"会见时间(必填;格式yyyy-MM-dd HH:mm:ss)",
	 *					"createtime":"创建时间(必填;格式yyyy-MM-dd HH:mm:ss)",
	 *					"dlmj":"带领民警(必填;最大字段长度:100)",
	 *					"ywtbs":"有无体表伤(必填;字典:ywyc,最大字段长度:1)",
	 *					"ywwjp":"有无违禁品(必填;字典:ywyc,最大字段长度:1)",
	 *					"ywqxyc":"有无异常(必填;字典:ywyc,最大字段长度:1)",
	 *					"sbqk":"伤病情况(必填)",
	 *					"wjpqk":"违禁品情况"(必填),
	 *					"qxycqk":"异常情况说明(必填)",
	 *					"rybh":"人员编号(必填;=最大字段长度:21)"
	 *               }]
	 *          }
	 *
	 * @apiUse CreateError
	 */
	@ApiOperation("带入监室")
	@PostMapping("/drjsAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> addJshjDrjs(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/jshj/drjsAdd";
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
			if (StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))) {
				return ResponseMessage.error( "请输入taskID" );
			}
			//封装数据
			List<CrjjcModelDO> list = JSONArray.parseArray(map.get("entity").toString(), CrjjcModelDO.class);
			CrjjcModelDO crjjcModelDO = list.get(0);
			if (StringUtils.isNullOrEmpty(crjjcModelDO.getSbqk())) {
				return ResponseMessage.error( "请输入伤病情况" );
			}
			if (StringUtils.isNullOrEmpty(crjjcModelDO.getWjpqk())) {
				return ResponseMessage.error( "请输入违禁品情况说明" );
			}
			if (StringUtils.isNullOrEmpty(crjjcModelDO.getQxycqk())) {
				return ResponseMessage.error( "请输入异常情况说明" );
			}
			String flag = "2";
			crjjcModelDO.setJsbh(jsbh);
			crjjcModelDO.setState("R2");
			crjjcModelDO.setBllx("2");
			String taskid = maps.getResult().get("taskid").toString();
			ResponseMessage<String> addJshjAqjc = kssServerApis.addJshjCrjjc(flag,taskid,crjjcModelDO);
			System.err.println("--"+JSON.toJSONString(addJshjAqjc));
			if(addJshjAqjc.getStatus() == 200){
				addJshjAqjc.setMessage("保存成功!");
			}else{
				addJshjAqjc.setMessage("服务异常,保存失败!");
			}
			return addJshjAqjc;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}





}
