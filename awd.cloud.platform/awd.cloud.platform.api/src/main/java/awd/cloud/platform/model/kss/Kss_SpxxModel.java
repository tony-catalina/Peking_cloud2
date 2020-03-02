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


public class Kss_SpxxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String pch;
	
	private java.lang.String spmc;
	
	private java.lang.String tm;
	
	private java.lang.String gg;
	
	private java.lang.String jldw;
	
	private java.math.BigDecimal lsj;
	
	private java.lang.String splb;
	
	private java.lang.String sfzjff;
	
	private java.lang.String sfxg;
	
	private java.lang.String jhpl;
	
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

	 

	public Kss_SpxxModel(){
	}
	public Kss_SpxxModel(String id) {
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
	public java.lang.String getPch() {
		return this.pch;
	}
	
	public void setPch(java.lang.String value) {
		this.pch = value;
	}
	public java.lang.String getSpmc() {
		return this.spmc;
	}
	
	public void setSpmc(java.lang.String value) {
		this.spmc = value;
	}
	public java.lang.String getTm() {
		return this.tm;
	}
	
	public void setTm(java.lang.String value) {
		this.tm = value;
	}
	public java.lang.String getGg() {
		return this.gg;
	}
	
	public void setGg(java.lang.String value) {
		this.gg = value;
	}
	public java.lang.String getJldw() {
		return this.jldw;
	}
	
	public void setJldw(java.lang.String value) {
		this.jldw = value;
	}
	public java.math.BigDecimal getLsj() {
		return this.lsj;
	}
	
	public void setLsj(java.math.BigDecimal value) {
		this.lsj = value;
	}
	public java.lang.String getSplb() {
		return this.splb;
	}
	
	public void setSplb(java.lang.String value) {
		this.splb = value;
	}
	public java.lang.String getSfzjff() {
		return this.sfzjff;
	}
	
	public void setSfzjff(java.lang.String value) {
		this.sfzjff = value;
	}
	public java.lang.String getSfxg() {
		return this.sfxg;
	}
	
	public void setSfxg(java.lang.String value) {
		this.sfxg = value;
	}
	public java.lang.String getJhpl() {
		return this.jhpl;
	}
	
	public void setJhpl(java.lang.String value) {
		this.jhpl = value;
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

