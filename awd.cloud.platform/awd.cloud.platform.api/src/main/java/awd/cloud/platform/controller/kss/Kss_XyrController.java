/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XyrModel;
import awd.cloud.platform.api.KssServerApis;
import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_RsajModel;
import awd.cloud.platform.model.kss.Kss_XyrModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.Pinyin4j;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xyr")
@Api(tags = "kss-xyr",description = "Xyr")
public class Kss_XyrController  extends PublicService {

	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {post} /v4/kss/xyr/dabh 档案编号查询
	 * @apiVersion 0.4.0
	 * @apiName dabh
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 档案编号查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}id         				                        档案id

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
	 *          {
	 *         "dah": "2001020011"
	 *       	}
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
	 *
	 *        }

	 */
	//dah
	@OpenAPI
	@ApiOperation("档案编号查询")
	@PostMapping("/dabh")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> dabh(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xyr/dabh";
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

			ResponseMessage<String> result= kssServerApis.dabh();
			System.err.println("--result--" + JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			List<Map<String,Object>> dabhList=new ArrayList<Map<String,Object>>();
			Map<String, Object> dahmap = new HashMap<String, Object>();
			dahmap.put("dah",result.getResult());
			dabhList.add(dahmap);
			maplist.put("entity", dabhList);
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",1);
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
	 * @api {post} /v4/kss/xyr/getXyrTaskList 获取嫌疑人查询
	 * @apiVersion 0.4.0
	 * @apiName getXyrTaskList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 获取嫌疑人查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}id         				                        档案id
	 * @apiSuccess {String}gcbh												过程编号
	 * @apiSuccess {String}wbrybh											网办人员编号
	 * @apiSuccess {String}dah												档案编号
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsbhString										监所编号(已转换)
	 * @apiSuccess {String}xm												姓名
	 * @apiSuccess {String}xmpy												姓名拼音
	 * @apiSuccess {String}bm												别名
	 * @apiSuccess {String}bmty												别名同音
	 * @apiSuccess {String}mz												民族
	 * @apiSuccess {String}mzString											民族(已转换)
	 * @apiSuccess {String}gj												国家
	 * @apiSuccess {String}gjString											国家(已转换)
	 * @apiSuccess {String}xb												性别
	 * @apiSuccess {String}xbString											性别(已转换)
	 * @apiSuccess {String}csrq												出生日期
	 * @apiSuccess {String}zjlx												证件类型
	 * @apiSuccess {String}zjlxString										证件类型(已转换)
	 * @apiSuccess {String}zjh												证件号
	 * @apiSuccess {String}zzmm												政治面貌
	 * @apiSuccess {String}zzmmString										政治面貌(已转换)
	 * @apiSuccess {String}hyzk												婚姻状况
	 * @apiSuccess {String}hyzkString										婚姻状况(已转换)
	 * @apiSuccess {String}zuc												足长
	 * @apiSuccess {String}sg												身高
	 * @apiSuccess {String}jg												籍贯
	 * @apiSuccess {String}jgString											籍贯(已转换)
	 * @apiSuccess {String}hjd												户籍地
	 * @apiSuccess {String}hjdString										户籍地(已转换)
	 * @apiSuccess {String}hjdxz											户籍地详址
	 * @apiSuccess {String}xzd												现住地
	 * @apiSuccess {String}xzdString										现住地(已转换)
	 * @apiSuccess {String}xzdxz											现住地详址
	 * @apiSuccess {String}whcd												文化程度
	 * @apiSuccess {String}whcdString										文化程度(已转换)
	 * @apiSuccess {String}zc												专长
	 * @apiSuccess {String}zcString											专长(已转换)
	 * @apiSuccess {String}sf												身份
	 * @apiSuccess {String}sfString											身份(已转换)
	 * @apiSuccess {String}tssf												特殊身份
	 * @apiSuccess {String}tssfString										特殊身份(已转换)
	 * @apiSuccess {String}zy												职业
	 * @apiSuccess {String}zyString											职业(已转换)
	 * @apiSuccess {String}gzdw												工作单位
	 * @apiSuccess {String}jkzk												健康情况
	 * @apiSuccess {String}jkzkString										健康情况(已转换)
	 * @apiSuccess {String}bhlx												病号类型
	 * @apiSuccess {String}bhlxString										病号类型(已转换)
	 * @apiSuccess {String}azb												艾滋病
	 * @apiSuccess {String}azbString										艾滋病(已转换)
	 * @apiSuccess {String}rsrq												入所日期
	 * @apiSuccess {String}rsxz												入所原因
	 * @apiSuccess {String}rsxzString										入所原因(已转换)
	 * @apiSuccess {String}zrdw												转入单位
	 * @apiSuccess {String}sydw												送押单位
	 * @apiSuccess {String}syr												送押人
	 * @apiSuccess {String}sy												收押人
	 * @apiSuccess {String}byzd												收押非拒捕人员
	 * @apiSuccess {String}sypzwsh											收押凭证文书号
	 * @apiSuccess {String}sypz												收押凭证
	 * @apiSuccess {String}sypzString										收押凭证(已转换)
	 * @apiSuccess {String}jyrq												羁押日期
	 * @apiSuccess {String}gyqx												关押期限
	 * @apiSuccess {String}ay												主要案由
	 * @apiSuccess {String}ayString											主要案由(已转换)
	 * @apiSuccess {String}xhay												细化案由
	 * @apiSuccess {String}xhayString										细化案由(已转换)
	 * @apiSuccess {String}fzjl												犯罪经历
	 * @apiSuccess {String}fyaq												简要案情
	 * @apiSuccess {String}zaaj												从案类型
	 * @apiSuccess {String}zaajString										从案类型(已转换)
	 * @apiSuccess {String}cylx												成员类型
	 * @apiSuccess {String}cylxString										成员类型(已转换)
	 * @apiSuccess {String}bar												办案人
	 * @apiSuccess {String}barjh											办案人警号
	 * @apiSuccess {String}bahj												办案环节
	 * @apiSuccess {String}bahjString										办案环节(已转换)
	 * @apiSuccess {String}dwlx												办案单位类型
	 * @apiSuccess {String}dwlxString										办案单位类型(已转换)
	 * @apiSuccess {String}syrkid											实有人口ID
	 * @apiSuccess {String}czzt												操作状态
	 * @apiSuccess {String}jsly												拒收理由
	 * @apiSuccess {String}aqdj												安全等级
	 * @apiSuccess {String}aqdjString										安全等级(已转换)
	 * @apiSuccess {String}sfyxjslx											是否允许家属联系
	 * @apiSuccess {String}sfyxjslxString									是否允许家属联系(已转换)
	 * @apiSuccess {String}state											状态
	 * @apiSuccess {String}stateString										状态(已转换)
	 * @apiSuccess {String}creator											创建者
	 * @apiSuccess {String}creatorjh										创建人警号
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}updator											更新人
	 * @apiSuccess {String}updatetime										更新时间
	 * @apiSuccess {String}zwbh												指纹编号
	 * @apiSuccess {String}bz												备注
	 * @apiSuccess {String}ywlcid											业务流程ID
	 * @apiSuccess {String}shyy												收回原因
	 * @apiSuccess {String}shrq												收回日期
	 * @apiSuccess {String}flwsh											法律文书号
	 * @apiSuccess {String}jsh												监室号
	 * @apiSuccess {String}jcys												检查医生
	 * @apiSuccess {String}xzhjaj											限制会见案件
	 * @apiSuccess {String}lb												类别
	 * @apiSuccess {String}spdw												审批单位
	 * @apiSuccess {String}spr												审批人
	 * @apiSuccess {String}gllb												管理类别
	 * @apiSuccess {String}zxf												重刑犯
	 * @apiSuccess {String}tabh												同案编号
	 * @apiSuccess {String}zw												职务
	 * @apiSuccess {String}fwbh												附物编号
	 * @apiSuccess {String}sdnjccjg											涉毒尿检初查结果
	 * @apiSuccess {String}sdnjdw											涉毒尿检单位
	 * @apiSuccess {String}sdnjcjsj											涉毒尿检初检时间
	 * @apiSuccess {String}sdnjjcr											涉毒尿检检查人
	 * @apiSuccess {String}lxdh												联系电话
	 * @apiSuccess {String}czr												暂不收押操作人
	 * @apiSuccess {String}czsj												暂不收押操作时间
	 * @apiSuccess {String}taskid											任务id
	 * @apiSuccess {String}ldpsxx											领导批示信息
	 * @apiSuccess {String}jkqkxx											健康检查信息

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
	 *          {
	 *         "dah": "2001020011"
	 *       	}
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
	 *   		"shyy": "null",
	 *         "xzhjaj": "null",
	 *         "jkzk": "null",
	 *         "barjh": "null",
	 *         "rsxzString": "本省市转入",
	 *         "zcString": "null",
	 *         "stateString": " ",
	 *         "bhlxString": "null",
	 *         "zaajString": "null",
	 *         "xhay": "null",
	 *         "gcbh": "6908215494488",
	 *         "bar": "null",
	 *         "id": "11000011420200102000890",
	 *         "state": "R2",
	 *         "taskid": "5191177",
	 *         "azbString": "null",
	 *         "czsj": "null",
	 *         "jcys": "null",
	 *         "lxdh": "null",
	 *         "tssf": "null",
	 *         "jkzkString": "null",
	 *         "sdnjccjg": "null",
	 *         "fzjl": "null",
	 *         "zc": "null",
	 *         "gllb": "null",
	 *         "hyzk": "null",
	 *         "rsxz": "15",
	 *         "ayString": "null",
	 *         "ay": "null",
	 *         "zjh": "null",
	 *         "jg": "null",
	 *         "zw": "null",
	 *         "sdnjjcr": "null",
	 *         "bmty": "null",
	 *         "jsh": "0104",
	 *         "zy": "null",
	 *         "hyzkString": "null",
	 *         "tabh": "null",
	 *         "azb": "null",
	 *         "bm": "null",
	 *         "sydw": "null",
	 *         "sf": "null",
	 *         "sg": "null",
	 *         "sypzwsh": "null",
	 *         "flwsh": "null",
	 *         "bz": "null",
	 *         "sfString": "null",
	 *         "sfyxjslxStrin": "null",
	 *         "jsbhString": "北京市第一看守所",
	 *         "hjdString": "null",
	 *         "syrkid": "null",
	 *         "shrq": "null",
	 *         "creator": "管理员",
	 *         "createtime": "2020-01-02 10:49:10",
	 *         "sy": "null",
	 *         "zyString": "null",
	 *         "sfyxjslx": "null",
	 *         "dwlx": "null",
	 *         "xbString": "男性",
	 *         "fyaq": "null",
	 *         "rsrq": "2020-01-02 10:49:00",
	 *         "lb": "null",
	 *         "zwbh": "null",
	 *         "bhlx": "null",
	 *         "updatetime": "null",
	 *         "jsbh": "110000114",
	 *         "csrq": "null",
	 *         "sdnjcjsj": "null",
	 *         "zjlx": "null",
	 *         "spdw": "null",
	 *         "xmpy": "ERTERENTA",
	 *         "zuc": "null",
	 *         "dah": "23234234",
	 *         "whcdString": "null",
	 *         "zjlxString": "null",
	 *         "aqdj": "null",
	 *         "jkqkxx": {
	 *           "xxbl": "true",
	 *           "snbh": "00000000",
	 *           "xm": "而特任他",
	 *           "rsrq": "2020-01-02 10:49:00",
	 *           "xmpy": "ERTERENTA",
	 *           "rybh": "6908215494488",
	 *           "ywlcid": "",
	 *           "dah": "23234234",
	 *           "xb": "1",
	 *           "jsbh": "110000114",
	 *           "jsh": "0104"
	 *
	 *        }

	 */
	//{"xm":"","jsh":"","bm":"","xb":"","rsrq_start":"","rsrq_end":"","bahj":"","gyqx_start":"","gyqx_end":"","taskDefinitionKey":"kss_rsdj_xxbl","processDefinitionKey":"kss_rsdj","pageSize":"10","pageIndex":"0","page":"1","rows":"10","sort":"id","order":"desc"}
	//id,gcbh,wbrybh,dah,jsbh,jsbhString,xm,xmpy,bm,bmty,mz,mzString,gj,gjString,xb,xbString,csrq,zjlx,zjlxString,zjh,zzmm,zzmmString,hyzk,hyzkString,zuc,sg,jg,jgString,hjd,hjdString,hjdxz,xzd,xzdString,xzdxz,whcd,whcdString,zc,zcString,sf,sfString,tssf,tssfString,zy,zyString,gzdw,jkzk,jkzkString,bhlx,bhlxString,azb,azbString,rsrq,rsxz,rsxzString,zrdw,sydw,syr,sy,byzd,sypzwsh,sypz,sypzString,jyrq,gyqx,ay,ayString,xhay,xhayString,fzjl,fyaq,zaaj,zaajString,cylx,cylxString,bar,barjh,bahj,bahjString,dwlx,dwlxString,syrkid,czzt,jsly,aqdj,aqdjString,sfyxjslx,sfyxjslxStrin,state,stateString,creator,creatorjh,createtime,updator,updatetime,zwbh,bz,ywlcid,shyy,shrq,flwsh,jsh,jcys,xzhjaj,lb,spdw,spr,gllb,zxf,tabh,zw,fwbh,sdnjccjg,sdnjdw,sdnjcjsj,sdnjjcr,lxdh,czr,czsj,taskid,ldpsxx,jkqkxx
	@OpenAPI
	@ApiOperation("获取嫌疑人查询")
	@PostMapping("/getXyrTaskList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> getXyrTaskList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xyr/getXyrTaskList";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}



			System.err.println("--maps--" + JSON.toJSONString(maps));
			// 领取状态(WPLQZT)
			//查询参数
			Variables variables=new Variables();
			Map<String,Object> params = new HashMap<String,Object>();
			Map<String, Object> notEqualsMap = Maps.newHashMap();
			Map<String, Object> greaterThanOrEqualMap = Maps.newHashMap();
			Map<String, Object> lessThanOrEqualMap = Maps.newHashMap();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				variables.setJsbh(jsbh);
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("taskDefinitionKey")))){
				variables.setTaskDefinitionKey((String)maps.getResult().get("taskDefinitionKey"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("processDefinitionKey")))){
				variables.setProcessDefinitionKey((String)maps.getResult().get("processDefinitionKey"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("pageSize")))){
				variables.setLimit((String)maps.getResult().get("pageSize"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("pageIndex")))){
				variables.setStart((String)maps.getResult().get("pageIndex"));
			}



			params.put("jsbh",jsbh);
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("xm")))){
				params.put("xm",(String)maps.getResult().get("xm"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("jsh")))){
				params.put("jsh",(String)maps.getResult().get("jsh"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("bm")))){
				params.put("bm",(String)maps.getResult().get("bm"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("xb")))){
				params.put("xb",(String)maps.getResult().get("xb"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("bahj")))){
				params.put("bahj",(String)maps.getResult().get("bahj"));
			}

			if((!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start")))){
				greaterThanOrEqualMap.put("rsrq", (String)maps.getResult().get("rsrq_start"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_start")))){
				greaterThanOrEqualMap.put("gyqx", (String)maps.getResult().get("gyqx_start"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end")))){
				lessThanOrEqualMap.put("rsrq", (String)maps.getResult().get("rsrq_end")+" 23:59:59");
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_end")))){
				lessThanOrEqualMap.put("gyqx", (String)maps.getResult().get("gyqx_end")+" 23:59:59");
			}

			variables.setParams(params);    //流程匹配参数
			variables.setNotEqualsMap(notEqualsMap);    //流程不等于匹配参数
			variables.setGreaterThanOrEqualMap(greaterThanOrEqualMap);    //流程大于匹配参数
			variables.setLessThanOrEqualMap(lessThanOrEqualMap);    //流程小于匹配参数
			System.err.println("--variables--" + JSON.toJSONString(variables));

			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.getTaskList(variables);
			System.err.println("--result--" + JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity",result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",result.getResult().getTotal());
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
	 * @api {post} /v4/kss/xyr/saveAsLegal 法律文书齐全保存
	 * @apiVersion 0.4.0
	 * @apiName saveAsLegal
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 法律文书齐全保存.
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
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       "entity":[
	 *                {
	 *                  "id":"id(最大长度:23)",
	 *                  "rybh":"网办人员编号(最大长度:30)",
	 *                  "gcbh":"过程编号(最大长度:30)",
	 *                  "xxbl_taskid":"taskid(最大长度:30)",
	 *                  "zjh":"证件号(必填；最大长度:18)",
	 *                  "xm":"姓名(必填；最大长度:30)",
	 *                  "xb":"性别(必填；最大长度:1)",
	 *                  "xzhjaj":"限制会见案件(必填；最大长度:20)",
	 *                  "jsh":"监室号(必填；最大长度:4)",
	 *                  "spdw":"审批单位(最大长度:500)",
	 *                  "spr":"审批人(最大长度:30)",
	 *                  "zrdw":"转入单位(必填；最大长度:30)",
	 *                  "bar":"办案人(必填；最大长度:30)",
	 *                  "lxdh":"联系电话(最大长度:20)",
	 *                  "bm":"别名(最大长度:30)",
	 *                  "gj":"国籍(必填；最大长度:3)",
	 *                  "whcd":"文化程度(必填；最大长度:2)",
	 *                  "sf":"身份(必填；最大长度:2)",
	 *                  "mz":"民族(必填；最大长度:2)",
	 *                  "dah":"档案编号(必填；最大长度:20)",
	 *                  "zjlx":"证件类型(必填；最大长度:30)",
	 *                  "zy":"职业(必填；最大长度:4)",
	 *                  "csrq":"出生日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "hyzk":"婚姻状况(必填；最大长度:1)",
	 *                  "hjd":"户籍地(必填；最大长度:6)",
	 *                  "jg":"籍贯(必填；最大长度:6)",
	 *                  "hjdxz":"户籍地详址(必填；最大长度:120)",
	 *                  "xzd":"现住地(必填；最大长度:6)",
	 *                  "zzmm":"政治面貌(最大长度:2)",
	 *                  "xzdxz":"现住地详址(必填；最大长度:120)",
	 *                  "tssf":"特殊身份(最大长度:10)",
	 *                  "gllb":"管理类别(最大长度:255)",
	 *                  "zxf":"重刑犯(最大长度:50)",
	 *                  "tabh":"同案编号(最大长度:30)",
	 *                  "gzdw":"工作单位(最大长度:40)",
	 *                  "zw":"职务(最大长度:50)",
	 *                  "zwbh":"指纹编号(最大长度:30)",
	 *                  "zc":"专长(最大长度:2)",
	 *                  "sypz":"收押凭证(必填；最大长度:1)",
	 *                  "sypzwsh":"收押凭证文书号(必填；最大长度:40)",
	 *                  "rsxz":"入所原因(必填；最大长度:2)",
	 *                  "jyrq":"羁押日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "jlrq":"扣留日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "dbrq":"逮捕日期(格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "sydw":"送押单位(必填；最大长度:60)",
	 *                  "syr":"送押人(必填；最大长度:30)",
	 *                  "rsrq":"入所日期(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "badw":"办案单位(必填；最大长度:60)",
	 *                  "sy":"收押人(最大长度:40)",
	 *                  "bardh":"办案民警电话(最大长度:20)",
	 *                  "ay":"主要案由(必填；最大长度:34)",
	 *                  "gyqx":"关押期限(必填；格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "fwbh":"附物编号(最大长度:50)",
	 *                  "sdnjdw":"涉毒尿检单位(最大长度:30)",
	 *                  "sdnjcjsj":"涉毒尿检初检时间(格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "sdnjjcr":"涉毒尿检检查人(最大长度:30)",
	 *                  "jyaq":"简要案情(必填；最大长度:255)",
	 *                  "bz":"备注()"
	 *                }
	 *              ]
	 *     }
	 */

	//{"zjh":"\\d{1,18}","xm":"\\S{1,30}","xb":"\\d{1}","xzhjaj":"\\S{1,20}","jsh":"\\d{1,4}","zrdw":"\\S{1,30}","bar":"\\S{1,30}","bm":"\\S{1,30}","gj":"\\d{1,3}","whcd":"\\d{1,2}","sf":"\\d{1,2}","mz":"\\d{1,2}","dah":"\\d{1,20}","zjlx":"\\d{1,30}","zy":"\\d{1,4}","csrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","hyzk":"\\d{1}","hjd":"\\d{1,6}","jg":"\\d{1,6}","hjdxz":"\\S{1,120}","xzd":"\\d{1,6}","xzdxz":"\\S{1,120}","zxf":"\\S{1,50}","sypz":"\\d{1}","sypzwsh":"\\d{1,40}","rsxz":"\\d{1,2}","jyrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jlrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","dbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sydw":"\\S{1,60}","syr":"\\S{1,30}","rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","badw":"\\S{1,60}","ay":"\\S{1,34}","gyqx":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	@ApiOperation("法律文书齐全保存")
	@PostMapping("/saveAsLegal")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveAsLegal(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/xyr/saveAsLegal";
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
			System.err.println(map.get("entity").toString());
			List<XyrModel> xyrModels = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);
			System.err.println("xyrModels--"+ JSON.toJSONString(xyrModels.get(0)));
			String jyaq=xyrModels.get(0).getJyaq();
			if (StringUtils.isNullOrEmpty(jyaq)){
				return ResponseMessage.error("简要案情不能为空！");
			}
			xyrModels.get(0).setCreatetime(new Date());
			xyrModels.get(0).setState("R2");
			xyrModels.get(0).setJsbh(jsbh);
			xyrModels.get(0).setCzzt("03");
			xyrModels.get(0).setCreator("管理员");
			XyrModel xyrEntity = xyrModels.get(0);
			System.err.println("xyrmodel--"+JSON.toJSONString(xyrEntity));
			String flowKey = CacheUtils.get().getFlowKey("KSS_RSDJ");
			if ("".equals(flowKey)) {
				return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
			}
			ResponseMessage<String> result = kssServerApis.saveAsLegal(flowKey,xyrEntity);
			System.err.println("xyrModel--"+JSON.toJSONString(result));
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
	 * @api {post} /v4/kss/xyr/xyrAqjcSave 嫌疑人安全检查保存
	 * @apiVersion 0.4.0
	 * @apiName xyrAqjcSave
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 嫌疑人安全检查保存.
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
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       "entity":[
	 *                {
	 *                  "jcry":"检查人员(必填;最大长度:30)",
	 *					"jcrq":"检查日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "zbmj":"值班民警(必填;最大长度:50)",
	 *                  "djsj":"登记时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                }
	 *              ]
	 *     }
	 */

	//{"jcry":"\\S{1,30}","jcrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","zbmj":"\\S{1,50}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
	//{"entity":[{"jcry":"\\S{1,30}","jcrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","zbmj":"\\S{1,50}","djsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}]}
	@ApiOperation("嫌疑人安全检查保存")
	@PostMapping("/xyrAqjcSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xyrAqjcSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/xyr/xyrAqjcSave";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String taskid=(String) rslist.get(0).get("taskid");
			System.err.println("taskid--"+taskid);
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			Map<String, Object> rsmap = JSONObject.parseObject(json, HashMap.class);
			System.err.println("map--"+map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<Kss_JbxxModelDO> jbxxmodel = JSONArray.parseArray(map.get("entity").toString(), Kss_JbxxModelDO.class);
			System.err.println("jbxxmodel--"+ JSON.toJSONString(jbxxmodel.get(0)));
			jbxxmodel.get(0).setCreatetime(new Date());
			jbxxmodel.get(0).setState("R2");
			jbxxmodel.get(0).setJsbh(jsbh);
			jbxxmodel.get(0).setSykzrq(Calendar.getInstance().getTime());
			jbxxmodel.get(0).setCreator("管理者");
			List<Kss_RsajModel> rsajmodel = JSONArray.parseArray(rsmap.get("entity").toString(), Kss_RsajModel.class);
			rsajmodel.get(0).setId(null);
			Kss_JbxxModelDO jbxxModel = jbxxmodel.get(0);
			Kss_RsajModel rsajModel=rsajmodel.get(0);
			System.err.println("rsajModel--"+JSON.toJSONString(rsajModel));
			System.err.println("jbxxModel--"+JSON.toJSONString(jbxxModel));
			Map<String,Object> rsajMap=Maps.newHashMap();
			rsajMap.put("rsajModel",rsajModel);
			rsajMap.put("jbxxModel",jbxxModel);
			ResponseMessage<String> result = kssServerApis.rsAqjc(taskid,rsajMap);
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
	 * @api {post} /v4/kss/xyr/saveZbsy 暂不收押保存
	 * @apiVersion 0.4.0
	 * @apiName saveZbsy
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 暂不收押保存.
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
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *       "entity":[
	 *                {
	 *                	"xm":"姓名(必填;最大长度:30)",
	 *                  "xb":"性别(必填;最大长度:1)",
	 *                  "id":"嫌疑人id(必填;最大长度:23)",
	 *                  "taskid":"taskid(必填;最大长度:23)",
	 *                  "rsrq":"入所时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "ysyj":"医生意见(必填;最大长度:100)",
	 *                  "jcys":"医生(必填;最大长度:30)",
	 *                  "ysjcrq":"签名日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *                  "jkzk":"检查结果(必填;最大长度:30)",
	 *                  "sldqm":"所领导签名(必填;最大长度:50)",
	 *                  "sldqmrq":"签名日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
	 *					"jsly":"不宜入所原因(必填;最大长度:200)",
	 *                  "jsbh":"监所编号(必填;最大长度:9)",
	 *                  "gcbh":"过程编号(必填;最大长度:30)",
	 *                }
	 *              ]
	 *     }
	 */

	//{"xm":"\\S{1,30}","xb":"\\d{1}","id":"\\d{1,23}","rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","ysyj":"\\S{1,100}","jcys":"\\S{1,30}","ysjcrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jkzk":"\\S{1,30}","sldqm":"\\S{1,50}","sldqmrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jsly":"\\S{1,200}","jsbh":"\\d{1,9}","gcbh":"\\d{1,30}"}
	//{"entity":[{"syr:":"","xm":"aa","xb":"1","id":"11000011420200102000891","taskid":"5205071","rsrq":"2020-01-02 10:50:45","ysyj":"不符合入所条件","jcys":"aa","ysjcrq":"2020-01-02 10:50:45","jkzk":"一般或较弱","sldqm":"cc","sldqmrq":"2020-01-02 10:50:45","jsly":"一般或较弱","jsbh":"110000114","gcbh":"7027410579526"}]}
	@ApiOperation("暂不收押保存")
	@PostMapping("/saveZbsy")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveZbsy(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/kss/xyr/saveZbsy";
		try {
			//校验权限
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//数据类型校验
			Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
			List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
			String taskid=(String) rslist.get(0).get("taskid");
			System.err.println("taskid--"+taskid);
			if (StringUtils.isNullOrEmpty(taskid)){
				return ResponseMessage.error("taskid不能为空！");
			}
			System.err.println("map--"+map.get("entity").toString());
			map.put("interfaceId", interfaceId);
			ResponseMessage<String> msg = this.modelYz(map);
			if(msg.getStatus()!=200) {
				return ResponseMessage.error(msg.getMessage());
			}
			List<XyrModel> xyrModels = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);
			System.err.println("xyrModels--"+ JSON.toJSONString(xyrModels.get(0)));
			xyrModels.get(0).setCreatetime(new Date());
			xyrModels.get(0).setState("R2");
			xyrModels.get(0).setJsbh(jsbh);
			xyrModels.get(0).setCreator("管理者");
			XyrModel xyrModel=xyrModels.get(0);
			System.err.println("xyrModel--"+JSON.toJSONString(xyrModel));
			ResponseMessage<String> result = kssServerApis.saveZbsy(taskid,xyrModel);
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
	 * @api {post} /v4/kss/xyr/getFwdjTaskList 附物登记查询
	 * @apiVersion 0.4.0
	 * @apiName getFwdjTaskList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 附物登记查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}processDefinitionId								processDefinitionId
	 * @apiSuccess {String}processInstanceId								processInstanceId
	 * @apiSuccess {String}createtime										创建时间
	 * @apiSuccess {String}lessThanOrEqualMap								lessThanOrEqualMap
	 * @apiSuccess {String}start											起始
	 * @apiSuccess {String}ywlcid											ywlcid
	 * @apiSuccess {String}notEqualsMap										notEqualsMap
	 * @apiSuccess {String}greaterThanOrEqualMap							greaterThanOrEqualMap
	 * @apiSuccess {String}xxbl												xxbl
	 * @apiSuccess {String}snbh												snbh
	 * @apiSuccess {String}xm												姓名
	 * @apiSuccess {String}rsrq												入所日期
	 * @apiSuccess {String}xmpy												姓名拼音
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}ywlcid											ywlcid
	 * @apiSuccess {String}gyqx												关押期限
	 * @apiSuccess {String}xb												性别
	 * @apiSuccess {String}badw												办案单位
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}jsh												监室号
	 * @apiSuccess {String}processDefinitionKey								processDefinitionKey
	 * @apiSuccess {String}taskDefinitionKey								taskDefinitionKey
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}name												名称
	 * @apiSuccess {String}limit											总数
	 * @apiSuccess {String}assignee											assignee
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}taskId											taskId
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
	 *
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
	 *			"zszt": "",
	 * 			"xm": "",
	 * 			"jsh": "",
	 * 			"bm": "",
	 * 			"xb": "",
	 * 			"rsrq_start": "",
	 * 			"rsrq_end": "",
	 * 			"bahj": "",
	 * 			"gyqx_start": "",
	 * 			"gyqx_end": "",
	 * 			"taskDefinitionKey": "kss_rsdj_xxbl",
	 * 			"processDefinitionKey": "kss_rsdj",
	 * 			"pageSize": "10",
	 * 			"pageIndex": "1",
	 * 			"page": "1",
	 * 			"rows": "10",
	 * 			"sort": "id",
	 * 			"order": "desc"
	 *        }

	 */
	//{"zszt":"","xm":"","jsh":"","bm":"","xb":"","rsrq_start":"","rsrq_end":"","bahj":"","gyqx_start":"","gyqx_end":"","taskDefinitionKey":"kss_rsdj_xxbl","processDefinitionKey":"kss_rsdj","pageSize":"10","pageIndex":"0","page":"1","rows":"10","sort":"id","order":"desc"}
	//processDefinitionId,processInstanceId,createtime,lessThanOrEqualMap,start,ywlcid,notEqualsMap,greaterThanOrEqualMap,params,xxbl,snbh,xm,rsrq,xmpy,rybh,ywlcid,gyqx,xb,badw,jsbh,jsh,processDefinitionKey,taskDefinitionKey,rybh,name,limit,assignee,jsbh,taskId,message,result,total,data,status,timestamp
	@OpenAPI
	@ApiOperation("附物登记查询")
	@PostMapping("/getFwdjTaskList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> getFWdjTaskList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xyr/getFwdjTaskList";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			System.err.println("--maps--" + JSON.toJSONString(maps));
			// 领取状态(WPLQZT)
			//查询参数
			Variables variables=new Variables();
			Map<String,Object> params = new HashMap<String,Object>();
			Map<String, Object> notEqualsMap = Maps.newHashMap();
			Map<String, Object> greaterThanOrEqualMap = Maps.newHashMap();
			Map<String, Object> lessThanOrEqualMap = Maps.newHashMap();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				variables.setJsbh(jsbh);
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("taskDefinitionKey")))){
				variables.setTaskDefinitionKey((String)maps.getResult().get("taskDefinitionKey"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("processDefinitionKey")))){
				variables.setProcessDefinitionKey((String)maps.getResult().get("processDefinitionKey"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("pageSize")))){
				variables.setLimit((String)maps.getResult().get("pageSize"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("pageIndex")))){
				variables.setStart((String)maps.getResult().get("pageIndex"));
			}
			params.put("jsbh",jsbh);
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("xm")))){
				params.put("xm",(String)maps.getResult().get("xm"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("jsh")))){
				params.put("jsh",(String)maps.getResult().get("jsh"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("bm")))){
				params.put("bm",(String)maps.getResult().get("bm"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("xb")))){
				params.put("xb",(String)maps.getResult().get("xb"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("bahj")))){
				params.put("bahj",(String)maps.getResult().get("bahj"));
			}

			if((!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_start")))){
				greaterThanOrEqualMap.put("rsrq", (String)maps.getResult().get("rsrq_start"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_start")))){
				greaterThanOrEqualMap.put("gyqx", (String)maps.getResult().get("gyqx_start"));
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("rsrq_end")))){
				lessThanOrEqualMap.put("rsrq", (String)maps.getResult().get("rsrq_end")+" 23:59:59");
			}
			if((!StringUtils.isNullOrEmpty(maps.getResult().get("gyqx_end")))){
				lessThanOrEqualMap.put("gyqx", (String)maps.getResult().get("gyqx_end")+" 23:59:59");
			}

			variables.setParams(params);    //流程匹配参数
			variables.setNotEqualsMap(notEqualsMap);    //流程不等于匹配参数
			variables.setGreaterThanOrEqualMap(greaterThanOrEqualMap);    //流程大于匹配参数
			variables.setLessThanOrEqualMap(lessThanOrEqualMap);    //流程小于匹配参数
			System.err.println("--variables--" + JSON.toJSONString(variables));

			ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.getTaskList(variables);
			System.err.println("--result--" + JSON.toJSONString(result));

			//封装需要的数据
			Map<String, Object> maplist = new HashMap<String, Object>();
			maplist.put("entity",result.getResult().getData());
			maplist.put("interfaceId", interfaceId);
			maplist.put("total",result.getResult().getTotal());
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





	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_XyrModel>> xyr_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_XyrModel>> result= kssService.xyr_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}


	/**
	 * @api {get} /v4/kss/xyr/xyrList 嫌疑人信息查询
	 * @apiVersion 0.4.0
	 * @apiName xyrList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 嫌疑人信息查询.
	 *

	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 *
	 * @apiSuccess {String}jsbh         				                    监所编号
	 * @apiSuccess {String}id          				                        ID
	 * @apiSuccess {String}xm         				                        姓名
	 * @apiSuccess {String}xb                                               性别
	 * @apiSuccess {String}xbString                                         性别(已转换)
	 * @apiSuccess {String}jsbhString                                       监所编号(已转换)
	 * @apiSuccess {String}creator                                          创建人
	 * @apiSuccess {String}jsh                                              监室号
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
	 *         "xbString": "女性",
	 *         "creator": "管理员",
	 *         "xm": "123",
	 *         "xb": "2",
	 *         "id": "11000011420190819000389",
	 *         "jsbhString": "北京市第一看守所",
	 *         "jsh": "12",
	 *         "jsbh": "110000114"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576826568061
	 * }
	 *
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{
	 *        "zjh_type":"证件号类型",
	 *        "xm_type":"姓名类型",
	 *        "czzt":"操作状态(字典：CZZT)",
	 *        "xm":"姓名(最大字段长度：30)",
	 *        "xb":"性别(字典：XB)",
	 *        "sydw":"送押单位(最大字段长度：60)",
	 *        "jsh":"监室号(最大字段长度：4)",
	 *        "zjh":"证件号(最大字段长度：8)",
	 *        }
	 *
	 */

	@OpenAPI
	@ApiOperation("嫌疑人信息查询")
	@GetMapping("xyrList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> xyrList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		String interfaceId = "/v4/kss/xyr/xyrList";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}

			String zjh_type = (String) maps.getResult().get("zjh_type");
			String xm_type = (String) maps.getResult().get("xm_type");

			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("czzt"))) {
				param.and("czzt", TermType.in, maps.getResult().get("czzt"));
			}

			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				String xmpy =  Pinyin4j.cn2Pinyin((String) maps.getResult().get("xm"));
				if("0".equals(xm_type)) {
					param.and("xmpy", TermType.like, "%"+xmpy+"%");
				}else {
					param.and("xm", TermType.like, "%"+maps.getResult().get("xm")+"%");
				}

			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xb"))){
				param.and("xb",TermType.eq,maps.getResult().get("xb"));
			}

			if(!StringUtils.isNullOrEmpty(maps.getResult().get("sydw"))){
				param.and("sydw",TermType.eq,maps.getResult().get("sydw"));
			}

			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh",TermType.eq, maps.getResult().get("jsh"));
			}

			if (!StringUtils.isNullOrEmpty(maps.getResult().get("zjh"))) {
				if ("0".equals(zjh_type)) {
					param.and("zjh", TermType.eq, maps.getResult().get("zjh"));
				} else {
					param.and("zjh", TermType.not, maps.getResult().get("zjh"));
				}
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);

			ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.xyrQuery(param);
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
	 * @api {post} /v4/kss/xyr/saveAsShdj 保存回收登记
	 * @apiVersion 0.4.0
	 * @apiName saveAsShdj
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 保存回收登记.
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
	 *   json:{"entity":[{
	 *                     "xm":" 姓名(必填; 最大字段长度：30)",
	 *                     "rsxz":"入所原因(必填;字典：RSXZ)",
	 *                     "jsh":"监室号(必填; 最大字段长度：4)",
	 *                     "flwsh":"法律文书号(必填;最大字段长度：40)",
	 *                      "shyy":"收回原因(必填;字典：AJLB ; 最大字段长度：34)",
	 *                      "rsrq":"入所日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
	 *                      "gcbh":"过程编号(必填; 最大字段长度：30)"
	 *                     }]
	 *             }
	 *
	 */

	//{"rsrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","xm":".{1,30}","rsxz":"\\d{1,1}","jsh":"\\d{1,4}","flwsh":".{1,40}","shyy":"\\d{1,1}","gcbh":"\\d{1,30}","creator":".{1,50}"}
	@ApiOperation("保存回收登记")
	@PostMapping("saveAsShdj")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> saveAsShdj(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/xyr/saveAsShdj";

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

			List<XyrModel> xyrList = JSONArray.parseArray(map.get("entity").toString(), XyrModel.class);

			xyrList.get(0).setJsbh(jsbh);
			xyrList.get(0).setUpdatetime(new Date());
			xyrList.get(0).setState("R2");
			xyrList.get(0).setCzzt("03");

			System.out.println(xyrList.get(0).getCreator()+"--------------------");
			XyrModel xyrEntity=  xyrList.get(0);

			String flowKey = CacheUtils.get().getFlowKey("KSS_RSDJ");
			if ("".equals(flowKey)) {
			  return ResponseMessage.error("获取流程实例错误,请联系管理员！！");
			  }

			ResponseMessage<String> result = kssServerApis.saveAsShdj(flowKey, xyrEntity);

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








	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xyr_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XyrModel data) {
		return kssService.xyr_save(data);
	}










	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> xyr_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_XyrModel data) {
		return kssService.xyr_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_XyrModel> xyr_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xyr_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> xyr_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.xyr_deleteByKey(id);
	}
}
