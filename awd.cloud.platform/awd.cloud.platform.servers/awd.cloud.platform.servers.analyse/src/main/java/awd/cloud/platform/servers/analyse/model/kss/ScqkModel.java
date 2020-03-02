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
public class ScqkModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date rq;

	private String tt;

	private String ldxm;

	private String ldzw;

	private String cy;

	private String jdr;

	private String scnr;

	private String yjjy;

	private String zgqk;

	private String jlr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jlrq;

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



	public ScqkModel(){
	}
	public ScqkModel(String id) {
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

	public java.util.Date getRq() {
		return this.rq;
	}

	public void setRq(java.util.Date value) {
		this.rq = value;
	}

	public String getTt() {
		return this.tt;
	}

	public void setTt(String value) {
		this.tt = value;
	}
	public String getLdxm() {
		return this.ldxm;
	}

	public void setLdxm(String value) {
		this.ldxm = value;
	}
	public String getLdzw() {
		return this.ldzw;
	}

	public void setLdzw(String value) {
		this.ldzw = value;
	}
	public String getCy() {
		return this.cy;
	}

	public void setCy(String value) {
		this.cy = value;
	}
	public String getJdr() {
		return this.jdr;
	}

	public void setJdr(String value) {
		this.jdr = value;
	}
	public String getScnr() {
		return this.scnr;
	}

	public void setScnr(String value) {
		this.scnr = value;
	}
	public String getYjjy() {
		return this.yjjy;
	}

	public void setYjjy(String value) {
		this.yjjy = value;
	}
	public String getZgqk() {
		return this.zgqk;
	}

	public void setZgqk(String value) {
		this.zgqk = value;
	}
	public String getJlr() {
		return this.jlr;
	}

	public void setJlr(String value) {
		this.jlr = value;
	}

	public java.util.Date getJlrq() {
		return this.jlrq;
	}

	public void setJlrq(java.util.Date value) {
		this.jlrq = value;
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

