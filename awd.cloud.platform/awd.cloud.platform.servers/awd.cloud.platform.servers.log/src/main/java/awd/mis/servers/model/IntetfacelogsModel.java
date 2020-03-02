/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class IntetfacelogsModel implements Model {
	
	private java.lang.String appcode;
	private java.lang.String interfaceurl;
	private java.util.Date optime;	
	private java.lang.String opip;
	private java.lang.String creator;
	//columns END


	public IntetfacelogsModel(){
	}

	
	public java.lang.String getInterfaceurl() {
		return this.interfaceurl;
	}
	
	public void setInterfaceurl(java.lang.String value) {
		this.interfaceurl = value;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	
	
	public java.util.Date getOptime() {
		return this.optime;
	}
	
	public void setOptime(java.util.Date value) {
		this.optime = value;
	}
	
	public java.lang.String getOpip() {
		return this.opip;
	}
	
	public void setOpip(java.lang.String value) {
		this.opip = value;
	}


	public java.lang.String getCreator() {
		return creator;
	}


	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}
	
	
	
}

