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
public class ZbdjModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zbrq;

	private String sld;

	private String sy;

	private String ts;

	private String xkzb;

	private String xkzhb;

	private String xkwb;

	private String yszb;

	private String yszhb;

	private String yswb;

	private String gj;

	private String zkzb;

	private String zkzhb;

	private String zkwb;

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

	private String week;
	//columns END



	public ZbdjModel(){
	}
	public ZbdjModel(String id) {
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

	public java.util.Date getZbrq() {
		return this.zbrq;
	}

	public void setZbrq(java.util.Date value) {
		this.zbrq = value;
	}

	public String getSld() {
		return this.sld;
	}

	public void setSld(String value) {
		this.sld = value;
	}
	public String getSy() {
		return this.sy;
	}

	public void setSy(String value) {
		this.sy = value;
	}
	public String getTs() {
		return this.ts;
	}

	public void setTs(String value) {
		this.ts = value;
	}
	public String getXkzb() {
		return this.xkzb;
	}

	public void setXkzb(String value) {
		this.xkzb = value;
	}
	public String getXkzhb() {
		return this.xkzhb;
	}

	public void setXkzhb(String value) {
		this.xkzhb = value;
	}
	public String getXkwb() {
		return this.xkwb;
	}

	public void setXkwb(String value) {
		this.xkwb = value;
	}
	public String getYszb() {
		return this.yszb;
	}

	public void setYszb(String value) {
		this.yszb = value;
	}
	public String getYszhb() {
		return this.yszhb;
	}

	public void setYszhb(String value) {
		this.yszhb = value;
	}
	public String getYswb() {
		return this.yswb;
	}

	public void setYswb(String value) {
		this.yswb = value;
	}
	public String getGj() {
		return this.gj;
	}

	public void setGj(String value) {
		this.gj = value;
	}
	public String getZkzb() {
		return this.zkzb;
	}

	public void setZkzb(String value) {
		this.zkzb = value;
	}
	public String getZkzhb() {
		return this.zkzhb;
	}

	public void setZkzhb(String value) {
		this.zkzhb = value;
	}
	public String getZkwb() {
		return this.zkwb;
	}

	public void setZkwb(String value) {
		this.zkwb = value;
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

	public String getWeek() {
		return this.week;
	}

	public void setWeek(String value) {
		this.week = value;
	}
	 
}

