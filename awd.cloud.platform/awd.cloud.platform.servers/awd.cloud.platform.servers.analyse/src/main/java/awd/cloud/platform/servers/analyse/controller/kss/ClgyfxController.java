package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_ClgyfxService;
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
@RequestMapping("/Kss")
public class ClgyfxController {
    @Autowired
    private Kss_ClgyfxService kss_clgyfxService;


    @GetMapping("/swj_Clgyfx")
    @ApiOperation("上位机=超量关押分析")
    @OpenAPI
    public Map<String, Object> swj_Clgyfx(@RequestParam(value="starttime", required = false) String starttime,@RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result = new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = kss_clgyfxService.swj_Clgyfx(starttime,endtime);
        if (list == null ||list.size() == 0) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }

    @GetMapping("/clgyfx")
    @ApiOperation("超量关押分析")
    @OpenAPI
    public Map<String, Object> getClgyfxnum(@RequestParam(value="starttime", required = false) String starttime,@RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result = new HashMap<String, Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = kss_clgyfxService.queryClgyfxList(starttime,endtime);
        if (list == null ||list.size() == 0) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return result;
    }
}
