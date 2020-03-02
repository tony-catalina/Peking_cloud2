/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FjszModel implements Model {
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String fjmc;
	private java.lang.String fjhm;
	private java.lang.String rybh;
	private java.lang.String fjqc;
	private java.lang.String syry;
	private java.lang.String fjlx;
	private java.lang.String fjlxString;
	private java.lang.String yyfj;
	private java.lang.String yyfjString;
	private java.lang.String syzt;
	private java.lang.String syztString;
	private java.lang.String bz;
	private java.lang.String state;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END


	public FjszModel(){
	}

	public FjszModel(
		java.lang.String id
	){
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
	public java.lang.String getFjmc() {
		return this.fjmc;
	}
	
	public void setFjmc(java.lang.String value) {
		this.fjmc = value;
	}
	public java.lang.String getFjhm() {
		return this.fjhm;
	}
	
	public void setFjhm(java.lang.String value) {
		this.fjhm = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getFjqc() {
		return this.fjqc;
	}
	
	public void setFjqc(java.lang.String value) {
		this.fjqc = value;
	}
	public java.lang.String getSyry() {
		return this.syry;
	}
	
	public void setSyry(java.lang.String value) {
		this.syry = value;
	}
	public java.lang.String getFjlx() {
		return this.fjlx;
	}
	
	public void setFjlx(java.lang.String value) {
		this.fjlx = value;
	}
	public java.lang.String getYyfj() {
		return this.yyfj;
	}
	
	public void setYyfj(java.lang.String value) {
		this.yyfj = value;
	}
	public java.lang.String getSyzt() {
		return this.syzt;
	}
	
	public void setSyzt(java.lang.String value) {
		this.syzt = value;
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

	public java.lang.String getFjlxString() {
		return fjlxString;
	}

	public void setFjlxString(java.lang.String fjlxString) {
		this.fjlxString = fjlxString;
	}

	public java.lang.String getYyfjString() {
		return yyfjString;
	}

	public void setYyfjString(java.lang.String yyfjString) {
		this.yyfjString = yyfjString;
	}

	public java.lang.String getSyztString() {
		return syztString;
	}

	public void setSyztString(java.lang.String syztString) {
		this.syztString = syztString;
	}

	
}

