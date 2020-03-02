package awd.cloud.platform.webs.charts.config;

import awd.framework.expands.rabbitmq.configuration.ExchangeConfig;
import awd.framework.expands.rabbitmq.configuration.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author WS
 * @Description:
 * @date 2019/11/12 下午3:12
 */
@Configuration
@Component
public class RabbitMqQueueConfig extends RabbitMqConfig {
    Logger logger = LoggerFactory.getLogger(RabbitMqQueueConfig.class);

    @Autowired
    private ExchangeConfig exchangeConfig;

    @Bean
    public Queue queue() {
        Queue queue = new Queue("awd.msg.charts", true, false, false);
        this.logger.info("安威德服务 {} queue 创建成功","awd.msg.charts");
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(this.queue()).to(exchangeConfig.topicExchange()).with("awd.queue.#");
    }
}
