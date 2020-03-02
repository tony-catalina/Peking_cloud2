/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;
import javax.servlet.http.HttpServletRequest;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/dmsz")
@Api(tags = "jnp-dmsz",description = "dmsz")
public class Jnp_DmszController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/dmsz/dmszQuery 点名设置查询
     * @apiVersion 0.4.0
     * @apiName dmszQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 点名设置查询.
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
     * @apiSuccess {String} dmsjd         			    点名时间段
     * @apiSuccess {String} jsh         			    监室号
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
     *         "dmsjd": "2019-12-27 16:43:59 - 2019-12-27 16:44:02",
     *         "jsh": "0114"
     *       }
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576496854065
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "jsh":"监室号(最大长度:4)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("点名设置查询")
    @GetMapping("/dmszQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> dmsz_query(HttpServletRequest request,String appcode,String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/dmsz/dmszQuery";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }

            //查询参数
            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--"+ JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.jsdmQuery(param);
            System.err.println("result"+JSON.toJSONString(result));

            result.getResult().getData().forEach(map -> {
                String kssjStr = map.get("kssj").toString();
                String jssjStr = map.get("jssj").toString();

                String dmsjd = kssjStr + " - " + jssjStr;

                map.put("dmsjd",dmsjd);
            });



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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

}
