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
public class WmjsModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String jsh;

	private String ly;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djrq;

	private String rs;

	private String jzyj;

	private String bllx;

	private String blr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blrq;

	private String ldxm;

	private String ldyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ldpssj;

	private String bz;

	private String psbz;

	private String state;

	private String ywlcid;

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
	//columns END



	public WmjsModel(){
	}
	public WmjsModel(String id) {
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
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getLy() {
		return this.ly;
	}

	public void setLy(String value) {
		this.ly = value;
	}

	public java.util.Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(java.util.Date value) {
		this.djrq = value;
	}

	public String getRs() {
		return this.rs;
	}

	public void setRs(String value) {
		this.rs = value;
	}
	public String getJzyj() {
		return this.jzyj;
	}

	public void setJzyj(String value) {
		this.jzyj = value;
	}
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}
	public String getBlr() {
		return this.blr;
	}

	public void setBlr(String value) {
		this.blr = value;
	}

	public java.util.Date getBlrq() {
		return this.blrq;
	}

	public void setBlrq(java.util.Date value) {
		this.blrq = value;
	}

	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getLdyj() {
		return this.ldyj;
	}

	public void setLdyj(String value) {
		this.ldyj = value;
	}

	public java.util.Date getLdpssj() {
		return this.ldpssj;
	}

	public void setLdpssj(java.util.Date value) {
		this.ldpssj = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
	}
	public String getPsbz() {
		return this.psbz;
	}

	public void setPsbz(String value) {
		this.psbz = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
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
	 
}

