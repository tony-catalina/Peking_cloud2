package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_jshjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2020-02-07 15:40
 * Description：<描述>
 */
@Service
public class Jls_jshjService {

    @Autowired
    private Jls_jshjDao jls_jshjDao;

    @UseDataSource("jls_ds")
    public List<Map<String, Object>> Jshj_rqFx(String hjsj, String jssj) {

        return jls_jshjDao.jshjInfo(hjsj,jssj);
    }


    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> swj_jshj(String hjsj, String jssj) {

        return jls_jshjDao.swj_jshj(hjsj,jssj);
    }

}
