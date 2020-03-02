package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_SnjyDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_XsdjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_XsdjService {
    @Autowired
    private Jls_XsdjDao jls_XsdjDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> xsdjCount(String jsbh, String starttime,String endtime) {
        return  jls_XsdjDao.xsdjCount(jsbh,starttime,endtime);
    }


}
