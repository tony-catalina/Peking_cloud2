package awd.cloud.platform.servers.analyse.controller.all;


import awd.cloud.platform.servers.analyse.service.all.Sy_gylService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/all/sy")
@Api(description = "关押量")
public class Sy_gylController {
     @Autowired
     private Sy_gylService gylService;

    //案由分析 拘留所
    @OpenAPI
    @ResponseBody
    @GetMapping("/gylQuery")
    @ApiOperation("查询")
    @CrossOrigin
    public Map<String,Object> gylQuery(){

        Map<String,Object> map = new HashMap();
        try{
            Integer gyl = gylService.find_gyl();

            Integer gyl_jls = gylService.find_gyl_jls();

            String a = gyl+gyl_jls+"";

            map.put("code","200");
            map.put("data",a);
            map.put("msg","成功");
            map.put("result",true);
            map.put("totalSize","");
        } catch (Exception e){
            e.printStackTrace();
            map.put("code","500");
            map.put("data","0") ;
            map.put("msg","失败");
            map.put("result",false);
            map.put("totalSize","");
        }
        return map;
    }
}
