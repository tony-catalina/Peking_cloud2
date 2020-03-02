package awd.mis.servers.controller;

import awd.framework.common.utils.OpenAPI;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.config.RocketProducer;
import awd.mis.servers.config.RocketProperties;
import awd.mis.servers.message.ReceviceMessage;
import com.chinaoly.sjzyfwpt.SjzyfwServerUtil;
import com.chinaoly.sjzyfwpt.util.ServiceResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rocket")
public class RocketMqController {

    @Autowired
    private RocketProducer rocketProducer;
    @Autowired
    private RocketProperties rocketProperties;


    @PostMapping("/send")
    @OpenAPI
    public Object callback(@RequestParam("json") String json) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message(rocketProperties.getTopic(), "taga", (json).getBytes());

        SendResult send = rocketProducer.getProducer().send(message);
         System.out.println(send);

        return new HashMap<>();
    }

    @GetMapping("/getTCPAPI")
    @OpenAPI
    public ServiceResult<?> getTCPAPI(@RequestParam("url") String url,
                                      @RequestParam("method") String method,
                                      Map<String, String> params) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        /**
         * 单表数据服务和外部数据服务调用
         * @return
         */
        //服务调用url
        if (StringUtils.isNullOrEmpty(url)) {
            url = "http://14.66.87.170:8188/api/v1/JWXX/vWcuJR";
        }
        //服务标志符
        //String secretKey = "1d22b7016f794ecf83860db442971e28";
        //调用应用id
        //String accessKey = "dcd45c3c1b094429be8f9ab16f3be456";
        //数据筛选条件,若没有params传null
        if (params.isEmpty()) {
            params.put("pageNum", "1");
            params.put("pageSize", "5");
        }
        ServiceResult<?> result = SjzyfwServerUtil.getDataForTable(url, params, method, rocketProperties.getSecretKeyApi(), rocketProperties.getAccessKeyApi());
        return result;
    }

    /**
     * 数据订阅服务调用
     *
     * @return
     */
    public ServiceResult<?> getDataForSubscribePull() {
        //当前类的实例,当前类需要继承RocketMQConsumerPull类,重写receiveData方法
        ReceviceMessage app = new ReceviceMessage();
        //数据筛选条件,若没有params传null
        Map<String, String> params = new HashMap<>();
        params.put("tag", "*");
        ServiceResult<?> result = SjzyfwServerUtil.getDataForSubscribePull(app, "http://14.66.87.170:8188/api/v1/RYGL/EMXxYc", params, "dfb121f1929042a7835b8581a28cea34", "1c7f020f440c4d7a87ba4946f0ff237f");
        return result;
    }
    public static void main(String[] args) {
        RocketMqController receviceMessage = new RocketMqController();
        receviceMessage.getDataForSubscribePull();
    }
}