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
public class SphjqkdjModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String rybh;

	private String hjrid;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjkssj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date hjjssj;

	private String bhjrdd;

	private String bhjrbx;

	private String hjrdd;

	private String hjrbx;

	private String jsyy;

	private String hjlx;

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



	public SphjqkdjModel(){
	}
	public SphjqkdjModel(String id) {
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
	public String getHjrid() {
		return this.hjrid;
	}

	public void setHjrid(String value) {
		this.hjrid = value;
	}

	public java.util.Date getHjkssj() {
		return this.hjkssj;
	}

	public void setHjkssj(java.util.Date value) {
		this.hjkssj = value;
	}


	public java.util.Date getHjjssj() {
		return this.hjjssj;
	}

	public void setHjjssj(java.util.Date value) {
		this.hjjssj = value;
	}

	public String getBhjrdd() {
		return this.bhjrdd;
	}

	public void setBhjrdd(String value) {
		this.bhjrdd = value;
	}
	public String getBhjrbx() {
		return this.bhjrbx;
	}

	public void setBhjrbx(String value) {
		this.bhjrbx = value;
	}
	public String getHjrdd() {
		return this.hjrdd;
	}

	public void setHjrdd(String value) {
		this.hjrdd = value;
	}
	public String getHjrbx() {
		return this.hjrbx;
	}

	public void setHjrbx(String value) {
		this.hjrbx = value;
	}
	public String getJsyy() {
		return this.jsyy;
	}

	public void setJsyy(String value) {
		this.jsyy = value;
	}
	public String getHjlx() {
		return this.hjlx;
	}

	public void setHjlx(String value) {
		this.hjlx = value;
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

