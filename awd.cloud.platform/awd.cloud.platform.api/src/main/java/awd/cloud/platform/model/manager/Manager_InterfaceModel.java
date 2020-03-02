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


public class Manager_InterfaceModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String interfaceName;
	
	private java.lang.String interfaceDescription;
	
	private java.lang.String interfaceType;
	
	private java.lang.String interfaceId;
	
	private java.lang.String method;
	
	private java.lang.String sfqy;
	
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

	private java.lang.String kfzd;

	private java.lang.String tablename;

	public Manager_InterfaceModel(){
	}
	public Manager_InterfaceModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getInterfaceName() {
		return this.interfaceName;
	}
	
	public void setInterfaceName(java.lang.String value) {
		this.interfaceName = value;
	}
	public java.lang.String getInterfaceDescription() {
		return this.interfaceDescription;
	}
	
	public void setInterfaceDescription(java.lang.String value) {
		this.interfaceDescription = value;
	}
	public java.lang.String getInterfaceType() {
		return this.interfaceType;
	}
	
	public void setInterfaceType(java.lang.String value) {
		this.interfaceType = value;
	}
	public java.lang.String getInterfaceId() {
		return this.interfaceId;
	}
	
	public void setInterfaceId(java.lang.String value) {
		this.interfaceId = value;
	}
	public java.lang.String getMethod() {
		return this.method;
	}
	
	public void setMethod(java.lang.String value) {
		this.method = value;
	}
	public java.lang.String getSfqy() {
		return this.sfqy;
	}
	
	public void setSfqy(java.lang.String value) {
		this.sfqy = value;
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
	public java.lang.String getKfzd() {
		return kfzd;
	}
	public void setKfzd(java.lang.String kfzd) {
		this.kfzd = kfzd;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}

