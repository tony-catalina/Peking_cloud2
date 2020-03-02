package awd.cloud.platform.controller.kss;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.bj.kss.model.XjzcModel;
import awd.cloud.platform.api.KssServerApis;
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
@RequestMapping("/v4/kss/xjzc")
@Api(tags = "kss-xjzc",description = "xjzc")
public class Kss_XjzcController extends PublicService{

	@Autowired
    private KssServerApis kssServerApis;
	
	
	/**
     * @api {get} /v4/kss/xjzc/xjzcQuery 现金支出查询
     * @apiVersion 0.4.0
     * @apiName xjzcQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 现金支出查询.
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
     *    json:{"entity":[{
     *               "rybh":"人员编号(必填; 最大字段长度：21)",
     *               "xflx":" 消费类型 (非必填; 最大字段长度：2)",
     *               }]
     *          }
     *
     */
	@OpenAPI
    @ApiOperation("现金支出查询")
    @GetMapping("/xjzcQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xjzc_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {
		
		//接口id
        String interfaceId = "/v4/kss/xjzc/xjzcQuery";
        String state = request.getParameter("state");
		try {
			ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            	param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xflx"))) {
            	param.and("xflx", TermType.eq, maps.getResult().get("xflx"));
            }
            param.and("jsbh",TermType.eq,jsbh);
            ResponseMessage<PagerResult<XjzcModel>> result = kssServerApis.xjzcQueryForPage(param);
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
