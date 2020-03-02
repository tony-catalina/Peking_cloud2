package awd.cloud.platform.api;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import awd.bj.base.model.Variables;
import awd.bj.jls.model.ClcsModel;
import awd.bj.jls.model.CrjdjModel;
import awd.bj.jls.model.DwkfModel;
import awd.bj.jls.model.JbxxModel;
import awd.bj.jls.model.JkqkModel;
import awd.bj.jls.model.LkrywhModel;
import awd.bj.jls.model.QjcsModel;
import awd.bj.jls.model.ScqkModel;
import awd.bj.jls.model.ShgxModel;
import awd.bj.jls.model.SzjdjlModel;
import awd.bj.jls.model.TsdjModel;
import awd.bj.jls.model.TxthModel;
import awd.bj.jls.model.WlrydjModel;
import awd.bj.jls.model.WpglModel;
import awd.bj.jls.model.WpjsModel;
import awd.bj.jls.model.XxfbModel;
import awd.bj.jls.model.XyrAndPhotosModel;
import awd.bj.jls.model.XyrModel;
import awd.cloud.platform.api.hystrix.JlsServerFallBackFactory;
import awd.cloud.platform.model.jls.DwkfAndRyxxModel;
import awd.cloud.platform.model.jls.SqswInfoModel;
import awd.cloud.platform.model.jls.WlrydjInfoModel;
import awd.cloud.platform.model.jls.WpglInfoModel;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;


@FeignClient(name = "${awd.api.jls:AWD-JLS-SERVER}", fallbackFactory = JlsServerFallBackFactory.class)
@Component
public interface JlsServerApis {


    //关系登记新增
    @PostMapping("/jls_shgx")
    ResponseMessage<String> shgxSave(@RequestBody ShgxModel jls_shgxModel);

    //关系登记查询
    @GetMapping("/jls_jbxx")
    ResponseMessage<PagerResult<JbxxModel>> jbxxQueryForPage(@RequestBody QueryParam param);

    //业务动态查询记录
    @GetMapping("/jls_shgx")
    ResponseMessage<PagerResult<ShgxModel>> shgxQueryForPage(@RequestBody QueryParam param);

    //业务台账查询接口
    @GetMapping("/jls_shgx/jbxxlist")
    ResponseMessage<PagerResult<Map<String,Object>>> shgxJbxxQueryForPage(@RequestBody QueryParam param);

    //快速入所查询
    @GetMapping("/jls_xyr")
    ResponseMessage<PagerResult<XyrModel>> xyrQueryForPage(@RequestBody QueryParam param);

    @GetMapping("/jls_qjcs/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> tsclQueryForPage(QueryParam param);

    @GetMapping("/jls_jshj")
    ResponseMessage<String> qjcsStartflow(@RequestBody QjcsModel qjcsModel);

    /**
     * 期满出所管教核定
     * @param processDefinitionId
     * @param jls_clcsModel
     * @return
     */
    @PostMapping("/jls_clcs/addflow")
    ResponseMessage<String> clcsStartflow(@RequestParam(value = "processDefinitionId") String processDefinitionId, @RequestBody ClcsModel jls_clcsModel);

    @PostMapping("/jls_clcs/addSpflow")
    ResponseMessage<String> clcsSpflow(@RequestBody ClcsModel jls_ClcsModel, @RequestParam("taskid") String taskid, @RequestParam("yjlx") String yjlx);

    @PostMapping("/jls_jkqk/jkqkSave")
    ResponseMessage<String> jkqkSave(@RequestParam("taskid")String taskid, @RequestParam("ywlcid") String ywlcid, @RequestBody JkqkModel jkqkModel);

    @PostMapping("/jls_wpgl/wpglSaveByZcrs")
    ResponseMessage<String> wpglSaveByZcrs(@RequestParam("taskid") String taskid,
                                           @RequestParam("ywlcid") String ywlcid,
                                           @RequestBody List<WpglModel> wpglEntity);

    /**
     * 期满出所无附物退还
     */
    @PostMapping("/jls_clcs/flowImplement")
    public ResponseMessage<String> addFlowWfwth(@RequestParam(value = "taskid") String taskid,@RequestBody Variables variables);
    /**
     * 物品管理
     */
    @PostMapping("/jls_wpgl/PlFlowwpgl")
    public ResponseMessage<String> savePLAsCustom(@RequestBody WpglInfoModel wpglInfoModel);

    /**
     * 期满出所执行流程
     */
    @PostMapping("/jls_clcs/addZxflow")
    ResponseMessage<String> qmcsZxflow(@RequestBody ClcsModel jls_ClcsModel,@RequestParam("taskid") String taskid, @RequestParam("type") String type);
    /**
     * 期满出所开证打印
     */
    @PostMapping("/jls_clcs/addZxflow")
    ResponseMessage<String> qmcsKzdyZxflow(@RequestBody ClcsModel jls_ClcsModel,@RequestParam("taskid") String taskid, @RequestParam("type") String type);

    @PostMapping("/jls_crjdj/qjhsDrjs")
    ResponseMessage<String> qjhsDrjs(@RequestBody CrjdjModel crjdjModel, @RequestParam("taskid") String taskid);

    @GetMapping("/jls_qjcs/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> qjcsJbxxForPage(@RequestBody QueryParam param);

    @GetMapping("/jls_wlrydj")
    ResponseMessage<PagerResult<Map<String, Object>>> wlrydjQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/jls_wlrydj/wlrySave")
    ResponseMessage<String> wlryxgSave(@RequestBody WlrydjInfoModel wlrydjInfoModel);
    
    // 提前解除执行流程
    @PostMapping("/jls_clcs/addZxflow")
    ResponseMessage<String> tqjcZxflow(@RequestBody ClcsModel clcsModel,@RequestParam("taskid") String taskid, @RequestParam("type") String type);

    @PutMapping("/jls_wlrydj/{id}")
    ResponseMessage<String> wlrydjUpdate(@RequestParam(value = "id") String id, @RequestBody WlrydjModel wlrydjModel);

    /**
     * 期满出所业务动态查询
     */
    @GetMapping("/jls_clcs/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> ywtzQuery(@RequestBody QueryParam param);

    /**
     * 送物登记保存
     */
    @PostMapping("/jls_wpjs/addflow")
    ResponseMessage<String> swdjSave(@RequestParam(value = "processDefinitionId") String processDefinitionId, @RequestBody SqswInfoModel model);

    /**
     *物品接收保存
     */
    @PostMapping("/jls_wpjs/addZxflow")
    ResponseMessage<String> wpjsZxflow(@RequestParam("taskid") String taskid, @RequestBody WpjsModel wpjsEntity);

    /**
     * 派发物品保存
     */
    @PostMapping("/jls_wpjs/addPfwpflow")
    ResponseMessage<String> swPfwpSave(@RequestBody SqswInfoModel model);

    @GetMapping("/jls_wlrydj/wlrydjCount")
    ResponseMessage<PagerResult<Map<String, Object>>> wlrydjCount(@RequestParam(value = "jsbh") String jsbh,
                                                     @RequestParam(value = "starttime") String starttime, @RequestParam(value = "endtime") String endtime);

    @GetMapping("/jls_szjdjl")
    ResponseMessage<PagerResult<Map<String, Object>>> szjdjlQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/jls_szjdjl")
    ResponseMessage<String> szjdjlSave(@RequestBody SzjdjlModel szjdjlModel);

    @PutMapping("/jls_szjdjl/{id}")
    ResponseMessage<String> szjdjlUpdate(@RequestParam(value = "id") String id, @RequestBody SzjdjlModel szjdjlModel);

    @GetMapping("/jls_szjdjl/szjdjlCount")
    ResponseMessage<PagerResult<Map<String, Object>>> szjdjlCount(@RequestParam(value = "jsbh") String jsbh,
                                                     @RequestParam(value = "starttime") String starttime, @RequestParam(value = "endtime") String endtime);

    /**
     * 	嫌疑人快速入所
     * @param xyrAndPhotosModel
     * @return
     */
    @PostMapping("/jls_xyr/saveAsKsrs")
    ResponseMessage<String> saveAsKsrsOrJypz(@RequestBody XyrAndPhotosModel xyrAndPhotosModel);


    @GetMapping("/jls_scqk")
    ResponseMessage<PagerResult<Map<String, Object>>> scqkQueryForPage(@RequestBody QueryParam param);

    @PostMapping("/jls_scqk/scqkAndsctt")
    ResponseMessage<String> scqkAndsctt(@RequestBody ScqkModel scqkModel);
    /**
     * 提讯核对凭证保存
     */
    @PostMapping(value = "/jls_tsdj/hdpzSave")
    ResponseMessage<String> hdpzSave(@RequestBody TsdjModel tsdjModel);

    /**
     * 提讯登记呈批保存
     */
    @PostMapping(value = "/jls_tsdj/djcpSave")
    ResponseMessage<String> djcpSave(@RequestBody TsdjModel model);

    /**
     * 所长意见
     */
    @PostMapping(value = "/jls_tsdj/szyjSave")
    ResponseMessage<String> szyjSave(@RequestBody TsdjModel model);
    /**
     * 安排提询保存
     */
    @PostMapping(value = "/jls_tsdj/aptxSave")
    ResponseMessage<String> aptxSave(@RequestBody TsdjModel model);

    /**
     * 询完登记
     */
    @PostMapping(value = "/jls_tsdj/xwdjSave")
    ResponseMessage<String> xwdjSave(@RequestParam("stjcqk") String stjcqk,@RequestBody TsdjModel model);

    /**
     *  对外开放申请登记保存
     */
    @PostMapping("/jls_dwkf/dwkfBySqdj")
    ResponseMessage<String> dwkfBySqdj(@RequestBody DwkfAndRyxxModel dwkfAndRyxxModel);
    /**
     *  对外开放查询
     */
    @GetMapping("/jls_dwkf")
    ResponseMessage<PagerResult<DwkfModel>> dwkfQueryForPage(@RequestBody QueryParam param);

    /**
     *  对外开放所长意见保存
     */
    @PostMapping("/jls_dwkf/dwkfBySzyj")
    ResponseMessage<String> dwkfBySzyj(@RequestBody DwkfModel dwkfModel);

    /**
     *  亲情电话管教呈批保存
     */
    @PostMapping("/jls_txth/saveByGjcp")
    ResponseMessage<String> saveByGjcp(@RequestBody TxthModel txthEntity);
    /**
     *  亲情电话所领导意见保存
     */
    @PostMapping("/jls_txth/saveByLdsp")
    ResponseMessage<String> saveByLdsp(@RequestBody TxthModel txthEntity);
    /**
     *  亲情电话所通话登记保存
     */
    @PostMapping("/jls_txth/saveByLdsp")
    ResponseMessage<String> thdjsave(@RequestBody TxthModel txthEntity);

    /**
     * 提讯业务动态查询
     */
    @GetMapping("/jls_tsdj/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> txYwdtQuery(@RequestBody QueryParam arg1);
    /**
     * 提讯业务台账查询
     */
    @GetMapping("/jls_tsdj/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> txYwtzQuery(@RequestBody QueryParam arg1);

    
    /**
     * 检验凭证
     * @param xyrModel
     * @return
     */
    @PostMapping("/jls_xyr/saveAsXyrByJypz")
    ResponseMessage<String> saveAsXyrByJypz(@RequestBody XyrModel xyrModel);
    
    /**
     * 	查询流程中的基本信息
     *
     * @param variables
     * @return
     */
    @PostMapping("/jls_jbxx/getListCustom")
    ResponseMessage<PagerResult<JbxxModel>> getListCustom(Variables variables);
    
    /**
     * 简要登记
     * @param xyrModel
     * @return
     */
    @PostMapping("/jls_xyr/saveAsXyr")
    ResponseMessage<String> saveAsXyr(@RequestBody XyrModel xyrModel);
    
    @PostMapping("/jls_jkqk/jkqkSaveByZcrs")
    ResponseMessage<String> jkqkSaveByZcrs(@RequestParam("jkjcjg") String jkjcjg,
    								@RequestParam("taskid") String taskid,
                                     @RequestParam("ywlcid") String ywlcid,
                                     @RequestBody JkqkModel jkqkModel);

    /**
     * 送物业务台账查询
     */
    @GetMapping("/jls_wpjs/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> swYwtzQuery(@RequestBody QueryParam param);
    /**
     * 对外开放业务台账查询
     */
    @GetMapping("/jls_dwkf")
    ResponseMessage<PagerResult<DwkfModel>> dwkfYwtzQuery(@RequestBody QueryParam param);

    /**
     * 亲情电话业务台账查询
     */
    @GetMapping("/jls_txth/jbxxlist")
    ResponseMessage<PagerResult<Map<String, Object>>> qqdhYwtzQuery(@RequestBody QueryParam param);

    /**
     * 人员信息补录健康补录
     */
    @PostMapping("/jls_jkqk")
    ResponseMessage<String> jkqksave(@RequestBody JkqkModel jkqkModel);

    /**
     * 人员信息补录检查维护
     */
    @PutMapping("/jls_jkqk/{id}")
    ResponseMessage<String> jkqkUpdate(@RequestParam(value = "id") String id, @RequestBody JkqkModel jkqkModel);

    /**
     * 信息发布查询
     */
    @GetMapping("/jls_xxfb")
    ResponseMessage<PagerResult<XxfbModel>> xxfbQueryForPage(@RequestBody QueryParam param);
    /**
     * 信息发布保存
     */
    @PostMapping("/jls_xxfb/xxfbAndGztx")
    ResponseMessage<String> xxfbSave(@RequestBody XxfbModel xxfbModel);

    /**
     * 临控人员维护查询
     */
    @GetMapping("/jls_lkrywh")
    ResponseMessage<PagerResult<Map<String, Object>>> lkrywhQueryForPage(@RequestBody QueryParam param);
    /**
     * 临控人员维护保存
     */
    @PostMapping("/jls_lkrywh")
    ResponseMessage<String> lkrywhSave(@RequestBody LkrywhModel lkrywhModel);

}
