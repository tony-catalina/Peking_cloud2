package awd.mis.servers.api.hystrix;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.ManagerServersApi;
import awd.mis.servers.vo.User;
import feign.hystrix.FallbackFactory;

/**
 * @Description
 * @Author WS
 * @Date 2019-09-17 14:21
 */
@Component
public class ManagerApiFallBackFactory implements FallbackFactory<ManagerServersApi> {

    Logger logger = LoggerFactory.getLogger(ManagerServersApi.class);

    @Override
    public ManagerServersApi create(Throwable cause) {
        return new ManagerServersApi() {

			@Override
			public ResponseMessage<Map<String, Object>> getUserByName(String jsbh, String username) {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }
}
