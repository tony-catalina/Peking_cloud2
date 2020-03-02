/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.kss;

import awd.cloud.platform.model.Model;
import javax.validation.constraints.NotNull;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class Kss_WpjsModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String rybh;
	
	private java.lang.String jsbh;
	
	private java.lang.String srlx;
	
	private java.lang.String jswpmc;
	
	private java.lang.String jszjh;
	
	private java.lang.String djr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;
	
	private java.lang.String fzdbh;
	
	private java.lang.Short fzdsl;
	
	private java.lang.Short djwpsl;
	
	private java.lang.String djwpbh;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	//columns END

	 

	public Kss_WpjsModel(){
	}
	public Kss_WpjsModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	public java.lang.String getSrlx() {
		return this.srlx;
	}
	
	public void setSrlx(java.lang.String value) {
		this.srlx = value;
	}
	public java.lang.String getJswpmc() {
		return this.jswpmc;
	}
	
	public void setJswpmc(java.lang.String value) {
		this.jswpmc = value;
	}
	public java.lang.String getJszjh() {
		return this.jszjh;
	}
	
	public void setJszjh(java.lang.String value) {
		this.jszjh = value;
	}
	public java.lang.String getDjr() {
		return this.djr;
	}
	
	public void setDjr(java.lang.String value) {
		this.djr = value;
	}
	
	public java.util.Date getDjsj() {
		return this.djsj;
	}
	
	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}
	
	public java.lang.String getFzdbh() {
		return this.fzdbh;
	}
	
	public void setFzdbh(java.lang.String value) {
		this.fzdbh = value;
	}
	public java.lang.Short getFzdsl() {
		return this.fzdsl;
	}
	
	public void setFzdsl(java.lang.Short value) {
		this.fzdsl = value;
	}
	public java.lang.Short getDjwpsl() {
		return this.djwpsl;
	}
	
	public void setDjwpsl(java.lang.Short value) {
		this.djwpsl = value;
	}
	public java.lang.String getDjwpbh() {
		return this.djwpbh;
	}
	
	public void setDjwpbh(java.lang.String value) {
		this.djwpbh = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	
	public java.util.Date getUpdatetime() {
		return this.updatetime;
	}
	
	public void setUpdatetime(java.util.Date value) {
		this.updatetime = value;
	}
	
	 
}

