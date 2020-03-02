package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
import awd.cloud.platform.model.jwp.WgsjclModel;
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
@RequestMapping("/v4/jwp/jwp_fyqr")
@Api(tags = "jwp-fyqr",description = "fyqr")
public class Jwp_FyqrController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jwp/jwp_fyqr/fyqrList 服药确认查询
     * @apiVersion 0.4.0
     * @apiName fyqrQuery
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 服药确认查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}xm         				                         姓名
     * @apiSuccess {String}jsh         				                         监室号
     * @apiSuccess {String}rybh         				                     人员编号
     * @apiSuccess {String}jsbh                                              监所编号
     * @apiSuccess {String}xb                                                性别
     * @apiSuccess {String}id                                                ID
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
     *     "total": 6,
     *     "data": [
     *       {
     *         "xm": "李无力",
     *         "rybh": "110000114201912140001",
     *         "xb": "1",
     *         "id": "11000011420191214000439",
     *         "jsh": "1201",
     *         "jsbh": "110000114"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577082191258
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "jsh":" 监室号  (最大字段长度：4)",
     *          "state":"状态(最大字段长度：2)",
     *          "sffy":"是否需要发药,字典（SHFO）"
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("服药确认查询")
    @GetMapping("/fyqrList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> fyqr_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp_fyqr/fyqrList";
        String state = "R8";
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
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("sffy"))) {
                param.and("sffy", TermType.eq, maps.getResult().get("sffy"));
            }
            if (!StringUtils.isNullOrEmpty(request.getParameter("state"))) {
                state = request.getParameter("state");
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.queryForPage(param);
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
     * @api {post} /v4/jwp/jwp_fyqr/fyqrSave 服药确认保存
     * @apiVersion 0.4.0
     * @apiName fyqrSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 服药确认保存.
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
     *                  "rybh":"人员编号(必填; 最大长度:21)",
     *                  "ryxm":"人员姓名(必填;最大长度:255)",
     *                  "ypmc":"药品名称(必填；最大长度:255)",
     *                  "sffy":"是否服药(必填；最大长度:1)",
     *                  "ypsl":"数量(必填；最大长度:255)",
     *                  "creator":"创建者(必填；最大长度:50)",
     *                  "qrzp1":"照片()",
     *                  "qrzp2":"照片()",
     *                  "qrzp3":"照片()"
     *                }
     *              ]
     *     }
     */
    @ApiOperation("服药确认保存")
    @PostMapping("/fyqrSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> fyqrSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/jwp_fyqr/fyqrSave";
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
            List<FyqrModel> fyqrmodel = JSONArray.parseArray(map.get("entity").toString(), FyqrModel.class);
            System.err.println("fyqrmodel--"+ JSON.toJSONString(fyqrmodel.get(0)));
            fyqrmodel.get(0).setCreatetime(new Date());
            fyqrmodel.get(0).setState("R2");
            fyqrmodel.get(0).setAppcode(appcode);
            fyqrmodel.get(0).setJsbh(jsbh);
            fyqrmodel.get(0).setJsh("0101");
            fyqrmodel.get(0).setFysj(new Date());
            FyqrModel FyModel = fyqrmodel.get(0);
            System.err.println("fyqrmodel--"+JSON.toJSONString(FyModel));
            ResponseMessage<String> fyqrModel = jwpServerApis.fyqrSave(FyModel);
            if(fyqrModel.getStatus() == 200){
                fyqrModel.setMessage("保存成功!");
            }else{
                fyqrModel.setMessage("服务异常,保存失败!");
            }
            return fyqrModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


}