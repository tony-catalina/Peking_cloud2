package awd.mis.desktop.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.config.CasProperties;
import awd.mis.desktop.model.LoginlogsModel;
import awd.mis.desktop.service.AuthorService;
import awd.mis.desktop.service.UserService;
import awd.mis.desktop.tools.CacheUtils;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.TermType;
import cn.hutool.extra.qrcode.QrCodeUtil;

@RestController
public class UserController {
	@Autowired
	private UserService userservice;
	
	@Autowired
	private CasProperties casProperties;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private LogsService logsService;
	
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping(value = "/user/logout", method = { RequestMethod.GET, RequestMethod.POST },produces = "application/json;charset=UTF-8")
	public void viewlogin(HttpSession session,HttpServletResponse response) {	
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		QueryParam param=new QueryParam();
		param.setPaging(false);
		param.and("jsbh", TermType.eq, currentUser.getJsbh())
		.and("loginname", TermType.eq, currentUser.getName())
		.and("logintime", TermType.notnull,"1")
		.and("logouttime", TermType.isnull,"1");
		ResponseMessage<PagerResult<LoginlogsModel>> respone=logsService.loginlogsList(param);
		if(respone!=null&&respone.getResult()!=null) {
			List<LoginlogsModel> list=respone.getResult().getData();
			for (LoginlogsModel loginlogsModel : list) {
				System.out.println(logsService);
				logsService.loginout(loginlogsModel.getId());
			}
		}
		CacheUtils.del("currentUser".toUpperCase());
		session.invalidate();
		try {
			response.sendRedirect(casProperties.getAppLogoutUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/user/commonJs" ,method = RequestMethod.GET,produces = "application/javascript;charset=UTF-8")
	public ModelAndView share_common(ModelMap model) {		
		return new ModelAndView("user/common_js");
	}
	//使用说明
	@RequestMapping(value = "/user/usersysm" ,method = RequestMethod.GET,produces = "application/javascript;charset=UTF-8")
	public ModelAndView usersysm(ModelMap model) {		
		return new ModelAndView("user/sysm");
	}
	
	@RequestMapping(value ="user/qrcode")
	public void  qrcode(ModelMap model,String url,HttpServletResponse response) {
		try {
			QrCodeUtil.generate(url, 183, 186,"png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value ="/user/changePassword",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  changePassword(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			String password_now=request.getParameter("password_now");
			String password_new=request.getParameter("password_new");
			
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			if(password_now.equals(currentUser.getPassword())) {
				if(!password_now.equals(password_new)) {
					result.setCode("true");
					result.setInfo("修改成功！");
					result.setData(authorService.changePassword(currentUser,password_now,password_new));
				}else {
					result.setCode("false");
					result.setData(false);
					result.setInfo("您两次输入的密码一致,请重新输入！");
				}
			}else {
				result.setCode("false");
				result.setData(false);
				result.setInfo("原密码错误！");
			}			
			return result;			
	}
	/**
	 * 共享操作
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/userShare/checkByPath", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap checkByPath(HttpServletRequest request) {
		String path = request.getParameter("/desktop/新建文件夹/");
		ResultMap result=new ResultMap();		
		result.setCode("false");	
		result.setData("");		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}
	/**
	 * 共享得信息
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/userShare/set", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap set(HttpServletRequest request) {
		String path = request.getParameter("/desktop/新建文件夹/");
		String type = request.getParameter("folder");
		String name = request.getParameter("新建文件夹");
		ResultMap result=new ResultMap();		
		result.setCode("true");		
		result.setData(userservice.getdata());		
		result.setInfo(userservice.getInfo()); 		
		result.setUse_time(Calendar.getInstance().getTimeInMillis());		
		return result;
	}

	/**
	 * 删除用户
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/user/delete", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap userDelete(HttpServletRequest request){
		String id = request.getParameter("id");
		ResponseMessage<Integer> code = managerService.userDel(id);
		ResultMap result=new ResultMap();
		if(code.getStatus() == 200) {
			result.setCode("true");
			result.setInfo("删除成功");
		}else {
			result.setCode("false");
			result.setInfo("删除失败");
		}
		return result;
	}

}
