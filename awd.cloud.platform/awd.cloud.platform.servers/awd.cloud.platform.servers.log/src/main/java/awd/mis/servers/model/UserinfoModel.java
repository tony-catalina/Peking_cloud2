/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


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
	private java.util.Date spsj;
	private java.lang.String spsjString;
	private java.lang.String spbz;
    private java.lang.String spbzString;
	private java.lang.String state;
	private java.lang.String stateString;
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

