package awd.cloud.platform.servers.analyse.service.jls;

import awd.cloud.platform.servers.analyse.dao.all.ZYRY_rygxDao;
import awd.cloud.platform.servers.analyse.dao.jls.Jls_zyry_rygxDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.apache.struts2.components.Else;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-12-21 9:50
 * Description：<描述>
 */

@Service
public class Jls_zyry_rygxService {

    @Autowired
    private Jls_zyry_rygxDao dao;


    @UseDataSource("jls_ds")
    public HashMap<String,Object> findRygx(String jsbh, String rybh, String jsh) {

        ArrayList<Object> arrayList = new ArrayList<>();

        ArrayList<HashMap<String, Object>> list = dao.selectBr(rybh);//本人姓名照片
        if (list.size()>0||!list.isEmpty()){
        for (int i = 0; i < list.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
                if (list.get(i).get("xm")!=null || list.get(i).get("xm")!=""){
                    System.out.println("................"+list.get(i).get("xm"));
                    map.put("name",list.get(i).get("xm"));
                }else {
                    map.put("name","未登记姓名");
                }
                if (list.get(i).get("url")!=null || list.get(i).get("url")!=""){
                    map.put("img",list.get(i).get("url"));
                }else {
                    map.put("img","");
                }
                map.put("type", 0);
                arrayList.add(map);
        }
        }else {
            Map map=new HashMap();
            map.put("name","无此登记人");
            map.put("img","无照片");
            map.put("type", 0);
            arrayList.add(map);
        }
        ArrayList<HashMap<String, Object>> list1 = dao.selectFz(rybh);//父子
        if(list1.size()>0||!list1.isEmpty()){
        for (int i = 0; i < list1.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
                if (list1.get(i).get("fz")!=null || list1.get(i).get("fz")!=""){
                    map.put("name",list1.get(i).get("fz"));
                }else {
                    map.put("name","未登记父亲");
                }
                if (list1.get(i).get("url")!=null || list1.get(i).get("url")!=""){
                    map.put("img",list1.get(i).get("url"));
                }else {
                    map.put("img","照片未上传");
                }
            map.put("type", 1);
            map.put("relation", "父子");
            arrayList.add(map);
        }
        }else{
            Map map=new HashMap();
            map.put("name","查询不到此登记人");
            map.put("img","找不到相关照片");
            map.put("type", 1);
            map.put("relation", "父子");
            arrayList.add(map);
        }

        ArrayList<HashMap<String, Object>> list2 = dao.selectzg(jsbh,  jsh);//主管民警
        if (list2.size()>0||!list2.isEmpty()){
        for (int i = 0; i < list2.size(); i++) {
            HashMap<String, Object> map = new HashMap<>();
                if (list2.get(i).get("zg")!=null || list2.get(i).get("zg")!=""){
                    map.put("name",list2.get(i).get("zg"));
                }else {
                    map.put("name","暂未登记主管民警");
                }
            map.put("type", 2);
            map.put("img","");
            map.put("relation", "主管民警");
            arrayList.add(map);
        }
        }else {
            Map map=new HashMap();
            map.put("name","暂未登记人");
            map.put("img","");
            map.put("type", 2);
            map.put("relation", "主管民警");
            arrayList.add(map);
        }

        //协管民警
        ArrayList<HashMap<String, Object>> selectxg = dao.selectxg(jsbh, jsh);
        if (selectxg.size()>0||!selectxg.isEmpty()){
        for(int i =0;i<selectxg.size();i++) {
            HashMap<String, Object> map = new HashMap<>();
            if (selectxg.get(i).get("xg") != null || selectxg.get(i).get("xg") != "") {
                map.put("name", selectxg.get(i).get("xg"));
            }else {
                map.put("name", "暂无协管民警");
            }
            map.put("img","");
            map.put("type", 5);
            map.put("relation", "协管民警");
            arrayList.add(map);
        }
            }else {
            Map map=new HashMap();
            map.put("name","无此登记人");
            map.put("img","");
            map.put("type", 5);
            map.put("relation", "协管民警");
            arrayList.add(map);
        }
        /*ArrayList<HashMap<String, Object>> list3 = dao.selectTar(rybh);//同案人
        if (list3.size()!=0){
            for (int i = 0; i < list3.size(); i++) {
                HashMap<String, Object> map = new HashMap<>();
                if (list3.get(i).get("tar")!=null || list3.get(i).get("tar")!=""){
                    map.put("name",list3.get(i).get("tar"));
                }else {
                    map.put("name","暂无同案人员");
                }
                if (list3.get(i).get("tarurl")!=null || list3.get(i).get("tarurl")!=""){
                    map.put("img",list3.get(i).get("tarurl"));
                }else {
                    map.put("img","照片未上传");
                }
                map.put("type", 3);
                map.put("relation", "同案");
                arrayList.add(map);
        }
        }else {
                HashMap<String, Object> map = new HashMap<>();
                    map.put("name","同案无信息");
                    map.put("img","照片未上传");
                    map.put("type", 3);
                    map.put("relation", "同案");
                    arrayList.add(map);
        }*/
        ArrayList<HashMap<String, Object>> list4 = dao.selectTj(rybh);
        //同监
        if (list4.size()> 0||!list4.isEmpty()) {
        for (int i = 0; i < list4.size(); i++) {
            if (rybh.equals(list4.get(i).get("rybh"))==false) {
                HashMap<String, Object> map = new HashMap<>();
                if (list4.get(i).get("xm") != null || list4.get(i).get("xm") != "") {
                    map.put("name", list4.get(i).get("xm"));
                } else {
                    map.put("name", "暂无同监人员");
                }
                if (list4.get(i).get("url") != null || list4.get(i).get("url") != "") {
                    map.put("img", list4.get(i).get("url"));
                } else {
                    map.put("img", "照片未上传");
                }
                map.put("type", 4);
                map.put("relation", "同监");
                arrayList.add(map);
            }
        }
        }else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name","同监无信息");
            map.put("img","照片未上传");
            map.put("type", 4);
            map.put("relation", "同监");
            arrayList.add(map);
        }
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("source", 0);
        map1.put("target", 1);
        map1.put("value", "主管民警");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("source", 0);
        map2.put("target", 2);
        map2.put("value", "父子");
        /*HashMap<String, Object> map3 = new HashMap<>();
        map3.put("source", 0);
        map3.put("target", 3);
        map3.put("value", "同案");*/
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("source", 0);
        map4.put("target", 4);
        map4.put("value", "同监");
        HashMap<String, Object> map10 = new HashMap<>();
        map10.put("source", 0);
        map10.put("target", 5);
        map10.put("value", "协管民警");
        ArrayList<Object> list7 = new ArrayList<>();
        list7.add(map1);list7.add(map2);
      /*list7.add(map3);*/
        list7.add(map4);
        list7.add(map10); //links

        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("d", arrayList);
        map5.put("links", list7);

        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("name", "人员关系");
        map6.put("list", map5);


        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("data", map6);

        map9.put("msg", "");

        map9.put("code", 200);

        return map9;
    }
    //在押人员风险等级
    @UseDataSource("jls_ds")
    public HashMap<String, Object> findFxdj(String rybh){
        try {
            ArrayList<HashMap<String, Object>> list = dao.selectFxdj(rybh);
            HashMap<String, Object> map = new HashMap<>();
            map.put("name","一级");
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","二级");
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name","三级");
            ArrayList<Object> list1 = new ArrayList<>();
            list1.add(map);
            list1.add(map1);
            list1.add(map2);
            HashMap<String, Object> map3 = new HashMap<>();
            if(list.size()>0){
                String dj = list.get(0).get("dj").toString();
                System.out.println("jghg=s"+dj);
                if("1".equals(dj)){map3.put("value","一级");
                    map3.put("type",0);
                }
                else if("2".equals(dj)){map3.put("value","二级");
                    map3.put("type",1);
                }
                else if("3".equals(dj)){map3.put("value","三级");
                    map3.put("type",2);
                }
                else{
                    map3.put("value","一级");
                    map3.put("type",0);
                }
                System.out.println("fsfahjhh="+map3.toString());
                map3.put("name","风险等级");
                map3.put("list",list1);
            }
            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("ctr",map3);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("manage",map4);
            return map5;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("zyry_fxdj", "在押人员风险等级");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }
    }
