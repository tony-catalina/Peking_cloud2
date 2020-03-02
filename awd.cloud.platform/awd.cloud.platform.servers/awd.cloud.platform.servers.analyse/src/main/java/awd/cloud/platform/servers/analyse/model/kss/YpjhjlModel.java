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
public class YpjhjlModel implements Model {

	//columns START
	
	private String id;


	private String ypbh;

	private String jsbh;

	private java.math.BigDecimal jhsl;

	private String pzwh;

	private String pch;

	private java.math.BigDecimal jg;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date scrq;

	private String bzq;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dqsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jhsj;

	private String jhr;

	private String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;

	private String creator;

	private String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;

	private String jhbh;

	private java.math.BigDecimal zje;
	//columns END



	public YpjhjlModel(){
	}
	public YpjhjlModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getYpbh() {
		return this.ypbh;
	}

	public void setYpbh(String value) {
		this.ypbh = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public java.math.BigDecimal getJhsl() {
		return this.jhsl;
	}

	public void setJhsl(java.math.BigDecimal value) {
		this.jhsl = value;
	}
	public String getPzwh() {
		return this.pzwh;
	}

	public void setPzwh(String value) {
		this.pzwh = value;
	}
	public String getPch() {
		return this.pch;
	}

	public void setPch(String value) {
		this.pch = value;
	}
	public java.math.BigDecimal getJg() {
		return this.jg;
	}

	public void setJg(java.math.BigDecimal value) {
		this.jg = value;
	}

	public java.util.Date getScrq() {
		return this.scrq;
	}

	public void setScrq(java.util.Date value) {
		this.scrq = value;
	}

	public String getBzq() {
		return this.bzq;
	}

	public void setBzq(String value) {
		this.bzq = value;
	}

	public java.util.Date getDqsj() {
		return this.dqsj;
	}

	public void setDqsj(java.util.Date value) {
		this.dqsj = value;
	}


	public java.util.Date getJhsj() {
		return this.jhsj;
	}

	public void setJhsj(java.util.Date value) {
		this.jhsj = value;
	}

	public String getJhr() {
		return this.jhr;
	}

	public void setJhr(String value) {
		this.jhr = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
	}

	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String value) {
		this.creator = value;
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

	public String getJhbh() {
		return this.jhbh;
	}

	public void setJhbh(String value) {
		this.jhbh = value;
	}
	public java.math.BigDecimal getZje() {
		return this.zje;
	}
	
	public void setZje(java.math.BigDecimal value) {
		this.zje = value;
	}
	 
}

