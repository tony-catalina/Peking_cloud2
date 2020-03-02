package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.LscsModel;
import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
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
@RequestMapping("/v4/jwp_lscs")
@Api(tags="jwp_lscs",description="jwp_lscs")
public class Jwp_LscsController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp_lscs/addLscsHsqr 带入监室保存接口
     * @apiVersion 0.4.0
     * @apiName addLscsHsqr
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 带入监室保存接口.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiParam {String}blsj                                             办理时间
     * @apiParam {String}creator         				                    创建人
     * @apiParam {String}rybh                                             人员编号
     * @apiParam {String}ywlcid                                           流程编号
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     * {
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
     *    json:
     *    {
     *      "entity":
     *          [
     *              {
     *                 "blsj":"办理时间(必填; 格式：yyyy-MM-dd hh:mm:ss)2019-12-16 15:32:00",
     *                 "creator":"创建人(必填; 最大字段长度：20)",
     *                 "rybh":"人员编号(必填; 最大字段长度：21)",
     *                 "ywlcid":"业务流程id(必填;)",
     *              }
     *          ]
     *    }
     *
     */
    @OpenAPI
    @ApiOperation("带入监室保存接口")
    @PostMapping("/addLscsHsqr")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
    @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<String> LscsHsqr_save(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp_lscs/addLscsHsqr";
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
            List<LscsModel> lscsModels = JSONArray.parseArray(map.get("entity").toString(), LscsModel.class);
            System.err.println("lscsmodel--"+ JSON.toJSONString(lscsModels.get(0)));
            lscsModels.get(0).setCreatetime(new Date());
            lscsModels.get(0).setState("R2");
//            lscsModels.get(0).setAppcode(appcode);
            lscsModels.get(0).setJsbh(jsbh);
            LscsModel model = lscsModels.get(0);
            System.err.println("fyqrmodel--"+JSON.toJSONString(model));
            ResponseMessage<String> lscsModel = kssServerApis.lscsSave(model);
            return lscsModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


}
