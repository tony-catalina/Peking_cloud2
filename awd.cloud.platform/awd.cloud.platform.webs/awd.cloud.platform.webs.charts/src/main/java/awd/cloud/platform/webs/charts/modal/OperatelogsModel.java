/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.platform.webs.charts.modal;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import awd.cloud.platform.webs.charts.utils.Model;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperatelogsModel implements Model {

	private java.lang.String id;
	private java.lang.String jsbh;
	private java.lang.String optype;
	private java.lang.String opcontent;
	private java.lang.String opresult;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private java.util.Date optime;
	private java.lang.String operator;
	private java.lang.String creator;
	// columns END

	public OperatelogsModel() {
	}

	

	public java.lang.String getId() {
		return id;
	}



	public void setId(java.lang.String id) {
		this.id = id;
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
