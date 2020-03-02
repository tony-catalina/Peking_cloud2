package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_JqDao;
import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Jls_JbxxService {
    @Autowired
    private Jls_JbxxDao jls_jbxxDao;
    @Autowired
    private Jls_JqDao jls_JqDao;


    //超期羁押=上位机版本
    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> swj_cqjyCount(String starttime,String endtime) {
        return jls_jbxxDao.swj_cqjyCount(starttime,endtime);
    }

    //下面上位机版本
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swj_hyzklh(String starttime,String endtime) {
        return jls_jbxxDao.swj_hyzklh(starttime,endtime);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swj_hyzkso(String starttime,String endtime) {
        return jls_jbxxDao.swj_hyzkso(starttime,endtime);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swj_jkzkslqx(String starttime,String endtime) {
        return jls_jbxxDao.swj_jkzkslqx(starttime,endtime);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swj_jkzkcj(String starttime,String endtime) {
        return jls_jbxxDao.swj_jkzkcj(starttime,endtime);
    }
    //上面上位机版本

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> hyzklh() {
        return jls_jbxxDao.hyzklh();
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> hyzkso() {
        return jls_jbxxDao.hyzkso();
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jkzkslqx() {
        return jls_jbxxDao.jkzkslqx();
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jkzkcj() {
        return jls_jbxxDao.jkzkcj();
    }



    @UseDataSource("jls_ds")
    public List<Map<String, Object>> queryClgyfxList(String starttime,String endtime){
        List<Map<String,Object>> result=jls_jbxxDao.queryClgyfxNum(starttime,endtime) ;
        for (Map<String,Object> map:result) {
                  String dz=(String) map.get("dz");
                  map.put("dzString",CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> Zdry_rqFx(String starttime, String endtime){
        List<Map<String,Object>> result = jls_jbxxDao.ZdryInfo(starttime, endtime);
        for (Map<String,Object> map:result){
            String dz = (String)map.get("dz");
            map.put("dzString",CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }

    //查看违规类型
    @UseDataSource("jls_ds")
    public ArrayList<HashMap<String,Object>> select_wglx(String starttime, String endtime) {

        return jls_jbxxDao.select_wglx(starttime,endtime);
    }

    //上位机违规情况
    @UseDataSource("jls_ds")
    public List<HashMap<String,Object>> swj_wglx(String starttime, String endtime) {
        List<HashMap<String,Object>> result = jls_jbxxDao.swj_wglx(starttime,endtime);
        for (HashMap<String,Object> map:result){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }

        //大屏kss监区违规程度
    @UseDataSource("jls_ds")
    public ResponseMessage< ArrayList<Object>> select_wgcd(String jsbh) {

        ArrayList<Object> list1 = null;
        try {
            ArrayList<HashMap<String, Object>> list = jls_jbxxDao.select_wgcd(jsbh);
            list1 = new ArrayList<>();
            if(list.size()>0){
                for(HashMap<String, Object> key:list){
                    String all="0";String qw="0";String zd="0";String yz="0";
                    Object all0 = key.get("all");
                    Object qw1 = key.get("qw");
                    Object zd1 = key.get("zd");
                    Object yz1 = key.get("yz");
                    if(key!=null && !"".equals(key)){
                        if(all0!=null&&!"".equals(all0.toString())){
                            all=all0.toString();
                        }
                        if(qw1!=null&&!"".equals(qw1.toString())){
                            qw=qw1.toString();
                        }
                        if(zd1!=null&&!"".equals(zd1.toString())){
                            zd=zd1.toString();
                        }
                        if(yz1!=null&&!"".equals(yz1.toString())){
                            yz=yz1.toString();
                        }


                    }
                    if(!all.equals("0")) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("id", 0);
                        map.put("type", 0);
                        map.put("rule", "轻");
                        map.put("name", "轻微违规情况");
                        map.put("value", qw);
                        map.put("deci", String.format("%.2f", (float) Integer.parseInt(qw) / (float) Integer.parseInt(all) * 100));

                        HashMap<String, Object> map1 = new HashMap<>();
                        map1.put("id", 1);
                        map1.put("type", 1);
                        map1.put("rule", "中");
                        map1.put("name", "一般违规情况");
                        map1.put("value", zd);
                        map1.put("deci", String.format("%.2f", (float) Integer.parseInt(zd) / (float) Integer.parseInt(all) * 100));

                        HashMap<String, Object> map2 = new HashMap<>();
                        map2.put("id", 2);
                        map2.put("type", 2);
                        map2.put("rule", "重");
                        map2.put("name", "严重违规情况");
                        map2.put("value", yz);
                        map2.put("deci", String.format("%.2f", (float)Integer.parseInt(yz) / (float) Integer.parseInt(all) * 100));


                        list1.add(map);list1.add(map1);list1.add(map2);
                        //return   list1;
                    }else{
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("id", 0);
                        map.put("type", 0);
                        map.put("rule", "轻");
                        map.put("name", "轻微违规情况");
                        map.put("value", qw);
                        map.put("deci", "0");

                        HashMap<String, Object> map1 = new HashMap<>();
                        map1.put("id", 1);
                        map1.put("type", 1);
                        map1.put("rule", "中");
                        map1.put("name", "一般违规情况");
                        map1.put("value", zd);
                        map1.put("deci", "0");

                        HashMap<String, Object> map2 = new HashMap<>();
                        map2.put("id", 2);
                        map2.put("type", 2);
                        map2.put("rule", "重");
                        map2.put("name", "严重违规情况");
                        map2.put("value", yz);
                        map2.put("deci", "0");


                        list1.add(map);list1.add(map1);list1.add(map2);
                    }
                }
            }else{
                HashMap<String, Object> map = new HashMap<>();
                map.put("id", 0);
                map.put("type", 0);
                map.put("rule", "轻");
                map.put("name", "轻微违规情况");
                map.put("value", 0);
                map.put("deci", 0);

                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("id", 1);
                map1.put("type", 1);
                map1.put("rule", "中");
                map1.put("name", "一般违规情况");
                map1.put("value", 0);
                map1.put("deci", 0);
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("id", 2);
                map2.put("type", 2);
                map2.put("rule", "重");
                map2.put("name", "严重违规情况");
                map2.put("value", 0);
                map2.put("deci", 0);

                list1.add(map);list1.add(map1);list1.add(map2);  //title

            }
            return ResponseMessage.ok(list1);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return ResponseMessage.error("请求失败，请重新刷新页面");
        }


    }


    @UseDataSource("jls_ds")
    public List<Map<String, Object>> cqjyCount() {
        return jls_jbxxDao.cqjyCount();
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> alljbxxCount() {
        return jls_jbxxDao.alljbxxCount();
    }

    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> jls_rscx(String starttime, String endtime) {
        return jls_jbxxDao.jls_rscx(starttime, endtime);
    }



    @UseDataSource("jls_ds")
    public List<Map<String, Object>> zcrsCount(String jsbh, String starttime,String endtime) {
        return  jls_jbxxDao.zcrsCount(jsbh,starttime,endtime);
    }
    
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jsxkh(String jsbh,String endtime) {
        return  jls_jbxxDao.jsxkh(endtime,jsbh);
    }
    @UseDataSource("jls_ds")
    public Map<String, Object> dpzsry(String jsbh) {
        return  jls_jbxxDao.dpzsry(jsbh);
    }
    
    @UseDataSource("jls_ds")
    public Map<String, Object> dpaqgl(String jsbh) {
        return  jls_jbxxDao.dpaqgl(jsbh);
    }
    //提讯会见
    @UseDataSource("jls_ds")
    public Map<String, Object> dptxhj(String jsbh) {
        return  jls_jbxxDao.dptxhj(jsbh);
    }
    
  //大屏关押期限
    @UseDataSource("jls_ds")
    public Map<String, Object> dpcqjy(String jsbh) {
        return  jls_jbxxDao.dpcqjy(jsbh);
    }
    
   //大屏诉讼情况分类
    @UseDataSource("jls_ds")
    public Map<String, Object> dprsxz(String jsbh) {
        return  jls_jbxxDao.dprsxz(jsbh);
    }
  //大屏案由情况分类
    @UseDataSource("jls_ds")
    public Map<String, Object> dpajlb(String jsbh) {
        return  jls_jbxxDao.dpajlb(jsbh);
    }
    
  //分所大屏各监区滑动卡牌
    @UseDataSource("jls_ds")
    public List<Map<String, Object>> dpjqhdkp(String jsbh) {
    	List<JqModel> jqlist = jls_JqDao.jqlist(jsbh);
    	List<Map<String, Object>> allmap = new ArrayList<Map<String,Object>>();
    	List<Map<String, Object>> list = jls_jbxxDao.dpjqhdkp(jsbh);
    	if(jqlist.size()>0) {
    		for (JqModel jqModel : jqlist) {
    			String jq = "";
				Map<String, Object> map111 = new LinkedHashMap<String, Object>();
				map111.put("name", jqModel.getJqmc());
				map111.put("val0", 0);
				map111.put("val01", 0);
				map111.put("val1", 0);
				map111.put("val11", 0);
				map111.put("val2", 0);
				map111.put("val21", 0);
				map111.put("val3", 0);
				map111.put("val31", 0);
				map111.put("val4", 0);
				map111.put("val41", 0);
				map111.put("jqh", jqModel.getJqh());
				for (Map<String, Object> map : list) {
					if(jqModel.getJqh().equals(map.get("name").toString())) {
						map111.put("val0", map.get("val0"));
						map111.put("val01", map.get("val01"));
						map111.put("val1", map.get("val1"));
						map111.put("val11", map.get("val11"));
						map111.put("val2", map.get("val2"));
						map111.put("val3", map.get("val3"));
						map111.put("val4", map.get("val4"));
						map111.put("val21", String.format("%.2f", (float) Integer.parseInt(map.get("val2").toString()) / (float) Integer.parseInt(map.get("val0").toString()) * 100));
						map111.put("val31", String.format("%.2f", (float) Integer.parseInt(map.get("val3").toString()) / (float) Integer.parseInt(map.get("val0").toString()) * 100));
						map111.put("val41", String.format("%.2f", (float) Integer.parseInt(map.get("val4").toString()) / (float) Integer.parseInt(map.get("val0").toString()) * 100));
					}
				}
				allmap.add(map111);
			}
    	}
        return  allmap;
    }
    //监区违规趋势图
    @UseDataSource("jls_ds")
    public Map<String, Object> dpzyryFxys(String rybh) {
        Map<String, Object> allmap = new HashMap<String, Object>();
            Map<String, Object> map = jls_jbxxDao.dpzyryFxys(rybh);
            if (!map.isEmpty()||map.size()>0){
                List list = new ArrayList();
                for (String key : map.keySet()) {
                    Map<String, Object> keymap = new HashMap<String, Object>();
                    keymap.put("name", key);
                    keymap.put("type", map.get(key));
                    list.add(keymap);
                }
                allmap.put("data", list);
                allmap.put("code", 200);
                allmap.put("msg", "查询成功");
            }else {
                allmap.put("data", "暂查无此人");
                allmap.put("code", 200);
                allmap.put("msg", "查询失败");
            }
        return allmap;
    }
    @UseDataSource("jls_ds")
    public Map<String, Object> select_ajfx(String rybh) {
        Map<String, Object> allmap = new HashMap<String, Object>();
        List list=new ArrayList();
        List<Map<String, Object>> map = jls_jbxxDao.select_ajfx(rybh);
        if (!map.isEmpty()||map.size()>0){
        int j=0;
        for(int i=0;i<map.size();i++){
            j++;
            Map<String, Object> jymap = new HashMap<String, Object>();
            if(map.get(i).get("sj").toString()!=null){
                jymap.put("xl", j);
                String sj = map.get(i).get("sj").toString();
                jymap.put("item1", sj);
            }else{
                jymap.put("xl", 1);
                jymap.put("item1", "暂未记录");
            }
            if(map.get(i).get("lx").toString()!=null){
                String lx = map.get(i).get("lx").toString();
                jymap.put("item2", lx);
            }else{
                jymap.put("item2", "暂未记录");
            }
            if(map.get(i).get("jg").toString()!=null){
                String jg = map.get(i).get("jg").toString();
                jymap.put("item3", jg);
            }else{
                jymap.put("item3", "暂未记录");
            }
            if(map.get(i).get("clr").toString()!=null){
                String clr = map.get(i).get("clr").toString();
                jymap.put("item4",clr );
            }else{
                jymap.put("item4", "暂未记录");
            }
            list.add(jymap);
        }
        }
        allmap.put("data", list);
        List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
        Map<String, Object> msp = new HashMap<String, Object>();
        allmap.put("code", 200);
        allmap.put("msg", "查询成功");
        msp.put("series", "xl");
        msp.put("name", "序列");
        Map<String, Object> msp1 = new HashMap<String, Object>();
        msp1.put("series", "item1");
        msp1.put("name", "违规事件");
        Map<String, Object> msp2 = new HashMap<String, Object>();
        msp2.put("series", "item2");
        msp2.put("name", "违规类型");
        Map<String, Object> msp3 = new HashMap<String, Object>();
        msp3.put("series", "item3");
        msp3.put("name", "处理结果");
        Map<String, Object> msp4 = new HashMap<String, Object>();
        msp4.put("series", "item4");
        msp4.put("name", "处理人");
        maps.add(msp);
        maps.add(msp1);
        maps.add(msp2);
        maps.add(msp3);
        maps.add(msp4);
        allmap.put("title", maps);
        return allmap;
    }
    @UseDataSource("jls_ds")
    public Map<String, Object> select_jy(String rybh) {
        Map<String, Object> allmap = new HashMap<String, Object>();
        List list=new ArrayList();
        List<Map<String, Object>> map = jls_jbxxDao.select_snjy(rybh);
        int j=0;
        if (!map.isEmpty() || map.size()>0){
            for(int i=0;i<map.size();i++){
                j++;
                Map<String, Object> jymap = new HashMap<String, Object>();
                if(map.get(i).get("zz").toString()!=null||map.get(i).get("zz").toString()!=""){
                    jymap.put("xl", j);
                    String zz = map.get(i).get("zz").toString();
                    jymap.put("item1", zz);
                }else{
                    jymap.put("xl", j);
                    jymap.put("item1", "暂无");
                }
                if(map.get(i).get("zd").toString()!=null||map.get(i).get("zd").toString()!=""){
                    String zd = map.get(i).get("zd").toString();
                    jymap.put("item2", zd);
                }else{
                    jymap.put("item2", "暂无");
                }
                if(map.get(i).get("cl").toString()!=null||map.get(i).get("cl").toString()!=""){
                    String cl = map.get(i).get("cl").toString();
                    jymap.put("item3", cl);
                }else{
                    jymap.put("item3", "暂无");
                }
                if(map.get(i).get("ys").toString()!=null||map.get(i).get("ys").toString()!=""){
                    String ys = map.get(i).get("ys").toString();
                    jymap.put("item4",ys );
                }else{
                    jymap.put("item4", "暂无");
                }
                list.add(jymap);
            }
        }
        List<Map<String, Object>> ma = jls_jbxxDao.select_swjy(rybh);
        if (!ma.isEmpty()||ma.size()>0){
            for(int i=0;i<ma.size();i++){
                j++;
                Map<String, Object> jymap = new HashMap<String, Object>();
                if(ma.get(i).get("zz").toString()!=null||ma.get(i).get("zz").toString()!=""){
                    jymap.put("xl", j);
                    String zz = ma.get(i).get("zz").toString();
                    jymap.put("item1", zz);
                }else{
                    jymap.put("xl", j);
                    jymap.put("item1", "暂无");
                }
                if(ma.get(i).get("zd").toString()!=null||ma.get(i).get("zd").toString()!=""){
                    String zd = ma.get(i).get("zd").toString();
                    jymap.put("item2", zd);
                }else{
                    jymap.put("item2", "暂无");
                }
                if(ma.get(i).get("cl").toString()!=null||ma.get(i).get("cl").toString()!=""){
                    String cl = ma.get(i).get("cl").toString();
                    jymap.put("item3", cl);
                }else{
                    jymap.put("item3", "暂无");
                }
                if(ma.get(i).get("ys").toString()!=null||ma.get(i).get("ys").toString()!=""){
                    String ys = ma.get(i).get("ys").toString();
                    jymap.put("item4",ys );
                }else{
                    jymap.put("item4", "暂无");
                }
                list.add(jymap);
            }
        }
        allmap.put("data", list);
        List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
        Map<String, Object> msp = new HashMap<String, Object>();
        allmap.put("code", 200);
        allmap.put("msg", "查询成功");
        msp.put("series", "xl");
        msp.put("name", "序列");
        Map<String, Object> msp1 = new HashMap<String, Object>();
        msp1.put("series", "item1");
        msp1.put("name", "病情");
        Map<String, Object> msp2 = new HashMap<String, Object>();
        msp2.put("series", "item2");
        msp2.put("name", "诊断情况");
        Map<String, Object> msp3 = new HashMap<String, Object>();
        msp3.put("series", "item3");
        msp3.put("name", "处理结果");
        Map<String, Object> msp4 = new HashMap<String, Object>();
        msp4.put("series", "item4");
        msp4.put("name", "就诊医生");
        maps.add(msp);
        maps.add(msp1);
        maps.add(msp2);
        maps.add(msp3);
        maps.add(msp4);
        allmap.put("title", maps);
        return allmap;
    }

    @UseDataSource("jls_ds")
    public List<Map<String,Object>> zyrygyl(){
        return jls_jbxxDao.zyrygyl();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> hjdfx(){
        return jls_jbxxDao.hjdfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> gjfx(){
        return jls_jbxxDao.gjfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> nlfx(){
        return jls_jbxxDao.nlfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> whcd(){
        return jls_jbxxDao.whcd();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> sffx(){
        return jls_jbxxDao.sffx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> jkqkfx(){
        return jls_jbxxDao.jkqkfx();
    }
    @UseDataSource("jls_ds")
    public List<Map<String,Object>> bhfx(){
        return jls_jbxxDao.bhfx();
    }
}
