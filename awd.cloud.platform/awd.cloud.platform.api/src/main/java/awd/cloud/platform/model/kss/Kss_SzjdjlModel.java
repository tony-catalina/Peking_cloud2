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


public class Kss_SzjdjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String sldxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jdsj;
	
	private java.lang.String zlfxm;
	
	private java.lang.String lfxb;
	
	private java.lang.String lfnl;
	
	private java.lang.String lfgzdw;
	
	private java.lang.String lxfs;
	
	private java.lang.String fywt;
	
	private java.lang.String dfyj;
	
	private java.lang.String clqk;
	
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
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String lfrzw;
	
	private java.lang.String jbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;
	
	private java.lang.Integer lfrs;
	
	private java.lang.String lxdh;
	//columns END

	 

	public Kss_SzjdjlModel(){
	}
	public Kss_SzjdjlModel(String id) {
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
	public java.lang.String getSldxm() {
		return this.sldxm;
	}
	
	public void setSldxm(java.lang.String value) {
		this.sldxm = value;
	}
	
	public java.util.Date getJdsj() {
		return this.jdsj;
	}
	
	public void setJdsj(java.util.Date value) {
		this.jdsj = value;
	}
	
	public java.lang.String getZlfxm() {
		return this.zlfxm;
	}
	
	public void setZlfxm(java.lang.String value) {
		this.zlfxm = value;
	}
	public java.lang.String getLfxb() {
		return this.lfxb;
	}
	
	public void setLfxb(java.lang.String value) {
		this.lfxb = value;
	}
	public java.lang.String getLfnl() {
		return this.lfnl;
	}
	
	public void setLfnl(java.lang.String value) {
		this.lfnl = value;
	}
	public java.lang.String getLfgzdw() {
		return this.lfgzdw;
	}
	
	public void setLfgzdw(java.lang.String value) {
		this.lfgzdw = value;
	}
	public java.lang.String getLxfs() {
		return this.lxfs;
	}
	
	public void setLxfs(java.lang.String value) {
		this.lxfs = value;
	}
	public java.lang.String getFywt() {
		return this.fywt;
	}
	
	public void setFywt(java.lang.String value) {
		this.fywt = value;
	}
	public java.lang.String getDfyj() {
		return this.dfyj;
	}
	
	public void setDfyj(java.lang.String value) {
		this.dfyj = value;
	}
	public java.lang.String getClqk() {
		return this.clqk;
	}
	
	public void setClqk(java.lang.String value) {
		this.clqk = value;
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
	
	public java.lang.String getZjlx() {
		return this.zjlx;
	}
	
	public void setZjlx(java.lang.String value) {
		this.zjlx = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	public java.lang.String getLfrzw() {
		return this.lfrzw;
	}
	
	public void setLfrzw(java.lang.String value) {
		this.lfrzw = value;
	}
	public java.lang.String getJbr() {
		return this.jbr;
	}
	
	public void setJbr(java.lang.String value) {
		this.jbr = value;
	}
	
	public java.util.Date getLssj() {
		return this.lssj;
	}
	
	public void setLssj(java.util.Date value) {
		this.lssj = value;
	}
	
	
	public java.util.Date getDjsj() {
		return this.djsj;
	}
	
	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}
	
	public java.lang.Integer getLfrs() {
		return this.lfrs;
	}
	
	public void setLfrs(java.lang.Integer value) {
		this.lfrs = value;
	}
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	 
}

