package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.WgsjclModel;
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
@RequestMapping("/v4/jwp/wgsjcl")
@Api(tags = "jwp-wgsjcl",description = "wgsjcl")
public class Jwp_WgsjclController extends PublicService{
    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {post} /v4/jwp/wgsjcl/wgdjSave   违规登记保存
     * @apiVersion 0.4.0
     * @apiName wgdjSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription  违规登记保存
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
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
     *               "clqk":"处理情况(必填; 最大字段长度：50)",
     *               "dxbh":" 对象编号(必填; 最大字段长度：21)",
     *               "wgsj":"违规时间(必填; 格式：yyyy-MM-dd HH:mm:ss)",
     *               "creator":"创建人(必填; 最大字段长度：50)",
     *               "wgqk":"违规情况(必填; 最大字段长度：100)",
     *               "wgqkcon":"违规详细情况"
     *               }]
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("违规登记保存")
    @PostMapping("/wgdjSave ")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> xdrzSave (HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/wgsjcl/wgdjSave";

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
                return ResponseMessage.error(msg.getMessage());
            }

            List<WgsjclModel> wgsjclList = JSONArray.parseArray(map.get("entity").toString(), WgsjclModel.class);
            String wgqkcon = wgsjclList.get(0).getWgqkcon();
            if(null==wgqkcon || " " .equals(wgqkcon)){
                return ResponseMessage.error("请输入违规详细情况");
            }

            WgsjclModel wgsjclmodel= wgsjclList.get(0);
            wgsjclmodel.setJsbh(jsbh);
            wgsjclmodel.setCreatetime(new Date());
            wgsjclmodel.setState("R2");

            ResponseMessage<String> jstz2Model = kssServerApis.wgdjSave(wgsjclmodel);

            if(jstz2Model.getStatus() == 200){
                jstz2Model.setMessage("保存成功!");
            }else{
                jstz2Model.setMessage("服务异常,保存失败!");
            }
            return jstz2Model;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
