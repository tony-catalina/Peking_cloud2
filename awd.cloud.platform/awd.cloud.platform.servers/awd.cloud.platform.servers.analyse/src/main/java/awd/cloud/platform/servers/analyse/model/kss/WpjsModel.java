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
public class WpjsModel implements Model {

	
	//columns START
	
	private String id;


	private String rybh;

	private String jsbh;

	private String srlx;

	private String jswpmc;

	private String jszjh;

	private String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;

	private String fzdbh;

	private Short fzdsl;

	private Short djwpsl;

	private String djwpbh;

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



	public WpjsModel(){
	}
	public WpjsModel(String id) {
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
	public String getSrlx() {
		return this.srlx;
	}

	public void setSrlx(String value) {
		this.srlx = value;
	}
	public String getJswpmc() {
		return this.jswpmc;
	}

	public void setJswpmc(String value) {
		this.jswpmc = value;
	}
	public String getJszjh() {
		return this.jszjh;
	}

	public void setJszjh(String value) {
		this.jszjh = value;
	}
	public String getDjr() {
		return this.djr;
	}

	public void setDjr(String value) {
		this.djr = value;
	}

	public java.util.Date getDjsj() {
		return this.djsj;
	}

	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}

	public String getFzdbh() {
		return this.fzdbh;
	}

	public void setFzdbh(String value) {
		this.fzdbh = value;
	}
	public Short getFzdsl() {
		return this.fzdsl;
	}

	public void setFzdsl(Short value) {
		this.fzdsl = value;
	}
	public Short getDjwpsl() {
		return this.djwpsl;
	}

	public void setDjwpsl(Short value) {
		this.djwpsl = value;
	}
	public String getDjwpbh() {
		return this.djwpbh;
	}

	public void setDjwpbh(String value) {
		this.djwpbh = value;
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

