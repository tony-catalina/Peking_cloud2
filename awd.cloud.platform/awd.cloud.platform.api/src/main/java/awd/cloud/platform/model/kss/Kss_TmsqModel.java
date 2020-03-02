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


public class Kss_TmsqModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String state;
	
	private java.lang.String sm;
	
	private java.lang.String type;
	
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
	
	private byte[] photo1;
	
	private byte[] photo2;
	
	private byte[] photo3;
	
	private java.lang.String photo1url;
	
	private java.lang.String photo2url;
	
	private java.lang.String photo3url;
	
	private java.lang.String sqqk;
	//columns END

	 

	public Kss_TmsqModel(){
	}
	public Kss_TmsqModel(String id) {
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
	public java.lang.String getRybh() {
		return this.rybh;
	}
	
	public void setRybh(java.lang.String value) {
		this.rybh = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getSm() {
		return this.sm;
	}
	
	public void setSm(java.lang.String value) {
		this.sm = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
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
	
	public byte[] getPhoto1() {
		return this.photo1;
	}
	
	public void setPhoto1(byte[] value) {
		this.photo1 = value;
	}
	public byte[] getPhoto2() {
		return this.photo2;
	}
	
	public void setPhoto2(byte[] value) {
		this.photo2 = value;
	}
	public byte[] getPhoto3() {
		return this.photo3;
	}
	
	public void setPhoto3(byte[] value) {
		this.photo3 = value;
	}
	public java.lang.String getPhoto1url() {
		return this.photo1url;
	}
	
	public void setPhoto1url(java.lang.String value) {
		this.photo1url = value;
	}
	public java.lang.String getPhoto2url() {
		return this.photo2url;
	}
	
	public void setPhoto2url(java.lang.String value) {
		this.photo2url = value;
	}
	public java.lang.String getPhoto3url() {
		return this.photo3url;
	}
	
	public void setPhoto3url(java.lang.String value) {
		this.photo3url = value;
	}
	public java.lang.String getSqqk() {
		return this.sqqk;
	}
	
	public void setSqqk(java.lang.String value) {
		this.sqqk = value;
	}
	 
}

