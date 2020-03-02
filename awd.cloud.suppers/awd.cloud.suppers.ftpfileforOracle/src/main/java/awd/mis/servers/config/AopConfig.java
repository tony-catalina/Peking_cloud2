package awd.mis.servers.config;

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
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AopConfig {

    private final static Logger logger = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(public * awd.mis.servers.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = (HttpServletResponse) attributes.getResponse();
        /* 修改默认'x-frame-options' 值为 'SAMEORIGIN' 允许界面在同域名下的页面中嵌套 */
        //response.setHeader("x-frame-options","SAMEORIGIN");
		
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
		//response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600"); // 保持跨域Ajax时的Cookie
		response.setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with,Authorization,Origin, Accept, Content-Type,x-xsrf-token");
		//response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		response.setHeader("Accept", "application/json");



        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("业务完成后执行");
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        //logger.info("response={}", object.toString());
    }

}
