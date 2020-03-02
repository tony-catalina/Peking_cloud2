package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_SwfzDao;
import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_SwfzService {
    @Autowired
    private Jls_SwfzDao jls_SwfzDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> swfzCount(String jsbh, String starttime,String endtime) {
        return  jls_SwfzDao.swfzCount(jsbh,starttime,endtime);
    }


}
