/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.appstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class MsgtypeModel implements Model{
	
	//alias
	public static final String TABLE_ALIAS = "Msgtype";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_APPID = "APPID";
	public static final String ALIAS_APPNAME = "APP名称";
	public static final String ALIAS_MSGTYPE = "消息分类";
	public static final String ALIAS_ROLECODE = "角色（岗位）code，与manager数据库role关联";
	public static final String ALIAS_BUSINESSID = "业务ID";
	public static final String ALIAS_BUSINESSNAME = "业务名称";
	public static final String ALIAS_DESCRIPTION = "消息描述";
	public static final String ALIAS_ROUTINGKEY = "队列绑定key";
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appid;
	
	private java.lang.String appname;
	
	private java.lang.String msgtype;
	
	private java.lang.String rolecode;
	
	private java.lang.String businessid;
	
	private java.lang.String businessname;
	
	private java.lang.String description;
	
	private java.lang.String routingkey;


	public MsgtypeModel(){
	}
	public MsgtypeModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

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
	public java.lang.String getMsgtype() {
		return this.msgtype;
	}
	
	public void setMsgtype(java.lang.String value) {
		this.msgtype = value;
	}
	public java.lang.String getRolecode() {
		return this.rolecode;
	}
	
	public void setRolecode(java.lang.String value) {
		this.rolecode = value;
	}
	public java.lang.String getBusinessid() {
		return this.businessid;
	}
	
	public void setBusinessid(java.lang.String value) {
		this.businessid = value;
	}
	public java.lang.String getBusinessname() {
		return this.businessname;
	}
	
	public void setBusinessname(java.lang.String value) {
		this.businessname = value;
	}
	public java.lang.String getRoutingkey() {
		return this.routingkey;
	}
	
	public java.lang.String getDescription() {
		return description;
	}
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	public void setRoutingkey(java.lang.String value) {
		this.routingkey = value;
	}
	 
}

