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


public class Kss_MjjbxxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jh;
	
	private java.lang.String sfzh;
	
	private java.lang.String xm;
	
	private java.lang.String xb;
	
	private java.lang.String mz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csny;
	
	private java.lang.String jx;
	
	private java.lang.String xl;
	
	private java.lang.String zzmm;
	
	private java.lang.String zpurl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cjgmgzsj;
	
	private java.lang.String hyzk;
	
	private java.lang.String jtzz;
	
	private java.lang.String hjszd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cjgzsj;
	
	private java.lang.String bm;
	
	private java.lang.String bmzw;
	
	private java.lang.String gbzwjb;
	
	private java.lang.String lxdh;
	
	private java.lang.String xlzxs;
	
	private java.lang.String sjh;
	
	private java.lang.String flag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lkrq;
	
	private java.lang.String lkyy;
	
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
	
	private java.lang.String xmpy;
	//columns END

	 

	public Kss_MjjbxxModel(){
	}
	public Kss_MjjbxxModel(String id) {
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
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
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
	
	public java.lang.String getJx() {
		return this.jx;
	}
	
	public void setJx(java.lang.String value) {
		this.jx = value;
	}
	public java.lang.String getXl() {
		return this.xl;
	}
	
	public void setXl(java.lang.String value) {
		this.xl = value;
	}
	public java.lang.String getZzmm() {
		return this.zzmm;
	}
	
	public void setZzmm(java.lang.String value) {
		this.zzmm = value;
	}
	public java.lang.String getZpurl() {
		return this.zpurl;
	}
	
	public void setZpurl(java.lang.String value) {
		this.zpurl = value;
	}
	
	public java.util.Date getCjgmgzsj() {
		return this.cjgmgzsj;
	}
	
	public void setCjgmgzsj(java.util.Date value) {
		this.cjgmgzsj = value;
	}
	
	public java.lang.String getHyzk() {
		return this.hyzk;
	}
	
	public void setHyzk(java.lang.String value) {
		this.hyzk = value;
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
	
	public java.util.Date getCjgzsj() {
		return this.cjgzsj;
	}
	
	public void setCjgzsj(java.util.Date value) {
		this.cjgzsj = value;
	}
	
	public java.lang.String getBm() {
		return this.bm;
	}
	
	public void setBm(java.lang.String value) {
		this.bm = value;
	}
	public java.lang.String getBmzw() {
		return this.bmzw;
	}
	
	public void setBmzw(java.lang.String value) {
		this.bmzw = value;
	}
	public java.lang.String getGbzwjb() {
		return this.gbzwjb;
	}
	
	public void setGbzwjb(java.lang.String value) {
		this.gbzwjb = value;
	}
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	public java.lang.String getXlzxs() {
		return this.xlzxs;
	}
	
	public void setXlzxs(java.lang.String value) {
		this.xlzxs = value;
	}
	public java.lang.String getSjh() {
		return this.sjh;
	}
	
	public void setSjh(java.lang.String value) {
		this.sjh = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	
	public java.util.Date getLkrq() {
		return this.lkrq;
	}
	
	public void setLkrq(java.util.Date value) {
		this.lkrq = value;
	}
	
	public java.lang.String getLkyy() {
		return this.lkyy;
	}
	
	public void setLkyy(java.lang.String value) {
		this.lkyy = value;
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
	
	public java.lang.String getXmpy() {
		return this.xmpy;
	}
	
	public void setXmpy(java.lang.String value) {
		this.xmpy = value;
	}
	 
}

