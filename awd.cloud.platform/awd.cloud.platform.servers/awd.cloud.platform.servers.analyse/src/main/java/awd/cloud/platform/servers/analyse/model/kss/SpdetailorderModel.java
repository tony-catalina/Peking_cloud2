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
public class SpdetailorderModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String ddbh;

	private String sptm;

	private String sfqh;

	private String sfzjff;

	private java.math.BigDecimal spsl;

	private java.math.BigDecimal xfje;

	private java.math.BigDecimal sfje;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ddcjsj;

	private String cgdbh;

	private String status;

	private String shzt;

	private String shyj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date shsj;

	private String shr;

	private String state;

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



	public SpdetailorderModel(){
	}
	public SpdetailorderModel(String id) {
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
	public String getDdbh() {
		return this.ddbh;
	}

	public void setDdbh(String value) {
		this.ddbh = value;
	}
	public String getSptm() {
		return this.sptm;
	}

	public void setSptm(String value) {
		this.sptm = value;
	}
	public String getSfqh() {
		return this.sfqh;
	}

	public void setSfqh(String value) {
		this.sfqh = value;
	}
	public String getSfzjff() {
		return this.sfzjff;
	}

	public void setSfzjff(String value) {
		this.sfzjff = value;
	}
	public java.math.BigDecimal getSpsl() {
		return this.spsl;
	}

	public void setSpsl(java.math.BigDecimal value) {
		this.spsl = value;
	}
	public java.math.BigDecimal getXfje() {
		return this.xfje;
	}

	public void setXfje(java.math.BigDecimal value) {
		this.xfje = value;
	}
	public java.math.BigDecimal getSfje() {
		return this.sfje;
	}

	public void setSfje(java.math.BigDecimal value) {
		this.sfje = value;
	}

	public java.util.Date getDdcjsj() {
		return this.ddcjsj;
	}

	public void setDdcjsj(java.util.Date value) {
		this.ddcjsj = value;
	}

	public String getCgdbh() {
		return this.cgdbh;
	}

	public void setCgdbh(String value) {
		this.cgdbh = value;
	}
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String value) {
		this.status = value;
	}
	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String value) {
		this.shzt = value;
	}
	public String getShyj() {
		return this.shyj;
	}

	public void setShyj(String value) {
		this.shyj = value;
	}

	public java.util.Date getShsj() {
		return this.shsj;
	}

	public void setShsj(java.util.Date value) {
		this.shsj = value;
	}

	public String getShr() {
		return this.shr;
	}

	public void setShr(String value) {
		this.shr = value;
	}
	public String getState() {
		return this.state;
	}

	public void setState(String value) {
		this.state = value;
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

