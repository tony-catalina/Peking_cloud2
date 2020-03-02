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


public class Kss_FssgModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String qklx;
	
	private java.lang.String sglx;
	
	private java.lang.String sgxz;
	
	private java.lang.String sjlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sgsj;
	
	private java.lang.String sgdd;
	
	private java.lang.String sjry;
	
	private java.lang.String jyqk;
	
	private java.lang.String cljg;
	
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String creator;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	
	private java.lang.String sgrs;
	//columns END

	 

	public Kss_FssgModel(){
	}
	public Kss_FssgModel(String id) {
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
	public java.lang.String getQklx() {
		return this.qklx;
	}
	
	public void setQklx(java.lang.String value) {
		this.qklx = value;
	}
	public java.lang.String getSglx() {
		return this.sglx;
	}
	
	public void setSglx(java.lang.String value) {
		this.sglx = value;
	}
	public java.lang.String getSgxz() {
		return this.sgxz;
	}
	
	public void setSgxz(java.lang.String value) {
		this.sgxz = value;
	}
	public java.lang.String getSjlx() {
		return this.sjlx;
	}
	
	public void setSjlx(java.lang.String value) {
		this.sjlx = value;
	}
	
	public java.util.Date getSgsj() {
		return this.sgsj;
	}
	
	public void setSgsj(java.util.Date value) {
		this.sgsj = value;
	}
	
	public java.lang.String getSgdd() {
		return this.sgdd;
	}
	
	public void setSgdd(java.lang.String value) {
		this.sgdd = value;
	}
	public java.lang.String getSjry() {
		return this.sjry;
	}
	
	public void setSjry(java.lang.String value) {
		this.sjry = value;
	}
	public java.lang.String getJyqk() {
		return this.jyqk;
	}
	
	public void setJyqk(java.lang.String value) {
		this.jyqk = value;
	}
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
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
	
	public java.lang.String getSgrs() {
		return this.sgrs;
	}
	
	public void setSgrs(java.lang.String value) {
		this.sgrs = value;
	}
	 
}

