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


public class Kss_XtzxjdModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String ajmc;
	
	private java.lang.String gaajbh;
	
	private java.lang.String ysdwmc;
	
	private java.lang.String jsdwmc;
	
	private java.lang.String csjsjgsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jdrq;
	
	private java.lang.String wfqx;
	
	private java.lang.String sfyzwf;
	
	private java.lang.String wfbjcdw;
	
	private java.lang.String jdwslx;
	
	private java.lang.String fgyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fkrq;
	
	private java.lang.String fsr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fssj;
	
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

	 

	public Kss_XtzxjdModel(){
	}
	public Kss_XtzxjdModel(String id) {
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
	public java.lang.String getAjmc() {
		return this.ajmc;
	}
	
	public void setAjmc(java.lang.String value) {
		this.ajmc = value;
	}
	public java.lang.String getGaajbh() {
		return this.gaajbh;
	}
	
	public void setGaajbh(java.lang.String value) {
		this.gaajbh = value;
	}
	public java.lang.String getYsdwmc() {
		return this.ysdwmc;
	}
	
	public void setYsdwmc(java.lang.String value) {
		this.ysdwmc = value;
	}
	public java.lang.String getJsdwmc() {
		return this.jsdwmc;
	}
	
	public void setJsdwmc(java.lang.String value) {
		this.jsdwmc = value;
	}
	public java.lang.String getCsjsjgsj() {
		return this.csjsjgsj;
	}
	
	public void setCsjsjgsj(java.lang.String value) {
		this.csjsjgsj = value;
	}
	
	public java.util.Date getJdrq() {
		return this.jdrq;
	}
	
	public void setJdrq(java.util.Date value) {
		this.jdrq = value;
	}
	
	public java.lang.String getWfqx() {
		return this.wfqx;
	}
	
	public void setWfqx(java.lang.String value) {
		this.wfqx = value;
	}
	public java.lang.String getSfyzwf() {
		return this.sfyzwf;
	}
	
	public void setSfyzwf(java.lang.String value) {
		this.sfyzwf = value;
	}
	public java.lang.String getWfbjcdw() {
		return this.wfbjcdw;
	}
	
	public void setWfbjcdw(java.lang.String value) {
		this.wfbjcdw = value;
	}
	public java.lang.String getJdwslx() {
		return this.jdwslx;
	}
	
	public void setJdwslx(java.lang.String value) {
		this.jdwslx = value;
	}
	public java.lang.String getFgyj() {
		return this.fgyj;
	}
	
	public void setFgyj(java.lang.String value) {
		this.fgyj = value;
	}
	
	public java.util.Date getFkrq() {
		return this.fkrq;
	}
	
	public void setFkrq(java.util.Date value) {
		this.fkrq = value;
	}
	
	public java.lang.String getFsr() {
		return this.fsr;
	}
	
	public void setFsr(java.lang.String value) {
		this.fsr = value;
	}
	
	public java.util.Date getFssj() {
		return this.fssj;
	}
	
	public void setFssj(java.util.Date value) {
		this.fssj = value;
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

