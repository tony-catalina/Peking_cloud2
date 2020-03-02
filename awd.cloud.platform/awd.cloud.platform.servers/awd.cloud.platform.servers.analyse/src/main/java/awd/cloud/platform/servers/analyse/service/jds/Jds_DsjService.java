package awd.cloud.platform.servers.analyse.service.jds;

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
 * Date：2019-11-15 18:31
 * Description：<描述>
 */
@Service

public class Jds_DsjService {

    //大屏戒毒所在押人数
    @Autowired
    private DsjPTDao dsjPTDao;


    @UseDataSource("jds_ds")
    public Map<String, Object> dsJPT_JDS() {

        Map<String, Object> map = dsjPTDao.zyRS();

        for(int i =0;i<map.size();i++){
            ArrayList<Object> listR = new ArrayList<>();     //R:层
            HashMap<String, Object> map2 = new HashMap<>();  //右最内层
            ArrayList<Object> listL = new ArrayList<>();     //L:层
            HashMap<String, Object> map1 = new HashMap<>();  //左最内层
            Set<String> set = map.keySet();
            if(set.equals("zy")){
                map1.put("name","在押总数");
                map1.put("value",map.get("zy"));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(set.equals("nx")){
                map1.put("name","其中女性");
                map1.put("value",map.get("nv"));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(set.equals("jrrs")){
                map1.put("name","今日入所");
                map1.put("value",map.get("jrrs"));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(set.equals("jrcs")){
                map1.put("name","今日出所");
                map1.put("value",map.get("jrcs"));
                map1.put("unit","人");
                listL.add(map1);
            }

            //右
            if(set.equals("yj")){
                map2.put("name","一级风险人数");
                map2.put("value",map.get("yj"));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get("yjzb"));
                listR.add(map2);
            }
            if(set.equals("ej")){
                map2.put("name","二级风险人数");
                map2.put("value",map.get("ej"));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get("ejzb"));
                listR.add(map2);
            }
            if(set.equals("sj")){
                map2.put("name","三级风险人数");
                map2.put("value",map.get("sj"));
                map2.put("unit","人");
                map2.put("pro","占比");
                map2.put("pValue",map.get("sjzb"));
                listR.add(map2);
            }

        }

        HashMap<String, Object> mapCount = new HashMap<>(); //count层
        mapCount.put("l:",mapCount.get("listL"));
        mapCount.put("r:",mapCount.get("listR"));


        HashMap<String, Object> mapCountInto = new HashMap<>();//count层套进去
        mapCountInto.put("count",mapCountInto.get(mapCount));
        mapCountInto.put("name","戒毒所");


        return  mapCountInto;
    }
}
