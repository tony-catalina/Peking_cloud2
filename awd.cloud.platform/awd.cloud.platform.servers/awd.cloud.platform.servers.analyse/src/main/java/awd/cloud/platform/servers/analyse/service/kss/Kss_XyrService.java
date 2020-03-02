package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.XyrDao;
import awd.cloud.platform.servers.analyse.dao.kss.XzpaDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_XyrService {
    @Autowired
    private XyrDao xyrDao;

    /**
     * 未收
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> wsrsCount() {
        return xyrDao.wsrsCount();
    }
}
