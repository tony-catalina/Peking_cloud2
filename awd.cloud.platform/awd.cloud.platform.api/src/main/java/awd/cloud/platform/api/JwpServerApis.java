package awd.cloud.platform.api;

import awd.cloud.platform.api.hystrix.JwpServerFallBackFactory;
import awd.cloud.platform.model.jwp.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@FeignClient(name = "${awd.api.jwp:AWD-BJ-WATCHHOUSE-SERVER}",url = "http://192.168.4.57:18100",fallbackFactory =JwpServerFallBackFactory.class)
@Component
public interface JwpServerApis {

    @RequestMapping(value = "/jwp_qlyw", method = RequestMethod.GET)
    public ResponseMessage<PagerResult<Map<String, Object>>> queryQlywForPage(QueryParam arg1);

    /**
     * 一日作息查询
     *
     * @param qParam
     * @return
     */
    @GetMapping("/jwp_yrzx")
    public ResponseMessage<PagerResult<Map<String, Object>>> yrzxQueryForPage(QueryParam qParam);

    /**
     * 通知公告
     */
    @GetMapping("/jwp_nptg")
    public ResponseMessage<PagerResult<Map<String, Object>>> nptgByjbxxQueryForPage(QueryParam qParam);

    /**
     * /**
     * 心理评测
     */
    @GetMapping("/jwp_xlpc")
    public ResponseMessage<PagerResult<Map<String, Object>>> xlpcByjbxxQueryForPage(QueryParam qParam);

    /**
     * 监室点名查询
     */
    @GetMapping("/jwp_jsdm")
    public ResponseMessage<PagerResult<Map<String, Object>>> jsdmQuery(QueryParam qParam);

    /**
     * 学习教育查询
     */
    @GetMapping("/jwp_xxjy")
    public ResponseMessage<PagerResult<Map<String, Object>>> xxjyForPage(QueryParam qParam);


    /**
     * 监室监规查询
     */
    @GetMapping("/jwp_jsjg")
    public ResponseMessage<PagerResult<Map<String, Object>>> jsjgForPage(QueryParam qParam);

    /**
     * 服药确认查询
     */
    @GetMapping("/jwp_fyqr/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> fyqrForPage(QueryParam qParam);

    /**
     * 预约用水新增
     */
    @PostMapping("/jwp_yyys")
    public ResponseMessage<String> yyysSave(@RequestBody YyysModel yyysModel);

    /**
     * 每周菜谱查询
     */
    @GetMapping("/jwp_yzsp")
    public ResponseMessage<PagerResult<Map<String, Object>>> yzspForPage(QueryParam qParam);


    /**
     * 值班签到
     */
    @PostMapping("/jwp_zbqd")
    public ResponseMessage<String> zbqdSave(@RequestBody ZbqdModel zbqdModel);

    /**
     * 值班表查询
     */
    @GetMapping("/jwp_zbb")
    public ResponseMessage<PagerResult<Map<String, Object>>> zbbForPage(QueryParam qParam);

    /**
     * 坐值申请查询
     */
    @GetMapping("/jwp_zzsq")
    public ResponseMessage<PagerResult<Map<String, Object>>> zzsqQuery(QueryParam qParam);

    /**
     * 就医预约新增
     */
    @PostMapping("/jwp_jyyy")
    public ResponseMessage<String> jyyySave(@RequestBody JyyyModel jyyyModel);

    /**
     * 预约管理保存
     */
    @PostMapping("/jwp_hjyy")
    public ResponseMessage<String> hjyySave(@RequestBody HjyyModel hjyyModel);

    /**
     * 服药确认保存
     */
    @PostMapping("/jwp_fyqr")
    public ResponseMessage<String> fyqrSave(@RequestBody FyqrModel fyqrModel);

    /**
     * 可视对讲保存
     */
    @PostMapping("/jwp_ksdj")
    public ResponseMessage<String> ksdjSave(@RequestBody KsdjModel ksdjModel);


    /**
     * 视频点播保存
     */
    @PostMapping("/jwp_spdb")
    public ResponseMessage<String> spdbSave(@RequestBody SpdbModel spdbModel);


    /**
     * 心理评测保存
     */
    @PostMapping("/jwp_xlpc/xlpcSave")
    public ResponseMessage<Map<String,Object>> xlpcSave(@RequestBody XlpcModel xlpcModel);

    /**
     * 心理评测题目查询
     */
    @GetMapping("/jwp_xlpctm")
    public ResponseMessage<PagerResult<Map<String, Object>>> xlpctmQuery(QueryParam qParam);

    /**
     * 心理评测试卷查询
     */
    @GetMapping("/jwp_xlpcsj")
    public ResponseMessage<PagerResult<Map<String, Object>>> xlpcsjQuery(QueryParam qParam);

    /**
     * 每日小结新增
     */
    @PostMapping("/jwp_mrxj")
    public ResponseMessage<String> mrxjSave(@RequestBody MrxjModel mrxjModel);


    /**
     * 监室点名保存
     */
    @PostMapping("/jwp_jsdm")
    public ResponseMessage<String> jsdmSave(@RequestBody JsdmModel jsdmModel);

    /**
     * 管教谈话保存
     */
    @PostMapping("/jwp_zdry")
    public ResponseMessage<String> thjlSave(@RequestBody ThjlModel thjlModel);

    /**
     * 登记查询
     */
    @GetMapping("/jwp_jsyl/fxdjCx")
    public Map<String, Object> fxdjCxQuery(@RequestParam("jsbh") String jsbh, @RequestParam("jsh") String jsh);

    /**
     * 一周食谱查询
     *
     * @param jsbh
     * @return
     */
    @GetMapping("/jwp_yzsp/yzspList")
    public ResponseMessage<List<Map<String, Object>>> yzspQuery(@RequestParam("jsbh") String jsbh);


    /**
     * 监室预览查询
     */
    @GetMapping("/jwp_jsyl/ryxxList")
    public Map<String, Object> ryxxQuery(@RequestParam("jsbh") String jsbh, @RequestParam("jsh") String jsh);


    @GetMapping("/jwp_jskp/getJskp")
    ResponseMessage<PagerResult<Map<String, Object>>> getJskp(QueryParam queryParam);

    @GetMapping("/kss_jbxx/getListCustom")
    ResponseMessage<Map<String, Object>> getMoreCustom(@RequestParam("jsbh") String jsbh, @RequestParam("type") String type, @RequestParam("jsh") String jsh);

    /**
     * 械具使用登记保存
     */
    @PostMapping("/jwp_xj")
    public ResponseMessage<String> xjSave(@RequestBody XjModel xjModel);

    /**
     * 眼镜登记保存
     */
    @PostMapping("/jwp_yjdj")
    public ResponseMessage<String> yjdjSave(@RequestBody YjdjModel YjdjModel);

    /**
     * 用笔登记保存
     */
    @PostMapping("/jwp_ybdj")
    public ResponseMessage<String> ybdjSave(@RequestBody YbdjModel YbdjModel);

    /**
     * 监视考评保存
     */
    @PostMapping("/jwp_jskp/saveJskp")
    public ResponseMessage<String> saveJskp(@RequestBody JskpModel jskpModel);

    /**
     * 监室人员基本信息
     */
    @GetMapping("/jwp_jsyl/rybhList")
    public ResponseMessage<List<Map<String, Object>>> rybhQuery(@RequestParam("rybh") String rybh);

    /**
     * 重点人员保存
     */
    @PostMapping("/jwp_zdry")
    public ResponseMessage<String> zdrySave(@RequestBody ZdryModel zdryModel);

    /**
     * 视频信息查询
     *
     * @param qParam
     * @return
     */
    @GetMapping("/jwp_spdbk")
    public ResponseMessage<PagerResult<Map<String, Object>>> spdbkQueryForPage(QueryParam qParam);

    /**
     * 预约用水查询
     */
    @GetMapping("/jwp_yyys")
    public ResponseMessage<PagerResult<Map<String, Object>>> yyysForPage(QueryParam qParam);
}
