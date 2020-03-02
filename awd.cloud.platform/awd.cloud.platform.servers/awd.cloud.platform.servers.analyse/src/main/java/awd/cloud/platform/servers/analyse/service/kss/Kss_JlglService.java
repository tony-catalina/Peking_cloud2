package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JlglDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Kss_JlglService {
    @Autowired
    private JlglDao jlglDao;

    /**
     * 奖励管理业务动态
     */
    @UseDataSource("kss_ds")
    public List<Map<String,Object>> jlglYwdt(String starttime, String endtime, String jsbh,String jsh){
        /*System.out.println("-----=jdhsdsiikdk");
        System.out.println("-----=jdhsdsiikdk"+starttime+"--"+endtime+"--"+jsbh+"--"+jsh);
        System.out.println("++++++++000000"+jlglDao.jlglYwdt(starttime, endtime, jsbh,jsh));*/
        return jlglDao.jlglYwdt(starttime, endtime, jsbh,jsh);
    }
}
