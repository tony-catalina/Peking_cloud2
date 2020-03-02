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
public class TlhmcjlModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String xm;

	private String bm;

	private String xb;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date csrq;

	private String whcd;

	private String mz;

	private String zzmm;

	private String jkzk;

	private String zy;

	private String cbdw;

	private String sjc;

	private String state;

	private String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	//columns END



	public TlhmcjlModel(){
	}
	public TlhmcjlModel(String id) {
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
	public String getXm() {
		return this.xm;
	}

	public void setXm(String value) {
		this.xm = value;
	}
	public String getBm() {
		return this.bm;
	}

	public void setBm(String value) {
		this.bm = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}

	public java.util.Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(java.util.Date value) {
		this.csrq = value;
	}

	public String getWhcd() {
		return this.whcd;
	}

	public void setWhcd(String value) {
		this.whcd = value;
	}
	public String getMz() {
		return this.mz;
	}

	public void setMz(String value) {
		this.mz = value;
	}
	public String getZzmm() {
		return this.zzmm;
	}

	public void setZzmm(String value) {
		this.zzmm = value;
	}
	public String getJkzk() {
		return this.jkzk;
	}

	public void setJkzk(String value) {
		this.jkzk = value;
	}
	public String getZy() {
		return this.zy;
	}

	public void setZy(String value) {
		this.zy = value;
	}
	public String getCbdw() {
		return this.cbdw;
	}

	public void setCbdw(String value) {
		this.cbdw = value;
	}
	public String getSjc() {
		return this.sjc;
	}

	public void setSjc(String value) {
		this.sjc = value;
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
	
	 
}

