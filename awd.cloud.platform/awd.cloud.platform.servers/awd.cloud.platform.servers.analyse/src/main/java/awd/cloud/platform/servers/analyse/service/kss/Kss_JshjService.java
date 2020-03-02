package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JshjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class Kss_JshjService {

    @Autowired
    private JshjDao jshjDao;

    @UseDataSource("kss_ds")
    public List<Map<String, Object>> Jshj_rqFx(String hjsj, String jssj) {

        return jshjDao.jshjInfo(hjsj,jssj);
    }

    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> jshjFx(String hjsj, String jssj) {

        return jshjDao.jshjFx(hjsj,jssj);
    }

    @UseDataSource("kss_ds")
    public int getJshjCount(String jsbh){

        return jshjDao.getJshjCount(jsbh);
    }

}
