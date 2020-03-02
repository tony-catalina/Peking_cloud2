package awd.mis.servers;

import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.core.resource.WebAppResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.http.HttpStatus;

@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients
@ServletComponentScan
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MessageManagerApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MessageManagerApplication.class, args);
    }

    @Autowired
    void setEnviroment(Environment env) {
        System.out.println("spring.application.name from env: " + env.getProperty("spring.application.name"));
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
    	BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ResourcePatternResolver patternResolver = ResourcePatternUtils.getResourcePatternResolver(new DefaultResourceLoader());
		/*
		 * try { // WebAppResourceLoader 配置root路径是关键 WebAppResourceLoader
		 * webAppResourceLoader = new WebAppResourceLoader(
		 * patternResolver.getResource("classpath:/templates").getFile().getPath());
		 * beetlGroupUtilConfiguration.setResourceLoader(webAppResourceLoader);
		 * ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader(
		 * this.getClass().getClassLoader(), "/templates");
		 * beetlGroupUtilConfiguration.setResourceLoader(resourceLoader); } catch
		 * (Exception e) { e.printStackTrace(); }
		 */
        
		ClasspathResourceLoader classPathLoader = new ClasspathResourceLoader(this.getClass().getClassLoader(), "/templates");
		beetlGroupUtilConfiguration.setResourceLoader(classPathLoader);
		// 读取配置文件信息
		beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:templates/"));
        
        //读取配置文件信息
        return beetlGroupUtilConfiguration;
    }
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return new EmbeddedServletContainerCustomizer() {
            public void customize(ConfigurableEmbeddedServletContainer container) {
//                ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400.htm");
                ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401.htm");
                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404.htm");
//                ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.htm");
                container.addErrorPages(error401Page,error404Page);
            }
        };
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

    @Override
    public void run(String... arg0) throws Exception {
    	System.err.println(">>>>>>>>>>>>>>>>>>>>安威德消息管理控制台启动完成<<<<<<<<<<<<<<<<<<<<");
    }

}
