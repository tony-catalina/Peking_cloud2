package awd.mis.desktop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import awd.mis.desktop.filter.WebHandleInterceptor;

@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter{


	@Bean
	WebHandleInterceptor webHandleInterceptor() {
         return new WebHandleInterceptor();
    }
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webHandleInterceptor());
	}
	
	@Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")  
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("GET", "POST", "DELETE", "PUT")  
                .maxAge(3600);         
    }
	
}
