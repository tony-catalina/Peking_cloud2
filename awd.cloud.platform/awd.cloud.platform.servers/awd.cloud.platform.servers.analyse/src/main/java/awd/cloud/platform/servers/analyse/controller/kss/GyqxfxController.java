package awd.cloud.platform.servers.analyse.controller.kss;

import awd.cloud.platform.servers.analyse.service.kss.Kss_GyqxfxService;
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
public class GyqxfxController {
    @Autowired
    private Kss_GyqxfxService kss_gyqxfxService;

    @GetMapping("/Gyqxfxnum")
    @ApiOperation("关押期限分析")
    @OpenAPI
    public Map<String, Object> getGyqxfxnum(@RequestParam(value = "starttime", required = false) String starttime,
                                            @RequestParam(value = "endtime", required = false) String endtime){
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = kss_gyqxfxService.queryGyqxfxList(starttime, endtime);
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
