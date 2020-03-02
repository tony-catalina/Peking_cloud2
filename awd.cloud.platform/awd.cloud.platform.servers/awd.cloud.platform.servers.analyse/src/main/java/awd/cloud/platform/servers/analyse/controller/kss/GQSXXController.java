package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.kss_GQSXXService;
import awd.framework.common.utils.OpenAPI;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-12 10:30
 * Description：<描述>
 */
@Controller
@Api(description = "各区所信息 页面中间偏下 律师、家属会见")
@RequestMapping("/kss/gqsxx")
public class GQSXXController {

    @Autowired
    private kss_GQSXXService service;

    @GetMapping("/select_GQSXX")
    @ResponseBody
    @OpenAPI
    @CrossOrigin
    public Map<String,Object> select_GQSXX(@RequestParam(required = false,value = "jsbh") String jsbh){


        try {
            Map<String, Object> reMap = Maps.newHashMap();

            if(!jsbh.equals("110000121") &&!jsbh.equals("110101121")&&!jsbh.equals("110105121")&&!jsbh.equals("110111121")&&!jsbh.equals("110108121")) {

                List<Object> gqsxx = service.find_GQSXX(jsbh);
                reMap.put("code", 200);
                reMap.put("data", 54);
                reMap.put("msg", "成功");
                reMap.put("result", true);
                reMap.put("totalSize", null);
                reMap.put("mapResp", gqsxx);

            }

            else if(jsbh.equals("110000121") ||jsbh.equals("110101121")||jsbh.equals("110105121")||jsbh.equals("110111121")||jsbh.equals("110108121")) {

                List<Object> gqsxx = service.find_jls_GQSXX(jsbh);
                reMap.put("code", 200);
                reMap.put("data", 54);
                reMap.put("msg", "成功");
                reMap.put("result", true);
                reMap.put("totalSize", null);
                reMap.put("mapResp", gqsxx);

            }


            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("GQSXX", "各区所信息 页面中间偏下 律师、家属会见");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }
    }

}
