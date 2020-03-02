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
public class YjyaylModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ylsj;

	private String zzzhz;

	private String hyjlr;

	private String ksscjry;

	private String wjzdcjry;

	private String qtry;

	private String ylqk;

	private String czwt;

	private String gjcs;

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



	public YjyaylModel(){
	}
	public YjyaylModel(String id) {
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

	public java.util.Date getYlsj() {
		return this.ylsj;
	}

	public void setYlsj(java.util.Date value) {
		this.ylsj = value;
	}

	public String getZzzhz() {
		return this.zzzhz;
	}

	public void setZzzhz(String value) {
		this.zzzhz = value;
	}
	public String getHyjlr() {
		return this.hyjlr;
	}

	public void setHyjlr(String value) {
		this.hyjlr = value;
	}
	public String getKsscjry() {
		return this.ksscjry;
	}

	public void setKsscjry(String value) {
		this.ksscjry = value;
	}
	public String getWjzdcjry() {
		return this.wjzdcjry;
	}

	public void setWjzdcjry(String value) {
		this.wjzdcjry = value;
	}
	public String getQtry() {
		return this.qtry;
	}

	public void setQtry(String value) {
		this.qtry = value;
	}
	public String getYlqk() {
		return this.ylqk;
	}

	public void setYlqk(String value) {
		this.ylqk = value;
	}
	public String getCzwt() {
		return this.czwt;
	}

	public void setCzwt(String value) {
		this.czwt = value;
	}
	public String getGjcs() {
		return this.gjcs;
	}

	public void setGjcs(String value) {
		this.gjcs = value;
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

