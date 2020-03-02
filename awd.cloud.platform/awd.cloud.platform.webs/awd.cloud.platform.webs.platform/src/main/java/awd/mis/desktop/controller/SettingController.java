package awd.mis.desktop.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import awd.framework.common.utils.StringUtils;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.ResultMap;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.service.SettingService;
import awd.mis.desktop.tools.CacheUtils;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
public class SettingController {
	
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private ManagerService managerService;
	
	private static String  name;
	private static String  jsbh;
	
	
	@RequestMapping(value = "/setting", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public ModelAndView setting(ModelMap model) {		
		return new ModelAndView("setting/setting");
	}
	/**
	 * 系统设置选项--个人中心
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/setting/slider",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap slider(ModelMap model,HttpServletRequest request) {
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ResultMap result=new ResultMap();
		String slider=request.getParameter("slider");
		Map<String, Object> data=settingService.getSetting(slider);	
		String  about =settingService.getAbout();
		String  help =settingService.getHelp();
		result.setCode("true");	
		if("about".equals(slider)) {
			result.setData(about);
		}else if("help".equals(slider)){
			result.setData(help);
		}else {
			result.setData(data);
		}	
		result.setInfo("获取成功！");
		result.setUse_time(Calendar.getInstance().getTimeInMillis());
		return result;
	}

	
	@RequestMapping(value ="/setting/set",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  setting(ModelMap model,HttpServletRequest request,HttpServletResponse response) {
			String k=request.getParameter("key");
			String v=request.getParameter("v");
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			boolean saveSucces=settingService.set(currentUser,k,v);
			result.setCode(saveSucces?"true":"false");
			result.setData(saveSucces?"修改已生效！":"修改失败！");
			return result;			
	}
	
	@RequestMapping(value ="/setting/system_setting",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  system_setting(ModelMap model,HttpServletRequest request,HttpServletResponse response) {		
			Object data=request.getParameter("data");
			JSONObject json=JSONUtil.parseObj(data);
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			boolean saveSucces=settingService.setSetting(currentUser,json);
			result.setCode(saveSucces?"true":"false");
			result.setData(saveSucces?"保存成功！":"保存失败");
			return result;			
	}
	
	@RequestMapping(value ="/setting/user_setting",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResultMap  user_setting(ModelMap model,HttpServletRequest request,HttpServletResponse response) {		
			Object data=request.getParameter("data");
			JSONObject json=JSONUtil.parseObj(data);
			String appcode=request.getParameter("appcode");
			System.out.println(appcode);
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ResultMap result=new ResultMap();
			boolean saveSucces=settingService.setSetting(currentUser,appcode,json);
			result.setCode(saveSucces?"true":"false");
			result.setData(saveSucces?"保存成功！":"保存失败");
			return result;			
	}
	
	@RequestMapping(value ="/setting/getUserSetting",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String,String>  getUserSetting(HttpServletRequest request){
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String,String> map = new HashMap<>();
		String key = request.getParameter("key");
		QueryParam qParam = new QueryParam();
		qParam.and("key", key);
		qParam.and("userid", currentUser.getId());
		qParam.and("app", "eyJ0eXBlIjoiYXBwIiwid2lkdGgiOiIiLCJoZWlnaHQiOiIiLCJjb250ZW50IjoiY29yZS5kZXNrdG9wKCk7In0=");
		qParam.and("jsbh", currentUser.getJsbh());
		ResponseMessage<PagerResult<UsersettingModel>>  rm=managerService.userSettingQuery(qParam);
		if(rm.getResult().getTotal() != 0) {
		List<UsersettingModel> l = rm.getResult().getData();
		QueryParam param = new QueryParam();
		if(!"0".equals(l.get(0).getValue())) {
		param.and("url",l.get(0).getValue());
		ResponseMessage<PagerResult<AppModel>> apprm= managerService.appQuery(param);
		System.err.println(l.get(0).getValue());
		map.put("path", l.get(0).getValue());
		map.put("icon", "./images/file_icon/icon_app/"+apprm.getResult().getData().get(0).getIcon());
		map.put("height", "100%");
		map.put("width", "100%");
		map.put("isParent", "false");
		map.put("isReadable", "1");
		map.put("isWriteable", "1");
		map.put("name", apprm.getResult().getData().get(0).getName());
		map.put("resize", "1");
		map.put("simple", "0");
		map.put("size", "0");
		map.put("ext","oexe");
		map.put("content", l.get(0).getValue());
		map.put("type", "url");
		}else {
			map.put("path","0");
		}
		}else {
			map.put("path","0");
		}
		return map;
	}
	
	/**
	 * 判断登陆用户是否和上次的一样
	 * @param request
	 * @return
	 */
	@RequestMapping(value ="/setting/getLoginUser",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public boolean  getLoginUser(HttpServletRequest request){
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//首先获取缓存
		String json=(String) CacheUtils.get().getKey("currentUser".toUpperCase());
		System.err.println(json+"-------");
		System.err.println(json+"-------");
		System.err.println(json+"-------");
		//如果没有该说明没用户登陆过
		if(!StringUtils.isNullOrEmpty(json)) {
			String [] str = json.replace("{", "").replace("}", "").split(",");
			//如果该缓存和当前用户的登陆信息一致，则为一个用户
			if(currentUser.getName().equals(str[0]) && currentUser.getJsbh().equals(str[1])) {
				return true;
			}else {
				Map<String,String> map  = new HashMap<>();
				map.put("jsbh", currentUser.getJsbh());
				map.put("name", name = currentUser.getName());
				CacheUtils.set("currentUser".toUpperCase(), map.toString());
				return false;
			}
		}else {
		Map<String,String> map  = new HashMap<>();
		map.put("jsbh", currentUser.getJsbh());
		map.put("name", name = currentUser.getName());
		CacheUtils.set("currentUser".toUpperCase(), map.toString());
			return true;
		}
	}
	  
}
