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


public class Kss_ShgxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String jsxm;
	
	private java.lang.String xb;
	
	private java.lang.String nl;
	
	private java.lang.String jszjh;
	
	private java.lang.String gx;
	
	private java.lang.String dwdh;
	
	private java.lang.String gzdw;
	
	private java.lang.String sj;
	
	private java.lang.String dh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;
	
	private java.lang.String mz;
	
	private java.lang.String zzdz;
	
	private java.lang.String dz;
	
	private java.lang.String yb;
	
	private java.lang.String bz;
	
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
	
	private java.lang.String zjlx;
	
	private java.lang.String zy;
	
	private java.lang.String sfswgx;
	//columns END

	 

	public Kss_ShgxModel(){
	}
	public Kss_ShgxModel(String id) {
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
	public java.lang.String getJsxm() {
		return this.jsxm;
	}
	
	public void setJsxm(java.lang.String value) {
		this.jsxm = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	public java.lang.String getNl() {
		return this.nl;
	}
	
	public void setNl(java.lang.String value) {
		this.nl = value;
	}
	public java.lang.String getJszjh() {
		return this.jszjh;
	}
	
	public void setJszjh(java.lang.String value) {
		this.jszjh = value;
	}
	public java.lang.String getGx() {
		return this.gx;
	}
	
	public void setGx(java.lang.String value) {
		this.gx = value;
	}
	public java.lang.String getDwdh() {
		return this.dwdh;
	}
	
	public void setDwdh(java.lang.String value) {
		this.dwdh = value;
	}
	public java.lang.String getGzdw() {
		return this.gzdw;
	}
	
	public void setGzdw(java.lang.String value) {
		this.gzdw = value;
	}
	public java.lang.String getSj() {
		return this.sj;
	}
	
	public void setSj(java.lang.String value) {
		this.sj = value;
	}
	public java.lang.String getDh() {
		return this.dh;
	}
	
	public void setDh(java.lang.String value) {
		this.dh = value;
	}
	
	public java.util.Date getCsrq() {
		return this.csrq;
	}
	
	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}
	
	public java.lang.String getMz() {
		return this.mz;
	}
	
	public void setMz(java.lang.String value) {
		this.mz = value;
	}
	public java.lang.String getZzdz() {
		return this.zzdz;
	}
	
	public void setZzdz(java.lang.String value) {
		this.zzdz = value;
	}
	public java.lang.String getDz() {
		return this.dz;
	}
	
	public void setDz(java.lang.String value) {
		this.dz = value;
	}
	public java.lang.String getYb() {
		return this.yb;
	}
	
	public void setYb(java.lang.String value) {
		this.yb = value;
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
	
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
	}
	public java.lang.String getSfswgx() {
		return this.sfswgx;
	}
	
	public void setSfswgx(java.lang.String value) {
		this.sfswgx = value;
	}
	 
}

