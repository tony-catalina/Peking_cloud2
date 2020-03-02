package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JjwpsljlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.ProcessService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
@RequestMapping("/v4/kss/jjwpsl")
@Api(tags = "kss-jjwpsl", description = "jjwpsl")
public class Kss_JjwpslController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private ProcessService processService;

    /**
     * @api {get} /v4/kss/jjwpsl/jjwpslList 救济物品申领查询
     * @apiVersion 0.4.0
     * @apiName jjwpslList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 救济物品申领查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}psbzString          				                批示标志(已转换)
     * @apiSuccess {String}xb          				                        性别
     * @apiSuccess {String}djr          				                    登记人
     * @apiSuccess {String}jjrqString          				                救济日期
     * @apiSuccess {String}psbz          				                    批示标志
     * @apiSuccess {String}xbString          				                性别(已转换)
     * @apiSuccess {String}jjyy          				                    救济原因
     * @apiSuccess {String}djsjString          				                登记时间
     * @apiSuccess {String}snbh          				                    所内编号
     * @apiSuccess {String}xm          				                        姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}wp          				                        物品
     * @apiSuccess {String}ldyj          				                    领导意见
     * @apiSuccess {String}nl          				                        年龄
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}ldxm          				                    领导姓名
     * @apiSuccess {String}ldpssjString          				            领导批示时间
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
     *         "psbzString": "批示通过",
     *         "xb": "1",
     *         "djr": "管理员",
     *         "jjrqString": "2019-08-14 20:15:59",
     *         "psbz": "1",
     *         "xbString": "男性",
     *         "jjyy": "啊实打实",
     *         "djsjString": "2019-08-14 20:15:59",
     *         "snbh": "20190080",
     *         "xm": "现在才",
     *         "rybh": "110000111201907160003",
     *         "wp": "大苏打",
     *         "ldyj": "同意",
     *         "nl": 24,
     *         "jsh": "0201",
     *         "ldxm": "管理员",
     *         "ldpssjString": "2019-08-14 00:00:00"
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
     *   "rybh":"人员编号",
     *   "djsjStart":"登记开始时间(格式:yyyy-MM-dd)",
     *   "djsjEnd":"登记结束时间(格式:yyyy-MM-dd)"
     * }
     *
     */
    @ApiOperation("救济物品申领查询")
    @GetMapping("/jjwpslList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jjwpslList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //rybh,djsjStart,djsjEnd
        String interfaceId = "/v4/kss/jjwpsl/jjwpslList";
        String state = "R2";
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)){
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("djsjStart"))) {
                param.and("djsj", TermType.gte, maps.getResult().get("djsjStart") + " 00:00:00");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("djsjEnd"))) {
                param.and("djsj", TermType.lte, maps.getResult().get("djsjEnd") + " 23:59:59");
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.hqjjwpxxQueryForPage(param);
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

    /**
     * @api {post} /v4/kss/jjwpsl/jjwpslAdd 救济物品申领保存
     * @apiVersion 0.4.0
     * @apiName jjwpslAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 救济物品申领保存
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
     *   "result": "11000011420200119000265",
     *   "status": 200,
     *   "timestamp": null
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[{
     *     "creator":"创建人(必填; 最大长度:30)",
     *     "rybh":"人员编号(必填; 最大长度:21)",
     *     "djr":"登记人(必填; 最大长度:100)",
     *     "wp":"登记人(必填; 格式:物品1*数量,物品2*数量...)",
     *     "jjyy":"登记人(必填)"
     *   }]
     * }
     *
     */
    @ApiOperation("救济物品申领保存")
    @PostMapping("/jjwpslAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<?> jjwpslAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"creator":".{1,30}","rybh":"\\d{1,21}","djr":".{1,100}"}
        String interfaceId = "/v4/kss/jjwpsl/jjwpslAdd";
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

            JjwpsljlModel jjwpsljlModel = JSONArray.parseArray(map.get("entity").toString(), JjwpsljlModel.class).get(0);
            if(StringUtils.isNullOrEmpty(jjwpsljlModel.getJjyy())){
                return ResponseMessage.error("jjyy不可为空");
            }
            if(StringUtils.isNullOrEmpty(jjwpsljlModel.getWp())){
                return ResponseMessage.error("wp不可为空");
            }

            String rybh = jjwpsljlModel.getRybh();

            ResponseMessage<String> processResult = processService.FlowMutex(jsbh, rybh, "KSS_JJWPSL", "JJWPSLJL");
            System.err.println("processResult==" + JSON.toJSONString(processResult));
            if (processResult.getStatus() != 200) {
                return processResult;
            }
            String jbxxJsonObject = CacheUtils.getJbxxByRybh(rybh).toJSONString();
            System.err.println("jbxxJsonObject==" + jbxxJsonObject);

//            JbxxModel jbxxModel = JSONArray.parseArray(jbxxJsonObject.toJSONString(), JbxxModel.class).get(0);
            JbxxModel jbxxModel = JSON.parseObject(jbxxJsonObject,JbxxModel.class);
            jjwpsljlModel.setState("R2");
            jjwpsljlModel.setJsbh(jsbh);
            jjwpsljlModel.setCreatetime(new Date());
            jjwpsljlModel.setDjsj(new Date());
            jjwpsljlModel.setPsbz("0");
            jjwpsljlModel.setPastable("1");

            Map<String,Object> paramMap= Maps.newHashMap();
            paramMap.put("lcid",CacheUtils.get().getFlowKey("KSS_JJWPSL"));
            paramMap.put("jbxxEntity",jbxxModel);
            paramMap.put("jjwpsljlEntity",jjwpsljlModel);

            ResponseMessage<Map<String, String>> result = kssServerApis.jjwpsladdFlow(paramMap);
            System.err.println("result--" + JSON.toJSONString(result));

            ResponseMessage<String> newResult = new ResponseMessage<>();
            if (result.getStatus() == 200) {
                newResult.setMessage("保存成功!");
                newResult.setStatus(200);
                newResult.setResult(result.getResult().get("id"));
                return newResult;
            } else {
                result.setMessage("服务异常,保存失败!");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }
}
