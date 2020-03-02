package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
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
@RequestMapping("/v4/jnp/spdetailorder")
@Api(tags = "jnp-spdetailorder",description = "spdetailorder")
public class Jnp_SpdetailorderController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;
    /**
     * @api {get} /v4/jnp/spdetailorder/xfjlQuery 消费记录查询
     * @apiVersion 0.4.0
     * @apiName xfjlQuery
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 消费记录查询
     *
     * @apiParam {String} appcode 						应用代码(必传)
     * @apiParam {String} jsbh 							监所编号(必传)
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} xfje 						总价
     * @apiSuccess {String} xbString 					性别(已转换)
     * @apiSuccess {String} lsj 						零售价
     * @apiSuccess {String} ddcjsjString 				订单时间
     * @apiSuccess {String} spsl 						商品数量
     * @apiSuccess {String} xm 						    姓名
     * @apiSuccess {String} spmc 						商品名称
     * @apiSuccess {String} xb 						    性别
     * @apiSuccess {String} je 						    用户余额
     * @apiSuccess {String} nl 						    年龄
     *
     * @apiSuccess {String}message         				返回信息
     * @apiSuccess {String}result         				返回结果
     * @apiSuccess {String}total         				返回数量
     * @apiSuccess {String}date         				返回数据
     * @apiSuccess {String}page         				返回页数
     * @apiSuccess {String}status         				返回状态
     * @apiSuccess {String}timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "xfje": 20,
     *         "xbString": "男性",
     *         "lsj": "1.0",
     *         "ddcjsjString": "2019-09-06 18:33:36",
     *         "spsl": 1,
     *         "xm": "测试11号",
     *         "spmc": "手套",
     *         "xb": "1",
     *         "je": 5,
     *         "nl": 23
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576487845360
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
    @ApiOperation("消费记录查询")
    @GetMapping("/xfjlQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xfjl_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/spdetailorder/xfjlQuery";
        String state = request.getParameter("state");
        try{
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
//			if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//				param.and("user", TermType.eq, maps.getResult().get("user"));
//			}
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            param.and("jbxx_state",TermType.eq,"R8");
            param.and("status", TermType.eq, "08");
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.spdetailorderByjbxxQueryForPage(param);
            System.err.println("result--------------------"+ JSON.toJSONString(result));

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
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("查询失败");
        }
    }
}
