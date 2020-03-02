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


public class Kss_JsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jqh;
	
	private java.lang.String jsmc;
	
	private java.lang.String jsh;
	
	private java.lang.String jslb;
	
	private java.lang.String type;
	
	private java.lang.Integer innum;
	
	private java.lang.Integer bznum;
	
	private java.lang.String zgmj;
	
	private java.lang.String xgmj;
	
	private java.lang.String wmjs;
	
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

	 

	public Kss_JsModel(){
	}
	public Kss_JsModel(String id) {
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
	public java.lang.String getJqh() {
		return this.jqh;
	}
	
	public void setJqh(java.lang.String value) {
		this.jqh = value;
	}
	public java.lang.String getJsmc() {
		return this.jsmc;
	}
	
	public void setJsmc(java.lang.String value) {
		this.jsmc = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getJslb() {
		return this.jslb;
	}
	
	public void setJslb(java.lang.String value) {
		this.jslb = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.Integer getInnum() {
		return this.innum;
	}
	
	public void setInnum(java.lang.Integer value) {
		this.innum = value;
	}
	public java.lang.Integer getBznum() {
		return this.bznum;
	}
	
	public void setBznum(java.lang.Integer value) {
		this.bznum = value;
	}
	public java.lang.String getZgmj() {
		return this.zgmj;
	}
	
	public void setZgmj(java.lang.String value) {
		this.zgmj = value;
	}
	public java.lang.String getXgmj() {
		return this.xgmj;
	}
	
	public void setXgmj(java.lang.String value) {
		this.xgmj = value;
	}
	public java.lang.String getWmjs() {
		return this.wmjs;
	}
	
	public void setWmjs(java.lang.String value) {
		this.wmjs = value;
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

