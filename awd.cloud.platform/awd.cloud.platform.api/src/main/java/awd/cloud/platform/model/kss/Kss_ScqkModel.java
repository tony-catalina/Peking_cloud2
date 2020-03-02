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


public class Kss_ScqkModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rq;
	
	private java.lang.String tt;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldzw;
	
	private java.lang.String cy;
	
	private java.lang.String jdr;
	
	private java.lang.String scnr;
	
	private java.lang.String yjjy;
	
	private java.lang.String zgqk;
	
	private java.lang.String jlr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlrq;
	
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

	 

	public Kss_ScqkModel(){
	}
	public Kss_ScqkModel(String id) {
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
	
	public java.util.Date getRq() {
		return this.rq;
	}
	
	public void setRq(java.util.Date value) {
		this.rq = value;
	}
	
	public java.lang.String getTt() {
		return this.tt;
	}
	
	public void setTt(java.lang.String value) {
		this.tt = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getLdzw() {
		return this.ldzw;
	}
	
	public void setLdzw(java.lang.String value) {
		this.ldzw = value;
	}
	public java.lang.String getCy() {
		return this.cy;
	}
	
	public void setCy(java.lang.String value) {
		this.cy = value;
	}
	public java.lang.String getJdr() {
		return this.jdr;
	}
	
	public void setJdr(java.lang.String value) {
		this.jdr = value;
	}
	public java.lang.String getScnr() {
		return this.scnr;
	}
	
	public void setScnr(java.lang.String value) {
		this.scnr = value;
	}
	public java.lang.String getYjjy() {
		return this.yjjy;
	}
	
	public void setYjjy(java.lang.String value) {
		this.yjjy = value;
	}
	public java.lang.String getZgqk() {
		return this.zgqk;
	}
	
	public void setZgqk(java.lang.String value) {
		this.zgqk = value;
	}
	public java.lang.String getJlr() {
		return this.jlr;
	}
	
	public void setJlr(java.lang.String value) {
		this.jlr = value;
	}
	
	public java.util.Date getJlrq() {
		return this.jlrq;
	}
	
	public void setJlrq(java.util.Date value) {
		this.jlrq = value;
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

