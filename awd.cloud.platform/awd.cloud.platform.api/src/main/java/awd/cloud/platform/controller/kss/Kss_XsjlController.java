/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import javax.servlet.http.HttpServletRequest;

import awd.bj.kss.model.XsjlModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.TermType;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_XsjlModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xsjl")
@Api(tags = "kss-xsjl", description = "Xsjl")
public class Kss_XsjlController extends PublicService {

    @Autowired
    private KssService kssService;
    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/kss/xsjl/xsjlUpdate 巡视记录修改
     * @apiVersion 0.4.0
     * @apiName xsjlSave
     * @apiGroup g_kss
     * @apiPermission user
     * @apiDescription 巡视记录修改
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * {
     * "appcode":"应用代码(必填)",
     * "jsbh":"监所编号(必填; 最大长度:9)",
     * json:{"entity":[{
     * "id":"Id(必填; 最大长度:23)",
     * "xsfw":"巡视范围(必填; 最大长度:9)",
     * "xssj":"巡视时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * "xsmj":"巡视民警(必填; 最大长度:50)",
     * "xslx":"巡视类型(必填; 最大长度:2)",
     * "xsqk":"巡视情况(必填; 最大长度:255)",
     * "bz":"备注(最大长度:255)"
     * ]
     * }
     * }
     */
    @ApiOperation("巡视记录修改")
    @PostMapping("/xsjlUpdate")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xsjlUpdate(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/xsjl/xsjlUpdate";
        //校验权限
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        try {
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<XsjlModel> modelList = JSONArray.parseArray(map.get("entity").toString(), XsjlModel.class);
            System.err.println("model--" + JSON.toJSONString(modelList.get(0)));
            modelList.get(0).setState("R2");
            modelList.get(0).setJsbh(jsbh);
            String id=null;
            if(StringUtils.isNullOrEmpty(maps.getResult().get("id"))){
                 id = maps.getResult().get("id").toString();
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsfw"))) {
                modelList.get(0).setXsfw(maps.getResult().get("xsfw").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsmj"))) {
                modelList.get(0).setXsmj(maps.getResult().get("xsmj").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xslx"))) {
                modelList.get(0).setXslx(maps.getResult().get("xslx").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsqk"))) {
                modelList.get(0).setXsqk(maps.getResult().get("xsqk").toString());
            }
            if(StringUtils.isNullOrEmpty(maps.getResult().get("bz"))){
                modelList.get(0).setBz(maps.getResult().get("bz").toString());
            }

            SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(StringUtils.isNullOrEmpty(maps.getResult().get("xssj"))){
                Date xssj = spl.parse(maps.getResult().get("xssj").toString());
                modelList.get(0).setXssj(xssj);
            }
            modelList.get(0).setUpdatetime(new Date());
            ResponseMessage<String> result = kssServerApis.xsjlUpdate(id, modelList.get(0));
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("修改失败！");
        }
    }

    /**
     * @api {post} /v4/kss/xsjl/xsjlSave 巡视记录保存
     * @apiVersion 0.4.0
     * @apiName xsjlSave
     * @apiGroup g_kss
     * @apiPermission user
     * @apiDescription 巡视记录保存
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				生成的主键信息
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     * @apiSuccessExample {json} 返回（成功）:
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     * @apiExample 请求参数:
     * {
     * "appcode":"应用代码(必填)",
     * "jsbh":"监所编号(必填; 最大长度:9)",
     * json:{"entity":[{
     * "xsfw":"巡视范围(必填; 最大长度:9)",
     * "xssj":"巡视时间(必填; 格式:yyyy-MM-dd hh:mm:ss)",
     * "xsmj":"巡视民警(必填; 最大长度:50)",
     * "xslx":"巡视类型(必填; 最大长度:2)",
     * "xsqk":"巡视情况(必填; 最大长度:255)",
     * "bz":"备注(最大长度:255)"
     * }]
     * }
     * }
     */
    @ApiOperation("新增巡视记录")
    @PostMapping("/xsjlSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xsjlSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/xsjl/xsjlSave";
        //校验权限
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }
        try {
            //数据类型校验
            Map<String, Object> map = JSONObject.parseObject(json, HashMap.class);
            map.put("interfaceId", interfaceId);
            ResponseMessage<String> msg = this.modelYz(map);
            if (msg.getStatus() != 200) {
                return ResponseMessage.error(msg.getMessage());
            }
            List<XsjlModel> modelList = JSONArray.parseArray(map.get("entity").toString(), XsjlModel.class);
            System.err.println("model--" + JSON.toJSONString(modelList.get(0)));
            modelList.get(0).setState("R2");
            modelList.get(0).setJsbh(jsbh);
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsfw"))) {
                modelList.get(0).setXsfw(maps.getResult().get("xsfw").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsmj"))) {
                modelList.get(0).setXsmj(maps.getResult().get("xsmj").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xslx"))) {
                modelList.get(0).setXslx(maps.getResult().get("xslx").toString());
            }
            if (StringUtils.isNullOrEmpty(maps.getResult().get("xsqk"))) {
                modelList.get(0).setXsqk(maps.getResult().get("xsqk").toString());
            }
            if(StringUtils.isNullOrEmpty(maps.getResult().get("bz"))){
                modelList.get(0).setBz(maps.getResult().get("bz").toString());
            }

            SimpleDateFormat spl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(StringUtils.isNullOrEmpty(maps.getResult().get("xssj"))){
                Date xssj = spl.parse(maps.getResult().get("xssj").toString());
                modelList.get(0).setXssj(xssj);
            }

            modelList.get(0).setCreatetime(new Date());
            ResponseMessage<String> result = kssServerApis.xsjlSave(modelList.get(0));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

    /**
     * @api {get} /v4/kss/xsjl/xsjlQuery 巡视记录查询
     * @apiVersion 0.4.0
     * @apiName xjjsQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 巡视记录查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String} xsfw:                       巡视范围
     * @apiSuccess {String} xssj:                       巡视时间
     * @apiSuccess {String} xsmj:                       巡视民警
     * @apiSuccess {String} xsqk:                       巡视情况
     * @apiSuccess {String} xslxString:                 巡视类型
     * @apiSuccess {String} bz:                         备注
     */
    @OpenAPI
    @ApiOperation("巡视记录查询")
    @GetMapping("/xsjlQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> xsjlQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/xsjl/xsjlQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xsmj"))) {
            param.and("jsbh", TermType.like, "%" + maps.getResult().get("xsmj") + "%");
        }


        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xssjStartTime"))) {
            String xssjStartTime = (String) maps.getResult().get("xssjStartTime");
            System.err.println("xssjStartTime:" + xssjStartTime);
            param.and("xssj", TermType.gte, xssjStartTime + " 00:00:00");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("xssjEndTime"))) {
            String xssjEndTime = (String) maps.getResult().get("xssjEndTime");
            System.err.println("xssjEndTime:" + xssjEndTime);
            System.err.println("=============================================================");
            param.and("xssj", TermType.lte, xssjEndTime + " 23:59:59");
        }
        ResponseMessage<PagerResult<XsjlModel>> result = kssServerApis.xsjlQueryForPage(param);

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
    }


    @OpenAPI
    @ApiOperation("分页查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<Kss_XsjlModel>> xsjl_query(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_XsjlModel>> result = kssService.xsjl_query(queryParam);
        if (result.getStatus() == 200) {
            result.setMessage("查询成功");
            if (result.getResult() == null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
    }


    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xsjl_save(@RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_XsjlModel data) {
        return kssService.xsjl_save(data);
    }


    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> xsjl_updateByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user, @RequestBody Kss_XsjlModel data) {
        return kssService.xsjl_updateByKey(id, data);
    }


    @OpenAPI
    public ResponseMessage<Kss_XsjlModel> xsjl_getByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.xsjl_getByKey(id);
    }


    @OpenAPI
    public ResponseMessage<Integer> xsjl_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, @RequestParam(name = "user") String user) {
        return kssService.xsjl_deleteByKey(id);
    }
}
