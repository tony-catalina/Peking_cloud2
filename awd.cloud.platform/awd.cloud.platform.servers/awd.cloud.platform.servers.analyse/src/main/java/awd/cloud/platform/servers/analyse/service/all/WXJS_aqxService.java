package awd.cloud.platform.servers.analyse.service.all;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-13 18:35
 * Description：<描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
public class WXJS_aqxService {

    @Transactional(readOnly = true)
    public HashMap<String,Object> find_aqx(){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","一级风险人数");
        map.put("value",1000);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name","二级风险人数");
        map1.put("value",1000);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name","三级风险人数");
        map2.put("value",1000);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("name","被监管人员打架数");
        map3.put("value",1000);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("name","被监管人员突发疾病数");
        map4.put("value",1000);
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("name","被监管人员企图自杀自伤数");
        map5.put("value",1000);
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("name","被监管人员因病死亡人数");
        map6.put("value",1000);
        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("name","被监管人出所就医人数");
        map7.put("value",1000);
        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("name","看守所交付执执行人数");
        map8.put("value",1000);
        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("name","基础设施建设情况");
        map9.put("value",1000);
        ArrayList<Object> list = new ArrayList<>();
        list.add(map);list.add(map1);list.add(map2);list.add(map3);list.add(map4);
        list.add(map5);list.add(map6);list.add(map7);list.add(map8);list.add(map9);

        HashMap<String, Object> map10 = new HashMap<>();
        map10.put("list",list);map10.put("name","安全型");
        HashMap<String, Object> map11 = new HashMap<>();
        map11.put("ct",map10);

        HashMap<String, Object> map14 = new HashMap<>();
        map14.put("name","昌平区看守所");map14.put("value0",0);
        map14.put("value1",86);map14.put("value2","是");
        map14.put("value3","是");
        HashMap<String, Object> map15 = new HashMap<>();
        map15.put("name","延庆区看守所");map15.put("value0",1);
        map15.put("value1",86);map15.put("value2","是");
        map15.put("value3","是");
        ArrayList<Object> list1 = new ArrayList<>();
        list1.add(map14); list1.add(map15);

        HashMap<String, Object> map16 = new HashMap<>();
        map16.put("name","监所");map16.put("unit","");
        HashMap<String, Object> map17 = new HashMap<>();
        map17.put("name","资金投入情况");map17.put("unit","（万元）");
        HashMap<String, Object> map18 = new HashMap<>();
        map18.put("name","“俩警”建设完成情况");map18.put("unit","（完成项目数）");
        HashMap<String, Object> map19 = new HashMap<>();
        map19.put("name","监室“挂点”消除完成情况");map19.put("unit","（是/否）");
        HashMap<String, Object> map20 = new HashMap<>();
        map20.put("name","监室安全玻璃改造完成情况");map20.put("unit","（是/否）");
        ArrayList<Object> list2 = new ArrayList<>();
        list2.add(map16);list2.add(map18);list2.add(map17);list2.add(map19);
        list2.add(map20);

        HashMap<String, Object> map21 = new HashMap<>();
        map21.put("body",list1); map21.put("header",list2);
        HashMap<String, Object> map22 = new HashMap<>();
        map22.put("list1",map21);


        HashMap<String, Object> map12 = new HashMap<>();
        map12.put("collection",map11);
        map12.put("popCllection",map22);

        HashMap<String, Object> map13 = new HashMap<>();
        map13.put("data",map12);map13.put("code",200);map13.put("msg","");
        return  map13;


    }
}
