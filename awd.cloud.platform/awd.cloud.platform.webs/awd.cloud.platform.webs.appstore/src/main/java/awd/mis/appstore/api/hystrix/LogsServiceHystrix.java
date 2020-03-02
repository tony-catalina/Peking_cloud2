package awd.mis.appstore.api.hystrix;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import awd.mis.appstore.api.LogsService;
import awd.mis.appstore.model.PlModel;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import feign.hystrix.FallbackFactory;

@Service("logsService")
public class LogsServiceHystrix implements FallbackFactory<LogsService> {
	public static Logger logger = Logger.getLogger(LogsService.class);
	@Override
    public LogsService create(Throwable throwable) {

        return new LogsService() {

	
	@Override
	public Map<String, Object> upload(MultipartFile file, String jsbh, String dir, String rybh, String share,
			String updator) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return null;
	}

	@Override
	public ResponseMessage<PagerResult<PlModel>> getPlEntyListByAppcode(QueryParam queryParam) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return ResponseMessage.ok();
	}

	@Override
	public ResponseMessage<String> savePl(PlModel plModel) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return ResponseMessage.ok();
	}

	@Override
	public ResponseMessage<String> updatePlById(String id, PlModel plModel) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return ResponseMessage.ok();
	}

	@Override
	public ResponseMessage<String> deletePlById(String id) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return ResponseMessage.ok();
	}

	@Override
	public ResponseMessage<String> removePlById(String id) {
		throwable.printStackTrace();
        logger.info("进入熔断器=======>>"+ Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
        return ResponseMessage.ok();
	}
};
}
}
