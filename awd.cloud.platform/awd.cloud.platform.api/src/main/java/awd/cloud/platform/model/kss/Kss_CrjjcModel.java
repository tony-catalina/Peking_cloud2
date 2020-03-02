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


public class Kss_CrjjcModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String bllx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;
	
	private java.lang.String ywqxyc;
	
	private java.lang.String qxycqk;
	
	private java.lang.String ywzssb;
	
	private java.lang.String sbqk;
	
	private java.lang.String ywwjp;
	
	private java.lang.String wjpqk;
	
	private java.lang.String ywtbs;
	
	private java.lang.String zssbqk;
	
	private java.lang.String dlmj;
	
	private java.lang.String ywlcid;
	
	private java.lang.String bdfs;
	
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
	
	private java.lang.String ywbh;
	//columns END

	 

	public Kss_CrjjcModel(){
	}
	public Kss_CrjjcModel(String id) {
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
	public java.lang.String getBllx() {
		return this.bllx;
	}
	
	public void setBllx(java.lang.String value) {
		this.bllx = value;
	}
	
	public java.util.Date getBlsj() {
		return this.blsj;
	}
	
	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}
	
	public java.lang.String getYwqxyc() {
		return this.ywqxyc;
	}
	
	public void setYwqxyc(java.lang.String value) {
		this.ywqxyc = value;
	}
	public java.lang.String getQxycqk() {
		return this.qxycqk;
	}
	
	public void setQxycqk(java.lang.String value) {
		this.qxycqk = value;
	}
	public java.lang.String getYwzssb() {
		return this.ywzssb;
	}
	
	public void setYwzssb(java.lang.String value) {
		this.ywzssb = value;
	}
	public java.lang.String getSbqk() {
		return this.sbqk;
	}
	
	public void setSbqk(java.lang.String value) {
		this.sbqk = value;
	}
	public java.lang.String getYwwjp() {
		return this.ywwjp;
	}
	
	public void setYwwjp(java.lang.String value) {
		this.ywwjp = value;
	}
	public java.lang.String getWjpqk() {
		return this.wjpqk;
	}
	
	public void setWjpqk(java.lang.String value) {
		this.wjpqk = value;
	}
	public java.lang.String getYwtbs() {
		return this.ywtbs;
	}
	
	public void setYwtbs(java.lang.String value) {
		this.ywtbs = value;
	}
	public java.lang.String getZssbqk() {
		return this.zssbqk;
	}
	
	public void setZssbqk(java.lang.String value) {
		this.zssbqk = value;
	}
	public java.lang.String getDlmj() {
		return this.dlmj;
	}
	
	public void setDlmj(java.lang.String value) {
		this.dlmj = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getBdfs() {
		return this.bdfs;
	}
	
	public void setBdfs(java.lang.String value) {
		this.bdfs = value;
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
	
	public java.lang.String getYwbh() {
		return this.ywbh;
	}
	
	public void setYwbh(java.lang.String value) {
		this.ywbh = value;
	}
	 
}

