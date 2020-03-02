package awd.cloud.suppers.interfaces.controller.jls;

import java.util.Map;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/jls/jbxx")
@AccessLogger("jls")
@Api(value = "jls")
public class JbxxController {

	@Autowired
	private JbxxService jbxxService;
	
	@OpenAPI
	@ApiOperation(value="自定义查询")
	@RequestMapping(value = "/getJbxx",method = {RequestMethod.GET,RequestMethod.POST})
	public ResponseMessage<PagerResult<Map<String, Object>>> getJbxx() {
		
		return jbxxService.getJbxx();
	}
	
	@OpenAPI
	@ApiOperation(value="保存数据,如果数据不存在则新增一条数据")
	@RequestMapping(value = "/saveJbxx",method = {RequestMethod.POST})
	public ResponseMessage<String> saveJbxx() {
		
		Map<String,Object> map = Maps.newHashMap();

		return jbxxService.saveJbxx(map);
	}
	
	@OpenAPI
	@ApiOperation(value="根据id更新")
	@RequestMapping(value = "/updateJbxxById",method = {RequestMethod.POST})
	public ResponseMessage<String> updateJbxxById() {

		String id = "";
		Map<String,Object> map = Maps.newHashMap();

		return jbxxService.updateJbxxById(id,map);
	}
}
