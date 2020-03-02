//package awd.mis.servers.api.hystrix;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import awd.mis.servers.api.ActivitiService;
//import awd.mis.servers.entity.RequestParameters;
//import awd.mis.servers.tools.R;
//import feign.hystrix.FallbackFactory;
//
//@Service("activitiService")
//public class ActivitiFallBackFactory implements FallbackFactory<ActivitiService> {
//	public static final Logger logger = LoggerFactory.getLogger(ActivitiService.class);
//
//	@Override
//	public ActivitiService create(Throwable cause) {
//		cause.printStackTrace();
//		logger.info("熔断错误的具体信息: {} " ,cause.getMessage());
//		return new ActivitiService() {
//
//			@Override
//			public List<RequestParameters> flowSynchronization(String jsbh) {
//				return null;
//			}
//
//			@Override
//			public R setProcessNumCache(String userId, String roleIds, String jsbh) {
//				return null;
//			}
//			
//		};
//	}
//
//}
