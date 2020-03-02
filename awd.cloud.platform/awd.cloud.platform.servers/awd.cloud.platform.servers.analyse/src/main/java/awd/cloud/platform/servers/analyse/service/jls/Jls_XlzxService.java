package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_XlzxDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_XlzxService {
    @Autowired
    private Jls_XlzxDao jls_XlzxDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> xlzxCount(String jsbh, String starttime,String endtime) {
        return  jls_XlzxDao.xlzxCount(jsbh,starttime,endtime);
    }


}
