package awd.cloud.suppers.interfaces.controller.manager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import awd.cloud.suppers.interfaces.service.jls.JbxxService;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;

@RestController
@RefreshScope
@RequestMapping("/interface/jbxx")
@AccessLogger("jls")
@Api(value = "jls") 
public class UserController {

	@Autowired
	private JbxxService jbxxService;
	
	@OpenAPI
	@RequestMapping(value = "/getJbxx",method = {RequestMethod.POST})
	public ResponseMessage<PagerResult<Map<String, Object>>> getJbxx() {
		
		return jbxxService.getJbxx();
	}
	
	@OpenAPI
	@RequestMapping(value = "/saveJbxx",method = {RequestMethod.POST})
	public ResponseMessage<String> saveJbxx() {
		
		Map<String,Object> map = Maps.newHashMap();
		map.put("xm", "map测试");
		map.put("jsbh", "110000121");
		map.put("rybh", "00000000000000000");
		
		return jbxxService.saveJbxx(map);
	}
	
	@OpenAPI
	@RequestMapping(value = "/updateJbxxById",method = {RequestMethod.POST})
	public ResponseMessage<String> updateJbxxById() {
		
		Map<String,Object> map = Maps.newHashMap();
		map.put("xm", "map测试");
		map.put("jsbh", "110000121");
		map.put("rybh", "00000000000000000");
		
		String id = "00000000001";
		
		return jbxxService.updateJbxxById(id,map);
	}
}
