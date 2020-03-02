/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.model.logs;

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


public class Logs_DocumentModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String title;
	
	private java.lang.String theme;
	
	private java.lang.String zsdw;
	
	private java.lang.String csdw;
	
	private java.lang.String cbdw;
	
	private java.lang.String qfr;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date qfrq;
	
	private java.lang.String wjzh;
	
	private java.lang.String bmjb;
	
	private java.lang.String hj;
	
	private java.lang.String ngr;
	
	private java.lang.String ngdw;
	
	private java.lang.String uuid;
	
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
	
	private java.lang.String hfbj;
	//columns END

	 

	public Logs_DocumentModel(){
	}
	public Logs_DocumentModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	public java.lang.String getTheme() {
		return this.theme;
	}
	
	public void setTheme(java.lang.String value) {
		this.theme = value;
	}
	public java.lang.String getZsdw() {
		return this.zsdw;
	}
	
	public void setZsdw(java.lang.String value) {
		this.zsdw = value;
	}
	public java.lang.String getCsdw() {
		return this.csdw;
	}
	
	public void setCsdw(java.lang.String value) {
		this.csdw = value;
	}
	public java.lang.String getCbdw() {
		return this.cbdw;
	}
	
	public void setCbdw(java.lang.String value) {
		this.cbdw = value;
	}
	public java.lang.String getQfr() {
		return this.qfr;
	}
	
	public void setQfr(java.lang.String value) {
		this.qfr = value;
	}
	
	public java.util.Date getQfrq() {
		return this.qfrq;
	}
	
	public void setQfrq(java.util.Date value) {
		this.qfrq = value;
	}
	
	public java.lang.String getWjzh() {
		return this.wjzh;
	}
	
	public void setWjzh(java.lang.String value) {
		this.wjzh = value;
	}
	public java.lang.String getBmjb() {
		return this.bmjb;
	}
	
	public void setBmjb(java.lang.String value) {
		this.bmjb = value;
	}
	public java.lang.String getHj() {
		return this.hj;
	}
	
	public void setHj(java.lang.String value) {
		this.hj = value;
	}
	public java.lang.String getNgr() {
		return this.ngr;
	}
	
	public void setNgr(java.lang.String value) {
		this.ngr = value;
	}
	public java.lang.String getNgdw() {
		return this.ngdw;
	}
	
	public void setNgdw(java.lang.String value) {
		this.ngdw = value;
	}
	public java.lang.String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(java.lang.String value) {
		this.uuid = value;
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
	
	public java.lang.String getHfbj() {
		return this.hfbj;
	}
	
	public void setHfbj(java.lang.String value) {
		this.hfbj = value;
	}
	 
}

