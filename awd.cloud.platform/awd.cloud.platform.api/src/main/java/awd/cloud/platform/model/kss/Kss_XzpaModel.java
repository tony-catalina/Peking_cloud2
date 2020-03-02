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


public class Kss_XzpaModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String lx;
	
	private java.lang.String sfzdaj;
	
	private java.lang.String sar;
	
	private java.lang.Integer sars;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date afsj;
	
	private java.lang.String afdd;
	
	private java.lang.Long saje;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tbjurq;
	
	private java.lang.String jjqxz;
	
	private java.lang.String saqy;
	
	private java.lang.String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;
	
	private java.lang.String jyjg;
	
	private java.lang.String sldyj;
	
	private java.lang.String sldyjnr;
	
	private java.lang.String sldqm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sldqmrq;
	
	private java.lang.String cdr;
	
	private java.lang.String cdrdh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cdrq;
	
	private java.lang.String cddw;
	
	private java.lang.String jsr;
	
	private java.lang.String jsrdh;
	
	private java.lang.String cdbz;
	
	private java.lang.String czjg;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fkrq;
	
	private java.lang.Long phxsaj;
	
	private java.lang.Long phzaaj;
	
	private java.lang.Long xsjls;
	
	private java.lang.Long dbrs;
	
	private java.lang.Long ljrs;
	
	private java.lang.Long ysqss;
	
	private java.lang.Long qtrs;
	
	private java.lang.Long bdbtj;
	
	private java.lang.Long sdbtj;
	
	private java.lang.Long wszts;
	
	private java.lang.Long zzjehj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clcdsj;
	
	private java.lang.String zwdw;
	
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
	
	private java.lang.String pastable;
	
	private java.lang.String ywlcid;
	//columns END

	 

	public Kss_XzpaModel(){
	}
	public Kss_XzpaModel(String id) {
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
	public java.lang.String getLx() {
		return this.lx;
	}
	
	public void setLx(java.lang.String value) {
		this.lx = value;
	}
	public java.lang.String getSfzdaj() {
		return this.sfzdaj;
	}
	
	public void setSfzdaj(java.lang.String value) {
		this.sfzdaj = value;
	}
	public java.lang.String getSar() {
		return this.sar;
	}
	
	public void setSar(java.lang.String value) {
		this.sar = value;
	}
	public java.lang.Integer getSars() {
		return this.sars;
	}
	
	public void setSars(java.lang.Integer value) {
		this.sars = value;
	}
	
	public java.util.Date getAfsj() {
		return this.afsj;
	}
	
	public void setAfsj(java.util.Date value) {
		this.afsj = value;
	}
	
	public java.lang.String getAfdd() {
		return this.afdd;
	}
	
	public void setAfdd(java.lang.String value) {
		this.afdd = value;
	}
	public java.lang.Long getSaje() {
		return this.saje;
	}
	
	public void setSaje(java.lang.Long value) {
		this.saje = value;
	}
	
	public java.util.Date getTbjurq() {
		return this.tbjurq;
	}
	
	public void setTbjurq(java.util.Date value) {
		this.tbjurq = value;
	}
	
	public java.lang.String getJjqxz() {
		return this.jjqxz;
	}
	
	public void setJjqxz(java.lang.String value) {
		this.jjqxz = value;
	}
	public java.lang.String getSaqy() {
		return this.saqy;
	}
	
	public void setSaqy(java.lang.String value) {
		this.saqy = value;
	}
	public java.lang.String getDjr() {
		return this.djr;
	}
	
	public void setDjr(java.lang.String value) {
		this.djr = value;
	}
	
	public java.util.Date getDjrq() {
		return this.djrq;
	}
	
	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}
	
	public java.lang.String getJyjg() {
		return this.jyjg;
	}
	
	public void setJyjg(java.lang.String value) {
		this.jyjg = value;
	}
	public java.lang.String getSldyj() {
		return this.sldyj;
	}
	
	public void setSldyj(java.lang.String value) {
		this.sldyj = value;
	}
	public java.lang.String getSldyjnr() {
		return this.sldyjnr;
	}
	
	public void setSldyjnr(java.lang.String value) {
		this.sldyjnr = value;
	}
	public java.lang.String getSldqm() {
		return this.sldqm;
	}
	
	public void setSldqm(java.lang.String value) {
		this.sldqm = value;
	}
	
	public java.util.Date getSldqmrq() {
		return this.sldqmrq;
	}
	
	public void setSldqmrq(java.util.Date value) {
		this.sldqmrq = value;
	}
	
	public java.lang.String getCdr() {
		return this.cdr;
	}
	
	public void setCdr(java.lang.String value) {
		this.cdr = value;
	}
	public java.lang.String getCdrdh() {
		return this.cdrdh;
	}
	
	public void setCdrdh(java.lang.String value) {
		this.cdrdh = value;
	}
	
	public java.util.Date getCdrq() {
		return this.cdrq;
	}
	
	public void setCdrq(java.util.Date value) {
		this.cdrq = value;
	}
	
	public java.lang.String getCddw() {
		return this.cddw;
	}
	
	public void setCddw(java.lang.String value) {
		this.cddw = value;
	}
	public java.lang.String getJsr() {
		return this.jsr;
	}
	
	public void setJsr(java.lang.String value) {
		this.jsr = value;
	}
	public java.lang.String getJsrdh() {
		return this.jsrdh;
	}
	
	public void setJsrdh(java.lang.String value) {
		this.jsrdh = value;
	}
	public java.lang.String getCdbz() {
		return this.cdbz;
	}
	
	public void setCdbz(java.lang.String value) {
		this.cdbz = value;
	}
	public java.lang.String getCzjg() {
		return this.czjg;
	}
	
	public void setCzjg(java.lang.String value) {
		this.czjg = value;
	}
	
	public java.util.Date getFkrq() {
		return this.fkrq;
	}
	
	public void setFkrq(java.util.Date value) {
		this.fkrq = value;
	}
	
	public java.lang.Long getPhxsaj() {
		return this.phxsaj;
	}
	
	public void setPhxsaj(java.lang.Long value) {
		this.phxsaj = value;
	}
	public java.lang.Long getPhzaaj() {
		return this.phzaaj;
	}
	
	public void setPhzaaj(java.lang.Long value) {
		this.phzaaj = value;
	}
	public java.lang.Long getXsjls() {
		return this.xsjls;
	}
	
	public void setXsjls(java.lang.Long value) {
		this.xsjls = value;
	}
	public java.lang.Long getDbrs() {
		return this.dbrs;
	}
	
	public void setDbrs(java.lang.Long value) {
		this.dbrs = value;
	}
	public java.lang.Long getLjrs() {
		return this.ljrs;
	}
	
	public void setLjrs(java.lang.Long value) {
		this.ljrs = value;
	}
	public java.lang.Long getYsqss() {
		return this.ysqss;
	}
	
	public void setYsqss(java.lang.Long value) {
		this.ysqss = value;
	}
	public java.lang.Long getQtrs() {
		return this.qtrs;
	}
	
	public void setQtrs(java.lang.Long value) {
		this.qtrs = value;
	}
	public java.lang.Long getBdbtj() {
		return this.bdbtj;
	}
	
	public void setBdbtj(java.lang.Long value) {
		this.bdbtj = value;
	}
	public java.lang.Long getSdbtj() {
		return this.sdbtj;
	}
	
	public void setSdbtj(java.lang.Long value) {
		this.sdbtj = value;
	}
	public java.lang.Long getWszts() {
		return this.wszts;
	}
	
	public void setWszts(java.lang.Long value) {
		this.wszts = value;
	}
	public java.lang.Long getZzjehj() {
		return this.zzjehj;
	}
	
	public void setZzjehj(java.lang.Long value) {
		this.zzjehj = value;
	}
	
	public java.util.Date getClcdsj() {
		return this.clcdsj;
	}
	
	public void setClcdsj(java.util.Date value) {
		this.clcdsj = value;
	}
	
	public java.lang.String getZwdw() {
		return this.zwdw;
	}
	
	public void setZwdw(java.lang.String value) {
		this.zwdw = value;
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
	
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	 
}

