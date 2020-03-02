/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.logs;

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


public class Logs_PlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appcode;
	
	private java.lang.String plr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date plsj;
	
	private java.lang.Integer dz;
	
	private java.lang.String clzt;
	
	private java.lang.String hflbid;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String content;
	//columns END

	 

	public Logs_PlModel(){
	}
	public Logs_PlModel(String id) {
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
	public java.lang.String getPlr() {
		return this.plr;
	}
	
	public void setPlr(java.lang.String value) {
		this.plr = value;
	}
	
	public java.util.Date getPlsj() {
		return this.plsj;
	}
	
	public void setPlsj(java.util.Date value) {
		this.plsj = value;
	}
	
	public java.lang.Integer getDz() {
		return this.dz;
	}
	
	public void setDz(java.lang.Integer value) {
		this.dz = value;
	}
	public java.lang.String getClzt() {
		return this.clzt;
	}
	
	public void setClzt(java.lang.String value) {
		this.clzt = value;
	}
	public java.lang.String getHflbid() {
		return this.hflbid;
	}
	
	public void setHflbid(java.lang.String value) {
		this.hflbid = value;
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
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	 
}

