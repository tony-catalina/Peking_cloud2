package awd.cloud.platform.servers.analyse.service.sjs;

import awd.cloud.platform.servers.analyse.dao.kss.DsjPTDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author：YaoBen
 * Date：2019-11-15 18:41
 * Description：<描述>
 */
@Service

public class Sjs_DsjService {

    //大屏收教所在押人数
    @Autowired
    private DsjPTDao dsjPTDao;


    @UseDataSource("sjs_ds")
    public Map<String, Object> dsJPT_SJS() {

        Map<String, Object> map = dsjPTDao.zyRS();

        for(String key:map.keySet()){
            ArrayList<Object> listR = new ArrayList<>();     //R:层
            HashMap<String, Object> map2 = new HashMap<>();  //右最内层
            ArrayList<Object> listL = new ArrayList<>();     //L:层
            HashMap<String, Object> map1 = new HashMap<>();  //左最内层
            Set<String> set = map.keySet();
            if(key.equals("zy")){
                map1.put("name","在押总数");
                map1.put("value",map.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("nx")){
                map1.put("name","其中女性");
                map1.put("value",map.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("jrrs")){
                map1.put("name","今日入所");
                map1.put("value",map.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("jrcs")){
                map1.put("name","今日出所");
                map1.put("value",map.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }

            //右
            if(key.equals("yj")){
                map2.put("name","一级风险人数");
                map2.put("value",map.get(key));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get(key));
                listR.add(map2);
            }
            if(key.equals("ej")){
                map2.put("name","二级风险人数");
                map2.put("value",map.get(key));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get(key));
                listR.add(map2);
            }
            if(key.equals("sj")){
                map2.put("name","三级风险人数");
                map2.put("value",map.get(key));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get(key));
                listR.add(map2);
            }

        }

        HashMap<String, Object> mapCount = new HashMap<>(); //count层
        mapCount.put("l:",mapCount.get("listL"));
        mapCount.put("r:",mapCount.get("listR"));


        HashMap<String, Object> mapCountInto = new HashMap<>();//count层套进去
        mapCountInto.put("count",mapCountInto.get(mapCount));
        mapCountInto.put("name","拘留所");


        return  mapCountInto;
    }
}
