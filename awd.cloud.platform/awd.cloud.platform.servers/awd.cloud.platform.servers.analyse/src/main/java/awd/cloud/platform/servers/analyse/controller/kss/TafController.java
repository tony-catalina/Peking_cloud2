package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_TafService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kss/taf")
public class TafController {
    @Autowired
    private Kss_TafService kss_tafService;

    @GetMapping("/pjdjCx")
    @ApiOperation("同案犯查询")
    @OpenAPI
    public Map<String , Object> taf_rq(@RequestParam(value = "starttime",required = false) String starttime,
                                        @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_tafService.Taf_rqFx(starttime ,endtime );
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }



    @GetMapping("/swj_tafcx")
    @ApiOperation("同案犯查询=上位机版本")
    @OpenAPI
    public Map<String , Object> swj_tafcx(@RequestParam(value = "starttime",required = false) String starttime,
                                       @RequestParam(value = "endtime",required = false) String endtime){
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null ){
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list= kss_tafService.swj_tafcx(starttime ,endtime );
        if(list==null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        }else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }

        return result;
    }
}
