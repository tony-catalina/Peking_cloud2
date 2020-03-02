package awd.cloud.platform.servers.analyse.service.kss;


import awd.cloud.platform.servers.analyse.dao.kss.JjxDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_JjxService {
    @Autowired
    private JjxDao jjxDao;

    /**
     * 查询加减刑数量
     * @param starttime
     * @param endtime
     * @return
     */
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> queryJjxList(String starttime, String endtime){
        return jjxDao.queryJjxNum(starttime, endtime);
    }
}

