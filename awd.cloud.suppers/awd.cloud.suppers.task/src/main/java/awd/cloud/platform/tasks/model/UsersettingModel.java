/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.platform.tasks.utils.Model;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersettingModel implements Model {

	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String jsbhString;
	private java.lang.String userid;
	private java.lang.String useridString;
	private java.lang.String app;
	private java.lang.String appString;
	private java.lang.String key;
    private java.lang.String keyString;
	private java.lang.String value;
	private java.lang.String state;
    private java.lang.String stateString;
	private java.lang.String creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createtime;
    private java.lang.String createtimeString;
	private java.lang.String updator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;
    private java.lang.String updatetimeString;
	//columns END


	public void UsersettingEntity(){
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getApp() {
		return this.app;
	}
	
	public void setApp(java.lang.String value) {
		this.app = value;
	}
	
	public java.lang.String getKey() {
		return this.key;
	}
	
	public void setKey(java.lang.String value) {
		this.key = value;
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
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



	public java.lang.String getCreatetimeString() {
		return createtimeString;
	}



	public void setCreatetimeString(java.lang.String createtimeString) {
		this.createtimeString = createtimeString;
	}



	public java.lang.String getUpdatetimeString() {
		return updatetimeString;
	}



	public void setUpdatetimeString(java.lang.String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}



	public java.lang.String getKeyString() {
		return keyString;
	}



	public void setKeyString(java.lang.String keyString) {
		this.keyString = keyString;
	}



	public java.lang.String getStateString() {
		return stateString;
	}



	public void setStateString(java.lang.String stateString) {
		this.stateString = stateString;
	}



	public java.lang.String getJsbhString() {
		return jsbhString;
	}



	public void setJsbhString(java.lang.String jsbhString) {
		this.jsbhString = jsbhString;
	}



	public java.lang.String getUseridString() {
		return useridString;
	}



	public void setUseridString(java.lang.String useridString) {
		this.useridString = useridString;
	}



	public java.lang.String getAppString() {
		return appString;
	}



	public void setAppString(java.lang.String appString) {
		this.appString = appString;
	}
	
	
	
	
}

