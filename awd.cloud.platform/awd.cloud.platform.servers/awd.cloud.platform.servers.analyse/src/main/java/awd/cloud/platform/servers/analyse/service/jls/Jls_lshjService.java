package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_lshjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-07 13:36
 * Description：<描述>
 */
@Service
public class Jls_lshjService {

    @Autowired
    private Jls_lshjDao jls_lshjDao;

    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> Lshj_rqFx(String hjsj, String jssj) {

        return jls_lshjDao.lshjInfo(hjsj, jssj);
    }

}
