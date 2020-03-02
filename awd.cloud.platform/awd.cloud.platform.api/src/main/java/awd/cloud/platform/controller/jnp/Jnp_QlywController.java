package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/qlyw")
@Api(tags = "jnp-qlyw",description = "qlyw")
public class Jnp_QlywController extends PublicService{
	
	@Autowired
	private JwpServerApis jwpServerApis;
	
    /**
     * @api {get} /v4/jnp/qlyw/qlywQuery 权力义务查询
     * @apiVersion 0.4.0
     * @apiName qlywQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 权力义务查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												查询参数集(必填)
     *
     * @apiSuccess {String} jsh         			                      	 监室号
     * @apiSuccess {String} qlywnr         			                      	 权利义务内容
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{
     *     "jsh":"监室号(最大字段长度:4)"
     *     "page":"当前页",
     *     "pagesize":"一页数据数量"
     *       }
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 2,
     *     "data": [
     *       {
     *         "qlywnr": "大法师打发撒旦法撒打发斯蒂芬",
     *         "jsh": "0102"
     *       },
     *       {
     *         "qlywnr": "xxxxxxxxxx",
     *         "jsh": "0101"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576479514808
     * }
     * @apiUse QueryError
     */
    @OpenAPI
    @ApiOperation("权力义务查询")
    @GetMapping("/qlywQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> qlyw_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		//接口id
    	String interfaceId = "/v4/jnp/qlyw/qlywQuery";
    	String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
        if(maps.getStatus()!=200) {
        	return ResponseMessage.error(maps.getMessage());
        }
        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(jsbh)) {
        	param.and("jsbh", TermType.eq, jsbh);
        }
        // if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
        // 	param.and("user", TermType.eq, maps.getResult().get("user"));
        // }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
        	param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
        }
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
       ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.queryQlywForPage(param);
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
	}
}
