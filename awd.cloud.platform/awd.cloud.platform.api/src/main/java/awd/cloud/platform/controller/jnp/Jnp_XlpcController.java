package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.XlpcModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
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
@RequestMapping("/v4/jnp/xlpc")
@Api(tags = "jnp-xlpc",description = "xlpc")
public class Jnp_XlpcController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * 心理评测权限接口
     * @api {get} /v4/jnp/xlpc/xlpcQuery 心理评测查询
     * @apiVersion 0.4.0
     * @apiName xlpcQuery
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 心理评测查询.
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} df         				    得分
     * @apiSuccess {String} sjid         				试卷id
     * @apiSuccess {String} rybh         				人员编号
     * @apiSuccess {String} cpsj         				测评时间
     * @apiSuccess {String} da         				    答案
     * @apiSuccess {String} jsh         				监室号
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "df": 10,
     *         "sjid": "2019120280001",
     *         "rybh": "112212331",
     *         "cpsj": "2019-12-12 00:00:00",
     *         "da": "A,B,A,C,D",
     *         "jsh": "0101"
     *       }],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576552534787
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     * {
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大长度:9)"
     *   "entity": [
     *     {
     *       "jsh": "监室号（非必填，最大长度：4）",
     *       "rybh":"人员编号(非必填,最大长度：21)"
     *     }
     *   ]
     * }
     *
     */
    @OpenAPI
    @ApiOperation("心理评测查询")
    @GetMapping("/xlpcQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xlpc_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/xlpc/xlpcQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }


        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.xlpcByjbxxQueryForPage(param);
        System.err.println("result"+ JSON.toJSONString(result));




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
     * @api {post} /v4/jnp/xlpc/xlpcSave 心理评测保存
	 * @apiVersion 0.4.0
     * @apiName xlpcSave
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 心理评测保存
     *
     * @apiParam {String} appcode 								应用代码(必填)
     * @apiParam {String} jsbh 									监所编号(必填)(最大长度:9)
     * @apiParam {String} json 									保存参数集
     *
     * @apiSuccess {String} message         				    成功信息
     * @apiSuccess {String} result         				        生成的主键信息
     *
     * @apiSuccess {String} df         				            得分
     * @apiSuccess {String} id         				            保存成功后返回的id
     * @apiSuccess {String} rybh         				        人员编号
     * @apiSuccess {String} jsbh         				        监所编号
     * @apiSuccess {String} jsh         				        监室号
     *
     * @apiSuccess {String} status         				        代码
     * @apiSuccess {String} timestamp         				    时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *     "message": null,
     *     "result": {
     *        "df": 32,
     *        "id": "11000011420191230000013",
     *        "rybh": "110000114201910160001",
     *        "jsbh": "110000114",
     *        "jsh": "0101"
     *   },
     *     "status": 200,
     *     "timestamp": 1576562125923
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample Example usage:
     * appcode:""
     * jsbh:""
     * json:{
     *          "entity":[
     *            {
     *              "jsh":"监室号(必填; 最大长度:4)",
     *              "rybh":"人员编号(必填; 最大长度:21)",
     *              "creator":"登录名(必填; 最大长度:50)",
     *              "sjid":"试卷id(必填; 最大长度:23)",
     *              "da":"答案(必填; 最大长度:255;将所有选择项答案按题号顺序以逗号拼接，如：A,B,A,C,D)",
     *              "cpsj":"测评时间(必填; 格式:yyyy-MM-dd hh:mm:ss)"
     *            }
     *          ]
     *        }
     */
    @ApiOperation("心理评测新增")
    @PostMapping("/xlpcSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String,Object>> xlpc_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/xlpc/xlpcSave";
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
            System.err.println("map+ "+ map.toString());

            List<XlpcModel> xlpcmodel = JSONArray.parseArray(map.get("entity").toString(), XlpcModel.class);
            System.err.println("xlpcmodel--"+ JSON.toJSONString(xlpcmodel.get(0)));
            xlpcmodel.get(0).setCreatetime(new Date());
            xlpcmodel.get(0).setState("R2");
            xlpcmodel.get(0).setAppcode(appcode);
            xlpcmodel.get(0).setJsbh(jsbh);
            XlpcModel model = xlpcmodel.get(0);
            System.err.println("xlpcmodel--"+JSON.toJSONString(model));
            ResponseMessage<Map<String,Object>> result = jwpServerApis.xlpcSave(model);
            System.err.println("result--"+JSON.toJSONString(result));
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
                //返回值设置
                result.getResult().put("rybh",model.getRybh());
                result.getResult().put("jsbh",jsbh);
                result.getResult().put("jsh",model.getJsh());

            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }
}
