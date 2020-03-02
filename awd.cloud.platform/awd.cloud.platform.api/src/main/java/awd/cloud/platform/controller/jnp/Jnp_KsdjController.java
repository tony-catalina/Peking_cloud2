package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.KsdjModel;
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
@RequestMapping("/v4/jnp/ksdj")
@Api(tags = "jnp-ksdj",description = "ksdj")
public class Jnp_KsdjController  extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;
    /**
     * @api {post} /v4/jnp/ksdj/ksdjSave 可视对讲保存
     * @apiVersion 0.4.0
     * @apiName ksdjSave
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 可视对讲保存.
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
     *               "rybh":"人员编号(必填; 最大字段长度：21)",
     *               "jsh":" 监室号  (必填; 最大字段长度：4)",
     *               "djsj":"登记时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "djsy":"登记事由(必填; 字典（sy）)",
     *               "mjqr":"民警确认(必填; 最大字段长度：10)"
     *               }]
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("可视对讲保存")
    @PostMapping("/ksdjSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> ksdj_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/ksdj/ksdjSave";
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

            List<KsdjModel> ksdjModel = JSONArray.parseArray(map.get("entity").toString(), KsdjModel.class);
            System.err.println("ksdjModel--"+JSON.toJSONString(ksdjModel.get(0)));
            ksdjModel.get(0).setCreatetime(new Date());
            ksdjModel.get(0).setState("R2");
            ksdjModel.get(0).setAppcode(appcode);
            ksdjModel.get(0).setJsbh(jsbh);
            KsdjModel ksdjModel1 = ksdjModel.get(0);
            System.err.println("ksdjModel1--"+ JSON.toJSONString(ksdjModel1));
            ResponseMessage<String> djksModel = jwpServerApis.ksdjSave(ksdjModel1);
            if(djksModel.getStatus() == 200){
                djksModel.setMessage("保存成功!");
            }else{
                djksModel.setMessage("服务异常,保存失败!");
            }
            return djksModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
