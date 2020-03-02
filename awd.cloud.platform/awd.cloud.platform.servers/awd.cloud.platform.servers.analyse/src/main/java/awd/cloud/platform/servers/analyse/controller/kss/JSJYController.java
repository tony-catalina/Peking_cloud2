package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_JSJYService;
import awd.cloud.platform.servers.analyse.utils.CacheUtils;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-11 16:25
 * Description：<描述>
 */
//全市监所羁押分布
@Controller
@Api(description = "大屏全市监所羁押分布")
@RequestMapping("/kss/jsjy")
public class JSJYController {

    @Autowired
    private Kss_JSJYService service;

    @GetMapping("/select_JSJY")
    @ResponseBody
    @OpenAPI
    @CrossOrigin
    //String jsbh 动态要监所编号
    public Map<String, Object> select_Jsjy(@RequestParam(value = "jsbh") String jsbh) {


        List<String> strings = JSON.parseArray(jsbh, String.class);

        try {
            ArrayList<Object> list = new ArrayList<>();

            Integer num = 0; //朝阳区kss, jls数量
            Integer num1 = 0; //房山区kss，jls数量
            Integer num2 = 0; //东城区kss,jls数量
            Integer num3 = 0;//海淀区kss,jls数量

            for (int i = 0; i < strings.size(); i++) {
                HashMap<String, Object> map1 = new HashMap<>();


                String s = strings.get(i).toString();
                //kss,jls朝阳区数量
                if (s.equals("110105121") || s.equals("110000114") || s.equals("110000112") || s.equals("110000113") || s.equals("110000116") || s.equals("110000121") || s.equals("110105121")||s.equals("110105111")) {
                    if (s.equals("110000121") || s.equals("110105121")) {
                        Map<String, Object> jsjy = service.find__jls_JSJY(s);//jls朝阳区数量
                        num += Integer.parseInt(jsjy.get("sl").toString());
                        System.out.println("jls朝阳区="+num+"=="+s);
                    } else {
                        Map<String, Object> jsjy = service.find_JSJY(s);//kss 朝阳区数量
                        num += Integer.parseInt(jsjy.get("sl").toString());
                        System.out.println("kss朝阳区="+num+"=="+s);
                    }

                }

                //kss,jls 房山区数量
                else if (s.equals("110111111") || s.equals("110111112") || s.equals("110111121")) {
                    if (s.equals("110111121")) {
                        Map<String, Object> jsjy = service.find__jls_JSJY(s);
                        num1 += Integer.parseInt(jsjy.get("sl").toString());
                    } else {
                        Map<String, Object> jsjy = service.find_JSJY(s);
                        num1 += Integer.parseInt(jsjy.get("sl").toString());
                    }

                }

                //东城区kss,jls数量
                else if (s.equals("110101121") || s.equals("110101111")) {
                    if (s.equals("110101121")) {
                        Map<String, Object> jls_jsjy = service.find__jls_JSJY(s);//东城区jls
                        num2 += Integer.parseInt(jls_jsjy.get("sl").toString());

                    }
                    if (s.equals("110101111")) {
                        Map<String, Object> jls_jsjy = service.find_JSJY(s);//东城区kss
                        num2 += Integer.parseInt(jls_jsjy.get("sl").toString());
                    }


                }

                //海淀区kss,jls数量
                else if (s.equals("110108111") || s.equals("110108121")) {
                    System.out.println("yaoben");
                    if (s.equals("110108121")) {
                        Map<String, Object> jls_jsjy = service.find__jls_JSJY(s);//海淀区jls
                        num3 += Integer.parseInt(jls_jsjy.get("sl").toString());

                    }
                    if (s.equals("110108111")) {
                        System.out.println("yaoben2");
                        Map<String, Object> jls_jsjy = service.find_JSJY(s);//海淀区kss
                        num3 += Integer.parseInt(jls_jsjy.get("sl").toString());
                    }


                }



                else if(!s.equals("110105121") || !s.equals("110000114") || !s.equals("110000112") || !s.equals("110000113") || !s.equals("110000116") || !s.equals("110000121") ||!s.equals("110111111") || !s.equals("110111112") || !s.equals("110111121")||!s.equals("110101121") || !s.equals("110101111")||!s.equals("110105111")){
                    Map<String, Object> jsjy = service.find_JSJY(s);//其他kss

                    String kssmc = CacheUtils.get().getKssmcByKss(s);

                    HashMap<String, Object> map = new HashMap<>();
                    String substring = kssmc.substring(0, kssmc.indexOf("区"));
                    System.out.println("dfffff=="+s+substring);
                    map.put("name", substring + "区");
                    map.put("constNumber", 300);
                    map.put("alineNumber", jsjy.get("sl"));
                    list.add(map);

                }

            }
            HashMap<String, Object> map2 = new HashMap<>();
            map2.put("name", "朝阳区");
            map2.put("constNumber", 300);
            map2.put("alineNumber", num);
            list.add(map2);
            HashMap<String, Object> map3 = new HashMap<>();//kss 房山区数量
            map3.put("name", "房山区");
            map3.put("constNumber", 300);
            map3.put("alineNumber", num1);
            list.add(map3);
            HashMap<String, Object> map4 = new HashMap<>();//东城区kss,jls数量
            map4.put("name", "东城区");
            map4.put("constNumber", 300);
            map4.put("alineNumber", num2);
            list.add(map4);
            HashMap<String, Object> map5 = new HashMap<>();//海淀区kss,jls数量
            map5.put("name", "海淀区");
            map5.put("constNumber", 300);
            map5.put("alineNumber", num3);
            list.add(map5);

            Map<String, Object> reMap = new HashMap<>();
            reMap.put("code", 200);
            reMap.put("data", 54);
            reMap.put("msg", "成功");
            reMap.put("result", true);
            reMap.put("totalSize", null);
            reMap.put("JSJY1", list);
            return reMap;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("JSJY", "大屏全市监所羁押分布");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }
}