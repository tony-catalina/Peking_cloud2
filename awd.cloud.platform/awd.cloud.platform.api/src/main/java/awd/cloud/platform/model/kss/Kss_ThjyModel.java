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


public class Kss_ThjyModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	
	private java.lang.String thyy;
	
	private java.lang.String thnr;
	
	private java.lang.String fzmj;
	
	private java.lang.String ywqxyc;
	
	private java.lang.String qxycqk;
	
	private java.lang.String ywzssb;
	
	private java.lang.String sbqk;
	
	private java.lang.String sfljjs;
	
	private java.lang.String bljjsyy;
	
	private java.lang.String bllbz;
	
	private java.lang.String sjzlJsbz;
	
	private java.lang.String barcode;
	
	private java.lang.String usefinger;
	
	private java.lang.String nozhiwenyy;
	
	private java.lang.String skth;
	
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
	
	private java.lang.String thdd;
	
	private java.lang.String sfls;
	
	private java.lang.String ywqyxx;
	//columns END

	 

	public Kss_ThjyModel(){
	}
	public Kss_ThjyModel(String id) {
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
	
	public java.util.Date getKssj() {
		return this.kssj;
	}
	
	public void setKssj(java.util.Date value) {
		this.kssj = value;
	}
	
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	public java.lang.String getThyy() {
		return this.thyy;
	}
	
	public void setThyy(java.lang.String value) {
		this.thyy = value;
	}
	public java.lang.String getThnr() {
		return this.thnr;
	}
	
	public void setThnr(java.lang.String value) {
		this.thnr = value;
	}
	public java.lang.String getFzmj() {
		return this.fzmj;
	}
	
	public void setFzmj(java.lang.String value) {
		this.fzmj = value;
	}
	public java.lang.String getYwqxyc() {
		return this.ywqxyc;
	}
	
	public void setYwqxyc(java.lang.String value) {
		this.ywqxyc = value;
	}
	public java.lang.String getQxycqk() {
		return this.qxycqk;
	}
	
	public void setQxycqk(java.lang.String value) {
		this.qxycqk = value;
	}
	public java.lang.String getYwzssb() {
		return this.ywzssb;
	}
	
	public void setYwzssb(java.lang.String value) {
		this.ywzssb = value;
	}
	public java.lang.String getSbqk() {
		return this.sbqk;
	}
	
	public void setSbqk(java.lang.String value) {
		this.sbqk = value;
	}
	public java.lang.String getSfljjs() {
		return this.sfljjs;
	}
	
	public void setSfljjs(java.lang.String value) {
		this.sfljjs = value;
	}
	public java.lang.String getBljjsyy() {
		return this.bljjsyy;
	}
	
	public void setBljjsyy(java.lang.String value) {
		this.bljjsyy = value;
	}
	public java.lang.String getBllbz() {
		return this.bllbz;
	}
	
	public void setBllbz(java.lang.String value) {
		this.bllbz = value;
	}
	public java.lang.String getSjzlJsbz() {
		return this.sjzlJsbz;
	}
	
	public void setSjzlJsbz(java.lang.String value) {
		this.sjzlJsbz = value;
	}
	public java.lang.String getBarcode() {
		return this.barcode;
	}
	
	public void setBarcode(java.lang.String value) {
		this.barcode = value;
	}
	public java.lang.String getUsefinger() {
		return this.usefinger;
	}
	
	public void setUsefinger(java.lang.String value) {
		this.usefinger = value;
	}
	public java.lang.String getNozhiwenyy() {
		return this.nozhiwenyy;
	}
	
	public void setNozhiwenyy(java.lang.String value) {
		this.nozhiwenyy = value;
	}
	public java.lang.String getSkth() {
		return this.skth;
	}
	
	public void setSkth(java.lang.String value) {
		this.skth = value;
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
	
	public java.lang.String getThdd() {
		return this.thdd;
	}
	
	public void setThdd(java.lang.String value) {
		this.thdd = value;
	}
	public java.lang.String getSfls() {
		return this.sfls;
	}
	
	public void setSfls(java.lang.String value) {
		this.sfls = value;
	}
	public java.lang.String getYwqyxx() {
		return this.ywqyxx;
	}
	
	public void setYwqyxx(java.lang.String value) {
		this.ywqyxx = value;
	}
	 
}

