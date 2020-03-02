package awd.cloud.platform.servers.analyse.service.jds;

import awd.cloud.platform.servers.analyse.dao.jds.Jds_JbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jds_JbxxService {

    @Autowired
    private Jds_JbxxDao jbxxDao;

    //出所人数分析查询=上位机
    @UseDataSource("jds_ds")
    public List<Map<String, Object>> swj_csrsFX(String starttime, String  endtime){
        List<Map<String, Object>> maps = jbxxDao.swj_csrsFX(starttime, endtime);
        if(!StringUtils.isNullOrEmpty(maps)) {
            for (Map<String, Object> key : maps) {
                Object dz = key.get("dz");
                if (!StringUtils.isNullOrEmpty(dz.toString())) {
                    key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
                } else {
                    key.put("dzString", "");
                }

            }
        }
        return maps;
    }

    //上位机戒毒所在戒人数分析查询
    @UseDataSource("jds_ds")
    public List<Map<String, Object>> swj_zjrsFX(String starttime, String endtime) {

        List<Map<String,Object>> result = jbxxDao.swj_zjrsFX(starttime,endtime);
        for (Map<String,Object> map : result ){
            String dz = (String) map.get("dz");
            map.put("dzString", CacheUtils.get().getDictionarys("XZQH",dz));
        }
        return result;
    }

    //上位机统计分析戒毒所--人员年龄分析
    @UseDataSource("jds_ds")
    public List<Map<String,Object>> getRynlFx(String starttime,String endtime){
        List<Map<String,Object>> list =jbxxDao.swjJdsRynlFx(starttime, endtime);
        if (!StringUtils.isNullOrEmpty(list)){
            for (Map<String,Object> map:list){
                String dz = (String) map.get("dz");
                map.put("dzString",CacheUtils.get().getDictionarys("XZQH",dz));
            }
        }
        return list;
    }
    //人员吸毒时间分析查询=上位机
    @UseDataSource("jds_ds")
    public List<Map<String, Object>> swj_xdsjFX(String starttime, String  endtime){
        List<Map<String, Object>> maps = jbxxDao.swj_xdsjFX(starttime, endtime);
        if(!StringUtils.isNullOrEmpty(maps)) {
            for (Map<String, Object> key : maps) {
                Object dz = key.get("dz");
                if (!StringUtils.isNullOrEmpty(dz.toString())) {
                    key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
                } else {
                    key.put("dzString", "");
                }

            }
        }
        return maps;
    }
}
