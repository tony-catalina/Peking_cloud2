package awd.mis.servers.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.entity.ApplicationMsgList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import awd.mis.servers.config.AwdMqInit;
import awd.mis.servers.entity.ApplicationMsg;
import awd.mis.servers.entity.MessageContent;
import awd.mis.servers.enums.QueueEnum;
import awd.mis.servers.service.SendMessage;
import awd.mis.servers.utils.AwdMqUtils;

@Service("sendMessage")
public class SimpleSendMessage implements SendMessage {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    Logger logger = LoggerFactory.getLogger(SimpleSendMessage.class);

    @Override
    public void sendMessage(String msgtype,String message) {
    	logger.info("消息内容：{}", message);
    	String exchange = AwdMqInit.get().getDefaultfanout();
    	try {
    		AwdMqUtils.get().sendMessage(exchange, msgtype, message);
    	} catch (IOException e) {
    		e.printStackTrace();
    		msgBuild(exchange, msgtype, message);
    	}
    }
    
    @Override
    public void sendMessage(ApplicationMsg applicationMsg) {
        logger.info("消息内容：{}", JSONObject.toJSONString(applicationMsg));
        String exchange = AwdMqInit.get().getDefaultfanout();
        String routingkey = applicationMsg.getProduce() +
                "_" + applicationMsg.getClassify() +
                "_" + applicationMsg.getRole() +
                "_" + applicationMsg.getBusinessid();
        try {
            AwdMqUtils.get().sendMessage(exchange, routingkey, applicationMsg.getMessageContent().toString());
        } catch (IOException e) {
            e.printStackTrace();
            msgBuild(exchange, routingkey, JSONUtil.toJson(applicationMsg));
        }
    }

    @Override
    public void sendMessageToAwdApp(QueueEnum receiver, MessageContent messageContent) {
        logger.info("消息内容：{}", JSONObject.toJSONString(messageContent));
        String exchange = AwdMqInit.get().getAwdexchange();
        String routingkey = "";
        try {
            AwdMqUtils.get().sendMessage(exchange, routingkey, messageContent.toString());
        } catch (IOException e) {
            msgBuild(exchange, routingkey, messageContent.toString());
        }
    }

    @Override
    public void sendMessage(ApplicationMsgList applicationMsgList) {
        logger.info("消息内容：{}", JSONObject.toJSONString(applicationMsgList));
        String exchange = AwdMqInit.get().getDefaultfanout();
        String routingkey = applicationMsgList.getProduce() +
                "_" + applicationMsgList.getClassify() +
                "_" + applicationMsgList.getRole() +
                "_" + applicationMsgList.getBusinessid();
        try {
            for (MessageContent messageContent : applicationMsgList.getMessageContent()) {
                ApplicationMsg applicationMsg = new ApplicationMsg();
                BeanUtils.copyProperties(applicationMsgList, applicationMsg,"messageContent");
                applicationMsg.setMessageContent(messageContent);
                AwdMqUtils.get().sendMessage(exchange, routingkey, JSONUtil.toJson(applicationMsg));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void sendMessage(ApplicationMsg applicationMsg, String exchangeName, String queueKey) {
        logger.info("消息内容：{}", JSONObject.toJSONString(applicationMsg));
        msgBuild(exchangeName, queueKey, applicationMsg.getMessageContent().toString());
    }

    private void msgBuild(String exchangeName, String queueRoutingKey, String messageContent) {
        try {
            String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase() + System.currentTimeMillis();
            CorrelationData correlationData = new CorrelationData(uuid);
            rabbitTemplate.convertAndSend(exchangeName, queueRoutingKey, messageContent, correlationData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationMsgList applicationMsgList = new ApplicationMsgList();
        applicationMsgList.setBusinessid("sdsd");
        applicationMsgList.setMsgId("21212");
        applicationMsgList.setProduce("sdsdsd");
        applicationMsgList.setClassify("22323");
        ApplicationMsg applicationMsg = new ApplicationMsg();
        MessageContent messageContent = new MessageContent();
        messageContent.setContent("sdsdsdsdsdsd");
        List<MessageContent> li = new ArrayList<MessageContent>();
        li.add(messageContent);
        applicationMsgList.setMessageContent(li);
        System.err.println(JSONUtil.toJson(applicationMsgList));

        BeanUtils.copyProperties(applicationMsgList, applicationMsg);
        System.err.println(JSONUtil.toJson(applicationMsg));
    }

}
