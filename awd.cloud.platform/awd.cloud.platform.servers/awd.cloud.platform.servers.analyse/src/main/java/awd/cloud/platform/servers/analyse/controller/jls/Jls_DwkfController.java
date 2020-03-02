package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_DwkfService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jls_dwkf")
@RefreshScope
@Api(tags = "jls_dwkf",description = "对外开放")
public class Jls_DwkfController {
    @Autowired
    private Jls_DwkfService jls_dwkfService;

    @GetMapping("/dwkfCount")
    @ApiOperation("业务台账")
    @OpenAPI

    public ResponseMessage<Map<String, Object>> jtjyDt_rq( @RequestParam(value = "jsbh", required = false) String badw,@RequestParam(value = "starttime", required = false) String starttime,
                                         @RequestParam(value = "endtime", required = false) String endtime ) {

        if (!StringUtils.isNullOrEmpty(starttime)) {
            starttime += " 00:00:00";
        }
        if (!StringUtils.isNullOrEmpty(endtime)) {
            endtime += " 23:59:59";
        }
        System.err.println("endtime---"+endtime);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> list = jls_dwkfService.Dwkf_dtFx(starttime, endtime, badw);
        System.err.println("list---"+JSON.toJSONString(list));
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

}
