package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.ZrapModel;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/v4/jwp/jwp_zrap")
@RefreshScope
@Api(tags = "jwp-zrap", description = "zrap")
public class Jwp_ZrapController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp/jwp_zrap/zrapList 值日安排查询
     * @apiVersion 0.4.0
     * @apiName zrapList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 值日安排查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     *
     * @apiSuccess {String}createtime         				                 创建时间
     * @apiSuccess {String}creator                                           创建人
     * @apiSuccess {String}week         				                     星期
     * @apiSuccess {String}apsj                                              安排时间
     * @apiSuccess {String}stateString                                       状态(已转换)
     * @apiSuccess {String}zbr2                                              值班人2
     * @apiSuccess {String}zbr3                                              值班人3
     * @apiSuccess {String}zbr4                                              值班人4
     * @apiSuccess {String}zbr5                                              值班人5
     * @apiSuccess {String}bz                                                备注
     * @apiSuccess {String}updator                                           更新人
     * @apiSuccess {String}id                                                id
     * @apiSuccess {String}state                                             状态
     * @apiSuccess {String}jsbhString                                        监所名称
     * @apiSuccess {String}updatetime                                        更新时间
     * @apiSuccess {String}jsh                                               值班人1
     * @apiSuccess {String}jsbh                                              值班人1
     * @apiSuccess {String}zbr1                                              值班人1
     *
     * @apiSuccess {String}page                                              当前页
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "createtime": "2019-11-18 09:50:22",
     *         "creator": "管理员",
     *         "week": "1",
     *         "apsj": "2019-11-17",
     *         "stateString": "有效",
     *         "zbr2": "邹嘉02",
     *         "zbr3": "张威",
     *         "zbr4": "张威",
     *         "zbr5": "救济",
     *         "bz": "null",
     *         "updator": "null",
     *         "id": "11000011420191118000724",
     *         "state": "R2",
     *         "jsbhString": "北京市第一看守所",
     *         "updatetime": "null",
     *         "jsh": "0101",
     *         "jsbh": "110000114",
     *         "zbr1": "爱新觉罗冤人"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577072293631
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "jsh":" 监室号  (最大字段长度：4)",
     *          "apsj":"安排时间(格式:yyyy-MM-dd HH:mm:ss)"
     *          "type":"type",
     *          "state":"状态(最大字段长度：2; 字典:R3.删除  R7.历史  R8.在押)",
     *          "page":"当前页数",
     *          "pageSize":"一页数据数量"
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("值日安排查询")
    @GetMapping("/zrapList")
    @HystrixCommand
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zrap_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/jwp/jwp_zrap/zrapList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            ZrapModel paramModel = new ZrapModel();
            //查询参数
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                paramModel.setJsbh(jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                paramModel.setJsh((String) maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("apsj"))) {
                String apsj = (String) maps.getResult().get("apsj");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = format.parse(apsj);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                paramModel.setApsj(date);
            }

            System.err.println("param--" + JSON.toJSONString(paramModel));

            ResponseMessage<List<Map<String, Object>>> result = kssServerApis.findZrap(paramModel);
            System.err.println(result);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().size());
            maplist.put("page", request.getParameter("page"));
            ResponseMessage<Map<String, Object>> resultList = this.kfzdShow(maplist);
            if (resultList.getStatus() == 200) {
                resultList.setMessage("查询成功");
                if (resultList.getResult() == null) {
                    resultList.setMessage("未查询数据");
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }
}
