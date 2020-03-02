package awd.mis.servers.api.hystrix;

import java.util.List;
import org.springframework.stereotype.Service;
import awd.mis.servers.api.ActivitiService;
import awd.mis.servers.entity.RequestParameters;
import awd.mis.servers.tools.R;
@Service("activitiService")
public class ActivitiServiceHystrix implements ActivitiService{
	
	
	@Override
	public List<RequestParameters> flowSynchronization(String jsbh) {
		return null;
	}

	@Override
	public R setProcessNumCache(String userId, String roleIds, String jsbh) {
		return null;
	}

}
