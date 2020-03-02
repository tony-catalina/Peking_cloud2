/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import awd.framework.common.model.Model;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class BarModel implements Model {

	private java.lang.String id;
	private java.lang.String sfzh;
	private java.lang.String xm;
	private java.lang.String dwlx;
	private java.lang.String badw;
	private java.sql.Blob zwtx;
	private java.sql.Blob zwtz;
	private java.lang.String active;
	private java.lang.String state;
	private java.lang.String creator;
	private java.util.Date createtime;
	private java.lang.String updator;
	private java.util.Date updatetime;
	//columns END


	public void BarEntity(){
	}

	public void BarEntity(
		java.lang.String id
	){
		this.id = id;
	}

	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
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
	
	public java.sql.Blob getZwtx() {
		return this.zwtx;
	}
	
	public void setZwtx(java.sql.Blob value) {
		this.zwtx = value;
	}
	
	public java.sql.Blob getZwtz() {
		return this.zwtz;
	}
	
	public void setZwtz(java.sql.Blob value) {
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
	
	public java.lang.Object getCreatetime() {
		return this.createtime;
	}
	
	
	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	
	public java.lang.Object getUpdatetime() {
		return this.updatetime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
	
}

