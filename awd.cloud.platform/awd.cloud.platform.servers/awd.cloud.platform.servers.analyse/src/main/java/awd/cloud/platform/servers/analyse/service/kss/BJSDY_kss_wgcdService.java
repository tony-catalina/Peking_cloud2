package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.BJSDY_kss_wgcdDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-30 11:58
 * Description：<描述>
 */
//被监管人员违规程度分析

@Service
public class BJSDY_kss_wgcdService {

    @Autowired
    private BJSDY_kss_wgcdDao wgcdDao;

   /* @Autowired
    private BJSDY_kss_jqwgDao jqwgDao;*/

    @UseDataSource("kss_ds")
    public Map<String, Object> wgCount(String jsbh) {

        return null;
           /* ArrayList<Map<String, Object>> map1 = wgcdDao.qdwgCount(jsbh);
            Map<String, Object> map4 = new HashMap<>();
            List list = new ArrayList();
            List list1 = new ArrayList();
            Map<String, Object> map5 = new HashMap<>();
            Map<String, Object> map6 = new HashMap<>();
            Map<String, Object> map7 = new HashMap<>();
            Map<String, Object> map8 = new HashMap<>();
            Map<String, Object> map9 = new HashMap<>();
            Map<String, Object> map10 = new HashMap<>();
            for (int i = 0; i < map1.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                String person = map1.get(i).get("person").toString();
                String deci = map1.get(i).get("deci").toString();
                map.put("id", 0);
                map.put("type", 0);
                map.put("rule", "轻");
                map.put("name", "轻度违规情况");
                map.put("person", person);
                map.put("deci", deci);
                list.add(map);
            }

            ArrayList<Map<String, Object>> map2 = wgcdDao.ybwgCount(jsbh);

            for (int i = 0; i < map2.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                String person = map2.get(i).get("person").toString();
                String deci = map2.get(i).get("deci").toString();
                map.put("id", 1);
                map.put("type", 1);
                map.put("rule", "中");
                map.put("name", "一般违规情况");
                map.put("person", person);
                map.put("deci", deci);
                list.add(map);
            }

            ArrayList<Map<String, Object>> map3 = wgcdDao.zdwgCount(jsbh);

            for (int i = 0; i < map3.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                String person = map3.get(i).get("person").toString();
                String deci = map3.get(i).get("deci").toString();
                map.put("id", 2);
                map.put("type", 2);
                map.put("rule", "重");
                map.put("name", "严重违规情况");
                map.put("person", person);
                map.put("deci", deci);
                list.add(map);
            }
            List list2 = jqwgDao.select_jsh(jsbh);
            for(int i =0;i<list2.size();i++){
                String jsh = list2.get(i).toString();
                ArrayList<HashMap<String, Object>> hashMaps = jqwgDao.select_jqwgry(jsbh,jsh);
                for(int j=0;j<hashMaps.size();j++){
                    Map<String, Object> xq = new HashMap<>();
                    String item1 = hashMaps.get(j).get("姓名").toString();
                    String item2 = hashMaps.get(j).get("违规事件").toString();
                    String item3 = hashMaps.get(j).get("违规类型").toString();
                    String item4 = hashMaps.get(j).get("处理结果").toString();
                    String item5 = hashMaps.get(j).get("监室号").toString();
                    String item6 = hashMaps.get(j).get("主管民警").toString();
                    String xgmj = hashMaps.get(j).get("协管民警").toString();
                    List list3 = new ArrayList();
                    list3.add(item6);
                    list3.add(xgmj);
                    String p = StringUtils.join(list3.toArray(), ",");
                    xq.put("item1", item1);
                    xq.put("item2", item2);
                    if (item3.equals("3")) {
                        xq.put("item3", "轻度违规");
                    }
                    if (item3.equals("2")) {
                        xq.put("item3", "一般违规");
                    }
                    if (item3.equals("1")) {
                        xq.put("item3", "重度违规");
                    }
                    xq.put("item4", item4);
                    xq.put("item5", item5);
                    xq.put("item6", p);
                    list1.add(xq);
                }
            }
             map10.put("data",list1);
             List list4=new ArrayList();
             List list5=new ArrayList();
             List li=new ArrayList();
             map10.put("list",li);
             Map map=new HashMap();


             map.put("name","监区名");
             map.put("value","监区号");
             Map map11=new HashMap();
             map11.put("seriesNum","7");
             List list3=new ArrayList();
             Map map12=new HashMap();
             Map map13=new HashMap();
             Map map14=new HashMap();
             Map map15=new HashMap();
             Map map16=new HashMap();
             Map map17=new HashMap();
             Map map18=new HashMap();
             map12.put("series","x1");
             map12.put("name","序列");
             map13.put("series","item1");
             map13.put("name","姓名");
             map14.put("series","item2");
             map14.put("name","违规事件");
             map15.put("series","item3");
             map15.put("name","违规类型");
             map16.put("series","item4");
             map16.put("name","处理结果");
             map17.put("series","item5");
             map17.put("name","监室号");
             map18.put("series","item6");
             map18.put("name","主管、协管民警");
             list3.add(map12);
             list3.add(map13);
             list3.add(map14);
             list3.add(map15);
             list3.add(map16);
             list3.add(map17);
             list3.add(map18);
             map11.put("seriesNum","7");
             map11.put("titles",list3);
             map.put("list",map11);
             li.add(map);
             list4.add(map10);
             map9.put("list", list4);


             map9.put("min",1);

             map9.put("name", "件");
             list5.add(map9);
             map8.put("name", "监区违规情况趋势图");
             map8.put("b", list5);
             map4.put("lb", map8);

        map7.put("name", "被监管人员违规程度分析");
        map7.put("list", list);
        map4.put("rc", map7);
        map5.put("index", map4);
        map6.put("msg", "");
        map6.put("code", 200);
        map6.put("data", map5);
            return map6;*/
        }
}
