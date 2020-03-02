package awd.mis.servers.message;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.utils.JSONUtil;
import awd.framework.expands.rabbitmq.enums.QueueEnum;
import awd.framework.expands.rabbitmq.enums.TypeEnum;
import awd.framework.expands.rabbitmq.model.KssMessage;
import awd.mis.servers.api.ManagerService;
import awd.mis.servers.entity.ExceptionmessageEntity;
import awd.mis.servers.entity.MsgboxEntity;
import awd.mis.servers.entity.OperatelogsEntity;
import awd.mis.servers.model.UserinfoModel;
import awd.mis.servers.service.ExceptionmessageService;
import awd.mis.servers.service.MsgboxService;
import awd.mis.servers.service.OperatelogsService;
import awd.mis.servers.utils.CacheUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

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
public class ReceiveMessage {

    Logger logger = LoggerFactory.getLogger(ReceiveMessage.class);
    
    @Autowired
    private OperatelogsService operatelogsService;

    /**
     * description: 日志保存
     *
     * @return void
     * @params message
     * @author by: WS
     * @createtime: 2019/11/7
     */
    @RabbitListener(queues = "awd.log.msg")
    public void reciveLogsMessage(String message) {
        try {
            System.err.println("------------------------");
            System.err.println("message: " + message);
            System.err.println("正在处理日志消息");
            OperatelogsEntity operatelogsEntity = JSONUtil.toBean(message, OperatelogsEntity.class);
            operatelogsService.insert(operatelogsEntity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("消息处理出差，错误消息为{}", message);
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String xxxxxxxx = "sfdghskjdklsjdlkhskjd圣诞节客户说的考核时间都会看见啥都会尽快合适的接收到坚实的健康斯柯达合适的";
        byte[] xs = xxxxxxxx.getBytes("utf-8");

        String xxxxx = Base64.getEncoder().encodeToString(xs);
        System.err.println(xxxxx);
        System.err.println(Base64.getDecoder().decode(xxxxx));
        System.err.println(new String(Base64.getDecoder().decode(xxxxx), "utf-8"));
    }
}
