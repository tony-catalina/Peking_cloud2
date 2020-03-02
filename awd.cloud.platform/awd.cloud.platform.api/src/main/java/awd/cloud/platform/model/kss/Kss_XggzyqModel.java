/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

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


public class Kss_XggzyqModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jqh;
	
	private java.lang.String yqqk;
	
	private java.lang.String yqnr;
	
	private java.lang.String yqfk;
	
	private java.lang.String fkr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fksj;
	
	private java.lang.String fkqk;
	
	private java.lang.String jsbh;
	
	private java.lang.String fkzt;
	
	private java.lang.String state;
	
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

	 

	public Kss_XggzyqModel(){
	}
	public Kss_XggzyqModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJqh() {
		return this.jqh;
	}
	
	public void setJqh(java.lang.String value) {
		this.jqh = value;
	}
	public java.lang.String getYqqk() {
		return this.yqqk;
	}
	
	public void setYqqk(java.lang.String value) {
		this.yqqk = value;
	}
	public java.lang.String getYqnr() {
		return this.yqnr;
	}
	
	public void setYqnr(java.lang.String value) {
		this.yqnr = value;
	}
	public java.lang.String getYqfk() {
		return this.yqfk;
	}
	
	public void setYqfk(java.lang.String value) {
		this.yqfk = value;
	}
	public java.lang.String getFkr() {
		return this.fkr;
	}
	
	public void setFkr(java.lang.String value) {
		this.fkr = value;
	}
	
	public java.util.Date getFksj() {
		return this.fksj;
	}
	
	public void setFksj(java.util.Date value) {
		this.fksj = value;
	}
	
	public java.lang.String getFkqk() {
		return this.fkqk;
	}
	
	public void setFkqk(java.lang.String value) {
		this.fkqk = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getFkzt() {
		return this.fkzt;
	}
	
	public void setFkzt(java.lang.String value) {
		this.fkzt = value;
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
	
	 
}

