/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.cloud.suppers.interfaces.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class UserinfoModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	private java.lang.String usertype;
	
	private java.lang.String usertypeString;
	
	private java.lang.String loginname;
	
	private java.lang.String loginpass;
	
	private java.lang.String email;
	
	private java.lang.String realname;
	
	private java.lang.String glybz;
	
	private java.lang.String glybzString;
	
	private java.lang.String state;
	
	private java.lang.String stateString;
	

	public UserinfoModel(){
	}
	public UserinfoModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
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
	public java.lang.String getUsertypeString() {
		return usertypeString;
	}
	public void setUsertypeString(java.lang.String usertypeString) {
		this.usertypeString = usertypeString;
	}
	public java.lang.String getGlybzString() {
		return glybzString;
	}
	public void setGlybzString(java.lang.String glybzString) {
		this.glybzString = glybzString;
	}
	public java.lang.String getStateString() {
		return stateString;
	}
	public void setStateString(java.lang.String stateString) {
		this.stateString = stateString;
	}
	
	 
}

