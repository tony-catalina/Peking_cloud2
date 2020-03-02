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
public class JsModel implements Model {
	
	//columns START
	
	private String id;


	private String jsbh;

	private String jqh;

	private String jsmc;

	private String jsh;

	private String jslb;

	private String type;

	private Integer innum;

	private Integer bznum;

	private String zgmj;

	private String xgmj;

	private String wmjs;

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



	public JsModel(){
	}
	public JsModel(String id) {
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
	public String getJqh() {
		return this.jqh;
	}

	public void setJqh(String value) {
		this.jqh = value;
	}
	public String getJsmc() {
		return this.jsmc;
	}

	public void setJsmc(String value) {
		this.jsmc = value;
	}
	public String getJsh() {
		return this.jsh;
	}

	public void setJsh(String value) {
		this.jsh = value;
	}
	public String getJslb() {
		return this.jslb;
	}

	public void setJslb(String value) {
		this.jslb = value;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String value) {
		this.type = value;
	}
	public Integer getInnum() {
		return this.innum;
	}

	public void setInnum(Integer value) {
		this.innum = value;
	}
	public Integer getBznum() {
		return this.bznum;
	}

	public void setBznum(Integer value) {
		this.bznum = value;
	}
	public String getZgmj() {
		return this.zgmj;
	}

	public void setZgmj(String value) {
		this.zgmj = value;
	}
	public String getXgmj() {
		return this.xgmj;
	}

	public void setXgmj(String value) {
		this.xgmj = value;
	}
	public String getWmjs() {
		return this.wmjs;
	}

	public void setWmjs(String value) {
		this.wmjs = value;
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

