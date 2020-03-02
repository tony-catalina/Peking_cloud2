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


public class Kss_DjglModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String djkt;
	
	private java.lang.String djlx;
	
	private java.lang.String djnr;
	
	private java.lang.String djbm;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date djsj;
	
	private java.lang.String zqjcqk;
	
	private java.lang.String jxjg;
	
	private java.lang.String hjqk;
	
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

	 

	public Kss_DjglModel(){
	}
	public Kss_DjglModel(String id) {
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
	public java.lang.String getDjkt() {
		return this.djkt;
	}
	
	public void setDjkt(java.lang.String value) {
		this.djkt = value;
	}
	public java.lang.String getDjlx() {
		return this.djlx;
	}
	
	public void setDjlx(java.lang.String value) {
		this.djlx = value;
	}
	public java.lang.String getDjnr() {
		return this.djnr;
	}
	
	public void setDjnr(java.lang.String value) {
		this.djnr = value;
	}
	public java.lang.String getDjbm() {
		return this.djbm;
	}
	
	public void setDjbm(java.lang.String value) {
		this.djbm = value;
	}
	
	public java.util.Date getDjsj() {
		return this.djsj;
	}
	
	public void setDjsj(java.util.Date value) {
		this.djsj = value;
	}
	
	public java.lang.String getZqjcqk() {
		return this.zqjcqk;
	}
	
	public void setZqjcqk(java.lang.String value) {
		this.zqjcqk = value;
	}
	public java.lang.String getJxjg() {
		return this.jxjg;
	}
	
	public void setJxjg(java.lang.String value) {
		this.jxjg = value;
	}
	public java.lang.String getHjqk() {
		return this.hjqk;
	}
	
	public void setHjqk(java.lang.String value) {
		this.hjqk = value;
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

