/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.entity;

import javax.validation.GroupSequence;

import org.hibernate.validator.constraints.Length;

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
public class RabbitUsersEntity extends SimpleGenericEntity<String>  {

	//alias
	public static final String TABLE_ALIAS = "RabbitUsers";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPID = "APPID";
	public static final String ALIAS_APPNAME = "APP名称";
	public static final String ALIAS_USERNAME = "用户名";
	public static final String ALIAS_PASSWORD = "密码";
	public static final String ALIAS_VHOST = "虚拟主机";
	public static final String ALIAS_QUEUENAME = "绑定的消息队列";
	public static final String ALIAS_TARGS = "用户标签";
	public static final String ALIAS_WRITRPERMISSION = "写入权限";
	public static final String ALIAS_READPERMISSION = "读取权限";

	//所有组
	@GroupSequence({CreateGroup.class, UpdateGroup.class})
	public interface All {
	}

	//columns START
		/*@NotNull(message="id不能为空",groups=CreateGroup.class)
	    @Length(max=23,message="id最大长度23位" ,groups=CreateGroup.class)
		private java.lang.String id;*/
	
		private java.lang.String appid;
	
		@Length(max=50,message="APP名称最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String appname;
	
		@Length(max=100,message="用户名最大长度100位" ,groups=CreateGroup.class)
		private java.lang.String username;
	
		@Length(max=100,message="密码最大长度100位" ,groups=CreateGroup.class)
		private java.lang.String password;
	
		@Length(max=50,message="虚拟主机最大长度50位" ,groups=CreateGroup.class)
		private java.lang.String vhost;
	
		@Length(max=255,message="绑定的消息队列最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String queuename;
	
		@Length(max=255,message="用户标签最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String targs;
	
		@Length(max=255,message="写入权限最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String writrpermission;
	
		@Length(max=255,message="读取权限最大长度255位" ,groups=CreateGroup.class)
		private java.lang.String readpermission;
	
	//columns END


	public RabbitUsersEntity(){
	}

	/*public RabbitUsersEntity(
		java.lang.String id
	){
		this.id = id;
	}*/



	/*public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}*/


	public java.lang.String getAppid() {
		return this.appid;
	}

	public void setAppid(java.lang.String value) {
		this.appid = value;
	}

	public java.lang.String getAppname() {
		return this.appname;
	}

	public void setAppname(java.lang.String value) {
		this.appname = value;
	}

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	public java.lang.String getVhost() {
		return this.vhost;
	}

	public void setVhost(java.lang.String value) {
		this.vhost = value;
	}

	public java.lang.String getQueuename() {
		return this.queuename;
	}

	public void setQueuename(java.lang.String value) {
		this.queuename = value;
	}

	public java.lang.String getTargs() {
		return this.targs;
	}

	public void setTargs(java.lang.String value) {
		this.targs = value;
	}

	public java.lang.String getWritrpermission() {
		return this.writrpermission;
	}

	public void setWritrpermission(java.lang.String value) {
		this.writrpermission = value;
	}

	public java.lang.String getReadpermission() {
		return this.readpermission;
	}

	public void setReadpermission(java.lang.String value) {
		this.readpermission = value;
	}
}

