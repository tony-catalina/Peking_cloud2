package awd.cloud.platform.servers.analyse.service.all;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WXJS_ljxService {

    public Map<String,Object>  ljxQuery(){
        Map<String,Object> rb=new HashMap<>();
        Map<String,Object> map=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        Map<String,Object> list4=new HashMap<>();
        Map<String,Object> collection=new HashMap<>();
        Map<String,Object> popCllection=new HashMap<>();
        List< Map<String,Object>>  llist=new ArrayList<>();
        List< Map<String,Object>>  rlist=new ArrayList<>();
        List< Map<String,Object>>  header=new ArrayList<>();
        List< Map<String,Object>>  body=new ArrayList<>();
        Map<String,Object> llistmap1=new HashMap<>();
        llistmap1.put("name","35岁以下");
        llistmap1.put("value","250");
        llist.add(llistmap1);
        Map<String,Object> llistmap2=new HashMap<>();
        llistmap2.put("name","35至50岁");
        llistmap2.put("value","300");
        llist.add(llistmap2);
        Map<String,Object> llistmap3=new HashMap<>();
        llistmap3.put("name","50岁以上");
        llistmap3.put("value","350");
        llist.add(llistmap3);
        Map<String,Object> rlistmap1=new HashMap<>();
        rlistmap1.put("name","廉政警示教育");
        rlistmap1.put("value","10");
        rlist.add(rlistmap1);
        Map<String,Object> rlistmap2=new HashMap<>();
        rlistmap2.put("name","确定风险岗位");
        rlistmap2.put("value","20");
        rlist.add(rlistmap2);
        Map<String,Object> rlistmap3=new HashMap<>();
        rlistmap3.put("name","梳理廉政风险点");
        rlistmap3.put("value","30");
        rlist.add(rlistmap3);
        Map<String,Object> rlistmap4=new HashMap<>();
        rlistmap4.put("name","创新廉政风险防范机制");
        rlistmap4.put("value","40");
        rlist.add(rlistmap4);
        Map<String,Object> rlistmap5=new HashMap<>();
        rlistmap5.put("name","诫勉谈话");
        rlistmap5.put("value","50");
        rlist.add(rlistmap5);
        Map<String,Object> rlistmap6=new HashMap<>();
        rlistmap6.put("name","提醒谈话");
        rlistmap6.put("value","60");
        rlist.add(rlistmap6);
        rb.put("name","廉洁型");
        rb.put("lname","全市在编警力");
        rb.put("lvalue","1000");
        rb.put("rname","平均年龄");
        rb.put("rvalue","60");
        rb.put("llist",llist);
        rb.put("rlist",rlist);
        Map<String,Object> header1=new HashMap<>();
        header1.put("name","监所");
        header.add(header1);
        Map<String,Object> header2=new HashMap<>();
        header2.put("name","民警数量");
        header.add(header2);
        Map<String,Object> header3=new HashMap<>();
        header3.put("name","本月调入警力数");
        header.add(header3);
        Map<String,Object> header4=new HashMap<>();
        header4.put("name","本月调出警力数");
        header.add(header4);
        Map<String,Object> header5=new HashMap<>();
        header5.put("name","队伍平均年龄");
        header.add(header5);
        Map<String,Object> header6=new HashMap<>();
        header6.put("name","班子应配数");
        header.add(header6);
        Map<String,Object> header7=new HashMap<>();
        header7.put("name","班子实配数");
        header.add(header7);
        Map<String,Object> header8=new HashMap<>();
        header8.put("name","班子平均年龄");
        header.add(header8);
        Map<String,Object> body1=new HashMap<>();
        body1.put("name","东城区看守所");
        body1.put("value","5");
        body1.put("value1","8");
        body1.put("value2","5");
        body1.put("value3","2");
        body1.put("value4","4");
        body1.put("value5","6");
        body1.put("value6","40");
        body.add(body1);
        list4.put("header",header);
        list4.put("body",body);
        collection.put("rb",rb);
        popCllection.put("list4",list4);
        data.put("collection",collection);
        data.put("popCllection",popCllection);
        map.put("code","200");
        map.put("msg","查询成功");
        map.put("data",data);
        return  map;
    }

}
