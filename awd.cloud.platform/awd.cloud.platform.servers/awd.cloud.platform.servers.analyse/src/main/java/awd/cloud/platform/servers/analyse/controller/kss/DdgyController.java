package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_DagyService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_JjSYService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
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
 * Date：2019-11-11 15:02
 * Description：<描述>
 */
@Controller
@Api(description = "大屏单独关押情况")
@RequestMapping("/qsjs/ddgy")
public class DdgyController {

    @Autowired
    private Kss_DagyService service;
    @Autowired
    private Kss_JjSYService kss_jjSYService;//调用械具

    //大屏单独关押 械具使用情况
    @ResponseBody
    @GetMapping("/select_Dagy")
    @OpenAPI
    @CrossOrigin
    public Map<String, Object> select_Dagy(@RequestParam(value ="jsbh") String jsbh){

        List<String> strings = JSON.parseArray(jsbh, String.class);
        try {
            HashMap<String, Object> mapResp = new HashMap<>();//最外面一层


            ArrayList<Object> ddgyMap = new ArrayList<>();
            ArrayList<Object> jjsyMap = new ArrayList<>();

            for(int i =0;i<strings.size();i++){
                HashMap<String, Object> ddgy = service.find_ddgy(strings.get(i));//单独关押
                if(!StringUtils.isNullOrEmpty(ddgy.get("name"))){
                    ddgyMap.add(ddgy);
                }




                HashMap<String, Object> jjsy = kss_jjSYService.find_JJSY(strings.get(i));//kss械具使用
                  //jls械具使用
                String s = strings.get(i).toString();
                System.out.println();
                if(s.equals("110000121") ||s.equals("110101121") || s.equals("110105121") ||s.equals("110111121")||s.equals("110108121") ) {

                    HashMap<String, Object> jls_jjsy = kss_jjSYService.find_jls_JJSY(s);

                    jjsyMap.add(jls_jjsy);

                }
                if(!StringUtils.isNullOrEmpty(jjsy.get("name"))){
                    jjsyMap.add(jjsy);
                }

            }

            mapResp.put("r",jjsyMap);
            mapResp.put("l",ddgyMap);


            Map<String,Object> reMap = Maps.newHashMap();
            reMap.put("code",200);
            reMap.put("data",54);
            reMap.put("msg","成功");
            reMap.put("result",true);
            reMap.put("totalSize",null);
            reMap.put("mapResp",mapResp);


            return  reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("Ddgy", "大屏单独关押 械具使用情况");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }

    }
}
