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


public class Kss_LsbdModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String lszh;
	
	private java.lang.String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcsj;
	
	private java.lang.String jcr;
	
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String lsxm;
	
	private java.lang.String lsxb;
	
	private java.lang.String lszz;
	
	private java.lang.String lsjsxh;
	
	private java.lang.String pzdw;
	
	private java.lang.String lsdh;
	
	private java.lang.String lssjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lszyzmks;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lszyzmjs;
	
	private java.lang.String zlxm;
	
	private java.lang.String zlxb;
	
	private java.lang.String zlzjlx;
	
	private java.lang.String zlzjhm;
	
	private java.lang.String ryxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	
	private java.lang.String lsdw;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String creator;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	 

	public Kss_LsbdModel(){
	}
	public Kss_LsbdModel(String id) {
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
	public java.lang.String getLszh() {
		return this.lszh;
	}
	
	public void setLszh(java.lang.String value) {
		this.lszh = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	
	public java.util.Date getJcsj() {
		return this.jcsj;
	}
	
	public void setJcsj(java.util.Date value) {
		this.jcsj = value;
	}
	
	public java.lang.String getJcr() {
		return this.jcr;
	}
	
	public void setJcr(java.lang.String value) {
		this.jcr = value;
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
	
	public java.lang.String getLsxm() {
		return this.lsxm;
	}
	
	public void setLsxm(java.lang.String value) {
		this.lsxm = value;
	}
	public java.lang.String getLsxb() {
		return this.lsxb;
	}
	
	public void setLsxb(java.lang.String value) {
		this.lsxb = value;
	}
	public java.lang.String getLszz() {
		return this.lszz;
	}
	
	public void setLszz(java.lang.String value) {
		this.lszz = value;
	}
	public java.lang.String getLsjsxh() {
		return this.lsjsxh;
	}
	
	public void setLsjsxh(java.lang.String value) {
		this.lsjsxh = value;
	}
	public java.lang.String getPzdw() {
		return this.pzdw;
	}
	
	public void setPzdw(java.lang.String value) {
		this.pzdw = value;
	}
	public java.lang.String getLsdh() {
		return this.lsdh;
	}
	
	public void setLsdh(java.lang.String value) {
		this.lsdh = value;
	}
	public java.lang.String getLssjh() {
		return this.lssjh;
	}
	
	public void setLssjh(java.lang.String value) {
		this.lssjh = value;
	}
	
	public java.util.Date getLszyzmks() {
		return this.lszyzmks;
	}
	
	public void setLszyzmks(java.util.Date value) {
		this.lszyzmks = value;
	}
	
	
	public java.util.Date getLszyzmjs() {
		return this.lszyzmjs;
	}
	
	public void setLszyzmjs(java.util.Date value) {
		this.lszyzmjs = value;
	}
	
	public java.lang.String getZlxm() {
		return this.zlxm;
	}
	
	public void setZlxm(java.lang.String value) {
		this.zlxm = value;
	}
	public java.lang.String getZlxb() {
		return this.zlxb;
	}
	
	public void setZlxb(java.lang.String value) {
		this.zlxb = value;
	}
	public java.lang.String getZlzjlx() {
		return this.zlzjlx;
	}
	
	public void setZlzjlx(java.lang.String value) {
		this.zlzjlx = value;
	}
	public java.lang.String getZlzjhm() {
		return this.zlzjhm;
	}
	
	public void setZlzjhm(java.lang.String value) {
		this.zlzjhm = value;
	}
	public java.lang.String getRyxm() {
		return this.ryxm;
	}
	
	public void setRyxm(java.lang.String value) {
		this.ryxm = value;
	}
	
	public java.util.Date getHjsj() {
		return this.hjsj;
	}
	
	public void setHjsj(java.util.Date value) {
		this.hjsj = value;
	}
	
	public java.lang.String getLsdw() {
		return this.lsdw;
	}
	
	public void setLsdw(java.lang.String value) {
		this.lsdw = value;
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

