package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.XjModel;
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
@RequestMapping("/v4/jwp/xj")
@Api(tags = "jwp-xj", description = "xj")
public class Jwp_XjController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {post} /v4/jwp/xj/xjsydjSave     械具使用登记保存
     * @apiVersion 0.4.0
     * @apiName xjsydjSave
     * @apiGroup g_jwp
     * @apiPermission user
     *
     * @apiDescription 械具使用登记保存
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
     * @apiUse CreateError
     *
     * @apiExample Example usage:
     *
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     *        "entity":[
     *          {
     *            "rybh":"人员编号(必填; 最大长度:21)",
     *            "xm":"姓名(必填; 最大长度:10)",
     *            "xjmc":"械具名称(必填; 最大长度:30)",
     *            "syyy":"使用原因(必填;)",
     *            "bllx":"办理类型(必填; 字典:1,发放 2,收回; 最大长度:1)",
     *            "blr":"办理人(必填; 最大长度:20)",
     *            "blsj":"领取时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *          }
     *        ]
     *      }
     */
    @OpenAPI
    @ApiOperation("械具使用登记保存")
    @PostMapping("/xjsydjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> xjsydj_save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        //{"entity":[{"rybh":"\\d{1,21}","xm":"\\S{1,10}","xjmc":"\\S{1,30}","bllx":"\\d{1}","blr":"\\S{1,20}","blsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}]}
        String interfaceId = "/v4/jwp/xj/xjsydjSave";
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

            List<XjModel> xjModel = JSONArray.parseArray(map.get("entity").toString(), XjModel.class);
            System.err.println("xjModel--"+ JSON.toJSONString(xjModel.get(0)));
            xjModel.get(0).setCreatetime(new Date());
            xjModel.get(0).setCreator("管理员");
            xjModel.get(0).setState("R2");
            xjModel.get(0).setAppcode(appcode);
            xjModel.get(0).setJsbh(jsbh);
            XjModel model = xjModel.get(0);
            System.err.println("xjModel1--"+ JSON.toJSONString(model));
            ResponseMessage<String> xjModel11 = jwpServerApis.xjSave(model);
            System.err.println("xjModel11--"+ JSON.toJSONString(xjModel11));
            if(xjModel11.getStatus() == 200){
                xjModel11.setMessage("保存成功!");
            }else{
                xjModel11.setMessage("服务异常,保存失败!");
            }
            return xjModel11;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }
}
