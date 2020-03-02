/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_LshjModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/lshj")
@Api(tags = "jnp-lshj",description = "Lshj")
public class Jnp_LshjController extends PublicService {
	
	@Autowired
    private KssService kssService;
	
	@Autowired
	private KssServerApis kssServerApis;

	/**
	 * @api {get} /v4/jnp/lshj/lshjQuery 律师会见查询
	 * @apiVersion 0.4.0
	 * @apiName lshjQuery
	 * @apiGroup g_jnp
	 * @apiPermission user
	 *
	 * @apiDescription 律师会见查询.
	 *
	 * @apiParam {String} appcode 						应用代码(必传)
	 * @apiParam {String} jsbh 							监所编号(必传)
	 * @apiParam {String} json 							查询参数集
	 *
	 * @apiSuccess {String} lsxb2String         		律师2性别(已转换)
	 * @apiSuccess {String} lsxbString         			律师1性别(已转换)
	 * @apiSuccess {String} lsxb         				律师1性别
	 * @apiSuccess {String} lsxb2         				律师2性别
	 * @apiSuccess {String} hjsj         				会见时间
	 * @apiSuccess {String} xb         					人员性别
	 * @apiSuccess {String} hjsjString         			会见时间(格式化)
	 * @apiSuccess {String} jssjString         			会见结束时间(格式化)
	 * @apiSuccess {String} lsxm         				律师1姓名
	 * @apiSuccess {String} jssj         				会见结束时间
	 * @apiSuccess {String} xbString         			性别(已转换)
	 * @apiSuccess {String} xm         					人员姓名
	 * @apiSuccess {String} rybh         				人员编号
	 * @apiSuccess {String} jsbh         				监所编号
	 * @apiSuccess {String} nl         					人员年龄
	 * @apiSuccess {String} lsxm2         				律师2姓名
	 *
	 * @apiSuccess {String}message         				                     返回信息
	 * @apiSuccess {String}result         				                     返回结果
	 * @apiSuccess {String}total         				                     返回数量
	 * @apiSuccess {String}date         				                     返回数据
	 * @apiSuccess {String}page         				                     返回页数
	 * @apiSuccess {String}status         				                     返回状态
	 * @apiSuccess {String}timestamp         				                 时间戳
	 *
	 * @apiSuccessExample {json} 返回 (成功):
	 * HTTP/1.1 200 OK
	 * {
	 *   "message": "查询成功",
	 *   "result": {
	 *     "total": 1,
	 *     "data": [
	 *       {
	 *         "lsxb2String": "男性",
	 *         "lsxbString": "男性",
	 *         "lsxb": "1",
	 *         "lsxb2": "1",
	 *         "hjsj": 1573468882000,
	 *         "xb": "2",
	 *         "hjsjString": "2019-11-11 18:41:22",
	 *         "jssjString": "2019-11-11 18:42:41",
	 *         "lsxm": "律师姓名1",
	 *         "jssj": 1573468961000,
	 *         "xbString": "女性",
	 *         "xm": "发射点",
	 *         "rybh": "110000111201907120003",
	 *         "jsbh": "110000114",
	 *         "nl": 33,
	 *         "lsxm2": "律师姓名"
	 *       }
	 *     ],
	 *     "page": "1"
	 *   },
	 *   "status": 200,
	 *   "timestamp": 1576483624887
	 * }
	 *
	 * @apiUse QueryError
	 *
	 * @apiExample 请求参数:
	 * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
	 * json:
	 *   {
	 *     "jsh":"监室号(最大长度:4)",
	 *     "rybh":"人员编号(最大长度:21)",
	 *     "page":"当前页数",
	 *     "pageSize":"一页数据数量"
	 *   }
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping("/lshjQuery")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> lshj_query(HttpServletRequest request,@RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
    	String interfaceId = "/v4/jnp/lshj/lshjQuery";
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
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
        	param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
        	param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        System.err.println("param--"+JSON.toJSONString(param));
        
        ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.lshjQuery(param);
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
	
	

	@ApiOperation("新增")
	@PostMapping("lshjSave")
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lshj_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LshjModel data) {
		return kssService.lshj_save(data);
	}
	
	


	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> lshj_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_LshjModel data) {
		return kssService.lshj_updateByKey(id, data);
	}	


	@OpenAPI
	public ResponseMessage<Kss_LshjModel> lshj_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lshj_getByKey(id);
	}
	
	

	@OpenAPI
	public ResponseMessage<Integer> lshj_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.lshj_deleteByKey(id);
	}
}
