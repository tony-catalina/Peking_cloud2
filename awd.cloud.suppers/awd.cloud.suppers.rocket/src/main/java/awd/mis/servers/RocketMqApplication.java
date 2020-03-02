package awd.mis.servers;

import awd.framework.expands.logging.AccessLoggerListener;
import awd.mis.servers.message.ReceviceMessage;
import com.ace.cache.EnableAceCache;
import com.alibaba.fastjson.JSON;
import net.bull.javamelody.MonitoredWithSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ServletComponentScan
@EnableAceCache
@MonitoredWithSpring
//@EnableScheduling
public class RocketMqApplication implements CommandLineRunner {

    @Autowired
    private ReceviceMessage receviceMessage;

    public static void main(String[] args) {
        SpringApplication.run(RocketMqApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("spring.application.name from env: " + env.getProperty("spring.application.name"));
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("entities");
    }

    @Bean
    public AccessLoggerListener accessLoggerListener() {
        @SuppressWarnings("rawtypes")
        Class excludes[] = {
                ServletRequest.class,
                ServletResponse.class,
                InputStream.class,
                OutputStream.class,
                MultipartFile.class
        };
        return loggerInfo -> System.out.println("有请求啦:" + JSON.toJSONString(loggerInfo.toSimpleMap(obj -> {
            if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj))) return obj.getClass().getName();
            return JSON.toJSONString(obj);
        })));
    }


    @Override
    public void run(String... arg0) throws Exception {
        receviceMessage.getDataForSubscribePull();
        System.out.println(">>>>>>>>>>>>>>>rocket服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
    }

}
