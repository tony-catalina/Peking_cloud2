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


public class Kss_SxsphjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String hjly;
	
	private java.lang.String sqr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;
	
	private java.lang.String hjrid;
	
	private java.lang.String hjdd;
	
	private java.lang.String ltzh;
	
	private java.lang.String fkfs;
	
	private java.lang.String fkdx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fkrq;
	
	private java.lang.String fkqk;
	
	private java.lang.String fkr;
	
	private java.lang.String ywlcid;
	
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

	 

	public Kss_SxsphjModel(){
	}
	public Kss_SxsphjModel(String id) {
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
	public java.lang.String getSqr() {
		return this.sqr;
	}
	
	public void setSqr(java.lang.String value) {
		this.sqr = value;
	}
	
	public java.util.Date getSqsj() {
		return this.sqsj;
	}
	
	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}
	
	public java.lang.String getHjrid() {
		return this.hjrid;
	}
	
	public void setHjrid(java.lang.String value) {
		this.hjrid = value;
	}
	public java.lang.String getHjdd() {
		return this.hjdd;
	}
	
	public void setHjdd(java.lang.String value) {
		this.hjdd = value;
	}
	public java.lang.String getLtzh() {
		return this.ltzh;
	}
	
	public void setLtzh(java.lang.String value) {
		this.ltzh = value;
	}
	public java.lang.String getFkfs() {
		return this.fkfs;
	}
	
	public void setFkfs(java.lang.String value) {
		this.fkfs = value;
	}
	public java.lang.String getFkdx() {
		return this.fkdx;
	}
	
	public void setFkdx(java.lang.String value) {
		this.fkdx = value;
	}
	
	public java.util.Date getFkrq() {
		return this.fkrq;
	}
	
	public void setFkrq(java.util.Date value) {
		this.fkrq = value;
	}
	
	public java.lang.String getFkqk() {
		return this.fkqk;
	}
	
	public void setFkqk(java.lang.String value) {
		this.fkqk = value;
	}
	public java.lang.String getFkr() {
		return this.fkr;
	}
	
	public void setFkr(java.lang.String value) {
		this.fkr = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
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

