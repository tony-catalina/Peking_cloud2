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


public class Kss_XfmxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsh;
	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String sp;
	
	private java.lang.String splx;
	
	private java.lang.String dw;
	
	private java.lang.Short sl;
	
	private java.lang.Short xfje;
	
	private java.lang.String sfyxdd;
	
	private java.lang.String szlsid;
	
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

	 

	public Kss_XfmxModel(){
	}
	public Kss_XfmxModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
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
	public java.lang.String getSp() {
		return this.sp;
	}
	
	public void setSp(java.lang.String value) {
		this.sp = value;
	}
	public java.lang.String getSplx() {
		return this.splx;
	}
	
	public void setSplx(java.lang.String value) {
		this.splx = value;
	}
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	public java.lang.Short getSl() {
		return this.sl;
	}
	
	public void setSl(java.lang.Short value) {
		this.sl = value;
	}
	public java.lang.Short getXfje() {
		return this.xfje;
	}
	
	public void setXfje(java.lang.Short value) {
		this.xfje = value;
	}
	public java.lang.String getSfyxdd() {
		return this.sfyxdd;
	}
	
	public void setSfyxdd(java.lang.String value) {
		this.sfyxdd = value;
	}
	public java.lang.String getSzlsid() {
		return this.szlsid;
	}
	
	public void setSzlsid(java.lang.String value) {
		this.szlsid = value;
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

