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


public class Kss_YpjhjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String ypbh;
	
	private java.lang.String jsbh;
	
	private java.math.BigDecimal jhsl;
	
	private java.lang.String pzwh;
	
	private java.lang.String pch;
	
	private java.math.BigDecimal jg;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date scrq;
	
	private java.lang.String bzq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dqsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jhsj;
	
	private java.lang.String jhr;
	
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
	
	private java.lang.String jhbh;
	
	private java.math.BigDecimal zje;
	//columns END

	 

	public Kss_YpjhjlModel(){
	}
	public Kss_YpjhjlModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getYpbh() {
		return this.ypbh;
	}
	
	public void setYpbh(java.lang.String value) {
		this.ypbh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.math.BigDecimal getJhsl() {
		return this.jhsl;
	}
	
	public void setJhsl(java.math.BigDecimal value) {
		this.jhsl = value;
	}
	public java.lang.String getPzwh() {
		return this.pzwh;
	}
	
	public void setPzwh(java.lang.String value) {
		this.pzwh = value;
	}
	public java.lang.String getPch() {
		return this.pch;
	}
	
	public void setPch(java.lang.String value) {
		this.pch = value;
	}
	public java.math.BigDecimal getJg() {
		return this.jg;
	}
	
	public void setJg(java.math.BigDecimal value) {
		this.jg = value;
	}
	
	public java.util.Date getScrq() {
		return this.scrq;
	}
	
	public void setScrq(java.util.Date value) {
		this.scrq = value;
	}
	
	public java.lang.String getBzq() {
		return this.bzq;
	}
	
	public void setBzq(java.lang.String value) {
		this.bzq = value;
	}
	
	public java.util.Date getDqsj() {
		return this.dqsj;
	}
	
	public void setDqsj(java.util.Date value) {
		this.dqsj = value;
	}
	
	
	public java.util.Date getJhsj() {
		return this.jhsj;
	}
	
	public void setJhsj(java.util.Date value) {
		this.jhsj = value;
	}
	
	public java.lang.String getJhr() {
		return this.jhr;
	}
	
	public void setJhr(java.lang.String value) {
		this.jhr = value;
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
	
	public java.lang.String getJhbh() {
		return this.jhbh;
	}
	
	public void setJhbh(java.lang.String value) {
		this.jhbh = value;
	}
	public java.math.BigDecimal getZje() {
		return this.zje;
	}
	
	public void setZje(java.math.BigDecimal value) {
		this.zje = value;
	}
	 
}

