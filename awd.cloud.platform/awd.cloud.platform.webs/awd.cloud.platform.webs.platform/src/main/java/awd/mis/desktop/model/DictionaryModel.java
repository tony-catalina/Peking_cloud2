/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.desktop.model;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 
 * @version 1.0
 * @since 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryModel implements Model{
	private java.lang.String id;
	private java.lang.String jslx;
	private java.lang.String fieldname;
	private java.lang.String code;
	private java.lang.String content;
	private java.lang.String py;
	private java.lang.String isgb;
	private java.math.BigDecimal sypl;
	private java.lang.String editable;
	private java.lang.String creator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date   createtime;
	private java.lang.String updator;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")  
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date   updatetime;
	//columns END
	private java.lang.String fieldnameString;
	private java.lang.String jslxString;
	private java.lang.String isgbString;
	private java.lang.String editableString;
	

	public DictionaryModel(){
	}
	public DictionaryModel(String id) {
		this.id = id;
	}
	

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getJslx() {
		return this.jslx;
	}
	
	public void setJslx(java.lang.String value) {
		this.jslx = value;
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
	public java.lang.Object getCreatetime() {
		return this.createtime;
	}
	

	public java.lang.String getUpdator() {
		return this.updator;
	}
	
	public void setUpdator(java.lang.String value) {
		this.updator = value;
	}
	public java.lang.Object getUpdatetime() {
		return this.updatetime;
	}
	public java.lang.String getJslxString() {
		return jslxString;
	}
	public void setJslxString(java.lang.String jslxString) {
		this.jslxString = jslxString;
	}
	public java.lang.String getIsgbString() {
		return isgbString;
	}
	public void setIsgbString(java.lang.String isgbString) {
		this.isgbString = isgbString;
	}
	public java.lang.String getEditableString() {
		return editableString;
	}
	public void setEditableString(java.lang.String editableString) {
		this.editableString = editableString;
	}
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	public void setUpdatetime(java.util.Date updatetime) {
		this.updatetime = updatetime;
	}
	public java.lang.String getFieldnameString() {
		return fieldnameString;
	}
	public void setFieldnameString(java.lang.String fieldnameString) {
		this.fieldnameString = fieldnameString;
	}
	
	
	
}

