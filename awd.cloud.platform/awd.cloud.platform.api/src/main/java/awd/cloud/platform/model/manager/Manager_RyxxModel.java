/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

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


public class Manager_RyxxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;
	
	private java.lang.String jsbh;
	
	private java.lang.String gcbh;
	
	private java.lang.String snbh;
	
	private java.lang.String rybh;
	
	private java.lang.String jsh;
	
	private java.lang.String xm;
	
	private java.lang.String xmpy;
	
	private java.lang.String bm;
	
	private java.lang.String bmty;
	
	private java.lang.String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String hyzk;
	
	private java.lang.String mz;
	
	private java.lang.String gj;
	
	private java.lang.String jg;
	
	private java.lang.String hjd;
	
	private java.lang.String hjdxz;
	
	private java.lang.String xzd;
	
	private java.lang.String xzdxz;
	
	private java.lang.String whcd;
	
	private java.lang.String zy;
	
	private java.lang.String zc;
	
	private java.lang.String gzdw;
	
	private java.lang.String sf;
	
	private java.lang.String tssf;
	
	private java.lang.String dah;
	
	private java.lang.String zzmm;
	
	private java.lang.String zwbh;
	
	private java.lang.String jkzk;
	
	private java.lang.String zuc;
	
	private java.lang.String sg;
	
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
	//columns END

	 

	public Manager_RyxxModel(){
	}
	public Manager_RyxxModel(String id) {
		this.id = id;
	}
	
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getGcbh() {
		return this.gcbh;
	}
	
	public void setGcbh(java.lang.String value) {
		this.gcbh = value;
	}
	public java.lang.String getSnbh() {
		return this.snbh;
	}
	
	public void setSnbh(java.lang.String value) {
		this.snbh = value;
	}
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsh() {
		return this.jsh;
	}
	
	public void setJsh(java.lang.String value) {
		this.jsh = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getXmpy() {
		return this.xmpy;
	}
	
	public void setXmpy(java.lang.String value) {
		this.xmpy = value;
	}
	public java.lang.String getBm() {
		return this.bm;
	}
	
	public void setBm(java.lang.String value) {
		this.bm = value;
	}
	public java.lang.String getBmty() {
		return this.bmty;
	}
	
	public void setBmty(java.lang.String value) {
		this.bmty = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	
	public java.util.Date getCsrq() {
		return this.csrq;
	}
	
	public void setCsrq(java.util.Date value) {
		this.csrq = value;
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
	public java.lang.String getHyzk() {
		return this.hyzk;
	}
	
	public void setHyzk(java.lang.String value) {
		this.hyzk = value;
	}
	public java.lang.String getMz() {
		return this.mz;
	}
	
	public void setMz(java.lang.String value) {
		this.mz = value;
	}
	public java.lang.String getGj() {
		return this.gj;
	}
	
	public void setGj(java.lang.String value) {
		this.gj = value;
	}
	public java.lang.String getJg() {
		return this.jg;
	}
	
	public void setJg(java.lang.String value) {
		this.jg = value;
	}
	public java.lang.String getHjd() {
		return this.hjd;
	}
	
	public void setHjd(java.lang.String value) {
		this.hjd = value;
	}
	public java.lang.String getHjdxz() {
		return this.hjdxz;
	}
	
	public void setHjdxz(java.lang.String value) {
		this.hjdxz = value;
	}
	public java.lang.String getXzd() {
		return this.xzd;
	}
	
	public void setXzd(java.lang.String value) {
		this.xzd = value;
	}
	public java.lang.String getXzdxz() {
		return this.xzdxz;
	}
	
	public void setXzdxz(java.lang.String value) {
		this.xzdxz = value;
	}
	public java.lang.String getWhcd() {
		return this.whcd;
	}
	
	public void setWhcd(java.lang.String value) {
		this.whcd = value;
	}
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
	}
	public java.lang.String getZc() {
		return this.zc;
	}
	
	public void setZc(java.lang.String value) {
		this.zc = value;
	}
	public java.lang.String getGzdw() {
		return this.gzdw;
	}
	
	public void setGzdw(java.lang.String value) {
		this.gzdw = value;
	}
	public java.lang.String getSf() {
		return this.sf;
	}
	
	public void setSf(java.lang.String value) {
		this.sf = value;
	}
	public java.lang.String getTssf() {
		return this.tssf;
	}
	
	public void setTssf(java.lang.String value) {
		this.tssf = value;
	}
	public java.lang.String getDah() {
		return this.dah;
	}
	
	public void setDah(java.lang.String value) {
		this.dah = value;
	}
	public java.lang.String getZzmm() {
		return this.zzmm;
	}
	
	public void setZzmm(java.lang.String value) {
		this.zzmm = value;
	}
	public java.lang.String getZwbh() {
		return this.zwbh;
	}
	
	public void setZwbh(java.lang.String value) {
		this.zwbh = value;
	}
	public java.lang.String getJkzk() {
		return this.jkzk;
	}
	
	public void setJkzk(java.lang.String value) {
		this.jkzk = value;
	}
	public java.lang.String getZuc() {
		return this.zuc;
	}
	
	public void setZuc(java.lang.String value) {
		this.zuc = value;
	}
	public java.lang.String getSg() {
		return this.sg;
	}
	
	public void setSg(java.lang.String value) {
		this.sg = value;
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
	
	 
}

