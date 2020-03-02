package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.YqDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 17:03
 **/
@Service
public class Kss_YqService {
    @Autowired
    private YqDao yqDao;
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> yq_rqcxY(String starttime, String endtime) {

        return yqDao.rqcxY(starttime, endtime);
    }

    @UseDataSource("kss_ds")
     public List<Map<String,Object>> yqYwdt(String starttime, String endtime,String jsbh){
        return yqDao.yqYwdt(starttime, endtime, jsbh);
    }
}
