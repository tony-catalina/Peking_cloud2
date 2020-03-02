/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.base.model.Variables;
import awd.bj.kss.model.JwzxjlModel;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JwzxjlModel;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/jwzxjl")
@Api(tags = "kss-jwzxjl",description = "Jwzxjl") 
public class Kss_JwzxjlController  extends PublicService {

	@Autowired
	private KssServerApis kssServerApis;
	
	@Autowired
    private KssService kssService;

	@Autowired
    private KssAnalyseApis kssAnalyseApis;


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_JwzxjlModel>> jwzxjl_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JwzxjlModel>> result= kssService.jwzxjl_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}


	/**
	 * @api {post} /v4/kss/jwzxjl/jwzxAdd  监外执行保存操作(流程)
	 * @apiVersion 0.4.0
	 * @apiName jwzxAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监外执行保存操作(流程).
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
	 *             "xm":"姓名(必填)",
	 *             "entity":[{
	 *                      "creator":"创建人(必填; 最大字段长度：50)",
	 *                      "sqrlx":"申请人类型(必填; 字典(SQRLX))",
	 *                      "zyjwzxly":"暂予监外执行理由(必填; 最大字段长度：300)",
	 *                      "sqyy":"申请原因(必填; 字典(ZYJYYY))",
	 *                      "gzqjbx":"改造期间表现(必填; 最大字段长度：300)",
	 *                      "tfr":"填发人(必填; 最大字段长度：255)",
	 *                      "tfrq":"填发日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "rybh":"人员编号(必填; 最大字段长度：21)"
	 *                     }]
	 *             }
	 *
	 *
	 */
	//  {"tfrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sqrlx":"\\d{1,1}","creator":".{1,50}","sqyy":"\\d{1,1}","zyjwzxly":".{1,300}","gzqjbx":".{1,300}","tfr":".{1,255}","rybh":"\\d{1,21}"}
	@ApiOperation("监外执行保存操作(流程)")
	@PostMapping("/jwzxAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jwzxAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/jwzxjl/jwzxAdd";

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

			String flowKey = CacheUtils.get().getFlowKey("_kss_jwzx".toUpperCase());

			List<JwzxjlModel> jwzxjlList = JSONArray.parseArray(map.get("entity").toString(), JwzxjlModel.class);
			JwzxjlModel jwzxjlModel=jwzxjlList.get(0);
			jwzxjlModel.setJsbh(jsbh);
			jwzxjlModel.setSqsj(new Date());
			jwzxjlModel.setCreatetime(new Date());
			jwzxjlModel.setSqrxm(map.get("xm").toString());

			ResponseMessage<String> result = kssServerApis.jwzxjlAddflow(flowKey, jwzxjlModel);

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
	 * @api {post} /v4/kss/jwzxjl/swhyjAdd  监外执行所务会意见保存(流程)
	 * @apiVersion 0.4.0
	 * @apiName swhyjAdd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监外执行所务会意见保存(流程)
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
	 *             "jbxxxm":"姓名(必填)",
	 *             "taskid":"任务ID(必填)",
	 *             "jbxxjsh":"监室号(必填; 最大字段长度：4)",
	 *             "jbxxsnbh":"所内编号(必填; 最大字段长度：8)",
	 *             "entity":[{
	 *                      "kssyj":"看守所意见(必填; 最大字段长度：300)",
	 *                      "ywlcid":"业务流程ID(必填; 最大字段长度：10)",
	 *                      "zsjcrymd":"驻所检查人员名单(必填;最大字段长度：100)",
	 *                      "shyj":"审核意见(必填; 最大字段长度：255)",
	 *                      "shr":"审核人(必填; 最大字段长度：255)",
	 *                      "shsj":"审核时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "rybh":"人员编号(必填; 最大字段长度：21)",
	 *                      "sqyy":"申请原因(必填; 字典:(ZYJYYY))"
	 *                      "tfrq":"填发时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "gzqjbx":"改造期间表现(必填; 最大字段长度：300)",
	 *                      "id":"ID(最大字段长度：23)",
	 *                      "tfr":"填发人(必填; 最大字段长度：255)",
	 *                      "zyjwzxly":"暂予监外执行理由(必填; 最大字段长度：300)",
	 *                      "sqrlx":"申请人类型(必填; 字典:(SQRLX))",
	 *                      "updator":"更新人(必填; 最大字段长度：50)",
	 *                      "updatetime":"更新时间(必填; 格式：yyyy-MM-dd hh:mm:ss)"
	 *                     }]
	 *             }
	 *
	 */
	//{"kssyj":".{1,300}","zsjcrymd":".{1,100}","shyj":".{1,255}","shr":".{1,255}","shsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","rybh":"\\d{1,21}","sqyy":"\\d{1,1}","tfrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","gzqjbx":".{1,300}","id":"\\d{1,23}","tfr":".{1,255}","zyjwzxly":".{1,300}","sqrlx":"\\d{1,1}","updator":".{1,50}","updatetime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}

	@ApiOperation("监外执行所务会意见保存(流程)")
	@PostMapping("/swhyjAdd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> swhyjAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/jwzxjl/swhyjAdd";

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

			String taskid=map.get("taskid").toString();
			if (taskid == null || "".equals(taskid)) {
				return ResponseMessage.error(Result.ERR_SAVE, "任务ID:taskid必传");
			}
			String jbxxxm=map.get("jbxxxm").toString();
			if (jbxxxm == null || "".equals(jbxxxm)) {
				return ResponseMessage.error(Result.ERR_SAVE, "jbxxxm必传");
			}
			String jbxxjsh=map.get("jbxxjsh").toString();
			if (jbxxjsh == null || "".equals(jbxxjsh)) {
				return ResponseMessage.error(Result.ERR_SAVE, "jbxxjsh必传");
			}
			String jbxxsnbh=map.get("jbxxsnbh").toString();
			if (jbxxsnbh == null || "".equals(jbxxsnbh)) {
				return ResponseMessage.error(Result.ERR_SAVE, "jbxxsnbh必传");
			}

			Map<String, Object> params = new HashMap<String, Object>();
			List<JwzxjlModel> jwzxjlList = JSONArray.parseArray(map.get("entity").toString(), JwzxjlModel.class);

			JwzxjlModel jwzxjlModel=jwzxjlList.get(0);
			String ywlcid=jwzxjlModel.getYwlcid();

			if (ywlcid == null || "".equals(ywlcid)) {
				return ResponseMessage.error(Result.ERR_SAVE, "业务流程ID:ywlcid必传");
			}
			jwzxjlModel.setUpdatetime(new Date());
			jwzxjlModel.setUpdator(map.get("jbxxxm").toString());
			jwzxjlModel.setJsbh(jsbh);

					params.put("jwzx",jwzxjlModel);
			 		params.put("xm", map.get("jbxxxm").toString());
			 		params.put("jsbh", jsbh);
			 		params.put("rybh",jwzxjlModel.getRybh());
		         	params.put("jsh", map.get("jbxxjsh").toString());
					params.put("snbh", map.get("jbxxsnbh").toString());

			 		Variables variables = new Variables();
			        variables.setJsbh(jsbh);
			        variables.setRybh(jwzxjlModel.getRybh());
			        variables.setTaskId(taskid);
			        variables.setParams(params);


					ResponseMessage<String> result = kssServerApis.swhyjAdd(variables);

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
	 * @api {post} /v4/kss/jwzxjl/addJwzxJd  监外执行鉴定保存(流程)
	 * @apiVersion 0.4.0
	 * @apiName addJwzxJd
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 监外执行鉴定保存(流程)
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
     *
     * 	"xm": "姓名",
     * 	"type": "类别",
     * 	"taskid": "任务ID(必填)",
     * 	"entity": [{
     * 		"id": "ID(必填, 最大字段长度：23)",
     * 		"jbxxCsrq": "出生日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		"jbxxHjd": "户籍地(字典:(XZQH);最大字段长度：6)",
     * 		"jbxxJsh": "监室号(必填, 最大字段长度：4)",
     * 		"jbxxRsrq": "入所日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		"jbxxSnbh": "所内编号(必填, 最大字段长度：8)",
     * 		"jbxxXm": "姓名(必填, 最大字段长度：50)",
     * 		"jdjg": "鉴定机关(必填, 最大字段长度：100)",
     * 		"jdresult": "鉴定结果(必填, 最大字段长度：255)",
     * 		"jdsj": "鉴定时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		"jsbh": "监所编号(必填, 最大字段长度：9)",
     * 		"nr": "内容(必填, 最大字段长度：300)",
     * 		"rybh": "人员编号((必填, 最大字段长度：23)",
     * 		"sqyy": "申请原因(字典:(ZYJYYY))",
     * 		"updatetime": "更新时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     * 		"updator": "更新人(必填, 最大字段长度：50)",
     * 		"ywlcid": "业务流程ID(必填, 最大字段长度：10)"
     *        }]
     * }
     *
	 */
	//{"id":"\\d{1,23}","jbxxCsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbxxHjd":"\\d{1,6}","jbxxJsh":"\\d{1,4}","jbxxRsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jbxxSnbh":"\\d{1,8}","jbxxXm":".{1,50}","jdjg":".{1,50}","jdresult":".{1,255}","jdsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jsbh":"\\d{1,9}","nr":".{1,300}","rybh":"\\d{1,21}","sqyy":"\\d{1,1}","updator":".{1,50}","updatetime":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}

	@ApiOperation("监外执行鉴定保存(流程)")
	@PostMapping("/addJwzxJd")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> addJwzxJd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/jwzxjl/addJwzxJd";

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

			String taskid=map.get("taskid").toString();
			if (taskid == null || "".equals(taskid)) {
				return ResponseMessage.error(Result.ERR_SAVE, "任务ID:taskid必传");
			}
			String type=map.get("type").toString();
			if (type == null || "".equals(type)) {
				return ResponseMessage.error(Result.ERR_SAVE, "type必传");
			}
            String xm=map.get("xm").toString();
            if (xm == null || "".equals(xm)) {
                return ResponseMessage.error(Result.ERR_SAVE, "xm必传");
            }

			List<JwzxjlModel> jwzxjlList = JSONArray.parseArray(map.get("entity").toString(), JwzxjlModel.class);
			JwzxjlModel jwzxjlModel=jwzxjlList.get(0);
			jwzxjlModel.setUpdator(xm);
			jwzxjlModel.setUpdatetime(new Date());
			jwzxjlModel.setJsbh(jsbh);


			ResponseMessage<String> result = kssServerApis.addJwzxJd(taskid, jwzxjlModel, type);

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
     * @api {get} /v4/kss/jwzxjl/jwzxYwdt 监外执行业务动态
     * @apiVersion 0.4.0
     * @apiName jwzxYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 监外执行业务动态
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}jwzx          				                     监外执行
     * @apiSuccess {String}zl                                                自理
     * @apiSuccess {String}jb                                                疾病
     * @apiSuccess {String}zrs                                               总人数
     * @apiSuccess {String}ye                                                婴儿
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
     *    "data": [
     *        [
	 *       {
	 *         "result": {
	 *           "jwzx": [
	 *             {
	 *               "zl": 23,
	 *               "jb": 29,
	 *               "zrs": 82,
	 *               "ye": 30
	 *             }
	 *           ]
	 *         }
	 *       }
	 *     ],
     *         }]
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
     *          "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *          "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     *        }
     *
     */
    @OpenAPI
    @ApiOperation("监外执行业务动态")
    @GetMapping("/jwzxYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jwzxYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        String interfaceId = "/v4/kss/jwzxjl/jwzxYwdt";
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

            String starttime = (String) maps.getResult().get("starttime");
            String endtime = (String) maps.getResult().get("endtime");

            if (!StringUtils.isNullOrEmpty(starttime)) {
                param.and("starttime", TermType.eq, starttime);
            }
            if (!StringUtils.isNullOrEmpty(endtime)) {
                param.and("endtime", TermType.eq, endtime);
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.jwzxjlYwdt(jsbh,starttime,endtime);

            System.err.println("result" + JSON.toJSONString(result));

            List lists=new ArrayList<>();
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
	 * @api {get} /v4/kss/jwzxjl/jbxxForJwzx 暂予监外执行业务台账查询
	 * @apiVersion 0.4.0
	 * @apiName jbxxForJwzx
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 暂予监外执行业务台账查询
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}xm                                                姓名
	 * @apiSuccess {String}jsbh                                              监所编号
	 * @apiSuccess {String}jsh                                               监室号
	 * @apiSuccess {String}xb                                                性别
	 * @apiSuccess {String}xbString                                          性别（已转换）
	 * @apiSuccess {String}rybh                                              人员编号
	 *
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
	 *    "data":
	 *         {
	 *        "xbString": "男性",
	 *         "xm": "0925",
	 *         "rybh": "110000114201909250001",
	 *         "xb": "1",
	 *         "jsh": "0112",
	 *         "jsbh": "110000114"
	 *         }
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
	 *           "xm":"姓名(最大字段长度：50)",
	 *           "xm_type":"姓名类别(0:根据姓名查；!=0:不根据姓名查)",
	 *           "jbxx_jsh":"监室号(最大字段长度：4)",
	 * 	         "jbxx_jsh_type":"监室号类别(0：根据监室号查；!=0:不根据监室号查)",
	 * 	         "jbxx_xb":"性别(字典：XB ；最大字段长度：1)",
	 * 	         "jbxx_xb_type":"性别类型(0:根据性别查；!=0:不根据性别查)",
	 * 	         "sqyy":"申请原因( 字典：ZYJYYY；最大字段长度：1)",
	 * 	         "sqyy_type":"申请原因类型(0:根据申请原因查；!=0:不根据申请原因查)",
	 * 	         "csrq_start":"开始出所时间(格式:yyyy-MM-dd hh:mm:ss)",
	 * 	         "csrq_end":"结束出所时间(格式:yyyy-MM-dd hh:mm:ss)",
	 * 	         "jsh":"监室号(最大字段长度：4)",
	 * 	         "jbxx_jsbh":"监所编号(最大字段长度：9)",
	 *        }
	 *
	 */
	@OpenAPI
	@ApiOperation("暂予监外执行业务台账查询")
	@GetMapping("/jbxxForJwzx")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> jbxxForJwzx(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/jwzxjl/jbxxForJwzx";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			// 领取状态(WPLQZT)
			//查询参数

			QueryParam qParam = new QueryParam();
			if(!StringUtils.isNullOrEmpty( maps.getResult().get("jsh"))){
				qParam.and("jbxx_jsh", TermType.like,  maps.getResult().get("jsh")+"%");
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				String xm = maps.getResult().get("xm").toString();
				if ("0".equals( maps.getResult().get("xm_type"))) {
					qParam.and("jbxx_xmpy", TermType.like, "%" + xm + "%");
				} else {
					qParam.and("jbxx_xm", TermType.like, "%" + xm + "%");
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_jsh"))) {
				String jbxx_jsh = maps.getResult().get("jbxx_jsh").toString();
				if ("0".equals(maps.getResult().get("jbxx_jsh_type"))) {
					qParam.and("jbxx_jsh", TermType.like, "%" + jbxx_jsh + "%");
				} else {
					qParam.and("jbxx_jsh", TermType.not, jbxx_jsh);
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("jbxx_xb"))) {
				String jbxx_xb = maps.getResult().get("jbxx_xb").toString();
				if ("0".equals(maps.getResult().get("jbxx_xb_type"))) {
					qParam.and("jbxx_xb", TermType.eq, jbxx_xb);
				} else {
					qParam.and("jbxx_xb", TermType.not, jbxx_xb);
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("sqyy"))) {
				String sqyy = maps.getResult().get("sqyy").toString();
				if ("0".equals(maps.getResult().get("sqyy_type"))) {
					qParam.and("sqyy", TermType.eq, sqyy);
				} else {
					qParam.and("sqyy", TermType.not, sqyy);
				}
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_start"))) {
				qParam.and("cssj", TermType.gt, maps.getResult().get("csrq_start"));
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("csrq_end"))) {
				qParam.and("cssj", TermType.lt, maps.getResult().get("csrq_end"));
			}

			qParam.and("jsbh", TermType.eq, jsbh);
			qParam.and("jbxx_jsbh", TermType.eq, maps.getResult().get("jbxx_jsbh"));
			qParam.and("state", TermType.eq, "R2");
			qParam.and("jbxx_state", TermType.eq, "R8");


			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jbxxForJwzx(qParam);

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



	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> jwzxjl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_JwzxjlModel data) {
		return kssService.jwzxjl_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_JwzxjlModel> jwzxjl_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jwzxjl_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> jwzxjl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.jwzxjl_deleteByKey(id);
	}
}
