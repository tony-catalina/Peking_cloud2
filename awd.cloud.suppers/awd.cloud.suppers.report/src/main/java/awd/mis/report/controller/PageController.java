package awd.mis.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.eng.spagobi.sdk.documents.bo.SDKDocument;
import it.eng.spagobi.sdk.proxy.DocumentsServiceProxy;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



@Controller
public class PageController {
	@Autowired
	Environment env;
    /**
     * 默认页<br/>
     * @RequestMapping("/") 和 @RequestMapping 是有区别的
     * 如果不写参数，则为全局默认页，加入输入404页面，也会自动访问到这个页面。
     * 如果加了参数“/”，则只认为是根页面。
     * 可以通过localhost:8080或者localhost:8080/index访问该方法
     */
    @RequestMapping(value = {"/","/index"})
    public String index(Map<String, Object> model,HttpServletRequest request){
        // 直接返回字符串，框架默认会去 spring.view.prefix 目录下的 （index拼接spring.view.suffix）页面
        // 本例为 /WEB-INF/jsp/index.jsp
    	String document=request.getParameter("document");
    	String parameters=request.getParameter("parameters");
    	String displaytoolbar=request.getParameter("displaytoolbar");
    	String displaysliders=request.getParameter("displaysliders");
    	
    	String lable=request.getParameter("label");
    	String name=request.getParameter("name");
    	System.out.println("报表label:"+lable);
    	System.out.println("报表name:"+name);
    	System.out.println("报表paramters:"+parameters);
    	
    	boolean toolbar=false;
    	boolean slider=false;
    	
    	if(!StringUtils.isEmpty(displaytoolbar)) {
    		if("true".equals(displaytoolbar)) {
    			toolbar=true;
    		}else {
    			toolbar=false;
    		}
    	}
    	if(!StringUtils.isEmpty(displaysliders)) {
    		if("true".equals(displaysliders)) {
    			slider=true;
    		}else {
    			slider=false;
    		}
    	}
    	String user=StringUtils.isEmpty(env.getProperty("reort.server.user"))?"biuser":env.getProperty("reort.server.user");;
    	String password=StringUtils.isEmpty(env.getProperty("reort.server.password"))?"biuser":env.getProperty("reort.server.password");
    	String url=StringUtils.isEmpty(env.getProperty("reort.server.url"))?"http://192.168.4.103:8084/SpagoBI/":env.getProperty("reort.server.url");
    	System.out.println("服务器用户名:"+user);
    	System.out.println("服务器密码:"+password);
    	System.out.println("服务器地址:"+url);
    	Integer documentid=-1;
    	if(!StringUtils.isEmpty(document)) {
    		documentid=Integer.parseInt(document);
    	}else {
    		if(!StringUtils.isEmpty(lable)&&!StringUtils.isEmpty(name)) {
        		DocumentsServiceProxy proxy = new DocumentsServiceProxy(user, password);
            	proxy.setEndpoint(url+"sdk/DocumentsService");
            	try {
        			SDKDocument[] documents = proxy.getDocumentsAsList(null, null, null);			
        			for (int i = 0; i < documents.length; i++) {
        				SDKDocument aDoc = documents[i];
        				if(aDoc.getLabel().equals(lable)&&aDoc.getName().equals(name)) {
        					documentid=aDoc.getId();
        				}
        			}    			
        		} catch (RemoteException e) {
        			e.printStackTrace();
        		}
        	} 
    	}    	   	
    	
    	model.put("url", url);
        model.put("user", user);
        model.put("password", password);
        model.put("documentid", documentid);
        model.put("role", "/spagobi/user");
        model.put("parameters", parameters);
        model.put("displaytoolbar", toolbar);
        model.put("displaysliders", slider);
        return "index";
    }

    
}

