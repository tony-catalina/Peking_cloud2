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
public class ShgxModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String jsxm;

	private String xb;

	private String nl;

	private String jszjh;

	private String gx;

	private String gzdw;

	private String dh;

	private String dz;

	private String yb;

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

	private String zjlx;

	/**
	 * 传递开始结束时间使用
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date starttime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date endtime;
	//columns END



	public ShgxModel(){
	}
	public ShgxModel(String id) {
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
	public String getJsxm() {
		return this.jsxm;
	}

	public void setJsxm(String value) {
		this.jsxm = value;
	}
	public String getXb() {
		return this.xb;
	}

	public void setXb(String value) {
		this.xb = value;
	}
	public String getNl() {
		return this.nl;
	}

	public void setNl(String value) {
		this.nl = value;
	}
	public String getJszjh() {
		return this.jszjh;
	}

	public void setJszjh(String value) {
		this.jszjh = value;
	}
	public String getGx() {
		return this.gx;
	}

	public void setGx(String value) {
		this.gx = value;
	}
	public String getGzdw() {
		return this.gzdw;
	}

	public void setGzdw(String value) {
		this.gzdw = value;
	}
	public String getDh() {
		return this.dh;
	}

	public void setDh(String value) {
		this.dh = value;
	}
	public String getDz() {
		return this.dz;
	}

	public void setDz(String value) {
		this.dz = value;
	}
	public String getYb() {
		return this.yb;
	}

	public void setYb(String value) {
		this.yb = value;
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

	public String getZjlx() {
		return this.zjlx;
	}

	public void setZjlx(String value) {
		this.zjlx = value;
	}
	 
}

