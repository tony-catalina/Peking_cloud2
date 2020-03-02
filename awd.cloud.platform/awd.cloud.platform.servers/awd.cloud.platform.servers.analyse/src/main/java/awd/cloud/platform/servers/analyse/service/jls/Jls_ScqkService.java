package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ScqkDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_ScqkService {
    @Autowired
    private Jls_ScqkDao jls_ScqkDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> scqkCount(String jsbh, String starttime,String endtime) {
        return  jls_ScqkDao.scqkCount(jsbh,starttime,endtime);
    }


}
