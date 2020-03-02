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


public class Kss_YpxxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String syff;
	
	private java.lang.String jsbh;
	
	private java.lang.String ypmc;
	
	private java.lang.String tm;
	
	private java.lang.String fydw;
	
	private java.lang.String ypdw;
	
	private java.math.BigDecimal jg;
	
	private java.lang.String gg;
	
	private java.lang.String sccj;
	
	private java.lang.String sfcfy;
	
	private java.lang.Short dzkcl;
	
	private byte[] smszp;
	
	private byte[] ypbzzp;
	
	private java.lang.String sypl;
	
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
	
	private java.lang.String smsurl;
	
	private java.lang.String ypbzurl;
	//columns END

	 

	public Kss_YpxxModel(){
	}
	public Kss_YpxxModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getSyff() {
		return this.syff;
	}
	
	public void setSyff(java.lang.String value) {
		this.syff = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getYpmc() {
		return this.ypmc;
	}
	
	public void setYpmc(java.lang.String value) {
		this.ypmc = value;
	}
	public java.lang.String getTm() {
		return this.tm;
	}
	
	public void setTm(java.lang.String value) {
		this.tm = value;
	}
	public java.lang.String getFydw() {
		return this.fydw;
	}
	
	public void setFydw(java.lang.String value) {
		this.fydw = value;
	}
	public java.lang.String getYpdw() {
		return this.ypdw;
	}
	
	public void setYpdw(java.lang.String value) {
		this.ypdw = value;
	}
	public java.math.BigDecimal getJg() {
		return this.jg;
	}
	
	public void setJg(java.math.BigDecimal value) {
		this.jg = value;
	}
	public java.lang.String getGg() {
		return this.gg;
	}
	
	public void setGg(java.lang.String value) {
		this.gg = value;
	}
	public java.lang.String getSccj() {
		return this.sccj;
	}
	
	public void setSccj(java.lang.String value) {
		this.sccj = value;
	}
	public java.lang.String getSfcfy() {
		return this.sfcfy;
	}
	
	public void setSfcfy(java.lang.String value) {
		this.sfcfy = value;
	}
	public java.lang.Short getDzkcl() {
		return this.dzkcl;
	}
	
	public void setDzkcl(java.lang.Short value) {
		this.dzkcl = value;
	}
	public byte[] getSmszp() {
		return this.smszp;
	}
	
	public void setSmszp(byte[] value) {
		this.smszp = value;
	}
	public byte[] getYpbzzp() {
		return this.ypbzzp;
	}
	
	public void setYpbzzp(byte[] value) {
		this.ypbzzp = value;
	}
	public java.lang.String getSypl() {
		return this.sypl;
	}
	
	public void setSypl(java.lang.String value) {
		this.sypl = value;
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
	
	public java.lang.String getSmsurl() {
		return this.smsurl;
	}
	
	public void setSmsurl(java.lang.String value) {
		this.smsurl = value;
	}
	public java.lang.String getYpbzurl() {
		return this.ypbzurl;
	}
	
	public void setYpbzurl(java.lang.String value) {
		this.ypbzurl = value;
	}
	 
}

