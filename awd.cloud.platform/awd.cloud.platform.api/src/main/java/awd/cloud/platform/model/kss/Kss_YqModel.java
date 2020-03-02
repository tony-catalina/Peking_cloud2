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


public class Kss_YqModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ygyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xgyqx;
	
	private java.lang.String yqyy;
	
	private java.lang.String blr;
	
	private java.lang.String blrdh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;
	
	private java.lang.String flwsh;
	
	private java.lang.String pzr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pzrq;
	
	private java.lang.String pzdw;
	
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
	
	private java.lang.String wspzlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wssdrq;
	//columns END

	 

	public Kss_YqModel(){
	}
	public Kss_YqModel(String id) {
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
	
	public java.util.Date getYgyqx() {
		return this.ygyqx;
	}
	
	public void setYgyqx(java.util.Date value) {
		this.ygyqx = value;
	}
	
	
	public java.util.Date getXgyqx() {
		return this.xgyqx;
	}
	
	public void setXgyqx(java.util.Date value) {
		this.xgyqx = value;
	}
	
	public java.lang.String getYqyy() {
		return this.yqyy;
	}
	
	public void setYqyy(java.lang.String value) {
		this.yqyy = value;
	}
	public java.lang.String getBlr() {
		return this.blr;
	}
	
	public void setBlr(java.lang.String value) {
		this.blr = value;
	}
	public java.lang.String getBlrdh() {
		return this.blrdh;
	}
	
	public void setBlrdh(java.lang.String value) {
		this.blrdh = value;
	}
	
	public java.util.Date getBlrq() {
		return this.blrq;
	}
	
	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}
	
	public java.lang.String getFlwsh() {
		return this.flwsh;
	}
	
	public void setFlwsh(java.lang.String value) {
		this.flwsh = value;
	}
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	
	public java.util.Date getPzrq() {
		return this.pzrq;
	}
	
	public void setPzrq(java.util.Date value) {
		this.pzrq = value;
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
	
	 
}

