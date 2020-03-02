package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_SnjyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_SnjyService {
    @Autowired
    private Jls_SnjyDao jls_SnjyDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> snjyCount(String jsbh, String starttime,String endtime) {
        return  jls_SnjyDao.snjyCount(jsbh,starttime,endtime);
    }


}
