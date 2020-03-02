package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.model.jwp.YyysModel;
import awd.cloud.platform.model.kss.Kss_SpdetailorderModel;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/yyys")
@Api(tags = "jnp-yyys",description = "yyys")
public class Jnp_YyysController extends PublicService{
	
	@Autowired
	private JwpServerApis jwpServerApis;
	

    /**
	 * @api {post} /v4/jnp/yyys/yyysSave 预约用水保存
	 * @apiVersion 0.4.0
	 * @apiName yyysSave
	 * @apiGroup g_jnp
	 * @apiPermission user
	 *
	 * @apiDescription 预约用水保存
	 *
	 * @apiParam {String} appcode 						应用代码（必填）
	 * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
	 * @apiParam {String} json 							保存参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				生成的主键信息
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回（成功）:
	 * HTTP/1.1 200 OK
	 * {
	 *    "message": "保存成功!",
	 *    "result": "11000011420191214000011",
	 *    "status": 200,
	 *    "timestamp": 1576308305534
	 * }
	 *
	 * @apiUse CreateError
	 *
	 * @apiExample Example usage:
	 * appcode:"应用代码(必填)"
	 * jsbh:"监所编号(必填; 最大长度:9)"
	 * json:{
	 *        "entity":[
	 *          {
	 *            "xm":"姓名(必填; 最大长度:30)",
	 *            "jsh":"监室号(必填; 最大长度:4)",
	 *            "yssc":"用水时长(必填; 最大长度:60)",
	 *            "qqsj":"请求用水时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
	 *            "rybh":"人员编号(必填; 最大长度:21)",
	 *            "ysfs":"用水方式(必填; 1：洗澡 2：大便 3：小便 4：洗漱 5：洗衣服)",
	 *            "creator":"创建人(必填; 最大长度:50)"
	 *          }
	 *        ]
	 *      }
	 */
	@ApiOperation("新增")
	@PostMapping("yyysSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> yyys_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
    	//接口id
    	String interfaceId = "/v4/jnp/yyys/yyysSave";
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
	    	List<YyysModel> yyysmodel = JSONArray.parseArray(map.get("entity").toString(), YyysModel.class);
	    	System.err.println("yyysmodel--"+JSON.toJSONString(yyysmodel.get(0)));
	    	yyysmodel.get(0).setCreatetime(new Date());
	    	yyysmodel.get(0).setState("R2");
	    	yyysmodel.get(0).setAppcode(appcode);
			yyysmodel.get(0).setJsbh(jsbh);
	    	YyysModel yyModel = yyysmodel.get(0);
	    	System.err.println("yyysmodel--"+JSON.toJSONString(yyModel));
	    	ResponseMessage<String> yyysModel = jwpServerApis.yyysSave(yyModel);
	    	System.err.println("--"+JSON.toJSONString(yyysModel));
	    	if(yyysModel.getStatus() == 200){
	    		yyysModel.setMessage("保存成功!");
			}else{
				yyysModel.setMessage("服务异常,保存失败!");
			}
	    	return yyysModel;
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseMessage.error("保存失败！");
		}
	}

	/**
	 * @api {get} /v4/jnp/yyys/yyysQuery 预约用水查询
	 * @apiVersion 0.4.0
	 * @apiName yyysQuery
	 * @apiGroup g_jnp
	 * @apiPermission user
	 *
	 * @apiDescription 预约用水查询
	 *
	 * @apiParam {String} appcode 						应用代码(必传)
	 * @apiParam {String} jsbh 							监所编号(必传)
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} message         			成功信息
	 * @apiSuccess {String} result         				数据信息
	 * @apiSuccess {String} total         				数据数量
	 * @apiSuccess {String} data         				数据
	 *
	 * @apiSuccess {String} yssc         				用水时长(秒)
	 * @apiSuccess {String} ysfs         				用水方式(字典值代码)
	 * @apiSuccess {String} ysfsString         		    用水方式(转换值)
	 *
	 * @apiSuccess {String} page         				当前页数
	 * @apiSuccess {String} status         				代码
	 * @apiSuccess {String} timestamp         			时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "yssc": "111",
	 *         "ysfs": "3",
	 *         "ysfsString": "小便"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1578029385151
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
	 * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:
	 *   {
	 *     "page":"当前页数",
	 *     "pageSize":"一页数据数量"
	 *   }
	 */
	@OpenAPI
	@ApiOperation("预约用水查询")
	@GetMapping("/yyysQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> yyys_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
		String interfaceId = "/v4/jnp/yyys/yyysQuery";
		String state = request.getParameter("state");
		//通过校验获取查询参数
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
			if(maps.getStatus()!=200) {
				return ResponseMessage.error(maps.getMessage());
			}

			//查询参数
			QueryParam param = new QueryParam();
			if(!StringUtils.isNullOrEmpty(jsbh)) {
				param.and("jsbh", TermType.eq, jsbh);
			}

			DefaultQueryParam.addDefaultQueryParam(request, param, state);
			System.err.println("param--"+ JSON.toJSONString(param));
			ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.yyysForPage(param);
			System.err.println("result"+JSON.toJSONString(result));

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

}
