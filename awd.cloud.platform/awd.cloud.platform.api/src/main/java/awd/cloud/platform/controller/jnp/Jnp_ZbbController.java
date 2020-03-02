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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：张延
 * Date：2019-12-10 15:43
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jnp/zbb")
@Api(tags = "jnp-zbb",description = "zbb")
public class Jnp_ZbbController  extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/zbb/zbbQuery 值班表查询
     * @apiVersion 0.4.0
     * @apiName zbbQuery
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 值班表查询.
     *
     *
     * @apiParam {String} appcode 										    应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填 ; 最大字段长度：9)
     * @apiParam {String} json 												查询参数集(必填)

     * @apiSuccess {String} zbsjd        				                     值班时间段
     * @apiSuccess {String} zbrxm        				                     值班人员姓名
     * @apiSuccess {String} jsh        				                         监室号
     * @apiSuccess {Datetime}zbrq         				                     值班日期
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
     *         "zbsjd": "22:00-23:00",
     *         "zbrxm": "值班人姓名",
     *         "jsh": "0101",
     *         "zbrq": "2019-12-10 00:00:00"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576307843635
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填 ; 最大字段长度：9)",
     *  json:{
     *         "jsh":"监室号(最大字段长度:4)",
     *         "page":"当前页",
     *         "pagesize":"一页数据数量"
     *         }
     *
     */
    @OpenAPI
    @ApiOperation("值班表查询")
    @GetMapping("/zbbQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zbb_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/zbb/zbbQuery";
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.zbbForPage(param);
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

    /**
     * @api {get} /v4/jnp/zbb/zbryxxQuery 值班人员信息查询
     * @apiVersion 0.4.0
     * @apiName zbryxxQuery
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 值班人员信息查询.
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
     * @apiSuccess {String} zbsjd        				值班时间段
     * @apiSuccess {String} rybh        				人员编号
     * @apiSuccess {String} zbrxm        				值班人员姓名
     * @apiSuccess {String} zbrq         				值班日期
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
     *         "zbsjd": "22:00-23:00",
     *         "rybh": "00001",
     *         "zbrxm": "值班人姓名",
     *         "zbrq": "星期二"
     *       }
     *     ],
     *     "page": 1
     *   },
     *   "status": 200,
     *   "timestamp": 1576308374726
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
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     */
    @OpenAPI
    @ApiOperation("值班人员信息查询")
    @GetMapping("/zbryxxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zbryxx_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/zbb/zbryxxQuery";
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
//            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jq"))) {
//                param.and("jq", TermType.eq, maps.getResult().get("jq"));
//            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.zbbForPage(param);
            System.err.println("result" + JSON.toJSONString(result));

            result.getResult().getData().forEach(map -> {
                String dateStr = map.get("zbrq").toString();
                String week = dateToWeek(dateStr);
                map.put("zbrq",week);
            });

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

    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

}

