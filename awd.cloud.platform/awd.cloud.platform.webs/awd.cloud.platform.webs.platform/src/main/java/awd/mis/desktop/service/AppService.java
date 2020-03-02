package awd.mis.desktop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.App;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.GroupappModel;
import awd.mis.desktop.model.GroupmenusModel;
import awd.mis.desktop.model.JsappModel;
import awd.mis.desktop.model.MenusModel;
import awd.mis.desktop.model.SettingModel;
import awd.mis.desktop.model.UserappModel;
import awd.mis.desktop.model.UsergroupModel;
import awd.mis.desktop.model.UsermenuModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.TermType;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;

@Service("appService")
public class AppService{
	@Autowired
	private  ManagerService managerService;	
	@Autowired
    private DiscoveryClient client;
	
	public List<App> getApp(User currentUser) {
		List<App> list=new ArrayList<App>();
		
		List<AppModel> applist=currentUser.getApps();
		if(applist!=null) {
			for (AppModel appModel : applist) {
				App app=new App();
				if("1".equals(app.getType())) {
					app.setName(appModel.getName()+".oexe");
					app.setExt("oexe");
				}else {
					app.setName(appModel.getName()+".exe");
					app.setExt("exe");
				}
				
				String menutype="1";
	        	List<UsersettingModel> usersetting=currentUser.getSetting();
	        	for (UsersettingModel usersettingModel : usersetting) {
					if(appModel.getAppcode().equals(usersettingModel.getApp())) {
						if("menue_type".equals(usersettingModel.getKey())) {
							menutype=usersettingModel.getValue();
						}
					}
				}
	        	
				app.setPath(appModel.getUrl());
				app.setType("url");
				app.setMode("-rw- rw- rw-(0666)");
				app.setAtime(DateUtil.formatDate(appModel.getCreatetime()));
				app.setCtime(DateUtil.formatDate(appModel.getUpdatetime()));
				app.setMtime(DateUtil.formatDate(Calendar.getInstance().getTime()));
				app.setIsReadable("1");
				app.setIsWriteable("0");
				app.setSize("0");
				app.setIsParent("false");
//				System.out.println(appModel.getUrl().indexOf("http"));
				if(appModel.getUrl().indexOf("http")>-1) {
					app.setContent(appModel.getUrl()+"?name="+menutype);	
				}else {
					 List<ServiceInstance> servelist = client.getInstances(appModel.getUrl());
				        if (list != null && servelist.size() > 0 ) {				        	
				        	app.setContent("http://"+servelist.get(RandomUtil.randomInt(servelist.size())).getUri().getHost()
				        			+":"+servelist.get(RandomUtil.randomInt(servelist.size())).getUri().getPort()
				        			+"?name="+menutype);
				        }
				}	
				
				app.setIcon(appModel.getIcon());
				app.setWidth("max");
				app.setHeight("max");
				app.setSimple("0");
				app.setResize("1");
				list.add(app);
			}
		}		
		return list;
	}

	public App getApp(String type, String path) {
		App app=new App();
		app.setName("管教办公.oexe");
		app.setPath("http://192.168.4.98:9002/index.html?name=2");
		app.setExt("oexe");
		app.setType("url");
		app.setMode("-rw- rw- rw-(0666)");
		app.setAtime(DateUtil.formatDate(Calendar.getInstance().getTime()));
		app.setCtime(DateUtil.formatDate(Calendar.getInstance().getTime()));
		app.setMtime(DateUtil.formatDate(Calendar.getInstance().getTime()));
		app.setIsReadable("1");
		app.setIsWriteable("1");
		app.setSize("210");
		app.setIsParent("false");
		app.setContent("http://192.168.4.98:9002/index.html?name=2");
		app.setIcon("gjyy.png");
		app.setWidth("max");
		app.setHeight("max");
		app.setSimple("0");
		app.setResize("1");
		app.setDownload_path("http://192.168.4.98:9002/index.html?name=2");
		app.setFile_md5("1233455666677");
		return app;
	}
	
	public Map<String, Object> getApp() {
		Map<String, Object> data=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		Map<String, Object> folder=new HashMap<>();
		Map<String, Object> maps=new HashMap<>();
		folder.put("content", "http://baidu365.duapp.com/wnl.html?bd_user=855814346&bd_sig=a64e6e262e8cfa1c42dd716617be2102&canvas_pos=platform");
		folder.put("desc", "365日历");
		folder.put("group", "life");
		folder.put("height", "440");
		folder.put("icon", "365.png");
		folder.put("name", "365日历");
		folder.put("resize", "1");
		folder.put("simple", "0");
		folder.put("type", "url");
		folder.put("width", "544");
		maps.put("365日历", folder);
		map.put("content", "window.open(\"https://www.icloud.com/\");");
		map.put("desc", "icloud");
		map.put("group", "others");
		map.put("height", "600");
		map.put("icon", "icloud.png");
		map.put("name", "icloud");
		map.put("resize", "1");
		map.put("simple", "0");
		map.put("type", "app");
		map.put("width", "800");
		maps.put("icloud", map);
		data.put("data", maps);
		return data;
	}

	public Map<String, Object> getJsApp(String jsbh) {
		//获取该监所拥有的app时，当用户的不是管理员时，他的监所app应该排除groupapp和userapp
		User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		QueryParam filter=new QueryParam();
		//如果该用户不是管理员用户
		if(currentUser.getUserinfo().getGlybz().equals("0")) {
			QueryParam qParam=new QueryParam();
			qParam.and("groupid", currentUser.getGroup().getId());
			qParam.setPaging(false);
			ResponseMessage<PagerResult<GroupappModel>> rm = managerService.groupAppQuery(qParam);
			//当该用户组有禁用的应用时，就不查询该应用
			if(rm.getResult().getTotal() != 0) {
				for(GroupappModel model:rm.getResult().getData()) {
					filter.and("appcode", TermType.not, model.getAppcode());
				}
			}
			//当该用户有被禁止的应用时
			QueryParam qp=new QueryParam();
			qp.and("userid", currentUser.getId());
			qp.setPaging(false);
			ResponseMessage<PagerResult<UserappModel>> userrm = managerService.userAppQuery(qp);
			if(userrm.getResult().getTotal() != 0) {
				for(UserappModel model:userrm.getResult().getData()) {
					filter.and("appcode", TermType.not, model.getAppcode());
				}
			}
		}
		Map<String, Object> treeList=new HashMap<>();
		filter.and("jsbh", TermType.eq, jsbh);
		ResponseMessage<PagerResult<JsappModel>> respone=managerService.jsAppQuery(filter);
		if(respone.getStatus()==200&&respone.getResult()!=null) {
			List<JsappModel> jsapplist=respone.getResult().getData();
			System.out.println("监所已绑定的应用数："+respone.getResult().getData().size());
			String appcodes="";
			for (int i=0;i<jsapplist.size();i++) {
				appcodes+=jsapplist.get(i).getAppcode()+",";
			}
			QueryParam params=new QueryParam();
			params.and("appcode", TermType.in, appcodes.length()>0?appcodes:"无绑定应用");
			params.setPaging(false);
			ResponseMessage<PagerResult<AppModel>> apprespone=managerService.appQuery(params);
			if(apprespone.getStatus()==200&&apprespone.getResult()!=null) {
				List<AppModel> applist=apprespone.getResult().getData();
				for (AppModel appModel : applist) {
					Map<String, Object> app=new HashMap<>();		
					app.put("id", appModel.getAppcode());
					app.put("name", appModel.getName());
					app.put("parent_id", appModel.getType());
					treeList.put((String) appModel.getAppcode(), app);
					if("1".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "1");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					if("2".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "2");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					if("3".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "3");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					if("4".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "1");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					if("5".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "5");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					if("0".equals(appModel.getType())){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "0");
						kss.put("name", currentUser.getGroup().getGroupname());
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}
					
				}
			}			
		}	
		
		return treeList;
	}
	
	public Map<String, Object> getJsMenu(String jsbh,String userid) {
		//获取该监所拥有的app时，当用户的不是管理员时，他的监所app应该排除groupapp和userapp
				User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				QueryParam filter=new QueryParam();
				QueryParam ap=new QueryParam();
				ap.and("userid", userid);
				ResponseMessage<PagerResult<UsergroupModel>> um = managerService.userGroupQuery(ap);
				//如果该用户不是管理员用户
					QueryParam qParam=new QueryParam();
					if(um.getResult().getTotal() != 0) {
						qParam.and("groupid", um.getResult().getData().get(0).getGroupid());
					}
					qParam.setPaging(false);
					System.err.println("groupid"+um.getResult().getData().get(0).getGroupid());
					ResponseMessage<PagerResult<GroupappModel>> rm = managerService.groupAppQuery(qParam);
					//当该用户组有禁用的应用时，就不查询该应用
					if(rm.getResult().getTotal() != 0) {
						for(GroupappModel model:rm.getResult().getData()) {
							filter.and("appcode", TermType.not, model.getAppcode());
						}
					}
					//当该用户有被禁止的应用时
					QueryParam qp=new QueryParam();
					qp.and("userid", currentUser.getId());
					qp.setPaging(false);
					ResponseMessage<PagerResult<UserappModel>> userrm = managerService.userAppQuery(qp);
					if(userrm.getResult().getTotal() != 0) {
						for(UserappModel model:userrm.getResult().getData()) {
							filter.and("appcode", TermType.not, model.getAppcode());
						}
					}
		Map<String, Object> treeList=new HashMap<>();
		filter.and("jsbh", TermType.eq, jsbh);
		System.err.println(JSONObject.toJSONString(filter)+"---");
		System.err.println(JSONObject.toJSONString(filter)+"---");
		System.err.println(JSONObject.toJSONString(filter)+"---");
		ResponseMessage<PagerResult<JsappModel>> respone=managerService.jsAppQuery(filter);
		if(respone.getStatus()==200&&respone.getResult()!=null) {
			//查询监所安装的app
			List<String> list= new ArrayList<>();
			List<JsappModel> jsapplist=respone.getResult().getData();
			String  appcodes="";
			for (int i=0;i<jsapplist.size();i++) {
				list.add(jsapplist.get(i).getAppcode());
			}
			System.out.println(JSONUtil.toJsonStr(list));
			//查询用户禁止的app
			QueryParam userappfilter=new QueryParam();
			userappfilter.setPaging(false);
			userappfilter.and("userid", TermType.eq, userid);
			ResponseMessage<PagerResult<UserappModel>>userappRespone=managerService.userAppQuery(userappfilter);
			if(userappRespone.getStatus()==200&&userappRespone.getResult()!=null) {				
				List<UserappModel> userapplist=userappRespone.getResult().getData();					
				for (UserappModel userappModel : userapplist) {
					list.remove(userappModel.getAppcode());					
				}
			}
			
			//查询没有禁止的所有app
			for (int i=0;i<list.size();i++) {
				appcodes+=list.get(i)+",";
			}
			System.err.println("--------------所有app----------------------");
			System.err.println(list.size());
			QueryParam params=new QueryParam();
			params.and("appcode", TermType.in, appcodes.length()==0?"无应用":appcodes);
			params.setPaging(false);
			ResponseMessage<PagerResult<AppModel>> apprespone=managerService.appQuery(params);
			if(apprespone.getStatus()==200&&apprespone.getResult()!=null) {
				List<AppModel> applist=apprespone.getResult().getData();
				for (AppModel appModel : applist) {
					Map<String, Object> app=new HashMap<>();		
					app.put("id", appModel.getAppcode());
					app.put("name", appModel.getName());
					app.put("parent_id", appModel.getType());
					treeList.put((String) appModel.getAppcode(), app);
					if(appModel.getZone().indexOf("1")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "1");
						kss.put("name", "看守所");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}else
					if(appModel.getZone().indexOf("2")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "2");
						kss.put("name", "拘留所");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}else
					if(appModel.getZone().indexOf("3")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "3");
						kss.put("name", "戒毒所");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}else
					if(appModel.getZone().indexOf("4")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "1");
						kss.put("name", "收教所");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}else
					if(appModel.getZone().indexOf("5")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "5");
						kss.put("name", "安康医院");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}else
					if(appModel.getZone().indexOf("0")>-1){
						Map<String, Object> kss=new HashMap<>();		
						kss.put("id", "0");
						kss.put("name", "监管总队");
						kss.put("parent_id", "");
						treeList.put((String) kss.get("id"), kss);
					}		
					QueryParam usergroupparam=new QueryParam();
					usergroupparam.setPaging(false);
					usergroupparam.and("userid", TermType.eq, userid);
					ResponseMessage<PagerResult<UsergroupModel>> usergrouprepone=managerService.userGroupQuery(usergroupparam);
					if(usergrouprepone.getStatus()==200&&usergrouprepone.getResult()!=null) {
						if(usergrouprepone.getResult().getData().size()>0) {
							QueryParam groupmenuparam=new QueryParam();
							groupmenuparam.and("groupid", TermType.eq, usergrouprepone.getResult().getData().get(0).getGroupid());
							ResponseMessage<PagerResult<GroupmenusModel>> groupmenurespone=managerService.groupMenuQuery(groupmenuparam);
							
							if(groupmenurespone.getStatus()==200&&groupmenurespone.getResult()!=null) {
								System.err.println("groupmenu 有禁止数据");
								if(groupmenurespone.getResult().getData().size()>0) {
									QueryParam menusparam=new QueryParam();
									menusparam.setPaging(false);
									menusparam.and("appcode", TermType.eq, appModel.getAppcode());
									ResponseMessage<PagerResult<MenusModel>> menurespone=managerService.menusQuery(menusparam);
									if(menurespone.getStatus()==200&&menurespone.getResult()!=null) {
										System.err.println("根据appcode查询到菜单");
										List<MenusModel> menulist=menurespone.getResult().getData();
										for (MenusModel menusModel : menulist) {
											List<GroupmenusModel> groupmenuslist=groupmenurespone.getResult().getData();
											boolean ishavemenu=true;
											for (GroupmenusModel groupmenusModel : groupmenuslist) {
												System.err.println(menusModel.getMenu()+"====="+groupmenusModel.getMenuid());
												if(menusModel.getId().equals(groupmenusModel.getMenuid())) {
													ishavemenu=false;
													break;
												}
											}
											if(ishavemenu) {												
												if(!isHasSubMenu(menulist,menusModel.getMenuname())) {
													Map<String, Object> parentmenu=new HashMap<>();
													parentmenu.put("id", menusModel.getParent());
													parentmenu.put("name",menusModel.getParent());
													parentmenu.put("parent_id", menusModel.getAppcode());
													if(treeList.get((String)parentmenu.get("id"))==null) {
														treeList.put((String) parentmenu.get("id"), parentmenu);
													}
													
													Map<String, Object> menu=new HashMap<>();
													menu.put("id", menusModel.getMenu());
													menu.put("name",menusModel.getMenuname());
													menu.put("parent_id", menusModel.getParent());
													treeList.put((String) menu.get("id"), menu);												
												}else {												
													Map<String, Object> menu=new HashMap<>();
													menu.put("id", menusModel.getMenuname());
													menu.put("name",menusModel.getMenuname());
													menu.put("parent_id", menusModel.getParent());
													treeList.put((String) menu.get("id"), menu);
												}				
												
											}						
											
										}
									}					
									
								}else {
									QueryParam menusparam=new QueryParam();
									menusparam.setPaging(false);
									menusparam.and("appcode", TermType.eq, appModel.getAppcode());
									ResponseMessage<PagerResult<MenusModel>> menurespone=managerService.menusQuery(menusparam);
									if(menurespone.getStatus()==200&&menurespone.getResult()!=null) {
										List<MenusModel> menulist=menurespone.getResult().getData();
										for (MenusModel menusModel : menulist) {
											if(!isHasSubMenu(menulist,menusModel.getMenuname())) {
												Map<String, Object> parentmenu=new HashMap<>();
												parentmenu.put("id", menusModel.getParent());
												parentmenu.put("name",menusModel.getParent());
												parentmenu.put("parent_id", menusModel.getAppcode());
												if(treeList.get((String)parentmenu.get("id"))==null) {
													treeList.put((String) parentmenu.get("id"), parentmenu);
												}
												
												Map<String, Object> menu=new HashMap<>();
												menu.put("id", menusModel.getMenu());
												menu.put("name",menusModel.getMenuname());
												menu.put("parent_id", menusModel.getParent());
												treeList.put((String) menu.get("id"), menu);												
											}else {												
												Map<String, Object> menu=new HashMap<>();
												menu.put("id", menusModel.getMenuname());
												menu.put("name",menusModel.getMenuname());
												menu.put("parent_id", menusModel.getParent());
												treeList.put((String) menu.get("id"), menu);
											}											
										}
									}
								}
								
							}else {
								System.err.println("groupmenu 没有禁止数据");
								QueryParam menusparam=new QueryParam();
								menusparam.setPaging(false);
								menusparam.and("appcode", TermType.eq, appModel.getAppcode());
								ResponseMessage<PagerResult<MenusModel>> menurespone=managerService.menusQuery(menusparam);
								if(menurespone.getStatus()==200&&menurespone.getResult()!=null) {
									List<MenusModel> menulist=menurespone.getResult().getData();
									for (MenusModel menusModel : menulist) {
										
										if(!isHasSubMenu(menulist,menusModel.getMenuname())) {
											Map<String, Object> parentmenu=new HashMap<>();
											parentmenu.put("id", menusModel.getParent());
											parentmenu.put("name",menusModel.getParent());
											parentmenu.put("parent_id", menusModel.getAppcode());
											if(treeList.get((String)parentmenu.get("id"))==null) {
												treeList.put((String) parentmenu.get("id"), parentmenu);
											}
											
											Map<String, Object> menu=new HashMap<>();
											menu.put("id", menusModel.getMenu());
											menu.put("name",menusModel.getMenuname());
											menu.put("parent_id", menusModel.getParent());
											treeList.put((String) menu.get("id"), menu);												
										}else {												
											Map<String, Object> menu=new HashMap<>();
											menu.put("id", menusModel.getMenuname());
											menu.put("name",menusModel.getMenuname());
											menu.put("parent_id", menusModel.getParent());
											treeList.put((String) menu.get("id"), menu);
										}				
									}
								}
							}	
						}
					}											
				}
			}
						
		}			
		return treeList;
	}

	public Map<String, Object> getAppSetting(User currentUser,String appcode) {
		Map<String, Object> result=new HashMap<>();
		Map<String, Object> allsett=null;
		ResponseMessage<List<SettingModel>> respone=managerService.getSettingByGroup(appcode, "CONFIG");
		if(respone.getStatus()==200) {
			allsett=new HashMap<>();
			List<SettingModel> list=respone.getResult();
			List<SettingModel> array=new ArrayList<SettingModel>();
			if(list!=null) {
				if("1".equals(currentUser.getUserinfo().getGlybz())) {
					array=list;
				}else {
					for (SettingModel settingModel : list) {
						if(!settingModel.getProtype().equals("2")) {
							array.add(settingModel);
						}
					}
				}
			}
			
			result.put("allsetting", array);
			Map<String, Object> userset=new HashMap<>();
			List<UsersettingModel> usersetinglist=currentUser.getSetting();
			for (UsersettingModel usersettingModel : usersetinglist) {
				if(usersettingModel.getApp().equals(appcode)) {
					userset.put(usersettingModel.getKey(), usersettingModel.getValue());
				}
			}
			QueryParam usersetparam=new QueryParam();
			usersetparam.setPaging(false);
			usersetparam.and("userid", TermType.eq, currentUser.getId())
			.and("app", TermType.eq, appcode);
			ResponseMessage<PagerResult<UsersettingModel>> setrepone=managerService.userSettingQuery(usersetparam);
			if(setrepone.getStatus()==200&&setrepone.getResult()!=null) {
				List<UsersettingModel> usersettinglist=setrepone.getResult().getData();
				Map<String, Object> mysetting=new HashMap<>();
				for (UsersettingModel usersettingModel : usersettinglist) {
					mysetting.put(usersettingModel.getKey(), usersettingModel.getValue());
				}
				result.put("mysetting", mysetting);
			}
			
		}		
		return result;
	}

	public Map<String, Object> getforbidApp(String userid) {
		Map<String, Object>  result=new HashMap<>();
		QueryParam userappparam=new QueryParam();
		userappparam.setPaging(false);
		userappparam.and("userid", TermType.eq, userid);
		ResponseMessage<PagerResult<UserappModel>> userapprespone=managerService.userAppQuery(userappparam);
		if(userapprespone.getStatus()==200&&userapprespone.getResult()!=null) {
			List<UserappModel> userapplist=userapprespone.getResult().getData();
			String  appids="";
			for(int i=0;i<userapplist.size();i++) {
				appids+=userapplist.get(i).getAppcode()+",";
			}
			QueryParam appparam=new QueryParam();
			appparam.and("appcode", TermType.in, appids.length()==0?"无禁用应用":appids);
			appparam.setPaging(false);
			ResponseMessage<PagerResult<AppModel>> apprespone=managerService.appQuery(appparam);
			if(apprespone.getStatus()==200&&apprespone.getResult()!=null) {
				List<AppModel> applist=apprespone.getResult().getData();
				for (AppModel appModel : applist) {
					result.put(appModel.getAppcode(), appModel.getName());
				}				
				return result;
			}
		}
		return null;
	}

	public Map<String, Object> getforbidMenu(String userid) {
		Map<String, Object>  result=new HashMap<>();
		QueryParam userappparam=new QueryParam();
		userappparam.setPaging(false);
		userappparam.and("userid", TermType.eq, userid);
		ResponseMessage<PagerResult<UsermenuModel>> userapprespone=managerService.userMenuQuery(userappparam);
		if(userapprespone.getStatus()==200&&userapprespone.getResult()!=null) {
			List<UsermenuModel> usermenulist=userapprespone.getResult().getData();
			String appmenu="";
			for(int i=0;i<usermenulist.size();i++) {
				appmenu+=usermenulist.get(i).getMenu()+",";
			}
			QueryParam menuparam=new QueryParam();
			menuparam.setPaging(false);	
			menuparam.and("menu", TermType.in, appmenu.length()==0?"无禁用菜单":appmenu);
			ResponseMessage<PagerResult<MenusModel>> apprespone=managerService.menusQuery(menuparam);
			if(apprespone.getStatus()==200&&apprespone.getResult()!=null) {
				List<MenusModel> applist=apprespone.getResult().getData();
				for (MenusModel menuModel : applist) {
					result.put(menuModel.getMenu(),menuModel.getAppcode());
				}				
				return result;
			}
		}
		return null;
	}
	
	private boolean isHasSubMenu(List<MenusModel> list ,String parent) {
		 boolean ishas=false;
		 int i=0;
		 for (MenusModel menusModel : list) {
			if(parent.equals(menusModel.getParent())) {
				i++;
			}
		}
		 ishas=i>1?true:false;
		 return ishas;
	}
	
	
}
