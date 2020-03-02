package awd.mis.desktop.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 获取spring管理对象
 * @author MZP
 */
@Component
@Lazy(false)
public class DeskTopApplicationContextRegister  implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文
     * @param applicationContext spring上下文
     */

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}