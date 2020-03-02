package awd.cloud.platform.tasks.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.utils.JsbhUtil;

@Controller
@RequestMapping("/test")
public class TestController {
	

	@RequestMapping(value="/accordion",method=RequestMethod.GET)
	public String accordion() {
		
		return "test/accordion";
		
	}
}
