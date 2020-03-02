package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.JsdmModel;
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
@RequestMapping("/v4/jnp/jsdm")
@Api(tags = "jnp-jsdm",description = "jsdm")
public class Jnp_JsdmController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/jsdm/zddmQuery 自动点名查询
     * @apiVersion 0.4.0
     * @apiName zddmQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 自动点名查询.
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} sfzjString         			是否在监(已转换)
     * @apiSuccess {String} xm         				    姓名
     * @apiSuccess {String} sfzj         				是否在监字典
     * @apiSuccess {String} rybh         				人员编号
     * @apiSuccess {String} wdmyy         				未点名原因
     * @apiSuccess {String} mjqr         				民警确认
     * @apiSuccess {String} jsh         				监室号
     * @apiSuccess {String} dmsj         				点名时间
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "sfzjString": "null",
     *         "xm": "司马茜\t",
     *         "sfzj": "null",
     *         "rybh": "110000111201907120002",
     *         "wdmyy": "未点名原因",
     *         "mjqr": "民警确认",
     *         "jsh": "0101",
     *         "dmsj": "2019-11-27 00:00:00"
     *       }],
     *     "page": 1
     *   },
     *   "status": 200,
     *   "timestamp": 1576308123984
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "creator":"管理员(最大长度:60)",
     *     "jsh":"监室号(最大长度:4)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("自动点名查询")
    @GetMapping("/zddmQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> zddm_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/jsdm/zddmQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }


            //查询参数
            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
//            if(!StringUtils.isNullOrEmpty(maps.getResult().get("user"))) {
//                param.and("user", TermType.eq, maps.getResult().get("user"));
//            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("creator"))) {
                param.and("creator", TermType.eq, maps.getResult().get("creator"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--"+ JSON.toJSONString(param));
            ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.jsdmQuery(param);
            System.err.println("result"+JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total",  result.getResult().getTotal());
            maplist.put("page",  request.getParameter("page"));
            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if(list.getStatus()==200) {
                list.setMessage("查询成功");
                if(list.getResult()==null) {
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
    
     * @api {post} /v4/jnp/jsdm/jsdmSave 监室点名保存
     * @apiVersion 0.4.0
     * @apiName jsdmSave
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 监室点名保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json                                              保存参数集(必填)

     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "保存成功!",
     *   "result": "11000011420191214000005",
     *   "status": 200,
     *   "timestamp": 1576311187399
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *   appcode:"应用代码(必填)",
     *   jsbh:"监所编号(必填; 字段长度：9)",
     *   json:{"entity":[{
     *                 "jsh":"监室号(必填; 最大长度:4)"
     *                 "dmr":"点名人(必填; 最大长度:50)",
     *                 "jssj":"结束时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *                 "kssj":"开始时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                 "wdrs":"未到人数(必填；最大长度:2)",
     *                 "wdrybh":"未到人员编号(必填;多个参数之间用逗号','隔开)",
     *                 "yy":"原因"
     *               }]
     *           }
     *
     */
    @OpenAPI
    @ApiOperation("监室点名保存")
    @PostMapping("/jsdmSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> jsdm_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/jsdm/jsdmSave";
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

            List<JsdmModel> jsdmModel = JSONArray.parseArray(map.get("entity").toString(), JsdmModel.class);
            System.err.println("jsdmModel--"+JSON.toJSONString(jsdmModel.get(0)));

            //未到人数校验
            if (jsdmModel.get(0).getWdrybh()!=null){
                String[] list=jsdmModel.get(0).getWdrybh().split(",");
                int wdrs=Integer.parseInt(jsdmModel.get(0).getWdrs());
                if (list.length!=wdrs){
                    return ResponseMessage.error("未到人员编号的数量与未到人数不匹配");
                }
            }

            jsdmModel.get(0).setCreatetime(new Date());
            jsdmModel.get(0).setState("R2");
            jsdmModel.get(0).setAppcode(appcode);
            jsdmModel.get(0).setJsbh(jsbh);
            JsdmModel jsdmModel1 = jsdmModel.get(0);
            System.err.println("jsdmModel1--"+ JSON.toJSONString(jsdmModel1));
            ResponseMessage<String> jsdmModel11 = jwpServerApis.jsdmSave(jsdmModel1);
            if(jsdmModel11.getStatus() == 200){
                jsdmModel11.setMessage("保存成功!");
            }else{
                jsdmModel11.setMessage("服务异常,保存失败!");
            }
            return jsdmModel11;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}



