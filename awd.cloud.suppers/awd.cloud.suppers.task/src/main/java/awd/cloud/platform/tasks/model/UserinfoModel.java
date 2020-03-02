/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.tasks.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.platform.tasks.utils.Model;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserinfoModel implements Model {
	
	private java.lang.String id;
	private java.lang.String userid;
	private java.lang.String usertype;
	private java.lang.String usertypeString;
	private java.lang.String jsbh;
	private java.lang.String jsbhString;
	private java.lang.String loginname;
	private java.lang.String loginpass;
	private java.lang.String sfzh;
	private java.lang.String jh;
	private java.lang.String email;
	private java.lang.String realname;
	private java.lang.String glybz;
	private java.lang.String glyzbString;
	private java.lang.String spr;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date spsj;
	private java.lang.String spsjString;
	private java.lang.String spbz;
    private java.lang.String spbzString;
	private java.lang.String state;
	private java.lang.String stateString;
	private java.lang.String creator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date createtime;
	private java.lang.String createtimeString;
	private java.lang.String updator;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date updatetime;
    private java.lang.String updatetimeString;
	//columns END


	public UserinfoModel(){
	}

	public UserinfoModel(
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
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUsertype() {
		return this.usertype;
	}
	
	public void setUsertype(java.lang.String value) {
		this.usertype = value;
	}
	
	public java.lang.String getJsbh() {
		return this.jsbh;
	}
	
	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}
	
	public java.lang.String getLoginname() {
		return this.loginname;
	}
	
	public void setLoginname(java.lang.String value) {
		this.loginname = value;
	}
	
	public java.lang.String getLoginpass() {
		return this.loginpass;
	}
	
	public void setLoginpass(java.lang.String value) {
		this.loginpass = value;
	}
	
	public java.lang.String getSfzh() {
		return this.sfzh;
	}
	
	public void setSfzh(java.lang.String value) {
		this.sfzh = value;
	}
	
	public java.lang.String getJh() {
		return this.jh;
	}
	
	public void setJh(java.lang.String value) {
		this.jh = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getRealname() {
		return this.realname;
	}
	
	public void setRealname(java.lang.String value) {
		this.realname = value;
	}
	
	public java.lang.String getGlybz() {
		return this.glybz;
	}
	
	public void setGlybz(java.lang.String value) {
		this.glybz = value;
	}
	
	public java.lang.String getSpr() {
		return this.spr;
	}
	
	public void setSpr(java.lang.String value) {
		this.spr = value;
	}
	
	
	public java.util.Date getSpsj() {
		return this.spsj;
	}
	
	public void setSpsj(java.util.Date value) {
		this.spsj = value;
	}
	
	public java.lang.String getSpbz() {
		return this.spbz;
	}
	
	public void setSpbz(java.lang.String value) {
		this.spbz = value;
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

	public java.lang.String getUsertypeString() {
		return usertypeString;
	}

	public void setUsertypeString(java.lang.String usertypeString) {
		this.usertypeString = usertypeString;
	}

	public java.lang.String getSpsjString() {
		return spsjString;
	}

	public void setSpsjString(java.lang.String spsjString) {
		this.spsjString = spsjString;
	}

	public java.lang.String getSpbzString() {
		return spbzString;
	}

	public void setSpbzString(java.lang.String spbzString) {
		this.spbzString = spbzString;
	}

	public java.lang.String getStateString() {
		return stateString;
	}

	public void setStateString(java.lang.String stateString) {
		this.stateString = stateString;
	}

	public java.lang.String getCreatetimeString() {
		return createtimeString;
	}

	public void setCreatetimeString(java.lang.String createtimeString) {
		this.createtimeString = createtimeString;
	}

	public java.lang.String getUpdatetimeString() {
		return updatetimeString;
	}

	public void setUpdatetimeString(java.lang.String updatetimeString) {
		this.updatetimeString = updatetimeString;
	}

	public java.lang.String getGlyzbString() {
		return glyzbString;
	}

	public void setGlyzbString(java.lang.String glyzbString) {
		this.glyzbString = glyzbString;
	}

	public java.lang.String getJsbhString() {
		return jsbhString;
	}

	public void setJsbhString(java.lang.String jsbhString) {
		this.jsbhString = jsbhString;
	}
	
	
	
}

