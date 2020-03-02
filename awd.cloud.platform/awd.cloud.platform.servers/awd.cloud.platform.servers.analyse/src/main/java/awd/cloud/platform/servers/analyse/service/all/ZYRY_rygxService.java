package awd.cloud.platform.servers.analyse.service.all;

import awd.cloud.platform.servers.analyse.dao.all.ZYRY_rygxDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-21 9:50
 * Description：<描述>
 */

@Service
public class ZYRY_rygxService {

    @Autowired
    private ZYRY_rygxDao dao;


    @UseDataSource("kss_ds")
    public  HashMap<String, Object> findRygx(String jsbh, String rybh, String jsh) {

        //本人
        ArrayList<HashMap<String, Object>> list = dao.selectBr(rybh);//本人姓名
        ArrayList<HashMap<String, Object>> list1 = dao.selectImg(rybh);//本人URL
        ArrayList<Object> list2 = new ArrayList<>();
        String brxm="";//暂无本人姓名
        String brimg="";//暂无本人照片
        if(list.size()>0){

            for(HashMap<String, Object> xm :list){
            if(xm!=null){
                Object xm1 = xm.get("xm");//本人姓名
                if(xm1!=null && !"".equals(xm1.toString())){
                    brxm=xm1.toString();
                }
            }
            }
        }else{brxm="";}//暂无本人姓名

        if(list1.size()>0){
            for(HashMap<String, Object> img : list1){
              if(img!=null){
                  Object img1 = img.get("img");
                  if(img1!=null && !"".equals(img1.toString())){
                      brimg=img1.toString();
                  }
              }
            }
        }else{brimg="";}//暂无本人照片
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",brxm);
        map.put("img",brimg);
        map.put("type", 0);
        list2.add(map);




        //父子
        ArrayList<HashMap<String, Object>> list3 = dao.selectFz(rybh);
        if(list3.size()>0){
        HashMap<String, Object> map11 = new HashMap<>();
            String fq="";//暂无父亲姓名
            for(HashMap<String, Object> fz :list3) {
                if (fz != null) {
                    Object xm = fz.get("xm");

                    if (xm != null && !"".equals(xm.toString())) {
                        fq = xm.toString();
                    }
                }

                map11.put("name", fq);
                map11.put("img", "");//来访人员无照片
                map11.put("type", 1);
                list2.add(map11);
            } }else{
            HashMap<String, Object> map11 = new HashMap<>();
            map11.put("name", "无父亲姓名");
            map11.put("img", "来访人员无照片");
            map11.put("type", 1);
            list2.add(map11);
        }



        //主管民警
        ArrayList<HashMap<String, Object>> selectzg = dao.selectzg(jsbh, jsh);
        String zgxm="";//暂无主管民警
        String zgimg="";//暂无主管民警照片

         if(selectzg.size()>0){
             HashMap<String, Object> map1 = new HashMap<>();
             for(HashMap<String, Object> zgmj:selectzg) {
                 if (zgmj != null) {
                     Object zg = zgmj.get("zg");//主管民警xm
                     if (zg != null && !"".equals(zg.toString())) {
                         zgxm = zg.toString();
                     }

                     Object zgjh = zgmj.get("zgjh");//主管民警警号
                     if (zgjh != null && !"".equals(zgjh)) {
                         //根据民警警号 查照片
                         ArrayList<HashMap<String, Object>> mjZp = dao.selectMjZp(zgjh.toString());
                         if (mjZp.size() > 0) {
                             for (HashMap<String, Object> mjimg : mjZp) {
                                 Object img = mjimg.get("img");
                                 if (img != null && !"".equals(img.toString())) {
                                     zgimg = img.toString();//照片
                                 }
                             }
                         }
                     }
                 }
                 map1.put("name", zgxm);
                 map1.put("img", zgimg);
                 map1.put("type", 2);
                 list2.add(map1);
             }
         }else{
             HashMap<String, Object> map1 = new HashMap<>();
             map1.put("name",zgxm);
             map1.put("img",zgimg);
             map1.put("type", 2);
             list2.add(map1);

         }


        //协管民警
        ArrayList<HashMap<String, Object>> selectxg = dao.selectxg(jsbh, jsh);


        if (selectxg.size() > 0){
            for (HashMap<String,Object> xg_map : selectxg) {
                HashMap<String, Object> map1 = new HashMap<>();
                if (xg_map != null) {

                    Object xg = xg_map.get("xg");
                    String xg_value = "";//暂无协管民警

                    String xgjh_value = null;
                    if (xg != null && !"".equals(xg.toString().trim())) {
                        xg_value = xg.toString();
                    }
                    map1.put("name",xg_value);

                    Object xgjh = xg_map.get("xgjh");
                    if (xgjh != null && !"".equals(xgjh.toString().trim())) {
                        xgjh_value = xgjh.toString();
                    }

                    ArrayList<HashMap<String, Object>> xgjh_list = null;
                    String photo_url = "";//暂无协管民警照片
                    if (xgjh_value != null) {

                        xgjh_list = dao.selectMjZp(xgjh_value);
                    }

                    if (xgjh_list != null) {
                       if(!xgjh_list.isEmpty()) {
                           if (xgjh_list != null && !"".equals(xgjh_list.get(0).get("url"))) {
                               photo_url = xgjh_list.get(0).get("url").toString();
                           }
                       }
                    }
                    map1.put("img",photo_url);
                    map1.put("type", 3);         //???????????????
                    list2.add(map1);

                }
            }

    }else{
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","协管民警");
            map1.put("img","暂无协管民警照片");
            map1.put("type", 3);  //???????????????
            list2.add(map1);
        }


        //同监
        ArrayList<HashMap<String, Object>> list4 = dao.selectTj(jsbh, jsh);//同监姓名和rybh

        if(list4.size()>0) {
            for (HashMap<String, Object> tj : list4) {
                if (tj != null) {
                    HashMap<String, Object> map1 = new HashMap<>();
                    String tj_value = "";//暂无同监人员姓名

                    String tjRybh_value = null;//同监rybh
                    Object tjRybh = tj.get("rybh");
                    if(!tjRybh.toString().equals(rybh)){ //去除自己本身

                        Object xm = tj.get("xm");//同监姓名

                        if (xm != null && !"".equals(xm.toString().trim())) {
                            tj_value = xm.toString();
                        }
                        map1.put("name", tj_value);

                    if (tjRybh != null && !"".equals(tjRybh.toString().trim())) {
                        tjRybh_value = tjRybh.toString();
                    }

                    ArrayList<HashMap<String, Object>> tjImg = null;//同监人员照片
                    String tj_photo = "";//暂无同监人员照片
                    if (tjRybh_value != null) {
                        tjImg = dao.selectImg(rybh);
                    }
                    if (tjImg != null && tjImg.size() > 0) {
                        if (tjImg.get(0) != null && !"".equals(tjImg.get(0).get("img"))) {
                            tj_photo = tjImg.get(0).get("img").toString();
                        }
                    }
                    map1.put("img", tj_photo);
                    map1.put("type", 4);         //???????????????
                    list2.add(map1);

                }}
            }}else{
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("name","");//暂无同监
            map1.put("img","");//暂无同监照片
            map1.put("type", 4);  //???????????????
            list2.add(map1);
        }




        //同案人
        ArrayList<HashMap<String, Object>> list5 = dao.selectTafh(rybh);//根据rybh 查同案犯号  用来查下面的同案人
          if(list5.size()>0){
              for(HashMap<String, Object> tafh:list5) {
                  if (tafh != null) {
                      HashMap<String, Object> map1 = new HashMap<>();
                      Object taf_h = tafh.get("taf");//同案犯号
                      String taf_xm = "";//暂无同案人姓名
                      String tar_img = "";//暂无同案人员照片
                      if (taf_h != null && !"".equals(taf_h.toString().trim())) {
                          //同案人 姓名  和 人员编号 根据同案犯号
                          ArrayList<HashMap<String, Object>> tar = dao.selectTar(taf_h.toString());
                          if (tar.size() > 0) {
                              for (HashMap<String, Object> tar_xm : tar) {

                                  Object tar_rybh = tar_xm.get("rybh");//同案rybh
                                  //String tar_img ="暂无同案人员照片";
                                  if (tar_rybh != null && !"".equals(tar_rybh.toString().trim())) {                           if(!tar_rybh.toString().equals(rybh)) { //去除自己

                                      Object xm = tar_xm.get("xm");
                                      if (xm != null && !"".equals(xm.toString().trim())) {
                                          taf_xm = tar_xm.get("xm").toString();//同案xm
                                      }

                                      ArrayList<HashMap<String, Object>> taf_img = dao.selectImg(tar_rybh.toString());//同案img 根据rybh
                                      if (taf_img.size() > 0) {
                                          for (HashMap<String, Object> url : taf_img) {
                                              Object img = url.get("img");
                                              if (img != null && !"".equals(img.toString().trim())) {
                                                  tar_img = img.toString();
                                              }
                                          }
                                      }
                                  }
                                  }
                              }
                          }
                      }
                      map1.put("name", taf_xm);
                      map1.put("img", tar_img);
                      map1.put("type", 5);  //???????????????
                      list2.add(map1);

                  }

              }}else{
              HashMap<String, Object> map1 = new HashMap<>();
              map1.put("name","暂无同案人姓名");
              map1.put("img","暂无同案人员照片");
              map1.put("type", 5);  //???????????????
              list2.add(map1);
          }


        ArrayList<Object> list6 = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("source", 0);
        map1.put("target", 1);
        map1.put("value", "父子");
        list6.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("source", 0);
        map2.put("target", 2);
        map2.put("value", "主管民警");
        list6.add(map2);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("source", 0);
        map3.put("target", 3);
        map3.put("value", "协管民警");
        list6.add(map3);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("source", 0);
        map4.put("target", 4);
        map4.put("value", "同监人员");
        list6.add(map4);
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("source", 0);
        map5.put("target", 5);
        map5.put("value", "同案人员");
        list6.add(map5);

        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("d",list2);
        map6.put("links",list6);


        return map6;

    }

    }
