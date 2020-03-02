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


public class Kss_HjbdModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String sfslaj;
	
	private java.lang.String sfjdwm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdrq;
	
	private java.lang.String bahj;
	
	private java.lang.String yssjd;
	
	private java.lang.String dwlx;
	
	private java.lang.String badw;
	
	private java.lang.String bar;
	
	private java.lang.String bardh;
	
	private java.lang.String ybadwlx;
	
	private java.lang.String ybadw;
	
	private java.lang.String ybar;
	
	private java.lang.String ybardh;
	
	private java.lang.String wsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yjyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zzrq;
	
	private java.lang.String wspzlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wssdrq;
	
	private java.lang.String pzdw;
	
	private java.lang.String bz;
	
	private java.lang.String ywlcsyid;
	
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

	 

	public Kss_HjbdModel(){
	}
	public Kss_HjbdModel(String id) {
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
	public java.lang.String getSfslaj() {
		return this.sfslaj;
	}
	
	public void setSfslaj(java.lang.String value) {
		this.sfslaj = value;
	}
	public java.lang.String getSfjdwm() {
		return this.sfjdwm;
	}
	
	public void setSfjdwm(java.lang.String value) {
		this.sfjdwm = value;
	}
	
	public java.util.Date getBdrq() {
		return this.bdrq;
	}
	
	public void setBdrq(java.util.Date value) {
		this.bdrq = value;
	}
	
	public java.lang.String getBahj() {
		return this.bahj;
	}
	
	public void setBahj(java.lang.String value) {
		this.bahj = value;
	}
	public java.lang.String getYssjd() {
		return this.yssjd;
	}
	
	public void setYssjd(java.lang.String value) {
		this.yssjd = value;
	}
	public java.lang.String getDwlx() {
		return this.dwlx;
	}
	
	public void setDwlx(java.lang.String value) {
		this.dwlx = value;
	}
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	public java.lang.String getBar() {
		return this.bar;
	}
	
	public void setBar(java.lang.String value) {
		this.bar = value;
	}
	public java.lang.String getBardh() {
		return this.bardh;
	}
	
	public void setBardh(java.lang.String value) {
		this.bardh = value;
	}
	public java.lang.String getYbadwlx() {
		return this.ybadwlx;
	}
	
	public void setYbadwlx(java.lang.String value) {
		this.ybadwlx = value;
	}
	public java.lang.String getYbadw() {
		return this.ybadw;
	}
	
	public void setYbadw(java.lang.String value) {
		this.ybadw = value;
	}
	public java.lang.String getYbar() {
		return this.ybar;
	}
	
	public void setYbar(java.lang.String value) {
		this.ybar = value;
	}
	public java.lang.String getYbardh() {
		return this.ybardh;
	}
	
	public void setYbardh(java.lang.String value) {
		this.ybardh = value;
	}
	public java.lang.String getWsh() {
		return this.wsh;
	}
	
	public void setWsh(java.lang.String value) {
		this.wsh = value;
	}
	
	public java.util.Date getGyqx() {
		return this.gyqx;
	}
	
	public void setGyqx(java.util.Date value) {
		this.gyqx = value;
	}
	
	
	public java.util.Date getYjyqx() {
		return this.yjyqx;
	}
	
	public void setYjyqx(java.util.Date value) {
		this.yjyqx = value;
	}
	
	
	public java.util.Date getQsrq() {
		return this.qsrq;
	}
	
	public void setQsrq(java.util.Date value) {
		this.qsrq = value;
	}
	
	
	public java.util.Date getZzrq() {
		return this.zzrq;
	}
	
	public void setZzrq(java.util.Date value) {
		this.zzrq = value;
	}
	
	public java.lang.String getWspzlx() {
		return this.wspzlx;
	}
	
	public void setWspzlx(java.lang.String value) {
		this.wspzlx = value;
	}
	
	public java.util.Date getWssdrq() {
		return this.wssdrq;
	}
	
	public void setWssdrq(java.util.Date value) {
		this.wssdrq = value;
	}
	
	public java.lang.String getPzdw() {
		return this.pzdw;
	}
	
	public void setPzdw(java.lang.String value) {
		this.pzdw = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getYwlcsyid() {
		return this.ywlcsyid;
	}
	
	public void setYwlcsyid(java.lang.String value) {
		this.ywlcsyid = value;
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

