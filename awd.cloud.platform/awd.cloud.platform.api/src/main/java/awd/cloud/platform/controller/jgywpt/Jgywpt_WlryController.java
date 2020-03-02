package awd.cloud.platform.controller.jgywpt;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jgywpt/wlry")
@Api(tags = "jgywpt-wlry", description = "wlry")
public class Jgywpt_WlryController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jgywpt/wlry/fkxxList 访客信息查询
     * @apiVersion 0.4.0
     * @apiName fkxxList
     * @apiGroup g_jgywpt
     * @apiPermission any
     * @apiDescription 访客信息查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集(必填)
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     *
     * @apiSuccess {String}id         				                         id
     * @apiSuccess {String}lris_url2         				                 民警左眼虹膜
     * @apiSuccess {String}dw         				                         访客单位
     * @apiSuccess {String}lris_url1         				                 民警右眼虹膜
     * @apiSuccess {String}xm                                                访客姓名
     * @apiSuccess {String}zjh                                               访客证件号
     * @apiSuccess {String}drsj                                              访问时段-开始时间
     * @apiSuccess {String}dcsj                                              访问时段-结束时间
     *
     * @apiSuccess {String}page                                              当前页
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 2,
     *     "data": [
     *        {
     *         "lris_url2": "null",
     *         "dw": "北京市第一看守所",
     *         "lris_url1": "null",
     *         "xm": "张三",
     *         "dcsj": "2019-12-23 15:34:24",
     *         "zjh": "110101199003073335",
     *         "drsj": "2019-09-05 19:19:46",
     *         "id": "11000011420190905000026"
     *       },
     *       {
     *         "lris_url2": "null",
     *         "dw": "北京市第一看守所",
     *         "lris_url1": "null",
     *         "xm": "张三",
     *         "dcsj": "2019-12-23 15:34:24",
     *         "zjh": "110101199003073335",
     *         "drsj": "2019-09-05 18:02:06",
     *         "id": "11000011420190905000027"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577082191258
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "id":"0000",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */
    @OpenAPI
    @ApiOperation("访客信息查询")
    @GetMapping("/fkxxList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> mjxxList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jgywpt/wlry/fkxxList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            // 领取状态(WPLQZT)
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("id"))) {
                param.and("id", TermType.eq, maps.getResult().get("id"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wlryQuery(param);
            for (Map<String, Object> resultMap : result.getResult().getData()) {
                String str = (String) resultMap.get("wljbxx");
                if (str != null && !"".equals(str)) {
                    str.replace("\\", "");
                    List<Map> list = JSONObject.parseArray(str, Map.class);
                    resultMap.put("xm", list.get(0).get("xm"));
                    resultMap.put("zjh", list.get(0).get("zjh"));
                    resultMap.put("dw", list.get(0).get("dw"));
                }
            }
            System.err.println("result" + JSON.toJSONString(result));
            System.err.println("result123" + result.toString());

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
