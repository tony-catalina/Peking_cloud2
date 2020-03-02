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


public class Logs_CloudfileModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String rybh;
	
	private java.lang.String fdir;
	
	private java.lang.String dir;
	
	private java.lang.String filename;
	
	private java.lang.String filetype;
	
	private java.lang.String fileicon;
	
	private java.lang.String scbz;
	
	private java.lang.String share;
	
	private java.lang.String isdir;
	
	private java.lang.String uploader;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date uptime;
	
	private java.lang.Double downnum;
	
	private java.lang.String bz;
	
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
	
	private java.lang.String groupname;
	
	private java.lang.String remotefilename;
	//columns END

	 

	public Logs_CloudfileModel(){
	}
	public Logs_CloudfileModel(String id) {
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
	public java.lang.String getFdir() {
		return this.fdir;
	}
	
	public void setFdir(java.lang.String value) {
		this.fdir = value;
	}
	public java.lang.String getDir() {
		return this.dir;
	}
	
	public void setDir(java.lang.String value) {
		this.dir = value;
	}
	public java.lang.String getFilename() {
		return this.filename;
	}
	
	public void setFilename(java.lang.String value) {
		this.filename = value;
	}
	public java.lang.String getFiletype() {
		return this.filetype;
	}
	
	public void setFiletype(java.lang.String value) {
		this.filetype = value;
	}
	public java.lang.String getFileicon() {
		return this.fileicon;
	}
	
	public void setFileicon(java.lang.String value) {
		this.fileicon = value;
	}
	public java.lang.String getScbz() {
		return this.scbz;
	}
	
	public void setScbz(java.lang.String value) {
		this.scbz = value;
	}
	public java.lang.String getShare() {
		return this.share;
	}
	
	public void setShare(java.lang.String value) {
		this.share = value;
	}
	public java.lang.String getIsdir() {
		return this.isdir;
	}
	
	public void setIsdir(java.lang.String value) {
		this.isdir = value;
	}
	public java.lang.String getUploader() {
		return this.uploader;
	}
	
	public void setUploader(java.lang.String value) {
		this.uploader = value;
	}
	
	public java.util.Date getUptime() {
		return this.uptime;
	}
	
	public void setUptime(java.util.Date value) {
		this.uptime = value;
	}
	
	public java.lang.Double getDownnum() {
		return this.downnum;
	}
	
	public void setDownnum(java.lang.Double value) {
		this.downnum = value;
	}
	public java.lang.String getBz() {
		return this.bz;
	}
	
	public void setBz(java.lang.String value) {
		this.bz = value;
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
	
	public java.lang.String getGroupname() {
		return this.groupname;
	}
	
	public void setGroupname(java.lang.String value) {
		this.groupname = value;
	}
	public java.lang.String getRemotefilename() {
		return this.remotefilename;
	}
	
	public void setRemotefilename(java.lang.String value) {
		this.remotefilename = value;
	}
	 
}

