package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.CfglDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_CfglService {
    @Autowired
    private CfglDao cfglDao;

    /**
     * 惩罚管理业务动态
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> cfglYwdt(String starttime, String endtime,String jsbh,String jsh) {
        return cfglDao.cfglYwdt(starttime, endtime, jsbh,jsh);
    }
}
