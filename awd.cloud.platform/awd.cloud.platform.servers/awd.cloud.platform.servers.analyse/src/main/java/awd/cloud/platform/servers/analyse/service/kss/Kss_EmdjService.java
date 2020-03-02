package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.EmdjDao;
import awd.cloud.platform.servers.analyse.vo.AnalysisResultVO;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by wuyu on 2019/7/4 14:48
 **/
@Service
public class Kss_EmdjService {
    @Autowired
    private EmdjDao emdjDao;
    @UseDataSource("kss_ds")

    public List<Map<String, Object>> emdj_rqcxE(String starttime, String endtime) {
        return emdjDao.rqcxE(starttime, endtime);
    }

    //耳目登记上位机版本
    @UseDataSource("kss_ds")
    public List<AnalysisResultVO> swj_emdj_rqcxE(String starttime, String endtime) {
        return emdjDao.swj_rqcxE(starttime, endtime);
    }
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> queryJq(String jsbh){
        return  emdjDao.queryJq(jsbh);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> emYbjjs(String jsbh){
        return  emdjDao.emYbjjs(jsbh);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> emWbjjs(String jsbh){
        return  emdjDao.emWbjjs(jsbh);
    }

    @UseDataSource("kss_ds")
    public List<Map<String,Object>> emZs(String jqh,String jsbh){
              return  emdjDao.emZs(jqh,jsbh);
    }


    @UseDataSource("kss_ds")
    public List<Map<String,Object>> emTj(String jqh,String jsbh){
        return  emdjDao.emTj(jqh, jsbh);
    }

}
