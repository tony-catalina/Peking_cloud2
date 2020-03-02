/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.constraints.NotNull;

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
public class FlownodeModel implements Model {
	
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String nodecode;
	private java.lang.String nodename;
	private java.lang.String flowmapid;
	private java.lang.String jdlx;
	private java.lang.String role;
	private java.lang.String memo;
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
	private java.lang.String menu;
	//columns END


	public FlownodeModel(){
	}

	public FlownodeModel(
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
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getNodecode() {
		return this.nodecode;
	}
	
	public void setNodecode(java.lang.String value) {
		this.nodecode = value;
	}
	
	public java.lang.String getNodename() {
		return this.nodename;
	}
	
	public void setNodename(java.lang.String value) {
		this.nodename = value;
	}
	
	public java.lang.String getFlowmapid() {
		return this.flowmapid;
	}
	
	public void setFlowmapid(java.lang.String value) {
		this.flowmapid = value;
	}
	
	public java.lang.String getJdlx() {
		return this.jdlx;
	}
	
	public void setJdlx(java.lang.String value) {
		this.jdlx = value;
	}
	
	public java.lang.String getRole() {
		return this.role;
	}
	
	public void setRole(java.lang.String value) {
		this.role = value;
	}
	
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
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
	
	public java.lang.String getMenu() {
		return menu;
	}

	public void setMenu(java.lang.String menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "FlownodeModel [id=" + id + ", jsbh=" + jsbh + ", nodecode=" + nodecode + ", nodename=" + nodename
				+ ", flowmapid=" + flowmapid + ", jdlx=" + jdlx + ", role=" + role + ", memo=" + memo + ", creator="
				+ creator + ", createtime=" + createtime + ", updator=" + updator + ", updatetime=" + updatetime + "]";
	}
	
	
}

