package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.ShffModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.kss.Kss_JbxxModelDO;
import awd.cloud.platform.model.kss.Kss_ShffModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.JSONUtil;
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
 * Date：2020-01-03 14:31
 * Description：<描述>
 */

@RestController
@RefreshScope
@RequestMapping("/v4/kss/shgl")
@Api(tags = "kss-shgl",description = "Shgl")
public class Kss_ShglController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;



    /**
     * @api {get} /v4/kss/shgl/shffList 手环查询
     * @apiVersion 0.4.0
     * @apiName shffList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 手环查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     *
     * @apiSuccess {String}rybh          				                     人员编号
     * @apiSuccess {String}jsbh         				                     监所编号
     * @apiSuccess {String}jsbhString                                        监所编号(转换后)
     * @apiSuccess {String}id                                                ID
     * @apiSuccess {String}creator                                           创建人
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
     *     "data": [
     *       {
     *         "creator": "管理员",
     *         "rybh": "310000111201906200008",
     *         "id": "11000011420191018000086",
     *         "jsbhString": "北京市第一看守所",
     *         "jsbh": "110000114",
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576826568061
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "rybh":"人员编号(最大字段长度：21)",
     *        }
     * @return

     */

    @OpenAPI
    @ApiOperation("手环查询")
    @GetMapping("/shffList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> shffQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/shgl/shffList";

        String state = request.getParameter("state");

        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq,jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.shffQueryForPage(param);

            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
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
     * @api {post} /v4/kss/shgl/shffAdd  手环保存
     * @apiVersion 0.4.0
     * @apiName shffAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 手环保存
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
     *                     "bdzt":"绑定状态(必填; 字典(BDZT)；最大字段长度：1)",
     *                     "rybh":"更新人(必填; 最大字段长度：21)",
     *                     "bdsj":"绑定时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *                     "shid":"手环ID(必填; 最大字段长度：30)",
     *                     "creator":"创建人(必填; 最大字段长度：50)",
     *                     "bdr":"绑定人(必填; 最大字段长度：30)",
     *                     "jsh":"监室号(必填; 最大字段长度：6)"
     *                     }]
     *             }
     *
     */
           //     {"bdzt":"\\d{1,1}","rybh":"\\d{1,21}","bdsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","shid":".{1,30}","creator":".{1,50}","bdr":".{1,30}","jsh":"\\d{1,6}"}

    @ApiOperation("手环保存")
    @PostMapping("/shffAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> shffAdd(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/shgl/shffAdd";

        String state = request.getParameter("state");
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
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }


            List<Kss_ShffModel> shffList = JSONArray.parseArray(map.get("entity").toString(), Kss_ShffModel.class);
            Kss_ShffModel shffModel=  shffList.get(0);
            shffModel.setState("R2");
            shffModel.setCreatetime(new Date());
            shffModel.setJsbh(jsbh);
            shffModel.setBdzt("2");



            ResponseMessage<String> shffModel1 = kssServerApis.shffSave(shffModel);

            if(shffModel1.getStatus() == 200){
                shffModel1.setMessage("保存成功!");
            }else{
                shffModel1.setMessage("服务异常,保存失败!");
            }
            return shffModel1;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }





    /**
     * @api {post} /v4/kss/shgl/updateShff  手环解绑
     * @apiVersion 0.4.0
     * @apiName updateShff
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 手环解绑
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
     *                  "id":"id(必填；最大字段长度：23)",
     *                  "updator":"跟新人(必填；最大字段长度：50)",
     *                  "jbxxRybh":"人员编号(必填；最大字段长度：21)",
     *                  "jbyy":"解绑原因(必填；最大字段长度：500)",
     *                  "jsh":"监室号(必填；最大字段长度：6)",
     *                  "rybh":"人员编号(最大字段长度：21)",
     *                  "shid":"手环ID(最大字段长度：30)",
     *                  "bdr":"绑定人(最大字段长度：30)",
     *                  "jbr":"解绑人(最大字段长度：200),
     *                  "bfyy":"补发原因(最大字段长度：300)",
     *                  "bz":"备注(最大字段长度：100)",
     *                  "creator":"创建人(最大字段长度：50)"
     *                     }]
     *             }
     *
     */
    //     {"id":"\\d{1,23}","jbxxRybh":"\\d{1,21}","updator":".{1,50}","jbyy":".{1,500}","jsh":"\\d{1,6}","rybh":"\\d{0,21}","shid":".{0,30}","bdr":".{0,30}","jbr":".{0,30}","bfyy":".{0,300}","bz":".{0,100}","creator":".{0,50}"}


    @ApiOperation("手环解绑")
    @PostMapping("/updateShff")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> updateShff(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/shgl/updateShff";

        String state = request.getParameter("state");
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
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }


            List<ShffModel> shffList = JSONArray.parseArray(map.get("entity").toString(), ShffModel.class);
            ShffModel shffModel=  shffList.get(0);

            List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
             Map<String,Object> entityMap = mapsList.get(0);

            String jbxxRybh= (String) entityMap.get("jbxxRybh");
            String id=shffModel.getId();
            shffModel.setState("R2");
            shffModel.setCreatetime(new Date());
            shffModel.setUpdatetime(new Date());
            shffModel.setJsbh(jsbh);
            shffModel.setBdzt("3");

            System.err.println("手环解绑"+ JSONUtil.toJson(shffModel));
         ResponseMessage  result= kssServerApis.update(id,shffModel);

            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
                 kssServerApis.shUpdateById(jbxxRybh);
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }




    /**
     * @api {post} /v4/kss/shgl/shffBf  手环补发
     * @apiVersion 0.4.0
     * @apiName shffBf
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 手环补发
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
     *                       "id":"id(必填；最大字段长度：23)",
     *                       "updator":"跟新人(必填；最大字段长度：50)",
     *                       "jbyy":"解绑原因(最大字段长度：500)",
     *                       "jsh":"监室号(最大字段长度：6)",
     *                        "rybh":"人员编号(必填；最大字段长度：21)",
     *                        "shid":"手环ID(必填；最大字段长度：30)",
     *                        "bdr":"绑定人(最大字段长度：30)",
     *                        "jbr":"解绑人(最大字段长度：200),
     *                        "bfyy":"补发原因(必填；最大字段长度：300)",
     *                        "bz":"备注(最大字段长度：100)",
     *                        "creator":"创建人(最大字段长度：50)"
     *                     }]
     *             }
     *
     */
    //     {"id":"\\d{1,23}","updator":".{1,50}","jbyy":".{0,500}","jsh":"\\d{0,6}","rybh":"\\d{1,21}","shid":".{1,30}","bdr":".{0,30}","jbr":".{0,30}","bfyy":".{1,300}","bz":".{0,100}","creator":".{0,50}"}


    @ApiOperation("手环补发")
    @PostMapping("/shffBf")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> shffBf(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/kss/shgl/shffBf";

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
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }


            List<ShffModel> shffList = JSONArray.parseArray(map.get("entity").toString(), ShffModel.class);
            ShffModel shffModel=  shffList.get(0);
            List<Kss_JbxxModelDO> jbxxList = JSONArray.parseArray(map.get("entity").toString(), Kss_JbxxModelDO.class);
            Kss_JbxxModelDO jbxxModel=  jbxxList.get(0);
            // List<Map> mapsList = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            // Map<String,Object> entityMap = mapsList.get(0);

            String jbxxid =jbxxModel.getId();
            String id=  shffModel.getId();
            shffModel.setState("R2");
            shffModel.setJsbh(jsbh);
            shffModel.setBdzt("2");
            shffModel.setUpdatetime(new Date());
            System.err.println("手环解绑"+JSONUtil.toJson(shffModel));
            ResponseMessage  result= kssServerApis.update(id, shffModel);

            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
                kssServerApis.update(jbxxid,jbxxModel);
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
