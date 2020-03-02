package awd.cloud.platform.servers.analyse.controller.all;


import awd.cloud.platform.servers.analyse.service.all.WXJS_ljxService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/wxjs")
@Api(tags = "wxjs-ljx",description = "廉洁型查询")
public class WXJS_ljxController {

    @Autowired
    private WXJS_ljxService ljxService;

    @OpenAPI
    @CrossOrigin
    @GetMapping("/select_ljx")
    @ApiOperation("查询")
    @ResponseBody
    public Map<String,Object>  select_ljx(){

        Map<String,Object> map=ljxService.ljxQuery();
        return  map;
    }
}
