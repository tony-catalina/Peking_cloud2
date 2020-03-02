package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_QjcsDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Jls_QjcsService {
    @Autowired
    private Jls_QjcsDao qjcsDao;

    @UseDataSource("jls_ds")
    public Map<String, Object> qjcsYwdt(String starttime, String endtime, String jsbh) {
        Map<String, Object> qjcsYwdt = qjcsDao.qjcsYwdt(starttime ,endtime ,jsbh);
        return qjcsYwdt;
    }

}
