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
public class SxsphjModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String hjly;

	private String sqr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sqsj;

	private String hjrid;

	private String hjdd;

	private String ltzh;

	private String fkfs;

	private String fkdx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fkrq;

	private String fkqk;

	private String fkr;

	private String ywlcid;

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



	public SxsphjModel(){
	}
	public SxsphjModel(String id) {
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
	public String getHjly() {
		return this.hjly;
	}

	public void setHjly(String value) {
		this.hjly = value;
	}
	public String getSqr() {
		return this.sqr;
	}

	public void setSqr(String value) {
		this.sqr = value;
	}

	public java.util.Date getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(java.util.Date value) {
		this.sqsj = value;
	}

	public String getHjrid() {
		return this.hjrid;
	}

	public void setHjrid(String value) {
		this.hjrid = value;
	}
	public String getHjdd() {
		return this.hjdd;
	}

	public void setHjdd(String value) {
		this.hjdd = value;
	}
	public String getLtzh() {
		return this.ltzh;
	}

	public void setLtzh(String value) {
		this.ltzh = value;
	}
	public String getFkfs() {
		return this.fkfs;
	}

	public void setFkfs(String value) {
		this.fkfs = value;
	}
	public String getFkdx() {
		return this.fkdx;
	}

	public void setFkdx(String value) {
		this.fkdx = value;
	}

	public java.util.Date getFkrq() {
		return this.fkrq;
	}

	public void setFkrq(java.util.Date value) {
		this.fkrq = value;
	}

	public String getFkqk() {
		return this.fkqk;
	}

	public void setFkqk(String value) {
		this.fkqk = value;
	}
	public String getFkr() {
		return this.fkr;
	}

	public void setFkr(String value) {
		this.fkr = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
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

