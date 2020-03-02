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


public class Kss_JqfbModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String xm;
	
	private java.lang.String jh;
	
	private java.lang.String fbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fbsj;
	
	private java.lang.String fbdd;
	
	private java.lang.String jqnr;
	
	private java.lang.String cljg;
	
	private java.lang.String bz;
	
	private java.lang.String ly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;
	
	private java.lang.String clr;
	
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
	
	private java.lang.String state;
	//columns END

	 

	public Kss_JqfbModel(){
	}
	public Kss_JqfbModel(String id) {
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
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	public java.lang.String getFbr() {
		return this.fbr;
	}
	
	public void setFbr(java.lang.String value) {
		this.fbr = value;
	}
	
	public java.util.Date getFbsj() {
		return this.fbsj;
	}
	
	public void setFbsj(java.util.Date value) {
		this.fbsj = value;
	}
	
	public java.lang.String getFbdd() {
		return this.fbdd;
	}
	
	public void setFbdd(java.lang.String value) {
		this.fbdd = value;
	}
	public java.lang.String getJqnr() {
		return this.jqnr;
	}
	
	public void setJqnr(java.lang.String value) {
		this.jqnr = value;
	}
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	
	public java.util.Date getClsj() {
		return this.clsj;
	}
	
	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}
	
	public java.lang.String getClr() {
		return this.clr;
	}
	
	public void setClr(java.lang.String value) {
		this.clr = value;
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
	
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	 
}

