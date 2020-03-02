package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_QqdhDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_QqdhService {
    @Autowired
    private Jls_QqdhDao jls_qqdhDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> qqdh_dtFx(String starttime, String endtime, String jsbh) {
        int thCount=jls_qqdhDao.qqdhDt(starttime ,endtime ,jsbh);
        int dayCount=jls_qqdhDao.qqdhday(starttime ,endtime ,jsbh);
        List list=new ArrayList();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("thCount",thCount);
        map.put("dayCount",dayCount);
        list.add(map);
        return list;
    }
}
