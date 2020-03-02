package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.DdgyDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 14:56
 * Description：<描述>
 */
@Service

public class Kss_DagyService {

    @Autowired
    private DdgyDao ddgyDao;

    //大屏单独关押情况

    @UseDataSource("kss_ds")
    public  HashMap<String, Object> find_ddgy(String jsbh){

        Map<String, Object> ddgy = ddgyDao.ddgy(jsbh);

            HashMap<String, Object> map = new HashMap<>();  //最里层

        String kssmc = CacheUtils.get().getKssmcByKss(jsbh);

        map.put("name",kssmc);
        map.put("value",ddgy.get("zy"));

        return map;

    }
}
