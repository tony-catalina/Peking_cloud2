package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.KssDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_ClgyfxService {
    @Autowired
    private KssDao kssDao;


    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_Clgyfx(String starttime,String endtime){
        List<Map<String, Object>> maps = kssDao.swj_Clgyfx(starttime, endtime);
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
    public List<Map<String, Object>> queryClgyfxList(String starttime,String endtime){
        return kssDao.queryClgyfxNum(starttime,endtime);
    }
}
