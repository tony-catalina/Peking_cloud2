package awd.cloud.platform.api;

import java.util.List;
import java.util.Map;

import awd.bj.kss.model.*;
import awd.cloud.platform.model.kss.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import awd.bj.base.model.Variables;
import awd.cloud.platform.api.hystrix.KssServerFallBackFactory;
import awd.cloud.platform.model.jwp.JbxxModelDO;
import awd.cloud.platform.model.jwp.SpdetailorderModelDO;
import awd.cloud.platform.model.jwp.WgsjclModel;
import awd.cloud.platform.utils.ModelList;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;

@FeignClient(name = "${awd.api.server:AWD-KSS-SERVER}", url = "http://192.168.4.50:13101", fallbackFactory = KssServerFallBackFactory.class)
@Component
public interface KssServerApis {


    @GetMapping("/xsjl")
    public ResponseMessage<PagerResult<XsjlModel>> xsjlQueryForPage(QueryParam param);


    @PostMapping("/xjhz/getYeWithRybhAndBz")
    public String getYeWithRybhAndBz(@RequestParam(value = "rybh") String rybh, @RequestParam(value = "currency") String currency);

    //人员基本信息查询
    @GetMapping("/jbxx")
    public ResponseMessage<PagerResult<Map<String, Object>>> queryForPage(QueryParam queryParam);

    @GetMapping("/jbxx/getRyxxAndPhoto")
    public ResponseMessage<List<Map<String, Object>>> getRyxxAndPhoto(QueryParam queryParam);

    //历史羁押具体情况查询
    @PostMapping("/jbxx/findLsjyJtqk")
    ResponseMessage<List<Map<String, Object>>> findLsjyJtqk(@RequestParam(value = "zjh") String zjh, @RequestParam(value = "jsbh") String jsbh);

    //基本信息-误出所h恢复
    @PostMapping("/jbxx/setWcshf")
    public ResponseMessage<String> wcshfSave(@RequestBody JbxxModel jbxxModel);

    //基本信息-入所安全检查
    @PostMapping("/jbxx/rsAqjc")
    public ResponseMessage<String> rsAqjc(@RequestParam(value = "taskid") String taskid, @RequestBody Map<String, Object> rsajMap);

    //基本信息-流程信息查询
    @PostMapping("/jbxx/getListCustom")
    ResponseMessage<PagerResult<Map<String, Object>>> getListCustom(Variables variables);

    //基本信息,救济物品保存
    @PostMapping("/jbxx/jjwpSave")
    public ResponseMessage<String> jjwpSave(@RequestParam(value = "taskid") String taskid, @RequestBody JjwpModal jjwpModal);

    //基本信息修改
    @PutMapping("/jbxx/{id}")
    ResponseMessage<String> update(@RequestParam(value = "id") String id, @RequestBody Kss_JbxxModelDO jbxxEntity);

    //基本信息-收押开征保存
    @PostMapping("/jbxx/getSykz")
    ResponseMessage<String> getSykz(@RequestParam(value = "taskid") String taskid, @RequestBody Kss_SykzModel sykzModal);

    //流程频率验证
    @PostMapping("/jbxx/VerificationHour")
    public ResponseMessage<Integer> VerificationHour(@RequestBody Map<String, Object> map);

    //流程月验证
    @PostMapping("/jbxx/VerificationMonth")
    ResponseMessage<Integer> VerificationMonth(@RequestBody Map<String, Object> map);

    //基本信息根据人员编号查询
    @GetMapping("/jbxx/getByRybh/{rybh}")
    ResponseMessage<Map<String, Object>> getJbxxByRybh(@PathVariable("rybh") String rybh);

    //基本信息-修改
    @PutMapping("/jbxx/shUpdateById")
    public ResponseMessage shUpdateById(@RequestParam(value = "rybh") String rybh);

    //
    @PostMapping("/jbxx/getJsXqList")
    ResponseMessage<List<Map<String, Object>>> getJsXqList(@RequestBody Map<String, Object> map);

    //根据识别服号查询
    @GetMapping("/jbxx/findCountBySbfh")
    ResponseMessage<String> findCountBySbfh(@RequestParam(value = "yfh") String yfh);

    //律师会见查询
    @GetMapping("/lshj")
    public ResponseMessage<PagerResult<LshjModel>> lshjQueryForPage(QueryParam qParam);

    //铺位管理查询
    @GetMapping("/pwgl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> pwglByjbxxQueryForPage(QueryParam qParam);

    //律师会见基本信息查询
    @GetMapping("/lshj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> lshjQuery(QueryParam param);

    //律师会见,登记呈报
    @PostMapping("/lshj/addLshjDjcb")
    public ResponseMessage<String> addLshjDjcb(@RequestParam(value = "taskid") String taskid, @RequestBody LshjModel model);

    //律师会见,监区提人登记
    @PostMapping("/lshj/addLshjCrjFlow")
    public ResponseMessage<String> addLshjCrjFlow(@RequestParam("flag") String flag, @RequestParam("taskid") String taskid, @RequestBody CrjjcModel model);

    //会见室是否为空
    @PostMapping("/lshj/hjsIsNull")
    public ResponseMessage<PagerResult<Map<String, Object>>> hjsIsNull(@RequestParam("hjs") String hjs, @RequestParam("jsbh") String jsbh);

    //律师会见,核对凭证
    @PostMapping("/lshj/addLshjHdpz")
    public ResponseMessage<String> addLshjHdpz(@RequestParam(value = "lcid") String lcid, @RequestBody LshjModel model);

    //订单详情保存(多个订单用list)
    @PostMapping("/spdetailorder/saveSpdetailAndDeduct")
    public ResponseMessage<String> insertByLists(@RequestParam(value = "processDefinitionId") String processDefinitionId, @RequestBody List<SpdetailorderModelDO> list, @RequestParam("jsbh") String jsbh);

    //救济物品申领记录基本信息查询
    @GetMapping("/jjwpsljl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> hqjjwpxxQueryForPage(QueryParam qParam);

    //提审登记基本信息查询
    @GetMapping("/tsdj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> txQuery(QueryParam param);

    //提审登记保存操作(流程)
    @PostMapping("/tsdj/addflow")
    public ResponseMessage<String> tsdjAddflow(@RequestBody Kss_TsdjModelDO tsdjModel, @RequestParam(value = "LcId") String LcId);

    //提审室状态查询
    @GetMapping("/tsdj/tssUseState")
    public ResponseMessage<List<Map<String, Object>>> TssUseState(@RequestParam(value = "tss") String tss);

    //提审登记监区提人
    @PostMapping("/tsdj/addJqtrFlow")
    public ResponseMessage<String> addJqtrFlow(@RequestBody Map<String, Object> map);

    //回所安全检查
    @PostMapping("/tsdj/addTsdjHsaqjc")
    public ResponseMessage<String> addTsdjHsaqjc(@RequestParam("taskid") String taskid, Map<String, Object> map);

    //退讯
    @PostMapping("/tsdj/addTsdjTx")
    public ResponseMessage<String> addTsdjTx(@RequestParam("taskid") String taskid, @RequestBody Kss_TsdjModelDO model);

    //提解登记基本信息查询
    @GetMapping("/tjdj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> tjdjQueryForPage(QueryParam qParam);

    //监室查询
    @GetMapping("/js")
    public ResponseMessage<PagerResult<Map<String, Object>>> jsQueryForPage(QueryParam qParam);

    //监室秩序检查
    @GetMapping("/jszxjc")
    public ResponseMessage<PagerResult<Map<String, Object>>> jszxjcQueryForPage(@RequestBody QueryParam qParam);

    //监室秩序检查保存
    @PostMapping("/jszxjc/addJszxjc")
    public ResponseMessage<String> jszxjcSave(@RequestBody JszxjcModel jszxjcModel);

    //监室秩序检查修改
    @PutMapping("/jszxjc/{id}")
    public ResponseMessage<String> jszxjcUpdate(@RequestParam(value = "id") String id, @RequestBody JszxjcModel jszxjcModel);

    //值日安排
    @GetMapping("/zrap")
    public ResponseMessage<PagerResult<Map<String, Object>>> zrapQueryForPage(QueryParam qParam);

    //照片查询
    @GetMapping("/photos/photoQuery")
    public ResponseMessage<PagerResult<Map<String, Object>>> photoQuery(QueryParam qParam);

    //就医基本信息查询
    @GetMapping("/jy/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jyByjbxxQueryForPage(QueryParam qParam);

    //订单详情-消费记录查询
    @GetMapping("/spdetailorder/xfjlList")
    ResponseMessage<PagerResult<Map<String, Object>>> spdetailorderByjbxxQueryForPage(QueryParam param);

    //救济物品管理查询
    @GetMapping("/kss_jjwpgl")
    public ResponseMessage<PagerResult<Map<String, Object>>> jjwpglQuery(QueryParam qParam);

    //违规事件处理查询
    @GetMapping("/wggl")
    public ResponseMessage<PagerResult<Map<String, Object>>> wgglQueryForPage(QueryParam qParam);

    //物品接受基本信息查询
    @GetMapping("/wpjs/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> wpjsQueryForPage(QueryParam qParam);

    //现金汇总基本信息查询
    @GetMapping("/xjhz/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> yecxQueryForPage(QueryParam qParam);

    //协助破案基本信息查询(照片地址)
    @GetMapping("/xzpa/jbxxlistWithPhoto")
    public ResponseMessage<PagerResult<Map<String, Object>>> xzpaByjbxxQueryForPage(QueryParam qParam);

    //值班登记查询
    @GetMapping("/zbdj")
    public ResponseMessage<PagerResult<Map<String, Object>>> zbdjQuery(QueryParam qParam);

    //谈话教育基本信息查询
    @GetMapping("/thjy/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> thjyQueryForPage(QueryParam qParam);

    //谈话教育保存
    @PostMapping("/thjy/thjySaveAndOverFlow")
    public ResponseMessage<String> thjySaveAndOverFlow(@RequestBody ThjyModel thjyEntity, @RequestParam(value = "taskid") String taskid);

    //物品接受基本信息查询
    @GetMapping("/wpjs/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> hqwpQueryForPage(QueryParam qParam);

    //现金接受基本信息查询
    @GetMapping("/xjjs/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> xjjsQuery(QueryParam qParam);

    //商品信息查询
    @GetMapping("/spxx")
    public ResponseMessage<PagerResult<Map<String, Object>>> spxxQuery(QueryParam qParam);

    //家属送物保存
    @PostMapping("/jssw/saveWp")
    public ResponseMessage<String> jsswSave(@RequestBody GxswModel gxswModel);

    //违规事件处理-今日违规查询
    @GetMapping("/wggl/jrwgQuery")
    public List<Map<String, Object>> jrwgQueryForPage(QueryParam qParam);

    //救济物品申领记录保存
    @PostMapping(value = "/jjwpsljl/addFlow")
    public ResponseMessage<Map<String, String>> jjwpsladdFlow(@RequestBody Map<String, Object> map);

    //违规事件处理保存
    @PostMapping("/wggl")
    public ResponseMessage<String> wgdjSave(@RequestBody WgsjclModel wgsjclModel);

    //物品管理查询
    @GetMapping("/wpgl")
    public ResponseMessage<PagerResult<Map<String, Object>>> wpglQuery(QueryParam qParam);

    //物品管理基本信息查询
    @GetMapping("/wpgl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> wpglJbxxListQuery(QueryParam qParam);

    //家属送物基本信息查询
    @GetMapping("/jssw/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jsswQuery(QueryParam qParam);

    //基本信息查询
    @GetMapping("/jbxx")
    ResponseMessage<PagerResult<JbxxModelDO>> jbxxQuery(QueryParam queryParam);

    //照片查询
    @GetMapping("/photos")
    public ResponseMessage<PagerResult<PhotosModel>> photosList(QueryParam param);

    //根据id修改照片记录
    @PutMapping("/photos/{id}")
    public ResponseMessage<String> photosUpdate(@RequestParam(value = "id") String id, @RequestBody PhotosModel photosModel);

    //照片保存
    @PostMapping("/photos/saveOrUpdate")
    public ResponseMessage<String> savePhotos(@RequestBody PhotosModel photo);

    //基本信息查询
    @GetMapping("/jbxx")
    public ResponseMessage<PagerResult<Map<String, Object>>> zdryQuery(QueryParam param);

    //消毒日志查询
    @GetMapping("/xdrz")
    public ResponseMessage<PagerResult<Map<String, Object>>> xdrzQuery(QueryParam param);


    @PostMapping("/zrap/findZrap")
    public ResponseMessage<List<Map<String, Object>>> findZrap(@RequestBody ZrapModel zrapModel);

    //民警基本信息查询
    @PostMapping("/mjjbxx/mjjbxxQuery")
    public PagerResult mjxxQuery(@RequestBody Map<String, String> map);

    //监室调整保存
    @PostMapping("/jstz/addJstz")
    public ResponseMessage<Map<String, String>> jstzSave(@RequestBody Kss_JstzModal kss_jstzModal);

    //外来人员查询
    @GetMapping("/wlry")
    public ResponseMessage<PagerResult<Map<String, Object>>> wlryQuery(QueryParam param);

    //医生巡诊保存
    @PostMapping("/ysxz/saveYsxzYz")
    public ResponseMessage<Integer> ysxzSave(@RequestBody Map<String, Object> params);

    //家属会见基本信息查询
    @GetMapping("/jshj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jshjQuery(QueryParam param);

    //消毒日志保存
    @PostMapping("/xdrz")
    public ResponseMessage<String> xdrzSave(@RequestBody XdrzModel xdrzModel);

    //临时出所查询
    @GetMapping("/lscs")
    public ResponseMessage<PagerResult<Map<String, Object>>> lscsQueryForPage(QueryParam queryParam);

    //临时出所登记流程
    @PostMapping(path = "/lscs/addflow")
    public ResponseMessage<String> lscsAddflow(@RequestParam(value = "processDefinitionId") String processDefinitionId, @RequestBody ModelList<LscsModel> lscslist);

    @GetMapping("/lscs/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForLscs(QueryParam queryParam);

    //临时出所-回所确认保存
    @PostMapping("/lscs/addLscsHsqr")
    public ResponseMessage<String> lscsSave(@RequestBody LscsModel lscsModel);

    //谈话教育保存
    @PostMapping("/thjy")
    public ResponseMessage<String> thjySave(@RequestBody ThjyModel thjyModel);

    //监室调整基本信息查询
    @GetMapping("/jstz/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jstzList(QueryParam param);

    //处理出所保存
    @PostMapping("/clcs/addflow")
    public ResponseMessage<String> clcsSave(@RequestBody List<Kss_CscsModel> list);

    //处理出所保存
    @PostMapping("/clcs/startflow")
    public ResponseMessage<String> saveStartflow(@RequestBody Kss_ClcsInfoModel clcsInfo);

    //现金汇总-财务查询
    @PostMapping("/xjhz/getCwcxList")
    public ResponseMessage<PagerResult<Map<String, Object>>> cwcxList(@RequestParam("rybh") String rybh,
                                                                      @RequestParam("pageSize") String pageSize,
                                                                      @RequestParam("pageIndex") String pageIndex,
                                                                      @RequestParam("sort") String sort,
                                                                      @RequestParam("order") String order);

    //监区查询
    @GetMapping("/jq")
    public ResponseMessage<PagerResult<JqModel>> jqQuery(QueryParam param);

    //嫌疑人查询
    @GetMapping("/xyr")
    public ResponseMessage<PagerResult<Map<String, Object>>> xyrQuery(QueryParam qParam);

    //嫌疑人-档案编号查询
    @PostMapping("/xyr/getDabh")
    public ResponseMessage<String> dabh();

    //嫌疑人修改
    @PostMapping("/xyr/updateById")
    public ResponseMessage<String> updateXyrById(@RequestParam("processDefinitionId") String processDefinitionId, @RequestBody Kss_XyrModel xyrModel);

    //嫌疑人-任务查询
    @PostMapping("/xyr/getTaskList")
    public ResponseMessage<PagerResult<Map<String, Object>>> getTaskList(@RequestBody Variables variables);

    @PostMapping("/xyr/saveAsShdj")
    public ResponseMessage<String> saveAsShdj(@RequestParam("processDefinitionId") String processDefinitionId, @RequestBody XyrModel xyrEntity);

    //嫌疑人-法律文书保存
    @PostMapping("/xyr/saveAsLegal")
    public ResponseMessage<String> saveAsLegal(@RequestParam("processDefinitionId") String processDefinitionId, @RequestBody XyrModel xyrEntity);

    //领导审批-入所审批
    @PostMapping("/Ldsp/saveRsLdsp")
    public ResponseMessage<String> ldspForRsdj(@RequestBody JkqkModel jkqkModel, @RequestParam(value = "taskid") String taskid, @RequestParam(value = "xm") String xm);


    //音频文件上传
    @PostMapping("/audio/audioUpload")
    public ResponseMessage<Map<String, Object>> audioUpload(@RequestBody AudioModel audioModel);

    //入所健康情况保存
    @PostMapping("/jkqk/firstJkjc")
    ResponseMessage<String> firstJkjc(@RequestParam(value = "taskid") String taskid, @RequestBody JkTmModal jkTmModal);

    //照片上传
    @RequestMapping(value = "/photos/uploadZpDfs", method = RequestMethod.POST)
    public String uploadZpDfs(@RequestParam("fileName") String fileName,
                              @RequestParam("file") byte[] file,
                              @RequestParam("ext") String ext);

    //健康情况查询
    @GetMapping("/jkqk")
    ResponseMessage<PagerResult<Map<String, Object>>> jkqkQueryForPage(QueryParam qParam);

    //嫌疑人-暂不收押保存
    @PostMapping("/xyr/saveZbsy")
    public ResponseMessage<String> saveZbsy(@RequestParam(value = "taskid") String taskid, @RequestBody XyrModel xyrModel);

    //手环发放查询
    @GetMapping("/shff")
    ResponseMessage<PagerResult<Map<String, Object>>> shffQueryForPage(QueryParam queryParam);

    //手环发放保存
    @PostMapping("/shff")
    public ResponseMessage<String> shffSave(@RequestBody Kss_ShffModel shffModel);

    //嫌疑人-附物登记保存
    @PostMapping("/xyr/getFwdjTaskList")
    public ResponseMessage<PagerResult<Map<String, Object>>> getFwdjTaskList(@RequestBody Variables variables);

    //物品接受-附物登记保存
    @PostMapping("/wpjs/syFwdj")
    public ResponseMessage<String> syFwdj(@RequestParam("taskid") String taskid, @RequestBody Kss_FwdjModel fwdjModel);

    //现金汇总基本信息查询
    @GetMapping("/xjhz/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> xjhzlist(QueryParam param);

    //权力义务告知-告知保存
    @PostMapping("/kss_qlywgz/saveGz")
    public ResponseMessage saveGz(@RequestParam(value = "taskid") String taskid, @RequestBody QlywgzModel qlywgzModel);

    //领导审批保存
    @PostMapping("/Ldsp/save")
    public ResponseMessage<?> ldspSave(@RequestBody LdspModelDO ldspEntity, @RequestParam(value = "taskid") String taskid);

    //现金汇总-现金退还保存
    @PostMapping("/xjhz/csxjth")
    public ResponseMessage<String> csxjth(@RequestBody XjhzModel xjhzModel);

    //处理出所-删除释放证明
    @PostMapping("/clcs/deleteFlowSfz")
    public ResponseMessage<String> deleteFlowSfz(@RequestParam(value = "taskid") String taskid, @RequestBody Variables variables);

    //处理出所
    @PostMapping("/clcs/csAndJbxx")
    public ResponseMessage<String> csAndJbxx(@RequestParam(value = "taskid") String taskid, @RequestBody List<Kss_CrjjcModelDO> crjjcList);

    //临时出所-领导审批保存
    @PostMapping("/lscs/addLscsLdsp")
    public ResponseMessage<String> addLscsLdsp(@RequestBody Map<String, Object> map);

    //临时出所保存
    @PostMapping("/lscs/addLscsCs")
    public ResponseMessage<String> addLscsCs(@RequestBody Map<String, Object> map);

    //提审证管理基本信息查询
    @GetMapping("/tszgl/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> tszglQuery(QueryParam param);

    //临时出所回所确认
    @PostMapping("/lscs/addLscsHsqr")
    public ResponseMessage<String> addLscsHsqr(@RequestBody Map<String, Object> map);

    //监外执行所务会意见保存(流程)
    @PostMapping("/jwzx/addSwhyj")
    public ResponseMessage<String> swhyjAdd(@RequestBody Variables variables);

    //监外执行保存操作(流程)
    @PostMapping("/jwzx/addflow")
    public ResponseMessage<String> jwzxjlAddflow(@RequestParam(value = "lcid") String lcid, @RequestBody JwzxjlModel jwzxModel);

    //撤销同案犯
    @PostMapping("/taf/removeTaf")
    public ResponseMessage<String> removeTaf(@RequestParam(value = "rybh") String rybh);

    //同案犯保存
    @PostMapping("/taf/saveTafs")
    public ResponseMessage<String> saveTafs(@RequestBody TafModel tafModel);

    //同案犯查询
    @PostMapping("/taf/findTaf")
    public ResponseMessage<PagerResult<?>> findTaf(@RequestBody TafModel tafEntity);

    //提解基本信息查询
    @GetMapping("/tjdj/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> jbxxForTj(QueryParam param);

    //延期基本信息查询
    @GetMapping("/yq/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> jbxxForYq(QueryParam param);

    //提审登流程提审登记节业务操作
    @PostMapping("/tsdj/addTsdj")
    public ResponseMessage<String> addTsdj(@RequestParam(value = "taskid") String taskid, @RequestParam(value = "ywlcid") String ywlcid, @RequestBody Kss_TsdjModelDO tsdjModel);

    //监外执行鉴定保存(流程)
    @PostMapping("/jwzx/addJwzxJd")
    public ResponseMessage<String> addJwzxJd(@RequestParam("taskid") String taskid, @RequestBody JwzxjlModel model, @RequestParam("type") String type);

    //延期证件核对
    @PostMapping("/yq/addflow")
    public ResponseMessage<String> yqAddflow(@RequestParam(value = "lcid") String lcid, @RequestBody Kss_YqModelDO model);

    //延期登记
    @PostMapping("/yq/addYqdjFlow")
    public ResponseMessage<String> addYqdjFlow(@RequestParam("taskid") String taskid, @RequestBody Kss_YqModelDO model);

    //批量延期登记
    @PostMapping("/yq/addYqdjPlFlow")
    public ResponseMessage<String> addYqdjPlFlow(@RequestBody Map<String, Object> map);

    //领导审批流程保存
    @PostMapping("/jslx/saveJslxLdsp")
    public ResponseMessage<Map<String, String>> saveJslxLdsp(@RequestParam(value = "taskid") String taskid, @RequestBody Kss_LdspModelDO ldspModel, @RequestParam(value = "type") String type);

    @GetMapping("/jwzx/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForJwzx(QueryParam queryParam);

    //提解登记检验凭证方法(开启流程)
    @PostMapping("/tjdj/addflow")
    public ResponseMessage<String> tjdjAddflow(@RequestBody TjdjModel tjdjModel, @RequestParam(value = "LcId") String LcId);

    //提解登记-提解登记保存
    @PostMapping("/tjdj/addTjdjFlow")
    public ResponseMessage<String> addTjdjFlow(@RequestParam("taskid") String taskid, @RequestBody TjdjModel model);


    @PostMapping("/tjdj/addTjCrjFlow")
    public ResponseMessage<String> addTjCrjFlow(@RequestParam("flag") String flag, @RequestParam("taskid") String taskid, @RequestBody Kss_TjAndCrjjcModel tjAndCrjjcModel);

    //环节变动基本信息查询
    @GetMapping("/hjbd/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForHjbd(QueryParam queryParam);

    //环节变动保存
    @PostMapping("/hjbd/hjbdSavePl")
    public ResponseMessage<String> saveHjbdPl(@RequestBody List<HjbdModel> list);

    //判决登记保存
    @PostMapping("/pjdj/pjdjAdd")
    public ResponseMessage<String> pjdjSave(@RequestBody PjdjModelDO pjdjEntity);

    //家属会见基本信息保存
    @GetMapping("/jshj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jbxxForJshj(QueryParam queryParam);

    //家属会见-会见室查询
    @PostMapping("/jshj/jshjHjs")
    public ResponseMessage<Map<String, Object>> jshjHjs(@RequestParam("hjs") String hjs, @RequestParam("jsbh") String jsbh);

    //家属会见会见登记(流程)
    @PostMapping("/jshj/addflow")
    public ResponseMessage<String> jshjAddflow(@RequestParam(value = "lcid") String lcid, @RequestBody Map<String, Object> map);

    //家属会见领导审批保存
    @PostMapping("/jshj/addJshjLdsp")
    public ResponseMessage<String> addJshjLdsp(@RequestParam("taskid") String taskid, @RequestBody Map<String, Object> map);

    //家属会见-出入监检查保存
    @PostMapping("/jshj/addJshjCrjjc")
    public ResponseMessage<String> addJshjCrjjc(@RequestParam("flag") String flag, @RequestParam("taskid") String taskid, @RequestBody CrjjcModelDO model);

    //家属联系保存
    @PostMapping("/jslx/addFlow")
    public ResponseMessage<Map<String, String>> jslxAddFlow(@RequestParam(value = "csid") String csid, @RequestBody Kss_JslxModelDO jslxModel);

    //接待民警家属联系保存
    @PostMapping("/jslx/saveJslx")
    public ResponseMessage<String> saveJslx(@RequestParam(value = "taskid") String taskid, @RequestBody Map<String, Object> map);

    //外来人员查询
    @GetMapping("/wlry")
    public ResponseMessage<PagerResult<Map<String, Object>>> wlryQueryForPage(QueryParam qParam);

    //外来人员保存
    @PostMapping("/wlry")
    public ResponseMessage<String> wlrydj(WlryModel model);

    //判决登录基本信息查询
    @GetMapping("/pjdj/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> jbxxForPjdj(QueryParam qParam);

    //会见完毕安全检查
    @PostMapping("/jshj/updateJshj")
    public ResponseMessage<String> updateJshj(@RequestParam("taskid") String taskid, @RequestBody JshjModelDO model);

    //离开登记保存
    @PutMapping("/wlry/{id}")
    public ResponseMessage<String> wlrylk(@PathVariable("id") String id, @RequestBody WlryModel model);

    //送钱送物查询
    @PostMapping("/xjjs/sqswlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> sqswList(@RequestBody Map<String, Object> map);

    //所间移压查询
    @GetMapping("/sjyy")
    ResponseMessage<PagerResult<Map<String, Object>>> sjyyQueryForPage(QueryParam queryParam);

    //领导接访查询
    @GetMapping("/szjdjl")
    public ResponseMessage<PagerResult<Map<String, Object>>> ldjfQueryForPage(QueryParam qParam);

    //领导接访登记
    @PatchMapping("/szjdjl")
    public ResponseMessage<String> jjdlAdd(SzjdjlModel model);

    //来访接访保存
    @PutMapping("/szjdjl/{id}")
    public ResponseMessage<String> jjdlUpdate(@PathVariable("id") String id, @RequestBody SzjdjlModel model);

    //所间移押保存
    @PostMapping("/sjyy/saveSjyys")
    public ResponseMessage<String> sjyySaveSjyys(@RequestBody SjyyModelDO sjyyModel);

    //开始所间移押
    @PostMapping("/sjyy/startSjyy")
    public ResponseMessage<String> startSjyy(@RequestParam(value = "rybhs") String rybhs, @RequestParam(value = "userName") String userName);

    //所间移压-拒收
    @PostMapping("/sjyy/startJs")
    public ResponseMessage<String> startJs(@RequestParam(value = "rybhs") String rybhs, @RequestParam(value = "userName") String userName);

    //看守所查询
    @GetMapping("/kss")
    public ResponseMessage<PagerResult<Map<String, Object>>> kssQueryForPage(QueryParam qParam);

    //提审证管理新增
    @PostMapping("/tszgl/tszglSave")
    public ResponseMessage<String> tszglSave(@RequestBody TszglModel tszglEntity);

    //律师违规查询
    @GetMapping("/lswg")
    public ResponseMessage<PagerResult<Map<String, Object>>> lswgQueryForPage(QueryParam qParam);

    //提审证修改
    @PutMapping("/tszgl/{id}")
    public ResponseMessage<String> tszglUpdate(@RequestParam(value = "id") String id, @RequestBody TszglModel tszglEntity);

    //岗位交接班查询
    @GetMapping("/gwjjb")
    public ResponseMessage<PagerResult<Map<String, Object>>> gwjjbList(@RequestBody QueryParam queryParam);

    //律师违规保存
    @PostMapping("/lswg")
    public ResponseMessage<String> lswgSave(@RequestBody LswgModel lswgModel);

    //查找民警接班信息
    @PostMapping("/gwjjb/findMjJbxx")
    public List<Map<String, Object>> findMjJbxx(@RequestParam(value = "jsbh") String jsbh, @RequestParam(value = "code") String code);

    //岗位交接班保存
    @PostMapping("/gwjjb/SaveGwjjb")
    public String SaveGwjjb(@RequestBody Map<String, Object> map);

    //岗位交接班交班确认
    @PostMapping("/gwjjb/gwjjbjbqr")
    public String gwjjbjbqr(@RequestBody Map<String, Object> map);

    //手环修改
    @PutMapping("/shff/{id}")
    public ResponseMessage<String> update(@RequestParam(value = "id") String id, @RequestBody ShffModel shffModel);

    //精神病鉴定查询
    @GetMapping("/jsbjd")
    ResponseMessage<PagerResult<Map<String, Object>>> jsbjdQueryForPage(QueryParam queryParam);

    //同案犯查询
    @GetMapping("/taf/getTafListByRybh")
    public ResponseMessage<PagerResult<Map<String, Object>>> findTaf(@RequestBody QueryParam param);

    //精神病鉴定保存
    @PostMapping("/jsbjd/addflow")
    ResponseMessage<String> jsbjdAddflow(@RequestParam(value = "lcid") String lcid, @RequestBody Kss_JsbjdModelDO jsbjdModel);

    //家属送物-送钱送物保存
    @PostMapping("/jssw/addSqsw")
    public ResponseMessage<String> addSqsw(@RequestBody Map<String, Object> map);

    //历史投牢花名册查询
    @GetMapping("/lstlhmc")
    ResponseMessage<PagerResult<Map<String, Object>>> lstlhmcQueryForPage(QueryParam queryParam);

    //入所登记照片保存
    @PostMapping("/photos/saveForRsdjZp")
    public ResponseMessage<String> saveForRsdjZp(@RequestBody Kss_RsdjModel rsdjModal);

    //监室调整保存
    @PostMapping("/jstz/addJstz")
    public ResponseMessage<Map<String, String>> addJstz(@RequestBody JstzModelDO jmd);

    //监室确认
    @PostMapping("/jstz/jsqdAndJbxx")
    public ResponseMessage<String> addJstzAndJbxx(@RequestBody List<Kss_JstzLdspModel> JstzLdspModel);

    //监室调整查询
    @GetMapping("/jstz/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jstzQueryJbxxForPage(@RequestBody QueryParam param);

    //管教领导审批保存
    @PostMapping("/GJLdsp/save")
    public ResponseMessage<String> ldspSavejstz(@RequestBody List<JstzLdspModel> jstzLdspModels);

    //涉维人员保存
    @PostMapping("/swrypg")
    public ResponseMessage<String> saveSwrypg(@RequestBody SwrypgModel model);

    //社会关系接收姓名查询
    @GetMapping("/shgx/shgxJsxmList")
    public ResponseMessage<PagerResult<Map<String, Object>>> shgxJsxmList(@RequestParam(value = "rybh") String rybh);

    //信件管理基本信息查询
    @GetMapping("/xjgl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> xjglJbxxlist(@RequestBody QueryParam queryParam);

    //信件管理保存
    @PostMapping("/xjgl")
    public ResponseMessage<String> xjglSave(XjglModel xjglModel);

    //根据床位号查询铺位
    @GetMapping("/pwgl/findPwglByCwh")
    public ResponseMessage<String> findPwglByCwh(@RequestParam(value = "cwh") String cwh);

    //耳目设置的保存操作
    @RequestMapping(value = "/emdj/addFlow", method = RequestMethod.POST)
    public ResponseMessage<Map<String, String>> emdjAddFlow(@RequestBody EmdjModelDO emdjEntityDo);

    //耳目-中队长审批
    @PostMapping("/emdj/emdjSpFlow")
    public ResponseMessage<String> emdjSpFlow(@RequestBody EmdjModel emdjModel, @RequestParam(value = "taskid") String taskid, @RequestParam(value = "type") String type);

    //耳目撤销的保存操作
    @RequestMapping(value = "/emdj/addCXFlow", method = RequestMethod.POST)
    public ResponseMessage<Map<String, Object>> emdjAddCXflow(@RequestBody EmdjModelDO emdjEntityDo);

    //铺位管理保存
    @PostMapping("/pwgl")
    public ResponseMessage<String> pwglSave(@RequestBody PwglModel data);

    //领导审批
    @PutMapping("/xjgl/{id}")
    public ResponseMessage<String> xjglUpdate(@RequestParam(value = "id") String id, @RequestBody XjglModel xjglModel);

    //情况反映保存
    @PostMapping("/qkfy")
    public ResponseMessage<String> qkfySave(@RequestBody QkfyModel qkfyModel);

    //情况反映查询
    @GetMapping("/qkfy")
    public ResponseMessage<PagerResult<Map<String, Object>>> qkfyQueryForPage(@RequestBody QueryParam map);

    //在押人员表现鉴定保存
    @PostMapping("/zdzy")
    public ResponseMessage<String> zdzySave(@RequestBody ZdzyModel zdzyModel);

    //在押人员奖励记录业务台账查询
    @GetMapping("/zyryjljl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> zyryjljlYwtz(QueryParam queryParam);

    //在押人员保存
    @PostMapping("/zdry/addFlow")
    public ResponseMessage<String> zdryaddFlow(@RequestBody ZdryModel zdryModel, @RequestParam(value = "processDefinitionId") String processDefinitionId);

    //在押人员审批保存
    @PostMapping("/zdry/zdrySpflow")
    public ResponseMessage<String> zdrySpflow(@RequestBody ZdryModel zdryModel, @RequestParam(value = "taskid") String taskid, @RequestParam(value = "type") String type);


    @GetMapping("/emdj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> emYwtz(QueryParam queryParam);

    //在押人员奖励记录保存
    @PostMapping("/zyryjljl/addflow")
    public ResponseMessage<String> addflow(@RequestParam("processDefinitionId") String processDefinitionId, @RequestBody ZyryjljlModel zyryjljlModel);

    //在押人员奖励记录-领导审批保存
    @PostMapping("/zyryjljl/jlglSpFlow")
    public ResponseMessage<String> jlglSpFlow(@RequestBody ZyryjljlModel zyryjljlModel, @RequestParam("taskid") String taskid, @RequestParam("type") String type);

    //查询新的结果
    @PostMapping("/zdry/selectNewResult")
    public ResponseMessage<Map<String, Object>> selectNewResult(@RequestParam("jsbh") String jsbh, @RequestParam("rybh") String rybh);

    //在押人员奖励记录-奖励管理登记保存
    @PostMapping("/zyryjljl/jlglDjFlow")
    public ResponseMessage<String> jlglDjFlow(@RequestBody ZyryjljlModel zyryjljlModel, @RequestParam("taskid") String taskid);

    //在押人员惩罚记录基本信息查询
    @GetMapping("/zyrycfjl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> zyrycfjlYwtz(QueryParam queryParam);

    //加减刑保存
    @PostMapping(value = "/jjx/addFlow")
    public ResponseMessage<Map<String, Object>> JJXaddFlow(@RequestBody Map<String, Object> map);

    //在押人员惩罚记录保存
    @PostMapping("/zyrycfjl/addflow")
    public ResponseMessage<String> cfglAddflow(@RequestBody Map<String, Object> map);

    //社会关系查询
    @GetMapping("/shgx")
    public ResponseMessage<PagerResult<Map<String, Object>>> shgxQueryForPage(QueryParam param);

    //社会关系的保存操作
    @PostMapping("/shgx/shgxsSave")
    public ResponseMessage<String> shgxsSave(@RequestBody ShgxsModel data);

    //社会关系基本信息查询
    @GetMapping("/shgx/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> shgxYwtz(QueryParam queryParam);

    //械具保存
    @PostMapping("/xj/addFlow")
    public ResponseMessage<Map<String, String>> xjAddFlow(@RequestBody Map<String, Object> map);

    //械具-领导审批
    @PostMapping("/xj/addSpflow")
    public ResponseMessage<String> xjsySpFlow(@RequestBody XjModel xjModel, @RequestParam(value = "taskid") String taskid, @RequestParam(value = "type") String type);

    //协助破案保存
    @PostMapping("/xzpa/addflow")
    public ResponseMessage<String> saveForXzpa(@RequestParam("processDefinitionId") String processDefinitionId, @RequestBody XzpaModel xzpaModel);

    //协助破案领导审批保存
    @PostMapping("/xzpa/xzpaSpflow")
    public ResponseMessage<String> xzpaSpFlow(@RequestBody XzpaModel xzpaModel, @RequestParam(value = "taskid") String taskid);

    //材料转递保存
    @PostMapping("/xzpa/xzpaflow")
    public ResponseMessage<String> saveForclzd(@RequestBody XzpaModel xzpaModel, @RequestParam(value = "taskid") String taskid);

    //协助破案基本信息拆线呢
    @GetMapping("/xzpa/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> xzpaYwtz(QueryParam queryParam);

    //在押人员惩罚记录-延长呈报
    @PostMapping("/zyrycfjl/addYcflow")
    public ResponseMessage<String> cfglYcflow(@RequestBody ZyrycfjlModel zyrycfjlModel, @RequestParam("taskid") String taskid);

    //处罚管理审批
    @PostMapping("/zyrycfjl/addSpflow")
    public ResponseMessage<String> cfglSpflow(@RequestBody ZyrycfjlModel zyrycfjlModel, @RequestParam("taskid") String taskid, @RequestParam("type") String type);

    //解除处罚登记
    @PostMapping("/zyrycfjl/jcCfflow")
    public ResponseMessage<String> cfglJcflow(@RequestBody ZyrycfjlModel zyrycfjlModel, @RequestParam("ywlcid") String ywlcid);

    //接济物品查询
    @GetMapping("/kss_jjwpgl")
    ResponseMessage<PagerResult<Map<String, Object>>> jjwpglqueryForPage(QueryParam qParam);

    //禁闭设置查询
    @GetMapping("/jb")
    public ResponseMessage<PagerResult<Map<String, Object>>> jbQueryForPage(@RequestBody QueryParam map);

    //救济物品保存
    @PostMapping("/kss_jjwpgl/jjwpglSave")
    ResponseMessage<String> jjwpglSave(@RequestBody JjwpglModel jjwpglModel);

    //禁闭设置保存
    @PostMapping("/jb/addFlow")
    public ResponseMessage<Map<String, String>> jbAddflow(@RequestBody Map<String, Object> map);

    //单独关押保存
    @PostMapping("/kss_ddgy/ddgyaddflow")
    public ResponseMessage<String> ddgyaddflow(@RequestBody DdgyModel ddgyModel);

    //单独关押流程保存
    @PostMapping("/kss_ddgy/ddgyExecuteflow")
    public ResponseMessage<String> ddgyExecuteflow(@RequestBody DdgyModel ddgyModel);

    //械具业务台账查询
    @GetMapping("/xj/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> xjYwtz(QueryParam queryParam);

    //单独关押领导审批
    @PostMapping("/kss_ddgy/ddgy_ldspAdd")
    public ResponseMessage<String> ddgy_ldspAdd(@RequestBody DdgyModel ddgyModel);

    //带入关押监室保存
    @PostMapping("/kss_ddgy/drgyjsAdd")
    public ResponseMessage<String> drgyjsAdd(@RequestBody DdgyModel ddgyModel);

    //耳目登记查询
    @GetMapping("/emdj")
    public ResponseMessage<PagerResult<Map<String, Object>>> emdjQueryForPage(@RequestBody QueryParam arg1);

    //耳目等级保存
    @PostMapping("/emdj")
    public ResponseMessage<String> emdjSave(@RequestBody EmdjModel emdjModel);

    //值日安排保存
    @PostMapping("/zrap/addZrap")
    public ResponseMessage<Integer> addZrap(@RequestBody ZrapModel zrapModel);

    //清监管理查询
    @GetMapping("/qjgl")
    public ResponseMessage<PagerResult<Map<String, Object>>> qjglQueryForPage(@RequestBody QueryParam qParam);

    //文明监室查询
    @GetMapping("/wmjs")
    public ResponseMessage<PagerResult<Map<String, Object>>> wmjsQueryForPage(@RequestBody QueryParam arg1);

    //文明监室保存
    @PostMapping("/wmjs/addWmjs")
    public ResponseMessage<?> wmjsSave(@RequestParam(value = "lcid") String lcid, @RequestBody WmjsModel wmjsModel);

    //耳目考核保存
    @PostMapping("/qjgl/addQjgl")
    public ResponseMessage<String> qjglSave(@RequestBody QjglModel qjglModel);

    //心理健康查询
    @GetMapping("/xljk")
    public ResponseMessage<PagerResult<Map<String, Object>>> xljkList(@RequestBody QueryParam queryParam);

    //心理健康保存
    @PostMapping("/xljk")
    public ResponseMessage<String> xljkSave(@RequestBody XljkModel data);

    //心理健康修改
    @PutMapping("/xljk/{id}")
    public ResponseMessage<String> xljkUpdateByPrimaryKey(@PathVariable(value = "id") String id, @RequestBody XljkModel data);

    //心理干预
    @GetMapping("/xlgy")
    public ResponseMessage<PagerResult<Map<String, Object>>> xlgyQueryForPage(@RequestBody QueryParam map);

    //心理干预保存
    @PostMapping("/xlgy")
    public ResponseMessage<String> xlgySave(@RequestBody XlgyModel xlgyModel);

    //心理干预修改
    @PutMapping("/xlgy/{id}")
    public ResponseMessage<String> xlgyUpdate(@RequestParam(value = "id") String id, @RequestBody XlgyModel xlgyModel);

    //处遇评鉴
    @GetMapping("/cypj")
    public ResponseMessage<PagerResult<Map<String, Object>>> cypjQueryForPage(@RequestBody QueryParam map);

    //处遇品级保存
    @PostMapping("/cypj/addcypj")
    public ResponseMessage<Map<String, String>> cypjAddcypj(@RequestBody CypjModel cypjModel);

    // 重点人员查询
    @GetMapping("/zdzy")
    public ResponseMessage<PagerResult<Map<String, Object>>> zdzyQueryForPage(@RequestBody QueryParam param);

    //在押案犯查询
    @GetMapping("/zyaf/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> zyafQueryForPage(QueryParam param);

    //在押案犯保存
    @PostMapping("/zyaf/addZyaf")
    public ResponseMessage<Map<String, Object>> addZyaf(@RequestParam(value = "id") String id, @RequestBody ZyafModel zyafModel);

    //在押案犯修改
    @PutMapping("/zyaf/{id}")
    public ResponseMessage<String> zyafUpdate(@RequestParam(value = "id") String id, @RequestBody ZyafModel zyafModel);

    //风险评估
    @GetMapping("/fxpg")
    public ResponseMessage<PagerResult<Map<String, Object>>> fxpgQueryForPage(@RequestBody QueryParam param);

    //同案犯保存
    @PostMapping("/taf/saveTafByAy")
    public ResponseMessage<String> saveTaf(@RequestBody TafModelIDO tafModelIDO);

    //严管人员查询
    @GetMapping("/ygry")
    public ResponseMessage<PagerResult<Map<String, Object>>> ygryQueryForPage(@RequestBody QueryParam map);

    //严管人员设置保存
    @PostMapping(path = "/ygry/addFlow")
    public ResponseMessage<Map<String, String>> ygryAddflow(@RequestBody Map<String, Object> map);

    //加扣分记录保存
    @PostMapping("/jkfjl")
    public ResponseMessage<String> jkfjlSave(@RequestBody JkfjlModel jkfjlModel);

    //加扣分查询
    @GetMapping("/jkfjl")
    public ResponseMessage<PagerResult<Map<String, Object>>> jkfjlQueryForPage(QueryParam param);

    //现金接受查询
    @GetMapping("/xjjs/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> xjjsQueryJbxxForPage(@RequestBody QueryParam param);

    //家属送物查询
    @GetMapping("/jssw/jsswListByRybh")
    public ResponseMessage<PagerResult<Map<String, Object>>> JsswListByRybh(QueryParam param);

    //家属送物根据id修改
    @PostMapping("/jssw/updateJssw")
    public ResponseMessage<String> updateJsswGjqrById(@RequestParam(value = "id") String id, @RequestBody JsswModel jsswModel);

    /**
     * 新增现金接收和社XjjsModelDO会关系（接济人信息）
     */
    @PostMapping("/xjjs/saveXjjsAndShgx")
    public ResponseMessage<Integer> saveXjjsAndShgx(@RequestBody Map<String, Object> map, @RequestParam(value = "jsbh") String jsbh);

    /**
     * 社会关系保存
     *
     * @param shgxModel
     * @return
     */
    @PostMapping("/shgx")
    public ResponseMessage<String> shgxsave(@RequestBody ShgxModel shgxModel);

    /**
     * 更新社会关系
     *
     * @param id
     * @param shgxEntity
     * @return
     */
    @PutMapping("/shgx/{id}")
    ResponseMessage<String> shgxupdate(@RequestParam(value = "id") String id, @RequestBody ShgxModel shgxEntity);

    /**
     * 现金接收更新
     */
    @PutMapping("/xjjs/{id}")
    public ResponseMessage<String> xjjsUpdate(@RequestParam(value = "id") String id, @RequestBody XjjsModelDO xjjsModel);

    /**
     * 现金汇总
     */
    @GetMapping("/xjhz")
    public ResponseMessage<PagerResult<XjhzModelDO>> xjhzQueryForPage(@RequestBody QueryParam param);

    /**
     * 修改现金汇总
     */
    @PutMapping("/xjhz/{id}")
    public ResponseMessage<String> xjhzUpdateID(@RequestParam(value = "id") String id, @RequestBody XjhzModelDO xjhzModel);

    //新增物品接收、社会关系、物品管理接口
    @PostMapping("/wpjs/saveWpAndShgx")
    public ResponseMessage<Integer> saveWpAndShgx(@RequestBody Map<String, Object> map, @RequestParam("jsbh") String jsbh);

    /**
     * 现金支出保存
     */
    @PostMapping("/xjzc")
    public ResponseMessage<String> xjzcSave(@RequestBody XjzcModel xjzcModel);

    /**
     * 现金汇总保存
     *
     * @param xjhzModel
     * @return
     */
    @PatchMapping("/xjhz")
    public ResponseMessage<String> saveOrUpdateXjhz(@RequestBody XjhzModelDO xjhzModel);

    /**
     * 现金支出查询
     */
    @GetMapping("/xjzc")
    public ResponseMessage<PagerResult<XjzcModel>> xjzcQueryForPage(@RequestBody QueryParam param);

    /**
     * 商品管理保存
     *
     * @param spxxModel
     * @return
     */
    @PostMapping("/spxx")
    public ResponseMessage<String> spxxSave(@RequestBody SpxxModel spxxModel);

    /**
     * 商品管理修改
     *
     * @param id
     * @param spxxModel
     * @return
     */
    @PutMapping("/spxx/{id}")
    public ResponseMessage<String> spxxUpdate(@RequestParam(value = "id") String id, @RequestBody SpxxModel spxxModel);

    @PostMapping("/spdetailorder/getProcessForGwgl")
    public ResponseMessage<PagerResult<Map<String, Object>>> getProcessForGwgl(@RequestBody Variables variables);

    /**
     * 批量修改
     */
    @PostMapping("/spdetailorder/updatespList")
    public ResponseMessage<String> spdetailorderUpdateByList(@RequestBody List<SpdetailorderModelDO> spList, @RequestParam(value = "jsbh") String jsbh);

    @PostMapping("/spdetailorder/UpdateSpdetailForList")
    public ResponseMessage<String> UpdateSpdetailForList(@RequestParam(value = "flag") String flag, @RequestBody List<SpdetailorderModelDO> spList);

    /**
     * 商品领取（可批量领取）
     */
    @PostMapping("/spdetailorder/spLq")
    public ResponseMessage<String> spLq(@RequestBody Map<String, Object> map);

    /**
     * 消费信息查询
     *
     * @param param
     * @return
     */
    @GetMapping("/xjzc")
    public ResponseMessage<PagerResult<XjzcModel>> xfxxList(@RequestBody QueryParam param);

    /**
     * 督导辅警工作
     */
    @GetMapping("/ddfjgz")
    public ResponseMessage<PagerResult<DdfjgzModel>> ddfjgzQueryForPage(QueryParam queryParam);

    @PostMapping("/ddfjgz")
    public ResponseMessage<String> ddfjgzSave(@RequestBody DdfjgzModel ddfjgzEntity);

    /**
     * 点名
     */
    @GetMapping("/dm")
    public ResponseMessage<PagerResult<Map<String, Object>>> dmQueryForPage(QueryParam queryParam);

    @PostMapping("/dm")
    public ResponseMessage<String> dmadd(@RequestBody DmModel data);

    /**
     * 权益保障
     */
    @GetMapping("/qybz")
    public ResponseMessage<PagerResult<QybzModel>> qybzQueryForPage(QueryParam queryParam);

    @PostMapping("/qybz")
    public ResponseMessage<String> qybzSave(QybzModel qybzModel);

    /**
     * 巡更
     *
     * @param queryParam
     * @return
     */
    @GetMapping("/xg")
    public ResponseMessage<PagerResult<XgModel>> xgQueryForPage(QueryParam queryParam);

    @GetMapping("/zyrybgcl")
    ResponseMessage<PagerResult<ZyrybgclModel>> zyrybgclQueryForPage(QueryParam queryParam);

    @PostMapping("/zyrybgcl")
    public ResponseMessage<String> zyrybgclSave(@RequestBody ZyrybgclModel zyrybgclEntity);

    /**
     * 陪同巡诊查询
     *
     * @param queryParam
     * @return
     */
    @GetMapping("/qybz")
    public ResponseMessage<PagerResult<QybzModel>> ptxzList(QueryParam queryParam);

    /**
     * 陪同巡诊保存
     *
     * @param qybzEntity
     * @return
     */
    @PostMapping("/qybz/saves")
    public ResponseMessage<String> ptxzAdd(@RequestBody QybzModel qybzEntity);

    @PostMapping("/xsjl")
    public ResponseMessage<String> xsjlSave(@RequestBody XsjlModel xsjlEntity);

    @PutMapping("/xsjl/{id}")
    public ResponseMessage<String> xsjlUpdate(@RequestParam(value = "id") String id, @RequestBody XsjlModel xsjlEntity);

    /**
     * 监区违规登记
     *
     * @param wgsjclEntity
     * @return
     */
    @PostMapping("/wggl/saves")
    public ResponseMessage<Integer> wgglSaves(@RequestBody WgsjclModelDO wgsjclEntity);

    @GetMapping("/jkrz")
    ResponseMessage<PagerResult<JkrzModel>> jkrzQueryForPage(QueryParam queryParam);

    /**
     * 维修跟随查询
     *
     * @param queryParam
     * @return
     */
    @GetMapping("/qybz")
    public ResponseMessage<PagerResult<QybzModel>> wxgsList(QueryParam queryParam);

    /**
     * 突发事件登记
     */
    @GetMapping("/tfsjdj")
    ResponseMessage<PagerResult<TfsjdjModel>> tfsjdjQueryForPage(QueryParam queryParam);

    /**
     * 维修跟随保存
     *
     * @param qybzEntity
     * @return
     */
    @PostMapping("/qybz/saves")
    public ResponseMessage<String> wxgsAdd(@RequestBody QybzModel qybzEntity);

    @PostMapping("/jkrz")
    public ResponseMessage<String> jkrzSave(@RequestBody JkrzModel jkrzEntity);

    /**
     * 安全检查
     */
    @GetMapping("/aqjc")
    public ResponseMessage<PagerResult<AqjcModel>> aqjcQueryForPage(@RequestBody QueryParam param);

    /**
     * 权益保障放风保存
     *
     * @param qybzEntity
     * @return
     */
    @PostMapping("/qybz/saves")
    public ResponseMessage<Integer> qybzSaves(@RequestBody QybzModel qybzEntity);

    /***
     *所情分析
     */
    @GetMapping("/sqfx")
    public ResponseMessage<PagerResult<SqfxModel>> sqfxList(QueryParam param);

    @RequestMapping(value = "/mjzp/uploadZpDfs", method = RequestMethod.POST)
    public String uploadZpDfs(@RequestBody UploadFileModel upfile);

    /**
     * 所情分析保存
     *
     * @param sqfxModel
     * @return
     */
    @PostMapping("/sqfx")
    public ResponseMessage<String> sqfxSave(@RequestBody SqfxModel sqfxModel);

    /**
     * 所情分析修改
     *
     * @param sqfxModel
     * @return
     */
    @PutMapping("/sqfx/{id}")
    public ResponseMessage<String> sqfxUpdate(@RequestParam(value = "id") String id, @RequestBody SqfxModel sqfxModel);

    @PostMapping("/aqjc")
    public ResponseMessage<String> aqjcSave(@RequestBody AqjcModel aqjcEntity);

    @PutMapping("/aqjc/{id}")
    public ResponseMessage<String> aqjcUpdate(@RequestParam(value = "id") String id, @RequestBody AqjcModel aqjcEntity);

    /**
     * 视察情况
     */
    @GetMapping("/scqk")
    public ResponseMessage<PagerResult<ScqkModel>> scqkQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/scqk")
    public ResponseMessage<String> scqkSave(@RequestBody ScqkModel scqkEntity);

    @PutMapping("/scqk/{id}")
    public ResponseMessage<String> scqkUpdate(@RequestParam(value = "id") String id, @RequestBody ScqkModel scqkEntity);


    /**
     * 集体教育
     */
    @GetMapping("/jtjy")
    public ResponseMessage<PagerResult<JtjyModel>> jtjyQueryForPage(QueryParam param);

    @PostMapping("/jtjy")
    public ResponseMessage<String> jtjySave(@RequestBody JtjyModel jtjyEntity);

    @PutMapping("/jtjy/{id}")
    public ResponseMessage<String> jtjyUpdate(@RequestParam(value = "id") String id, @RequestBody JtjyModel jtjyEntity);

    /**
     * 狱情分析
     */
    @GetMapping("/yqfx")
    public ResponseMessage<PagerResult<YqfxModel>> yqfxQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/yqfx")
    public ResponseMessage<String> yqfxSave(@RequestBody YqfxModel yqfxEntity);

    @PutMapping("/yqfx/{id}")
    public ResponseMessage<String> yqfxUpdate(@RequestParam(value = "id") String id, @RequestBody YqfxModel yqfxEntity);

    @PostMapping("/tfsjdj")
    public ResponseMessage<String> tfsjdjSave(@RequestBody TfsjdjModel tfsjdjEntity);

    @PutMapping("/tfsjdj/{id}")
    public ResponseMessage<String> tfsjdjUpdate(@RequestParam(value = "id") String id, @RequestBody TfsjdjModel tfsjdjEntity);

    /**
     * 所长接待记录
     */
    @GetMapping("/szjdjl")
    public ResponseMessage<PagerResult<SzjdjlModel>> szjdjlQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/szjdjl")
    public ResponseMessage<String> szjdjlSave(@RequestBody SzjdjlModel szjdjlEntity);

    @PutMapping("/szjdjl/{id}")
    public ResponseMessage<String> szjdjlUpdate(@RequestParam(value = "id") String id, @RequestBody SzjdjlModel szjdjlEntity);

    /**
     * 所务会议
     */
    @GetMapping("/swhy")
    public ResponseMessage<PagerResult<SwhyModel>> swhyQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/swhy")
    public ResponseMessage<String> swhySave(@RequestBody SwhyModel swhyEntity);

    @PutMapping("/swhy/{id}")
    public ResponseMessage<String> swhyUpdate(@RequestParam(value = "id") String id, @RequestBody SwhyModel swhyEntity);

    /**
     * 联席会议
     */
    @GetMapping("/lxhy")
    public ResponseMessage<PagerResult<LxhyModel>> lxhyQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/lxhy")
    public ResponseMessage<String> lxhySave(@RequestBody LxhyModel lxhyEntity);

    @PutMapping("/lxhy/{id}")
    public ResponseMessage<String> lxhyUpdate(@RequestParam(value = "id") String id, @RequestBody LxhyModel lxhyEntity);

    /**
     * 在押人员动态分析会议
     */
    @GetMapping("/zydtfxhy")
    public ResponseMessage<PagerResult<ZydtfxhyModel>> zydtfxhyQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/zydtfxhy")
    public ResponseMessage<String> zydtfxhySave(@RequestBody ZydtfxhyModel zydtfxhyEntity);

    @PutMapping("/zydtfxhy/{id}")
    public ResponseMessage<String> zydtfxhyUpdate(@RequestParam(value = "id") String id, @RequestBody ZydtfxhyModel zydtfxhyEntity);

    /**
     * 发生事故
     */
    @GetMapping("/fssg")
    public ResponseMessage<PagerResult<FssgModel>> fssgQueryForPage(QueryParam qParam);

    @PostMapping("/fssg")
    public ResponseMessage<String> fssgSave(@RequestBody FssgModel fssgEntity);

    @PutMapping("/fssg/{id}")
    public ResponseMessage<String> fssgUpdate(@RequestParam(value = "id") String id, @RequestBody FssgModel fssgEntity);

    @GetMapping("/jqzx")
    public ResponseMessage<PagerResult<JqzxModel>> queryForPageJqzx(@RequestBody QueryParam qParam);

    @PostMapping("/jqzx")
    public ResponseMessage<String> saveJqzx(@RequestBody JqzxModel jqzxEntity);

    /***
     *文秘宣传
     */
    @GetMapping("/wmxc")
    public ResponseMessage<PagerResult<WmxcModel>> wmxcList(QueryParam param);

    @PostMapping("/wmxc")
    public ResponseMessage<String> wmxcSave(@RequestBody WmxcModel wmxcModel);

    @PutMapping("/wmxc/{id}")
    public ResponseMessage<String> wmxcUpdate(@RequestParam(value = "id") String id, @RequestBody WmxcModel wmxcModel);

    //监室内务
    @GetMapping("/jsnw")
    public ResponseMessage<PagerResult<JsnwModel>> queryForPageJsnw(@RequestBody QueryParam qParam);

    //警情发布查询
    @GetMapping("/jqfb")
    public ResponseMessage<PagerResult<JqfbModelDO>> jqfbList(QueryParam param);

    //警情发布新增
    @PostMapping("/jqfb")
    public ResponseMessage<String> jqfbSave(@RequestBody JqfbModelDO jqfbModel);

    //警情发布修改
    @PutMapping("/jqfb/{id}")
    public ResponseMessage<String> jqfbUpdate(@RequestParam(value = "id") String id, @RequestBody JqfbModelDO jqfbModel);

    //其他工作人员管理查询
    @GetMapping("/gzrygl")
    public ResponseMessage<PagerResult<GzryglModel>> gzryglQueryForPage(QueryParam queryParam);

    //其他工作人员新增
    @PostMapping("/gzrygl")
    public ResponseMessage<String> gzryglSave(@RequestBody GzryglModel gzryglEntity);

    //其他工作人员修改
    @PutMapping("/gzrygl/{id}")
    public String gzryglUpdate(@RequestParam(value = "id") String id, @RequestBody GzryglModel gzryglEntity);


    @PostMapping("/jsnw")
    public ResponseMessage<String> saveJsnw(@RequestBody JsnwModel jsnwModel);

    /***
     * 党建管理
     */
    @GetMapping("/djgl")
    public ResponseMessage<PagerResult<DjglModel>> djglList(QueryParam param);

    @PostMapping("/djgl")
    public ResponseMessage<String> djglSave(@RequestBody DjglModel djglModel);

    @PutMapping("/djgl/{id}")
    public ResponseMessage<String> djglUpdate(@RequestParam(value = "id") String id, @RequestBody DjglModel djglModel);

    @GetMapping("/mjzf")
    public ResponseMessage<PagerResult<MjzfModel>> queryForPageMjzf(@RequestBody QueryParam qParam);

    /***
     * 值班考勤
     */
    @GetMapping("/zbkq")
    public ResponseMessage<PagerResult<ZbkqModel>> zbkqList(QueryParam param);

    @PostMapping("/zbkq")
    public ResponseMessage<String> zbkqSave(@RequestBody ZbkqModel zbkqModel);

    @PutMapping("/zbkq/{id}")
    public ResponseMessage<String> zbkqUpdate(@RequestParam(value = "id") String id, @RequestBody ZbkqModel zbkqModel);

    @PostMapping("/mjzf")
    public ResponseMessage<String> saveMjzf(@RequestBody MjzfModel mjzfModel);

    @GetMapping("/xggzyq")
    public ResponseMessage<PagerResult<XggzyqModel>> queryForPageXggzyq(@RequestBody QueryParam qParam);

    /**
     * 应急预案演练查询
     */
    @GetMapping("/yjyayl")
    public ResponseMessage<PagerResult<YjyaylModel>> yjyaylQueryForPage(@RequestBody QueryParam param);

    /**
     * 应急预案演练保存
     */
    @PostMapping("/yjyayl")
    public ResponseMessage<String> yjyaylSave(@RequestBody YjyaylModel yjyaylEntity);

    /**
     * 应急预案演练修改
     */
    @PutMapping("/yjyayl/{id}")
    public String yjyaylUpdate(@RequestParam(value = "id") String id, @RequestBody YjyaylModel yjyaylEntity);

    /**
     * 民警基本信息
     */
    @GetMapping("/mjjbxx")
    public ResponseMessage<PagerResult<MjjbxxModel>> mjjbxxQueryForPage(QueryParam qParam);

    @PostMapping("/xggzyq")
    public ResponseMessage<String> saveXggzyq(@RequestBody XggzyqModel xggzyqModel);


    /***
     *预案编制
     */
    @GetMapping("/yabz")
    public ResponseMessage<PagerResult<YabzModel>> yabzList(QueryParam param);

    @PostMapping("/yabz")
    public ResponseMessage<String> yabzSave(@RequestBody YabzModel yabzModel);

    @PutMapping("/yabz/{id}")
    public ResponseMessage<String> yabzUpdate(@RequestParam(value = "id") String id, @RequestBody YabzModel yabzModel);

    @GetMapping("/tsdj/jbxxlist")
    public ResponseMessage<PagerResult<TsdjModel>> tsdjquery(@RequestBody QueryParam qParam);

    /***
     *绩效考核
     */
    @GetMapping("/jxkh")
    public ResponseMessage<PagerResult<JxkhModel>> jxkhList(QueryParam param);

    @PostMapping("/jxkh")
    public ResponseMessage<String> jxkhSave(@RequestBody JxkhModel jxkhModel);

    @PutMapping("/jxkh/{id}")
    public ResponseMessage<String> jxkhUpdate(@RequestParam(value = "id") String id, @RequestBody JxkhModel jxkhModel);


    @GetMapping("/jshj/jbxxlist")
    public ResponseMessage<PagerResult<JshjModelDO>> jshjquery(@RequestBody QueryParam param);

    @GetMapping("/lshj/jbxxlist")
    public ResponseMessage<PagerResult<LshjModel>> lshjquery(@RequestBody QueryParam param);

    /**
     * 武器使用
     */
    @GetMapping("/wqsy")
    public ResponseMessage<PagerResult<WqsyModel>> getWqsy(@RequestBody QueryParam param);

    /**
     * 武器使用流程开启
     */
    @PostMapping("/wqsy/addFlow")
    public ResponseMessage<String> wqsyAddFlow(@RequestParam("lcid") String lcid, @RequestBody WqsyModel model);

    /**
     * 武器使用领导审批
     */
    @PostMapping("/wqsy/addLdsp")
    public ResponseMessage<String> addLdsp(@RequestParam("taskid") String taskid, @RequestBody LdspModel model);

    /**
     * 武器使用退还
     */
    @PostMapping("/wqsy/addWqgh")
    public ResponseMessage<String> addWqgh(@RequestParam("taskid") String taskid, @RequestBody WqsyModel entity);

    /**
     * 武器使用业务台账
     */
    @GetMapping("/wqsy/wqsyAndMjxx")
    public ResponseMessage<PagerResult<Map<String, Object>>> wqsyAndMjxx(@RequestParam("syly") String syly, @RequestParam("syts") String syts, @RequestParam("wqzl") String wqzl);


    @PostMapping("/mjjbxx/saveMjxxAndPicture")
    public ResponseMessage<String> saveMjxxAndPicture(@RequestBody Map<String, Object> params);

    @PostMapping("/mjjbxx/updateMjxxAndPicture")
    public ResponseMessage<String> updateMjxxAndPicture(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/mjzp/getPicByMjid/{mjid}", method = RequestMethod.GET)
    public String getPicByMjid(@PathVariable("mjid") String mjid);

    @PostMapping("/mjjbxx")
    public ResponseMessage<String> mjjbxxSave(@RequestBody MjjbxxModel mjjbxxEntity);

    @PutMapping("/mjjbxx/{id}")
    public ResponseMessage<String> mjjbxxUpdate(@RequestParam(value = "id") String id, @RequestBody MjjbxxModel mjjbxxEntity);

    @GetMapping("/zdzy/jbxxlist")
    public ResponseMessage<PagerResult<ZdzyModel>> zdzyquery(@RequestBody QueryParam param);


    /**
     * 巡检记录
     */
    @GetMapping("/xjjl")
    public ResponseMessage<PagerResult<XjjlModel>> xjjlList(QueryParam qParam);

    @PostMapping("/xjjl")
    public ResponseMessage<String> xjjlSave(@RequestBody XjjlModel xjjlEntity);

    @PutMapping("/xjjl/{id}")
    public ResponseMessage<String> xjjlUpdate(@RequestParam(value = "id") String id, @RequestBody XjjlModel xjjlEntity);

    /**
     * 教育质量改造评估
     */
    @GetMapping("/jygzzlpg")
    public ResponseMessage<PagerResult<JygzzlpgModel>> jypgList(QueryParam qParam);

    @PostMapping("/jygzzlpg")
    public ResponseMessage<String> jypgSave(@RequestBody JygzzlpgModel jygzzlpgModel);

    @PutMapping("/jygzzlpg/{id}")
    public ResponseMessage<String> jypgUpdate(@RequestParam(value = "id") String id, @RequestBody JygzzlpgModel jygzzlpgModel);

    @GetMapping("/clcs/jbxxlist")
    public ResponseMessage<PagerResult<ClcsModelDO>> clcsQueryForPage(@RequestBody QueryParam param);

    @GetMapping("/lsfx/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> lsfxQuery(@RequestBody QueryParam param);

    @GetMapping("/jslx/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> jslxQueryForPage(QueryParam param);

    /**
     * 民警教育经历查询
     */
    @GetMapping("/mjjyjl")
    public ResponseMessage<PagerResult<MjjyjlModel>> mjjyjlQueryForPage(QueryParam qParam);

    /**
     * 民警教育经历保存
     */
    @PostMapping("/mjjyjl")
    public ResponseMessage<String> mjjyjlSave(@RequestBody MjjyjlModel mjjyjlEntity);

    /**
     * 民警教育经历修改
     */
    @PutMapping("/mjjyjl/{id}")
    public ResponseMessage<String> mjjyjlUpdate(@RequestParam(value = "id") String id, @RequestBody MjjyjlModel mjjyjlEntity);

    /**
     * 民警奖惩记录查询
     */
    @GetMapping("/mjjcjl")
    public ResponseMessage<PagerResult<MjjcjlModel>> mjjcjlQueryForPage(QueryParam qParam);

    /**
     * 民警奖惩记录保存
     */
    @PostMapping("/mjjcjl")
    public ResponseMessage<String> mjjcjlSave(@RequestBody MjjcjlModel mjjcjlEntity);

    /**
     * 民警奖惩记录修改
     */
    @PutMapping("/mjjcjl/{id}")
    public ResponseMessage<String> mjjcjlUpdate(@RequestParam(value = "id") String id, @RequestBody MjjcjlModel mjjcjlEntity);

    /**
     * 民警工作经历查询
     */
    @GetMapping("/mjgzjl")
    public ResponseMessage<PagerResult<MjgzjlModel>> mjgzjlQueryForPage(QueryParam qParam);

    /**
     * 民警工作经历保存
     */
    @PostMapping("/mjgzjl")
    public ResponseMessage<String> mjgzjlSave(@RequestBody MjgzjlModel mjgzjlEntity);

    /**
     * 民警工作经历修改
     */
    @PutMapping("/mjgzjl/{id}")
    public ResponseMessage<String> mjgzjlUpdate(@RequestParam(value = "id") String id, @RequestBody MjgzjlModel mjgzjlEntity);

    /**
     * 民警社会关系查询
     */
    @GetMapping("/mjshgx")
    public ResponseMessage<PagerResult<MjshgxModel>> mjshgxQueryForPage(QueryParam qParam);

    /**
     * 民警社会关系保存
     */
    @PostMapping("/mjshgx")
    public ResponseMessage<String> mjshgxSave(@RequestBody MjshgxModel mjshgxEntity);

    /**
     * 民警社会关系修改
     */
    @PutMapping("/mjshgx/{id}")
    public ResponseMessage<String> mjshgxUpdate(@RequestParam(value = "id") String id, @RequestBody MjshgxModel mjshgxEntity);

    @GetMapping("/xsjl")
    public ResponseMessage<PagerResult<Map<String, Object>>> xsjlList(QueryParam param);

    /**
     * 值班
     */
    @PostMapping("/zbdj/queryOne")
    public ResponseMessage<List<ZbdjModel>> zbdjSelectOne(@RequestParam(value = "jsbh") String jsbh);

    @PostMapping("/zbdj/addZbdj")
    public ResponseMessage<String> zbdjSave(@RequestBody List<ZbdjModel> list);

    /**
     * 历史值班
     */
    @GetMapping("/zbdjhistory/zbdjHistoryList")
    public ResponseMessage<PagerResult<ZbdjhistoryModel>> zbdjHistoryList(QueryParam param);

    @GetMapping("/jkfjl/jbxxlist")
    public ResponseMessage<PagerResult<Map<String, Object>>> cyflcxquery(QueryParam param);

    /**
     * 人员信息修改
     *
     * @param id
     * @param jbxxEntity
     * @return
     */
    @PutMapping("/jbxx/{id}")
    ResponseMessage<String> ryxxUpdate(@RequestParam(value = "id") String id, @RequestBody JbxxModel jbxxEntity);

    @GetMapping("/jjx")
    public ResponseMessage<PagerResult<Map<String, Object>>> jjxQueryForPage(QueryParam param);

    /**
     * 消毒日志
     */
    @GetMapping("/xdrz")
    public ResponseMessage<PagerResult<XdrzModel>> xdrzList(QueryParam param);

    @PutMapping("/xdrz/{id}")
    public ResponseMessage<String> xdrzUpdate(@RequestParam(value = "id") String id, @RequestBody XdrzModel xdrzModel);

    @PostMapping(value = "/jjx/addFlow")
    public ResponseMessage<String> addFlow(Map<String, Object> map1);

    @GetMapping("/tscl")
    public ResponseMessage<PagerResult<Map<String, Object>>> tsclQueryForPage(QueryParam param);

    @PostMapping("/tscl/addTscl")
    public ResponseMessage<String> addTscl(Map<String, Object> map1);

    @PostMapping("/tscl/addTscldj")
    public ResponseMessage<String> addTscldj(@RequestParam(value="taskid")String taskid, @RequestParam(value="rybh")String rybh,@RequestBody TsclModel tsclModel);
}
