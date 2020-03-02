package awd.cloud.platform.servers.analyse.service.kss;

import awd.AnalyseApplication;
import awd.cloud.platform.servers.analyse.dao.kss.ShgxDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_ShgxService {
    @Autowired
    private ShgxDao shgxDao;

    /**
     * 查询社会关系数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> queryShgxList(String starttime, String endtime){
        return shgxDao.queryShgxNum(starttime, endtime);
    }
}