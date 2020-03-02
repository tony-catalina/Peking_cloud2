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


public class Kss_YabzModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String yadw;
	
	private java.lang.String yalx;
	
	private java.lang.String yadj;
	
	private java.lang.String czfa;
	
	private java.lang.String bzcs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sxsj;
	
	private java.lang.String xgry;
	
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

	 

	public Kss_YabzModel(){
	}
	public Kss_YabzModel(String id) {
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
	public java.lang.String getYadw() {
		return this.yadw;
	}
	
	public void setYadw(java.lang.String value) {
		this.yadw = value;
	}
	public java.lang.String getYalx() {
		return this.yalx;
	}
	
	public void setYalx(java.lang.String value) {
		this.yalx = value;
	}
	public java.lang.String getYadj() {
		return this.yadj;
	}
	
	public void setYadj(java.lang.String value) {
		this.yadj = value;
	}
	public java.lang.String getCzfa() {
		return this.czfa;
	}
	
	public void setCzfa(java.lang.String value) {
		this.czfa = value;
	}
	public java.lang.String getBzcs() {
		return this.bzcs;
	}
	
	public void setBzcs(java.lang.String value) {
		this.bzcs = value;
	}
	
	public java.util.Date getSxsj() {
		return this.sxsj;
	}
	
	public void setSxsj(java.util.Date value) {
		this.sxsj = value;
	}
	
	public java.lang.String getXgry() {
		return this.xgry;
	}
	
	public void setXgry(java.lang.String value) {
		this.xgry = value;
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

