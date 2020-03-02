package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_GbjyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_GbjyService {
    @Autowired
    private Jls_GbjyDao jls_gbjyDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> Gbjy_dtFx(String starttime, String endtime, String badw) {
        return jls_gbjyDao.gbjyDt(starttime ,endtime ,badw);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> Gbjy_tzFx(String state, String xm, String starttime, String endtime, String starttime1, String endtime1) {
        return jls_gbjyDao.gbjyZt(state ,xm ,starttime, endtime, starttime1, endtime1);
    }
}
