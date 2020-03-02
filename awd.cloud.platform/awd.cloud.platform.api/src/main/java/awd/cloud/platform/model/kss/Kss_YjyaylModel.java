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


public class Kss_YjyaylModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date ylsj;
	
	private java.lang.String zzzhz;
	
	private java.lang.String hyjlr;
	
	private java.lang.String ksscjry;
	
	private java.lang.String wjzdcjry;
	
	private java.lang.String qtry;
	
	private java.lang.String ylqk;
	
	private java.lang.String czwt;
	
	private java.lang.String gjcs;
	
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

	 

	public Kss_YjyaylModel(){
	}
	public Kss_YjyaylModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.util.Date getYlsj() {
		return this.ylsj;
	}
	
	public void setYlsj(java.util.Date value) {
		this.ylsj = value;
	}
	
	public java.lang.String getZzzhz() {
		return this.zzzhz;
	}
	
	public void setZzzhz(java.lang.String value) {
		this.zzzhz = value;
	}
	public java.lang.String getHyjlr() {
		return this.hyjlr;
	}
	
	public void setHyjlr(java.lang.String value) {
		this.hyjlr = value;
	}
	public java.lang.String getKsscjry() {
		return this.ksscjry;
	}
	
	public void setKsscjry(java.lang.String value) {
		this.ksscjry = value;
	}
	public java.lang.String getWjzdcjry() {
		return this.wjzdcjry;
	}
	
	public void setWjzdcjry(java.lang.String value) {
		this.wjzdcjry = value;
	}
	public java.lang.String getQtry() {
		return this.qtry;
	}
	
	public void setQtry(java.lang.String value) {
		this.qtry = value;
	}
	public java.lang.String getYlqk() {
		return this.ylqk;
	}
	
	public void setYlqk(java.lang.String value) {
		this.ylqk = value;
	}
	public java.lang.String getCzwt() {
		return this.czwt;
	}
	
	public void setCzwt(java.lang.String value) {
		this.czwt = value;
	}
	public java.lang.String getGjcs() {
		return this.gjcs;
	}
	
	public void setGjcs(java.lang.String value) {
		this.gjcs = value;
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

