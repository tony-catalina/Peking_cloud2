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
public class YpxxModel implements Model {
	

	//columns START
	
	private String id;


	private String syff;

	private String jsbh;

	private String ypmc;

	private String tm;

	private String fydw;

	private String ypdw;

	private java.math.BigDecimal jg;

	private String gg;

	private String sccj;

	private String sfcfy;

	private Short dzkcl;

	private byte[] smszp;

	private byte[] ypbzzp;

	private String sypl;

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

	private String smsurl;

	private String ypbzurl;
	//columns END



	public YpxxModel(){
	}
	public YpxxModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getSyff() {
		return this.syff;
	}

	public void setSyff(String value) {
		this.syff = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getYpmc() {
		return this.ypmc;
	}

	public void setYpmc(String value) {
		this.ypmc = value;
	}
	public String getTm() {
		return this.tm;
	}

	public void setTm(String value) {
		this.tm = value;
	}
	public String getFydw() {
		return this.fydw;
	}

	public void setFydw(String value) {
		this.fydw = value;
	}
	public String getYpdw() {
		return this.ypdw;
	}

	public void setYpdw(String value) {
		this.ypdw = value;
	}
	public java.math.BigDecimal getJg() {
		return this.jg;
	}

	public void setJg(java.math.BigDecimal value) {
		this.jg = value;
	}
	public String getGg() {
		return this.gg;
	}

	public void setGg(String value) {
		this.gg = value;
	}
	public String getSccj() {
		return this.sccj;
	}

	public void setSccj(String value) {
		this.sccj = value;
	}
	public String getSfcfy() {
		return this.sfcfy;
	}

	public void setSfcfy(String value) {
		this.sfcfy = value;
	}
	public Short getDzkcl() {
		return this.dzkcl;
	}

	public void setDzkcl(Short value) {
		this.dzkcl = value;
	}
	public byte[] getSmszp() {
		return this.smszp;
	}

	public void setSmszp(byte[] value) {
		this.smszp = value;
	}
	public byte[] getYpbzzp() {
		return this.ypbzzp;
	}

	public void setYpbzzp(byte[] value) {
		this.ypbzzp = value;
	}
	public String getSypl() {
		return this.sypl;
	}

	public void setSypl(String value) {
		this.sypl = value;
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

	public String getSmsurl() {
		return this.smsurl;
	}

	public void setSmsurl(String value) {
		this.smsurl = value;
	}
	public String getYpbzurl() {
		return this.ypbzurl;
	}

	public void setYpbzurl(String value) {
		this.ypbzurl = value;
	}
	 
}

