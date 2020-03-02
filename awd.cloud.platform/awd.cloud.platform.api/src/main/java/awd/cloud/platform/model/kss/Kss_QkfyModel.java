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


public class Kss_QkfyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jsh;
	
	private java.lang.String fyr;
	
	private java.lang.String fyrbh;
	
	private java.lang.String bfydx;
	
	private java.lang.String bfydxbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fysj;
	
	private java.lang.String wgqk;
	
	private java.lang.String xxwgqk;
	
	private java.lang.String sfyycqk;
	
	private java.lang.String ycqk;
	
	private java.lang.String xxycqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;
	
	private java.lang.String zscs;
	
	private java.lang.String zsr;
	
	private java.lang.String fdcs;
	
	private java.lang.String fdr;
	
	private java.lang.String yxzt;
	
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

	 

	public Kss_QkfyModel(){
	}
	public Kss_QkfyModel(String id) {
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
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getFyr() {
		return this.fyr;
	}
	
	public void setFyr(java.lang.String value) {
		this.fyr = value;
	}
	public java.lang.String getFyrbh() {
		return this.fyrbh;
	}
	
	public void setFyrbh(java.lang.String value) {
		this.fyrbh = value;
	}
	public java.lang.String getBfydx() {
		return this.bfydx;
	}
	
	public void setBfydx(java.lang.String value) {
		this.bfydx = value;
	}
	public java.lang.String getBfydxbh() {
		return this.bfydxbh;
	}
	
	public void setBfydxbh(java.lang.String value) {
		this.bfydxbh = value;
	}
	
	public java.util.Date getFysj() {
		return this.fysj;
	}
	
	public void setFysj(java.util.Date value) {
		this.fysj = value;
	}
	
	public java.lang.String getWgqk() {
		return this.wgqk;
	}
	
	public void setWgqk(java.lang.String value) {
		this.wgqk = value;
	}
	public java.lang.String getXxwgqk() {
		return this.xxwgqk;
	}
	
	public void setXxwgqk(java.lang.String value) {
		this.xxwgqk = value;
	}
	public java.lang.String getSfyycqk() {
		return this.sfyycqk;
	}
	
	public void setSfyycqk(java.lang.String value) {
		this.sfyycqk = value;
	}
	public java.lang.String getYcqk() {
		return this.ycqk;
	}
	
	public void setYcqk(java.lang.String value) {
		this.ycqk = value;
	}
	public java.lang.String getXxycqk() {
		return this.xxycqk;
	}
	
	public void setXxycqk(java.lang.String value) {
		this.xxycqk = value;
	}
	
	public java.util.Date getWgsj() {
		return this.wgsj;
	}
	
	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
	}
	
	public java.lang.String getZscs() {
		return this.zscs;
	}
	
	public void setZscs(java.lang.String value) {
		this.zscs = value;
	}
	public java.lang.String getZsr() {
		return this.zsr;
	}
	
	public void setZsr(java.lang.String value) {
		this.zsr = value;
	}
	public java.lang.String getFdcs() {
		return this.fdcs;
	}
	
	public void setFdcs(java.lang.String value) {
		this.fdcs = value;
	}
	public java.lang.String getFdr() {
		return this.fdr;
	}
	
	public void setFdr(java.lang.String value) {
		this.fdr = value;
	}
	public java.lang.String getYxzt() {
		return this.yxzt;
	}
	
	public void setYxzt(java.lang.String value) {
		this.yxzt = value;
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

