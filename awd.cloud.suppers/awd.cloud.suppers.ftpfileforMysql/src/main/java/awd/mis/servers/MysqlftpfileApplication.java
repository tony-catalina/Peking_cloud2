package awd.mis.servers;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import awd.framework.expands.logging.AccessLoggerListener;
import awd.mis.servers.utils.FTPUtils;
import awd.mis.servers.utils.FileUtil;
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
@MonitoredWithSpring
@EnableScheduling
public class MysqlftpfileApplication implements CommandLineRunner{

	@Autowired
	private FTPUtils ftp;
	
	@Autowired
	private FileUtil file;
	
    public static void main(String[] args) {
    	SpringApplication.run(MysqlftpfileApplication.class,args);
	}

    @Autowired
    void setEnviroment(Environment env) {
        System.err.println("spring.application.name from env: "+ env.getProperty("spring.application.name"));
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
        return loggerInfo -> System.out.println("request is comming:" + JSON.toJSONString(loggerInfo.toSimpleMap(obj -> {
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
		System.err.println("---------------- initing FTP ----------------");
		ftp.initFtpClient();
    	System.err.println("---------------- init FTP finish ! ----------------");
		System.err.println("---------------- begin to creat FTP folder ----------------");
		file.makeDir();
		System.err.println("---------------- create FTP folder end !----------------");
		System.out.println(">>>>>>>>>>>>>>>ftp file upload and download !<<<<<<<<<<<<<");		
	}
    
}
