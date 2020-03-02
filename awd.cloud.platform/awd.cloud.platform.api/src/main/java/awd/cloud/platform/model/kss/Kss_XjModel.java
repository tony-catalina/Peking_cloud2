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


public class Kss_XjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String lsh;
	
	private java.lang.String ly;
	
	private java.lang.String xjmc;
	
	private java.lang.Byte jjsl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	
	private java.lang.Short syts;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	
	private java.lang.String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;
	
	private java.lang.String zdzxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzpssj;
	
	private java.lang.String zdzyj;
	
	private java.lang.String zdzpsbz;
	
	private java.lang.String ldxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String ldyj;
	
	private java.lang.String ldpsbz;
	
	private java.lang.String bz;
	
	private java.lang.String psbz;
	
	private java.lang.String ywlcid;
	
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
	
	private java.lang.String syqx;
	
	private java.lang.String xjsybz;
	
	private java.lang.String xjsyblr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xjsyblsj;
	
	private java.lang.String bdlx;
	
	private java.lang.String xjycly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yxjsyqx;
	
	private java.lang.String ycjbmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ycjbrq;
	
	private java.lang.String yczdspr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yczdspsj;
	
	private java.lang.String yczdpsbz;
	
	private java.lang.String yczdspyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ycldspsj;
	
	private java.lang.String ycldspr;
	
	private java.lang.String ycldpsbz;
	
	private java.lang.String ycldspyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xjjcsj;
	
	private java.lang.String jczxr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jczxqk;
	
	private java.lang.String jcbz;
	
	private java.lang.String xjsyzxqk;
	//columns END

	 

	public Kss_XjModel(){
	}
	public Kss_XjModel(String id) {
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
	public java.lang.String getLsh() {
		return this.lsh;
	}
	
	public void setLsh(java.lang.String value) {
		this.lsh = value;
	}
	public java.lang.String getLy() {
		return this.ly;
	}
	
	public void setLy(java.lang.String value) {
		this.ly = value;
	}
	public java.lang.String getXjmc() {
		return this.xjmc;
	}
	
	public void setXjmc(java.lang.String value) {
		this.xjmc = value;
	}
	public java.lang.Byte getJjsl() {
		return this.jjsl;
	}
	
	public void setJjsl(java.lang.Byte value) {
		this.jjsl = value;
	}
	
	public java.util.Date getKssj() {
		return this.kssj;
	}
	
	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}
	
	public java.lang.Short getSyts() {
		return this.syts;
	}
	
	public void setSyts(java.lang.Short value) {
		this.syts = value;
	}
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getBlr() {
		return this.blr;
	}
	
	public void setBlr(java.lang.String value) {
		this.blr = value;
	}
	
	public java.util.Date getBlrq() {
		return this.blrq;
	}
	
	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}
	
	public java.lang.String getZdzxm() {
		return this.zdzxm;
	}
	
	public void setZdzxm(java.lang.String value) {
		this.zdzxm = value;
	}
	
	public java.util.Date getZdzpssj() {
		return this.zdzpssj;
	}
	
	public void setZdzpssj(java.util.Date value) {
		this.zdzpssj = value;
	}
	
	public java.lang.String getZdzyj() {
		return this.zdzyj;
	}
	
	public void setZdzyj(java.lang.String value) {
		this.zdzyj = value;
	}
	public java.lang.String getZdzpsbz() {
		return this.zdzpsbz;
	}
	
	public void setZdzpsbz(java.lang.String value) {
		this.zdzpsbz = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	
	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}
	
	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}
	
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
	}
	public java.lang.String getLdpsbz() {
		return this.ldpsbz;
	}
	
	public void setLdpsbz(java.lang.String value) {
		this.ldpsbz = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
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
	public java.lang.String getSyqx() {
		return this.syqx;
	}
	
	public void setSyqx(java.lang.String value) {
		this.syqx = value;
	}
	public java.lang.String getXjsybz() {
		return this.xjsybz;
	}
	
	public void setXjsybz(java.lang.String value) {
		this.xjsybz = value;
	}
	public java.lang.String getXjsyblr() {
		return this.xjsyblr;
	}
	
	public void setXjsyblr(java.lang.String value) {
		this.xjsyblr = value;
	}
	
	public java.util.Date getXjsyblsj() {
		return this.xjsyblsj;
	}
	
	public void setXjsyblsj(java.util.Date value) {
		this.xjsyblsj = value;
	}
	
	public java.lang.String getBdlx() {
		return this.bdlx;
	}
	
	public void setBdlx(java.lang.String value) {
		this.bdlx = value;
	}
	public java.lang.String getXjycly() {
		return this.xjycly;
	}
	
	public void setXjycly(java.lang.String value) {
		this.xjycly = value;
	}
	
	public java.util.Date getYxjsyqx() {
		return this.yxjsyqx;
	}
	
	public void setYxjsyqx(java.util.Date value) {
		this.yxjsyqx = value;
	}
	
	public java.lang.String getYcjbmj() {
		return this.ycjbmj;
	}
	
	public void setYcjbmj(java.lang.String value) {
		this.ycjbmj = value;
	}
	
	public java.util.Date getYcjbrq() {
		return this.ycjbrq;
	}
	
	public void setYcjbrq(java.util.Date value) {
		this.ycjbrq = value;
	}
	
	public java.lang.String getYczdspr() {
		return this.yczdspr;
	}
	
	public void setYczdspr(java.lang.String value) {
		this.yczdspr = value;
	}
	
	public java.util.Date getYczdspsj() {
		return this.yczdspsj;
	}
	
	public void setYczdspsj(java.util.Date value) {
		this.yczdspsj = value;
	}
	
	public java.lang.String getYczdpsbz() {
		return this.yczdpsbz;
	}
	
	public void setYczdpsbz(java.lang.String value) {
		this.yczdpsbz = value;
	}
	public java.lang.String getYczdspyj() {
		return this.yczdspyj;
	}
	
	public void setYczdspyj(java.lang.String value) {
		this.yczdspyj = value;
	}
	
	public java.util.Date getYcldspsj() {
		return this.ycldspsj;
	}
	
	public void setYcldspsj(java.util.Date value) {
		this.ycldspsj = value;
	}
	
	public java.lang.String getYcldspr() {
		return this.ycldspr;
	}
	
	public void setYcldspr(java.lang.String value) {
		this.ycldspr = value;
	}
	public java.lang.String getYcldpsbz() {
		return this.ycldpsbz;
	}
	
	public void setYcldpsbz(java.lang.String value) {
		this.ycldpsbz = value;
	}
	public java.lang.String getYcldspyj() {
		return this.ycldspyj;
	}
	
	public void setYcldspyj(java.lang.String value) {
		this.ycldspyj = value;
	}
	
	public java.util.Date getXjjcsj() {
		return this.xjjcsj;
	}
	
	public void setXjjcsj(java.util.Date value) {
		this.xjjcsj = value;
	}
	
	public java.lang.String getJczxr() {
		return this.jczxr;
	}
	
	public void setJczxr(java.lang.String value) {
		this.jczxr = value;
	}
	
	public java.util.Date getJczxqk() {
		return this.jczxqk;
	}
	
	public void setJczxqk(java.util.Date value) {
		this.jczxqk = value;
	}
	
	public java.lang.String getJcbz() {
		return this.jcbz;
	}
	
	public void setJcbz(java.lang.String value) {
		this.jcbz = value;
	}
	public java.lang.String getXjsyzxqk() {
		return this.xjsyzxqk;
	}
	
	public void setXjsyzxqk(java.lang.String value) {
		this.xjsyzxqk = value;
	}
	 
}

