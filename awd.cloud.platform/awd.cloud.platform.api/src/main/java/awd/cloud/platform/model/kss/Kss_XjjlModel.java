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


public class Kss_XjjlModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String type;
	
	private java.lang.String name;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date xjrq;
	
	private java.lang.String xjr;
	
	private java.lang.String ywyc;
	
	private java.lang.String ycqk;
	
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
	
	private java.lang.String dbrzsfzc;
	
	private java.lang.String fsrzsfzc;
	
	private java.lang.String zsjcsbsfzc;
	//columns END

	 

	public Kss_XjjlModel(){
	}
	public Kss_XjjlModel(String id) {
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
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.util.Date getXjrq() {
		return this.xjrq;
	}
	
	public void setXjrq(java.util.Date value) {
		this.xjrq = value;
	}
	
	public java.lang.String getXjr() {
		return this.xjr;
	}
	
	public void setXjr(java.lang.String value) {
		this.xjr = value;
	}
	public java.lang.String getYwyc() {
		return this.ywyc;
	}
	
	public void setYwyc(java.lang.String value) {
		this.ywyc = value;
	}
	public java.lang.String getYcqk() {
		return this.ycqk;
	}
	
	public void setYcqk(java.lang.String value) {
		this.ycqk = value;
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
	
	public java.lang.String getDbrzsfzc() {
		return this.dbrzsfzc;
	}
	
	public void setDbrzsfzc(java.lang.String value) {
		this.dbrzsfzc = value;
	}
	public java.lang.String getFsrzsfzc() {
		return this.fsrzsfzc;
	}
	
	public void setFsrzsfzc(java.lang.String value) {
		this.fsrzsfzc = value;
	}
	public java.lang.String getZsjcsbsfzc() {
		return this.zsjcsbsfzc;
	}
	
	public void setZsjcsbsfzc(java.lang.String value) {
		this.zsjcsbsfzc = value;
	}
	 
}

