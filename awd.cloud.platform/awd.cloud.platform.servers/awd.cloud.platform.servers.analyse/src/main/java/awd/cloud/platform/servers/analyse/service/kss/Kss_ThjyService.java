package awd.cloud.platform.servers.analyse.service.kss;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.cloud.platform.servers.analyse.dao.kss.ThjyDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;

/**
 * Create by wuyu on 2019/7/4 11:02
 **/
@Service
public class Kss_ThjyService {
    @Autowired
    private ThjyDao thjyDao;
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> thjy_rqcx(String kssj,String jssj) {

        return thjyDao.rqcx(kssj, jssj);
    }
    @UseDataSource("kss_ds")
    public int getBzJsThrs(String jsbh, String jsh) {
        return thjyDao.getBzJsThrs(jsbh,jsh);
    }
}
