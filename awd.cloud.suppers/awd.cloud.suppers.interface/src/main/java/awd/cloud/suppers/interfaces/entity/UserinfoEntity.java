/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.entity;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.framework.common.entity.SimpleGenericEntity;
import awd.framework.common.service.web.group.CreateGroup;
import awd.framework.common.service.web.group.UpdateGroup;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserinfoEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "Userinfo";
	public static final String ALIAS_ID = "ID";
	public static final String ALIAS_USERTYPE = "用户类型(USERTYPE)";
	public static final String ALIAS_LOGINNAME = "登录名";
	public static final String ALIAS_LOGINPASS = "登录密码";
	public static final String ALIAS_EMAIL = "EMAIL";
	public static final String ALIAS_REALNAME = "真实姓名";
	public static final String ALIAS_GLYBZ = "管理员标识(SHFO)";
	public static final String ALIAS_STATE = "启用状态";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	
		@Length(max=1,message="用户类型(USERTYPE)最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String usertype;
	
		@NotNull(message="登录名不能为空",groups=CreateGroup.class)
		@Length(max=50,message="登录名最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String loginname;
	
		@NotNull(message="登录密码不能为空",groups=CreateGroup.class)
		@Length(max=50,message="登录密码最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String loginpass;
	
		@Length(max=50,message="EMAIL最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String email;
	
		@NotNull(message="真实姓名不能为空",groups=CreateGroup.class)
		@Length(max=50,message="真实姓名最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String realname;
	
		@NotNull(message="管理员标识(SHFO)不能为空",groups=CreateGroup.class)
		@Length(max=1,message="管理员标识(SHFO)最大长度1位" ,groups=CreateGroup.class)
		private java.lang.String glybz;
	
		@NotNull(message="启用状态不能为空",groups=CreateGroup.class)
		@Length(max=2,message="启用状态最大长度2位" ,groups=CreateGroup.class)
		private java.lang.String state;
	
			
	//columns END


	public UserinfoEntity(){
	}


	public java.lang.String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(java.lang.String value) {
		this.usertype = value;
	}

	public java.lang.String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}

	public java.lang.String getLoginpass() {
		return this.loginpass;
	}

	public void setLoginpass(java.lang.String value) {
		this.loginpass = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	public java.lang.String getRealname() {
		return this.realname;
	}

	public void setRealname(java.lang.String value) {
		this.realname = value;
	}

	public java.lang.String getGlybz() {
		return this.glybz;
	}

	public void setGlybz(java.lang.String value) {
		this.glybz = value;
	}

	public java.lang.String getState() {
		return this.state;
	}

	public void setState(java.lang.String value) {
		this.state = value;
	}

}

