package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_DwkfDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_GbjyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_DwkfService {
    @Autowired
    private Jls_DwkfDao jls_dwkfDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> Dwkf_dtFx(String starttime, String endtime, String badw) {
        return jls_dwkfDao.dwkfDt(starttime ,endtime ,badw);
    }
}
