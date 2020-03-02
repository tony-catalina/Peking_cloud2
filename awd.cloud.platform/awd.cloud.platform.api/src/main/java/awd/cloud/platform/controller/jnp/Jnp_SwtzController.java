package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_ZfaceModel;
import awd.cloud.platform.model.manager.Manager_ZfingerModel;
import awd.cloud.platform.model.manager.Manager_ZirisModel;
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
import java.util.*;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/swtz")
@Api(tags = "jnp-swtz",description = "swtz")
public class Jnp_SwtzController extends PublicService {
    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private ManagerService managerService;

    /**
     * @api {get} /v4/jnp/swtz/swtzQuery 生物特征查询
     * @apiVersion 0.4.0
     * @apiName swtzQuery
     * @apiGroup g_jnp
     * @apiPermission any
     * @apiDescription 用户权限信息查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} type 												生物特征类别(1:人脸;2:虹膜;3:指纹)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}rybh         				                    人员编号
     * @apiSuccess {String}ryxm         				                    人员姓名
     * @apiSuccess {String}url                                              图片地址
     * @apiSuccess {String}type                                             生物特征类别
     * @apiSuccess {String}typeString                                       生物特征类别
     *
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
     *         "rybh": "110000111201907170002",
     *         "typeString": "指纹",
     *         "ryxm": "是的2",
     *         "type": 3,
     *         "url": "http://14.66.87.165:8889/storagegroup/M00/00/02/DkJXpV4Otg2AZS9PAAE5maDXZHo164.jpg"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578814362963
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    "appcode":"应用代码(必填)"
     *     "jsbh":"监所编号(必填；最大字段长度：9位)",
     *     "type":"生物特征类别(必填；最大字段长度：1位; 1:人脸;2:虹膜;3:指纹)",
     *      "json":{
     *          "rybh":"人员编号（是否必填：否;最大长度21位）",
     *          "jsh":"监室号（是否必填：否;最大长度4位）",
     *          "page":"当前页（是否必填：否）",
     *          "pageSize":"一页数据量（是否必填：否）"
     *               }
     */
    @OpenAPI
    @ApiOperation("生物特征查询")
    @GetMapping("/swtzQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> swtz_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,@RequestParam(name="type") String type, String json) {
        //接口id
        String interfaceId = "/v4/jnp/swtz/swtzQuery";
        String state = "R8";

        try {
            //通过校验获取查询参数
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }


            //查询参数
            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            ResponseMessage<PagerResult<Map<String, Object>>> result = kssServerApis.queryForPage(param);
            List<Map<String,Object>> swtzs = new ArrayList<>();
            if (result.getResult().getTotal()>0){
                String rybhs = "";
                List<Map<String,Object>> jbxxs = result.getResult().getData();
                for (Map<String,Object> map : jbxxs) {
                    rybhs = map.get("rybh")+","+ rybhs;
                }
                QueryParam queryParam = new QueryParam();
                queryParam.and("jsbh",TermType.eq,jsbh);
                queryParam.and("rybh",TermType.in,rybhs.substring(0,rybhs.length()-1));
                queryParam.and("state",TermType.eq,"R2");
                if ("1".equals(type)){
                    ResponseMessage<PagerResult<Manager_ZfaceModel>> zfaces = managerService.zface_query(queryParam);
                    jbxxs.forEach(jbxx -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put("rybh",jbxx.get("rybh").toString());
                        map.put("ryxm",jbxx.get("xm").toString());
                        map.put("type",1);
                        map.put("typeString","人脸");
                        zfaces.getResult().getData().forEach(zfaceModel ->{
                            if (jbxx.get("rybh").toString().equals(zfaceModel.getRybh())){
                                if (StringUtils.isNullOrEmpty(map.get("url"))){
                                    map.put("url",zfaceModel.getRltxurl());
                                }else{
                                    map.put("url",map.get("url").toString()+","+zfaceModel.getRltxurl());
                                }
                            }
                        });
                        swtzs.add(map);
                    });
                }else if ("2".equals(type)){
                    ResponseMessage<PagerResult<Manager_ZirisModel>> ziris = managerService.ziris_query(queryParam);
                    jbxxs.forEach(jbxx -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put("rybh",jbxx.get("rybh").toString());
                        map.put("ryxm",jbxx.get("xm").toString());
                        map.put("type",2);
                        map.put("typeString","虹膜");
                        ziris.getResult().getData().forEach(zirisModel ->{
                            if (jbxx.get("rybh").toString().equals(zirisModel.getRybh())){
                                if (StringUtils.isNullOrEmpty(map.get("url"))){
                                    map.put("url",zirisModel.getHmtxurl());
                                }else{
                                    map.put("url",map.get("url").toString()+","+zirisModel.getHmtxurl());
                                }
                            }
                        });
                        swtzs.add(map);
                    });
                }else if ("3".equals(type)){
                    ResponseMessage<PagerResult<Manager_ZfingerModel>> zfingers = managerService.zfinger_query(queryParam);
                    jbxxs.forEach(jbxx -> {
                        Map<String,Object> map = new HashMap<>();
                        map.put("rybh",jbxx.get("rybh").toString());
                        map.put("ryxm",jbxx.get("xm").toString());
                        map.put("type",3);
                        map.put("typeString","指纹");
                        zfingers.getResult().getData().forEach(zfingerModel ->{
                            if (jbxx.get("rybh").toString().equals(zfingerModel.getRybh())){
                                if (StringUtils.isNullOrEmpty(map.get("url"))){
                                    map.put("url",zfingerModel.getZwtxurl());
                                }else{
                                    map.put("url",map.get("url").toString()+","+zfingerModel.getZwtxurl());
                                }
                            }
                        });
                        swtzs.add(map);
                    });
                }
            }

            System.err.println("result" + JSON.toJSONString(result));


            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", swtzs);
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", swtzs.size());
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
}
