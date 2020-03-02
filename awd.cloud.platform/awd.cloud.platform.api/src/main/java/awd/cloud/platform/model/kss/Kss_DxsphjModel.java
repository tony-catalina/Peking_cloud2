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


public class Kss_DxsphjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String hjrid;
	
	private java.lang.String hjly;
	
	private java.lang.String jyqjbx;
	
	private java.lang.String sfhcqk;
	
	private java.lang.String hjlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjjssj;
	
	private java.lang.String bhjrdd;
	
	private java.lang.String bhjrbx;
	
	private java.lang.String hjrdd;
	
	private java.lang.String hjrbx;
	
	private java.lang.String jsyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date swhzksj;
	
	private java.lang.String lxrid;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lxsj;
	
	private java.lang.String lxdh;
	
	private java.lang.String lxqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date nhjsj;
	
	private java.lang.String cjry;
	
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
	
	private java.lang.String ywlcid;
	
	private java.lang.String psbz;
	
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

	 

	public Kss_DxsphjModel(){
	}
	public Kss_DxsphjModel(String id) {
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
	public java.lang.String getHjrid() {
		return this.hjrid;
	}
	
	public void setHjrid(java.lang.String value) {
		this.hjrid = value;
	}
	public java.lang.String getHjly() {
		return this.hjly;
	}
	
	public void setHjly(java.lang.String value) {
		this.hjly = value;
	}
	public java.lang.String getJyqjbx() {
		return this.jyqjbx;
	}
	
	public void setJyqjbx(java.lang.String value) {
		this.jyqjbx = value;
	}
	public java.lang.String getSfhcqk() {
		return this.sfhcqk;
	}
	
	public void setSfhcqk(java.lang.String value) {
		this.sfhcqk = value;
	}
	public java.lang.String getHjlx() {
		return this.hjlx;
	}
	
	public void setHjlx(java.lang.String value) {
		this.hjlx = value;
	}
	
	public java.util.Date getHjkssj() {
		return this.hjkssj;
	}
	
	public void setHjkssj(java.util.Date value) {
		this.hjkssj = value;
	}
	
	
	public java.util.Date getHjjssj() {
		return this.hjjssj;
	}
	
	public void setHjjssj(java.util.Date value) {
		this.hjjssj = value;
	}
	
	public java.lang.String getBhjrdd() {
		return this.bhjrdd;
	}
	
	public void setBhjrdd(java.lang.String value) {
		this.bhjrdd = value;
	}
	public java.lang.String getBhjrbx() {
		return this.bhjrbx;
	}
	
	public void setBhjrbx(java.lang.String value) {
		this.bhjrbx = value;
	}
	public java.lang.String getHjrdd() {
		return this.hjrdd;
	}
	
	public void setHjrdd(java.lang.String value) {
		this.hjrdd = value;
	}
	public java.lang.String getHjrbx() {
		return this.hjrbx;
	}
	
	public void setHjrbx(java.lang.String value) {
		this.hjrbx = value;
	}
	public java.lang.String getJsyy() {
		return this.jsyy;
	}
	
	public void setJsyy(java.lang.String value) {
		this.jsyy = value;
	}
	
	public java.util.Date getSwhzksj() {
		return this.swhzksj;
	}
	
	public void setSwhzksj(java.util.Date value) {
		this.swhzksj = value;
	}
	
	public java.lang.String getLxrid() {
		return this.lxrid;
	}
	
	public void setLxrid(java.lang.String value) {
		this.lxrid = value;
	}
	
	public java.util.Date getLxsj() {
		return this.lxsj;
	}
	
	public void setLxsj(java.util.Date value) {
		this.lxsj = value;
	}
	
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	public java.lang.String getLxqk() {
		return this.lxqk;
	}
	
	public void setLxqk(java.lang.String value) {
		this.lxqk = value;
	}
	
	public java.util.Date getNhjsj() {
		return this.nhjsj;
	}
	
	public void setNhjsj(java.util.Date value) {
		this.nhjsj = value;
	}
	
	public java.lang.String getCjry() {
		return this.cjry;
	}
	
	public void setCjry(java.lang.String value) {
		this.cjry = value;
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
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
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

