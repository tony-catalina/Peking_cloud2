package awd.cloud.platform.controller.jls;

import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.JkqkModel;
import awd.bj.jls.model.XxfbModel;
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
@RequestMapping("/v4/jls/xxfb")
@Api(tags = "jls-xxfb",description = "Xxfb")
public class Jls_XxfbController extends PublicService {

    @Autowired
    private JlsServerApis jlsServerApis;

    /**
     * @api {post} /v4/jls/xxfb/xxfbSave 信息发布保存
     * @apiVersion 0.4.0
     * @apiName xxfbSave
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription 信息发布保存
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
     *      "fbr":"发布人(必填; 最大字段长度：30)",
     *      "fbjb":"发布界别(必填; 最大字段长度：1)",
     *      "fbnr":"发布内容(必填)",
     *      "fbgw":"发布岗位(必填; 最大字段长度：2)",
     *      "fbg":"发布给(必填; 最大字段长度：50)",
     *      "fbkssj":"发布开始时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "fbjssj":"发布结束时间(必填; 格式：yyyy-MM-dd hh:mm:ss)",
     *      "creator":"创建人(必填; 最大字段长度：30)"
     *   }
     *   ]
     * }
     */
    //{"tbr":".{1,30}","tbrq":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","fbr":".{1,30}","fbjb":".{1,1}","fbgw":".{1,2}","fbg":".{1,50}","fbkssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","fbjssj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$","creator":".{1,30}"}
    @ApiOperation("信息发布保存")
    @PostMapping("xxfbSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xxfbSave(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jls/xxfb/xxfbSave";

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

            List<XxfbModel> modelList = JSONArray.parseArray(map.get("entity").toString(),XxfbModel.class);
            XxfbModel xxfbModel = modelList.get(0);
            xxfbModel.setState("R2");
            xxfbModel.setJsbh(jsbh);
            xxfbModel.setCreatetime(new Date());
            ResponseMessage<String> result = jlsServerApis.xxfbSave(xxfbModel);
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
     * @api {get} /v4/jls/xxfb/xxfbQuery   信息发布查询
     * @apiVersion 0.4.0
     * @apiName xxfbQuery
     * @apiGroup g_jls
     * @apiPermission any
     * @apiDescription   信息发布查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}tbr                                                  填表人
     * @apiSuccess {String}tbrq                                                 填表日期
     * @apiSuccess {String}fbr                                                  发布人
     * @apiSuccess {String}fbjb                                                 发布级别
     * @apiSuccess {String}fbnr                                                 发布内容
     * @apiSuccess {String}fbkssj                                               发布开始时间
     * @apiSuccess {String}fbjssj                                               发布结束时间
     * @apiSuccess {String}fbgw                                                 发布岗位
     * @apiSuccess {String}fbg                                                  发布给
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}updator                                              更新人
     * @apiSuccess {String}updatetime                                           更新时间
     * @apiSuccess {String}jsbhString                                           监所编号（已转换）
     * @apiSuccess {String}fbjbString                                           发布级别（已转换）
     * @apiSuccess {String}fbgwString                                           发布岗位（已转换）
     * @apiSuccess {String}stateString                                          状态（已转换）
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
     *     "total": 15,
     *     "data": [
     *       {
     *       id: "11000012120191128000019"
     *       jsbh: "110000121"
     *       tbr: "管理员"
     *       tbrq: "2019-11-28"
     *       fbr: "管理员"
     *       fbjb: "2"
     *       fbnr: "sadf"
     *       fbkssj: "2019-11-28 16:16:49"
     *       fbjssj: "2019-11-28 16:17:00"
     *       fbgw: "03"
     *       fbg: ""
     *       state: "R2"
     *       creator: "管理员"
     *       createtime: "2019-11-28 16:16:28"
     *       updator: ""
     *       updatetime: null
     *       jsbhString: ""
     *       fbjbString: "一般"
     *       fbgwString: "管教岗位"
     *       stateString: "有效"
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
     *      "fbr":"发布人",
     *      "fbjb":"发布级别",
     *      "fbgw":"发布岗位",
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "id",
     *      "order": "desc"
     * }
     */
    //id,jsbh,tbr,tbrq,fbr,fbjb,fbnr,fbkssj,fbjssj,fbgw,fbg,jsbhString,fbjbString,fbgwString
    @ApiOperation("信息发布查询")
    @GetMapping("/xxfbQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> xxfbQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/jls/xxfb/xxfbQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbr"))) {
                queryParam.and("fbr", TermType.eq, "%"+ maps.getResult().get("fbr")+"%");
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbjb"))) {
                queryParam.and("fbjb", TermType.eq, maps.getResult().get("fbjb"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("fbgw"))) {
                queryParam.and("fbgw", TermType.eq, maps.getResult().get("fbgw"));
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<XxfbModel>> result = jlsServerApis.xxfbQueryForPage(queryParam);
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
