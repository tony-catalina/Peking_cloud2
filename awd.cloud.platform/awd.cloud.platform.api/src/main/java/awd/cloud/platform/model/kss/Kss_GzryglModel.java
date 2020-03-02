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


public class Kss_GzryglModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String zgxm;
	
	private java.lang.String xmpy;
	
	private java.lang.String mz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csny;
	
	private java.lang.String zy;
	
	private java.lang.String zjlx;
	
	private java.lang.String zjh;
	
	private java.lang.String zzmm;
	
	private java.lang.String gw;
	
	private java.lang.String jtzz;
	
	private java.lang.String hjszd;
	
	private java.lang.String lxdh;
	
	private java.lang.String sjh;
	
	private java.lang.String xl;
	
	private java.lang.String yszyzbh;
	
	private java.lang.String yszgzbh;
	
	private java.lang.String sfqzys;
	
	private java.lang.String flag;
	
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

	 

	public Kss_GzryglModel(){
	}
	public Kss_GzryglModel(String id) {
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
	public java.lang.String getZgxm() {
		return this.zgxm;
	}
	
	public void setZgxm(java.lang.String value) {
		this.zgxm = value;
	}
	public java.lang.String getXmpy() {
		return this.xmpy;
	}
	
	public void setXmpy(java.lang.String value) {
		this.xmpy = value;
	}
	public java.lang.String getMz() {
		return this.mz;
	}
	
	public void setMz(java.lang.String value) {
		this.mz = value;
	}
	
	public java.util.Date getCsny() {
		return this.csny;
	}
	
	public void setCsny(java.util.Date value) {
		this.csny = value;
	}
	
	public java.lang.String getZy() {
		return this.zy;
	}
	
	public void setZy(java.lang.String value) {
		this.zy = value;
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
	public java.lang.String getZzmm() {
		return this.zzmm;
	}
	
	public void setZzmm(java.lang.String value) {
		this.zzmm = value;
	}
	public java.lang.String getGw() {
		return this.gw;
	}
	
	public void setGw(java.lang.String value) {
		this.gw = value;
	}
	public java.lang.String getJtzz() {
		return this.jtzz;
	}
	
	public void setJtzz(java.lang.String value) {
		this.jtzz = value;
	}
	public java.lang.String getHjszd() {
		return this.hjszd;
	}
	
	public void setHjszd(java.lang.String value) {
		this.hjszd = value;
	}
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	public java.lang.String getSjh() {
		return this.sjh;
	}
	
	public void setSjh(java.lang.String value) {
		this.sjh = value;
	}
	public java.lang.String getXl() {
		return this.xl;
	}
	
	public void setXl(java.lang.String value) {
		this.xl = value;
	}
	public java.lang.String getYszyzbh() {
		return this.yszyzbh;
	}
	
	public void setYszyzbh(java.lang.String value) {
		this.yszyzbh = value;
	}
	public java.lang.String getYszgzbh() {
		return this.yszgzbh;
	}
	
	public void setYszgzbh(java.lang.String value) {
		this.yszgzbh = value;
	}
	public java.lang.String getSfqzys() {
		return this.sfqzys;
	}
	
	public void setSfqzys(java.lang.String value) {
		this.sfqzys = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
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

