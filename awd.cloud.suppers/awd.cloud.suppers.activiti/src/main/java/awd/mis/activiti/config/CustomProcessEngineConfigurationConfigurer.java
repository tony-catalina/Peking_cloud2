package awd.mis.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * Created by watchdb on 2018/1/1.
 */
@Component
public class CustomProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer {
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        System.out.println("########## CustomProcessEngineConfigurationConfigurer #############");
        System.out.println(processEngineConfiguration.getActivityFontName());
    }
}
