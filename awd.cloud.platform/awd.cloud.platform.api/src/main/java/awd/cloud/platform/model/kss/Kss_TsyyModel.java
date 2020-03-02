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


public class Kss_TsyyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String dw;
	
	private java.lang.String ry;
	
	private java.lang.String dwdh;
	
	private java.lang.String sjhm;
	
	private java.lang.String cxyy;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyrq;
	
	private java.lang.String yysjd;
	
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
	
	private java.lang.String lrr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lrsj;
	
	private java.lang.String bz;
	//columns END

	 

	public Kss_TsyyModel(){
	}
	public Kss_TsyyModel(String id) {
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
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	public java.lang.String getRy() {
		return this.ry;
	}
	
	public void setRy(java.lang.String value) {
		this.ry = value;
	}
	public java.lang.String getDwdh() {
		return this.dwdh;
	}
	
	public void setDwdh(java.lang.String value) {
		this.dwdh = value;
	}
	public java.lang.String getSjhm() {
		return this.sjhm;
	}
	
	public void setSjhm(java.lang.String value) {
		this.sjhm = value;
	}
	public java.lang.String getCxyy() {
		return this.cxyy;
	}
	
	public void setCxyy(java.lang.String value) {
		this.cxyy = value;
	}
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	
	public java.util.Date getYyrq() {
		return this.yyrq;
	}
	
	public void setYyrq(java.util.Date value) {
		this.yyrq = value;
	}
	
	public java.lang.String getYysjd() {
		return this.yysjd;
	}
	
	public void setYysjd(java.lang.String value) {
		this.yysjd = value;
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
	
	public java.lang.String getLrr() {
		return this.lrr;
	}
	
	public void setLrr(java.lang.String value) {
		this.lrr = value;
	}
	
	public java.util.Date getLrsj() {
		return this.lrsj;
	}
	
	public void setLrsj(java.util.Date value) {
		this.lrsj = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	 
}

