package awd.mis.servers.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * @Description 消息发送到交换机确认机制
 * @Author WS
 * @Date 2019/3/14
 * @Param
 * @Return
 */
@Component
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    Logger logger = LoggerFactory.getLogger(MsgSendConfirmCallBack.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送到exchange成功,id: {}", correlationData.getId());
        } else {
            logger.info("消息发送到exchange失败,原因: {}", cause);
        }
    }
}