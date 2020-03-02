package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.ZYRY_ryqkService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-12-20 18:23
 * Description：<描述>
 */
@Controller
@RequestMapping("/ZYRY_ryqk")
@Api(description = "在押人员=人员情况")
public class ZYRY_ryqkController {

    @Autowired
    private ZYRY_ryqkService service;

    @ResponseBody
    @OpenAPI
    @CrossOrigin
    @GetMapping("/selectRyqk")
    public Map<String,Object> selectRyqk(@RequestParam(required = false,value = "rybh") String rybh) {




        HashMap<String, Object> ryqk = service.findRyqk(rybh);


        return ryqk;

    }



    //2在押人员风险等级
    @ResponseBody
    @OpenAPI
    @GetMapping("/selectFxdj")
    public Map<String,Object> selectFxdj(@RequestParam(required = false,value = "rybh") String rybh) {

        HashMap<String, Object> fxdj = service.findFxdj(rybh);

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",fxdj);
        map.put("msg","");
        map.put("code",200);


        return map;

    }


}
