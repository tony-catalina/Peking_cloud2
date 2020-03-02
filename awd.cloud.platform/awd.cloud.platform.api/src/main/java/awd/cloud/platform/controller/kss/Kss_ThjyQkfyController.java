package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.QkfyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/qkfy")
@Api(tags = "kss-qkfy",description = "qkfy")
public class Kss_ThjyQkfyController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {post} /v4/kss/qkfy/qkfyAdd 情况反映保存
     * @apiVersion 0.4.0
     * @apiName qkfyAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 情况反映保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420200116000078",
     *   "status": 200,
     *   "timestamp": 1579151327950
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[{
     *      "creator":"创建人(必填; 最大长度:50)",
     *      "jsh":"监室号(必填; 最大长度:4)",
     *      "fyr":"反映人(必填; 最大长度:30)",
     *      "bfydxbh":"被反映对象编号(必填; 最大长度:21)",
     *      "fyrbh":"反映人编号(最大长度:21)",
     *      "xxwgqk":"详细违规情况(最大长度:200)"
     *   }]
     * }
     *
     */
    @ApiOperation("情况反映保存")
    @PostMapping("/qkfyAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> qkfyAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"creator":".{1,50}","jsh":"\\d{1,4}","fyr":".{1,30}","fyrbh":"\\d{0,21}","xxwgqk":".{0,200}","bfydxbh":"\\d{1,21}"}
        //接口id
        String interfaceId = "/v4/kss/qkfy/qkfyAdd";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            QkfyModel model = JSONArray.parseArray(map.get("entity").toString(), QkfyModel.class).get(0);
            model.setJsbh(jsbh);
            model.setState("R2");
            model.setCreatetime(new Date());
            model.setFysj(new Date());
            if (StringUtils.isNullOrEmpty(model.getWgqk())) {
                model.setWgqk("");
            }
            model.setZscs("0");
            model.setFdcs("0");
            model.setYxzt("0");
            System.err.println(JSONObject.toJSON(model));
            ResponseMessage<String> result = kssServerApis.qkfySave(model);
            System.err.println("result--" + JSON.toJSONString(result));

            if (result.getStatus() == 200) {
                result.setMessage("保存成功!");
            } else {
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/kss/qkfy/qkfyList 情况反映查询
     * @apiVersion 0.4.0
     * @apiName qkfyList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 情况反映查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}xxwgqk          				                    详细违规情况
     * @apiSuccess {String}fyrbh          				                    反映人编号
     * @apiSuccess {String}bfydxbh          				                被反映对象编号
     * @apiSuccess {String}fysj          				                    反映时间
     * @apiSuccess {String}bfydx          				                    被反映对象姓名
     * @apiSuccess {String}fyr          				                    反映人姓名
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
     *     "total": 1,
     *     "data": [
     *       {
     *         "xxwgqk": "详细违规情况",
     *         "fyrbh": "110000114201911070002",
     *         "bfydxbh": "110000114201911070002",
     *         "fysj": "2019-11-08 11:10:49",
     *         "bfydx": "被反映对象\t",
     *         "fyr": "璐璐"
     *       }
     *     ],
     *     "page": 1
     *   },
     *   "status": 200,
     *   "timestamp": 1579163979499
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
    @ApiOperation("情况反映查询")
    @GetMapping("/qkfyList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> qkfyList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/qkfy/qkfyList";
        String state = "R2";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bfydxbh"))) {
                param.and("bfydxbh", TermType.eq, maps.getResult().get("bfydxbh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.qkfyQueryForPage(param);
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
