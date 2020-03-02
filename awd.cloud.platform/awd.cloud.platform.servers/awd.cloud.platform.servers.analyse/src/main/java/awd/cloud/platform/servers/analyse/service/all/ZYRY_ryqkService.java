package awd.cloud.platform.servers.analyse.service.all;

import awd.cloud.platform.servers.analyse.dao.all.ZYRY_ryqkDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-20 17:34
 * Description：<描述>
 */

@Service
public class ZYRY_ryqkService {


    @Autowired
    private ZYRY_ryqkDao dao;

    //在押人员=人员情况
    @UseDataSource("kss_ds")
    public HashMap<String,Object> findRyqk(String rybh){

        try {
            ArrayList<HashMap<String, Object>> list = dao.selectAjqk(rybh);//案件情况

            ArrayList<Object> list1 = new ArrayList<>();
            for(int i=0;i<list.size();i++){
                HashMap<String, Object> map = new HashMap<>();
                map.put("name","案由");


                try {

                   if(list.get(i).get("ay").equals("")){
                       map.put("value",(!CacheUtils.get().getDictionarys("AJLB", list.get(i).get("ay").toString()).equals(""))?CacheUtils.get().getDictionarys("AJLB", list.get(i).get("ay").toString()):"没有登记案由");
                   }else {map.put("value","没有登记案由");}

                } catch (Exception e) {
                    map.put("value","没有登记案由");
                }


                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("name","入所时间：");
             try {
                 if(!list.get(0).keySet().contains("rs")){map1.put("value","没有登记入所时间");}
                  else{  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String rs = format.format(list.get(0).get("rs"));
                 map1.put("value",(rs==null && rs.equals(""))?"没有登记入所时间":rs);}
                } catch (Exception e) {
                 map1.put("value","没有登记入所时间");
                }

                list1.add(map);list1.add(map1);      // list
            }

            HashMap<String, Object> map = new HashMap<>();
            map.put("name","案件情况");
            map.put("list",list1);      // t


            ArrayList<HashMap<String, Object>> list2 = dao.selectStzk(rybh);//身体状况
            HashMap<String, Object> mapall = new HashMap<>();
          if(list2.size()>0) {
              ArrayList<Object> list3 = new ArrayList<>();
              for (int i = 0; i < list2.size(); i++) {
                  HashMap<String, Object> map1 = new HashMap<>();
                  map1.put("name", "体表伤情：");

                  try {
                      if (!list2.get(0).keySet().contains("tbsq")) {
                          map1.put("value", "没有登记体表伤情");
                      }
                      map1.put("value", (!list2.get(0).get("tbsq").equals("") && list2.get(0).get("tbsq") != null ? list2.get(0).get("tbsq") : "没有登记体表伤情"));
                  } catch (Exception e) {
                      map1.put("value", "没有登记体表伤情");
                  }


                  HashMap<String, Object> map2 = new HashMap<>();
                  map2.put("name", "身体状况：");

                  try {
                      if (!list2.get(0).keySet().contains("stzk")) {
                          map2.put("value", "没有登记身体状况");
                      }
                      map2.put("value", ((list2.get(0).get("stzk") != null && !list2.get(0).get("stzk").equals("")) ? list2.get(0).get("stzk") : "没有登记身体状况"));
                  } catch (Exception e) {
                      map2.put("value", "没有登记身体状况");
                  }


                  HashMap<String, Object> map3 = new HashMap<>();
                  map3.put("name", "心跳：");

                  try {
                      if (!list2.get(0).keySet().contains("xt")) {
                          map3.put("value", (!list2.get(0).get("xt").equals("")) ? list2.get(0).get("xt") : "没有登记心跳");
                      } else {
                          map3.put("value", "没有登记心跳");
                      }
                  } catch (Exception e) {
                      map3.put("value", "没有登记心跳");
                  }


                  HashMap<String, Object> map4 = new HashMap<>();
                  map4.put("name", "血压：");

                  try {
                      if (!list2.get(0).keySet().contains("xy")) {
                          map4.put("value", "未登记血压");
                      } else {
                          map4.put("value", (list2.get(0).get("xy") != null && !list2.get(0).get("xy").equals("")) ? list2.get(0).get("xy") : "未登记血压");
                      }
                  } catch (Exception e) {
                      map4.put("value", "未登记血压");
                  }
                  list3.add(map1);
                  list3.add(map2);
                  list3.add(map3);
                  list3.add(map4);
              }
              //HashMap<String, Object> mapall = new HashMap<>();
              mapall.put("name", "身体状况");
              mapall.put("list", list3);      // b
          }else{
              ArrayList<Object> list3 = new ArrayList<>();
              HashMap<String, Object> map1 = new HashMap<>();
              map1.put("name", "体表伤情：");
              map1.put("value", "没有登记体表伤情");
              HashMap<String, Object> map2 = new HashMap<>();
              map2.put("name", "身体状况：");
              map2.put("value", "没有登记身体状况");
              HashMap<String, Object> map3 = new HashMap<>();
              map3.put("name", "心跳：");
              map3.put("value", "没有登记心跳");
              HashMap<String, Object> map4 = new HashMap<>();
              map4.put("name", "血压：");
              map4.put("value", "没有记血压");
              list3.add(map1);
              list3.add(map2);
              list3.add(map3);
              list3.add(map4);

              mapall.put("name", "身体状况");
              mapall.put("list", list3);           // b
          }




            ArrayList<HashMap<String, Object>> list4 = dao.selectJszt(rybh);//精神状态
            ArrayList<Object> list5 = new ArrayList<>();
            for(int i=0;i<list4.size();i++){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("name","精神状态：");

                try {
                    Object js = list4.get(0).get("js");
                    if (js.equals(1) ){map2.put("value","正常");}
                    if (js.equals(0) ){map2.put("value","不正常");}
                    else {map2.put("value","否");}
                } catch (Exception e) {
                    map2.put("value","否");
                }
                list5.add(map2);
            }
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name","精神状况");
            map2.put("list",list5);                  //j

            HashMap<String, Object> map3 = new HashMap<>();
            map3.put("name","人员情况");
            map3.put("t",map);
            map3.put("b",mapall);
            map3.put("j",map2);

            HashMap<String, Object> map4 = new HashMap<>();
            map4.put("lc",map3);
            HashMap<String, Object> map5 = new HashMap<>();
            map5.put("manage",map4);
            HashMap<String, Object> map6 = new HashMap<>();
            map6.put("data",map5);
            map6.put("msg","");
            map6.put("code",200);

            return  map6;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("zyry_ryqk", "在押人员=人员情况");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


    //2在押人员风险等级
    @UseDataSource("kss_ds")
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
                    map3.put("value","一级");map3.put("type",0);
                }

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
            result.put("zyry_fxdj", "2在押人员风险等级");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }
}
