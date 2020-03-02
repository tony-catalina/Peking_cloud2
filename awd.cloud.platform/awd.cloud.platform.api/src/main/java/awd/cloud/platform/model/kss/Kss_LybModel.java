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


public class Kss_LybModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jq;
	
	private java.lang.String jsbh;
	
	private java.lang.String lyr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lysj;
	
	private java.lang.String yxts;
	
	private java.lang.String nr;
	
	private java.lang.String state;
	
	private java.lang.String lyrylx;
	
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
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yxrq;
	//columns END

	 

	public Kss_LybModel(){
	}
	public Kss_LybModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJq() {
		return this.jq;
	}
	
	public void setJq(java.lang.String value) {
		this.jq = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getLyr() {
		return this.lyr;
	}
	
	public void setLyr(java.lang.String value) {
		this.lyr = value;
	}
	
	public java.util.Date getLysj() {
		return this.lysj;
	}
	
	public void setLysj(java.util.Date value) {
		this.lysj = value;
	}
	
	public java.lang.String getYxts() {
		return this.yxts;
	}
	
	public void setYxts(java.lang.String value) {
		this.yxts = value;
	}
	public java.lang.String getNr() {
		return this.nr;
	}
	
	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getLyrylx() {
		return this.lyrylx;
	}
	
	public void setLyrylx(java.lang.String value) {
		this.lyrylx = value;
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
	
	
	public java.util.Date getYxrq() {
		return this.yxrq;
	}
	
	public void setYxrq(java.util.Date value) {
		this.yxrq = value;
	}
	
	 
}

