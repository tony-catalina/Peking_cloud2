package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.jls.Jls_DsjService;
import awd.cloud.platform.servers.analyse.service.kss.Kss_DsjService;
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
 * Date：2019-11-11 9:53
 * Description：<描述>
 */

@Controller
@Api(description = "大屏=五所数据模块")
@RequestMapping("/kss/dsjpt")
public class DsjPTController {

    @Autowired
    private Kss_DsjService service;
    @Autowired
    private Jls_DsjService jls_dsjService;
   // @Autowired
    //private Jds_DsjService jds_dsjService;
    //@Autowired
    //private Qls_DsjService qls_dsjService;
   // @Autowired
   // private Sjs_DsjService sjs_dsjService;


    //大屏看守所在押人数
    @GetMapping("/findKss_dsjPT")
    @ResponseBody
    @OpenAPI
    @CrossOrigin
    public Map<String, Object> findKss_dsjPT(

    ) {

        try {
            Map<String,Object> reMap = new HashMap<>();

            //大屏看守所在押人数
            Map<String, Object> listKSS = service.dsJPT_KSS();


            //大屏拘留所在押人数
            Map<String, Object> mapsJLS = jls_dsjService.dsJPT_JLS();
            /*//大屏戒毒所在押人数
            Map<String, Object> mapsJDS = jds_dsjService.dsJPT_JDS();
            //大屏强辽所在押人数
            Map<String, Object> mapsQLS = qls_dsjService.dsJPT_QLS();
            // //大屏收教所在押人数
             Map<String, Object> mapsSJS = sjs_dsjService.dsJPT_SJS();*/

            reMap.put("code",200);
            reMap.put("data",54);
            reMap.put("msg","成功");
            reMap.put("result",true);
            reMap.put("totalSize",null);
            reMap.put("kss",listKSS);
            reMap.put("jls",mapsJLS);

            return reMap;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> result = new HashMap<>();
            result.put("dsjPT", "大屏看守所在押人数");
            result.put("code", 500);
            result.put("msg", "查询失败");
            return result;
        }


    }
}
