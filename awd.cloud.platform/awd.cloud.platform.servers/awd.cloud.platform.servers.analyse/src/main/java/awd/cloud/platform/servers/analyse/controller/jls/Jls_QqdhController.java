package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_QqdhService;
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
@RequestMapping("/jls_qqdh")
@RefreshScope
@Api(tags = "jls_qqdh",description = "亲情电话")
public class Jls_QqdhController {
    @Autowired
    private Jls_QqdhService jls_qqdhService;

    @GetMapping("/qqdhdt")
    @ApiOperation("业务动态")
    @OpenAPI
    public Map<String, Object> qqdhDt_rq(@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime,
                                         @RequestParam(value = "jsbh", required = false) String jsbh) {

        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_qqdhService.qqdh_dtFx(starttime, endtime, jsbh);
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
