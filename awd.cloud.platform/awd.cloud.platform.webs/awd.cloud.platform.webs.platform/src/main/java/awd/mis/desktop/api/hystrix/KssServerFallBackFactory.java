package awd.mis.desktop.api.hystrix;

import awd.mis.desktop.api.KssServerService;
import awd.mis.desktop.model.FjszModel;
import awd.mis.desktop.model.JqModel;
import awd.mis.desktop.model.JsModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author WS
 * @Description:
 * @date 2020/1/6 下午1:43
 */
@Service("kssServerService")
public class KssServerFallBackFactory implements FallbackFactory<KssServerService> {


    public static final Logger logger = LoggerFactory.getLogger(KssServerService.class);

    @Override
    public KssServerService create(Throwable cause) {
        return new KssServerService() {

            /**
             * @param params
             *******************************************************************/
            @Override
            public ResponseMessage<PagerResult<FjszModel>> fjszQuery(QueryParam params) {
                return null;
            }

            @Override
            public ResponseMessage<String> fjszSave(FjszModel fjsz) {
                return null;
            }

            @Override
            public ResponseMessage<String> fjszUpdate(String id, FjszModel fjsz) {
                return null;
            }

            @Override
            public ResponseMessage<String> fjszDelete(String id) {
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JqModel>> jqQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jqSave(JqModel jq) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jqDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<PagerResult<JsModel>> jsQuery(QueryParam params) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jsUpdate(String id, JsModel js) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jsSave(JsModel js) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jsDelete(String id) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }

            @Override
            public ResponseMessage<String> jqUpdate(String id, JqModel jq) {
                cause.printStackTrace();
                logger.info("熔断错误的具体信息: {} ", cause.getMessage());
                return null;
            }


        };
    }

}
