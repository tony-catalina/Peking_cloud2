package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.CSJYPMDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 11:02
 * Description：<描述>
 */
@Service
public class Kss_CSJYPMService {

    @Autowired
    private CSJYPMDao csjypmDao;


    //1 kss出所就医排名
    @UseDataSource("kss_ds")
    public Map<String, Object> find_CSJYPM(String jsbh){

        Map<String, Object> map = csjypmDao.select_CSJYPM(jsbh);

        return map;
    }


    //2 jls出所就医排名
    @UseDataSource("jls_ds")
    public Map<String, Object> find_jls_CSJYPM(String jsbh){

        Map<String, Object> map = csjypmDao.select_jls_CSJYPM(jsbh);

        return map;
    }

}
