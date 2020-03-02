package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.BJSDY_kss_zdgzDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-11-28 16:05
 * Description：<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
public class BJSDY_kss_zdgzService {

    @Autowired
    private BJSDY_kss_zdgzDao zdgzDao;

    //重点关注人员
    @UseDataSource("kss_ds")
    public HashMap<String,Object> find_zdgz(String jsbh){


        ArrayList<HashMap<String, Object>> list = zdgzDao.select_zdgz(jsbh);
        ArrayList<Object> list3 = new ArrayList<>();




        for(int i=0;i<list.size();i++){
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("series","xl");
        map1.put("name","序列");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("series","item1");
        map2.put("name","姓名");
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("series","item2");
        map3.put("name","监室号");
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("series","item3");
        map4.put("name","风险等级");
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("series","item4");
        map5.put("name","列控原因");
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("series","item5");
        map6.put("name","管控措施");
        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("series","item6");
        map7.put("name","管控民警");
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(map1);list2.add(map2);list2.add(map3);
        list2.add(map4);list2.add(map5);list2.add(map6);
        list2.add(map7);



            HashMap<String, Object> map = new HashMap<>();
            if(list.get(i).keySet().contains("姓名")){
               map.put("item1",list.get(i).get("姓名"));

            }
            if(list.get(i).keySet().contains("监室号")){
                map.put("item2",list.get(i).get("监室号"));

            }
            if(list.get(i).keySet().contains("风险等级")){
                map.put("item3",list.get(i).get("风险等级"));
            }
            if(list.get(i).keySet().contains("列控原因")){
                map.put("item4",list.get(i).get("列控原因"));
            }
            if(list.get(i).keySet().contains("管控措施")){
                map.put("item5",list.get(i).get("管控措施"));
            }
            if(list.get(i).keySet().contains("管控民警")){
                map.put("item6",list.get(i).get("管控民警"));
            }
            map.put("x1",1);
            ArrayList<Object> list1 = new ArrayList<>();
            list1.add(map);


            HashMap<String, Object> map11 = new HashMap<>();
            map11.put("data",list1);
            map11.put("seriesNum",7);
            map11.put("titles",list2);


            HashMap<String, Object> map9 = new HashMap<>();
            map9.put("area","一级风险");
            map9.put("id",i);
            map9.put("img",list.get(i).get("img"));
            map9.put("list",map11);
            map9.put("name",list.get(i).get("姓名"));
            map9.put("number",list.get(i).get("监室号")+"监室");
            map9.put("type",i);

            list3.add(map9);


            /*if(list.get(i).keySet().contains("img")){
                map.put("img",list.get(i).get("img"));
            }*/

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("cc",list3);
        return map;

    }
}
