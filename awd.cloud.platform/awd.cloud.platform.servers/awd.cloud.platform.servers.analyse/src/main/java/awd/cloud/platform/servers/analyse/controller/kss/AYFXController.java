package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.jls.Jls_AYFXService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_AYFXService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 16:09
 * Description：<描述>
 */
@Controller
@Api(description = "大屏案由分析 看守所 拘留所")
@RequestMapping("/kss/AYFXController")
public class AYFXController {

    @Autowired
    private Kss_AYFXService service; //案由分析 看守所
    @Autowired
    private Jls_AYFXService jls_ayfxService;//案由分析 拘留所

    @ResponseBody
    @GetMapping("/select_AYFX")
    @OpenAPI
    @CrossOrigin
    public  Map<String,Object> select_AYFX(){

        try {
            Map<String, Object> ayfx = service.find_AYFX();//案由分析 看守所
            Map<String, Object> ayfx1 = jls_ayfxService.find_AYFX();//案由分析 拘留所


            Map<String,Object> reMap = new HashMap<>();
            reMap.put("code",200);
            reMap.put("data",54);
            reMap.put("msg","成功");
            reMap.put("result",true);
            reMap.put("kss",ayfx);
            reMap.put("jls",ayfx1);

            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("AYFX", "大屏案由分析 看守所 拘留所");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;

        }
    }

}
