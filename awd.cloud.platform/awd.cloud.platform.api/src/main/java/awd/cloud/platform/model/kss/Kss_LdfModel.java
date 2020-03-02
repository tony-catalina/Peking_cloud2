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


public class Kss_LdfModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String bx;
	
	private java.lang.String ly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sbrq;
	
	private java.lang.String sbr;
	
	private java.lang.String bllx;
	
	private java.lang.String zdzxm;
	
	private java.lang.String zdzyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzpssj;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
	private java.lang.String psbz;
	
	private java.lang.String ywlcid;
	
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

	 

	public Kss_LdfModel(){
	}
	public Kss_LdfModel(String id) {
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
	public java.lang.String getBx() {
		return this.bx;
	}
	
	public void setBx(java.lang.String value) {
		this.bx = value;
	}
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	
	public java.util.Date getSbrq() {
		return this.sbrq;
	}
	
	public void setSbrq(java.util.Date value) {
		this.sbrq = value;
	}
	
	public java.lang.String getSbr() {
		return this.sbr;
	}
	
	public void setSbr(java.lang.String value) {
		this.sbr = value;
	}
	public java.lang.String getBllx() {
		return this.bllx;
	}
	
	public void setBllx(java.lang.String value) {
		this.bllx = value;
	}
	public java.lang.String getZdzxm() {
		return this.zdzxm;
	}
	
	public void setZdzxm(java.lang.String value) {
		this.zdzxm = value;
	}
	public java.lang.String getZdzyj() {
		return this.zdzyj;
	}
	
	public void setZdzyj(java.lang.String value) {
		this.zdzyj = value;
	}
	
	public java.util.Date getZdzpssj() {
		return this.zdzpssj;
	}
	
	public void setZdzpssj(java.util.Date value) {
		this.zdzpssj = value;
	}
	
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
	}
	
	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}
	
	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
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

