package awd.cloud.platform.controller.jnp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.FyqrModel;
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
@RequestMapping("/v4/jnp/fyqr")
@Api(tags = "jnp-fyqr",description = "fyqr")
public class Jnp_FyqrController extends PublicService {


    @Autowired
    private JwpServerApis jwpServerApis;

    /**
     * @api {get} /v4/jnp/fyqr/fyqrQuery 服药确认查询
     * @apiVersion 0.4.0
     * @apiName fyqrQuery
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 服药确认查询.
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
     * @apiSuccess {String} xbString         			性别(转换值)
     * @apiSuccess {String} xm         			        姓名
     * @apiSuccess {String} rybh         			    人员编号
     * @apiSuccess {String} qrzp1url         			确认照片1地址
     * @apiSuccess {String} qrzp2url         			确认照片2地址
     * @apiSuccess {String} xb         			        性别
     * @apiSuccess {String} qrsj         			    确认时间
     * @apiSuccess {String} qrzp3url         			确认照片3地址
     * @apiSuccess {String} nl         			        年龄
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
     *         "xbString": "女性",
     *         "xm": "司马茜\t",
     *         "rybh": "110000111201907120002",
     *         "qrzp1url": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-MyAaF4OAAAIiecvuh8496.jpg",
     *         "qrzp2url": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-MyAaF4OAAAIiecvuh8496.jpg",
     *         "xb": "2",
     *         "qrsj": "2019-12-12 15:51:52",
     *         "qrzp3url": "http://192.168.4.50:8888/storagegroup/M00/00/22/wKgEMl2m-MyAaF4OAAAIiecvuh8496.jpg",
     *         "nl": 34
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
     *     "jsh":"监室号(最大长度:4)",
     *     "rybh":"人员编号(最大长度:21)",
     *     "page":"当前页数",
     *     "pageSize":"一页数据数量"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("服药确认查询")
    @GetMapping("/fyqrQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> fyqr_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh,String json) {

        //接口id
        String interfaceId = "/v4/jnp/fyqr/fyqrQuery";
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
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
                param.and("jsh", TermType.eq, maps.getResult().get("jsh"));
            }
            if(!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--"+JSON.toJSONString(param));

            ResponseMessage<PagerResult<Map<String,Object>>> result= jwpServerApis.fyqrForPage(param);
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

    /**
     * @api {post} /v4/jnp/fyqr/fyqrSave 服药确认保存
	 * @apiVersion 0.4.0
     * @apiName fyqrSave
     * @apiGroup g_jnp
     * @apiPermission user
     *
     * @apiDescription 服药确认保存
     *
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填）(最大长度:9)
     * @apiParam {String} json 							保存参数集
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
     *    "message": "保存成功!",
     *    "result": "11000011420191214000011",
     *    "status": 200,
     *    "timestamp": 1576308305534
     * }
     *
     * @apiUse CreateError
     *
     * @apiExample 请求参数:
     * {
     *      "appcode":"应用代码(必填)",
     *      "jsbh":"监所编号(必填; 最大长度:9)",
     *      "entity":[
     *           {
     *              "jsh":"监室号(必填; 最大长度:4)",
     *              "rybh":"人员编号(必填; 最大长度:21)",
     *              "ryxm":"人员姓名(必填; 最大长度:255)",
     *              "ypmc":"药品名称(必填; 最大长度:255)",
     *              "ypsl":"药品数量(必填; 最大长度:255)",
     *              "sffy":"是否服药(必填; 字典:0,否 1,是; 最大长度:1)",
     *              "fysj":"服药时间(必填; 格式:yyyy-MM-dd hh:mm:ss)"
     *           }
     *        ]
     *      }
     * }
     *
     */
    @ApiOperation("新增")
    @PostMapping("/fyqrSave")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> fyqr_save(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/jnp/fyqr/fyqrSave";
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
                return ResponseMessage.error(msg.getMessage());
            }
            System.err.println(map.get("entity").toString());
            List<FyqrModel> modelList = JSONArray.parseArray(map.get("entity").toString(), FyqrModel.class);
            System.err.println("model--"+ JSON.toJSONString(modelList.get(0)));
            modelList.get(0).setCreatetime(new Date());
            modelList.get(0).setState("R2");
            modelList.get(0).setAppcode(appcode);
            modelList.get(0).setJsbh(jsbh);
            FyqrModel model = modelList.get(0);
            System.err.println("model--"+JSON.toJSONString(model));
            ResponseMessage<String> result = jwpServerApis.fyqrSave(model);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMessage.error("保存失败！");
        }
    }

}
