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


public class Kss_JygzzlpgModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String cjhywzs;
	
	private java.lang.String cjhknjzd;
	
	private java.lang.String rzrcqk;
	
	private java.lang.String jsnbx;
	
	private java.lang.String wffzs;
	
	private java.lang.String jstc;
	
	private java.lang.String zyyx;
	
	private java.lang.String czwt;
	
	private java.lang.String tskn;
	
	private java.lang.String mxfzqx;
	
	private java.lang.String sjry;
	
	private java.lang.String swry;
	
	private java.lang.String ybbjdx;
	
	private java.lang.String sfylrhlw;
	
	private java.lang.String azbjgzjy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;
	
	private java.lang.String djr;
	
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

	 

	public Kss_JygzzlpgModel(){
	}
	public Kss_JygzzlpgModel(String id) {
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
	public java.lang.String getCjhywzs() {
		return this.cjhywzs;
	}
	
	public void setCjhywzs(java.lang.String value) {
		this.cjhywzs = value;
	}
	public java.lang.String getCjhknjzd() {
		return this.cjhknjzd;
	}
	
	public void setCjhknjzd(java.lang.String value) {
		this.cjhknjzd = value;
	}
	public java.lang.String getRzrcqk() {
		return this.rzrcqk;
	}
	
	public void setRzrcqk(java.lang.String value) {
		this.rzrcqk = value;
	}
	public java.lang.String getJsnbx() {
		return this.jsnbx;
	}
	
	public void setJsnbx(java.lang.String value) {
		this.jsnbx = value;
	}
	public java.lang.String getWffzs() {
		return this.wffzs;
	}
	
	public void setWffzs(java.lang.String value) {
		this.wffzs = value;
	}
	public java.lang.String getJstc() {
		return this.jstc;
	}
	
	public void setJstc(java.lang.String value) {
		this.jstc = value;
	}
	public java.lang.String getZyyx() {
		return this.zyyx;
	}
	
	public void setZyyx(java.lang.String value) {
		this.zyyx = value;
	}
	public java.lang.String getCzwt() {
		return this.czwt;
	}
	
	public void setCzwt(java.lang.String value) {
		this.czwt = value;
	}
	public java.lang.String getTskn() {
		return this.tskn;
	}
	
	public void setTskn(java.lang.String value) {
		this.tskn = value;
	}
	public java.lang.String getMxfzqx() {
		return this.mxfzqx;
	}
	
	public void setMxfzqx(java.lang.String value) {
		this.mxfzqx = value;
	}
	public java.lang.String getSjry() {
		return this.sjry;
	}
	
	public void setSjry(java.lang.String value) {
		this.sjry = value;
	}
	public java.lang.String getSwry() {
		return this.swry;
	}
	
	public void setSwry(java.lang.String value) {
		this.swry = value;
	}
	public java.lang.String getYbbjdx() {
		return this.ybbjdx;
	}
	
	public void setYbbjdx(java.lang.String value) {
		this.ybbjdx = value;
	}
	public java.lang.String getSfylrhlw() {
		return this.sfylrhlw;
	}
	
	public void setSfylrhlw(java.lang.String value) {
		this.sfylrhlw = value;
	}
	public java.lang.String getAzbjgzjy() {
		return this.azbjgzjy;
	}
	
	public void setAzbjgzjy(java.lang.String value) {
		this.azbjgzjy = value;
	}
	
	public java.util.Date getDjrq() {
		return this.djrq;
	}
	
	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}
	
	public java.lang.String getDjr() {
		return this.djr;
	}
	
	public void setDjr(java.lang.String value) {
		this.djr = value;
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

