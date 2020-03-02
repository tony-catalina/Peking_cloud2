package awd.cloud.suppers.speechserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{
	
    @Value("${speech.voicedir}")
    private String path;


    /**
     * 访问外部文件配置，访问D盘下文件
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为jsp中访问的目录，locations为image相对应的本地路径
        registry.addResourceHandler("/voice/**").addResourceLocations("file:"+path);
    }
    
    

}
