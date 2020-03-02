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
public class XyrModel implements Model {
	
	//columns START
	
	private String id;


	private String gcbh;

	private String wbrybh;

	private String dah;

	private String jsbh;

	private String xm;

	private String xmpy;

	private String bm;

	private String bmty;

	private String mz;

	private String gj;

	private String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;

	private String zjlx;

	private String zjh;

	private String zzmm;

	private String hyzk;

	private String zuc;

	private String sg;

	private String jg;

	private String hjd;

	private String hjdxz;

	private String xzd;

	private String xzdxz;

	private String whcd;

	private String zc;

	private String sf;

	private String tssf;

	private String zy;

	private String gzdw;

	private String jkzk;

	private String bhlx;

	private String azb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rsrq;

	private String rsxz;

	private String zrdw;

	private String sydw;

	private String syr;

	private String sy;

	private String byzd;

	private String sypzwsh;

	private String sypz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jyrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyqx;

	private String ay;

	private String xhay;

	private String fzjl;

	private String jyaq;

	private String caaj;

	private String cylx;

	private String bar;

	private String barjh;

	private String bahj;

	private String bardh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zzqsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dbrq;

	private String badw;

	private String dwlx;

	private String syrkid;

	private String czzt;

	private String jsly;

	private String aqdj;

	private String sfyxjslx;

	private String state;

	private String creator;

	private String creatorjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String zwbh;

	private String bz;

	private String ywlcid;
	//columns END



	public XyrModel(){
	}
	public XyrModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getGcbh() {
		return this.gcbh;
	}

	public void setGcbh(String value) {
		this.gcbh = value;
	}
	public String getWbrybh() {
		return this.wbrybh;
	}

	public void setWbrybh(String value) {
		this.wbrybh = value;
	}
	public String getDah() {
		return this.dah;
	}

	public void setDah(String value) {
		this.dah = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getXmpy() {
		return this.xmpy;
	}

	public void setXmpy(String value) {
		this.xmpy = value;
	}
	public String getBm() {
		return this.bm;
	}

	public void setBm(String value) {
		this.bm = value;
	}
	public String getBmty() {
		return this.bmty;
	}

	public void setBmty(String value) {
		this.bmty = value;
	}
	public String getMz() {
		return this.mz;
	}

	public void setMz(String value) {
		this.mz = value;
	}
	public String getGj() {
		return this.gj;
	}

	public void setGj(String value) {
		this.gj = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}

	public java.util.Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}

	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String value) {
		this.zzmm = value;
	}
	public String getHyzk() {
		return this.hyzk;
	}

	public void setHyzk(String value) {
		this.hyzk = value;
	}
	public String getZuc() {
		return this.zuc;
	}

	public void setZuc(String value) {
		this.zuc = value;
	}
	public String getSg() {
		return this.sg;
	}

	public void setSg(String value) {
		this.sg = value;
	}
	public String getJg() {
		return this.jg;
	}

	public void setJg(String value) {
		this.jg = value;
	}
	public String getHjd() {
		return this.hjd;
	}

	public void setHjd(String value) {
		this.hjd = value;
	}
	public String getHjdxz() {
		return this.hjdxz;
	}

	public void setHjdxz(String value) {
		this.hjdxz = value;
	}
	public String getXzd() {
		return this.xzd;
	}

	public void setXzd(String value) {
		this.xzd = value;
	}
	public String getXzdxz() {
		return this.xzdxz;
	}

	public void setXzdxz(String value) {
		this.xzdxz = value;
	}
	public String getWhcd() {
		return this.whcd;
	}

	public void setWhcd(String value) {
		this.whcd = value;
	}
	public String getZc() {
		return this.zc;
	}

	public void setZc(String value) {
		this.zc = value;
	}
	public String getSf() {
		return this.sf;
	}

	public void setSf(String value) {
		this.sf = value;
	}
	public String getTssf() {
		return this.tssf;
	}

	public void setTssf(String value) {
		this.tssf = value;
	}
	public String getZy() {
		return this.zy;
	}

	public void setZy(String value) {
		this.zy = value;
	}
	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String value) {
		this.gzdw = value;
	}
	public String getJkzk() {
		return this.jkzk;
	}

	public void setJkzk(String value) {
		this.jkzk = value;
	}
	public String getBhlx() {
		return this.bhlx;
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}
	public String getAzb() {
		return this.azb;
	}

	public void setAzb(String value) {
		this.azb = value;
	}

	public java.util.Date getRsrq() {
		return this.rsrq;
	}

	public void setRsrq(java.util.Date value) {
		this.rsrq = value;
	}

	public String getRsxz() {
		return this.rsxz;
	}

	public void setRsxz(String value) {
		this.rsxz = value;
	}
	public String getZrdw() {
		return this.zrdw;
	}

	public void setZrdw(String value) {
		this.zrdw = value;
	}
	public String getSydw() {
		return this.sydw;
	}

	public void setSydw(String value) {
		this.sydw = value;
	}
	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String value) {
		this.syr = value;
	}
	public String getSy() {
		return this.sy;
	}

	public void setSy(String value) {
		this.sy = value;
	}
	public String getByzd() {
		return this.byzd;
	}

	public void setByzd(String value) {
		this.byzd = value;
	}
	public String getSypzwsh() {
		return this.sypzwsh;
	}

	public void setSypzwsh(String value) {
		this.sypzwsh = value;
	}
	public String getSypz() {
		return this.sypz;
	}

	public void setSypz(String value) {
		this.sypz = value;
	}

	public java.util.Date getJyrq() {
		return this.jyrq;
	}

	public void setJyrq(java.util.Date value) {
		this.jyrq = value;
	}


	public java.util.Date getGyqx() {
		return this.gyqx;
	}

	public void setGyqx(java.util.Date value) {
		this.gyqx = value;
	}

	public String getAy() {
		return this.ay;
	}

	public void setAy(String value) {
		this.ay = value;
	}
	public String getXhay() {
		return this.xhay;
	}

	public void setXhay(String value) {
		this.xhay = value;
	}
	public String getFzjl() {
		return this.fzjl;
	}

	public void setFzjl(String value) {
		this.fzjl = value;
	}
	public String getJyaq() {
		return this.jyaq;
	}

	public void setJyaq(String value) {
		this.jyaq = value;
	}
	public String getCaaj() {
		return this.caaj;
	}

	public void setCaaj(String value) {
		this.caaj = value;
	}
	public String getCylx() {
		return this.cylx;
	}

	public void setCylx(String value) {
		this.cylx = value;
	}
	public String getBar() {
		return this.bar;
	}

	public void setBar(String value) {
		this.bar = value;
	}
	public String getBarjh() {
		return this.barjh;
	}

	public void setBarjh(String value) {
		this.barjh = value;
	}
	public String getBahj() {
		return this.bahj;
	}

	public void setBahj(String value) {
		this.bahj = value;
	}
	public String getBardh() {
		return this.bardh;
	}

	public void setBardh(String value) {
		this.bardh = value;
	}

	public java.util.Date getZzqsrq() {
		return this.zzqsrq;
	}

	public void setZzqsrq(java.util.Date value) {
		this.zzqsrq = value;
	}


	public java.util.Date getJlrq() {
		return this.jlrq;
	}

	public void setJlrq(java.util.Date value) {
		this.jlrq = value;
	}


	public java.util.Date getDbrq() {
		return this.dbrq;
	}

	public void setDbrq(java.util.Date value) {
		this.dbrq = value;
	}

	public String getBadw() {
		return this.badw;
	}

	public void setBadw(String value) {
		this.badw = value;
	}
	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String value) {
		this.dwlx = value;
	}
	public String getSyrkid() {
		return this.syrkid;
	}

	public void setSyrkid(String value) {
		this.syrkid = value;
	}
	public String getCzzt() {
		return this.czzt;
	}

	public void setCzzt(String value) {
		this.czzt = value;
	}
	public String getJsly() {
		return this.jsly;
	}

	public void setJsly(String value) {
		this.jsly = value;
	}
	public String getAqdj() {
		return this.aqdj;
	}

	public void setAqdj(String value) {
		this.aqdj = value;
	}
	public String getSfyxjslx() {
		return this.sfyxjslx;
	}

	public void setSfyxjslx(String value) {
		this.sfyxjslx = value;
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
	public String getCreatorjh() {
		return this.creatorjh;
	}

	public void setCreatorjh(String value) {
		this.creatorjh = value;
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

	public String getZwbh() {
		return this.zwbh;
	}

	public void setZwbh(String value) {
		this.zwbh = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	 
}

