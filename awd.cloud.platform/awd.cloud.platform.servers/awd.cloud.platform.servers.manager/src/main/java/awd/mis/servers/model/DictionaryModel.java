/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.model;

import awd.framework.common.model.Model;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */


public class DictionaryModel implements Model  {
	//columns START
	private java.lang.String id;
	private java.lang.String fieldname;
	private java.lang.String code;
	private java.lang.String content;
	private java.lang.String py;
	private java.lang.String isgb;
	private java.math.BigDecimal sypl;
	private java.lang.String editable;
	private java.lang.String creator;
	private java.util.Date createtime;
	private java.lang.String updator;
	private java.util.Date updatetime;
	private java.lang.String jslx;
	//columns END

	public DictionaryModel(){
	}

	public DictionaryModel(
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
	
	public java.lang.String getFieldname() {
		return this.fieldname;
	}
	
	public void setFieldname(java.lang.String value) {
		this.fieldname = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	
	public java.lang.String getPy() {
		return this.py;
	}
	
	public void setPy(java.lang.String value) {
		this.py = value;
	}
	
	public java.lang.String getIsgb() {
		return this.isgb;
	}
	
	public void setIsgb(java.lang.String value) {
		this.isgb = value;
	}
	
	public java.math.BigDecimal getSypl() {
		return this.sypl;
	}
	
	public void setSypl(java.math.BigDecimal value) {
		this.sypl = value;
	}
	
	public java.lang.String getEditable() {
		return this.editable;
	}
	
	public void setEditable(java.lang.String value) {
		this.editable = value;
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
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
	}
	
}

