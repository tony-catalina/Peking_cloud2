package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
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
@RequestMapping("/v4/jnp/spdbk")
@Api(tags = "jnp-spdbk",description = "spdbk")
public class Jnp_SpdbkController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/spdbk/spdbkQuery    视频源信息查询
     * @apiVersion 0.4.0
     * @apiName spdbkQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 获取视频地址信息.
     *
     *
     * @apiParam {String} appcode 											应用代码（必填）
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String} spfl         			                         视频类别
     * @apiSuccess {String} spmc         				                     视频名称
     * @apiSuccess {String} id         				                         视频id
     * @apiSuccess {String} url         				                     视频地址
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "spfl": "娱乐",
     *         "spmc": "小品",
     *         "id": "000001",
     *         "url": "http://192.168.4.103"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576480934091
     * }
     *
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{
     *     "id":"视频id(最大字段长度:23)",
     *     "page":"当前页",
     *     "pagesize":"一页数据数量"
     *       }
     *
     *
     *
     *
     * @apiUse QueryError
     */
    @OpenAPI
    @ApiOperation("获取视频地址信息")
    @GetMapping("/spdbkQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> spdbk_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/spdbk/spdbkQuery";
        String state = request.getParameter("state");
        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }


            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("id"))) {
                param.and("id", TermType.eq, maps.getResult().get("id"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.spdbkQueryForPage(param);
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
