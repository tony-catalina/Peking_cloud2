package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_JkqkDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_JqDao;
import awd.cloud.platform.servers.analyse.model.jls.JqModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_JkqkService {
    @Autowired
    private Jls_JkqkDao jls_jkqkDao;
    //拘留所身体状况
    @UseDataSource("jls_ds")
    public Map<String, Object> jkqkCount(String jsbh,String rybh) {
        return  jls_jkqkDao.jkqkCount(jsbh,rybh);
    }
}
