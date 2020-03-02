/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.manager;

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


public class Manager_BarModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String sfzh;
	
	private java.lang.String xm;
	
	private java.lang.String dwlx;
	
	private java.lang.String badw;
	
	private byte[] zwtx;
	
	private byte[] zwtz;
	
	private java.lang.String active;
	
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

	 

	public Manager_BarModel(){
	}
	public Manager_BarModel(String id) {
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
	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getDwlx() {
		return this.dwlx;
	}
	
	public void setDwlx(java.lang.String value) {
		this.dwlx = value;
	}
	public java.lang.String getBadw() {
		return this.badw;
	}
	
	public void setBadw(java.lang.String value) {
		this.badw = value;
	}
	public byte[] getZwtx() {
		return this.zwtx;
	}
	
	public void setZwtx(byte[] value) {
		this.zwtx = value;
	}
	public byte[] getZwtz() {
		return this.zwtz;
	}
	
	public void setZwtz(byte[] value) {
		this.zwtz = value;
	}
	public java.lang.String getActive() {
		return this.active;
	}
	
	public void setActive(java.lang.String value) {
		this.active = value;
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

