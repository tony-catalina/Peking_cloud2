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


public class Kss_AqjcModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jcxs;
	
	private java.lang.Byte ksscjrs;
	
	private java.lang.Byte wjcjrs;
	
	private java.lang.Byte jskcjrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date sj;
	
	private java.lang.String fzr;
	
	private java.lang.String zsjcry;
	
	private java.lang.String fw;
	
	private java.lang.String nr;
	
	private java.lang.String jcjg;
	
	private java.lang.String cljg;
	
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
	//columns END

	 

	public Kss_AqjcModel(){
	}
	public Kss_AqjcModel(String id) {
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
	public java.lang.String getJcxs() {
		return this.jcxs;
	}
	
	public void setJcxs(java.lang.String value) {
		this.jcxs = value;
	}
	public java.lang.Byte getKsscjrs() {
		return this.ksscjrs;
	}
	
	public void setKsscjrs(java.lang.Byte value) {
		this.ksscjrs = value;
	}
	public java.lang.Byte getWjcjrs() {
		return this.wjcjrs;
	}
	
	public void setWjcjrs(java.lang.Byte value) {
		this.wjcjrs = value;
	}
	public java.lang.Byte getJskcjrs() {
		return this.jskcjrs;
	}
	
	public void setJskcjrs(java.lang.Byte value) {
		this.jskcjrs = value;
	}
	
	public java.util.Date getSj() {
		return this.sj;
	}
	
	public void setSj(java.util.Date value) {
		this.sj = value;
	}
	
	public java.lang.String getFzr() {
		return this.fzr;
	}
	
	public void setFzr(java.lang.String value) {
		this.fzr = value;
	}
	public java.lang.String getZsjcry() {
		return this.zsjcry;
	}
	
	public void setZsjcry(java.lang.String value) {
		this.zsjcry = value;
	}
	public java.lang.String getFw() {
		return this.fw;
	}
	
	public void setFw(java.lang.String value) {
		this.fw = value;
	}
	public java.lang.String getNr() {
		return this.nr;
	}
	
	public void setNr(java.lang.String value) {
		this.nr = value;
	}
	public java.lang.String getJcjg() {
		return this.jcjg;
	}
	
	public void setJcjg(java.lang.String value) {
		this.jcjg = value;
	}
	public java.lang.String getCljg() {
		return this.cljg;
	}
	
	public void setCljg(java.lang.String value) {
		this.cljg = value;
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
	
	 
}

