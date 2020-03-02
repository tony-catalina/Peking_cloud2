package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.SzjdjlDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_SzjdjlService {
    @Autowired
    private SzjdjlDao szjdjlDao;
    /**
     * 领导接访，来访次数统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> ldjfLfcs(String starttime, String endtime, String jsbh){
        return szjdjlDao.ldjfLfcs(starttime, endtime, jsbh);
    }

    /**
     * 领导接访，处理情况统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> ldjfClqk(String starttime, String endtime, String jsbh){
        return szjdjlDao.ldjfClqk(starttime, endtime, jsbh);
    }
    /**
     * 领导接访，总人数统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> ldjfZrs(String starttime, String endtime, String jsbh){
        return szjdjlDao.ldjfZrs(starttime, endtime, jsbh);
    }
}
