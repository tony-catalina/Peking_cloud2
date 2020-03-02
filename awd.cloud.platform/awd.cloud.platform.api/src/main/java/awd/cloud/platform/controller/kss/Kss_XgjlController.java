package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XgModel;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xgjl")
@Api(tags = "kss-xgjl",description = "xgjl")
public class Kss_XgjlController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/xgjl/xgjlList 巡更记录查询
     * @apiVersion 0.4.0
     * @apiName xgjlList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 巡更记录查询
     *
     * @apiParam {String} appcode 												应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id          				                        id
     * @apiSuccess {String}jsbh          				                        监所编号
     * @apiSuccess {String}jsbhString          				                监所编号（已转换）
     * @apiSuccess {String}xgsj          				                        巡更时间
     * @apiSuccess {String}xgmj       				                            巡更民警
     * @apiSuccess {String}xgddbh          				                    巡更地点编号
     * @apiSuccess {String}bz          				                        备注
     * @apiSuccess {String}state          				                        状态
     * @apiSuccess {String}stateString          				                状态(已转换)
     * @apiSuccess {String}creator          				                    创建人
     * @apiSuccess {String}createtime          				                创建时间
     * @apiSuccess {String}updator          				                    修改人
     * @apiSuccess {String}updatetime          				                修改时间

     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 4,
     *     "data": [
     *       {
     *        	id: "31000011120190513000106"
     *          jsbh: "110000114"
     *          jsbhString: "北京市第一看守所"
     *          xgsj: "2018-06-03 13:15:19"
     *          xgmj: "张三"
     *          xgddbh: "aaa"
     *          bz: "aaa"
     *          state: "R2"
     *          stateString: "有效"
     *          creator: "管理员"
     *          createtime: "2018-01-29 15:44:55"
     *          updator: "管理员"
     *          updatetime: "2018-01-29 16:32:29"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578447454182
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     *    json:{
     *          "xgmj": "巡更民警",
     *          "page": "当前页数",
     *          "rows": "一页数据量",
     *          "sort": "id",
     *          "order": "desc"
     *         }
     *
     */
    // id,jsbh,jsbhString,xgsj,xgmj,xgddbh,bz,creator,createtime,updator,updatetime
    @OpenAPI
    @ApiOperation("巡更记录查询")
    @PostMapping("/xgjlList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> xgjlList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){

        String interfaceId = "/v4/kss/xgjl/xgjlList";
        String state = request.getParameter("state");
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("xgmj"))) {
                param.and("xgmj", TermType.eq, maps.getResult().get("xgmj"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<XgModel>> result = kssServerApis.xgQueryForPage(param);
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
