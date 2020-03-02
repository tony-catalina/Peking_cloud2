package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
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
 * Date：2019-12-20 13:10
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jwp_dzsp")
@Api(tags = "jwp-dzsp",description = "dzsp")
public class Jwp_DzspController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private JwpServerApis jwpServerApis;

    @Autowired
    private KssAnalyseApis kssAnalyseApis;


    /**
     * @api {get} /v4/jwp/jwp_dzsp/dzspList 电子水牌查询
     * @apiVersion 0.4.0
     * @apiName dzspList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 电子水牌查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}db         				                         逮捕
     * @apiSuccess {String}es          				                         二审
     * @apiSuccess {String}gabczc1         				                     公安补充侦查1
     * @apiSuccess {String}gabczc2                                           公安补充侦查2
     * @apiSuccess {String}jb                                                禁闭
     * @apiSuccess {String}jds                                                 新收入所
     * @apiSuccess {String}jj                                                 械具
     * @apiSuccess {String}jshj                                                 家属会见
     * @apiSuccess {String}lsfx                                                 留所服刑
     * @apiSuccess {String}lshj                                                律师会见
     * @apiSuccess {String}lsjy                                                临时寄押
     * @apiSuccess {String}lsts                                                离所探视
     * @apiSuccess {String}ptbh                                                普通病号
     * @apiSuccess {String}scqs1                                               审查起诉1
     * @apiSuccess {String}scqs2                                               审查起诉2
     * @apiSuccess {String}scqs3                                               审查起诉3
     * @apiSuccess {String}swjyzl                                              所外就医治疗
     * @apiSuccess {String}swjyzy                                              所外就医住院
     * @apiSuccess {String}ts                                                  提审
     * @apiSuccess {String}wjs                                                 未决
     * @apiSuccess {String}xsjl                                                刑事拘留
     * @apiSuccess {String}yjs                                                 已决待送
     * @apiSuccess {String}ys                                                  一审
     * @apiSuccess {String}Zdbjdx                                              重点帮教对象
     * @apiSuccess {String}zdry                                                重点人员
     * @apiSuccess {String}zs                                                  总数
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 57,
     *     "data": [
     *       {
     *        {
     *         "jj": 41,
     *         "wjs": 54,
     *         "scqs3": 1,
     *         "yjs": 7,
     *         "scqs2": 1,
     *         "xsjl": 18,
     *         "zdry": 165,
     *         "swjyzy": 5,
     *         "gabczc2": 4,
     *         "gabczc1": 5,
     *         "zdbjdx": 5,
     *         "swjyzl": 5,
     *         "ys": 1,
     *         "lsjy": 14,
     *         "lsfx": 7,
     *         "es": 1,
     *         "lsts": 25,
     *         "jds": 0,
     *         "jb": 94,
     *         "zs": 68,
     *         "jshj": 20,
     *         "lshj": 33,
     *         "scqs1": 1,
     *         "db": 24,
     *         "ptbh": 2,
     *         "ts": 6
     *       }
     *       }
     *     ],
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
     *          }

     */
    @OpenAPI
    @ApiOperation("电子水牌查询")
    @GetMapping("/dzspList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> dzsp_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp_dzsp/dzspList";
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
            Map<String, Object> result = kssAnalyseApis.queryLsp(jsbh);
            System.err.println("result" + JSON.toJSONString(result));
            List<Map<String,Object>> resultList = new ArrayList<>();
            resultList.add(result);

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", resultList);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", 1);
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

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