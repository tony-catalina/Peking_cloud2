/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.mis.servers.model.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginlogsModel implements Model{
	
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String loginname;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date logintime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date loginouttime;
	private java.lang.String loginip;
	private java.lang.String logintype;	
	//columns END
	
	
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	public java.util.Date getLogintime() {
		return this.logintime;
	}
	
	public void setLogintime(java.util.Date value) {
		this.logintime = value;
	}
	
	
	public java.util.Date getLoginouttime() {
		return this.loginouttime;
	}
	
	public void setLoginouttime(java.util.Date value) {
		this.loginouttime = value;
	}
	
	public java.lang.String getLoginip() {
		return this.loginip;
	}
	
	public void setLoginip(java.lang.String value) {
		this.loginip = value;
	}
	
	public java.lang.String getLogintype() {
		return this.logintype;
	}
	
	public void setLogintype(java.lang.String value) {
		this.logintype = value;
	}
	
}

