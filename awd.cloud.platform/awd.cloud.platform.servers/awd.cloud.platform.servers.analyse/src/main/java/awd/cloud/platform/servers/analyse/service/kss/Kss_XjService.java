package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.XjDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_XjService {
    @Autowired
    private XjDao xjDao;
    
    /**
     * 械具总人数
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> xjZrs(String starttime, String endtime, String jsbh){
        return xjDao.xjZrs(starttime, endtime, jsbh);
    }
    
    /**
     * 械具使用呈批统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> xjSycp(String starttime, String endtime, String jsbh){
        return xjDao.xjSycp(starttime, endtime, jsbh);
    }

    /**
     * 械具使用登记统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> xjSydj(String starttime, String endtime, String jsbh){
        return xjDao.xjSydj(starttime, endtime, jsbh);
    }

    /**
     * 械具延长登记统计
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> xjYcdj(String starttime, String endtime, String jsbh){
        return xjDao.xjYcdj(starttime, endtime, jsbh);
    }

    @UseDataSource("kss_ds")
    public int getXjCount(String jsbh){
        return xjDao.getXjCount(jsbh);
    }
}
