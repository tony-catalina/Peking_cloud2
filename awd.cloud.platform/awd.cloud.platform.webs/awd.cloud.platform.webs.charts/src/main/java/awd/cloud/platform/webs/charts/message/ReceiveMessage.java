package awd.cloud.platform.webs.charts.message;

import awd.cloud.platform.webs.charts.api.ManagerApi;
import awd.cloud.platform.webs.charts.modal.User;
import awd.cloud.platform.webs.charts.utils.CacheUtils;
import awd.cloud.platform.webs.charts.utils.ResponseMessage;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.rabbitmq.enums.TypeEnum;
import awd.framework.expands.rabbitmq.model.ApplicationMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @author WS
 * @Description:
 * @date 2019/11/12 下午4:01
 */
@Component
public class ReceiveMessage {

    @Autowired
    ManagerApi managerApi;

    Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);

    @RabbitHandler
    @RabbitListener(queues = "awd.msg.test")
    public void test(@Payload ApplicationMessage applicationMessage,
                     Channel channel,
                     @Headers Map<String, Object> headers) {
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //获取当前用户
        //User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 消息处理界面
        logger.info("获取到消息：{} ", JSONObject.toJSONString(applicationMessage));
        logger.info("消息类型是：{} ", applicationMessage.getTypeEnum().toString());
        Set<TypeEnum> typeEnums = applicationMessage.getTypeEnum();
        boolean flage = false;
        while (typeEnums.iterator().hasNext()) {
            if (typeEnums.iterator().next() == TypeEnum.CHARTS) {
                flage = true;
                break;
            }
        }
        if (flage) {
            try {
                String content = new String(applicationMessage.getContent(), "utf-8");
                logger.info("获取到消息内容主体：{}", content);
                System.err.println(applicationMessage.getMsgId());

                //todo 先放到Redis中
                //获取消息中传递的jsbh
                String jsbh = applicationMessage.getJsbh();
                if (!StringUtils.isNullOrEmpty(jsbh)) {
                    /*String msgId = users.getJsbh()+users.getUsername()+"_"+System.currentTimeMillis();
                    System.err.println(msgId);
                    //将数据放进redis中
                    CacheUtils.get().setTempMessage(msgId, content);*///根据jsbh获取数据库中的登录名
                    ResponseMessage<List<Map<String,String>>> result = managerApi.getUsers(jsbh);
                    Random random = new Random();
                    String jy="";
                    List<Map<String,String>> users = result.getResult();
                    //遍历获得到的登录名
                    for (Map<String,String> loginName:users) {
                        int ends = random.nextInt(99);
                        if(ends <10){
                            jy = "0"+String.valueOf(ends);
                        }else {
                            jy = String.valueOf(ends);
                        }
                        String msgId = loginName.get("jsbh")+loginName.get("loginname")+"_"+jy+System.currentTimeMillis();
                        System.err.println(msgId);
                        //将数据放进redis中
                        CacheUtils.get().setTempMessage(msgId, content);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                channel.basicAck(deliveryTag, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.info("消息类型不是图形分析系统,自动确认：{}", applicationMessage.getTypeEnum().toArray());
            try {
                channel.basicAck(deliveryTag, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
