package awd.cloud.platform.controller.mjjk;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.ManagerService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/mjjk/role")
@Api(tags = "mjjk-role",description = "role")
public class Mjjk_RoleController extends PublicService{
    @Autowired
    private ManagerService managerService;

    /**
     * @api {get} /v4/mjjk/role/roleList 用户权限信息查询
     * @apiVersion 0.4.0
     * @apiName roleList
     * @apiGroup g_mjjk
     * @apiPermission any
     * @apiDescription 用户权限信息查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id         				                         id
     * @apiSuccess {String}name         				                     角色名称
     * @apiSuccess {String}code                                              角色编号
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
     *     "total": 3,
     *     "data": [
     *        {
     *         "code": "11",
     *         "name": "收押",
     *         "id": "99999999920171201000001"
     *       },
     *       {
     *         "code": "13",
     *         "name": "管教",
     *         "id": "99999999920171201000002"
     *       },
     *       {
     *         "code": "14",
     *         "name": "医生",
     *         "id": "99999999920171201000003"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577082191258
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *            "id":"id（是否必填：否）",
     *            "name":"名称（是否必填：否）",
     *            "code":"编号（是否必填：否）"
     *            "page":"当前页（是否必填：否）",
     *            "pageSize":"一页数据量（是否必填：否）"
     * }
     */

    @OpenAPI
    @ApiOperation("用户权限信息查询")
    @GetMapping("/roleList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> roleList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/mjjk/role/roleList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            System.err.println("------------------------"+appcode.toUpperCase());
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            Map<String, Object> jsonMap = maps.getResult();
            // 领取状态(WPLQZT)
            //查询参数
            QueryParam param = new QueryParam();
            param.and("jslx",TermType.eq,jsbh.substring(7,8));
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            for (String s:jsonMap.keySet()){
                if ("page".equals(s)){
                    param.setPageIndex(Integer.parseInt(jsonMap.get(s).toString())-1);
                }else if ("pageSize".equals(s)){
                    param.setPageSize(Integer.parseInt(jsonMap.get(s).toString()));
                }else{
                    param.and(s,TermType.eq,jsonMap.get(s));
                }
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = managerService.roleQuery(param);
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
