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


public class Manager_FlowmapModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String mapname;
	
	private java.lang.String mapkey;
	
	private java.lang.String version;
	
	private java.lang.String mapmutex;
	
	private java.lang.String timelimit;
	
	private java.lang.Byte monthlylimit;
	
	private java.lang.String memo;
	
	private java.lang.String flag;
	
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
	
	private java.lang.String menu;
	//columns END

	 

	public Manager_FlowmapModel(){
	}
	public Manager_FlowmapModel(String id) {
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
	public java.lang.String getMapname() {
		return this.mapname;
	}
	
	public void setMapname(java.lang.String value) {
		this.mapname = value;
	}
	public java.lang.String getMapkey() {
		return this.mapkey;
	}
	
	public void setMapkey(java.lang.String value) {
		this.mapkey = value;
	}
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	public java.lang.String getMapmutex() {
		return this.mapmutex;
	}
	
	public void setMapmutex(java.lang.String value) {
		this.mapmutex = value;
	}
	public java.lang.String getTimelimit() {
		return this.timelimit;
	}
	
	public void setTimelimit(java.lang.String value) {
		this.timelimit = value;
	}
	public java.lang.Byte getMonthlylimit() {
		return this.monthlylimit;
	}
	
	public void setMonthlylimit(java.lang.Byte value) {
		this.monthlylimit = value;
	}
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
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
	
	public java.lang.String getMenu() {
		return this.menu;
	}
	
	public void setMenu(java.lang.String value) {
		this.menu = value;
	}
	 
}

