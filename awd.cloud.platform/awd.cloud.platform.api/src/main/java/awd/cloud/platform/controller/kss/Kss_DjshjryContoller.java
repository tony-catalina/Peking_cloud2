package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
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
@RequestMapping("/v4/kss/djshjry")
@Api(tags = "kss_djshjry", description = "djshjry")
public class Kss_DjshjryContoller extends PublicService {


    @Autowired
    KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/djshjry/djshjryQuery 待家属会见人员查询
     * @apiVersion 0.4.0
     * @apiName djshjryQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 待家属会见人员查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} snbh                        所内编号
     * @apiSuccess {String} JSBH	                    监所编号
     * @apiSuccess {String} RYBH	                    人员编号
     * @apiSuccess {String} SQR	                        申请人
     * @apiSuccess {String} SQSJ	                    申请时间
     * @apiSuccess {String} lyjsx	                    申请理由
     *
     */
    @OpenAPI
    @ApiOperation("待家属会见人员查询")
    @GetMapping("/djshjryQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> djshjryQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/djshjry/djshjryQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        param.and("jsbh", jsbh);

        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sqsj_start"))) {
            param.and("sqsj", TermType.gte,maps.getResult().get("sqsj_start")+"00:00:00");
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("sqsj_end"))) {
            param.and("sqsj",TermType.lte,maps.getResult().get("sqsj_end")+"23:59:59");
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("mjxm"))) {
            param.and("creator",TermType.eq,maps.getResult().get("mjxm"));
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jslxQueryForPage(param);
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
    }
}
