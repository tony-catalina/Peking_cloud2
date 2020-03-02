package awd.cloud.platform.controller.kss;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
@RequestMapping("/v4/kss/wgcl")
@Api(tags = "kss-wgcl",description = "wgcl")
public class Kss_WgclController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/wgcl/wgclList 违规处理查询
     * @apiVersion 0.4.0
     * @apiName wgclList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 违规处理查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}clr          				                    处理人
     * @apiSuccess {String}clqkString          				                处理情况(已转换)
     * @apiSuccess {String}wglx          				                    违规类型
     * @apiSuccess {String}wgqkcon          				                违规情况详情
     * @apiSuccess {String}clqk          				                    处理情况
     * @apiSuccess {String}clzt          				                    处理状态
     * @apiSuccess {String}clsj          				                    处理时间
     * @apiSuccess {String}wgsj          				                    违规时间
     * @apiSuccess {String}dxbh          				                    对象编号
     * @apiSuccess {String}xm          				                        姓名
     * @apiSuccess {String}clztString          				                处理状态(已转换)
     * @apiSuccess {String}wglxString          				                违规类型(已转换)
     * @apiSuccess {String}wgqk          				                    违规情况
     * @apiSuccess {String}jsh          				                    监室号
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 62,
     *     "data": [
     *       {
     *         "clr": "管理员",
     *         "clqkString": "扣减大帐,停止接济",
     *         "wglx": "1",
     *         "wgqkcon": "我",
     *         "clqk": "6012,6011",
     *         "clzt": "1",
     *         "clsj": "2019-12-05 14:27:44",
     *         "wgsj": "2020-01-02 14:21:12",
     *         "dxbh": "110000114201910120010",
     *         "xm": "小行星",
     *         "clztString": "未处理",
     *         "wglxString": "监区违规",
     *         "wgqk": "0011",
     *         "jsh": "1020"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579247687629
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "bfydxbh":"被反映对象编号",
     * }
     *
     */
    @ApiOperation("违规处理查询")
    @PostMapping("/wgclList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> wgclList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/wgcl/wgclList";
        String state = "R2";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("clzt"))) {
                param.and("clzt", TermType.eq, maps.getResult().get("clzt"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("wglx"))) {
                param.and("wglx", TermType.eq, maps.getResult().get("wglx"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dxbh"))) {
                param.and("dxbh", TermType.like, "%" + maps.getResult().get("dxbh") + "%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("wgsj_start"))) {
                param.and("wgsj", TermType.gte, maps.getResult().get("wgsj_start") + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("wgsj_end"))) {
                param.and("wgsj", TermType.lte, maps.getResult().get("wgsj_end") + " 23:59:59");
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.wgglQueryForPage(param);
            System.err.println("result--" + JSON.toJSONString(result));

            result.getResult().getData().forEach(map -> {
                if(map.get("dxbh").toString().length() == 21) {
                    JSONObject obj = CacheUtils.get().getJbxxByRybh(map.get("dxbh").toString());
                    System.err.println("obj==" + JSONUtils.toJSONString(obj));
                    if (!StringUtils.isNullOrEmpty(obj) && !StringUtils.isNullOrEmpty(obj.get("rybh"))) {
                        map.put("xm",obj.get("xm").toString());
                        map.put("jsh",obj.get("jsh").toString());
                    }
                }
            });
            System.err.println("--result--" + JSON.toJSONString(result));

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
