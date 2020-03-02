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


public class Kss_KssModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String kssmc;
	
	private java.lang.String kssmcpy;
	
	private java.lang.String dz;
	
	private java.lang.String dq;
	
	private java.lang.String dh;
	
	private java.lang.String cz;
	
	private java.lang.String yzbm;
	
	private java.lang.String email;
	
	private java.lang.String net;
	
	private java.lang.String ldxm;
	
	private java.lang.String dj;
	
	private java.lang.String gm;
	
	private java.lang.Short rs;
	
	private java.lang.Short bzrs;
	
	private java.lang.Short jss;
	
	private java.lang.Short rl;
	
	private java.lang.String version;
	
	private java.lang.String kssjc;
	
	private java.lang.String enable;
	
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
	
	private java.lang.String maxsnbh;
	
	private java.lang.Short xjnum;
	
	private java.lang.Short zgnum;
	
	private java.lang.Short wznum;
	
	private java.lang.String syjb;
	
	private java.lang.String jsxzjb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qysj;
	
	private java.lang.Short drjsnum;
	
	private java.math.BigDecimal zjzmj;
	
	private java.math.BigDecimal jqmj;
	
	private java.lang.String bqszxs;
	
	private java.lang.Short zswjnum;
	
	private java.lang.String ssxs;
	
	private java.lang.String abdoor;
	
	private java.lang.Short bhfcrj;
	
	private java.lang.Short xwsnum;
	
	private java.lang.Short lshjsnum;
	
	private java.lang.Short jshjsnum;
	
	private java.lang.Short doctornum;
	
	private java.lang.Short nursenum;
	
	private java.lang.String longitude;
	
	private java.lang.String latitude;
	
	private byte[] gatephoto;
	//columns END

	 

	public Kss_KssModel(){
	}
	public Kss_KssModel(String id) {
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
	public java.lang.String getKssmc() {
		return this.kssmc;
	}
	
	public void setKssmc(java.lang.String value) {
		this.kssmc = value;
	}
	public java.lang.String getKssmcpy() {
		return this.kssmcpy;
	}
	
	public void setKssmcpy(java.lang.String value) {
		this.kssmcpy = value;
	}
	public java.lang.String getDz() {
		return this.dz;
	}
	
	public void setDz(java.lang.String value) {
		this.dz = value;
	}
	public java.lang.String getDq() {
		return this.dq;
	}
	
	public void setDq(java.lang.String value) {
		this.dq = value;
	}
	public java.lang.String getDh() {
		return this.dh;
	}
	
	public void setDh(java.lang.String value) {
		this.dh = value;
	}
	public java.lang.String getCz() {
		return this.cz;
	}
	
	public void setCz(java.lang.String value) {
		this.cz = value;
	}
	public java.lang.String getYzbm() {
		return this.yzbm;
	}
	
	public void setYzbm(java.lang.String value) {
		this.yzbm = value;
	}
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	public java.lang.String getNet() {
		return this.net;
	}
	
	public void setNet(java.lang.String value) {
		this.net = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getDj() {
		return this.dj;
	}
	
	public void setDj(java.lang.String value) {
		this.dj = value;
	}
	public java.lang.String getGm() {
		return this.gm;
	}
	
	public void setGm(java.lang.String value) {
		this.gm = value;
	}
	public java.lang.Short getRs() {
		return this.rs;
	}
	
	public void setRs(java.lang.Short value) {
		this.rs = value;
	}
	public java.lang.Short getBzrs() {
		return this.bzrs;
	}
	
	public void setBzrs(java.lang.Short value) {
		this.bzrs = value;
	}
	public java.lang.Short getJss() {
		return this.jss;
	}
	
	public void setJss(java.lang.Short value) {
		this.jss = value;
	}
	public java.lang.Short getRl() {
		return this.rl;
	}
	
	public void setRl(java.lang.Short value) {
		this.rl = value;
	}
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	public java.lang.String getKssjc() {
		return this.kssjc;
	}
	
	public void setKssjc(java.lang.String value) {
		this.kssjc = value;
	}
	public java.lang.String getEnable() {
		return this.enable;
	}
	
	public void setEnable(java.lang.String value) {
		this.enable = value;
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
	
	public java.lang.String getMaxsnbh() {
		return this.maxsnbh;
	}
	
	public void setMaxsnbh(java.lang.String value) {
		this.maxsnbh = value;
	}
	public java.lang.Short getXjnum() {
		return this.xjnum;
	}
	
	public void setXjnum(java.lang.Short value) {
		this.xjnum = value;
	}
	public java.lang.Short getZgnum() {
		return this.zgnum;
	}
	
	public void setZgnum(java.lang.Short value) {
		this.zgnum = value;
	}
	public java.lang.Short getWznum() {
		return this.wznum;
	}
	
	public void setWznum(java.lang.Short value) {
		this.wznum = value;
	}
	public java.lang.String getSyjb() {
		return this.syjb;
	}
	
	public void setSyjb(java.lang.String value) {
		this.syjb = value;
	}
	public java.lang.String getJsxzjb() {
		return this.jsxzjb;
	}
	
	public void setJsxzjb(java.lang.String value) {
		this.jsxzjb = value;
	}
	
	public java.util.Date getJssj() {
		return this.jssj;
	}
	
	public void setJssj(java.util.Date value) {
		this.jssj = value;
	}
	
	
	public java.util.Date getQysj() {
		return this.qysj;
	}
	
	public void setQysj(java.util.Date value) {
		this.qysj = value;
	}
	
	public java.lang.Short getDrjsnum() {
		return this.drjsnum;
	}
	
	public void setDrjsnum(java.lang.Short value) {
		this.drjsnum = value;
	}
	public java.math.BigDecimal getZjzmj() {
		return this.zjzmj;
	}
	
	public void setZjzmj(java.math.BigDecimal value) {
		this.zjzmj = value;
	}
	public java.math.BigDecimal getJqmj() {
		return this.jqmj;
	}
	
	public void setJqmj(java.math.BigDecimal value) {
		this.jqmj = value;
	}
	public java.lang.String getBqszxs() {
		return this.bqszxs;
	}
	
	public void setBqszxs(java.lang.String value) {
		this.bqszxs = value;
	}
	public java.lang.Short getZswjnum() {
		return this.zswjnum;
	}
	
	public void setZswjnum(java.lang.Short value) {
		this.zswjnum = value;
	}
	public java.lang.String getSsxs() {
		return this.ssxs;
	}
	
	public void setSsxs(java.lang.String value) {
		this.ssxs = value;
	}
	public java.lang.String getAbdoor() {
		return this.abdoor;
	}
	
	public void setAbdoor(java.lang.String value) {
		this.abdoor = value;
	}
	public java.lang.Short getBhfcrj() {
		return this.bhfcrj;
	}
	
	public void setBhfcrj(java.lang.Short value) {
		this.bhfcrj = value;
	}
	public java.lang.Short getXwsnum() {
		return this.xwsnum;
	}
	
	public void setXwsnum(java.lang.Short value) {
		this.xwsnum = value;
	}
	public java.lang.Short getLshjsnum() {
		return this.lshjsnum;
	}
	
	public void setLshjsnum(java.lang.Short value) {
		this.lshjsnum = value;
	}
	public java.lang.Short getJshjsnum() {
		return this.jshjsnum;
	}
	
	public void setJshjsnum(java.lang.Short value) {
		this.jshjsnum = value;
	}
	public java.lang.Short getDoctornum() {
		return this.doctornum;
	}
	
	public void setDoctornum(java.lang.Short value) {
		this.doctornum = value;
	}
	public java.lang.Short getNursenum() {
		return this.nursenum;
	}
	
	public void setNursenum(java.lang.Short value) {
		this.nursenum = value;
	}
	public java.lang.String getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(java.lang.String value) {
		this.longitude = value;
	}
	public java.lang.String getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(java.lang.String value) {
		this.latitude = value;
	}
	public byte[] getGatephoto() {
		return this.gatephoto;
	}
	
	public void setGatephoto(byte[] value) {
		this.gatephoto = value;
	}
	 
}

