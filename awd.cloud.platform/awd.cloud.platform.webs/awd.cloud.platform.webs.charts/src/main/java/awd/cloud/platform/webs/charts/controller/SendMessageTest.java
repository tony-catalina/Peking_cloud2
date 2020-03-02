package awd.cloud.platform.webs.charts.controller;

import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.rabbitmq.enums.TypeEnum;
import awd.framework.expands.rabbitmq.model.ApplicationMessage;
import awd.framework.expands.rabbitmq.send.SendMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author WS
 * @Description: 消息发送测试
 * @date 2019/11/8 下午2:00
 */
@RestController
public class SendMessageTest {

    @Autowired
    private SendMessage sendMessage;

    @GetMapping("/test")
    @OpenAPI
    public String sendMessage(@RequestParam("message") String message,@RequestParam("jsbh") String jsbh) {
        ApplicationMessage applicationMessage = new ApplicationMessage();
        applicationMessage.setTypeEnum(TypeEnum.JWP,TypeEnum.CHARTS);
        applicationMessage.setJsbh(jsbh);
        applicationMessage.setMsgId(UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis());
        try {
            applicationMessage.setContent(message.getBytes("utf-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("=================="+ JSON.toJSONString(applicationMessage));
        sendMessage.sendAPPMessage(applicationMessage);
        System.err.println("消息发送成功！");
        return "Success";
    }
}
