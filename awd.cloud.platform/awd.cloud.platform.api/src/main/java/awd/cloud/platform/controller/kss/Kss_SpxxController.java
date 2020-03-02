package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.SpxxModel;
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
@RequestMapping("/v4/kss/spxx")
@Api(tags = "kss-spxx", description = "spxx")
public class Kss_SpxxController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/spxx/spxxList   商品信息查询
     * @apiVersion 0.4.0
     * @apiName spxxList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription   商品信息查询
     *
     * @apiParam {String} appcode                                               应用代码(必填)
     * @apiParam {String} jsbh                                                  监所编号(必填;最大字段长度：9)
     * @apiParam {String} json                                                  查询参数集
     *
     * @apiSuccess {String}id                                                   id
     * @apiSuccess {String}jsbh                                                 监所编号
     * @apiSuccess {String}jsbhString                                           监所编号（已转换）
     * @apiSuccess {String}pch                                                  批次号
     * @apiSuccess {String}tm                                                   条码
     * @apiSuccess {String}gg                                                   规格
     * @apiSuccess {String}jldw                                                 计量单位
     * @apiSuccess {String}lsj                                                  零售价
     * @apiSuccess {String}splb                                                 商品类别
     * @apiSuccess {String}spldString                                           商品类别（已转换）
     * @apiSuccess {String}sfzjff                                               是否直接发放
     * @apiSuccess {String}sfzjffString                                         是否直接发放（已转换）
     * @apiSuccess {String}sfxg                                                 是否限购
     * @apiSuccess {String}sfxgString                                           是否限购（已转换）
     * @apiSuccess {String}jhpl                                                 进货频率
     * @apiSuccess {String}bz                                                   备注
     * @apiSuccess {String}state                                                状态
     * @apiSuccess {String}stateString                                          状态（已转换）
     * @apiSuccess {String}creator                                              创建人
     * @apiSuccess {String}createtime                                           创建时间
     * @apiSuccess {String}updator                                              更新人
     * @apiSuccess {String}updatetime                                           更新时间
     * @apiSuccess {String}photos                                               商品图片
     * @apiSuccess {String}photourl                                             商品图片路径
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
     *     "total": 10,
     *     "data": [
     *       {
     *       id: "11000011420191019000023"
     *       jsbh: "110000114"
     *       jsbhString: "北京市第一看守所"
     *       pch: "1"
     *       spmc: "抹布"
     *       tm: "1"
     *       gg: "1"
     *       jldw: "1"
     *       lsj: 1
     *       splb: "0002"
     *       splbString: "日用品"
     *       sfzjff: ""
     *       sfzjffString: null
     *       sfxg: "0"
     *       sfxgString: "否"
     *       jhpl: ""
     *       bz: "1"
     *       state: "R2"
     *       stateString: "有效"
     *       creator: "管理员"
     *       createtime: "2019-12-19 16:52:10"
     *       updator: "管理员"
     *       updatetime: "2019-12-19 16:52:10"
     *       photos: "iVBORw0KGgoAAAANSUhEUgAAAj8AAAECCAYAAABTPwsAAAABc1"
     *       photourl: "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-M2AMiu3AAABsc1UHh4780.jpg"
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
     *      "spmc":"商品名称
     *      "tm":"条码"
     *      "page": "当前页数",
     *      "rows": "一页数据量",
     *      "sort": "lysj",
     *      "order": "desc"
     * }
     */
    //id,jsbh,jsbhString,pch,tm,gg,jldw,lsj,splb,spldString,sfzjff,sfzjffString,sfxg,sfxgString,jhpl,bz,creator,createtime,updator,updatetime,photos,photourl
    @ApiOperation("商品信息查询")
    @GetMapping("/spxxList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<Map<String, Object>> sqswList(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        String interfaceId = "/v4/kss/spxx/spxxList";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("spmc"))) {
                queryParam.and("spmc", TermType.eq, maps.getResult().get("spmc"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("tm"))) {
                queryParam.and("tm", TermType.eq, maps.getResult().get("tm"));
            }
            DefaultQueryParam.addDefaultQueryParam(request,queryParam, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.spxxQuery(queryParam);
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
     * @api {post} /v4/kss/spxx/spxxAdd  商品信息保存
     * @apiVersion 0.4.0
     * @apiName spxxAdd
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 商品信息保存
     *
     * @apiParam {String} appcode 											    应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String} message         			                        成功信息
     * @apiSuccess {String} result         				                    生成的主键信息
     * @apiSuccess {String} status         				                    代码
     * @apiSuccess {String} timestamp         			                        时间戳
     *
     *@apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * 	"entity": [{
     *          "spmc": "商品名称（必填：最大字段长度：50）"，
     *          "lsj": "零售价（必填：最大字段长度：10）"，
     *          "tm": "条码（必填：最大字段长度：20）"，
     *          "sfxg": "是否限购"（必填：最大字段长度：1），
     *          "gg": "规格（必填：最大字段长度：20）"，
     *          "jldw": "计量单位（必填：最大字段长度：10）",
     *          "splb": "商品类别（必填：最大字段长度：4）",
     *          "pch": "批次号（必填：最大字段长度：21）",
     *          "sfzjff": "是否直接发放（必填：最大字段长度：1）",
     *          "bz": "备注（必填：最大字段长度：200）"
     *          "creator":"创建人（必填：最大字段长度：50）",
     *        }]
     *
     *
     * }
     */
    //{"spmc":".{1,50}","lsj":"\\d{1,10}","tm":"\\d{1,20}","sfxg":"\\d{1,1}","gg":".{1,20}","jldw":".{1,10}","splb":"\\d{1,4}","pch":"\\d{1,21}","sfzjff":"\\d{1,1}","bz":".{1,200}","creator":".{1,50}"}
    //{"creator":".{1,50}","rybh":".{1,21}","asbh":"\\d{1,9}","bsbh":"\\d{1,9}","asblr":".{1,50}","asblsj":"^(\\d{4})-([0-1]\\d)-([0-3]\\d)\\s([0-5]\\d):([0-5]\\d):([0-5]\\d)$"}
    @ApiOperation("商品信息保存")
    @PostMapping("/spxxAdd")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> spxxAdd( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/spxx/spxxAdd";

        try{
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }

            List<SpxxModel> list = JSONArray.parseArray(map.get("entity").toString(),SpxxModel.class);
            SpxxModel spxxModel = list.get(0);
            spxxModel.setState("R2");
            spxxModel.setJsbh(jsbh);
            spxxModel.setCreatetime(new Date());

            ResponseMessage<String> result =   kssServerApis.spxxSave(spxxModel);
            if (result.getStatus() == 200) {
                result.setMessage("保存成功!");
            } else {
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {post} /v4/kss/spxx/spxxUpdate  商品信息修改
     * @apiVersion 0.4.0
     * @apiName spxxUpdate
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 商品信息修改
     *
     * @apiParam {String} appcode 											    应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												修改参数集(必填)
     *
     * @apiSuccess {String} message         			                        成功信息
     * @apiSuccess {String} result         				                    生成的主键信息
     * @apiSuccess {String} status         				                    代码
     * @apiSuccess {String} timestamp         			                        时间戳
     *
     *@apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *    "message": "修改成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * 	"entity": [{
     * 	        "id": "商品名称（必填：最大字段长度：23）",
     *          "spmc": "商品名称（必填：最大字段长度：50）"，
     *          "lsj": "零售价（必填：最大字段长度：10）"，
     *          "tm": "条码（必填：最大字段长度：20）"，
     *          "sfxg": "是否限购"（必填：最大字段长度：1），
     *          "gg": "规格（必填：最大字段长度：20）"，
     *          "jldw": "计量单位（必填：最大字段长度：10）",
     *          "splb": "商品类别（必填：最大字段长度：4）",
     *          "pch": "批次号（必填：最大字段长度：21）",
     *          "sfzjff": "是否直接发放（必填：最大字段长度：1）",
     *          "bz": "备注（必填：最大字段长度：200）"
     *          "updator":"修改人（必填：最大字段长度：50）"
     *        }]
     * }
     */
    //{"id":"\\d{1,23}","spmc":".{1,50}","lsj":"\\d{1,10}","tm":"\\d{1,20}","sfxg":"\\d{1,1}","gg":".{1,20}","jldw":".{1,10}","splb":"\\d{1,4}","pch":"\\d{1,21}","sfzjff":"\\d{1,1}","bz":".{1,200}","updator":".{1,50}"}
    @ApiOperation("商品信息修改")
    @PostMapping("/spxxUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> spxxUpdate( HttpServletRequest request,@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/spxx/spxxUpdate";

        try {
            //校验权限
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                System.out.println("----------------" + interfaceId);
                return ResponseMessage.error(msg.getMessage());
            }
            List<SpxxModel> list = JSONArray.parseArray(map.get("entity").toString(),SpxxModel.class);
            SpxxModel spxxModel = list.get(0);
            spxxModel.setUpdatetime(new Date());
            spxxModel.setJsbh(jsbh);

            ResponseMessage<String> result =   kssServerApis.spxxUpdate(spxxModel.getId(),spxxModel);
            if (result.getStatus() == 200) {
                result.setMessage("修改成功!");
            } else {
                result.setMessage("服务异常,修改失败!");
            }
            return result;

        }catch (Exception e){
        e.printStackTrace();
        return ResponseMessage.error("修改失败！");
        }
    }


}
