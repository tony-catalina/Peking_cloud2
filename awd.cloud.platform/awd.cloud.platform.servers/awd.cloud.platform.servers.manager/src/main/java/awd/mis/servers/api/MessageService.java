package awd.mis.servers.api;

import awd.framework.common.controller.message.ResponseMessage;
import awd.mis.servers.api.hystrix.MessageFallBackFactory;
import awd.mis.servers.entity.ApplicationMsg;
import awd.mis.servers.entity.ApplicationMsgList;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "${awd.api.manager:AWD-MIS-MESSAGE-SERVER}",fallbackFactory = MessageFallBackFactory.class)

public interface MessageService {
    /**
     * 消息发送
     * @param
     * @return
     */
    @PostMapping("/awdMessage/sendMessage/json")
    public ResponseMessage sendMsgJson(@RequestParam("msgtype") String msgtype, @RequestParam("message") String message);
    

    /**
     * 消息发送
     * @param applicationMsg
     * @return
     */
    @PostMapping("/awdMessage/sendMessage")
    public ResponseMessage sendMsg(@RequestBody ApplicationMsg applicationMsg);

    /**
     * 批量发送消息
     * @param applicationMsgList
     * @return
     */
    @PostMapping("/awdMessage/sendMessageList")
    public ResponseMessage sendMessageList(@RequestBody ApplicationMsgList applicationMsgList);
}
