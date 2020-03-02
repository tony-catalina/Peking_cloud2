package awd.mis.desktop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import awd.mis.desktop.views.GlobalVar;


@Controller
public class SearchController {
	
	@RequestMapping(value = "/search", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String printWelcome(ModelMap model) {
		GlobalVar.getConfig().put("my_desktop", "\\/search\\/");
		return "search/search";
	}	
}