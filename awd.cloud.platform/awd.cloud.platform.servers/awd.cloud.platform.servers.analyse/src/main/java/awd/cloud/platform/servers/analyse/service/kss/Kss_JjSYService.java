package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JjSYDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 15:44
 * Description：<描述>
 */
@Service

public class Kss_JjSYService {

    @Autowired
    private JjSYDao jjSYDao;



    //kss械具
    @UseDataSource("kss_ds")
    public HashMap<String, Object> find_JJSY(String jsbh) {

        Map<String, Object> map = jjSYDao.jjSY(jsbh);

        HashMap<String, Object> map1 = new HashMap<>();
        String kssmc = CacheUtils.get().getKssmcByKss(jsbh);
        map1.put("name", kssmc);
        map1.put("value", map.get("jj"));

        System.out.println("map1=="+map1.toString());
        return map1;
    }






    //2jls械具
    @UseDataSource("jls_ds")
    public HashMap<String, Object> find_jls_JJSY(String jsbh) {

        HashMap<String, Object> map1 = new HashMap<>();

        if(jsbh.equals("110000121")) {

            Map<String, Object> map = jjSYDao.jls_jjSY(jsbh);

            map1.put("name", "北京市拘留所");
            map1.put("value", map.get("jj"));
        }
        else if(jsbh.equals("110101121")) {
            Map<String, Object> map = jjSYDao.jls_jjSY(jsbh);

            map1.put("name", "东城区拘留所");
            map1.put("value", map.get("jj"));
        }
        else if(jsbh.equals("110105121")) {
            Map<String, Object> map = jjSYDao.jls_jjSY(jsbh);

            map1.put("name", "朝阳区拘留所");
            map1.put("value", map.get("jj"));
        }
        else if(jsbh.equals("110111121")) {
            Map<String, Object> map = jjSYDao.jls_jjSY(jsbh);

            map1.put("name", "房山区拘留所");
            map1.put("value", map.get("jj"));
        }
        else if(jsbh.equals("110108121")) {
            Map<String, Object> map = jjSYDao.jls_jjSY(jsbh);

            map1.put("name", "海定区拘留所");
            map1.put("value", map.get("jj"));
        }

        return map1;

    }
}





