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


public class Kss_FxpgRygkcsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String gkcs;
	
	private java.lang.String lszt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lssj;
	
	private java.lang.String pastable;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;
	
	private java.lang.String saveuser;
	
	private java.lang.String lsr;
	
	private java.lang.String lsqk;
	
	private java.lang.String zzr;
	
	private java.lang.String gklx;
	
	private java.lang.String lsh;
	
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
	
	private java.lang.String pglx;
	
	private java.lang.String yfxdj;
	
	private java.lang.String xfxdj;
	
	private java.lang.String yfxzb;
	
	private java.lang.String xfxzb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pgsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ypgsj;
	//columns END

	 

	public Kss_FxpgRygkcsModel(){
	}
	public Kss_FxpgRygkcsModel(String id) {
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
	public java.lang.String getGkcs() {
		return this.gkcs;
	}
	
	public void setGkcs(java.lang.String value) {
		this.gkcs = value;
	}
	public java.lang.String getLszt() {
		return this.lszt;
	}
	
	public void setLszt(java.lang.String value) {
		this.lszt = value;
	}
	
	public java.util.Date getLssj() {
		return this.lssj;
	}
	
	public void setLssj(java.util.Date value) {
		this.lssj = value;
	}
	
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	
	public java.util.Date getSavetime() {
		return this.savetime;
	}
	
	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}
	
	public java.lang.String getSaveuser() {
		return this.saveuser;
	}
	
	public void setSaveuser(java.lang.String value) {
		this.saveuser = value;
	}
	public java.lang.String getLsr() {
		return this.lsr;
	}
	
	public void setLsr(java.lang.String value) {
		this.lsr = value;
	}
	public java.lang.String getLsqk() {
		return this.lsqk;
	}
	
	public void setLsqk(java.lang.String value) {
		this.lsqk = value;
	}
	public java.lang.String getZzr() {
		return this.zzr;
	}
	
	public void setZzr(java.lang.String value) {
		this.zzr = value;
	}
	public java.lang.String getGklx() {
		return this.gklx;
	}
	
	public void setGklx(java.lang.String value) {
		this.gklx = value;
	}
	public java.lang.String getLsh() {
		return this.lsh;
	}
	
	public void setLsh(java.lang.String value) {
		this.lsh = value;
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
	
	public java.lang.String getPglx() {
		return this.pglx;
	}
	
	public void setPglx(java.lang.String value) {
		this.pglx = value;
	}
	public java.lang.String getYfxdj() {
		return this.yfxdj;
	}
	
	public void setYfxdj(java.lang.String value) {
		this.yfxdj = value;
	}
	public java.lang.String getXfxdj() {
		return this.xfxdj;
	}
	
	public void setXfxdj(java.lang.String value) {
		this.xfxdj = value;
	}
	public java.lang.String getYfxzb() {
		return this.yfxzb;
	}
	
	public void setYfxzb(java.lang.String value) {
		this.yfxzb = value;
	}
	public java.lang.String getXfxzb() {
		return this.xfxzb;
	}
	
	public void setXfxzb(java.lang.String value) {
		this.xfxzb = value;
	}
	
	public java.util.Date getPgsj() {
		return this.pgsj;
	}
	
	public void setPgsj(java.util.Date value) {
		this.pgsj = value;
	}
	
	
	public java.util.Date getYpgsj() {
		return this.ypgsj;
	}
	
	public void setYpgsj(java.util.Date value) {
		this.ypgsj = value;
	}
	
	 
}

