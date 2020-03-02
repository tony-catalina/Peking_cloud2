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


public class Kss_WpglModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String wpjsid;
	
	private java.lang.String wpmc;
	
	private java.math.BigDecimal sl;
	
	private java.lang.String xh;
	
	private java.lang.String tz;
	
	private java.lang.String lqzt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lqrq;
	
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
	
	private java.lang.String lqr;
	//columns END

	 

	public Kss_WpglModel(){
	}
	public Kss_WpglModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getWpjsid() {
		return this.wpjsid;
	}
	
	public void setWpjsid(java.lang.String value) {
		this.wpjsid = value;
	}
	public java.lang.String getWpmc() {
		return this.wpmc;
	}
	
	public void setWpmc(java.lang.String value) {
		this.wpmc = value;
	}
	public java.math.BigDecimal getSl() {
		return this.sl;
	}
	
	public void setSl(java.math.BigDecimal value) {
		this.sl = value;
	}
	public java.lang.String getXh() {
		return this.xh;
	}
	
	public void setXh(java.lang.String value) {
		this.xh = value;
	}
	public java.lang.String getTz() {
		return this.tz;
	}
	
	public void setTz(java.lang.String value) {
		this.tz = value;
	}
	public java.lang.String getLqzt() {
		return this.lqzt;
	}
	
	public void setLqzt(java.lang.String value) {
		this.lqzt = value;
	}
	
	public java.util.Date getLqrq() {
		return this.lqrq;
	}
	
	public void setLqrq(java.util.Date value) {
		this.lqrq = value;
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
	
	public java.lang.String getLqr() {
		return this.lqr;
	}
	
	public void setLqr(java.lang.String value) {
		this.lqr = value;
	}
	 
}

