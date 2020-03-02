package awd.cloud.platform.servers.analyse.service.all;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-10 16:01
 * Description：<描述>
 */

    @Service
    @Transactional(propagation = Propagation.MANDATORY,rollbackFor = Throwable.class)
public class WXJS_fzxService {

    //五刑监所=法制型=无表暂时封装假数据
    @Transactional(readOnly = true)
        public HashMap<String,Object> find_fzx(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","法律问题");
        map.put("unit","件");
        map.put("value",100);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name","看守所");
        map1.put("unit","%");
        map1.put("value",0.11);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name","拘留所");
        map2.put("unit","%");
        map2.put("value",0.22);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("name","表扬下发");
        map3.put("unit","%");
        map3.put("value",0.33);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("name","整改下发");
        map4.put("unit","");
        map4.put("value",110);
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("name","检查通报");
        map5.put("unit","件");
        map5.put("value",110);
        ArrayList<Object> list = new ArrayList<>();
        list.add(map);list.add(map1);list.add(map2);
        list.add(map3);list.add(map4);list.add(map5);


        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("name","需要法律援助");
        map6.put("value",120);
        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("name","转递投诉、控告、检举材料");
        map7.put("value",120);
        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("name","转递申诉材料");
        map8.put("value",120);
        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("name","快递上诉材料");
        map9.put("value",120);
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(map6);list1.add(map7);
        list1.add(map8);list1.add(map9);


        HashMap<String, Object> map10 = new HashMap<>();
        map10.put("col","#eb6f49");
        map10.put("value",130);
        HashMap<String, Object> map11 = new HashMap<>();
        map11.put("col","#fdb628");
        map11.put("value",140);
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(map10);list2.add(map11);
        HashMap<String, Object> map12 = new HashMap<>();
        map12.put("list",list2);map12.put("name","具有JAVA资格统计");

        HashMap<String, Object> map13 = new HashMap<>();
        map13.put("col","#eb6f49");
        map13.put("value",150);
        HashMap<String, Object> map14 = new HashMap<>();
        map14.put("col","#fdb628");
        map14.put("value",160);
        ArrayList<Object> list3 = new ArrayList<>();
        list3.add(map13);list3.add(map14);
        HashMap<String, Object> map15 = new HashMap<>();
        map15.put("list",list3);map15.put("name","参加web培训统计");

        HashMap<String, Object> map16 = new HashMap<>();
        map16.put("col","#eb6f49");
        map16.put("value",170);
        HashMap<String, Object> map17 = new HashMap<>();
        map17.put("col","#fdb628");
        map17.put("value",180);
        ArrayList<Object> list4 = new ArrayList<>();
        list4.add(map16);list4.add(map17);
        HashMap<String, Object> map18 = new HashMap<>();
        map18.put("list",list4);map18.put("name","参加执法教育统计");
        ArrayList<Object> list5 = new ArrayList<>();
        list5.add(map12);list5.add(map15);list5.add(map18);


        HashMap<String, Object> map19 = new HashMap<>();
        map19.put("blist",list); map19.put("clist",list1);
        map19.put("name","法治型"); map19.put("tlist",list5);
        HashMap<String, Object> map20 = new HashMap<>();
        map20.put("lt",map19);


        //弹窗数据
        HashMap<String, Object> map22 = new HashMap<>();
        map22.put("name","北京市第一看守所");map22.put("value0",1);
        map22.put("value1",2);map22.put("value2",3);
        HashMap<String, Object> map24 = new HashMap<>();
        map24.put("name","北京市第一看守所");map24.put("value0",8);
        map24.put("value1",9);map24.put("value2",10);
        ArrayList<Object> list6 = new ArrayList<>();
        list6.add(map22); list6.add(map24);

        HashMap<String, Object> map25 = new HashMap<>();
        map25.put("name","监所");
        HashMap<String, Object> map26 = new HashMap<>();
        map26.put("name","总队组织培训参加人数");
        HashMap<String, Object> map27 = new HashMap<>();
        map27.put("name","监所组织培训参加人数");
        HashMap<String, Object> map28 = new HashMap<>();
        map28.put("name","其他组织培训参加人数");
        ArrayList<Object> list7 = new ArrayList<>();
        list7.add(map25);list7.add(map26);list7.add(map27);
        list7.add(map28);
        HashMap<String, Object> map29 = new HashMap<>();
        map29.put("body",list6);map29.put("header",list7);
        HashMap<String, Object> map30 = new HashMap<>();
        map30.put("list0",map29);




        HashMap<String, Object> map21 = new HashMap<>();
        map21.put("collection",map20);
        map21.put("popCllection",map30);

        HashMap<String, Object> map23 = new HashMap<>();
        map23.put("code",200);map23.put("msg","");
        map23.put("data",map21);


        return map23;

        }

}
