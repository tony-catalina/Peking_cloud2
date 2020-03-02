/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.LstlhmcModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
import awd.cloud.platform.model.kss.Kss_LstlhmcModel;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/lstlhmc")
@Api(tags = "kss-lstlhmc",description = "Lstlhmc") 
public class Kss_LstlhmcController extends PublicService {
	
	@Autowired
    private KssService kssService;
	@Autowired
	private KssServerApis kssServerApis;


	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_LstlhmcModel>> lstlhmc_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_LstlhmcModel>> result= kssService.lstlhmc_query(queryParam);
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
	public ResponseMessage<String> lstlhmc_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LstlhmcModel data) {
		return kssService.lstlhmc_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lstlhmc_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LstlhmcModel data) {
		return kssService.lstlhmc_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_LstlhmcModel> lstlhmc_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lstlhmc_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> lstlhmc_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lstlhmc_deleteByKey(id);
	}




	/**
	 * @api {post} /v4/kss/lstlhmc/lstlhmcList   历史投牢花名册查询（若有参数，就执行拼接参数查询）
	 * @apiVersion 0.4.0
	 * @apiName lstlhmcList
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription    历史投牢花名册查询（若有参数，就执行拼接参数查询）
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}jsbh                                             监所编号
	 * @apiSuccess {String}jsbhString     									监所编号(已转换)
	 * @apiSuccess {String}dyrq  											打印日期
	 * @apiSuccess {String}rs   											人数
	 * @apiSuccess {String}rybh                                             人员编号
	 * @apiSuccess {String}state   											删除状态(字典:STATE)
	 * @apiSuccess {String}stateString										删除状态(已转换)
	 * @apiSuccess {String}creator                                          创建者
	 * @apiSuccess {String}createtime                                       创建时间
	 * @apiSuccess {String}updator   										更新人
	 * @apiSuccess {String}updatetime  										更新时间
	 *
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
	 *     "total": 49,
	 *     "data": [
	 *       {
	 *         "rs": 2,
	 *         "creator": "管理员",
	 *         "createtime": "2019-08-01 14:02:18",
	 *         "rybh": "110000111201907160013,110000111201907160003",
	 *         "updator": "null",
	 *         "id": "11000011420190801000023",
	 *         "dyrq": "2019-08-01 14:02:18",
	 *         "state": "R2",
	 *         "stateString": "有效",
	 *         "jsbhString": "北京市第一看守所",
	 *         "updatetime": "2019-08-01 14:02:18",
	 *         "jsbh": "110000114"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578981208482
	 * }


	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 * 	  json:{
	 *             "startTime":"开始打印的时间"
	 *             "endTime":"结束打印的时间"
	 *         }
	 *
	 *
	 */

	@ApiOperation("历史投牢花名册查询")
	@PostMapping("/lstlhmcList")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> lstlhmcList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/lstlhmc/lstlhmcList";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("startTime"))) {
				param.and("dyrq",TermType.gte,maps.getResult().get("startTime").toString());
			}
			if (!StringUtils.isNullOrEmpty(maps.getResult().get("endTime"))){
				param.and("dyrq",TermType.lte,maps.getResult().get("endTime").toString()+" 23:59:59");
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>>  result = kssServerApis.lstlhmcQueryForPage(param);
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
	 * @api {post} /v4/kss/lstlhmc/jbxxListWithoutState   查询基本信息表（若有参数，就执行拼接参数查询）
	 * @apiVersion 0.4.0
	 * @apiName jbxxListWithoutState
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription    查询基本信息表（若有参数，就执行拼接参数查询）
	 *
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id          				                        id
	 * @apiSuccess {String}gcbh                                             过程编号
	 * @apiSuccess {String}wbrybh 											网办人员编号
	 * @apiSuccess {String}rybh  											人员编号
	 * @apiSuccess {String}dah 												档案编号
	 * @apiSuccess {String}jsbhString                                       监所编号(已转换)
	 * @apiSuccess {String}snbh  											人员所内编号
	 * @apiSuccess {String}jsh										        监视号
	 * @apiSuccess {String}xm                                          		姓名
	 * @apiSuccess {String}xmpy  											姓名拼音
	 * @apiSuccess {String}xmpyszm   										姓名拼音首字母
	 * @apiSuccess {String}bm  												别名
	 * @apiSuccess {String}bmty          				                	别名同音
	 * @apiSuccess {String}mz                                             	民族(数字字典:MZ)
	 * @apiSuccess {String}mzString                                         民族(已转换)
	 * @apiSuccess {String}gj                                             	国籍(字典:GJ)
	 * @apiSuccess {String}gjString                                        	国籍(已转换)
	 * @apiSuccess {String}xb                                               性别(字典:xb)
	 * @apiSuccess {String}xbString                                         性别(已转换)
	 * @apiSuccess {String}csrq                                             出生日期
	 * @apiSuccess {String}zjlx                                             证件类型(字典:ZJLX)
	 * @apiSuccess {String}zjlxString                                       证件类型(已转换)
	 * @apiSuccess {String}zjh                                             	证件号
	 * @apiSuccess {String}zzmm  											政治面貌(字典:ZZMM)
	 * @apiSuccess {String}zzmmString                                       政治面貌(已转换)
	 * @apiSuccess {String}hyzk                                     		婚姻状况(字典:HYZK)
	 * @apiSuccess {String}hyzkString                                       婚姻状况(已转换)
	 * @apiSuccess {String}zuc                                             	足长
	 * @apiSuccess {String}sg                                              	身高
	 * @apiSuccess {String}jg                                             	籍贯(字典:XZQH)
	 * @apiSuccess {String}jgString                                         籍贯(已转换)
	 * @apiSuccess {String}hjd                                             	户籍地(字典:XZQH)
	 * @apiSuccess {String}hjdString                                        户籍地(已转换)
	 * @apiSuccess {String}hjdxz                                           	户籍地详址
	 * @apiSuccess {String}xzd                                             	现住地(字典:XZQH)
	 * @apiSuccess {String}xzdString                                        现住地(已转换)
	 * @apiSuccess {String}xzdxz                                           	现住地详址
	 * @apiSuccess {String}whcd                                             文化程度(字典:WHCD)
	 * @apiSuccess {String}whcdString                                       文化程度(已转换)
	 * @apiSuccess {String}zc                                               专长(字典:ZC)
	 * @apiSuccess {String}zcString                                       	专长(已转换)
	 * @apiSuccess {String}sf          				                        身份(字典:sf)
	 * @apiSuccess {String}sfString                                         身份(已转换)
	 * @apiSuccess {String}tssf          				                    特殊身份(字典:TSSF)
	 * @apiSuccess {String}tssfString                                       特殊身份(已转换)
	 * @apiSuccess {String}zy 												职业(字典:ZY)
	 * @apiSuccess {String}zyString  										职业(已转换)
	 * @apiSuccess {String}gzdw 											(原)工作单位
	 * @apiSuccess {String}jkzk                                       		健康情况(字典:JKZK)
	 * @apiSuccess {String}jkzkString  										健康情况(已转换)
	 * @apiSuccess {String}bhlx										        病号类型(字典:BHLX)
	 * @apiSuccess {String}bhlxString                                       病号类型(已转换)
	 * @apiSuccess {String}azb  											艾滋病(字典:SHFO)
	 * @apiSuccess {String}azbString   										艾滋病(已转换)
	 * @apiSuccess {String}rsrq  											入所日期
	 * @apiSuccess {String}rsxz          				                	入所性质(字典:RSXZ)
	 * @apiSuccess {String}rsxzString                                       入所性质(已转换)
	 * @apiSuccess {String}zrdw                                         	转入单位
	 * @apiSuccess {String}shid                                             手环ID
	 * @apiSuccess {String}sydw                                        		送押单位
	 * @apiSuccess {String}syr                                              送押人
	 * @apiSuccess {String}sy                                         		收押人
	 * @apiSuccess {String}byzd                                             收押非拘捕人员
	 * @apiSuccess {String}sypzwsh                                          收押凭证文书号
	 * @apiSuccess {String}sypz                                       		收押凭证(字典:SYPZ)
	 * @apiSuccess {String}sypzString                                       收押凭证(已转换)
	 * @apiSuccess {String}jyrq  											羁押日期
	 * @apiSuccess {String}gyqx                                       		关押期限
	 * @apiSuccess {String}ay                                     			主要案由(字典:AJLB)
	 * @apiSuccess {String}ayString                                       	主要案由(已转换)
	 * @apiSuccess {String}xhay                                             细化案由(字典:AJLB)
	 * @apiSuccess {String}xhayString                                       细化案由(已转换)
	 * @apiSuccess {String}fzjl                                             犯罪经历
	 * @apiSuccess {String}jyaq                                         	简要案情
	 * @apiSuccess {String}zxf                                             	重刑犯(字典:ZXF)
	 * @apiSuccess {String}zxfString                                        重刑犯(已转换)
	 * @apiSuccess {String}wxdj                                           	危险等级(字典:FXDJ)
	 * @apiSuccess {String}wxdjString                                       危险等级(已转换)
	 * @apiSuccess {String}caaj                                        		从案类型(字典:AJLB)
	 * @apiSuccess {String}caajString                                       从案类型(已转换)
	 * @apiSuccess {String}cylx                                             成员类型(字典:CYLX)
	 * @apiSuccess {String}cylxString                                       成员类型(已转换)
	 * @apiSuccess {String}bar                                              办案人(多个以逗号或空格分开)
	 * @apiSuccess {String}barjh                                       		办案人警号(多个以逗号或空格分开)
	 * @apiSuccess {String}bahj          				                    办案环节(字典:BAJD)
	 * @apiSuccess {String}bahjString                                       办案环节(已转换)
	 * @apiSuccess {String}bardh          				                    办案民警电话
	 * @apiSuccess {String}czdh                                             办案传真电话
	 * @apiSuccess {String}zzqsrq 											暂住起始日期
	 * @apiSuccess {String}jlrq  											拘留日期
	 * @apiSuccess {String}dbrq 											逮捕日期
	 * @apiSuccess {String}scqsrq                                           审查起诉日期
	 * @apiSuccess {String}ysfyrq  											移送法院日期
	 * @apiSuccess {String}badw										        办案单位
	 * @apiSuccess {String}dwlx                                          	办案单位类型(字典:DWLX)
	 * @apiSuccess {String}dwlxString  										办案单位类型(已转换)
	 * @apiSuccess {String}hx   											缓刑
	 * @apiSuccess {String}hxqx  											缓刑期限
	 * @apiSuccess {String}csyy          				                	出所原因(字典:CSYY)
	 * @apiSuccess {String}csyyString                                       出所原因(已转换)
	 * @apiSuccess {String}csqx                                         	出所去向
	 * @apiSuccess {String}cssj                                             出所时间
	 * @apiSuccess {String}xq                                        		刑期
	 * @apiSuccess {String}cljg                                             处理结果(字典:CLJG)
	 * @apiSuccess {String}cljgString                                       处理结果(已转换)
	 * @apiSuccess {String}fjx                                             	附加刑(字典:FJX)
	 * @apiSuccess {String}fjxString                                        附加刑(已转换)
	 * @apiSuccess {String}zwbh                                       		指纹编号
	 * @apiSuccess {String}jcqk                                            	奖惩情况
	 * @apiSuccess {String}ykss  											原看守所
	 * @apiSuccess {String}lsyy                                       		留所原因(字典:LSYY)
	 * @apiSuccess {String}lsyyString                                     	留所原因(已转换)
	 * @apiSuccess {String}rygllb                                       	人员管理类别(字典:RYGLLB)
	 * @apiSuccess {String}rygllbString                                     人员管理类别(已转换)
	 * @apiSuccess {String}zszt      										在所状态(字典:ZSZT)
	 * @apiSuccess {String}zsztString                                       在所状态(已转化)
	 * @apiSuccess {String}lscsyy                                         	临时出所原因(字典:LSCS)
	 * @apiSuccess {String}lscsyyString                                     临时出所原因(已转换)
	 * @apiSuccess {String}lscssj                                        	上一次临时出所时间
	 * @apiSuccess {String}crjbj                                           	出入监标记(字典:CJBJ)
	 * @apiSuccess {String}crjbjString                                      出入监标记(已转换)
	 * @apiSuccess {String}rkbhgbj                                        	入库合格不合格标记(字典:SHFO)
	 * @apiSuccess {String}rkbhgbjString                                    入库合格不合格标记(已转换)
	 * @apiSuccess {String}rkbhgyy                                          不合格入库原因
	 * @apiSuccess {String}lrsfjsString                                     是否及时录入标记(已转换)
	 * @apiSuccess {String}zyryxgqk                                         在押人员相关情况 (字典:ZYRYXGQK)
	 * @apiSuccess {String}zyryxgqkString                                   在押人员相关情况(已转换)
	 * @apiSuccess {String}emlx          				                    耳目类型(字典:EMLX)
	 * @apiSuccess {String}emlxString                                       耳目类型(已转换)
	 * @apiSuccess {String}jygzzlpg          				                教育质量改造评估(字典:SHFO)
	 * @apiSuccess {String}jygzzlpgString                                   教育质量改造评估(已转换)
	 * @apiSuccess {String}flws 											法律文书打印
	 * @apiSuccess {String}zyaf  											重要案犯(字典:ZYAF)
	 * @apiSuccess {String}zyafString 										重要案犯(已转换)
	 * @apiSuccess {String}sfcxcy                                       	是否抽血采样(字典:SHFO)
	 * @apiSuccess {String}sfcxcyString  									是否抽血采样(已转换)
	 * @apiSuccess {String}sffy										        是否需要发药(字典:SHFO)
	 * @apiSuccess {String}sffyString                                       是否需要发药(已转换)
	 * @apiSuccess {String}fyksrq  											发药开始日期
	 * @apiSuccess {String}fyjsrq   										发药结束日期
	 * @apiSuccess {String}wpsflq  											物品是否领取完(字典:SHFO)
	 * @apiSuccess {String}wpsflqString          				            物品是否领取完(已转换)
	 * @apiSuccess {String}sfwxjc                                           五项检查是否及时(字典:SHFO)
	 * @apiSuccess {String}sfwxjcString                                     五项检查是否及时(已转换)
	 * @apiSuccess {String}ywlcid                                           业务流程ID
	 * @apiSuccess {String}syrdh                                        	送押人电话
	 * @apiSuccess {String}tabh                                            	同案编号
	 * @apiSuccess {String}za                                         		专案
	 * @apiSuccess {String}yfh                                              衣服号
	 * @apiSuccess {String}zw                                            	职务(字典:ZW)
	 * @apiSuccess {String}zwString                                      	职务(已转换)
	 * @apiSuccess {String}zwjb                                             职务级别(ZWJB)
	 * @apiSuccess {String}zwjbString  										职务级别(已转换)
	 * @apiSuccess {String}tz                                       		体重
	 * @apiSuccess {String}cwh                                     			床位号
	 * @apiSuccess {String}tbtsbj                                       	体表特殊标记
	 * @apiSuccess {String}cljgrq                                           处理结果日期
	 * @apiSuccess {String}pjzm                                             判决罪名
	 * @apiSuccess {String}zxtzssdrq                                        执行通知书送达日期
	 * @apiSuccess {String}tzcsbz                                        	通知出所标志(字典:SHFO)
	 * @apiSuccess {String}tzcsbzString                                     通知出所标志(已转换)
	 * @apiSuccess {String}je                                        		金额
	 * @apiSuccess {String}jbr                                           	经办人
	 * @apiSuccess {String}jbrq                                             经办日期
	 * @apiSuccess {String}sfswry                                       	是否涉维人员(字典:SHFO)
	 * @apiSuccess {String}sfswryString                                     是否涉维人员(已转换)
	 * @apiSuccess {String}swpgzb                                           涉维评估指标
	 * @apiSuccess {String}swpgr                                       		涉维评估人
	 * @apiSuccess {String}swpgsj                                           涉维评估时间
	 * @apiSuccess {String}jsycbz                                       	精神异常标志(字典:SHFO)
	 * @apiSuccess {String}jsycbzString          				            精神异常标志(已转换)
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
	 *{
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "lscssj": "null",
	 *         "wpsflq": "null",
	 *         "jsycbzString": "null",
	 *         "jkzk": "3",
	 *         "rsxzString": "临时寄押",
	 *         "shid": "null",
	 *         "zcString": "驾机",
	 *         "isjdwm": "null",
	 *         "cypjfz": "100",
	 *         "zszt": "11",
	 *         "bhlxString": "不是",
	 *         "swpgsj": "null",
	 *         "gcbh": "15096167325114",
	 *         "bar": "办案人",
	 *         "flws": "null",
	 *         "fyksrq": "null",
	 *         "emlx": "null",
	 *         "state": "R2",
	 *         "sfswryString": "null",
	 *         "jkzkString": "有病",
	 *         "emlxString": "null",
	 *         "xmpyszm": "null",
	 *         "rsjclxString": "不检测",
	 *         "hyzk": "1",
	 *         "fjx": "null",
	 *         "ay": "010100,010140,010150,010180",
	 *         "zzqsrq": "null",
	 *         "jbr": "null",
	 *         "jsh": "1201",
	 *         "bmty": "WU",
	 *         "wcnbjString": "null",
	 *         "bardh": "13222753885",
	 *         "cljgString": "null",
	 *         "zxtzssdrq": "null",
	 *         "sykzrq": "2019-12-14 13:08:04",
	 *         "wcnbj": "null",
	 *         "sf": "09",
	 *         "sffy": "null",
	 *         "sg": "189",
	 *         "sypzwsh": "12321",
	 *         "bz": "备注备注备注备注备注备注备注,备注备注备注备注备注备注备注",
	 *         "sfString": "无业人员",
	 *         "csyyString": "null",
	 *         "wpsflqString": "null",
	 *         "createtime": "2019-12-14 13:08:04",
	 *         "jygzzlpgString": "null",
	 *         "sy": "办案人",
	 *         "ryzwbj": "0",
	 *         "rygllbString": "null",
	 *         "crjbjString": "null",
	 *         "zyString": "中国共产党中央委员会和地方各级组织负责人.",
	 *         "sfswry": "null",
	 *         "xbString": "男性",
	 *         "fyjsrq": "null",
	 *         "sfyxjslxString": "null",
	 *         "rsrq": "2019-12-14 13:02:46",
	 *         "cwh": "01",
	 *         "slaj": "null",
	 *         "sfcxcy": "0",
	 *         "zwbh": "null",
	 *         "bhlx": "1",
	 *         "rygllb": "null",
	 *         "csrq": "1985-12-24 00:00:00",
	 *         "zjlx": "11",
	 *         "dagdbjString": "null",
	 *         "tz": 0,
	 *         "zuc": "26",
	 *         "ysfyrq": "null",
	 *         "tzcsbz": "null",
	 *         "pjzm": "null",
	 *         "zyryxgqkString": "null",
	 *         "jsly": "null",
	 *         "gyqx": "2020-02-14 00:00:00",
	 *         "cylx": "null",
	 *         "lastfxpgfz": "null",
	 *         "wbrybh": "null",
	 *         "csqx": "null",
	 *         "sfwxjc": "null",
	 *         "xhayString": "null",
	 *         "whcd": "70",
	 *         "jsycbz": "null",
	 *         "sypz": "2",
	 *         "rkbhgbjString": "null",
	 *         "zdry": "0",
	 *         "ykss": "null",
	 *         "crjbj": "null",
	 *         "mzString": "汉",
	 *         "syr": "送押",
	 *         "lsyy": "null",
	 *         "cylxString": "null",
	 *         "zzmm": "03",
	 *         "syrdh": "null",
	 *         "rkbhgbj": "null",
	 *         "gj": "156",
	 *         "zxf": "null",
	 *         "cssj": "null",
	 *         "jygzzlpg": "null",
	 *         "xb": "1",
	 *         "sypzString": "逮捕证",
	 *         "xzdxz": "北京东城区",
	 *         "jbString": "无",
	 *         "cszwyz": "null",
	 *         "xm": "李无力",
	 *         "gzdw": "作单位\t",
	 *         "rybh": "110000114201912140001",
	 *         "xq": "0,1,1",
	 *         "yfh": "null",
	 *         "swpgr": "null",
	 *         "barjh": "null",
	 *         "zxfString": "null",
	 *         "hxqx": "null",
	 *         "stateString": " ",
	 *         "zwjb": "null",
	 *         "xhay": "null",
	 *         "wxdj": "null",
	 *         "hx": "null",
	 *         "sffyString": "null",
	 *         "dbrq": "2019-12-14 00:00:00",
	 *         "czdh": "null",
	 *         "id": "11000011420191214000439",
	 *         "azbString": "null",
	 *         "zwString": "null",
	 *         "jcqk": "null",
	 *         "tssf": "03",
	 *         "fzjl": "null",
	 *         "za": "null",
	 *         "zc": "03",
	 *         "caaj": "null",
	 *         "jlrq": "2019-12-14 00:00:00",
	 *         "rsxz": "13",
	 *         "cljgrq": "null",
	 *         "ayString": "背叛、分裂国家案,武装叛乱、暴乱案,策动武装暴乱案,资助危害国家安全犯罪活动案",
	 *         "jb": "01",
	 *         "zjh": "110101199303072633",
	 *         "tzcsbzString": "null",
	 *         "je": 0,
	 *         "badw": "北京市公安局国保局指挥处",
	 *         "jg": "110101",
	 *         "zw": "null",
	 *         "swpgzb": "null",
	 *         "zy": "0-10",
	 *         "jj": "01",
	 *         "hyzkString": "未婚",
	 *         "tabh": "null",
	 *         "jbrq": "null",
	 *         "azb": "null",
	 *         "cljg": "null",
	 *         "rsjclx": "0",
	 *         "drjsr": "null",
	 *         "bm:": "null",
	 *         "slajString": "null",
	 *         "sydw": "北京市公安局国保局指挥处",
	 *         "ldfString": "无",
	 *         "clsypzr": "null",
	 *         "lscsyy": "null",
	 *         "sfypjString": "否",
	 *         "jsbhString": "北京市第一看守所",
	 *         "grjl": "null",
	 *         "hjdString": "北京东城区",
	 *         "creator": "管理员",
	 *         "sfyxjslx": "null",
	 *         "zdryString": "非重点",
	 *         "dwlx": "null",
	 *         "zyafString": "不是",
	 *         "updatetime": "null",
	 *         "jsbh": "110000114",
	 *         "zyryxgqk": "null",
	 *         "sfypj": "0",
	 *         "xmpy": "LIWULI",
	 *         "dah": "201912140006",
	 *         "whcdString": "初中",
	 *         "jyaq": "简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*简要案情*",
	 *         "zjlxString": "身份证",
	 *         "rkbhgyy": "null",
	 *         "zyaf": "1",
	 *         "cszwyzString": "null",
	 *         "hjd": "110101",
	 *         "xzd": "110101",
	 *         "wxdjString": "null",
	 *         "lrsfjs": "null",
	 *         "sfcxcyString": "否",
	 *         "mz": "01",
	 *         "zzmmString": "中国共产主义青年团团员",
	 *         "bahj": "12",
	 *         "jgString": "北京东城区",
	 *         "sfrzString": "null",
	 *         "jjString": "无",
	 *         "sfwxjcString": "null",
	 *         "csyy": "null",
	 *         "zsztString": "未决",
	 *         "isjdwmString": "null",
	 *         "fjxString": "null",
	 *         "lscsyyString": "null",
	 *         "dagdbj": "null",
	 *         "ywlcid": ",5068639",
	 *         "caajString": "null",
	 *         "hjdxz": "北京东城区",
	 *         "zwjbString": "null",
	 *         "snbh": "20190311",
	 *         "scqsrq": "null",
	 *         "ygryString": "无",
	 *         "xzdString": "北京东城区",
	 *         "tssfString": "民主党首",
	 *         "bahjString": "逮捕",
	 *         "lsyyString": "null",
	 *         "ygry": "01",
	 *         "sfrz": "null",
	 *         "byzd": "null",
	 *         "ldf": "01",
	 *         "zrdw": "北京市公安局国保局指挥处",
	 *         "gjString": "中国",
	 *         "drjssj": "null",
	 *         "dwlxString": "null",
	 *         "ryzwbjString": "未录入",
	 *         "tbtsbj": "null",
	 *         "updator": "null",
	 *         "jyrq": "2019-12-14 00:00:00",
	 *         "lrsfjsString": "null"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578640551496
	 * }


	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
	 * 	  json:{
	 *             "xm":"姓名"
	 *             "snbh":"人员所内编号"
	 *             "jqh":"监区号"
	 *             "jsh":"监室号"
	 *             "rybh":"人员编号"
	 *         }
	 *
	 *
	 */

	@ApiOperation("查询基本信息表")
	@PostMapping("/jbxxListWithoutState")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<Map<String, Object>> jbxxListWithoutState(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

		String interfaceId = "/v4/kss/lstlhmc/jbxxListWithoutState";
		String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			//查询参数
			QueryParam param = new QueryParam();
			if (!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}


				String sortName="";
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("sort"))) {
				 sortName = maps.getResult().get("sort").toString().substring(0, sortName.length() - 6);

			}

			List<Sort> sorts = new ArrayList<>();
			Sort sort = new Sort();
			String orderBy="";
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("order"))) {
				   orderBy = maps.getResult().get("order").toString();

			}

			if(sortName != null && sortName.indexOf("String") > 0 && orderBy != null && orderBy.indexOf("String")>0) {
				sort.setName(sortName);
				sort.setOrder(orderBy);
			}else {
				sort.setName("id");
				sort.setOrder("desc");
			}
			sorts.add(sort);
			param.setSorts(sorts);





			if(!StringUtils.isNullOrEmpty(maps.getResult().get("snbh"))) {
				param.and("snbh", TermType.like, "%"+maps.getResult().get("snbh").toString()+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
				param.and("xm", TermType.like, "%"+maps.getResult().get("xm").toString()+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jqh"))) {
				param.and("jsh", TermType.like, maps.getResult().get("jqh").toString()+"%");
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
				param.and("jsh", TermType.like, maps.getResult().get("jsh").toString());
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
				param.and("rybh", TermType.in, maps.getResult().get("rybh").toString());
			}
			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--" + JSON.toJSONString(param));

			ResponseMessage<PagerResult<Map<String,Object>>>  result = kssServerApis.queryForPage(param);
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



}
