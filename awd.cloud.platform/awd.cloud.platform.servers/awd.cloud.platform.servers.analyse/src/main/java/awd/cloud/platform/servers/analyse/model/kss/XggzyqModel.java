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
public class XggzyqModel implements Model {

	//columns START
	
	private String id;


	private String jqh;

	private String yqqk;

	private String yqnr;

	private String yqfk;

	private String fkr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date fksj;

	private String fkqk;

	private String jsbh;

	private String fkzt;

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



	public XggzyqModel(){
	}
	public XggzyqModel(String id) {
		this.id = id;
	}


	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public String getJqh() {
		return this.jqh;
	}

	public void setJqh(String value) {
		this.jqh = value;
	}
	public String getYqqk() {
		return this.yqqk;
	}

	public void setYqqk(String value) {
		this.yqqk = value;
	}
	public String getYqnr() {
		return this.yqnr;
	}

	public void setYqnr(String value) {
		this.yqnr = value;
	}
	public String getYqfk() {
		return this.yqfk;
	}

	public void setYqfk(String value) {
		this.yqfk = value;
	}
	public String getFkr() {
		return this.fkr;
	}

	public void setFkr(String value) {
		this.fkr = value;
	}

	public java.util.Date getFksj() {
		return this.fksj;
	}

	public void setFksj(java.util.Date value) {
		this.fksj = value;
	}

	public String getFkqk() {
		return this.fkqk;
	}

	public void setFkqk(String value) {
		this.fkqk = value;
	}
	public String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(String value) {
		this.jsbh = value;
	}
	public String getFkzt() {
		return this.fkzt;
	}

	public void setFkzt(String value) {
		this.fkzt = value;
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

