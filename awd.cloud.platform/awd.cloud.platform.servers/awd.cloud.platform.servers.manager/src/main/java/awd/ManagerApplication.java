package awd;

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

import awd.mis.servers.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartFile;

import com.ace.cache.EnableAceCache;
import com.fasterxml.jackson.databind.ObjectMapper;

import awd.framework.common.dao.api.datasource.DataSourceHolder;
import awd.framework.common.dao.api.datasource.DatabaseType;
import awd.framework.common.datasource.orm.rdb.executor.AbstractJdbcSqlExecutor;
import awd.framework.common.datasource.orm.rdb.executor.SqlExecutor;
import awd.framework.common.utils.JSONUtil;
import awd.framework.expands.logging.AccessLoggerListener;
import awd.mis.servers.config.FeignBasicAuthRequestInterceptor;
import feign.RequestInterceptor;
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
@EnableAceCache
@MonitoredWithSpring
public class ManagerApplication implements CommandLineRunner {

    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private ClassficService classficService;
    @Autowired
    private FlowmapService flowmapService;
    @Autowired
    private FlownodeService flownodeService;
    @Autowired
    private FxpgService fxpgService;
    @Autowired
    private WgzdService wgzdService;
    @Autowired
    private AppService appService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenusService menusService;
    @Autowired
    private InterfaceService interfaceService;
    @Autowired
    private InterfacebindingService interfacebindingService;
    @Autowired
    private ContrastipService contrastipService;

    @Autowired
    private TableinfoService tableinfoService;

    @Autowired
    private MfaceService mfaceService;

    @Autowired
    private MjjbxxService mjjbxxService;

    @Autowired
    private JbxxService jbxxService;

    @Autowired
    private MirisService mirisService;

    @Autowired
    private MfingerService mfingerService;

    @Autowired
    private ZfaceService zfaceService;

    @Autowired
    private ZirisService zirisService;

    @Autowired
    private ZfingerService zfingerService;

    @Autowired
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("-------------------检查配置文件是否获取到---------------------------------------");
        System.out.println("spring.application.name from env: " + env.getProperty("spring.application.name"));
        System.out.println("-------------------检查结束---------------------------------------");
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
        return loggerInfo -> System.out.println("有请求啦:" + JSONUtil.toJson(loggerInfo.toSimpleMap(obj -> {
            if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj))) return obj.getClass().getName();
            return JSONUtil.toJson(obj);
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
        System.out.println(">>>>>>>>>>>>>>>平台管理服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");


        System.out.println("------------------加载字典到缓存----------------------------");
        dictionaryService.cached();
        System.out.println("------------------字典到缓存结束----------------------------");

        System.out.println("------------------分类表加载到缓存----------------------------");
        classficService.cached();
        System.out.println("------------------分类表加载缓存结束----------------------------");

        System.out.println("------------------流程图信息加载到缓存----------------------------");
        flowmapService.initFlowMapCache();
        System.out.println("------------------流程图信息加载缓存结束----------------------------");

        System.out.println("------------------流程节点息加载到缓存----------------------------");
        flownodeService.cached();
        System.out.println("------------------流程节点信息加载缓存结束----------------------------");

        System.out.println("------------------风险评估字典加载到缓存----------------------------");
        fxpgService.cached();
        System.out.println("------------------风险评估字典加载缓存结束----------------------------");

        System.out.println("------------------违规字典加载到缓存----------------------------");
        wgzdService.cached();
        System.out.println("------------------违规字典加载缓存结束----------------------------");

        System.out.println("------------------应用信息加载到缓存----------------------------");
        appService.cached();
        System.out.println("------------------应用信息加载缓存结束----------------------------");

        System.out.println("------------------用户角色加载到缓存----------------------------");
        roleService.cached();
        System.out.println("------------------用户角色加载缓存结束----------------------------");

        System.out.println("------------------应用菜单加载到缓存----------------------------");
        menusService.cached();

        System.out.println("------------------表信息加载到缓存----------------------------");
        tableinfoService.cached();

        System.out.println("------------------api接口----------------------------");
        interfaceService.cached();

        System.out.println("------------------api 接口权限添加---------------------------");
        interfacebindingService.cached();
        System.out.println("------------------应用菜单加载缓存结束----------------------------");
        System.out.println("------------------生物特征采集机器与用户机器IP绑定关系缓存---------------------------");
        contrastipService.cached();
        System.out.println("------------------生物特征采集机器与用户机器IP绑定关系缓存结束---------------------------");

        System.out.println("------------------虹膜、指纹、人脸信息缓存---------------------------");
        mfaceService.cached();
        mfingerService.cached();
        mirisService.cached();
        zfaceService.cached();
        zfingerService.cached();
        zirisService.cached();
        System.out.println("------------------虹膜、指纹、人脸信息缓存结束---------------------------");
        System.out.println(">>>>>>>>>>>>>>>平台管理服务启动结束<<<<<<<<<<<<<");
    }

}
