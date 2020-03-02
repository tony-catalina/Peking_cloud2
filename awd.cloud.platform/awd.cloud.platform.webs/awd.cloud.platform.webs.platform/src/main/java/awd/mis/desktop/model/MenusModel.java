/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenusModel implements Model {
	private java.lang.String id;
	private java.lang.String appcode;
	private java.lang.String appcodeString;
	private java.lang.String menu;
	private java.lang.String parent;
	private java.lang.String menuname;
	private java.lang.String url;
	private java.lang.String creator;
	private java.util.Date createtime;
	private java.lang.String updator;
	private java.util.Date updatetime;
	//columns END


	public MenusModel(){
	}

	public MenusModel(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	
	public java.lang.String getMenu() {
		return this.menu;
	}
	
	public void setMenu(java.lang.String value) {
		this.menu = value;
	}
	
	public java.lang.String getParent() {
		return this.parent;
	}
	
	public void setParent(java.lang.String value) {
		this.parent = value;
	}
	
	public java.lang.String getMenuname() {
		return this.menuname;
	}
	
	public void setMenuname(java.lang.String value) {
		this.menuname = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}


	public java.lang.String getAppcodeString() {
		return appcodeString;
	}

	public void setAppcodeString(java.lang.String appcodeString) {
		this.appcodeString = appcodeString;
	}
	
	
	
}

