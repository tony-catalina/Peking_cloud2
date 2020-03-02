package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.jls.Jls_DsjPTDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-15 18:27
 * Description：<描述>
 */


@Service
public class Jls_DsjService {

    //大屏拘留所在押人数
    @Autowired
    private Jls_DsjPTDao jls_dsjPTDao;


    @UseDataSource("jls_ds")
    public Map<String, Object> dsJPT_JLS() {
        Map<String, Object> maps = jls_dsjPTDao.zyRS_jls();


        //return maps;
        ArrayList<Object> listL = new ArrayList<>();     //L:层
        ArrayList<Object> listR = new ArrayList<>();     //R:层
        HashMap<String, Object> map2 = new HashMap<>();  //右最内层
        HashMap<String, Object> map3 = new HashMap<>();  //右最内层
        HashMap<String, Object> map4 = new HashMap<>();  //右最内层

        for(String key:maps.keySet()){

            HashMap<String, Object> map1 = new HashMap<>();  //左最内层

            if(key.equals("zy")){

                map1.put("name","在押总数");
                map1.put("value",maps.get(key));
                 map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("nx")){

                map1.put("name","其中女性");
                map1.put("value",maps.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("jrrs")){
                map1.put("name","今日入所");
                map1.put("value",maps.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }
            if(key.equals("jrcs")){
                map1.put("name","今日出所");
                map1.put("value",maps.get(key));
                map1.put("unit","人");
                listL.add(map1);
            }

               //右
               if(key.equals("yj") || key.equals("yjzb")){
                 if(key.equals("yj")){
                     map2.put("name","一级风险人数");
                     map2.put("value",maps.get(key));
                     map2.put("unit","人");

                 }
                  if(key.equals("yjzb")) {
                      map2.put("pro","占比");
                      map2.put("pValue",maps.get(key));
                  }

               }
               if(key.equals("ej") || key.equals("ejzb")){
                   if(key.equals("ej")){
                       map3.put("name","二级风险人数");
                       map3.put("value",maps.get(key));
                       map3.put("unit","人");

                   }
                   if(key.equals("ejzb")) {
                       map3.put("pro","占比");
                       map3.put("pValue",maps.get(key));
                   }

               }
               if(key.equals("sj") ||key.equals("sjzb") ){

                   if(key.equals("sj")){
                       map4.put("name","三级风险人数");
                       map4.put("value",maps.get(key));
                       map4.put("unit","人");

                   }
                   if(key.equals("sjzb")) {
                       map4.put("pro","占比");
                       map4.put("pValue",maps.get(key));
                   }

               }

        }


        listR.add(map2);
        listR.add(map3);
        listR.add(map4);

        HashMap<String, Object> mapCount = new HashMap<>(); //count层
        mapCount.put("l",listL);
         mapCount.put("r",listR);




        HashMap<String, Object> mapCountInto = new HashMap<>();//count层套进去
        mapCountInto.put("cont",mapCount);
        mapCountInto.put("name","拘留所");



        return  mapCountInto;
    }
}
