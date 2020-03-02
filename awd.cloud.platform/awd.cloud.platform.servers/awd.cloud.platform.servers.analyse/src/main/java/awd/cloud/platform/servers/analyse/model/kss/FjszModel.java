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
public class FjszModel implements Model {

	
	//columns START
	
	private String id;


	private String jsbh;

	private String fjmc;

	private String fjhm;

	private String rybh;

	private String fjqc;

	private String syry;

	private String fjlx;

	private String yyfj;

	private String syzt;

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



	public FjszModel(){
	}
	public FjszModel(String id) {
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
	public String getFjmc() {
		return this.fjmc;
	}

	public void setFjmc(String value) {
		this.fjmc = value;
	}
	public String getFjhm() {
		return this.fjhm;
	}

	public void setFjhm(String value) {
		this.fjhm = value;
	}
	public String getRybh() {
		return this.rybh;
	}

	public void setRybh(String value) {
		this.rybh = value;
	}
	public String getFjqc() {
		return this.fjqc;
	}

	public void setFjqc(String value) {
		this.fjqc = value;
	}
	public String getSyry() {
		return this.syry;
	}

	public void setSyry(String value) {
		this.syry = value;
	}
	public String getFjlx() {
		return this.fjlx;
	}

	public void setFjlx(String value) {
		this.fjlx = value;
	}
	public String getYyfj() {
		return this.yyfj;
	}

	public void setYyfj(String value) {
		this.yyfj = value;
	}
	public String getSyzt() {
		return this.syzt;
	}

	public void setSyzt(String value) {
		this.syzt = value;
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

