package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_SwjyService;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

@RestController
@RequestMapping("/jls_swjy")
@RefreshScope
@Api(tags = "jls_swjy",description = "所长接待记录")
public class Jls_SwjyController {
    @Autowired
    private Jls_SwjyService jls_SwjyService;


    @GetMapping("/swjyCount")
    @ApiOperation("业务动态")
    @OpenAPI
    public ResponseMessage<Map<String, Object>> swjyCount( @RequestParam(value = "jsbh", required = false) String jsbh,
    		@RequestParam(value = "starttime", required = false) String starttime,
            @RequestParam(value = "endtime", required = false) String endtime) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        List<Map<String, Object>> list = jls_SwjyService.swjyCount(jsbh,starttime,endtime);
	        result.put("swjy", list);
	        return ResponseMessage.ok(result);
    	}catch (Exception e){
			e.printStackTrace();
			return ResponseMessage.error("执行失败");
    	}
    }
    
    @GetMapping("/jlsdpylgl")
    @ApiOperation("大屏医疗管理")
    @OpenAPI
    public Map<String, Object> dpylgl( @RequestParam(value = "jsbh", required = false) String jsbh) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        Map<String, Object> results = new HashMap<String, Object>();
	        Map<String, Object> map = jls_SwjyService.dpylgl(jsbh);
	        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	        for (String key : map.keySet()) {
				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("name", key);
				maps.put("msg", map.get(key));
				list.add(maps);
			}
	        result.put("list", list);
	        Map<String, Object> data = new HashMap<String, Object>();
	        data.put("ylgl", result);
	        results.put("msg", "查询成功");
	        results.put("code", 200);
	        results.put("data", data);
	        return results;
    	}catch (Exception e){
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("aqjc", "");
	        result.put("code", 500);
	        result.put("msg", "查询失败");
			return result;
    	}
    }
    
    @GetMapping("/jlsdpjqbb")
    @ApiOperation("大屏医疗管理")
    @OpenAPI
    public Map<String, Object> dpjqbb( @RequestParam(value = "jsbh", required = false) String jsbh) {
    	try {
	        Map<String, Object> result = new HashMap<String, Object>();
	        Map<String, Object> results = new HashMap<String, Object>();
	        List<Map<String, Object>> map = jls_SwjyService.dpjqbb(jsbh);
	        results.put("msg", "查询成功");
	        results.put("code", 200);
	        results.put("data", map);
	        return results;
    	}catch (Exception e){
			e.printStackTrace();
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("data", "");
	        result.put("code", 500);
	        result.put("msg", "查询失败");
			return result;
    	}
    }
}