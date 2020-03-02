package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XjhzModel;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/v4/kss/xjhz")
@Api(tags = "kss-xjhz",description = "Xjhz")
public class Kss_XjhzController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;



    /**
     * @api {post} /v4/kss/xjhz/xjhzList 根据人员标号查询现金汇总
     * @apiVersion 0.4.0
     * @apiName findXjhz
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 根据人员标号查询现金汇总.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}snbh         				                    所内编号
     * @apiSuccess {String}xm          				                        人员姓名
     * @apiSuccess {String}rybh          				                    人员编号
     * @apiSuccess {String}xb          				                        性别
     * @apiSuccess {String}jsbh          				                    监所编号
     * @apiSuccess {String}jsh          				                    监室号
     * @apiSuccess {String}rsrq                                             入所日期
     * @apiSuccess {String}csrq                                             出所日期
     * @apiSuccess {String}hjd                                              户籍地(数字字典:xzqh)
     * @apiSuccess {String}jsbhstring                                       监所编号(已转换)
     * @apiSuccess {String}currency                                         币种
     * @apiSuccess {String}currencystring                                   币种(已转换)
     * @apiSuccess {String}je                                               金额
     * @apiSuccess {String}state                                            删除状态(数字字典:ywstate)
     * @apiSuccess {String}stateString                                      删除状态(已转换)(数字字典:ywstate)
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}createtim                                        创建时间
     * @apiSuccess {String}updator                                          更新人
     * @apiSuccess {String}updatetime                                       更新时间
     *
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *      {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 144,
     *     "data": [
     *       {
     *         "csrq": "1986-01-24 00:00:00",
     *         "creator": "管理员",
     *         "createtime": 1562899510000,
     *         "hjd": "四川美姑县",
     *         "stateString": "有效",
     *         "snbh": "20190047",
     *         "xm": "发射点",
     *         "rsrq": "2019-12-31 16:04:49",
     *         "rybh": "110000111201907120003",
     *         "updator": "管理员",
     *         "currency": "156",
     *         "id": "11000011120190712000078",
     *         "je": 0,
     *         "state": "R2",
     *         "jsbhString": "北京市第一看守所",
     *         "updatetime": 1575095641000,
     *         "jsh": "0101",
     *         "jsbh": "110000114",
     *         "currencyString": "null"
     *       },
     *          ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578448210932
     * }

     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     *   json:{
     *          rybh:"人员编号"
     *         }
     *
     *
     */

    @ApiOperation("根据人员的编号查询")
    @PostMapping("/xjhzList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> findXjhz(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/xjhz/xjhzList";

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
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.xjhzlist(param);
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
     * @api {post} /v4/kss/xjhz/xjth 现金退还
     * @apiVersion 0.4.0
     * @apiName xjth
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 现金退还.
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
     *                     "rybh":"人员编号(必填;最大长度:21)",
     *                     "ye":"余额(必填;最大长度:10)",
     *                     "id":"id(必填;最大长度:23)",
     *                     "blr":"更新人(必填;最大长度:50)"
     *                }]
     *         }
     *
     *
     */


    @ApiOperation("现金退还")
    @PostMapping("/xjth")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xjth(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //{"rybh":"\\d{1,21}","ye":"\\S{1,10}","id":"\\S{1,23}","blr":"\\S{1,50}"}
        //{"entity":[{"rybh":"110000111201907110004","ye":"1.00","id":"11000011120190711000075","blr":"管理员"}]}
        String interfaceId = "/v4/kss/xjhz/xjth";

        //通过校验获取查询参数
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            System.err.println("map--"+map.get("entity").toString());
            map.put("interfaceId", interfaceId);
            List<Map<String, Object>> rslist=toListMap(map.get("entity").toString());
            String ye=(String) rslist.get(0).get("ye");
            String blr=(String) rslist.get(0).get("blr");
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<XjhzModel> xjhzModels = JSONArray.parseArray(map.get("entity").toString(), XjhzModel.class);
            xjhzModels.get(0).setState("R2");
            xjhzModels.get(0).setJsbh(jsbh);
            xjhzModels.get(0).setUpdator(blr);
            XjhzModel xjhzModel=xjhzModels.get(0);
            xjhzModel.setCurrency("156");
            xjhzModel.setJe(new BigDecimal(ye));
            xjhzModel.setCreatetime(new Date());
            System.err.println("XjhzModel--"+ JSON.toJSONString(xjhzModel));
            ResponseMessage<String> result = kssServerApis.csxjth(xjhzModel);
            System.err.println("result--"+result.getResult());
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
