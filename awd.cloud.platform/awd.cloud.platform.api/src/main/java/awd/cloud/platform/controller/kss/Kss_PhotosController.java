package awd.cloud.platform.controller.kss;

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
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/photots")
@Api(tags = "kss-photos",description = "photos")
public class Kss_PhotosController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/photots/photosList 人员照片查询
     * @apiVersion 0.4.0
     * @apiName photosList
     * @apiGroup g_kss
     * @apiPermission any
     *
     * @apiDescription 人员照片查询.
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     *
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     *
     * @apiSuccess {String} rybh         			    人员编号
     * @apiSuccess {String} photoUrl         			照片地址
     * @apiSuccess {String} type         			    照片位置
     *
     * @apiSuccess {String} page         				当前页数
     * @apiSuccess {String} status         				代码
     * @apiSuccess {String} timestamp         			时间戳
     *
     * @apiSuccessExample {json} 返回（成功）:
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 1,
     *     "data": [
     *       {
     *         "rybh": "110000111201907120002",
     *         "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-MyAaF4OAAAIiecvuh8496.jpg",
     *         "type": "1"
     *       }
     *      ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1576496854065
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample Example usage:
     * appcode:"应用代码(必填)",
     * jsbh:"监所编号(必填; 最大长度:9)",
     * json:
     *   {
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("人员照片查询")
    @GetMapping("/photosList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> photos_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        //接口id
        String interfaceId = "/v4/kss/photots/photosList";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps =this.userAuthorizatio(request,json, interfaceId);
            if(maps.getStatus()!=200) {
                return ResponseMessage.error(maps.getMessage());
            }

            //查询参数
            QueryParam param = new QueryParam();
            if(!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--"+ JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String,Object>>> result= kssServerApis.photoQuery(param);
            System.err.println("result"+JSON.toJSONString(result));

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
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("查询失败！");
        }
    }
}
