package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.PjdjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_PjdjService {
    @Autowired
    private PjdjDao pjdjDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> pjdj_rqFx(String starttime, String endtime){
        return pjdjDao.pjdjInfo(starttime, endtime);
    }

    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> pjdjFx(String starttime, String endtime){
        return pjdjDao.pjdjFx(starttime, endtime);
    }
    
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> pjdjYwdt(String starttime, String endtime,String jsbh){
        return  pjdjDao.pjdjYwdt(starttime, endtime, jsbh);
    }
}
