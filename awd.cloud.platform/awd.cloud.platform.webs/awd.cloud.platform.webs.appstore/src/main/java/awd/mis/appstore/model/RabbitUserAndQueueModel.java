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
public class RabbitUserAndQueueModel implements Model{
	
	private java.lang.String appid;
	
	private java.lang.String appname;
	
	private java.lang.String username;
	
	private java.lang.String password;
	
	private java.lang.String vhost;
	
	private java.lang.String targs;
	
	private java.lang.String writrpermission;
	
	private java.lang.String readpermission;
	
	private java.lang.String queuename;
	
	private java.lang.String type;
	
	private java.lang.String istemp;
	
	private java.lang.String autodelete;

	public java.lang.String getAppid() {
		return appid;
	}

	public void setAppid(java.lang.String appid) {
		this.appid = appid;
	}

	public java.lang.String getAppname() {
		return appname;
	}

	public void setAppname(java.lang.String appname) {
		this.appname = appname;
	}

	public java.lang.String getUsername() {
		return username;
	}

	public void setUsername(java.lang.String username) {
		this.username = username;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getVhost() {
		return vhost;
	}

	public void setVhost(java.lang.String vhost) {
		this.vhost = vhost;
	}

	public java.lang.String getTargs() {
		return targs;
	}

	public void setTargs(java.lang.String targs) {
		this.targs = targs;
	}

	public java.lang.String getWritrpermission() {
		return writrpermission;
	}

	public void setWritrpermission(java.lang.String writrpermission) {
		this.writrpermission = writrpermission;
	}

	public java.lang.String getReadpermission() {
		return readpermission;
	}

	public void setReadpermission(java.lang.String readpermission) {
		this.readpermission = readpermission;
	}

	public java.lang.String getQueuename() {
		return queuename;
	}

	public void setQueuename(java.lang.String queuename) {
		this.queuename = queuename;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getIstemp() {
		return istemp;
	}

	public void setIstemp(java.lang.String istemp) {
		this.istemp = istemp;
	}

	public java.lang.String getAutodelete() {
		return autodelete;
	}

	public void setAutodelete(java.lang.String autodelete) {
		this.autodelete = autodelete;
	}
	
	 
}

