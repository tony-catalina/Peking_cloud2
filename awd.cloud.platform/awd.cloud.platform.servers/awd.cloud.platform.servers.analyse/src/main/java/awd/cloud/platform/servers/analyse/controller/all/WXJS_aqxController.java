package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.WXJS_aqxService;
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
 * Date：2019-12-13 18:47
 * Description：<描述>
 */
@Controller
@RequestMapping("/wxjs")
@Api(description = "五刑监所=安全型")
public class WXJS_aqxController {

    @Autowired
    private WXJS_aqxService aqxService;

    @GetMapping("/select_aqx")
    @ResponseBody
    @OpenAPI
    @CrossOrigin
    public Map<String,Object> select_aqx(){
    	Map<String, Object> aqx = aqxService.find_aqx();

        return aqx;

    }
}
