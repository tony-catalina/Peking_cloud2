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


public class Kss_JkqkModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String gcbh;
	
	private java.lang.String jsbh;
	
	private java.lang.String zjh;
	
	private java.lang.String jclx;
	
	private java.lang.String xx;
	
	private java.lang.String xy;
	
	private java.lang.String xl;
	
	private java.lang.String tw;
	
	private java.lang.String xcg;
	
	private java.lang.String xdt;
	
	private java.lang.String bc;
	
	private java.lang.String zq;
	
	private java.lang.String xp;
	
	private java.lang.String ky;
	
	private java.lang.String yy;
	
	private java.lang.String xzzk;
	
	private java.lang.String jkzk;
	
	private java.lang.String jbll;
	
	private java.lang.String gms;
	
	private java.lang.String brbs;
	
	private java.lang.String jtbs;
	
	private java.lang.String crb;
	
	private java.lang.String bhlx;
	
	private java.lang.String sbq;
	
	private java.lang.String zsbw;
	
	private java.lang.String zsrqyyy;
	
	private java.lang.String ylnxrsjc;
	
	private java.lang.String sdrydj;
	
	private java.lang.String azbryjc;
	
	private java.lang.String tnbryjc;
	
	private java.lang.String lxbjc;
	
	private java.lang.String wxys;
	
	private java.lang.String tbsqyy;
	
	private java.lang.String lyr;
	
	private java.lang.String jcys;
	
	private java.lang.String ysyj;
	
	private java.lang.String cqcs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ysjcrq;
	
	private java.lang.String bz;
	
	private java.lang.String sjzljsbz;
	
	private java.lang.String sfyjs;
	
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
	
	private java.lang.String xds;
	
	private java.lang.String sss;
	
	private java.lang.String zszz;
	
	private java.lang.String tbssqk;
	
	private java.lang.String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;
	
	private java.lang.String ldxm;
	
	private java.lang.String tsqksy;
	
	private java.lang.String ldpsbz;
	
	private java.lang.String ywlcid;
	
	private java.lang.String tbtsbz;
	
	private java.lang.String tx;
	
	private java.lang.String lx;
	
	private java.lang.String xf;
	
	private java.lang.String fz;
	//columns END

	 

	public Kss_JkqkModel(){
	}
	public Kss_JkqkModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getGcbh() {
		return this.gcbh;
	}
	
	public void setGcbh(java.lang.String value) {
		this.gcbh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getZjh() {
		return this.zjh;
	}
	
	public void setZjh(java.lang.String value) {
		this.zjh = value;
	}
	public java.lang.String getJclx() {
		return this.jclx;
	}
	
	public void setJclx(java.lang.String value) {
		this.jclx = value;
	}
	public java.lang.String getXx() {
		return this.xx;
	}
	
	public void setXx(java.lang.String value) {
		this.xx = value;
	}
	public java.lang.String getXy() {
		return this.xy;
	}
	
	public void setXy(java.lang.String value) {
		this.xy = value;
	}
	public java.lang.String getXl() {
		return this.xl;
	}
	
	public void setXl(java.lang.String value) {
		this.xl = value;
	}
	public java.lang.String getTw() {
		return this.tw;
	}
	
	public void setTw(java.lang.String value) {
		this.tw = value;
	}
	public java.lang.String getXcg() {
		return this.xcg;
	}
	
	public void setXcg(java.lang.String value) {
		this.xcg = value;
	}
	public java.lang.String getXdt() {
		return this.xdt;
	}
	
	public void setXdt(java.lang.String value) {
		this.xdt = value;
	}
	public java.lang.String getBc() {
		return this.bc;
	}
	
	public void setBc(java.lang.String value) {
		this.bc = value;
	}
	public java.lang.String getZq() {
		return this.zq;
	}
	
	public void setZq(java.lang.String value) {
		this.zq = value;
	}
	public java.lang.String getXp() {
		return this.xp;
	}
	
	public void setXp(java.lang.String value) {
		this.xp = value;
	}
	public java.lang.String getKy() {
		return this.ky;
	}
	
	public void setKy(java.lang.String value) {
		this.ky = value;
	}
	public java.lang.String getYy() {
		return this.yy;
	}
	
	public void setYy(java.lang.String value) {
		this.yy = value;
	}
	public java.lang.String getXzzk() {
		return this.xzzk;
	}
	
	public void setXzzk(java.lang.String value) {
		this.xzzk = value;
	}
	public java.lang.String getJkzk() {
		return this.jkzk;
	}
	
	public void setJkzk(java.lang.String value) {
		this.jkzk = value;
	}
	public java.lang.String getJbll() {
		return this.jbll;
	}
	
	public void setJbll(java.lang.String value) {
		this.jbll = value;
	}
	public java.lang.String getGms() {
		return this.gms;
	}
	
	public void setGms(java.lang.String value) {
		this.gms = value;
	}
	public java.lang.String getBrbs() {
		return this.brbs;
	}
	
	public void setBrbs(java.lang.String value) {
		this.brbs = value;
	}
	public java.lang.String getJtbs() {
		return this.jtbs;
	}
	
	public void setJtbs(java.lang.String value) {
		this.jtbs = value;
	}
	public java.lang.String getCrb() {
		return this.crb;
	}
	
	public void setCrb(java.lang.String value) {
		this.crb = value;
	}
	public java.lang.String getBhlx() {
		return this.bhlx;
	}
	
	public void setBhlx(java.lang.String value) {
		this.bhlx = value;
	}
	public java.lang.String getSbq() {
		return this.sbq;
	}
	
	public void setSbq(java.lang.String value) {
		this.sbq = value;
	}
	public java.lang.String getZsbw() {
		return this.zsbw;
	}
	
	public void setZsbw(java.lang.String value) {
		this.zsbw = value;
	}
	public java.lang.String getZsrqyyy() {
		return this.zsrqyyy;
	}
	
	public void setZsrqyyy(java.lang.String value) {
		this.zsrqyyy = value;
	}
	public java.lang.String getYlnxrsjc() {
		return this.ylnxrsjc;
	}
	
	public void setYlnxrsjc(java.lang.String value) {
		this.ylnxrsjc = value;
	}
	public java.lang.String getSdrydj() {
		return this.sdrydj;
	}
	
	public void setSdrydj(java.lang.String value) {
		this.sdrydj = value;
	}
	public java.lang.String getAzbryjc() {
		return this.azbryjc;
	}
	
	public void setAzbryjc(java.lang.String value) {
		this.azbryjc = value;
	}
	public java.lang.String getTnbryjc() {
		return this.tnbryjc;
	}
	
	public void setTnbryjc(java.lang.String value) {
		this.tnbryjc = value;
	}
	public java.lang.String getLxbjc() {
		return this.lxbjc;
	}
	
	public void setLxbjc(java.lang.String value) {
		this.lxbjc = value;
	}
	public java.lang.String getWxys() {
		return this.wxys;
	}
	
	public void setWxys(java.lang.String value) {
		this.wxys = value;
	}
	public java.lang.String getTbsqyy() {
		return this.tbsqyy;
	}
	
	public void setTbsqyy(java.lang.String value) {
		this.tbsqyy = value;
	}
	public java.lang.String getLyr() {
		return this.lyr;
	}
	
	public void setLyr(java.lang.String value) {
		this.lyr = value;
	}
	public java.lang.String getJcys() {
		return this.jcys;
	}
	
	public void setJcys(java.lang.String value) {
		this.jcys = value;
	}
	public java.lang.String getYsyj() {
		return this.ysyj;
	}
	
	public void setYsyj(java.lang.String value) {
		this.ysyj = value;
	}
	public java.lang.String getCqcs() {
		return this.cqcs;
	}
	
	public void setCqcs(java.lang.String value) {
		this.cqcs = value;
	}
	
	public java.util.Date getYsjcrq() {
		return this.ysjcrq;
	}
	
	public void setYsjcrq(java.util.Date value) {
		this.ysjcrq = value;
	}
	
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getSjzljsbz() {
		return this.sjzljsbz;
	}
	
	public void setSjzljsbz(java.lang.String value) {
		this.sjzljsbz = value;
	}
	public java.lang.String getSfyjs() {
		return this.sfyjs;
	}
	
	public void setSfyjs(java.lang.String value) {
		this.sfyjs = value;
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
	public java.lang.String getXds() {
		return this.xds;
	}
	
	public void setXds(java.lang.String value) {
		this.xds = value;
	}
	public java.lang.String getSss() {
		return this.sss;
	}
	
	public void setSss(java.lang.String value) {
		this.sss = value;
	}
	public java.lang.String getZszz() {
		return this.zszz;
	}
	
	public void setZszz(java.lang.String value) {
		this.zszz = value;
	}
	public java.lang.String getTbssqk() {
		return this.tbssqk;
	}
	
	public void setTbssqk(java.lang.String value) {
		this.tbssqk = value;
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
	
	public java.lang.String getLdxm() {
		return this.ldxm;
	}
	
	public void setLdxm(java.lang.String value) {
		this.ldxm = value;
	}
	public java.lang.String getTsqksy() {
		return this.tsqksy;
	}
	
	public void setTsqksy(java.lang.String value) {
		this.tsqksy = value;
	}
	public java.lang.String getLdpsbz() {
		return this.ldpsbz;
	}
	
	public void setLdpsbz(java.lang.String value) {
		this.ldpsbz = value;
	}
	public java.lang.String getYwlcid() {
		return this.ywlcid;
	}
	
	public void setYwlcid(java.lang.String value) {
		this.ywlcid = value;
	}
	public java.lang.String getTbtsbz() {
		return this.tbtsbz;
	}
	
	public void setTbtsbz(java.lang.String value) {
		this.tbtsbz = value;
	}
	public java.lang.String getTx() {
		return this.tx;
	}
	
	public void setTx(java.lang.String value) {
		this.tx = value;
	}
	public java.lang.String getLx() {
		return this.lx;
	}
	
	public void setLx(java.lang.String value) {
		this.lx = value;
	}
	public java.lang.String getXf() {
		return this.xf;
	}
	
	public void setXf(java.lang.String value) {
		this.xf = value;
	}
	public java.lang.String getFz() {
		return this.fz;
	}
	
	public void setFz(java.lang.String value) {
		this.fz = value;
	}
	 
}

