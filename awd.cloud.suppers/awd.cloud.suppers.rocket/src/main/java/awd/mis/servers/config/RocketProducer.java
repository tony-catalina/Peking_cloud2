package awd.mis.servers.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RocketProducer {

    private DefaultMQProducer producer;


    public RocketProducer(@Autowired
                                  RocketProperties rocketProperties
    ) {
        producer = new DefaultMQProducer(rocketProperties.getProducerGroup());
        // 指定nameServer地址,多个地址之间以 ; 隔开
        //JSONObject
        producer.setNamesrvAddr(rocketProperties.getNameServer());
        start();
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    /**
     * 对象在使用之前必须调用一次,并且只能初始化一次
     */
    public void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 一般在应用上下文,使用上下文监听器,进行关闭
     */
    public void shutdown() {
        producer.shutdown();
    }

}