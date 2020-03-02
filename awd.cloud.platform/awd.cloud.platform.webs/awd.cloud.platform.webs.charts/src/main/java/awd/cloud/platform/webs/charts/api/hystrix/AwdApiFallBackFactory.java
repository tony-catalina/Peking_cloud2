package awd.cloud.platform.webs.charts.api.hystrix;

import awd.cloud.platform.webs.charts.modal.DictionaryModel;
import awd.cloud.platform.webs.charts.modal.JbxxModelDO;
import awd.cloud.platform.webs.charts.modal.OperatelogsModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import awd.bj.base.model.Variables;
import awd.cloud.platform.webs.charts.api.AwdApi;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

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

			@Override
			public ResponseMessage<PagerResult<JbxxModelDO>> kss_queryJbxxForPage(QueryParam queryParam) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<Long> kss_queryJbxxCount(String jsbh) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<List<Map<String, Object>>> kss_queryJbxxCountByField(String jsbh, String field,
					String value) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<JbxxModelDO>> kss_getListCustom(Variables variables) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<Long> kss_queryTaskCount(String jsbh, String flow, String node) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}


		};
	}
}
