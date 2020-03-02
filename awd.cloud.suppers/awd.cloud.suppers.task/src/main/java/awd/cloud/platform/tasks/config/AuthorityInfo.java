package awd.cloud.platform.tasks.config;
import org.springframework.security.core.GrantedAuthority;

/**
 * SpringSecurity 权限控制，使用方法在方法上增加注解	
 * @PreAuthorize("hasAuthority('AWD')")//有AWD权限的才能访问
 * @author WS
 *
 */

public class AuthorityInfo implements GrantedAuthority {

	private static final long serialVersionUID = -3611059928807929554L;
	
	/**
	 * 权限CODE
	 */
	private String authority;

	public AuthorityInfo(String authority) {
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
