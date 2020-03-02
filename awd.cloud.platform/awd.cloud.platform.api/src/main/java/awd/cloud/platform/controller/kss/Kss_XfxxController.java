package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.XjzcModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.*;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/xfxx")
@Api(tags = "kss-xfxx",description = "xfxx")
public class Kss_XfxxController extends PublicService {

    @Autowired
    private KssServerApis kssServerApis;

    /**
     * @api {get} /v4/kss/xfxx/xfxxList 根据人员编号查询消费信息
     * @apiVersion 0.4.0
     * @apiName xfxxList
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 根据人员编号查询消费信息
     *
     * @apiParam {String} appcode 												应用代码(必填)
     * @apiParam {String} jsbh 												监所编号(必填;最大字段长度：9)
     * @apiParam {String} json 												查询参数集
     *
     * @apiSuccess {String}id          				                        id
     * @apiSuccess {String}rybh          				                        人员编号
     * @apiSuccess {String}jsbh          				                        监所编号
     * @apiSuccess {String}jsbhString          				                监所编号（已转换）
     * @apiSuccess {String}currency          				                    币种
     * @apiSuccess {String}xfje       				                            消费金额
     * @apiSuccess {String}xflx          				                        消费类型
     * @apiSuccess {String}xfqje          				                        消费前金额
     * @apiSuccess {String}xfhye          				                        消费后金额
     * @apiSuccess {String}zy          				                        摘要
     * @apiSuccess {String}blr          				                        办理人
     * @apiSuccess {String}blsj          				                        办理时间
     * @apiSuccess {String}state          				                        状态
     * @apiSuccess {String}stateString          				                状态(已转换)
     * @apiSuccess {String}creator          				                    创建人
     * @apiSuccess {String}createtime          				                创建时间
     * @apiSuccess {String}updator          				                    修改人
     * @apiSuccess {String}updatetime          				                修改时间
     * @apiSuccess {String}currencyString          				            币种（已转换）
     * @apiSuccess {String}xflxString          				                消费类型（已转换）

     * @apiSuccess {String}message                                              返回信息
     * @apiSuccess {String}result                                               返回结果
     * @apiSuccess {String}total                                                返回总数
     * @apiSuccess {String}data                                                 返回数据
     * @apiSuccess {String}status                                               返回状态
     * @apiSuccess {String}timestamp                                            时间戳
     *
     *  @apiSuccessExample {json} 返回 (成功):
     *      HTTP/1.1 200 OK
     *
     * {
     *   "message": "查询成功",
     *   "result": {
     *     "total": 4,
     *     "data": [
     *       {
     *        	"id": "11000011420190902000202"
     *          "rybh": "310000111201906200007"
     *          "jsbh": "110000114"
     *          "jsbhString": "北京市第一看守所"
     *          "currency": "156"
     *          "xfje": 46
     *          "xflx": "01"
     *          "xfqje": 46
     *          "xfhye": 0
     *          "zy": "出所退还现金"
     *          "blr": "管理员"
     *          "blsj": "2019-09-02 20:00:38"
     *          "state": "R2"
     *          "stateString": "有效"
     *          "creator": "管理员"
     *          "createtime": "2019-09-02 20:00:39"
     *          "updator": ""
     *          "updatetime": null
     *          "currencyString": "人民币元"
     *          "xflxString": "出所退还"
     *       }
     *     ],
     *     "page": "1"
     *   },
     *   "status": 200,
     *   "timestamp": 1578447454182
     * }
     *
     * @apiUse QueryError
     *
     * @apiExample 请求参数:
     *    appcode:"应用代码（必填）",
     *    jsbh:"监所编号(必填; 最大字段长度：9)(必填)",
     *    json:{
     *          "rybh": "人员编号(最大长度:21)",
     *          "page": "当前页数",
     *          "rows": "一页数据量",
     *          "sort": "id",
     *          "order": "desc"
     *         }
     *
     */
    // id,rybh,jsbh,jsbhString,currency,xfje,xflx,xfqje,xfhye,zy,blr,blsj,creator,createtime,updator,updatetime,currencyString,xflxString
    public ResponseMessage<Map<String,Object>> xfxxList(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, String json){

        String interfaceId = "/v4/kss/xfxx/xfxxList";
        String state = request.getParameter("state");
        try {
            ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
            if (maps.getStatus() != 200) {
                return ResponseMessage.error(maps.getMessage());
            }
            //查询参数
            QueryParam param = new QueryParam();
            if (!StringUtils.isNullOrEmpty(jsbh)) {
                param.and("jsbh", TermType.eq, jsbh);
            }
            if (!StringUtils.isNullOrEmpty(maps.getResult().get("rybh"))) {
                param.and("rybh", TermType.eq, maps.getResult().get("rybh"));
            }
            DefaultQueryParam.addDefaultQueryParam(request, param, state);
            System.err.println("param--" + JSON.toJSONString(param));

            ResponseMessage<PagerResult<XjzcModel>> result = kssServerApis.xfxxList(param);
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


}
