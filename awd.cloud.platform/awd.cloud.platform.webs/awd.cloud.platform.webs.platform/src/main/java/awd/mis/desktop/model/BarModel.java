/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class BarModel implements Model {
	
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String jsbhString;
	private java.lang.String sfzh;
	private java.lang.String xm;
	private java.lang.String dwlx;
    private java.lang.String dwlxString;
	private java.lang.String badw;
	private java.lang.String active;
    private java.lang.String activeString;
	private java.lang.String state;
    private java.lang.String stateString;
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


	public void BarEntity(){
	}

	public void BarEntity(
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
	
	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	
	public java.lang.String getDwlx() {
		return this.dwlx;
	}
	
	public void setDwlx(java.lang.String value) {
		this.dwlx = value;
	}
	
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	
	
	public java.lang.String getActive() {
		return this.active;
	}
	
	public void setActive(java.lang.String value) {
		this.active = value;
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

	public java.lang.String getJsbh() {
		return jsbh;
	}

	public void setJsbh(java.lang.String jsbh) {
		this.jsbh = jsbh;
	}

	public java.lang.String getJsbhString() {
		return jsbhString;
	}

	public void setJsbhString(java.lang.String jsbhString) {
		this.jsbhString = jsbhString;
	}

	public java.lang.String getDwlxString() {
		return dwlxString;
	}

	public void setDwlxString(java.lang.String dwlxString) {
		this.dwlxString = dwlxString;
	}

	public java.lang.String getActiveString() {
		return activeString;
	}

	public void setActiveString(java.lang.String activeString) {
		this.activeString = activeString;
	}

	public java.lang.String getStateString() {
		return stateString;
	}

	public void setStateString(java.lang.String stateString) {
		this.stateString = stateString;
	}
	
	
	
}

