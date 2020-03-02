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


public class Kss_SphjsqModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String hjly;
	
	private java.lang.String jyqjbx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date swhzksj;
	
	private java.lang.String cjry;
	
	private java.lang.String zdzyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzpssj;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
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

	 

	public Kss_SphjsqModel(){
	}
	public Kss_SphjsqModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getHjly() {
		return this.hjly;
	}
	
	public void setHjly(java.lang.String value) {
		this.hjly = value;
	}
	public java.lang.String getJyqjbx() {
		return this.jyqjbx;
	}
	
	public void setJyqjbx(java.lang.String value) {
		this.jyqjbx = value;
	}
	
	public java.util.Date getSwhzksj() {
		return this.swhzksj;
	}
	
	public void setSwhzksj(java.util.Date value) {
		this.swhzksj = value;
	}
	
	public java.lang.String getCjry() {
		return this.cjry;
	}
	
	public void setCjry(java.lang.String value) {
		this.cjry = value;
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

