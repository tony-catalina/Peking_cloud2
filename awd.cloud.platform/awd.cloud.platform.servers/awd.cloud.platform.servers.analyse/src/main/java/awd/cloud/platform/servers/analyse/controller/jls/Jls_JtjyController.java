package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_JtjyService;
import awd.framework.common.controller.message.ResponseMessage;
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
@RequestMapping("/jls_jtjy")
@RefreshScope
@Api(tags = "jls_jtjy",description = "集体教育")
public class Jls_JtjyController {
    @Autowired
    private Jls_JtjyService jls_jtjyService;

    @GetMapping("/jtjydt")
    @ApiOperation("业务动态")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> jtjyDt_rq(@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "badw", required = false) String badw) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_jtjyService.jtjy_dtFx(starttime, endtime, badw);
        if (list == null) {
            result.put("state", 400);
            result.put("data", null);
            result.put("msg", "分析失败");
        } else {
            result.put("state", 200);
            result.put("data", list);
            result.put("msg", "分析成功");
        }
        return ResponseMessage.ok(result);
    }

    @GetMapping("/jtjytz")
    @ApiOperation("业务台账")
    @OpenAPI
    public Map<String, Object> jtjyTz_rq(@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "jyr", required = false) String jyr,
                                         @RequestParam(value = "zw", required = false) String zw,
                                         @RequestParam(value = "pzr", required = false) String pzr) {

        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_jtjyService.jtjy_tzFx(starttime, endtime, jyr, zw, pzr);
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
