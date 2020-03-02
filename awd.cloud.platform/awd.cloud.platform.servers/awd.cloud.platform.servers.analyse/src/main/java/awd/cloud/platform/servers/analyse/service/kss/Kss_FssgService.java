package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.FssgDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 15:09
 **/
@Service
public class Kss_FssgService {
    @Autowired
    private FssgDao fssgDao;
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> fssg_rqcxF(String starttime, String endtime) {

        return fssgDao.rqcxF(starttime, endtime);
    }
}

