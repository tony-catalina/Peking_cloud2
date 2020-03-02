package awd.cloud.suppers.interfaces.api.hystrix;

import awd.cloud.suppers.interfaces.utils.PagerResult;
import awd.cloud.suppers.interfaces.utils.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import awd.cloud.suppers.interfaces.api.JlsServersApi;
import awd.cloud.suppers.interfaces.api.KssCoreServersApi;
import awd.cloud.suppers.interfaces.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;

import java.util.Map;

/**
 * @Description
 * @Date 2019-11-15
 */
@Component
public class KssCoreServersApiFallBackFactory implements FallbackFactory<KssCoreServersApi> {
    Logger logger = LoggerFactory.getLogger(JlsServersApi.class);

    @Override
    public KssCoreServersApi create(Throwable throwable) {
        return new KssCoreServersApi() {
            @Override
            public ResponseMessage<String> test() {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> queryZrap(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> queryPwgl(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getThjl(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveThjl(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateThjlById(String id,Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getJyjl(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveJyjl(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateJyjlById(String id,Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getSpdetailorder(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveSpdetailorder(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateSpdetailorderById(String id, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getSporder(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveSporder(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateSporderById(String id, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getSpxx(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveSpxx(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateSpxxById(String id, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getYy(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveYy(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateYyById(String id, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getWgjl(QueryParam param) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> saveWgjl(Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }

            @Override
            public ResponseMessage<String> updateWgjlById(String id, Map<String, Object> map) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return null;
            }
        };
    }
}