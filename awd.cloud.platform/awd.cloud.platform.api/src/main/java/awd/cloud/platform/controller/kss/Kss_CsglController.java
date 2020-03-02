package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.DefaultQueryParam;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：张延
 * Date：2020-01-06 20:17
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/csgl")
@Api(tags = "kss-csgl",description = "Csgl")
public class Kss_CsglController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;

    /**
     * @api {get} /v4/kss/csgl/clcsYwdt 出所管理业务动态查询
     * @apiVersion 0.4.0
     * @apiName clcsYwdt
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 出所管理业务动态查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}clcsYwdt          				                 处理处所业务动态
     * @apiSuccess {String}xmsf          				                     刑满释放
     * @apiSuccess {String}qbhs        				                         取保候审
     * @apiSuccess {String}js         				                         假释
     * @apiSuccess {String}qt          				                         其他
     * @apiSuccess {String}casf          				                     撤案释放
     * @apiSuccess {String}zrs          				                     总人数
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *    "data": [
     *       {
     *         "result": {
     *           "clcsYwdt": [
     *             {
     *               "xmsf": 8,
     *               "qt": 147,
     *               "qbhs": 4,
     *               "js": 0,
     *               "zrs": 165,
     *               "casf": 6
     *             }
     *           ]
     *         }
     *         }]
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "starttime":"开始时间(格式:yyyy-MM-dd hh:mm:ss)",
     *          "endtime":"结束时间(格式:yyyy-MM-dd hh:mm:ss)"
     *        }
     *
     */
    @OpenAPI
    @ApiOperation("出所管理业务动态查询")
    @GetMapping("/clcsYwdt")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> clcsYwdt(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        String interfaceId = "/v4/kss/csgl/clcsYwdt";
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
                param.and("jsbh", TermType.eq,jsbh);
            }

            String starttime = (String) maps.getResult().get("starttime");
            String endtime = (String) maps.getResult().get("endtime");

            if (!StringUtils.isNullOrEmpty(starttime)) {
                param.and("starttime", TermType.eq, starttime);
            }
            if (!StringUtils.isNullOrEmpty(endtime)) {
                param.and("endtime", TermType.eq, endtime);
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<Map<String, Object>> result = kssAnalyseApis.clcsYwdt(jsbh,starttime,endtime);

            System.err.println("result" + JSON.toJSONString(result));

            List lists=new ArrayList<>();
            lists.add(result);

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().size());
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
