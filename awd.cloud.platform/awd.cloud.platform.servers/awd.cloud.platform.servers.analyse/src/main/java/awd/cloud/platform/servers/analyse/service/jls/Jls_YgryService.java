package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_YgryDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_YgryService {
    @Autowired
    private Jls_YgryDao jls_YgryDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> ygryCount(String jsbh, String starttime,String endtime) {
        return  jls_YgryDao.ygryCount(jsbh,starttime,endtime);
    }


}
