package awd.cloud.platform.api.hystrix;

import awd.cloud.platform.api.JwpServerApis;
import awd.cloud.platform.model.jwp.*;
import awd.cloud.platform.utils.PagerResult;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("JwpServerApis")
public class JwpServerFallBackFactory implements FallbackFactory<JwpServerApis> {

    public static Logger logger = Logger.getLogger(JwpServerApis.class);

    @Override
    public JwpServerApis create(Throwable throwable) {
        return new JwpServerApis() {
            @Override
            public ResponseMessage<String> zdrySave(ZdryModel zdryModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> spdbkQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> yyysForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> rybhQuery(String rybh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> saveJskp(JskpModel jskpModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> getJskp(QueryParam queryParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

			@Override
			public ResponseMessage<PagerResult<Map<String,Object>>> queryQlywForPage(QueryParam arg1) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
			}

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> yrzxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> nptgByjbxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xlpcByjbxxQueryForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jsdmQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xxjyForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> jsjgForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> fyqrForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }


            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> yzspForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> yyysSave(YyysModel yyysModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> zbqdSave(ZbqdModel zbqdModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zbbForPage(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> zzsqQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> jyyySave(JyyyModel jyyyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> hjyySave(HjyyModel hjyyModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> fyqrSave(FyqrModel fyqrModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> ksdjSave(KsdjModel ksdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<Map<String,Object>> xlpcSave(XlpcModel xlpcModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xlpctmQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<PagerResult<Map<String, Object>>> xlpcsjQuery(QueryParam qParam) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> spdbSave(SpdbModel spdbModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> mrxjSave(MrxjModel mrxjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> jsdmSave(JsdmModel jsdmModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> thjlSave(ThjlModel thjlModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public Map<String, Object> fxdjCxQuery(String jsbh, String jsh) {
                return null;
            }

            @Override
            public ResponseMessage<List<Map<String, Object>>> yzspQuery(String jsbh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public Map<String, Object> ryxxQuery(String jsbh, String jsh) {
                return null;
            }

            @Override
            public ResponseMessage<Map<String, Object>> getMoreCustom(String jsbh, String type, String jsh) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> xjSave(XjModel xjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> yjdjSave(YjdjModel YjdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }

            @Override
            public ResponseMessage<String> ybdjSave(YbdjModel YbdjModel) {
                throwable.printStackTrace();
                logger.info("进入熔断器=======>>" + Thread.currentThread().getStackTrace()[1].getMethodName() + "方法执行出错");
                return ResponseMessage.error("监外屏服务调用失败");
            }
        };
    }
}
