package awd.mis.servers.message;

import awd.mis.servers.service.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author WS
 * @Date 2019-08-24 13:42
 */
@Component
public class ConvertAndSendMessages {

    @Autowired
    private SendMessage sendMessage;


}
