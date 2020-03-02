package awd.cloud.platform.servers.analyse.service.jls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_SwDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;

@Service
public class Jls_SwService {
    @Autowired
    private Jls_SwDao jls_swDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> sw_dtFx(String starttime, String endtime, String jsbh) {
        return jls_swDao.swDt(starttime ,endtime ,jsbh);
    }
}
