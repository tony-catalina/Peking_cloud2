/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.webs.charts.modal;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.platform.webs.charts.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleModel implements Model {
	
	
	private java.lang.String id;
	private java.lang.String jslx;
	private java.lang.String jslxString;
	private java.lang.String code;
	private java.lang.String name;
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


	public RoleModel(){
	}

	public RoleModel(
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
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
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

	public java.lang.String getJslxString() {
		return jslxString;
	}

	public void setJslxString(java.lang.String jslxString) {
		this.jslxString = jslxString;
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
	
	
	
	
}

