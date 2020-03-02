package awd.cloud.platform.controller.kss;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.XjjsModelDO;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xjjs")
@Api(tags = "kss-xjjs",description = "xjjs")
public class Kss_XjjsController extends PublicService{
	
	@Autowired
    private KssServerApis kssServerApis;
	
	/**
     * @api {get} /v4/kss/xjjs/xjjsQuery 现金接收查询
     * @apiVersion 0.4.0
     * @apiName xjjsQuery
     * @apiGroup g_kss
     * @apiPermission any
     *
     * @apiDescription 现金接收查询.
     * 
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} currencyString         	 币种(转换值)
     * @apiSuccess {String} currency         		     币种
     * @apiSuccess {String} rybh         			    人员编号
     * @apiSuccess {String} jsbh                      监所编号
     * @apiSuccess {String} jsje         			   接收金额
     * @apiSuccess {String} jszjh         			    家属证件号
     * @apiSuccess {String} blrq         			     办理日期
     * @apiSuccess {String} zy         			                摘要
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "currencyString": "人民币",
     *         "rybh": "110000111201907120002",
     *         "jsbh": "110000114",
     *         "jsje": "2020",
     *         "jszjh": "321320199107027516",
     *         "blrq": "2019-12-12 15:51:52",
     *         "blr": "管理员",
     *         "zy": "摘要",
     *         "currency": 1
     *       }
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576496854065
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "jsh":"监室号(最大长度:4)",
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
	@OpenAPI
    @ApiOperation("现金接受查询")
    @GetMapping("/xjjsQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<Map<String, Object>> xjjs_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		
		System.err.println("张江南");
		//接口id
        String interfaceId = "/v4/kss/xjjs/xjjsQuery";
        String state = "R2";
        //通过校验获取查询参数
         ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }
        
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsbh"))) {
            param.and("jsbh", TermType.eq, maps.getResult().get("jsbh"));
        }
        String rybh=maps.getResult().get("rybh").toString();
        if(!StringUtils.isNullOrEmpty(rybh)) {
        	param.and("rybh",TermType.eq,rybh);
        }
        String blrq_start =(String) maps.getResult().get("blrq_start");
		if(!StringUtils.isNullOrEmpty(blrq_start)) {
			param.and("blrq", TermType.lte, blrq_start);
    	}
		String blrq_end = maps.getResult().get("blrq_end").toString();
		if(!StringUtils.isNullOrEmpty(blrq_end)) {
			param.and("blrq", TermType.gte, blrq_end);
    	}
    	String srlx = maps.getResult().get("srlx").toString();
    	if(!StringUtils.isNullOrEmpty(srlx)) {
    		param.and("srlx", TermType.eq, srlx);
    	}
    	DefaultQueryParam.addDefaultQueryParam(request, param, state);
    	ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.xjjsQueryJbxxForPage(param);
    	
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

	@ApiOperation("新增")
    @PostMapping("/xjjsSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
	public ResponseMessage<String> xjjs_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
		 //接口id
        String interfaceId = "/v4/kss/xjjs/xjjsSave";
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
            String jsxm = maps.getResult().get("jsxm").toString();
    		String gx = maps.getResult().get("gx").toString();
    		String dh = maps.getResult().get("dh").toString();
    		String dz = request.getParameter("dz");
    		String jszjh = request.getParameter("jszjh");
            
            
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}
