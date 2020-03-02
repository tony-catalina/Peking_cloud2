package awd.cloud.platform.servers.analyse.service.all;

import awd.cloud.platform.servers.analyse.dao.all.ZYRY_jbxxDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-20 15:00
 * Description：<描述>
 */

@Service
public class ZYRY_jbxxService {

    @Autowired
  private ZYRY_jbxxDao dao;



     //在押人员==基本信息
    @UseDataSource("kss_ds")
    public HashMap<String,Object> findJbxx(String rybh){
        HashMap<String, Object> map = dao.selectJbxx(rybh);
        ArrayList<Object> list = new ArrayList<>();
        if(map.size()>0){
            for(String key : map.keySet() ){
               String xm = "没有登记姓名";
               String xb ="没有登记性别";
               String mz ="没有登记民族";
               String sfzh="没有登记身份证号";
               String ay="没有登记入所原因";
           if(key!=null){
               Object xm1 = map.get("xm");
               if(xm1!=null && !"".equals(xm1.toString())){
                   xm=xm1.toString();
               }
               Object xb1 = map.get("xb");
               if(xb1!=null && !"".equals(xb1.toString())){
                   xb=xb1.toString();
               }
               Object mz1 = map.get("mz");
               if(mz1!=null && !"".equals(mz1.toString())){
                   mz=mz1.toString();
               }
               Object zjh = map.get("zjh");
               if(zjh!=null && !"".equals(zjh.toString())){
                   sfzh=zjh.toString();
               }
               Object ay1 = map.get("ay");
               if(ay1!=null && !"".equals(ay1.toString())){
                   String a = CacheUtils.get().getDictionarys("AJLB", ay1.toString());
                   if(!"".equals(a)) {
                       ay = a;
                   }
               }

           }
                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("name","姓名");
                map1.put("msg",xm);
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("name","性别");
                map2.put("msg",xb);
                HashMap<String, Object> map3 = new HashMap<>();
                map3.put("name","民族");
                map3.put("msg",mz);
                HashMap<Object, Object> map4 = new HashMap<>();
                map4.put("name","身份证号");
                map4.put("msg",sfzh);
                HashMap<String, Object> map5 = new HashMap<>();
                map5.put("name","入所原因");
                map5.put("msg",ay);
                //ArrayList<Object> list = new ArrayList<>();
                list.add(map1);
                list.add(map2);
                list.add(map3);
                list.add(map4);
                list.add(map5);     //人员信息
            }
        }else{
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","姓名");
            map1.put("msg","没有登记姓名");
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name","性别");
            map2.put("msg","没有登记性别");
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("name","民族");
            map3.put("msg","没有登记民族");
            HashMap<Object, Object> map4 = new HashMap<>();
            map4.put("name","身份证号");
            map4.put("msg","没有登记身份证号");
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("name","入所原因");
            map5.put("msg","没有登记入所原因");
            //ArrayList<Object> list = new ArrayList<>();
            list.add(map1);
            list.add(map2);
            list.add(map3);
            list.add(map4);
            list.add(map5);     //人员信息
        }
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("name","基本信息");
        ArrayList<HashMap<String, Object>> list1 = dao.selectJbxxImg(rybh);//照片
                String url ="暂无照片";
        if(list1.size()>0){
            for(HashMap<String, Object> img :list1){
             if(img!=null){
                 Object img1 = img.get("img");
                 if(img1!=null && !"".equals(img1.toString())){
                     url=img1.toString();
                 }
             }
            }
        }

           map6.put("name","基本信息");
           map6.put("img", url);
        map6.put("msg","短信关注");
        map6.put("msgList",list);


        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("lt",map6);
        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("manage",map7);
        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("data",map8);

        map9.put("msg","");

        map9.put("code",200);

        return map9;

       /* try {
            HashMap<String, Object> map = dao.selectJbxx(rybh);

            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","姓名");
            try {
                if(!map.keySet().contains("xm")){ map1.put("msg","没有登记姓名");}
                else{map1.put("msg",( map.get("xm")!="")?map.get("xm"):"没有登记姓名");}
            } catch (Exception e) {
                map1.put("msg","没有登记姓名");
            }
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name","性别");
            try {
                if(!map.keySet().contains("xb")){ map2.put("msg","没有登记性别");}
                else{map2.put("msg",( map.get("xb")!="")?map.get("xb"):"没有登记性别");}
            } catch (Exception e) {
                map2.put("msg","没有登记性别");
            }
            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("name","民族");
            try {
                if(!map.keySet().contains("mz")){ map3.put("msg","没有登记民族");}
                else{map3.put("msg",( map.get("mz")!=null)?map.get("mz"):"没有登记民族");}
            } catch (Exception e) {
                map3.put("msg","没有登记民族");
            }
            HashMap<Object, Object> map4 = new HashMap<>();
            map4.put("name","身份证号");
            try {
                if(!map.keySet().contains("zjh")){ map4.put("msg","没有登记身份证号");}
                else{map4.put("msg",(map.get("zjh")!="")?map.get("zjh"):"没有登记身份证号" );}
            } catch (Exception e) {
                map4.put("msg","没有登记身份证号");
            }
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("name","入所原因");

            try {

                if(!map.keySet().contains("ay")){ map5.put("msg","没有登记入所原因");}
                else{map5.put("msg",( CacheUtils.get().getDictionarys("AJLB", map.get("ay").toString())!="")?CacheUtils.get().getDictionarys("AJLB", map.get("ay").toString()):"没有登记入所原因");}
            } catch (Exception e) {
                map5.put("msg","没有登记入所原因");
            }

            ArrayList<Object> list = new ArrayList<>();
            list.add(map1);
            list.add(map2);
            list.add(map3);
            list.add(map4);
            list.add(map5);     //人员信息


            HashMap<String, Object> map6 = new HashMap<>();
            map6.put("name","基本信息");
            ArrayList<HashMap<String, Object>> list1 = dao.selectJbxxImg(rybh);//照片
            if(list1.size()>0) {
                try {
                    map6.put("img", (!list1.get(0).get("img").equals(""))?list1.get(0).get("img"):"没有上传照片");
                } catch (Exception e) {
                    map6.put("img", "没有上传照片");
                }


            }
            map6.put("msg","短信关注");
            map6.put("msgList",list);


            HashMap<String, Object> map7 = new HashMap<>();
            map7.put("lt",map6);
            HashMap<String, Object> map8 = new HashMap<>();
            map8.put("manage",map7);
            HashMap<String, Object> map9 = new HashMap<>();
            map9.put("data",map8);

            map9.put("msg","");

            map9.put("code",200);

            return map9;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("zary_jbxx", "在押人员==基本信息");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }*/
    }

}
