package awd.mis.appstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import awd.mis.appstore.filter.WebHandlerInterceptor;

@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter{

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new WebHandlerInterceptor());
    }

}
