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


public class Kss_FxpgModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String jtbg;
	
	private java.lang.String ffrz;
	
	private java.lang.String ddcjdjl;
	
	private java.lang.String dddhyzk;
	
	private java.lang.String dqjtqk;
	
	private java.lang.String dqgqsjqk;
	
	private java.lang.String dqshsczk;
	
	private java.lang.String dxdqxtqk;
	
	private java.lang.String dxdqstzk;
	
	private java.lang.String ddzsjsycqk;
	
	private java.lang.String dazdxjkf;
	
	private java.lang.String sjyz;
	
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
	
	private java.lang.String pglx;
	
	private java.lang.String yfxdj;
	
	private java.lang.String xfxdj;
	
	private java.lang.String yfxzb;
	
	private java.lang.String xfxzb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date pgsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ypgsj;
	//columns END

	 

	public Kss_FxpgModel(){
	}
	public Kss_FxpgModel(String id) {
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
	public java.lang.String getJtbg() {
		return this.jtbg;
	}
	
	public void setJtbg(java.lang.String value) {
		this.jtbg = value;
	}
	public java.lang.String getFfrz() {
		return this.ffrz;
	}
	
	public void setFfrz(java.lang.String value) {
		this.ffrz = value;
	}
	public java.lang.String getDdcjdjl() {
		return this.ddcjdjl;
	}
	
	public void setDdcjdjl(java.lang.String value) {
		this.ddcjdjl = value;
	}
	public java.lang.String getDddhyzk() {
		return this.dddhyzk;
	}
	
	public void setDddhyzk(java.lang.String value) {
		this.dddhyzk = value;
	}
	public java.lang.String getDqjtqk() {
		return this.dqjtqk;
	}
	
	public void setDqjtqk(java.lang.String value) {
		this.dqjtqk = value;
	}
	public java.lang.String getDqgqsjqk() {
		return this.dqgqsjqk;
	}
	
	public void setDqgqsjqk(java.lang.String value) {
		this.dqgqsjqk = value;
	}
	public java.lang.String getDqshsczk() {
		return this.dqshsczk;
	}
	
	public void setDqshsczk(java.lang.String value) {
		this.dqshsczk = value;
	}
	public java.lang.String getDxdqxtqk() {
		return this.dxdqxtqk;
	}
	
	public void setDxdqxtqk(java.lang.String value) {
		this.dxdqxtqk = value;
	}
	public java.lang.String getDxdqstzk() {
		return this.dxdqstzk;
	}
	
	public void setDxdqstzk(java.lang.String value) {
		this.dxdqstzk = value;
	}
	public java.lang.String getDdzsjsycqk() {
		return this.ddzsjsycqk;
	}
	
	public void setDdzsjsycqk(java.lang.String value) {
		this.ddzsjsycqk = value;
	}
	public java.lang.String getDazdxjkf() {
		return this.dazdxjkf;
	}
	
	public void setDazdxjkf(java.lang.String value) {
		this.dazdxjkf = value;
	}
	public java.lang.String getSjyz() {
		return this.sjyz;
	}
	
	public void setSjyz(java.lang.String value) {
		this.sjyz = value;
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
	
	public java.lang.String getPglx() {
		return this.pglx;
	}
	
	public void setPglx(java.lang.String value) {
		this.pglx = value;
	}
	public java.lang.String getYfxdj() {
		return this.yfxdj;
	}
	
	public void setYfxdj(java.lang.String value) {
		this.yfxdj = value;
	}
	public java.lang.String getXfxdj() {
		return this.xfxdj;
	}
	
	public void setXfxdj(java.lang.String value) {
		this.xfxdj = value;
	}
	public java.lang.String getYfxzb() {
		return this.yfxzb;
	}
	
	public void setYfxzb(java.lang.String value) {
		this.yfxzb = value;
	}
	public java.lang.String getXfxzb() {
		return this.xfxzb;
	}
	
	public void setXfxzb(java.lang.String value) {
		this.xfxzb = value;
	}
	
	public java.util.Date getPgsj() {
		return this.pgsj;
	}
	
	public void setPgsj(java.util.Date value) {
		this.pgsj = value;
	}
	
	
	public java.util.Date getYpgsj() {
		return this.ypgsj;
	}
	
	public void setYpgsj(java.util.Date value) {
		this.ypgsj = value;
	}
	
	 
}

