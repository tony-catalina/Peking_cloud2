package awd.cloud.platform.servers.analyse.controller.jls;

import awd.cloud.platform.servers.analyse.service.jls.Jls_JbxxService;
import awd.cloud.platform.servers.analyse.service.jls.Jls_JkqkService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jls_jkqk")
@RefreshScope
@Api(tags = "jls_jkqk",description = "健康情况")
public class Jls_JkqkController {
    @Autowired
    private Jls_JkqkService jls_jkqkService;

	@GetMapping("/jkqkCount")
	@ApiOperation("拘留所人员身体状况")
	@OpenAPI
	public Map<String, Object> jkqkCount(@RequestParam(value = "jsbh",required = false)String jsbh,@RequestParam(value = "rybh", required = false) String rybh) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("fgsf="+rybh);
			System.out.println("ghjh="+jsbh);
			Map map2=new HashMap();
			Map map3=new HashMap();
			Map map4=new HashMap();
			Map map5=new HashMap();
			Map<String, Object> map1 = jls_jkqkService.jkqkCount(jsbh,rybh);
			if (map1!=null||map1.size()>0||!map1.isEmpty()){
				if (map1.get("wss").toString()!=null){
					String wss = map1.get("wss").toString();
					map2.put("name","体表伤情");
					map2.put("value",wss);
				}else {
					map2.put("name","体表伤情");
					map2.put("value","暂未记录");
				}
				if (map1.get("jkzk").toString()!=null){
					String jkzk = map1.get("jkzk").toString();
					map3.put("name","身体状况");
					map3.put("value",jkzk);
				}else {
					map3.put("name","身体状况");
					map3.put("value","暂未记录");
				}
				if (map1.get("xl").toString()!=null){
					String xl = map1.get("xl").toString();
					map4.put("name","心跳");
					map4.put("value",xl);
				}else {
					map4.put("name","心跳");
					map4.put("value","暂未记录");
				}
				if (map1.get("xy").toString()!=null){
					String xy = map1.get("xy").toString();
					map5.put("name","血压");
					map5.put("value",xy);
				}else {
					map5.put("name","血压");
					map5.put("value","暂未记录");
				}
			}else {
				map2.put("name","体表伤情");
				map2.put("value","暂未登记体表伤情情况");
				map3.put("name","身体状况");
				map3.put("value","暂未登记身体状况");
				map4.put("name","心跳");
				map4.put("value","暂未登记心跳状况");
				map5.put("name","血压");
				map5.put("value","暂未登记血压状况");
			}
			List list=new ArrayList();
			list.add(map2);
			list.add(map3);
			list.add(map4);
			list.add(map5);
			map.put("list",list);
			map.put("name","身体状况");
			return map;
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