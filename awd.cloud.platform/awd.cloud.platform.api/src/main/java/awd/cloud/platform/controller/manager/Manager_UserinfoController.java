package awd.cloud.platform.controller.manager;

import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
@RequestMapping("/v4/manager/userinfo")
@Api(tags = "manager-userinfo", description = "userinfo")
public class Manager_UserinfoController extends PublicService {

    @Autowired
    private ManagerService managerService;

    /**
     * @api {get} /v4/manager/userinfo/userinfoQuery    根据监所编号和登录用户名获取用户信息
     * @apiVersion 0.4.0
     * @apiName userinfoQuery
     * @apiGroup g_manager
     * @apiPermission any
     *
     * @apiDescription 根据监所编号和登录用户名获取用户信息.
     *
     *
     * @apiParam {String} appcode 											应用代码（必填）
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} loginname 										用户登录名(必填)
     *
     *
     * @apiSuccess {String} realname         			                     真实姓名
     * @apiSuccess {String} jh         			                             警号
     * @apiSuccess {String} sjh         			                         联系电话
     * @apiSuccess {String} id         			                             用户id
     * @apiSuccess {String} bm         			                             所属部门
     * @apiSuccess {String} sfzh         			                         身份证号
     * @apiSuccess {String}message         				                     返回信息
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}total         				                     返回数量
     * @apiSuccess {String}date         				                     返回数据
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "sjh": "15689788974",
     *         "sfzh": "1231231231",
     *         "bm": "监管",
     *         "id": "00000000201710250000081",
     *         "jh": "888",
     *         "realname": "管理员真名"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577690805287
     * }
     *
     * @apiExample 请求参数:
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  loginname:"登录名(必填)"
     *
     *
     *
     *
     * @apiUse QueryError
     */
    @OpenAPI
    @ApiOperation("根据监所编号和登录用户名获取用户信息")
    @GetMapping("/userinfoQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> userinfoQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name="loginname") String loginname) {

        String interfaceId = "/v4/manager/userinfo/userinfoQuery";
        String json ="{}";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }


            ResponseMessage<Map<String, Object>> result = managerService.userinfoQuery(jsbh,loginname);
            System.err.println("result" + JSON.toJSONString(result));
            List<Map<String,Object>> lists = new ArrayList<>();
            lists.add(result.getResult());
            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", lists);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().get("total"));
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
