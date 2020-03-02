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


public class Kss_SpdetailorderModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String ddbh;
	
	private java.lang.String sptm;
	
	private java.lang.String sfqh;
	
	private java.lang.String sfzjff;
	
	private java.math.BigDecimal spsl;
	
	private java.math.BigDecimal xfje;
	
	private java.math.BigDecimal sfje;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ddcjsj;
	
	private java.lang.String cgdbh;
	
	private java.lang.String status;
	
	private java.lang.String shzt;
	
	private java.lang.String shyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date shsj;
	
	private java.lang.String shr;
	
	private java.lang.String state;
	
	private java.lang.String bz;
	
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
	
	private java.lang.String gjqrr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gjqrsj;
	
	private java.lang.String gjzdblr;
	
	private java.lang.String gjzdshyj;
	
	private java.lang.String gjzdpsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gjzdshsj;
	
	private java.lang.String zhzdblr;
	
	private java.lang.String zhzdshyj;
	
	private java.lang.String zhzdpsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zhzdshsj;
	
	private java.lang.String ywlcid;
	
	private java.lang.String lqr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lqsj;
	//columns END

	 

	public Kss_SpdetailorderModel(){
	}
	public Kss_SpdetailorderModel(String id) {
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
	public java.lang.String getDdbh() {
		return this.ddbh;
	}
	
	public void setDdbh(java.lang.String value) {
		this.ddbh = value;
	}
	public java.lang.String getSptm() {
		return this.sptm;
	}
	
	public void setSptm(java.lang.String value) {
		this.sptm = value;
	}
	public java.lang.String getSfqh() {
		return this.sfqh;
	}
	
	public void setSfqh(java.lang.String value) {
		this.sfqh = value;
	}
	public java.lang.String getSfzjff() {
		return this.sfzjff;
	}
	
	public void setSfzjff(java.lang.String value) {
		this.sfzjff = value;
	}
	public java.math.BigDecimal getSpsl() {
		return this.spsl;
	}
	
	public void setSpsl(java.math.BigDecimal value) {
		this.spsl = value;
	}
	public java.math.BigDecimal getXfje() {
		return this.xfje;
	}
	
	public void setXfje(java.math.BigDecimal value) {
		this.xfje = value;
	}
	public java.math.BigDecimal getSfje() {
		return this.sfje;
	}
	
	public void setSfje(java.math.BigDecimal value) {
		this.sfje = value;
	}
	
	public java.util.Date getDdcjsj() {
		return this.ddcjsj;
	}
	
	public void setDdcjsj(java.util.Date value) {
		this.ddcjsj = value;
	}
	
	public java.lang.String getCgdbh() {
		return this.cgdbh;
	}
	
	public void setCgdbh(java.lang.String value) {
		this.cgdbh = value;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	public java.lang.String getShzt() {
		return this.shzt;
	}
	
	public void setShzt(java.lang.String value) {
		this.shzt = value;
	}
	public java.lang.String getShyj() {
		return this.shyj;
	}
	
	public void setShyj(java.lang.String value) {
		this.shyj = value;
	}
	
	public java.util.Date getShsj() {
		return this.shsj;
	}
	
	public void setShsj(java.util.Date value) {
		this.shsj = value;
	}
	
	public java.lang.String getShr() {
		return this.shr;
	}
	
	public void setShr(java.lang.String value) {
		this.shr = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	
	public java.lang.String getGjqrr() {
		return this.gjqrr;
	}
	
	public void setGjqrr(java.lang.String value) {
		this.gjqrr = value;
	}
	
	public java.util.Date getGjqrsj() {
		return this.gjqrsj;
	}
	
	public void setGjqrsj(java.util.Date value) {
		this.gjqrsj = value;
	}
	
	public java.lang.String getGjzdblr() {
		return this.gjzdblr;
	}
	
	public void setGjzdblr(java.lang.String value) {
		this.gjzdblr = value;
	}
	public java.lang.String getGjzdshyj() {
		return this.gjzdshyj;
	}
	
	public void setGjzdshyj(java.lang.String value) {
		this.gjzdshyj = value;
	}
	public java.lang.String getGjzdpsbz() {
		return this.gjzdpsbz;
	}
	
	public void setGjzdpsbz(java.lang.String value) {
		this.gjzdpsbz = value;
	}
	
	public java.util.Date getGjzdshsj() {
		return this.gjzdshsj;
	}
	
	public void setGjzdshsj(java.util.Date value) {
		this.gjzdshsj = value;
	}
	
	public java.lang.String getZhzdblr() {
		return this.zhzdblr;
	}
	
	public void setZhzdblr(java.lang.String value) {
		this.zhzdblr = value;
	}
	public java.lang.String getZhzdshyj() {
		return this.zhzdshyj;
	}
	
	public void setZhzdshyj(java.lang.String value) {
		this.zhzdshyj = value;
	}
	public java.lang.String getZhzdpsbz() {
		return this.zhzdpsbz;
	}
	
	public void setZhzdpsbz(java.lang.String value) {
		this.zhzdpsbz = value;
	}
	
	public java.util.Date getZhzdshsj() {
		return this.zhzdshsj;
	}
	
	public void setZhzdshsj(java.util.Date value) {
		this.zhzdshsj = value;
	}
	
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getLqr() {
		return this.lqr;
	}
	
	public void setLqr(java.lang.String value) {
		this.lqr = value;
	}
	
	public java.util.Date getLqsj() {
		return this.lqsj;
	}
	
	public void setLqsj(java.util.Date value) {
		this.lqsj = value;
	}
	
	 
}

