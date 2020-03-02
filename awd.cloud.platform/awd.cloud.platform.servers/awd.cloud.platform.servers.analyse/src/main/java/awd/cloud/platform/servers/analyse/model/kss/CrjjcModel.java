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
public class CrjjcModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String bllx;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date blsj;

	private String ywqxyc;

	private String qxycqk;

	private String ywzssb;

	private String sbqk;

	private String ywwjp;

	private String wjpqk;

	private String ywtbs;

	private String zssbqk;

	private String dlmj;

	private String ywlcid;

	private String bdfs;

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



	public CrjjcModel(){
	}
	public CrjjcModel(String id) {
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
	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String value) {
		this.bllx = value;
	}

	public java.util.Date getBlsj() {
		return this.blsj;
	}

	public void setBlsj(java.util.Date value) {
		this.blsj = value;
	}

	public String getYwqxyc() {
		return this.ywqxyc;
	}

	public void setYwqxyc(String value) {
		this.ywqxyc = value;
	}
	public String getQxycqk() {
		return this.qxycqk;
	}

	public void setQxycqk(String value) {
		this.qxycqk = value;
	}
	public String getYwzssb() {
		return this.ywzssb;
	}

	public void setYwzssb(String value) {
		this.ywzssb = value;
	}
	public String getSbqk() {
		return this.sbqk;
	}

	public void setSbqk(String value) {
		this.sbqk = value;
	}
	public String getYwwjp() {
		return this.ywwjp;
	}

	public void setYwwjp(String value) {
		this.ywwjp = value;
	}
	public String getWjpqk() {
		return this.wjpqk;
	}

	public void setWjpqk(String value) {
		this.wjpqk = value;
	}
	public String getYwtbs() {
		return this.ywtbs;
	}

	public void setYwtbs(String value) {
		this.ywtbs = value;
	}
	public String getZssbqk() {
		return this.zssbqk;
	}

	public void setZssbqk(String value) {
		this.zssbqk = value;
	}
	public String getDlmj() {
		return this.dlmj;
	}

	public void setDlmj(String value) {
		this.dlmj = value;
	}
	public String getYwlcid() {
		return this.ywlcid;
	}

	public void setYwlcid(String value) {
		this.ywlcid = value;
	}
	public String getBdfs() {
		return this.bdfs;
	}

	public void setBdfs(String value) {
		this.bdfs = value;
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

