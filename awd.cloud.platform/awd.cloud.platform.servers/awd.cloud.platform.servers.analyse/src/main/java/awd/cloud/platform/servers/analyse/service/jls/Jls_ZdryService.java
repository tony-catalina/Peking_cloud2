package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ZdryDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_ZdryService {
    @Autowired
    private Jls_ZdryDao jls_zdryDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> zdry_dtFx(String starttime, String endtime, String jsbh) {
        return jls_zdryDao.zdryDt(starttime ,endtime ,jsbh);
    }

}
