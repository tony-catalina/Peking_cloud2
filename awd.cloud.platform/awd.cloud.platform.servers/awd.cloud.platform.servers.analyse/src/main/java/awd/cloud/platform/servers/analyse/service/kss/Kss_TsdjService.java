package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.TsdjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_TsdjService {
    @Autowired
    private TsdjDao tsdjDao;

    /**
     * 查询提审登记数量
     * @param kssj
     * @param jssj
     * @return
     */
    @UseDataSource("kss_ds")
    public List<Map<String, Object>> queryTsdjList(String kssj, String jssj){
        return tsdjDao.queryTsdjNum(kssj, jssj);
    }

    //提审登记=上位机版本
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> swj_tsdjnum(String kssj, String jssj){
        return tsdjDao.swj_tsdjnum(kssj, jssj);
    }

    @UseDataSource("kss_ds")
    public int getTsdjCount(String jsbh){
        return tsdjDao.getTsdjCount(jsbh);
    }

    @UseDataSource("kss_ds")
    public int getJsTsdjCount(String jsbh, String jsh) {
        return tsdjDao.getJsTsdjCount(jsbh,jsh);
    }
}
