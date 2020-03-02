package awd.cloud.suppers.finger.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {	
	 
	
	@RequestMapping("/index1.html")
	public String index(Model model,HttpServletRequest request) {
			return "hongda_index";
	}
	@RequestMapping("/index2.html")
	public String logout(Model model,HttpServletRequest request) {
			return "yisuo_index";
	}

}
