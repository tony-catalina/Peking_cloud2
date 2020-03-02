package awd.cloud.platform.webs.charts.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户信息
 * @author WS
 *
 */
public class UserInfo implements UserDetails {

	private static final long serialVersionUID = 6762450515926250529L;

	/**
	 * 用户名称
	 */
	private String loginname;

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getJsbh() {
		return jsbh;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}


}
