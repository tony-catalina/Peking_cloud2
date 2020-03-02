package awd.cloud.platform.webs.charts.api.hystrix;

import awd.cloud.platform.webs.charts.api.ManagerApi;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@Component
public class ManagerFallBackFactory implements FallbackFactory<ManagerApi> {
	
	public static Logger logger = Logger.getLogger(ManagerApi.class);
	@Override
    public ManagerApi create(Throwable throwable) {

        return new ManagerApi() {
     @Override
    public ResponseMessage<List<Map<String,String>>> getUsers(String jsbh) {
         throwable.printStackTrace();
         logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
         return ResponseMessage.ok();
    }
};
	}
}
