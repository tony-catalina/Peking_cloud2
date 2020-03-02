package awd.cloud.platform.tasks.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import awd.cloud.platform.tasks.config.AuthorityInfo;


/**
 * 用户信息
 * @author WS
 *
 */
public class User implements UserDetails {

	private static final long serialVersionUID = 6762450515926250529L;
	
	private String id;

	/**
	 * 登录名称
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;
	
	/**
	 * 监所编号
	 */
	private String jsbh;
	
	/**  
     *  帐号是否不锁定，false则验证不通过 ,自带参数
     */ 
	private boolean isAccountNonLocked = true;
	
	/**  
     *  帐号是否不过期，false则验证不通过  ,自带参数
     */ 

	private boolean isAccountNonExpired = true;
	
	/**  
     *  凭证是否不过期，false则验证不通过  ,自带参数
     */

	private boolean isCredentialsNonExpired = true;

	/**
	 *  该帐号是否启用，false则验证不通过 ,自带参数
	 */
	private boolean isEnabled = true;
 
	private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();
	
	
	private UserinfoModel userinfo;
	private List<AppModel> apps;
	private List<MenusModel> menus;
	private List<RoleModel> roles;
	private GroupsModel   group;
	private List<UsersettingModel> setting;
	
	
	private String state;
	private String jh;
	private String sfzh;
	private String groupid;
	private String rolesstr;
	private String isadmin;
	private String email;
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}
	
	public String getName() {
		return this.username;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}


	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}


	public void setAuthorities(Set<AuthorityInfo> authorities) {
		this.authorities = authorities;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}
	
	
	

	


	public GroupsModel getGroup() {
		return group;
	}


	public void setGroup(GroupsModel group) {
		this.group = group;
	}
	


	public UserinfoModel getUserinfo() {
		return userinfo;
	}


	public void setUserinfo(UserinfoModel user) {
		this.userinfo = user;
	}


	public List<AppModel> getApps() {
		return apps;
	}


	public void setApps(List<AppModel> apps) {
		this.apps = apps;
	}


	public List<MenusModel> getMenus() {
		return menus;
	}


	public void setMenus(List<MenusModel> menus) {
		this.menus = menus;
	}


	public List<RoleModel> getRoles() {
		return roles;
	}


	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}


	public List<UsersettingModel> getSetting() {
		return setting;
	}


	public void setSetting(List<UsersettingModel> setting) {
		this.setting = setting;
	}
	
	


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	


	public String getJh() {
		return jh;
	}


	public void setJh(String jh) {
		this.jh = jh;
	}


	public String getSfzh() {
		return sfzh;
	}


	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}


	public String getGroupid() {
		return groupid;
	}


	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}


	public String getRolesstr() {
		return rolesstr;
	}


	public void setRolesstr(String rolesstr) {
		this.rolesstr = rolesstr;
	}


	public String getIsadmin() {
		return isadmin;
	}


	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	
	
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	


	@Override
	public String toString() {
		return "UserInfo [ username=" + username + ", password=" + password + ", jsbh="
				+ jsbh + ", isAccountNonLocked=" + isAccountNonLocked + ", isAccountNonExpired=" + isAccountNonExpired
				+ ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", isEnabled=" + isEnabled + ", authorities="
				+ authorities + "]";
	}


}
