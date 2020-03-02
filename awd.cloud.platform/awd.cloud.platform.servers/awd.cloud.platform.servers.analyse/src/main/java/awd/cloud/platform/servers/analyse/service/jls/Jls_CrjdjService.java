package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_CrjdjDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_SzjdjlDao;
import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_CrjdjService {
    @Autowired
    private Jls_CrjdjDao jls_CrjdjDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> crjdjCount(String jsbh, String starttime,String endtime) {
        return  jls_CrjdjDao.crjdjCount(jsbh,starttime,endtime);
    }


}
