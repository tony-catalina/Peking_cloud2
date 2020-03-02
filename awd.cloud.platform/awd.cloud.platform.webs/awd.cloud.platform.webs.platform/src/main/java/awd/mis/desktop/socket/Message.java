package awd.mis.desktop.socket;

import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.rabbitmq.enums.ExchangeEnum;
import awd.framework.expands.rabbitmq.enums.QueueEnum;
import awd.framework.expands.rabbitmq.model.KssMessage;
import awd.framework.expands.rabbitmq.send.SendMessage;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.model.OperatelogsModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.tools.*;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @program: awd.cloud
 * @description:
 * @author: WS
 * @create: 2019-03-15
 **/

@Component
public class Message {

    Logger logger = LoggerFactory.getLogger(Message.class);

    @Autowired
    private ManagerService managerService;
    @Autowired
    private SendMessage sendMessage;
    @Autowired
    private MyWebSocket myWebSocket;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitHandler
    @RabbitListener(queues = "awd.msg.instancy")
    public void handleMessage(@Payload KssMessage kssMessage,
                              Channel channel,
                              @Headers Map<String, Object> headers) throws Exception {

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        boolean flag;//= true;
        String messageId = kssMessage.getMsgId();

        if (kssMessage == null) {
            return;
        }

        boolean allUser = kssMessage.isAllUsers();
        boolean jsUser = kssMessage.isJsUsers();
        String jsbh = kssMessage.getJsbh();

        QueryParam queryParam = new QueryParam();
        queryParam.setPaging(false);

        try {

            if (StringUtils.isNullOrEmpty(jsbh) && allUser == false) {
                //todo 处理错误数据！
                return;
            }
            queryParam.and("state", "R2");
            List<UserinfoModel> userInfoModelList = Lists.newArrayList();
            //消息针对所有用户 type = allUser
            if (allUser) {
                queryParam.and("usertype", TermType.nin, "8,9");
                ResponseMessage<PagerResult<UserinfoModel>> userResult = managerService.userInfoQuery(queryParam);
                userInfoModelList = userResult.getResult().getData();

                logger.info("获取到用户信息：{}", JSONObject.toJSONString(userInfoModelList));
                flag = sendUserMessage(userInfoModelList, kssMessage, "allUser", messageId);
            }
            //监所所有用户 type = jsUser
            else if (jsUser) {
                queryParam.and("jsbh", jsbh);
                ResponseMessage<PagerResult<UserinfoModel>> userResult = managerService.userInfoQuery(queryParam);
                userInfoModelList = userResult.getResult().getData();
                flag = sendUserMessage(userInfoModelList, kssMessage, "jsUser", messageId);

            }
            //监所角色 type = jsRole
            else if (!"".equals(CacheUtils.get().getRole(kssMessage.getRole()))) {

                ResponseMessage<List<UserinfoModel>> result = managerService.getUserByRole(kssMessage.getJsbh(), kssMessage.getRole());
                userInfoModelList = result.getResult();
                flag = sendUserMessage(userInfoModelList, kssMessage, "jsRole", messageId);

            }
            //指定用户 type = owner
            else {
                flag = sendUserMessage(userInfoModelList, kssMessage, "owner", messageId);
            }
            //手工ACK
            if (flag) {
                channel.basicAck(deliveryTag, false);
                logger.info("消息消费成功，消息手动确认完成,消息ID {} ", messageId);
            } else {
                channel.basicAck(deliveryTag, false);
                logger.info("消息弹窗失败保存进入数据库，数据存入数据库并舍弃消息,消息ID {} ", messageId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicAck(deliveryTag, false);
            CacheUtils.get().setBadMsg(kssMessage);
            logger.info("消息消费失败，数据存入数据库并存入Redis等待下一步处理,消息ID {} ", kssMessage.getMsgId());
        }

    }

    private boolean sendUserMessage(List<UserinfoModel> userinfoModelList, KssMessage kssMessage, String type, String messageId) {
        try {
            //单个消息
            if ("owner".equals(type)) {
                boolean flag = myWebSocket.sendUserMessage(kssMessage);
                return true;
            }
            String jsbh = kssMessage.getJsbh();

            for (UserinfoModel userinfoModel : userinfoModelList) {
                boolean flag = myWebSocket.sendUserMessage(kssMessage);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(OperatelogsModel operatelogsModel) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase() + System.currentTimeMillis();
        CorrelationData correlationData = new CorrelationData(uuid);
        String message = JSONUtil.toJson(operatelogsModel);
        this.rabbitTemplate.convertAndSend(ExchangeEnum.AWD_DIRECT_EXCHANGE.getName(), QueueEnum.AWD_REGISTER_LOGS_MSG.getRoutingKey(), message, correlationData);
    }

    public String sendInstancyMessage(@RequestParam("jsbh") String jsbh, @RequestParam("jsr") String jsr,
                                      @RequestParam("jsrbh") String jsrbh, @RequestParam("msg") String content)
            throws UnsupportedEncodingException {
        KssMessage kssMessage = new KssMessage();
        KssMessage messageModel = new KssMessage();
        messageModel.setLevel("1");
        messageModel.setJsbh(jsbh);
        messageModel.setReceiver(jsr);
        messageModel.setReceiverIdentifier(jsrbh);
        messageModel.setContent(content.getBytes("utf-8"));
        sendMessage.sendPopUps(kssMessage);
        return null;
    }


//    private void handleBadMessage(KssMessage messageModel) throws IOException {
//        int num = CacheUtils.get().getBadMsgNum(messageId);
//
//        if (num > 5) {
//            channel.basicAck(deliveryTag, false);
//           // insertBadMsg(messageModel, num,messageId);
//        } else {
//            CacheUtils.get().setBadMsgNum(messageId);
//            channel.basicNack(deliveryTag, false, true);
//        }
//    }

//    private void insertBadMsg(KssMessage messageModel, int cscs,String messageId) {
//        ExceptionmessageEntity exceptionmessageEntity = new ExceptionmessageEntity();
//        exceptionmessageEntity.setContent(JSONObject.toJSONString(messageModel));
//        exceptionmessageEntity.setCreatetime(Calendar.getInstance().getTime());
//        exceptionmessageEntity.setMessageid(messageId);
//        exceptionmessageEntity.setCscs(cscs + 1);
//        exceptionmessageEntity.setFlag("0");
//        CacheUtils.get().removePattern(CacheUtils.CACHE_BADMSGNUM + messageId);
//        exceptionmessageService.insert(exceptionmessageEntity);
//    }
}
