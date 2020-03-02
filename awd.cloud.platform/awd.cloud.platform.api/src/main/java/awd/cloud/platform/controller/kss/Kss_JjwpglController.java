package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JjwpglModel;
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
import java.util.Map;

/**
 * Author：张延
 * Date：2020-01-17 13:48
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/kss/jjwpgl")
@Api(tags = "kss-jjwpgl",description = "Jjwpgl")
public class Kss_JjwpglController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @return
     * @api {get} /v4/kss/jjwpgl/jjwpgl  商品管理查询
     * @apiVersion 0.4.0
     * @apiName jjwpgl
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 商品管理查询
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}jjwpmc                                           接济物品名称
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}jjwpid                                           救济物品编号
     * @apiSuccess {String}jjwpphoto                                        救济物品照片url

     * @apiSuccess {String}message                                           返回信息
     * @apiSuccess {String}result                                            返回结果
     * @apiSuccess {String}total                                             返回总数
     * @apiSuccess {String}data                                              返回数据
     * @apiSuccess {String}status                                            返回状态
     * @apiSuccess {String}timestamp                                         时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data":  [
     *         "jjwpmc":"232",
     *         "id":"11000011420191217000000",
     *         "jjwpid":"123",
     *         "jjwpphoto":"R0lGODlhZABkAPUAAAAAAAgICBAQEBkZGSEhISk"
     *      ],
     * "page": "1"
     * },
     * "status": 200,
     * "timestamp": 1576826568061
     * }
     * @apiUse QueryError
     * @apiExample 请求参数:
     * appcode:"应用代码（必填）",
     * jsbh:"监所编号(必填; 最大字段长度：9)",
     * json:{
     * "jjwpmc":"接济物品名称(最大字段长度：30)",
     * "jjwpid":"救济物品编号(最大字段长度：13)"
     * }
     */
    @OpenAPI
    @ApiOperation("商品管理查询")
    @GetMapping("/jjwpgl")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jjwpglQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        String interfaceId = "/v4/kss/jjwpgl/jjwpgl";
        String state = "R2";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            // 领取状态(WPLQZT)
            //查询参数

            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jjwpmc"))) {
                param.and("jjwpmc", TermType.like, "%"+maps.getResult().get("jjwpmc")+"%");
            }
            if(!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jjwpid"))) {
                param.and("jjwpid", TermType.eq,maps.getResult().get("jjwpid"));
            }

            DefaultQueryParam.addDefaultQueryParam(request, param, state);

            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jjwpglqueryForPage(param);

            System.err.println("result" + JSON.toJSONString(result));


            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult().getData());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().getTotal());
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
     * @api {post} /v4/kss/jjwpgl/jjwpglSave 救济物品保存
     * @apiVersion 0.4.0
     * @apiName jjwpglSave
     * @apiGroup g_kss
     * @apiPermission user
     *
     * @apiDescription 救济物品保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集（必填）
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     *
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     *   "message": "保存成功!",
     *   "result": "保存成功",
     *   "status": 200,
     *   "timestamp": 1578886392184
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     *
     *   "appcode":"应用代码(必填)",
     *   "jsbh":"监所编号(必填; 最大字段长度:9)",
     *   json{
     *      "entity":[{
     * 		 "creator": "创建人(必填；最大字段长度：50)",
     * 		  "jjwpmc":"接济物品名称(必填；最大字段长度：30)",
     *        "jjwpid":"救济物品编号(必填；最大字段长度：13)"
     * 		  "ksqsl": "可申请数量(最大字段长度:255)",
     * 		  "id": "ID(最大字段长度:23)",
     * 		  "jjwpphoto": "图片"
     *        }]
     *   }
     * @return
     */
    //{"creator":".{1,50}","jjwpmc":".{1,30}","jjwpid":"\\d{1,13}","ksqsl":"\\d{0,255}","id":"\\d{0,23}"}
    @ApiOperation("救济物品保存")
    @PostMapping("/jjwpglSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jjwpglSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json){
        //接口id
        String interfaceId = "/v4/kss/jjwpgl/jjwpglSave";
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
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());

            JjwpglModel jjwpglModel = JSONArray.parseArray(map.get("entity").toString(), JjwpglModel.class).get(0);

            jjwpglModel.setCreatetime(new Date());
            jjwpglModel.setJsbh(jsbh);
            // MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) (request);
            // MultipartFile photo0 = multiRequest.getFile("photo");
            // if (!StringUtils.isNullOrEmpty(photo0)) {
            //jjwpglModel.setJjwpphoto(photo0.getBytes());
            // }
            ResponseMessage<String> result = kssServerApis.jjwpglSave(jjwpglModel);

            System.err.println("result--" + JSON.toJSONString(result));

            if (result.getStatus() == 200) {
                result.setMessage("保存成功!");
            } else {
                result.setMessage("服务异常,保存失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
