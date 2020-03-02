package awd.cloud.platform.controller.jnp;

import awd.bj.manager.model.DictionaryModel;
import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
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
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/jnp/dictionary")
@Api(tags = "jnp-dictionary",description = "dictionary")
public class Jnp_DictionaryController extends PublicService {

    @Autowired
    private ManagerService managerService;

    /**
     * @api {get} /v4/jnp/dictionary/getDictionary 字典项查询
     * @apiVersion 0.4.0
     * @apiName getDictionary
     * @apiGroup g_jnp
     * @apiPermission any
     *
     * @apiDescription 字典项查询.
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
     * @apiSuccess {String} fieldname         			字典字段
     * @apiSuccess {String} code         			    字典代码
     * @apiSuccess {String} content         			字典内容
     * @apiSuccess {String} fieldnameString         	字典字段名称
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
     *         "fieldname": "XB",
     *         "code": "0",
     *         "content": "未知的性别",
     *         "fieldnameString": "性别"
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
     *     "fieldname":"字典字段(必填)"
     *   }
     *
     */
    @OpenAPI
    @ApiOperation("字典项查询")
    @GetMapping("/getDictionary")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> getDictionary(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jnp/dictionary/getDictionary";
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            System.err.println("maps" + maps);
            //查询参数
            if (StringUtils.isNullOrEmpty(maps.getResult().get("fieldname"))){
                return ResponseMessage.error("fieldname不可为空");
            }
            String fieldname = maps.getResult().get("fieldname").toString();
            System.err.println("fieldname" + fieldname);

            ResponseMessage<List<DictionaryModel>> result= managerService.getByNode(fieldname);
            System.err.println("result--" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total",result.getResult().size());
            maplist.put("page", request.getParameter("page"));

            System.err.println("result" + JSON.toJSONString(maplist));

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
