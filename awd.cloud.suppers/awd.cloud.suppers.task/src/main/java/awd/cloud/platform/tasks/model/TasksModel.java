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


public class TasksModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String tasktype;
	
	private java.lang.String taskname;
	
	private java.lang.String url;
	
	private java.lang.String params;
	
	private java.lang.String schedule;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date extime;
	
	private java.lang.String stoptime;
	
	private java.lang.String exstate;
	
	private java.lang.String exresult;
	
	private java.lang.String extimes;
	
	private java.lang.String enable;
	//columns END

	 

	public TasksModel(){
	}
	public TasksModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getTasktype() {
		return this.tasktype;
	}
	
	public void setTasktype(java.lang.String value) {
		this.tasktype = value;
	}
	public java.lang.String getTaskname() {
		return this.taskname;
	}
	
	public void setTaskname(java.lang.String value) {
		this.taskname = value;
	}
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	public java.lang.String getParams() {
		return this.params;
	}
	
	public void setParams(java.lang.String value) {
		this.params = value;
	}
	public java.lang.String getSchedule() {
		return this.schedule;
	}
	
	public void setSchedule(java.lang.String value) {
		this.schedule = value;
	}
	
	public java.util.Date getExtime() {
		return this.extime;
	}
	
	public void setExtime(java.util.Date value) {
		this.extime = value;
	}
	
	public java.lang.String getStoptime() {
		return this.stoptime;
	}
	
	public void setStoptime(java.lang.String value) {
		this.stoptime = value;
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
	public java.lang.String getExtimes() {
		return this.extimes;
	}
	
	public void setExtimes(java.lang.String value) {
		this.extimes = value;
	}
	public java.lang.String getEnable() {
		return this.enable;
	}
	
	public void setEnable(java.lang.String value) {
		this.enable = value;
	}
	 
}

