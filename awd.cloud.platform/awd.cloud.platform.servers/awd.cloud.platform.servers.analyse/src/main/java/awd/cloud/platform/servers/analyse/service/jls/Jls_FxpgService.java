package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_FxpgDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Jls_FxpgService {
    @Autowired
    private Jls_FxpgDao jls_FxpgDao;

    @UseDataSource("jls_ds")
    public Map<String, Object> fxpgYwdt(String starttime, String endtime, String jsbh) {
        Map<String, Object> fxpg = jls_FxpgDao.fxpg(starttime ,endtime ,jsbh);
        return fxpg;
    }

}
