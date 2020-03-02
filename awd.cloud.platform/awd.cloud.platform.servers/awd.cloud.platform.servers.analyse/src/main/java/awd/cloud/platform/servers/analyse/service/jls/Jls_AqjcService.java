package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_AqjcDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_AqjcService {
    @Autowired
    private Jls_AqjcDao jls_AqjcDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> aqjcCount(String jsbh, String starttime,String endtime) {
        return  jls_AqjcDao.aqjcCount(jsbh,starttime,endtime);
    }


}
