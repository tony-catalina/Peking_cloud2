package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_SqdtfxhyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_SqdtfxhyService {
    @Autowired
    private Jls_SqdtfxhyDao jls_SqdtfxhyDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> sqdtfxhyCount(String jsbh, String starttime,String endtime) {
        return  jls_SqdtfxhyDao.sqdtfxhyCount(jsbh,starttime,endtime);
    }


}
