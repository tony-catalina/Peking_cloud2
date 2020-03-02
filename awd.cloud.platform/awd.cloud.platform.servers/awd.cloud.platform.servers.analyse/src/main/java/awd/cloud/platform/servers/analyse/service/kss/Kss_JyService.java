package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Kss_JyService {

    @Autowired
    private JyDao jyDao;

    /**
     * 流水牌>重点人员>所外就医
     * @param jsbh
     * @param field
     * @param value
     * @return
     */
    @UseDataSource("kss_ds")
    public int getJyCountByField(String jsbh,String field,String value) {

        return jyDao.getJyCountByField(jsbh,field,value);
    }
    @UseDataSource("kss_ds")
    public int getJyCountByCsjylx( String jsbh, String jsh,String field, String value) {

        return jyDao.getJyCountByCsjylx(jsbh,jsh,field,value);
    }

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> swjyCount() {
        return jyDao.swjyCount();
    }
    
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> snjyCount() {
        return jyDao.snjyCount();
    }
}
