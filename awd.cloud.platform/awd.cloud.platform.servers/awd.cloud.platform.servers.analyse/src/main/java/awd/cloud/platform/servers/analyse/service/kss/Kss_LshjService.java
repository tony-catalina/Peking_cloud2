package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.LshjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_LshjService {
    @Autowired
    private LshjDao lshjDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Lshj_rqFx(String hjsj, String jssj) {

        return lshjDao.lshjInfo(hjsj, jssj);

    }

    @UseDataSource("kss_ds")
    public int getLshjCount(String jsbh){
        return lshjDao.getLshjCount(jsbh);
    }

    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> Swj_Lshj_rqFx(String hjsj, String jssj) {
        return lshjDao.swj_lshjInfo(hjsj, jssj);
    }
}