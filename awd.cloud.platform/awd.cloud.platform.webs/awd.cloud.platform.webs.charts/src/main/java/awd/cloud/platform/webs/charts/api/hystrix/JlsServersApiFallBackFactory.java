package awd.cloud.platform.webs.charts.api.hystrix;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import awd.bj.base.model.Variables;
import awd.cloud.platform.webs.charts.api.JlsServersApi;
import awd.cloud.platform.webs.charts.modal.jls.FlwsbgModel;
import awd.cloud.platform.webs.charts.modal.jls.JbxxModel;
import awd.cloud.platform.webs.charts.modal.jls.PhotosModel;
import awd.cloud.platform.webs.charts.modal.jls.SwjyModel;
import awd.cloud.platform.webs.charts.modal.jls.XsjlModel;
import awd.cloud.platform.webs.charts.utils.PagerResult;
import awd.cloud.platform.webs.charts.utils.QueryParam;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

/**
 * @Description
 * @Author WS
 * @Date 2019-09-04 13:57
 */
@Component
public class JlsServersApiFallBackFactory implements FallbackFactory<JlsServersApi> {
    Logger logger = LoggerFactory.getLogger(JlsServersApi.class);

    @Override
    public JlsServersApi create(Throwable throwable) {
        return new JlsServersApi() {

			@Override
			public ResponseMessage<PagerResult<JbxxModel>> jbxxQueryForPage(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<XsjlModel>> xsjlQueryForPage(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<PhotosModel>> photosQueryForPage(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<FlwsbgModel>> flsxbgQueryForPage(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

			@Override
			public ResponseMessage<PagerResult<SwjyModel>> swjyQueryForPage(QueryParam param) {
				throwable.printStackTrace();
				logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
				return ResponseMessage.ok();
			}

            };


        };
}
