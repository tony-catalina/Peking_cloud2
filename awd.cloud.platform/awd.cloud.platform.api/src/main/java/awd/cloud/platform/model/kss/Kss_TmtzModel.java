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


public class Kss_TmtzModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String gcbh;
	
	private java.lang.String zjh;
	
	private java.lang.String sg;
	
	private java.lang.String tz;
	
	private java.lang.String zc;
	
	private java.lang.String tb;
	
	private java.lang.String jb;
	
	private java.lang.String xb;
	
	private java.lang.String fb;
	
	private java.lang.String bb;
	
	private java.lang.String db;
	
	private java.lang.String sz;
	
	private java.lang.String xz;
	
	private java.lang.String tmtz1;
	
	private java.lang.String rtbw1;
	
	private java.lang.String fw1;
	
	private java.lang.String sl1;
	
	private java.lang.String tmtz2;
	
	private java.lang.String rtbw2;
	
	private java.lang.String fw2;
	
	private java.lang.String sl2;
	
	private java.lang.String tmtz3;
	
	private java.lang.String rtbw3;
	
	private java.lang.String fw3;
	
	private java.lang.String sl3;
	
	private java.lang.String tmtz4;
	
	private java.lang.String rtbw4;
	
	private java.lang.String fw4;
	
	private java.lang.String sl4;
	
	private java.lang.String tmtz5;
	
	private java.lang.String rtbw5;
	
	private java.lang.String fw5;
	
	private java.lang.String sl5;
	
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
	
	private java.lang.String jkqkid;
	//columns END

	 

	public Kss_TmtzModel(){
	}
	public Kss_TmtzModel(String id) {
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
	public java.lang.String getGcbh() {
		return this.gcbh;
	}
	
	public void setGcbh(java.lang.String value) {
		this.gcbh = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	public java.lang.String getSg() {
		return this.sg;
	}
	
	public void setSg(java.lang.String value) {
		this.sg = value;
	}
	public java.lang.String getTz() {
		return this.tz;
	}
	
	public void setTz(java.lang.String value) {
		this.tz = value;
	}
	public java.lang.String getZc() {
		return this.zc;
	}
	
	public void setZc(java.lang.String value) {
		this.zc = value;
	}
	public java.lang.String getTb() {
		return this.tb;
	}
	
	public void setTb(java.lang.String value) {
		this.tb = value;
	}
	public java.lang.String getJb() {
		return this.jb;
	}
	
	public void setJb(java.lang.String value) {
		this.jb = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	public java.lang.String getFb() {
		return this.fb;
	}
	
	public void setFb(java.lang.String value) {
		this.fb = value;
	}
	public java.lang.String getBb() {
		return this.bb;
	}
	
	public void setBb(java.lang.String value) {
		this.bb = value;
	}
	public java.lang.String getDb() {
		return this.db;
	}
	
	public void setDb(java.lang.String value) {
		this.db = value;
	}
	public java.lang.String getSz() {
		return this.sz;
	}
	
	public void setSz(java.lang.String value) {
		this.sz = value;
	}
	public java.lang.String getXz() {
		return this.xz;
	}
	
	public void setXz(java.lang.String value) {
		this.xz = value;
	}
	public java.lang.String getTmtz1() {
		return this.tmtz1;
	}
	
	public void setTmtz1(java.lang.String value) {
		this.tmtz1 = value;
	}
	public java.lang.String getRtbw1() {
		return this.rtbw1;
	}
	
	public void setRtbw1(java.lang.String value) {
		this.rtbw1 = value;
	}
	public java.lang.String getFw1() {
		return this.fw1;
	}
	
	public void setFw1(java.lang.String value) {
		this.fw1 = value;
	}
	public java.lang.String getSl1() {
		return this.sl1;
	}
	
	public void setSl1(java.lang.String value) {
		this.sl1 = value;
	}
	public java.lang.String getTmtz2() {
		return this.tmtz2;
	}
	
	public void setTmtz2(java.lang.String value) {
		this.tmtz2 = value;
	}
	public java.lang.String getRtbw2() {
		return this.rtbw2;
	}
	
	public void setRtbw2(java.lang.String value) {
		this.rtbw2 = value;
	}
	public java.lang.String getFw2() {
		return this.fw2;
	}
	
	public void setFw2(java.lang.String value) {
		this.fw2 = value;
	}
	public java.lang.String getSl2() {
		return this.sl2;
	}
	
	public void setSl2(java.lang.String value) {
		this.sl2 = value;
	}
	public java.lang.String getTmtz3() {
		return this.tmtz3;
	}
	
	public void setTmtz3(java.lang.String value) {
		this.tmtz3 = value;
	}
	public java.lang.String getRtbw3() {
		return this.rtbw3;
	}
	
	public void setRtbw3(java.lang.String value) {
		this.rtbw3 = value;
	}
	public java.lang.String getFw3() {
		return this.fw3;
	}
	
	public void setFw3(java.lang.String value) {
		this.fw3 = value;
	}
	public java.lang.String getSl3() {
		return this.sl3;
	}
	
	public void setSl3(java.lang.String value) {
		this.sl3 = value;
	}
	public java.lang.String getTmtz4() {
		return this.tmtz4;
	}
	
	public void setTmtz4(java.lang.String value) {
		this.tmtz4 = value;
	}
	public java.lang.String getRtbw4() {
		return this.rtbw4;
	}
	
	public void setRtbw4(java.lang.String value) {
		this.rtbw4 = value;
	}
	public java.lang.String getFw4() {
		return this.fw4;
	}
	
	public void setFw4(java.lang.String value) {
		this.fw4 = value;
	}
	public java.lang.String getSl4() {
		return this.sl4;
	}
	
	public void setSl4(java.lang.String value) {
		this.sl4 = value;
	}
	public java.lang.String getTmtz5() {
		return this.tmtz5;
	}
	
	public void setTmtz5(java.lang.String value) {
		this.tmtz5 = value;
	}
	public java.lang.String getRtbw5() {
		return this.rtbw5;
	}
	
	public void setRtbw5(java.lang.String value) {
		this.rtbw5 = value;
	}
	public java.lang.String getFw5() {
		return this.fw5;
	}
	
	public void setFw5(java.lang.String value) {
		this.fw5 = value;
	}
	public java.lang.String getSl5() {
		return this.sl5;
	}
	
	public void setSl5(java.lang.String value) {
		this.sl5 = value;
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
	
	public java.lang.String getJkqkid() {
		return this.jkqkid;
	}
	
	public void setJkqkid(java.lang.String value) {
		this.jkqkid = value;
	}
	 
}

