package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.XdrzModel;
import awd.cloud.platform.api.KssServerApis;
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
@RequestMapping("/v4/jwp/jwp_xdrz")
@Api(tags="jwp_xdrz",description="jwp_xdrz")
public class Jwp_XdrzController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp/jwp_xdrz/xdrzList 消毒日志查询
     * @apiVersion 0.4.0
     * @apiName xdrzList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 消毒日志查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}blr          				                    办理人
     * @apiSuccess {String}blsj         				                    办理时间
     * @apiSuccess {String}bz                                               备注
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}createtor                                        创建人
     * @apiSuccess {String}id                                               ID
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}state                                            状态 字典（YWSTATE）
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}updatetor                                        更新人
     * @apiSuccess {String}xddd                                             消毒地点
     * @apiSuccess {String}xdff                                             消毒方式 字典（XDFF）
     * @apiSuccess {String}xdffString                                       消毒方式
     * @apiSuccess {String}xdrq                                             消毒日期
     * @apiSuccess {String}xdry                                             消毒人员
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 63,
     *     "data": [
     *       {
     *         "createtime": "2019-11-22 15:08:10",
     *         "blr": "办理人",
     *         "xdffString": "紫外线消毒",
     *         "xdry": "消毒人员",
     *         "blsj": "2019-11-22 13:45:28",
     *         "xdrq": "2019-11-22",
     *         "xdff": "2",
     *         "bz": "多线程自动测试",
     *         "id": "00000000020191122000551",
     *         "state": "R2",
     *         "updatetime": "2020-01-23 11:57:10",
     *         "jsbh": "110000114",
     *         "updatetor": "123",
     *         "createtor": "自动",
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577356008049
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *    json:{
     *    "xdrqStart":"消毒开始时间( 格式：yyyy-MM-dd hh:mm:ss)",
     *    "xdrqEnd":"消毒结束时间( 格式：yyyy-MM-dd hh:mm:ss)"
     *    }
     *
     */
    @OpenAPI
    @ApiOperation("消毒日志查询")
    @GetMapping("/xdrzList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
    @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> zdry_query(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_xdrz/xdrzList";
        String state = request.getParameter("state");
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xdrqStart"))) {
            param.and("xdrq", TermType.gte, maps.getResult().get("xdrqStart"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xdrqEnd"))) {
            param.and("xdrq", TermType.lte, maps.getResult().get("xdrqEnd"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.xdrzQuery(param);
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
     * @api {post} /v4/jwp/jwp_xdrz/xdrzSave 消毒日志保存
     * @apiVersion 0.4.0
     * @apiName xdrzSave
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 消毒日志保存.
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
     *               "xdry":"消毒人员(必填; 最大字段长度：100)",
     *               "xdff":" 消毒方式(必填; 最大字段长度：2; 字典(XDFF))",
     *               "xdrq":"消毒日期(必填; 格式：yyyy-MM-dd)",
     *               "xddd":"消毒地点(必填)",
     *               "creator":"创建人(必填; 最大字段长度：20)"
     *               }]
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("消毒日志保存")
    @PostMapping("/xdrzSave ")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> xdrzSave (HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp_xdrz/xdrzSave";


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

            List<XdrzModel> xdrzList = JSONArray.parseArray(map.get("entity").toString(), XdrzModel.class);
            String xddd = xdrzList.get(0).getXddd();
            if(null==xddd || " " .equals(xddd)){
                return ResponseMessage.error("请输入消毒地点");
            }

            XdrzModel xdrzmodel= xdrzList.get(0);
            xdrzmodel.setJsbh(jsbh);
            xdrzmodel.setCreatetime(new Date());
            xdrzmodel.setState("R2");

            ResponseMessage<String> jstz2Model = kssServerApis.xdrzSave(xdrzmodel);

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
