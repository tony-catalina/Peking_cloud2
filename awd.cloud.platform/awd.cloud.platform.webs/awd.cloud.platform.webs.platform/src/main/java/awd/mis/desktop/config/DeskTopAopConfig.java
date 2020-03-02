package awd.mis.desktop.config;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import awd.mis.desktop.socket.Message;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.OperatelogsModel;
import cn.hutool.json.JSONUtil;

@Aspect
@Component
public class DeskTopAopConfig {

    private final static Logger logger = LoggerFactory.getLogger(DeskTopAopConfig.class);
    private OperatelogsModel operatelogs;
    @Autowired
    private LogsService logsService;

    @Autowired
    private Message message;

    @Pointcut("execution(public * awd.mis.desktop.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            operatelogs = new OperatelogsModel();
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            operatelogs.setJsbh(currentUser.getJsbh());
            operatelogs.setOperator(request.getRequestURL().toString());
            operatelogs.setOptime(Calendar.getInstance().getTime());
            operatelogs.setOpcontent(JSONUtil.toJsonStr(request.getParameterMap()));
            operatelogs.setCreator(currentUser.getName());
            operatelogs.setOptype(request.getMethod());
        }


        //url
//        logger.info("url={}", request.getRequestURL());
//        //method
//        logger.info("method={}", request.getMethod());
//        //ip
//        logger.info("ip={}", request.getRemoteAddr());
//        //类方法
//        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        
//        Enumeration<String> paramter = request.getParameterNames();
//		while (paramter.hasMoreElements()) {
//			String str = (String) paramter.nextElement();
//			logger.info(str + "={}", request.getParameter(str));
//		}
//        //参数
//        logger.info("args={}", JSONUtil.toJsonStr(request.getParameterMap()));
//        /*显示一些参数*/
//		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request  
//				 .getSession().getAttribute("SPRING_SECURITY_CONTEXT");  
//				// 登录名  
//				System.err.println("用户名:"  
//				 + securityContextImpl.getAuthentication().getName());  
//				// 登录密码，未加密的  
//				System.err.println("证书:"  
//				 + securityContextImpl.getAuthentication().getCredentials());  
//				WebAuthenticationDetails details = (WebAuthenticationDetails) securityContextImpl  
//				 .getAuthentication().getDetails();  
//				// 获得访问地址  
//				System.err.println("访问地址:" + details.getRemoteAddress());  
//				// 获得sessionid  
//				System.err.println("SessionId:" + details.getSessionId());  
//				// 获得当前用户所拥有的权限  
//				List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl  
//				 .getAuthentication().getAuthorities();  
//				for (GrantedAuthority grantedAuthority : authorities) {  
//				 System.err.println("权限:" + grantedAuthority.getAuthority());  
//				}
    }

    @After("log()")
    public void doAfter() {
//        logger.info("业务完成后执行");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (object != null && JSONUtil.isJson(object.toString())) {
                operatelogs.setOpresult(object == null ? "{}" : JSONUtil.toJsonStr(object));
            } else {
                operatelogs.setOpresult("{}");
            }
            //logsService.operatelogsSave(operatelogs);
           // message.sendMessage(operatelogs);
        }
        //logger.info("response={}", JSONUtil.toJsonStr(object));
    }

}
