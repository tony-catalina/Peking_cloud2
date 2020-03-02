package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_GjfxService;
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
public class GjfxController {
    @Autowired
    private Kss_GjfxService kss_gjfxService;

    @GetMapping("/ghfxnum")
    @ApiOperation("国籍分析")
    @OpenAPI
    public Map<String,Object> getGjfxnum(@RequestParam(value="jyrq", required = false) String jyrq,
                                         @RequestParam(value="starttime", required = false) String starttime,
                                         @RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result = new HashMap<String,Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = kss_gjfxService.queryGjfxList(jyrq,starttime, endtime);
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

    @GetMapping("/gjfx")
    @ApiOperation("上位机国籍分析")
    @OpenAPI
    public Map<String,Object> getGjfx(@RequestParam(value="jyrq", required = false) String jyrq,
                                         @RequestParam(value="starttime", required = false) String starttime,
                                         @RequestParam(value="endtime", required = false) String endtime){
        Map<String, Object> result = new HashMap<String,Object>();
        if(starttime != null){
            starttime += " 00:00:00";
        }
        if(endtime != null){
            endtime += " 23:59:59";
        }
        List<Map<String, Object>> list = kss_gjfxService.queryGjfx(jyrq,starttime, endtime);
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
