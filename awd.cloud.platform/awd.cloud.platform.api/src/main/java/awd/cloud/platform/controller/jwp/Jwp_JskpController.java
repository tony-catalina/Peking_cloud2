package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.ZrapModel;
import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
import awd.cloud.platform.model.jwp.JskpModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.cloud.platform.utils.TermType;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v4/jwp/jwp_jskp")
@RefreshScope
@Api(tags = "jwp-jskp", description = "jskp")
public class Jwp_JskpController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jwp/jwp_jskp/jskpList 监室考评查询
     * @apiVersion 0.4.0
     * @apiName jskpList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 监室考评查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     *
     * @apiSuccess {String}createtime         				                 创建时间
     * @apiSuccess {String}djmj                                              登记民警
     * @apiSuccess {String}ly                                                理由
     * @apiSuccess {String}creator                                           创建人
     * @apiSuccess {String}djsj                                              登记时间
     * @apiSuccess {String}fz                                                分值
     * @apiSuccess {String}bz                                                备注
     * @apiSuccess {String}updator                                           更新人
     * @apiSuccess {String}appcode                                           appcode
     * @apiSuccess {String}state                                             状态
     * @apiSuccess {String}updatetime                                        更新时间
     * @apiSuccess {String}jsbh                                              监所编号
     * @apiSuccess {String}jsh                                               监室号
     *
     * @apiSuccess {String}page                                              当前页
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "createtime": "2019-11-19 15:35:29",
     *         "djmj": "登记民警2",
     *         "ly": "afas",
     *         "creator": "null",
     *         "djsj": "2019-11-19 15:33:26",
     *         "fz": "56",
     *         "bz": "备注",
     *         "updator": "管理员",
     *         "appcode": "null",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 14:49:25",
     *         "jsbh": "110000114",
     *         "jsh": "0101"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577071819625
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "jsh":" 监室号  (最大字段长度：4)",
     *          "djsj":" 登记时间  (最大字段长度：4)",
     *          "page":"当前页数",
     *          "pageSize":"一页数据数量"
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("监室考评查询")
    @GetMapping("/jskpList")
    @HystrixCommand
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jskp_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp_jskp/jskpList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam qParam = new QueryParam();
            //查询参数
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                qParam.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                qParam.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("djsjStart"))) {
                qParam.and("djsj", TermType.gte, maps.getResult().get("djsjStart"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("djsjEnd"))) {
                qParam.and("djsj", TermType.lte, maps.getResult().get("djsjEnd"));
            }

            System.err.println("param--" + JSON.toJSONString(qParam));

            ResponseMessage<PagerResult<Map<String, Object>>> result = jwpServerApis.getJskp(qParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));
            ResponseMessage<Map<String, Object>> resultList = this.kfzdShow(maplist);
            if (resultList.getStatus() == 200) {
                resultList.setMessage("查询成功");
                if (resultList.getResult() == null) {
                    resultList.setMessage("未查询数据");
                }
            }
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {post} /v4/jwp/jwp_jskp/jskpSave 监视考评添加
     * @apiVersion 0.4.0
     * @apiName jskpSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 监视考评保存.
     *

     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[
     *                {
     *                  "jsh":"监室号(必填; 最大长度:4)",
     *                  "fz":"分值(必填;最大长度:10)",
     *                  "ly":"理由(必填;)",
     *                  "djmj":"登记民警(必填; 最大长度:50)",
     *                  "djsj":"登记时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                  "bz":"备注(必填)"
     *                }
     *              ]
     *     }
     */
    @ApiOperation("监视考评保存")
    @PostMapping("/jskpSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jskpSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/jwp_jskp/jskpSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());
            List<JskpModel> jskpmodel = JSONArray.parseArray(map.get("entity").toString(), JskpModel.class);
            System.err.println("jskpmodel--"+ JSON.toJSONString(jskpmodel.get(0)));
            jskpmodel.get(0).setCreatetime(new Date());
            jskpmodel.get(0).setState("R2");
            jskpmodel.get(0).setAppcode(appcode);
            jskpmodel.get(0).setJsbh(jsbh);
            jskpmodel.get(0).setDjmj("管理员");
            jskpmodel.get(0).setCreator("管理员");
            JskpModel jsModel = jskpmodel.get(0);
            System.err.println("jskpmodel--"+JSON.toJSONString(jsModel));
            ResponseMessage<String> jskpModel = jwpServerApis.saveJskp(jsModel);
            if(jskpModel.getStatus() == 200){
                jskpModel.setMessage("保存成功!");
            }else{
                jskpModel.setMessage("服务异常,保存失败!");
            }
            return jskpModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }




}
