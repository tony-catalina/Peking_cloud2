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

public class OperatelogsModel implements Model {

	private java.lang.String jsbh;
	private java.lang.String optype;
	private java.lang.String opcontent;
	private java.lang.String opresult;
	private java.util.Date optime;
	private java.lang.String operator;
	private java.lang.String creator;
	// columns END

	public OperatelogsModel() {
	}


	public java.lang.String getJsbh() {
		return this.jsbh;
	}

	public void setJsbh(java.lang.String value) {
		this.jsbh = value;
	}

	public java.lang.String getOptype() {
		return this.optype;
	}

	public void setOptype(java.lang.String value) {
		this.optype = value;
	}


	public java.util.Date getOptime() {
		return this.optime;
	}

	public void setOptime(java.util.Date value) {
		this.optime = value;
	}

	public java.lang.String getOperator() {
		return this.operator;
	}

	public void setOperator(java.lang.String value) {
		this.operator = value;
	}


	public java.lang.String getCreator() {
		return creator;
	}


	public void setCreator(java.lang.String creator) {
		this.creator = creator;
	}


	public java.lang.String getOpcontent() {
		return opcontent;
	}


	public void setOpcontent(java.lang.String opcontent) {
		this.opcontent = opcontent;
	}


	public java.lang.String getOpresult() {
		return opresult;
	}


	public void setOpresult(java.lang.String opresult) {
		this.opresult = opresult;
	}

	

}
