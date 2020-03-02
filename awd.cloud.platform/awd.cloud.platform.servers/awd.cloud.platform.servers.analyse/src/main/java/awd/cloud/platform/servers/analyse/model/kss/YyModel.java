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
public class YyModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String yylx;

	private String yydw;

	private String yydwdh;

	private String yyrsjh;

	private String yyr;

	private String zjlx;

	private String zjh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date yyrq;

	private String yysjd;

	private String yyyy;

	private String lrr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lrsj;

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



	public YyModel(){
	}
	public YyModel(String id) {
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
	public String getYylx() {
		return this.yylx;
	}

	public void setYylx(String value) {
		this.yylx = value;
	}
	public String getYydw() {
		return this.yydw;
	}

	public void setYydw(String value) {
		this.yydw = value;
	}
	public String getYydwdh() {
		return this.yydwdh;
	}

	public void setYydwdh(String value) {
		this.yydwdh = value;
	}
	public String getYyrsjh() {
		return this.yyrsjh;
	}

	public void setYyrsjh(String value) {
		this.yyrsjh = value;
	}
	public String getYyr() {
		return this.yyr;
	}

	public void setYyr(String value) {
		this.yyr = value;
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

	public java.util.Date getYyrq() {
		return this.yyrq;
	}

	public void setYyrq(java.util.Date value) {
		this.yyrq = value;
	}

	public String getYysjd() {
		return this.yysjd;
	}

	public void setYysjd(String value) {
		this.yysjd = value;
	}
	public String getYyyy() {
		return this.yyyy;
	}

	public void setYyyy(String value) {
		this.yyyy = value;
	}
	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String value) {
		this.lrr = value;
	}

	public java.util.Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(java.util.Date value) {
		this.lrsj = value;
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

