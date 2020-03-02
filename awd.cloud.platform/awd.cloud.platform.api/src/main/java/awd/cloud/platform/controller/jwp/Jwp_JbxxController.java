package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v4/jwp/jwp_jbxx")
@RefreshScope
@Api(tags = "jwp-jbxx",description = "jbxx")
public class Jwp_JbxxController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp/jwp_jbxx/getMoreCustom 基本信息流程查询
     * @apiVersion 0.4.0
     * @apiName getMoreCustom
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 基本信息流程查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     *
     * @apiSuccess {String}photourl         				                 人员照片地址
     * @apiSuccess {String}ywlx                                              业务类型
     * @apiSuccess {String}snbh         				                     所内编号
     * @apiSuccess {String}rybh                                              人员照片
     * @apiSuccess {String}crjbjString                                       出入监标记(已转换)
     * @apiSuccess {String}ywlcid                                            业务流程id
     * @apiSuccess {String}crjbj                                             出入监标记
     * @apiSuccess {String}taskid                                            任务编号
     * @apiSuccess {String}jsh                                               监室号
     *
     * @apiSuccess {String}page                                              当前页
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total":1
     *     "data":[
     *       {
     *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/1E/wKgEMl2QeAeADFuQAAAIiecvuh8199.jpg",
     *         "ywlx": "Lscs",
     *         "snbh": "20190059",
     *         "xm": "打算",
     *         "rybh": "110000111201907120015",
     *         "crjbjString": "临时出所",
     *         "ywlcid": "5123827",
     *         "crjbj": "01",
     *         "taskid": "5124201",
     *         "jsh": "0101"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576838901699
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          "rybh":"人员编号(最大字段长度：21)",
     *          "jsh":" 监室号  (最大字段长度：4)",
     *          "type":"type",
     *          "page":"当前页数",
     *          "pageSize":"一页数据数量"
     *          }
     *
     */
    @GetMapping("/getMoreCustom")
    @ResponseBody
    @OpenAPI
    public ResponseMessage<Map<String, Object>> getMoreCustom(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/jwp/jwp_jbxx/getMoreCustom";
        String state = request.getParameter("state");
        String type = "1";
        String jsh = "0101";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("type"))) {
                type = (String)maps.getResult().get("type");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                jsh = (String)maps.getResult().get("jsh");
            }

//            DefaultQueryParam.addDefaultQueryParam(request, param, state);
//            System.err.println("param--" + JSON.toJSONString(param));
//            ResponseMessage<PagerResult<Map<String, Object>>>

            ResponseMessage<Map<String, Object>> result = jwpServerApis.getMoreCustom(jsbh,type,jsh);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().get("data"));
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().get("total"));
            maplist.put("page", request.getParameter("page"));
            ResponseMessage<Map<String, Object>> list = this.kfzdShow(maplist);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }



    /**
     * @api {get} /v4/jwp/jwp_jbxx/jbxxShow 认证接口查询
     * @apiVersion 0.4.0
     * @apiName jbxxShow
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 认证接口查询
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}xm         				                         姓名
     * @apiSuccess {String}xq         				                         刑期
     * @apiSuccess {String}cwh         				                         床位号

     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *{
     *   "message": "查询成功",
     *   "result": {
     *     "total": 8,
     *     "data": [
     *       {
     *         "xm": "李无力",
     *         "cwh": "01",
     *         "xq": "0,1,1"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577433854036
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "jsh":"监室号(最大字段长度：4)",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */

    @OpenAPI
    @ApiOperation("认证接口查询")
    @GetMapping("/jbxxShow")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> jbxxShow(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jbxx/jbxxShow";
        String state = request.getParameter("state");
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);

        ResponseMessage<PagerResult<Map<String, Object>>> result= kssServerApis.queryForPage(param);
        System.err.println("result"+ JSON.toJSONString(result));

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", result.getResult().getData());
        maplist.put("interfaceId", interfaceId);
        maplist.put("total", result.getResult().getTotal());
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

}