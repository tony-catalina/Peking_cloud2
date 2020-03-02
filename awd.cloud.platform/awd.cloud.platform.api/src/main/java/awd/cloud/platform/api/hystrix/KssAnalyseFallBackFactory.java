package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.KssAnalyseApis;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KssAnalyseFallBackFactory implements FallbackFactory<KssAnalyseApis> {
    public static Logger logger = Logger.getLogger(KssAnalyseApis.class);

    @Override
    public KssAnalyseApis create(Throwable throwable) {
        return new KssAnalyseApis() {

            @Override
            public ResponseMessage<Map<String, Object>> xjYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> xzpaYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> zdryYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> ldjfYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> jcjlYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> emYwdt(String jsbh, String jqh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> cfglYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> wlryYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> yqYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public Map<String, Object> queryLsp(String jsbh) {
                return null;
            }

            @Override
            public ResponseMessage<Map<String, Object>> syrsYwdt(String jsbh, String startTime, String endTime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> clcsYwdt(String jsbh, String startTime, String endTime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> lscsYwdt(String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> jwzxjlYwdt(String jsbh, String startTime, String endTime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> tjYwdt(String jsbh, String startTime, String endTime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> hjbdYwdt(String jsbh, String startTime, String endTime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String, Object>> pjdjYwdt(String jsbh, String starttime, String endtime) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("看守所服务调用失败");
            }

        };
    }
}
