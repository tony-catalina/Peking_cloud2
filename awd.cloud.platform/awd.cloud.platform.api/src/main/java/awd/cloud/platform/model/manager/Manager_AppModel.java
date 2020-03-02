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


public class Manager_AppModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String appcode;
	
	private java.lang.String name;
	
	private java.lang.String version;
	
	private java.lang.String type;
	
	private java.lang.String zone;
	
	private java.lang.String url;
	
	private java.lang.String memo;
	
	private java.lang.String pic1;
	
	private java.lang.String pic2;
	
	private java.lang.String pic3;
	
	private java.lang.String flag;
	
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
	
	private java.lang.String role;
	
	private java.lang.String fruit;
	
	private java.lang.String icon;
	
	private byte[] p1;
	
	private byte[] p2;
	
	private byte[] p3;
	
	private java.lang.String exeurl;
	
	private byte[] exefile;
	
	private java.lang.String sysversion;
	
	private java.lang.String browversion;
	//columns END

	 

	public Manager_AppModel(){
	}
	public Manager_AppModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getAppcode() {
		return this.appcode;
	}
	
	public void setAppcode(java.lang.String value) {
		this.appcode = value;
	}
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.String getZone() {
		return this.zone;
	}
	
	public void setZone(java.lang.String value) {
		this.zone = value;
	}
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	public java.lang.String getMemo() {
		return this.memo;
	}
	
	public void setMemo(java.lang.String value) {
		this.memo = value;
	}
	public java.lang.String getPic1() {
		return this.pic1;
	}
	
	public void setPic1(java.lang.String value) {
		this.pic1 = value;
	}
	public java.lang.String getPic2() {
		return this.pic2;
	}
	
	public void setPic2(java.lang.String value) {
		this.pic2 = value;
	}
	public java.lang.String getPic3() {
		return this.pic3;
	}
	
	public void setPic3(java.lang.String value) {
		this.pic3 = value;
	}
	public java.lang.String getFlag() {
		return this.flag;
	}
	
	public void setFlag(java.lang.String value) {
		this.flag = value;
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
	
	public java.lang.String getRole() {
		return this.role;
	}
	
	public void setRole(java.lang.String value) {
		this.role = value;
	}
	public java.lang.String getFruit() {
		return this.fruit;
	}
	
	public void setFruit(java.lang.String value) {
		this.fruit = value;
	}
	public java.lang.String getIcon() {
		return this.icon;
	}
	
	public void setIcon(java.lang.String value) {
		this.icon = value;
	}
	public byte[] getP1() {
		return this.p1;
	}
	
	public void setP1(byte[] value) {
		this.p1 = value;
	}
	public byte[] getP2() {
		return this.p2;
	}
	
	public void setP2(byte[] value) {
		this.p2 = value;
	}
	public byte[] getP3() {
		return this.p3;
	}
	
	public void setP3(byte[] value) {
		this.p3 = value;
	}
	public java.lang.String getExeurl() {
		return this.exeurl;
	}
	
	public void setExeurl(java.lang.String value) {
		this.exeurl = value;
	}
	public byte[] getExefile() {
		return this.exefile;
	}
	
	public void setExefile(byte[] value) {
		this.exefile = value;
	}
	public java.lang.String getSysversion() {
		return this.sysversion;
	}
	
	public void setSysversion(java.lang.String value) {
		this.sysversion = value;
	}
	public java.lang.String getBrowversion() {
		return this.browversion;
	}
	
	public void setBrowversion(java.lang.String value) {
		this.browversion = value;
	}
	 
}

