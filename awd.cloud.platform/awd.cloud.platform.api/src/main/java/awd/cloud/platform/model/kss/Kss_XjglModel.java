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


public class Kss_XjglModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String xjdz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xjrq;
	
	private java.lang.String jsr;
	
	private java.lang.String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;
	
	private java.lang.String xjnr;
	
	private java.lang.String sldyj;
	
	private java.lang.String ldzs;
	
	private java.lang.String ldqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldqmrq;
	
	private java.lang.String xjclqk;
	
	private java.lang.String jbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbrq;
	
	private java.lang.String xjlx;
	
	private java.lang.String ywjd;
	
	private java.lang.String state;
	
	private java.lang.String bz;
	
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
	
	private java.lang.String pastable;
	//columns END

	 

	public Kss_XjglModel(){
	}
	public Kss_XjglModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getXjdz() {
		return this.xjdz;
	}
	
	public void setXjdz(java.lang.String value) {
		this.xjdz = value;
	}
	
	public java.util.Date getXjrq() {
		return this.xjrq;
	}
	
	public void setXjrq(java.util.Date value) {
		this.xjrq = value;
	}
	
	public java.lang.String getJsr() {
		return this.jsr;
	}
	
	public void setJsr(java.lang.String value) {
		this.jsr = value;
	}
	public java.lang.String getDjr() {
		return this.djr;
	}
	
	public void setDjr(java.lang.String value) {
		this.djr = value;
	}
	
	public java.util.Date getDjrq() {
		return this.djrq;
	}
	
	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}
	
	public java.lang.String getXjnr() {
		return this.xjnr;
	}
	
	public void setXjnr(java.lang.String value) {
		this.xjnr = value;
	}
	public java.lang.String getSldyj() {
		return this.sldyj;
	}
	
	public void setSldyj(java.lang.String value) {
		this.sldyj = value;
	}
	public java.lang.String getLdzs() {
		return this.ldzs;
	}
	
	public void setLdzs(java.lang.String value) {
		this.ldzs = value;
	}
	public java.lang.String getLdqm() {
		return this.ldqm;
	}
	
	public void setLdqm(java.lang.String value) {
		this.ldqm = value;
	}
	
	public java.util.Date getLdqmrq() {
		return this.ldqmrq;
	}
	
	public void setLdqmrq(java.util.Date value) {
		this.ldqmrq = value;
	}
	
	public java.lang.String getXjclqk() {
		return this.xjclqk;
	}
	
	public void setXjclqk(java.lang.String value) {
		this.xjclqk = value;
	}
	public java.lang.String getJbr() {
		return this.jbr;
	}
	
	public void setJbr(java.lang.String value) {
		this.jbr = value;
	}
	
	public java.util.Date getJbrq() {
		return this.jbrq;
	}
	
	public void setJbrq(java.util.Date value) {
		this.jbrq = value;
	}
	
	public java.lang.String getXjlx() {
		return this.xjlx;
	}
	
	public void setXjlx(java.lang.String value) {
		this.xjlx = value;
	}
	public java.lang.String getYwjd() {
		return this.ywjd;
	}
	
	public void setYwjd(java.lang.String value) {
		this.ywjd = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	 
}

