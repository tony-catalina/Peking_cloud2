package awd.cloud.platform.servers.analyse.service.all;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-13 17:17
 * Description：<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
public class WXJS_fwxService {

    //五刑监所=服务型=无表暂时封装假数据
    @Transactional(readOnly = true)
    public HashMap<String,Object> find_fwx(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","转递核实");
        map.put("value",119);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name","资料核实");
        map1.put("value",119);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name","威胁");
        map2.put("value",119);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("name","抓获违法");
        map3.put("value",119);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("name","贩毒");
        map4.put("value",119);
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("name","卖枪");
        map5.put("value",119);
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("name","走私");
        map6.put("value",119);
        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("name","卖老虎");
        map7.put("value",119);
        ArrayList<Object> list = new ArrayList<>();
        list.add(map);list.add(map1);list.add(map2);list.add(map3);
        list.add(map4);list.add(map5);list.add(map6);list.add(map7);


        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("name","依法未收押");map8.put("value","1086");
        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("name","xx"); map9.put("value","1087");
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(map8);list1.add(map9);


        HashMap<String, Object> map10 = new HashMap<>();
        map10.put("name","未收拘入所");map10.put("value","1087");
        HashMap<String, Object> map11 = new HashMap<>();
        map11.put("name","xx"); map11.put("value","1086");
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(map10);list2.add(map11);


        HashMap<String, Object> map12 = new HashMap<>();
        map12.put("list",list); map12.put("min",0);map12.put("name","服务型");
        map12.put("pie0",list1);map12.put("pie1",list2);
        map12.put("pieName","依法未收押或未收拘入所");

        HashMap<String, Object> map13 = new HashMap<>();
        map13.put("lb",map12);
        HashMap<String, Object> map14 = new HashMap<>();
        map14.put("collection",map13);
        HashMap<String, Object> map15 = new HashMap<>();
        map15.put("code",200);map15.put("msg","");
        map15.put("data",map14);

        return map15;


    }

}
