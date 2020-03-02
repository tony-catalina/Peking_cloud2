package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.XzpaDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_XzpaService {
    @Autowired
    private XzpaDao xzpaDao;

    /**
     * 协助破案业务动态
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> xzpaYwdt(String starttime, String endtime,String jsbh) {
        return xzpaDao.xzpaYwdt(starttime, endtime, jsbh);
    }
}
