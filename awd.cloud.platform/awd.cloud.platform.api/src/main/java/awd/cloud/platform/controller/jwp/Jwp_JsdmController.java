package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.JsdmModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jsdm")
@Api(tags = "jwp-jsdm",description = "jsdm")
public class Jwp_JsdmController extends PublicService{
    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {post} /v4/jwp/dm/jsdmSave 点名保存
     * @apiVersion 0.4.0
     * @apiName jsdmSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 点名保存.
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
     *                  "dmr":"点名人(必填; 最大长度:50)",
     *                  "jssj":"结束时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "kssj":"开始时间(必填；格式:yyyy-MM-dd hh:mm:ss)",
     *                  "wdrs":"未到人数(必填；最大长度:2)",
     *                  "wdrybh":"未到人员编号(必填;多个参数之间用逗号','隔开)",
     *                  "yy":"原因()"
     *                }
     *              ]
     *     }
     */
    @ApiOperation("点名保存")
    @PostMapping("/jsdmSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jsdmSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/jsdm/jsdmSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            System.err.println("map--"+map);
            List<JsdmModel> jslist = JSONArray.parseArray(map.get("entity").toString(), JsdmModel.class);
            System.err.println("wdrybh--"+ jslist.get(0).getWdrybh());
            if (jslist.get(0).getWdrybh()!=null){
                String[] list=jslist.get(0).getWdrybh().split(",");
                int wdrs=Integer.parseInt(jslist.get(0).getWdrs());
                if (list.length!=wdrs){
                    return ResponseMessage.error("未到人员编号的数量与未到人数不匹配");
                }
            }

            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());
            List<JsdmModel> jsdmmodel = JSONArray.parseArray(map.get("entity").toString(), JsdmModel.class);
            System.err.println("jsdmmodel--"+ JSON.toJSONString(jsdmmodel.get(0)));
            jsdmmodel.get(0).setCreatetime(new Date());
            jsdmmodel.get(0).setState("R2");
            jsdmmodel.get(0).setAppcode(appcode);
            jsdmmodel.get(0).setJsbh(jsbh);
            jsdmmodel.get(0).setJsh("0101");
            jsdmmodel.get(0).setCreator("渣男");
            JsdmModel dmModel = jsdmmodel.get(0);
            System.err.println("dmmodel--"+JSON.toJSONString(dmModel));
            ResponseMessage<String> jsdmModel = jwpServerApis.jsdmSave(dmModel);
            if(jsdmModel.getStatus() == 200){
                jsdmModel.setMessage("保存成功!");
            }else{
                jsdmModel.setMessage("服务异常,保存失败!");
            }
            return jsdmModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
