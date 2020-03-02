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


public class Kss_YzmxModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String ypbh;
	
	private java.lang.String yzbh;
	
	private java.lang.Byte cs;
	
	private java.lang.Short sl;
	
	private java.lang.String fyjg;
	
	private java.lang.String fyzysx;
	
	private java.lang.String state;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	
	private java.lang.String creator;
	
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updatetime;
	
	private java.lang.String dw;
	//columns END

	 

	public Kss_YzmxModel(){
	}
	public Kss_YzmxModel(String id) {
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
	public java.lang.String getYpbh() {
		return this.ypbh;
	}
	
	public void setYpbh(java.lang.String value) {
		this.ypbh = value;
	}
	public java.lang.String getYzbh() {
		return this.yzbh;
	}
	
	public void setYzbh(java.lang.String value) {
		this.yzbh = value;
	}
	public java.lang.Byte getCs() {
		return this.cs;
	}
	
	public void setCs(java.lang.Byte value) {
		this.cs = value;
	}
	public java.lang.Short getSl() {
		return this.sl;
	}
	
	public void setSl(java.lang.Short value) {
		this.sl = value;
	}
	public java.lang.String getFyjg() {
		return this.fyjg;
	}
	
	public void setFyjg(java.lang.String value) {
		this.fyjg = value;
	}
	public java.lang.String getFyzysx() {
		return this.fyzysx;
	}
	
	public void setFyzysx(java.lang.String value) {
		this.fyzysx = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.lang.String getCreator() {
		return this.creator;
	}
	
	public void setCreator(java.lang.String value) {
		this.creator = value;
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
	
	public java.lang.String getDw() {
		return this.dw;
	}
	
	public void setDw(java.lang.String value) {
		this.dw = value;
	}
	 
}

