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


public class Kss_LscsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;
	
	private java.lang.String lrmj;
	
	private java.lang.String csyy;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hssj;
	
	private java.lang.String pzr;
	
	private java.lang.String badw;
	
	private java.lang.String dcmj;
	
	private java.lang.String sjmj;
	
	private java.lang.String ldxm;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String wczt;
	
	private java.lang.String ywlcid;
	
	private java.lang.String psbz;
	
	private java.lang.String pastable;
	
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
	
	private java.lang.String tbjcqk;
	
	private java.lang.String wjpjcqk;
	
	private java.lang.String jbryj;
	
	private java.lang.String csdriver;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ndcssj;
	
	private java.lang.String hsdriver;
	
	private java.lang.String csqx;
	
	private java.lang.String sldyjnr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csjbrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hsjbrq;
	
	private java.lang.String flwsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbsj;
	
	private java.lang.String csqrr;
	
	private java.lang.String hsajr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hsajsj;
	
	private java.lang.String ajqkjl;
	
	private java.lang.String lsajr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lsajsj;
	
	private java.lang.String lsajycqk;
	
	private java.lang.String lsajjg;
	
	private java.lang.String bbjlh;
	
	private java.lang.String hsdjr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hsdjsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lssj;
	
	private java.lang.String lsdjr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lsdjsj;
	
	private java.lang.String yjmj;
	//columns END

	 

	public Kss_LscsModel(){
	}
	public Kss_LscsModel(String id) {
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
	
	public java.util.Date getBlsj() {
		return this.blsj;
	}
	
	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}
	
	public java.lang.String getLrmj() {
		return this.lrmj;
	}
	
	public void setLrmj(java.lang.String value) {
		this.lrmj = value;
	}
	public java.lang.String getCsyy() {
		return this.csyy;
	}
	
	public void setCsyy(java.lang.String value) {
		this.csyy = value;
	}
	
	public java.util.Date getCssj() {
		return this.cssj;
	}
	
	public void setCssj(java.util.Date value) {
		this.cssj = value;
	}
	
	
	public java.util.Date getHssj() {
		return this.hssj;
	}
	
	public void setHssj(java.util.Date value) {
		this.hssj = value;
	}
	
	public java.lang.String getPzr() {
		return this.pzr;
	}
	
	public void setPzr(java.lang.String value) {
		this.pzr = value;
	}
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	public java.lang.String getDcmj() {
		return this.dcmj;
	}
	
	public void setDcmj(java.lang.String value) {
		this.dcmj = value;
	}
	public java.lang.String getSjmj() {
		return this.sjmj;
	}
	
	public void setSjmj(java.lang.String value) {
		this.sjmj = value;
	}
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getLdyj() {
		return this.ldyj;
	}
	
	public void setLdyj(java.lang.String value) {
		this.ldyj = value;
	}
	
	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}
	
	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}
	
	public java.lang.String getWczt() {
		return this.wczt;
	}
	
	public void setWczt(java.lang.String value) {
		this.wczt = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getPsbz() {
		return this.psbz;
	}
	
	public void setPsbz(java.lang.String value) {
		this.psbz = value;
	}
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
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
	public java.lang.String getJbryj() {
		return this.jbryj;
	}
	
	public void setJbryj(java.lang.String value) {
		this.jbryj = value;
	}
	public java.lang.String getCsdriver() {
		return this.csdriver;
	}
	
	public void setCsdriver(java.lang.String value) {
		this.csdriver = value;
	}
	
	public java.util.Date getNdcssj() {
		return this.ndcssj;
	}
	
	public void setNdcssj(java.util.Date value) {
		this.ndcssj = value;
	}
	
	public java.lang.String getHsdriver() {
		return this.hsdriver;
	}
	
	public void setHsdriver(java.lang.String value) {
		this.hsdriver = value;
	}
	public java.lang.String getCsqx() {
		return this.csqx;
	}
	
	public void setCsqx(java.lang.String value) {
		this.csqx = value;
	}
	public java.lang.String getSldyjnr() {
		return this.sldyjnr;
	}
	
	public void setSldyjnr(java.lang.String value) {
		this.sldyjnr = value;
	}
	
	public java.util.Date getCsjbrq() {
		return this.csjbrq;
	}
	
	public void setCsjbrq(java.util.Date value) {
		this.csjbrq = value;
	}
	
	
	public java.util.Date getHsjbrq() {
		return this.hsjbrq;
	}
	
	public void setHsjbrq(java.util.Date value) {
		this.hsjbrq = value;
	}
	
	public java.lang.String getFlwsh() {
		return this.flwsh;
	}
	
	public void setFlwsh(java.lang.String value) {
		this.flwsh = value;
	}
	
	public java.util.Date getJbsj() {
		return this.jbsj;
	}
	
	public void setJbsj(java.util.Date value) {
		this.jbsj = value;
	}
	
	public java.lang.String getCsqrr() {
		return this.csqrr;
	}
	
	public void setCsqrr(java.lang.String value) {
		this.csqrr = value;
	}
	public java.lang.String getHsajr() {
		return this.hsajr;
	}
	
	public void setHsajr(java.lang.String value) {
		this.hsajr = value;
	}
	
	public java.util.Date getHsajsj() {
		return this.hsajsj;
	}
	
	public void setHsajsj(java.util.Date value) {
		this.hsajsj = value;
	}
	
	public java.lang.String getAjqkjl() {
		return this.ajqkjl;
	}
	
	public void setAjqkjl(java.lang.String value) {
		this.ajqkjl = value;
	}
	public java.lang.String getLsajr() {
		return this.lsajr;
	}
	
	public void setLsajr(java.lang.String value) {
		this.lsajr = value;
	}
	
	public java.util.Date getLsajsj() {
		return this.lsajsj;
	}
	
	public void setLsajsj(java.util.Date value) {
		this.lsajsj = value;
	}
	
	public java.lang.String getLsajycqk() {
		return this.lsajycqk;
	}
	
	public void setLsajycqk(java.lang.String value) {
		this.lsajycqk = value;
	}
	public java.lang.String getLsajjg() {
		return this.lsajjg;
	}
	
	public void setLsajjg(java.lang.String value) {
		this.lsajjg = value;
	}
	public java.lang.String getBbjlh() {
		return this.bbjlh;
	}
	
	public void setBbjlh(java.lang.String value) {
		this.bbjlh = value;
	}
	public java.lang.String getHsdjr() {
		return this.hsdjr;
	}
	
	public void setHsdjr(java.lang.String value) {
		this.hsdjr = value;
	}
	
	public java.util.Date getHsdjsj() {
		return this.hsdjsj;
	}
	
	public void setHsdjsj(java.util.Date value) {
		this.hsdjsj = value;
	}
	
	
	public java.util.Date getLssj() {
		return this.lssj;
	}
	
	public void setLssj(java.util.Date value) {
		this.lssj = value;
	}
	
	public java.lang.String getLsdjr() {
		return this.lsdjr;
	}
	
	public void setLsdjr(java.lang.String value) {
		this.lsdjr = value;
	}
	
	public java.util.Date getLsdjsj() {
		return this.lsdjsj;
	}
	
	public void setLsdjsj(java.util.Date value) {
		this.lsdjsj = value;
	}
	
	public java.lang.String getYjmj() {
		return this.yjmj;
	}
	
	public void setYjmj(java.lang.String value) {
		this.yjmj = value;
	}
	 
}

