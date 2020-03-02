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


public class ClassficModel implements Model {
	private java.lang.String id;
	private java.lang.String lx;	
	private java.lang.String parentid;
	private java.lang.String classid;
	private java.lang.String classidString;
	private java.lang.String name;
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

	public ClassficModel(){
	}

	public ClassficModel(
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

	public java.lang.String getParentid() {
		return parentid;
	}

	public void setParentid(java.lang.String parentid) {
		this.parentid = parentid;
	}

	public java.lang.String getLx() {
		return lx;
	}

	public void setLx(java.lang.String lx) {
		this.lx = lx;
	}
	

	public java.lang.String getClassid() {
		return classid;
	}

	public void setClassid(java.lang.String classid) {
		this.classid = classid;
	}


	public java.lang.String getClassidString() {
		return classidString;
	}

	public void setClassidString(java.lang.String classidString) {
		this.classidString = classidString;
	}

	@Override
	public String toString() {
		return "ClassficModel [id=" + id + ", lx=" + lx + ", parentid=" + parentid + ", classid=" + classid
				+ ", classidString=" + classidString + ", name=" + name + ", creator=" + creator + ", createtime="
				+ createtime + ", updator=" + updator + ", updatetime=" + updatetime + "]";
	}

	
	
		
}

