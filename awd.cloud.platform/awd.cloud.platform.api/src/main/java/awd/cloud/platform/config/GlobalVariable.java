package awd.cloud.platform.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * @Description 全局变量设置
 * @Author WS
 * @Date 2019/4/22
 */
@Component
public class GlobalVariable implements InitializingBean, ServletContextAware {

    private static ServletContext myServletContext;

    public static ServletContext get() {
        return myServletContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //get();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.myServletContext = servletContext;
    }
}
