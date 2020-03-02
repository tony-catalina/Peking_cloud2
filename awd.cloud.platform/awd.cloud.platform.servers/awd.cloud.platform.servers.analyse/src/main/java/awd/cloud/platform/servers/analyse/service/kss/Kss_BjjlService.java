package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.BjjlDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Kss_BjjlService {

    @Autowired
    private BjjlDao bjjlDao;

    /**
     * 流水牌>重点人员>帮教人员
     * @param jsbh
     * @return
     */
    @UseDataSource("kss_ds")
    public int getBjjlCount(String jsbh){
        return bjjlDao.getBjjlCount(jsbh);
    }
}
