package awd.mis.appstore.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.model.AppModel;
import awd.mis.appstore.model.ApplunboModel;
import awd.mis.appstore.model.InterfaceModel;
import awd.mis.appstore.model.UserinfoModel;
import awd.mis.appstore.service.AppService;
import awd.mis.appstore.service.UserService;
import awd.mis.appstore.tools.Base64Utils;
import awd.mis.appstore.tools.JSONUtil;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import awd.mis.appstore.tools.TermType;

@Controller
public class HtmlController {
	
	@Autowired
	private AppService appService;
	@Autowired
	private UserService userService;
	@Autowired
	private ManagerService managerService;
	
	@Value("${awd.api.url}")
	private String apiurl;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String appstore(ModelMap model,HttpServletRequest request) {
		String session=request.getParameter("session");
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		model.put("user", userinfo);
		if(!StringUtils.isEmpty(session)) {
			Map<String, String> user = new HashMap<String, String>();	
			String userinfostr=Base64Utils.decodeStr(session);
			userinfo=JSONUtil.toBean(userinfostr, UserinfoModel.class);
			request.getSession().setAttribute("currentUser", userinfo);
			user.put("loginname",userinfo.getLoginname());
			user.put("jsbh",userinfo.getJsbh());
			try {
				List<ApplunboModel> list = appService.getLunboUrl();
				model.addAttribute("lunbolist", list);
			}catch (Exception e) {
				e.printStackTrace();
			}
//			return "user";
			return "index";
		}else {
			//List<AppModel> list=appService.getTuiJian();
			try {
				List<ApplunboModel> list = appService.getLunboUrl();
				model.addAttribute("lunbolist", list);
			}catch (Exception e) {
				e.printStackTrace();
			}
		
//			return "appstore";	
			return "index";	
		}
		
		
	}
	//根据appId查询app信息和评论
	@RequestMapping(value="/appModel")
	public ModelAndView appmodel(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String appCode = request.getParameter("appcode");
		model.addAttribute("app", appService.getAppByCode(appCode));
		if(userinfo!=null) {
			model.addAttribute("jsbh", userinfo.getJsbh());
			model.addAttribute("glybz", userinfo.getGlybz());
			model.addAttribute("userName", userinfo.getLoginname());
			model.addAttribute("userType",userinfo.getUsertype());
		}else {
			model.addAttribute("jsbh", "1");
			model.addAttribute("glybz", "2");
			model.addAttribute("userName", "");
			model.addAttribute("userType","");
		}
	    return new ModelAndView("appModel/index");
	}
	
	@RequestMapping(value="/appModel_user_app")
	public ModelAndView userAppModel(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		model.put("user", userinfo);
		model.put("userName", userinfo.getLoginname());
		String id=request.getParameter("id");
		model.addAttribute("app", appService.getApp(id));
		return new ModelAndView("appModel/user_app_info");
	}
	
	@RequestMapping(value = "/manufacturerSetting", method = RequestMethod.GET)
	public String manufacturerSetting(ModelMap model) {
		Map<String, String> user = new HashMap<String, String>(); 
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();  
		String session=request.getParameter("session");
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		model.put("user", userinfo);
		model.put("userName", userinfo.getLoginname());
		System.err.println(userinfo.getUsertype()+"-------------------------------");
		if("9".equals(userinfo.getUsertype())){
			return "administrator";	
		}else if("8".equals(userinfo.getUsertype())) {
			return "manufacturer";			
		}else{
			return "user";
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(ModelMap model,HttpServletRequest request) {	
		String type = request.getParameter("type");
		String jsbh = request.getParameter("jsbh");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		if("02".equals(type))jsbh="999999999"; //软件供应商
		if("03".equals(type))jsbh="000000000"; //系统管理员
		System.out.println("登录状态:"+jsbh);
		UserinfoModel loginuser=userService.login(jsbh,name,pwd);
		
		List<AppModel> list=appService.getTuiJian();
		model.addAttribute("lunbolist", list);
		if(loginuser!=null) {
			request.getSession().setAttribute("currentUser", loginuser);	
			model.put("user", loginuser);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/applyApi", method = RequestMethod.GET)
	public String applyApi(@RequestParam("id") String appId,ModelMap map) {
		AppModel appEntity=null;
		for(int i=1;i<15;i++) {
			appEntity=new AppModel("1000000000000000000000"+i);
			appEntity.setAppcode("0000"+i);
			appEntity.setCreatetime(new Date());
			appEntity.setCreator("zhoujian");
			//appEntity.setDesc("app"+i);
			appEntity.setFlag("activity");
			appEntity.setName("app"+i+i+i);
			appEntity.setPic1("images/banner.jpg");
			appEntity.setPic2("IMG_1836_"+(23+i)+".png");
			appEntity.setPic3("IMG_1836_"+(23+i)+".png");
			appEntity.setState("R2");
			appEntity.setType("0101q");
			appEntity.setVersion("1.01");
			appEntity.setZone("china");
			if(appEntity.getId().equals(appId)) {
				map.put("app", appEntity);
				break;
			}
		}
		return "applyApi";
	}
	
	@RequestMapping(value = "/desktop", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		return "desktop/desktop";
	}
	
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ModelAndView viewlogin(ModelMap model,HttpServletRequest request) {	
		request.getSession().removeAttribute("currentUser");
		return new ModelAndView("redirect:/"); 
	}
	
	
	@RequestMapping(value = "/user/common_js" ,method = RequestMethod.GET)
	public ModelAndView share_common(ModelMap model) {		
		return new ModelAndView("user/common_js");
	}
	
	@RequestMapping(value = "/jkwd", method = RequestMethod.GET)
	public ModelAndView jkwd(ModelMap model) {
		model.addAttribute("apiurl", apiurl);		
		return new ModelAndView("apiCloud");
	}

	//根据appId查询app信息和评论
	@RequestMapping(value="/appXq")
	public ModelAndView appXq(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String appCode = request.getParameter("appcode");
		String flag = request.getParameter("flag");
		String type = request.getParameter("type");
		AppModel appModel = appService.getAppByCode(appCode);
		model.addAttribute("app", appModel);
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\AWDImgFile";
		File file=new File(path);
		if(!file.exists()){//如果文件夹不存在
			file.mkdirs();//创建文件夹
		}
		System.err.println("user.dir=="+path);
		try {
			if(appModel.getPic1()=="") {
				String url = "AWDImgFile"+"\\"+appModel.getId()+"pic1.jpg";
				Base64Utils.byteArrayToFile(appModel.getP1(),url);
				model.addAttribute("pic1", url);
			}else {
				model.addAttribute("pic1", appModel.getPic1());
			}
			if(appModel.getPic2()=="") {
				String url = "AWDImgFile"+"\\"+appModel.getId()+"pic2.jpg";
				Base64Utils.byteArrayToFile(appModel.getP2(),url);
				model.addAttribute("pic2", url);
			}else {
				model.addAttribute("pic2", appModel.getPic2());
			}
			if(appModel.getPic3()=="") {
				String url = "AWDImgFile"+"\\"+appModel.getId()+"pic3.jpg";
				Base64Utils.byteArrayToFile(appModel.getP3(),url);
				model.addAttribute("pic3", url);
			}else {
				model.addAttribute("pic3", appModel.getPic3());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(userinfo!=null) {
			model.addAttribute("jsbh", userinfo.getJsbh());
			model.addAttribute("glybz", userinfo.getGlybz());
			model.addAttribute("userName", userinfo.getLoginname());
			model.addAttribute("userType",userinfo.getUsertype());
			model.addAttribute("flag",flag);
		}else {
			model.addAttribute("jsbh", "1");
			model.addAttribute("glybz", "2");
			model.addAttribute("userName", "");
			model.addAttribute("userType","");
			model.addAttribute("flag",flag);
		}
		if(type != null && type != "" && type.equals("1")) {
			return new ModelAndView("extendC/appXq");
		}else {
			return new ModelAndView("extendC/index");
		}
	}
}
