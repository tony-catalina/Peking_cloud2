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
public class SwglModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String jsh;

	private String swqk;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date swrq;

	private String cbr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date dcrq;

	private String dcjgou;

	private String dcjguo;

	private String jyclyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date clsj;

	private String clfs;

	private String jlr;

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



	public SwglModel(){
	}
	public SwglModel(String id) {
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
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getSwqk() {
		return this.swqk;
	}

	public void setSwqk(String value) {
		this.swqk = value;
	}

	public java.util.Date getSwrq() {
		return this.swrq;
	}

	public void setSwrq(java.util.Date value) {
		this.swrq = value;
	}

	public String getCbr() {
		return this.cbr;
	}

	public void setCbr(String value) {
		this.cbr = value;
	}

	public java.util.Date getDcrq() {
		return this.dcrq;
	}

	public void setDcrq(java.util.Date value) {
		this.dcrq = value;
	}

	public String getDcjgou() {
		return this.dcjgou;
	}

	public void setDcjgou(String value) {
		this.dcjgou = value;
	}
	public String getDcjguo() {
		return this.dcjguo;
	}

	public void setDcjguo(String value) {
		this.dcjguo = value;
	}
	public String getJyclyj() {
		return this.jyclyj;
	}

	public void setJyclyj(String value) {
		this.jyclyj = value;
	}

	public java.util.Date getClsj() {
		return this.clsj;
	}

	public void setClsj(java.util.Date value) {
		this.clsj = value;
	}

	public String getClfs() {
		return this.clfs;
	}

	public void setClfs(String value) {
		this.clfs = value;
	}
	public String getJlr() {
		return this.jlr;
	}

	public void setJlr(String value) {
		this.jlr = value;
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

