package awd.cloud.platform.controller.jwp;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
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

/**
 * Author：张延
 * Date：2019-12-20 13:10
 * Description：<描述>
 */
@RestController
@RefreshScope
@RequestMapping("/v4/jwp/jwp_yzsp")
@Api(tags = "jwp-yzsp",description = "yzsp")
public class Jwp_YzspController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    @Autowired
    private JwpServerApis jwpServerApis;


    /**
     * @api {get} /v4/jwp/jwp_yzsp/getYzspList 一周食谱查询
     * @apiVersion 0.4.0
     * @apiName getYzspList
     * @apiGroup g_jwp
     * @apiPermission any
     * @apiDescription 一周食谱查询.
     *

     * @apiParam {String} appcode 											应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												保存参数集(必填)
     *
     * @apiSuccess {String}createtime                                       创建时间
     * @apiSuccess {String}creator                                          创建人
     * @apiSuccess {String}week                                             星期
     * @apiSuccess {String}blr                                              办理人
     * @apiSuccess {String}zc                                               早餐
     * @apiSuccess {String}wc                                               晚餐
     * @apiSuccess {String}blsj                                             办理时间
     * @apiSuccess {String}lc                                               中餐
     * @apiSuccess {String}appcode                                          appcode
     * @apiSuccess {String}id                                               id
     * @apiSuccess {String}state                                            状态
     * @apiSuccess {String}updatetime                                       更新时间
     * @apiSuccess {String}jsbh                                             监所编号
     * @apiSuccess {String}jsh                                              监室号
     * @apiSuccess {String}updatetor                                        更新人
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
     *     "total": 7,
     *     "data": [
     *       {
     *         "createtime": "2019-11-25 11:25:24",
     *         "creator": "管理员",
     *         "week": "1",
     *         "blr": "null",
     *         "zc": "稀饭",
     *         "wc": "饼",
     *         "blsj": "null",
     *         "lc": "鸡肉",
     *         "appcode": "null",
     *         "id": "10001",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:00",
     *         "jsbh": "110000114",
     *         "jsh": "0102",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-24 11:25:24",
     *         "creator": "管理员",
     *         "week": "2",
     *         "blr": "null",
     *         "zc": "稀饭",
     *         "wc": "饼",
     *         "blsj": "null",
     *         "lc": "鸡肉",
     *         "appcode": "null",
     *         "id": "10002",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:01",
     *         "jsbh": "110000114",
     *         "jsh": "0102",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-23 09:44:57",
     *         "creator": "管理员",
     *         "week": "3",
     *         "blr": "办理人",
     *         "zc": "馒头",
     *         "wc": "米饭",
     *         "blsj": "2019-11-28 09:44:57",
     *         "lc": "面",
     *         "appcode": "null",
     *         "id": "10003",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:01",
     *         "jsbh": "110000114",
     *         "jsh": "0101",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-22 09:44:57",
     *         "creator": "管理员",
     *         "week": "4",
     *         "blr": "办理人",
     *         "zc": "牛肉",
     *         "wc": "稀饭",
     *         "blsj": "2019-11-28 09:44:57",
     *         "lc": "饺子121111",
     *         "appcode": "null",
     *         "id": "10004",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:02",
     *         "jsbh": "110000114",
     *         "jsh": "0101",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-21 09:44:57",
     *         "creator": "管理员",
     *         "week": "5",
     *         "blr": "办理人",
     *         "zc": "饼",
     *         "wc": "饺子",
     *         "blsj": "2019-11-28 09:44:57",
     *         "lc": "馄饨",
     *         "appcode": "null",
     *         "id": "10005",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:02",
     *         "jsbh": "110000114",
     *         "jsh": "0101",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-20 09:44:57",
     *         "creator": "管理员",
     *         "week": "6",
     *         "blr": "办理人",
     *         "zc": "芹菜",
     *         "wc": "猪肉",
     *         "blsj": "2019-11-28 09:44:57",
     *         "lc": "萝卜",
     *         "appcode": "null",
     *         "id": "10006",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:02",
     *         "jsbh": "110000114",
     *         "jsh": "0101",
     *         "updatetor": "管理员"
     *       },
     *       {
     *         "createtime": "2019-11-19 09:44:57",
     *         "creator": "管理员",
     *         "week": "7",
     *         "blr": "办理人",
     *         "zc": "鸡翅",
     *         "wc": "稀饭",
     *         "blsj": "2019-11-28 09:44:57",
     *         "lc": "面",
     *         "appcode": "null",
     *         "id": "10007",
     *         "state": "R2",
     *         "updatetime": "2019-12-28 09:46:03",
     *         "jsbh": "110000114",
     *         "jsh": "0101",
     *         "updatetor": "管理员"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1577082699437
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)",
     *   json:{
     *          }
     *
     */
    @OpenAPI
    @ApiOperation("一周食谱查询")
    @GetMapping("/getYzspList")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> getYzspList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json) {

        String interfaceId = "/v4/jwp/jwp_yzsp/getYzspList";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }

            System.out.println(jsbh+"--------------------");

            ResponseMessage<List<Map<String, Object>>> result = jwpServerApis.yzspQuery(jsbh);
            System.err.println("result" + JSON.toJSONString(result));

            //封装需要的数据
            Map<String, Object> maplist = new HashMap<String, Object>();
            maplist.put("entity", result.getResult());
            maplist.put("interfaceId", interfaceId);
            maplist.put("total", result.getResult().size());
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