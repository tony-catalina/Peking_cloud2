package awd.mis.servers.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import awd.mis.servers.config.AuthorityInfo;

import javax.validation.constraints.NotNull;

/**
 * 用户信息
 *
 * @author WS
 */
public class User implements UserDetails {

    private static final long serialVersionUID = 6762450515926250529L;

    private String id;

    private String loginname;

    private String loginpass;

    private String jsbh;

    private String usertype;

    private java.lang.String sfzh;

    private java.lang.String jh;

    private java.lang.String realname;

    private java.lang.String xkid;

    private Set<AuthorityInfo> authorities = new HashSet<AuthorityInfo>();

    /**
     * 帐号是否不锁定，false则验证不通过 ,自带参数
     */
    private boolean isAccountNonLocked = true;

    /**
     * 帐号是否不过期，false则验证不通过  ,自带参数
     */

    private boolean isAccountNonExpired = true;

    /**
     * 凭证是否不过期，false则验证不通过  ,自带参数
     */

    private boolean isCredentialsNonExpired = true;

    /**
     * 该帐号是否启用，false则验证不通过 ,自带参数
     */
    private boolean isEnabled = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJh() {
        return jh;
    }

    public void setJh(String jh) {
        this.jh = jh;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getXkid() {
        return xkid;
    }

    public void setXkid(String xkid) {
        this.xkid = xkid;
    }

    public String getJsbh() {
        return jsbh;
    }

    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public void setAuthorities(Set<AuthorityInfo> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.loginpass;
    }

    @Override
    public String getUsername() {
        return this.loginname;
    }


}
