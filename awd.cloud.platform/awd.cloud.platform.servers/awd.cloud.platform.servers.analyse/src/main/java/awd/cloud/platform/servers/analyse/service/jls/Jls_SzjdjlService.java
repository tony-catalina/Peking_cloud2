package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JbxxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_SzjdjlDao;
import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_SzjdjlService {
    @Autowired
    private Jls_SzjdjlDao jls_SzjdjlDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> szjdjlCount(String jsbh, String starttime,String endtime) {
        return  jls_SzjdjlDao.szjdjlCount(jsbh,starttime,endtime);
    }


}
