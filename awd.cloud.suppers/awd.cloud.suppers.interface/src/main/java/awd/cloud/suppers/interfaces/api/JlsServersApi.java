package awd.cloud.suppers.interfaces.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import awd.cloud.suppers.interfaces.api.hystrix.JlsServersApiFallBackFactory;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;

/**
 * @Description
 * @Date 2019-11-15
 **/

@FeignClient(name = "${awd.api.jls:AWD-JLS-SERVER}", fallbackFactory = JlsServersApiFallBackFactory.class)

@Component
public interface JlsServersApi {
    @RequestMapping(path = {"/jls/test"})
    ResponseMessage<String> test();

    //安全检查 aqjc
    @GetMapping("/jls_aqjc")
    ResponseMessage<PagerResult<Map<String, Object>>> queryAqjc(@RequestBody QueryParam param);

    @PostMapping("/jls_aqjc")
    ResponseMessage<String> saveAqjc(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_aqjc/{id}"})
    ResponseMessage<String> updateAqjcById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //帮教关系反馈 bjgxfk
    @GetMapping("/jls_bjgxfk")
    ResponseMessage<PagerResult<Map<String, Object>>> queryBjgxfk(@RequestBody QueryParam param);

    @PostMapping("/jls_bjgxfk")
    ResponseMessage<String> saveBjgxfk(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_bjgxfk/{id}"})
    ResponseMessage<String> updateBjgxfkById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //帮教关系反馈_帮教小组 bjgxfk_bjxz
    @GetMapping("/jls_bjgxfkBjxz")
    ResponseMessage<PagerResult<Map<String, Object>>> queryBjgxfkBjxz(@RequestBody QueryParam param);

    @PostMapping("/jls_bjgxfkBjxz")
    ResponseMessage<String> saveBjgxfkBjxz(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_bjgxfkBjxz/{id}"})
    ResponseMessage<String> updateBjgxfkBjxzById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //帮教记录 bjjl
    @GetMapping("/jls_bjjl")
    ResponseMessage<PagerResult<Map<String, Object>>> queryBjjl(@RequestBody QueryParam param);

    @PostMapping("/jls_bjjl")
    ResponseMessage<String> saveBjjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_bjjl/{id}"})
    ResponseMessage<String> updateBjjlById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //处理出所人员登记 clcs
    @GetMapping("/jls_clcs")
    ResponseMessage<PagerResult<Map<String, Object>>> queryClcs(@RequestBody QueryParam param);

    @PostMapping("/jls_clcs")
    ResponseMessage<String> saveClcs(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_clcs/{id}"})
    ResponseMessage<String> updateClcsById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //超期羁押催办表 cqjycbb
    @GetMapping("/jls_cqjycbb")
    ResponseMessage<PagerResult<Map<String, Object>>> queryCqjycbb(@RequestBody QueryParam param);

    @PostMapping("/jls_cqjycbb")
    ResponseMessage<String> saveCqjycbb(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_cqjycbb/{id}"})
    ResponseMessage<String> updateCqjycbbById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //出入监登记 crjdj
    @GetMapping("/jls_crjdj")
    ResponseMessage<PagerResult<Map<String, Object>>> queryCrjdj(@RequestBody QueryParam param);

    @PostMapping("/jls_crjdj")
    ResponseMessage<String> saveCrjdj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_crjdj/{id}"})
    ResponseMessage<String> updateCrjdjById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //出所健康情况 csjkqk
    @GetMapping("/jls_csjkqk")
    ResponseMessage<PagerResult<Map<String, Object>>> queryCsjkqk(@RequestBody QueryParam param);

    @PostMapping("/jls_csjkqk")
    ResponseMessage<String> saveCsjkqk(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_csjkqk/{id}"})
    ResponseMessage<String> updateCsjkqkById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //床位管理 cwgl
    @GetMapping("/jls_cwgl")
    ResponseMessage<PagerResult<Map<String, Object>>> queryCwgl(@RequestBody QueryParam param);

    @PostMapping("/jls_cwgl")
    ResponseMessage<String> saveCwgl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_cwgl/{id}"})
    ResponseMessage<String> updateCwglById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //抽血采样 cxcy
    @GetMapping("/jls_cxcy")
    ResponseMessage<PagerResult<Map<String, Object>>> queryCxcy(@RequestBody QueryParam param);

    @PostMapping("/jls_cxcy")
    ResponseMessage<String> saveCxcy(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_cxcy/{id}"})
    ResponseMessage<String> updateCxcyById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //带出拘室 dcjs
    @GetMapping("/jls_dcjs")
    ResponseMessage<PagerResult<Map<String, Object>>> queryDcjs(@RequestBody QueryParam param);

    @PostMapping("/jls_dcjs")
    ResponseMessage<String> saveDcjs(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_dcjs/{id}"})
    ResponseMessage<String> updateDcjsById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //djshjry
    @GetMapping("/jls_djshjry")
    ResponseMessage<PagerResult<Map<String, Object>>> queryDjshjry(@RequestBody QueryParam param);

    @PostMapping("/jls_djshjry")
    ResponseMessage<String> saveDjshjry(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_djshjry/{id}"})
    ResponseMessage<String> updateDjshjryById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //对外开放 dwkf
    @GetMapping("/jls_dwkf")
    ResponseMessage<PagerResult<Map<String, Object>>> queryDwkf(@RequestBody QueryParam param);

    @PostMapping("/jls_dwkf")
    ResponseMessage<String> saveDwkf(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_dwkf/{id}"})
    ResponseMessage<String> updateDwkfById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //对外开放人员信息 dwkf_ryxx
    @GetMapping("/jls_dwkfRyxx")
    ResponseMessage<PagerResult<Map<String, Object>>> queryDwkfRyxx(@RequestBody QueryParam param);

    @PostMapping("/jls_dwkfRyxx")
    ResponseMessage<String> saveDwkfRyxx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_dwkfRyxx/{id}"})
    ResponseMessage<String> updateDwkfRyxxById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //dyflxgws
    @GetMapping("/jls_dyflxgws")
    ResponseMessage<PagerResult<Map<String, Object>>> queryDyflxgws(@RequestBody QueryParam param);

    @PostMapping("/jls_dyflxgws")
    ResponseMessage<String> saveDyflxgws(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_dyflxgws/{id}"})
    ResponseMessage<String> updateDyflxgwsById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //耳目登记 emdj
    @GetMapping("/jls_emdj")
    ResponseMessage<PagerResult<Map<String, Object>>> queryEmdj(@RequestBody QueryParam param);

    @PostMapping("/jls_emdj")
    ResponseMessage<String> saveEmdj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_emdj/{id}"})
    ResponseMessage<String> updateEmdjById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //耳目登记反应情况 emdj_fyqk
    @GetMapping("/jls_emdjFyqk")
    ResponseMessage<PagerResult<Map<String, Object>>> queryEmdjFyqk(@RequestBody QueryParam param);

    @PostMapping("/jls_emdjFyqk")
    ResponseMessage<String> saveEmdjFyqk(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_emdjFyqk/{id}"})
    ResponseMessage<String> updateEmdjFyqkById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //房间设置(提审或会见室) fjsz
    @GetMapping("/jls_fjsz")
    ResponseMessage<PagerResult<Map<String, Object>>> queryFjsz(@RequestBody QueryParam param);

    @PostMapping("/jls_fjsz")
    ResponseMessage<String> saveFjsz(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_fjsz/{id}"})
    ResponseMessage<String> updateFjszById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //法律文书变更 flwsbg
    @GetMapping("/jls_flwsbg")
    ResponseMessage<PagerResult<Map<String, Object>>> queryFlwsbg(@RequestBody QueryParam param);

    @PostMapping("/jls_flwsbg")
    ResponseMessage<String> saveFlwsbg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_flwsbg/{id}"})
    ResponseMessage<String> updateFlwsbgById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //发生事故 fssg
    @GetMapping("/jls_fssg")
    ResponseMessage<PagerResult<Map<String, Object>>> queryFssg(@RequestBody QueryParam param);

    @PostMapping("/jls_fssg")
    ResponseMessage<String> saveFssg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_fssg/{id}"})
    ResponseMessage<String> updateFssgById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //附物登记 fwdj
    @GetMapping("/jls_fwdj")
    ResponseMessage<PagerResult<Map<String, Object>>> queryFwdj(@RequestBody QueryParam param);

    @PostMapping("/jls_fwdj")
    ResponseMessage<String> saveFwdj(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_fwdj/{id}"})
    ResponseMessage<String> updateFwdjById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //风险评估 fxpg
    @GetMapping("/jls_fxpg")
    ResponseMessage<PagerResult<Map<String, Object>>> queryFxpg(@RequestBody QueryParam param);

    @PostMapping("/jls_fxpg")
    ResponseMessage<String> saveFxpg(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_fxpg/{id}"})
    ResponseMessage<String> updateFxpgById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //公共事务 ggsw
    @GetMapping("/jls_ggsw")
    ResponseMessage<PagerResult<Map<String, Object>>> queryGgsw(@RequestBody QueryParam param);

    @PostMapping("/jls_ggsw")
    ResponseMessage<String> saveGgsw(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_ggsw/{id}"})
    ResponseMessage<String> updateGgswById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //glws
    @GetMapping("/jls_glws")
    ResponseMessage<PagerResult<Map<String, Object>>> queryGlws(@RequestBody QueryParam param);

    @PostMapping("/jls_glws")
    ResponseMessage<String> saveGlws(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_glws/{id}"})
    ResponseMessage<String> updateGlwsById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //岗位交接班 gwjjb
    @GetMapping("/jls_gwjjb")
    ResponseMessage<PagerResult<Map<String, Object>>> queryGwjjb(@RequestBody QueryParam param);

    @PostMapping("/jls_gwjjb")
    ResponseMessage<String> saveGwjjb(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_gwjjb/{id}"})
    ResponseMessage<String> updateGwjjbById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //工作通讯 gztx
    @GetMapping("/jls_gztx")
    ResponseMessage<PagerResult<Map<String, Object>>> queryGztx(@RequestBody QueryParam param);

    @PostMapping("/jls_gztx")
    ResponseMessage<String> saveGztx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_gztx/{id}"})
    ResponseMessage<String> updateGztxById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //交班记录 jbjl
    @GetMapping("/jls_jbjl")
    ResponseMessage<PagerResult<Map<String, Object>>> queryJbjl(@RequestBody QueryParam param);

    @PostMapping("/jls_jbjl")
    ResponseMessage<String> saveJbjl(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_jbjl/{id}"})
    ResponseMessage<String> updateJbjlById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //被拘人基本信息 jbxx
    @GetMapping("/jls_jbxx")
    ResponseMessage<PagerResult<Map<String, Object>>> queryJbxx(@RequestBody QueryParam param);

    @PostMapping("/jls_jbxx")
    ResponseMessage<String> saveJbxx(@RequestBody Map<String, Object> map);

    @PutMapping(path = {"/jls_jbxx/{id}"})
    ResponseMessage<String> updateJbxxById(@RequestParam("id") String id, @RequestBody Map<String, Object> map);

    //奖惩记录 jcjl


    //戒具 jj


    //检举揭发 jjjf


    //健康情况 jkqk


    //拘留所 jls


    //拘区设置 jq


    //拘室信息 js


    //精神病鉴定 jsbjd


    //家属会见 jshj


    //家属会见_会见人 jshj_hjr


    //家属联系 jslx


    //监室调整 jstz


    //及时性_模块使用情况 jsx_mksyqkb


    //集体教育 jtjy


    //教育改造质量评估 jygzzlpg


    //就医 jypz


    //领导检查 kh_ld


    //劳动犯 ldf


    //领导检查_监区秩序 ldjc_jqzx


    //领导检查_监室内务 ldjc_jsnw


    //领导检查_民警执法 ldjc_mjzf


    //领导检查_相关工作 ldjc_xggz


    //领导审批 ldsp


    //劳教登记 ljdj


    //临控人员维护 lkrywh


    //llythd


    //临时出所 lscs


    //律师会见 lshj


    //律师会见_监视 lshj_monitor


    //历史投牢花名册 lstlhmc


    //律师违规 lswg


    //联席会议 lxhy


    //留言簿 lyb


    //民警工作经历 mj_gzjl


    //民警基本信息 mj_jbxx


    //民警奖惩记录 mj_jcjl


    //民警教育经历 mj_jyjl


    //民警社会关系 mj_shgx


    //民警考核表 mjkh


    //民警谈话记录 mjthjl


    //秘密人员 mmry


    //照片 photos


    //请假出所 qjcs


    //提讯室情况 qktxs


    //在押人员权益保障 qybz


    //妊娠检测 rsjc


    //人员考核 rykh


    //人员考核统计 rykhtj


    //日用品领取 ryplq


    //再入所 ryxxxg


    //设备运行情况表 sbyx


    //视察情况 scqk


    //视察情况_视察代表 scqk_scdb


    //社会关系 shgx


    //sjdb sjbd


    //所间移押 sjyy


    //所内就医 snjy


    //伤情补录 sqbl


    //所情动态分析会议 sqdtfxhy


    //申诉复议 ssfy


    //死亡登记 swdj


    //深挖犯罪 swfz


    //所务会议 swhy


    //所外就医 swjy


    //系统表_看守所转押 sys_ksszy


    //接待记录 szjdjl


    //同案犯信息 taf


    //谈话教育 thjy


    //谈话教育_风险评估 thjy_fxpg


    //谈话教育_工作情况 thjy_gzqk


    //谈话教育_情况反映 thjy_qkfy


    //谈话教育_谈话教育_风险评估 thjy_thjy_fxpg


    //谈话教育_谈话教育_工作情况 thjy_thjy_gzqk


    //谈话教育_谈话教育_情况反映 thjy_thjy_qkfy


    //谈话教育_谈话教育_线索情况 thjy_thjy_xsqk


    //谈话教育_线索情况 thjy_xsqk


    //退回信息 thxx


    //谈话因素 thys


    //tjcz


    //逃跑登记 tpdj


    //提审登记 tsdj


    //通信通话记录 txth


    //违规事件处理 wgsjcl


    //外来人员登记 wlrydj


    //外来人员车辆信息 wlrydj_clxx


    //外来人员基本信息 wlrydj_ryxx


    //文明监室 wmjs


    //文明评比 wmpb


    //物品管理 wpgl


    //物品接收 wpjs


    //物品领取 wplq


    //卫生防疫 wsfy


    //文书管理 wsgl


    //文书上传 wssc


    //消毒日志 xdrz


    //消费品管理 xfp


    //巡检表 xjb


    //信件管理 xjgl


    //现金汇总表 xjhz


    //现金接收 xjjs


    //现金支出 xjzc


    //心里咨询 xlzx


    //人力情报线索登记 xsdj


    //新收过渡阶段考核 xsgdjdkh


    //新收过渡阶段考核_考核内容 xsgdjdkh_khnr


    //新收过渡阶段考核_日常表现 xsgdjdkh_rcbx


    //巡视记录 xsjl


    //新收人员身体检查 xsrystjc


    //巡视事件间隔 xssjjg


    //巡视设置 xssz


    //巡视组设置 xsz


    //信息发布 xxfb


    //嫌疑人信息录入 xyr


    //衣服号管理 yfhgl


    //严管人员 ygry


    //应急预案演练记录 yjyayl


    //药品发放 ypff


    //狱情分析 yqfx


    //医生巡诊 ysxz


    //预约登记 yydj


    //值班登记 zbdj


    //重病号管理 zbhgl


    //重点人员 zdry


    //zdybbjl


    //zdys


    //重点在押 zdzy


    //zpwhjn


    //重要案犯 zyaf


    //在押人员动态分析会议记录 zydtfxhy


}
