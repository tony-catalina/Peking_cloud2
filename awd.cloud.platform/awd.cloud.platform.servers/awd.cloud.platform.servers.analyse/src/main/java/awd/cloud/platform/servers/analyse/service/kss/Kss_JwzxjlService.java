package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JwzxjlDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_JwzxjlService {
    @Autowired
    private JwzxjlDao jwzxjlDao;
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> jwzxjl_rqFx(String starttime, String endtime) {

        return jwzxjlDao.jwzxjlInfo(starttime,endtime);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> jwzxjlYwdt(String starttime, String endtime,String jsbh){
        System.out.println("startTime="+starttime);
        System.out.println("endTime="+endtime);
        return  jwzxjlDao.jwzxjlYwdt(starttime, endtime, jsbh);
    }
}
