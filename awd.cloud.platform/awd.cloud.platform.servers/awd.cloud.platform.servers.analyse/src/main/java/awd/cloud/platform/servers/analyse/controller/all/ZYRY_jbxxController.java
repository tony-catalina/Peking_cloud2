package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.ZYRY_jbxxService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-12-20 15:33
 * Description：<描述>
 */
@Controller
@Api(description = "在押人员=基本信息")
@RequestMapping("/ZYRY_jbxx")
public class ZYRY_jbxxController {

    @Autowired
    private ZYRY_jbxxService service;


    //在押人员==基本信息
    @ResponseBody
    @OpenAPI
    @CrossOrigin
    @GetMapping("/selectJbxx")
    public Map<String,Object> selectJbxx(@RequestParam(required = false,value = "rybh") String rybh){



        Map<String, Object>   jbxx = service.findJbxx(rybh);


        return jbxx;
    }
}
