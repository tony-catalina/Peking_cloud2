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


public class Kss_ZyrytsclModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String tsrxm;
	
	private java.lang.String gx;
	
	private java.lang.String btsdx;
	
	private java.lang.String tsdx;
	
	private java.lang.String tslx;
	
	private java.lang.String tsnr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tssj;
	
	private java.lang.String slr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date slsj;
	
	private java.lang.String spr;
	
	private java.lang.String spyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date spsj;
	
	private java.lang.String ywlcid;
	
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String bz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;
	
	private java.lang.String cljg;
	
	private java.lang.String clr;
	
	private java.lang.String creator;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	 

	public Kss_ZyrytsclModel(){
	}
	public Kss_ZyrytsclModel(String id) {
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
	public java.lang.String getTsrxm() {
		return this.tsrxm;
	}
	
	public void setTsrxm(java.lang.String value) {
		this.tsrxm = value;
	}
	public java.lang.String getGx() {
		return this.gx;
	}
	
	public void setGx(java.lang.String value) {
		this.gx = value;
	}
	public java.lang.String getBtsdx() {
		return this.btsdx;
	}
	
	public void setBtsdx(java.lang.String value) {
		this.btsdx = value;
	}
	public java.lang.String getTsdx() {
		return this.tsdx;
	}
	
	public void setTsdx(java.lang.String value) {
		this.tsdx = value;
	}
	public java.lang.String getTslx() {
		return this.tslx;
	}
	
	public void setTslx(java.lang.String value) {
		this.tslx = value;
	}
	public java.lang.String getTsnr() {
		return this.tsnr;
	}
	
	public void setTsnr(java.lang.String value) {
		this.tsnr = value;
	}
	
	public java.util.Date getTssj() {
		return this.tssj;
	}
	
	public void setTssj(java.util.Date value) {
		this.tssj = value;
	}
	
	public java.lang.String getSlr() {
		return this.slr;
	}
	
	public void setSlr(java.lang.String value) {
		this.slr = value;
	}
	
	public java.util.Date getSlsj() {
		return this.slsj;
	}
	
	public void setSlsj(java.util.Date value) {
		this.slsj = value;
	}
	
	public java.lang.String getSpr() {
		return this.spr;
	}
	
	public void setSpr(java.lang.String value) {
		this.spr = value;
	}
	public java.lang.String getSpyj() {
		return this.spyj;
	}
	
	public void setSpyj(java.lang.String value) {
		this.spyj = value;
	}
	
	public java.util.Date getSpsj() {
		return this.spsj;
	}
	
	public void setSpsj(java.util.Date value) {
		this.spsj = value;
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
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	
	public java.util.Date getClsj() {
		return this.clsj;
	}
	
	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}
	
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
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

