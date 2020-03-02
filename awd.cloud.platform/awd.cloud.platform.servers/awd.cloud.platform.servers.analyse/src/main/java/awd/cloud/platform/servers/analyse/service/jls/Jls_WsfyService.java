package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_WsfyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_WsfyService {
    @Autowired
    private Jls_WsfyDao jls_WsfyDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> wsfyCount(String jsbh, String starttime,String endtime) {
        return  jls_WsfyDao.wsfyCount(jsbh,starttime,endtime);
    }


}
