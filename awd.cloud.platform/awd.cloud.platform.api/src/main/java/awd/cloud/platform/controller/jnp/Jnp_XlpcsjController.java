package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/xlpcsj")
@Api(tags = "jnp-xlpcsj", description = "xlpcsj")
public class Jnp_XlpcsjController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/xlpcsj/xlpcsjQuery 测评试卷题目查询
     * @apiVersion 0.4.0
     * @apiName xlpcsjQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 测评试卷题目查询
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
     * @apiSuccess {String} sjsm         				试卷说明
     * @apiSuccess {String} sjtm         				试卷题目(同一个试卷id下有多个题目)
     * @apiSuccess {String} kxx         				可选项
     * @apiSuccess {String} tmnr         				题目内容
     * @apiSuccess {String} tmid         				题目id
     * @apiSuccess {String} sjid         				试卷Id
     * @apiSuccess {String} da         				    测评题得分对应结果
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     * 	"message": "查询成功!",
     * 	"result": {
     * 		"total": 10,
     * 		"data": [{
     *         "sjsm": "第一套试卷",
     *         "sjtm": [
     *           {
     *             "tmid": "11000011420200108000066",
     *             "kxx": [
     *               "A、火车",
     *               "B、自行车",
     *               "C、汽车",
     *               "D、飞机"
     *             ],
     *             "tmnr": "你出行时喜欢坐什么交通工具？"
     *           },
     *           {
     *             "tmid": "11000011420200108000069",
     *             "kxx": [
     *               "A、打火机",
     *               "B、口红",
     *               "C、记事本"
     *             ],
     *             "tmnr": "以下哪个是你身边必带的物品？"
     *           },
     *           {
     *             "tmid": "11000011420200109000092",
     *             "kxx": [
     *               "A、周树人",
     *               "B、鲁大师",
     *               "C、鲁班7号"
     *             ],
     *             "tmnr": "鲁迅原名是?"
     *           },
     *           {
     *             "tmid": "11000011420200109000093",
     *             "kxx": [
     *               "A、不知道",
     *               "B、xxx"
     *             ],
     *             "tmnr": "中国有多少个省"
     *           },
     *           {
     *             "tmid": "11000011420200114000098",
     *             "kxx": [
     *               "A、紫",
     *               "B、黑",
     *               "C、蓝",
     *               "D、白"
     *             ],
     *             "tmnr": "以下颜色喜欢哪个"
     *           },
     *           {
     *             "tmid": "11000011420200115000104",
     *             "kxx": [
     *               "A、1",
     *               "B、2",
     *               "C、3"
     *             ],
     *             "tmnr": "狗肉"
     *           },
     *           {
     *             "tmid": "11000011420200115000105",
     *             "kxx": [
     *               "A、1",
     *               "B、1"
     *             ],
     *             "tmnr": "羊肉"
     *           }
     *         ],
     *         "sjid": "202001141701",
     *         "da": "600才能及格呦"
     *       }],
     * 		"page": "1"
     *    },
     * 	"status": 200,
     * 	"timestamp": 1579230139998
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * {
     *   "appcode": "应用代码(必填)",
     *   "jsbh": "监所编号(必填; 最大长度:9)",
     *   "json": {
     *     "sjid": "试卷id"
     *   }
     * }
     *
     */
    @OpenAPI
    @ApiOperation("测评试卷获取接口")
    @GetMapping("/xlpcsjQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xlpctm_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/xlpcsj/xlpcsjQuery";
        String state = "R2";
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        QueryParam param = new QueryParam();
//        if(!StringUtils.isNullOrEmpty(maps.getResult().get("id"))) {
//            param.and("id", TermType.eq, maps.getResult().get("id"));
//        }
        if (!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("sjid"))) {
            param.and("sjid", TermType.eq, maps.getResult().get("sjid"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.xlpcsjQuery(param);
        result.getResult().getData().forEach(map -> {
            String tmids = map.get("sjtm").toString();
            QueryParam tmParam = new QueryParam();
            tmParam.and("id", TermType.in, tmids);
            ResponseMessage<PagerResult<Map<String, Object>>> tmResult = jwpServerApis.xlpctmQuery(tmParam);

            //处理题目数据
            List<Map<String,Object>> tmList = new ArrayList<>();
            tmResult.getResult().getData().forEach(tmMap -> {
                String tmnr = tmMap.get("tmnr").toString();
                String[] kxxs = tmMap.get("kxx").toString().split(",");

                List kssList = Arrays.asList(kxxs);
                Map<String, Object> m = new HashMap<>();
                m.put("tmnr",tmnr);
                m.put("kxx",kssList);
                m.put("tmid",tmMap.get("id").toString());
                tmList.add(m);
            });
            map.put("sjtm",tmList);
        });
        System.err.println("result" + JSON.toJSONString(result));

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", result.getResult().getData());
        maplist.put("interfaceId", interfaceId);
        maplist.put("total", result.getResult().getTotal());
        maplist.put("page", request.getParameter("page"));
        ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
        System.err.println("list" + JSON.toJSONString(list));
        if (list.getStatus() == 200) {
            list.setMessage("查询成功");
            if (list.getResult() == null) {
                list.setMessage("未查询数据");
            }
        }
        return list;
    }
}
