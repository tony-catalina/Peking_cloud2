package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.Tjdao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_TjService {

    @Autowired
    private Tjdao tjdao;

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> tjYwdt(String startTime, String endTime, String jsbh){
        System.out.println("startTime="+startTime);
        System.out.println("endTime="+endTime);
        return  tjdao.tjYwdt(startTime, endTime, jsbh);
    }
}
