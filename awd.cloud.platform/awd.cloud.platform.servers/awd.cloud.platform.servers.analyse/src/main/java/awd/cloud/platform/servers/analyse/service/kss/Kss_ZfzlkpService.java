package awd.cloud.platform.servers.analyse.service.kss;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-10 11:03
 * Description：<描述>
 */
//执法质量考评
    @Service
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
public class Kss_ZfzlkpService {

        @Transactional(readOnly = true)
    public ArrayList<Object> selectZfzlkp(){
            HashMap<String, Object> map = new HashMap<>();
            map.put("name","苏宁慧谷");
            map.put("value","110");

            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","南京");
            map1.put("value","120");

            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name","河西");
            map2.put("value","130");

            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("name","徐州");
            map3.put("value","140");

            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("name","其他");
            map4.put("value","150");

            ArrayList<Object> list = new ArrayList<>();
            list.add(map);list.add(map1);list.add(map2);
            list.add(map3);list.add(map4);

            return  list;

        }



}
