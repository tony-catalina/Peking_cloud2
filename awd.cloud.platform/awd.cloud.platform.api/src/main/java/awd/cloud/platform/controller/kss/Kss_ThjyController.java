package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.LshjModel;
import awd.bj.kss.model.ThjyModel;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/thjy")
@Api(tags = "kss-thjy", description = "thjy")
public class Kss_ThjyController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/thjy/thjyList 谈话教育查询
     * @apiVersion 0.4.0
     * @apiName thjyList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 谈话教育查询
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}thyy          				                    谈话原因
     * @apiSuccess {String}xb          				                        性别
     * @apiSuccess {String}thyyString          				                谈话原因(已转换)
     * @apiSuccess {String}jssjString          				                结束时间
     * @apiSuccess {String}thnr          				                    谈话内容
     * @apiSuccess {String}xbString          				                性别(已转换)
     * @apiSuccess {String}snbh          				                    所内编号
     * @apiSuccess {String}xm          				                        姓名
     * @apiSuccess {String}kssjString          				                开始时间
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}jsbh          				                    监所编号
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}nl          				                        年龄
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
     *         "thyy": "02",
     *         "xb": "1",
     *         "thyyString": "在押人员家庭发生变故谈话",
     *         "jssjString": "2019-12-20 12:00:08",
     *         "thnr": "问：你的基本情况？\r\n答：\r\n问：你的家庭成员情况？\r\n答：\r\n问：以前受过什么处理？\r\n答：\r\n问：交待一下你的案情？\r\n答：\r\n问：告知你《在押人员行为规范》共分为总则、基本规范、生活规范、学习规范、文明礼貌规范、劳动规范以及附则共七章四十二条（宣读解释其中的条款内容），帮助你理解并要求你必须在入所后七天内熟知其中内容，严格遵守执行，听明白了？\r\n答：\r\n问：你虽然因涉嫌犯罪被采取强制措施关进了看守所，但你还依法享有七项权利，看守所依法为你保障这些权利。所长找你谈话了解情况，你必须实事求是地反映情况，以便看守所能够及时准确掌握监室一切情况，把握工作主动权，积极维护监室秩序，切实保障你们的合法权益不受侵犯。你入已两天了，监室其他在押人员有无打骂欺负你的，有无打骂欺负别人的？有的话，都是谁？\r\n答：\r\n问：回去后，要抓紧熟背监规，熟记行为规范，并严格遵守，弃恶向善，逐步培养自己良好的行为习惯；加强改造，服从管教，不得违犯监规，抗拒管理，听明白了？\r\n答：\r\n",
     *         "xbString": "男性",
     *         "snbh": "20190213",
     *         "xm": "电动车",
     *         "kssjString": "2019-12-20 10:00:08",
     *         "rybh": "110000114201910280001",
     *         "jsbh": "110000114",
     *         "jsh": "0810",
     *         "nl": 34
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1579153743620
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "rybh":"人员编号",
     * }
     *
     */
    @ApiOperation("谈话教育查询")
    @GetMapping("/thjyList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> thjyList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/thjy/thjyList";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))){
                param.and("rybh",TermType.eq,maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String,Object>>> result = kssServerApis.thjyQueryForPage(param);
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
     * @api {post} /v4/kss/thjy/thjyAdd 谈话教育保存
     * @apiVersion 0.4.0
     * @apiName thjyAdd
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 谈话教育保存
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
     *   "taskid":"流程id(必填)",
     *   "entity":[
     *     {
     *      "rybh": "人员编号(必填; 最大长度:21)",
     * 		"kssj": "开始时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 		"jssj": "结束时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * 		"thyy": "谈话原因(必填; 字典:thyy; 最大长度:3)",
     * 		"thnr": "谈话内容(必填;)",
     * 		"fzmj": "负责民警(必填; 最大长度:30)",
     * 		"ywqxyc": "有无情绪异常(必填; 1:无 2:有; 最大长度:1)",
     * 		"qxycqk": "情绪异常情况",
     * 		"ywzssb": "有无自述伤病(必填; 1:无 2:有; 最大长度:1)",
     * 		"sbqk": "伤病情况",
     * 		"creator": "创建人(必填; 最大长度:50)"
     *     }
     *   ]
     * }
     *
     */
    @ApiOperation("谈话教育保存")
    @GetMapping("/thjyAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> thjyAdd(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/thjy/thjyAdd";
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

            if(StringUtils.isNullOrEmpty(maps.getResult().get("taskid"))){
                return ResponseMessage.error("taskid不可为空");
            }
            String taskid = maps.getResult().get("taskid").toString();

            ThjyModel model = JSONArray.parseArray(map.get("entity").toString(), ThjyModel.class).get(0);
            if(StringUtils.isNullOrEmpty(model.getThnr())){
                return ResponseMessage.error("thnr不可为空");
            }
            if ("2".equals(model.getYwqxyc()) && StringUtils.isNullOrEmpty(model.getQxycqk())) {
                return ResponseMessage.error("请说明情绪异常情况");
            }
            if ("2".equals(model.getYwzssb()) && StringUtils.isNullOrEmpty(model.getSbqk())) {
                return ResponseMessage.error("请说明伤病情况");
            }
            if ("100".equals(model.getThyy())){
                model.setSkth("1");
            }else {
                model.setSkth("0");
            }
            model.setState("R2");
            model.setJsbh(jsbh);
            model.setCreatetime(new Date());

            ResponseMessage<String> result = kssServerApis.thjySaveAndOverFlow(model, taskid);
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

}
