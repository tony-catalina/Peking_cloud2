/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.base.model.Variables;
import awd.bj.kss.model.JbxxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.kss.*;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
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
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/clcs")
@Api(tags = "kss-clcs",description = "clcs")
public class Kss_ClcsController extends PublicService {
	
	@Autowired
    private KssService kssService;

	@Autowired
	private KssServerApis kssServerApis;

	@Autowired
	private ProcessService processService;

	@Autowired
	private WorkFlowService workFlowService;

	/**
	 * @api {get} /v4/kss/clcs/csglList 处理出所查询
	 * @apiVersion 0.4.0
	 * @apiName csglList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 处理出所查询
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}xm          				                        姓名
	 * @apiSuccess {String}rybh          				                    人员编号
	 * @apiSuccess {String}gj        				                        国籍
	 * @apiSuccess {String}gjString         				                国籍（转换后）
	 * @apiSuccess {String}xb          				                        性别
	 * @apiSuccess {String}xbString          				                性别(转换后)
	 * @apiSuccess {String}cssj          				                    出所时间
	 *
	 * @apiSuccess {String}message                                          返回信息
	 * @apiSuccess {String}result                                           返回结果
	 * @apiSuccess {String}total                                            返回总数
	 * @apiSuccess {String}data                                             返回数据
	 * @apiSuccess {String}status                                           返回状态
	 * @apiSuccess {String}timestamp                                        时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *        "xbString": "男性",
	 *        "gj": "156",
	 *         "gjString": "中国",
	 *         "xm": "司马茜",
	 *         "rybh": "110000111201907120002",
	 *         "cssj": "2019-11-30 10:25:28",
	 *         "xb": "2"
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
	 *          "snbh":"所内编号(最大字段长度：8)",
	 *          "xm":"姓名(最大字段长度：50)",
	 *          "jsh":"监室号(最大字段长度：4)",
	 *          "xb":"性别(字典：XB；最大字段长度：1)",
	 *          "badw":"办案单位(最大字段长度：60)",
	 *          "bahj":"办案环节(字典：BAJD；最大字段长度：2)",
	 *          "rsrq_start":"入所开始日期(格式：yyyy-MM-dd)",
	 *          "rsrq_end":"入所结束日期(格式：yyyy-MM-dd)",
	 *          "gyqx_start":"关押日期开始(格式：yyyy-MM-dd)",
	 *          "gyqx_end":关押日期结束(格式：yyyy-MM-dd)"
	 *        }
	 *
	 */
	@OpenAPI
	@ApiOperation("处理出所查询")
	@GetMapping("/csglList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> csglList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/clcs/csglList";
		String state ="R8";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数

			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq,jsbh);
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("snbh"))) {
				param.and("snbh", TermType.eq,maps.getResult().get("snbh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.eq,maps.getResult().get("rybh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				param.and("xm", TermType.like,maps.getResult().get("xm"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.like,maps.getResult().get("jsh"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))) {
				param.and("xb", TermType.eq,maps.getResult().get("xb"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("badw"))) {
				param.and("badw", TermType.like,maps.getResult().get("badw"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("bahj"))) {
				param.and("bahj", TermType.eq,maps.getResult().get("bahj"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start"))) {
				param.and("rsrq", TermType.gt,maps.getResult().get("rsrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end"))) {
				param.and("rsrq", TermType.lt,maps.getResult().get("rsrq_end")+" 23:59:59");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_start"))) {
				param.and("gyqx", TermType.gt,maps.getResult().get("gyqx_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_end"))) {
				param.and("gyqx", TermType.lt,maps.getResult().get("gyqx_end")+" 23:59:59");
			}

		    if (state == null) {
			           state = "R7";
			        }
			param.and("cssj", TermType.gte, new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 00:00:00");
			param.and("cssj", TermType.lte, new SimpleDateFormat("yyyy-MM-dd").format(new Date())+" 23:59:59");


			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.queryForPage(param);

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
	 * @api {post} /v4/kss/clcs/clcsAdd 今日出所保存
	 * @apiVersion 0.4.0
	 * @apiName clcsAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 今日出所保存.
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
	 *      "jsh":"监室号(必填;最大字段长度：4)",
	 *      "updator":"更新人 (必填; 最大字段长度：50)",
	 *      "jbxxid":"基本信息ID()",
	 *      "taskid":"任务ID",
	 *      "snbh":"所内编号(必填; 最大字段长度：8)",
	 *      "gyqx":"关押期限(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *      "rsrq":"入所日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *      "ywlcid":"业务流程ID",
	 *      "xm":"姓名(必填; 最大字段长度：50)",
	 *      "xb":"性别(必填;字典：XB)",
	 *      "pzr":"批准人",
	 *      "pzdw":"批准单位",
	 *      "csyy":"出所原因(必填;字典：CSYY)",
	 *      "cssj":"出所时间(必填; 格式：yyyy-MM-dd hh:mm:ss)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("今日出所保存")
	@PostMapping("clcsAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> clcsAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/clcs/clcsAdd";

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

			List<Kss_CscsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), Kss_CscsModel.class);

			clcsList.get(0).setJsbh(jsbh);

			String taskid = clcsList.get(0).getTaskid();
			if (null==taskid  || "".equals(taskid)) {
				return ResponseMessage.error("任务ID:taskid必传");
			}

			ResponseMessage<String> clcs = kssServerApis.clcsSave(clcsList);

			if(clcs.getStatus() == 200){
				clcs.setMessage("保存成功!");
			}else{
				clcs.setMessage("服务异常,保存失败!");
			}
			return clcs;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {post} /v4/kss/clcs/csglJypz 出所管理检验凭证保存
	 * @apiVersion 0.4.0
	 * @apiName csglJypz
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 出所管理检验凭证保存.
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
	 *      "blr":"办案人(必填;最大字段长度：50)",
	 *      "xb":"性别(必填；字典：XB)",
	 *      "jsh":"监室号(必填;最大字段长度：4)",
	 *      "blrzjh":"办理人证件号 (必填; 最大字段长度：30)",
	 *      "pzws":"凭证文书号(必填; 最大字段长度：30)",
	 *      "blsj":"办理时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *      "blrlxdh":"办理人联系电话(必填; 最大字段长度：11)"
	 *   }]
	 * }
	 *
	 *
	 *
	 */
	//{"blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blr":".{1,50}","rybh":"\\d{1,21}","blrzjh":".{1,30}","pzws":".{1,30}","blrlxdh":"\\d{1,11}","xb":"\\d{1,1}","jsh":"\\d{1,4}"}
	@ApiOperation("出所管理检验凭证保存")
	@PostMapping("/csglJypz")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> csglJypz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/clcs/csglJypz";

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
				return ResponseMessage.error(msg.getMessage());
			}

			List<Kss_CscsModel> clcsList = JSONArray.parseArray(map.get("entity").toString(), Kss_CscsModel.class);
			List<JbxxModelDO> jbxx1 = JSONArray.parseArray(map.get("entity").toString(), JbxxModelDO.class);
			List<ClcsModelDO> data1 = JSONArray.parseArray(map.get("entity").toString(), ClcsModelDO.class);


			clcsList.get(0).setJsbh(jsbh);

			String jbxx = jbxx1.toString();
			String data = data1.toString();

			Map<String, Object> params = JSONUtil.toMap(jbxx);
			JbxxModelDO jbxxModel = JSONUtil.toBean(jbxx, JbxxModelDO.class);
			ClcsModelDO clcsModel = JSONUtil.toBean(data, ClcsModelDO.class);
			params.put("jypzblr", clcsModel.getBlr());
			params.put("jypzblrzjh", clcsModel.getBlrzjh());
			params.put("jypzblrlxdh", clcsModel.getBlrlxdh());
			params.put("jypzpzws", clcsModel.getPzws());
			System.err.println("检验凭证model" + JSONUtil.toJson(clcsModel));

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String blsj = simpleDateFormat.format(clcsModel.getBlsj());
			params.put("jypzblsj", blsj);

			clcsModel.setState("R2");
			clcsModel.setCreatetime(new Date());

			Variables variables = new Variables();
			variables.setJsbh(clcsModel.getJsbh());
			variables.setRybh(clcsModel.getRybh());
			variables.setParams(params);

				String csid = CacheUtils.get().getFlowKey("kss_csgl".toUpperCase());
				System.err.println("王承孝-----" + csid);
				if (StringUtils.isNullOrEmpty(csid)) {
					return ResponseMessage.error("获取流程实例失败，请联系管理员！");
				}
				Variables variables1 = new Variables();
				String str = clcsModel.getRybh();
				variables1.setRybh(str);
				variables1.setJsbh(jsbh);
				Result r = workFlowService.findPersonalTaskList("admin", "1", variables1);
				System.err.println("张江南-----------" + JSONUtil.toJson(r));
				if (r != null) {
					List<Map<String, Object>> obj = (List<Map<String, Object>>) r.getResult();
					for (Map<String, Object> maps1 : obj) {
						String processDefinitionId = maps1.get("processDefinitionId").toString();
						if (StringUtils.isNullOrEmpty(processDefinitionId)) {
							continue;
						}
						if (csid.equals(processDefinitionId)) {
							return ResponseMessage.error("已存在于该流程中，无法重新办理！！");
						}
					}
				}

				Kss_ClcsInfoModel clcsInfo = new Kss_ClcsInfoModel();
				clcsInfo.setTaskid(csid);
				clcsInfo.setJsbh(clcsModel.getJsbh());
				clcsInfo.setJbxxEntity(jbxxModel);
				clcsInfo.setClcsEntity(clcsModel);
				clcsInfo.setVariables(variables);

				ResponseMessage<String> clcs = kssServerApis.saveStartflow(clcsInfo);

				if (clcs.getStatus() == 200) {
					clcs.setMessage("保存成功!");
				} else {
					clcs.setMessage("服务异常,保存失败!");
				}
				return clcs;

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("保存失败！");
			}

	}



	/**
	 * @api {post} /v4/kss/clcs/deleteFlowSfz 人员打印释放证明书后移出该流程
	 * @apiVersion 0.4.0
	 * @apiName deleteFlowSfz
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 人员打印释放证明书后移出该流程.
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
	 *   "taskid":"任务ID",
	 *   "entity":[{
	 *     "rybh":"人员编号(必填; 最大字段长度：21)",
	 *     "jsh":"监室号(必填;最大字段长度：4)",
	 *     "xm":"姓名 (必填; 最大字段长度：50)",
	 *     "snbh":"所内编号(必填; 最大字段长度：8)"
	 *   }]
	 * }
	 *
	 */
	@ApiOperation("人员打印释放证明书后移出该流程")
	@PostMapping("/deleteFlowSfz")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> deleteFlowSfz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/clcs/deleteFlowSfz";

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
			List<JbxxModel> jbxxList = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class);
			Variables variables = new Variables();
			Map<String, Object> m = new HashMap<>();

				String taskid = map.get("taskid").toString();
				if (taskid == null || "".equals(taskid)) {
					return ResponseMessage.error(Result.ERR_SAVE, "任务ID:taskid必传");
				}


				variables.setRybh(jbxxList.get(0).getRybh());
				variables.setJsbh(jsbh);
				m.put("rybh", jbxxList.get(0).getRybh());
				m.put("snbh", jbxxList.get(0).getSnbh());
				m.put("jsh", jbxxList.get(0).getJsh());
				m.put("xm", jbxxList.get(0).getXm());
				variables.setParams(m);

				ResponseMessage<String> result = kssServerApis.deleteFlowSfz(taskid, variables);

				if (result.getStatus() == 200) {
					result.setMessage("删除成功!");
				} else {
					result.setMessage("服务异常,删除失败!");
				}
				return result;

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseMessage.error("删除失败！");
			}
	}


	/**
	 * @api {post} /v4/kss/clcs/csUpdatejbxx 出所保存
	 * @apiVersion 0.4.0
	 * @apiName csUpdatejbxx
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 出所保存.
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
	 *      "ywlcid":" 业务流程ID(必填;最大字段长度：15,多选用“,”隔开)",
	 *      "rybhs":"人员编号(必填;最大字段长度：21,多选用“,”隔开)",
	 *      "jshs":"监室号 (必填; 最大字段长度：4,多选用“,”隔开)",
	 *       "cssj":"出所时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *       "blsj":"办理时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *       "dlmj":"操作人(必填; 最大字段长度：50)",
	 *   }]
	 * }
	 *
	 */

	@ApiOperation("出所保存")
	@PostMapping("csUpdatejbxx")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> csUpdatejbxx(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//{"cssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","dlmj":"\\S{1,50}"}
		//{"entity":[{"rybhs":"310000111201906200007,310000111201906200001","taskid":"5109258","ywlcid":"5209485,5206511","jshs":"0101","cssj":"2019-10-10 10:10:10","blsj":"2019-10-10 10:10:10","dlmj":"管理员"}]}
		String interfaceId = "/v4/kss/clcs/csUpdatejbxx";

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
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String taskid=(String) rslist.get(0).get("taskid");
			String ywlcid=(String) rslist.get(0).get("ywlcid");
			String ywlcids[] = ywlcid.split(",");
			String jshs[] = ((String) rslist.get(0).get("jshs")).split(",");
			String rybhs = (String) rslist.get(0).get("rybhs");
			String dlmj = (String) rslist.get(0).get("dlmj");
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(ywlcid)){
				return ResponseMessage.error("ywlcid不能为空！");
			}
			if (StringUtils.isNullOrEmpty(jshs)){
				return ResponseMessage.error("jshs不能为空！");
			}
			if(!StringUtils.isNullOrEmpty(dlmj)) {
				return ResponseMessage.error("dlmj不能为空！");
			}
			List<Kss_CrjjcModelDO> crjjcList = new ArrayList<>();
			String[] rybh = null;
			if(!StringUtils.isNullOrEmpty(rybhs)) {
				rybh = rybhs.split(",");
			}
			if(!StringUtils.isNullOrEmpty(rybh)) {
				for(int i=0;i<rybh.length;i++) {
					Kss_CrjjcModelDO crjjc = new Kss_CrjjcModelDO();
					crjjc.setRybh(rybh[i]);
					crjjc.setJbxxJsh(jshs[i]);
					crjjc.setJsbh(jsbh);
					crjjc.setCreatetime(new Date());
					crjjc.setBllx("1");
					crjjc.setBlsj(new Date());
					crjjc.setDlmj(dlmj);
					crjjc.setState("R2");
					crjjc.setYwlcid(ywlcids[i]);
					crjjc.setCreator(dlmj);
					crjjcList.add(crjjc);
				}
			}else {
				return ResponseMessage.error("缺失必要参数");
			}
			ResponseMessage<String> result = kssServerApis.csAndJbxx(taskid,crjjcList);

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
	 * @api {get} /v4/kss/clcs/findFlowBylcid 根据id查询流程
	 * @apiVersion 0.4.0
	 * @apiName findFlowBylcid
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 根据id查询流程
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}message                                           返回信息
	 * @apiSuccess {String}result                                            返回结果
	 * @apiSuccess {String}status                                            返回状态
	 * @apiSuccess {String}timestamp                                         时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "成功!",
	 *   "result": null,
	 *   "status": 200,
	 *   "timestamp": 1578396600643
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码（必填）",
	 * jsbh:"监所编号(必填; 最大字段长度：9)",
	 * json:{
	 *   "rybh":"人员编号(必填; 最大字段长度：21)",
	 * }
	 */
	@OpenAPI
	@ApiOperation("根据id查询流程")
	@GetMapping("/findFlowBylcid")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<String> findFlowBylcid(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){
		//接口id
		String interfaceId = "/v4/kss/clcs/findFlowBylcid";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}

			if(StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				ResponseMessage.error("rubh不可为空");
			}
			String rybh = maps.getResult().get("rybh").toString();

			String flowkey = CacheUtils.get().getFlowKey( "kss_csgl".toUpperCase());

			ResponseMessage<String> result=processService.FlowSingle(flowkey, rybh, jsbh);
			System.err.println("result----"+result);

			if(result.getStatus() == 200){
				result.setMessage("成功!");
			}else{
				result.setMessage("失败!");
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("查询失败！");
		}
	}

	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> clcs_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_ClcsModel data) {
		return kssService.clcs_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_ClcsModel> clcs_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.clcs_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> clcs_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.clcs_deleteByKey(id);
	}
}
