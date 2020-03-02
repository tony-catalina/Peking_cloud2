package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
import awd.cloud.platform.model.jwp.ThjlModel;
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
@RequestMapping("/v4/jnp/thjl")
@Api(tags = "jnp-thjl",description = "thjl")
public class Jnp_ThjlController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;


    @OpenAPI
    @ApiOperation("分页查询")
    @GetMapping("/thjlQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> thjl_query(HttpServletRequest request, String json) {
        return null;
    }

    /**
     * @api {post} /v4/jnp/thjl/thjlSave 管教谈话保存
     * @apiVersion 0.4.0
     * @apiName thjlSave
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 管教谈话保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)"
     * jsbh:"监所编号(必填; 最大长度:9)"
     * json:{
     *        "entity":[
     *          {
     *            "jsh":"监室号(必填; 最大长度:4)",
     *            "rybh":"人员编号(必填; 最大长度:21)",
     *            "thmj":"谈话民警(必填; 最大长度:50)",
     *            "thkssj":"谈话开始时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "thjssj":"谈话结束时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *            "thnrurl":"谈话内容音频文件地址(必填; 最大长度:100)"
     *          }
     *        ]
     *      }
     */
    @OpenAPI
    @ApiOperation("管教谈话新增")
    @PostMapping("/thjlSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})

    public ResponseMessage<String> thjl_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/thjl/thjlSave";
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
            List<ThjlModel> thjlModels = JSONArray.parseArray(map.get("entity").toString(), ThjlModel.class);
            System.err.println("fyqrmodel--"+ JSON.toJSONString(thjlModels.get(0)));
            thjlModels.get(0).setCreatetime(new Date());
            thjlModels.get(0).setState("R2");
            thjlModels.get(0).setAppcode(appcode);
            thjlModels.get(0).setJsbh(jsbh);
            ThjlModel thjlModel = thjlModels.get(0);
            System.err.println("fyqrmodel--"+JSON.toJSONString(thjlModel));
            ResponseMessage<String> fyqrModel = jwpServerApis.thjlSave(thjlModel);
            return fyqrModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


}
