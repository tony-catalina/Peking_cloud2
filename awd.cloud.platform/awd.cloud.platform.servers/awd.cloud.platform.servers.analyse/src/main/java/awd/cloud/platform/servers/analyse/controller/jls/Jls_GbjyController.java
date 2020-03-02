package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_GbjyService;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_thjy")
@RefreshScope
@Api(tags = "jls_thjy",description = "谈话教育")
public class Jls_GbjyController {
     @Autowired
     private Jls_GbjyService jls_gbjyService;

    @GetMapping("/thjydt")
    @ApiOperation("业务动态")
    @OpenAPI
    public Map<String, Object> jtjyDt_rq(@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "badw", required = false) String badw) {

        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_gbjyService.Gbjy_dtFx(starttime, endtime, badw);
        if (list == null) {
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

    @GetMapping("/thjytz")
    @ApiOperation("业务台账")
    @OpenAPI
    public Map<String, Object> jtjyDt_rq(@RequestParam(value = "state", required = false) String state,
                                         @RequestParam(value = "xm", required = false) String xm,
                                         @RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "starttime", required = false) String starttime1,
                                         @RequestParam(value = "endtime", required = false) String endtime1) {

        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_gbjyService.Gbjy_tzFx(state, xm, starttime, endtime, starttime1, endtime1);
        if (list == null) {
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
