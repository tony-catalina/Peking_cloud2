package awd.cloud.platform.controller.jwp;

import awd.bj.kss.model.PhotosModel;
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

/**
 * Author：张延
 * Date：2019-12-28 9:26
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jwp_photos")
@Api(tags="jwp_photos",description="jwp_photos")
public class Jwp_PhotosController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/jwp/jwp_photos/getphotoList 人员照片查询
     * @apiVersion 0.4.0
     * @apiName getphotoList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 人员照片查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}photoUrls         				                 照片
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
     *        "photoUrl": "http://192.168.4.50:8888/storagegroup/M00/00/1E/wKgEMl2QeAWAQXRkAAABsc1UHh4400.jpg"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577356008049
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *    json:{
     *          "rybh":"人员编号(最大字段长度：21)"
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("人员照片查询")
    @GetMapping("/getphotoList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code=200,message="查询成功"),@ApiResponse(code=401,message="未授权"),
            @ApiResponse(code=403,message="无权限"),@ApiResponse(code = 404,message="数据不存在")})
    public ResponseMessage<Map<String,Object>> getphotoList(HttpServletRequest request, @RequestParam(name="appcode") String appcode, @RequestParam(name="jsbh")String jsbh, String json){

        String interfaceId = "/v4/jwp/jwp_photos/getphotoList";
        String state = request.getParameter("state");
        ResponseMessage<Map<String,Object>> maps = this.userAuthorizatio(request,json,interfaceId);
        if(maps.getStatus()!=200) {
            return ResponseMessage.error(maps.getMessage());
        }

        //查询参数
        QueryParam param = new QueryParam();
        if(!StringUtils.isNullOrEmpty(jsbh)) {
            param.and("jsbh", TermType.eq, jsbh);
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
            param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
        }
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        ResponseMessage<PagerResult<PhotosModel>> result= kssServerApis.photosList(param);
        System.err.println("result"+ JSON.toJSONString(result));

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
    }


}
