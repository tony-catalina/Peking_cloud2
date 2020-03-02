package awd.mis.desktop.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.SearchCriteria;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.service.AppService;


@RestController
public class AppController {
	@Autowired
	private AppService appService;
	
	/**
	 * 根据code 获取应用信息
	 */
	@GetMapping(value = "/app/get", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap get(HttpServletRequest request,@RequestParam("appcode") String appcode) {
		long start=Calendar.getInstance().getTimeInMillis();
		ResultMap result = new ResultMap();	
		result.setCode("true");		
		result.setData(appService.getApp());	
		long end=Calendar.getInstance().getTimeInMillis();
		result.setUse_time(end-start);			
		return result;		
	}
	/**
	 * 根据用户ID获取开通的应用列表
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/app/user_app", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap user_app(HttpServletRequest request,@RequestParam("userid") String userid) {
		long start=Calendar.getInstance().getTimeInMillis();
		ResultMap result = new ResultMap();	
		result.setCode("true");			
		result.setInfo("操作成功");		
		long end=Calendar.getInstance().getTimeInMillis();
		result.setUse_time(end-start);		
		return result;
	}
	
	/**
	 * 获取监所开通的应用
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/app/getJsApp", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap jsappTree(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result = new ResultMap();	
		String hasmenu=request.getParameter("menu");
		String userid=request.getParameter("userid");
		Map<String, Object> data=null;
		if("1".equals(hasmenu)) {
			data=appService.getJsMenu(currentUser.getJsbh(),userid);
		}else {
			data=appService.getJsApp(currentUser.getJsbh());
		}
		result.setCode("true");
		result.setInfo("查询成功！");
		result.setData(data);		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	
	
	
	@GetMapping(value = "/app/getsetting", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getsetting(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String appcode=request.getParameter("appcode");
		System.out.println(appcode);
		ResultMap result = new ResultMap();		
		result.setCode("true");
		result.setInfo("查询成功！");
		result.setData(appService.getAppSetting(currentUser,appcode));		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	
	
	@GetMapping(value = "/app/getforbidApp", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getforbidApp(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userid=request.getParameter("userid");
		ResultMap result = new ResultMap();		
		result.setCode("true");
		result.setInfo("查询成功！");
		result.setData(appService.getforbidApp(userid));		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	
	@GetMapping(value = "/app/getforbidMenu", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap getforbidMenu(HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userid=request.getParameter("userid");
		ResultMap result = new ResultMap();		
		result.setCode("true");
		result.setInfo("查询成功！");
		result.setData(appService.getforbidMenu(userid));		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}

	
}