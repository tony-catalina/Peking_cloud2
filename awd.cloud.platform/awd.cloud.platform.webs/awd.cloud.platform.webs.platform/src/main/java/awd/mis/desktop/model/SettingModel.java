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
public class SettingModel implements Model {
	private java.lang.String id;
	private java.lang.String appcode;
	private java.lang.String appcodeString;
	private java.lang.String progroup;
	private java.lang.String protype;
	private java.lang.String proname;
	private java.lang.String prolist;
	private java.lang.String provalue;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public SettingModel(){
	}

	public SettingModel(
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
	
	public java.lang.String getProgroup() {
		return this.progroup;
	}
	
	public void setProgroup(java.lang.String value) {
		this.progroup = value;
	}
	
	public java.lang.String getProtype() {
		return this.protype;
	}
	
	public void setProtype(java.lang.String value) {
		this.protype = value;
	}
	
	public java.lang.String getProname() {
		return this.proname;
	}
	
	public void setProname(java.lang.String value) {
		this.proname = value;
	}
	
	public java.lang.String getProlist() {
		return this.prolist;
	}
	
	public void setProlist(java.lang.String value) {
		this.prolist = value;
	}
	
	public java.lang.String getProvalue() {
		return this.provalue;
	}
	
	public void setProvalue(java.lang.String value) {
		this.provalue = value;
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

