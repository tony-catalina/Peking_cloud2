package awd.mis.servers.config;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import awd.framework.common.utils.ApplicationContextHolder;

@Component
public class RabbitMQConnection {

    @Value("${spring.rabbitmq.host}")
    private String hostip;
    
    @Value("${spring.rabbitmq.port}")
    private int port;
    
    @Value("${spring.rabbitmq.username}")
    private String username;
    
    @Value("${spring.rabbitmq.password}")
    private String password;
    
    @Value("${spring.rabbitmq.virtualHost}")
    private String virtualHost;
    
    
    public static RabbitMQConnection get() {
    	ApplicationContext act = ApplicationContextHolder.get();
		RabbitMQConnection rabbitMQConnection =  act.getBean(RabbitMQConnection.class);
        return rabbitMQConnection;
    }
    
    /**
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置服务器地址
        connectionFactory.setHost(hostip);
        connectionFactory.setPort(port);
        //设置用户名
        connectionFactory.setUsername(username);
        //设置密码
        connectionFactory.setPassword(password);

        connectionFactory.setVirtualHost(virtualHost);

        return connectionFactory.newConnection();
    }
    
    /**
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public Connection getConnection(String vhost) throws IOException, TimeoutException {
    	//定义一个连接工厂
    	ConnectionFactory connectionFactory = new ConnectionFactory();
    	
    	//设置服务器地址
    	connectionFactory.setHost(hostip);
    	connectionFactory.setPort(port);
    	//设置用户名
    	connectionFactory.setUsername(username);
    	//设置密码
    	connectionFactory.setPassword(password);
    	
    	connectionFactory.setVirtualHost(vhost);
    	
    	return connectionFactory.newConnection();
    }

}