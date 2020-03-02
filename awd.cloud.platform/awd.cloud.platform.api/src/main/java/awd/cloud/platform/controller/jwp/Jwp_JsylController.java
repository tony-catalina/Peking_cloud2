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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jwp_jsyl")
@Api(tags="jwp_jsyl",description="jwp_jsyl")
public class Jwp_JsylController extends PublicService {

    @Autowired
    private JwpServerApis jwpServerApis;

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp/jwp_jsyl/djcxList 风险登记查询
     * @apiVersion 0.4.0
     * @apiName djcxList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 风险登记查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}zbh         				                        重病号
     * @apiSuccess {String}ejfx         				                    二级风险
     * @apiSuccess {String}yjfx                                             一级风险
     * @apiSuccess {String}sjfx                                             三级风险
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "zbh": 2,
     *         "ejfx": 1,
     *         "yjfx": 3,
     *         "sjfx": 3
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577352311038
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *    json:{"jsh":"监室号(必填; 最大字段长度：4)"}
     *
     */
    @OpenAPI
    @ApiOperation("风险登记查询")
    @GetMapping("/djcxList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
    @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> djcx_query(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/djcxList";
        String state = request.getParameter("state");
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        String jsh = maps.getResult().get("jsh").toString() == null?"":maps.getResult().get("jsh").toString();
        Map<String,Object>result= jwpServerApis.fxdjCxQuery(jsbh,jsh);
        System.err.println("result"+ JSON.toJSONString(result));

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        List<Map<String, Object>> entitylist = (List<Map<String, Object>>) JSON.parse(JSON.toJSONString(result.get("djcx")));
        if(entitylist.size()==1 && "".equals(entitylist.get(0))){
            maplist.put("entity", new Object[0]);
            maplist.put("total", 0);
        }else{
            maplist.put("entity", result.get("djcx"));
            maplist.put("total", entitylist.size());
        }
        maplist.put("interfaceId", interfaceId);
        System.out.println("entitylist: "+entitylist);

//        maplist.put("page",  request.getParameter("page"));
        System.err.println("maplist: "+maplist);
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
     * @api {get} /v4/jwp/jwp_jsyl/jsylList 监室预览查询
     * @apiVersion 0.4.0
     * @apiName jsylList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 监室预览查询.
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}wxdj         				                    危险登记
     * @apiSuccess {String}jkzk         				                    健康状况
     * @apiSuccess {String}xm                                               人员姓名
     * @apiSuccess {String}rsrq                                             入所日期
     * @apiSuccess {String}sfffyb                                           是否发放用笔
     * @apiSuccess {String}rybh                                             人员编号
     * @apiSuccess {String}gyqx                                             关押期限
     * @apiSuccess {String}bhlx                                             病号类型
     * @apiSuccess {String}photosUrl                                        人员照片地址
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}sfffyj                                           是否发放眼镜
     *
     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "wxdj": "重大三级",
     *         "jkzk": "有生理缺陷",
     *         "xm": "发射点",
     *         "rsrq": "2019-06-16 16:04:49",
     *         "sfffyb": "0",
     *         "rybh": "110000111201907120003",
     *         "gyqx": "2019-08-16",
     *         "bhlx": "2",
     *         "photosUrl": "null",
     *         "jsh": "0101",
     *         "sfffyj": "0"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577352311038
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *    json:{"jsh":"监室号(必填; 最大字段长度：4)"}
     *
     */
    @OpenAPI
    @ApiOperation("监室预览查询")
    @GetMapping("/jsylList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> jsyl_query(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/jsylList";
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        String jsh = "";
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            jsh = maps.getResult().get("jsh").toString();
        }else{
            return ResponseMessage.error("监室号必填");
        }

        System.err.println("jsbh: "+jsbh);
        System.err.println("jsh: "+jsh);
        Map<String,Object> result= jwpServerApis.ryxxQuery(jsbh,jsh);
        System.err.println("result"+ JSON.toJSONString(result));

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", result.get("ryxx"));
        maplist.put("interfaceId", interfaceId);
        List<Map<String, Object>> entitylist = (List<Map<String, Object>>) JSON.parse(JSON.toJSONString(result.get("ryxx")));
        System.out.println("entitylist: "+entitylist);
        maplist.put("total", entitylist.size());
//        maplist.put("page",  request.getParameter("page"));
        System.err.println("maplist: "+maplist);
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
     * @api {get} /v4/jwp/jwp_jsyl/jshjList 家属会见查询
     * @apiVersion 0.4.0
     * @apiName jshjList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 家属会见查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}xm         				                         家属姓名
     * @apiSuccess {String}hjsj         				                     会见时间
     * @apiSuccess {String}jssj         				                     结束时间
     * @apiSuccess {String}hjs                                               会见室
     * @apiSuccess {String}ly                                                理由
     * @apiSuccess {String}lyString                                          理由

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
     *     "total": 3,
     *     "data": [
     *       {
     *         "hjs": "2",
     *         "jssj": 1575335528000,
     *         "xm": "AAA",
     *         "hjsj": 1575335497000,
     *         "hjsjString": "2019-12-03 09:11:37",
     *         "jssjString": "2019-12-03 09:12:08",
     *         "ly": "1"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577339635531
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "jsh":"监室号(最大字段长度：4)",
     *      "rybh":"人员编号(必填；最大字段长度：21)",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */


    @OpenAPI
    @ApiOperation("家属会见查询")
    @GetMapping("/jshjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> jshjList(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/jshjList";
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
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);


        if(null==maps.getResult().get("rybh") || "" .equals(maps.getResult().get("rybh"))){
            return ResponseMessage.error("请输入人员编号");
        }


        ResponseMessage<PagerResult<Map<String, Object>>> result= kssServerApis.jshjQuery(param);
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

    /**
     * @api {get} /v4/jwp/jwp_jsyl/rybhList 人员基本信息查询
     * @apiVersion 0.4.0
     * @apiName rybhList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 人员基本信息查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}hjdString         				                 户籍地(字典已转换)
     * @apiSuccess {String}rsrqString         				                 入所日期(字典已转换)
     * @apiSuccess {String}jkzkString         				                 健康情况(字典已转换)
     * @apiSuccess {String}csrqString                                        出生日期(字典已转换)
     * @apiSuccess {String}rsxzString                                        入所性质(字典已转换)
     * @apiSuccess {String}crjbjString                                       出入监标记(字典已转换)
     * @apiSuccess {String}zyString         				                 职业(字典已转换)
     * @apiSuccess {String}mzString         				                 民族(字典已转换)
     * @apiSuccess {String}photosUrl                                         照片路径
     * @apiSuccess {String}hyzhString                                        婚姻状况(字典已转换)
     * @apiSuccess {String}xbString                                          性别(字典已转换)
     * @apiSuccess {String}snbh         				                     人员所内编号(字典已转换)
     * @apiSuccess {String}xm         				                         姓名(字典已转换)
     * @apiSuccess {String}zjh                                               证件号(字典已转换)

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
     *     "total": 1,
     *     "data": [
     *       {
     *         "hjdString": "北京崇文区",
     *         "rsrqString": "2019-07-12 11:45:55",
     *         "jkzkString": "有病",
     *         "csrqString": "1987-02-01",
     *         "rsxzString": "逮捕",
     *         "crjbjString": "提审",
     *         "zyString": "中国共产党中央委员会和地方各级组织负责人",
     *         "mzString": "汉",
     *         "photosUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-R-AXRKdAAABsc1UHh4337.jpg",
     *         "hyzhString": "未婚",
     *         "xbString": "女性",
     *         "snbh": "20190053",
     *         "xm": "李东",
     *         "zjh": "110101199003074231"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577416993079
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *          "rybh":"人员编号(必填；最大字段长度：21)",
     *             }
     */
    @OpenAPI
    @ApiOperation("人员基本信息查询")
    @GetMapping("/rybhList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> rybh_query(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/rybhList";
        String state = request.getParameter("state");
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        String rybh = "";
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            rybh = maps.getResult().get("rybh").toString();
        }else {
            return ResponseMessage.error("人员编号不能为空");
        }
        ResponseMessage<List<Map<String, Object>>> result= jwpServerApis.rybhQuery(rybh);
        System.err.println("result"+ JSON.toJSONString(result));

        List<Map<String, Object>> alist=new ArrayList<Map<String, Object>>();
        Map<String, Object> mapmsg=new HashMap<String, Object>();
        for (int i = 0; i <result.getResult().size(); i++) {
            Map<String, Object> aa= (Map<String, Object>) result.getResult().get(i).get("msg");
            for (String key : aa.keySet()){
                mapmsg.put(key,aa.get(key));
            }
            mapmsg.put("photosUrl",result.getResult().get(i).get("photosUrl"));
            alist.add(mapmsg);
        }

        //封装需要的数据
        Map<String, Object> maplist = new HashMap<String, Object>();
        maplist.put("entity", alist);
        maplist.put("interfaceId", interfaceId);
        //crjbjString,csrqString,hjdString,hyzhString,jkzkString,mzString,rsrqString,rsxzString,snbh,xbString,xm,zjh,zyString,photosUrl
        System.err.println("alist"+ JSON.toJSONString(alist));
        List<Map<String, Object>> entitylist = (List<Map<String, Object>>) JSON.parse(JSON.toJSONString(alist));
        System.out.println("entitylist: "+entitylist);
        maplist.put("total", entitylist.size());
        System.err.println("maplist: "+maplist);
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
     * @api {get} /v4/jwp/jwp_jsyl/lshjList 律师会见查询
     * @apiVersion 0.4.0
     * @apiName lshjList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 律师会见查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}xm         				                         律师姓名
     * @apiSuccess {String}hjsj         				                     会见时间
     * @apiSuccess {String}jssj         				                     结束时间
     * @apiSuccess {String}hjs                                               会见室
     * @apiSuccess {String}hjsy                                              会见事由

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
     *     "total": 3,
     *     "data": [
     *       {
     *          "hjs": "1",
     *         "jssj": 1573468961000,
     *         "xm": "发射点",
     *         "hjsy": "asdas",
     *         "hjsj": 1573468882000
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577339635531
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "jsh":"监室号(最大字段长度：4)",
     *      "rybh":"人员编号(必填；最大字段长度：21)",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */

    @OpenAPI
    @ApiOperation("律师会见查询")
    @GetMapping("/lshjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> lshjList(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/lshjList";
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
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);

        if(null==maps.getResult().get("rybh") || "" .equals(maps.getResult().get("rybh"))){
            return ResponseMessage.error("请输入人员编号");
        }


        ResponseMessage<PagerResult<Map<String, Object>>> result= kssServerApis.lshjQuery(param);
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





    /**
     * @api {get} /v4/jwp/jwp_jsyl/tsdjList 提讯查询
     * @apiVersion 0.4.0
     * @apiName tsdjList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 提讯查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     *
     * @apiSuccess {String}baryy         				                     办案人员姓名1
     * @apiSuccess {String}kssj         				                     开始时间
     * @apiSuccess {String}jssj         				                     结束时间
     * @apiSuccess {String}tss                                               提讯地点
     * @apiSuccess {String}txsy                                              提讯事由

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
     *     "total": 3,
     *     "data": [
     *       {
     *         "baryy": "123",
     *         "jssj": 1576919591000,
     *         "tss": "1",
     *         "kssjString": "2019-12-21 17:13:03",
     *         "txsy": "123",
     *         "kssj": 1576919583000,
     *         "jssjString": "2019-12-21 17:13:11"
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577339635531
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9)",
     *      "json":{
     *      "jsh":"监室号(最大字段长度：4)",
     *      "rybh":"人员编号(必填；最大字段长度：21)",
     *      "page":"当前页",
     *      "pagesize":"一页数据量"
     *               }
     */

    @OpenAPI
    @ApiOperation("提讯查询")
    @GetMapping("/tsdjList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> tsdjList(HttpServletRequest request, @RequestParam(name="appcode") String appcode,@RequestParam(name="jsbh")String jsbh,String json){

        String interfaceId = "/v4/jwp/jwp_jsyl/tsdjList";
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
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.eq, maps.getResult().get("jsh"));
        }

        DefaultQueryParam.addDefaultQueryParam(request, param, state);

        if(null==maps.getResult().get("rybh") || "" .equals(maps.getResult().get("rybh"))){
            return ResponseMessage.error("请输入人员编号");
        }


        ResponseMessage<PagerResult<Map<String, Object>>> result= kssServerApis.txQuery(param);
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
