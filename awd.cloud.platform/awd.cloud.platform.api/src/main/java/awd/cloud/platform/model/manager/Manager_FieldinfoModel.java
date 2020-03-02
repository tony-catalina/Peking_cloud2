/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Manager_FieldinfoModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String username;
	
	private java.lang.String tablename;
	
	private java.lang.String fieldname;
	
	private java.lang.String fieldnamezh;
	
	private java.lang.Integer fieldlen;
	
	private java.lang.String fieldtype;
	
	private java.lang.String dictype;
	
	private java.lang.Short fieldprec;
	
	private java.lang.String monitor;
	
	private java.lang.String cybz;
	
	private java.lang.String memo;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	 

	public Manager_FieldinfoModel(){
	}
	public Manager_FieldinfoModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	public java.lang.String getTablename() {
		return this.tablename;
	}
	
	public void setTablename(java.lang.String value) {
		this.tablename = value;
	}
	public java.lang.String getFieldname() {
		return this.fieldname;
	}
	
	public void setFieldname(java.lang.String value) {
		this.fieldname = value;
	}
	public java.lang.String getFieldnamezh() {
		return this.fieldnamezh;
	}
	
	public void setFieldnamezh(java.lang.String value) {
		this.fieldnamezh = value;
	}
	public java.lang.Integer getFieldlen() {
		return this.fieldlen;
	}
	
	public void setFieldlen(java.lang.Integer value) {
		this.fieldlen = value;
	}
	public java.lang.String getFieldtype() {
		return this.fieldtype;
	}
	
	public void setFieldtype(java.lang.String value) {
		this.fieldtype = value;
	}
	public java.lang.String getDictype() {
		return this.dictype;
	}
	
	public void setDictype(java.lang.String value) {
		this.dictype = value;
	}
	public java.lang.Short getFieldprec() {
		return this.fieldprec;
	}
	
	public void setFieldprec(java.lang.Short value) {
		this.fieldprec = value;
	}
	public java.lang.String getMonitor() {
		return this.monitor;
	}
	
	public void setMonitor(java.lang.String value) {
		this.monitor = value;
	}
	public java.lang.String getCybz() {
		return this.cybz;
	}
	
	public void setCybz(java.lang.String value) {
		this.cybz = value;
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
	
	 
}

