package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_YsxzService;
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
@RequestMapping("/jls_ysxz")
@RefreshScope
@Api(tags = "jls_ysxz",description = "所长接待记录")
public class Jls_YsxzController {
    @Autowired
    private Jls_YsxzService jls_YsxzService;


    @GetMapping("/ysxzCount")
    @ApiOperation("业务动态")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> ysxzCount( @RequestParam(value = "jsbh", required = false) String jsbh,
    		@RequestParam(value = "starttime", required = false) String starttime,
            @RequestParam(value = "endtime", required = false) String endtime) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        List<Map<String, Object>> list = jls_YsxzService.ysxzCount(jsbh,starttime,endtime);
	        result.put("ysxz", list);
	        return ResponseMessage.ok(result);
    	}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
    	}
    }
}