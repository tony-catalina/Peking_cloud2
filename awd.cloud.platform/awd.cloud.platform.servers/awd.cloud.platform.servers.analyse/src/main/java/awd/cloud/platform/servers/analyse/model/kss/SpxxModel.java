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
public class SpxxModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String pch;

	private String spmc;

	private String tm;

	private String gg;

	private String jldw;

	private java.math.BigDecimal lsj;

	private String splb;

	private String sfzjff;

	private String sfxg;

	private String jhpl;

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



	public SpxxModel(){
	}
	public SpxxModel(String id) {
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
	public String getPch() {
		return this.pch;
	}

	public void setPch(String value) {
		this.pch = value;
	}
	public String getSpmc() {
		return this.spmc;
	}

	public void setSpmc(String value) {
		this.spmc = value;
	}
	public String getTm() {
		return this.tm;
	}

	public void setTm(String value) {
		this.tm = value;
	}
	public String getGg() {
		return this.gg;
	}

	public void setGg(String value) {
		this.gg = value;
	}
	public String getJldw() {
		return this.jldw;
	}

	public void setJldw(String value) {
		this.jldw = value;
	}
	public java.math.BigDecimal getLsj() {
		return this.lsj;
	}

	public void setLsj(java.math.BigDecimal value) {
		this.lsj = value;
	}
	public String getSplb() {
		return this.splb;
	}

	public void setSplb(String value) {
		this.splb = value;
	}
	public String getSfzjff() {
		return this.sfzjff;
	}

	public void setSfzjff(String value) {
		this.sfzjff = value;
	}
	public String getSfxg() {
		return this.sfxg;
	}

	public void setSfxg(String value) {
		this.sfxg = value;
	}
	public String getJhpl() {
		return this.jhpl;
	}

	public void setJhpl(String value) {
		this.jhpl = value;
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

