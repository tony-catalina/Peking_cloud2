package awd.cloud.platform.tasks.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.utils.JsbhUtil;

@Controller
public class WelcomeController {
	

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String welcome() {
		User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if("000000000".equals(users.getJsbh())) {
			return "index";
		}else {
			return "error";
		}		
	}
	
	@RequestMapping(value="/tasks",method=RequestMethod.GET)
	public String tasks() {
		User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "tasks/index";		
	}
}
