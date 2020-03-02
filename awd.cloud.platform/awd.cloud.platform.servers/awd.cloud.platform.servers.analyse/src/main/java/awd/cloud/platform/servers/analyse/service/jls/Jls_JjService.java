package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JjDao;
import awd.cloud.platform.servers.analyse.model.jls.JbxxModel;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JjService {
    @Autowired
    private Jls_JjDao jls_JjDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jjCount(String jsbh, String starttime,String endtime) {
        return  jls_JjDao.jjCount(jsbh,starttime,endtime);
    }


}
