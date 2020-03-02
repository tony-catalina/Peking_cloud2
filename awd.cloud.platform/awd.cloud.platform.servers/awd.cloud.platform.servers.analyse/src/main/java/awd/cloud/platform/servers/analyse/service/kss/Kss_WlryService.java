package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.WlryDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_WlryService {
    @Autowired
    private WlryDao wlryDao;

    /**
     * 外来人员,来所事由统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>>  wlryLssy(String starttime, String endtime,String jsbh){
                     return wlryDao.wlryLssy(starttime, endtime, jsbh);
    }

    /**
     * 外来人员,来所人数统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>>  wlryLfrs(String starttime, String endtime,String jsbh){
        return wlryDao.wlryLfrs(starttime, endtime, jsbh);
    }

    /**
     * 外来人员,来所车辆统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>>  wlrySxcls(String starttime, String endtime,String jsbh){
        return wlryDao.wlrySxcls(starttime, endtime, jsbh);
    }
}
