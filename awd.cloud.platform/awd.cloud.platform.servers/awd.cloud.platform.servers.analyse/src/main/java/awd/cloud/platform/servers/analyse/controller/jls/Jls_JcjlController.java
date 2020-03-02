package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_JcjlService;
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
@RequestMapping("/jls_jcjl")
@RefreshScope
@Api(tags = "jls_jcjl",description = "奖惩记录")
public class Jls_JcjlController {
    @Autowired
    private Jls_JcjlService jls_jcjlService;

    @GetMapping("/Jcjldt")
    @ApiOperation("业务动态")
    @OpenAPI
    public Map<String, Object> jcjldt_rq(@RequestParam(value = "starttime", required = false) String starttime,
                                       @RequestParam(value = "endtime", required = false) String endtime,
                                       @RequestParam(value = "badw", required = false) String badw) {

        if (starttime != null) {
            starttime += " 00:00:00";
        }
        if (endtime != null) {
            endtime += " 23:59:59";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_jcjlService.jcjl_rqFx(starttime, endtime, badw);
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

    @GetMapping("/jcjltz")
    @ApiOperation("业务台账")
    @OpenAPI
    public Map<String, Object> jcjltz_rq(@RequestParam(value = "cpgj", required = false) String cpgj,
                                         @RequestParam(value = "cprq", required = false) String cprq,
                                         @RequestParam(value = "ly", required = false) String ly,
                                         @RequestParam(value = "gjyj", required = false) String gjyj) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_jcjlService.jcjl_ywFx(cpgj, cprq, ly, gjyj);
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