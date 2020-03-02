package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.entity.YsxzEntity;
import awd.cloud.platform.entity.YzEntity;
import awd.cloud.platform.model.kss.Kss_JbxxModel;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jwp/ysxz")
@Api(tags = "jwp-ysxz",description = "ysxz")
public class Jwp_YsxzController extends PublicService {



    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {post} /v4/jwp/ysxz/ysxzSave 医生巡诊保存
     * @apiVersion 0.4.0
     * @apiName ysxzSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 医生巡诊保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)

     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳

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
     *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{"entity":[{
     *             "brbq":"病人病情(必填)",
     *             "rybh":"人员编号(必填 ; 最大字段长度：21)",
     *             "yszt":"医生嘱托()",
     *             "xzrq":"巡诊日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *             "sffy":"是否发药(必填)",
     *             "fykssj":"发药开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *             "creator":"用户名(必填; 最大字段长度：50)",
     *             "jbmc":"疾病名称(必填；字典：JBMC)",
     *             "zz":"诊断情况(最大字段长度：100)",
     *             "fyjsrq":"发药结束时间(格式：yyyy-MM-dd hh:mm:ss)",
     *             "ypbh":"药品编号(必填)",
     *             "cs":"次数(必填)",
     *             "sl":"数量(必填)",
     *             "fyjg":"服药间隔(必填)",
     *             "fyzysx":"服药注意事项"
     *             }]
     *         }
     *
     */
    @OpenAPI
    @ApiOperation("医生巡诊保存")
    @PostMapping("/ysxzSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Integer> ysxz_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/ysxz/ysxzSave";
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


            List<YsxzEntity> ysxzEntityList = JSONArray.parseArray(map.get("entity").toString(), YsxzEntity.class);
            List<YzEntity> YzEntityList = JSONArray.parseArray(map.get("entity").toString(), YzEntity.class);
            List<Kss_JbxxModel> JbxxModelList = JSONArray.parseArray(map.get("entity").toString(), Kss_JbxxModel.class);


            YsxzEntity ysxzEntity = ysxzEntityList.get(0);
            YzEntity yzEntity = YzEntityList.get(0);
            Kss_JbxxModel jbxxModel = JbxxModelList.get(0);

            //jbxx实体类处理
            jbxxModel.setJsbh(jsbh);
            System.err.println("jbxxModel--"+ JSON.toJSONString(jbxxModel));

            //yz实体类处理
            yzEntity.setJsbh(jsbh);
            yzEntity.setState("R2");
            yzEntity.setLy("医生巡诊");
            System.err.println("yzEntity--"+ JSON.toJSONString(yzEntity));

            //ysxz实体类处理
            ysxzEntity.setJsbh(jsbh);
            ysxzEntity.setState("R2");
            System.err.println("ysxzEntity--"+ JSON.toJSONString(ysxzEntity));

            List<Map> list = JSONArray.parseArray(map.get("entity").toString(), Map.class);
            String  ypbh = (String) list.get(0).get("ypbh");
            String  fyzysx = (String) list.get(0).get("fyzysx");
            String  cs = (String) list.get(0).get("cs");
            String  sl = (String) list.get(0).get("sl");
            String  fyjg = (String) list.get(0).get("fyjg");

            Map<String,Object> param=new HashMap();
                param.put("ysxzModel",ysxzEntity);
                param.put("yzModel",yzEntity);
                param.put("jbxxModel",jbxxModel);
                Map<String,Object> yzmxMap = new HashMap<>();
                yzmxMap.put("ypbh",ypbh);
                yzmxMap.put("fyzysx",fyzysx);
                yzmxMap.put("cs",cs);
                yzmxMap.put("sl",sl);
                yzmxMap.put("fyjg",fyjg);
                param.put("yzmx",yzmxMap);

            ResponseMessage<Integer> ysxz = kssServerApis.ysxzSave(param);

            if(ysxz.getStatus() == 200){
                ysxz.setMessage("保存成功!");
            }else{
                ysxz.setMessage("服务异常,保存失败!");
            }
            return ysxz;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
