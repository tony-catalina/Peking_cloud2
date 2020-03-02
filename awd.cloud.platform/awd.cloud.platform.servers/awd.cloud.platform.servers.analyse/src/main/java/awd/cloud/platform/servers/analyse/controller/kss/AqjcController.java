package awd.cloud.platform.servers.analyse.controller.kss;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import awd.cloud.platform.servers.analyse.service.kss.Kss_AqjcService;
import awd.framework.common.utils.OpenAPI;

@RestController
@RequestMapping("kss/aqjc")
@Api(tags = "kss_aqjc",description = "安全检查")
public class AqjcController {
	@Autowired
	private Kss_AqjcService kss_AqjcService;
	
	@GetMapping("/rqfx/{jsbh}")
	@ApiOperation("安全检查")
	@OpenAPI
	public Map<String, Object> aqjc_rq(@PathVariable(value="jsbh") String jsbh){
		Map<String, Object> result=new HashMap<String, Object>();
		List<Map<String, Object>> list= kss_AqjcService.aqjc_rqFx(jsbh);
		if(list==null) {
			result.put("state", 400);
			result.put("data", null);
			result.put("msg", "分析失败");
		}else {
			result.put("state", 200);
			result.put("data", list);
			result.put("msg", "分析成功");
		}
		
		return result;		
	}
	

}
