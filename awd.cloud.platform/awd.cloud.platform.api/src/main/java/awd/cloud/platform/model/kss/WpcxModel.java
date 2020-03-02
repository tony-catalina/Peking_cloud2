/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;


import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.bj.base.model.Model;
import awd.cloud.platform.utils.CacheUtils;
/**
 * @author mzp
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class WpcxModel implements Model {
	
	 
	 private java.lang.String rybh;
	 private java.lang.String jsbh;
	 private java.lang.String lqwpmc;
	 private java.lang.String lqwpmcString;

	 private java.lang.Integer sl;
	 
	 private java.lang.String xh;
	 
	 private java.lang.String tz;
	 
	 private java.lang.String lqzt;
	 private java.lang.String lqztString;

	 private java.lang.String bz;
	 
	 private java.lang.String jslx;
	 private java.lang.String jslxString;

	 private java.lang.String jszjh;
	 
	 private java.lang.String djr;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	 private java.util.Date djsj;
	 private java.lang.String djsjString;
	 
	 private java.lang.String fzdbh;
	 
	 private java.lang.String fzdsl;
	 
	 private java.lang.String djwpsl;
	 
	 private java.lang.String djwpbh;
	 
	 private java.lang.String gx;
	 private java.lang.String gxString;

	 private java.lang.String jsxm;
	 
	 private java.lang.String dh;
	 
	 private java.lang.String dz;
	 
	 private java.lang.String lqr;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	 @JSONField(format = "yyyy-MM-dd HH:mm:ss")
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	 private java.util.Date lqrq;
	 private java.lang.String lqrqString;
	 
	 private java.util.Date blr;
	 
	 private java.lang.String taskid;
	 
	 private java.lang.String wpglid;
	 
	 private String wpmcString;
	 
	 
	 

	

	public java.lang.String getRybh() {
		return rybh;
	}

	public void setRybh(java.lang.String rybh) {
		this.rybh = rybh;
	}



	public java.lang.String getJsbh() {
		return jsbh;
	}

	public void setJsbh(java.lang.String jsbh) {
		this.jsbh = jsbh;
	}



	public java.lang.String getLqwpmc() {
		return lqwpmc;
	}

	public void setLqwpmc(java.lang.String lqwpmc) {
		this.lqwpmc = lqwpmc;
	}

	public java.lang.Integer getSl() {
		return sl;
	}

	public void setSl(java.lang.Integer sl) {
		this.sl = sl;
	}

	public java.lang.String getXh() {
		return xh;
	}

	public void setXh(java.lang.String xh) {
		this.xh = xh;
	}

	public java.lang.String getTz() {
		return tz;
	}

	public void setTz(java.lang.String tz) {
		this.tz = tz;
	}

	public java.lang.String getLqzt() {
		return lqzt;
	}

	public void setLqzt(java.lang.String lqzt) {
		this.lqzt = lqzt;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.String getJslx() {
		return jslx;
	}

	public void setJslx(java.lang.String jslx) {
		this.jslx = jslx;
	}

	public java.lang.String getJszjh() {
		return jszjh;
	}

	public void setJszjh(java.lang.String jszjh) {
		this.jszjh = jszjh;
	}

	public java.lang.String getDjr() {
		return djr;
	}

	public void setDjr(java.lang.String djr) {
		this.djr = djr;
	}

	public java.util.Date getDjsj() {
		return djsj;
	}

	public void setDjsj(java.util.Date djsj) {
		this.djsj = djsj;
	}

	public java.lang.String getFzdbh() {
		return fzdbh;
	}

	public void setFzdbh(java.lang.String fzdbh) {
		this.fzdbh = fzdbh;
	}

	public java.lang.String getFzdsl() {
		return fzdsl;
	}

	public void setFzdsl(java.lang.String fzdsl) {
		this.fzdsl = fzdsl;
	}

	public java.lang.String getDjwpsl() {
		return djwpsl;
	}

	public void setDjwpsl(java.lang.String djwpsl) {
		this.djwpsl = djwpsl;
	}

	public java.lang.String getDjwpbh() {
		return djwpbh;
	}

	public void setDjwpbh(java.lang.String djwpbh) {
		this.djwpbh = djwpbh;
	}

	public java.lang.String getGx() {
		return gx;
	}

	public void setGx(java.lang.String gx) {
		this.gx = gx;
	}

	public java.lang.String getJsxm() {
		return jsxm;
	}

	public void setJsxm(java.lang.String jsxm) {
		this.jsxm = jsxm;
	}

	public java.lang.String getDh() {
		return dh;
	}

	public void setDh(java.lang.String dh) {
		this.dh = dh;
	}

	public java.lang.String getDz() {
		return dz;
	}

	public void setDz(java.lang.String dz) {
		this.dz = dz;
	}

	public java.lang.String getLqr() {
		return lqr;
	}

	public void setLqr(java.lang.String lqr) {
		this.lqr = lqr;
	}

	public java.util.Date getLqrq() {
		return lqrq;
	}

	public void setLqrq(java.util.Date lqrq) {
		this.lqrq = lqrq;
	}

	public java.util.Date getBlr() {
		return blr;
	}

	public void setBlr(java.util.Date blr) {
		this.blr = blr;
	}

	public java.lang.String getDjsjString() {
		return djsjString;
	}

	public void setDjsjString(java.lang.String djsjString) {
		this.djsjString = djsjString;
	}

	public java.lang.String getLqrqString() {
		return lqrqString;
	}

	public void setLqrqString(java.lang.String lqrqString) {
		this.lqrqString = lqrqString;
	}

	public java.lang.String getTaskid() {
		return taskid;
	}

	public void setTaskid(java.lang.String taskid) {
		this.taskid = taskid;
	}

	public java.lang.String getWpglid() {
		return wpglid;
	}

	public void setWpglid(java.lang.String wpglid) {
		this.wpglid = wpglid;
	}

	public String getLqwpmcString() {
		return CacheUtils.get().getDictionary("JJWP", this.lqwpmc);
	}

	public String getLqztString() {
		return CacheUtils.get().getDictionary("WPLQZT", this.lqzt);
	}

	public String getJslxString() {
		return jslxString;
	}

	public String getGxString() {
		return CacheUtils.get().getDictionary("GX", this.gx);
	}

	public void setLqwpmcString(String lqwpmcString) {
		this.lqwpmcString = lqwpmcString;
	}

	public void setLqztString(String lqztString) {
		this.lqztString = lqztString;
	}

	public void setJslxString(String jslxString) {
		this.jslxString = jslxString;
	}

	public void setGxString(String gxString) {
		this.gxString = gxString;
	}
}

