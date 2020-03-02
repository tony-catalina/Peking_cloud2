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


public class Kss_JtjyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.Short cjrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sj;
	
	private java.lang.String jyr;
	
	private java.lang.String fw;
	
	private java.lang.String nr;
	
	private java.lang.String xs;
	
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

	 

	public Kss_JtjyModel(){
	}
	public Kss_JtjyModel(String id) {
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
	public java.lang.Short getCjrs() {
		return this.cjrs;
	}
	
	public void setCjrs(java.lang.Short value) {
		this.cjrs = value;
	}
	
	public java.util.Date getSj() {
		return this.sj;
	}
	
	public void setSj(java.util.Date value) {
		this.sj = value;
	}
	
	public java.lang.String getJyr() {
		return this.jyr;
	}
	
	public void setJyr(java.lang.String value) {
		this.jyr = value;
	}
	public java.lang.String getFw() {
		return this.fw;
	}
	
	public void setFw(java.lang.String value) {
		this.fw = value;
	}
	public java.lang.String getNr() {
		return this.nr;
	}
	
	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getXs() {
		return this.xs;
	}
	
	public void setXs(java.lang.String value) {
		this.xs = value;
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

