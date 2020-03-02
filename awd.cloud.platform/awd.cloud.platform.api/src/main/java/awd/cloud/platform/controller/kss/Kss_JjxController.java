/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.JbxxModel;
import awd.bj.kss.model.JjxModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.KssService;
import awd.cloud.platform.model.kss.Kss_JjxModel;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.CacheUtils;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
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
@RequestMapping("/v4/kss/jjx")
@Api(tags = "kss-jjx", description = "Jjx")
public class Kss_JjxController extends PublicService {

    @Autowired
    private KssService kssService;
    @Autowired
    private KssServerApis kssServerApis;


    /**
     * @api {post} /v4/kss/jjx/jjxSave 加减刑保存
     * @apiVersion 0.4.0
     * @apiName jjxSave
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 加减刑保存.
     *@apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填; 最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}result         				                     返回结果
     * @apiSuccess {String}page         				                     返回页数
     * @apiSuccess {String}status         				                     返回状态
     * @apiSuccess {String}timestamp         				                 时间戳
	 * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "保存成功!",
     * "result": "11000011420191214000011",
     * "status": 200,
     * "timestamp": 1576308305534
     * }
     * @apiUse CreateError
     *@apiExample 请求参数:
      *  appcode:"应用代码(必填)",
     *  jsbh:"监所编号(必填; 最大字段长度：9)",
     *  json:{"entity":[{
     * "rybh": "110000111201907150008",
     * "id":"",
     * "snbh": "20190071",
     * "xm": "测试4号",
     * "jsh": "0403",
     * "blrq": "2020-02-18",
     * "yxmrq": "2022-01-01",
     * "flag": "01",
     * "jjxq": "2",
     * "xmsfrq": "2024-01-01",
     * "jjxq": 02-00-00,
     * "blr": "管理员",
     * "bz": "啊啊啊"
     * }]
     * }
     */
    @ApiOperation("加减刑保存")
    @PostMapping("jjxSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> jjxSave(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {

        String interfaceId = "/v4/kss/jjx/jjxSave";

        //通过校验获取查询参数
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
            List<Kss_JjxModel> jjxModel = JSONArray.parseArray(map.get("entity").toString(), Kss_JjxModel.class);
            jjxModel.get(0).setState("R2");
            jjxModel.get(0).setJsbh(jsbh);
            jjxModel.get(0).setCreatetime(new Date());
			JbxxModel jbxxModel=new JbxxModel();
			jbxxModel.setXm(maps.getResult().get("xm").toString());
			jbxxModel.setSnbh(maps.getResult().get("snbh").toString());
			Map<String,Object> map1=new HashMap<>();
			map1.put("lcid", CacheUtils.get().getFlowKey("KSS_JJX"));
			map1.put("jbxxEntity",jbxxModel);
			map1.put("jjxEntity",jjxModel.get(0));
            ResponseMessage<String> result = kssServerApis.addFlow(map1);
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

    /**
     * @api {get} /v4/kss/jjx/jjxQuery 加减刑查询
     * @apiVersion 0.4.0
     * @apiName jjxQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 加减刑查询.
     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     * @apiSuccess {String}blrq          				                    办理日期
     * @apiSuccess {String}yxmrq         				                    原刑满日期
     * @apiSuccess {String}jjxq          				                    加减刑期
     * @apiSuccess {String}xmsfrq          				                    刑满释放期
     * @apiSuccess {String}pzr          				                    批准人
     * @apiSuccess {String}pzwh          				                    批准文号
     * @apiSuccess {String}flagString          				                加刑或减刑
     * @apiSuccess {String}message                                          返回信息
     * @apiSuccess {String}result                                           返回结果
     * @apiSuccess {String}total                                            返回总数
     * @apiSuccess {String}data                                             返回数据
     * @apiSuccess {String}status                                           返回状态
     * @apiSuccess {String}timestamp                                        时间戳
     * @apiSuccessExample {json} 返回 (成功):
     * HTTP/1.1 200 OK
     * {
     * "message": "查询成功",
     * "result": {
     * "total": 1,
     * "data": [
     * {
     * "id": "11000011420191109000114",
     * "jsbh": "110000114",
     * "jsbhString": "北京市第一看守所",
     * "rybh": "110000111201907150008",
     * "flag": "01",
     * "flagString": "加刑",
     * "yxmrq": "2022-01-01",
     * "jjxq": "2,2021-11-30",
     * "xmsfrq": "2019-11-30",
     * "pzwh": "",
     * "pzr": "",
     * "blr": "太上老君",
     * "blrq": "2019-11-09",
     * "ldxm": "",
     * "ldyj": "",
     * "ldpssj": null,
     * "bz": "太上老君",
     * "psbz": "0",
     * "psbzString": null,
     * "ywlcid": "4142852",
     * "state": "R2",
     * "stateString": "有效",
     * "creator": "管理员",
     * "createtime": "2019-11-09 13:29:55",
     * "updator": "",
     * "updatetime": null,
     * "pastable": "1",
     * "pastableString": "是"
     * }
     * ],
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
     * "rybh":"人员编号(最大字段长度：21)",
     * }
     */
    @OpenAPI
    @ApiOperation("加减刑查询")
    @GetMapping("/jjxQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> jjxQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/jjx/jjxQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        param.and("jsbh", jsbh);
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.jjxQueryForPage(param);
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


}
