package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.JlsDpFxDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JlsDpFxService {
    @Autowired
    private JlsDpFxDao jlsDpFxDao;

    //大屏jls送监未收人员
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_sjwsry(){
        return  jlsDpFxDao.jls_sjwsry();
    }
    //年度入所量比对
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_ndrs(){
        return  jlsDpFxDao.jls_ndrs();
    }
    //超期羁押分析
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_cqjy(){
        return  jlsDpFxDao.jls_cqjy();
    }
    //重点人员分析
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_zdry(){
        return  jlsDpFxDao.jls_zdry();
    }
    //所外就医
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_swjy(){
        return  jlsDpFxDao.jls_swjy();
    }
    //所内就医
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_snjy(){
        return  jlsDpFxDao.jls_snjy();
    }
    //人员管理情况分析=械具、禁闭、严管人员
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_rygl(){
        return  jlsDpFxDao.jls_rygl();
    }
    //人员管理情况分析=耳目
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_rygl_em(){
        return  jlsDpFxDao.jls_rygl_em();
    }
    //会见人数分析
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_hjrs(){
        return  jlsDpFxDao.jls_hjrs();
    }
    //吸毒人员分析 吸毒人数、百分比
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_xdry(){
        return jlsDpFxDao.jls_xdry();
    }
    //吸毒人员分析 吸毒人数、百分比==总人数
    @UseDataSource("jls_ds")
    public Map<String,Object> find_jls_xdry_all(){
        return jlsDpFxDao.jls_xdry_all();
    }
    //jls风险情况分析
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_fxdj(){
        return jlsDpFxDao.jls_fxdj();
    }
    //jls关押期限分析
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> find_jls_gyqx(){
        return jlsDpFxDao.jls_gyqx();
    }




    @UseDataSource("jls_ds")
    public List<Map<String,Object>> jsryfx(){
        return jlsDpFxDao.jsryfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> rsxz(String jsbh){
        return jlsDpFxDao.rsxz(jsbh);
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> ajlb(String jsbh){
        return jlsDpFxDao.ajlb(jsbh);
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> zccs(){
        return jlsDpFxDao.zccs();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> lscsfx(){
        return jlsDpFxDao.lscsfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> csyyfx(String jsbh){
        return jlsDpFxDao.csyyfx(jsbh);
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> swfz(){
        return jlsDpFxDao.swfz();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> yzblfz(){
        return jlsDpFxDao.yzblfz();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> shsefx(String jsbh){
        return jlsDpFxDao.shsefx(jsbh);
    }
}
