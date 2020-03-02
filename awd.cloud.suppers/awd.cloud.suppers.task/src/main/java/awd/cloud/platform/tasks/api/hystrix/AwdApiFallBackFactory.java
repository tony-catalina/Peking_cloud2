package awd.cloud.platform.tasks.api.hystrix;

import awd.cloud.platform.tasks.api.AwdApi;
import awd.cloud.platform.tasks.model.DictionaryModel;
import awd.cloud.platform.tasks.model.OperatelogsModel;
import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.utils.ResponseMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

import java.util.List;

/**
 * @program: awd.bj.kss
 * @description:
 * @author: WS
 * @create: 2019-01-22
 **/
@Component
public class AwdApiFallBackFactory implements FallbackFactory<AwdApi> {

	public static Logger logger = Logger.getLogger(AwdApi.class);

	@Override
	public AwdApi create(Throwable throwable) {

		return new AwdApi() {

			@Override
			public ResponseMessage<User> getUserByName(String jsbh, String username) {
				throwable.printStackTrace();
		        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
		        return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<List<DictionaryModel>> getByField(String type) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<String> operatelogsSave(OperatelogsModel operatelogs) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}
		};
	}
}
