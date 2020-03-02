package awd.mis.servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import awd.framework.expands.logging.AccessLoggerListener;
import net.bull.javamelody.MonitoredWithSpring;

@SpringBootApplication
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ServletComponentScan
@MonitoredWithSpring
public class MessageServerApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MessageServerApplication.class, args);
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

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/myerror"));
                //container.setSessionTimeout(1800);//单位为S

            }
        };
    }
    @Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(smt);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        //设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
        return mappingJackson2HttpMessageConverter;
    }

    @Override
    public void run(String... arg0) throws Exception {
    	System.err.println(">>>>>>>>>>>>>>>>>>>>安威德消息管理后台服务启动完成<<<<<<<<<<<<<<<<<<<<");
    }

}
