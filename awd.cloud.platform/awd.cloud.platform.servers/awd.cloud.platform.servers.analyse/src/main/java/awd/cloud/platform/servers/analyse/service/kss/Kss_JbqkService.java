package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JbDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 14:13
 **/
@Service
public class Kss_JbqkService {
    @Autowired
    private JbDao jbDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> jb_rqcxJ(String kssj, String jssj) {

        return jbDao.rqcxJ(kssj, jssj);
    }

    //上位机_禁闭情况
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> swj_jb_rqcxJ(String kssj, String jssj) {

        return jbDao.swj_rqcxJ(kssj, jssj);
    }

    @UseDataSource("kss_ds")
    public int getJbCount (String jsbh){

        return jbDao.getJbCount(jsbh);
    }
}