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
public class QkfyModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String jsh;

	private String fyr;

	private String fyrbh;

	private String bfydx;

	private String bfydxbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fysj;

	private String wgqk;

	private String xxwgqk;

	private String sfyycqk;

	private String ycqk;

	private String xxycqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date wgsj;

	private String zscs;

	private String zsr;

	private String fdcs;

	private String fdr;

	private String yxzt;

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



	public QkfyModel(){
	}
	public QkfyModel(String id) {
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
	public String getFyr() {
		return this.fyr;
	}

	public void setFyr(String value) {
		this.fyr = value;
	}
	public String getFyrbh() {
		return this.fyrbh;
	}

	public void setFyrbh(String value) {
		this.fyrbh = value;
	}
	public String getBfydx() {
		return this.bfydx;
	}

	public void setBfydx(String value) {
		this.bfydx = value;
	}
	public String getBfydxbh() {
		return this.bfydxbh;
	}

	public void setBfydxbh(String value) {
		this.bfydxbh = value;
	}

	public java.util.Date getFysj() {
		return this.fysj;
	}

	public void setFysj(java.util.Date value) {
		this.fysj = value;
	}

	public String getWgqk() {
		return this.wgqk;
	}

	public void setWgqk(String value) {
		this.wgqk = value;
	}
	public String getXxwgqk() {
		return this.xxwgqk;
	}

	public void setXxwgqk(String value) {
		this.xxwgqk = value;
	}
	public String getSfyycqk() {
		return this.sfyycqk;
	}

	public void setSfyycqk(String value) {
		this.sfyycqk = value;
	}
	public String getYcqk() {
		return this.ycqk;
	}

	public void setYcqk(String value) {
		this.ycqk = value;
	}
	public String getXxycqk() {
		return this.xxycqk;
	}

	public void setXxycqk(String value) {
		this.xxycqk = value;
	}

	public java.util.Date getWgsj() {
		return this.wgsj;
	}

	public void setWgsj(java.util.Date value) {
		this.wgsj = value;
	}

	public String getZscs() {
		return this.zscs;
	}

	public void setZscs(String value) {
		this.zscs = value;
	}
	public String getZsr() {
		return this.zsr;
	}

	public void setZsr(String value) {
		this.zsr = value;
	}
	public String getFdcs() {
		return this.fdcs;
	}

	public void setFdcs(String value) {
		this.fdcs = value;
	}
	public String getFdr() {
		return this.fdr;
	}

	public void setFdr(String value) {
		this.fdr = value;
	}
	public String getYxzt() {
		return this.yxzt;
	}

	public void setYxzt(String value) {
		this.yxzt = value;
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

