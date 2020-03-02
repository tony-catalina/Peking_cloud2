package awd.cloud.platform.tasks.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import awd.cloud.platform.tasks.api.AwdApi;
import awd.cloud.platform.tasks.model.AppModel;
import awd.cloud.platform.tasks.model.GroupsModel;
import awd.cloud.platform.tasks.model.MenusModel;
import awd.cloud.platform.tasks.model.RoleModel;
import awd.cloud.platform.tasks.model.User;
import awd.cloud.platform.tasks.model.UsersettingModel;
import awd.cloud.platform.tasks.utils.ResponseMessage;
import awd.framework.common.utils.JSONUtil;


/**
 *  用于加载用户信息 实现UserDetailsService接口，或者实现AuthenticationUserDetailsService接口
 *	实现UserDetailsService接口，实现loadUserByUsername方法
 * @author WS
 *
 */
public class CustomUserDetailsService  implements UserDetailsService {
	private static User user=null;
	@Autowired
	private AwdApi managerService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//这里为了方便，就直接返回一个用户信息，实际当中这里修改为调用服务或缓存来获取用户信息
				user = new User();		
				if(username!=null) {
					//获取用户详细信息
					String us[] = username.split("@");
					user.setUsername(us[0]);
					user.setJsbh(us[1]);			
					
					try {
						ResponseMessage<User> respone=managerService.getUserByName(us[1],us[0]);
						if(respone!=null&&respone.getStatus()==200) {
							User getuser=respone.getResult();
							if(getuser!=null) {
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
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}else {
					Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
					AuthorityInfo authorityInfo = new AuthorityInfo("NONE");
					authorities.add(authorityInfo);
				}
				System.out.println("----------------------------------获取用户权限信息-------------------------------------------");
				System.out.println(JSONUtil.toJson(user));
				System.out.println("-----------------------------------------------------------------------------");
				return user;
	} 


}
