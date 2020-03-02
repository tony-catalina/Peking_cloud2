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


public class Kss_ZbkqModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String jh;
	
	private java.lang.String xm;
	
	private java.lang.String zbrs;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date zbsj;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date jbsj;
	
	private java.lang.String bdqk;
	
	private java.lang.String cljs;
	
	private java.lang.String xsjl;
	
	private java.lang.String jjsx;
	
	private java.lang.String bz;
	
	private java.lang.String state;
	
	private java.lang.String jbr;
	
	private java.lang.String jbrs;
	
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

	 

	public Kss_ZbkqModel(){
	}
	public Kss_ZbkqModel(String id) {
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
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	public java.lang.String getXm() {
		return this.xm;
	}
	
	public void setXm(java.lang.String value) {
		this.xm = value;
	}
	public java.lang.String getZbrs() {
		return this.zbrs;
	}
	
	public void setZbrs(java.lang.String value) {
		this.zbrs = value;
	}
	
	public java.util.Date getZbsj() {
		return this.zbsj;
	}
	
	public void setZbsj(java.util.Date value) {
		this.zbsj = value;
	}
	
	
	public java.util.Date getJbsj() {
		return this.jbsj;
	}
	
	public void setJbsj(java.util.Date value) {
		this.jbsj = value;
	}
	
	public java.lang.String getBdqk() {
		return this.bdqk;
	}
	
	public void setBdqk(java.lang.String value) {
		this.bdqk = value;
	}
	public java.lang.String getCljs() {
		return this.cljs;
	}
	
	public void setCljs(java.lang.String value) {
		this.cljs = value;
	}
	public java.lang.String getXsjl() {
		return this.xsjl;
	}
	
	public void setXsjl(java.lang.String value) {
		this.xsjl = value;
	}
	public java.lang.String getJjsx() {
		return this.jjsx;
	}
	
	public void setJjsx(java.lang.String value) {
		this.jjsx = value;
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
	public java.lang.String getJbr() {
		return this.jbr;
	}
	
	public void setJbr(java.lang.String value) {
		this.jbr = value;
	}
	public java.lang.String getJbrs() {
		return this.jbrs;
	}
	
	public void setJbrs(java.lang.String value) {
		this.jbrs = value;
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

