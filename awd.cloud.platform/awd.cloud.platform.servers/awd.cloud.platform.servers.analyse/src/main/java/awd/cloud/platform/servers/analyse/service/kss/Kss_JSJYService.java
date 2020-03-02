package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JSJYDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 16:18
 * Description：<描述>
 */
//全市监所羁押分布
@Service

public class Kss_JSJYService {

    @Autowired
    private JSJYDao jsjyDao;

    //全市监所羁押分布

    @UseDataSource("kss_ds")
     public Map<String, Object> find_JSJY(String jsbh){


        //kss
        Map<String, Object> map = jsjyDao.jSJY(jsbh);
        return map;

    }



    @UseDataSource("jls_ds")
    public Map<String, Object> find__jls_JSJY(String jsbh) {
        //jls
        Map<String, Object> jls_jSJY = jsjyDao.jls_jSJY(jsbh);

        return jls_jSJY;
    }
}
