package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ZbhdjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_ZbhdjService {
    @Autowired
    private Jls_ZbhdjDao jls_ZbhdjDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> zbhdjCount(String jsbh, String starttime,String endtime) {
        return  jls_ZbhdjDao.zbhdjCount(jsbh,starttime,endtime);
    }


}
