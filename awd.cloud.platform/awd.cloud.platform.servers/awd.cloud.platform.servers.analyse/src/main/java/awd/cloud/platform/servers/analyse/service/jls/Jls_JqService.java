package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JqDao;
import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JqService {
    @Autowired
    private Jls_JqDao jls_JqDao;

    @UseDataSource("jls_ds")
    public List<JqModel> jqlist(String jsbh) {
        return  jls_JqDao.jqlist(jsbh);
    }


}
