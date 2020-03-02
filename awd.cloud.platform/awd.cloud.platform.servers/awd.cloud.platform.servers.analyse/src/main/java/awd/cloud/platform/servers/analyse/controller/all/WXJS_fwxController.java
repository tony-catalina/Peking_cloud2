package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.WXJS_fwxService;
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
 * Date：2019-12-13 17:39
 * Description：<描述>
 */
@Api(description = "五刑监所=服务型")
@RequestMapping("/wxjs")
@Controller

public class WXJS_fwxController {

    @Autowired
    private WXJS_fwxService fwxService;

    @GetMapping("/select_fwx")
    @CrossOrigin
    @ResponseBody
    @OpenAPI
    public Map<String,Object> select_fwx(){
    	Map<String, Object> fwx = fwxService.find_fwx();

        return  fwx;
    }
}
