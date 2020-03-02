package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.ClcsDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_ClcsService {
    @Autowired
    private ClcsDao clcsDao;
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Clcs_rqFx(String starttime, String endtime) {

        return clcsDao.ClcsInfo(starttime,endtime);
    }

    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> Swj_clcs_rqFx(String starttime, String endtime) {

        return clcsDao.swj_clcsInfo(starttime,endtime);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> clcsYwdt(String startTime, String endTime,String jsbh){
        System.out.println("____________startTime="+startTime);
        System.out.println("------------------endTime="+endTime);
             return  clcsDao.clcsYwdt(startTime, endTime, jsbh);
    }
}
