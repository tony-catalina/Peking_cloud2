package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.TafDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_TafService {

    @Autowired
    private TafDao tafDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Taf_rqFx(String starttime, String endtime){
        return tafDao.TafInfo(starttime, endtime);
    }

     //同案犯查询=上位机版本
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swj_tafcx(String starttime, String endtime){
        return tafDao.swj_tafcx(starttime, endtime);
    }
}
