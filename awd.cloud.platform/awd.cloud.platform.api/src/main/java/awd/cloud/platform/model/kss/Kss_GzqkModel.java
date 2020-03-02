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


public class Kss_GzqkModel implements Model{	
	
	//columns START
	
	private java.lang.String id;

	
	private java.lang.String jsbh;
	
	private java.lang.String bbzmbxm;
	
	private java.lang.String bbzmbrybh;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date bzsj;
	
	private java.lang.String sbzrw;
	
	private java.lang.String wcqk;
	
	private java.lang.String bzr;
	
	private java.lang.String bzdx;
	
	private java.lang.String bzdxbh;
	
	private java.lang.String jtsm;
	
	private java.lang.String state;
	
	private java.lang.String yxzt;
	
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

	 

	public Kss_GzqkModel(){
	}
	public Kss_GzqkModel(String id) {
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
	public java.lang.String getBbzmbxm() {
		return this.bbzmbxm;
	}
	
	public void setBbzmbxm(java.lang.String value) {
		this.bbzmbxm = value;
	}
	public java.lang.String getBbzmbrybh() {
		return this.bbzmbrybh;
	}
	
	public void setBbzmbrybh(java.lang.String value) {
		this.bbzmbrybh = value;
	}
	
	public java.util.Date getBzsj() {
		return this.bzsj;
	}
	
	public void setBzsj(java.util.Date value) {
		this.bzsj = value;
	}
	
	public java.lang.String getSbzrw() {
		return this.sbzrw;
	}
	
	public void setSbzrw(java.lang.String value) {
		this.sbzrw = value;
	}
	public java.lang.String getWcqk() {
		return this.wcqk;
	}
	
	public void setWcqk(java.lang.String value) {
		this.wcqk = value;
	}
	public java.lang.String getBzr() {
		return this.bzr;
	}
	
	public void setBzr(java.lang.String value) {
		this.bzr = value;
	}
	public java.lang.String getBzdx() {
		return this.bzdx;
	}
	
	public void setBzdx(java.lang.String value) {
		this.bzdx = value;
	}
	public java.lang.String getBzdxbh() {
		return this.bzdxbh;
	}
	
	public void setBzdxbh(java.lang.String value) {
		this.bzdxbh = value;
	}
	public java.lang.String getJtsm() {
		return this.jtsm;
	}
	
	public void setJtsm(java.lang.String value) {
		this.jtsm = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getYxzt() {
		return this.yxzt;
	}
	
	public void setYxzt(java.lang.String value) {
		this.yxzt = value;
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

