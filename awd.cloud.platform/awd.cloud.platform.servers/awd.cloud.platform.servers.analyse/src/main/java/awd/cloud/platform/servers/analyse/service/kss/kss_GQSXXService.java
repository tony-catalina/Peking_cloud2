package awd.cloud.platform.servers.analyse.service.kss;

import awd.cloud.platform.servers.analyse.dao.kss.GQSXXDao;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-12 10:22
 * Description：<描述>
 */
@Service
public class kss_GQSXXService {

    @Autowired
    private GQSXXDao gqsxxDao;


    //kss各区所信息 页面中间偏下 律师会见。。
    @UseDataSource("kss_ds")
    public List<Object> find_GQSXX(String jsbh){

        HashMap<String, Object>  map1 = new HashMap<>();
        List<Map<String, String>> maps = gqsxxDao.select_xf(jsbh);  //l 鼠标悬浮
        for(int i =0;i<maps.size();i++){
        HashMap<String, Object> map = new HashMap<>();
            map.put("sjyl",maps.get(i).get("sjyl"));
            map.put("zbjl",maps.get(i).get("zbjl"));
            map.put("sld",maps.get(i).get("sld"));
            map.put("dh",maps.get(i).get("sjh"));
            map.put("dz",maps.get(i).get("dz"));
            map.put("name",CacheUtils.get().getJsbhString(jsbh));
            map1.put("l",map);

        }


        HashMap<String, Object> map2 = new HashMap<>();
        List<Map<String, String>> maps1 = gqsxxDao.select_GQSXX(jsbh);//各区所信息 页面中间偏下 律师会见。。。
        for(int j =0;j<maps1.size();j++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("zyzs",maps1.get(j).get("zy"));
            map.put("man",maps1.get(j).get("man"));
            map.put("woman",maps1.get(j).get("woman"));
            map.put("zats",maps1.get(j).get("ts"));
            map.put("lshj",maps1.get(j).get("lshj"));
            map.put("jshj",maps1.get(j).get("jshj"));
            map.put("yj",maps1.get(j).get("yjwx"));
            map.put("ej",maps1.get(j).get("ejwx"));
            map.put("sj",maps1.get(j).get("sjwx"));
            map2.put("r",map);



        }

        ArrayList<Object> list = new ArrayList<>();
        list.add(map1);list.add(map2);


        return list;
    }


    //jls各区所信息 页面中间偏下 律师会见。。
    @UseDataSource("jls_ds")
    public List<Object> find_jls_GQSXX(String jsbh){
          String mc ="暂无名称";
        if(jsbh.equals("310000000")){mc="拘留所";}
        if(jsbh.equals("110000121")){mc="北京市拘留所";}
        if(jsbh.equals("110101121")){mc="东城区拘留所";}
        if(jsbh.equals("110105121")){mc="朝阳区拘留所";}
        if(jsbh.equals("110111121")){mc="房山区拘留所";}
        if(jsbh.equals("110108121")){mc="海定区拘留所";}


        HashMap<String, Object> map1 = new HashMap<>();
        List<Map<String, String>> maps = gqsxxDao.select_jls_xf(jsbh);  //l 鼠标悬浮
        for(int i =0;i<maps.size();i++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("sjyl",maps.get(i).get("sjyl"));
            map.put("zbjl",maps.get(i).get("zbjl"));
            map.put("sld",maps.get(i).get("sld"));
            map.put("dh",maps.get(i).get("sjh"));
            map.put("dz",maps.get(i).get("dz"));
            map.put("name",mc);

            map1.put("l",map);

        }

        HashMap<String, Object> map2 = new HashMap<>();
        List<Map<String, String>> maps1 = gqsxxDao.select_jls_GQSXX(jsbh);//各区所信息 页面中间偏下 律师会见。。。
        for(int j =0;j<maps1.size();j++){
            HashMap<String, Object> map = new HashMap<>();
            map.put("zyzs",maps1.get(j).get("zy"));
            map.put("man",maps1.get(j).get("man"));
            map.put("woman",maps1.get(j).get("woman"));
            map.put("zats",maps1.get(j).get("ts"));
            map.put("lshj",maps1.get(j).get("lshj"));
            map.put("jshj",maps1.get(j).get("jshj"));
            map.put("yj",maps1.get(j).get("yjwx"));
            map.put("ej",maps1.get(j).get("ejwx"));
            map.put("sj",maps1.get(j).get("sjwx"));
            map2.put("r",map);



        }

        ArrayList<Object> list = new ArrayList<>();
        list.add(map1);list.add(map2);


        return list;
    }






}
