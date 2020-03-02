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


public class Kss_JslxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String sqr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;
	
	private java.lang.String lyjsx;
	
	private java.lang.String lxr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lxsj;
	
	private java.lang.String lxqk;
	
	private java.lang.String lxzt;
	
	private java.lang.String rsqsfyjb;
	
	private java.lang.String rsqjbxxqk;
	
	private java.lang.String sfyjzbs;
	
	private java.lang.String jzbsxxqk;
	
	private java.lang.String sfcqfyhfyzmyw;
	
	private java.lang.String cqfyhfyzmywxxqk;
	
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
	
	private java.lang.String ywlcid;
	
	private java.lang.String pastable;
	
	private java.lang.String jsxm1;
	
	private java.lang.String gx1;
	
	private java.lang.String jslxdh1;
	
	private java.lang.String jsxm2;
	
	private java.lang.String gx2;
	
	private java.lang.String jslxdh2;
	
	private java.lang.String jsxm3;
	
	private java.lang.String gx3;
	
	private java.lang.String jslxdh3;
	//columns END

	 

	public Kss_JslxModel(){
	}
	public Kss_JslxModel(String id) {
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
	public java.lang.String getSqr() {
		return this.sqr;
	}
	
	public void setSqr(java.lang.String value) {
		this.sqr = value;
	}
	
	public java.util.Date getSqsj() {
		return this.sqsj;
	}
	
	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}
	
	public java.lang.String getLyjsx() {
		return this.lyjsx;
	}
	
	public void setLyjsx(java.lang.String value) {
		this.lyjsx = value;
	}
	public java.lang.String getLxr() {
		return this.lxr;
	}
	
	public void setLxr(java.lang.String value) {
		this.lxr = value;
	}
	
	public java.util.Date getLxsj() {
		return this.lxsj;
	}
	
	public void setLxsj(java.util.Date value) {
		this.lxsj = value;
	}
	
	public java.lang.String getLxqk() {
		return this.lxqk;
	}
	
	public void setLxqk(java.lang.String value) {
		this.lxqk = value;
	}
	public java.lang.String getLxzt() {
		return this.lxzt;
	}
	
	public void setLxzt(java.lang.String value) {
		this.lxzt = value;
	}
	public java.lang.String getRsqsfyjb() {
		return this.rsqsfyjb;
	}
	
	public void setRsqsfyjb(java.lang.String value) {
		this.rsqsfyjb = value;
	}
	public java.lang.String getRsqjbxxqk() {
		return this.rsqjbxxqk;
	}
	
	public void setRsqjbxxqk(java.lang.String value) {
		this.rsqjbxxqk = value;
	}
	public java.lang.String getSfyjzbs() {
		return this.sfyjzbs;
	}
	
	public void setSfyjzbs(java.lang.String value) {
		this.sfyjzbs = value;
	}
	public java.lang.String getJzbsxxqk() {
		return this.jzbsxxqk;
	}
	
	public void setJzbsxxqk(java.lang.String value) {
		this.jzbsxxqk = value;
	}
	public java.lang.String getSfcqfyhfyzmyw() {
		return this.sfcqfyhfyzmyw;
	}
	
	public void setSfcqfyhfyzmyw(java.lang.String value) {
		this.sfcqfyhfyzmyw = value;
	}
	public java.lang.String getCqfyhfyzmywxxqk() {
		return this.cqfyhfyzmywxxqk;
	}
	
	public void setCqfyhfyzmywxxqk(java.lang.String value) {
		this.cqfyhfyzmywxxqk = value;
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
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	public java.lang.String getJsxm1() {
		return this.jsxm1;
	}
	
	public void setJsxm1(java.lang.String value) {
		this.jsxm1 = value;
	}
	public java.lang.String getGx1() {
		return this.gx1;
	}
	
	public void setGx1(java.lang.String value) {
		this.gx1 = value;
	}
	public java.lang.String getJslxdh1() {
		return this.jslxdh1;
	}
	
	public void setJslxdh1(java.lang.String value) {
		this.jslxdh1 = value;
	}
	public java.lang.String getJsxm2() {
		return this.jsxm2;
	}
	
	public void setJsxm2(java.lang.String value) {
		this.jsxm2 = value;
	}
	public java.lang.String getGx2() {
		return this.gx2;
	}
	
	public void setGx2(java.lang.String value) {
		this.gx2 = value;
	}
	public java.lang.String getJslxdh2() {
		return this.jslxdh2;
	}
	
	public void setJslxdh2(java.lang.String value) {
		this.jslxdh2 = value;
	}
	public java.lang.String getJsxm3() {
		return this.jsxm3;
	}
	
	public void setJsxm3(java.lang.String value) {
		this.jsxm3 = value;
	}
	public java.lang.String getGx3() {
		return this.gx3;
	}
	
	public void setGx3(java.lang.String value) {
		this.gx3 = value;
	}
	public java.lang.String getJslxdh3() {
		return this.jslxdh3;
	}
	
	public void setJslxdh3(java.lang.String value) {
		this.jslxdh3 = value;
	}
	 
}

