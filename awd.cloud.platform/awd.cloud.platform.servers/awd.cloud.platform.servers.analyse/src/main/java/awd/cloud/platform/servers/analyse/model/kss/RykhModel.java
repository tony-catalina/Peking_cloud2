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
public class RykhModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String khxdm;

	private String khr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date khsj;

	private String khmk;

	private String khmkid;

	private String khmkcontent;

	private String jkflx;

	private java.math.BigDecimal jkfs;

	private java.math.BigDecimal cygljkfs;

	private java.math.BigDecimal fxpgjkfs;

	private String sftg;

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



	public RykhModel(){
	}
	public RykhModel(String id) {
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
	public String getKhxdm() {
		return this.khxdm;
	}

	public void setKhxdm(String value) {
		this.khxdm = value;
	}
	public String getKhr() {
		return this.khr;
	}

	public void setKhr(String value) {
		this.khr = value;
	}

	public java.util.Date getKhsj() {
		return this.khsj;
	}

	public void setKhsj(java.util.Date value) {
		this.khsj = value;
	}

	public String getKhmk() {
		return this.khmk;
	}

	public void setKhmk(String value) {
		this.khmk = value;
	}
	public String getKhmkid() {
		return this.khmkid;
	}

	public void setKhmkid(String value) {
		this.khmkid = value;
	}
	public String getKhmkcontent() {
		return this.khmkcontent;
	}

	public void setKhmkcontent(String value) {
		this.khmkcontent = value;
	}
	public String getJkflx() {
		return this.jkflx;
	}

	public void setJkflx(String value) {
		this.jkflx = value;
	}
	public java.math.BigDecimal getJkfs() {
		return this.jkfs;
	}

	public void setJkfs(java.math.BigDecimal value) {
		this.jkfs = value;
	}
	public java.math.BigDecimal getCygljkfs() {
		return this.cygljkfs;
	}

	public void setCygljkfs(java.math.BigDecimal value) {
		this.cygljkfs = value;
	}
	public java.math.BigDecimal getFxpgjkfs() {
		return this.fxpgjkfs;
	}

	public void setFxpgjkfs(java.math.BigDecimal value) {
		this.fxpgjkfs = value;
	}
	public String getSftg() {
		return this.sftg;
	}

	public void setSftg(String value) {
		this.sftg = value;
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

