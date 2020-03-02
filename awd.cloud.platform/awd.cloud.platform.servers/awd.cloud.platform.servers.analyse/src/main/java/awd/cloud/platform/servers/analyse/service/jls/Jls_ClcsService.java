package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_ClcsDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Jls_ClcsService {
    @Autowired
    private Jls_ClcsDao clcsDao;

    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> Clcs_rqFx(String starttime, String endtime) {

        return clcsDao.ClcsInfo(starttime,endtime);
    }

    @UseDataSource("jls_ds")
    public Map<String, Object> qmcsYwdt(String starttime, String endtime, String jsbh) {
        Map<String, Object> qmcsYwdt = new HashMap<>();
        Map<String, Object> qmcs = clcsDao.qmcsYwdt(starttime ,endtime ,jsbh);
        Map<String, Object> ncs = clcsDao.ncsrsYwdt(jsbh);
        qmcsYwdt = MapUtils.merge(qmcs, ncs);
        return qmcsYwdt;
    }

    @UseDataSource("jls_ds")
    public Map<String, Object> tqjcYwdt(String starttime, String endtime, String jsbh) {
        Map<String, Object> tqjcYwdt = clcsDao.tqjcYwdt(starttime ,endtime ,jsbh);
        return tqjcYwdt;
    }
}
