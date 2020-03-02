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


public class Kss_SwfzModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String tgxsbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tgrq;
	
	private java.lang.String xslx;
	
	private java.lang.String zyrxm;
	
	private java.lang.String xb;
	
	private java.lang.String ay;
	
	private java.lang.String gycs;
	
	private java.lang.String xsnrzy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sdxsrq;
	
	private java.lang.String tjbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjsj;
	
	private java.lang.String czqk;
	
	private java.lang.String cyca;
	
	private java.lang.String ladw;
	
	private java.lang.String lah;
	
	private java.lang.String yzhbjjrjzhrq;
	
	private java.lang.String czcs;
	
	private java.lang.String flzmws;
	
	private java.lang.String padw;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date parq;
	
	private java.lang.String pabh;
	
	private java.lang.String cbdw;
	
	private java.lang.String clqk;
	
	private java.lang.String sfycd;
	
	private java.lang.String sfycr;
	
	private java.lang.String wlcx;
	
	private java.lang.String cydk;
	
	private java.lang.String sdfc;
	
	private java.lang.String qknr;
	
	private java.lang.String czbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fksj;
	
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
	
	private java.lang.String pastable;
	
	private java.lang.String ywlcid;
	//columns END

	 

	public Kss_SwfzModel(){
	}
	public Kss_SwfzModel(String id) {
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
	public java.lang.String getTgxsbm() {
		return this.tgxsbm;
	}
	
	public void setTgxsbm(java.lang.String value) {
		this.tgxsbm = value;
	}
	
	public java.util.Date getTgrq() {
		return this.tgrq;
	}
	
	public void setTgrq(java.util.Date value) {
		this.tgrq = value;
	}
	
	public java.lang.String getXslx() {
		return this.xslx;
	}
	
	public void setXslx(java.lang.String value) {
		this.xslx = value;
	}
	public java.lang.String getZyrxm() {
		return this.zyrxm;
	}
	
	public void setZyrxm(java.lang.String value) {
		this.zyrxm = value;
	}
	public java.lang.String getXb() {
		return this.xb;
	}
	
	public void setXb(java.lang.String value) {
		this.xb = value;
	}
	public java.lang.String getAy() {
		return this.ay;
	}
	
	public void setAy(java.lang.String value) {
		this.ay = value;
	}
	public java.lang.String getGycs() {
		return this.gycs;
	}
	
	public void setGycs(java.lang.String value) {
		this.gycs = value;
	}
	public java.lang.String getXsnrzy() {
		return this.xsnrzy;
	}
	
	public void setXsnrzy(java.lang.String value) {
		this.xsnrzy = value;
	}
	
	public java.util.Date getSdxsrq() {
		return this.sdxsrq;
	}
	
	public void setSdxsrq(java.util.Date value) {
		this.sdxsrq = value;
	}
	
	public java.lang.String getTjbm() {
		return this.tjbm;
	}
	
	public void setTjbm(java.lang.String value) {
		this.tjbm = value;
	}
	
	public java.util.Date getTjsj() {
		return this.tjsj;
	}
	
	public void setTjsj(java.util.Date value) {
		this.tjsj = value;
	}
	
	public java.lang.String getCzqk() {
		return this.czqk;
	}
	
	public void setCzqk(java.lang.String value) {
		this.czqk = value;
	}
	public java.lang.String getCyca() {
		return this.cyca;
	}
	
	public void setCyca(java.lang.String value) {
		this.cyca = value;
	}
	public java.lang.String getLadw() {
		return this.ladw;
	}
	
	public void setLadw(java.lang.String value) {
		this.ladw = value;
	}
	public java.lang.String getLah() {
		return this.lah;
	}
	
	public void setLah(java.lang.String value) {
		this.lah = value;
	}
	public java.lang.String getYzhbjjrjzhrq() {
		return this.yzhbjjrjzhrq;
	}
	
	public void setYzhbjjrjzhrq(java.lang.String value) {
		this.yzhbjjrjzhrq = value;
	}
	public java.lang.String getCzcs() {
		return this.czcs;
	}
	
	public void setCzcs(java.lang.String value) {
		this.czcs = value;
	}
	public java.lang.String getFlzmws() {
		return this.flzmws;
	}
	
	public void setFlzmws(java.lang.String value) {
		this.flzmws = value;
	}
	public java.lang.String getPadw() {
		return this.padw;
	}
	
	public void setPadw(java.lang.String value) {
		this.padw = value;
	}
	
	public java.util.Date getParq() {
		return this.parq;
	}
	
	public void setParq(java.util.Date value) {
		this.parq = value;
	}
	
	public java.lang.String getPabh() {
		return this.pabh;
	}
	
	public void setPabh(java.lang.String value) {
		this.pabh = value;
	}
	public java.lang.String getCbdw() {
		return this.cbdw;
	}
	
	public void setCbdw(java.lang.String value) {
		this.cbdw = value;
	}
	public java.lang.String getClqk() {
		return this.clqk;
	}
	
	public void setClqk(java.lang.String value) {
		this.clqk = value;
	}
	public java.lang.String getSfycd() {
		return this.sfycd;
	}
	
	public void setSfycd(java.lang.String value) {
		this.sfycd = value;
	}
	public java.lang.String getSfycr() {
		return this.sfycr;
	}
	
	public void setSfycr(java.lang.String value) {
		this.sfycr = value;
	}
	public java.lang.String getWlcx() {
		return this.wlcx;
	}
	
	public void setWlcx(java.lang.String value) {
		this.wlcx = value;
	}
	public java.lang.String getCydk() {
		return this.cydk;
	}
	
	public void setCydk(java.lang.String value) {
		this.cydk = value;
	}
	public java.lang.String getSdfc() {
		return this.sdfc;
	}
	
	public void setSdfc(java.lang.String value) {
		this.sdfc = value;
	}
	public java.lang.String getQknr() {
		return this.qknr;
	}
	
	public void setQknr(java.lang.String value) {
		this.qknr = value;
	}
	public java.lang.String getCzbm() {
		return this.czbm;
	}
	
	public void setCzbm(java.lang.String value) {
		this.czbm = value;
	}
	
	public java.util.Date getFksj() {
		return this.fksj;
	}
	
	public void setFksj(java.util.Date value) {
		this.fksj = value;
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
	
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	 
}

