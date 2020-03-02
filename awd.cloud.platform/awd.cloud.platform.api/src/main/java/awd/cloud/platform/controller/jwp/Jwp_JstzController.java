package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JstzModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_JstzModal;
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
import java.util.List;
import java.util.Map;

/**
 * Author：张延
 * Date：2019-12-20 13:10
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jstz")
@Api(tags = "jwp-jstz",description = "jstz")
public class Jwp_JstzController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/jwp/jstz/jstzSave 监室调整保存
     * @apiVersion 0.4.0
     * @apiName jstzSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 监室调整保存.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

     *
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{"entity":[{
     *               "xjsh":"现监室(必填;最大字段长度：4)",
     *               "yjsh":"原监室(必填;最大字段长度：4)",
     *               "yy":"原因 (必填; 字典：TJYY)",
     *               "rybh":" 人员编号(必填; 最大字段长度：21)",
     *               "tzsj":"调整时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "creator":"扫虹膜的民警(必填; 最大字段长度：30)",
     *               "xb":"性别(必填;字典：XB)"
     *               "xm":"姓名(必填;最大字段长度：50)",
     *               "snbh":"所内编号(最大字段长度：8)",
     *               "tjr":"调监人"
     *               }]
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("监室调整保存")
    @PostMapping("/jstzSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, String>> jstzSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jstz/jstzSave";


        //通过校验获取查询参数
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

            List<JbxxModel> jbxxList = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class);
            List<JstzModel> jstzList = JSONArray.parseArray(map.get("entity").toString(), JstzModel.class);


            jbxxList.get(0).setJsbh(jsbh);
            jstzList.get(0).setJsbh(jsbh);
            jstzList.get(0).setCreatetime(new Date());
            jbxxList.get(0).setState("R2");
            jstzList.get(0).setState("R2");

            JbxxModel jbxxEntity= jbxxList.get(0);
            JstzModel jstzEntity= jstzList.get(0);

            Kss_JstzModal  kss_jstzsModal=new Kss_JstzModal();
            String key = "KSS_JSTZ";
            String processDefinitionId = CacheUtils.get().getFlowKey(key);
            kss_jstzsModal.setJbxxEntity(jbxxEntity);
            kss_jstzsModal.setJstzEntity(jstzEntity);
            kss_jstzsModal.setLcid(processDefinitionId);
            ResponseMessage<Map<String, String>> jstz2Model = kssServerApis.jstzSave(kss_jstzsModal);

            if(jstz2Model.getStatus() == 200){
                jstz2Model.setMessage("保存成功!");
            }else{
                jstz2Model.setMessage("服务异常,保存失败!");
            }
            return jstz2Model;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


    /**
     * @api {get} /v4/jwp/jstz/jstzlist 监室调整查询
     * @apiVersion 0.4.0
     * @apiName jstzlist
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 监室调整查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess  {String} ay                                              案由
     * @apiSuccess  {String} ayString                                        案由(已转换)
     * @apiSuccess  {String} bz                                              备注
     * @apiSuccess  {String} createtime                                      创建时间
     * @apiSuccess  {String} createtimeString                                创建时间已转换)
     * @apiSuccess  {String} creator                                         创建人
     * @apiSuccess  {String} csrq                                            出所日期
     * @apiSuccess  {String} csrqString                                      出所日期(已转换)
     * @apiSuccess  {String} dah                                              档案编号
     * @apiSuccess  {String} id                                                ID
     * @apiSuccess  {String} jsbh                                            监所编号
     * @apiSuccess  {String} psbz                                            批示标志
     * @apiSuccess  {String} rsrq                                            入所日期
     * @apiSuccess  {String} rsrqString                                      入所日期(已转换)
     * @apiSuccess  {String} rybh                                            人员编号
     * @apiSuccess  {String} state                                           状态
     * @apiSuccess  {String} tjr                                             提解人
     * @apiSuccess  {String} tzsj                                            调整时间
     * @apiSuccess  {String} tzsjString                                      调整时间(已转换)
     * @apiSuccess  {String} updatetime                                      更新时间
     * @apiSuccess  {String} updatetimeString                                更新时间(已转换)
     * @apiSuccess  {String} updator                                         更新人
     * @apiSuccess  {String} xb                                              性别
     * @apiSuccess  {String} xbString                                        性别(已转换)
     * @apiSuccess  {String} xjsh                                            现监室号
     * @apiSuccess  {String} xm                                              姓名
     * @apiSuccess  {String} yjsh                                            原监室号
     * @apiSuccess  {String} ywlcid                                          流程编号
     * @apiSuccess  {String} yy                                              原因
     * @apiSuccess  {String} yyString                                        原因(已转换)
     * @apiSuccess  {String} zdzpssj                                         中队长批示时间
     * @apiSuccess  {String} zdzpssjString                                   中队长批示时间(已转换)
     * @apiSuccess  {String} zdzxm                                             中队长姓名
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
     *     "total": 57,
     *     "data": [
     *       {
     *         "csrq": 823104000000,
     *         "ywlcid": "5175080",
     *         "dah": "1232434",
     *         "tzsjString": "2019-12-27",
     *         "bz": "规划局有一天",
     *         "id": "11000011420191227000590",
     *         "state": "R2",
     *         "yjsh": "0114",
     *         "yy": "0",
     *         "yyString":"其他",
     *         "createtime": 1577430906000,
     *         "createtimeString":"2019-07-16 12:11:11"
     *         "creator": "管理员",
     *         "zdzpssj": 1577430914000,
     *         "rsrqString": "2019-07-16",
     *         "zdzxm": "管理员",
     *         "updatetimeString":""
     *         "zdzpssjString":"2019-11-19 15:21:07"
     *         "csrqString": "1996-02-01",
     *         "xb": "1",
     *         "xbString":"男性",
     *         "tzsj": 1577431510000,
     *         "psbz": "1",
     *         "rsrq": 1563259609000,
     *         "xm": "测试11号",
     *         "ayString": "煽动分裂国家案",
     *         "ay": "010130",
     *         "rybh": "110000114201911270020",
     *         "updator": "管理员",
     *         "updatetime": 1577431510000,
     *         "tjr": "管理员",
     *         "jsbh": "110000114",
     *         "xjsh": "9005"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577697716307
     * }
     *
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "jsh":"监室号(最大字段长度：4)",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */
    @OpenAPI
    @ApiOperation("监室调整查询")
    @GetMapping("/jstzlist")
    @HystrixCommand
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功"),
            @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"),
            @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jstz_list(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/jstz/jstzlist";
        String state = request.getParameter("state");
        //通过校验获取查询参数
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jstzList(param);
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