/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

import awd.bj.kss.model.JbxxInfo;
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


public class Kss_LshjModel extends JbxxInfo {
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String ywlcid;
	
	private java.lang.String lsxm;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String dw;
	
	private java.lang.Short rs;
	
	private java.lang.String hjsy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	
	private java.lang.String lrmj;
	
	private java.lang.String pzr;
	
	private java.lang.String fzmj;
	
	private java.lang.String jsr;
	
	private java.lang.String pastable;
	
	private java.lang.String hjlx;
	
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
	
	private java.lang.String hjs;
	
	private java.lang.String lszl;
	
	private java.lang.String lsfy;
	
	private java.lang.String hjpzjg;
	
	private java.lang.String xkjdwsh;
	
	private java.lang.String tbjcqk;
	
	private java.lang.String wjpjcqk;
	//columns END

	 

	public Kss_LshjModel(){
	}
	public Kss_LshjModel(String id) {
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
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getLsxm() {
		return this.lsxm;
	}
	
	public void setLsxm(java.lang.String value) {
		this.lsxm = value;
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
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	public java.lang.Short getRs() {
		return this.rs;
	}
	
	public void setRs(java.lang.Short value) {
		this.rs = value;
	}
	public java.lang.String getHjsy() {
		return this.hjsy;
	}
	
	public void setHjsy(java.lang.String value) {
		this.hjsy = value;
	}
	
	public java.util.Date getHjsj() {
		return this.hjsj;
	}
	
	public void setHjsj(java.util.Date value) {
		this.hjsj = value;
	}
	
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getLrmj() {
		return this.lrmj;
	}
	
	public void setLrmj(java.lang.String value) {
		this.lrmj = value;
	}
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	public java.lang.String getFzmj() {
		return this.fzmj;
	}
	
	public void setFzmj(java.lang.String value) {
		this.fzmj = value;
	}
	public java.lang.String getJsr() {
		return this.jsr;
	}
	
	public void setJsr(java.lang.String value) {
		this.jsr = value;
	}
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	public java.lang.String getHjlx() {
		return this.hjlx;
	}
	
	public void setHjlx(java.lang.String value) {
		this.hjlx = value;
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
	
	public java.lang.String getHjs() {
		return this.hjs;
	}
	
	public void setHjs(java.lang.String value) {
		this.hjs = value;
	}
	public java.lang.String getLszl() {
		return this.lszl;
	}
	
	public void setLszl(java.lang.String value) {
		this.lszl = value;
	}
	public java.lang.String getLsfy() {
		return this.lsfy;
	}
	
	public void setLsfy(java.lang.String value) {
		this.lsfy = value;
	}
	public java.lang.String getHjpzjg() {
		return this.hjpzjg;
	}
	
	public void setHjpzjg(java.lang.String value) {
		this.hjpzjg = value;
	}
	public java.lang.String getXkjdwsh() {
		return this.xkjdwsh;
	}
	
	public void setXkjdwsh(java.lang.String value) {
		this.xkjdwsh = value;
	}
	public java.lang.String getTbjcqk() {
		return this.tbjcqk;
	}
	
	public void setTbjcqk(java.lang.String value) {
		this.tbjcqk = value;
	}
	public java.lang.String getWjpjcqk() {
		return this.wjpjcqk;
	}
	
	public void setWjpjcqk(java.lang.String value) {
		this.wjpjcqk = value;
	}
	 
}

