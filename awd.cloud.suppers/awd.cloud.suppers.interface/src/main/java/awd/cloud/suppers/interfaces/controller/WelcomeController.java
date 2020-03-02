package awd.cloud.suppers.interfaces.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {	
	 
	
	@RequestMapping({"/"
		,"/index.html"})
	public String index(Model model,HttpServletRequest request) {
		System.err.println("index---");
		return "index";
	}
	@RequestMapping("/helloWorld.html")
	public String helloWorld(Model model,HttpServletRequest request) {
		System.err.println("helloWorld---");
		System.err.println("helloWorld-ewqe--");
		return "helloWorld";
	}
	
	@RequestMapping("/home/homePage.html")
	public String homePage(Model model,HttpServletRequest request) {
		return "home/homePage";
	}
	
	@RequestMapping("/error/404.html")
	public String error(Model model,HttpServletRequest request) {
		return "error/404";
	}
	@RequestMapping("/img/images.html")
	public String images(Model model,HttpServletRequest request) {
		return "img/images";
	}
	@RequestMapping("/links/linksAdd.html")
	public String linksAdd(Model model,HttpServletRequest request) {
		return "links/linksAdd";
	}
	@RequestMapping("/links/linksList.html")
	public String linksList(Model model,HttpServletRequest request) {
		return "links/linksList";
	}
	@RequestMapping("/message/message.html")
	public String message(Model model,HttpServletRequest request) {
		return "message/message";
	}
	@RequestMapping("/message/messageReply.html")
	public String messageReply(Model model,HttpServletRequest request) {
		return "message/messageReply";
	}
	@RequestMapping("/news/newsAdd.html")
	public String newsAdd(Model model,HttpServletRequest request) {
		return "news/newsAdd";
	}
	@RequestMapping("/news/newsList.html")
	public String newsList(Model model,HttpServletRequest request) {
		return "news/newsList";
	}
	@RequestMapping("/systemParameter/systemParameter.html")
	public String systemParameter(Model model,HttpServletRequest request) {
		return "systemParameter/systemParameter";
	}
	@RequestMapping("/user/addUser.html")
	public String addUser(Model model,HttpServletRequest request) {
		return "user/addUser";
	}
	@RequestMapping("/user/allUsers.html")
	public String allUsers(Model model,HttpServletRequest request) {
		return "user/allUsers";
	}
	@RequestMapping("/user/changePwd.html")
	public String changePwd(Model model,HttpServletRequest request) {
		return "user/changePwd";
	}
	@RequestMapping("/user/userInfo.html")
	public String userInfo(Model model,HttpServletRequest request) {
		return "user/userInfo";
	}

}
