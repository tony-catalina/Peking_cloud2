package awd.cloud.platform.controller.jls;

import awd.bj.jls.model.LkrywhModel;
import awd.cloud.platform.api.JlsServerApis;
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
@RequestMapping("/v4/jls/lkrywh")
@Api(tags = "jls-lkrywh",description = "Lkrywh")
public class Jls_LkrywhController extends PublicService {
    @Autowired
    private JlsServerApis jlsServerApis;

    /**
     * @api {post} /v4/jls/lkrywh/lkrywhSave 临控人员维护保存
     * @apiVersion 0.4.0
     * @apiName lkrywhSave
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 临控人员维护保存
     *
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String} message         			                    成功信息
     * @apiSuccess {String} result         				                	生成的主键信息
     * @apiSuccess {String} status         				                	代码
     * @apiSuccess {String} timestamp         			                    时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
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
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     *   "entity":[{
     *      "tbr":"填表人(必填; 最大字段长度：30)",
     *      "tbrq":"填表日期(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "bbkray":"被布控人简要案情",
     *      "sqrdw":"申请人单位名称",
     *      "lxdh":"申请人联系电话",
     *      "sqrxm":"申请人姓名",
     *      "xm":"被布控人姓名",
     *      "zjhm":"被布控人证件号码",
     *      "ajlb":"被布控人涉案类别",
     *      "csrq":"被布控人出生日期",
     *      "xzdxz":"被布控人现住地详址",
     *      "hjdxz":"被布控人户籍地详址",
     *      "bdsj":"比对时间",
     *      "sdm":"所代码",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }
     *   ]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
    @ApiOperation("临控人员维护保存")
    @PostMapping("lkrywhSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> lkrywhSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/lkrywh/lkrywhSave";

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
                System.out.println("----------------"+interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<LkrywhModel> modelList = JSONArray.parseArray(map.get("entity").toString(),LkrywhModel.class);
            LkrywhModel lkrywhModel = modelList.get(0);
            lkrywhModel.setState("R2");
            lkrywhModel.setJsbh(jsbh);
            lkrywhModel.setCreatetime(new Date());
            ResponseMessage<String> result = jlsServerApis.lkrywhSave(lkrywhModel);
            if(result.getStatus() == 200){
                result.setMessage("保存成功!");
            }else{
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/jls/lkrywh/lkrywhQuery   临控人员维护查询
     * @apiVersion 0.4.0
     * @apiName lkrywhQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   临控人员维护查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}ajlb                                                 被布控人涉案类别
     * @apiSuccess {String}ajlbString                                           被布控人涉案类别(已转换)
     * @apiSuccess {String}bbkray                                               被布控人简要案情
     * @apiSuccess {String}bbkrayString                                         被布控人简要案情(已转换)
     * @apiSuccess {String}bdsj                                                 比对时间
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}csrq                                                 被布控人出生日期
     * @apiSuccess {String}hjdxz                                                被布控人户籍地详址
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}jsbhString                                           监所编号（已转换）
     * @apiSuccess {String}lxdh                                                 联系电话
     * @apiSuccess {String}sdm                                                  所代码
     * @apiSuccess {String}sqrdw                                                申请人单位
     * @apiSuccess {String}sqrxm                                                申请人姓名
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}stateString                                          状态（已转换）
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}updatetime                                           更新时间
     * @apiSuccess {String}updator                                              更新人
     * @apiSuccess {String}xm                                                   被布控人姓名
     * @apiSuccess {String}xzdxz                                                被布控人现住地详址
     * @apiSuccess {String}zjhm                                                 被布控人证件号码
     *
     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 9,
     *     "data": [
     *       {
     *       ajlb: "010100"
     *       ajlbString: "扰乱公共秩序案件"
     *       bbkray: "101010100"
     *       bbkrayString: "扰乱单位秩序"
     *       bdsj: "2019-11-28 16:18:27"
     *       createtime: "2019-11-28 16:18:18"
     *       creator: "管理员"
     *       csrq: "1990-03-07"
     *       hjdxz: "北京东城区"
     *       id: "11000012120191128000010"
     *       jsbh: "110000121"
     *       jsbhString: ""
     *       lxdh: "13771369109"
     *       sdm: "234"
     *       sqrdw: "asdf"
     *       sqrxm: "sdf"
     *       state: "R2"
     *       stateString: "有效"
     *       tbr: "管理员"
     *       tbrq: "2019-11-28"
     *       updatetime: ""
     *       updator: ""
     *       xm: "dsf"
     *       xzdxz: "110101"
     *       zjhm: "110101199003078750"
     *       }，
     *       "page": "1"
     *     }，
     *   "status": 200,
     *   "timestamp": 1576826568061
     *  }
     *
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * json:{
     *      "xm":"被布控人姓名",
     *      "zjhm":"被布控人证件号码",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //ajlb,ajlbString,bbkray,bbkrayString,bdsj,csrq,hjdxz,id,jsbh,jsbhString,lxdh,sdm,sqrdw,sqrxm,tbr,tbrq,xm,xzdxz,zjhm
    @ApiOperation("临控人员维护查询")
    @GetMapping("/lkrywhQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> lkrywhQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/lkrywh/lkrywhQuery";
        String state = request.getParameter("state");
        try{
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam queryParam = new QueryParam();
            queryParam.and("jsbh", TermType.eq, jsbh);
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                queryParam.and("xm", TermType.eq, "%"+ maps.getResult().get("xm")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("zjhm"))) {
                queryParam.and("zjhm", TermType.eq, "%"+ maps.getResult().get("zjhm")+"%");
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String,Object>>> result = jlsServerApis.lkrywhQueryForPage(queryParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String,Object> mapList = new HashMap<String,Object>();
            mapList.put("entity",result.getResult().getData());
            mapList.put("interfaceId",interfaceId);
            mapList.put("total",result.getResult().getTotal());
            mapList.put("page",request.getParameter("page"));
            System.err.println("result" + JSON.toJSONString(mapList));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(mapList);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }

    /**
     * @api {get} /v4/jls/lkrywh/lkrywhYwtz   临控人员维护业务台账
     * @apiVersion 0.4.0
     * @apiName lkrywhYwtz
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   临控人员维护业务台账
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}ajlb                                                 被布控人涉案类别
     * @apiSuccess {String}ajlbString                                           被布控人涉案类别(已转换)
     * @apiSuccess {String}bbkray                                               被布控人简要案情
     * @apiSuccess {String}bbkrayString                                         被布控人简要案情(已转换)
     * @apiSuccess {String}bdsj                                                 比对时间
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}csrq                                                 被布控人出生日期
     * @apiSuccess {String}hjdxz                                                被布控人户籍地详址
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}jsbhString                                           监所编号（已转换）
     * @apiSuccess {String}lxdh                                                 联系电话
     * @apiSuccess {String}sdm                                                  所代码
     * @apiSuccess {String}sqrdw                                                申请人单位
     * @apiSuccess {String}sqrxm                                                申请人姓名
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}stateString                                          状态（已转换）
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}updatetime                                           更新时间
     * @apiSuccess {String}updator                                              更新人
     * @apiSuccess {String}xm                                                   被布控人姓名
     * @apiSuccess {String}xzdxz                                                被布控人现住地详址
     * @apiSuccess {String}zjhm                                                 被布控人证件号码
     *
     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 9,
     *     "data": [
     *       {
     *       ajlb: "010100"
     *       ajlbString: "扰乱公共秩序案件"
     *       bbkray: "101010100"
     *       bbkrayString: "扰乱单位秩序"
     *       bdsj: "2019-11-28 16:18:27"
     *       createtime: "2019-11-28 16:18:18"
     *       creator: "管理员"
     *       csrq: "1990-03-07"
     *       hjdxz: "北京东城区"
     *       id: "11000012120191128000010"
     *       jsbh: "110000121"
     *       jsbhString: ""
     *       lxdh: "13771369109"
     *       sdm: "234"
     *       sqrdw: "asdf"
     *       sqrxm: "sdf"
     *       state: "R2"
     *       stateString: "有效"
     *       tbr: "管理员"
     *       tbrq: "2019-11-28"
     *       updatetime: ""
     *       updator: ""
     *       xm: "dsf"
     *       xzdxz: "110101"
     *       zjhm: "110101199003078750"
     *       }，
     *       "page": "1"
     *     }，
     *   "status": 200,
     *   "timestamp": 1576826568061
     *  }
     *
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     * json:{
     *      "xm":"被布控人姓名",
     *      "zjhm":"被布控人证件号码",
     *      "bdsjStart":"比对时间",
     *      "bdsjEnd":"比对时间",
     *      "sdm":"所代码",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //ajlb,ajlbString,bbkray,bbkrayString,bdsj,csrq,hjdxz,id,jsbh,jsbhString,lxdh,sdm,sqrdw,sqrxm,tbr,tbrq,xm,xzdxz,zjhm
    @ApiOperation("临控人员维护业务台账")
    @GetMapping("/lkrywhYwtz")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> lkrywhYwtz(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/lkrywh/lkrywhYwtz";
        String state = request.getParameter("state");
        try{
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam queryParam = new QueryParam();
            queryParam.and("jsbh", TermType.eq, jsbh);
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("xm"))) {
                queryParam.and("xm", TermType.eq, "%"+ maps.getResult().get("xm")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("zjhm"))) {
                queryParam.and("zjhm", TermType.eq, "%"+ maps.getResult().get("zjhm")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("sdm"))) {
                queryParam.and("sdm", TermType.eq, "%"+ maps.getResult().get("sdm")+"%");
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bdsjStart"))){
                queryParam.and("bdsj", TermType.gte, maps.getResult().get("bdsjStart"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("bdsjEnd"))){
                queryParam.and("bdsj", TermType.gte, maps.getResult().get("bdsjEnd"));
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String,Object>>> result = jlsServerApis.lkrywhQueryForPage(queryParam);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String,Object> mapList = new HashMap<String,Object>();
            mapList.put("entity",result.getResult().getData());
            mapList.put("interfaceId",interfaceId);
            mapList.put("total",result.getResult().getTotal());
            mapList.put("page",request.getParameter("page"));
            System.err.println("result" + JSON.toJSONString(mapList));

            ResponseMessage<Map<String, Object>> list = this.kfzdShow(mapList);
            if (list.getStatus() == 200) {
                list.setMessage("查询成功");
                if (list.getResult() == null) {
                    list.setMessage("未查询数据");
                }
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }
}
