package awd.mis.desktop.filter;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.config.AuthorityInfo;
import awd.mis.desktop.config.CasProperties;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.GroupsModel;
import awd.mis.desktop.model.MenusModel;
import awd.mis.desktop.model.RoleModel;
import awd.mis.desktop.model.SettingModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.tools.PagerResult;
import awd.mis.desktop.tools.QueryParam;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.TermType;
import awd.mis.desktop.views.GlobalVar;
import cn.hutool.core.util.StrUtil;

public class WebHandleInterceptor  implements HandlerInterceptor{
	private String appcode="eyJ0eXBlIjoiYXBwIiwid2lkdGgiOiIiLCJoZWlnaHQiOiIiLCJjb250ZW50IjoiY29yZS5kZXNrdG9wKCk7In0=";
	@Autowired
	private CasProperties casProperties;
	
	private  Map<String, Object> global;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//System.out.println("-------------------afterCompletion--------------------------");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		//System.out.println("-------------------postHandle--------------------------");
		if(arg3!=null) {
			if(!StringUtils.isEmpty(arg3.getViewName())) {
				arg3.addAllObjects(global);
			}
		}
		
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		//System.out.println("-------------------preHandle--------------------------");
		
		Map<String, String> setting=new HashMap<>();
		global=new HashMap<>();
		if(SecurityContextHolder.getContext()!=null&&SecurityContextHolder.getContext().getAuthentication()!=null) {
			User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			QueryParam param = new QueryParam();
			param.and("jsbh", TermType.eq, users.getJsbh());
			param.and("userid", TermType.eq, users.getId());
			param.and("app", TermType.eq, appcode);
			ResponseMessage<PagerResult<UsersettingModel>> list = managerService.userSettingQuery(param);
			if(users!=null) {
				GlobalVar.getConfig().put("user", users.getUsername());
				global.put("user",users);
				if(users.getSetting()!=null) {				
					for (UsersettingModel usersetting : list.getResult().getData()) {
						if(appcode.equals(usersetting.getApp())) {
							setting.put(usersetting.getKey(), usersetting.getValue());
						}
					}
				}
				String field="name";
				String order="up";
				if(!StringUtils.isEmpty(setting.get("list_sort_field,list_sort_order"))) {
				String fields = (String) setting.get("list_sort_field,list_sort_order");
				String[] all = fields.split(",");
				for(int i=0;i<all.length;i++) {
					if(all[i].equals("up")||all[i].equals("down")) {
						order=all[i];
					}else {
						field=all[i];
					}
				}
				}
				setting.put("list_sort_field", field);
				setting.put("list_sort_order", order);
				int is_admin=0;
				int is_root=0;
				//System.out.println("-----------登录用户信息--------------");
				if(users.getUserinfo()!=null) {
					if("1".equals(users.getUserinfo().getGlybz()))is_root=1;
					if("000000000".equals(users.getUserinfo().getJsbh()))is_admin=1;
				}else {
					is_root=0;
					is_admin=0;
				}
//				System.out.println(is_root);				
				GlobalVar.getConfig().put("lang", "zh-Cn");		
				GlobalVar.getConfig().put("system_os","linux");
				GlobalVar.getConfig().put("is_root",is_root);			//是否是管理员	
				GlobalVar.getConfig().put("is_admin",is_admin);			//是否是超级用户
				GlobalVar.getConfig().put("user_type",users.getJsbh().substring(7, 8));			//是否是超级用户
				GlobalVar.getConfig().put("user_jsbh",users.getJsbh());
				GlobalVar.getConfig().put("user_name",users.getGroup().getGroupname());
				boolean lx=false;
				if(users.getRoles()!=null) {
				List<RoleModel> roles = users.getRoles();
				String codes="";
				String codess="";
					for(int i = 0;i<roles.size();i++) {
						System.err.println("roles"+roles.get(i));
						RoleModel roleModel = roles.get(i);
						if(roleModel.getCode()!=null) {
							codes=roleModel.getCode();
							if(codes.equals("08")||codes.equals("18")||codes.equals("28")||codes.equals("38")||codes.equals("48")) {
								lx=true;
								break;
							}
						}
					}
				}
				GlobalVar.getConfig().put("lx",lx);
				//获取系统配置
				ResponseMessage<List<SettingModel>> system_init=managerService.getSettingByGroup(appcode,"SYSTEM_INIT");				
				if(system_init!=null&&system_init.getStatus()==200) {
					List<SettingModel> settinglist=system_init.getResult();
					for (SettingModel settingModel : settinglist) {
						if("first_in".equals(settingModel.getProname())) {
							GlobalVar.getConfig().put("web_root",settingModel.getProvalue());
						}
						if("system_desc".equals(settingModel.getProname())) {
							GlobalVar.getConfig().put("CLOUD_DESC",settingModel.getProvalue());
						}
						if("path_hidden".equals(settingModel.getProname())) {
							GlobalVar.getConfig().put("CLOUD_PATH_HIDE",settingModel.getProvalue());
						}
						if("system_name".equals(settingModel.getProname())) {
							GlobalVar.getConfig().put("CLOUD_NAME",settingModel.getProvalue());
						}
						if("new_user_folder".equals(settingModel.getProname())) {
							GlobalVar.getConfig().put("CLOUD_NEW_FOLDER",settingModel.getProvalue());
						}
					}
					
				}else {
					GlobalVar.getConfig().put("web_root","");
				}	
//				System.out.println(JSONUtil.toJsonStr(users));
				if(users.getUserinfo()==null) {
					ResponseMessage<User> respone=managerService.getUserByName(users.getJsbh(),users.getUsername());
					if(respone!=null&&respone.getStatus()==200) {
						User getuser=respone.getResult();
						if(users!=null) {
							Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
							users.setId(getuser.getId());						
							//用户组
							GroupsModel groups=getuser.getGroup();					
							if(groups!=null) {
								AuthorityInfo authorityInfo = new AuthorityInfo("GROUP_"+groups.getGroupname());
								authorities.add(authorityInfo);
								users.setGroup(groups);
							}
							//角色
							List<RoleModel> rolelist=getuser.getRoles();
							for (RoleModel roleModel : rolelist) {
								AuthorityInfo authorityInfo = new AuthorityInfo("ROLE_"+roleModel.getCode());
								authorities.add(authorityInfo);
								users.setRoles(rolelist);
							}
							//菜单
							List<MenusModel> menulist=getuser.getMenus();
							for (MenusModel menusModel : menulist) {
								AuthorityInfo authorityInfo = new AuthorityInfo("MENU_"+menusModel.getMenu());
								authorities.add(authorityInfo);
								users.setMenus(menulist);
							}
							//应用
							List<AppModel> applist=getuser.getApps();
							for (AppModel appModel : applist) {
								AuthorityInfo authorityInfo = new AuthorityInfo("APP_"+appModel.getAppcode());
								authorities.add(authorityInfo);
								users.setApps(applist);
							}
							//设置
							List<UsersettingModel> settinglist=getuser.getSetting();
							users.setSetting(settinglist);						
							users.setAuthorities(authorities);
							users.setUserinfo(getuser.getUserinfo());
							users.setPassword(getuser.getUserinfo().getLoginpass());
							GlobalVar.getConfig().put("user_type",users.getUserinfo().getUsertype());
						}						
					}					
				}
				
				String hostip=casProperties.getAppServerUrl().substring(7, casProperties.getAppServerUrl().length());

				GlobalVar.getConfig().put("web_host",hostip);
				GlobalVar.getConfig().put("app_host","\\/");
				GlobalVar.getConfig().put("static_path","\\/");
				GlobalVar.getConfig().put("version","4.0");
				GlobalVar.getConfig().put("version_desc","");
				GlobalVar.getConfig().put("upload_max",500000000);
				GlobalVar.getConfig().put("param_rewrite",false);
				GlobalVar.getConfig().put("json_data","");
				GlobalVar.getConfig().put("AWD_GROUP_ROOT_SELF",URLEncoder.encode("{group_selef}"));
				GlobalVar.getConfig().put("AWD_GROUP_ROOT_ALL",URLEncoder.encode("{group_all}"));
				GlobalVar.getConfig().put("AWD_GROUP_PATH","");
				GlobalVar.getConfig().put("AWD_GROUP_SHARE",URLEncoder.encode("{public}"));
				GlobalVar.getConfig().put("AWD_USER_FAV",URLEncoder.encode("{user_fav}"));
				GlobalVar.getConfig().put("AWD_USER_SHARE",URLEncoder.encode("{user_share}"));
				GlobalVar.getConfig().put("AWD_USER_RECYCLE",URLEncoder.encode("{user_recycle}"));	
				GlobalVar.getUserConfig().put("jsbh", users.getJsbh());
				GlobalVar.getUserConfig().put("list_type", StrUtil.isBlank(setting.get("list_type"))?"list":setting.get("list_type"));
				GlobalVar.getUserConfig().put("list_sort_field",StrUtil.isBlank(setting.get("list_sort_field"))?"name":setting.get("list_sort_field"));
				GlobalVar.getUserConfig().put("list_sort_order",StrUtil.isBlank(setting.get("list_sort_order"))?"up":setting.get("list_sort_order"));
				GlobalVar.getUserConfig().put("file_repeat",StrUtil.isBlank(setting.get("file_repeat"))?"replace":setting.get("file_repeat"));
				GlobalVar.getUserConfig().put("file_icon_size",StrUtil.isBlank(setting.get("file_icon_size"))?"80":setting.get("file_icon_size"));
				GlobalVar.getUserConfig().put("animate_open",StrUtil.isBlank(setting.get("animate_open"))?"1":setting.get("animate_open"));
				GlobalVar.getUserConfig().put("sound_open",StrUtil.isBlank(setting.get("sound_open"))?"1":setting.get("sound_open"));
				GlobalVar.getUserConfig().put("theme",StrUtil.isBlank(setting.get("theme"))?"win10":setting.get("theme"));
				GlobalVar.getUserConfig().put("wall",StrUtil.isBlank(setting.get("wall"))?"8":setting.get("wall"));
				GlobalVar.getUserConfig().put("wall_diy",StrUtil.isBlank(setting.get("wall_diy"))?false:setting.get("wall_diy"));
				GlobalVar.getUserConfig().put("recycle_open",StrUtil.isBlank(setting.get("recycle_open"))?"1":setting.get("recycle_open"));
				GlobalVar.getUserConfig().put("resize_config","{\\\"filename\\\":250,\\\"filetype\\\":80,\\\"filesize\\\":80,\\\"filetime\\\":215,\\\"editor_left_tree_width\\\":200, \\\"explorer_left_tree_width\\\":200}");
				global.put("config",GlobalVar.getConfig());
				global.put("userconfig",GlobalVar.getUserConfig());
			}						
		}	
//		System.err.println("===============系统配置============================");
//		System.err.println(JSONUtil.toJsonStr(GlobalVar.getConfig()));
		return true;
	}

}
