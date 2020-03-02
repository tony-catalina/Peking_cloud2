package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_YsxzDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_YsxzService {
    @Autowired
    private Jls_YsxzDao jls_YsxzDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> ysxzCount(String jsbh, String starttime,String endtime) {
        return  jls_YsxzDao.ysxzCount(jsbh,starttime,endtime);
    }


}
