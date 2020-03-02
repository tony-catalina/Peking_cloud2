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
public class WpglModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String wpjsid;

	private String wpmc;

	private java.math.BigDecimal sl;

	private String xh;

	private String tz;

	private String lqzt;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lqrq;

	private String bz;

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



	public WpglModel(){
	}
	public WpglModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getWpjsid() {
		return this.wpjsid;
	}

	public void setWpjsid(String value) {
		this.wpjsid = value;
	}
	public String getWpmc() {
		return this.wpmc;
	}

	public void setWpmc(String value) {
		this.wpmc = value;
	}
	public java.math.BigDecimal getSl() {
		return this.sl;
	}

	public void setSl(java.math.BigDecimal value) {
		this.sl = value;
	}
	public String getXh() {
		return this.xh;
	}

	public void setXh(String value) {
		this.xh = value;
	}
	public String getTz() {
		return this.tz;
	}

	public void setTz(String value) {
		this.tz = value;
	}
	public String getLqzt() {
		return this.lqzt;
	}

	public void setLqzt(String value) {
		this.lqzt = value;
	}

	public java.util.Date getLqrq() {
		return this.lqrq;
	}

	public void setLqrq(java.util.Date value) {
		this.lqrq = value;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String value) {
		this.bz = value;
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

