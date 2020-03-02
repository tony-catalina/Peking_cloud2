 package awd;


import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import awd.framework.expands.logging.AccessLoggerListener;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import awd.cloud.platform.api.ManagerService;
import awd.cloud.platform.model.manager.Manager_InterfaceModel;
import awd.framework.common.utils.JSONUtil;
import io.swagger.annotations.ApiOperation;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableHystrix
@EnableFeignClients
public class AwdapiApplication implements CommandLineRunner{


    public static void main( String[] args ){
    	SpringApplication.run(AwdapiApplication.class, args);
    }
    
    @Autowired
	WebApplicationContext applicationContext;
    @Autowired
    ManagerService managerService;
    
    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("my-config.appName from env: "+ env.getProperty("spring.application.name"));
    }
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("entities");
    }

	 @Bean
	 public AccessLoggerListener accessLoggerListener() {
		 @SuppressWarnings("rawtypes")
		 Class excludes[] = { ServletRequest.class, ServletResponse.class, InputStream.class, OutputStream.class,
				 MultipartFile.class };
		 return loggerInfo -> System.out.println("有请求啦:" + (JSON.toJSONString(loggerInfo.toSimpleMap(obj -> {
			 if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj)))
				 return obj.getClass().getName();
			 return JSON.toJSONString(obj);
		 })).length()>4000?"数据太多不打印":JSON.toJSONString(loggerInfo.toSimpleMap(obj -> {
			 if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj)))
				 return obj.getClass().getName();
			 return JSON.toJSONString(obj);
		 }))));
	 }

	 @Bean
	 public ServletListenerRegistrationBean<EventListener> getDemoListener() {
		 ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<>();
		 registrationBean.setListener(new RequestContextListener());
//        registrationBean.setOrder(1);
		 return registrationBean;
	 }

	 @Bean
	 public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		 MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		 // 设置日期格式
		 ObjectMapper objectMapper = new ObjectMapper();
		 SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
		 objectMapper.setDateFormat(smt);
		 mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
		 // 设置中文编码格式
		 List<MediaType> list = new ArrayList<MediaType>();
		 list.add(MediaType.APPLICATION_JSON_UTF8);
		 mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
		 return mappingJackson2HttpMessageConverter;
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
    
    public List<Map<String, String>> getAllUrl() {
		RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
		// 获取url与类和方法的对应信息
		Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
	
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
			Map<String, String> map1 = new HashMap<String, String>();
			RequestMappingInfo info = m.getKey();  
            HandlerMethod method = m.getValue();  
            PatternsRequestCondition p = info.getPatternsCondition();  
            for (String url : p.getPatterns()) {  
            	map1.put("url", url);
            }  
            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名  
            map1.put("method", method.getMethod().getName()); // 方法名 
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
            	map1.put("type", requestMethod.toString());
			}
            Class cl1;
			try {				
				cl1 = Class.forName(method.getMethod().getDeclaringClass().getName());
				 Method[] methods = cl1.getDeclaredMethods();
		         //获取RequestMapping注解
		         RequestMapping anno = (RequestMapping) cl1.getAnnotation(ApiOperation.class);
		         for (Method me : methods) {		        	  
		        	   ApiOperation apiOperation = me.getAnnotation(ApiOperation.class);
		        	   //获取方法上@ApiOperation注解的value值
		        	   if(apiOperation!=null) {
		        		   String apiOperationValue = apiOperation.value();
			        	   map1.put("description", apiOperationValue);
		        	   }		        	   
		        	}
		         
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
           
			System.out.println(JSONUtil.toJson(map1));
			
			Manager_InterfaceModel interfaceModel=new Manager_InterfaceModel();
			interfaceModel.setInterfaceName(map1.get("method"));
			interfaceModel.setInterfaceDescription(map1.get("description"));
			interfaceModel.setInterfaceType(getTypeMethod(map1.get("url")));
			interfaceModel.setMethod(map1.get("type"));
			interfaceModel.setInterfaceId(map1.get("url"));
			interfaceModel.setSfqy("1");
			interfaceModel.setCreator("应用平台接口");
			interfaceModel.setCreatetime(Calendar.getInstance().getTime());
			managerService.interface_save(interfaceModel);
            list.add(map1);
		}
		
		return list;

    }
    
    private String getTypeMethod(String url) {
    	String type="";
    	if(url.indexOf("kss")>0) {
    		type= "1";
    	}
    	if(url.indexOf("jls")>0) {
    		type= "2";
    	}
    	if(url.indexOf("jds")>0) {
    		type= "3";
    	}
    	if(url.indexOf("sjs")>0) {
    		type= "4";
    	}
    	if(url.indexOf("akyy")>0) {
    		type= "5";
    	}
    	if(url.indexOf("manager")>0) {
    		type= "0";
    	}
    	if(url.indexOf("logs")>0) {
    		type= "9";
    	}
    	if(url.indexOf("author")>0) {
    		type= "9";
    	}
    	return type;
    }
    
	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>服务自注册<<<<<<<<<<<<<");	
		//getAllUrl();
		System.out.println(">>>>>>>>>>>>>>微应用平台App 启动结束<<<<<<<<<<<<<");		
	}
}
