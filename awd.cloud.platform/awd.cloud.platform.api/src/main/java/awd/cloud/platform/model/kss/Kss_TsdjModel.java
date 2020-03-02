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


public class Kss_TsdjModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String dw;
	
	private java.lang.String baryy;
	
	private java.lang.String barye;
	
	private java.lang.String zjh1;
	
	private java.lang.String zjh2;
	
	private java.lang.String qtbar;
	
	private java.lang.String qtzjh;
	
	private java.lang.String wcnjhr;
	
	private java.lang.String wcndlr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date kssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jssj;
	
	private java.lang.String tszbr;
	
	private java.lang.String dcmj;
	
	private java.lang.String jsr;
	
	private java.lang.String ywshwjwpqk;
	
	private java.lang.String yccon;
	
	private java.lang.String flag;
	
	private java.lang.String pastable;
	
	private java.lang.String bllx;
	
	private java.lang.String sjzlJsbz;
	
	private java.lang.String ywlcid;
	
	private java.lang.String tss;
	
	private java.lang.String dgnxwsycqk;
	
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
	
	private java.lang.String txsy;
	
	private java.lang.String txlx;
	
	private java.lang.String jcr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jcsj;
	
	private java.lang.String jcjg;
	
	private java.lang.String wjwpdj;
	
	private java.lang.String kysqdj;
	
	private java.lang.String zbmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date txrq;
	
	private java.lang.String lfrzjh;
	
	private java.lang.String lfrpzh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lfsj;
	
	private java.lang.String lxdh;
	
	private java.lang.String lfrxm;
	
	private java.lang.String txry;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date txsj;
	//columns END

	 

	public Kss_TsdjModel(){
	}
	public Kss_TsdjModel(String id) {
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
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	public java.lang.String getBaryy() {
		return this.baryy;
	}
	
	public void setBaryy(java.lang.String value) {
		this.baryy = value;
	}
	public java.lang.String getBarye() {
		return this.barye;
	}
	
	public void setBarye(java.lang.String value) {
		this.barye = value;
	}
	public java.lang.String getZjh1() {
		return this.zjh1;
	}
	
	public void setZjh1(java.lang.String value) {
		this.zjh1 = value;
	}
	public java.lang.String getZjh2() {
		return this.zjh2;
	}
	
	public void setZjh2(java.lang.String value) {
		this.zjh2 = value;
	}
	public java.lang.String getQtbar() {
		return this.qtbar;
	}
	
	public void setQtbar(java.lang.String value) {
		this.qtbar = value;
	}
	public java.lang.String getQtzjh() {
		return this.qtzjh;
	}
	
	public void setQtzjh(java.lang.String value) {
		this.qtzjh = value;
	}
	public java.lang.String getWcnjhr() {
		return this.wcnjhr;
	}
	
	public void setWcnjhr(java.lang.String value) {
		this.wcnjhr = value;
	}
	public java.lang.String getWcndlr() {
		return this.wcndlr;
	}
	
	public void setWcndlr(java.lang.String value) {
		this.wcndlr = value;
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
	
	public java.lang.String getTszbr() {
		return this.tszbr;
	}
	
	public void setTszbr(java.lang.String value) {
		this.tszbr = value;
	}
	public java.lang.String getDcmj() {
		return this.dcmj;
	}
	
	public void setDcmj(java.lang.String value) {
		this.dcmj = value;
	}
	public java.lang.String getJsr() {
		return this.jsr;
	}
	
	public void setJsr(java.lang.String value) {
		this.jsr = value;
	}
	public java.lang.String getYwshwjwpqk() {
		return this.ywshwjwpqk;
	}
	
	public void setYwshwjwpqk(java.lang.String value) {
		this.ywshwjwpqk = value;
	}
	public java.lang.String getYccon() {
		return this.yccon;
	}
	
	public void setYccon(java.lang.String value) {
		this.yccon = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
	}
	public java.lang.String getPastable() {
		return this.pastable;
	}
	
	public void setPastable(java.lang.String value) {
		this.pastable = value;
	}
	public java.lang.String getBllx() {
		return this.bllx;
	}
	
	public void setBllx(java.lang.String value) {
		this.bllx = value;
	}
	public java.lang.String getSjzlJsbz() {
		return this.sjzlJsbz;
	}
	
	public void setSjzlJsbz(java.lang.String value) {
		this.sjzlJsbz = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getTss() {
		return this.tss;
	}
	
	public void setTss(java.lang.String value) {
		this.tss = value;
	}
	public java.lang.String getDgnxwsycqk() {
		return this.dgnxwsycqk;
	}
	
	public void setDgnxwsycqk(java.lang.String value) {
		this.dgnxwsycqk = value;
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
	
	public java.lang.String getTxsy() {
		return this.txsy;
	}
	
	public void setTxsy(java.lang.String value) {
		this.txsy = value;
	}
	public java.lang.String getTxlx() {
		return this.txlx;
	}
	
	public void setTxlx(java.lang.String value) {
		this.txlx = value;
	}
	public java.lang.String getJcr() {
		return this.jcr;
	}
	
	public void setJcr(java.lang.String value) {
		this.jcr = value;
	}
	
	public java.util.Date getJcsj() {
		return this.jcsj;
	}
	
	public void setJcsj(java.util.Date value) {
		this.jcsj = value;
	}
	
	public java.lang.String getJcjg() {
		return this.jcjg;
	}
	
	public void setJcjg(java.lang.String value) {
		this.jcjg = value;
	}
	public java.lang.String getWjwpdj() {
		return this.wjwpdj;
	}
	
	public void setWjwpdj(java.lang.String value) {
		this.wjwpdj = value;
	}
	public java.lang.String getKysqdj() {
		return this.kysqdj;
	}
	
	public void setKysqdj(java.lang.String value) {
		this.kysqdj = value;
	}
	public java.lang.String getZbmj() {
		return this.zbmj;
	}
	
	public void setZbmj(java.lang.String value) {
		this.zbmj = value;
	}
	
	public java.util.Date getDjsj() {
		return this.djsj;
	}
	
	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}
	
	
	public java.util.Date getTxrq() {
		return this.txrq;
	}
	
	public void setTxrq(java.util.Date value) {
		this.txrq = value;
	}
	
	public java.lang.String getLfrzjh() {
		return this.lfrzjh;
	}
	
	public void setLfrzjh(java.lang.String value) {
		this.lfrzjh = value;
	}
	public java.lang.String getLfrpzh() {
		return this.lfrpzh;
	}
	
	public void setLfrpzh(java.lang.String value) {
		this.lfrpzh = value;
	}
	
	public java.util.Date getLfsj() {
		return this.lfsj;
	}
	
	public void setLfsj(java.util.Date value) {
		this.lfsj = value;
	}
	
	public java.lang.String getLxdh() {
		return this.lxdh;
	}
	
	public void setLxdh(java.lang.String value) {
		this.lxdh = value;
	}
	public java.lang.String getLfrxm() {
		return this.lfrxm;
	}
	
	public void setLfrxm(java.lang.String value) {
		this.lfrxm = value;
	}
	public java.lang.String getTxry() {
		return this.txry;
	}
	
	public void setTxry(java.lang.String value) {
		this.txry = value;
	}
	
	public java.util.Date getTxsj() {
		return this.txsj;
	}
	
	public void setTxsj(java.util.Date value) {
		this.txsj = value;
	}
	
	 
}

