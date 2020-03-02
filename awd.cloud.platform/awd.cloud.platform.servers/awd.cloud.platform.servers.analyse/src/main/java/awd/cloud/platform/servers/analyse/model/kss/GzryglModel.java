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
public class GzryglModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String zgxm;

	private String xmpy;

	private String mz;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csny;

	private String zy;

	private String zjlx;

	private String zjh;

	private String zzmm;

	private String gw;

	private String jtzz;

	private String hjszd;

	private String lxdh;

	private String sjh;

	private String xl;

	private String yszyzbh;

	private String yszgzbh;

	private String sfqzys;

	private String flag;

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
	//columns END



	public GzryglModel(){
	}
	public GzryglModel(String id) {
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
	public String getZgxm() {
		return this.zgxm;
	}

	public void setZgxm(String value) {
		this.zgxm = value;
	}
	public String getXmpy() {
		return this.xmpy;
	}

	public void setXmpy(String value) {
		this.xmpy = value;
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

	public String getZy() {
		return this.zy;
	}

	public void setZy(String value) {
		this.zy = value;
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
	public String getGw() {
		return this.gw;
	}

	public void setGw(String value) {
		this.gw = value;
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
	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String value) {
		this.lxdh = value;
	}
	public String getSjh() {
		return this.sjh;
	}

	public void setSjh(String value) {
		this.sjh = value;
	}
	public String getXl() {
		return this.xl;
	}

	public void setXl(String value) {
		this.xl = value;
	}
	public String getYszyzbh() {
		return this.yszyzbh;
	}

	public void setYszyzbh(String value) {
		this.yszyzbh = value;
	}
	public String getYszgzbh() {
		return this.yszgzbh;
	}

	public void setYszgzbh(String value) {
		this.yszgzbh = value;
	}
	public String getSfqzys() {
		return this.sfqzys;
	}

	public void setSfqzys(String value) {
		this.sfqzys = value;
	}
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String value) {
		this.flag = value;
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
	
	 
}

