/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FxpgRygkcsModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String gkcs;

	private String lszt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lssj;

	private String pastable;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date savetime;

	private String saveuser;

	private String lsr;

	private String lsqk;

	private String zzr;

	private String gklx;

	private String lsh;

	private String bz;

	private String state;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String pglx;

	private String yfxdj;

	private String xfxdj;

	private String yfxzb;

	private String xfxzb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pgsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ypgsj;
	//columns END



	public FxpgRygkcsModel(){
	}
	public FxpgRygkcsModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getGkcs() {
		return this.gkcs;
	}

	public void setGkcs(String value) {
		this.gkcs = value;
	}
	public String getLszt() {
		return this.lszt;
	}

	public void setLszt(String value) {
		this.lszt = value;
	}

	public java.util.Date getLssj() {
		return this.lssj;
	}

	public void setLssj(java.util.Date value) {
		this.lssj = value;
	}

	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}

	public java.util.Date getSavetime() {
		return this.savetime;
	}

	public void setSavetime(java.util.Date value) {
		this.savetime = value;
	}

	public String getSaveuser() {
		return this.saveuser;
	}

	public void setSaveuser(String value) {
		this.saveuser = value;
	}
	public String getLsr() {
		return this.lsr;
	}

	public void setLsr(String value) {
		this.lsr = value;
	}
	public String getLsqk() {
		return this.lsqk;
	}

	public void setLsqk(String value) {
		this.lsqk = value;
	}
	public String getZzr() {
		return this.zzr;
	}

	public void setZzr(String value) {
		this.zzr = value;
	}
	public String getGklx() {
		return this.gklx;
	}

	public void setGklx(String value) {
		this.gklx = value;
	}
	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String value) {
		this.lsh = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public String getPglx() {
		return this.pglx;
	}

	public void setPglx(String value) {
		this.pglx = value;
	}
	public String getYfxdj() {
		return this.yfxdj;
	}

	public void setYfxdj(String value) {
		this.yfxdj = value;
	}
	public String getXfxdj() {
		return this.xfxdj;
	}

	public void setXfxdj(String value) {
		this.xfxdj = value;
	}
	public String getYfxzb() {
		return this.yfxzb;
	}

	public void setYfxzb(String value) {
		this.yfxzb = value;
	}
	public String getXfxzb() {
		return this.xfxzb;
	}

	public void setXfxzb(String value) {
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

