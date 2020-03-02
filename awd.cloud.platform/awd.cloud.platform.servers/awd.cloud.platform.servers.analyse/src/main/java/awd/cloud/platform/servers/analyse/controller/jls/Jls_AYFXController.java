package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_AYFXService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Author：YaoBen
 * Date：2019-11-13 16:22
 * Description：<描述>
 */
@Controller
@RequestMapping("/Jls_AYFXController")

@Api(tags = "Jls_AYFXController",description = "案由分析")
public class Jls_AYFXController {

  @Autowired
    private Jls_AYFXService service;


    //案由分析 拘留所
    @ResponseBody
    @PostMapping("/select_AYFX")
    @OpenAPI
    public Map<String,Object> select_AYFX(){

       return service.find_AYFX();
    }
}
