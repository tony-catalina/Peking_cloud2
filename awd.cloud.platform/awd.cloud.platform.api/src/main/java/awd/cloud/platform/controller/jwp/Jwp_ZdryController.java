package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.ZdryModel;
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
@RequestMapping("/v4/jwp/jwp_zdry")
@Api(tags="jwp_zdry",description="jwp_zdry")
public class Jwp_ZdryController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jwp/jwp_zdry/zdryList 重点人员查询
     * @apiVersion 0.4.0
     * @apiName zdryList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 重点人员查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}xm         				                        姓名
     * @apiSuccess {String}jsbh         				                    监所编号
     * @apiSuccess {String}snbh                                             所内编号
     * @apiSuccess {String}xb                                               性别
     * @apiSuccess {String}xbString                                         性别(字典转换后)
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 6,
     *     "data": [
     *       {
     *         "xbString": "女性",
     *         "snbh": "20190045",
     *         "xm": "结界",
     *         "rybh": "110000111201907120001",
     *         "xb": "2",
     *         "jsbh": "110000114"，
     *         "jsh":"1001"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577352930257
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *    json:{
     *    "jsh":"监室号(最大字段长度：4)"，
     *    "type":"男女类别(字典 XB)"
     *    }
     *
     */
    @OpenAPI
    @ApiOperation("重点人员查询")
    @GetMapping("/zdryList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
    @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> zdry_query(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_zdry/zdryList";
        String state = "R8";
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }
        String zdry = "1";
        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jsh", TermType.eq,maps.getResult().get("jsh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("type"))) {
            param.and("xb", TermType.eq,maps.getResult().get("type"));
        }

        Term term = new Term();
        term.and("state", state).and("zdry", zdry);
        param.addTerm(term);
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String,Object>>> result=kssServerApis.queryForPage(param);
        System.err.println("result: "+ JSON.toJSONString(result));

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", result.getResult().getData());
        maplist.put("interfaceId", interfaceId);
        maplist.put("total",  result.getResult().getTotal());
        maplist.put("page",  request.getParameter("page"));
        ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
        if(list.getStatus()==200) {
            list.setMessage("查询成功");
            if(list.getResult()==null) {
                list.setMessage("未查询数据");
            }
        }
        return list;
    }

    /**
     * @api {post} /v4/jwp/jwp_zdry/zdrySave 重点人员保存
     * @apiVersion 0.4.0
     * @apiName zdrySave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 重点人员保存.
     *

     * @apiParam {String} appcode 					    应用代码(必填)
     * @apiParam {String} jsbh 							监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 							保存参数集(必填)
     *
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *       "entity":[
     *                {
     *                  "djmj":"登记民警(必填; 最大长度:255)",
     *                  "djsj":"登记时间(必填;格式:yyyy-MM-dd hh:mm:ss)",
     *                  "rybh":"人员编号(必填；最大长度:21)",
     *                  "ssqk":"属实情况(必填；最大长度:255)",
     *                  "xm":"姓名(必填;最大长度:10)"
     *                }
     *              ]
     *     }
     */
    @ApiOperation("重点人员保存")
    @PostMapping("/zdrySave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> zdrySave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jwp/jwp_zdry/zdrySave";
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
            List<ZdryModel> zdryModels = JSONArray.parseArray(map.get("entity").toString(), ZdryModel.class);
            System.err.println("zdryModels--"+ JSON.toJSONString(zdryModels.get(0)));
            zdryModels.get(0).setCreatetime(new Date());
            zdryModels.get(0).setAppcode(appcode);
            zdryModels.get(0).setJsbh(jsbh);
            zdryModels.get(0).setJsh("0101");
            zdryModels.get(0).setCreator("管理员");
            ZdryModel zdrymodel = zdryModels.get(0);
            System.err.println("zdrymodel--"+JSON.toJSONString(zdrymodel));
            ResponseMessage<String> zdryModel = jwpServerApis.zdrySave(zdrymodel);
            if(zdryModel.getStatus() == 200){
                zdryModel.setMessage("保存成功!");
            }else{
                zdryModel.setMessage("服务异常,保存失败!");
            }
            return zdryModel;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }


}
