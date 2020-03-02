package awd.mis.servers.config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import awd.framework.common.utils.ApplicationContextHolder;

@Configuration
public class AwdMqInit {
	
	Logger logger = LoggerFactory.getLogger(AwdMqInit.class);
	 
	@Value("${spring.rabbitmq.virtualHost}")
	private String virtualHost;
	
    @Value("${awdrabbit.exchange.defaultfanout}")
    private String defaultfanout;
    
    @Value("${awdrabbit.exchange.awdexchange}")
    private String awdexchange;
    
    @Value("${awdrabbit.exchange.appexchange}")
    private String appexchange;
    
    private FanoutExchange defaultFanoutExchange;
    
    private DirectExchange awdDirectExchange;
    
    private DirectExchange appDirectExchange;

    @Autowired
    private AwdRabbitQueuesProperties rabbitMqProperties;

    @Autowired
    private ConnectionFactory connectionFactory;
    
    private static AwdMqInit instance;

    public AwdMqInit() {
        super();
    }

    public static AwdMqInit get() {
        if (instance == null) {
        	instance =  ApplicationContextHolder.get().getBean(AwdMqInit.class);
        }
        return instance;
    }
    
    /**
     * 	根据名称 创建队列
     * @param queue
     * @return
     */
    public Queue createQueue(String queue) {
        return new Queue(queue, true, false, false, null);
    }
    
    /**
     * description:查找队列
     *
     * @return org.springframework.amqp.core.Queue
     * @params queueLinkedList queueName
     * @author by: WS
     * @createtime: 2019/12/4
     */
    public Queue getQueue(LinkedList<Queue> queueLinkedList, String queueName) {
        for (Queue queue : queueLinkedList) {
            if (queue.getName().equals(queueName)) {
                return queue;
            }
        }
        return createQueue(queueName);
    }
    
    @Bean
    public Connection getConnectionFactory() {
    	return connectionFactory.createConnection();
    }
    
    /**
     * 创建默认的广播交换机
     * @return
     */
    @Bean(name="defaultFanoutExchange")
    public FanoutExchange getDefaultFanoutExchange() {
    	if (defaultFanoutExchange == null) {
    		defaultFanoutExchange = new FanoutExchange(defaultfanout, true, false);
		}
		return defaultFanoutExchange;
	}

    /**
     * 创建默认的安威德的直连交换机
     * @return
     */
	@Bean(name="awdDirectExchange")
	public DirectExchange getAwdDirectExchange() {
		if (awdDirectExchange == null) {
			awdDirectExchange = new DirectExchange(awdexchange, true, false);
		}
		return awdDirectExchange;
	}

	/**
	 * 创建默认的第三方应用的直连交换机
	 * @return
	 */
	@Bean(name="appDirectExchange")
	public DirectExchange getAppDirectExchange() {
		if (appDirectExchange == null) {
			appDirectExchange = new DirectExchange(appexchange, true, false);
		}
		return appDirectExchange;
    }
   
	/**
	 * 根据配置文件生成安威德的消息队列
	 * @return
	 */
    @Bean(name="awdQueueList")
    public LinkedList<Queue> awdQueueList(@Qualifier("awdDirectExchange") DirectExchange awdDirectExchange) {
        LinkedList<Queue> queues = new LinkedList<>();
        List<AwdRabbitQueuesProperties.QueueProperties> valueMapList = rabbitMqProperties.getQueuePropertiesList();
        if (valueMapList != null && valueMapList.size() > 0) {
            for (int i = 0; i < valueMapList.size(); i++) {
            	Queue queue = createQueue(valueMapList.get(i).getQueue());
            	logger.info("[--正在创建第{}/{}个队列,队列名称{}  --]", i + 1, valueMapList.size(), valueMapList.get(i).getQueue());
                queues.add(queue);
            }
        }
        return queues;
    }

	/**
	 * 把直连交换机和默认的广播交换机绑定
	 * @param appDirectExchange
	 * @param awdDirectExchange
	 * @param defaultFanoutExchange
	 * @return
	 */
	@Bean
	public List<Binding> binding(@Qualifier("appDirectExchange") DirectExchange appDirectExchange,
	    						@Qualifier("awdDirectExchange") DirectExchange awdDirectExchange,
	    						@Qualifier("defaultFanoutExchange") FanoutExchange defaultFanoutExchange) {
		List<Binding> bindings = new ArrayList<Binding>();
		bindings.add(BindingBuilder.bind(appDirectExchange).to(defaultFanoutExchange));
		bindings.add(BindingBuilder.bind(awdDirectExchange).to(defaultFanoutExchange));
		bindings.add(BindingBuilder.bind(new Queue("awd.msg.platform")).to(defaultFanoutExchange));
        return bindings;
    }
	
	/**
	 * 把直连交换机和默认的广播交换机绑定
	 * @param appDirectExchange
	 * @param awdDirectExchange
	 * @param defaultFanoutExchange
	 * @return
	 */
	@Bean(name = "bindingAwdQueues")
	public List<Binding> bindingAwdQueues(@Qualifier("awdDirectExchange") DirectExchange awdDirectExchange,
										@Qualifier("awdQueueList") LinkedList<Queue> awdQueueList) {
		List<Binding> bindings = new ArrayList<Binding>();
		List<AwdRabbitQueuesProperties.QueueProperties> queueProperties = rabbitMqProperties.getQueuePropertiesList();
		if (awdQueueList != null && awdQueueList.size() > 0) {
			for (int i = 0; i < queueProperties.size(); i++) {
				AwdRabbitQueuesProperties.QueueProperties queue = queueProperties.get(i);
				String awdqueue = queue.getQueue();
				String routingkey = queue.getRoutingkey();
				bindings.add(BindingBuilder.bind(getQueue(awdQueueList, queue.getQueue())).
						to(awdDirectExchange).with(routingkey));
				logger.info("[--正在绑定第{}/{}个消息队列到交换机队列,交换机名称 {} 消息队列名称 {} 绑定key {}  --]", i + 1,queueProperties.size(), awdexchange,awdqueue,routingkey);
			}
		}
		return bindings;
	}
	

	public String getVirtualHost() {
		return virtualHost;
	}

	public String getDefaultfanout() {
		return defaultfanout;
	}

	public String getAwdexchange() {
		return awdexchange;
	}

	public String getAppexchange() {
		return appexchange;
	}
    
}
	