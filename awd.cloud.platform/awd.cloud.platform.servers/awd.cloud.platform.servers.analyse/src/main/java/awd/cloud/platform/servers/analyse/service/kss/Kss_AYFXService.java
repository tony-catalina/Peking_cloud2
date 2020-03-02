package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.AYFXDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 16:06
 * Description：<描述>
 */

@Service
public class Kss_AYFXService {

    @Autowired
    private AYFXDao ayfxDao;


    //案由分析 看守所

    @UseDataSource("kss_ds")
    public Map<String,Object> find_AYFX(){


        ArrayList<Map<String,Object>> map = ayfxDao.select_AYFX();

        ArrayList<Object> list = new ArrayList<>();//倒数第二


        for(int i =0;i<map.size();i++){
            HashMap<String, Object> map1 = new HashMap<>();//最里层


                if(map.get(i).get("mc").equals("")){ map1.put("name","没有案由");}
                if(!map.get(i).get("mc").equals("")){map1.put("name",map.get(i).get("mc"));}


            try {
                map1.put("value",map.get(i).get("sl"));
            } catch (Exception e) {
                map1.put("value",0);
            }
            list.add(map1);

        }


        HashMap<String, Object> map5 = new HashMap<>();//正数第二 看守所
        map5.put("name","看守所");
        map5.put("list",list);
        return map5;
    }
}
