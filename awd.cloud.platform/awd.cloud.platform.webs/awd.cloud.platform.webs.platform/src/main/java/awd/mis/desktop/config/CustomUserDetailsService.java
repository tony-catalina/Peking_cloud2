package awd.mis.desktop.config;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import awd.mis.desktop.api.LogsService;
import awd.mis.desktop.api.ManagerService;
import awd.mis.desktop.bean.User;
import awd.mis.desktop.model.AppModel;
import awd.mis.desktop.model.GroupsModel;
import awd.mis.desktop.model.LoginlogsModel;
import awd.mis.desktop.model.MenusModel;
import awd.mis.desktop.model.RoleModel;
import awd.mis.desktop.model.UserinfoModel;
import awd.mis.desktop.model.UsersettingModel;
import awd.mis.desktop.tools.ResponseMessage;
import awd.mis.desktop.tools.SystemUtils;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;

/**
 *  用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 *	实现UserDetailsService接口，实现loadUserByUsername方法
 * @author WS
 *
 */
public class CustomUserDetailsService  implements UserDetailsService  {
	Log log=LogFactory.getLog(CustomUserDetailsService.class);
	
	private static User user=null;
	@Autowired
	private  ManagerService managerService;
	@Autowired
	private LogsService  logsService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//这里为了方便，就直接返回一个用户信息，实际当中这里修改为调用服务或缓存来获取用户信息
		user = new User();		
		if(username!=null) {
			//获取用户详细信息
			log.info("获取用户名："+username);
			String us[] = username.split("@");
			user.setUsername(us[0]);
			user.setJsbh(us[1]);			
			
			try {
				ResponseMessage<User> respone=managerService.getUserByName(us[1],us[0]);
				log.info("获取用户信息："+respone);
				if(respone!=null&&respone.getStatus()==200) {
					User getuser=respone.getResult();
					if(user!=null) {
						Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
						user.setId(getuser.getId());						
						//用户组
						GroupsModel groups=getuser.getGroup();					
						if(groups!=null) {
							AuthorityInfo authorityInfo = new AuthorityInfo("GROUP_"+groups.getGroupname());
							authorities.add(authorityInfo);
							user.setGroup(groups);
						}
						//角色
						List<RoleModel> rolelist=getuser.getRoles();
						for (RoleModel roleModel : rolelist) {
							AuthorityInfo authorityInfo = new AuthorityInfo("ROLE_"+roleModel.getCode());
							authorities.add(authorityInfo);
							user.setRoles(rolelist);
						}
						//菜单
						List<MenusModel> menulist=getuser.getMenus();
						for (MenusModel menusModel : menulist) {
							AuthorityInfo authorityInfo = new AuthorityInfo("MENU_"+menusModel.getMenu());
							authorities.add(authorityInfo);
							user.setMenus(menulist);
						}
						//应用
						List<AppModel> applist=getuser.getApps();
						for (AppModel appModel : applist) {
							AuthorityInfo authorityInfo = new AuthorityInfo("APP_"+appModel.getAppcode());
							authorities.add(authorityInfo);
							user.setApps(applist);
						}
						//设置
						List<UsersettingModel> settinglist=getuser.getSetting();
						user.setSetting(settinglist);						
						user.setAuthorities(authorities);
						user.setUserinfo(getuser.getUserinfo());
						user.setPassword(getuser.getUserinfo().getLoginpass());
					}
					
				}
				ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				HttpServletRequest request = requestAttributes.getRequest();
				LoginlogsModel loginlogs=new LoginlogsModel();
				loginlogs.setJsbh(user.getJsbh());
				loginlogs.setLoginip(SystemUtils.getIpAddr(request));
				loginlogs.setLoginname(user.getName());
				loginlogs.setLogintime(Calendar.getInstance().getTime());
				loginlogs.setLogintype("1");
				logsService.loginlogsSave(loginlogs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			log.info(username+":登录成功");
		}else {
			Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
			AuthorityInfo authorityInfo = new AuthorityInfo("NONE");
			authorities.add(authorityInfo);
			log.info(username+":登录失败");
		}
		System.out.println("----------------------------------获取用户权限信息-------------------------------------------");
		System.out.println(user);
		System.out.println(JSONUtil.toJsonStr(user));
		System.out.println("-----------------------------------------------------------------------------");
		return user;
	} 
	
	public static String  getUserName() {
		return user.getUsername();
	}
	
	
	public static String getJsbh() {
		return user.getJsbh();
	}


}
