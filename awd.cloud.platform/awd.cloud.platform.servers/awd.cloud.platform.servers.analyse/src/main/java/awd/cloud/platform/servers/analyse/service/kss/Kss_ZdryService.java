package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.ZdryDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_ZdryService {
    @Autowired
    private ZdryDao zdryDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_zdry(String starttime, String endtime){

        List<Map<String, Object>> maps = zdryDao.swj_zdry(starttime, endtime);
        if(!StringUtils.isNullOrEmpty(maps)) {
            for (Map<String, Object> key : maps) {
                Object dz = key.get("dz");
                if (!StringUtils.isNullOrEmpty(dz)) {
                    key.put("dzString", CacheUtils.get().getDictionarys("XZQH", dz.toString()));
                } else {
                    key.put("dzString", "");
                }

            }
        }
        return maps;
    }

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Zdry_rqFx(String starttime, String endtime){
        
        return zdryDao.ZdryInfo(starttime, endtime);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> zdryYwdt(String starttime, String endtime,String jsbh,String jsh){
        return zdryDao.zdryYwdt(starttime, endtime, jsbh,jsh);
    }

    @UseDataSource("kss_ds")
    public int getZdryCount(String jsbh){
        return zdryDao.getZdryCount(jsbh);
    }
}
