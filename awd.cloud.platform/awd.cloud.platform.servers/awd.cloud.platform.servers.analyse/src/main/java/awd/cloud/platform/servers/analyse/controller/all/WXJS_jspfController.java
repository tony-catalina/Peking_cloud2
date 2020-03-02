package awd.cloud.platform.servers.analyse.controller.all;


import awd.cloud.platform.servers.analyse.service.all.WXJS_jspfService;
import awd.framework.common.utils.OpenAPI;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/wxjs")
@Api(tags = "wxjs-jspf",description = "监所评分查询")
public class WXJS_jspfController {

    @Autowired
    private WXJS_jspfService  jspfService;

    @OpenAPI
    @CrossOrigin
    @GetMapping("/select_jspf")
    @ApiOperation("查询")
    @ResponseBody
    public Map<String,Object>  select_jspf(){
        Map<String,Object> map=jspfService.jspfQuery();
        return  map;
    }
}
