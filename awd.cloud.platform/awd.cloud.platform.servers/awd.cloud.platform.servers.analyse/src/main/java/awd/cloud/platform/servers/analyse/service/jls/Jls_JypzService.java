package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_JypzDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Jls_JypzService {

    @Autowired
    private Jls_JypzDao jypzDao;

    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> queryJypzList(String starttime, String endtime){
        return jypzDao.queryJypzNum(starttime, endtime);
    }
}
