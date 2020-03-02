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


public class Kss_WlryModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String lssy;
	
	private java.lang.String jtsy;
	
	private java.lang.Integer lfrs;
	
	private java.lang.Integer sxcls;
	
	private java.lang.String drmj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date drsj;
	
	private java.lang.String wljbxx;
	
	private java.lang.String clxx;
	
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

	 

	public Kss_WlryModel(){
	}
	public Kss_WlryModel(String id) {
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
	public java.lang.String getLssy() {
		return this.lssy;
	}
	
	public void setLssy(java.lang.String value) {
		this.lssy = value;
	}
	public java.lang.String getJtsy() {
		return this.jtsy;
	}
	
	public void setJtsy(java.lang.String value) {
		this.jtsy = value;
	}
	public java.lang.Integer getLfrs() {
		return this.lfrs;
	}
	
	public void setLfrs(java.lang.Integer value) {
		this.lfrs = value;
	}
	public java.lang.Integer getSxcls() {
		return this.sxcls;
	}
	
	public void setSxcls(java.lang.Integer value) {
		this.sxcls = value;
	}
	public java.lang.String getDrmj() {
		return this.drmj;
	}
	
	public void setDrmj(java.lang.String value) {
		this.drmj = value;
	}
	
	public java.util.Date getDrsj() {
		return this.drsj;
	}
	
	public void setDrsj(java.util.Date value) {
		this.drsj = value;
	}
	
	public java.lang.String getWljbxx() {
		return this.wljbxx;
	}
	
	public void setWljbxx(java.lang.String value) {
		this.wljbxx = value;
	}
	public java.lang.String getClxx() {
		return this.clxx;
	}
	
	public void setClxx(java.lang.String value) {
		this.clxx = value;
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

