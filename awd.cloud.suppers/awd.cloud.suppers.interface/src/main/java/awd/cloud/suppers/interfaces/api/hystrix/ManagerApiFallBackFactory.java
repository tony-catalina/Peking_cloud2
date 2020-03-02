package awd.cloud.suppers.interfaces.api.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import awd.cloud.suppers.interfaces.api.ManagerApi;
import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

/**
 * @Description
 * @Date 2019-11-15 
 */
@Component
public class ManagerApiFallBackFactory implements FallbackFactory<ManagerApi> {

    Logger logger = LoggerFactory.getLogger(ManagerApi.class);

    @Override
    public ManagerApi create(Throwable cause) {
        return new ManagerApi() {
            @Override
            public ResponseMessage<String> test() {
                cause.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("查询出错！");
            }

			@Override
			public ResponseMessage<PagerResult<UserinfoEntity>> queryUser(QueryParam param) {
				cause.printStackTrace();
				logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.error("查询出错！");
			}
        };
    }
}
