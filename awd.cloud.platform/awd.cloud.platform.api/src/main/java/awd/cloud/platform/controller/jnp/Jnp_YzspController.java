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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/yzsp")
@Api(tags = "jnp-yzsp",description = "yzsp")
public class Jnp_YzspController   extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/yzsp/yzspQuery 每周菜谱查询
     * @apiVersion 0.4.0
     * @apiName yzspQuery
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 每周菜谱查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												查询参数集(必填)
     *
     * @apiSuccess {String} jsh         				                     监室号
     * @apiSuccess {String} zc         				                         早餐
     * @apiSuccess {String} lc         				                         午餐
     * @apiSuccess {String} wc         				                         晚餐
     * @apiSuccess {String} week         				                     时间
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 7,
     *     "code": "0",
     *     "data": [
     *       {
     *         "week": "星期一",
     *         "lc": "鸡肉",
     *         "zc": "稀饭",
     *         "wc": "饼",
     *         "jsh": "0102"
     *       },
     *       {
     *         "week": "星期二",
     *         "lc": "鸡肉",
     *         "zc": "稀饭",
     *         "wc": "饼",
     *         "jsh": "0102"
     *       },
     *       {
     *         "week": "星期三",
     *         "lc": "2",
     *         "zc": "1",
     *         "wc": "3",
     *         "jsh": "0101"
     *       },
     *       {
     *         "week": "星期四",
     *         "lc": "22",
     *         "zc": "11",
     *         "wc": "33",
     *         "jsh": "0101"
     *       },
     *       {
     *         "week": "星期五",
     *         "lc": "5",
     *         "zc": "4",
     *         "wc": "6",
     *         "jsh": "0101"
     *       },
     *       {
     *         "week": "星期六",
     *         "lc": "55",
     *         "zc": "44",
     *         "wc": "66",
     *         "jsh": "0101"
     *       },
     *       {
     *         "week": "星期日",
     *         "lc": "8",
     *         "zc": "7",
     *         "wc": "9",
     *         "jsh": "0101"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576307277964
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *     jsbh:"监所编号(必填; 最大字段长度：9)",
     *     json:{
     *           "jsh":"监室号(最大字段长度:4)",
     *           "page":"当前页",
     *           "pagesize":"一页数据数量"
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("每周菜谱查询")
    @GetMapping("/yzspQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> yzsp_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/yzsp/yzspQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
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
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.yzspForPage(param);
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
