package awd.cloud.platform.servers.analyse.controller.jls;


import awd.cloud.platform.servers.analyse.service.jls.Jls_JbjlService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_jbjl")
@Api(tags ="jls_jbjl",description = "交班记录")
public class Jls_JbjlController {
    @Autowired
    private Jls_JbjlService jls_JbjlService;


    @GetMapping("/jlsdpzbld")
    @ApiOperation("大屏值班领导")
    @OpenAPI
    public Map<String, Object> dpzbld( @RequestParam(value = "jsbh", required = false) String jsbh) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        Map<String, Object> map = jls_JbjlService.dpzbld(jsbh);
	        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	        list.add(map);
	        result.put("aqjc", list);
	        result.put("code", 200);
	        result.put("msg", "查询成功");
	        return result;
    	}catch (Exception e){
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
	        result.put("code", 500);
	        result.put("msg", "查询失败");
			return result;
    	}
    }
}