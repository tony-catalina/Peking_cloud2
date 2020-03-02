package awd.mis.servers.service;

import awd.mis.servers.entity.ApplicationMsgList;
import org.springframework.stereotype.Component;

import awd.mis.servers.entity.ApplicationMsg;
import awd.mis.servers.entity.MessageContent;
import awd.mis.servers.enums.QueueEnum;

/**
 * @Description
 * @Author WS
 * @Date 2019-04-30 17:16
 */
@Component
public interface SendMessage {
    /**
     * description: 消息发送
     *
     * @return void
     * @params applicationMsg 消息体
     * exchangeName 交换机名称
     * queueKey 队列绑定key值
     * @author by: WS
     * @createtime: 2019/12/4
     */
    void sendMessage(ApplicationMsg applicationMsg, String exchangeName, String queueKey);

    /**
     * 消息发送
     * @param applicationMsg
     */
	void sendMessage(String msgtype,String message);
	
	/**
	 * 消息发送
	 * @param applicationMsg
	 */
	void sendMessage(ApplicationMsg applicationMsg);

	/**
	 * 直接私发消息给awd的应用
	 * @param messageContent
	 */
	void sendMessageToAwdApp(QueueEnum receiver,MessageContent messageContent);

	void sendMessage(ApplicationMsgList applicationMsgList);
}
