/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.model.kss.Kss_SpdetailorderModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.DateTimeUtils;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/spdetailorder")
@Api(tags = "kss-spdetailorder",description = "Spdetailorder") 
public class Kss_SpdetailorderController extends PublicService{
	
	@Autowired
	private KssServerApis kssServerApis;
	
	
	/**
	 * @return
	 * @api {get} /v4/kss/spdetailorder/getProcessForGwgl 订单查询
	 * @apiVersion 0.4.0
	 * @apiName getProcessForGwgl
	 * @apiGroup g_kss
	 * @apiPermission any
	 * @apiDescription 订单查询
	 * @apiParam {String} appcode 											应用代码(必填)
	 * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
	 * @apiParam {String} json 												查询参数集
	 *
	 * @apiSuccess {String}id												送物人姓名
	 * @apiSuccess {String}jsbh												监所编号
	 * @apiSuccess {String}rybh												人员编号
	 * @apiSuccess {String}ddbh										                     订单编号
	 * @apiSuccess {String}sptm										                    商品条码
	 * @apiSuccess {String}sptmString										 商品条码(已转换)
	 * @apiSuccess {String}spsl										                     商品数量
	 * @apiSuccess {String}xfje											           消费金额
	 * @apiSuccess {String}sfje											           实付金额
	 * @apiSuccess {String}ddcjsj											下单时间
	 * @apiSuccess {String}status											订单状态
	 * @apiSuccess {String}statusString										订单状态(已转换)
	 * @apiSuccess {String}shzt											          审核状态(PSBZ)
	 * @apiSuccess {String}shyj											          审核意见
	 * @apiSuccess {String}shsj											          审核时间
	 * @apiSuccess {String}shr											          审核人
	 * @apiSuccess {String}gjqrr											管教确认人
	 * @apiSuccess {String}gjqrsj											管教确认时间
	 * @apiSuccess {String}gjzdblr											管教中队办理人
	 * @apiSuccess {String}gjzdshyj											管教中队审核意见
	 * @apiSuccess {String}gjzdpsbz											管教中队审批状态
	 * @apiSuccess {String}gjzdshsj											管教中队审批时间
	 * @apiSuccess {String}zhzdblr											综合中队办理人
	 * @apiSuccess {String}zhzdshyj											综合中队审核意见
	 * @apiSuccess {String}zhzdpsbz											综合中队审批状态
	 * @apiSuccess {String}zhzdshsj											综合中队审批时间
	 * @apiSuccess {String}ywlcid											业务流程id
	 * @apiSuccess {String}spmc											          商品名称
	 * @apiSuccess {String}taskid											流程id
	 * @apiSuccess {String}ldspxx{
	 * 								spbz									审批标志
	 * 								qrtj									true
	 * 								gwgl_sfje
	 * 								gwgl_sqsj
	 * 								gwgl_sptmString
	 * 								gwgl_sptm
	 * 								gwgl_spsl
	 * 								snbh									 所内编号
	 * 								xm										 姓名
	 * 								rybh									 人员编号
	 * 								gwgl_ddbh								 订单编号
	 *                              jsbh									 监所编号
	 *                              jsh										 监室号
	 * 								}									           审批信息
	 *
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
	 * "data":  [
	 *         "ddbh": "1113231",
	 *         "sptm": "123131",
	 *         "spsl": "1",
	 *         "xfje": "12019-12-04 10:10:10",
	 *         ......
	 * ],
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
	 *    "processDefinitionKey":"业务流程id(必传)",
	 *    "taskDefinitionKey":"流程id(必传)",
	 *    "sptm":"商品条码(非必传)"
	 * }
	 */
    
    @OpenAPI
	@ApiOperation("订单查询")
	@GetMapping("/getProcessForGwgl")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> getProcessForGwgl(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
		String interfaceId = "/v4/kss/spdetailorder/getProcessForGwgl";
		String state = "R2";
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
			if (maps.getStatus() != 200) {
				return ResponseMessage.error(maps.getMessage());
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("processDefinitionKey"))) {
				return ResponseMessage.error("业务id必传！");
			}
			if(StringUtils.isNullOrEmpty(maps.getResult().get("taskDefinitionKey"))) {
				return ResponseMessage.error("流程id必传！");
			}
			Variables variables = new Variables();
			variables.setJsbh(jsbh);
			variables.setProcessDefinitionKey(maps.getResult().get("processDefinitionKey").toString());
			String sptm=maps.getResult().get("sptm").toString();
			Map<String,Object> params = Maps.newHashMap();
			if(!StringUtils.isNullOrEmpty(sptm)) {
				params.put("gwgl_sptm", sptm);
			}
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("taskDefinitionKey"))) {
				variables.setTaskDefinitionKey(maps.getResult().get("taskDefinitionKey").toString());
			}
			int _pageIndex = 0;
			int _pageSize = 0;
			if(!StringUtils.isNullOrEmpty(maps.getResult().get("pageSize")) && !StringUtils.isNullOrEmpty(maps.getResult().get("page"))) {
				if(!StringUtils.isNullOrEmpty(maps.getResult().get("page"))) {
					_pageIndex = Integer.parseInt(maps.getResult().get("page").toString());
				}
				if(!StringUtils.isNullOrEmpty(maps.getResult().get("pageSize"))) {
					_pageIndex = Integer.parseInt(maps.getResult().get("pageSize").toString());
				}
				int num = _pageIndex;
				if(1 != _pageIndex && 0!= _pageIndex) {
					_pageIndex = _pageSize*num-_pageSize;
					_pageSize = _pageSize*num;
				}else {
					_pageIndex = 0;
				}

			}
			variables.setStart(String.valueOf(_pageIndex));
			variables.setLimit(String.valueOf(_pageIndex));
			variables.setParams(params);
			ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.getProcessForGwgl(variables);
			 //封装需要的数据
	        Map<String, Object> maplist = new HashMap<String, Object>();
	        maplist.put("entity", result.getResult().getData());
	        maplist.put("interfaceId", interfaceId);
	        maplist.put("total",  result.getResult().getTotal());
	        maplist.put("page",  request.getParameter("page"));
	        ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
	        if(list.getStatus()==200) {
	        	list.setMessage("查询成功");
	            if(list.getResult()==null) {
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
     * @api {post} /v4/kss/spdetailorder/saveSpdetailAndDeduct 在押人员申请购物
     * @apiVersion 0.4.0
     * @apiName saveSpdetailAndDeduct
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 在押人员申请购物
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
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *               "rybh":"人员编号(必填; 最大字段长度：21)",
	 *               "sptm":" 物品名称  (必填; 最大字段长度：50)",
	 *               "spsl":"商品数量(必填; 最大字段长度：50)",
	 *               "sj":"售价(必填; 最大字段长度：10)",
	 *               "creator":"管理员(必填; 最大字段长度：50)"
	 *               }]
	 *          }
	 *
     */
    @OpenAPI
    @ApiOperation("在押人员申请购物")
    @PostMapping("/saveSpdetailAndDeduct")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> saveSpdetailAndDeduct(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
    	Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
    	//接口id
    	String interfaceId = "/v4/kss/spdetailorder/saveSpdetailAndDeduct";
        //校验权限
    	try {
	        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
	        if(maps.getStatus()!=200) {
	        	return ResponseMessage.error(maps.getMessage());
	        }
	       //数据类型校验
	    	map.put("interfaceId", interfaceId);
	    	ResponseMessage<String> msg = this.modelYz(map);
	    	if(msg.getStatus()!=200) {
	    		return ResponseMessage.error(msg.getMessage());
	    	}


	    	String rq = DateTimeUtils.format(Calendar.getInstance().getTime(), "yyyyMMddHHmmss");
	    	String dhbh = "kss"+ jsbh + rq;
	    	System.err.println(map.get("entity").toString());
	    	List<SpdetailorderModelDO> splist = JSONArray.parseArray(map.get("entity").toString(), SpdetailorderModelDO.class);
	    	System.err.println("splist--"+JSON.toJSONString(splist));
	    	if(splist.size()==0) {
				return ResponseMessage.error(300,"请先添加购物车");
			}
	    	splist.forEach(s ->{
				s.setState("R2");
				s.setDdbh(dhbh);
				s.setJsbh(jsbh);
				s.setSfje(s.getSj());
				s.setXfje(s.getSj());
				s.setStatus("01");
			});
	    	//流程id
	    	String processDefinitionId = CacheUtils.get().getFlowKey("KSS_GWGL");
	    	ResponseMessage<String> re = kssServerApis.insertByLists(processDefinitionId,splist,jsbh);
	    	if(re.getStatus() == 200){
	    		re.setMessage("保存成功!");
			}else{
				re.setMessage("服务异常,保存失败!");
			}
	    	return re;
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
    }
    
    /**
     * @api {post} /v4/kss/spdetailorder/saveSpdetailByGjqr 管教确认(批量)
     * @apiVersion 0.4.0
     * @apiName saveSpdetailByGjqr
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 管教确认(批量)
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
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *   			 "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "sptm":"商品条码(必填; 最大字段长度：20)",
	 *               "sptmString":"商品条码(必填; 最大字段长度：20)",
	 *               "spsl":"商品数量(必填; 最大字段长度：65)",
	 *               "sfje":"消费金额(必填; 最大字段长度：5)",
	 *               "ddbh":"订单编号(必填; 最大字段长度：36)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "gjqrr":"管教确认人(必填; 最大字段长度：30)",
	 *               "ywlcid":"业务流程id",
	 *               },{
	 *               "ywlcid":"业务流程id",
	 *               "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "sptm":"商品条码(必填; 最大字段长度：20)",
	 *               "sptmString":"商品条码(必填; 最大字段长度：20)",
	 *               "spsl":"商品数量(必填; 最大字段长度：65)",
	 *               "sfje":"消费金额(必填; 最大字段长度：5)",
	 *               "ddbh":"订单编号(必填; 最大字段长度：36)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "gjqrr":"管教确认人(必填; 最大字段长度：30)",
	 *               }]
	 *          }
	 *
     */
    @OpenAPI
    @ApiOperation("管教确认(批量)")
    @PostMapping("/saveSpdetailByGjqr")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> saveSpdetailByGjqr(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
    	Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
    	//接口id
    	String interfaceId = "/v4/kss/spdetailorder/saveSpdetailByGjqr";
        //校验权限
    	try {
	        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
	        if(maps.getStatus()!=200) {
	        	return ResponseMessage.error(maps.getMessage());
	        }
	       //数据类型校验
	    	map.put("interfaceId", interfaceId);
	    	ResponseMessage<String> msg = this.modelYz(map);
	    	if(msg.getStatus()!=200) {
	    		return ResponseMessage.error(msg.getMessage());
	    	}

	    	List<SpdetailorderModelDO> splist = JSON.parseArray(request.getParameter("json"), SpdetailorderModelDO.class);
			splist.forEach(s ->{
				s.setGjqrsj(new Date());
				s.setStatus("03");
				s.setShsj(new Date());
				s.setJsbh(jsbh);
				s.setUpdator(s.getGjqrr());
				System.err.println(s.getTaskid()+"**********");
			});
	    	ResponseMessage<String> re = kssServerApis.spdetailorderUpdateByList(splist,jsbh);
	    	if(re.getStatus() == 200){
	    		re.setMessage("保存成功!");
			}else{
				re.setMessage("服务异常,保存失败!");
			}
	    	return re;
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
    }
    
    
    /**
     * @api {post} /v4/kss/spdetailorder/ShForGwgl 流程审核方法（公共）(批量)
     * @apiVersion 0.4.0
     * @apiName ShForGwgl
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 流程审核方法（公共）(批量)
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
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *   json:{"entity":[{
	 *   			 "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "sptm":"商品条码(必填; 最大字段长度：20)",
	 *               "sptmString":"商品条码(必填; 最大字段长度：20)",
	 *               "spsl":"商品数量(必填; 最大字段长度：65)",
	 *               "sfje":"消费金额(必填; 最大字段长度：5)",
	 *               "ddbh":"订单编号(必填; 最大字段长度：36)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "ywlcid":"业务流程id(必填; 最大字段长度：21)",
	 *               "blr":"办理人(必填; 最大字段长度：30)",
	 *               "shr":"审核人(必填; 最大字段长度：30)",
	 *               "psbz":"批示标志(必填;字典PSBZ)",
	 *               "spyj":"批示意见(必填;最多长度225)",
	 *               "flag":"阶段标志位(必传;1是管教中队审批，flag为2是所领导审批，flag为3是综合中队审批)"
	 *               "blsj":"办理时间(必填; 格式(2019-10-10 10:10:10))",
	 *               },{
	 *   			 "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "sptm":"商品条码(必填; 最大字段长度：20)",
	 *               "sptmString":"商品条码(必填; 最大字段长度：20)",
	 *               "spsl":"商品数量(必填; 最大字段长度：65)",
	 *               "sfje":"消费金额(必填; 最大字段长度：5)",
	 *               "ddbh":"订单编号(必填; 最大字段长度：36)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "ywlcid":"业务流程id(必填; 最大字段长度：21)",
	 *               "blr":"办理人(必填; 最大字段长度：30)",
	 *               "shr":"审核人(必填; 最大字段长度：30)",
	 *               "psbz":"批示标志(必填;字典PSBZ)",
	 *               "spyj":"批示意见(必填;最多长度225)",
	 *               "flag":"阶段标志位(必传;1是管教中队审批，flag为2是所领导审批，flag为3是综合中队审批)"
	 *               "blsj":"办理时间(必填; 格式(2019-10-10 10:10:10))",
	 *               }]
	 *          }
	 *
     */
    @OpenAPI
    @ApiOperation("流程审核方法（公共）(批量)")
    @PostMapping("/ShForGwgl")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> ShForGwgl(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
    	Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
    	//接口id
    	String interfaceId = "/v4/kss/spdetailorder/ShForGwgl";
        //校验权限
    	try {
	        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
	        if(maps.getStatus()!=200) {
	        	return ResponseMessage.error(maps.getMessage());
	        }
	       //数据类型校验
	    	map.put("interfaceId", interfaceId);
	    	ResponseMessage<String> msg = this.modelYz(map);
	    	if(msg.getStatus()!=200) {
	    		return ResponseMessage.error(msg.getMessage());
	    	}
	    	String flag = map.get("flag").toString();
			String blr  =map.get("flag").toString();
			String psbz = map.get("flag").toString();
			String blsj = map.get("flag").toString();
			String spyj = map.get("flag").toString();
	    	List<SpdetailorderModelDO> splist = JSON.parseArray(request.getParameter("json"), SpdetailorderModelDO.class);
			splist.forEach(s ->{
				s.setStatus("03");
				//flag为1是管教中队审批，flag为2是所领导审批，flag为3是综合中队审批
				if("1".equals(flag)) {
					s.setGjzdpsbz(psbz);
					s.setGjzdshsj(new Date());
					s.setGjzdshyj(spyj);
					s.setGjzdblr(blr);
				}else if("2".equals(flag)) {
					s.setShr(blr);
					s.setShsj(new Date());
					s.setShyj("审批通过");
					s.setShzt(psbz);
				}else if("3".equals(flag)){
					s.setZhzdpsbz(psbz);
					s.setZhzdshsj(new Date());
					s.setZhzdshyj("审批通过");
					s.setZhzdblr(blr);
				}
				s.setJsbh(jsbh);
				s.setUpdator(blr);
				System.err.println(s.getTaskid()+"**********");
			});
	    	ResponseMessage<String> re = kssServerApis.UpdateSpdetailForList(flag,splist);
	    	if(re.getStatus() == 200){
	    		re.setMessage("保存成功!");
			}else{
				re.setMessage("服务异常,保存失败!");
			}
	    	return re;
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
    }
    
    
    /**
     * @api {post} /v4/kss/spdetailorder/spLq 商品领取(批量领取同一个人！！！)
     * @apiVersion 0.4.0
     * @apiName spLq
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 商品领取(批量领取同一个人！！！)
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
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample 请求参数:
	 *    appcode:"应用代码（必填）",
	 *    jsbh:"监所编号(必填; 最大字段长度：9)",
	 *    json:{"entity":[{
	 *   			 "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "ywlcid":"业务流程id(必填; 最大字段长度：21)",
	 *               "lqr":"办理人(必填; 最大字段长度：30)",
	 *               "lqsj":"办理时间(必填; 格式(2019-10-10 10:10:10))",
	 *               },{
	 *   			 "rybh":"人员编号(必传;最大字段长度21)",
	 *               "id":"id(必填; 最大字段长度：23)",
	 *               "jsh":" 监室号  (必填; 最大字段长度：6)",
	 *               "xm":"姓名(必填; 最大字段长度：30)",
	 *               "snbh":"所内编号(必填; 最大字段长度：15)",
	 *               "taskid":"流程id(必填; 最大字段长度：15)",
	 *               "ywlcid":"业务流程id(必填; 最大字段长度：21)",
	 *               "lqr":"办理人(必填; 最大字段长度：30)",
	 *               "lqsj":"办理时间(必填; 格式(2019-10-10 10:10:10))",
	 *               }]
	 *          }
	 *
     */
    @OpenAPI
    @ApiOperation("商品领取(批量)")
    @PostMapping("/spLq")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> spLq(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
    	Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
    	//接口id
    	String interfaceId = "/v4/kss/spdetailorder/ShForGwgl";
        //校验权限
    	try {
	        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
	        if(maps.getStatus()!=200) {
	        	return ResponseMessage.error(maps.getMessage());
	        }
	       //数据类型校验
	    	map.put("interfaceId", interfaceId);
	    	ResponseMessage<String> msg = this.modelYz(map);
	    	if(msg.getStatus()!=200) {
	    		return ResponseMessage.error(msg.getMessage());
	    	}
	    	String lqr=map.get("lqr").toString();
			String lqsj=map.get("lqsj").toString();
			String taskid=map.get("taskid").toString();
			String rybh=map.get("rybh").toString();
			String ywlcid=map.get("ywlcid").toString();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String, Object> mapsp=new HashMap<String, Object>();
			List<SpdetailorderModelDO> list=new ArrayList<SpdetailorderModelDO>();
				for(int i=0;i<ywlcid.split(",").length;i++) {
					SpdetailorderModelDO sp=new SpdetailorderModelDO();
					sp.setLqr(lqr);
					sp.setLqsj(sdf.parse(lqsj));
					sp.setYwlcid(ywlcid.split(",")[i]);
					list.add(sp);
				}
				mapsp.put("lqr",lqr);
				mapsp.put("lqsj",lqsj);
				mapsp.put("ywlcid", ywlcid);
				mapsp.put("xm", map.get("xm"));
				mapsp.put("snbh", map.get("snbh"));
				mapsp.put("jsh", map.get("jsh"));
				mapsp.put("taskid", taskid);
				mapsp.put("rybh", rybh);
				mapsp.put("jsbh", jsbh);
				mapsp.put("list", list);
	    	ResponseMessage<String> re = kssServerApis.spLq(map);
	    	if(re.getStatus() == 200){
	    		re.setMessage("保存成功!");
			}else{
				re.setMessage("服务异常,保存失败!");
			}
	    	return re;
    	}catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
    }
}
