package awd.mis.servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.ace.cache.EnableAceCache;
import com.alibaba.fastjson.JSON;

import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import awd.framework.expands.logging.AccessLoggerListener;
import awd.mis.servers.utils.SetFastdfsValueUtil;
import net.bull.javamelody.MonitoredWithSpring;

@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableCaching
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableHystrix
@EnableAutoConfiguration
@EnableFeignClients
@ServletComponentScan
@EnableRabbit
@EnableAceCache
@MonitoredWithSpring
public class LogsApplication implements CommandLineRunner{

	@Autowired
	private SetFastdfsValueUtil setFastdfsValueUtil;
	
    public static void main(String[] args) {
    	SpringApplication.run(LogsApplication.class,args);
	}

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("spring.application.name from env: "+ env.getProperty("spring.application.name"));
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

    @Bean
    @ConditionalOnMissingBean(SqlExecutor.class)
    public SqlExecutor sqlExecutor(DataSource dataSource) {    
        DataSourceHolder.install(dataSource, DatabaseType.mysql);
        return new AbstractJdbcSqlExecutor() {
            @Override
            public Connection getConnection() {
                return DataSourceUtils.getConnection(dataSource);
            }

            @Override
            public void releaseConnection(Connection connection) throws SQLException {
                DataSourceUtils.releaseConnection(connection, dataSource);
            }
        };

    }
    
	@Override
	public void run(String... arg0) throws Exception {
		//setFastdfsValueUtil.setFastdfsValueUtil();
		System.out.println(">>>>>>>>>>>>>>>日志消息服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");		
	}
    
}
