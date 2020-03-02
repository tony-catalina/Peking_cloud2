package awd.cloud.platform.servers.analyse.controller.all;

import awd.cloud.platform.servers.analyse.service.all.WXJS_fzxService;
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
 * Date：2019-12-13 17:09
 * Description：<描述>
 */

@Controller
@RequestMapping("/wxjs")
@Api(description = "五刑监所=法制型")
public class WXJS_fzxController {

    @Autowired
    private WXJS_fzxService fzxService;

    @GetMapping("/fzx")
    @ResponseBody
    @CrossOrigin
    @OpenAPI
    public Map<String,Object> select_fzx(){
    	Map<String, Object> fzx = fzxService.find_fzx();
        return fzx;
    }
}
