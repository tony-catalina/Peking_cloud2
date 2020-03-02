package awd.cloud.platform.controller.kss;

import awd.bj.kss.model.TsdjModel;
import awd.cloud.platform.api.KssServerApis;
import awd.cloud.platform.service.PublicService;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
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
@RequestMapping("/v4/kss/tsdj")
@Api(tags = "kss-tsdj", description = "tsdj")
public class Kss_TsdjController extends PublicService {


    @Autowired
    KssServerApis kssServerApis;


    /**
     * @api {get} /v4/kss/tsdj/tsdjQuery 提审登记查询
     * @apiVersion 0.4.0
     * @apiName tsdjQuery
     * @apiGroup g_kss
     * @apiPermission any
     * @apiDescription 提审登记查询.
     * @apiParam {String} appcode 						应用代码（必填）
     * @apiParam {String} jsbh 							监所编号（必填，最大长度：9）
     * @apiParam {String} json 							查询参数集
     * @apiSuccess {String} message         			成功信息
     * @apiSuccess {String} result         				数据信息
     * @apiSuccess {String} total         				数据数量
     * @apiSuccess {String} data         				数据
     * @apiSuccess {String}rybh      人员编号
     * @apiSuccess {String}dw       单位
     * @apiSuccess {String}baryy    办案人员姓名1
     * @apiSuccess {String}barye    办案人员姓名2
     * @apiSuccess {String}zjh1     证件号1
     * @apiSuccess {String}zjh2     证件号2
     * @apiSuccess {String}qtbar    其他办案人
     * @apiSuccess {String}qtzjh    其他办案人证件号
     * @apiSuccess {String}wcnjhr   未成年人监护人
     * @apiSuccess {String}wcndlr   未成年人代理人
     * @apiSuccess {String}kssj     开始时间
     * @apiSuccess {String}jssj     结束时间
     * @apiSuccess {String}tszbr    监区值班民警
     * @apiSuccess {String}dcmj     带出民警
     * @apiSuccess {String}jsr      收监民警
     * @apiSuccess {String}ywshwjwpqk   有无伤痕及违禁物品情况
     * @apiSuccess {String}yccon    抄身检查情况说明
     * @apiSuccess {String}flag     完成状态
     * @apiSuccess {String}pastable 是否有效
     * @apiSuccess {String}bllx     办理类型
     * @apiSuccess {String}sjzlJsbz
     * @apiSuccess {String}ywlcid   业务流程id
     * @apiSuccess {String}tss      提讯地点
     * @apiSuccess {String}dgnxwsycqk
     * @apiSuccess {String}bz       备注
     * @apiSuccess {String}txsy     提讯事由
     * @apiSuccess {String}txlx     提讯类型
     * @apiSuccess {String}jcr      检查人
     * @apiSuccess {String}jcsj     检查时间
     * @apiSuccess {String}jcjg     检查结果，1：正常，2：异常
     * @apiSuccess {String}wjwpdj   违禁物品登记
     * @apiSuccess {String}kysqdj   可疑伤情登记
     * @apiSuccess {String}zbmj     值班民警
     * @apiSuccess {String}djsj     登记时间
     * @apiSuccess {String}txrq     提讯日期
     * @apiSuccess {String}lfrzjh   来访人证件号
     * @apiSuccess {String}lfrpzh   来访人凭证文书号
     * @apiSuccess {String}lfsj     来访时间
     * @apiSuccess {String}lxdh     联系电话
     * @apiSuccess {String}lfrxm    来访人姓名
     * @apiSuccess {String}txry     退讯人员
     * @apiSuccess {String}txsj     退讯时间
     * @apiSuccess {String}snbh     所内编号
     * @apiSuccess {String}jsh      监室号
     * @apiSuccess {String}xm       姓名
     */
    @OpenAPI
    @ApiOperation("提审登记查询")
    @GetMapping("/tsdjQuery")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<Map<String, Object>> tsdjQuery(HttpServletRequest request, @RequestParam(name = "appcode") String appcode, @RequestParam(name = "jsbh") String jsbh, String json) {
        //接口id
        String interfaceId = "/v4/kss/tsdj/tsdjQuery";
        String state = request.getParameter("state");
        //通过校验获取查询参数
        ResponseMessage<Map<String, Object>> maps = this.userAuthorizatio(request, json, interfaceId);
        if (maps.getStatus() != 200) {
            return ResponseMessage.error(maps.getMessage());
        }

        QueryParam param = new QueryParam();
        param.and("jsbh", jsbh);

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("kssj"))) {
            param.and("kssj", TermType.gte, maps.getResult().get("kssj") + " 00:00:00");
        }

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jssj"))) {
            param.and("jssj", TermType.lte, maps.getResult().get("jssj") + " 23:59:59");
        }

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("bllx"))) {
            param.and("bllx", maps.getResult().get("bllx"));
        }

        if (!StringUtils.isNullOrEmpty(maps.getResult().get("txry"))) {
            param.and("txry", TermType.like, "%" + maps.getResult().get("txry") + "%");
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("tss"))) {
            param.and("tss", maps.getResult().get("tss"));
        }
        if (!StringUtils.isNullOrEmpty(maps.getResult().get("jsh"))) {
            param.and("jbxx_jsh", TermType.like, "%" + maps.getResult().get("jsh") + "%");
        }
        ResponseMessage<PagerResult<TsdjModel>> result = kssServerApis.tsdjquery(param);
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
