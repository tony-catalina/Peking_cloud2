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


public class Kss_ZbdjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zbrq;
	
	private java.lang.String sld;
	
	private java.lang.String sy;
	
	private java.lang.String ts;
	
	private java.lang.String xkzb;
	
	private java.lang.String xkzhb;
	
	private java.lang.String xkwb;
	
	private java.lang.String yszb;
	
	private java.lang.String yszhb;
	
	private java.lang.String yswb;
	
	private java.lang.String gj;
	
	private java.lang.String zkzb;
	
	private java.lang.String zkzhb;
	
	private java.lang.String zkwb;
	
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
	
	private java.lang.String week;
	//columns END

	 

	public Kss_ZbdjModel(){
	}
	public Kss_ZbdjModel(String id) {
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
	
	public java.util.Date getZbrq() {
		return this.zbrq;
	}
	
	public void setZbrq(java.util.Date value) {
		this.zbrq = value;
	}
	
	public java.lang.String getSld() {
		return this.sld;
	}
	
	public void setSld(java.lang.String value) {
		this.sld = value;
	}
	public java.lang.String getSy() {
		return this.sy;
	}
	
	public void setSy(java.lang.String value) {
		this.sy = value;
	}
	public java.lang.String getTs() {
		return this.ts;
	}
	
	public void setTs(java.lang.String value) {
		this.ts = value;
	}
	public java.lang.String getXkzb() {
		return this.xkzb;
	}
	
	public void setXkzb(java.lang.String value) {
		this.xkzb = value;
	}
	public java.lang.String getXkzhb() {
		return this.xkzhb;
	}
	
	public void setXkzhb(java.lang.String value) {
		this.xkzhb = value;
	}
	public java.lang.String getXkwb() {
		return this.xkwb;
	}
	
	public void setXkwb(java.lang.String value) {
		this.xkwb = value;
	}
	public java.lang.String getYszb() {
		return this.yszb;
	}
	
	public void setYszb(java.lang.String value) {
		this.yszb = value;
	}
	public java.lang.String getYszhb() {
		return this.yszhb;
	}
	
	public void setYszhb(java.lang.String value) {
		this.yszhb = value;
	}
	public java.lang.String getYswb() {
		return this.yswb;
	}
	
	public void setYswb(java.lang.String value) {
		this.yswb = value;
	}
	public java.lang.String getGj() {
		return this.gj;
	}
	
	public void setGj(java.lang.String value) {
		this.gj = value;
	}
	public java.lang.String getZkzb() {
		return this.zkzb;
	}
	
	public void setZkzb(java.lang.String value) {
		this.zkzb = value;
	}
	public java.lang.String getZkzhb() {
		return this.zkzhb;
	}
	
	public void setZkzhb(java.lang.String value) {
		this.zkzhb = value;
	}
	public java.lang.String getZkwb() {
		return this.zkwb;
	}
	
	public void setZkwb(java.lang.String value) {
		this.zkwb = value;
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
	
	public java.lang.String getWeek() {
		return this.week;
	}
	
	public void setWeek(java.lang.String value) {
		this.week = value;
	}
	 
}

