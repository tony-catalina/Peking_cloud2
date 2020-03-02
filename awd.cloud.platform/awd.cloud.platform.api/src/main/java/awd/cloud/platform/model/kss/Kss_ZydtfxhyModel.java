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


public class Kss_ZydtfxhyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hysj;
	
	private java.lang.String hydd;
	
	private java.lang.String hyzcr;
	
	private java.lang.String hyjlr;
	
	private java.lang.String cjhyry;
	
	private java.lang.String hynr;
	
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
	//columns END

	 

	public Kss_ZydtfxhyModel(){
	}
	public Kss_ZydtfxhyModel(String id) {
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
	
	public java.util.Date getHysj() {
		return this.hysj;
	}
	
	public void setHysj(java.util.Date value) {
		this.hysj = value;
	}
	
	public java.lang.String getHydd() {
		return this.hydd;
	}
	
	public void setHydd(java.lang.String value) {
		this.hydd = value;
	}
	public java.lang.String getHyzcr() {
		return this.hyzcr;
	}
	
	public void setHyzcr(java.lang.String value) {
		this.hyzcr = value;
	}
	public java.lang.String getHyjlr() {
		return this.hyjlr;
	}
	
	public void setHyjlr(java.lang.String value) {
		this.hyjlr = value;
	}
	public java.lang.String getCjhyry() {
		return this.cjhyry;
	}
	
	public void setCjhyry(java.lang.String value) {
		this.cjhyry = value;
	}
	public java.lang.String getHynr() {
		return this.hynr;
	}
	
	public void setHynr(java.lang.String value) {
		this.hynr = value;
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
	
	 
}

