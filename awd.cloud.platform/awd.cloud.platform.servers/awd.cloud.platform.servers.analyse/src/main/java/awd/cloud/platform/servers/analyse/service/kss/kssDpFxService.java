package awd.cloud.platform.servers.analyse.service.kss;


import awd.cloud.platform.servers.analyse.dao.kss.kssDpFxDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class kssDpFxService {
    @Autowired
    private kssDpFxDao kssDpFxDao;

    //kss会见人数分析 : 家属会见、律师会见、提讯、总数
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_hjrs(){
        return kssDpFxDao.kss_hjrs();
    }

    //kss在所状态 :   已决、未决
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_zszt(){
        return kssDpFxDao.kss_zszt();
    }

    //kss人员管理情况分析 ： 械具、禁闭、单独关押、严管人员、耳目
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_rygl(){
        return kssDpFxDao.kss_rygl();
    }

    //kss全省办案阶段分析
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_qsbajd(String jsbh){
        return kssDpFxDao.kss_qsbajd(jsbh);
    }

    //kss年度入所量比对
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_ndrs(){
        return kssDpFxDao.kss_ndrs();
    }

    //kss风险情况分析
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_fxdj(){
        return kssDpFxDao.kss_fxdj();
    }

    //吸毒人员分析 吸毒人数、百分比
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> find_kss_xdry(){
        return kssDpFxDao.kss_xdry();
    }
    //吸毒人员分析 吸毒人数、百分比==总人数
    @UseDataSource("kss_ds")
    public Map<String,Object> find_kss_xdry_all(){
        return kssDpFxDao.kss_xdry_all();
    }







   @UseDataSource("kss_ds")
   public List<Map<String,Object>> zyrygyl(){
       return kssDpFxDao.zyrygyl();
   }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> hjdfx() {
        return kssDpFxDao.hjdfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> nlfx(){
        return kssDpFxDao.nlfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> whcd() {
       return kssDpFxDao.whcd();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> sffx() {
        return kssDpFxDao.sffx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> jkqkfx() {
        return kssDpFxDao.jkqkfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> jsryfx() {
        return kssDpFxDao.jsryfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> rsxz(String jsbh) {
        return kssDpFxDao.rsxz(jsbh);
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> ajlb(String jsbh) {
        return kssDpFxDao.ajlb(jsbh);
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> zccs() {
        return kssDpFxDao.zccs();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> lscsfx() {
        return kssDpFxDao.lscsfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swfz() {
        return kssDpFxDao.swfz();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> csyyfx(String jsbh) {
        return kssDpFxDao.csyyfx(jsbh);
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> yzblfz() {
        return kssDpFxDao.yzblfz();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> shsefx(String jsbh) {
        return kssDpFxDao.shsefx(jsbh);
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> mjsl() {
        return kssDpFxDao.mjsl();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> mjxbfx() {
        return kssDpFxDao.mjxbfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> mjjxfx(String jsbh) {
        return kssDpFxDao.mjjxfx(jsbh);
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> zzscfx() {
        return kssDpFxDao.zzscfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> yzjbfx() {
        return kssDpFxDao.yzjbfx();
    }
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> mjwhcd() {
        return kssDpFxDao.mjwhcd();
    }
}
