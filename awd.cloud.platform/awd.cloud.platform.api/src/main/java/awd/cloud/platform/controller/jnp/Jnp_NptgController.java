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
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/nptg")
@Api(tags = "jnp-nptg",description = "nptg")
public class Jnp_NptgController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/nptg/tzggQuery 通知公告查询
     * @apiVersion 0.4.0
     * @apiName tzggQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 通知公告查询.
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
     * @apiSuccess {String} tgnr         				通告内容
     * @apiSuccess {String} fbmj         				发布民警
     * @apiSuccess {String} tglx         				通告类型
     * @apiSuccess {String} fbsj         				发布时间
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "tgnr": "通告内容1",
     *         "fbmj": "发布民警1",
     *         "tglx": "通告类型1",
     *         "fbsj": "2019-12-17 15:02:51"
     *       }
     *     ],
     *     "page": 1
     *   },
     *   "status": 200,
     *   "timestamp": 1576307150383
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * {
     *   "appcode": "应用代码(必填)",
     *   "jsbh": "监所编号(必填; 最大长度:9)",
     *   "json": {
     *     "tglx": "通告类型（最大长度：50）",
     *     "tgnr": "通告内容",
     *     "fbmj": "发布民警（最大长度：50）",
     *     "fbsj": "发布时间(例: 2019-12-16)",
     *     "jsh": "监室号（最大长度：4）"
     *   }
     * }
     *
     */
    @OpenAPI
    @ApiOperation("通知公告")
    @GetMapping("/tzggQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> nptg_query(HttpServletRequest request, @RequestParam(name="appcode")String appcode, @RequestParam(name="jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/nptg/tzggQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("tglx"))) {
            param.and("tglx", TermType.eq, maps.getResult().get("tglx"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("tgnr"))) {
            param.and("tgnr", TermType.eq, maps.getResult().get("tgnr"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbmj"))) {
            param.and("fbmj", TermType.eq, maps.getResult().get("fbmj"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbsj"))) {
            param.and("fbsj", TermType.eq, maps.getResult().get("fbsj"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.nptgByjbxxQueryForPage(param);
        System.err.println("result"+ JSON.toJSONString(result));

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
    }
}
