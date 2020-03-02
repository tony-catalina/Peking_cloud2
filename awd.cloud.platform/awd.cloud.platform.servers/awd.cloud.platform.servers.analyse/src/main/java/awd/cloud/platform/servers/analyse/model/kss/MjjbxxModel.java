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
public class MjjbxxModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String jh;

	private String sfzh;

	private String xm;

	private String xb;

	private String mz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csny;

	private String jx;

	private String xl;

	private String zzmm;

	private String zpurl;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cjgmgzsj;

	private String hyzk;

	private String jtzz;

	private String hjszd;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date cjgzsj;

	private String bm;

	private String bmzw;

	private String gbzwjb;

	private String lxdh;

	private String xlzxs;

	private String sjh;

	private String flag;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lkrq;

	private String lkyy;

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

	private String xmpy;
	//columns END



	public MjjbxxModel(){
	}
	public MjjbxxModel(String id) {
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
	public String getJh() {
		return this.jh;
	}

	public void setJh(String value) {
		this.jh = value;
	}
	public String getSfzh() {
		return this.sfzh;
	}

	public void setSfzh(String value) {
		this.sfzh = value;
	}
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getMz() {
		return this.mz;
	}

	public void setMz(String value) {
		this.mz = value;
	}

	public java.util.Date getCsny() {
		return this.csny;
	}

	public void setCsny(java.util.Date value) {
		this.csny = value;
	}

	public String getJx() {
		return this.jx;
	}

	public void setJx(String value) {
		this.jx = value;
	}
	public String getXl() {
		return this.xl;
	}

	public void setXl(String value) {
		this.xl = value;
	}
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String value) {
		this.zzmm = value;
	}
	public String getZpurl() {
		return this.zpurl;
	}

	public void setZpurl(String value) {
		this.zpurl = value;
	}

	public java.util.Date getCjgmgzsj() {
		return this.cjgmgzsj;
	}

	public void setCjgmgzsj(java.util.Date value) {
		this.cjgmgzsj = value;
	}

	public String getHyzk() {
		return this.hyzk;
	}

	public void setHyzk(String value) {
		this.hyzk = value;
	}
	public String getJtzz() {
		return this.jtzz;
	}

	public void setJtzz(String value) {
		this.jtzz = value;
	}
	public String getHjszd() {
		return this.hjszd;
	}

	public void setHjszd(String value) {
		this.hjszd = value;
	}

	public java.util.Date getCjgzsj() {
		return this.cjgzsj;
	}

	public void setCjgzsj(java.util.Date value) {
		this.cjgzsj = value;
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String value) {
		this.bm = value;
	}
	public String getBmzw() {
		return this.bmzw;
	}

	public void setBmzw(String value) {
		this.bmzw = value;
	}
	public String getGbzwjb() {
		return this.gbzwjb;
	}

	public void setGbzwjb(String value) {
		this.gbzwjb = value;
	}
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String value) {
		this.lxdh = value;
	}
	public String getXlzxs() {
		return this.xlzxs;
	}

	public void setXlzxs(String value) {
		this.xlzxs = value;
	}
	public String getSjh() {
		return this.sjh;
	}

	public void setSjh(String value) {
		this.sjh = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
	}

	public java.util.Date getLkrq() {
		return this.lkrq;
	}

	public void setLkrq(java.util.Date value) {
		this.lkrq = value;
	}

	public String getLkyy() {
		return this.lkyy;
	}

	public void setLkyy(String value) {
		this.lkyy = value;
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

	public String getXmpy() {
		return this.xmpy;
	}

	public void setXmpy(String value) {
		this.xmpy = value;
	}
	 
}

