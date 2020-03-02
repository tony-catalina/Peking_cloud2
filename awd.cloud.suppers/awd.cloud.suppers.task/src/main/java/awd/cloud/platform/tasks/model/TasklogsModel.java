/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import awd.cloud.platform.tasks.utils.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class TasklogsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String taskid;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date exkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date exjssj;
	
	private java.lang.Float exhs;
	
	private java.lang.String exstate;
	
	private java.lang.String exresult;
	//columns END

	 

	public TasklogsModel(){
	}
	public TasklogsModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getTaskid() {
		return this.taskid;
	}
	
	public void setTaskid(java.lang.String value) {
		this.taskid = value;
	}
	
	public java.util.Date getExkssj() {
		return this.exkssj;
	}
	
	public void setExkssj(java.util.Date value) {
		this.exkssj = value;
	}
	
	
	public java.util.Date getExjssj() {
		return this.exjssj;
	}
	
	public void setExjssj(java.util.Date value) {
		this.exjssj = value;
	}
	
	public java.lang.Float getExhs() {
		return this.exhs;
	}
	
	public void setExhs(java.lang.Float value) {
		this.exhs = value;
	}
	public java.lang.String getExstate() {
		return this.exstate;
	}
	
	public void setExstate(java.lang.String value) {
		this.exstate = value;
	}
	public java.lang.String getExresult() {
		return this.exresult;
	}
	
	public void setExresult(java.lang.String value) {
		this.exresult = value;
	}
	 
}

