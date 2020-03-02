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

public class UpdatelogsModel implements Model {

	private java.lang.String jsbh;
	private java.lang.String username;
	private java.lang.String servername;
	private java.lang.String httpheads;
	private java.lang.String httpmethod;
	private java.lang.String url;
	private java.lang.String response;
	private java.lang.String operator;
	private java.util.Date optime;
	private java.lang.String opip;
	private java.lang.String creator;
	// columns END

	public UpdatelogsModel() {
	}


	public java.lang.String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}

	public java.lang.String getUsername() {
		return this.username;
	}

	public void setUsername(java.lang.String value) {
		this.username = value;
	}

	

	public java.lang.String getServername() {
		return servername;
	}


	public void setServername(java.lang.String servername) {
		this.servername = servername;
	}


	public java.lang.String getHttpheads() {
		return httpheads;
	}


	public void setHttpheads(java.lang.String httpheads) {
		this.httpheads = httpheads;
	}


	public java.lang.String getHttpmethod() {
		return httpmethod;
	}


	public void setHttpmethod(java.lang.String httpmethod) {
		this.httpmethod = httpmethod;
	}


	public java.lang.String getUrl() {
		return url;
	}


	public void setUrl(java.lang.String url) {
		this.url = url;
	}


	public java.lang.String getResponse() {
		return response;
	}


	public void setResponse(java.lang.String response) {
		this.response = response;
	}


	public java.lang.String getCreator() {
		return creator;
	}


	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}


	public java.lang.String getOperator() {
		return this.operator;
	}

	public void setOperator(java.lang.String value) {
		this.operator = value;
	}


	public java.util.Date getOptime() {
		return this.optime;
	}

	public void setOptime(java.util.Date value) {
		this.optime = value;
	}

	public java.lang.String getOpip() {
		return this.opip;
	}

	public void setOpip(java.lang.String value) {
		this.opip = value;
	}

}
