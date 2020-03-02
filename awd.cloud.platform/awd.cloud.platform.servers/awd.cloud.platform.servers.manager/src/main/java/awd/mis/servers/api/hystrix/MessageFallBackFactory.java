package awd.mis.servers.api.hystrix;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.MessageService;
import awd.mis.servers.entity.ApplicationMsg;
import awd.mis.servers.entity.ApplicationMsgList;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MessageFallBackFactory implements FallbackFactory<MessageService> {
    public static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Override
    public MessageService create(Throwable cause) {
        if(cause.getMessage()!=null) {
            cause.printStackTrace();
            logger.info("熔断错误的具体信息: {} " ,cause.getMessage());
        }
        return new MessageService() {
            @Override
            public ResponseMessage sendMsgJson(String msgtype,String message) {
                logger.info("进入熔断器：===========》》》》"+Thread.currentThread().getStackTrace()[1].getMethodName()+"方法执行出错");
                return ResponseMessage.ok();

            }

            @Override
            public ResponseMessage sendMsg(ApplicationMsg applicationMsg) {
                logger.info("进入熔断器：===========》》》》"+Thread.currentThread().getStackTrace()[1].getMethodName()+"方法执行出错");
                return ResponseMessage.ok();
            }

            @Override
            public ResponseMessage sendMessageList(ApplicationMsgList applicationMsgList) {
                logger.info("进入熔断器：===========》》》》"+Thread.currentThread().getStackTrace()[1].getMethodName()+"方法执行出错");
                return ResponseMessage.ok();
            }
        };
    }
}
