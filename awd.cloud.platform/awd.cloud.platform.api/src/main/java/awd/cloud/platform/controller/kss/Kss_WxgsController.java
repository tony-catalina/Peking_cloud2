package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.QybzModel;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/wxgs")
@Api(tags = "kss-wxgs",description = "wxgs")
public class Kss_WxgsController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/wxgs/wxgsList 维修跟随查询
     * @apiVersion 0.4.0
     * @apiName wxgsList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 维修跟随查询
     *
     * @apiParam {String} appcode 												应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id          				                        id
     * @apiSuccess {String}jsbh          				                        监所编号
     * @apiSuccess {String}jsbhString          				                监所编号（已转换）
     * @apiSuccess {String}jsh          				                        监室号
     * @apiSuccess {String}bzlx       				                            保障类型
     * @apiSuccess {String}bzlxString          				                保障类型（已转换）
     * @apiSuccess {String}kssj          				                        开始时间
     * @apiSuccess {String}jssj          				                        结束时间
     * @apiSuccess {String}dcsj          				                        带出时间
     * @apiSuccess {String}dcmj          				                        带出民警
     * @apiSuccess {String}dcrs          				                        带出人数
     * @apiSuccess {String}qy          				                        区域
     * @apiSuccess {String}dd          				                        地点
     * @apiSuccess {String}dlr          				                        带领人
     * @apiSuccess {String}dlys                                                带领医生
     * @apiSuccess {String}jtqk                                                具体情况
     * @apiSuccess {String}mjxm                                                民警姓名
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
     *        	"id": "11000011420191230000354",
     *          "jsbh": "110000114",
     *          "jsbhString": "北京市第一看守所",
     *          "jsh": "",
     *          "bzlx": "维修跟随",
     *          "bzlxString": null,
     *          "kssj": "2020-01-21 09:37:01",
     *          "jssj": "2020-01-22 09:37:10",
     *          "dcsj": null,
     *          "dcmj": "",
     *          "dcrs": "0",
     *          "qy:" "",
     *          "dd": "",
     *          "dlr": "23",
     *          "dlys": "",
     *          "jtqk": "",
     *          "mjxm": "12",
     *          "state": "R2",
     *          "stateString": "有效",
     *          "bz": "",
     *          "creator": "管理员",
     *          "createtime": "2020-01-22 09:37:18",
     *          "updator": "",
     *          "updatetime": null
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
     *          "mjxm": "民警姓名",
     *          "dcsj_start": "开始时间（格式:yyyy-MM-dd hh:mm:ss）",
     *          "dcsj_end": "结束时间（格式:yyyy-MM-dd hh:mm:ss）",
     *          "bzlx": "维修跟随",
     *          "page": "当前页数",
     *          "rows": "一页数据量",
     *          "sort": "id",
     *          "order": "desc"
     *         }
     *
     */
    // id,jsbh,jsbhString,jsh,bzlx,bzlxString,kssj,jssj,dcsj,dcmdaij,dcrs,qy,dd,dlr,dlys,jtqk,mjxm,creator,createtime,updator,updatetime
    @OpenAPI
    @ApiOperation("维修跟随查询")
    @GetMapping("/wxgsList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> wxgsList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){

        String interfaceId = "/v4/kss/wxgs/wxgsList";
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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("mjxm"))) {
                param.and("mjxm", TermType.like,"%"+ maps.getResult().get("mjxm")+"%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dcsj_start"))) {
                param.and("kssj", TermType.gte, maps.getResult().get("dcsj_start"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("dcsj_end"))) {
                param.and("kssj", TermType.lte, maps.getResult().get("dcsj_end"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bzlx"))) {
                param.and("bzlx", TermType.eq, maps.getResult().get("bzlx"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<QybzModel>> result = kssServerApis.wxgsList(param);
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

    /**
     * @api {post} /v4/kss/wxgs/wxgsAdd 维修跟随保存
     * @apiVersion 0.4.0
     * @apiName wxgsAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 维修跟随保存
     *
     * @apiParam {String} appcode 						    应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			    成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			    时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *   "entity":[
     *     {
     *      "bzlx": "保障类型（必填; 最大长度:20）",
     *      "jsh": "监室号（必填）",
     *      "kssj": "开始时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *      "jssj": "结束时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *      "mjxm": "民警姓名（必填; 最大长度:60）",
     *      "dlr": "带领人（必填）",
     *      "dcmj": "带出民警",
     *      "bz": "备注"
     *      "creator": "创建人(必填; 最大长度:50)"
     *     }
     *   ]
     * }
     *
     */
    //{"bzlx":".{1,20}","kssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","jssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","mjxm":".{1,60}","dlr":".{1,60}","dcmj":".{1,60}","creator":".{1,30}"}
    @ApiOperation("维修跟随保存")
    @PostMapping("/wxgsAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> wxgsAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/wxgs/wxgsAdd";
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
            List<QybzModel> list = JSONArray.parseArray(map.get("entity").toString(),QybzModel.class);
            QybzModel qybzModel = list.get(0);
            qybzModel.setState("R2");
            qybzModel.setJsbh(jsbh);
            qybzModel.setCreatetime(new Date());

            ResponseMessage<String> result =   kssServerApis.wxgsAdd(qybzModel);
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
}
