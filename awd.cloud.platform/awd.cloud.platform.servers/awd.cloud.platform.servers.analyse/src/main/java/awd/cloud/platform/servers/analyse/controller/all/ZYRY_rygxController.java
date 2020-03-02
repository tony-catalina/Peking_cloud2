package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.ZYRY_rygxService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * Author：YaoBen
 * Date：2019-12-21 13:46
 * Description：<描述>
 */
@Controller
@RequestMapping("/ZYRY_rygx")
@Api(description = "在押人员=人员关系")
public class ZYRY_rygxController {

    @Autowired
    private ZYRY_rygxService service;

    @ResponseBody
    @OpenAPI
    @CrossOrigin
    @GetMapping("/selectRygx")
    public  HashMap<String, Object> selectRygx(@RequestParam(required = false,value = "jsbh") String jsbh, @RequestParam(required = false,value ="rybh") String rybh,
                                         @RequestParam(required = false,value ="jsh") String jsh) {


        try {
            HashMap<String, Object> rygx = service.findRygx(jsbh, rybh, jsh);
            HashMap<String, Object> map = new HashMap<>();
            map.put("name","人员关系");
            map.put("list",rygx);

            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("code",200);
            map1.put("msg","");
            map1.put("data",map);

            return map1;
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("code",200);
            map1.put("msg","在押人员=人员关系=请求失败");
            map1.put("data","");

            return map1;
        }


    }
}
