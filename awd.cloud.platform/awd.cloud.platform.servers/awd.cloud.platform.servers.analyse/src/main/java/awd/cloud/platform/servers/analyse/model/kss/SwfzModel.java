/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.servers.analyse.model.kss;

import awd.framework.common.model.Model;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SwfzModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String tgxsbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tgrq;

	private String xslx;

	private String zyrxm;

	private String xb;

	private String ay;

	private String gycs;

	private String xsnrzy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sdxsrq;

	private String tjbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date tjsj;

	private String czqk;

	private String cyca;

	private String ladw;

	private String lah;

	private String yzhbjjrjzhrq;

	private String czcs;

	private String flzmws;

	private String padw;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date parq;

	private String pabh;

	private String cbdw;

	private String clqk;

	private String sfycd;

	private String sfycr;

	private String wlcx;

	private String cydk;

	private String sdfc;

	private String qknr;

	private String czbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fksj;

	private String bz;

	private String state;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String pastable;

	private String ywlcid;
	//columns END



	public SwfzModel(){
	}
	public SwfzModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getTgxsbm() {
		return this.tgxsbm;
	}

	public void setTgxsbm(String value) {
		this.tgxsbm = value;
	}

	public java.util.Date getTgrq() {
		return this.tgrq;
	}

	public void setTgrq(java.util.Date value) {
		this.tgrq = value;
	}

	public String getXslx() {
		return this.xslx;
	}

	public void setXslx(String value) {
		this.xslx = value;
	}
	public String getZyrxm() {
		return this.zyrxm;
	}

	public void setZyrxm(String value) {
		this.zyrxm = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getAy() {
		return this.ay;
	}

	public void setAy(String value) {
		this.ay = value;
	}
	public String getGycs() {
		return this.gycs;
	}

	public void setGycs(String value) {
		this.gycs = value;
	}
	public String getXsnrzy() {
		return this.xsnrzy;
	}

	public void setXsnrzy(String value) {
		this.xsnrzy = value;
	}

	public java.util.Date getSdxsrq() {
		return this.sdxsrq;
	}

	public void setSdxsrq(java.util.Date value) {
		this.sdxsrq = value;
	}

	public String getTjbm() {
		return this.tjbm;
	}

	public void setTjbm(String value) {
		this.tjbm = value;
	}

	public java.util.Date getTjsj() {
		return this.tjsj;
	}

	public void setTjsj(java.util.Date value) {
		this.tjsj = value;
	}

	public String getCzqk() {
		return this.czqk;
	}

	public void setCzqk(String value) {
		this.czqk = value;
	}
	public String getCyca() {
		return this.cyca;
	}

	public void setCyca(String value) {
		this.cyca = value;
	}
	public String getLadw() {
		return this.ladw;
	}

	public void setLadw(String value) {
		this.ladw = value;
	}
	public String getLah() {
		return this.lah;
	}

	public void setLah(String value) {
		this.lah = value;
	}
	public String getYzhbjjrjzhrq() {
		return this.yzhbjjrjzhrq;
	}

	public void setYzhbjjrjzhrq(String value) {
		this.yzhbjjrjzhrq = value;
	}
	public String getCzcs() {
		return this.czcs;
	}

	public void setCzcs(String value) {
		this.czcs = value;
	}
	public String getFlzmws() {
		return this.flzmws;
	}

	public void setFlzmws(String value) {
		this.flzmws = value;
	}
	public String getPadw() {
		return this.padw;
	}

	public void setPadw(String value) {
		this.padw = value;
	}

	public java.util.Date getParq() {
		return this.parq;
	}

	public void setParq(java.util.Date value) {
		this.parq = value;
	}

	public String getPabh() {
		return this.pabh;
	}

	public void setPabh(String value) {
		this.pabh = value;
	}
	public String getCbdw() {
		return this.cbdw;
	}

	public void setCbdw(String value) {
		this.cbdw = value;
	}
	public String getClqk() {
		return this.clqk;
	}

	public void setClqk(String value) {
		this.clqk = value;
	}
	public String getSfycd() {
		return this.sfycd;
	}

	public void setSfycd(String value) {
		this.sfycd = value;
	}
	public String getSfycr() {
		return this.sfycr;
	}

	public void setSfycr(String value) {
		this.sfycr = value;
	}
	public String getWlcx() {
		return this.wlcx;
	}

	public void setWlcx(String value) {
		this.wlcx = value;
	}
	public String getCydk() {
		return this.cydk;
	}

	public void setCydk(String value) {
		this.cydk = value;
	}
	public String getSdfc() {
		return this.sdfc;
	}

	public void setSdfc(String value) {
		this.sdfc = value;
	}
	public String getQknr() {
		return this.qknr;
	}

	public void setQknr(String value) {
		this.qknr = value;
	}
	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String value) {
		this.czbm = value;
	}

	public java.util.Date getFksj() {
		return this.fksj;
	}

	public void setFksj(java.util.Date value) {
		this.fksj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String value) {
		this.updator = value;
	}

	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}

	public String getPastable() {
		return this.pastable;
	}

	public void setPastable(String value) {
		this.pastable = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	 
}

