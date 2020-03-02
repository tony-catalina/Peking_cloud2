package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.ThjyModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
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

/**
 * Author：张延
 * Date：2019-12-27 11:10
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jwp-thjl")
@Api(tags = "jwp-thjl",description = "jwp-thjl")
public class Jwp_ThjyController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/jwp/jwp-thjl/thjlSave  个性教育保存
     * @apiVersion 0.4.0
     * @apiName thjlSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 个性教育保存.
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
     *               "thyy":"谈话原因(必填; 最大字段长度：200)",
     *               "thnr":" 谈话内容(必填)",
     *               "jssj":"谈话结束时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "kssj":"谈话开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "creator":"创建人(必填; 最大字段长度：50)",
     *               "fzmj":"负责民警(必填; 最大字段长度：30)",
     *               "thdd":"谈话地点(必填; 最大字段长度：30)",
     *               "rybh":"人员编号(必填;最大字段长度：21)"
     *               }]
     *          }
     *
     */

    @OpenAPI
    @ApiOperation("个性教育保存")
    @PostMapping("/thjlSave ")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> thjlSave (HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp-thjl/thjlSave";

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

            System.err.println(map.get("entity").toString());

            List<ThjyModel> thnrList = JSONArray.parseArray(map.get("entity").toString(), ThjyModel.class);
            String thnr = thnrList.get(0).getThnr();

              if(null==thnr || "".equals(thnr)){
                  return ResponseMessage.error("请输入谈话内容");
              }

            ThjyModel thjyModel= thnrList.get(0);
            thnrList.get(0).setJsbh(jsbh);
            thnrList.get(0).setCreatetime(new Date());
            thnrList.get(0).setState("R2");

            ResponseMessage<String> thjyModel1 = kssServerApis.thjySave(thjyModel);

            if(thjyModel1.getStatus() == 200){
                thjyModel1.setMessage("保存成功!");
            }else{
                thjyModel1.setMessage("服务异常,保存失败!");
            }
            return thjyModel1;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}