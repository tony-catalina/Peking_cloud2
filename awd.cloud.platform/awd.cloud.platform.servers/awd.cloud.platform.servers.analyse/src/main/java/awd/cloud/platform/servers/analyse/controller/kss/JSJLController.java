package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.dao.kss.JSJLDao;
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
 * Date：2019-11-12 16:01
 * Description：<描述>
 */
@Controller
@Api(description = "大屏今日监所警力部署")
@RequestMapping("/kss/jsjl")
public class JSJLController {

    @Autowired
    private JSJLDao jsjlDao;


    //今日监所警力部署
    @ResponseBody
    @GetMapping("/select_JSJL")
    @OpenAPI
    @CrossOrigin
    public Map<String, Object> select_JSJL(@RequestParam("jsbh") String jsbh) {


        List<String> list = JSON.parseArray(jsbh, String.class);

        try {
            ArrayList<Object> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {


                String jsbh1 = list.get(i).toString();//jsbh
                if (!jsbh1.equals("110000121") && !jsbh1.equals("110101121") && !jsbh1.equals("110105121") && !jsbh1.equals("110111121")&& !jsbh1.equals("110108121")) {
                    HashMap<String, Object> map1 = new HashMap<>();

                    String jsbhString = CacheUtils.get().getKssmcByKss(jsbh1);
                    map1.put("depart", "北京市");
                    map1.put("departB", jsbhString);



                    String sldAll = "";
                    ArrayList<Map<String, Object>> SLD = jsjlDao.select_jSJL_sld(jsbh1);//所领导



                    if (SLD.size() == 0) {
                        sldAll = "今日无值班领导";
                    } else {
                        for (int j = 0; j < SLD.size(); j++) {
                            Object sld1 = SLD.get(j).get("sld");

                            if (j == 0) {
                                sldAll = sld1.toString();
                            }
                            if (j >= 1) {
                                sldAll = sldAll.concat("、") + sld1;
                            }

                        }
                    }


                    map1.put("name", sldAll);


                    ArrayList<Map<String, Object>> zbrs = jsjlDao.select_jSJL_zbrs(jsbh1);//值班人数
                    if (zbrs.size() == 0) {
                        map1.put("number", "今日未登记值班人数");
                    } else {
                        for (int k = 0; k < zbrs.size(); k++) {
                            Object zbrs1 = zbrs.get(k).get("zbrs");

                            map1.put("number", zbrs1);
                        }
                    }


                    String sjhAll = "";
                    if (SLD.size() == 0) {
                        sjhAll = "暂无";
                    } else {
                        for (int u = 0; u < SLD.size(); u++) {

                            ArrayList<Map<String, Object>> sjh = jsjlDao.select_jSJL_sjh(jsbh1, SLD.get(u).get("sld").toString());//手机号
                            if (sjh.size() != 0) {
                                for (Map<String, Object> map : sjh) {
                                    String sld_sjh = map.get("sjh").toString().trim();
                                    if (sld_sjh != null && !"".equals(sld_sjh)) {
                                        sjhAll = sjhAll.concat(sld_sjh + "、");
                                    }
                                }
                            }
                        }
                        if (sjhAll.lastIndexOf(",") != -1) {
                            sjhAll = sjhAll.substring(0, sjhAll.lastIndexOf("、"));
                        }

                    }


                    map1.put("phone", sjhAll);

                    list1.add(map1);
                }


                //2 jls数据
                 else if (jsbh1.equals("110000121") ||jsbh1.equals("110101121")||jsbh1.equals("110105121")||jsbh1.equals("110111121")||jsbh1.equals("110108121")) {
                    HashMap<String, Object> map1 = new HashMap<>();


                    map1.put("depart", "北京市");
                    if(jsbh1.equals("110000121"))
                    {map1.put("departB", "北京市拘留所");}
                    else if(jsbh1.equals("110101121"))
                    {map1.put("departB", "东城区拘留所");}
                    else if(jsbh1.equals("110105121"))
                    {map1.put("departB", "朝阳区拘留所");}
                    else if(jsbh1.equals("110111121"))
                    {map1.put("departB", "房山区拘留所");}
                    else if(jsbh1.equals("110108121"))
                    {map1.put("departB", "海定区拘留所");}


                    String sldAll = "";
                    ArrayList<Map<String, Object>> SLD = jsjlDao.select_jls_jSJL_sld(jsbh1);//所领导

                    if (SLD.size() == 0) {
                        sldAll = "今日无值班领导";
                    } else {
                        for (int j = 0; j < SLD.size(); j++) {
                            Object sld1 = SLD.get(j).get("sld");

                            if (j == 0) {
                                sldAll = sld1.toString();
                            }
                            if (j >= 1) {
                                sldAll = sldAll.concat("、") + sld1;
                            }

                        }
                    }


                    map1.put("name", sldAll);

                     map1.put("number", "没有字段");



                    String sjhAll = "";
                    if (SLD.size() == 0) {
                        sjhAll = "暂无";
                    } else {
                        for (int u = 0; u < SLD.size(); u++) {

                            ArrayList<Map<String, Object>> sjh = jsjlDao.select_jls_jSJL_sjh(jsbh1, SLD.get(u).get("sld").toString());//手机号
                            if (sjh.size() != 0) {
                                for (Map<String, Object> map : sjh) {
                                    String sld_sjh = map.get("sjh").toString().trim();
                                    if (sld_sjh != null && !"".equals(sld_sjh)) {
                                        sjhAll = sjhAll.concat(sld_sjh + "、");
                                    }
                                }
                            }
                        }
                        if (sjhAll.lastIndexOf(",") != -1) {
                            sjhAll = sjhAll.substring(0, sjhAll.lastIndexOf("、"));
                        }

                    }


                    map1.put("phone", sjhAll);

                    list1.add(map1);
                }





            }


            Map<String, Object> reMap = new HashMap<>();
            reMap.put("code", 200);
            reMap.put("data", 54);
            reMap.put("msg", "成功");
            reMap.put("result", true);
            reMap.put("totalSize", null);
            reMap.put("JSJL1", list1);
            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("JSJY", "今日监所警力部署");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }


}



