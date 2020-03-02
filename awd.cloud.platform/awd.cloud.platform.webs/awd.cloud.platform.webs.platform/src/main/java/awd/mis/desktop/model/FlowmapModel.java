/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlowmapModel implements Model {
	
	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String mapname;
	private java.lang.String mapkey;
	private java.lang.Integer version;
	private java.lang.String mapmutex;
	private java.lang.String timelimit;
	private java.lang.Integer monthlylimit;
	private java.lang.String memo;
	private java.lang.String flag;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createtime;
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updatetime;
	//columns END


	public FlowmapModel(){
	}

	public FlowmapModel(
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
	
	public java.lang.Integer getMonthlylimit() {
		return this.monthlylimit;
	}
	
	public void setMonthlylimit(java.lang.Integer value) {
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

	public java.lang.String getMapkey() {
		return mapkey;
	}

	public void setMapkey(java.lang.String mapkey) {
		this.mapkey = mapkey;
	}

 

	public java.lang.Integer getVersion() {
		return version;
	}

	public void setVersion(java.lang.Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "FlowmapModel [id=" + id + ", jsbh=" + jsbh + ", mapname=" + mapname + ", mapkey=" + mapkey
				+ ", version=" + version + ", mapmutex=" + mapmutex + ", timelimit=" + timelimit + ", monthlylimit="
				+ monthlylimit + ", memo=" + memo + ", flag=" + flag + ", creator=" + creator + ", createtime="
				+ createtime + ", updator=" + updator + ", updatetime="
				+ updatetime + "]";
	}

	 

 
	
	
}




 