package awd.mis.servers.api;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import awd.mis.servers.api.hystrix.ActivitiServiceHystrix;
import awd.mis.servers.config.FeignSupportConfig;
import awd.mis.servers.entity.RequestParameters;
import awd.mis.servers.tools.R;


@FeignClient(name = "${awd.api.activiti:AWD-MIS-ACTIVITI-SERVER}",configuration = FeignSupportConfig.class,fallback=ActivitiServiceHystrix.class)
public interface ActivitiService {
	//现有流程信息全部保存到我们自己的表中和Redis缓存
    @RequestMapping(value = "/workflow/flowMapUpdate" ,method = RequestMethod.POST)
    public List<RequestParameters> flowSynchronization(@RequestParam(value = "jsbh" ,required=false) String jsbh);
    //流程待办数量缓存
    @GetMapping("/workflow/setProcessNumCache")
    public R setProcessNumCache(@RequestParam(name = "userId") String userId,
    							@RequestParam(name = "roleIds") String roleIds,
                                @RequestParam(name = "jsbh") String jsbh);
}
