package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.YyysModel;
import awd.cloud.platform.model.jwp.ZbqdModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/zbqd")
@Api(tags = "jnp-zbqd",description = "zbqd")
public class Jnp_ZbqdController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {post} /v4/jnp/zbqd/zbqdSave  值班签到保存
     * @apiVersion 0.4.0
     * @apiName zbqdSave
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 值班签到保存.
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							保存参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "保存成功",
     *   "result": "11000011420191214000006",
     *   "status": 200,
     *   "timestamp": 1576308324918
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大长度:9)"
     *   "entity": [
     *     {
     *       "jsh": "监室号（必填，最大长度：4）",
     *       "rybh": "人员编号（必填，最大长度：21）",
     *       "wjsyy": "未及时签到原因（）",
     *       "qdsj":"签到时间(必填;格式: yyyy-MM-dd hh:mm:ss)"
     *     }
     *   ]
     * }
     *
     */
    @OpenAPI
    @ApiOperation("值班签到保存")
    @PostMapping("/zbqdSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> zbqd_save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/zbqd/zbqdSave";
        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            System.err.println("map: "+map);
            ResponseMessage<String> msg = this.modelYz(map);
            if(msg.getStatus()!=200) {
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println("entity: "+map.get("entity").toString());
            List<ZbqdModel> zbqdModels = JSONArray.parseArray(map.get("entity").toString(), ZbqdModel.class);
            System.err.println("zbqdmodel--"+ JSON.toJSONString(zbqdModels.get(0)));
            zbqdModels.get(0).setCreatetime(new Date());
            zbqdModels.get(0).setState("R2");
            zbqdModels.get(0).setAppcode(appcode);
            zbqdModels.get(0).setJsbh(jsbh);
            ZbqdModel zbqdModel = zbqdModels.get(0);
            System.err.println("zbqdmodel--"+JSON.toJSONString(zbqdModel));
            ResponseMessage<String> yyysModel = jwpServerApis.zbqdSave(zbqdModel);
            System.err.println("--"+JSON.toJSONString(yyysModel));
            if(yyysModel.getStatus() == 200){
                yyysModel.setMessage("保存成功!");
            }else{
                yyysModel.setMessage("服务异常,保存失败!");
            }
            return yyysModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }
}
