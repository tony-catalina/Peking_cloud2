/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.jnp;

import awd.bj.base.model.Variables;
import awd.bj.kss.model.JbxxModel;
import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.api.WorkFlowService;
import awd.cloud.platform.model.kss.Kss_JjwpsljlModel;
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
@RequestMapping("/v4/jnp/jjwpsljl")
@Api(tags = "jnp-jjwpsljl", description = "Jjwpsljl")
public class Jnp_JjwpsljlController extends PublicService {

    @Autowired
    private KssService kssService;

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private WorkFlowService workFlowService;


    @OpenAPI
    @ApiOperation("分页查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<Kss_JjwpsljlModel>> jjwpsljl_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_JjwpsljlModel>> result = kssService.jjwpsljl_query(queryParam);
        if (result.getStatus() == 200) {
            result.setMessage("查询成功");
            if (result.getResult() == null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
    }

    /**
     * @api {post} /v4/jnp/jjwpsljl/jjwpsqSave 救济物品申请保存
     * @apiVersion 0.4.0
     * @apiName jjwpsqSave
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 救济物品申请保存.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json  											保存参数集(必填)
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

     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{"entity":[{
     *               "xm":"姓名(必填; 最大字段长度：50)",
     *               "snbh":"所内编号(必填; 最大字段长度：8)",
     *               "rybh":"人员编号(必填; 最大字段长度：21)",
     *               "jsh":"监室号 (必填; 最大字段长度：4)",
     *               "wp":" 物品(必填)",
     *               "jjrq":"救济日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "creator":"管理员(必填; 最大字段长度：30)",
     *               "djsj":"登记时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *               "jjyy":"救济原因(必填)"
     *               }]
     *          }
     *
     * @apiUse CreateError
     */
    @ApiOperation("救济物品申请保存")
    @PostMapping("/jjwpsqSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, String>> jjwpsq_save(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/jjwpsljl/jjwpsqSave";
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
            List<JbxxModel> jbxxModels = JSONArray.parseArray(map.get("entity").toString(), JbxxModel.class);
            List<Kss_JjwpsljlModel> jjwpModels = JSONArray.parseArray(map.get("entity").toString(), Kss_JjwpsljlModel.class);
            Variables variables = new Variables();
            System.err.println("rybh----------"+jbxxModels.get(0).getRybh());
            variables.setRybh(jbxxModels.get(0).getRybh());
            variables.setJsbh(jsbh);
            variables.setProcessDefinitionKey(CacheUtils.get().getFlowKey("kss_jjwpsl".toUpperCase()).split(":")[0]);
            System.err.println(variables.getProcessDefinitionKey()+"-----------------");
            System.err.println("variables=="+JSON.toJSONString(variables));
            List<Map<String, Object>> obj = (List<Map<String, Object>>) workFlowService.findPersonalTaskList("admin", "1", variables).getResult();
            System.err.println("obj============="+obj.size());
            if (obj.size()>0) {
                return ResponseMessage.error("已存在于该流程中，无法重新办理！！");
            }
            System.err.println(map.get("entity").toString());

            System.err.println("jbxxModels--"+JSON.toJSONString(jbxxModels.get(0)));
            System.err.println("jjwpModels--"+JSON.toJSONString(jjwpModels.get(0)));
            jjwpModels.get(0).setState("R2");
            jjwpModels.get(0).setJsbh(jsbh);
            jjwpModels.get(0).setDjr(jjwpModels.get(0).getCreator());
            jjwpModels.get(0).setCreatetime(new Date());
            jjwpModels.get(0).setWp(jjwpModels.get(0).getWp());
            jjwpModels.get(0).setPsbz("0");
            jjwpModels.get(0).setPastable("1");
            jbxxModels.get(0).setXm(jbxxModels.get(0).getXm());
            jbxxModels.get(0).setSnbh(jbxxModels.get(0).getSnbh());
            jbxxModels.get(0).setJsh(jbxxModels.get(0).getJsh());
            Map<String,Object> jjwpMap = new HashMap();
            jjwpMap.put("lcid",CacheUtils.get().getFlowKey("KSS_JJWPSL"));
            jjwpMap.put("jbxxEntity",jbxxModels.get(0));
            jjwpMap.put("jjwpsljlEntity",jjwpModels.get(0));
            System.err.println("jjwpMap--"+JSON.toJSONString(jjwpMap));
            ResponseMessage<Map<String, String>> jjwpsq = kssServerApis.jjwpsladdFlow(jjwpMap);
            System.err.println("--"+JSON.toJSONString(jjwpsq));
            if(jjwpsq.getStatus() == 200){
                jjwpsq.setMessage("保存成功!");
            }else{
                jjwpsq.setMessage("服务异常,保存失败!");
            }
            return jjwpsq;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }



    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jjwpsljl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_JjwpsljlModel data) {
        return kssService.jjwpsljl_updateByKey(id, data);
    }


    @OpenAPI
    public ResponseMessage<Kss_JjwpsljlModel> jjwpsljl_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.jjwpsljl_getByKey(id);
    }



    @OpenAPI
    public ResponseMessage<Integer> jjwpsljl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.jjwpsljl_deleteByKey(id);
    }
}
