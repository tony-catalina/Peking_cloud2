package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_WpjsDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_WpjsService {
    @Autowired
    private Jls_WpjsDao jls_WpjsDao;

    @UseDataSource("jls_ds")
    public Map<String, Object> wpjsCount(String jsbh, String starttime,String endtime) {
    	int swzs = jls_WpjsDao.swzsCount(jsbh, starttime, endtime);
    	System.err.println(swzs);
    	int jszs = jls_WpjsDao.jszsCount(jsbh, starttime, endtime);
    	System.err.println(jszs);
    	int jrsw = jls_WpjsDao.jrswCount(jsbh);
    	System.err.println(jrsw);
    	int jrjs = jls_WpjsDao.jrjsCount(jsbh);
    	System.err.println(jrjs);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("swzs", swzs);
        map.put("jszs", jszs);
        map.put("jrsw", jrsw);
        map.put("jrjs", jrjs);
        return  map;
    }


}
