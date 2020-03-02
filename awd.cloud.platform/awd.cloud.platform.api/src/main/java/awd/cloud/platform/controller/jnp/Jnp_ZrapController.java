/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_ZrapModel;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/zrap")
@Api(tags = "jnp-zrap", description = "Zrap")
public class Jnp_ZrapController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jnp/zrap/zrapQuery 值日安排分页查询
     * @apiVersion 0.4.0
     * @apiName zrapQuery
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 值日安排分页查询
     *
     * @apiParam {String} appcode 										    应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填；最大字段长度：9)
     * @apiParam {String} json 											    查询参数集(必填)
     *
     *
     * @apiSuccess {String} mtzrxx         				                     每天值日信息
     * @apiSuccess {String} zrxx         				                     值日信息
     * @apiSuccess {String} zrrxm         				                     值日人姓名
     * @apiSuccess {DateTime} zrsjd         				                 值日时间段
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     * @apiUse QueryError
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 7,
     *     "data": {
     *       "mtzrxx": [
     *         {
     *           "zrxx": [
     *             {
     *               "zrrxm": "爱新觉罗冤人",
     *               "zrsjd": "12:00-14:30"
     *             },
     *             {
     *               "zrrxm": "邹嘉02",
     *               "zrsjd": "22:30-00:30"
     *             }
     *           ],
     *           "zrsj": "周一"
     *         }
     *       ],
     *       "jsh": "0101"
     *     },
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576483481615
     * }

     *
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{
     *     "jsh":"监室号(最大字段长度:4)"
     *     "page":"当前页",
     *     "pagesize":"一页数据数量"
     *       }
     *
     *
     */
    @OpenAPI
    @ApiOperation("值日安排分页查询")
    @GetMapping("/zrapQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zrap_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/zrap/zrapQuery";
        String state = request.getParameter("state");
        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }


            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            // if (!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
            //     param.and("user", TermType.eq, maps.getResult().get("user"));
            // }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            //只查询最新七天的值日
            param.setPageIndex(0);
            param.setPageSize(7);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.zrapQueryForPage(param);
            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));
            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            List<Map<String, Object>> data = (List<Map<String, Object>>) list.getResult().get("data");
            System.err.println("data==" + JSON.toJSONString(data));
            try {
                //存放最外层公共信息
                Map<String, Object> publicMap = new HashMap();
                List<Map<String, Object>> publicList = new ArrayList<>();
                //存放一周值日人员信息
                Map<String, Object> mtzrxx = null;
                List<Map<String, Object>> mtzrxxList = new ArrayList<>();
                //存放每天值日人员信息
                Map<String, Object> zrxx = null;
                List<Map<String, Object>> zrxxList = null;
                for (int i = 0; i < data.size(); i++) {
                    zrxxList = new ArrayList<>();
                    mtzrxx = new HashMap();
                    String date = null;
                    String time = null;
                    switch (i) {
                        case 0:
                            date = "周日";
                            break;
                        case 1:
                            date = "周六";
                            break;
                        case 2:
                            date = "周五";
                            break;
                        case 3:
                            date = "周四";
                            break;
                        case 4:
                            date = "周三";
                            break;
                        case 5:
                            date = "周二";
                            break;
                        case 6:
                            date = "周一";
                            break;
                    }
                    for (int k = 0; k < 5; k++) {
                        switch (k) {
                            case 0:
                                time = "12:00-14:30";
                                break;
                            case 1:
                                time = "22:30-00:30";
                                break;
                            case 2:
                                time = "00:30-02:30";
                                break;
                            case 3:
                                time = "02:30-04:30";
                                break;
                            case 4:
                                time = "04:30-06:30";
                                break;
                        }
                        zrxx = new HashMap();
                        zrxx.put("zrsjd", time);
                        zrxx.put("zrrxm", data.get(i).get("zbr" + (k + 1)));
                        zrxxList.add(zrxx);
                    }
                    mtzrxx.put("zrxx", zrxxList);
                    mtzrxx.put("zrsj", date);
                    mtzrxxList.add(mtzrxx);
                }
                publicMap.put("jsh", data.get(0).get("jsh"));
                publicMap.put("mtzrxx", mtzrxxList);
                list.getResult().put("data", publicMap);
            } catch (Exception e) {
                e.printStackTrace();
                list.getResult().put("data", new ArrayList<>());
            }
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



    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zrap_save(@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_ZrapModel data) {
        return null;
    }



    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zrap_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_ZrapModel data) {
        return null;
    }


    @OpenAPI
    public ResponseMessage<Kss_ZrapModel> zrap_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return null;
    }



    @OpenAPI
    public ResponseMessage<Integer> zrap_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return null;
    }
}
