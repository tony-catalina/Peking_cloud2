package awd.cloud.platform.tasks.config;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import awd.cloud.platform.tasks.api.AwdApi;
import awd.cloud.platform.tasks.model.OperatelogsModel;
import awd.cloud.platform.tasks.model.User;
import cn.hutool.json.JSONUtil;

/**
 * 综合岗位AOP切面
 * @author ws
 *
 */
@Aspect
@Component
public class AopConfig {

    private final static Logger logger = LoggerFactory.getLogger(AopConfig.class);
    private OperatelogsModel operatelogs;
    
    @Autowired
    private AwdApi awdApi;

    @Pointcut("execution(public * awd.bj.kss.webs.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response =attributes.getResponse();
        if(SecurityContextHolder.getContext().getAuthentication()!=null) {
        	operatelogs=new OperatelogsModel();
        	User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            operatelogs.setJsbh(currentUser.getJsbh());
            operatelogs.setOperator(request.getRequestURL().toString());
            operatelogs.setOptime(Calendar.getInstance().getTime());
            operatelogs.setOpcontent(JSONUtil.toJsonStr(request.getParameterMap()));
            operatelogs.setCreator(currentUser.getName());
            operatelogs.setOptype(request.getMethod());
        }       
    }

    @After("log()")
    public void doAfter() {
//        logger.info("业务完成后执行");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
    	if(SecurityContextHolder.getContext().getAuthentication()!=null) {
    		if(object!=null&&JSONUtil.isJson(object.toString())) {
    			operatelogs.setOpresult(object==null?"{}":JSONUtil.toJsonStr(object));
    		}else {
    			operatelogs.setOpresult("{}");
    		}
            awdApi.operatelogsSave(operatelogs);
    	}
    }

}
