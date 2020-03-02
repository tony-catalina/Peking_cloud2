package awd.mis.servers.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.api.ActivitiService;
import awd.mis.servers.entity.RequestParameters;
import awd.mis.servers.service.FlowmapService;
import awd.mis.servers.tools.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RefreshScope
@RequestMapping("/activiti")
@AccessLogger("流程信息")
@Api(tags = "awd-activiti", description = "提供流程管理功能")
public class InitActivitiCacheController {
	@Autowired
	private ActivitiService  activitiService;
	@Autowired
	private FlowmapService flowmapService;
	//写两个方法 
	/**
	 * 1、缓存流程节点的接口
	 * 		
	 * 2、缓存流程节点代办数量的接口
	 */
	@OpenAPI()
	@ApiOperation("缓存流程节点")
	@RequestMapping(value="/saveFlowMapNodeCache" ,method = RequestMethod.POST)
	public  ResponseMessage updateFlow(@RequestParam(value = "jsbh",required=false) String jsbh) {
		System.out.println(jsbh);
		List<RequestParameters> r=activitiService.flowSynchronization(jsbh);
		System.out.println(r);
		 for(RequestParameters p:r) {		
				/*
				 * flowMap id过长  
				 * WorkFlowService flowSynchronization获取流程节点信息
				 * flowmapService.updateFlowMapFlowNodeCache保存节点信息到flowMap flowNode表
				 * */
				flowmapService.updateFlowMapFlowNodeCache(p);
		 }
		System.out.println(r);
		
		return ResponseMessage.ok();
	}
	@OpenAPI()
	@ApiOperation("缓存流程节点代办数量")
	@RequestMapping(value="/saveProcessNumCache" ,method = RequestMethod.POST)
	public R setProcessNumCache(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "roleIds") String roleIds,
            @RequestParam(name = "jsbh") String jsbh) {
		System.out.println(userId);
		System.out.println(roleIds);
		System.out.println(jsbh);
		return activitiService.setProcessNumCache(userId, roleIds, jsbh);
	}
}
