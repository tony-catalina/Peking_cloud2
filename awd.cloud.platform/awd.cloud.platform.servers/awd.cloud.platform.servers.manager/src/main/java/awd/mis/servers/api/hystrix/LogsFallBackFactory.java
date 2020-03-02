package awd.mis.servers.api.hystrix;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.LogsService;
import awd.mis.servers.model.HfModel;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("logsService")
public class LogsFallBackFactory implements FallbackFactory<LogsService> {
    public static final Logger logger = LoggerFactory.getLogger(LogsService.class);

    @Override
    public LogsService create(Throwable cause) {

        return new LogsService() {

            @Override
            public ResponseMessage<String> saveHf(HfModel hf1) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return ResponseMessage.error("进入熔断器了");
            }

            @Override
            public ResponseMessage<String> upload(byte[] file, String fileName, String ext) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return ResponseMessage.error("进入熔断器了");
            }

            @Override
            public ResponseMessage<Map<String, String>> upload(Map<String, byte[]> file, String fileName, String ext) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return ResponseMessage.error("进入熔断器了");
            }


        };
    }

}
