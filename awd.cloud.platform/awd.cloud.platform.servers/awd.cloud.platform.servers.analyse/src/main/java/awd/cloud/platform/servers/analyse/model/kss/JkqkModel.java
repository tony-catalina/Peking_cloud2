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
public class JkqkModel implements Model {

	
	//columns START
	
	private String id;


	private String gcbh;

	private String jsbh;

	private String zjh;

	private String jclx;

	private String xx;

	private String xy;

	private String xl;

	private String tw;

	private String xcg;

	private String xdt;

	private String bc;

	private String zq;

	private String xp;

	private String yy;

	private String xzzk;

	private String jkzk;

	private String jbll;

	private String gms;

	private String brbs;

	private String jtbs;

	private String crb;

	private String bhlx;

	private String sbq;

	private String zsbw;

	private String zsrqyyy;

	private String ylnxrsjc;

	private String sdrydj;

	private String azbryjc;

	private String tnbryjc;

	private String lxbjc;

	private String wxys;

	private String tbsqyy;

	private String lyr;

	private String jcys;

	private String ysyj;

	private String cqcs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ysjcrq;

	private String bz;

	private String sjzljsbz;

	private String sfyjs;

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

	private String sg;

	private String tz;

	private String zc;

	private String tb;

	private String jb;

	private String xb;

	private String fb;

	private String bb;

	private String db;

	private String sz;

	private String xz;

	private String xds;
	//columns END



	public JkqkModel(){
	}
	public JkqkModel(String id) {
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
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getZjh() {
		return this.zjh;
	}

	public void setZjh(String value) {
		this.zjh = value;
	}
	public String getJclx() {
		return this.jclx;
	}

	public void setJclx(String value) {
		this.jclx = value;
	}
	public String getXx() {
		return this.xx;
	}

	public void setXx(String value) {
		this.xx = value;
	}
	public String getXy() {
		return this.xy;
	}

	public void setXy(String value) {
		this.xy = value;
	}
	public String getXl() {
		return this.xl;
	}

	public void setXl(String value) {
		this.xl = value;
	}
	public String getTw() {
		return this.tw;
	}

	public void setTw(String value) {
		this.tw = value;
	}
	public String getXcg() {
		return this.xcg;
	}

	public void setXcg(String value) {
		this.xcg = value;
	}
	public String getXdt() {
		return this.xdt;
	}

	public void setXdt(String value) {
		this.xdt = value;
	}
	public String getBc() {
		return this.bc;
	}

	public void setBc(String value) {
		this.bc = value;
	}
	public String getZq() {
		return this.zq;
	}

	public void setZq(String value) {
		this.zq = value;
	}
	public String getXp() {
		return this.xp;
	}

	public void setXp(String value) {
		this.xp = value;
	}
	public String getYy() {
		return this.yy;
	}

	public void setYy(String value) {
		this.yy = value;
	}
	public String getXzzk() {
		return this.xzzk;
	}

	public void setXzzk(String value) {
		this.xzzk = value;
	}
	public String getJkzk() {
		return this.jkzk;
	}

	public void setJkzk(String value) {
		this.jkzk = value;
	}
	public String getJbll() {
		return this.jbll;
	}

	public void setJbll(String value) {
		this.jbll = value;
	}
	public String getGms() {
		return this.gms;
	}

	public void setGms(String value) {
		this.gms = value;
	}
	public String getBrbs() {
		return this.brbs;
	}

	public void setBrbs(String value) {
		this.brbs = value;
	}
	public String getJtbs() {
		return this.jtbs;
	}

	public void setJtbs(String value) {
		this.jtbs = value;
	}
	public String getCrb() {
		return this.crb;
	}

	public void setCrb(String value) {
		this.crb = value;
	}
	public String getBhlx() {
		return this.bhlx;
	}

	public void setBhlx(String value) {
		this.bhlx = value;
	}
	public String getSbq() {
		return this.sbq;
	}

	public void setSbq(String value) {
		this.sbq = value;
	}
	public String getZsbw() {
		return this.zsbw;
	}

	public void setZsbw(String value) {
		this.zsbw = value;
	}
	public String getZsrqyyy() {
		return this.zsrqyyy;
	}

	public void setZsrqyyy(String value) {
		this.zsrqyyy = value;
	}
	public String getYlnxrsjc() {
		return this.ylnxrsjc;
	}

	public void setYlnxrsjc(String value) {
		this.ylnxrsjc = value;
	}
	public String getSdrydj() {
		return this.sdrydj;
	}

	public void setSdrydj(String value) {
		this.sdrydj = value;
	}
	public String getAzbryjc() {
		return this.azbryjc;
	}

	public void setAzbryjc(String value) {
		this.azbryjc = value;
	}
	public String getTnbryjc() {
		return this.tnbryjc;
	}

	public void setTnbryjc(String value) {
		this.tnbryjc = value;
	}
	public String getLxbjc() {
		return this.lxbjc;
	}

	public void setLxbjc(String value) {
		this.lxbjc = value;
	}
	public String getWxys() {
		return this.wxys;
	}

	public void setWxys(String value) {
		this.wxys = value;
	}
	public String getTbsqyy() {
		return this.tbsqyy;
	}

	public void setTbsqyy(String value) {
		this.tbsqyy = value;
	}
	public String getLyr() {
		return this.lyr;
	}

	public void setLyr(String value) {
		this.lyr = value;
	}
	public String getJcys() {
		return this.jcys;
	}

	public void setJcys(String value) {
		this.jcys = value;
	}
	public String getYsyj() {
		return this.ysyj;
	}

	public void setYsyj(String value) {
		this.ysyj = value;
	}
	public String getCqcs() {
		return this.cqcs;
	}

	public void setCqcs(String value) {
		this.cqcs = value;
	}

	public java.util.Date getYsjcrq() {
		return this.ysjcrq;
	}

	public void setYsjcrq(java.util.Date value) {
		this.ysjcrq = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getSjzljsbz() {
		return this.sjzljsbz;
	}

	public void setSjzljsbz(String value) {
		this.sjzljsbz = value;
	}
	public String getSfyjs() {
		return this.sfyjs;
	}

	public void setSfyjs(String value) {
		this.sfyjs = value;
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

	public String getSg() {
		return this.sg;
	}

	public void setSg(String value) {
		this.sg = value;
	}
	public String getTz() {
		return this.tz;
	}

	public void setTz(String value) {
		this.tz = value;
	}
	public String getZc() {
		return this.zc;
	}

	public void setZc(String value) {
		this.zc = value;
	}
	public String getTb() {
		return this.tb;
	}

	public void setTb(String value) {
		this.tb = value;
	}
	public String getJb() {
		return this.jb;
	}

	public void setJb(String value) {
		this.jb = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getFb() {
		return this.fb;
	}

	public void setFb(String value) {
		this.fb = value;
	}
	public String getBb() {
		return this.bb;
	}

	public void setBb(String value) {
		this.bb = value;
	}
	public String getDb() {
		return this.db;
	}

	public void setDb(String value) {
		this.db = value;
	}
	public String getSz() {
		return this.sz;
	}

	public void setSz(String value) {
		this.sz = value;
	}
	public String getXz() {
		return this.xz;
	}

	public void setXz(String value) {
		this.xz = value;
	}
	public String getXds() {
		return this.xds;
	}

	public void setXds(String value) {
		this.xds = value;
	}
	 
}

