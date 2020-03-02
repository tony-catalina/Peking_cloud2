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


public class Kss_ZyrycfjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String cfly;
	
	private java.lang.String sjms;
	
	private java.lang.String cfzl;
	
	private java.lang.Integer cfts;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cfksrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cfjsrq;
	
	private java.lang.String zdzxm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zdzpssj;
	
	private java.lang.String zdzpsbz;
	
	private java.lang.String zdzyj;
	
	private java.lang.String zdzyjbz;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldpsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String ldyj;
	
	private java.lang.String ldyjbz;
	
	private java.lang.String bz;
	
	private java.lang.String jbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbsj;
	
	private java.lang.String zxqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zxsj;
	
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
	
	private java.lang.String ycjbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yccpsj;
	
	private java.lang.Integer ycts;
	
	private java.lang.String ycly;
	
	private java.lang.String ycpsbz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yjssj;
	
	private java.lang.String ycspr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ycspsj;
	
	private java.lang.String cfqjbx;
	
	private java.lang.String zxr;
	
	private java.lang.String ycspyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcdjrq;
	
	private java.lang.String jcr;
	
	private java.lang.String ywlcid;
	
	private java.lang.String state;
	//columns END

	 

	public Kss_ZyrycfjlModel(){
	}
	public Kss_ZyrycfjlModel(String id) {
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
	public java.lang.String getCfly() {
		return this.cfly;
	}
	
	public void setCfly(java.lang.String value) {
		this.cfly = value;
	}
	public java.lang.String getSjms() {
		return this.sjms;
	}
	
	public void setSjms(java.lang.String value) {
		this.sjms = value;
	}
	public java.lang.String getCfzl() {
		return this.cfzl;
	}
	
	public void setCfzl(java.lang.String value) {
		this.cfzl = value;
	}
	public java.lang.Integer getCfts() {
		return this.cfts;
	}
	
	public void setCfts(java.lang.Integer value) {
		this.cfts = value;
	}
	
	public java.util.Date getCfksrq() {
		return this.cfksrq;
	}
	
	public void setCfksrq(java.util.Date value) {
		this.cfksrq = value;
	}
	
	
	public java.util.Date getCfjsrq() {
		return this.cfjsrq;
	}
	
	public void setCfjsrq(java.util.Date value) {
		this.cfjsrq = value;
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
	
	public java.lang.String getZdzpsbz() {
		return this.zdzpsbz;
	}
	
	public void setZdzpsbz(java.lang.String value) {
		this.zdzpsbz = value;
	}
	public java.lang.String getZdzyj() {
		return this.zdzyj;
	}
	
	public void setZdzyj(java.lang.String value) {
		this.zdzyj = value;
	}
	public java.lang.String getZdzyjbz() {
		return this.zdzyjbz;
	}
	
	public void setZdzyjbz(java.lang.String value) {
		this.zdzyjbz = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getLdpsbz() {
		return this.ldpsbz;
	}
	
	public void setLdpsbz(java.lang.String value) {
		this.ldpsbz = value;
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
	public java.lang.String getLdyjbz() {
		return this.ldyjbz;
	}
	
	public void setLdyjbz(java.lang.String value) {
		this.ldyjbz = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getJbr() {
		return this.jbr;
	}
	
	public void setJbr(java.lang.String value) {
		this.jbr = value;
	}
	
	public java.util.Date getJbsj() {
		return this.jbsj;
	}
	
	public void setJbsj(java.util.Date value) {
		this.jbsj = value;
	}
	
	public java.lang.String getZxqk() {
		return this.zxqk;
	}
	
	public void setZxqk(java.lang.String value) {
		this.zxqk = value;
	}
	
	public java.util.Date getZxsj() {
		return this.zxsj;
	}
	
	public void setZxsj(java.util.Date value) {
		this.zxsj = value;
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
	
	public java.lang.String getYcjbr() {
		return this.ycjbr;
	}
	
	public void setYcjbr(java.lang.String value) {
		this.ycjbr = value;
	}
	
	public java.util.Date getYccpsj() {
		return this.yccpsj;
	}
	
	public void setYccpsj(java.util.Date value) {
		this.yccpsj = value;
	}
	
	public java.lang.Integer getYcts() {
		return this.ycts;
	}
	
	public void setYcts(java.lang.Integer value) {
		this.ycts = value;
	}
	public java.lang.String getYcly() {
		return this.ycly;
	}
	
	public void setYcly(java.lang.String value) {
		this.ycly = value;
	}
	public java.lang.String getYcpsbz() {
		return this.ycpsbz;
	}
	
	public void setYcpsbz(java.lang.String value) {
		this.ycpsbz = value;
	}
	
	public java.util.Date getYjssj() {
		return this.yjssj;
	}
	
	public void setYjssj(java.util.Date value) {
		this.yjssj = value;
	}
	
	public java.lang.String getYcspr() {
		return this.ycspr;
	}
	
	public void setYcspr(java.lang.String value) {
		this.ycspr = value;
	}
	
	public java.util.Date getYcspsj() {
		return this.ycspsj;
	}
	
	public void setYcspsj(java.util.Date value) {
		this.ycspsj = value;
	}
	
	public java.lang.String getCfqjbx() {
		return this.cfqjbx;
	}
	
	public void setCfqjbx(java.lang.String value) {
		this.cfqjbx = value;
	}
	public java.lang.String getZxr() {
		return this.zxr;
	}
	
	public void setZxr(java.lang.String value) {
		this.zxr = value;
	}
	public java.lang.String getYcspyj() {
		return this.ycspyj;
	}
	
	public void setYcspyj(java.lang.String value) {
		this.ycspyj = value;
	}
	
	public java.util.Date getJcrq() {
		return this.jcrq;
	}
	
	public void setJcrq(java.util.Date value) {
		this.jcrq = value;
	}
	
	
	public java.util.Date getJcdjrq() {
		return this.jcdjrq;
	}
	
	public void setJcdjrq(java.util.Date value) {
		this.jcdjrq = value;
	}
	
	public java.lang.String getJcr() {
		return this.jcr;
	}
	
	public void setJcr(java.lang.String value) {
		this.jcr = value;
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
	 
}

