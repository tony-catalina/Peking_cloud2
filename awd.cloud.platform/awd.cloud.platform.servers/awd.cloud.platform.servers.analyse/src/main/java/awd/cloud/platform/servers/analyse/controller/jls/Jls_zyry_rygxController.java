package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.all.ZYRY_rygxService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_zyry_rygxService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-12-21 13:46
 * Description：<描述>
 */
@Controller
@RequestMapping("/jls_zyry_rygx")
@Api(description = "拘留所在押人员=人员关系")
public class Jls_zyry_rygxController {

    @Autowired
    private Jls_zyry_rygxService service;

   @ResponseBody
    @OpenAPI
    @CrossOrigin
    @GetMapping("/selectRygx")
    public Map<String,Object> selectRygx(@RequestParam(required = false,value = "jsbh") String jsbh, @RequestParam(required = false,value ="rybh") String rybh,
                                         @RequestParam(required = false,value ="jsh") String jsh) {
        return service.findRygx(jsbh, rybh, jsh);
    }

    //在押人员风险等级
    @ResponseBody
    @OpenAPI
    @GetMapping("/jlsFxdj")
    public Map<String,Object> jlsFxdj(@RequestParam(required = false,value = "rybh") String rybh) {
        HashMap<String, Object> fxdj = service.findFxdj(rybh);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",fxdj);
        map.put("msg","");
        map.put("code",200);
        return map;

    }
}
