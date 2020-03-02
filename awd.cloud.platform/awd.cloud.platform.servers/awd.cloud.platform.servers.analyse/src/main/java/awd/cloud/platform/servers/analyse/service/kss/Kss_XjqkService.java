package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.XjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_XjqkService {
    @Autowired
    private XjDao xjDao;

    /**
     * 查询械具数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> queryXjList(String starttime, String endtime){
        return xjDao.queryXjqkNum(starttime, endtime);
    }
}
