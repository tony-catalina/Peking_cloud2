package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JtjyDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

@Service
public class Jls_JtjyService {
    @Autowired
    private Jls_JtjyDao jls_jtjyDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jtjy_dtFx(String starttime, String endtime, String badw) {
        return jls_jtjyDao.jtjyDt(starttime ,endtime ,badw);
    }

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> jtjy_tzFx(String starttime, String endtime, String jyr, String zw, String pzr) {
        return jls_jtjyDao.jtjyTz(starttime ,endtime ,jyr, zw, pzr);
    }
}
