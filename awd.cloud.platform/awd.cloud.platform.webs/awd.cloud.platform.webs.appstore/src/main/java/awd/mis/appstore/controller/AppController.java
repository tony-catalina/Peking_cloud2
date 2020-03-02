package awd.mis.appstore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import awd.mis.appstore.model.*;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import awd.mis.appstore.api.ManagerService;
import awd.mis.appstore.service.AppService;
import awd.mis.appstore.tools.PagerResult;
import awd.mis.appstore.tools.QueryParam;
import awd.mis.appstore.tools.ResponseMessage;
import awd.mis.appstore.tools.TermType;


@RestController
public class AppController {
	
	@Autowired
	private AppService appService;
	@Autowired
	private ManagerService managerService;
	

	private List<UserinfoModel> users;
	
	@RequestMapping(value = "/search/api/getSearchResult")
	public @ResponseBody Map<String, Object> getSearchResultViaAjax(@RequestBody SearchCriteria search) {

		Map<String, Object> result = new HashMap<>();
		if (isValidSearchCriteria(search)) {
			List<UserinfoModel> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());
			if (users.size() > 0) {
				result.put("code","200");
				result.put("msg","");
				result.put("result",users);
			} else {
				result.put("code","204");
				result.put("msg","No user!");
			}

		} else {
			result.put("code","400");
			result.put("msg","Search criteria is empty!");
		}

		return result;

	}

	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;
		if (search == null) {
			valid = false;
		}else {
			if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
				valid = false;
			}
		}		
		return valid;
	}

	// Simulate the search function
	private List<UserinfoModel> findByUserNameOrEmail(String username, String email) {
		List<UserinfoModel> result = new ArrayList<UserinfoModel>();
		for (UserinfoModel user : users) {
			if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {
				if (username.equals(user.getLoginname()) && email.equals(user.getEmail())) {
					result.add(user);
					continue;
				} else {
					continue;
				}
			}
			if (!StringUtils.isEmpty(username)) {
				if (username.equals(user.getLoginname())) {
					result.add(user);
					continue;
				}
			}

			if (!StringUtils.isEmpty(email)) {
				if (email.equals(user.getEmail())) {
					result.add(user);
					continue;
				}
			}

		}

		return result;

	}
	
	@RequestMapping(value = "/apps", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage<PagerResult<AppModel>> allApps(HttpServletRequest request) {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  	
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String jsbh="";
		String appname=request.getParameter("namelike");
		if(appname != null) {
			appname = "%" + appname + "%";
		}else {
			appname = "%";
		}
		String classfic=request.getParameter("classfic");
		String[] rolename=request.getParameterValues("rolename");
		String rolenames = "";
		if (rolename !=null) {
			for (int i = 0; i < rolename.length; i++) {
				rolenames = rolenames + "," + rolename[i];
			}
			rolenames = rolenames.substring(1, rolenames.length());
		}
		String feature=request.getParameter("fruit");
		if(userinfo!=null) {
			jsbh=userinfo.getJsbh();
		}		
		ResponseMessage<PagerResult<AppModel>> list;	
		if (pageIndex == null || pageSize == null) {
			System.err.println("----无分页");
			list=appService.findAll(jsbh,appname,classfic,rolenames,feature);		
		}else {
			System.err.println("----有分页");
			list=appService.findAllWithPage(jsbh,appname,classfic,rolenames,feature,pageIndex,pageSize);		
		}
		
		return list;
	}
	
	public List<?> groupAppByZone(List<AppModel> list ,String zone){//根据传入的监所类别zone值，分组app
		List<AppModel> appList = new ArrayList();
		list.forEach(app->{
			if (app.getZone().contains(zone)) {
				appList.add(app);
			}
		});
		return appList;
	}
	
	
	
	@RequestMapping(value = "/getAppModelById", method = RequestMethod.POST)
	public @ResponseBody AppModel getAppModelById(String id) {
		AppModel app = appService.getApp(id);
		return app;
	}
	
	@RequestMapping(value = "/savePl", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage<String> savePl(PlModel plModel) { // 添加评论
		return appService.savePl(plModel);
	}
	
	@RequestMapping(value = "/getAppPl", method = RequestMethod.POST)
	public ResponseMessage<PagerResult<PlModel>> getAppPl(HttpServletRequest request) { // 获取评论
		UserinfoModel userinfo=(UserinfoModel) request.getSession().getAttribute("currentUser");
		QueryParam queryParam = new QueryParam();
		String appcode =request.getParameter("appcode");
		String pageIndex =request.getParameter("pageIndex");
		String pageSize =request.getParameter("pageSize");
		if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(appcode)) {
			queryParam.and("appcode",TermType.eq,appcode);
		}
		if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(pageIndex)) {
			queryParam.setPageIndex(Integer.valueOf(pageIndex).intValue());
		}
		if(!awd.framework.common.utils.StringUtils.isNullOrEmpty(pageSize)) {
			queryParam.setPageSize(Integer.valueOf(pageSize).intValue());
		}
		ResponseMessage<PagerResult<PlModel>> list = appService.getAppPl(queryParam);
		return list;
	}
	
	@PostMapping("/getAppEntyListByJsbh")
	public ResponseMessage<PagerResult<AppModel>> getAppEntyListByJsbh(String pageIndex,String pageSize,String jsbh) {
		ResponseMessage<PagerResult<AppModel>> list = appService.getAppEntyListByJsbh(pageIndex,pageSize,jsbh);
		return list;
	}
	
	@RequestMapping(value = "/getJsTypeByGroups", method = RequestMethod.POST)
	public List<Map<String, Object>> getJsTypeByGroups(HttpServletRequest request) {	
		String type = request.getParameter("id");
		int t = 0;
		if(type !=null && type !="") {
			t = Integer.parseInt(type)-1;
		}
		ResponseMessage<List<Map<String, Object>>> list=managerService.getJsTypeByGroups(t+"");
		List<Map<String, Object>> list1 = list.getResult();
		List<Map<String, Object>> result = new ArrayList<>();
		if(list1.size()>0) {
			for(int i=0;i<list1.size();i++) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", list1.get(i).get("jsbh"));
				map.put("name", list1.get(i).get("name"));
				result.add(map);
			}
		}
		return result;
	}

	/**
	 * 查询菜单信息
	 * @param request
	 * @return
	 */
	@GetMapping (value = "/getMenus")
	public ResponseMessage<PagerResult<MenusModel>> getMenus(HttpServletRequest request) {
		String appcode = request.getParameter("appcode");
		String row = request.getParameter("rows");
		String page = request.getParameter("page");
		System.err.println("row: "+row);
		System.err.println("page: "+page);
		System.err.println(row);
		System.err.println(page);
		System.err.println(row);
		System.err.println(page);
		QueryParam queryParam = new QueryParam();
		queryParam.and("appcode",appcode);
		if(StringUtils.isEmpty(row) && StringUtils.isEmpty(page)){
			queryParam.setPageIndex(0);
			queryParam.setPageSize(10);
		}else {
			queryParam.setPageIndex(Integer.parseInt(page)-1);
			queryParam.setPageSize(Integer.parseInt(row));
		}
		System.err.println(JSONObject.toJSONString(queryParam));
		ResponseMessage<PagerResult<MenusModel>> list = managerService.getMenus(queryParam);
		return list;
	}

	/**
	 * 查询系统参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getSetting")
	public ResponseMessage<PagerResult<SettingModel>> getSetting(HttpServletRequest request) {
		String appcode = request.getParameter("appcode");
		String row = request.getParameter("rows");
		String page = request.getParameter("page");
		System.err.println("appcode: "+appcode);
		QueryParam queryParam = new QueryParam();
		queryParam.and("appcode",appcode);
		queryParam.setPaging(false);
		if(StringUtils.isEmpty(row) && StringUtils.isEmpty(page)){
			queryParam.setPageIndex(0);
			queryParam.setPageSize(10);
		}else {
			queryParam.setPageIndex(Integer.parseInt(page)-1);
			queryParam.setPageSize(Integer.parseInt(row));
		}
		ResponseMessage<PagerResult<SettingModel>> list = managerService.getSetting(queryParam);
		return list;
	}

	/**
	 * 查询api请求
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getApiUrl")
	public ResponseMessage<PagerResult<InterfaceModel>> getApiUrl(HttpServletRequest request) {
		String appcode = request.getParameter("appcode");
		String row = request.getParameter("rows");
		String pages = request.getParameter("page");
		String page = Integer.valueOf(pages)*Integer.valueOf(row)-Integer.valueOf(row)+"";
		System.err.println("appcode: "+appcode);
		QueryParam queryParam = new QueryParam();
		queryParam.and("appcode",appcode);
		queryParam.setPaging(false);
		ResponseMessage<PagerResult<InterfaceModel>> list = managerService.getinterfaceByAppcode(appcode,"2",page,row);
		return list;
	}

}
