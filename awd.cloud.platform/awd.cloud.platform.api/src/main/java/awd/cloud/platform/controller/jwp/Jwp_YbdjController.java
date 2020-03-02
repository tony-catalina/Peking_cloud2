package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.YbdjModel;
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
@RequestMapping("/v4/jwp/ybdj")
@Api(tags = "jwp-ybdj", description = "ybdj")
public class Jwp_YbdjController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {post} /v4/jwp/ybdj/ybdjSave     眼镜登记保存
     * @apiVersion 0.4.0
     * @apiName ybdjSave
     * @apiGroup g_jwp
     * @apiPermission user
     * @apiDescription 眼镜登记保存
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
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:{
     * "entity":[
     *   {
     *     "xm":"姓名(必填; 最大长度:50)",
     *     "jsh":"监室号(必填; 最大长度:4)",
     *     "ffmj":"发放民警(必填; 最大长度:50)",
     *     "ffsj":"发放时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     *     "bllx":"办理类型(必填; 字典:1,发放 2,收回)",
     *     "rybh":"人员编号(必填; 最大长度:21)",
     *     "sfffyb":"是否发放用笔(必填; 字典:0,否 1,是)",
     *     "bz":"备注"
     *   }]
     * }
     */
    @OpenAPI
    @ApiOperation("眼镜登记保存")
    @PostMapping("/ybdjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> ybdj_save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        //{"entity":[{"ffmj":"\\S{1,50}","jsh":"\\d{1,4}","ffsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","sfffyb":"\\d{1}","bllx":"\\d{1}","xm":"\\S{1,50}","rybh":"\\d{1,21}"}]}
        String interfaceId = "/v4/jwp/ybdj/ybdjSave";
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

            List<YbdjModel> modelList = JSONArray.parseArray(map.get("entity").toString(), YbdjModel.class);
            System.err.println("model--" + JSON.toJSONString(modelList.get(0)));
            modelList.get(0).setCreatetime(new Date());
            modelList.get(0).setCreator("管理员");
            modelList.get(0).setState("R2");
//            modelList.get(0).setAppcode(appcode);
            modelList.get(0).setJsbh(jsbh);
            System.err.println("modelList--" + JSON.toJSONString(modelList));

            ResponseMessage<String> result = jwpServerApis.ybdjSave(modelList.get(0));
            System.err.println("model11--" + JSON.toJSONString(result));
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
