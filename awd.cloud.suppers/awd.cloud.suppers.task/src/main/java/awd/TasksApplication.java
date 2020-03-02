package awd;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.DataSourceUtils;

import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import net.bull.javamelody.MonitoredWithSpring;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@Configuration
@EnableHystrix
@EnableFeignClients
@MonitoredWithSpring
public class TasksApplication implements CommandLineRunner{
	
    public static void main( String[] args ){
        SpringApplication.run(TasksApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("my-config.appName from env: "+ env.getProperty("spring.application.name"));
    }
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("entities");
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
        try {
            ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
                    this.getClass().getClassLoader(), "/templates");
            beetlGroupUtilConfiguration.setResourceLoader(resourceLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }
    
    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver() {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setPrefix("/");
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(getBeetlGroupUtilConfiguration());
        return beetlSpringViewResolver;
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
    public void run(String... args) throws Exception {
    	System.out.println(">>>>>>>>>>>>>>定时任务系统启动<<<<<<<<<<<<<");

        System.out.println(">>>>>>>>>>>>>>定时任务系统启动结束<<<<<<<<<<<<<");
    }
}
