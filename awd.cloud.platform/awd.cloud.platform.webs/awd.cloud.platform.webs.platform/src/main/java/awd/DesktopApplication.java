 package awd;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ace.cache.EnableAceCache;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.hutool.core.date.DateUtil;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableHystrix
@EnableFeignClients
@EnableCircuitBreaker
@EnableAceCache
public class DesktopApplication implements CommandLineRunner{
    public static void main( String[] args ){
    	System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("file.encoding=" + System.getProperty("file.encoding"));
        System.out.println("Default Charset=" + Charset.defaultCharset());
        System.out.println("Default Charset in Use=" + getDefaultCharSet());
    	SpringApplication.run(DesktopApplication.class, args);
    }
    
    
    private static String getDefaultCharSet() {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        String enc = writer.getEncoding();
        return enc;
    }
    
    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("my-config.appName from env: " + env.getProperty("spring.application.name"));
    }
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("entities");
    }    
    
    @Bean(initMethod = "init", name = "beetlConfig")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
					this.getClass().getClassLoader(), "/templates");
			beetlGroupUtilConfiguration.setResourceLoader(resourceLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 读取配置文件信息
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
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>监管业务办理平台 启动<<<<<<<<<<<<<");	
		System.out.println("--------------------应用初始化----------------------");
		
		System.out.println("--------------------初始化结束----------------------");
		System.out.println(">>>>>>>>>>>>>>监管业务办理平台 启动结束<<<<<<<<<<<<<");	
	}
}
