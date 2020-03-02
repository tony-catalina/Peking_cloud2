package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_TsdjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisJlsResultVO;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Jls_TsdjService {

    @Autowired
    private Jls_TsdjDao tsdjDao;
    /**
     * 查询提审登记数量
     * @param kssj
     * @param jssj
     * @return
     */
    @UseDataSource("jls_ds")
    public List<AnalysisJlsResultVO> queryTsdjList(String kssj, String jssj){
        return tsdjDao.queryTsdjNum(kssj, jssj);
    }
}
