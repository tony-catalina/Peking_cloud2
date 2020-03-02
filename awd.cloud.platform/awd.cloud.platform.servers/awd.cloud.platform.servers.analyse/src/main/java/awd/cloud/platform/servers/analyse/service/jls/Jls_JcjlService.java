package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JcjlDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JcjlService {
    @Autowired
    private Jls_JcjlDao jls_jcjlDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jcjl_rqFx(String starttime, String endtime, String badw) {
        return  jls_jcjlDao.jcjlFx(starttime ,endtime ,badw);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jcjl_ywFx(String cpgj, String cprq, String ly, String gjyj) {
        return jls_jcjlDao.jcjlyw(cpgj ,cprq ,ly, gjyj);
    }

}
