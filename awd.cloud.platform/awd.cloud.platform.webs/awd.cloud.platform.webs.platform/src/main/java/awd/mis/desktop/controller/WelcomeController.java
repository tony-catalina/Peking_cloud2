package awd.mis.desktop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import awd.mis.desktop.bean.User;
import cn.hutool.core.util.StrUtil;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class WelcomeController {
	@Autowired
	EnvironmentManager environmentManager;
	

	@RequestMapping("/")
	public String printWelcome(ModelMap model,HttpServletRequest request) {
		User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("username",users.getUsername());
		request.getSession().setAttribute("userInfo",users.getJsbh()+"&"+users.getUsername());
		
		return "forward:/desktop?path=\\/桌面\\/";
	}
	
	
	@RequestMapping("/myerror")
	public String error(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
//		String loginurl=environmentManager.getProperty("cas.server.host.login_url").toString();
//		System.out.println(loginurl);
//		try {
//			response.sendRedirect("");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return "forward:/user/logout";
	}
	
	
	@RequestMapping("/getPdfFile")	
	public void getRemoteFile(@RequestParam("url")String url, HttpServletResponse response) {  
	        InputStream inputStream = null;  
	        try {  
	            try {  
	                String strUrl = url.trim();
	                //System.err.println(strUrl);
	                URL urll=new URL(strUrl);
	                //打开请求连接
	                URLConnection connection = urll.openConnection();
	                HttpURLConnection httpURLConnection=(HttpURLConnection) connection;
	                httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	                // 取得输入流，并使用Reader读取
	                inputStream = httpURLConnection.getInputStream();
	                int bytesum = 0;
	                int byteread = 0;
	                byte[] buffer = new byte[1024];
	                // 清空response
	                response.reset();
	                // 设置response的Header
	                response.addHeader("Content-Disposition", "attachment;filename=" + new String("cbzm.pdf".getBytes()));
	                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	                response.setContentType("application/octet-stream");
	                bytesum = 0;
	                byteread = 0;
	                buffer = new byte[1024];
	                while ((byteread = inputStream.read(buffer)) != -1) {
	                    bytesum += byteread;
	                    toClient.write(buffer, 0, byteread);
	                }
	                toClient.flush();
	                inputStream.close();
	                
	                toClient.close();
	            } catch (IOException e) {  
	                e.printStackTrace();
	                inputStream = null;  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();
	            inputStream = null;  
	        }  
	    }
	
	
	
	@RequestMapping("/webcache")
	public String cache(ModelMap model,HttpServletRequest request) {
		return "webcache/index";
	}
	
}
