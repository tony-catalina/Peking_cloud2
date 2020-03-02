package awd.cloud.platform.servers.analyse.dao.jls;

import awd.framework.common.dao.api.Dao;

import java.util.List;
import java.util.Map;

public interface JlsDpFxDao extends Dao {

    //大屏jls送监未收人员
    List<Map<String, Object>> jls_sjwsry();
    //年度入所量比对
    List<Map<String, Object>> jls_ndrs();
    //超期羁押分析
    List<Map<String, Object>> jls_cqjy();
    //重点人员分析
    List<Map<String, Object>> jls_zdry();
    //所外就医
    List<Map<String, Object>> jls_swjy();
    //所内就医
    List<Map<String, Object>> jls_snjy();
    //人员管理情况分析=械具、禁闭、严管人员
    List<Map<String, Object>> jls_rygl();
    //人员管理情况分析=耳目
    List<Map<String, Object>> jls_rygl_em();
    //会见人数分析
    List<Map<String, Object>> jls_hjrs();
    //吸毒人员分析 吸毒人数、百分比
    List<Map<String, Object>>jls_xdry();
    //吸毒人员分析 吸毒人数、百分比
    Map<String, Object>jls_xdry_all();
    //jls风险情况分析
    List<Map<String, Object>>jls_fxdj();
    //关押期限分析
    List<Map<String, Object>>jls_gyqx();


    /*拒收人员分析*/
    List<Map<String, Object>> jsryfx();
    /*入所性质*/
    List<Map<String, Object>> rsxz(String jsbh);
    /*案件类别*/
    List<Map<String, Object>> ajlb(String jsbh);
    /*正常出所分析*/
    List<Map<String, Object>> zccs();
    /*临时出所分析*/
    List<Map<String, Object>> lscsfx();
    /*出所原因分析*/
    List<Map<String, Object>> csyyfx(String jsbh);
    /*深挖犯罪*/
    List<Map<String, Object>> swfz();
    /*严重暴力犯罪*/
    List<Map<String, Object>> yzblfz();
    /*涉黑涉恶分析*/
    List<Map<String, Object>> shsefx(String jsbh);
}
