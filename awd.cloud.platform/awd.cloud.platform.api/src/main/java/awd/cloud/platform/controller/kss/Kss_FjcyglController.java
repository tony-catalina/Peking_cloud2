package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.CypjModel;
import awd.bj.kss.model.XlgyModel;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/fjcygl")
@Api(tags = "kss-fjcygl",description = "Fjcygl")
public class Kss_FjcyglController extends PublicService {
    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/kss/fjcygl/cypjList 处遇品级查询
     * @apiVersion 0.4.0
     * @apiName cypjList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 处遇品级查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsbhString                                       监所编号（已转换）
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}kpsx                                             考评时限
     * @apiSuccess {String}ljkhzf                                           累计考核总分
     * @apiSuccess {String}ljkhpjf                                          累计考核平均分
     * @apiSuccess {String}khksrq                                           考核开始日期
     * @apiSuccess {String}pjrq                                             评鉴日期
     * @apiSuccess {String}zhpdyj                                           综合评定意见
     * @apiSuccess {String}zhpdyjString                                     综合评定意见（已转换）
     * @apiSuccess {String}dqssjd                                           当前诉讼阶段
     * @apiSuccess {String}dqssjdString                                     当前诉讼阶段（已转换）
     * @apiSuccess {String}jkfjl                                            加扣分记录
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}stateString                                      状态（已转换）
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}updatetime                                       更新时间
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
     *     "total": 1,
     *     "data": [{
     *          "id": "11000011420200117000092",
     * 	        "rybh": "110000114202001030001",
     * 	        "jsbh": "110000114",
     * 	        "jsbhString": "北京市第一看守所",
     * 	        "jsh": "",
     * 	        "kpsx": "",
     * 	        "ljkhzf": 0,
     * 	        "ljkhpjf": 0,
     * 	        "khksrq": null,
     * 	        "pjrq": "2020-01-17 16:03:13",
     * 	        "zhpdyj": "1",
     * 	        "zhpdyjString": "主动检举揭发其他犯罪嫌疑人、被告人私藏、自制各类可能危及看守所安全的 或危险品，及时消除安全隐患的。",
     * 	        "dqssjd": "12",
     * 	        "dqssjdString": "逮捕",
     * 	        "jkfjl": "",
     * 	        "bz": "",
     * 	        "state": "R2",
     * 	        "stateString": "有效",
     * 	        "creator": "管理员",
     * 	        "createtime": "2020-01-17 16:03:19",
     * 	        "updator": "",
     * 	        "updatetime": null
     * 		}],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579158673428
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "rybh$eq":"人员编号(最大长度:21)",
     *          "state$eq":"状态(最大长度:2)",
     *          "page":"1",
     *          "rows":"20",
     *          "sort":"id",
     *          "order":"desc"
     *        }
     *
     */
    //id,rybh,jsbh,jsbhString,jsh,kpsx,ljkhzf,ljkhpjf,khksrq,pjrq,zhpdyj,zhpdyjString,dqssjd,dqssjdString,jkfjl,bz,state,stateString,creator,createtime,updator,updatetime
    //{"rybh$eq":"110000114202001030001","state$eq":"R2","page":"1","rows":"20","sort":"id","order":"desc"}
    @OpenAPI
    @ApiOperation("处遇品级查询")
    @PostMapping("/cypjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String,Object>> cypjList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/fjcygl/cypjList";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数
            String rybh=(String) maps.getResult().get("rybh$eq");
            String state = (String) maps.getResult().get("state$eq");
            QueryParam qParam = new QueryParam();
            if(!StringUtils.isNullOrEmpty(rybh)) {
                qParam.and("rybh", TermType.gte, rybh);
            }
            if(StringUtils.isNullOrEmpty(state)) {
                return ResponseMessage.error("state不能为空！");
            }
            DefaultQueryParam.addDefaultQueryParam(request, qParam,state);
            ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.cypjQueryForPage(qParam);
            System.err.println("result++++++"+result.getResult().getData().toString());
            for (Map<String,Object> mapss:result.getResult().getData()){
                if (mapss.get("zhpdyj")!=null||mapss.get("zhpdyj")!=""){
                    String[] zhpydjs = mapss.get("zhpdyj").toString().split(",");
                    System.out.println("aaaaaaa"+zhpydjs[0]);
                    String zhpdyjString = "";
                    for (int i=0;i<zhpydjs.length;i++) {
                        String zhpdyj = CacheUtils.get().getDictionary("ZHPDYJ", zhpydjs[i]);
                        zhpdyjString+=zhpdyj+"。";
                        System.out.println("zhpdyj===================="+zhpdyj);
                        mapss.put("zhpdyjString",zhpdyjString);
                    }
                }
            }
            System.err.println("result" + JSON.toJSONString(result));
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

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
     * @api {post} /v4/kss/fjcygl/cypjAdd  处遇品级保存
     * @apiVersion 0.4.0
     * @apiName cypjAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription  处遇品级保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000011",
     *   "status": 200,
     *   "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     * 	"entity": [{
     *          "rybh":"人员编号(最大字段长度:21)",
     *          "dqssjd":"当前诉讼阶段(最大字段长度:3)",
     *          "snbh":"所内编号(最大字段长度:8)",
     *          "xm":"姓名(最大字段长度:30)",
     *          "jsh":"监室号(最大字段长度:4)",
     *          "jkfjl":"加扣分记录()",
     *          "pjrq":"评审日期(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *          "zhpdyj":"综合评定意见(必填)",
     *          "bz":"备注()"
     * 	        }]
     * }
     *
     *
     */
    @ApiOperation("处遇品级保存")
    @PostMapping("/cypjAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, String>> cypjAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //{"pjrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
        //{"entity":[{"creator":"aa","rybh":"110000114201912190006","dqssjd":"35","snbh":"20190317","xm":"123","jsh":"1201","jkfjl":"","pjrq":"2020-01-20 10:30:23","zhpdyj":"1","bz":"ss"}]}
        //接口id
        String interfaceId = "/v4/kss/fjcygl/cypjAdd";
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

            //封装数据
            List<CypjModel> CypjModels = JSONArray.parseArray(map.get("entity").toString(), CypjModel.class);
            CypjModel cypjModel=CypjModels.get(0);
            cypjModel.setState("R2");
            cypjModel.setJsbh(jsbh);
            cypjModel.setCreatetime(new Date());
            if (StringUtils.isNullOrEmpty(cypjModel.getCreator())){
                return ResponseMessage.error("creator不能为空！");
            }
            if (StringUtils.isNullOrEmpty(cypjModel.getZhpdyj())){
                return ResponseMessage.error("综合评定意见zhpdyj不能为空！");
            }
            ResponseMessage<Map<String, String>> result = kssServerApis.cypjAddcypj(cypjModel);
            System.err.println("--"+ JSON.toJSONString(result));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }



}
