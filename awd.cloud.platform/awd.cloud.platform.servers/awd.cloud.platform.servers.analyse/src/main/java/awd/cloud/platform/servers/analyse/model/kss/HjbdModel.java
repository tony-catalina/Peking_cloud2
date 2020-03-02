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

import java.util.Date;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class HjbdModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String sfslaj;

	private String sfjdwm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bdrq;

	private String bahj;

	private String yssjd;

	private String dwlx;

	private String badw;

	private String bar;

	private String bardh;

	private String ybadwlx;

	private String ybadw;

	private String ybar;

	private String ybardh;

	private String wsh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date gyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yjyqx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qsrq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zzrq;

	private String wspzlx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wssdrq;

	private String pzdw;

	private String bz;

	private String ywlcsyid;

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


	/**
	 * 传递开始结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;


//columns END



	public HjbdModel(){
	}
	public HjbdModel(String id) {
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
	public String getSfslaj() {
		return this.sfslaj;
	}

	public void setSfslaj(String value) {
		this.sfslaj = value;
	}
	public String getSfjdwm() {
		return this.sfjdwm;
	}

	public void setSfjdwm(String value) {
		this.sfjdwm = value;
	}

	public java.util.Date getBdrq() {
		return this.bdrq;
	}

	public void setBdrq(java.util.Date value) {
		this.bdrq = value;
	}

	public String getBahj() {
		return this.bahj;
	}

	public void setBahj(String value) {
		this.bahj = value;
	}
	public String getYssjd() {
		return this.yssjd;
	}

	public void setYssjd(String value) {
		this.yssjd = value;
	}
	public String getDwlx() {
		return this.dwlx;
	}

	public void setDwlx(String value) {
		this.dwlx = value;
	}
	public String getBadw() {
		return this.badw;
	}

	public void setBadw(String value) {
		this.badw = value;
	}
	public String getBar() {
		return this.bar;
	}

	public void setBar(String value) {
		this.bar = value;
	}
	public String getBardh() {
		return this.bardh;
	}

	public void setBardh(String value) {
		this.bardh = value;
	}
	public String getYbadwlx() {
		return this.ybadwlx;
	}

	public void setYbadwlx(String value) {
		this.ybadwlx = value;
	}
	public String getYbadw() {
		return this.ybadw;
	}

	public void setYbadw(String value) {
		this.ybadw = value;
	}
	public String getYbar() {
		return this.ybar;
	}

	public void setYbar(String value) {
		this.ybar = value;
	}
	public String getYbardh() {
		return this.ybardh;
	}

	public void setYbardh(String value) {
		this.ybardh = value;
	}
	public String getWsh() {
		return this.wsh;
	}

	public void setWsh(String value) {
		this.wsh = value;
	}

	public java.util.Date getGyqx() {
		return this.gyqx;
	}

	public void setGyqx(java.util.Date value) {
		this.gyqx = value;
	}


	public java.util.Date getYjyqx() {
		return this.yjyqx;
	}

	public void setYjyqx(java.util.Date value) {
		this.yjyqx = value;
	}


	public java.util.Date getQsrq() {
		return this.qsrq;
	}

	public void setQsrq(java.util.Date value) {
		this.qsrq = value;
	}


	public java.util.Date getZzrq() {
		return this.zzrq;
	}

	public void setZzrq(java.util.Date value) {
		this.zzrq = value;
	}

	public String getWspzlx() {
		return this.wspzlx;
	}

	public void setWspzlx(String value) {
		this.wspzlx = value;
	}

	public java.util.Date getWssdrq() {
		return this.wssdrq;
	}

	public void setWssdrq(java.util.Date value) {
		this.wssdrq = value;
	}

	public String getPzdw() {
		return this.pzdw;
	}

	public void setPzdw(String value) {
		this.pzdw = value;
	}
	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getYwlcsyid() {
		return this.ywlcsyid;
	}

	public void setYwlcsyid(String value) {
		this.ywlcsyid = value;
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

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	 
}

