package awd.cloud.platform.servers.analyse.controller.all;


import awd.cloud.platform.servers.analyse.service.all.WXJS_jspfService;
import awd.cloud.platform.servers.analyse.service.all.WXJS_zhxService;
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
@Api(tags = "wxjs-zhx",description = "智慧型查询")
public class WXJS_zhxController {

    @Autowired
    private WXJS_zhxService zhxService;

    @OpenAPI
    @CrossOrigin
    @GetMapping("/select_zhx")
    @ApiOperation("查询")
    @ResponseBody
    public Map<String,Object>  select_zhx(){
        Map<String,Object> map=zhxService.zhxQuery();
        return  map;
    }
}
