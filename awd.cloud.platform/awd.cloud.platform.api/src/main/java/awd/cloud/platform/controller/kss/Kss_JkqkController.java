/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JkqkModel;
import awd.bj.kss.model.TmsqModel;
import awd.bj.kss.model.TmtzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.JkTmModal;
import awd.cloud.platform.model.kss.JkqkModelDO;
import awd.cloud.platform.model.kss.Kss_JkqkModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jkqk")
@Api(tags = "kss-jkqk",description = "Jkqk") 
public class Kss_JkqkController  extends PublicService {
	@Autowired
	private KssServerApis kssServerApis;

	@Autowired
	private KssService kssService;


	/**
	 * @api {get} /v4/kss/jkqk/jkqkList 健康情况查询
	 * @apiVersion 0.4.0
	 * @apiName jkqkList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 健康情况查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}xm          				                        姓名
	 * @apiSuccess {String}jsbh         				                    监所编号
	 * @apiSuccess {String}creator                                          创建人
	 * @apiSuccess {String}id                                               ID

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
	 *     "data": [
	 *       {
	 *          "creator": "管理员",
	 *         "xm": "淳于轮怡",
	 *         "id": "11000011420190801000220",
	 *         "jsbh": "110000114"
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
	 *          "rybh":"人员编号(最大字段长度：21)",
	 *        }
	 *

	 */

	@OpenAPI
	@ApiOperation("健康情况查询")
	@GetMapping("/jkqkList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jkqk_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {


		String interfaceId = "/v4/kss/jkqk/jkqkList";
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
				param.and("jsbh", TermType.eq,jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
			}
			if (!StringUtils.isNullOrEmpty(state)){
				param.and("state", TermType.eq, state);
			}


			DefaultQueryParam.addDefaultQueryParam(request, param, state);

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jkqkQueryForPage(param);

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



	/**
	 * @api {post} /v4/kss/jkqk/firstJkjc 第一次健康检查
	 * @apiVersion 0.4.0
	 * @apiName firstJkjc
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 第一次健康检查
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												保存参数集(必填)
	 *
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
	 *                  json:{"entity":[{
	 *                        "ysyj":" 医生意见(必填; 最大字段长度：100)",
	 *                         "yy":"表达能力(必填;最大字段长度：20)",
	 *                         "brbs":"本人病史()",
	 *                         "jkzk":"健康状况(必填;字典：JKZK)",
	 *                         "updator":"跟新人(必填;最大字段长度：50)",
	 *                         "taskid":"任务ID(必填)"
	 *                    }]
	 *                      }
	 *
	 */

	//{"ysyj":".{1,100}","yy":"\\d{1,20}","jkzk":"\\d{1,1}","updator":".{1,50}"}
	@ApiOperation("第一次健康检查")
	@PostMapping("firstJkjc")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> firstJkjc(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

		String interfaceId = "/v4/kss/jkqk/firstJkjc";

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

			ResponseMessage<String> result = new ResponseMessage<>();

				//处理前台参数
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
				MultipartFile pic1 = multiRequest.getFile("pic1");
				MultipartFile pic2 = multiRequest.getFile("pic2");
				MultipartFile pic3 = multiRequest.getFile("pic3");

				String formData = request.getParameter("formData");

				JSONObject jsonObject = JSONObject.parseObject(formData);
				String dah = jsonObject.getString("dah");
				String jsh = jsonObject.getString("jsh");
				String sydw = jsonObject.getString("sydw");
				String gcbh = jsonObject.getString("gcbh");
				String xm = jsonObject.getString("xm");
				String xb = jsonObject.getString("xb");
				String id = jsonObject.getString("id");
				String jkzkString = request.getParameter("jkzkString");
				JkqkModelDO jkqkModel = JSONUtil.toBean(formData, JkqkModelDO.class);
				TmtzModel tmtzModel = JSONUtil.toBean(formData, TmtzModel.class);
				jkqkModel.setId("");
				tmtzModel.setId("");
				//任务ID
				String taskid = request.getParameter("taskid");

				if (taskid == null || "".equals(taskid)) {
					return ResponseMessage.error(Result.ERR_SAVE, "保存失败，请联系管理员！！！");//
				}
				//是否接受标记
				String sfyjs = jkqkModel.getSfyjs();
				if (StringUtils.isNullOrEmpty(sfyjs)) {
					return ResponseMessage.error(Result.ERR_SAVE, "未说明该人是否接收入所！！");

				}

				List<JkqkModel> jkqkList = JSONArray.parseArray(map.get("entity").toString(), JkqkModel.class);

				JkqkModel jkqkModel1 = jkqkList.get(0);
				//处理健康情况表数据
				jkqkModel1.setState("R2");
				jkqkModel1.setJclx("1"); //入所检查
				jkqkModel1.setSjzljsbz("1");
				jkqkModel1.setCreator(jkqkModel1.getCreator());
				jkqkModel1.setCreatetime(new Date());
				// jkqkModel1.setUpdator(request.getParameter("updator"));
				// jkqkModel1.setYsyj(request.getParameter("ysyj"));
				// jkqkModel1.setYy(request.getParameter("yy"));
				// jkqkModel1.setBrbs(request.getParameter("brbs"));
				jkqkModel1.setSfyjs(sfyjs);
				jkqkModel1.setTbsqyy("0");
				jkqkModel1.setYlnxrsjc("0");
				jkqkModel1.setLxbjc("0");
			//	jkqkModel1.setJkzk("3");
				jkqkModel1.setWxys("0");
				jkqkModel1.setLyr("0");
				jkqkModel1.setSdrydj("0");
				jkqkModel1.setAzbryjc("0");
				jkqkModel1.setTnbryjc("0");
				jkqkModel1.setXb(xb);

				//处理体貌特征表
				tmtzModel.setState("R2");
				tmtzModel.setJsbh(jsbh);
				tmtzModel.setCreator(jkqkModel1.getCreator());
				tmtzModel.setCreatetime(new Date());

				//处理体貌伤情
				TmsqModel tmsqModel = new TmsqModel();
				tmsqModel.setJsbh(jsbh);
				tmsqModel.setRybh(jkqkModel.getGcbh());
				tmsqModel.setState("R2");
				tmsqModel.setCreator(jkqkModel1.getCreator());
				tmsqModel.setCreatetime(new Date());


				// if (pic1 != null) {
				// 	String pic1Url = "";
				// 	String fileName = pic1.getOriginalFilename();
				// 	String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				// 	try {
				// 		pic1Url = kssServerApis.uploadZpDfs(fileName, pic1.getBytes(), ext);
				// 	} catch (IOException e) {
				// 		e.printStackTrace();
				// 	}
				// 	tmsqModel.setPhoto1url(pic1Url);
				// 	//tmsqModel.setPhoto1(Base64Utils.encode(pic1.getBytes()));
				// }
				//
				// if (pic2 != null) {
				// 	String pic2Url = "";
				// 	String fileName = pic2.getOriginalFilename();
				// 	String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				// 	try {
				// 		pic2Url = kssServerApis.uploadZpDfs(fileName, pic2.getBytes(), ext);
				// 	} catch (IOException e) {
				// 		e.printStackTrace();
				// 	}
				// 	tmsqModel.setPhoto2url(pic2Url);
				// 	//tmsqModel.setPhoto2(Base64Utils.encode(pic2.getBytes()));
				// }
				//
				// if (pic3 != null) {
				// 	String pic3Url = "";
				// 	String fileName = pic3.getOriginalFilename();
				// 	String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
				// 	try {
				// 		pic3Url = kssServerApis.uploadZpDfs(fileName, pic3.getBytes(), ext);
				// 	} catch (IOException e) {
				// 		e.printStackTrace();
				// 	}
				// 	tmsqModel.setPhoto3url(pic3Url);
				// 	//tmsqModel.setPhoto3(Base64Utils.encode(pic3.getBytes()));
				// }

				Map<String, Object> map1 = Maps.newHashMap();
				Map<String, Object> params = Maps.newHashMap();
				params.put("updator", "管理员");
				params.put("snbh", "00000000");
				params.put("xm", xm);
				params.put("id", id);
				params.put("jsh", jsh);
				params.put("sydw", sydw);
				params.put("dah", dah);
				params.put("rybh", gcbh);
				params.put("jkzkString", jkzkString);
				map1.put("taskid", taskid);
				map1.put("params", params);
				map1.put("jkqkModel", jkqkModel);
				map1.put("tmtzModel", tmtzModel);
				map1.put("tmsqModel", tmsqModel);
				map1.put("sfjs", sfyjs);
				JkTmModal jkTmModal = new JkTmModal();
				jkTmModal.setMap(map);
				System.err.println("================吴宝===============");
				System.err.println("taskid:" + taskid);
				System.err.println("入所体检表表单：" + JSONUtil.toJson(map));
				System.err.println("=========================================");


				System.out.println("\33[32;4m张天啸，收押入所-健康检查已校验成功\33[30;0m");

				result = kssServerApis.firstJkjc(taskid, jkTmModal);

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


	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkqk_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkqkModel data) {
		return kssService.jkqk_save(data);
	}


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jkqk_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JkqkModel data) {
		return kssService.jkqk_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JkqkModel> jkqk_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkqk_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jkqk_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jkqk_deleteByKey(id);
	}
}
