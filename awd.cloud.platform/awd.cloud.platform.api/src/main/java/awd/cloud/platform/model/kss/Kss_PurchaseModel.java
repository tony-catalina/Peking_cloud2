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


public class Kss_PurchaseModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jhdh;
	
	private java.lang.String bh;
	
	private java.lang.String hwlx;
	
	private java.lang.String splx;
	
	private java.lang.String yplx;
	
	private java.lang.Short sl;
	
	private java.lang.String dw;
	
	private java.lang.Short hwdj;
	
	private java.lang.Integer zj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jhrq;
	
	private java.lang.String sfyx;
	
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

	 

	public Kss_PurchaseModel(){
	}
	public Kss_PurchaseModel(String id) {
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
	public java.lang.String getJhdh() {
		return this.jhdh;
	}
	
	public void setJhdh(java.lang.String value) {
		this.jhdh = value;
	}
	public java.lang.String getBh() {
		return this.bh;
	}
	
	public void setBh(java.lang.String value) {
		this.bh = value;
	}
	public java.lang.String getHwlx() {
		return this.hwlx;
	}
	
	public void setHwlx(java.lang.String value) {
		this.hwlx = value;
	}
	public java.lang.String getSplx() {
		return this.splx;
	}
	
	public void setSplx(java.lang.String value) {
		this.splx = value;
	}
	public java.lang.String getYplx() {
		return this.yplx;
	}
	
	public void setYplx(java.lang.String value) {
		this.yplx = value;
	}
	public java.lang.Short getSl() {
		return this.sl;
	}
	
	public void setSl(java.lang.Short value) {
		this.sl = value;
	}
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	public java.lang.Short getHwdj() {
		return this.hwdj;
	}
	
	public void setHwdj(java.lang.Short value) {
		this.hwdj = value;
	}
	public java.lang.Integer getZj() {
		return this.zj;
	}
	
	public void setZj(java.lang.Integer value) {
		this.zj = value;
	}
	
	public java.util.Date getJhrq() {
		return this.jhrq;
	}
	
	public void setJhrq(java.util.Date value) {
		this.jhrq = value;
	}
	
	public java.lang.String getSfyx() {
		return this.sfyx;
	}
	
	public void setSfyx(java.lang.String value) {
		this.sfyx = value;
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

